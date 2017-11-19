package rx.plugins;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Single;
import rx.Single.OnSubscribe;
import rx.Subscription;

public abstract class RxJavaSingleExecutionHook
{
  public <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> paramOnSubscribe)
  {
    return paramOnSubscribe;
  }
  
  public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> paramOperator)
  {
    return paramOperator;
  }
  
  public <T> Throwable onSubscribeError(Throwable paramThrowable)
  {
    return paramThrowable;
  }
  
  public <T> Subscription onSubscribeReturn(Subscription paramSubscription)
  {
    return paramSubscription;
  }
  
  public <T> Observable.OnSubscribe<T> onSubscribeStart(Single<? extends T> paramSingle, Observable.OnSubscribe<T> paramOnSubscribe)
  {
    return paramOnSubscribe;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\plugins\RxJavaSingleExecutionHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */