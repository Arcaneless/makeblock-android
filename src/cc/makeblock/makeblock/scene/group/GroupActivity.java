package cc.makeblock.makeblock.scene.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.customview.ListenerScrollWebView;
import cc.makeblock.makeblock.customview.ListenerScrollWebView.ScrollListener;
import cc.makeblock.makeblock.engine.GlideApp;
import cc.makeblock.makeblock.engine.GlideRequest;
import cc.makeblock.makeblock.engine.GlideRequests;
import cc.makeblock.makeblock.engine.utils.AndroidBug5497Workaround;

public class GroupActivity
  extends BaseActivity
{
  private static final int FILE_CHOOSER_RESULT_CODE = 1;
  public static final String URL_FOR_LOADING_KEY = "URL_FOR_LOADING_KEY";
  private PercentRelativeLayout connectFailLayout;
  private PercentRelativeLayout connectLayout;
  private boolean isAnimationEnd = true;
  private boolean isNavBarShow = true;
  ValueCallback<Uri> mUploadMessage;
  ValueCallback<Uri[]> mUploadMessageArray;
  private View navBar;
  private View navForward;
  private View navGoBack;
  private boolean onPageLoadSuccess = false;
  private String urlForLoading;
  private ListenerScrollWebView webView;
  
  private void hideNavBar()
  {
    if ((this.isNavBarShow) && (this.isAnimationEnd))
    {
      ViewPropertyAnimator localViewPropertyAnimator = this.navBar.animate().translationY(this.navBar.getHeight());
      localViewPropertyAnimator.setListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          super.onAnimationEnd(paramAnonymousAnimator);
          GroupActivity.access$802(GroupActivity.this, true);
        }
      });
      localViewPropertyAnimator.start();
      this.isAnimationEnd = false;
      this.isNavBarShow = false;
    }
  }
  
  private void onForwardPressed()
  {
    if ((this.webView.canGoForward()) && (this.onPageLoadSuccess)) {
      this.webView.goForward();
    }
  }
  
  private void onGoBackPressed()
  {
    if ((this.webView.canGoBack()) && (this.onPageLoadSuccess)) {
      this.webView.goBack();
    }
  }
  
  private void onPageLoadFinished()
  {
    this.connectLayout.setVisibility(8);
    if (this.onPageLoadSuccess) {
      this.webView.setVisibility(0);
    }
    for (;;)
    {
      setBackAndForwardButton();
      return;
      onPageLoadFailed();
    }
  }
  
  private void setBackAndForwardButton()
  {
    if (this.webView.canGoForward())
    {
      this.navForward.setEnabled(true);
      this.navForward.setAlpha(1.0F);
    }
    while (this.webView.canGoBack())
    {
      this.navGoBack.setEnabled(true);
      this.navGoBack.setAlpha(1.0F);
      return;
      this.navForward.setEnabled(false);
      this.navForward.setAlpha(0.5F);
    }
    this.navGoBack.setEnabled(false);
    this.navGoBack.setAlpha(0.5F);
  }
  
  private void setWebViewScrollListener()
  {
    this.webView.setScrollListener(new ListenerScrollWebView.ScrollListener()
    {
      public void scrollDown()
      {
        GroupActivity.this.showNavBar();
      }
      
      public void scrollUp()
      {
        GroupActivity.this.hideNavBar();
      }
    });
  }
  
  private void showNavBar()
  {
    if ((!this.isNavBarShow) && (this.isAnimationEnd))
    {
      ViewPropertyAnimator localViewPropertyAnimator = this.navBar.animate().translationY(0.0F);
      localViewPropertyAnimator.setListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          super.onAnimationEnd(paramAnonymousAnimator);
          GroupActivity.access$802(GroupActivity.this, true);
        }
      });
      localViewPropertyAnimator.start();
      this.isAnimationEnd = false;
      this.isNavBarShow = true;
    }
  }
  
  public void finish()
  {
    finishExitFlow();
  }
  
  public void loadWebView()
  {
    onPageLoadStart();
    if (this.urlForLoading != null)
    {
      this.webView.loadUrl(this.urlForLoading);
      return;
    }
    this.webView.loadUrl(getString(2131493238));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
    {
      if (paramIntent != null) {
        break label51;
      }
      if (this.mUploadMessage != null) {
        this.mUploadMessage.onReceiveValue(null);
      }
      if (this.mUploadMessageArray != null) {
        this.mUploadMessageArray.onReceiveValue(null);
      }
    }
    label51:
    do
    {
      return;
      if (this.mUploadMessage != null) {
        this.mUploadMessage.onReceiveValue(paramIntent.getData());
      }
    } while (this.mUploadMessageArray == null);
    this.mUploadMessageArray.onReceiveValue(new Uri[] { paramIntent.getData() });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427366);
    paramBundle = (ImageView)findViewById(2131296460);
    GlideApp.with(this).load(Integer.valueOf(2131165396)).into(paramBundle);
    AndroidBug5497Workaround.assistActivity(this);
    this.navBar = findViewById(2131296587);
    this.navGoBack = findViewById(2131296462);
    this.navGoBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupActivity.this.onGoBackPressed();
      }
    });
    this.navForward = findViewById(2131296464);
    this.navForward.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupActivity.this.onForwardPressed();
      }
    });
    findViewById(2131296465).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupActivity.this.webView.reload();
      }
    });
    findViewById(2131296463).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupActivity.this.finish();
      }
    });
    findViewById(2131296641).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupActivity.this.loadWebView();
      }
    });
    this.connectLayout = ((PercentRelativeLayout)findViewById(2131296414));
    this.connectFailLayout = ((PercentRelativeLayout)findViewById(2131296413));
    this.webView = ((ListenerScrollWebView)findViewById(2131296466));
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setDomStorageEnabled(true);
    this.webView.getSettings().setSupportZoom(true);
    this.webView.setWebChromeClient(new CustomWebChromeClient(null));
    this.webView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        GroupActivity.this.onPageLoadFinished();
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        GroupActivity.this.onPageLoadStart();
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        GroupActivity.access$502(GroupActivity.this, false);
      }
    });
    paramBundle = null;
    if (getIntent().getExtras() != null) {
      paramBundle = getIntent().getExtras().getString("URL_FOR_LOADING_KEY");
    }
    if (paramBundle != null) {
      this.urlForLoading = paramBundle;
    }
    loadWebView();
    setWebViewScrollListener();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.webView.destroy();
  }
  
  public void onPageLoadFailed()
  {
    this.webView.setVisibility(8);
    this.connectFailLayout.setVisibility(0);
  }
  
  public void onPageLoadStart()
  {
    this.webView.setVisibility(8);
    this.connectFailLayout.setVisibility(8);
    this.connectLayout.setVisibility(0);
    this.onPageLoadSuccess = true;
    setBackAndForwardButton();
  }
  
  private class CustomWebChromeClient
    extends WebChromeClient
  {
    private CustomWebChromeClient() {}
    
    @SuppressLint({"NewApi"})
    public boolean onShowFileChooser(WebView paramWebView, ValueCallback<Uri[]> paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams)
    {
      if (GroupActivity.this.mUploadMessage != null) {
        GroupActivity.this.mUploadMessage.onReceiveValue(null);
      }
      Log.i("UPFILE", "file chooser params：" + paramFileChooserParams.toString());
      GroupActivity.this.mUploadMessageArray = paramValueCallback;
      paramValueCallback = new Intent("android.intent.action.GET_CONTENT");
      paramValueCallback.addCategory("android.intent.category.OPENABLE");
      if (TextUtils.isEmpty(paramFileChooserParams.getAcceptTypes()[0])) {}
      for (paramWebView = "*/*";; paramWebView = paramFileChooserParams.getAcceptTypes()[0])
      {
        paramValueCallback.setType(paramWebView);
        GroupActivity.this.startActivityForResult(Intent.createChooser(paramValueCallback, "File Chooser"), 1);
        return true;
      }
    }
    
    public void openFileChooser(ValueCallback<Uri> paramValueCallback)
    {
      Log.i("UPFILE", "in openFile Uri Callback");
      if (GroupActivity.this.mUploadMessage != null) {
        GroupActivity.this.mUploadMessage.onReceiveValue(null);
      }
      GroupActivity.this.mUploadMessage = paramValueCallback;
      paramValueCallback = new Intent("android.intent.action.GET_CONTENT");
      paramValueCallback.addCategory("android.intent.category.OPENABLE");
      paramValueCallback.setType("*/*");
      GroupActivity.this.startActivityForResult(Intent.createChooser(paramValueCallback, "File Chooser"), 1);
    }
    
    public void openFileChooser(ValueCallback paramValueCallback, String paramString)
    {
      Log.i("UPFILE", "in openFile Uri Callback has accept Type" + paramString);
      if (GroupActivity.this.mUploadMessage != null) {
        GroupActivity.this.mUploadMessage.onReceiveValue(null);
      }
      GroupActivity.this.mUploadMessage = paramValueCallback;
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.addCategory("android.intent.category.OPENABLE");
      if (TextUtils.isEmpty(paramString)) {}
      for (paramValueCallback = "*/*";; paramValueCallback = paramString)
      {
        localIntent.setType(paramValueCallback);
        GroupActivity.this.startActivityForResult(Intent.createChooser(localIntent, "File Chooser"), 1);
        return;
      }
    }
    
    public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
    {
      Log.i("UPFILE", "in openFile Uri Callback has accept Type" + paramString1 + "has capture" + paramString2);
      if (GroupActivity.this.mUploadMessage != null) {
        GroupActivity.this.mUploadMessage.onReceiveValue(null);
      }
      GroupActivity.this.mUploadMessage = paramValueCallback;
      paramString2 = new Intent("android.intent.action.GET_CONTENT");
      paramString2.addCategory("android.intent.category.OPENABLE");
      if (TextUtils.isEmpty(paramString1)) {}
      for (paramValueCallback = "*/*";; paramValueCallback = paramString1)
      {
        paramString2.setType(paramValueCallback);
        GroupActivity.this.startActivityForResult(Intent.createChooser(paramString2, "File Chooser"), 1);
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\group\GroupActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */