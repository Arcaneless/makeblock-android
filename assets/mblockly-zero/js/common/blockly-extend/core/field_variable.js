/**
 * Event handler for a change in variable name.
 * Special case the 'Rename variable...' and 'Delete variable...' options.
 * In the rename case, prompt the user for a new name.
 * @param {string} text The selected dropdown menu option.
 * @return {null|undefined|string} An acceptable new variable name, or null if
 *     change is to be either aborted (cancel button) or has been already
 *     handled (rename), or undefined if an existing variable was chosen.
 */
Blockly.FieldVariable.prototype.classValidator = function(text) {
  var workspace = this.sourceBlock_.workspace;
  if (text == Blockly.Msg.RENAME_VARIABLE) {
    var oldVar = this.getText();
    Blockly.hideChaff();
    text = Blockly.Variables.promptName(
        Blockly.Msg.RENAME_VARIABLE_TITLE.replace('%1', oldVar), oldVar);
    if (text) {
      workspace.renameVariable(oldVar, text);
    }
    return null;
  } else if (text == Blockly.Msg.DELETE_VARIABLE.replace('%1',
      this.getText())) {
    workspace.deleteVariable(this.getText());
    return null;
  }
  return undefined;
};
