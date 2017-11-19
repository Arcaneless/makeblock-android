package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;

public class ao
  extends F
{
  private boolean f = false;
  
  public ao(Context paramContext)
  {
    super(paramContext);
  }
  
  public int a(RecognizerListener paramRecognizerListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.f = this.b.a("request_audio_focus", true);
        if ((this.d != null) && (this.d.t())) {
          this.d.b(this.b.a("asr_interrupt_error", false));
        }
        if (!h()) {
          break label128;
        }
        this.d = new r(this.a, this.b, a("iat"));
      }
      catch (SpeechError paramRecognizerListener)
      {
        for (;;)
        {
          i = paramRecognizerListener.getErrorCode();
          ad.a(paramRecognizerListener);
          continue;
          this.d = new p(this.a, this.b, a("iat"));
        }
      }
      catch (Throwable paramRecognizerListener)
      {
        for (;;)
        {
          label128:
          ad.a(paramRecognizerListener);
          int i = 20999;
        }
      }
      T.a(this.a, Boolean.valueOf(this.f), null);
      ((p)this.d).a(new c(paramRecognizerListener));
      i = 0;
      return i;
      if (i()) {
        this.d = new q(this.a, this.b, a("iat"));
      }
    }
  }
  
  public int a(String paramString1, String paramString2, GrammarListener paramGrammarListener)
  {
    int j = 20012;
    int i;
    if (TextUtils.isEmpty(paramString2)) {
      i = 20009;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (TextUtils.isEmpty(paramString1));
      i = j;
    } while (paramGrammarListener == null);
    new o().a(paramString1, paramString2, new a(paramGrammarListener), this.b);
    return 0;
  }
  
  public int a(String paramString1, String paramString2, LexiconListener paramLexiconListener)
  {
    int j = 20012;
    int i;
    if (TextUtils.isEmpty(paramString2)) {
      i = 20009;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (TextUtils.isEmpty(paramString1));
      i = j;
    } while (paramLexiconListener == null);
    o localo = new o();
    this.b.a("subject", "uup", false);
    String str2 = getParameter("lexicon_type");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = paramString1;
    }
    this.b.a("data_type", str1, false);
    localo.a(paramString1, paramString2, new b(paramLexiconListener), this.b);
    return 0;
  }
  
  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    synchronized (this.c)
    {
      if (this.d == null)
      {
        ad.a("writeAudio error, no active session.");
        return 21004;
      }
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      {
        ad.a("writeAudio error,buffer is null.");
        return 10109;
      }
    }
    if (paramArrayOfByte.length < paramInt2 + paramInt1)
    {
      ad.a("writeAudio error,buffer length < length.");
      return 10109;
    }
    if (((p)this.d).a() != -1) {
      return 10106;
    }
    paramInt1 = ((p)this.d).a(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt1;
  }
  
  public void b(String paramString)
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((p)this.d).n().a(paramString);
      }
      return;
    }
  }
  
  public void cancel(boolean paramBoolean)
  {
    synchronized (this.c)
    {
      f();
      super.cancel(paramBoolean);
      return;
    }
  }
  
  public void e()
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((p)this.d).a(true);
      }
      return;
    }
  }
  
  protected void f()
  {
    if (this.d != null)
    {
      String str = this.d.v().e("asr_audio_path");
      if ((!TextUtils.isEmpty(str)) && (S.a(((p)this.d).b(), str))) {
        S.a(this.d.v().b("audio_format", null), str, this.d.v().a("sample_rate", this.d.q));
      }
    }
    T.b(this.a, Boolean.valueOf(this.f), null);
  }
  
  public boolean g()
  {
    return d();
  }
  
  protected boolean h()
  {
    if (TextUtils.isEmpty(this.b.e("bos_dispose"))) {
      return "meta".equalsIgnoreCase(this.b.e("vad_engine"));
    }
    return this.b.a("bos_dispose", false);
  }
  
  protected boolean i()
  {
    return -3 == this.b.a("audio_source", 1);
  }
  
  private final class a
    implements GrammarListener
  {
    private GrammarListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ao.a.a(ao.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          ao.a.a(ao.a.this).onBuildFinish(null, (SpeechError)paramAnonymousMessage.obj);
          continue;
          ao.a.a(ao.a.this).onBuildFinish((String)paramAnonymousMessage.obj, null);
        }
      }
    };
    
    public a(GrammarListener paramGrammarListener)
    {
      this.b = paramGrammarListener;
    }
    
    public void onBuildFinish(String paramString, SpeechError paramSpeechError)
    {
      if (paramSpeechError != null)
      {
        paramString = this.c.obtainMessage(0, paramSpeechError);
        this.c.sendMessage(paramString);
        return;
      }
      paramString = this.c.obtainMessage(1, paramString);
      this.c.sendMessage(paramString);
    }
  }
  
  private final class b
    implements LexiconListener
  {
    private LexiconListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ao.b.a(ao.b.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          ao.b.a(ao.b.this).onLexiconUpdated(null, (SpeechError)paramAnonymousMessage.obj);
          continue;
          ao.b.a(ao.b.this).onLexiconUpdated((String)paramAnonymousMessage.obj, null);
        }
      }
    };
    
    public b(LexiconListener paramLexiconListener)
    {
      this.b = paramLexiconListener;
    }
    
    public void onLexiconUpdated(String paramString, SpeechError paramSpeechError)
    {
      if (paramSpeechError != null)
      {
        paramString = this.c.obtainMessage(0, paramSpeechError);
        this.c.sendMessage(paramString);
        return;
      }
      paramString = this.c.obtainMessage(1, paramString);
      this.c.sendMessage(paramString);
    }
  }
  
  private final class c
    implements RecognizerListener
  {
    private RecognizerListener b = null;
    private boolean c = false;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ao.c.a(ao.c.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          ao.c.a(ao.c.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          ao.c.a(ao.c.this).onVolumeChanged(paramAnonymousMessage.arg1, (byte[])paramAnonymousMessage.obj);
          continue;
          ao.c.a(ao.c.this).onBeginOfSpeech();
          continue;
          ao.c.a(ao.c.this).onEndOfSpeech();
          continue;
          Object localObject = ao.c.a(ao.c.this);
          RecognizerResult localRecognizerResult = (RecognizerResult)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.arg1 == 1) {}
          for (boolean bool = true;; bool = false)
          {
            ((RecognizerListener)localObject).onResult(localRecognizerResult, bool);
            if (!ao.c.b(ao.c.this))
            {
              ao.this.b("ui_frs");
              ao.c.a(ao.c.this, true);
            }
            if (1 != paramAnonymousMessage.arg1) {
              break;
            }
            ao.this.b("ui_lrs");
            break;
          }
          localObject = (Message)paramAnonymousMessage.obj;
          ao.c.a(ao.c.this).onEvent(((Message)localObject).what, ((Message)localObject).arg1, ((Message)localObject).arg2, (Bundle)((Message)localObject).obj);
        }
      }
    };
    
    public c(RecognizerListener paramRecognizerListener)
    {
      this.b = paramRecognizerListener;
    }
    
    public void onBeginOfSpeech()
    {
      ad.a("onBeginOfSpeech");
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
      ao.this.f();
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
      if (paramBoolean) {
        ao.this.f();
      }
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */