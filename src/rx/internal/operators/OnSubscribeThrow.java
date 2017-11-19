package rx.internal.operators;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

public final class OnSubscribeThrow<T>
  implements Observable.OnSubscribe<T>
{
  private final Throwable exception;
  
  public OnSubscribeThrow(Throwable paramThrowable)
  {
    this.exception = paramThrowable;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    paramSubscriber.onError(this.exception);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeThrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */