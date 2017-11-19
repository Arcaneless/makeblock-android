/**
 * @description communication width device, iOS, android or others.
 *   send message to device and receive message from the
 * @author Hujinhogn
 * @copyright 2015 Makeblock.cc
 */

/**
 * 数据传输存在几个动作问题：
    1. cp主动发数据给coding界面
        涉及到的接口: sendWidgetValue
    2. block块向cp请求某个组件的值
        (第一层过滤)指令索引index：是发出获取数值请求时带出的，这样能保证接收到的值，是因请求才返回的，用作数据校验
        (第二层过滤)组件id：带入执行块中，是为了获取某个指定组件的值，过滤其他无用的值，在具体的block块获取值后进行判断
        涉及到的接口： requestWidgetValue
    3. 模块输出值给cp上的某个组件
        主要是显示面板
        涉及到的接口: sendValue2Cp
 */

MBlockly = MBlockly || {};
MBlockly.HostInterface = {

};

/* web 调用 native */
extend(MBlockly.HostInterface, {
  // blockly准备就绪，请求加载项目数据
  requestLoadProject: function() {
    var that = this;
    if(!MBlockly.Settings.DEBUG) {
      jsCallNative('requestLoadProject', {}, function(res) {
        MBlockly.Settings.MODE_WEBVIEW_ONLOAD = true;
        var decodeData = decodeURIComponent(res);
        data = JSON.parse(decodeData);
        that.renderProjectData(data);
      });
    } else {
      // in debug mode
      var data = MBlockly.Data.mockData;
      this.renderProjectData(data);

      if($('.test-area')) {
          $('.test-area').show();
      }

      window.TellNative = {
          requestLoadProject: function(){},
          reportCurrentWidget: function(){},
          saveControlPanel: function(){},
          sendValueToWidget: function(){},
          sendViaBluetooth: function(){},
          requestBluetoothReconnect: function(){},
          sendViaBluetoothUnreliably: function(){}
      };
    }
  },

  renderProjectData: function(data) {
    var that = this;
    setTimeout(function() {
      that.tellControlPanelBlocklyIsReady();
    }, 100);

    for(var i in data) {
      if(data[i]) {
          this.widgetAdd(JSON.stringify(data[i]));
      }
    }
  },

  tellControlPanelBlocklyIsReady: function() {
    jsCallNative('blocklyIsReady', {}, function(res) {});
  },

  // 告知cp当前正在编辑哪个组件
  reportCurrentWidget: function(id) {
    jsCallNative('reportCurrentWidget', {"id": id.toString()}, function(res) {});
  },

  // 组件编程数据存储
  saveControlPanel: function() {
    var data = JSON.stringify(widgetList);
    var jsonStr = encodeURIComponent(data);
    jsCallNative('saveControlPanel', {"data": jsonStr}, function(res) {});
  },

  // 提供数据给控制台，例如生成示波器波形等
  sendValue2Cp: function(id, value) {
    var params = {
      "id": id,
      "value": value
    };
    jsCallNative('sendValueToWidget', params, function(res) {});
  },


  // 主动请求相关组件的值
  requestWidgetValue: function(id, callback) {
    var valueWrapper = new ValueWrapper();

    jsCallNative('requestWidgetValue', {"id": id.toString()}, function(res) {
      var res = parseInt(res);
      callback && callback(res);
      valueWrapper.setValue(res);
    });

    return valueWrapper;
  },

  // 主动请求相关组件的名称
  requestWidgetName: function(id, callback) {
    jsCallNative('requestWidgetName', {"id": id.toString()}, function(res) {
      callback && callback(res);
    });
  },

  _bluetoothAvailableCheckPassed: function() {
    if (MBlockly.Settings.DEBUG) {
      return true;
    }
    var openBluetoothCheck = true; // 是否开启蓝牙检测
    if (openBluetoothCheck) {
      if (MBlockly.Control.bluetoothConnected) {
        return true;
      } else {
        if (MBlockly.Control.bleLastTimeConnected) {
          this.sendBlueReconnectRequest();
          MBlockly.Control.bleLastTimeConnected = false;
          throw "BleDisconnected";
        }
      }
    } else {
      return true;
    }
    return false;
  },

  sendBluetoothRequest: function(dataArray) {
    var dataStr = dataArray.join(' ');
    if (this._bluetoothAvailableCheckPassed()) {
      jsCallNative('sendViaBluetooth', {"cmdStr": dataStr}, function(res) {});
    }
  },

  sendBluetoothRequestUnrelibly: function(dataArray) {
    console.log('【send bluetooth ulybly】：' + intArrayToHexArray(dataArray).join(" "));
    var dataStr = dataArray.join(' ');
    if (this._bluetoothAvailableCheckPassed()) {
      if (!MBlockly.Settings.DEBUG) {
        // TellNative.sendViaBluetoothUnreliably(dataStr);
        jsCallNative('sendViaBluetoothUnreliably', {"cmdStr": dataStr}, function(res) {});
      }
    }
  },

  sendBlueReconnectRequest: function(dataArray) {
    jsCallNative('requestBluetoothReconnect', {}, function(res) {});
  },
});

/* native 直接调用 web */
extend(MBlockly.HostInterface, {

  // cp上新增组件
  widgetAdd: function(jsonStr) {
    if (jsonStr) {
      var that = this;
      var data = JSON.parse(jsonStr);
      data.code = decodeURIComponent(data.code);
      data.icon = MBlockly.Settings.UI_WIDGET_ICON_PATH + "widget-" + data.xib + ".png";
      MBlockly.Data.add(data);

      // TOFIX: hack for module do not save auto when added into the panel.
      setTimeout(function() {
        that.saveControlPanel();
      }, 50);
    }
  },

  // cp上删除组件
  widgetDelete: function(widgetId) {
    MBlockly.Data.deleteData(widgetId);
    this.saveControlPanel();
  },

  // cp上更新组件，比如改slider的名字，更新port口
  widgetUpdate: function(id, info) {
    var data = info;
    var targetWidget = MBlockly.Data.getOne(id);
    for (var i in data) {
      if (data[i]) {
        // 用slot口代替port口
        if (data.port == "0") {
          data.port = data.slot;
        }
        MBlockly.Data.updateData(id, i, data[i]);
      }
    }
    MBlockly.App.widgetOnLoad(id);
    this.saveControlPanel();
  },

  // 操作cp上的组件，实时传值给 program
  sendWidgetValue: function(id, value) {
    MBlockly.WhenEvent.activateWhenBlocks(id, value);
  },

  switchToCodingPanel: function(id) {
    MBlockly.Settings.MODE_IN_CODING = true;
    MBlockly.Settings.OPEN_HIGHLIGHT = true;
    MBlockly.App.widgetOnLoad(id);
    this.exitPlayMode();
  },

  switchToControlPanel: function() {
    MBlockly.Settings.OPEN_HIGHLIGHT = true;
    MBlockly.Settings.MODE_IN_CODING = false;
    // 提交所有编码数据给controlPanel
    this.saveControlPanel();
  },

  // 开始运行代码，当在 cp 的 play 界面开始定时执行 when star 模块的代码，pause 和 edit 界面停止各定时器
  enterPlayMode: function() {
    MBlockly.Settings.OPEN_INFO_TUEN_WITH_APP = true;
    if (MBlockly.Settings.MODE_IN_CODING) {
      MBlockly.WhenEvent.runWhenStarts();
    } else {
      setTimeout(function() {
        MBlockly.WhenEvent.runWhenStarts();
      }, 500);
    }
  },

  // 停止运行代码
  exitPlayMode: function() {
    MBlockly.Settings.OPEN_INFO_TUEN_WITH_APP = false;
    if (MBlockly.Settings.MODE_IN_CODING) {
      MBlockly.WhenEvent.stopWhenStarts();
    } else {
      setTimeout(function() {
        MBlockly.WhenEvent.stopWhenStarts();
      }, 400);
    }
  },

  // 通知web主板的类型
  tellMainboardInfo: function(jsonStr) {
    var data = JSON.parse(jsonStr);
    MBlockly.Control.setDeviceInfo(data);
  },

  // 清空webview中的所有有关blockly的数据，因为Android切换项目是不会刷新webview
  resetMblockly: function() {
    this.exitPlayMode();
    MBlockly.App.resetUi();
    MBlockly.Data.resetData();
    // 中断runtime
    // 中断数据重发
    // 重置处理传感器读值的队列
    MBlockly.Control.PromiseList.reset();

  }
});

nativeCallJs('requestSaveControlPanel', function(res, responseCallback) {
  MBlockly.HostInterface.saveControlPanel();
});

nativeCallJs('shake', function(res, responseCallback) {
  MBlockly.Control.tabletLastShakeTime = (new Date()).getTime() / 1000;
});

nativeCallJs('bleconnect', function(res, responseCallback) {
  MBlockly.Control.bluetoothConnected = true;
  MBlockly.Control.bleLastTimeConnected = true;
  MBlockly.Servo && MBlockly.Servo.bluetoothConnect();
});

nativeCallJs('bledisconnect', function(res, responseCallback) {
  MBlockly.Control.bluetoothConnected = false;
});

/**
 *  res: {
 *    leftRight: 1,
 *    forwardBackward: 1
 *  }
 */
nativeCallJs('tilt', function(jsonStr, responseCallback) {
  var json = JSON.parse(jsonStr);
  MBlockly.Control.tabletTiltLeftRightStatus = json.leftRight; // 1:left, -1:right
  MBlockly.Control.tabletTiltForwardBackwardStatus = json.forwardBackward; //1: backward, -1: forward

});

nativeCallJs('widgetAdd', function(jsonStr, responseCallback) {
  MBlockly.HostInterface.widgetAdd(jsonStr);
});

nativeCallJs('widgetDelete', function(widgetId, responseCallback) {
  MBlockly.HostInterface.widgetDelete(widgetId);
});


/**
 *  jsonStr: {
 *    "widgetId": 2,
 *    "changeInfo": {
 *      "port": 2, // key: value
 *      ...
 *    }
 *  }
 */
nativeCallJs('widgetUpdate', function(jsonStr, responseCallback) {
  var json = JSON.parse(jsonStr);
  MBlockly.HostInterface.widgetUpdate(json.widgetId, json.changeInfo);
});

/**
 *  jsonStr: {
 *    "widgetId": 2,
 *    "widgetState": 2,
 *    ...
 *  }
 */
nativeCallJs('sendWidgetValue', function(jsonStr, responseCallback) {
  var json = JSON.parse(jsonStr);
  if (MBlockly.Settings.OPEN_INFO_TUEN_WITH_APP) {
    // widgetId, widgetState
    MBlockly.HostInterface.sendWidgetValue(json.widgetId, json.widgetState);
  }
});

nativeCallJs('switchToControlPanel', function(params, responseCallback) {
  MBlockly.HostInterface.switchToControlPanel();
});

nativeCallJs('switchToCodingPanel', function(widetId, responseCallback) {
  MBlockly.HostInterface.switchToCodingPanel(widetId);
});

nativeCallJs('resetMblockly', function(params, responseCallback) {
  MBlockly.HostInterface.resetMblockly();
});

nativeCallJs('enterPlayMode', function(params, responseCallback) {
  MBlockly.HostInterface.enterPlayMode();
});

nativeCallJs('exitPlayMode', function(params, responseCallback) {
  MBlockly.HostInterface.exitPlayMode();
});

nativeCallJs('tellMainboardInfo', function(jsonStr, responseCallback) {
  MBlockly.HostInterface.tellMainboardInfo(jsonStr);
});

// 接收并解析蓝牙返回数据
nativeCallJs('receiveBluetoothData', function(dataStr, responseCallback) {
  var data = decodeURIComponent(dataStr);
  MBlockly.Control.decodeData(data);
});

nativeCallJs('setLedColor', function(jsonStr, responseCallback) {
  if (jsonStr) {
    var json = JSON.parse(jsonStr);
    MBlockly.Action.setLedColor(json);
  }
});

nativeCallJs('turnOffLed', function(jsonStr, responseCallback) {
  if (jsonStr) {
    var json = JSON.parse(jsonStr);
    MBlockly.Action.turnOffLed(json);
  }
});

nativeCallJs('setTone', function(jsonStr, responseCallback) {
  if (jsonStr) {
    var json = JSON.parse(jsonStr);
    MBlockly.Action.setTone(json);
  }
});

nativeCallJs('getSensorValue', function(jsonStr, responseCallback) {
  if (jsonStr) {
    var json = JSON.parse(jsonStr);
    MBlockly.Action.getSensorValue(json);
  }
});