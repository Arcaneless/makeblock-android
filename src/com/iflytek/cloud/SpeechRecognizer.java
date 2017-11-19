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
import com.iflytek.cloud.thirdparty.ao;
import com.iflytek.msc.MSC;
import com.iflytek.speech.GrammarListener.Stub;
import com.iflytek.speech.LexiconListener.Stub;
import com.iflytek.speech.RecognizerListener.Stub;
import com.iflytek.speech.SpeechRecognizerAidl;

public final class SpeechRecognizer
  extends E
{
  private static SpeechRecognizer a = null;
  private ao c = null;
  private SpeechRecognizerAidl d = null;
  private a e = null;
  private InitListener f = null;
  private Handler g = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (SpeechRecognizer.a(SpeechRecognizer.this) == null) {
        return;
      }
      SpeechRecognizer.a(SpeechRecognizer.this).onInit(0);
    }
  };
  
  protected SpeechRecognizer(Context paramContext, InitListener paramInitListener)
  {
    this.f = paramInitListener;
    if (MSC.isLoaded()) {
      this.c = new ao(paramContext);
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if ((localSpeechUtility != null) && (localSpeechUtility.a()) && (localSpeechUtility.getEngineMode() != E.a.a)) {
      this.d = new SpeechRecognizerAidl(paramContext.getApplicationContext(), paramInitListener);
    }
    while (paramInitListener == null) {
      return;
    }
    Message.obtain(this.g, 0, 0, 0, null).sendToTarget();
  }
  
  public static SpeechRecognizer createRecognizer(Context paramContext, InitListener paramInitListener)
  {
    try
    {
      if (a == null) {
        a = new SpeechRecognizer(paramContext, paramInitListener);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  public static SpeechRecognizer getRecognizer()
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
      this.d = new SpeechRecognizerAidl(paramContext.getApplicationContext(), this.f);
    }
    while ((this.f == null) || (this.d == null)) {
      return;
    }
    this.d.destory();
    this.d = null;
  }
  
  public int buildGrammar(String paramString1, String paramString2, final GrammarListener paramGrammarListener)
  {
    E.a locala = a("asr", this.d);
    ad.a("start engine mode = " + locala.toString());
    if (locala == E.a.b) {
      if (this.d != null) {}
    }
    while (this.c == null)
    {
      return 21001;
      this.d.setParameter("params", null);
      this.d.setParameter("params", this.b.toString());
      paramGrammarListener = new GrammarListener.Stub()
      {
        public void onBuildFinish(String paramAnonymousString, int paramAnonymousInt)
          throws RemoteException
        {
          GrammarListener localGrammarListener;
          if (paramGrammarListener != null)
          {
            localGrammarListener = paramGrammarListener;
            if (paramAnonymousInt != 0) {
              break label29;
            }
          }
          label29:
          for (SpeechError localSpeechError = null;; localSpeechError = new SpeechError(paramAnonymousInt))
          {
            localGrammarListener.onBuildFinish(paramAnonymousString, localSpeechError);
            return;
          }
        }
      };
      return this.d.buildGrammar(paramString1, paramString2, paramGrammarListener);
    }
    this.c.setParameter(this.b);
    return this.c.a(paramString1, paramString2, paramGrammarListener);
  }
  
  public void cancel()
  {
    if ((this.c != null) && (this.c.g())) {
      this.c.cancel(false);
    }
    do
    {
      return;
      if ((this.d == null) || (!this.d.isListening())) {
        break;
      }
    } while (this.e == null);
    this.d.cancel(a.c(this.e));
    return;
    ad.b("SpeechRecognizer cancel failed, is not running");
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
      bool = this.c.destroy();
    }
    if (bool) {
      a = null;
    }
    SpeechUtility localSpeechUtility = SpeechUtility.getUtility();
    if (localSpeechUtility != null)
    {
      ad.a("Destory asr engine.");
      localSpeechUtility.setParameter("engine_destroy", "engine_destroy=asr");
    }
    return bool;
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public boolean isListening()
  {
    if ((this.c != null) && (this.c.g())) {}
    while ((this.d != null) && (this.d.isListening())) {
      return true;
    }
    return false;
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  public int startListening(RecognizerListener paramRecognizerListener)
  {
    E.a locala = a("asr", this.d);
    ad.a("start engine mode = " + locala.toString());
    if (locala == E.a.b) {
      if (this.d != null) {}
    }
    while (this.c == null)
    {
      return 21001;
      this.d.setParameter("params", null);
      this.d.setParameter("params", this.b.toString());
      this.e = new a(paramRecognizerListener);
      return this.d.startListening(a.c(this.e));
    }
    this.c.setParameter(this.b);
    return this.c.a(paramRecognizerListener);
  }
  
  public void stopListening()
  {
    if ((this.c != null) && (this.c.g())) {
      this.c.e();
    }
    do
    {
      return;
      if ((this.d == null) || (!this.d.isListening())) {
        break;
      }
    } while (this.e == null);
    this.d.stopListening(a.c(this.e));
    return;
    ad.b("SpeechRecognizer stopListening failed, is not running");
  }
  
  public int updateLexicon(String paramString1, String paramString2, final LexiconListener paramLexiconListener)
  {
    E.a locala = a("asr", this.d);
    ad.a("start engine mode = " + locala.toString());
    if (locala == E.a.b) {
      if (this.d != null) {}
    }
    while (this.c == null)
    {
      return 21001;
      this.d.setParameter("params", null);
      this.d.setParameter("params", this.b.toString());
      paramLexiconListener = new LexiconListener.Stub()
      {
        public void onLexiconUpdated(String paramAnonymousString, int paramAnonymousInt)
          throws RemoteException
        {
          LexiconListener localLexiconListener;
          if (paramLexiconListener != null)
          {
            localLexiconListener = paramLexiconListener;
            if (paramAnonymousInt != 0) {
              break label29;
            }
          }
          label29:
          for (SpeechError localSpeechError = null;; localSpeechError = new SpeechError(paramAnonymousInt))
          {
            localLexiconListener.onLexiconUpdated(paramAnonymousString, localSpeechError);
            return;
          }
        }
      };
      return this.d.updateLexicon(paramString1, paramString2, paramLexiconListener);
    }
    this.c.setParameter(this.b);
    return this.c.a(paramString1, paramString2, paramLexiconListener);
  }
  
  public int writeAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((this.c != null) && (this.c.g())) {
      return this.c.a(paramArrayOfByte, paramInt1, paramInt2);
    }
    if ((this.d != null) && (this.d.isListening())) {
      return this.d.writeAudio(paramArrayOfByte, paramInt1, paramInt2);
    }
    ad.b("SpeechRecognizer writeAudio failed, is not running");
    return 21004;
  }
  
  private final class a
    implements RecognizerListener
  {
    private RecognizerListener b = null;
    private com.iflytek.speech.RecognizerListener c = null;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        boolean bool = true;
        if (SpeechRecognizer.a.b(SpeechRecognizer.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          SpeechRecognizer.a.b(SpeechRecognizer.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          SpeechRecognizer.a.b(SpeechRecognizer.a.this).onVolumeChanged(paramAnonymousMessage.arg1, (byte[])paramAnonymousMessage.obj);
          continue;
          SpeechRecognizer.a.b(SpeechRecognizer.a.this).onBeginOfSpeech();
          continue;
          SpeechRecognizer.a.b(SpeechRecognizer.a.this).onEndOfSpeech();
          continue;
          Object localObject = SpeechRecognizer.a.b(SpeechRecognizer.a.this);
          RecognizerResult localRecognizerResult = (RecognizerResult)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.arg1 == 1) {}
          for (;;)
          {
            ((RecognizerListener)localObject).onResult(localRecognizerResult, bool);
            break;
            bool = false;
          }
          localObject = (Message)paramAnonymousMessage.obj;
          SpeechRecognizer.a.b(SpeechRecognizer.a.this).onEvent(((Message)localObject).what, ((Message)localObject).arg1, ((Message)localObject).arg2, (Bundle)((Message)localObject).obj);
        }
      }
    };
    
    public a(RecognizerListener paramRecognizerListener)
    {
      this.b = paramRecognizerListener;
      this.c = new RecognizerListener.Stub()
      {
        public void onBeginOfSpeech()
          throws RemoteException
        {
          Message localMessage = SpeechRecognizer.a.a(SpeechRecognizer.a.this).obtainMessage(2);
          SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(localMessage);
        }
        
        public void onEndOfSpeech()
          throws RemoteException
        {
          Message localMessage = SpeechRecognizer.a.a(SpeechRecognizer.a.this).obtainMessage(3);
          SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(localMessage);
        }
        
        public void onError(int paramAnonymousInt)
          throws RemoteException
        {
          Message localMessage = SpeechRecognizer.a.a(SpeechRecognizer.a.this).obtainMessage(0, new SpeechError(paramAnonymousInt));
          SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(localMessage);
        }
        
        public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, Bundle paramAnonymousBundle)
          throws RemoteException
        {
          Message localMessage = new Message();
          localMessage.what = paramAnonymousInt1;
          localMessage.arg1 = paramAnonymousInt2;
          localMessage.arg2 = paramAnonymousInt3;
          localMessage.obj = paramAnonymousBundle;
          paramAnonymousBundle = SpeechRecognizer.a.a(SpeechRecognizer.a.this).obtainMessage(6, 0, 0, localMessage);
          SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(paramAnonymousBundle);
        }
        
        public void onResult(com.iflytek.speech.RecognizerResult paramAnonymousRecognizerResult, boolean paramAnonymousBoolean)
          throws RemoteException
        {
          int i = 1;
          Handler localHandler = SpeechRecognizer.a.a(SpeechRecognizer.a.this);
          if (paramAnonymousBoolean == true) {}
          for (;;)
          {
            paramAnonymousRecognizerResult = localHandler.obtainMessage(4, i, 0, new RecognizerResult(paramAnonymousRecognizerResult.getResultString()));
            SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(paramAnonymousRecognizerResult);
            return;
            i = 0;
          }
        }
        
        public void onVolumeChanged(int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
          throws RemoteException
        {
          paramAnonymousArrayOfByte = SpeechRecognizer.a.a(SpeechRecognizer.a.this).obtainMessage(1, paramAnonymousInt, 0, paramAnonymousArrayOfByte);
          SpeechRecognizer.a.a(SpeechRecognizer.a.this).sendMessage(paramAnonymousArrayOfByte);
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
    
    public void onResult(RecognizerResult paramRecognizerResult, boolean paramBoolean)
    {
      int i = 1;
      Handler localHandler = this.d;
      if (paramBoolean == true) {}
      for (;;)
      {
        paramRecognizerResult = localHandler.obtainMessage(4, i, 0, paramRecognizerResult);
        this.d.sendMessage(paramRecognizerResult);
        return;
        i = 0;
      }
    }
    
    public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.d.obtainMessage(1, paramInt, 0, paramArrayOfByte);
      this.d.sendMessage(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\SpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */