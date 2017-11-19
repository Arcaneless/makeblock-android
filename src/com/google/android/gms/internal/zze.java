package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze
  implements zzn
{
  private final Executor zzr;
  
  public zze(final Handler paramHandler)
  {
    this.zzr = new Executor()
    {
      public void execute(Runnable paramAnonymousRunnable)
      {
        paramHandler.post(paramAnonymousRunnable);
      }
    };
  }
  
  public void zza(zzk<?> paramzzk, zzm<?> paramzzm)
  {
    zza(paramzzk, paramzzm, null);
  }
  
  public void zza(zzk<?> paramzzk, zzm<?> paramzzm, Runnable paramRunnable)
  {
    paramzzk.zzr();
    paramzzk.zzc("post-response");
    this.zzr.execute(new zza(paramzzk, paramzzm, paramRunnable));
  }
  
  public void zza(zzk<?> paramzzk, zzr paramzzr)
  {
    paramzzk.zzc("post-error");
    paramzzr = zzm.zzd(paramzzr);
    this.zzr.execute(new zza(paramzzk, paramzzr, null));
  }
  
  private class zza
    implements Runnable
  {
    private final zzk zzt;
    private final zzm zzu;
    private final Runnable zzv;
    
    public zza(zzk paramzzk, zzm paramzzm, Runnable paramRunnable)
    {
      this.zzt = paramzzk;
      this.zzu = paramzzm;
      this.zzv = paramRunnable;
    }
    
    public void run()
    {
      if (this.zzu.isSuccess())
      {
        this.zzt.zza(this.zzu.result);
        if (!this.zzu.zzag) {
          break label77;
        }
        this.zzt.zzc("intermediate-response");
      }
      for (;;)
      {
        if (this.zzv != null) {
          this.zzv.run();
        }
        return;
        this.zzt.zzc(this.zzu.zzaf);
        break;
        label77:
        this.zzt.zzd("done");
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */