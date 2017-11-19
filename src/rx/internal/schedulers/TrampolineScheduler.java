package rx.internal.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

public final class TrampolineScheduler
  extends Scheduler
{
  public static final TrampolineScheduler INSTANCE = new TrampolineScheduler();
  
  static int compare(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 == paramInt2) {
      return 0;
    }
    return 1;
  }
  
  public Scheduler.Worker createWorker()
  {
    return new InnerCurrentThreadScheduler();
  }
  
  private static class InnerCurrentThreadScheduler
    extends Scheduler.Worker
    implements Subscription
  {
    final AtomicInteger counter = new AtomicInteger();
    private final BooleanSubscription innerSubscription = new BooleanSubscription();
    final PriorityBlockingQueue<TrampolineScheduler.TimedAction> queue = new PriorityBlockingQueue();
    private final AtomicInteger wip = new AtomicInteger();
    
    private Subscription enqueue(final Action0 paramAction0, long paramLong)
    {
      if (this.innerSubscription.isUnsubscribed()) {
        return Subscriptions.unsubscribed();
      }
      paramAction0 = new TrampolineScheduler.TimedAction(paramAction0, Long.valueOf(paramLong), this.counter.incrementAndGet());
      this.queue.add(paramAction0);
      if (this.wip.getAndIncrement() == 0)
      {
        do
        {
          paramAction0 = (TrampolineScheduler.TimedAction)this.queue.poll();
          if (paramAction0 != null) {
            paramAction0.action.call();
          }
        } while (this.wip.decrementAndGet() > 0);
        return Subscriptions.unsubscribed();
      }
      Subscriptions.create(new Action0()
      {
        public void call()
        {
          TrampolineScheduler.InnerCurrentThreadScheduler.this.queue.remove(paramAction0);
        }
      });
    }
    
    public boolean isUnsubscribed()
    {
      return this.innerSubscription.isUnsubscribed();
    }
    
    public Subscription schedule(Action0 paramAction0)
    {
      return enqueue(paramAction0, now());
    }
    
    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      paramLong = now() + paramTimeUnit.toMillis(paramLong);
      return enqueue(new SleepingAction(paramAction0, this, paramLong), paramLong);
    }
    
    public void unsubscribe()
    {
      this.innerSubscription.unsubscribe();
    }
  }
  
  private static final class TimedAction
    implements Comparable<TimedAction>
  {
    final Action0 action;
    final int count;
    final Long execTime;
    
    TimedAction(Action0 paramAction0, Long paramLong, int paramInt)
    {
      this.action = paramAction0;
      this.execTime = paramLong;
      this.count = paramInt;
    }
    
    public int compareTo(TimedAction paramTimedAction)
    {
      int j = this.execTime.compareTo(paramTimedAction.execTime);
      int i = j;
      if (j == 0) {
        i = TrampolineScheduler.compare(this.count, paramTimedAction.count);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\schedulers\TrampolineScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */