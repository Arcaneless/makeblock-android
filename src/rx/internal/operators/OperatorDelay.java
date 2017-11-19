package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorDelay<T>
  implements Observable.Operator<T, T>
{
  final long delay;
  final Scheduler scheduler;
  final TimeUnit unit;
  
  public OperatorDelay(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.delay = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    final Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    new Subscriber(paramSubscriber)
    {
      boolean done;
      
      public void onCompleted()
      {
        localWorker.schedule(new Action0()
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done)
            {
              OperatorDelay.1.this.done = true;
              OperatorDelay.1.this.val$child.onCompleted();
            }
          }
        }, OperatorDelay.this.delay, OperatorDelay.this.unit);
      }
      
      public void onError(final Throwable paramAnonymousThrowable)
      {
        localWorker.schedule(new Action0()
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done)
            {
              OperatorDelay.1.this.done = true;
              OperatorDelay.1.this.val$child.onError(paramAnonymousThrowable);
              OperatorDelay.1.this.val$worker.unsubscribe();
            }
          }
        });
      }
      
      public void onNext(final T paramAnonymousT)
      {
        localWorker.schedule(new Action0()
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done) {
              OperatorDelay.1.this.val$child.onNext(paramAnonymousT);
            }
          }
        }, OperatorDelay.this.delay, OperatorDelay.this.unit);
      }
    };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorDelay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */