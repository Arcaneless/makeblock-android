package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

class OperatorTimeoutBase<T>
  implements Observable.Operator<T, T>
{
  final FirstTimeoutStub<T> firstTimeoutStub;
  final Observable<? extends T> other;
  final Scheduler scheduler;
  final TimeoutStub<T> timeoutStub;
  
  OperatorTimeoutBase(FirstTimeoutStub<T> paramFirstTimeoutStub, TimeoutStub<T> paramTimeoutStub, Observable<? extends T> paramObservable, Scheduler paramScheduler)
  {
    this.firstTimeoutStub = paramFirstTimeoutStub;
    this.timeoutStub = paramTimeoutStub;
    this.other = paramObservable;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    paramSubscriber = new SerializedSubscriber(paramSubscriber);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    TimeoutSubscriber localTimeoutSubscriber = new TimeoutSubscriber(paramSubscriber, this.timeoutStub, localSerialSubscription, this.other, localWorker);
    paramSubscriber.add(localTimeoutSubscriber);
    paramSubscriber.setProducer(localTimeoutSubscriber.arbiter);
    localSerialSubscription.set((Subscription)this.firstTimeoutStub.call(localTimeoutSubscriber, Long.valueOf(0L), localWorker));
    return localTimeoutSubscriber;
  }
  
  static abstract interface FirstTimeoutStub<T>
    extends Func3<OperatorTimeoutBase.TimeoutSubscriber<T>, Long, Scheduler.Worker, Subscription>
  {}
  
  static abstract interface TimeoutStub<T>
    extends Func4<OperatorTimeoutBase.TimeoutSubscriber<T>, Long, T, Scheduler.Worker, Subscription>
  {}
  
  static final class TimeoutSubscriber<T>
    extends Subscriber<T>
  {
    long actual;
    final ProducerArbiter arbiter;
    final Scheduler.Worker inner;
    final Observable<? extends T> other;
    final SerialSubscription serial;
    final SerializedSubscriber<T> serializedSubscriber;
    boolean terminated;
    final OperatorTimeoutBase.TimeoutStub<T> timeoutStub;
    
    TimeoutSubscriber(SerializedSubscriber<T> paramSerializedSubscriber, OperatorTimeoutBase.TimeoutStub<T> paramTimeoutStub, SerialSubscription paramSerialSubscription, Observable<? extends T> paramObservable, Scheduler.Worker paramWorker)
    {
      this.serializedSubscriber = paramSerializedSubscriber;
      this.timeoutStub = paramTimeoutStub;
      this.serial = paramSerialSubscription;
      this.other = paramObservable;
      this.inner = paramWorker;
      this.arbiter = new ProducerArbiter();
    }
    
    public void onCompleted()
    {
      int i = 0;
      for (;;)
      {
        try
        {
          if (!this.terminated)
          {
            this.terminated = true;
          }
          else
          {
            if (i != 0)
            {
              this.serial.unsubscribe();
              this.serializedSubscriber.onCompleted();
            }
            return;
          }
        }
        finally {}
        i = 1;
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      int i = 0;
      for (;;)
      {
        try
        {
          if (!this.terminated)
          {
            this.terminated = true;
          }
          else
          {
            if (i != 0)
            {
              this.serial.unsubscribe();
              this.serializedSubscriber.onError(paramThrowable);
            }
            return;
          }
        }
        finally {}
        i = 1;
      }
    }
    
    public void onNext(T paramT)
    {
      int i = 0;
      for (;;)
      {
        try
        {
          long l;
          if (!this.terminated)
          {
            l = this.actual + 1L;
            this.actual = l;
            break label83;
            if (i != 0)
            {
              this.serializedSubscriber.onNext(paramT);
              this.serial.set((Subscription)this.timeoutStub.call(this, Long.valueOf(l), paramT, this.inner));
            }
          }
          else
          {
            l = this.actual;
          }
        }
        finally {}
        label83:
        i = 1;
      }
    }
    
    public void onTimeout(long paramLong)
    {
      int j = 0;
      for (int i = j;; i = 1)
      {
        try
        {
          if (paramLong == this.actual)
          {
            i = j;
            if (!this.terminated)
            {
              this.terminated = true;
              continue;
            }
          }
          if (i != 0)
          {
            if (this.other == null) {
              this.serializedSubscriber.onError(new TimeoutException());
            }
          }
          else {
            return;
          }
        }
        finally {}
        Subscriber local1 = new Subscriber()
        {
          public void onCompleted()
          {
            OperatorTimeoutBase.TimeoutSubscriber.this.serializedSubscriber.onCompleted();
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            OperatorTimeoutBase.TimeoutSubscriber.this.serializedSubscriber.onError(paramAnonymousThrowable);
          }
          
          public void onNext(T paramAnonymousT)
          {
            OperatorTimeoutBase.TimeoutSubscriber.this.serializedSubscriber.onNext(paramAnonymousT);
          }
          
          public void setProducer(Producer paramAnonymousProducer)
          {
            OperatorTimeoutBase.TimeoutSubscriber.this.arbiter.setProducer(paramAnonymousProducer);
          }
        };
        this.other.unsafeSubscribe(local1);
        this.serial.set(local1);
        return;
      }
    }
    
    public void setProducer(Producer paramProducer)
    {
      this.arbiter.setProducer(paramProducer);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorTimeoutBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */