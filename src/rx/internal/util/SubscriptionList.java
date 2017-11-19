package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class SubscriptionList
  implements Subscription
{
  private LinkedList<Subscription> subscriptions;
  private volatile boolean unsubscribed;
  
  public SubscriptionList() {}
  
  public SubscriptionList(Subscription paramSubscription)
  {
    this.subscriptions = new LinkedList();
    this.subscriptions.add(paramSubscription);
  }
  
  public SubscriptionList(Subscription... paramVarArgs)
  {
    this.subscriptions = new LinkedList(Arrays.asList(paramVarArgs));
  }
  
  private static void unsubscribeFromAll(Collection<Subscription> paramCollection)
  {
    if (paramCollection == null) {
      return;
    }
    Object localObject = null;
    Iterator localIterator = paramCollection.iterator();
    paramCollection = (Collection<Subscription>)localObject;
    while (localIterator.hasNext())
    {
      localObject = (Subscription)localIterator.next();
      try
      {
        ((Subscription)localObject).unsubscribe();
      }
      catch (Throwable localThrowable)
      {
        localObject = paramCollection;
        if (paramCollection == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(localThrowable);
        paramCollection = (Collection<Subscription>)localObject;
      }
    }
    Exceptions.throwIfAny(paramCollection);
  }
  
  public void add(Subscription paramSubscription)
  {
    if (paramSubscription.isUnsubscribed()) {
      return;
    }
    if (!this.unsubscribed) {}
    for (;;)
    {
      LinkedList localLinkedList2;
      try
      {
        if (this.unsubscribed) {
          break label61;
        }
        localLinkedList2 = this.subscriptions;
      }
      finally {}
      LinkedList localLinkedList1 = new LinkedList();
      this.subscriptions = localLinkedList1;
      localLinkedList1.add(paramSubscription);
      return;
      label61:
      paramSubscription.unsubscribe();
      return;
      localLinkedList1 = localLinkedList2;
      if (localLinkedList2 != null) {}
    }
  }
  
  public void clear()
  {
    if (!this.unsubscribed) {}
    try
    {
      LinkedList localLinkedList = this.subscriptions;
      this.subscriptions = null;
      unsubscribeFromAll(localLinkedList);
      return;
    }
    finally {}
  }
  
  public boolean hasSubscriptions()
  {
    boolean bool2 = false;
    if (!this.unsubscribed)
    {
      boolean bool1 = bool2;
      try
      {
        if (!this.unsubscribed)
        {
          bool1 = bool2;
          if (this.subscriptions != null)
          {
            bool1 = bool2;
            if (!this.subscriptions.isEmpty()) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
      finally {}
    }
    return false;
  }
  
  public boolean isUnsubscribed()
  {
    return this.unsubscribed;
  }
  
  public void remove(Subscription paramSubscription)
  {
    if (!this.unsubscribed) {
      try
      {
        LinkedList localLinkedList = this.subscriptions;
        if ((this.unsubscribed) || (localLinkedList == null)) {
          return;
        }
        boolean bool = localLinkedList.remove(paramSubscription);
        if (bool)
        {
          paramSubscription.unsubscribe();
          return;
        }
      }
      finally {}
    }
  }
  
  public void unsubscribe()
  {
    if (!this.unsubscribed) {
      try
      {
        if (this.unsubscribed) {
          return;
        }
        this.unsubscribed = true;
        LinkedList localLinkedList = this.subscriptions;
        this.subscriptions = null;
        unsubscribeFromAll(localLinkedList);
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\SubscriptionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */