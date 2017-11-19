/**
/**
 * Copyright 2015 Makeblock
 * Author: Wangyu
 *
 */

'use strict';

goog.provide('MBlockly.SmartServoSetPanel');

goog.require('Blockly.Blocks');

MBlockly.SmartServoSetPanel = function(panelType, text, opt_changeHandler, blockId) {
  this.panelType = panelType;
  this.validServoData = [];
  this.servoDataTemp_ = [];
  this.currentOpenedIndex = 0;
  this.changeHandler_ = opt_changeHandler;

  this.blockId = blockId;
  // 初始化舵机数据
  this.initServoData();
  //text = '舵机1，舵机2...'
  text = this.getServoArgsInitName(text);
  MBlockly.SmartServoSetPanel.superClass_.constructor.call(this, text);
};

goog.inherits(MBlockly.SmartServoSetPanel, Blockly.FieldTextInput);

/**
 * Clone this FieldTextInput.
 * @return {!Blockly.FieldTextInput} The result of calling the constructor again
 *   with the current values of the arguments used during construction.
 */
MBlockly.SmartServoSetPanel.prototype.clone = function() {
  return new MBlockly.SmartServoSetPanel(this.getText(), this.changeHandler_);
};

MBlockly.SmartServoSetPanel.prototype.save = function() {
  MBlockly.Data.updateData(MBlockly.App.currentWidget.id, this.blockId, this.validServoData);
}

MBlockly.SmartServoSetPanel.prototype.getServoArgsInitName = function(text) {
  var newText = text;
  //已存在舵机数据
  if(this.validServoData.length) {
    newText = this.formateText_();
  }
  return newText;
}

/**
 * [createOneServoData description]
 * @param  {Number} id  舵机编号
 * @param  {Number} angle  舵机转动角度
 * @param  {Number} speed  舵机转速
 * @param  {String} rotate 舵机旋转方向： left, right
 * @param  {hex-color} color  舵机 led 颜色值
 */
MBlockly.SmartServoSetPanel.prototype.createOneServoData = function(id, angle ,speed, rotate, color) {
  return {
    index: id || 1,
    angle: 0,
    speed: 100,
    rotate: Blockly.Msg.SMART_SERVO_PANEL_ROTATE_CLOCKWISE,
    color: MBlockly.SmartServoSetPanel.prototype.servoColors[0]
  }
}

/**
 * 初始化舵机数据
 */
MBlockly.SmartServoSetPanel.prototype.initServoData = function() {
  var data;
  if(MBlockly.App.currentWidget.id){
    data = MBlockly.Data.getOne(MBlockly.App.currentWidget.id)[this.blockId];
  }
  if(!data) {
    this.validServoData = [this.createOneServoData()];
  } else {
    this.validServoData = data;
  }
}

/**
 * 增加数据
 * @param  {Number} index 索引
 */
MBlockly.SmartServoSetPanel.prototype.addServoData = function(id) {
  var data = this.createOneServoData(id);
  this.servoDataTemp_.push(data);
}

/**
 * 查找数据
 * @param  {Number} index 索引
 */
MBlockly.SmartServoSetPanel.prototype.getServoData = function(index) {
  if(typeof index != 'undefined'){
    return this.servoDataTemp_[index];
  }else{
    return this.servoDataTemp_;
  }
}

/**
 * 删除数据
 * @param  {Number} index 索引
 */
MBlockly.SmartServoSetPanel.prototype.deleteServoData = function(index) {
  if(typeof index != 'undefined'){
    this.servoDataTemp_.splice(index, 1); //this.servoDataTemp_
  }
}

/**
 * 修改数据
 * @param  {Number} index
 */
MBlockly.SmartServoSetPanel.prototype.modifyServoData = function (index, data) {
  if(typeof index != 'undefined' && this.servoDataTemp_[index]){
    extend(this.servoDataTemp_[index], data);
  }
}

/**
 * 拷贝一份舵机数据
 */
MBlockly.SmartServoSetPanel.prototype.copyData = function(data) {
  var temp = [];
  for(var i = 0; i < data.length; i++){
    // temp.push(extend({}, data[i]));
    temp.push(JSON.parse(JSON.stringify(data[i])));
  }
  return temp;
}

//舵机最大数量
MBlockly.SmartServoSetPanel.prototype.MAX_NUMBER = 6;

/**
 * 设置 sider 项目的名称序号（即id）
 */
MBlockly.SmartServoSetPanel.prototype.setItemNameId = function () {
  var max = this.MAX_NUMBER;
  var newId = this.servoDataTemp_.length + 1;
  if(newId > max){
    return null;
  }

  var temp = this.getAllServosId();
  for(var j = 1; j < newId; j++){
    if(!temp[j]){
      newId = j;
      break;
    }
  }
  //舵机增加一条数据
  this.addServoData(newId)
  return newId;
}

/**
 * 获取所有舵机的编号 index
 */
MBlockly.SmartServoSetPanel.prototype.getAllServosId = function () {
  var temp = {};
  //数组去重
  for(var i = 0, item; item = this.servoDataTemp_[i]; i++){
    if(item){
      temp[item.index] = true;
    }
  }
  return temp;
}

MBlockly.SmartServoSetPanel.prototype.showEditor_ = function(opt_quietInput) {
  var self = this;
  var maxItem = 24; // PM指定
  //获取 block 块的 title
  var titleField = this.sourceBlock_.getField();
  //拷贝舵机数据
  this.servoDataTemp_ = this.copyData(this.validServoData);
  //初始呈现选择第一个数据
  var dataTemp_ = this.getServoData(0);
  var header =  '<div class="header">' +
                  '<span class="save">'+ Blockly.Msg.SMART_SERVO_PANEL_SAVE +'</span>' +
                  '<span class="title">'+ titleField.text_ +'</span>' +
                  '<span class="close">'+ Blockly.Msg.SMART_SERVO_PANEL_CLOSE +'</span>' +
                '</div>';

  var content = '<div class="content">' +
                  '<div class="sider-list">' +
                    '<div id="addServo" class="plus"><span class="icon-plus"></span></div>' +
                    '<div id="mainSider" class="main-sider">' +
                      this.generateMenu() +
                    '</div>' +
                    '<div id="subSider" class="sub-sider">' +
                      '<div class="sub-list">' +
                        this.generateSubMenu(maxItem) +
                      '</div>' +
                    '</div>' +
                  '</div>' +
                  '<div id="servoPanel" class="panel">' +
                    this.injectComponentsByType(this.panelType, dataTemp_) +
                  '</div>' +
                '</div>';

  var renderHtml = '<div class="smart-servo-panel">'+ header + content +'</div>';
  // show widget
  // this.showWidgetWithContentForSmartServo(renderHtml, 'smart-servo-modal');
  this.showWidget(renderHtml, 'smart-servo-modal');
  //初始化 angle
  this.renderAngleComponent($('#angleBody'), dataTemp_.index, dataTemp_.angle);
  // 注册完菜单事件
  this.menuEvents()
  // 注册 modal 事件
  this.modalEvents();
  // 注册 panel 内容区事件
  this.bindPanelEvents();
  // 注册速度滑杆事件
  this.bindPanelDragEvent();
  // 触发一次，导致联动效应
  // $('#mainSider li').eq(0).trigger(getEventType());
};

MBlockly.SmartServoSetPanel.prototype.showWidget = function(html, classname){
  var htmlStr = this.initModal(html, classname);
  //将 modal 嵌入 WidgetDiv
  Blockly.WidgetDiv.DIV.innerHTML = htmlStr;
  // Blockly.WidgetDiv.addClassAndMask('widget-center');
  Blockly.WidgetDiv.show(this, this.sourceBlock_.RTL, null, true, true);
}

/**
 * 根据 block 块的类型注入对应的组件模块
 * @param  {Block.type} type   block 块的 type
 * @param  {ServoData} dataTemp_ 注入组件的数据
 */
MBlockly.SmartServoSetPanel.prototype.injectComponentsByType = function (type, dataTemp_) {
  var html = '';
  var title_angle   = Blockly.Msg.SMART_SERVO_PANEL_ANGLE_TITLE;
  //角度 速度
  if(type == 'set_angle_speed'){
    var speed = dataTemp_.speed;
    html += '<div class="angle-panel">' +
                '<div class="title">' + title_angle + '</div>' +
                '<div id="angleBody" class="angle-body"></div>' +
                '<div id="trimButton" class="trim-btn-box">' +
                    '<span class="icon-wrapper"><span class="icon-minus"></span></span>' +
                    '<span class="icon-wrapper"><span class="icon-plus"></span></span>' +
                '</div>' +
            '</div>';
    html += '<div class="speed-panel">' + this.getSpeedComponent(speed) + '</div>';
  }
  //旋转方向 速度
  else if(type == 'set_rotate_speed'){
    var speed = dataTemp_.speed;
    var rotate = dataTemp_.rotate;
    html += this.getRotateComponent(rotate);
    html += '<div class="speed-panel">' + this.getSpeedComponent(speed) + '</div>';
  }
  //颜色
  else if(type == 'set_led_color'){
    var color = dataTemp_.color;
    html += this.getColorComponent(color);
  }
  return html;
}

/**
 * 生成侧边栏主菜单
 * 初始状态，生成一条 item
 */
MBlockly.SmartServoSetPanel.prototype.generateMenu = function () {
  var servosData = this.getServoData();
  var liStr = '';
  for (var i = 0, servo = ''; servo = servosData[i]; i++) {
    var index = servo.index;
    liStr += this.generateMenuItem(index, i==0, servosData.length == 1);
  }
  return '<ul id ="itemList">' + liStr + '</ul>';
}

/**
 * 生成一条菜单项目
 * @param  {[type]} index [description]
 */
MBlockly.SmartServoSetPanel.prototype.generateMenuItem = function(index, isSelected, isOnlyOne){
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  return '<li data-index="'+ index +'" class="'+ (isSelected?'selected':'') + '">' +
            '<span class="icon-cancel-wrapper" style="'+(isOnlyOne?'visibility:hidden':'')+'"><span class="icon icon-cancel"></span></span>'+
            '<span class="name">' + (name + index) + '</span>'+
            '<span class="icon-detail-wrapper"><span class="icon icon-detail"></span></span>'+
          '</li>';
}

/**
 * 创建次级菜单
 */
MBlockly.SmartServoSetPanel.prototype.generateSubMenu = function (max) {
  var max = max || this.MAX_NUMBER;
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  var list = [];
  max += 1;
  var temp = this.getAllServosId();
  for (var i = 1; i < max; i++) {
    if(!temp[i]){
      list.push('<p data-index="' + i + '"><span>'+ name + i +'</span></p>');
    }
  }
  return list.join('');
}

/**
 * 格式化 text
 */
MBlockly.SmartServoSetPanel.prototype.formateText_ = function () {
  var text_ = '';
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  text_ += name + this.validServoData[0].index;
  if (this.validServoData.length == 2) {
    text_ += ',' + name + this.validServoData[1].index;
  }else if(this.validServoData.length > 2){
    text_ += ',' + name + this.validServoData[1].index + '...';
  }
  return text_;
}

/**
 * 完成事件回调：
 * 生成有效数据，并删除暂存数据
 */
MBlockly.SmartServoSetPanel.prototype.onOk = function () {
  this.validServoData = this.copyData(this.servoDataTemp_);
  this.save();
  delete this.servoDataTemp_;
}

/**
 * 切换主菜单回调：
 * @param  {Number} index 选中的菜单索引号
 */
MBlockly.SmartServoSetPanel.prototype.onSwitch = function (index) {
  var that = this;
  var angleBody = $('#angleBody');
  var rotateBody = $('#rotateBody');
  var speedBody = $('#speedBody');
  var colorBody = $('#colorBody');
  //当前被选中的侧边栏项目
  this.currentOpenedIndex = index;
  var dataTemp_ = this.getServoData(index);
  //刷新 panel
  if(angleBody.length){
    that.renderAngleComponent(angleBody, dataTemp_.index, dataTemp_.angle);
  }
  if(rotateBody.length){
    var rotate = dataTemp_.rotate;
    this.updateRotateComponent(rotate);
  }
  if(speedBody.length){
    var speed = dataTemp_.speed;
    this.updateSpeedComponent(speed);
  }
  if (colorBody.length) {
    var color = dataTemp_.color;
    this.updateColorComponent(color);
  }
}

//关闭菜单
MBlockly.SmartServoSetPanel.prototype.hideSubSider = function (selected) {
  $('#subSider').hide();
  $('#itemList .icon-detail-wrapper').removeClass('selected');
}
/**
 * 速度模板
 * @param  {Number} selected [description]
 */
MBlockly.SmartServoSetPanel.prototype.getSpeedComponent = function (speed) {
  var title_speed   = Blockly.Msg.SMART_SERVO_PANEL_SPEED_TITLE;
  var html = '';
  html += '<div class="title">' + title_speed + '</div>' +
            '<div id="speedBody" class="speed-body">'+ speedTemplate(speed)+'</div>';
  //速度模板
  function speedTemplate(selected){
    var menuObject = [100, 80, 60, 40, 20, 0];
    var speed_coin_wrapper = '<div class="speed-circle">';
    var speed_coin = '';
    var speed_text_html = '<div class="speed-text">';
    var index = 0, selected_index;
    // for(var speed in menuObject){
    for(var i = 0; i< menuObject.length; i++){
      var speed = menuObject[i];
      if (speed == selected) {
        selected_index = index;
        speed_text_html += '<span class="selected">'+ speed +'</span>';

      }else{
        speed_text_html += '<span>'+ speed +'</span>';
      }

      if(selected_index === 0 || selected_index > 0){//蓝色
        speed_coin += '<span><i class="speed-coin" data-speed="'+ speed +'"></i></span>';
      }else{//灰色
        speed_coin += '<span><i class="speed-coin grey" data-speed="'+ speed +'"></i></span>';
      }
      index++;
    }
    //定位
    var top_height = selected_index / (index -1) * 100;
    speed_coin_wrapper += '<strong class="big-coin" style="top:'+ top_height +'%"></strong>' + speed_coin; //选中的速度节点
    //background color
    var back_bar = '<div class="backbar"><div class="frontbar" style="height: '+ top_height +'%"></div></div>';
    return back_bar + speed_coin_wrapper + '</div>' + speed_text_html+ '</div>';
  }
  return html;
}

MBlockly.SmartServoSetPanel.prototype.updateSpeedComponent = function(speed){
  // var eventType = getEventType();
  // $('#speedBody').find('[data-speed="'+ speed +'"]').trigger(eventType);
  var html = this.getSpeedComponent(speed);
  $('#servoPanel .speed-panel').html(html);
}
/**
 * 旋转方向组件
 * @param  {String} selected 旋转方向: clockwise anticlockwise
 */
MBlockly.SmartServoSetPanel.prototype.getRotateComponent = function (rotate) {
  var title_rotate  = Blockly.Msg.SMART_SERVO_PANEL_ROTATE_TITLE;
  var clockwise     = Blockly.Msg.SMART_SERVO_PANEL_ROTATE_CLOCKWISE;
  var anticlockwise = Blockly.Msg.SMART_SERVO_PANEL_ROTATE_ANTICLOCKWISE;
  var html = '';
  html += '<div class="rotate-panel">' +
              '<div class="title">' + title_rotate + '</div>' +
              '<div class="servo-png"></div>' +
              '<div id="rotateBody" class="rotate-body">'+ rotateTemplate(rotate) +'</div>' +
              '<div class="rotate-text"><span>'+ clockwise +'</span><span>'+ anticlockwise +'</span></div>' +
            '</div>';

  //旋转方向模板
  function rotateTemplate(selected){
    var html_ = '';
    if(selected == clockwise){
      html_ += '<div class="left selected" data-rotate="'+ clockwise+'"><i class="icon icon-left-rotate"></i></div>';
      html_ += '<div class="right" data-rotate="'+ anticlockwise+'"><i class="icon icon-right-rotate"></i></div>';
    }else{
      html_ += '<div class="left" data-rotate="'+ clockwise+'"><i class="icon icon-left-rotate"></i></div>';
      html_ += '<div class="right selected" data-rotate="'+ anticlockwise +'"><i class="icon icon-right-rotate"></i></div>';
    }
    return html_;
  }
  return html;
}

MBlockly.SmartServoSetPanel.prototype.updateRotateComponent = function(rotate){
  var eventType = getEventType();
  $('#rotateBody').find('[data-rotate="'+ rotate +'"]').trigger(eventType);
}

/**
 * 舵机 led 灯颜色
 */
MBlockly.SmartServoSetPanel.prototype.servoColors = ['#ff4d4d', '#ffa94d', '#fdff00', '#5ccf38', '#4dafff', '#834dff', '#eaeaea', '#000000'];

/**
 * 颜色组件
 * @param  {Hex-Color} color hex 格式的颜色值
 */
MBlockly.SmartServoSetPanel.prototype.getColorComponent = function (color) {
  var html = '<div class="color-panel">' +
               '<div id="colorBody" class="color-body">'+ colorTemplate(color, this.servoColors) +'</div>' +
             '</div>';
  //舵机颜色模板
  function colorTemplate(selected, colors){
    var html_ ='';
    for(var i = 0, color; color = colors[i]; i++){
      html_ += '<div class="ring'+(selected == color?' selected':'') +'" data-color="'+ color +'">'+
                '<div class="colorpie" style="background-color:'+ color +';"></div></div>';
    }
    return html_;
  }
  return html
}

/**
 * 更新颜色组件
 * @param  {[type]} color [description]
 */
MBlockly.SmartServoSetPanel.prototype.updateColorComponent = function (color) {
  var eventType = getEventType();
  $('#colorBody').find('[data-color="'+ color +'"]').trigger(eventType);
}

MBlockly.SmartServoSetPanel.prototype.renderAngleComponent = function(element, index, angle, minRange, maxRange) {
  var that  = this;
  var r = checkDeviceType().phone? 90 : 133;
  var CIRCLE_ANGLE = 360;
  // 默认范围设为[-720, 720]
  var minRange = typeof minRange !== 'undefined' ? minRange : -CIRCLE_ANGLE * 2;
  var maxRange = typeof maxRange !== 'undefined' ? maxRange : CIRCLE_ANGLE * 2;
  var rotateNumber = Math.floor(angle/CIRCLE_ANGLE);
  var trueAngle = angle || 0;
  var $tooltopText = null;
  var roundSlider_ = null;
  var fakeAngle = angle - CIRCLE_ANGLE * rotateNumber;
  //set angle by this plugin
  element.roundSlider({
    circleShape: 'full',
    radius: r,
    width: 6,
    min: 0,
    max: 360,
    handleSize: 16,
    startAngle: 90,
    handleShape: "square",
    editableTooltip: false, //不可编辑
    value: fakeAngle,
    animation: false,
    //拖动检测
    drag: function(args){
      rotateMotation(args);
      setSafeAngleAndRotateNumber(args.value);
      $tooltopText.text(trueAngle);
    },
    stop: function() {
      updateViewAndData();
    }
  });
  if($tooltopText == null) {
    $tooltopText = $(element).find('.rs-tooltip-text');
  }
  $tooltopText.text(angle);
  // 获取到 roundSlider 实例 
  roundSlider_ = window[element.selector.replace('#', '')];
  
  function rotateMotation(args){
    if(args.value - args.preValue <= -CIRCLE_ANGLE / 2) {
      rotateNumber ++;
    } else if(args.value - args.preValue >= CIRCLE_ANGLE / 2) {
      rotateNumber --;
    }
  }

  function setSafeAngleAndRotateNumber(angle){
    trueAngle = rotateNumber * CIRCLE_ANGLE +  angle;
    if(trueAngle >= maxRange) {
      rotateNumber = Math.floor(maxRange / CIRCLE_ANGLE);
      roundSlider_.setValue(maxRange - rotateNumber * CIRCLE_ANGLE);
      trueAngle = maxRange;
    } else if(trueAngle <= minRange) {
      rotateNumber = Math.floor(minRange / CIRCLE_ANGLE);
      roundSlider_.setValue(minRange - rotateNumber * CIRCLE_ANGLE);
      trueAngle = minRange;
    }
  }

  var clickValue = {
    value: null,
    preValue: null,
  };

  //事件类型
  var eventType = getEventType();
  //绑定 - + 事件
  $('#trimButton').off().on(eventType, '.icon-wrapper:last', function(e){
    var currentAngle = roundSlider_.getValue(); //360
    if(currentAngle >= CIRCLE_ANGLE){
      rotateNumber++;
      currentAngle = 1;
    }else{
      currentAngle++;
    }
    roundSlider_.setValue(currentAngle);
    setSafeAngleAndRotateNumber(currentAngle);
    //更新视图
    updateViewAndData();
  })
  .on(eventType, '.icon-wrapper:first', function(e){
    var currentAngle = roundSlider_.getValue(); //0
    if(currentAngle <= 0){
      rotateNumber--;
      currentAngle = CIRCLE_ANGLE - 1;
    }else{
      currentAngle--;
    }
    roundSlider_.setValue(currentAngle);
    setSafeAngleAndRotateNumber(currentAngle);
    //更新视图
    updateViewAndData();
  });

  //更新视图和其他数据
  function updateViewAndData(){
    var final_ = trueAngle;
    setStyleForButtons(final_);
    $tooltopText.text(final_);
    that.modifyServoData(that.currentOpenedIndex, {'angle': final_});
  }

  function setStyleForButtons(angle){
    var $trimButtonSpan = $('#trimButton .icon-wrapper');
    if(angle == minRange){
      $trimButtonSpan.eq(0).addClass('disabled');
    }else if(angle == maxRange){
      $trimButtonSpan.eq(1).addClass('disabled');
    }
    else{//清除
      $trimButtonSpan.removeClass('disabled');
    }
  }
};

/**
 * modal 事件
 */
MBlockly.SmartServoSetPanel.prototype.modalEvents = function() {
  var that = this;
  var eventType = getEventType();

  //添加
  $('.smart-servo-panel').on(eventType, '.plus', function(e){
    var keys = Object.keys(that.getAllServosId());
    //大于等于最大数量
    if(keys.length >= that.MAX_NUMBER){
      return false;
    }
    var id = that.setItemNameId();
    var item = '';
    //关闭次级菜单
    that.hideSubSider();
    if(id){
      var number_of_item = that.getServoData().length;
      if(number_of_item == 1){
        item = that.generateMenuItem(id, true, true);
      }
      //舵机数 > 2
      else{
        item = that.generateMenuItem(id);
      }
      $('#itemList').append(item);
      //舵机数 > 1
      if (number_of_item > 1) {
        $('#itemList li:first').find('.icon-cancel-wrapper').css('visibility', 'visible');
      }
    }
    //超出阈值
    if(keys.length + 1 >= that.MAX_NUMBER){
      $(this).addClass("disabled");
    };
    e.preventDefault();
  })
  //完成 == 保存
  .on(eventType, '.save', function(e){
    //没有数据不允许关闭
    if(that.getServoData().length == 0){
      return false;
    }
    //处理数据
    that.onOk();
    var text_ = that.formateText_();
    that.setText(text_);
    that.hideWidget();
    e.preventDefault();
  })
  //关闭 subSider
  .on(eventType, '.panel', function(e){
    that.hideSubSider();
  })
  //关闭 subSider
  .on(eventType, '.close', function(e){
    that.hideSubSider();
    that.hideWidget();
    e.preventDefault();
  })
};

/**
 * 菜单事件
 */
MBlockly.SmartServoSetPanel.prototype.menuEvents = function() {
  var that = this;
  var eventType = getEventType();
  // 选中项目
  $('#itemList').on(eventType, 'li', function(){
    var $this = $(this);
    //dom 节点索引值
    var index = $this.index();
    // $('#itemList').data('currentOpenedIndex', index);
    var servoIndex = $this.data('index');
    //记录当前被初始化的舵机的编号
    var currentIndex = $this.data('current');
    //初始化 panel
    if(!$this.hasClass('selected') || currentIndex != servoIndex){
      that.onSwitch(index);
      $this.data('current', servoIndex);
    }
    //关闭二级菜单
    that.hideSubSider();
    $this.addClass('selected').siblings().removeClass('selected');
  })
  //删除
  .on('click', '.icon-cancel-wrapper', function(e){
    var number_of_item = that.getServoData().length;
    //当舵机数只有 1 个时，不能删除
    if(number_of_item < 2){
      return false;
    }
    var li = $(this).parent();
    var index = li.index();
    //删除舵机数据和 dom 节点
    that.deleteServoData(index);
    li.remove();
    //当舵机删除后只剩1个，删除icon隐藏
    if(that.getServoData().length == 1){
      $('#itemList li:first').find('.icon-cancel-wrapper').css('visibility', 'hidden');
    }
    //关闭二级菜单
    that.hideSubSider();
    //阻止冒泡
    e.stopPropagation();
    //舵机数小于最大值时解除
    var keys = Object.keys(that.getAllServosId());
    if( keys.length < that.MAX_NUMBER){
      $('.smart-servo-panel .plus').removeClass("disabled");
    }
  })
  //展开/收起
  .on(eventType, '.icon-detail-wrapper', function(e){
    var subSider = $('#subSider');
    if(subSider.is(':visible')){
      that.hideSubSider();
    }
    //展开
    else{
      var mainindex = $(this).parent().index();
      var subMenuHtml = that.generateSubMenu();
      subSider.data('mainindex', mainindex);
      $(this).addClass('selected');
      if (subMenuHtml) {
        subSider.find('.sub-list').html(subMenuHtml).parent().show();
      }
    }
    //阻止冒泡
    e.stopPropagation();
  });

  //二级菜单事件
  $('#subSider').on('mouseup touchend', 'p>span', function(e){
    var $this = $(this);
    var $p = $this.parent();
    var mainindex = $this.parents('.sub-sider').data('mainindex');
    var index = $p.data('index');
    var text = $this.text();
    var targetLi = $('#itemList li').eq(mainindex);
    //修改 data 记录及 text 内容
    targetLi.data('index', index).find('.name').text(text);
    //修改servoData
    that.modifyServoData(mainindex, that.createOneServoData(index));
    e.preventDefault();
    targetLi.trigger(eventType);
  });
}

MBlockly.SmartServoSetPanel.prototype.bindPanelEvents = function(){
  var that = this;
  var eventType = getEventType();
  //颜色
  $('#colorBody').on(eventType, '.ring', function(){
    var $this = $(this);
    var index = that.currentOpenedIndex;
    //增加样式
    $this.addClass('selected').siblings().removeClass('selected');
    //颜色数据
    that.modifyServoData(index, {'color': $this.data('color')});
  });

  //旋转方向
  $('#rotateBody').on(eventType, '>div', function(){
    var $this = $(this);
    var index = that.currentOpenedIndex;
    $this.addClass('selected').siblings().removeClass('selected');
    //旋转数据
    that.modifyServoData(index, {'rotate': $this.data('rotate')});
  });
}

MBlockly.SmartServoSetPanel.prototype.bindPanelDragEvent = function () {
  // #speedBody 绑定mousedown 事件
  var that = this;
  var eventType = getEventType();
  var $speedPanel = $('.speed-panel');
  $speedPanel.on('touchstart', '.speed-coin', function(e){
    var $this = $(this);
    var speed = $this.data('speed');
    var index = that.currentOpenedIndex;
    //更新视图
    that.updateSpeedComponent(speed);
    //更新速度数据
    that.modifyServoData(index, {'speed': $this.data('speed')});
  });

  $speedPanel.on('touchstart', '.big-coin', function(e){
    if(e.type === 'mousedown' && e.which !== 1){//非左键
      return false;
    }
    //开始拖动
    startDrag.call(that, e, $speedPanel);
  })

  //startDrag
  function startDrag(e, $speedPanel){
    var that = this;
    var beat_box = $speedPanel.find('.speed-circle');
    var maxY = beat_box.height();
    var minY = 0;
    e.stopPropagation();
    e.preventDefault();
    //获取刚开始拖动时的位置信息
    var startPostion = getPostion(e);
    $('html').bind('touchmove.drag', function(e){
      e.preventDefault();
      //拖动过程中， 目标需跟随移动
      var target_ = e.target;
      var nowPosition = getPostion(e);
      //拖动的距离
      var dy = nowPosition.y - startPostion.y;
      //当前中心点的 offsetLeft
      var nowCenterOffsetTop = Math.floor(startPostion.offsetTop + startPostion.offsetHeight/2 + dy);
      if (nowCenterOffsetTop >= maxY || nowCenterOffsetTop <= minY) {
        target_.style.top = (nowCenterOffsetTop > 0 ? maxY: minY)  + 'px';
      }else{
        target_.style.top = nowCenterOffsetTop  + 'px';
      }
      var backbar = $speedPanel.find('.backbar');
      backbar.find('.frontbar').height((target_.offsetTop + target_.offsetHeight/2) * 100/ backbar.height() + '%');
      //滑竿定位
      var pos_y =  Math.floor( target_.offsetTop + target_.offsetHeight/2 );
      var el = getNearElement(pos_y, $speedPanel[0]);
      var span = $(el).parent();
      span.prevAll().find('i').addClass('grey');
      span.find('i').removeClass('grey');
      span.nextAll().find('i').removeClass('grey');
    });

    // 拖动释放
    $('html').bind('touchend.drag', function(e){
      $('html').off('touchmove.drag');
      $('html').off('touchend.drag');
      var target_ = e.target;
      var pos_y =  Math.floor( target_.offsetTop + target_.offsetHeight/2 );
      var el = getNearElement(pos_y, $speedPanel[0]);
      var speed = $(el).data('speed');
      that.updateSpeedComponent(speed);
      var index = that.currentOpenedIndex;
      that.modifyServoData(index, {'speed': speed});
    });
  }

  //判断元素位置，获取一个就近的元素
  function getNearElement(x, dom){
      var beat_coin = dom.querySelectorAll('.speed-coin');
      for (var i = 0, len = beat_coin.length; i < len-1; i++) {
        var begin_icon = beat_coin[i];
        var end_icon = beat_coin[i+1];
        if (x >= begin_icon.offsetTop && x <= end_icon.offsetTop ) {
          if( 2*x < begin_icon.offsetTop + end_icon.offsetTop){
            return begin_icon;
          }else{
            return end_icon;
          }
        }
      }
      if( x < beat_coin[0].offsetTop){
        return beat_coin[0];
      }else if(x > end_icon.offsetTop){
        return end_icon;
      }
  }
    //获取拖动事件的位置信息
  function getPostion (e){
    e = e.originalEvent;
    var target_ = e.target;
    if (e.touches && e.touches.length) {
      e = e.touches[0];
    } else if (e.changedTouches && e.changedTouches.length) {
      e = e.changedTouches[0];
    }
    var offsetTop = target_.offsetTop;
    var offsetHeight = target_.offsetHeight;
    return {
      x: e.pageX,
      y: e.pageY,
      offsetTop: offsetTop,
      offsetHeight: offsetHeight
    };
  }
}

