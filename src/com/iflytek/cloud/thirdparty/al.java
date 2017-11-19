package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.IdentityListener;
import com.iflytek.cloud.IdentityResult;
import com.iflytek.cloud.SpeechError;

public class al
  extends F
{
  private boolean f = false;
  
  public al(Context paramContext)
  {
    super(paramContext);
  }
  
  public int a(IdentityListener paramIdentityListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.f = this.b.a("request_audio_focus", true);
        if ((this.d != null) && (this.d.t())) {
          this.d.b(this.b.a("mfv_interrupt_error", false));
        }
        this.d = new A(this.a, this.b, a("mfv"));
        T.a(this.a, Boolean.valueOf(this.f), null);
        ((A)this.d).a(new a(paramIdentityListener));
        i = 0;
      }
      catch (SpeechError paramIdentityListener)
      {
        for (;;)
        {
          i = paramIdentityListener.getErrorCode();
          ad.a(paramIdentityListener);
        }
      }
      catch (Throwable paramIdentityListener)
      {
        for (;;)
        {
          ad.a(paramIdentityListener);
          int i = 20999;
        }
      }
      return i;
    }
  }
  
  public int a(String paramString1, String paramString2, String paramString3, IdentityListener paramIdentityListener)
  {
    setParameter("sst", paramString2);
    int j = a(paramIdentityListener);
    int i = j;
    if (j == 0)
    {
      i = a(paramString1, paramString3, null, 0, 0);
      c(paramString1);
    }
    return i;
  }
  
  public int a(String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    synchronized (this.c)
    {
      if (this.d == null)
      {
        ad.b("writeAudio error, no active session.");
        return 21004;
      }
      if (paramInt2 < 0)
      {
        ad.b("writeAudio error, length < 0.");
        return 10109;
      }
    }
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length < paramInt2 + paramInt1))
    {
      ad.b("writeAudio error, buffer length < offset + length.");
      return 10109;
    }
    ((A)this.d).a(paramString1, paramString2, paramArrayOfByte, paramInt1, paramInt2);
    return 0;
  }
  
  public void b(String paramString)
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((A)this.d).k().a(paramString);
      }
      return;
    }
  }
  
  public void c(String paramString)
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((A)this.d).a(paramString, true);
      }
      return;
    }
  }
  
  public void cancel(boolean paramBoolean)
  {
    T.b(this.a, Boolean.valueOf(this.f), null);
    super.cancel(paramBoolean);
  }
  
  public boolean e()
  {
    return d();
  }
  
  private final class a
    implements IdentityListener
  {
    private IdentityListener b = null;
    private boolean c = false;
    private Handler d = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (al.a.a(al.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          al.a.a(al.a.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          Object localObject = al.a.a(al.a.this);
          IdentityResult localIdentityResult = (IdentityResult)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.arg1 == 1) {}
          for (boolean bool = true;; bool = false)
          {
            ((IdentityListener)localObject).onResult(localIdentityResult, bool);
            if (!al.a.b(al.a.this))
            {
              al.this.b("ui_frs");
              al.a.a(al.a.this, true);
            }
            if (1 != paramAnonymousMessage.arg1) {
              break;
            }
            al.this.b("ui_lrs");
            break;
          }
          localObject = (Message)paramAnonymousMessage.obj;
          al.a.a(al.a.this).onEvent(((Message)localObject).what, ((Message)localObject).arg1, ((Message)localObject).arg2, (Bundle)((Message)localObject).obj);
        }
      }
    };
    
    public a(IdentityListener paramIdentityListener)
    {
      this.b = paramIdentityListener;
    }
    
    protected void a()
    {
      ((A)al.a(al.this)).a().a();
      T.b(al.b(al.this), Boolean.valueOf(al.c(al.this)), null);
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      a();
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
    
    public void onResult(IdentityResult paramIdentityResult, boolean paramBoolean)
    {
      int i = 1;
      if (paramBoolean) {
        a();
      }
      Handler localHandler = this.d;
      if (paramBoolean == true) {}
      for (;;)
      {
        paramIdentityResult = localHandler.obtainMessage(4, i, 0, paramIdentityResult);
        this.d.sendMessage(paramIdentityResult);
        return;
        i = 0;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */