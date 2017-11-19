MBlockly.BlockKeeper.makeBlock('display_airblock_led_color', ['LED_POSITION', '=COLOUR'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.display);

    var ledPositionList = new Blockly.FieldDropdown([
        [Blockly.Msg.DISPLAY_LED_ALL, 'ALL'],
        ["1", '1'],
        ["2", '2'],
        ["3", '3']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.DISPLAY_AIRBLOCK_LED_COLOR)
        .appendField(ledPositionList, 'LED_POSITION');
    
    this.appendDummyInput()
        .appendField(Blockly.Msg.DISPLAY_LED_TO_COLOR);

    this.appendValueInput('COLOUR')
        .setCheck('Colour')
        .setAlign(Blockly.ALIGN_RIGHT);

    this.setOutput(false);
    this.setInputsInline(true);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(position,colour){
    var colors = getColor(colour.data);
    var red = colors[0];
    var green = colors[1];
    var blue = colors[2];

    var positionValue = 0;
    if (position == '1'){
        positionValue = 0;
    }else if (position == '2'){
        positionValue = 1;
    }else if (position == '3'){
        positionValue = 2;
    }else if (position == 'ALL'){
        positionValue = 10
    }

    if (positionValue == 10) {
        MBlockly.Airblock.airblockSetLed({
            no: 0,
            red: red,  
            green: green,
            blue: blue
        });
        this.wait(0.3);

        MBlockly.Airblock.airblockSetLed({
            no: 1,
            red: red,  
            green: green,
            blue: blue
        });
        this.wait(0.3);

        MBlockly.Airblock.airblockSetLed({
            no: 2,
            red: red,  
            green: green,
            blue: blue
        });

    }else{
        MBlockly.Airblock.airblockSetLed({
            no: positionValue,
            red: red,  
            green: green,
            blue: blue
        });
    }
});

MBlockly.BlockKeeper.makeBlock('display_airblock_led_rgb', ['LED_POSITION', '=RED', '=GREEN', '=BLUE'], function(){
    this.setColour(MBlockly.BlockKeeper.HUE.display);

    var ledPositionList = new Blockly.FieldDropdown([
        [Blockly.Msg.DISPLAY_LED_ALL, 'ALL'],
        ["1", '1'],
        ["2", '2'],
        ["3", '3']
    ]);

    this.appendDummyInput()
        .appendField(Blockly.Msg.DISPLAY_AIRBLOCK_LED_COLOR)
        .appendField(ledPositionList, 'LED_POSITION');

    this.appendValueInput('RED')
        .setCheck('Number')
        .appendField(Blockly.Msg.DISPLAY_LED_RED)
        .setAlign(Blockly.ALIGN_RIGHT);
    this.appendValueInput('GREEN')
        .setCheck('Number')
        .appendField(Blockly.Msg.DISPLAY_LED_GREEN)
        .setAlign(Blockly.ALIGN_RIGHT);
    this.appendValueInput('BLUE')
        .setCheck('Number')
        .appendField(Blockly.Msg.DISPLAY_LED_BLUE)
        .setAlign(Blockly.ALIGN_RIGHT);

    this.setOutput(false);
    this.setInputsInline(false);
    this.setNextStatement(true);
    this.setPreviousStatement(true);

}, function(position,red,green,blue){

    var positionValue = 0;
    if (position == '1'){
        positionValue = 0;
    }else if (position == '2'){
        positionValue = 1;
    }else if (position == '3'){
        positionValue = 2;
    }else if (position == 'ALL'){
        positionValue = 10
    }

    if (positionValue == 10) {
        MBlockly.Airblock.airblockSetLed({
            no: 0,
            red: red,  
            green: green,
            blue: blue
        });
        this.wait(0.3);

        MBlockly.Airblock.airblockSetLed({
            no: 1,
            red: red,  
            green: green,
            blue: blue
        });
        this.wait(0.3);

        MBlockly.Airblock.airblockSetLed({
            no: 2,
            red: red,  
            green: green,
            blue: blue
        });

    }else{
        MBlockly.Airblock.airblockSetLed({
            no: positionValue,
            red: red,  
            green: green,
            blue: blue
        });
    }
});


function getColor(colorValue) {
    if(colorValue.match(/^['"].*['"]$/)){
        colorValue = colorValue.substring(1, colorValue.length-1);
    }
    var rgb = goog.color.hexToRgb(colorValue);

    return rgb;
}
