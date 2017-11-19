package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.TimeInterval;

public final class OperatorTimeInterval<T>
  implements Observable.Operator<TimeInterval<T>, T>
{
  final Scheduler scheduler;
  
  public OperatorTimeInterval(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super TimeInterval<T>> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      private long lastTimestamp = OperatorTimeInterval.this.scheduler.now();
      
      public void onCompleted()
      {
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        long l = OperatorTimeInterval.this.scheduler.now();
        paramSubscriber.onNext(new TimeInterval(l - this.lastTimestamp, paramAnonymousT));
        this.lastTimestamp = l;
      }
    };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorTimeInterval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */