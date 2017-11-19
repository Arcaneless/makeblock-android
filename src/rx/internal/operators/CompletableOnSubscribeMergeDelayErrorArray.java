package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.CompletableOnSubscribe;
import rx.Completable.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public final class CompletableOnSubscribeMergeDelayErrorArray
  implements Completable.CompletableOnSubscribe
{
  final Completable[] sources;
  
  public CompletableOnSubscribeMergeDelayErrorArray(Completable[] paramArrayOfCompletable)
  {
    this.sources = paramArrayOfCompletable;
  }
  
  public void call(final Completable.CompletableSubscriber paramCompletableSubscriber)
  {
    final CompositeSubscription localCompositeSubscription = new CompositeSubscription();
    final AtomicInteger localAtomicInteger = new AtomicInteger(this.sources.length + 1);
    final ConcurrentLinkedQueue localConcurrentLinkedQueue = new ConcurrentLinkedQueue();
    paramCompletableSubscriber.onSubscribe(localCompositeSubscription);
    Completable[] arrayOfCompletable = this.sources;
    int j = arrayOfCompletable.length;
    int i = 0;
    if (i < j)
    {
      localCompletable = arrayOfCompletable[i];
      if (!localCompositeSubscription.isUnsubscribed()) {}
    }
    while (localAtomicInteger.decrementAndGet() != 0)
    {
      Completable localCompletable;
      return;
      if (localCompletable == null)
      {
        localConcurrentLinkedQueue.offer(new NullPointerException("A completable source is null"));
        localAtomicInteger.decrementAndGet();
      }
      for (;;)
      {
        i += 1;
        break;
        localCompletable.unsafeSubscribe(new Completable.CompletableSubscriber()
        {
          public void onCompleted()
          {
            tryTerminate();
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            localConcurrentLinkedQueue.offer(paramAnonymousThrowable);
            tryTerminate();
          }
          
          public void onSubscribe(Subscription paramAnonymousSubscription)
          {
            localCompositeSubscription.add(paramAnonymousSubscription);
          }
          
          void tryTerminate()
          {
            if (localAtomicInteger.decrementAndGet() == 0)
            {
              if (localConcurrentLinkedQueue.isEmpty()) {
                paramCompletableSubscriber.onCompleted();
              }
            }
            else {
              return;
            }
            paramCompletableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(localConcurrentLinkedQueue));
          }
        });
      }
    }
    if (localConcurrentLinkedQueue.isEmpty())
    {
      paramCompletableSubscriber.onCompleted();
      return;
    }
    paramCompletableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(localConcurrentLinkedQueue));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\CompletableOnSubscribeMergeDelayErrorArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */