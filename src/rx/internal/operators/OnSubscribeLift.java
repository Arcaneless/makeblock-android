package rx.internal.operators;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

public final class OnSubscribeLift<T, R>
  implements Observable.OnSubscribe<R>
{
  static final RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
  final Observable.Operator<? extends R, ? super T> operator;
  final Observable.OnSubscribe<T> parent;
  
  public OnSubscribeLift(Observable.OnSubscribe<T> paramOnSubscribe, Observable.Operator<? extends R, ? super T> paramOperator)
  {
    this.parent = paramOnSubscribe;
    this.operator = paramOperator;
  }
  
  public void call(Subscriber<? super R> paramSubscriber)
  {
    try
    {
      Subscriber localSubscriber = (Subscriber)hook.onLift(this.operator).call(paramSubscriber);
      try
      {
        localSubscriber.onStart();
        this.parent.call(localSubscriber);
        return;
      }
      catch (Throwable localThrowable2)
      {
        Exceptions.throwIfFatal(localThrowable2);
        localSubscriber.onError(localThrowable2);
        return;
      }
      return;
    }
    catch (Throwable localThrowable1)
    {
      Exceptions.throwIfFatal(localThrowable1);
      paramSubscriber.onError(localThrowable1);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeLift.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */