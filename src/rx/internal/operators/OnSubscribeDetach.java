package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.RxJavaPluginUtils;

public final class OnSubscribeDetach<T>
  implements Observable.OnSubscribe<T>
{
  final Observable<T> source;
  
  public OnSubscribeDetach(Observable<T> paramObservable)
  {
    this.source = paramObservable;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    DetachSubscriber localDetachSubscriber = new DetachSubscriber(paramSubscriber);
    DetachProducer localDetachProducer = new DetachProducer(localDetachSubscriber);
    paramSubscriber.add(localDetachProducer);
    paramSubscriber.setProducer(localDetachProducer);
    this.source.unsafeSubscribe(localDetachSubscriber);
  }
  
  static final class DetachProducer<T>
    implements Producer, Subscription
  {
    final OnSubscribeDetach.DetachSubscriber<T> parent;
    
    public DetachProducer(OnSubscribeDetach.DetachSubscriber<T> paramDetachSubscriber)
    {
      this.parent = paramDetachSubscriber;
    }
    
    public boolean isUnsubscribed()
    {
      return this.parent.isUnsubscribed();
    }
    
    public void request(long paramLong)
    {
      this.parent.innerRequest(paramLong);
    }
    
    public void unsubscribe()
    {
      this.parent.innerUnsubscribe();
    }
  }
  
  static final class DetachSubscriber<T>
    extends Subscriber<T>
  {
    final AtomicReference<Subscriber<? super T>> actual;
    final AtomicReference<Producer> producer;
    final AtomicLong requested;
    
    public DetachSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.actual = new AtomicReference(paramSubscriber);
      this.producer = new AtomicReference();
      this.requested = new AtomicLong();
    }
    
    void innerRequest(long paramLong)
    {
      if (paramLong < 0L) {
        throw new IllegalArgumentException("n >= 0 required but it was " + paramLong);
      }
      Producer localProducer = (Producer)this.producer.get();
      if (localProducer != null) {
        localProducer.request(paramLong);
      }
      do
      {
        return;
        BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        localProducer = (Producer)this.producer.get();
      } while ((localProducer == null) || (localProducer == OnSubscribeDetach.TerminatedProducer.INSTANCE));
      localProducer.request(this.requested.getAndSet(0L));
    }
    
    void innerUnsubscribe()
    {
      this.producer.lazySet(OnSubscribeDetach.TerminatedProducer.INSTANCE);
      this.actual.lazySet(null);
      unsubscribe();
    }
    
    public void onCompleted()
    {
      this.producer.lazySet(OnSubscribeDetach.TerminatedProducer.INSTANCE);
      Subscriber localSubscriber = (Subscriber)this.actual.getAndSet(null);
      if (localSubscriber != null) {
        localSubscriber.onCompleted();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.producer.lazySet(OnSubscribeDetach.TerminatedProducer.INSTANCE);
      Subscriber localSubscriber = (Subscriber)this.actual.getAndSet(null);
      if (localSubscriber != null)
      {
        localSubscriber.onError(paramThrowable);
        return;
      }
      RxJavaPluginUtils.handleException(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      Subscriber localSubscriber = (Subscriber)this.actual.get();
      if (localSubscriber != null) {
        localSubscriber.onNext(paramT);
      }
    }
    
    public void setProducer(Producer paramProducer)
    {
      if (this.producer.compareAndSet(null, paramProducer)) {
        paramProducer.request(this.requested.getAndSet(0L));
      }
      while (this.producer.get() == OnSubscribeDetach.TerminatedProducer.INSTANCE) {
        return;
      }
      throw new IllegalStateException("Producer already set!");
    }
  }
  
  static enum TerminatedProducer
    implements Producer
  {
    INSTANCE;
    
    private TerminatedProducer() {}
    
    public void request(long paramLong) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeDetach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */