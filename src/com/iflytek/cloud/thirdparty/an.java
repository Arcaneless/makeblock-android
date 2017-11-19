package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechError;

public class an
  extends F
{
  private boolean f = false;
  
  public an(Context paramContext)
  {
    super(paramContext);
  }
  
  public int a(String paramString1, String paramString2, EvaluatorListener paramEvaluatorListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.f = this.b.a("request_audio_focus", true);
        if ((this.d != null) && (this.d.t())) {
          this.d.b(this.b.a("ise_interrupt_error", false));
        }
        this.d = new n(this.a, this.b, a("eva"));
        T.a(this.a, Boolean.valueOf(this.f), null);
        ((n)this.d).a(paramString1, paramString2, new a(paramEvaluatorListener));
        i = 0;
      }
      catch (SpeechError paramString1)
      {
        for (;;)
        {
          i = paramString1.getErrorCode();
          ad.a(paramString1);
        }
      }
      catch (Throwable paramString1)
      {
        for (;;)
        {
          ad.a(paramString1);
          int i = 20999;
        }
      }
      return i;
    }
  }
  
  public int a(byte[] paramArrayOfByte, String paramString, EvaluatorListener paramEvaluatorListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.f = this.b.a("request_audio_focus", true);
        if ((this.d != null) && (this.d.t())) {
          this.d.b(this.b.a("ise_interrupt_error", false));
        }
        this.d = new n(this.a, this.b, a("eva"));
        T.a(this.a, Boolean.valueOf(this.f), null);
        ((n)this.d).a(paramArrayOfByte, paramString, new a(paramEvaluatorListener));
        i = 0;
      }
      catch (SpeechError paramArrayOfByte)
      {
        for (;;)
        {
          i = paramArrayOfByte.getErrorCode();
          ad.a(paramArrayOfByte);
        }
      }
      catch (Throwable paramArrayOfByte)
      {
        for (;;)
        {
          ad.a(paramArrayOfByte);
          int i = 20999;
        }
      }
      return i;
    }
  }
  
  public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    synchronized (this.c)
    {
      if (this.d == null)
      {
        ad.a("writeAudio error, no active session.");
        return false;
      }
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      {
        ad.a("writeAudio error,buffer is null.");
        return false;
      }
    }
    if (paramArrayOfByte.length < paramInt2 + paramInt1)
    {
      ad.a("writeAudio error,buffer length < length.");
      return false;
    }
    ((n)this.d).onRecordBuffer(paramArrayOfByte, paramInt1, paramInt2);
    return true;
  }
  
  protected boolean b_()
  {
    return super.b_();
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
        ((n)this.d).a(true);
      }
      return;
    }
  }
  
  protected void f()
  {
    if (this.d != null)
    {
      String str = this.d.v().e("ise_audio_path");
      if ((!TextUtils.isEmpty(str)) && (S.a(((n)this.d).d(), str))) {
        S.a(this.d.v().b("audio_format", null), str, this.d.v().a("sample_rate", this.d.q));
      }
    }
    T.b(this.a, Boolean.valueOf(this.f), null);
  }
  
  public boolean g()
  {
    return d();
  }
  
  private final class a
    implements EvaluatorListener
  {
    private EvaluatorListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        boolean bool = true;
        if (an.a.a(an.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          an.a.a(an.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          an.a.a(an.a.this).onVolumeChanged(paramAnonymousMessage.arg1, (byte[])paramAnonymousMessage.obj);
          continue;
          an.a.a(an.a.this).onBeginOfSpeech();
          continue;
          an.a.a(an.a.this).onEndOfSpeech();
          continue;
          Object localObject = an.a.a(an.a.this);
          EvaluatorResult localEvaluatorResult = (EvaluatorResult)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.arg1 == 1) {}
          for (;;)
          {
            ((EvaluatorListener)localObject).onResult(localEvaluatorResult, bool);
            break;
            bool = false;
          }
          localObject = (Message)paramAnonymousMessage.obj;
          an.a.a(an.a.this).onEvent(((Message)localObject).what, ((Message)localObject).arg1, ((Message)localObject).arg2, (Bundle)((Message)localObject).obj);
        }
      }
    };
    
    public a(EvaluatorListener paramEvaluatorListener)
    {
      this.b = paramEvaluatorListener;
    }
    
    public void onBeginOfSpeech()
    {
      ad.a("onBeginOfSpeech");
      Message localMessage = this.c.obtainMessage(2, 0, 0, null);
      this.c.sendMessage(localMessage);
    }
    
    public void onEndOfSpeech()
    {
      Message localMessage = this.c.obtainMessage(3, 0, 0, null);
      this.c.sendMessage(localMessage);
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      an.this.f();
      paramSpeechError = this.c.obtainMessage(0, paramSpeechError);
      this.c.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    {
      Message localMessage = new Message();
      localMessage.what = paramInt1;
      localMessage.arg1 = paramInt2;
      localMessage.arg2 = paramInt3;
      localMessage.obj = paramBundle;
      Message.obtain(this.c, 6, localMessage).sendToTarget();
    }
    
    public void onResult(EvaluatorResult paramEvaluatorResult, boolean paramBoolean)
    {
      int i = 1;
      if (paramBoolean) {
        an.this.f();
      }
      Handler localHandler = this.c;
      if (paramBoolean == true) {}
      for (;;)
      {
        paramEvaluatorResult = localHandler.obtainMessage(4, i, 0, paramEvaluatorResult);
        this.c.sendMessage(paramEvaluatorResult);
        return;
        i = 0;
      }
    }
    
    public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.c.obtainMessage(1, paramInt, 0, paramArrayOfByte);
      this.c.sendMessage(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */