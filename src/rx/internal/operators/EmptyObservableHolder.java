package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public enum EmptyObservableHolder
  implements Observable.OnSubscribe<Object>
{
  INSTANCE;
  
  static final Observable<Object> EMPTY = Observable.create(INSTANCE);
  
  private EmptyObservableHolder() {}
  
  public static <T> Observable<T> instance()
  {
    return EMPTY;
  }
  
  public void call(Subscriber<? super Object> paramSubscriber)
  {
    paramSubscriber.onCompleted();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\EmptyObservableHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */