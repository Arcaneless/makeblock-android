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
import com.iflytek.cloud.thirdparty.ap;
import com.iflytek.msc.MSC;
import com.iflytek.speech.SpeechSynthesizerAidl;
import com.iflytek.speech.SynthesizerListener.Stub;

public class SpeechSynthesizer
  extends E
{
  private static SpeechSynthesizer c = null;
  InitListener a = null;
  private ap d = null;
  private SpeechSynthesizerAidl e = null;
  private a f = null;
  private Handler g = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (SpeechSynthesizer.this.a == null) {
        return;
      }
      SpeechSynthesizer.this.a.onInit(0);
    }
  };
  
  protected SpeechSynthesizer(Context paramContext, InitListener paramInitListener)
  {
    this.a = paramInitListener;
    if (MSC.isLoaded()) {
      this.d = new ap(paramContext);
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a))
    {
      this.e = new SpeechSynthesizerAidl(paramContext.getApplicationContext(), paramInitListener);
      return;
    }
    Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
  }
  
  public static SpeechSynthesizer createSynthesizer(Context paramContext, InitListener paramInitListener)
  {
    if (c == null) {
      c = new SpeechSynthesizer(paramContext, paramInitListener);
    }
    return c;
  }
  
  public static SpeechSynthesizer getSynthesizer()
  {
    return c;
  }
  
  protected void a(Context paramContext)
  {
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a))
    {
      if ((this.e != null) && (!this.e.isAvailable()))
      {
        this.e.destory();
        this.e = null;
      }
      this.e = new SpeechSynthesizerAidl(paramContext.getApplicationContext(), this.a);
    }
    while ((this.a == null) || (this.e == null)) {
      return;
    }
    this.e.destory();
    this.e = null;
  }
  
  public boolean destroy()
  {
    boolean bool = true;
    if (this.e != null) {
      this.e.destory();
    }
    if (this.d != null) {
      bool = this.d.destroy();
    }
    if (bool) {
      c = null;
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if (localSpeechUtility != null)
    {
      ad.a("Destory tts engine.");
      localSpeechUtility.setParameter("engine_destroy", "engine_destroy=tts");
    }
    return bool;
  }
  
  public String getParameter(String paramString)
  {
    if (("local_speakers".equals(paramString)) && (this.e != null)) {
      return this.e.getParameter(paramString);
    }
    if ("tts_play_state".equals(paramString))
    {
      if ((a("tts", this.e) == E.a.b) && (this.e != null)) {
        return this.e.getParameter(paramString);
      }
      if (this.d != null) {
        return "" + this.d.h();
      }
    }
    return super.getParameter(paramString);
  }
  
  public boolean isSpeaking()
  {
    if ((this.d != null) && (this.d.g())) {}
    while ((this.e != null) && (this.e.isSpeaking())) {
      return true;
    }
    return false;
  }
  
  public void pauseSpeaking()
  {
    if ((this.d != null) && (this.d.g())) {
      this.d.e();
    }
    while ((this.e == null) || (!this.e.isSpeaking()) || (this.f == null)) {
      return;
    }
    this.e.pauseSpeaking(a.c(this.f));
  }
  
  public void resumeSpeaking()
  {
    if ((this.d != null) && (this.d.g())) {
      this.d.f();
    }
    while ((this.e == null) || (!this.e.isSpeaking()) || (this.f == null)) {
      return;
    }
    this.e.resumeSpeaking(a.c(this.f));
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  public int startSpeaking(String paramString, SynthesizerListener paramSynthesizerListener)
  {
    if (a("tts", this.e) == E.a.b) {
      if (this.e != null) {}
    }
    while (this.d == null)
    {
      return 21001;
      this.e.setParameter("params", null);
      this.e.setParameter("params", this.b.toString());
      this.b.c("next_text");
      this.f = new a(paramSynthesizerListener);
      return this.e.startSpeaking(paramString, a.c(this.f));
    }
    this.d.setParameter(this.b);
    this.b.c("next_text");
    return this.d.a(paramString, paramSynthesizerListener);
  }
  
  public void stopSpeaking()
  {
    if ((this.d != null) && (this.d.g())) {
      this.d.a(false);
    }
    while ((this.e == null) || (!this.e.isSpeaking()) || (this.f == null)) {
      return;
    }
    this.e.stopSpeaking(a.c(this.f));
  }
  
  public int synthesizeToUri(String paramString1, String paramString2, SynthesizerListener paramSynthesizerListener)
  {
    if (a("tts", this.e) == E.a.b) {
      if (this.e != null) {}
    }
    while (this.d == null)
    {
      return 21001;
      this.e.setParameter("params", null);
      this.e.setParameter("params", this.b.toString());
      this.e.setParameter("tts_audio_uri", paramString2);
      this.f = new a(paramSynthesizerListener);
      return this.e.synthesizeToUrl(paramString1, a.c(this.f));
    }
    this.d.setParameter(this.b);
    return this.d.a(paramString1, paramString2, paramSynthesizerListener);
  }
  
  private final class a
    implements SynthesizerListener
  {
    private SynthesizerListener b = null;
    private com.iflytek.speech.SynthesizerListener c = null;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) == null) {}
        do
        {
          return;
          int i;
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onSpeakBegin();
            return;
          case 2: 
            paramAnonymousMessage = (Bundle)paramAnonymousMessage.obj;
            i = paramAnonymousMessage.getInt("percent");
            int j = paramAnonymousMessage.getInt("begpos");
            int k = paramAnonymousMessage.getInt("endpos");
            paramAnonymousMessage = paramAnonymousMessage.getString("spellinfo");
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onBufferProgress(i, j, k, paramAnonymousMessage);
            return;
          case 3: 
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onSpeakPaused();
            return;
          case 4: 
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onSpeakResumed();
            return;
          case 5: 
            i = ((Integer)paramAnonymousMessage.obj).intValue();
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onSpeakProgress(paramAnonymousMessage.arg1, paramAnonymousMessage.arg2, i);
            return;
          case 6: 
            SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onCompleted((SpeechError)paramAnonymousMessage.obj);
            return;
          }
          paramAnonymousMessage = (Message)paramAnonymousMessage.obj;
        } while (paramAnonymousMessage == null);
        SpeechSynthesizer.a.a(SpeechSynthesizer.a.this).onEvent(paramAnonymousMessage.what, paramAnonymousMessage.arg1, paramAnonymousMessage.arg2, (Bundle)paramAnonymousMessage.obj);
      }
    };
    
    public a(SynthesizerListener paramSynthesizerListener)
    {
      this.b = paramSynthesizerListener;
      this.c = new SynthesizerListener.Stub()
      {
        public void onBufferProgress(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, String paramAnonymousString)
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null)
          {
            paramAnonymousString = new Bundle();
            paramAnonymousString.putInt("percent", paramAnonymousInt1);
            paramAnonymousString.putInt("begpos", paramAnonymousInt2);
            paramAnonymousString.putInt("endpos", paramAnonymousInt3);
            paramAnonymousString.putString("spellinfo", "");
            if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null) {
              Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 2, paramAnonymousString).sendToTarget();
            }
          }
        }
        
        public void onCompleted(int paramAnonymousInt)
          throws RemoteException
        {
          Handler localHandler;
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null)
          {
            localHandler = SpeechSynthesizer.a.b(SpeechSynthesizer.a.this);
            if (paramAnonymousInt != 0) {
              break label35;
            }
          }
          label35:
          for (Object localObject = null;; localObject = new SpeechError(paramAnonymousInt))
          {
            Message.obtain(localHandler, 6, localObject).sendToTarget();
            return;
          }
        }
        
        public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, Bundle paramAnonymousBundle)
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null)
          {
            Message localMessage = Message.obtain();
            localMessage.what = paramAnonymousInt1;
            localMessage.arg1 = 0;
            localMessage.arg2 = 0;
            localMessage.obj = paramAnonymousBundle;
            Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 7, 0, 0, localMessage).sendToTarget();
          }
        }
        
        public void onSpeakBegin()
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null) {
            Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 1).sendToTarget();
          }
        }
        
        public void onSpeakPaused()
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null) {
            Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 3).sendToTarget();
          }
        }
        
        public void onSpeakProgress(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null) {
            Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 5, paramAnonymousInt1, paramAnonymousInt2, Integer.valueOf(paramAnonymousInt3)).sendToTarget();
          }
        }
        
        public void onSpeakResumed()
          throws RemoteException
        {
          if (SpeechSynthesizer.a.a(SpeechSynthesizer.a.this) != null) {
            Message.obtain(SpeechSynthesizer.a.b(SpeechSynthesizer.a.this), 4, 0, 0, null).sendToTarget();
          }
        }
      };
    }
    
    public void onBufferProgress(int paramInt1, int paramInt2, int paramInt3, String paramString)
    {
      if (this.b != null)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("percent", paramInt1);
        localBundle.putInt("begpos", paramInt2);
        localBundle.putInt("endpos", paramInt3);
        localBundle.putString("spellinfo", paramString);
        if (this.b != null) {
          Message.obtain(this.d, 2, localBundle).sendToTarget();
        }
      }
    }
    
    public void onCompleted(SpeechError paramSpeechError)
    {
      if (this.b != null) {
        Message.obtain(this.d, 6, paramSpeechError).sendToTarget();
      }
    }
    
    public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    {
      if (this.b != null)
      {
        Message localMessage = Message.obtain();
        localMessage.what = paramInt1;
        localMessage.arg1 = 0;
        localMessage.arg2 = 0;
        localMessage.obj = paramBundle;
        Message.obtain(this.d, 7, 0, 0, localMessage).sendToTarget();
      }
    }
    
    public void onSpeakBegin()
    {
      if (this.b != null) {
        Message.obtain(this.d, 1).sendToTarget();
      }
    }
    
    public void onSpeakPaused()
    {
      if (this.b != null) {
        Message.obtain(this.d, 3).sendToTarget();
      }
    }
    
    public void onSpeakProgress(int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.b != null) {
        Message.obtain(this.d, 5, paramInt1, paramInt2, Integer.valueOf(paramInt3)).sendToTarget();
      }
    }
    
    public void onSpeakResumed()
    {
      if (this.b != null) {
        Message.obtain(this.d, 4).sendToTarget();
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\SpeechSynthesizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */