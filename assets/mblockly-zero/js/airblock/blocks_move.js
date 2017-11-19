
//气垫船停止
MBlockly.BlockKeeper.makeBlock('move_carstop', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_STOP_MOVING);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetControlWord({
        word: 30,
        angle: 0,
        duration: 0,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });
});

//气垫船运动
MBlockly.BlockKeeper.makeBlock('move_carmove', ['DIRECTION','=ANGLE','=DURATION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_FORWARD, 'FORWARD'],
        [Blockly.Msg.MOVE_BACKWARD, 'BACK'],
        [Blockly.Msg.MOVE_MOVE_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_MOVE_RIGHT, 'RIGHT'],
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_CAR)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('ANGLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_WITH_DEGREE);

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);        

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(direction,angle,duration){
    var dir = -1;
    if (direction == 'FORWARD') {
        dir = 0;
    }else if (direction == 'BACK'){
        dir = 1;
    }else if (direction == 'LEFT'){
        dir = 2;
    }else if (direction == 'RIGHT'){
        dir = 3;
    }
    
    MBlockly.Airblock.airblockSetControlWord({
        word: dir,
        angle: angle,
        duration: duration,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });
        
    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//气垫船悬浮
MBlockly.BlockKeeper.makeBlock('move_carsuspend', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_CAR+" "+Blockly.Msg.MOVE_SUSPEND)

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetControlWord({
        word: 9,
        angle: 0,
        duration: 1,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });
});

//气垫船旋转
MBlockly.BlockKeeper.makeBlock('move_carrotate', ['DIRECTION','=ANGLE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_TURN_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_TURN_RIGHT, 'RIGHT'],
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_CAR)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('ANGLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.DEGREE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(direction,angle){
    var realAngle = angle;
    if(angle < 0){//角度为负
        if (direction == 'LEFT'){//左转
            realAngle = -angle;
        }
    }else if (angle > 0){
        if (direction == 'LEFT'){//左转
            realAngle = -angle;
        }
    }

    MBlockly.Airblock.airblockSetControlWord({
        word: 6,
        angle: realAngle,
        duration: 0,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    var duration = Math.abs(angle)/100.0;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//气垫船S运动
MBlockly.BlockKeeper.makeBlock('move_carsmove', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_CAR+" "+Blockly.Msg.MOVE_SMOVE)

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(angle,duration){
    MBlockly.Airblock.airblockPushControlWord({
        word:2,
        angle:-10,
        duration:0.4,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    MBlockly.Airblock.airblockPushControlWord({
        word:0,
        angle:80,
        duration:0.7,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    MBlockly.Airblock.airblockPushControlWord({
        word:0,
        angle:-120,
        duration:0.8,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    MBlockly.Airblock.airblockPushControlWord({
        word:0,
        angle:120,
        duration:1,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });
});

//气垫船设置悬浮油门
MBlockly.BlockKeeper.makeBlock('move_carsetthrottle', ['=THROTTLE','=THROTTLE2'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_SET_SUSPEND)

    this.appendValueInput('THROTTLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_FRONT_THROTTLE);

    this.appendValueInput('THROTTLE2')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_REAR_THROTTLE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(throttle,throttle2){
    if (throttle < 0) {
        throttle = 0;
    }
    if (throttle2 < 0){
        throttle2 = 0;
    }

    MBlockly.Airblock.airblockSetThrottle({
        throttle1: 1500,
        throttle2: throttle,
        throttle3: throttle2,
        throttle4: 0
    });
});


MBlockly.BlockKeeper.makeBlock('move_carsetsuspendthrottle', ['=THROTTLE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_SET_SUSPEND)

    this.appendValueInput('THROTTLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_THROTTLE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(throttle){
    if (throttle < 0) {
        throttle = 0;
    }

    MBlockly.Airblock.airblockSetThrottle({
        throttle1: 1500,
        throttle2: throttle,
        throttle3: throttle,
        throttle4: 0
    });
});



//无人机移动
MBlockly.BlockKeeper.makeBlock('move_dronemove', ['=DURATION', 'DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_FORWARD, 'FORWARD'],
        [Blockly.Msg.MOVE_BACKWARD, 'BACK'],
        [Blockly.Msg.MOVE_MOVE_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_MOVE_RIGHT, 'RIGHT']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(duration, direction){
    var dir = -1;
    if (direction == 'FORWARD') {
        dir = 0;
    }else if (direction == 'BACK'){
        dir = 1;
    }else if (direction == 'LEFT'){
        dir = 2;
    }else if (direction == 'RIGHT'){
        dir = 3;
    }

    MBlockly.Airblock.airblockSetControlWord({
        word:dir,
        angle:0,
        duration: duration,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//无人机旋转
MBlockly.BlockKeeper.makeBlock('move_dronerotate', ['DIRECTION','=ANGLE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_TURN_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_TURN_RIGHT, 'RIGHT'],
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('ANGLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.DEGREE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(direction,angle){
    var realAngle = angle;
    if(angle < 0){//角度为负
        if (direction == 'LEFT'){//左转
            realAngle = -angle;
        }
    }else if (angle > 0){
        if (direction == 'LEFT'){//左转
            realAngle = -angle;
        }
    }

    MBlockly.Airblock.airblockSetControlWord({
        word: 6,
        angle: realAngle,
        duration: 0,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    var duration = Math.abs(angle)/100.0;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//无人机上升下降
MBlockly.BlockKeeper.makeBlock('move_droneupdown', ['=DURATION', 'DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_UP, 'UP'],
        [Blockly.Msg.MOVE_DOWN, 'DOWN']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(duration,direction){
    var dir = -1;
    if (direction == 'UP'){
        dir = 4;
    }else if (direction == 'DOWN'){
        dir = 5;
    }

    MBlockly.Airblock.airblockSetControlWord({
        word:dir,
        angle:0,
        duration: duration,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//无人机螺旋上升、下降
MBlockly.BlockKeeper.makeBlock('move_dronerotateupdown', ['=DURATION', 'DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_SPIRAL_RISING, 'UP'],
        [Blockly.Msg.MOVE_SPIRAL_DOWNWARD, 'DOWN']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE)
        .appendField(dropdown, 'DIRECTION');

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);        

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(duration,direction){
    if (direction == 'UP'){
        // 上升 0.3 + 组合：角度5+时长1.5+横滚角0+偏航角速率-80+升降速率2
        MBlockly.Airblock.airblockSetControlWord({
            word:4,
            angle:0,
            duration:0.2,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
        this.wait(0.2);

        MBlockly.Airblock.airblockSetControlWord({
            word:12,
            angle:8,
            duration:duration,
            delay:0,
            param1:0,
            param2:200,
            param3:0.5
        });

    }else if (direction == 'DOWN'){
        //组合：角度5+时长1.5+横滚角0+偏航角速率-80+升降速率-2
        MBlockly.Airblock.airblockSetControlWord({
            word:12,
            angle:8,
            duration: duration,
            delay: 0,
            param1: 0,
            param2: 200,
            param3: -0.5
        });
    }

    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//无人机翻滚
MBlockly.BlockKeeper.makeBlock('move_droneroll', ['DIRECTION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_BACKWARD, 'BACK'],
        [Blockly.Msg.MOVE_FORWARD, 'FORWARD'],
        [Blockly.Msg.MOVE_MOVE_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_MOVE_RIGHT, 'RIGHT']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE+" "+Blockly.Msg.MOVE_ROLL)
        .appendField(dropdown, 'DIRECTION');

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(direction){
    var dir = 1;
    if (direction == 'BACK'){
        dir = 0;
    }else if (direction == 'LEFT'){
        dir = 3;
    }else if (direction == 'RIGHT'){
        dir = 2;
    }

    //param1表示空翻方向，1表示前空翻，0表示后空翻，3表示左空翻，2表示右空翻
    MBlockly.Airblock.airblockSetControlWord({
        word:11,
        angle:0,
        duration:0,
        delay: 0,
        param1: dir,
        param2: 0,
        param3: 0
    });
});

//无人机抖动
MBlockly.BlockKeeper.makeBlock('move_droneshake', ['=DURATION'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE+" "+Blockly.Msg.MOVE_SHAKE)

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);        

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(duration){
    MBlockly.Airblock.airblockSetControlWord({
        word:13,
        angle:20,
        duration: duration,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});

//无人机设置高度
MBlockly.BlockKeeper.makeBlock('move_dronesetheight', ['=HEIGHT'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE_SETHEIGHT);

    this.appendValueInput('HEIGHT')
        .setCheck('Number')

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(height){
    var heightValue = parseInt(height) + 1;
    MBlockly.Airblock.airblockSetLocation({
        height:heightValue,
        atitude:0,
        dimension:0,
    });
});

//无人机悬停
MBlockly.BlockKeeper.makeBlock('move_dronehover', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE+" "+Blockly.Msg.MOVE_HOVER);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetControlWord({
        word: 9,
        angle: 0,
        duration: 0,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });
});

//无人机着落
MBlockly.BlockKeeper.makeBlock('move_droneland', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE+" "+Blockly.Msg.MOVE_LANDING);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetWorkMode({
        mode: 5,
    });
});

//无人机逃离天花板
MBlockly.BlockKeeper.makeBlock('move_droneescape', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE+" "+Blockly.Msg.MOVE_ESCAPE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetControlWord({
        word:12,
        angle:0,
        duration:0.2,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: -3
    });
});

//无人机高级运动
MBlockly.BlockKeeper.makeBlock('move_droneadvancedmove', ['=ANGLE','=DURATION', 'DIRECTION','WISE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_FORWARD, 'FORWARD'],
        [Blockly.Msg.MOVE_BACKWARD, 'BACK'],
        [Blockly.Msg.MOVE_MOVE_LEFT, 'LEFT'],
        [Blockly.Msg.MOVE_MOVE_RIGHT, 'RIGHT']
    ]);

    var dropdown2 = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_CLOCKWISE, 'CLOCKWISE'],
        [Blockly.Msg.MOVE_ANTICLOCKWISE, 'ANTICLOCKWISE']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_DRONE)
        .appendField(dropdown, 'DIRECTION')
        .appendField(Blockly.Msg.MOVE_MEANWHILE)
        .appendField(dropdown2, 'WISE');

    this.appendValueInput('ANGLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_WITH_DEGREE);

    this.appendValueInput('DURATION')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_DURATION);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_ROTATE_SECOND);           

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(angle,duration,direction,wise){
    var dir = -1;
    if (direction == 'FORWARD') {
        dir = 0;
    }else if (direction == 'BACK'){
        dir = 1;
    }else if (direction == 'LEFT'){
        dir = 2;
    }else if (direction == 'RIGHT'){
        dir = 3;
    }

    var realAngle = angle;
    if(angle < 0){
        if (wise == 'ANTICLOCKWISE'){
            realAngle = -angle;
        }
    }else if (angle > 0){
        if (wise == 'ANTICLOCKWISE'){
            realAngle = -angle;
        }
    }

    MBlockly.Airblock.airblockSetControlWord({
        word:dir,
        angle:realAngle,
        duration: duration,
        delay: 0,
        param1: 0,
        param2: 0,
        param3: 0
    });

    var runtime = this;
    runtime.wait(duration);
    setTimeout(function() {
        MBlockly.Airblock.airblockSetControlWord({
            word: 10,
            angle: 0,
            duration: 0,
            delay: 0,
            param1: 0,
            param2: 0,
            param3: 0
        });
    }, duration*1000);
});


//自定义设置点击油门
MBlockly.BlockKeeper.makeBlock('move_cussetmotorthrottle', ['PORT','=THROTTLE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);
    var dropdown = new Blockly.FieldDropdown([
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"1", 'ONE'],
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"2", 'TWO'],
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"3", 'THREE'],
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"4", 'FOUR'],
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"5", 'FIVE'],
        [Blockly.Msg.MOVE_DC_MOTOR_PORT+"6", 'SIX']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_MOTORPORT)
        .appendField(dropdown, 'PORT');

    this.appendValueInput('THROTTLE')
        .setCheck('Number')
        .appendField(Blockly.Msg.MOVE_THROTTLE);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(port,throttle){
    var no = 1;
    if (port == 'TWO'){
        no = 2;
    }else if (port == 'THREE'){
        no = 3;
    }else if (port == 'FOUR'){
        no = 4;
    }else if (port == 'FIVE'){
        no = 5;
    }else if (port == 'SIX'){
        no = 0;
    }

    if (throttle > 2000) {
        throttle = 2000;
    }else if(throttle < 0){
        throttle = 0;
    }

    MBlockly.Airblock.airblockSetMotorThrottle({
        no: no,
        throttle: throttle
    });
});

//自定义停止运动
MBlockly.BlockKeeper.makeBlock('move_cusstop', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.move);

    this.appendDummyInput()
        .appendField(Blockly.Msg.MOVE_STOP_MOVING)

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 0,
        throttle: 0
    });
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 1,
        throttle: 0
    });
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 2,
        throttle: 0
    });
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 3,
        throttle: 0
    });
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 4,
        throttle: 0
    });
    MBlockly.Airblock.airblockSetMotorThrottle({
        no: 5,
        throttle: 0
    });
});


