(function(){
    if(window.FacePanelEditor) return;

    // "constants"
    var panelMargin = 5;
    var numColumns = 16;
    var numRows = 8;
    var circleRadius = 16;

    if(checkDeviceType().phone) {
        circleRadius = 12;
    }

    var dotMargin = 2;
    var circleCenterDistanceColumn = circleRadius * 2 + dotMargin;
    var circleCenterDistanceRow = circleRadius * 2 + dotMargin;
    var canvasWidth = (circleRadius * 2 + dotMargin) * numColumns  + panelMargin * 2 - dotMargin;
    var canvasHeight = (circleRadius * 2 + dotMargin) * numRows  + panelMargin - dotMargin;
    // console.log(canvasWidth + '-' + canvasHeight);
    var inactiveColor = "#eaeaea";
    var activeColor = "#37c3ff";

    // mode: draw, erase
    var mode = "draw";

    // "variables"
    var stage = null;
    var circleGrid = [];
    var valueGrid = [];
    var isMouseDown = false;
    var lastChangedCircleRowColumn = ""; // {row}-{column} like "5-3"

    var onMouseMoveInStage = function(event){

        if(isMouseDown){
            // see if mouse is within the bounds of dots
            if(isWithinBounds(event.stageX, event.stageY)){
                // calculate which circle did the mouse hit
                var circleRow = Math.floor((event.stageY - (panelMargin)) / circleCenterDistanceRow);
                var circleCol = Math.floor((event.stageX - (panelMargin)) / circleCenterDistanceColumn);
                if(circleRow<numRows && circleCol<numColumns &&
                    ""+circleRow+"-"+circleCol != lastChangedCircleRowColumn){

                    var selectedCircle = circleGrid[circleCol*numRows+circleRow];
                    // mouse drag across a dot!
                    selectedCircle.selected = !selectedCircle.selected;

                    if(mode == "draw") {
                        drawDot(selectedCircle);
                        valueGrid[circleCol * numRows + circleRow] = "1";
                    } else {
                        clearDot(selectedCircle);
                        valueGrid[circleCol * numRows + circleRow] = "0";
                    }

                    // if(selectedCircle.selected){
                    //     drawDot(selectedCircle);
                    //     valueGrid[circleRow*numColumns+circleCol] = "1";
                    // }
                    // else{   // if circle is off
                    //     // clearDot(selectedCircle);
                    //     // valueGrid[circleRow*numColumns+circleCol] = "0";
                    // }
                    lastChangedCircleRowColumn = ""+circleRow+"-"+circleCol;
                    stage.update();
                }
            } else {
                // console.log(false);
            }
        }
    };

    var drawDot = function (target) {
        // target.graphics.beginFill(activeColor).drawCircle(0,0,circleRadius);
        target.graphics.beginFill(activeColor).drawRoundRect(0,0,circleRadius*2,circleRadius*2, 5);
    };

    var clearDot = function (target) {
        // target.graphics.beginFill(inactiveColor).drawCircle(0,0,circleRadius);
        target.graphics.beginFill(inactiveColor).drawRoundRect(0,0,circleRadius*2,circleRadius*2, 3);
    };

    var isWithinBounds = function(x, y) {
        return (x > panelMargin &&
        x < circleCenterDistanceColumn*(numColumns-1) + 2 * circleRadius &&
        y > panelMargin &&
        y < circleCenterDistanceRow * (numRows-1) + 2 * circleRadius);
    };

    var circleHovered = function(event){
        drawDot(event.target)
        stage.update();
    };

    var destroy = function(){
        if(stage){
            createjs.Touch.disable(stage);
            stage = null;
        }
    };

    // render a small image of arbitrary size,
    // and return its base64 value
    var toBase64 = function(width, height) {
        var dummyCanvas = document.createElement('canvas');
        dummyCanvas.width = width;
        dummyCanvas.height = height;
        var dummyStage = new createjs.Stage(dummyCanvas);
        var pixelWidth = parseInt(width / numColumns);
        var pixelHeight = parseInt(height / numRows);
        for(var j=0;j<numColumns;j++){  // j=columns
            for(var i=0;i<numRows;i++){      // i=rows
                var rect = new createjs.Shape();
                if(valueGrid[j*numRows+i] === '1'){
                    rect.graphics.beginFill(activeColor).drawRect(j*pixelWidth, i*pixelHeight, pixelWidth, pixelHeight);
                }
                else{
                    // rect.graphics.beginFill(inactiveColor).drawRect(j*pixelWidth, i*pixelHeight, pixelWidth, pixelHeight);
                }
                dummyStage.addChild(rect);
            }
        }
        dummyStage.update();
        var result = dummyCanvas.toDataURL();

        return result;
    }

    var clear = function(){
        for(var i=0;i<numRows;i++){      // i=rows
            for(var j=0;j<numColumns;j++){  // j=columns
                var circle = circleGrid[i*numColumns+j];
                valueGrid[i*numColumns+j] = "0";
                clearDot(circle);
                circle.selected = false;
            }
        }
        stage.update();
    };

    var load = function(dataString){
        for(var j=0;j<numColumns;j++){  // j=columns
            for(var i=0;i<numRows;i++){      // i=rows
                if(stage){
                    var circle = circleGrid[j*numRows+i];
                    if(dataString && dataString[j*numRows+i] == "1"){
                        drawDot(circle);
                        circle.selected = true;
                    }
                    else{
                        clearDot(circle);
                        circle.selected = false;
                    }
                }
                valueGrid[i*numColumns+j] = dataString[i*numColumns+j];
            }
        }

        if(stage){
            stage.update();
        }
    };

    var init = function(canvasId, initString){
        if(stage){
            destroy(stage);
        }

        stage = new createjs.Stage(canvasId);
        createjs.Touch.enable(stage);

        mode = "draw";

        // draw an array of circles
        for(var j=0;j<numColumns;j++){  // j=columns
            for(var i=0;i<numRows;i++){      // i=rows
                circle = new createjs.Shape();
                if(initString && initString[j*numRows+i] == "1"){
                    drawDot(circle);
                    circle.selected = true;
                }
                else{
                    clearDot(circle);
                    circle.selected = false;
                }
                circle.x = panelMargin + circleCenterDistanceColumn*j;
                circle.y = panelMargin + circleCenterDistanceRow*i;
                circleGrid[j*numRows+i] = circle;
                valueGrid[j*numRows+i] = circle.selected?"1":"0";
                circle.addEventListener("mouseover", circleHovered);
                stage.addChild(circle);
            }
        }

        // stage properties
        // stage.enableMouseOver(20);
        stage.on("stagemousemove", onMouseMoveInStage);

        stage.on("stagemousedown", function(event){
            isMouseDown = true;
            onMouseMoveInStage(event);
        });

        stage.on("stagemouseup", function(event){
            isMouseDown = false;
            lastChangedCircleRowColumn = "";
        });

        stage.update();
    };

    var toString = function(){
        return valueGrid.join("");
    };

    var setMode = function(modeValue) {
        mode = modeValue;
    };

    // exports
    window.FacePanelEditor = {
        init: init,
        toString: toString,
        clear: clear,
        toBase64: toBase64,
        load: load,
        setMode: setMode
    };
})();



