package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.producers.SingleProducer;
import rx.subscriptions.Subscriptions;

public final class OnSubscribeToObservableFuture
{
  private OnSubscribeToObservableFuture()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> paramFuture)
  {
    return new ToObservableFuture(paramFuture);
  }
  
  public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
  {
    return new ToObservableFuture(paramFuture, paramLong, paramTimeUnit);
  }
  
  static class ToObservableFuture<T>
    implements Observable.OnSubscribe<T>
  {
    final Future<? extends T> that;
    private final long time;
    private final TimeUnit unit;
    
    public ToObservableFuture(Future<? extends T> paramFuture)
    {
      this.that = paramFuture;
      this.time = 0L;
      this.unit = null;
    }
    
    public ToObservableFuture(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
    {
      this.that = paramFuture;
      this.time = paramLong;
      this.unit = paramTimeUnit;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      paramSubscriber.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OnSubscribeToObservableFuture.ToObservableFuture.this.that.cancel(true);
        }
      }));
      try
      {
        if (paramSubscriber.isUnsubscribed()) {
          return;
        }
        if (this.unit != null) {
          break label68;
        }
        localObject1 = this.that.get();
      }
      catch (Throwable localThrowable)
      {
        Object localObject1;
        while (!paramSubscriber.isUnsubscribed())
        {
          Exceptions.throwOrReport(localThrowable, paramSubscriber);
          return;
          Object localObject2 = this.that.get(this.time, this.unit);
        }
      }
      paramSubscriber.setProducer(new SingleProducer(paramSubscriber, localObject1));
      return;
      label68:
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeToObservableFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */