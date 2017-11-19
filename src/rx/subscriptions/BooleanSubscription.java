package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.functions.Action0;

public final class BooleanSubscription
  implements Subscription
{
  static final Action0 EMPTY_ACTION = new Action0()
  {
    public void call() {}
  };
  final AtomicReference<Action0> actionRef;
  
  public BooleanSubscription()
  {
    this.actionRef = new AtomicReference();
  }
  
  private BooleanSubscription(Action0 paramAction0)
  {
    this.actionRef = new AtomicReference(paramAction0);
  }
  
  public static BooleanSubscription create()
  {
    return new BooleanSubscription();
  }
  
  public static BooleanSubscription create(Action0 paramAction0)
  {
    return new BooleanSubscription(paramAction0);
  }
  
  public boolean isUnsubscribed()
  {
    return this.actionRef.get() == EMPTY_ACTION;
  }
  
  public final void unsubscribe()
  {
    if ((Action0)this.actionRef.get() != EMPTY_ACTION)
    {
      Action0 localAction0 = (Action0)this.actionRef.getAndSet(EMPTY_ACTION);
      if ((localAction0 != null) && (localAction0 != EMPTY_ACTION)) {
        localAction0.call();
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\subscriptions\BooleanSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */