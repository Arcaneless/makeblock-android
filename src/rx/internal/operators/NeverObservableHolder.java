package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public enum NeverObservableHolder
  implements Observable.OnSubscribe<Object>
{
  INSTANCE;
  
  static final Observable<Object> NEVER = Observable.create(INSTANCE);
  
  private NeverObservableHolder() {}
  
  public static <T> Observable<T> instance()
  {
    return NEVER;
  }
  
  public void call(Subscriber<? super Object> paramSubscriber) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\NeverObservableHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */