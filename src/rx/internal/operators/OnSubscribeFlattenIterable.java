package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.RxJavaPluginUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public final class OnSubscribeFlattenIterable<T, R>
  implements Observable.OnSubscribe<R>
{
  final Func1<? super T, ? extends Iterable<? extends R>> mapper;
  final int prefetch;
  final Observable<? extends T> source;
  
  protected OnSubscribeFlattenIterable(Observable<? extends T> paramObservable, Func1<? super T, ? extends Iterable<? extends R>> paramFunc1, int paramInt)
  {
    this.source = paramObservable;
    this.mapper = paramFunc1;
    this.prefetch = paramInt;
  }
  
  public static <T, R> Observable<R> createFrom(Observable<? extends T> paramObservable, Func1<? super T, ? extends Iterable<? extends R>> paramFunc1, int paramInt)
  {
    if ((paramObservable instanceof ScalarSynchronousObservable)) {
      return Observable.create(new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable)paramObservable).get(), paramFunc1));
    }
    return Observable.create(new OnSubscribeFlattenIterable(paramObservable, paramFunc1, paramInt));
  }
  
  public void call(Subscriber<? super R> paramSubscriber)
  {
    final FlattenIterableSubscriber localFlattenIterableSubscriber = new FlattenIterableSubscriber(paramSubscriber, this.mapper, this.prefetch);
    paramSubscriber.add(localFlattenIterableSubscriber);
    paramSubscriber.setProducer(new Producer()
    {
      public void request(long paramAnonymousLong)
      {
        localFlattenIterableSubscriber.requestMore(paramAnonymousLong);
      }
    });
    this.source.unsafeSubscribe(localFlattenIterableSubscriber);
  }
  
  static final class FlattenIterableSubscriber<T, R>
    extends Subscriber<T>
  {
    Iterator<? extends R> active;
    final Subscriber<? super R> actual;
    volatile boolean done;
    final AtomicReference<Throwable> error;
    final long limit;
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final NotificationLite<T> nl;
    long produced;
    final Queue<Object> queue;
    final AtomicLong requested;
    final AtomicInteger wip;
    
    public FlattenIterableSubscriber(Subscriber<? super R> paramSubscriber, Func1<? super T, ? extends Iterable<? extends R>> paramFunc1, int paramInt)
    {
      this.actual = paramSubscriber;
      this.mapper = paramFunc1;
      this.error = new AtomicReference();
      this.wip = new AtomicInteger();
      this.requested = new AtomicLong();
      this.nl = NotificationLite.instance();
      if (paramInt == Integer.MAX_VALUE)
      {
        this.limit = Long.MAX_VALUE;
        this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
      }
      for (;;)
      {
        request(paramInt);
        return;
        this.limit = (paramInt - (paramInt >> 2));
        if (UnsafeAccess.isUnsafeAvailable()) {
          this.queue = new SpscArrayQueue(paramInt);
        } else {
          this.queue = new SpscAtomicArrayQueue(paramInt);
        }
      }
    }
    
    boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2, Subscriber<?> paramSubscriber, Queue<?> paramQueue)
    {
      if (paramSubscriber.isUnsubscribed())
      {
        paramQueue.clear();
        this.active = null;
        return true;
      }
      if (paramBoolean1)
      {
        if ((Throwable)this.error.get() != null)
        {
          Throwable localThrowable = ExceptionsUtils.terminate(this.error);
          unsubscribe();
          paramQueue.clear();
          this.active = null;
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
    
    void drain()
    {
      if (this.wip.getAndIncrement() != 0) {
        return;
      }
      Subscriber localSubscriber = this.actual;
      Queue localQueue = this.queue;
      int i = 1;
      for (;;)
      {
        label25:
        Object localObject1 = this.active;
        Object localObject3 = localObject1;
        boolean bool2;
        Object localObject4;
        if (localObject1 == null)
        {
          bool2 = this.done;
          localObject4 = localQueue.poll();
          if (localObject4 != null) {
            break label354;
          }
          bool1 = true;
          label62:
          if (checkTerminated(bool2, bool1, localSubscriber, localQueue)) {
            break label357;
          }
          localObject3 = localObject1;
          if (!bool1)
          {
            l1 = this.produced + 1L;
            if (l1 != this.limit) {
              break label359;
            }
            this.produced = 0L;
            request(l1);
          }
        }
        for (;;)
        {
          try
          {
            localObject3 = ((Iterable)this.mapper.call(this.nl.getValue(localObject4))).iterator();
            bool1 = ((Iterator)localObject3).hasNext();
            if (!bool1) {
              break label25;
            }
            this.active = ((Iterator)localObject3);
            if (localObject3 != null)
            {
              l3 = this.requested.get();
              l1 = 0L;
              l2 = l1;
              localObject1 = localObject3;
              if (l1 != l3) {
                if (checkTerminated(this.done, false, localSubscriber, localQueue)) {
                  break;
                }
              }
            }
          }
          catch (Throwable localThrowable1)
          {
            long l3;
            int j;
            label354:
            label357:
            label359:
            Exceptions.throwIfFatal(localThrowable1);
            onError(localThrowable1);
          }
          try
          {
            localObject1 = ((Iterator)localObject3).next();
            localSubscriber.onNext(localObject1);
            if (checkTerminated(this.done, false, localSubscriber, localQueue)) {
              break;
            }
            l2 = l1 + 1L;
          }
          catch (Throwable localThrowable2)
          {
            Exceptions.throwIfFatal(localThrowable2);
            localObject2 = null;
            this.active = null;
            onError(localThrowable2);
            l2 = l1;
            continue;
          }
          try
          {
            bool1 = ((Iterator)localObject3).hasNext();
            l1 = l2;
            if (bool1) {
              continue;
            }
            localObject1 = null;
            this.active = null;
          }
          catch (Throwable localThrowable3)
          {
            Exceptions.throwIfFatal(localThrowable3);
            localObject2 = null;
            this.active = null;
            onError(localThrowable3);
            continue;
            bool1 = false;
            continue;
          }
          if (l2 == l3)
          {
            bool2 = this.done;
            if ((!localQueue.isEmpty()) || (localObject1 != null)) {
              break label436;
            }
            bool1 = true;
            if (checkTerminated(bool2, bool1, localSubscriber, localQueue)) {
              break;
            }
          }
          if (l2 != 0L) {
            BackpressureUtils.produced(this.requested, l2);
          }
          if (localObject1 == null) {
            break label25;
          }
          j = this.wip.addAndGet(-i);
          i = j;
          if (j != 0) {
            break label25;
          }
          return;
          bool1 = false;
          break label62;
          break;
          this.produced = l1;
        }
      }
    }
    
    public void onCompleted()
    {
      this.done = true;
      drain();
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (ExceptionsUtils.addThrowable(this.error, paramThrowable))
      {
        this.done = true;
        drain();
        return;
      }
      RxJavaPluginUtils.handleException(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      if (!this.queue.offer(this.nl.next(paramT)))
      {
        unsubscribe();
        onError(new MissingBackpressureException());
        return;
      }
      drain();
    }
    
    void requestMore(long paramLong)
    {
      if (paramLong > 0L)
      {
        BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        drain();
      }
      while (paramLong >= 0L) {
        return;
      }
      throw new IllegalStateException("n >= 0 required but it was " + paramLong);
    }
  }
  
  static final class OnSubscribeScalarFlattenIterable<T, R>
    implements Observable.OnSubscribe<R>
  {
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final T value;
    
    public OnSubscribeScalarFlattenIterable(T paramT, Func1<? super T, ? extends Iterable<? extends R>> paramFunc1)
    {
      this.value = paramT;
      this.mapper = paramFunc1;
    }
    
    public void call(Subscriber<? super R> paramSubscriber)
    {
      try
      {
        Iterator localIterator = ((Iterable)this.mapper.call(this.value)).iterator();
        boolean bool = localIterator.hasNext();
        if (!bool)
        {
          paramSubscriber.onCompleted();
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwOrReport(localThrowable, paramSubscriber, this.value);
        return;
      }
      paramSubscriber.setProducer(new OnSubscribeFromIterable.IterableProducer(paramSubscriber, localThrowable));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeFlattenIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */