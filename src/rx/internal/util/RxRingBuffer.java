package rx.internal.util;

import java.io.PrintStream;
import java.util.Queue;
import rx.Observer;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public class RxRingBuffer
  implements Subscription
{
  public static final int SIZE;
  public static ObjectPool<Queue<Object>> SPMC_POOL;
  public static ObjectPool<Queue<Object>> SPSC_POOL;
  static int _size;
  private static final NotificationLite<Object> on = ;
  private final ObjectPool<Queue<Object>> pool;
  private Queue<Object> queue;
  private final int size;
  public volatile Object terminalState;
  
  static
  {
    _size = 128;
    if (PlatformDependent.isAndroid()) {
      _size = 16;
    }
    String str = System.getProperty("rx.ring-buffer.size");
    if (str != null) {}
    try
    {
      _size = Integer.parseInt(str);
      SIZE = _size;
      SPSC_POOL = new ObjectPool()
      {
        protected SpscArrayQueue<Object> createObject()
        {
          return new SpscArrayQueue(RxRingBuffer.SIZE);
        }
      };
      SPMC_POOL = new ObjectPool()
      {
        protected SpmcArrayQueue<Object> createObject()
        {
          return new SpmcArrayQueue(RxRingBuffer.SIZE);
        }
      };
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.err.println("Failed to set 'rx.buffer.size' with value " + str + " => " + localException.getMessage());
      }
    }
  }
  
  RxRingBuffer()
  {
    this(new SynchronizedQueue(SIZE), SIZE);
  }
  
  private RxRingBuffer(Queue<Object> paramQueue, int paramInt)
  {
    this.queue = paramQueue;
    this.pool = null;
    this.size = paramInt;
  }
  
  private RxRingBuffer(ObjectPool<Queue<Object>> paramObjectPool, int paramInt)
  {
    this.pool = paramObjectPool;
    this.queue = ((Queue)paramObjectPool.borrowObject());
    this.size = paramInt;
  }
  
  public static RxRingBuffer getSpmcInstance()
  {
    if (UnsafeAccess.isUnsafeAvailable()) {
      return new RxRingBuffer(SPMC_POOL, SIZE);
    }
    return new RxRingBuffer();
  }
  
  public static RxRingBuffer getSpscInstance()
  {
    if (UnsafeAccess.isUnsafeAvailable()) {
      return new RxRingBuffer(SPSC_POOL, SIZE);
    }
    return new RxRingBuffer();
  }
  
  public boolean accept(Object paramObject, Observer paramObserver)
  {
    return on.accept(paramObserver, paramObject);
  }
  
  public Throwable asError(Object paramObject)
  {
    return on.getError(paramObject);
  }
  
  public int available()
  {
    return this.size - count();
  }
  
  public int capacity()
  {
    return this.size;
  }
  
  public int count()
  {
    Queue localQueue = this.queue;
    if (localQueue == null) {
      return 0;
    }
    return localQueue.size();
  }
  
  public Object getValue(Object paramObject)
  {
    return on.getValue(paramObject);
  }
  
  public boolean isCompleted(Object paramObject)
  {
    return on.isCompleted(paramObject);
  }
  
  public boolean isEmpty()
  {
    Queue localQueue = this.queue;
    if (localQueue == null) {
      return true;
    }
    return localQueue.isEmpty();
  }
  
  public boolean isError(Object paramObject)
  {
    return on.isError(paramObject);
  }
  
  public boolean isUnsubscribed()
  {
    return this.queue == null;
  }
  
  public void onCompleted()
  {
    if (this.terminalState == null) {
      this.terminalState = on.completed();
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    if (this.terminalState == null) {
      this.terminalState = on.error(paramThrowable);
    }
  }
  
  public void onNext(Object paramObject)
    throws MissingBackpressureException
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      Queue localQueue;
      try
      {
        localQueue = this.queue;
      }
      finally {}
      if (!localQueue.offer(on.next(paramObject)))
      {
        i = 1;
        if (j != 0) {
          throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        }
      }
      else
      {
        i = 0;
        continue;
        j = 1;
        continue;
      }
      if (i != 0) {
        throw new MissingBackpressureException();
      }
      return;
      if (localQueue == null) {}
    }
  }
  
  public Object peek()
  {
    Queue localQueue;
    try
    {
      localQueue = this.queue;
    }
    finally {}
    return null;
    label15:
    Object localObject4 = localQueue.peek();
    Object localObject5 = this.terminalState;
    Object localObject1 = localObject4;
    if (localObject4 == null)
    {
      localObject1 = localObject4;
      if (localObject5 != null)
      {
        localObject1 = localObject4;
        if (localQueue.peek() == null) {
          break label72;
        }
      }
    }
    for (;;)
    {
      return localObject1;
      if (localQueue != null) {
        break label15;
      }
      break;
      label72:
      Object localObject3 = localObject5;
    }
  }
  
  public Object poll()
  {
    Queue localQueue;
    try
    {
      localQueue = this.queue;
    }
    finally {}
    return null;
    label15:
    Object localObject4 = localQueue.poll();
    Object localObject5 = this.terminalState;
    Object localObject1 = localObject4;
    if (localObject4 == null)
    {
      localObject1 = localObject4;
      if (localObject5 != null)
      {
        localObject1 = localObject4;
        if (localQueue.peek() == null) {
          break label77;
        }
      }
    }
    for (;;)
    {
      this.terminalState = null;
      return localObject1;
      if (localQueue != null) {
        break label15;
      }
      break;
      label77:
      Object localObject3 = localObject5;
    }
  }
  
  public void release()
  {
    for (;;)
    {
      Queue localQueue;
      ObjectPool localObjectPool;
      try
      {
        localQueue = this.queue;
        localObjectPool = this.pool;
      }
      finally {}
      localQueue.clear();
      this.queue = null;
      localObjectPool.returnObject(localQueue);
      return;
      if ((localObjectPool == null) || (localObject == null)) {}
    }
  }
  
  public void unsubscribe()
  {
    release();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\RxRingBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */