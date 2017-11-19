MBlockly.BlockKeeper.makeBlock('event_obstacle_ahead', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.event);
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage(MBlockly.Settings.resources().ICONS.START_OBSTACLE_DETECTED,
            MBlockly.Settings.BLOCK_ICON_WIDTH, MBlockly.Settings.BLOCK_ICON_HEIGHT, '*'))
        .appendField(Blockly.Msg.EVENT_WHEN_AIRBLOCK_OBSTACLE_AHEAD)
    this.setInputsInline(true);
    this.setOutput(true, 'Boolean');
    this.setNextStatement(false);
    this.setPreviousStatement(false);
}, function(){
    var val = MBlockly.Airblock.airblockGetUltrasonicDistance();
    if(val >= 2){
        return '0';  // in javascript 'false' == true!
    }
    else{
        return '1';
    }
}, Blockly.JavaScript.ORDER_FUNCTION_CALL);