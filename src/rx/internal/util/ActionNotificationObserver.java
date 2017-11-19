package rx.internal.util;

import rx.Notification;
import rx.Observer;
import rx.functions.Action1;

public final class ActionNotificationObserver<T>
  implements Observer<T>
{
  final Action1<Notification<? super T>> onNotification;
  
  public ActionNotificationObserver(Action1<Notification<? super T>> paramAction1)
  {
    this.onNotification = paramAction1;
  }
  
  public void onCompleted()
  {
    this.onNotification.call(Notification.createOnCompleted());
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.onNotification.call(Notification.createOnError(paramThrowable));
  }
  
  public void onNext(T paramT)
  {
    this.onNotification.call(Notification.createOnNext(paramT));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\ActionNotificationObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */