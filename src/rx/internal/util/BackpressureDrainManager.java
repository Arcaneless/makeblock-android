package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.annotations.Experimental;

@Experimental
public final class BackpressureDrainManager
  extends AtomicLong
  implements Producer
{
  private static final long serialVersionUID = 2826241102729529449L;
  protected final BackpressureQueueCallback actual;
  protected boolean emitting;
  protected Throwable exception;
  protected volatile boolean terminated;
  
  public BackpressureDrainManager(BackpressureQueueCallback paramBackpressureQueueCallback)
  {
    this.actual = paramBackpressureQueueCallback;
  }
  
  public final void drain()
  {
    boolean bool1;
    long l1;
    int n;
    int m;
    BackpressureQueueCallback localBackpressureQueueCallback;
    label49:
    try
    {
      if (this.emitting) {
        return;
      }
      this.emitting = true;
      bool1 = this.terminated;
      l1 = get();
      n = 0;
      m = 0;
      i = n;
    }
    finally {}
    try
    {
      localBackpressureQueueCallback = this.actual;
    }
    finally
    {
      if (i != 0) {
        break label296;
      }
    }
    int i = n;
    Object localObject9 = localBackpressureQueueCallback.peek();
    break label380;
    label64:
    localBackpressureQueueCallback.complete(this.exception);
    label103:
    label110:
    int k;
    label156:
    int j;
    if (1 == 0)
    {
      try
      {
        this.emitting = false;
        return;
      }
      finally {}
      if (l1 == 0L)
      {
        i = n;
        i = m;
      }
      try
      {
        bool1 = this.terminated;
        i = m;
        if (((BackpressureQueueCallback)localObject2).peek() != null) {}
        for (k = 1;; k = 0)
        {
          i = m;
          if (get() != Long.MAX_VALUE) {
            break;
          }
          break label390;
          i = j;
          this.emitting = false;
          i = j;
          if (1 != 0) {
            break label357;
          }
          try
          {
            this.emitting = false;
            return;
          }
          finally {}
          label188:
          i = n;
          localObject9 = ((BackpressureQueueCallback)localObject3).poll();
          break label404;
          label203:
          i = n;
          boolean bool2 = ((BackpressureQueueCallback)localObject3).accept(localObject9);
          if (bool2)
          {
            if (1 != 0) {
              break label357;
            }
            try
            {
              this.emitting = false;
              return;
            }
            finally {}
          }
          l1 -= 1L;
          j += 1;
          break label360;
        }
        label261:
        l1 = Long.MAX_VALUE;
        label266:
        i = m;
      }
      finally {}
    }
    try
    {
      this.emitting = false;
      label296:
      throw ((Throwable)localObject6);
      l1 = -j;
      i = m;
      l2 = addAndGet(l1);
      break label412;
      i = j;
      this.emitting = false;
      i = j;
      if (1 == 0) {
        try
        {
          this.emitting = false;
          return;
        }
        finally {}
      }
    }
    finally
    {
      long l2;
      throw ((Throwable)localObject8);
      label357:
      return;
      j = 0;
      label360:
      if ((l1 <= 0L) && (!bool1)) {
        break label110;
      }
      if (!bool1) {
        break label188;
      }
      break label49;
      label380:
      if (localObject9 != null) {
        break label103;
      }
      i = 1;
      break label64;
      label390:
      if ((k != 0) || (bool1)) {
        break label261;
      }
      j = 1;
      break label156;
      label404:
      if (localObject9 == null) {
        break label110;
      }
      break label203;
      label412:
      if (l2 != 0L)
      {
        l1 = l2;
        if (k != 0) {
          break label266;
        }
      }
      if (bool1)
      {
        l1 = l2;
        if (k == 0) {
          break label266;
        }
      }
    }
  }
  
  public final boolean isTerminated()
  {
    return this.terminated;
  }
  
  public final void request(long paramLong)
  {
    if (paramLong == 0L) {
      return;
    }
    label31:
    label70:
    label98:
    for (;;)
    {
      long l2 = get();
      if (l2 == 0L) {}
      for (int i = 1; l2 == Long.MAX_VALUE; i = 0)
      {
        if (i == 0) {
          break label70;
        }
        drain();
        return;
      }
      long l1;
      if (paramLong == Long.MAX_VALUE)
      {
        l1 = paramLong;
        i = 1;
      }
      for (;;)
      {
        if (!compareAndSet(l2, l1)) {
          break label98;
        }
        break label31;
        break;
        if (l2 > Long.MAX_VALUE - paramLong) {
          l1 = Long.MAX_VALUE;
        } else {
          l1 = l2 + paramLong;
        }
      }
    }
  }
  
  public final void terminate()
  {
    this.terminated = true;
  }
  
  public final void terminate(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
    }
  }
  
  public final void terminateAndDrain()
  {
    this.terminated = true;
    drain();
  }
  
  public final void terminateAndDrain(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
      drain();
    }
  }
  
  public static abstract interface BackpressureQueueCallback
  {
    public abstract boolean accept(Object paramObject);
    
    public abstract void complete(Throwable paramThrowable);
    
    public abstract Object peek();
    
    public abstract Object poll();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\BackpressureDrainManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */