/**
 * Copyright 2015 Makeblock
 * Author: Wangyu
 * Description: provide an face panel input interface
 * to input numbers between 0-255;
 *
 */

'use strict';

goog.provide('MBlockly.FacePanelInput');

goog.require('Blockly.Blocks');


MBlockly.FacePanelInput = function() {
  var blankPic = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADQAAAAaCAYAAAD43n+tAAAArklEQ…5qVXHFjwIRQebMgr46WeFlkft5D1m5Bcms7ETkDnUyVkZ7AiFyzBs+U8H7AAAAAElFTkSuQmCC";
  var defaultValue = "00000000000000000011100001000100010001000011100000000010000000100000001000000010001110000100010001000100001110000000000000000000";
  this.sourceBlock_ = null;
  // Ensure height and width are numbers.  Strings are bad at math.
  this.src = blankPic;
  this.canvasWidth = 552;
  this.canvasHeight = 275;

  this.width_ = this.canvasWidth / 15;
  this.height_ = this.canvasHeight / 15;

  if(checkDeviceType().phone) {
    this.canvasWidth = 424;
    this.canvasHeight = 211;
  }

  this.size_ = {height: this.height_, width: this.width_};
  this.setImage(this.src);

  this.setValue(defaultValue);
};
goog.inherits(MBlockly.FacePanelInput, Blockly.Field);

/**
 * Override init function.
 */
MBlockly.FacePanelInput.prototype.init = function() {
  if (this.fieldGroup_) {
    // Image has already been initialized once.
    return;
  }
  // Build the DOM.
  /** @type {SVGElement} */
  this.fieldGroup_ = Blockly.createSvgElement('g', {}, null);
  if (!this.visible_) {
    this.fieldGroup_.style.display = 'none';
  }

  // modified by Hyman: add background
  this.rectElement_ = Blockly.createSvgElement('rect',
      {
       'x': -2,
       'y': -2,
       'width': this.width_ + 2,
       'fill': 'rgba(255,255,255,.9)',
       'height': this.height_ + 2}, this.fieldGroup_, this.sourceBlock_.workspace);

  /** @type {SVGElement} */
  this.imageElement_ = Blockly.createSvgElement('image',
      {'height': this.height_ + 'px',
       'width': this.width_ + 'px'}, this.fieldGroup_);

  this.sourceBlock_.getSvgRoot().appendChild(this.fieldGroup_);

  // modified by Hyman
  FacePanelEditor.load(this.getValue());
  this.setImage(FacePanelEditor.toBase64(52, 26));

  // bind events.
  this.mouseUpWrapper_ =
      Blockly.bindEvent_(this.fieldGroup_, 'mouseup', this, this.onMouseUp_);
};


/**
 * Get the source URL of this image.
 * @return {string} Current text.
 * @override
 */
MBlockly.FacePanelInput.prototype.getValue = function() {
  return this.text;
};

MBlockly.FacePanelInput.prototype.setValue = function(newText) {

  this.text = newText;
};

/**
 * Set the source URL of this image.
 * @param {?string} src New source.
 * @override
 */
MBlockly.FacePanelInput.prototype.setImage = function(src) {
  if (src === null) {
    // No change if null.
    return;
  }
  this.src_ = src;
  if (this.imageElement_) {
    this.imageElement_.setAttributeNS('http://www.w3.org/1999/xlink',
        'xlink:href', goog.isString(src) ? src : '');
  }
};

Blockly.Blocks['math_facepanel'] = {
  /**
   * Block for numeric value.
   * @this Blockly.Block
   */
  init: function() {
    this.setHelpUrl(Blockly.Msg.MATH_NUMBER_HELPURL);
    this.setColour(Blockly.Blocks.math.HUE);
    this.appendDummyInput()
        .appendField(new MBlockly.FacePanelInput(), 'FACE');
    this.setOutput(true, 'FacePanel');
    this.setTooltip(Blockly.Msg.MATH_NUMBER_TOOLTIP);
  }
};

MBlockly.FacePanelInput.prototype.showEditor_ = function(opt_quietInput) {
  this.workspace_ = this.sourceBlock_.workspace;
  var quietInput = opt_quietInput || false;
  if (!quietInput && goog.userAgent.MOBILE || goog.userAgent.ANDROID || goog.userAgent.IPAD) {

      var self = this;
      var eventType = getEventType();
      // render content to WidgetDiv and then show
      var content = '<div id="facepanel-editor">'+
                         '<div class="facepanel-draw">' +
                         '<canvas width="' + self.canvasWidth + '" height="' + self.canvasHeight + '" id="facePanelCanvas"></canvas></div>'+
                         '<div class="facepanel-ops">' +
                           '<div class="fbtn active" id="f-edit"></div>' +
                           '<div class="fbtn" id="f-erase"></div>' +
                           '<div class="fbtn" id="f-clear"></div>' +
                           '<div class="fbtn" id="f-ok"></div>' +
                         '</div></div>';
      self.showWidgetWithContent(content);

      $('#f-ok').on(eventType, function(){
        self.setValue(FacePanelEditor.toString());
        self.setImage(FacePanelEditor.toBase64(52, 26));
        self.hideWidget();
      });

      $('#f-clear').on(eventType, function(){
        FacePanelEditor.clear();
      });

      $('#f-edit').on(eventType, function(e){
          $('.facepanel-ops .active').removeClass('active');
          $(e.target).addClass('active');
          FacePanelEditor.setMode('draw');
      });

      $('#f-erase').on(eventType, function(e){
        $('.facepanel-ops .active').removeClass('active');
        $(e.target).addClass('active');
        FacePanelEditor.setMode('erase');
      });

      FacePanelEditor.init('facePanelCanvas');
      FacePanelEditor.load(this.getValue());

      $('.modal-dialog-bg').off(eventType);
      $('.modal-dialog-bg').on(eventType, function(){
        self.hideWidget();
      });
  }

  // // for Pc
  // Blockly.WidgetDiv.show(this, this.sourceBlock_.RTL, this.widgetDispose_());

  // // Create the input.
  // var htmlInput = goog.dom.createDom('input', 'blocklyHtmlInput');
  // htmlInput.setAttribute('spellcheck', this.spellcheck_);
  // var fontSize = (Blockly.FieldTextInput.FONTSIZE * this.workspace_.scale) + 'pt';
  // Blockly.WidgetDiv.DIV.style.fontSize = fontSize;
  // htmlInput.style.fontSize = fontSize;
  // /** @type {!HTMLInputElement} */
  // Blockly.FieldTextInput.htmlInput_ = htmlInput;
  // Blockly.WidgetDiv.DIV.appendChild(htmlInput);

  // htmlInput.value = htmlInput.defaultValue = this.text_;
  // htmlInput.oldValue_ = null;
  // this.validate_();
  // this.resizeEditor_();
  // if (!quietInput) {
  //   htmlInput.focus();
  //   htmlInput.select();
  // }

  // // Bind to keydown -- trap Enter without IME and Esc to hide.
  // htmlInput.onKeyDownWrapper_ =
  //     Blockly.bindEvent_(htmlInput, 'keydown', this, this.onHtmlInputKeyDown_);
  // // Bind to keyup -- trap Enter; resize after every keystroke.
  // htmlInput.onKeyUpWrapper_ =
  //     Blockly.bindEvent_(htmlInput, 'keyup', this, this.onHtmlInputChange_);
  // // Bind to keyPress -- repeatedly resize when holding down a key.
  // htmlInput.onKeyPressWrapper_ =
  //     Blockly.bindEvent_(htmlInput, 'keypress', this, this.onHtmlInputChange_);
  // htmlInput.onWorkspaceChangeWrapper_ = this.resizeEditor_.bind(this);
  // this.workspace_.addChangeListener(htmlInput.onWorkspaceChangeWrapper_);
};