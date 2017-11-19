package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

class zzg<TResult>
{
  private Queue<zzf<TResult>> zzbLD;
  private boolean zzbLE;
  private final Object zzrN = new Object();
  
  public void zza(@NonNull Task<TResult> paramTask)
  {
    for (;;)
    {
      zzf localzzf;
      synchronized (this.zzrN)
      {
        if ((this.zzbLD == null) || (this.zzbLE)) {
          return;
        }
        this.zzbLE = true;
        synchronized (this.zzrN)
        {
          localzzf = (zzf)this.zzbLD.poll();
          if (localzzf == null)
          {
            this.zzbLE = false;
            return;
          }
        }
      }
      localzzf.onComplete(paramTask);
    }
  }
  
  public void zza(@NonNull zzf<TResult> paramzzf)
  {
    synchronized (this.zzrN)
    {
      if (this.zzbLD == null) {
        this.zzbLD = new ArrayDeque();
      }
      this.zzbLD.add(paramzzf);
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */