package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult>
  implements zzf<TResult>
{
  private final Executor zzbDK;
  private final Continuation<TResult, TContinuationResult> zzbLs;
  private final zzh<TContinuationResult> zzbLt;
  
  public zza(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation, @NonNull zzh<TContinuationResult> paramzzh)
  {
    this.zzbDK = paramExecutor;
    this.zzbLs = paramContinuation;
    this.zzbLt = paramzzh;
  }
  
  public void cancel()
  {
    throw new UnsupportedOperationException();
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    this.zzbDK.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = zza.zza(zza.this).then(paramTask);
          zza.zzb(zza.this).setResult(localObject);
          return;
        }
        catch (RuntimeExecutionException localRuntimeExecutionException)
        {
          if ((localRuntimeExecutionException.getCause() instanceof Exception))
          {
            zza.zzb(zza.this).setException((Exception)localRuntimeExecutionException.getCause());
            return;
          }
          zza.zzb(zza.this).setException(localRuntimeExecutionException);
          return;
        }
        catch (Exception localException)
        {
          zza.zzb(zza.this).setException(localException);
        }
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */