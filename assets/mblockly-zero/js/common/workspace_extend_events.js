/**
 * @fileOverview Define workspace extend common events.
 */

MBlockly = MBlockly || {};
MBlockly.WorkspaceEventsHandler = {
    hideDeleteMask: function() {
        $('#blockDeletionMask').hide();
        $('#blockDeletionMask').removeClass('mouse-over');
    },
    showDeleteMask: function(event) {
        event.stopPropagation();
        $('#blockDeletionMask').show();
        $('#blockDeletionMask').removeClass('mouse-over');
    },
    showDeleteDarkMask: function(event) {
        event.stopPropagation();
        $('#blockDeletionMask').show();
        $('#blockDeletionMask').addClass('mouse-over');
    }
};


/**
 * handle delete area events.
 */
Blockly.onBlockMouseUp = function() {
    MBlockly.WorkspaceEventsHandler.hideDeleteMask();
};

Blockly.onMouseOverToolboxDeletionZoneHandler = function(event) {
    MBlockly.WorkspaceEventsHandler.showDeleteDarkMask(event);
};

Blockly.onMouseOutToolboxDeletionZoneHandler = function(event) {
    MBlockly.WorkspaceEventsHandler.showDeleteMask(event);
};