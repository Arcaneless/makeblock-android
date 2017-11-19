package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks
{
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask)
    throws ExecutionException, InterruptedException
  {
    zzac.zzxx();
    zzac.zzb(paramTask, "Task must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    localzza.await();
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask, long paramLong, @NonNull TimeUnit paramTimeUnit)
    throws ExecutionException, InterruptedException, TimeoutException
  {
    zzac.zzxx();
    zzac.zzb(paramTask, "Task must not be null");
    zzac.zzb(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    if (!localzza.await(paramLong, paramTimeUnit)) {
      throw new TimeoutException("Timed out waiting for Task");
    }
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Callable<TResult> paramCallable)
  {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Executor paramExecutor, @NonNull final Callable<TResult> paramCallable)
  {
    zzac.zzb(paramExecutor, "Executor must not be null");
    zzac.zzb(paramCallable, "Callback must not be null");
    zzh localzzh = new zzh();
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Tasks.this.setResult(paramCallable.call());
          return;
        }
        catch (Exception localException)
        {
          Tasks.this.setException(localException);
        }
      }
    });
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forException(@NonNull Exception paramException)
  {
    zzh localzzh = new zzh();
    localzzh.setException(paramException);
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult)
  {
    zzh localzzh = new zzh();
    localzzh.setResult(paramTResult);
    return localzzh;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection)
  {
    if (paramCollection.isEmpty()) {
      return forResult(null);
    }
    Object localObject = paramCollection.iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((Task)((Iterator)localObject).next() == null) {
        throw new NullPointerException("null tasks are not accepted");
      }
    }
    localObject = new zzh();
    zzc localzzc = new zzc(paramCollection.size(), (zzh)localObject);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      zza((Task)paramCollection.next(), localzzc);
    }
    return (Task<Void>)localObject;
  }
  
  public static Task<Void> whenAll(Task<?>... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return forResult(null);
    }
    return whenAll(Arrays.asList(paramVarArgs));
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb)
  {
    paramTask.addOnSuccessListener(TaskExecutors.zzbLG, paramzzb);
    paramTask.addOnFailureListener(TaskExecutors.zzbLG, paramzzb);
  }
  
  private static <TResult> TResult zzb(Task<TResult> paramTask)
    throws ExecutionException
  {
    if (paramTask.isSuccessful()) {
      return (TResult)paramTask.getResult();
    }
    throw new ExecutionException(paramTask.getException());
  }
  
  private static final class zza
    implements Tasks.zzb
  {
    private final CountDownLatch zzth = new CountDownLatch(1);
    
    public void await()
      throws InterruptedException
    {
      this.zzth.await();
    }
    
    public boolean await(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return this.zzth.await(paramLong, paramTimeUnit);
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      this.zzth.countDown();
    }
    
    public void onSuccess(Object paramObject)
    {
      this.zzth.countDown();
    }
  }
  
  static abstract interface zzb
    extends OnFailureListener, OnSuccessListener<Object>
  {}
  
  private static final class zzc
    implements Tasks.zzb
  {
    private final zzh<Void> zzbLF;
    private Exception zzbLK;
    private final int zzbLM;
    private int zzbLN;
    private int zzbLO;
    private final Object zzrN = new Object();
    
    public zzc(int paramInt, zzh<Void> paramzzh)
    {
      this.zzbLM = paramInt;
      this.zzbLF = paramzzh;
    }
    
    private void zzSh()
    {
      if (this.zzbLN + this.zzbLO == this.zzbLM)
      {
        if (this.zzbLK == null) {
          this.zzbLF.setResult(null);
        }
      }
      else {
        return;
      }
      zzh localzzh = this.zzbLF;
      int i = this.zzbLO;
      int j = this.zzbLM;
      localzzh.setException(new ExecutionException(54 + i + " out of " + j + " underlying tasks failed", this.zzbLK));
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      synchronized (this.zzrN)
      {
        this.zzbLO += 1;
        this.zzbLK = paramException;
        zzSh();
        return;
      }
    }
    
    public void onSuccess(Object arg1)
    {
      synchronized (this.zzrN)
      {
        this.zzbLN += 1;
        zzSh();
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\tasks\Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */