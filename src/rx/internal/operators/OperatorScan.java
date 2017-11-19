package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public final class OperatorScan<R, T>
  implements Observable.Operator<R, T>
{
  private static final Object NO_INITIAL_VALUE = new Object();
  final Func2<R, ? super T, R> accumulator;
  private final Func0<R> initialValueFactory;
  
  public OperatorScan(R paramR, Func2<R, ? super T, R> paramFunc2)
  {
    this(new Func0()
    {
      public R call()
      {
        return OperatorScan.this;
      }
    }, paramFunc2);
  }
  
  public OperatorScan(Func0<R> paramFunc0, Func2<R, ? super T, R> paramFunc2)
  {
    this.initialValueFactory = paramFunc0;
    this.accumulator = paramFunc2;
  }
  
  public OperatorScan(Func2<R, ? super T, R> paramFunc2)
  {
    this(NO_INITIAL_VALUE, paramFunc2);
  }
  
  public Subscriber<? super T> call(final Subscriber<? super R> paramSubscriber)
  {
    final Object localObject = this.initialValueFactory.call();
    if (localObject == NO_INITIAL_VALUE) {
      new Subscriber(paramSubscriber)
      {
        boolean once;
        R value;
        
        public void onCompleted()
        {
          paramSubscriber.onCompleted();
        }
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          paramSubscriber.onError(paramAnonymousThrowable);
        }
        
        public void onNext(T paramAnonymousT)
        {
          if (!this.once) {
            this.once = true;
          }
          for (;;)
          {
            this.value = paramAnonymousT;
            paramSubscriber.onNext(paramAnonymousT);
            return;
            Object localObject = this.value;
            try
            {
              localObject = OperatorScan.this.accumulator.call(localObject, paramAnonymousT);
              paramAnonymousT = (T)localObject;
            }
            catch (Throwable localThrowable)
            {
              Exceptions.throwOrReport(localThrowable, paramSubscriber, paramAnonymousT);
            }
          }
        }
      };
    }
    final InitialProducer localInitialProducer = new InitialProducer(localObject, paramSubscriber);
    localObject = new Subscriber()
    {
      private R value = localObject;
      
      public void onCompleted()
      {
        localInitialProducer.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localInitialProducer.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        Object localObject = this.value;
        try
        {
          localObject = OperatorScan.this.accumulator.call(localObject, paramAnonymousT);
          this.value = localObject;
          localInitialProducer.onNext(localObject);
          return;
        }
        catch (Throwable localThrowable)
        {
          Exceptions.throwOrReport(localThrowable, this, paramAnonymousT);
        }
      }
      
      public void setProducer(Producer paramAnonymousProducer)
      {
        localInitialProducer.setProducer(paramAnonymousProducer);
      }
    };
    paramSubscriber.add((Subscription)localObject);
    paramSubscriber.setProducer(localInitialProducer);
    return (Subscriber<? super T>)localObject;
  }
  
  static final class InitialProducer<R>
    implements Producer, Observer<R>
  {
    final Subscriber<? super R> child;
    volatile boolean done;
    boolean emitting;
    Throwable error;
    boolean missed;
    long missedRequested;
    volatile Producer producer;
    final Queue<Object> queue;
    final AtomicLong requested;
    
    public InitialProducer(R paramR, Subscriber<? super R> paramSubscriber)
    {
      this.child = paramSubscriber;
      if (UnsafeAccess.isUnsafeAvailable()) {}
      for (paramSubscriber = new SpscLinkedQueue();; paramSubscriber = new SpscLinkedAtomicQueue())
      {
        this.queue = paramSubscriber;
        paramSubscriber.offer(NotificationLite.instance().next(paramR));
        this.requested = new AtomicLong();
        return;
      }
    }
    
    boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2, Subscriber<? super R> paramSubscriber)
    {
      if (paramSubscriber.isUnsubscribed()) {
        return true;
      }
      if (paramBoolean1)
      {
        Throwable localThrowable = this.error;
        if (localThrowable != null)
        {
          paramSubscriber.onError(localThrowable);
          return true;
        }
        if (paramBoolean2)
        {
          paramSubscriber.onCompleted();
          return true;
        }
      }
      return false;
    }
    
    void emit()
    {
      try
      {
        if (this.emitting)
        {
          this.missed = true;
          return;
        }
        this.emitting = true;
        emitLoop();
        return;
      }
      finally {}
    }
    
    void emitLoop()
    {
      Subscriber localSubscriber = this.child;
      Queue localQueue = this.queue;
      NotificationLite localNotificationLite = NotificationLite.instance();
      AtomicLong localAtomicLong = this.requested;
      long l2;
      for (long l1 = localAtomicLong.get();; l1 = l2)
      {
        if (l1 == Long.MAX_VALUE) {}
        for (int i = 1; checkTerminated(this.done, localQueue.isEmpty(), localSubscriber); i = 0) {
          return;
        }
        long l3 = 0L;
        for (;;)
        {
          boolean bool2;
          if (l1 != 0L)
          {
            bool2 = this.done;
            localObject = localQueue.poll();
            if (localObject != null) {
              break label165;
            }
          }
          label165:
          for (boolean bool1 = true; !checkTerminated(bool2, bool1, localSubscriber); bool1 = false)
          {
            if (!bool1) {
              break label171;
            }
            l2 = l1;
            if (l3 != 0L)
            {
              l2 = l1;
              if (i == 0) {
                l2 = localAtomicLong.addAndGet(l3);
              }
            }
            try
            {
              if (this.missed) {
                break label212;
              }
              this.emitting = false;
              return;
            }
            finally {}
          }
          break;
          label171:
          Object localObject = localNotificationLite.getValue(localObject);
          try
          {
            localObserver.onNext(localObject);
            l1 -= 1L;
            l3 -= 1L;
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, localObserver, localObject);
            return;
          }
        }
        label212:
        this.missed = false;
      }
    }
    
    public void onCompleted()
    {
      this.done = true;
      emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.error = paramThrowable;
      this.done = true;
      emit();
    }
    
    public void onNext(R paramR)
    {
      this.queue.offer(NotificationLite.instance().next(paramR));
      emit();
    }
    
    public void request(long paramLong)
    {
      if (paramLong < 0L) {
        throw new IllegalArgumentException("n >= required but it was " + paramLong);
      }
      Object localObject1;
      if (paramLong != 0L)
      {
        BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        ??? = this.producer;
        localObject1 = ???;
        if (??? != null) {}
      }
      for (;;)
      {
        synchronized (this.requested)
        {
          localObject1 = this.producer;
          break label116;
          this.missedRequested = BackpressureUtils.addCap(this.missedRequested, paramLong);
          if (localObject1 != null) {
            ((Producer)localObject1).request(paramLong);
          }
          emit();
          return;
        }
        label116:
        if (localObject2 != null) {}
      }
    }
    
    public void setProducer(Producer paramProducer)
    {
      if (paramProducer == null) {
        throw new NullPointerException();
      }
      synchronized (this.requested)
      {
        if (this.producer != null) {
          throw new IllegalStateException("Can't set more than one Producer!");
        }
      }
      long l2 = this.missedRequested;
      for (;;)
      {
        this.missedRequested = 0L;
        this.producer = paramProducer;
        Object localObject;
        if (localObject > 0L) {
          paramProducer.request(localObject);
        }
        emit();
        return;
        long l1 = l2;
        if (l2 != Long.MAX_VALUE) {
          l1 = l2 - 1L;
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorScan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */