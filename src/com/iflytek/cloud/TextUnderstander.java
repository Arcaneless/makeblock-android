package com.iflytek.cloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.thirdparty.E;
import com.iflytek.cloud.thirdparty.E.a;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.ag;
import com.iflytek.cloud.thirdparty.ar;
import com.iflytek.msc.MSC;
import com.iflytek.speech.TextUnderstanderAidl;
import com.iflytek.speech.TextUnderstanderListener.Stub;

public class TextUnderstander
  extends E
{
  private static TextUnderstander d = null;
  private ar a = null;
  private TextUnderstanderAidl c = null;
  private a e = null;
  private InitListener f = null;
  private Handler g = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (TextUnderstander.a(TextUnderstander.this) == null) {
        return;
      }
      TextUnderstander.a(TextUnderstander.this).onInit(0);
    }
  };
  
  protected TextUnderstander(Context paramContext, InitListener paramInitListener)
  {
    this.f = paramInitListener;
    if (MSC.isLoaded()) {
      this.a = new ar(paramContext);
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a)) {
      this.c = new TextUnderstanderAidl(paramContext.getApplicationContext(), paramInitListener);
    }
    while (paramInitListener == null) {
      return;
    }
    Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
  }
  
  public static TextUnderstander createTextUnderstander(Context paramContext, InitListener paramInitListener)
  {
    try
    {
      if (d == null) {
        d = new TextUnderstander(paramContext, paramInitListener);
      }
      paramContext = d;
      return paramContext;
    }
    finally {}
  }
  
  public static TextUnderstander getTextUnderstander()
  {
    return d;
  }
  
  protected void a(Context paramContext)
  {
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a))
    {
      if ((this.c != null) && (!this.c.isAvailable()))
      {
        this.c.destory();
        this.c = null;
      }
      this.c = new TextUnderstanderAidl(paramContext.getApplicationContext(), this.f);
    }
    while ((this.f == null) || (this.c == null)) {
      return;
    }
    this.c.destory();
    this.c = null;
  }
  
  public void cancel()
  {
    if (this.a != null)
    {
      this.a.cancel(false);
      return;
    }
    if (this.c != null)
    {
      this.c.cancel(a.a(this.e));
      return;
    }
    ad.b("TextUnderstander cancel failed, is not running");
  }
  
  public boolean destroy()
  {
    boolean bool = true;
    if (this.c != null)
    {
      this.c.destory();
      this.c = null;
    }
    if (this.a != null) {
      bool = this.a.destroy();
    }
    if (bool) {
      d = null;
    }
    return bool;
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public boolean isUnderstanding()
  {
    if ((this.a != null) && (this.a.e())) {}
    while ((this.c != null) && (this.c.isUnderstanding())) {
      return true;
    }
    return false;
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  public int understandText(String paramString, TextUnderstanderListener paramTextUnderstanderListener)
  {
    E.a locala = a("nlu", this.c);
    ad.a("start engine mode = " + locala.toString());
    if (locala == E.a.b) {
      if (this.c != null) {}
    }
    while (this.a == null)
    {
      return 21001;
      this.c.setParameter("params", null);
      this.c.setParameter("params", this.b.toString());
      this.e = new a(paramTextUnderstanderListener);
      return this.c.understandText(paramString, a.a(this.e));
    }
    this.a.setParameter(this.b);
    return this.a.a(paramString, paramTextUnderstanderListener);
  }
  
  private final class a
    implements TextUnderstanderListener
  {
    private TextUnderstanderListener b = null;
    private com.iflytek.speech.TextUnderstanderListener c = null;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (TextUnderstander.a.c(TextUnderstander.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          TextUnderstander.a.c(TextUnderstander.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          TextUnderstander.a.c(TextUnderstander.a.this).onResult((UnderstanderResult)paramAnonymousMessage.obj);
        }
      }
    };
    
    public a(TextUnderstanderListener paramTextUnderstanderListener)
    {
      this.b = paramTextUnderstanderListener;
      this.c = new TextUnderstanderListener.Stub()
      {
        public void onError(int paramAnonymousInt)
          throws RemoteException
        {
          Message localMessage = TextUnderstander.a.b(TextUnderstander.a.this).obtainMessage(0, new SpeechError(paramAnonymousInt));
          TextUnderstander.a.b(TextUnderstander.a.this).sendMessage(localMessage);
        }
        
        public void onResult(com.iflytek.speech.UnderstanderResult paramAnonymousUnderstanderResult)
          throws RemoteException
        {
          paramAnonymousUnderstanderResult = TextUnderstander.a.b(TextUnderstander.a.this).obtainMessage(4, new UnderstanderResult(paramAnonymousUnderstanderResult.getResultString()));
          TextUnderstander.a.b(TextUnderstander.a.this).sendMessage(paramAnonymousUnderstanderResult);
        }
      };
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      paramSpeechError = this.d.obtainMessage(0, paramSpeechError);
      this.d.sendMessage(paramSpeechError);
    }
    
    public void onResult(UnderstanderResult paramUnderstanderResult)
    {
      paramUnderstanderResult = this.d.obtainMessage(4, paramUnderstanderResult);
      this.d.sendMessage(paramUnderstanderResult);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\TextUnderstander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */