/**
 * Delete a variables and all of its uses from this workspace.
 * @param {string} name Name of variable to delete.
 */
Blockly.Workspace.prototype.deleteVariable = function(name) {
  var that = this;
  var variableIndex = this.variableIndexOf(name);

  if (variableIndex != -1) {
    var uses = this.getVariableUses(name);
    var deleteTargetBlock = function() {
      Blockly.Events.setGroup(true);
      for (var i = 0; i < uses.length; i++) {
        uses[i].dispose(true, false);
      }
      Blockly.Events.setGroup(false);
      that.variableList.splice(variableIndex, 1);
    }

    if (uses.length > 1) {
      for (var i = 0, block; block = uses[i]; i++) {
        if (block.type == 'procedures_defnoreturn' ||
          block.type == 'procedures_defreturn') {
          var procedureName = block.getFieldValue('NAME');
          window.alert(
              Blockly.Msg.CANNOT_DELETE_VARIABLE_PROCEDURE.replace('%1', name).
              replace('%2', procedureName));
          return;
        }
      }

      if(checkDeviceType().android || true) {
        // modified by Hyman
        var that = this;
        var callback = function(isConfirm) {
          if(!isConfirm) {
            return false;
          }
          deleteTargetBlock();
        };

        var options = {
          type: 'confirm',
          title: Blockly.Msg.DELETE_VARIABLE_CONFIRMATION.replace('%1', uses.length).replace('%2', name),
          callback: callback
        };
        new MBlockly.Modal(options).show();
      } else {
        // ios use the default setting
        var ok = window.confirm(
            Blockly.Msg.DELETE_VARIABLE_CONFIRMATION.replace('%1', uses.length).
            replace('%2', name));
        if (!ok) {
          return;
        }
        deleteTargetBlock();
      }
    } else {
      deleteTargetBlock();
    }
  }
};