package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public abstract interface Continuation<TResult, TContinuationResult>
{
  public abstract TContinuationResult then(@NonNull Task<TResult> paramTask)
    throws Exception;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\Continuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */