/*
 * @fileOverview A communication bridge between native and webview.
 * @author Hyman
 * @copyright 2017 Makeblock.com
 */

function setupWebViewJavascriptBridge(callback) {
  if (window.WebViewJavascriptBridge) {
    return callback(WebViewJavascriptBridge);
  }
  if (window.WVJBCallbacks) {
    return window.WVJBCallbacks.push(callback);
  }
  window.WVJBCallbacks = [callback];
  var WVJBIframe = document.createElement('iframe');
  WVJBIframe.style.display = 'none';

  if(checkDeviceType().android) {
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
  } else {
    WVJBIframe.src = 'https://__bridge_loaded__';
  }

  document.documentElement.appendChild(WVJBIframe);
  setTimeout(function() {
    document.documentElement.removeChild(WVJBIframe)
  }, 0)
}

/**
 * [jsCallNative description]
 * @param  {String} handlerName handler name
 * @param  {Object} json json type
 * @param  {Function} optCallback optional callback defined in js
 */
function jsCallNative(handlerName, json, optCallback) {
  setupWebViewJavascriptBridge(function(bridge) {
    bridge.callHandler(handlerName, json, function responseCallback(responseData) {
      optCallback && optCallback(responseData);
    });
  });
}

/**
 * [nativeCallJs description]
 * @param  {String} handlerName handler name
 * @param  {Function} optCallback [description]
 */
function nativeCallJs(handlerName, optCallback) {
  setupWebViewJavascriptBridge(function(bridge) {
    bridge.registerHandler(handlerName, function(responseData, responseCallback) {
      optCallback && optCallback(responseData, responseCallback);
    });
  });
}

/*
  接口约定

  传值的参数都使用 json 格式来进行

  bridge.registerHandler 方法中：
    - 'getAirblockUltrasonicSensorValue'：被 objectC 直接调用的 js 接口名
    - data：是 objectC 调用该方法时，回传的参数
      {
        "status": 0, // 0: 成功，-1: 失败
        "data": Object, // 数据
        "callback": callback
      }
    - responseCallback：是objectC 调用 js 方法后，在其中注入的回调，可以传值给该回调，告诉原生端

  bridge.callHandler 方法中：
    - 'requestWidgetName'： objectC 中实现的方法，被 js 调用
    - {"id": "1"}：参数
    - responseData：从 objectC 中获取到的数据
      {
        "status": 0, // 0: 成功，-1: 失败
        "data": Object, // 数据
        "callback": callback
      }
*/


// // example
// jsCallNative('sendViaBluetooth', {"data": "ff 55 04 01 00"}, function(res) {
// })

// nativeCallJs('receiveBluetoothData', function(res, responseCallback) {
// });
