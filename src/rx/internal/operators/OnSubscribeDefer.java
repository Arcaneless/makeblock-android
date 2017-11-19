package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;

public final class OnSubscribeDefer<T>
  implements Observable.OnSubscribe<T>
{
  final Func0<? extends Observable<? extends T>> observableFactory;
  
  public OnSubscribeDefer(Func0<? extends Observable<? extends T>> paramFunc0)
  {
    this.observableFactory = paramFunc0;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      Observable localObservable = (Observable)this.observableFactory.call();
      localObservable.unsafeSubscribe(Subscribers.wrap(paramSubscriber));
      return;
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwOrReport(localThrowable, paramSubscriber);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeDefer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */