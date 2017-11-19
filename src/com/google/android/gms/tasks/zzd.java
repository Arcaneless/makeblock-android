package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult>
  implements zzf<TResult>
{
  private final Executor zzbDK;
  private OnFailureListener zzbLz;
  private final Object zzrN = new Object();
  
  public zzd(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    this.zzbDK = paramExecutor;
    this.zzbLz = paramOnFailureListener;
  }
  
  public void cancel()
  {
    synchronized (this.zzrN)
    {
      this.zzbLz = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (!paramTask.isSuccessful()) {
      synchronized (this.zzrN)
      {
        if (this.zzbLz == null) {
          return;
        }
        this.zzbDK.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zzd.zza(zzd.this))
            {
              if (zzd.zzb(zzd.this) != null) {
                zzd.zzb(zzd.this).onFailure(paramTask.getException());
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */