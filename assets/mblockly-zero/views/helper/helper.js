$(function() {
  if (typeof TellNative == 'undefined') {
    TellNative = {
      requestLoadProject: function() {},
      reportCurrentWidget: function() {},
      saveControlPanel: function() {},
      sendValueToWidget: function() {},
      sendViaBluetooth: function() {},
      requestBluetoothReconnect: function() {},
      sendViaBluetoothUnreliably: function() {}
    };
  }

  /**
   * [description]
   * @param  {String} methodName
   * @param  {Function} responseCallback)
   */
  nativeCallJs('requestAirblockData', function(methodName, responseCallback) {
    var result = MBlockly.Airblock[methodName]();
    responseCallback(result);
  });

  /**
   * native eval js directly
   * @param  {String} jsStr  eg: 'MBlockly.Neurons.engine.getActiveBlocks().AIRBLOCK'
   * @param  {Function} responseCallback
   */
  nativeCallJs('evalJs', function(jsStr, responseCallback) {
    var result = eval(jsStr);
    responseCallback(result);
  });

  /**
   * excuteAirblockCmd
   * @param  {String} jsonStr
   *    '{
   *      "methodName": "airblockSetWorkMode",
   *      "params": "{}" || ""
   *    }'
   * @param  {Function} responseCallback
   */
  nativeCallJs('excuteAirblockCmd', function(jsonStr, responseCallback) {
    var data = JSON.parse(jsonStr);
    var methodName = data.methodName;
    var params = data.params;
    MBlockly.Airblock[methodName](params);
    responseCallback("success");
  });
});