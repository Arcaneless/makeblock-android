package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.thirdparty.E;
import com.iflytek.cloud.thirdparty.E.a;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.ag;
import com.iflytek.cloud.thirdparty.aq;
import com.iflytek.msc.MSC;
import com.iflytek.speech.SpeechUnderstanderAidl;
import com.iflytek.speech.SpeechUnderstanderListener.Stub;

public class SpeechUnderstander
  extends E
{
  protected static SpeechUnderstander a = null;
  private aq c = null;
  private SpeechUnderstanderAidl d = null;
  private a e = null;
  private InitListener f = null;
  private Handler g = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (SpeechUnderstander.a(SpeechUnderstander.this) == null) {
        return;
      }
      SpeechUnderstander.a(SpeechUnderstander.this).onInit(0);
    }
  };
  
  protected SpeechUnderstander(Context paramContext, InitListener paramInitListener)
  {
    this.f = paramInitListener;
    if (MSC.isLoaded()) {
      this.c = new aq(paramContext);
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a)) {
      this.d = new SpeechUnderstanderAidl(paramContext.getApplicationContext(), paramInitListener);
    }
    while (paramInitListener == null) {
      return;
    }
    Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
  }
  
  public static SpeechUnderstander createUnderstander(Context paramContext, InitListener paramInitListener)
  {
    try
    {
      if (a == null) {
        a = new SpeechUnderstander(paramContext, paramInitListener);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  public static SpeechUnderstander getUnderstander()
  {
    return a;
  }
  
  protected void a(Context paramContext)
  {
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a))
    {
      if ((this.d != null) && (!this.d.isAvailable()))
      {
        this.d.destory();
        this.d = null;
      }
      this.d = new SpeechUnderstanderAidl(paramContext.getApplicationContext(), this.f);
    }
    while ((this.f == null) || (this.d == null)) {
      return;
    }
    this.d.destory();
    this.d = null;
  }
  
  public void cancel()
  {
    if ((this.c != null) && (this.c.a()))
    {
      this.c.a(false);
      return;
    }
    if ((this.d != null) && (this.d.isUnderstanding()))
    {
      this.d.cancel(a.a(this.e));
      return;
    }
    ad.b("SpeechUnderstander cancel failed, is not running");
  }
  
  public boolean destroy()
  {
    boolean bool = true;
    if (this.d != null)
    {
      this.d.destory();
      this.d = null;
    }
    if (this.c != null) {
      bool = this.c.c();
    }
    if (bool) {
      a = null;
    }
    return bool;
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public boolean isUnderstanding()
  {
    if ((this.c != null) && (this.c.a())) {}
    while ((this.d != null) && (this.d.isUnderstanding())) {
      return true;
    }
    return false;
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  public int startUnderstanding(SpeechUnderstanderListener paramSpeechUnderstanderListener)
  {
    E.a locala = a("nlu", this.d);
    ad.a("start engine mode = " + locala.toString());
    if (locala == E.a.b) {
      if (this.d != null) {}
    }
    while (this.c == null)
    {
      return 21001;
      this.d.setParameter("params", null);
      this.d.setParameter("params", this.b.toString());
      this.e = new a(paramSpeechUnderstanderListener);
      return this.d.startUnderstanding(a.a(this.e));
    }
    this.c.a(this.b);
    return this.c.a(paramSpeechUnderstanderListener);
  }
  
  public void stopUnderstanding()
  {
    if ((this.c != null) && (this.c.a()))
    {
      this.c.b();
      return;
    }
    if ((this.d != null) && (this.d.isUnderstanding()))
    {
      this.d.stopUnderstanding(a.a(this.e));
      return;
    }
    ad.a("SpeechUnderstander stopUnderstanding, is not understanding");
  }
  
  public int writeAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((this.c != null) && (this.c.a())) {
      return this.c.a(paramArrayOfByte, paramInt1, paramInt2);
    }
    if ((this.d != null) && (this.d.isUnderstanding())) {
      return this.d.writeAudio(paramArrayOfByte, paramInt1, paramInt2);
    }
    ad.a("SpeechUnderstander writeAudio, is not understanding");
    return 21004;
  }
  
  private final class a
    implements SpeechUnderstanderListener
  {
    private SpeechUnderstanderListener b = null;
    private com.iflytek.speech.SpeechUnderstanderListener c = null;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (SpeechUnderstander.a.c(SpeechUnderstander.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onVolumeChanged(paramAnonymousMessage.arg1, (byte[])paramAnonymousMessage.obj);
          continue;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onBeginOfSpeech();
          continue;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onEndOfSpeech();
          continue;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onResult((UnderstanderResult)paramAnonymousMessage.obj);
          continue;
          Message localMessage = (Message)paramAnonymousMessage.obj;
          SpeechUnderstander.a.c(SpeechUnderstander.a.this).onEvent(localMessage.what, localMessage.arg1, localMessage.arg2, (Bundle)localMessage.obj);
        }
      }
    };
    
    public a(SpeechUnderstanderListener paramSpeechUnderstanderListener)
    {
      this.b = paramSpeechUnderstanderListener;
      this.c = new SpeechUnderstanderListener.Stub()
      {
        public void onBeginOfSpeech()
          throws RemoteException
        {
          Message localMessage = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(2);
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(localMessage);
        }
        
        public void onEndOfSpeech()
          throws RemoteException
        {
          Message localMessage = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(3);
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(localMessage);
        }
        
        public void onError(int paramAnonymousInt)
          throws RemoteException
        {
          Message localMessage = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(0, new SpeechError(paramAnonymousInt));
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(localMessage);
        }
        
        public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, Bundle paramAnonymousBundle)
          throws RemoteException
        {
          Message localMessage = new Message();
          localMessage.what = paramAnonymousInt1;
          localMessage.arg1 = paramAnonymousInt2;
          localMessage.arg2 = paramAnonymousInt3;
          localMessage.obj = paramAnonymousBundle;
          paramAnonymousBundle = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(6, 0, 0, localMessage);
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(paramAnonymousBundle);
        }
        
        public void onResult(com.iflytek.speech.UnderstanderResult paramAnonymousUnderstanderResult)
          throws RemoteException
        {
          paramAnonymousUnderstanderResult = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(4, new UnderstanderResult(paramAnonymousUnderstanderResult.getResultString()));
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(paramAnonymousUnderstanderResult);
        }
        
        public void onVolumeChanged(int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
          throws RemoteException
        {
          paramAnonymousArrayOfByte = SpeechUnderstander.a.b(SpeechUnderstander.a.this).obtainMessage(1, paramAnonymousInt, 0, paramAnonymousArrayOfByte);
          SpeechUnderstander.a.b(SpeechUnderstander.a.this).sendMessage(paramAnonymousArrayOfByte);
        }
      };
    }
    
    public void onBeginOfSpeech()
    {
      Message localMessage = this.d.obtainMessage(2, 0, 0, null);
      this.d.sendMessage(localMessage);
    }
    
    public void onEndOfSpeech()
    {
      Message localMessage = this.d.obtainMessage(3, 0, 0, null);
      this.d.sendMessage(localMessage);
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      paramSpeechError = this.d.obtainMessage(0, paramSpeechError);
      this.d.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    {
      Message localMessage = new Message();
      localMessage.what = paramInt1;
      localMessage.arg1 = paramInt2;
      localMessage.arg2 = paramInt3;
      localMessage.obj = paramBundle;
      paramBundle = this.d.obtainMessage(6, 0, 0, localMessage);
      this.d.sendMessage(paramBundle);
    }
    
    public void onResult(UnderstanderResult paramUnderstanderResult)
    {
      paramUnderstanderResult = this.d.obtainMessage(4, paramUnderstanderResult);
      this.d.sendMessage(paramUnderstanderResult);
    }
    
    public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.d.obtainMessage(1, paramInt, 0, paramArrayOfByte);
      this.d.sendMessage(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\SpeechUnderstander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */