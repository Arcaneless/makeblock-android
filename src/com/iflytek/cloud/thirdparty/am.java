package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.DataDownloader;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import java.util.Random;

public class am
  extends F
{
  private boolean f = false;
  
  public am(Context paramContext)
  {
    super(paramContext);
  }
  
  public int a(VerifierListener paramVerifierListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.f = this.b.a("request_audio_focus", true);
        if ((this.d != null) && (this.d.t())) {
          this.d.b(this.b.a("isv_interrupt_error", false));
        }
        this.d = new t(this.a, this.b, a("verify"));
        T.a(this.a, Boolean.valueOf(this.f), null);
        ((t)this.d).a(new a(paramVerifierListener));
        i = 0;
      }
      catch (SpeechError paramVerifierListener)
      {
        for (;;)
        {
          i = paramVerifierListener.getErrorCode();
          ad.a(paramVerifierListener);
        }
      }
      catch (Throwable paramVerifierListener)
      {
        for (;;)
        {
          ad.a(paramVerifierListener);
          int i = 20999;
        }
      }
      return i;
    }
  }
  
  public int a(String paramString1, String paramString2, SpeechListener paramSpeechListener)
  {
    int i = 0;
    synchronized (this.c)
    {
      try
      {
        this.b.a("cmd", paramString1);
        this.b.a("auth_id", paramString2);
        new s(this.a, a("manager")).a(this.b, new s.a(paramSpeechListener));
        return i;
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
          i = 20999;
        }
      }
    }
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
    if (((t)this.d).k() != -1) {
      return 10106;
    }
    ((t)this.d).onRecordBuffer(paramArrayOfByte, paramInt1, paramInt2);
    return 0;
  }
  
  public String a(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Random localRandom = new Random();
    String str = "023456789".charAt(localRandom.nextInt("023456789".length())) + "";
    localStringBuffer.append(str);
    int i = 0;
    while (i < paramInt - 1)
    {
      Boolean localBoolean = Boolean.valueOf(false);
      while (!localBoolean.booleanValue())
      {
        str = "023456789".charAt(localRandom.nextInt("023456789".length())) + "";
        if (localStringBuffer.indexOf(str) >= 0) {
          localBoolean = Boolean.valueOf(false);
        } else if (Integer.parseInt(localStringBuffer.charAt(localStringBuffer.length() - 1) + "") * Integer.parseInt(str) == 10) {
          localBoolean = Boolean.valueOf(false);
        } else {
          localBoolean = Boolean.valueOf(true);
        }
      }
      localStringBuffer.append(str);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public void a(SpeechListener paramSpeechListener)
  {
    DataDownloader localDataDownloader = new DataDownloader(this.a);
    localDataDownloader.setParameter(this.b);
    localDataDownloader.downloadData(paramSpeechListener);
  }
  
  public void e()
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((t)this.d).a();
      }
      return;
    }
  }
  
  public boolean f()
  {
    return d();
  }
  
  private final class a
    implements VerifierListener
  {
    private VerifierListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (am.a.a(am.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          am.a.a(am.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          am.a.a(am.a.this).onResult((VerifierResult)paramAnonymousMessage.obj);
          continue;
          am.a.a(am.a.this).onVolumeChanged(paramAnonymousMessage.arg1, (byte[])paramAnonymousMessage.obj);
          continue;
          am.a.a(am.a.this).onBeginOfSpeech();
          continue;
          am.a.a(am.a.this).onEndOfSpeech();
          continue;
          Message localMessage = (Message)paramAnonymousMessage.obj;
          am.a.a(am.a.this).onEvent(localMessage.what, localMessage.arg1, localMessage.arg2, (Bundle)localMessage.obj);
        }
      }
    };
    
    public a(VerifierListener paramVerifierListener)
    {
      this.b = paramVerifierListener;
    }
    
    protected void a()
    {
      String str = am.a(am.this).v().e("isv_audio_path");
      if ((!TextUtils.isEmpty(str)) && (S.a(((t)am.b(am.this)).j(), str))) {
        S.a(am.c(am.this).v().b("audio_format", null), str, am.e(am.this).v().a("sample_rate", am.d(am.this).q));
      }
      T.b(am.f(am.this), Boolean.valueOf(am.g(am.this)), null);
    }
    
    public void onBeginOfSpeech()
    {
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
      a();
      paramSpeechError = this.c.obtainMessage(0, paramSpeechError);
      this.c.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle)
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt1;
      localMessage.arg1 = paramInt2;
      localMessage.arg2 = paramInt3;
      localMessage.obj = paramBundle;
      Message.obtain(this.c, 5, localMessage).sendToTarget();
    }
    
    public void onResult(VerifierResult paramVerifierResult)
    {
      a();
      paramVerifierResult = this.c.obtainMessage(4, paramVerifierResult);
      this.c.sendMessage(paramVerifierResult);
    }
    
    public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.c.obtainMessage(1, paramInt, 0, paramArrayOfByte);
      this.c.sendMessage(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */