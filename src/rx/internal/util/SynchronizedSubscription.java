package rx.internal.util;

import rx.Subscription;

public class SynchronizedSubscription
  implements Subscription
{
  private final Subscription s;
  
  public SynchronizedSubscription(Subscription paramSubscription)
  {
    this.s = paramSubscription;
  }
  
  public boolean isUnsubscribed()
  {
    try
    {
      boolean bool = this.s.isUnsubscribed();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void unsubscribe()
  {
    try
    {
      this.s.unsubscribe();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\SynchronizedSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */