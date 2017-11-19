MBlockly = MBlockly || {};

$(document).ready(function() {
    var eventType = getEventType();
    var example = MBlockly.example;

    $('.menu-tip').on(eventType, function(e) {
        // $('.tips').toggle();
        MBlockly.WhenEvent.activateWhenBlocks(3, 1);
    });

    $('#blocklyDiv').on(eventType, function(e) {
        e.stopPropagation();
        $('.tips').hide();
    });

    $('.neuron-test .flex').on(eventType, function(e) {
        if($(this).hasClass('show')) {
           $(this).removeClass('show');
           $('.neuron-test table').hide();
        } else {
            $(this).addClass('show');
            $('.neuron-test table').show();
        }
    });

    // music icon
    $('.menu-music').on(eventType, function() {

        if($(this).hasClass('off')) {
            $(this).removeClass('off');
            example.playMusic();

        } else {
            $(this).addClass('off');
            example.pauseMusic();
        }
    });

    // servo tip
    $('.servo-tip').on(eventType, function(e) {
        $('.servo-record').toggle();
    });


    // tools
    $('.open-hightlight').on(eventType, function(e) {
        MBlockly.Settings.OPEN_HIGHLIGHT = true;
    });
    $('.close-hightlight').on(eventType, function(e) {
        MBlockly.Settings.OPEN_HIGHLIGHT = false;
    });
    $('.up-speed').on(eventType, function(e) {
        MBlockly.Settings.EXCUTE_CODE_DIRECTLY = true;
    });
    $('.normal-speed').on(eventType, function(e) {
        MBlockly.Settings.EXCUTE_CODE_DIRECTLY = false;
    });

    $('.open-report').on(eventType, function(e) {
        for(var i in MBlockly.Neurons.servoCache) {
            MBlockly.Servo.setServoReportMode(i, 1);
        }
    });
    $('.close-report').on(eventType, function(e) {
        for(var i in MBlockly.Neurons.servoCache) {
            MBlockly.Servo.setServoReportMode(i, 0);
        }
    });
});