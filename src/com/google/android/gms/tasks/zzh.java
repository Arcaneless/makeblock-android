package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzaaw;
import com.google.android.gms.internal.zzaax;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult>
  extends Task<TResult>
{
  private final zzg<TResult> zzbLH = new zzg();
  private boolean zzbLI;
  private TResult zzbLJ;
  private Exception zzbLK;
  private final Object zzrN = new Object();
  
  private void zzSe()
  {
    zzac.zza(this.zzbLI, "Task is not yet complete");
  }
  
  private void zzSf()
  {
    if (!this.zzbLI) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "Task is already complete");
      return;
    }
  }
  
  private void zzSg()
  {
    synchronized (this.zzrN)
    {
      if (!this.zzbLI) {
        return;
      }
      this.zzbLH.zza(this);
      return;
    }
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    paramOnCompleteListener = new zzc(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    this.zzbLH.zza(paramOnCompleteListener);
    zza.zzw(paramActivity).zzb(paramOnCompleteListener);
    zzSg();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    return addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    this.zzbLH.zza(new zzc(paramExecutor, paramOnCompleteListener));
    zzSg();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener)
  {
    paramOnFailureListener = new zzd(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    this.zzbLH.zza(paramOnFailureListener);
    zza.zzw(paramActivity).zzb(paramOnFailureListener);
    zzSg();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    return addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    this.zzbLH.zza(new zzd(paramExecutor, paramOnFailureListener));
    zzSg();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    paramOnSuccessListener = new zze(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    this.zzbLH.zza(paramOnSuccessListener);
    zza.zzw(paramActivity).zzb(paramOnSuccessListener);
    zzSg();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    return addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    this.zzbLH.zza(new zze(paramExecutor, paramOnSuccessListener));
    zzSg();
    return this;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    zzh localzzh = new zzh();
    this.zzbLH.zza(new zza(paramExecutor, paramContinuation, localzzh));
    zzSg();
    return localzzh;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    zzh localzzh = new zzh();
    this.zzbLH.zza(new zzb(paramExecutor, paramContinuation, localzzh));
    zzSg();
    return localzzh;
  }
  
  @Nullable
  public Exception getException()
  {
    synchronized (this.zzrN)
    {
      Exception localException = this.zzbLK;
      return localException;
    }
  }
  
  public TResult getResult()
  {
    synchronized (this.zzrN)
    {
      zzSe();
      if (this.zzbLK != null) {
        throw new RuntimeExecutionException(this.zzbLK);
      }
    }
    Object localObject3 = this.zzbLJ;
    return (TResult)localObject3;
  }
  
  public <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass)
    throws Throwable
  {
    synchronized (this.zzrN)
    {
      zzSe();
      if (paramClass.isInstance(this.zzbLK)) {
        throw ((Throwable)paramClass.cast(this.zzbLK));
      }
    }
    if (this.zzbLK != null) {
      throw new RuntimeExecutionException(this.zzbLK);
    }
    paramClass = this.zzbLJ;
    return paramClass;
  }
  
  public boolean isComplete()
  {
    synchronized (this.zzrN)
    {
      boolean bool = this.zzbLI;
      return bool;
    }
  }
  
  public boolean isSuccessful()
  {
    for (;;)
    {
      synchronized (this.zzrN)
      {
        if ((this.zzbLI) && (this.zzbLK == null))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void setException(@NonNull Exception paramException)
  {
    zzac.zzb(paramException, "Exception must not be null");
    synchronized (this.zzrN)
    {
      zzSf();
      this.zzbLI = true;
      this.zzbLK = paramException;
      this.zzbLH.zza(this);
      return;
    }
  }
  
  public void setResult(TResult paramTResult)
  {
    synchronized (this.zzrN)
    {
      zzSf();
      this.zzbLI = true;
      this.zzbLJ = paramTResult;
      this.zzbLH.zza(this);
      return;
    }
  }
  
  public boolean trySetException(@NonNull Exception paramException)
  {
    zzac.zzb(paramException, "Exception must not be null");
    synchronized (this.zzrN)
    {
      if (this.zzbLI) {
        return false;
      }
      this.zzbLI = true;
      this.zzbLK = paramException;
      this.zzbLH.zza(this);
      return true;
    }
  }
  
  public boolean trySetResult(TResult paramTResult)
  {
    synchronized (this.zzrN)
    {
      if (this.zzbLI) {
        return false;
      }
      this.zzbLI = true;
      this.zzbLJ = paramTResult;
      this.zzbLH.zza(this);
      return true;
    }
  }
  
  private static class zza
    extends zzaaw
  {
    private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();
    
    private zza(zzaax paramzzaax)
    {
      super();
      this.zzaBs.zza("TaskOnStopCallback", this);
    }
    
    public static zza zzw(Activity paramActivity)
    {
      zzaax localzzaax = zzs(paramActivity);
      zza localzza = (zza)localzzaax.zza("TaskOnStopCallback", zza.class);
      paramActivity = localzza;
      if (localzza == null) {
        paramActivity = new zza(localzzaax);
      }
      return paramActivity;
    }
    
    @MainThread
    public void onStop()
    {
      synchronized (this.mListeners)
      {
        Iterator localIterator = this.mListeners.iterator();
        while (localIterator.hasNext())
        {
          zzf localzzf = (zzf)((WeakReference)localIterator.next()).get();
          if (localzzf != null) {
            localzzf.cancel();
          }
        }
      }
      this.mListeners.clear();
    }
    
    public <T> void zzb(zzf<T> paramzzf)
    {
      synchronized (this.mListeners)
      {
        this.mListeners.add(new WeakReference(paramzzf));
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */