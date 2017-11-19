/**
 * @fileOverview Comnunicate with neuron engine.
 * @author hujinhong@makeblock.cc(Hyman)
 * @copyright 2016 Makeblock.cc
 */


/* use strict */
MBlockly.Neurons = {
  engine: null,
  servoCache: {},
};

extend(MBlockly.Neurons, {

  /**
   * Neuron engine init.
   */
  initNeurons: function() {
    this.engine = createNeuronsLogicEngine({
      "driver": "makeblockhd"
    });
  },

  setup: function() {
    console.log('-- set up neuron --');
    var data = null;

    this.initNeurons();
    data = this.engine.getActiveBlocks();
    this.blockListChanges(data);

    this.engine.on("blockListChanges", this.blockListChanges);
    this.engine.on("blockStatusChanges", this.blockStatusChanges);
  },

  /**
   * neurons_engine call me to construct blockly data structure.
   *
   * when new neuron item adds or old neuron item be removed, call this method.
   * @param  {[type]} data [description]
   */
  blockListChanges: function(data) {
    // TOIMPROVE: NOT A GOOD WAY TO WAIT DATA LOADING.
    if (data.AIRBLOCK) {
      var airblockLength = data.AIRBLOCK.length;

      console.log("airblock is ready");

      if(typeof jsCallNative != "undefined") {
        jsCallNative('airblockIsReady', { "airblockLength": airblockLength } , function(res) {});
      }

      if (TellNative.airblockIsReady) {
        TellNative.airblockIsReady(airblockLength);
      }
    }

    this.servoCache = {};
    if ($(".neuron-test table")) {
      MBlockly.Neurons.renderNeuronsUi(data);
    }

    // if(MBlockly.ServoRecord) {
    //     // load smartServo module.
    //     MBlockly.ServoRecord.setup();
    // }

    // 智能舵机是 500ms 发一次心跳包，以识别20个舵机为例，蓝牙一个间隔内（20ms-30ms）缓冲区可以容纳的是128个字节，1个心跳包的回包大概是6个字节，
    // 固件理论上最多可以返回20个心跳包回包，对于固件来说，20个回包的处理速度在10ms左右。
    // 在实际应用过程中，在发送心跳包的同时，还会发送blockly控制舵机的协议，固件同样会回包。
    // 为了避免蓝牙缓冲区被回包数撑爆，在运行代码前，将心跳包关闭。在蓝牙初次识别舵机时，才将心跳包打开。
    // 设定 3ms 后自动关闭心跳包
  },

  /**
   * neurons_engine call me to excute blockly code.
   *
   * when neuron status changes, report it to me.
   * @param  {string} ntype neuron item's type.
   * @param  {number} index neuron item's index of the same type.
   * @param  {array} value      neuron's new value.
   */
  blockStatusChanges: function(ntype, index, value) {
    var that = this;
    console.log(value.ANGLE);
    ntype = ntype.replace(" ", "");
    var that = MBlockly.Neurons;
    // update widget's value
    var className = '.' + ntype + '-' + index;

    if (ntype == 'SMART_SERVO') {
      value = value.ANGLE[0];
      that.generateServoCache(index, value);
    }

    if (ntype == 'ACCELEROMETER_GYRO') {
      for (var i = 0; i < value.length; i++) {
        value[i] = value[i].toFixed(2);
      }
    }
    if (!value && value != 0) {
      value = "";
    }
    $(className).text(value.toString());
  },

  generateServoCache: function(index, value) {
    this.servoCache[index] = value;
  },

  /**
   * when block shakes trigger some events on blockly.
   * @param  {string} ntype neuron item's type.
   * @param  {number} index neuron item's index of the same type.
   */
  handshake: function(ntype, index) {
    MBlockly.App.highLightNeuronItem(ntype, index);
  },

  /**
   * Test: render neuron block to front page.
   * @param  {json} blockList block list data.
   */
  renderNeuronsUi: function(blockList) {
    $(".neuron-test table").html("");
    var headStr = "" + "<tr><th>Name</th><th>Index</th><th>Value</th></tr>";
    $(".neuron-test table").append($(headStr));

    for (var i in blockList) {
      var values = blockList[i];
      for (var j = 1; j <= values.length; j++) {

        // set smartServo mode, 最多同时设置6个上报
        if (i == "SMART_SERVO") {
          this.servoCache[j] = "";
          // MBlockly.Servo.setServoReportMode(j, MBlockly.Servo.reportMode);
        }

        i = i.replace(" ", "");
        var className = i + '-' + j;
        var value;
        if (values[j]) {
          value = values[j].toString();
          if (i == "SMART_SERVO") {
            value = values[j].ANGLE.toString(); // 只展示角度值
          }
        } else {
          value = "";
        }
        var itemStr = '<tr><td>' + i + '</td><td>' + j + '</td><td class="' + className + '">' + value + '</td></tr>';
        $(".neuron-test table").append($(itemStr));
      }
    }
  }
});

$(function() {
  setTimeout(function() {
    MBlockly.Neurons.setup();
  }, 1000);
});