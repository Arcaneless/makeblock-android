package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorDoAfterTerminate<T>
  implements Observable.Operator<T, T>
{
  final Action0 action;
  
  public OperatorDoAfterTerminate(Action0 paramAction0)
  {
    if (paramAction0 == null) {
      throw new NullPointerException("Action can not be null");
    }
    this.action = paramAction0;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      void callAction()
      {
        try
        {
          OperatorDoAfterTerminate.this.action.call();
          return;
        }
        catch (Throwable localThrowable)
        {
          Exceptions.throwIfFatal(localThrowable);
          RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable);
        }
      }
      
      public void onCompleted()
      {
        try
        {
          paramSubscriber.onCompleted();
          return;
        }
        finally
        {
          callAction();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        try
        {
          paramSubscriber.onError(paramAnonymousThrowable);
          return;
        }
        finally
        {
          callAction();
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        paramSubscriber.onNext(paramAnonymousT);
      }
    };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorDoAfterTerminate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */