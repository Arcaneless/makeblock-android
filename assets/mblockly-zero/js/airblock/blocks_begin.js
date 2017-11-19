MBlockly.BlockKeeper.makeBlock('begin_power_on', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.start);

    this.appendDummyInput()
        .appendField(Blockly.Msg.BEGIN_POWER_ON);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    console.log("start");
    MBlockly.Airblock.airblockStart();
});

MBlockly.BlockKeeper.makeBlock('begin_power_off', [], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.start);

    this.appendDummyInput()
        .appendField(Blockly.Msg.BEGIN_POWER_OFF);

    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(){
    console.log("stop");
    MBlockly.Airblock.airblockStop();
});