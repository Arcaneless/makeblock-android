package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
  private final zzh<TResult> zzbLF = new zzh();
  
  @NonNull
  public Task<TResult> getTask()
  {
    return this.zzbLF;
  }
  
  public void setException(@NonNull Exception paramException)
  {
    this.zzbLF.setException(paramException);
  }
  
  public void setResult(TResult paramTResult)
  {
    this.zzbLF.setResult(paramTResult);
  }
  
  public boolean trySetException(@NonNull Exception paramException)
  {
    return this.zzbLF.trySetException(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult)
  {
    return this.zzbLF.trySetResult(paramTResult);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\TaskCompletionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */