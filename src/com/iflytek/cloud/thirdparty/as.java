package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.util.FileDownloadListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.cloud.util.ResourceUtil.RESOURCE_TYPE;
import java.io.File;
import org.json.JSONObject;

public class as
  extends F
{
  private static String n = "respath";
  private boolean f = false;
  private String g = null;
  private String h = null;
  private String i = null;
  private String j = null;
  private String k = null;
  private String l = null;
  private boolean m = false;
  private y o = null;
  private N p = null;
  
  public as(Context paramContext)
  {
    super(paramContext);
  }
  
  private void a(boolean paramBoolean)
  {
    ad.a("restart wake ,isError:" + paramBoolean);
    localObject1 = this.c;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        this.k = null;
        b(((x)this.d).j());
        return;
      }
      finally {}
      if (this.d.t())
      {
        this.k = ResourceUtil.generateResourcePath(this.a, ResourceUtil.RESOURCE_TYPE.path, this.p.b("ivw_config_path", null));
        this.l = this.p.b("cfg_threshold", null);
        b(((x)this.d).j());
      }
    }
  }
  
  private int b(WakeuperListener paramWakeuperListener)
  {
    synchronized (this.c)
    {
      for (;;)
      {
        try
        {
          if ((!h()) || (!i())) {
            continue;
          }
          ad.a("ivw use resource from server");
          if (!TextUtils.isEmpty(this.h)) {
            continue;
          }
          str = this.k;
          this.b.a("ivw_res_path", str);
          this.b.a("ivw_threshold", null);
          b(false);
          this.f = this.b.a("request_audio_focus", false);
          if ((this.d != null) && (this.d.t())) {
            ((x)this.d).b(false);
          }
          if (this.b.a("ivw_channel_num", 0) != 0) {
            continue;
          }
          this.d = new x(this.a, this.b, a("wakeuper"));
        }
        catch (SpeechError paramWakeuperListener)
        {
          String str;
          i1 = paramWakeuperListener.getErrorCode();
          ad.a(paramWakeuperListener);
          continue;
          this.d = new v(this.a, this.b, a("wakeuper"));
          continue;
        }
        catch (Throwable paramWakeuperListener)
        {
          ad.a(paramWakeuperListener);
          int i1 = 20999;
          continue;
        }
        T.a(this.a, Boolean.valueOf(this.f), null);
        ((x)this.d).a(new b(paramWakeuperListener));
        i1 = 0;
        return i1;
        str = this.h + ";" + this.k;
      }
      this.b.a("ivw_res_path", this.g);
      this.b.a("ivw_threshold", this.j);
      b(true);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    try
    {
      this.m = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private boolean g()
  {
    try
    {
      boolean bool = this.m;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private boolean h()
  {
    boolean bool2 = false;
    int i1 = this.b.a("ivw_net_mode", 0);
    boolean bool1;
    if (2 != i1)
    {
      bool1 = bool2;
      if (4 == i1)
      {
        bool1 = bool2;
        if (!W.a(this.a)) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  private boolean i()
  {
    boolean bool = false;
    if (!TextUtils.isEmpty(this.k))
    {
      if (TextUtils.isEmpty(this.g)) {
        bool = true;
      }
    }
    else {
      return bool;
    }
    return y.a(y.b(this.k, ""), y.b(this.i, ""));
  }
  
  public int a(WakeuperListener paramWakeuperListener)
  {
    synchronized (this.c)
    {
      try
      {
        this.g = this.b.d("ivw_res_path");
        this.j = this.b.d("ivw_threshold");
        this.h = null;
        if (!TextUtils.isEmpty(this.g))
        {
          i1 = this.g.indexOf(";");
          if ((i1 <= 0) || (this.g.length() <= i1)) {
            break label269;
          }
          this.h = this.g.substring(0, i1);
          this.i = this.g.substring(i1 + 1);
        }
        if (!h()) {
          break label258;
        }
        this.k = ResourceUtil.generateResourcePath(this.a, ResourceUtil.RESOURCE_TYPE.path, this.p.b("ivw_config_path", null));
        localN = this.p;
        l1 = System.currentTimeMillis();
        l2 = localN.b("ivw_query_last_time", 0L);
        long l3 = this.b.a("ivw_query_period", 86400000L);
        ad.a("query ivw res period: " + l3);
        if (l1 - l2 < l3) {
          break label301;
        }
        if (!i()) {
          break label292;
        }
        str = this.k;
      }
      catch (Throwable paramWakeuperListener)
      {
        for (;;)
        {
          N localN;
          long l1;
          long l2;
          label258:
          label269:
          ad.a(paramWakeuperListener);
          int i1 = 20999;
          continue;
          label292:
          String str = this.i;
          continue;
          label301:
          if (l1 == l2) {
            localN.a("ivw_query_last_time", l1);
          }
        }
      }
      ad.a("begin resource query res path: " + str);
      a(str, false, null);
      localN.a("ivw_query_last_time", l1);
      i1 = b(paramWakeuperListener);
      return i1;
      this.i = this.g;
    }
  }
  
  public int a(String paramString1, String paramString2, String paramString3, boolean paramBoolean, FileDownloadListener paramFileDownloadListener)
  {
    synchronized (this.c)
    {
      if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString3))) {
        return 20012;
      }
      ae.a("CreateDownload", null);
      if (this.o != null)
      {
        this.o.a();
        this.o = null;
      }
      this.o = new y(this.a);
      int i1 = this.o.a(paramString1, paramString2, paramString3, new a(paramBoolean, paramFileDownloadListener));
      return i1;
    }
  }
  
  public int a(String paramString, boolean paramBoolean, RequestListener paramRequestListener)
  {
    Object localObject1 = null;
    synchronized (this.c)
    {
      if (TextUtils.isEmpty(paramString))
      {
        ad.b("respath is null. please set respath before startlisten");
        return 25107;
      }
      if (!paramBoolean) {
        localObject1 = ResourceUtil.generateResourcePath(this.a, ResourceUtil.RESOURCE_TYPE.path, this.p.b("ivw_config_path", null));
      }
      if (this.o != null)
      {
        this.o.a();
        this.o = null;
      }
      this.o = new y(this.a);
      localObject1 = y.a(paramString, (String)localObject1);
      if (localObject1 == null)
      {
        ad.b("ivw invalid resource");
        return 25107;
      }
    }
    String str = (String)((JSONObject)localObject1).remove(n);
    if ((!TextUtils.isEmpty(str)) && (!str.equals(paramString)))
    {
      this.k = str;
      this.l = this.p.b("cfg_threshold", null);
    }
    for (;;)
    {
      ae.a("SendRequest", null);
      ad.a(((JSONObject)localObject1).toString());
      int i1 = this.o.a((JSONObject)localObject1, new c(paramBoolean, paramRequestListener));
      return i1;
      this.k = null;
      this.p.a("ivw_config_path");
      this.p.a("cfg_threshold");
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
    if (-1 != ((x)this.d).h()) {
      return 10106;
    }
    paramInt1 = ((x)this.d).a(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt1;
  }
  
  public void cancel(boolean paramBoolean)
  {
    synchronized (this.c)
    {
      if (this.o != null)
      {
        this.o.a();
        this.o = null;
      }
      T.b(this.a, Boolean.valueOf(this.f), null);
      super.cancel(paramBoolean);
      return;
    }
  }
  
  public boolean destroy()
  {
    synchronized (this.c)
    {
      if (this.o != null)
      {
        this.o.a();
        this.o = null;
      }
      boolean bool = super.destroy();
      return bool;
    }
  }
  
  public void e()
  {
    synchronized (this.c)
    {
      if (this.d != null) {
        ((x)this.d).a(true);
      }
      return;
    }
  }
  
  public boolean f()
  {
    synchronized (this.c)
    {
      boolean bool = d();
      return bool;
    }
  }
  
  private final class a
    implements FileDownloadListener
  {
    private FileDownloadListener b = null;
    private final int c = 0;
    private final int d = 1;
    private final int e = 2;
    private final int f = 3;
    private boolean g = false;
    private Handler h = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (as.a.a(as.a.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          as.a.a(as.a.this).onCompleted((String)paramAnonymousMessage.obj, null);
          continue;
          as.a.a(as.a.this).onCompleted(null, (SpeechError)paramAnonymousMessage.obj);
          continue;
          as.a.a(as.a.this).onStart();
          continue;
          as.a.a(as.a.this).onProgress(paramAnonymousMessage.arg1);
        }
      }
    };
    
    public a(boolean paramBoolean, FileDownloadListener paramFileDownloadListener)
    {
      this.g = paramBoolean;
      this.b = paramFileDownloadListener;
    }
    
    public void onCompleted(String paramString, SpeechError paramSpeechError)
    {
      ae.a("DownloadonFinish", null);
      if (paramSpeechError == null)
      {
        ad.a("onCompleted:filePath:" + paramString);
        if (!this.g)
        {
          if (!TextUtils.isEmpty(paramString))
          {
            as.c(as.this).a("ivw_config_path", paramString);
            as.c(as.this).a("cfg_threshold", as.c(as.this).b("cfg_threstemp", null));
          }
          as.a(as.this, false);
        }
        paramString = this.h.obtainMessage(2, paramString);
        this.h.sendMessage(paramString);
        return;
      }
      ad.a("onCompleted:errorcode:" + paramSpeechError.getErrorCode());
      paramString = this.h.obtainMessage(3, paramSpeechError);
      this.h.sendMessage(paramString);
    }
    
    public void onProgress(int paramInt)
    {
      Message localMessage = this.h.obtainMessage(1, paramInt, 0, null);
      this.h.sendMessage(localMessage);
    }
    
    public void onStart()
    {
      ae.a("DownloadonStart", null);
      ad.a("onStart");
      Message localMessage = this.h.obtainMessage(0, null);
      this.h.sendMessage(localMessage);
    }
  }
  
  private final class b
    implements WakeuperListener
  {
    private WakeuperListener b = null;
    private Handler c = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (as.b.a(as.b.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          as.b.a(as.b.this).onError((SpeechError)paramAnonymousMessage.obj);
          continue;
          if (!(as.b.a(as.b.this) instanceof as.b))
          {
            as.b.a(as.b.this).onBeginOfSpeech();
            continue;
            as.b.a(as.b.this).onResult((WakeuperResult)paramAnonymousMessage.obj);
            continue;
            as.b.a(as.b.this).onVolumeChanged(paramAnonymousMessage.arg1);
            continue;
            Message localMessage = (Message)paramAnonymousMessage.obj;
            if (localMessage != null) {
              as.b.a(as.b.this).onEvent(localMessage.what, localMessage.arg1, localMessage.arg2, (Bundle)localMessage.obj);
            }
          }
        }
      }
    };
    
    public b(WakeuperListener paramWakeuperListener)
    {
      this.b = paramWakeuperListener;
    }
    
    protected void a()
    {
      T.b(as.f(as.this), Boolean.valueOf(as.g(as.this)), null);
    }
    
    public void onBeginOfSpeech()
    {
      ad.a("onBeginOfSpeech");
      Message localMessage = this.c.obtainMessage(2, 0, 0, null);
      this.c.sendMessage(localMessage);
    }
    
    public void onError(SpeechError paramSpeechError)
    {
      ad.b("error:" + paramSpeechError.getErrorCode());
      if (!as.e(as.this))
      {
        as.a(as.this, true);
        return;
      }
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
      paramBundle = this.c.obtainMessage(6, 0, 0, localMessage);
      this.c.sendMessage(paramBundle);
    }
    
    public void onResult(WakeuperResult paramWakeuperResult)
    {
      if (!as.d(as.this).a("keep_alive", true)) {
        a();
      }
      paramWakeuperResult = this.c.obtainMessage(4, 1, 0, paramWakeuperResult);
      this.c.sendMessage(paramWakeuperResult);
    }
    
    public void onVolumeChanged(int paramInt)
    {
      ad.a("onVolumeChanged");
      Message localMessage = this.c.obtainMessage(5, paramInt, 0, null);
      this.c.sendMessage(localMessage);
    }
  }
  
  private class c
    implements RequestListener
  {
    private RequestListener b = null;
    private final int c = 0;
    private final int d = 1;
    private final int e = 2;
    private boolean f = false;
    private Handler g = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (as.c.a(as.c.this) == null) {
          return;
        }
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          as.c.a(as.c.this).onEvent(paramAnonymousMessage.arg1, (Bundle)paramAnonymousMessage.obj);
          continue;
          as.c.a(as.c.this).onBufferReceived((byte[])paramAnonymousMessage.obj);
          continue;
          as.c.a(as.c.this).onCompleted((SpeechError)paramAnonymousMessage.obj);
        }
      }
    };
    
    public c(boolean paramBoolean, RequestListener paramRequestListener)
    {
      this.f = paramBoolean;
      this.b = paramRequestListener;
    }
    
    public void onBufferReceived(byte[] paramArrayOfByte)
    {
      ad.a("onCompleted");
      try
      {
        if ((!this.f) && (as.a(as.this)))
        {
          JSONObject localJSONObject = new JSONObject(new String(paramArrayOfByte, "utf-8"));
          String str1 = localJSONObject.getString("dlurl");
          String str2 = localJSONObject.getString("md5");
          String str3 = str1.substring(str1.lastIndexOf(File.separator) + 1, str1.lastIndexOf("."));
          ad.a("resName:" + str3);
          str3 = S.a(as.b(as.this), str3);
          ad.a("auto download path:" + str3);
          as.this.a(str1, str3, str2, this.f, null);
          as.c(as.this).a("cfg_threstemp", localJSONObject.getString("thresholder"));
        }
        paramArrayOfByte = this.g.obtainMessage(1, paramArrayOfByte);
        this.g.sendMessage(paramArrayOfByte);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          onCompleted(new SpeechError(20014));
        }
      }
    }
    
    public void onCompleted(SpeechError paramSpeechError)
    {
      ae.a("RequestResult", null);
      paramSpeechError = this.g.obtainMessage(2, paramSpeechError);
      this.g.sendMessage(paramSpeechError);
    }
    
    public void onEvent(int paramInt, Bundle paramBundle)
    {
      ae.a("RequestResult", null);
      paramBundle = this.g.obtainMessage(0, paramInt, 0, paramBundle);
      this.g.sendMessage(paramBundle);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */