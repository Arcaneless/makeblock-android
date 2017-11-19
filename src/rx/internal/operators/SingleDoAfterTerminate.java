package rx.internal.operators;

import rx.Single;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.util.RxJavaPluginUtils;

public final class SingleDoAfterTerminate<T>
  implements Single.OnSubscribe<T>
{
  final Action0 action;
  final Single<T> source;
  
  public SingleDoAfterTerminate(Single<T> paramSingle, Action0 paramAction0)
  {
    this.source = paramSingle;
    this.action = paramAction0;
  }
  
  public void call(SingleSubscriber<? super T> paramSingleSubscriber)
  {
    SingleDoAfterTerminateSubscriber localSingleDoAfterTerminateSubscriber = new SingleDoAfterTerminateSubscriber(paramSingleSubscriber, this.action);
    paramSingleSubscriber.add(localSingleDoAfterTerminateSubscriber);
    this.source.subscribe(localSingleDoAfterTerminateSubscriber);
  }
  
  static final class SingleDoAfterTerminateSubscriber<T>
    extends SingleSubscriber<T>
  {
    final Action0 action;
    final SingleSubscriber<? super T> actual;
    
    public SingleDoAfterTerminateSubscriber(SingleSubscriber<? super T> paramSingleSubscriber, Action0 paramAction0)
    {
      this.actual = paramSingleSubscriber;
      this.action = paramAction0;
    }
    
    void doAction()
    {
      try
      {
        this.action.call();
        return;
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        RxJavaPluginUtils.handleException(localThrowable);
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      try
      {
        this.actual.onError(paramThrowable);
        return;
      }
      finally
      {
        doAction();
      }
    }
    
    public void onSuccess(T paramT)
    {
      try
      {
        this.actual.onSuccess(paramT);
        return;
      }
      finally
      {
        doAction();
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\SingleDoAfterTerminate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */