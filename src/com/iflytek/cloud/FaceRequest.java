package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.thirdparty.E;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.ak;

public class FaceRequest
  extends E
{
  private ak a;
  private Context c;
  
  public FaceRequest(Context paramContext)
  {
    this.c = paramContext;
  }
  
  public void cancel()
  {
    try
    {
      if (this.a != null)
      {
        this.a.a();
        this.a = null;
      }
      return;
    }
    finally {}
  }
  
  public String getParameter(String paramString)
  {
    return super.getParameter(paramString);
  }
  
  public int sendRequest(byte[] paramArrayOfByte, RequestListener paramRequestListener)
  {
    try
    {
      if (this.a != null)
      {
        this.a.a();
        this.a = null;
      }
      this.a = new ak(this.c, this.b);
      int i = this.a.a(paramArrayOfByte, new a(paramRequestListener));
      return i;
    }
    finally {}
  }
  
  public boolean setParameter(String paramString1, String paramString2)
  {
    return super.setParameter(paramString1, paramString2);
  }
  
  protected class a
    implements RequestListener
  {
    private RequestListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (FaceRequest.a.a(FaceRequest.a.this) == null) {
          return;
        }
        ad.a("SpeechListener onMsg = " + paramAnonymousMessage.what);
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          FaceRequest.a.a(FaceRequest.a.this).onEvent(paramAnonymousMessage.arg1, (Bundle)paramAnonymousMessage.obj);
          continue;
          FaceRequest.a.a(FaceRequest.a.this).onBufferReceived((byte[])paramAnonymousMessage.obj);
          continue;
          FaceRequest.a.a(FaceRequest.a.this).onCompleted((SpeechError)paramAnonymousMessage.obj);
        }
      }
    };
    
    public a(RequestListener paramRequestListener)
    {
      this.b = paramRequestListener;
    }
    
    public void onBufferReceived(byte[] paramArrayOfByte)
    {
      paramArrayOfByte = this.c.obtainMessage(1, paramArrayOfByte);
      this.c.sendMessage(paramArrayOfByte);
    }
    
    public void onCompleted(SpeechError paramSpeechError)
    {
      paramSpeechError = this.c.obtainMessage(2, paramSpeechError);
      this.c.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt, Bundle paramBundle)
    {
      paramBundle = this.c.obtainMessage(0, paramInt, 0, paramBundle);
      this.c.sendMessage(paramBundle);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\FaceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */