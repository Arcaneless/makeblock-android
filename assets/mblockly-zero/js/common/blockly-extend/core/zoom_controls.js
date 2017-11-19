/**
 * the width of the zoom rect
 */
Blockly.ZoomControls.prototype.ZOOM_RECT_WIDTH = 40;
/**
 * the padding of the zoom rect
 */
Blockly.ZoomControls.prototype.ZOOM_RECT_PADDING = 8;
/**
 * the height of the zoom-control's background
 */
Blockly.ZoomControls.prototype.ZOOM_BG_HEIGHT = 56;
/**
 * the width of the zoom-control's background
 */
Blockly.ZoomControls.prototype.ZOOM_BG_WIDTH = 248;
/**
 * the y axis of the zoom-control's background
 */
Blockly.ZoomControls.prototype.ZOOM_BG_Y = 64;
/**
 * Create the zoom controls.
 * @return {!Element} The zoom controls SVG group.
 */
Blockly.ZoomControls.prototype.createDom = function() {
  var workspace = this.workspace_;
  /* Here's the markup that will be generated:
  <g class="blocklyZoom">
    //modified by jeremy
    // add undo and redo button as bellow
    <clippath id="blocklyZoomUndoClipPath837493">
      <rect width="32" height="32" y="77"></rect>
    </clippath>
    <image width="96" height="124" x="-64" y="-15" xlink:href="media/sprites.png"
        clip-path="url(#blocklyZoomoutClipPath837493)"></image>
    <clippath id="blocklyZoomReduClipPath837493">
      <rect width="32" height="32" y="77"></rect>
    </clippath>
    <image width="96" height="124" x="-64" y="-15" xlink:href="media/sprites.png"
        clip-path="url(#blocklyZoomoutClipPath837493)"></image>

    <clippath id="blocklyZoomoutClipPath837493">
      <rect width="32" height="32" y="77"></rect>
    </clippath>
    <image width="96" height="124" x="-64" y="-15" xlink:href="media/sprites.png"
        clip-path="url(#blocklyZoomoutClipPath837493)"></image>
    <clippath id="blocklyZoominClipPath837493">
      <rect width="32" height="32" y="43"></rect>
    </clippath>
    <image width="96" height="124" x="-32" y="-49" xlink:href="media/sprites.png"
        clip-path="url(#blocklyZoominClipPath837493)"></image>
    <clippath id="blocklyZoomresetClipPath837493">
      <rect width="32" height="32"></rect>
    </clippath>
    <image width="96" height="124" y="-92" xlink:href="media/sprites.png"
        clip-path="url(#blocklyZoomresetClipPath837493)"></image>
  </g>
  */
  //image
  var img_width = Blockly.ZOOM_CTRL.width / 3.125;
  var img_height = Blockly.ZOOM_CTRL.height / 3.125;
  //适配Phone
  if(checkDeviceType().phone){
    this.ZOOM_BG_Y = 80;
    this.ZOOM_RECT_WIDTH = 32;
    this.ZOOM_RECT_PADDING = 4;
    this.ZOOM_BG_WIDTH = this.ZOOM_RECT_WIDTH * 5 + this.ZOOM_RECT_PADDING * 6; //184
    this.ZOOM_BG_HEIGHT = this.ZOOM_RECT_WIDTH + this.ZOOM_RECT_PADDING *2;
    //image
    img_width = Blockly.ZOOM_CTRL.width / 3.90625;
    img_height = Blockly.ZOOM_CTRL.height / 3.90625;
  }
  //rect
  var w_ = this.ZOOM_RECT_WIDTH;
  var pad_ = this.ZOOM_RECT_PADDING;
  var width = this.ZOOM_BG_WIDTH;
  var height = this.ZOOM_BG_HEIGHT;
  var init_y = this.ZOOM_BG_Y + pad_;
  //g
  this.svgGroup_ = Blockly.createSvgElement('g',
      {'class': 'blocklyZoom', 'id': 'blocklyZoom'}, null);
  //radius-rect
  Blockly.createSvgElement('rect',
      {'class': 'blocklyZoomBg',
       'width': width,
       'height': height,
       'x': 0,
       'y': this.ZOOM_BG_Y,
       'rx': height/2,
       'ry': height/2,
       'style': 'fill:#fff;stroke:#dedede;stroke-width:1;'
      },
      this.svgGroup_);

  var rnd = String(Math.random()).substring(2);;
  //custom to
  if (MBlockly.Settings.OPEN_UNDO) {
    //Undo
    var clip = Blockly.createSvgElement('clipPath',
        {'id': 'blocklyZoomUndoClipPath' + rnd},
        this.svgGroup_);
    Blockly.createSvgElement('rect',
        {'width': w_, 'height': w_, 'x': pad_, 'y': init_y},
        clip
    );
    var zoomUndoSvg = Blockly.createSvgElement('image',
        {'id': 'undoSvg', 'class': 'zoomundo',
         'width': img_width,
         'height': img_height,
         'x': pad_,
         'y': this.ZOOM_BG_Y + pad_,
         'data-default': pad_ - w_,
         'data-active': pad_ - w_ *2,
         'data-disabled': pad_,
         'clip-path': 'url(#blocklyZoomUndoClipPath' + rnd + ')'},
        this.svgGroup_
    );
    zoomUndoSvg.setAttributeNS(Blockly.HTML_LINK_NS, 'xlink:href', Blockly.ZOOM_CTRL.url);

    //Redo
    var clip = Blockly.createSvgElement('clipPath',
        {'id': 'blocklyZoomRedoClipPath' + rnd},
        this.svgGroup_);
    Blockly.createSvgElement('rect',
        {'width': this.ZOOM_RECT_WIDTH, 'height': this.ZOOM_RECT_WIDTH, 'x': w_ + pad_*2, 'y': init_y},
        clip);
    var zoomRedoSvg = Blockly.createSvgElement('image',
        {'id': 'redoSvg', 'class': 'zoomredo',
         'width': img_width,
         'height': img_height,
         'x': w_ + pad_*2,
         'y': init_y - w_,
         'data-default': pad_*2,
         'data-active': pad_*2 - w_,
         'data-disabled': w_ + pad_*2,
         'clip-path': 'url(#blocklyZoomRedoClipPath' + rnd + ')'},
        this.svgGroup_
    );
    zoomRedoSvg.setAttributeNS(Blockly.HTML_LINK_NS, 'xlink:href', Blockly.ZOOM_CTRL.url);
  }
  //position on the center
  //Reset
  var clip = Blockly.createSvgElement('clipPath',
      {'id': 'blocklyZoomresetClipPath' + rnd},
      this.svgGroup_);
  Blockly.createSvgElement('rect',
      {'width': this.ZOOM_RECT_WIDTH, 'height': this.ZOOM_RECT_WIDTH, 'x': w_*2 + pad_*3, 'y': init_y},
      clip
  );
  var zoomresetSvg = Blockly.createSvgElement('image',
      {'class': 'zoomreset',
       'width': img_width,
       'height': img_height,
       'x': w_ + pad_*3,
       'y': init_y - w_*2,
       'data-default': w_ + pad_*3,
       'data-active': pad_*3,
       'data-disabled': w_*2 + pad_*3,
       'clip-path': 'url(#blocklyZoomresetClipPath' + rnd + ')'},
      this.svgGroup_);
  zoomresetSvg.setAttributeNS(Blockly.HTML_LINK_NS, 'xlink:href', Blockly.ZOOM_CTRL.url);

  //In
  var clip = Blockly.createSvgElement('clipPath',
      {'id': 'blocklyZoominClipPath' + rnd},
      this.svgGroup_);
  Blockly.createSvgElement('rect',
      {'width': w_, 'height': w_, 'x': w_*3 + pad_*4, 'y': init_y},
      clip);
  var zoominSvg = Blockly.createSvgElement('image',
      {'class': 'zoomin',
       'width': img_width,
       'height': img_height,
       'x': w_*2 + pad_*4,//152,
       'y': init_y - w_*3,
       'data-default': w_*2 + pad_*4,
       'data-active': w_*1 + pad_*4,
       'data-disabled': w_*3 + pad_*4,
       'clip-path': 'url(#blocklyZoominClipPath' + rnd + ')'},
      this.svgGroup_);
  zoominSvg.setAttributeNS(Blockly.HTML_LINK_NS, 'xlink:href', Blockly.ZOOM_CTRL.url);

  //out
  var clip = Blockly.createSvgElement('clipPath',
      {'id': 'blocklyZoomoutClipPath' + rnd},
      this.svgGroup_);
  Blockly.createSvgElement('rect',
      {'width': w_, 'height': w_, 'x': w_*4 + pad_*5, 'y': init_y},
      clip);
  var zoomoutSvg = Blockly.createSvgElement('image',
      { 'class': 'zoomout',
        'width': img_width,
       'height': img_height,
       'x': w_*3 + pad_*5,
       'y': init_y - w_*4,
       'data-default': w_*3 + pad_*5,
       'data-active': w_*2 + pad_*5,
       'data-disabled': w_*4 + pad_*5,
       'clip-path': 'url(#blocklyZoomoutClipPath' + rnd + ')'},
      this.svgGroup_);
  zoomoutSvg.setAttributeNS(Blockly.HTML_LINK_NS, 'xlink:href', Blockly.ZOOM_CTRL.url);

  // Attach event listeners.
  //Redo
  zoomRedoSvg && Blockly.bindEvent_(zoomRedoSvg, 'mousedown', null, function(e) {
    e.stopPropagation();  //? Don't start a workspace scroll.
    e.preventDefault();  //? Stop double-clicking from selecting text.
    //不可用
    if(zoomRedoSvg.classList.contains('disabled')){
      return false;
    }
    zoomRedoSvg.setAttribute('x', zoomRedoSvg.getAttribute('data-active'));
    workspace.undo(true);
  });
  //Undo
  zoomUndoSvg && Blockly.bindEvent_(zoomUndoSvg, 'mousedown', null, function(e) {
    e.stopPropagation();  //? Don't start a workspace scroll.
    e.preventDefault();  //? Stop double-clicking from selecting text.
    //不可用
    if(zoomUndoSvg.classList.contains('disabled')){
      return false;
    }
    zoomUndoSvg.setAttribute('x', zoomUndoSvg.getAttribute('data-active'));
    workspace.undo(false);
  });
  Blockly.bindEvent_(zoomresetSvg, 'mousedown', null, function(e) {
    e.stopPropagation();  // Don't start a workspace scroll.
    e.preventDefault();  // Stop double-clicking from selecting text.
    zoomresetSvg.setAttribute('x', zoomresetSvg.getAttribute('data-active'));
    //重置到初始状态
    zoomoutSvg.classList.remove('disabled');
    zoomoutSvg.setAttribute('x', zoomoutSvg.getAttribute('data-default'));
    //重置到初始状态
    zoominSvg.classList.remove('disabled');
    zoominSvg.setAttribute('x', zoominSvg.getAttribute('data-default'));
    workspace.setScale(workspace.options.zoomOptions.startScale);//设置成其初设值
    workspace.scrollCenter();
  });
  // zoomin
  Blockly.bindEvent_(zoominSvg, 'mousedown', null, function(e) {
    e.stopPropagation();  // Don't start a workspace scroll.
    e.preventDefault();  // Stop double-clicking from selecting text.
    //移除不可用
    zoomoutSvg.classList.remove('disabled');
    zoomoutSvg.setAttribute('x', zoomoutSvg.getAttribute('data-default'));
    //不可用
    if(zoominSvg.classList.contains('disabled')){
      return false;
    }
    //激活
    zoominSvg.setAttribute('x', zoominSvg.getAttribute('data-active'));
    workspace.zoomCenter(-1);
  });
  // zoomout
  Blockly.bindEvent_(zoomoutSvg, 'mousedown', null, function(e) {
    e.stopPropagation();  // Don't start a workspace scroll.
    e.preventDefault();  // Stop double-clicking from selecting text.
    //移除不可用
    zoominSvg.classList.remove('disabled');
    zoominSvg.setAttribute('x', zoominSvg.getAttribute('data-default'));
    //不可用
    if(zoomoutSvg.classList.contains('disabled')){
      return false;
    }
    //激活
    zoomoutSvg.setAttribute('x', zoomoutSvg.getAttribute('data-active'));
    workspace.zoomCenter(1);
  });

  //Redo
  zoomRedoSvg && Blockly.bindEvent_(zoomRedoSvg, 'mouseup', null, function(e) {
    if(!zoomRedoSvg.classList.contains('disabled')){
      zoomRedoSvg.setAttribute('x', zoomRedoSvg.getAttribute('data-default'));
    }
    e.stopPropagation();
  });
  //Undo
  zoomUndoSvg && Blockly.bindEvent_(zoomUndoSvg, 'mouseup', null, function(e) {
    if(!zoomRedoSvg.classList.contains('disabled')){
      zoomRedoSvg.setAttribute('x', zoomRedoSvg.getAttribute('data-default'));
    }
    e.stopPropagation();
  });
  //zoomreset
  Blockly.bindEvent_(zoomresetSvg, 'mouseup', null, function(e) {
    zoomresetSvg.setAttribute('x', zoomresetSvg.getAttribute('data-default'));
    e.stopPropagation();
  });
  //zoomin
  Blockly.bindEvent_(zoominSvg, 'mouseup', null, function(e) {
    if(!zoominSvg.classList.contains('disabled')){
      zoominSvg.setAttribute('x', zoominSvg.getAttribute('data-default'));
    }
    e.stopPropagation();
  });
  //zoomout
  Blockly.bindEvent_(zoomoutSvg, 'mouseup', null, function(e) {
    if(!zoomoutSvg.classList.contains('disabled')){
      zoomoutSvg.setAttribute('x', zoomoutSvg.getAttribute('data-default'));
    }
    e.stopPropagation();
  });
  return this.svgGroup_;
};


/**
 * Move the zoom controls to the bottom-right corner.
 */
Blockly.ZoomControls.prototype.position = function() {
  var metrics = this.workspace_.getMetrics();
  if (!metrics) {
    // There are no metrics available (workspace is probably not visible).
    return;
  }
  if (this.workspace_.RTL) {
    this.left_ = this.MARGIN_SIDE_ + Blockly.Scrollbar.scrollbarThickness;
    if (metrics.toolboxPosition == Blockly.TOOLBOX_AT_LEFT) {
      this.left_ += metrics.flyoutWidth;
      if (this.workspace_.toolbox_) {
        this.left_ += metrics.absoluteLeft;
      }
    }
  } else {
    this.left_ = metrics.viewWidth + metrics.absoluteLeft /*-
        this.WIDTH_ - this.MARGIN_SIDE_ - Blockly.Scrollbar.scrollbarThickness*/;

    if (metrics.toolboxPosition == Blockly.TOOLBOX_AT_RIGHT) {
      this.left_ -= metrics.flyoutWidth;
    }
  }
  this.top_ = metrics.viewHeight + metrics.absoluteTop -
      this.HEIGHT_ - this.bottom_;
  if (metrics.toolboxPosition == Blockly.TOOLBOX_AT_BOTTOM) {
    this.top_ -= metrics.flyoutHeight;
  }
  var left_ = (this.left_ - this.ZOOM_BG_WIDTH)/2;
  // var bg_ = this.svgGroup_.getElementsByClassName('blocklyZoomBg')[0];
  // bg_.setAttribute('transform', 'translate(' + left_ + ',0)');
  this.svgGroup_.setAttribute('transform',
      'translate(' + left_ + ',' + this.top_ + ')');
};