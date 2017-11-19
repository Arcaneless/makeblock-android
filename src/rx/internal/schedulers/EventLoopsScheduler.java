package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public final class EventLoopsScheduler
  extends Scheduler
  implements SchedulerLifecycle
{
  static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
  static final int MAX_THREADS;
  static final FixedSchedulerPool NONE;
  static final PoolWorker SHUTDOWN_WORKER;
  final AtomicReference<FixedSchedulerPool> pool;
  final ThreadFactory threadFactory;
  
  static
  {
    int i = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
    int j = Runtime.getRuntime().availableProcessors();
    if ((i <= 0) || (i > j)) {
      i = j;
    }
    for (;;)
    {
      MAX_THREADS = i;
      SHUTDOWN_WORKER = new PoolWorker(RxThreadFactory.NONE);
      SHUTDOWN_WORKER.unsubscribe();
      NONE = new FixedSchedulerPool(null, 0);
      return;
    }
  }
  
  public EventLoopsScheduler(ThreadFactory paramThreadFactory)
  {
    this.threadFactory = paramThreadFactory;
    this.pool = new AtomicReference(NONE);
    start();
  }
  
  public Scheduler.Worker createWorker()
  {
    return new EventLoopWorker(((FixedSchedulerPool)this.pool.get()).getEventLoop());
  }
  
  public Subscription scheduleDirect(Action0 paramAction0)
  {
    return ((FixedSchedulerPool)this.pool.get()).getEventLoop().scheduleActual(paramAction0, -1L, TimeUnit.NANOSECONDS);
  }
  
  public void shutdown()
  {
    FixedSchedulerPool localFixedSchedulerPool;
    do
    {
      localFixedSchedulerPool = (FixedSchedulerPool)this.pool.get();
      if (localFixedSchedulerPool == NONE) {
        return;
      }
    } while (!this.pool.compareAndSet(localFixedSchedulerPool, NONE));
    localFixedSchedulerPool.shutdown();
  }
  
  public void start()
  {
    FixedSchedulerPool localFixedSchedulerPool = new FixedSchedulerPool(this.threadFactory, MAX_THREADS);
    if (!this.pool.compareAndSet(NONE, localFixedSchedulerPool)) {
      localFixedSchedulerPool.shutdown();
    }
  }
  
  private static class EventLoopWorker
    extends Scheduler.Worker
  {
    private final SubscriptionList both = new SubscriptionList(new Subscription[] { this.serial, this.timed });
    private final EventLoopsScheduler.PoolWorker poolWorker;
    private final SubscriptionList serial = new SubscriptionList();
    private final CompositeSubscription timed = new CompositeSubscription();
    
    EventLoopWorker(EventLoopsScheduler.PoolWorker paramPoolWorker)
    {
      this.poolWorker = paramPoolWorker;
    }
    
    public boolean isUnsubscribed()
    {
      return this.both.isUnsubscribed();
    }
    
    public Subscription schedule(final Action0 paramAction0)
    {
      if (isUnsubscribed()) {
        return Subscriptions.unsubscribed();
      }
      this.poolWorker.scheduleActual(new Action0()
      {
        public void call()
        {
          if (EventLoopsScheduler.EventLoopWorker.this.isUnsubscribed()) {
            return;
          }
          paramAction0.call();
        }
      }, 0L, null, this.serial);
    }
    
    public Subscription schedule(final Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (isUnsubscribed()) {
        return Subscriptions.unsubscribed();
      }
      this.poolWorker.scheduleActual(new Action0()
      {
        public void call()
        {
          if (EventLoopsScheduler.EventLoopWorker.this.isUnsubscribed()) {
            return;
          }
          paramAction0.call();
        }
      }, paramLong, paramTimeUnit, this.timed);
    }
    
    public void unsubscribe()
    {
      this.both.unsubscribe();
    }
  }
  
  static final class FixedSchedulerPool
  {
    final int cores;
    final EventLoopsScheduler.PoolWorker[] eventLoops;
    long n;
    
    FixedSchedulerPool(ThreadFactory paramThreadFactory, int paramInt)
    {
      this.cores = paramInt;
      this.eventLoops = new EventLoopsScheduler.PoolWorker[paramInt];
      int i = 0;
      while (i < paramInt)
      {
        this.eventLoops[i] = new EventLoopsScheduler.PoolWorker(paramThreadFactory);
        i += 1;
      }
    }
    
    public EventLoopsScheduler.PoolWorker getEventLoop()
    {
      int i = this.cores;
      if (i == 0) {
        return EventLoopsScheduler.SHUTDOWN_WORKER;
      }
      EventLoopsScheduler.PoolWorker[] arrayOfPoolWorker = this.eventLoops;
      long l = this.n;
      this.n = (1L + l);
      return arrayOfPoolWorker[((int)(l % i))];
    }
    
    public void shutdown()
    {
      EventLoopsScheduler.PoolWorker[] arrayOfPoolWorker = this.eventLoops;
      int j = arrayOfPoolWorker.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPoolWorker[i].unsubscribe();
        i += 1;
      }
    }
  }
  
  static final class PoolWorker
    extends NewThreadWorker
  {
    PoolWorker(ThreadFactory paramThreadFactory)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\schedulers\EventLoopsScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */