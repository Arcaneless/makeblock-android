package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;

public final class OnSubscribeFromCallable<T>
  implements Observable.OnSubscribe<T>
{
  private final Callable<? extends T> resultFactory;
  
  public OnSubscribeFromCallable(Callable<? extends T> paramCallable)
  {
    this.resultFactory = paramCallable;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    try
    {
      localSingleDelayedProducer.setValue(this.resultFactory.call());
      return;
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwOrReport(localThrowable, paramSubscriber);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeFromCallable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */