package rx;

import rx.internal.util.SubscriptionList;

public abstract class Subscriber<T>
  implements Observer<T>, Subscription
{
  private static final long NOT_SET = Long.MIN_VALUE;
  private Producer producer;
  private long requested = Long.MIN_VALUE;
  private final Subscriber<?> subscriber;
  private final SubscriptionList subscriptions;
  
  protected Subscriber()
  {
    this(null, false);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber)
  {
    this(paramSubscriber, true);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber, boolean paramBoolean)
  {
    this.subscriber = paramSubscriber;
    if ((paramBoolean) && (paramSubscriber != null)) {}
    for (paramSubscriber = paramSubscriber.subscriptions;; paramSubscriber = new SubscriptionList())
    {
      this.subscriptions = paramSubscriber;
      return;
    }
  }
  
  private void addToRequested(long paramLong)
  {
    if (this.requested == Long.MIN_VALUE)
    {
      this.requested = paramLong;
      return;
    }
    paramLong = this.requested + paramLong;
    if (paramLong < 0L)
    {
      this.requested = Long.MAX_VALUE;
      return;
    }
    this.requested = paramLong;
  }
  
  public final void add(Subscription paramSubscription)
  {
    this.subscriptions.add(paramSubscription);
  }
  
  public final boolean isUnsubscribed()
  {
    return this.subscriptions.isUnsubscribed();
  }
  
  public void onStart() {}
  
  protected final void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("number requested cannot be negative: " + paramLong);
    }
    try
    {
      if (this.producer != null)
      {
        Producer localProducer = this.producer;
        localProducer.request(paramLong);
        return;
      }
      addToRequested(paramLong);
      return;
    }
    finally {}
  }
  
  public void setProducer(Producer paramProducer)
  {
    int j = 0;
    for (;;)
    {
      long l;
      try
      {
        l = this.requested;
        this.producer = paramProducer;
        i = j;
        if (this.subscriber != null) {
          break label84;
        }
        if (i != 0)
        {
          this.subscriber.setProducer(this.producer);
          return;
        }
      }
      finally {}
      if (l == Long.MIN_VALUE)
      {
        this.producer.request(Long.MAX_VALUE);
        return;
      }
      this.producer.request(l);
      return;
      label84:
      int i = j;
      if (l == Long.MIN_VALUE) {
        i = 1;
      }
    }
  }
  
  public final void unsubscribe()
  {
    this.subscriptions.unsubscribe();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\Subscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */