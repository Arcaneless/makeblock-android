package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Completable.CompletableOnSubscribe;
import rx.Completable.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

public final class CompletableOnSubscribeConcat
  implements Completable.CompletableOnSubscribe
{
  final int prefetch;
  final Observable<Completable> sources;
  
  public CompletableOnSubscribeConcat(Observable<? extends Completable> paramObservable, int paramInt)
  {
    this.sources = paramObservable;
    this.prefetch = paramInt;
  }
  
  public void call(Completable.CompletableSubscriber paramCompletableSubscriber)
  {
    CompletableConcatSubscriber localCompletableConcatSubscriber = new CompletableConcatSubscriber(paramCompletableSubscriber, this.prefetch);
    paramCompletableSubscriber.onSubscribe(localCompletableConcatSubscriber);
    this.sources.subscribe(localCompletableConcatSubscriber);
  }
  
  static final class CompletableConcatSubscriber
    extends Subscriber<Completable>
  {
    final Completable.CompletableSubscriber actual;
    volatile boolean done;
    final ConcatInnerSubscriber inner;
    final AtomicBoolean once;
    final int prefetch;
    final SpscArrayQueue<Completable> queue;
    final SerialSubscription sr;
    final AtomicInteger wip;
    
    public CompletableConcatSubscriber(Completable.CompletableSubscriber paramCompletableSubscriber, int paramInt)
    {
      this.actual = paramCompletableSubscriber;
      this.prefetch = paramInt;
      this.queue = new SpscArrayQueue(paramInt);
      this.sr = new SerialSubscription();
      this.inner = new ConcatInnerSubscriber();
      this.wip = new AtomicInteger();
      this.once = new AtomicBoolean();
      add(this.sr);
      request(paramInt);
    }
    
    void innerComplete()
    {
      if (this.wip.decrementAndGet() != 0) {
        next();
      }
      if (!this.done) {
        request(1L);
      }
    }
    
    void innerError(Throwable paramThrowable)
    {
      unsubscribe();
      onError(paramThrowable);
    }
    
    void next()
    {
      boolean bool = this.done;
      Completable localCompletable = (Completable)this.queue.poll();
      if (localCompletable == null)
      {
        if (bool)
        {
          if (this.once.compareAndSet(false, true)) {
            this.actual.onCompleted();
          }
          return;
        }
        RxJavaPlugins.getInstance().getErrorHandler().handleError(new IllegalStateException("Queue is empty?!"));
        return;
      }
      localCompletable.unsafeSubscribe(this.inner);
    }
    
    public void onCompleted()
    {
      if (this.done) {}
      do
      {
        return;
        this.done = true;
      } while (this.wip.getAndIncrement() != 0);
      next();
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.once.compareAndSet(false, true))
      {
        this.actual.onError(paramThrowable);
        return;
      }
      RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
    }
    
    public void onNext(Completable paramCompletable)
    {
      if (!this.queue.offer(paramCompletable)) {
        onError(new MissingBackpressureException());
      }
      while (this.wip.getAndIncrement() != 0) {
        return;
      }
      next();
    }
    
    final class ConcatInnerSubscriber
      implements Completable.CompletableSubscriber
    {
      ConcatInnerSubscriber() {}
      
      public void onCompleted()
      {
        CompletableOnSubscribeConcat.CompletableConcatSubscriber.this.innerComplete();
      }
      
      public void onError(Throwable paramThrowable)
      {
        CompletableOnSubscribeConcat.CompletableConcatSubscriber.this.innerError(paramThrowable);
      }
      
      public void onSubscribe(Subscription paramSubscription)
      {
        CompletableOnSubscribeConcat.CompletableConcatSubscriber.this.sr.set(paramSubscription);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\CompletableOnSubscribeConcat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */