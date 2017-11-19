/**
 * Create a new variable on the given workspace.
 * @param {!Blockly.Workspace} workspace The workspace on which to create the
 *     variable.
 * @return {null|undefined|string} An acceptable new variable name, or null if
 *     change is to be aborted (cancel button), or undefined if an existing
 *     variable was chosen.
 */
Blockly.Variables.createVariable = function(workspace) {
  while (true) {
    var text = Blockly.Variables.promptName(Blockly.Msg.NEW_VARIABLE_TITLE, '');
    if (text) {
      if (workspace.variableIndexOf(text) != -1) {
        window.alert(Blockly.Msg.VARIABLE_ALREADY_EXISTS.replace('%1',
            text.toLowerCase()));
      } else {
        workspace.createVariable(text);
        break;
      }
    } else {
      text = null;
      break;
    }
  }
  return text;
};


/**
 * Prompt the user for a new variable name.
 * @param {string} promptText The string of the prompt.
 * @param {string} defaultText The default value to show in the prompt's field.
 * @return {?string} The new variable name, or null if the user picked
 *     something illegal.
 */
Blockly.Variables.promptName = function(promptText, defaultText) {
  if(checkDeviceType().android || true) {
    // Modified by Hyman: mock prompt dialog. Use callback to handler the vairable change events.
    var callback = function(newVar) {
      if (newVar) {
        newVar = newVar.replace(/[\s\xa0]+/g, ' ').replace(/^ | $/g, '');
        if (newVar == Blockly.Msg.RENAME_VARIABLE ||
            newVar == Blockly.Msg.NEW_VARIABLE) {
          // Ok, not ALL names are legal...
          newVar = null;
        }
      }
      if (newVar && workspace.variableIndexOf(newVar) == -1) {
        workspace.renameVariable(defaultText, newVar);
      }
    };

    var options = {
      type: 'prompt',
      title: promptText,
      defaultText: defaultText,
      callback: callback
    };
    new MBlockly.Modal(options).show();
    return null;
  } else {
    var newVar = window.prompt(promptText, defaultText);
    // Merge runs of whitespace.  Strip leading and trailing whitespace.
    // Beyond this, all names are legal.
    if (newVar) {
      newVar = newVar.replace(/[\s\xa0]+/g, ' ').replace(/^ | $/g, '');
      if (newVar == Blockly.Msg.RENAME_VARIABLE ||
          newVar == Blockly.Msg.NEW_VARIABLE) {
        // Ok, not ALL names are legal...
        newVar = null;
      }
    }
    return newVar;
  }

};
