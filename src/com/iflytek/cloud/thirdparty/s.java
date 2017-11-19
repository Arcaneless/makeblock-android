package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;

public class s
  extends B
{
  private u a = new u();
  private SpeechListener b;
  
  public s(Context paramContext, HandlerThread paramHandlerThread)
  {
    super(paramContext, paramHandlerThread);
  }
  
  protected void a(Message paramMessage)
    throws Throwable, SpeechError
  {
    switch (paramMessage.what)
    {
    default: 
      return;
    }
    try
    {
      paramMessage = String.format("{'ret':%d,'cmd':%s}", new Object[] { Integer.valueOf(this.a.a(this.r, this)), v().e("cmd") });
      if (this.b != null) {
        this.b.onBufferReceived(paramMessage.getBytes(p()));
      }
      a(21);
      return;
    }
    finally {}
  }
  
  protected void a(SpeechError paramSpeechError)
  {
    super.a(paramSpeechError);
    if (this.b != null) {
      this.b.onCompleted(paramSpeechError);
    }
  }
  
  public void a(ag paramag, a parama)
  {
    this.b = parama;
    a(paramag);
    paramag = Message.obtain();
    paramag.what = 13;
    d(paramag);
  }
  
  public String e()
  {
    return this.a.g();
  }
  
  public String f()
  {
    return this.a.f();
  }
  
  public String g()
  {
    return "ivp";
  }
  
  public static final class a
    implements SpeechListener
  {
    private SpeechListener a = null;
    private Handler b = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (s.a.a(s.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        default: 
          return;
        case 0: 
          s.a.a(s.a.this).onEvent(paramAnonymousMessage.arg1, (Bundle)paramAnonymousMessage.obj);
          return;
        case 1: 
          s.a.a(s.a.this).onBufferReceived((byte[])paramAnonymousMessage.obj);
          return;
        }
        s.a.a(s.a.this).onCompleted((SpeechError)paramAnonymousMessage.obj);
      }
    };
    
    public a(SpeechListener paramSpeechListener)
    {
      this.a = paramSpeechListener;
    }
    
    public void onBufferReceived(byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.b.obtainMessage(1, paramArrayOfByte);
      this.b.sendMessage(paramArrayOfByte);
    }
    
    public void onCompleted(SpeechError paramSpeechError)
    {
      paramSpeechError = this.b.obtainMessage(2, paramSpeechError);
      this.b.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt, Bundle paramBundle)
    {
      paramBundle = this.b.obtainMessage(0, paramInt, 0, paramBundle);
      this.b.sendMessage(paramBundle);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */