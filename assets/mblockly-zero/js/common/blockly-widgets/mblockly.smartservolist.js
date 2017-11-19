/**
 * Copyright 2015 Makeblock
 * Author: Wangyu
 * Description: provide an slider interface
 * to input numbers between 0-255;
 *
 */

'use strict';

goog.provide('MBlockly.SmartServoList');

goog.require('Blockly.Blocks');

MBlockly.SmartServoList = function(text, opt_changeHandler) {
  MBlockly.SmartServoList.superClass_.constructor.call(this, text);

  // 定义舵机序号下拉选框的可选数值
  this.indexOptions = [];
  for(var i = 1; i <= 12; i++) {
    this.indexOptions.push(i);
  }
  // 定义速度下拉选框的可选数值
  this.speedOptions = [10,20,30,40,50];

  text = text.slice(0,6);

  // 设置默认值
  this.text = text; // block的id
  this.setText(text);
  this.initData(text);
};
goog.inherits(MBlockly.SmartServoList, Blockly.FieldTextInput);

/**
 * Clone this FieldTextInput.
 * @return {!Blockly.FieldTextInput} The result of calling the constructor again
 *   with the current values of the arguments used during construction.
 */
MBlockly.SmartServoList.prototype.clone = function() {
  return new MBlockly.SmartServoList(this.getText(), this.changeHandler_);
};

Blockly.Blocks['smart_servo_list'] = {
  /**
   * Block for numeric value.
   * @this Blockly.Block
   */
  init: function() {
    this.setHelpUrl(Blockly.Msg.MATH_NUMBER_HELPURL);
    this.appendDummyInput()
        .appendField(new MBlockly.SmartServoList('list'), 'NUM');
    this.setOutput(true, 'SmartServo');
    this.setTooltip(Blockly.Msg.MATH_NUMBER_TOOLTIP);
  }
};

MBlockly.SmartServoList.prototype.initData = function(blockId) {
  var data = MBlockly.Data.getOne(MBlockly.App.currentWidget.id)[blockId];
  if(!data) {
    data = [
      {
        index: 1,
        angle: 10,
        speed: 30
      },
      {
        index: 2,
        angle: 10,
        speed: 30
      },
      {
        index: 3,
        angle: 10,
        speed: 30
      },
      {
        index: 4,
        angle: 10,
        speed: 30
      },
      {
        index: 5,
        angle: 10,
        speed: 30
      },
      {
        index: 6,
        angle: 10,
        speed: 30
      },
    ];
  }
  this.data = data;
  this.saveData();
};

MBlockly.SmartServoList.prototype.saveData = function() {
  MBlockly.Data.updateData(MBlockly.App.currentWidget.id, this.text, this.data);
};


MBlockly.SmartServoList.prototype.showEditor_ = function(opt_quietInput) {
    // this is mostly copied from google library;
    // not a really good practice, but clean.

  this.initData(this.text);

  var quietInput = opt_quietInput || false;
  var self = this;

  var header =  '<div class="header">' +
                  '<div class="icon icon-add"></div>' +
                '</div>';

  var listHtml = "";
  for(var i in this.data) {
    var itemData = this.data[i];
    var itemHtml = this.generateItemHtmlStr(itemData);
    listHtml += itemHtml;
  }

  var content = '<div class="content">' +
                  '<table>' +
                    '<tbody>' +
                      '<tr>' +
                        '<th>舵机序号</th>' +
                        '<th>角度</th>' +
                        '<th>速度</th>' +
                        '<th>操作</th>' +
                      '</tr>' +
                      listHtml +
                    '</tbody>' +
                  '</table>' +
                '</div>';

  var footer = '<div class="footer"><button id="btn-ok"></button></div>';
  var renderHtml = '<div class="smart-servo-list">'+ header + content + footer +'</div>';
  // show widget
  this.showWidgetWithContent(renderHtml, 'smart-servo');
  $('.apply-all').first().show();
  this.registerEvents();
};

MBlockly.SmartServoList.prototype.registerEvents = function() {
  var that = this;
  var eventType = getEventType();

  for(var i in this.data) {
    var itemData = this.data[i];
    var id = '#angle' + itemData.index;
    this.renderRoundSlider(id, itemData.index, itemData.angle);
  }

  $('.icon-add').on(eventType, function() {
    // 增加一个新条目
    var itemData = {};
    itemData.index = that.data.length + 1;
    itemData.angle = 30;
    itemData.speed = 30;
    that.data.push(itemData);
    that.saveData();
    var itemHtmlStr = that.generateItemHtmlStr(itemData);
    $('.smart-servo-list .content table tbody').append($(itemHtmlStr));

    var servoId = '#angle' + itemData.index;
    that.renderRoundSlider(servoId, itemData.index, itemData.angle);
  });

  $('#btn-ok').on('click', function(){
    that.hideWidget();
  });

  $('.apply-all').on('click', function(e){
    var angle = parseInt($(this).parent().parent().find('.angle .rs-tooltip').text());
    var speed = $(this).parent().parent().find('.speed').val();

    for(var i in that.data) {
      $("#angle" + that.data[i].index).data("roundSlider").setValue(angle);
      that.data[i].angle = angle;
      that.data[i].speed = speed;
    }
    $('.speed').val(speed);
  });

  $('.content table').on('change', '.servo-item .index', function(e){
    var newIndex = $(this).val();
    var $itemDom = $(this).parent().parent();
    var index = $itemDom.attr('data-index');

    for(var i in that.data) {
      if(that.data[i].index == index) {
        that.data[i].index = newIndex;
        that.saveData();
        return;
      }
    }
  });

  $('.content table').on('change', '.servo-item .speed', function(e){
    var newSpeed = $(this).val();
    var $itemDom = $(this).parent().parent();
    var index = $itemDom.attr('data-index');

    for(var i in that.data) {
      if(that.data[i].index == index) {
        that.data[i].speed = newSpeed;
        that.saveData();
        return;
      }
    }
  });

  $('.content table').on(eventType, '.servo-item .delete', function(e){
    var $itemDom = $(this).parent().parent();
    var index = $itemDom.attr('data-index');
    $itemDom.remove();

    // delete Item
    for(var i in that.data) {
      if(that.data[i].index == index && that.data.length > 0) {
        that.data.splice(i, 1);
        that.saveData();
        return;
      }
    }
  });
};

MBlockly.SmartServoList.prototype.renderRoundSlider = function(elementId, index, angle) {
  var that  = this;


  $(elementId).roundSlider({
    radius: 80,
    width: 8,
    handleSize: "+16",
    handleShape: "dot",

    min: -180,
    index: index,
    max: 180,
    step: 5,
    circleShape: "half-top", // circleShape: "half-bottom",
    value: angle,
    sliderType: "min-range",

    change: function (e) {
      // console.log(e.value);
      var newAngle = e.value;
      for(var i in that.data) {
        if(that.data[i].index == e.options.index) {
          that.data[i].angle = newAngle;
          that.saveData();
          return;
        }
      }
    }
  });


  // $('input[type="range"]').rangeslider({
  //   polyfill: true,
  //   onSlideEnd: function(position, value) {
  //     console.log(position + '-' + value);
  //   }
  // });

};

MBlockly.SmartServoList.prototype.generateItemHtmlStr = function(itemData) {
  // 构造舵机序号下拉框
  var indexOptionHtml = "";
  for(var m in this.indexOptions) {
    var optionValue = this.indexOptions[m];
    var selected = "";
    if(optionValue == itemData.index) {
      selected = "selected=true";
    }
    indexOptionHtml += '<option value="' + optionValue + '"' + selected + '">' + optionValue + '</option>';
  }

  // 构造速度下拉框
  var speedOptionHtml = "";
  for(var j in this.speedOptions) {
    var optionValue = this.speedOptions[j];
    var selected = "";
    if(optionValue == itemData.speed) {
      selected = "selected=true";
    }
    speedOptionHtml += '<option value="' + optionValue + '"' + selected + '">' + optionValue + '</option>';
  }

  var itemHtmlStr =  '<tr data-index="' + itemData.index + '" class="servo-item">' +
                    '<td>' +
                      '<select name="servoIndex" class="index">' +
                        indexOptionHtml +
                      '</select>' +
                    '</td>' +
                    '<td>' +
                      '<div class="angle" id="angle' + itemData.index + '"></div>' +
                      // '<input type="range" min="0" max="180" step="1" value="10" data-orientation="vertical">' +
                    '</td>' +
                    '<td>' +
                      '<select name="servoSpeed" class="speed">' +
                        speedOptionHtml +
                      '</select>' +
                    '</td>' +
                    '<td class="opts">' +
                      '<div class="delete"></div>' +
                      '<button class="apply-all">apply all</button>' +
                    '</td>' +
                  '</tr>';

  return itemHtmlStr;
};


