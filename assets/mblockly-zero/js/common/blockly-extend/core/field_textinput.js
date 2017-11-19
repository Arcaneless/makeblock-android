// Add method to customize widget content
Blockly.Field.prototype.showWidgetWithContent = function(htmlStr) {
    Blockly.WidgetDiv.DIV.innerHTML = htmlStr;
    Blockly.WidgetDiv.show(this, this.sourceBlock_.RTL, null, true);
};


//初始化modal
Blockly.Field.prototype.initModal = function(content, modal_classname){
  return  '<div class="field">' +
            '<div class="field-modal '+(modal_classname||'')+'">' +
              '<button class="img-icon icon-close img-icon-sm btn btn-circle btn-red md-close"></button>' +
              '<div class="field-content">' +
                  content +
              '</div>' +
            '</div>' +
          '</div>';
}

// // Add method to customize widget content
// Blockly.Field.prototype.showWidgetWithContentForSmartServo = function(content, classname) {
//   var htmlStr = this.initModal(content, classname);
//   //将 modal 嵌入 WidgetDiv
//   Blockly.WidgetDiv.DIV.innerHTML = htmlStr;
//   Blockly.WidgetDiv.addClassAndMask('widget-center');
//   Blockly.WidgetDiv.show(this, this.sourceBlock_.RTL, null, true);

//   //组件关闭按钮 绑定事件
//   // var btn_close = Blockly.WidgetDiv.DIV.getElementsByClassName('icon-close')[0];
//   // Blockly.bindEvent_(btn_close, 'mouseup', this, function(e){
//   //   Blockly.WidgetDiv.hide();
//   //   e.preventDefault();
//   //   e.stopPropagation();
//   // });
//   return Blockly.WidgetDiv.DIV;
// };

Blockly.Field.prototype.hideWidget = function(e) {
  Blockly.WidgetDiv.hide();
};

Blockly.Field.prototype.hideWidgetDelay = function(time) {
  setTimeout(function(){
    Blockly.WidgetDiv.hide();
  }, time||100);
};

