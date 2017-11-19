package com.gzsll.jsbridge;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gzsll.Logger;

public class WVJBWebViewClient
  extends WebViewClient
{
  private WVJBWebView mWVJBWebView;
  
  public WVJBWebViewClient(WVJBWebView paramWVJBWebView)
  {
    this.mWVJBWebView = paramWVJBWebView;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (paramString.startsWith("wvjbscheme"))
    {
      if (paramString.toUpperCase().indexOf("__BRIDGE_LOADED__") > 0) {
        this.mWVJBWebView.injectJavascriptFile();
      }
      for (;;)
      {
        return true;
        if (paramString.toUpperCase().indexOf("__WVJB_QUEUE_MESSAGE__") > 0) {
          this.mWVJBWebView.flushMessageQueue();
        } else {
          Logger.d("UnkownMessage:" + paramString);
        }
      }
    }
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\gzsll\jsbridge\WVJBWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */