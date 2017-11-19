package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult>
  implements zzf<TResult>
{
  private final Executor zzbDK;
  private OnSuccessListener<? super TResult> zzbLB;
  private final Object zzrN = new Object();
  
  public zze(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    this.zzbDK = paramExecutor;
    this.zzbLB = paramOnSuccessListener;
  }
  
  public void cancel()
  {
    synchronized (this.zzrN)
    {
      this.zzbLB = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (paramTask.isSuccessful()) {
      synchronized (this.zzrN)
      {
        if (this.zzbLB == null) {
          return;
        }
        this.zzbDK.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zze.zza(zze.this))
            {
              if (zze.zzb(zze.this) != null) {
                zze.zzb(zze.this).onSuccess(paramTask.getResult());
              }
              return;
            }
          }
        });
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */