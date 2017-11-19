package rx.observers;

import rx.Completable.CompletableSubscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.internal.util.RxJavaPluginUtils;

@Experimental
public final class SafeCompletableSubscriber
  implements Completable.CompletableSubscriber, Subscription
{
  final Completable.CompletableSubscriber actual;
  boolean done;
  Subscription s;
  
  public SafeCompletableSubscriber(Completable.CompletableSubscriber paramCompletableSubscriber)
  {
    this.actual = paramCompletableSubscriber;
  }
  
  public boolean isUnsubscribed()
  {
    return (this.done) || (this.s.isUnsubscribed());
  }
  
  public void onCompleted()
  {
    if (this.done) {
      return;
    }
    this.done = true;
    try
    {
      this.actual.onCompleted();
      return;
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwIfFatal(localThrowable);
      throw new OnCompletedFailedException(localThrowable);
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    RxJavaPluginUtils.handleException(paramThrowable);
    if (this.done) {
      return;
    }
    this.done = true;
    try
    {
      this.actual.onError(paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwIfFatal(localThrowable);
      throw new OnErrorFailedException(new CompositeException(new Throwable[] { paramThrowable, localThrowable }));
    }
  }
  
  public void onSubscribe(Subscription paramSubscription)
  {
    this.s = paramSubscription;
    try
    {
      this.actual.onSubscribe(this);
      return;
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwIfFatal(localThrowable);
      paramSubscription.unsubscribe();
      onError(localThrowable);
    }
  }
  
  public void unsubscribe()
  {
    this.s.unsubscribe();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observers\SafeCompletableSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */