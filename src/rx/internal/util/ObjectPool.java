package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public abstract class ObjectPool<T>
  implements SchedulerLifecycle
{
  final int maxSize;
  final int minSize;
  private final AtomicReference<Future<?>> periodicTask;
  Queue<T> pool;
  private final long validationInterval;
  
  public ObjectPool()
  {
    this(0, 0, 67L);
  }
  
  private ObjectPool(int paramInt1, int paramInt2, long paramLong)
  {
    this.minSize = paramInt1;
    this.maxSize = paramInt2;
    this.validationInterval = paramLong;
    this.periodicTask = new AtomicReference();
    initialize(paramInt1);
    start();
  }
  
  private void initialize(int paramInt)
  {
    if (UnsafeAccess.isUnsafeAvailable()) {}
    for (this.pool = new MpmcArrayQueue(Math.max(this.maxSize, 1024));; this.pool = new ConcurrentLinkedQueue())
    {
      int i = 0;
      while (i < paramInt)
      {
        this.pool.add(createObject());
        i += 1;
      }
    }
  }
  
  public T borrowObject()
  {
    Object localObject2 = this.pool.poll();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = createObject();
    }
    return (T)localObject1;
  }
  
  protected abstract T createObject();
  
  public void returnObject(T paramT)
  {
    if (paramT == null) {
      return;
    }
    this.pool.offer(paramT);
  }
  
  public void shutdown()
  {
    Future localFuture = (Future)this.periodicTask.getAndSet(null);
    if (localFuture != null) {
      localFuture.cancel(false);
    }
  }
  
  public void start()
  {
    for (;;)
    {
      if (this.periodicTask.get() != null) {
        label10:
        return;
      }
      Object localObject = GenericScheduledExecutorService.getInstance();
      try
      {
        localObject = ((ScheduledExecutorService)localObject).scheduleAtFixedRate(new Runnable()
        {
          public void run()
          {
            int j = ObjectPool.this.pool.size();
            int k;
            int i;
            if (j < ObjectPool.this.minSize)
            {
              k = ObjectPool.this.maxSize;
              i = 0;
              while (i < k - j)
              {
                ObjectPool.this.pool.add(ObjectPool.this.createObject());
                i += 1;
              }
            }
            if (j > ObjectPool.this.maxSize)
            {
              k = ObjectPool.this.maxSize;
              i = 0;
              while (i < j - k)
              {
                ObjectPool.this.pool.poll();
                i += 1;
              }
            }
          }
        }, this.validationInterval, this.validationInterval, TimeUnit.SECONDS);
        if (this.periodicTask.compareAndSet(null, localObject)) {
          break label10;
        }
        ((Future)localObject).cancel(false);
      }
      catch (RejectedExecutionException localRejectedExecutionException)
      {
        RxJavaPluginUtils.handleException(localRejectedExecutionException);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\ObjectPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */