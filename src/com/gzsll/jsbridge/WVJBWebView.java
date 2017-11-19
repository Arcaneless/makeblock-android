package com.gzsll.jsbridge;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.gzsll.WVJBMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WVJBWebView
  extends WebView
{
  private Map<String, WVJBHandler> messageHandlers = new HashMap();
  private ArrayList<WVJBMessage> messageQueue = new ArrayList();
  private MyJavascriptInterface myInterface = new MyJavascriptInterface(null);
  private Map<String, WVJBResponseCallback> responseCallbacks = new HashMap();
  private String script;
  private long uniqueId = 0L;
  
  public WVJBWebView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public WVJBWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public WVJBWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private String convertStreamToString(InputStream paramInputStream)
  {
    String str2 = "";
    Object localObject = str2;
    try
    {
      Scanner localScanner = new Scanner(paramInputStream, "UTF-8").useDelimiter("\\A");
      String str1 = str2;
      localObject = str2;
      if (localScanner.hasNext())
      {
        localObject = str2;
        str1 = localScanner.next();
      }
      localObject = str1;
      paramInputStream.close();
      return str1;
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
    return (String)localObject;
  }
  
  private String doubleEscapeString(String paramString)
  {
    return paramString.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f");
  }
  
  private void executeJavascript(String paramString)
  {
    executeJavascript(paramString, null);
  }
  
  private void executeJavascript(final String paramString, final JavascriptCallback paramJavascriptCallback)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      evaluateJavascript(paramString, new ValueCallback()
      {
        public void onReceiveValue(String paramAnonymousString)
        {
          if (paramJavascriptCallback != null)
          {
            String str = paramAnonymousString;
            if (paramAnonymousString != null)
            {
              str = paramAnonymousString;
              if (paramAnonymousString.startsWith("\""))
              {
                str = paramAnonymousString;
                if (paramAnonymousString.endsWith("\"")) {
                  str = paramAnonymousString.substring(1, paramAnonymousString.length() - 1).replaceAll("\\\\", "");
                }
              }
            }
            paramJavascriptCallback.onReceiveValue(str);
          }
        }
      });
      return;
    }
    if (paramJavascriptCallback != null)
    {
      MyJavascriptInterface localMyJavascriptInterface = this.myInterface;
      StringBuilder localStringBuilder = new StringBuilder();
      long l = this.uniqueId + 1L;
      this.uniqueId = l;
      localMyJavascriptInterface.addCallback(l + "", paramJavascriptCallback);
      post(new Runnable()
      {
        public void run()
        {
          WVJBWebView.this.loadUrl("javascript:window.WebViewJavascriptBridgeInterface.onResultForScript(" + WVJBWebView.this.uniqueId + "," + paramString + ")");
        }
      });
      return;
    }
    post(new Runnable()
    {
      public void run()
      {
        WVJBWebView.this.loadUrl("javascript:" + paramString);
      }
    });
  }
  
  private void init()
  {
    if (!isInEditMode())
    {
      getSettings().setJavaScriptEnabled(true);
      addJavascriptInterface(this.myInterface, "WebViewJavascriptBridgeInterface");
      setWebViewClient(new WVJBWebViewClient(this));
    }
  }
  
  private WVJBMessage json2Message(JSONObject paramJSONObject)
  {
    WVJBMessage localWVJBMessage = new WVJBMessage();
    try
    {
      if (paramJSONObject.has("callbackId")) {
        localWVJBMessage.callbackId = paramJSONObject.getString("callbackId");
      }
      if (paramJSONObject.has("data")) {
        localWVJBMessage.data = paramJSONObject.get("data");
      }
      if (paramJSONObject.has("handlerName")) {
        localWVJBMessage.handlerName = paramJSONObject.getString("handlerName");
      }
      if (paramJSONObject.has("responseId")) {
        localWVJBMessage.responseId = paramJSONObject.getString("responseId");
      }
      if (paramJSONObject.has("responseData")) {
        localWVJBMessage.responseData = paramJSONObject.get("responseData");
      }
      return localWVJBMessage;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return localWVJBMessage;
  }
  
  private JSONObject message2Json(WVJBMessage paramWVJBMessage)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (paramWVJBMessage.callbackId != null) {
        localJSONObject.put("callbackId", paramWVJBMessage.callbackId);
      }
      if (paramWVJBMessage.data != null) {
        localJSONObject.put("data", paramWVJBMessage.data);
      }
      if (paramWVJBMessage.handlerName != null) {
        localJSONObject.put("handlerName", paramWVJBMessage.handlerName);
      }
      if (paramWVJBMessage.responseId != null) {
        localJSONObject.put("responseId", paramWVJBMessage.responseId);
      }
      if (paramWVJBMessage.responseData != null) {
        localJSONObject.put("responseData", paramWVJBMessage.responseData);
      }
      return localJSONObject;
    }
    catch (JSONException paramWVJBMessage)
    {
      paramWVJBMessage.printStackTrace();
    }
    return localJSONObject;
  }
  
  private void processMessageQueue(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int i;
    do
    {
      return;
      try
      {
        localJSONArray = new JSONArray(paramString);
      }
      catch (JSONException paramString)
      {
        JSONArray localJSONArray;
        paramString.printStackTrace();
        return;
      }
    } while (i >= localJSONArray.length());
    WVJBMessage localWVJBMessage = json2Message(localJSONArray.getJSONObject(i));
    label68:
    label82:
    WVJBHandler localWVJBHandler;
    if (localWVJBMessage.responseId != null)
    {
      paramString = (WVJBResponseCallback)this.responseCallbacks.remove(localWVJBMessage.responseId);
      break label187;
      paramString.callback(localWVJBMessage.responseData);
      break label194;
      if (localWVJBMessage.callbackId != null) {
        paramString = new WVJBResponseCallback()
        {
          public void callback(Object paramAnonymousObject)
          {
            WVJBMessage localWVJBMessage = new WVJBMessage();
            localWVJBMessage.responseId = this.val$callbackId;
            localWVJBMessage.responseData = paramAnonymousObject;
            WVJBWebView.this.queueMessage(localWVJBMessage);
          }
        };
      }
      localWVJBHandler = (WVJBHandler)this.messageHandlers.get(localWVJBMessage.handlerName);
      break label206;
    }
    for (;;)
    {
      localWVJBHandler.request(localWVJBMessage.data, paramString);
      label187:
      label194:
      label206:
      do
      {
        Log.e("WebViewJavascriptBridge", "No handler for message from JS:" + localWVJBMessage.handlerName);
        break label194;
        i = 0;
        break;
        if (paramString != null) {
          break label68;
        }
        i += 1;
        break;
        paramString = null;
        break label82;
      } while (localWVJBHandler == null);
    }
  }
  
  private void queueMessage(WVJBMessage paramWVJBMessage)
  {
    if (this.messageQueue != null)
    {
      this.messageQueue.add(paramWVJBMessage);
      return;
    }
    dispatchMessage(paramWVJBMessage);
  }
  
  private void sendData(Object paramObject, WVJBResponseCallback paramWVJBResponseCallback, String paramString)
  {
    if ((paramObject == null) && (TextUtils.isEmpty(paramString))) {
      return;
    }
    WVJBMessage localWVJBMessage = new WVJBMessage();
    if (paramObject != null) {
      localWVJBMessage.data = paramObject;
    }
    if (paramWVJBResponseCallback != null)
    {
      paramObject = new StringBuilder().append("java_cb_");
      long l = this.uniqueId + 1L;
      this.uniqueId = l;
      paramObject = l;
      this.responseCallbacks.put(paramObject, paramWVJBResponseCallback);
      localWVJBMessage.callbackId = ((String)paramObject);
    }
    if (paramString != null) {
      localWVJBMessage.handlerName = paramString;
    }
    queueMessage(localWVJBMessage);
  }
  
  public void callHandler(String paramString)
  {
    callHandler(paramString, null, null);
  }
  
  public void callHandler(String paramString, Object paramObject)
  {
    callHandler(paramString, paramObject, null);
  }
  
  public void callHandler(String paramString, Object paramObject, WVJBResponseCallback paramWVJBResponseCallback)
  {
    sendData(paramObject, paramWVJBResponseCallback, paramString);
  }
  
  public void destroy()
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      ((ViewGroup)localViewParent).removeView(this);
    }
    clearAnimation();
    clearHistory();
    getSettings().setJavaScriptEnabled(false);
    stopLoading();
    clearView();
    removeAllViews();
    super.destroy();
  }
  
  public void dispatchMessage(WVJBMessage paramWVJBMessage)
  {
    paramWVJBMessage = doubleEscapeString(message2Json(paramWVJBMessage).toString());
    executeJavascript("WebViewJavascriptBridge._handleMessageFromJava('" + paramWVJBMessage + "');");
  }
  
  public void flushMessageQueue()
  {
    executeJavascript("WebViewJavascriptBridge._fetchQueue()", new JavascriptCallback()
    {
      public void onReceiveValue(String paramAnonymousString)
      {
        if (!TextUtils.isEmpty(paramAnonymousString)) {
          WVJBWebView.this.processMessageQueue(paramAnonymousString);
        }
      }
    });
  }
  
  public void injectJavascriptFile()
  {
    try
    {
      if (TextUtils.isEmpty(this.script)) {
        this.script = convertStreamToString(getResources().getAssets().open("WebViewJavascriptBridge.js"));
      }
      executeJavascript(this.script);
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Iterator localIterator;
        localIOException.printStackTrace();
      }
      this.messageQueue = null;
    }
    if (this.messageQueue != null)
    {
      localIterator = this.messageQueue.iterator();
      while (localIterator.hasNext()) {
        dispatchMessage((WVJBMessage)localIterator.next());
      }
    }
  }
  
  public void registerHandler(String paramString, WVJBHandler paramWVJBHandler)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramWVJBHandler == null)) {
      return;
    }
    this.messageHandlers.put(paramString, paramWVJBHandler);
  }
  
  public void unregisterHandler(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    this.messageHandlers.remove(paramString);
  }
  
  public static abstract interface JavascriptCallback
  {
    public abstract void onReceiveValue(String paramString);
  }
  
  private class MyJavascriptInterface
  {
    Map<String, WVJBWebView.JavascriptCallback> map = new HashMap();
    
    private MyJavascriptInterface() {}
    
    public void addCallback(String paramString, WVJBWebView.JavascriptCallback paramJavascriptCallback)
    {
      this.map.put(paramString, paramJavascriptCallback);
    }
    
    @JavascriptInterface
    public void onResultForScript(String paramString1, String paramString2)
    {
      paramString1 = (WVJBWebView.JavascriptCallback)this.map.remove(paramString1);
      if (paramString1 != null) {
        paramString1.onReceiveValue(paramString2);
      }
    }
  }
  
  public static abstract interface WVJBHandler
  {
    public abstract void request(Object paramObject, WVJBWebView.WVJBResponseCallback paramWVJBResponseCallback);
  }
  
  public static abstract interface WVJBResponseCallback
  {
    public abstract void callback(Object paramObject);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\gzsll\jsbridge\WVJBWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */