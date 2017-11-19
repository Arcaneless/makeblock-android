package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;

public class zzaty
  extends zzats
{
  private Handler mHandler;
  protected long zzbuT;
  private final zzasv zzbuU = new zzasv(this.zzbpw)
  {
    @WorkerThread
    public void run()
    {
      zzaty.this.zzMf();
    }
  };
  private final zzasv zzbuV = new zzasv(this.zzbpw)
  {
    @WorkerThread
    public void run()
    {
      zzaty.zza(zzaty.this);
    }
  };
  
  zzaty(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  private void zzMd()
  {
    try
    {
      if (this.mHandler == null) {
        this.mHandler = new Handler(Looper.getMainLooper());
      }
      return;
    }
    finally {}
  }
  
  @WorkerThread
  private void zzMg()
  {
    zzmq();
    zzaJ(false);
    zzJg().zzV(zznq().elapsedRealtime());
  }
  
  @WorkerThread
  private void zzap(long paramLong)
  {
    zzmq();
    zzMd();
    this.zzbuU.cancel();
    this.zzbuV.cancel();
    zzJt().zzLg().zzj("Activity resumed, time", Long.valueOf(paramLong));
    this.zzbuT = paramLong;
    if (zznq().currentTimeMillis() - zzJu().zzbsq.get() > zzJu().zzbss.get())
    {
      zzJu().zzbsr.set(true);
      zzJu().zzbst.set(0L);
    }
    if (zzJu().zzbsr.get())
    {
      this.zzbuU.zzx(Math.max(0L, zzJu().zzbsp.get() - zzJu().zzbst.get()));
      return;
    }
    this.zzbuV.zzx(Math.max(0L, 3600000L - zzJu().zzbst.get()));
  }
  
  @WorkerThread
  private void zzaq(long paramLong)
  {
    zzmq();
    zzMd();
    this.zzbuU.cancel();
    this.zzbuV.cancel();
    zzJt().zzLg().zzj("Activity paused, time", Long.valueOf(paramLong));
    if (this.zzbuT != 0L) {
      zzJu().zzbst.set(zzJu().zzbst.get() + (paramLong - this.zzbuT));
    }
    zzJu().zzbss.set(zznq().currentTimeMillis());
  }
  
  @MainThread
  protected void zzMc()
  {
    final long l = zznq().elapsedRealtime();
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzaty.zza(zzaty.this, l);
      }
    });
  }
  
  @MainThread
  protected void zzMe()
  {
    final long l = zznq().elapsedRealtime();
    zzJs().zzm(new Runnable()
    {
      public void run()
      {
        zzaty.zzb(zzaty.this, l);
      }
    });
  }
  
  @WorkerThread
  protected void zzMf()
  {
    zzmq();
    long l = zznq().elapsedRealtime();
    zzJt().zzLg().zzj("Session started, time", Long.valueOf(l));
    zzJu().zzbsr.set(false);
    zzJi().zze("auto", "_s", new Bundle());
  }
  
  @WorkerThread
  public boolean zzaJ(boolean paramBoolean)
  {
    zzmq();
    zznA();
    long l1 = zznq().elapsedRealtime();
    if (this.zzbuT == 0L) {
      this.zzbuT = (l1 - 3600000L);
    }
    long l2 = l1 - this.zzbuT;
    if ((!paramBoolean) && (l2 < 1000L))
    {
      zzJt().zzLg().zzj("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(l2));
      return false;
    }
    zzJu().zzbst.set(l2);
    zzJt().zzLg().zzj("Recording user engagement, ms", Long.valueOf(l2));
    Bundle localBundle = new Bundle();
    localBundle.putLong("_et", l2);
    zzatv.zza(zzJm().zzLU(), localBundle);
    zzJi().zze("auto", "_e", localBundle);
    this.zzbuT = l1;
    this.zzbuV.cancel();
    this.zzbuV.zzx(Math.max(0L, 3600000L - zzJu().zzbst.get()));
    return true;
  }
  
  protected void zzmr() {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzaty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */