package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

abstract interface zzf<TResult>
{
  public abstract void cancel();
  
  public abstract void onComplete(@NonNull Task<TResult> paramTask);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */