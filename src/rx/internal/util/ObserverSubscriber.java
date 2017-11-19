package rx.internal.util;

import rx.Observer;
import rx.Subscriber;

public final class ObserverSubscriber<T>
  extends Subscriber<T>
{
  final Observer<? super T> observer;
  
  public ObserverSubscriber(Observer<? super T> paramObserver)
  {
    this.observer = paramObserver;
  }
  
  public void onCompleted()
  {
    this.observer.onCompleted();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.observer.onError(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.observer.onNext(paramT);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\ObserverSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */