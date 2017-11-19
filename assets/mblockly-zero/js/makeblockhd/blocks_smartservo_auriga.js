// 探测智能舵机角度
MBlockly.BlockKeeper.makeBlock('smart_servo_detect_angle', ['ID'], function(){
  var idOptions = [];
  for(var i = 1; i < 25; i++) {
    var item = [i + '', i + ''];
    idOptions.push(item)
  }

  this.jsonInit({
    "message0": Blockly.Msg.SMART_SERVO_DETECT_ANGLE,
    "args0": [
      {
        "type": "field_dropdown",
        "name": "ID",
        "options": idOptions
      }
    ],
    "inputsInline": true,
    "output": "Number",
    "colour": MBlockly.BlockKeeper.HUE.detect
  });
}, function(id){
  id = parseInt(id);
  var runtime = this;
  runtime.pause(); // 暂停程序执行
  var val = MBlockly.Control.getSensorValue('SMART_SERVO_ANGLE', id, function(){
      // 恢复程序执行
      runtime.resume();
  });
  return val;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);


//停止运动
MBlockly.BlockKeeper.makeBlock('smart_servo_stop', [], function(){
  this.jsonInit({
    "message0": Blockly.Msg.SMART_SERVO_STOP,
    "args0": [],
    "inputsInline": true,
    "previousStatement": true,
    "nextStatement": true,
    "colour": MBlockly.BlockKeeper.HUE.move
  });
}, function(){
  var id = 255;
  MBlockly.Control.setServoAsDcMotor(id, 0);
});

//设置舵机角度、速度
MBlockly.BlockKeeper.makeBlock('smart_servo_set_angle_speed', ['=ID', '=ANGLE', '=SPEED'], function(){
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  this.setColour(MBlockly.BlockKeeper.HUE.move);
  this.appendDummyInput()
      .appendField(Blockly.Msg.SMART_SERVO_SET_ANGLE) //
      .appendField(new MBlockly.SmartServoSetPanel('set_angle_speed', name + '1', null, this.id));
  this.setInputsInline(true);
  this.setNextStatement(true);
  this.setPreviousStatement(true);
  this.setTooltip(Blockly.Msg.MATH_NUMBER_TOOLTIP);
}, function(id){
  var data = Blockly.selected.inputList[0].fieldRow[1].validServoData;
  for (var i in data) {
    var item = data[i];
    var angle = -1 * item.angle;
    MBlockly.Control.setServoAbsoluteAngle(item.index, angle, item.speed * 0.5);
  }
});

//设置舵机旋转方向、速度
MBlockly.BlockKeeper.makeBlock('smart_servo_set_rotate_speed', ['=ID', '=ROTATE', '=SPEED'], function(){
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  this.setColour(MBlockly.BlockKeeper.HUE.move);
  this.appendDummyInput()
      .appendField(Blockly.Msg.SMART_SERVO_SET_ROTATE)  //
      .appendField(new MBlockly.SmartServoSetPanel('set_rotate_speed', name + '1', null, this.id));
  this.setInputsInline(true);
  this.setNextStatement(true);
  this.setPreviousStatement(true);
  this.setTooltip(Blockly.Msg.MATH_NUMBER_TOOLTIP);
}, function(){
  var data = Blockly.selected.inputList[0].fieldRow[1].validServoData;
  for (var i in data) {
    var item = data[i];
    var direction = 1;
    if(item.rotate == Blockly.Msg.SMART_SERVO_PANEL_ROTATE_CLOCKWISE) {
      direction = -1;
    }
    var speed = direction * (parseInt(item.speed) / 100 * 255);
    MBlockly.Control.setServoAsDcMotor(item.index, speed);
  }
});

//设置舵机颜色
MBlockly.BlockKeeper.makeBlock('smart_servo_set_led_color', [], function(){
  var name = Blockly.Msg.SMART_SERVO_ARIGUA;
  this.setColour(MBlockly.BlockKeeper.HUE.display);
  this.appendDummyInput()
      .appendField(Blockly.Msg.SMART_SERVO_SET_LED_COLOR)  //
      .appendField(new MBlockly.SmartServoSetPanel('set_led_color', name + '1', null, this.id));
  this.setInputsInline(true);
  this.setNextStatement(true);
  this.setPreviousStatement(true);
  this.setTooltip(Blockly.Msg.MATH_NUMBER_TOOLTIP);
}, function(){
    var data = Blockly.selected.inputList[0].fieldRow[1].validServoData;
    var colorMapSource = MBlockly.SmartServoSetPanel.prototype.servoColors;
    var outputColor = {};
    outputColor[colorMapSource[0]] = "#ff0000";
    outputColor[colorMapSource[1]] = "#f01e00";
    outputColor[colorMapSource[2]] = "#ff7800";
    outputColor[colorMapSource[3]] = "#00ff00";
    outputColor[colorMapSource[4]] = "#4dafff";
    outputColor[colorMapSource[5]] = "#834dff";
    outputColor[colorMapSource[6]] = "#c878c8";
    outputColor[colorMapSource[7]] = "#000000";

    for (var i in data) {
      var item = data[i];
      var colors = getColor(outputColor[item.color]);
      MBlockly.Control.setServoLed(item.index, colors[0], colors[1], colors[2]);
    }
});