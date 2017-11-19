MBlockly.BlockKeeper.makeBlock('tablet_shaked', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.EVENT_TABLET_SHAKE,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_SHAKE);
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(){
    var time = (new Date()).getTime() / 1000;
    var result;
    // 如果当前时间距离上次iPad摇晃的时间小于1秒
    if(time-MBlockly.Control.tabletLastShakeTime < 1){
        result = true;
    }
    else{
        result = false;
    }
    this.result = result;
    return result;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);


MBlockly.BlockKeeper.makeBlock('tablet_tilt_lr', ['DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    var iconImages = MBlockly.Settings.resources().ICONS;
    var icon = new Blockly.FieldImage(iconImages.EVENT_TILT_LEFT,
        MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*');

    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.EVENT_TABLET_TILT_LEFT, 'LEFT'],
        [Blockly.Msg.EVENT_TABLET_TILT_RIGHT, 'RIGHT']
    ], function() {
        var that = this;
        setTimeout(function() {
            icon.imageElement_.setAttributeNS('http://www.w3.org/1999/xlink',
            'xlink:href', iconImages["EVENT_TILT_" + that.value_]);
        }, 50);
    });

    this.appendDummyInput()
        .appendField(icon)
        .appendField(Blockly.Msg.EVENT_TABLET_TILT)
        .appendField(dropdown, 'DIRECTION')
        .appendField(Blockly.Msg.EVENT_TABLET_TILT_TIP);
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(direction){
    var dir = (direction == 'LEFT') ? 1 : -1;
    console.log('tablet: ' + MBlockly.Control.tabletTiltLeftRightStatus);
    var deviceDirection = MBlockly.Control.tabletTiltLeftRightStatus;
    return (deviceDirection == dir);
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

MBlockly.BlockKeeper.makeBlock('tablet_tilt_fb', ['DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    var iconImages = MBlockly.Settings.resources().ICONS;
    var icon = new Blockly.FieldImage(iconImages.EVENT_TILT_FORWARD,
        MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*');

    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.EVENT_TABLET_TILT_FORWARD, 'FORWARD'],
        [Blockly.Msg.EVENT_TABLET_TILT_BACKWARD, 'BACKWARD']
    ], function() {
        var that = this;
        setTimeout(function() {
            icon.imageElement_.setAttributeNS('http://www.w3.org/1999/xlink',
            'xlink:href', iconImages["EVENT_TILT_" + that.value_]);
        }, 50);
    });

    this.appendDummyInput()
        .appendField(icon)
        .appendField(Blockly.Msg.EVENT_TABLET_TILT)
        .appendField(dropdown, 'DIRECTION')
        .appendField(Blockly.Msg.EVENT_TABLET_TILT_TIP);
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(direction){
    var dir = (direction == 'FORWARD') ? -1 : 1;
    var deviceDirection = MBlockly.Control.tabletTiltForwardBackwardStatus;
    return (deviceDirection == dir);
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

/* SENSOR */
MBlockly.BlockKeeper.makeBlock('event_linefollower_reads', ['VALUE', 'PORT'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.event);
     var dropdown = new Blockly.FieldDropdown([
         ['■■', 'BLACK_BLACK'],
         ['■□', 'BLACK_WHITE'],
         ['□■', 'WHITE_BLACK'],
         ['□□', 'WHITE_WHITE']
    ]);
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get("LINEFOLLOW"));

    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.EVENT_LINE_FOLLOW,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_LINEFOLLOWER)
        .appendField(dropdown, 'VALUE')
        .appendField(Blockly.Msg.EVENT_LINEFOLLOWER_AT)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(checkType, portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('LINEFOLLOW', port, function(){
        runtime.resume();
    });

    var wrapper = {
        toString: function(){
            if(this.val == MBlockly.Control.LINEFOLLOWER_VALUE[this.checkType]){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    wrapper.checkType = checkType;
    return wrapper;  // 返回的是 toString() 的值，是字符串类型
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

MBlockly.BlockKeeper.makeBlock('event_receieve_light', ['PORT'], function(){
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get('LIGHT'));
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.START_RECEIVE_LIGHT,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_WHEN_RECEIVE_LIGHT)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('LIGHTSENSOR', port, function(){
        runtime.resume();
    });
    var wrapper = {
        toString: function(){
            if(this.val >= MBlockly.Settings.FLAME_THRESHOLD){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

//机器人探测到火焰
MBlockly.BlockKeeper.makeBlock('event_detect_flame', ['PORT'], function(){
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get("FLAME"));
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.DETECT_FLAME,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_WHEN_FLAME_DETECTED)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('FLAMESENSOR', port, function(){
        runtime.resume();
    });
    var wrapper = {
        toString: function(){
            if(this.val <= MBlockly.Settings.FLAME_THRESHOLD){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

//机器人探测到触摸
MBlockly.BlockKeeper.makeBlock('event_detect_touch', ['PORT'], function(){
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get());
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.DETECT_TOUCH,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_WHEN_TOUCH_DETECTED)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('TOUCHSENSOR', port, function(){
        runtime.resume();
    });
    var wrapper = {
        toString: function(){
            if(this.val == 1){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

MBlockly.BlockKeeper.makeBlock('event_obstacle_ahead', ['PORT'], function(){
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get());

    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.START_OBSTACLE_DETECTED,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_WHEN_OBSTACLE_AHEAD)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('ULTRASONIC', port, function(val){
        runtime.resume();
    });
    var wrapper = {
        toString: function(){
            if(this.val >= MBlockly.Settings.OBSTACLE_THRESHOLD){
                return '0';  // in javascript 'false' == true!
            }
            else{
                return '1';
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

MBlockly.BlockKeeper.makeBlock('event_noise_around', ['PORT'], function(){
    var icon = new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.EVENT_SOUND,
        MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*');
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get('VOLUME'));
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(icon)
        .appendField(Blockly.Msg.EVENT_WHEN_SOUND_DETECTED)
        .appendField(port, 'PORT');
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    var runtime = this;
    runtime.pause();
    // 从默认板载音量传感器中接收值
    var val = MBlockly.Control.getSensorValue('VOLUME', port, function(val){
        runtime.resume();
    });
    var wrapper = {
        toString: function(){
            if(this.val >= MBlockly.Settings.NOISE_THRESHOLD && this.val < 700){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

MBlockly.BlockKeeper.makeBlock('event_switch_changed', ['SWITCH', 'STATE'], function(){
    var iconImages = MBlockly.Settings.resources().ICONS;
    var icon = new Blockly.FieldImage(iconImages.EVENT_SWITCH_ON,
        MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*');
    var dropdown = new Blockly.FieldDropdown([
         [Blockly.Msg.EVENT_SWITCH_ON, '1'],
         [Blockly.Msg.EVENT_SWITCH_OFF, '0']
    ], function() {
        var that = this;
        var imgsrc = (that.value_ == '1') ? 'ON' : 'OFF';
        setTimeout(function() {
            icon.imageElement_.setAttributeNS('http://www.w3.org/1999/xlink',
            'xlink:href', iconImages["EVENT_SWITCH_" + imgsrc]);
        }, 50);
    });
    var options = MBlockly.Data.widgetDropdownList.get('Switchs');
    var switchDropdown = new Blockly.FieldDropdown(options, function() {});

    this.setColour(MBlockly.BlockKeeper.HUE.event);

    this.appendDummyInput()
        .appendField(icon)
        .appendField(Blockly.Msg.EVENT_SWITCH)
        .appendField(switchDropdown, 'SWITCH')
        .appendField(Blockly.Msg.EVENT_SWITCH_IS)
        .appendField(dropdown, 'STATE');

    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(widgetId, switchState){
    if(widgetId == '0'){
        widgetId = this.widgetId;
    }
    var runtime = this;
    runtime.pause();
    var val = MBlockly.HostInterface.requestWidgetValue(widgetId, function(value) {
        runtime.resume();
    })
    var wrapper = {
        toString: function(){
            if(this.val == parseInt(switchState.data)){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);


MBlockly.BlockKeeper.makeBlock('event_button_changed', ['BUTTON', 'STATE'], function(){
    var iconImages = MBlockly.Settings.resources().ICONS;
    var icon = new Blockly.FieldImage(iconImages.EVENT_BUTTON_PRESSED,
        MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*');
    var dropdown = new Blockly.FieldDropdown([
         [Blockly.Msg.EVENT_BUTTON_PRESSED, '1'],
         [Blockly.Msg.EVENT_BUTTON_RELEASED, '0']
    ], function() {
        var that = this;
        var imgsrc = (that.value_ == '1') ? 'PRESSED' : 'RELEASED';
        setTimeout(function() {
            icon.imageElement_.setAttributeNS('http://www.w3.org/1999/xlink',
            'xlink:href', iconImages["EVENT_BUTTON_" + imgsrc]);
        }, 50);
    });
    var options = MBlockly.Data.widgetDropdownList.get('Buttons');
    var buttonDropdown = new Blockly.FieldDropdown(options, function() {});

    this.setColour(MBlockly.BlockKeeper.HUE.event);

    this.appendDummyInput()
        .appendField(icon)
        .appendField(Blockly.Msg.EVENT_BUTTON)
        .appendField(buttonDropdown, 'BUTTON')
        .appendField(Blockly.Msg.EVENT_BUTTON_IS)
        .appendField(dropdown, 'STATE');

    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(widgetId, state){
    if(widgetId == '0'){
        widgetId = this.widgetId;
    }
    var runtime = this;
    runtime.pause();
    var val = MBlockly.HostInterface.requestWidgetValue(widgetId, function(value) {
        runtime.resume();
    })
    var wrapper = {
        toString: function(){
            if(this.val == parseInt(state.data)){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);

//探测到按键被按下
MBlockly.BlockKeeper.makeBlock('event_detect_four_key', ['PORT', 'KEY'], function(){
    var keyList = new Blockly.FieldDropdown([
        [Blockly.Msg.EVENT_KEY_1_NAME, '1'],
        [Blockly.Msg.EVENT_KEY_2_NAME, '2'],
        [Blockly.Msg.EVENT_KEY_3_NAME, '3'],
        [Blockly.Msg.EVENT_KEY_4_NAME, '4']
    ]);
    var port = new Blockly.FieldDropdown(MBlockly.Data.portList.get("FOUR_KEY"));
    this.setColour(MBlockly.BlockKeeper.HUE.event);

    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.DETECT_FOURKEY,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(keyList, 'KEY')
        .appendField(Blockly.Msg.EVENT_BUTTON_PRESSED);//被按下

    this.appendDummyInput()
        .appendField(Blockly.Msg.EVENT_WHEN_KEY_DETECTED) //检测到按键
        .appendField(port, 'PORT')

    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(portStr, key){
    var port = parseInt(portStr.data.split('PORT-')[1]);
    key = parseInt(key);
    var runtime = this;
    runtime.pause();
    var val = MBlockly.Control.getSensorValue('KEY_PRESSDOWN_SENSOR', port, function(val){
        runtime.resume();
    }, key);
    var wrapper = {
        toString: function(){
            if(this.val == 1){
                return '1';
            }
            else{
                return '0';  // in javascript 'false' == true!
            }
        }
    };
    wrapper.val = val;
    return wrapper;
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);
