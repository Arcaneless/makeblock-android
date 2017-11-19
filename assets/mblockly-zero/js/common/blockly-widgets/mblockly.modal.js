/**
 * Copyright 2016 Makeblock
 * Author: Hyman
 * Description: provide an modal dialog interface.
 */

'use strict';

MBlockly.Modal = function(options) {
  this.type = options.type;
  this.title = options.title;
  this.value = options.defaultText;
  this.callback = options.callback;
  this.eventType = getEventType();
  this.initDialog(this.type);
};

MBlockly.Modal.prototype.initDialog = function(type) {
  var content = "";
  if(!$('.field-wrapper').length) {
    if(type == 'prompt') {
      content = '<div class="field-wrapper"><div class="field-mask"></div>' +
                    '<div class="field-modal">' +
                      '<div class="header">' +
                        '<h4>' + this.title + '</h4>' +
                      '</div>' +
                      '<div class="content">' +
                        '<input type="text" autoFocus value="' + this.value + '">' +
                      '</div>' +
                      '<div class="footer">' +
                        '<button class="cancel">' + Blockly.Msg.DIALOG_BTN_CANCEL + '</button><button class="confirm">' + Blockly.Msg.DIALOG_BTN_CONFIRM + '</button>' +
                      '</div>' +
                    '</div></div>';
    }

    if(type == "confirm") {
      content = '<div class="field-wrapper"><div class="field-mask"></div>' +
                      '<div class="field-modal">' +
                        '<div class="header">' +
                          '<h4>' + this.title + '</h4>' +
                        '</div>' +
                        '<div class="footer">' +
                          '<button class="cancel">' + Blockly.Msg.DIALOG_BTN_CANCEL + '</button><button class="confirm">' + Blockly.Msg.DIALOG_BTN_CONFIRM + '</button>' +
                        '</div>' +
                      '</div></div>';
    }

    if(type == "alert") {
      content = '<div class="field-wrapper"><div class="field-mask"></div>' +
                      '<div class="field-modal">' +
                        '<div class="header">' +
                          '<h4>' + this.title + '</h4>' +
                        '</div>' +
                        '<div class="footer">' +
                          '<button class="confirm">' + Blockly.Msg.DIALOG_BTN_CONFIRM + '</button>' +
                        '</div>' +
                      '</div></div>';
    }
    $(document.body).append($(content));
  }
  this.modal = $('.field-wrapper');
}

MBlockly.Modal.prototype.registerEvents = function(type) {
  var that = this;
  if(type == "prompt") {
    $('.field-modal .cancel').on(this.eventType, function(e) {
      that.hide(e);
    });

    $('.field-modal .confirm').on(this.eventType, function(e) {
        that.value = $('.field-modal input').val();
        that.hide(e);
    });
  }

  if(type == "confirm") {
    $('.field-modal .cancel').on(this.eventType, function(e) {
      that.value = false;
      that.hide(e);
    });

    $('.field-modal .confirm').on(this.eventType, function(e) {
      that.value = true;
      that.hide(e);
    });
  }

  if(type == "alert") {
    $('.field-modal .confirm').on(this.eventType, function(e) {
      that.hide(e);
    });
  }
};

MBlockly.Modal.prototype.show = function() {
  this.modal.show();
  this.registerEvents(this.type);
};

MBlockly.Modal.prototype.hide = function(event) {
  this.callback(this.value);
  setTimeout(function(){window.scrollTo(0,0);}, 50)
  this.modal.find('input').blur();
  event.stopPropagation();
  event.preventDefault();
  this.modal.remove();
};

MBlockly.Modal.prototype.getValue = function() {
  return this.value;
};

