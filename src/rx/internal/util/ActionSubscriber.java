package rx.internal.util;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public final class ActionSubscriber<T>
  extends Subscriber<T>
{
  final Action0 onCompleted;
  final Action1<Throwable> onError;
  final Action1<? super T> onNext;
  
  public ActionSubscriber(Action1<? super T> paramAction1, Action1<Throwable> paramAction11, Action0 paramAction0)
  {
    this.onNext = paramAction1;
    this.onError = paramAction11;
    this.onCompleted = paramAction0;
  }
  
  public void onCompleted()
  {
    this.onCompleted.call();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.onError.call(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.onNext.call(paramT);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\ActionSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */