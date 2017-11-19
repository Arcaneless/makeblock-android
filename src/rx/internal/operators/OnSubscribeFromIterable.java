package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class OnSubscribeFromIterable<T>
  implements Observable.OnSubscribe<T>
{
  final Iterable<? extends T> is;
  
  public OnSubscribeFromIterable(Iterable<? extends T> paramIterable)
  {
    if (paramIterable == null) {
      throw new NullPointerException("iterable must not be null");
    }
    this.is = paramIterable;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      Iterator localIterator = this.is.iterator();
      boolean bool = localIterator.hasNext();
      if (!paramSubscriber.isUnsubscribed())
      {
        if (!bool) {
          paramSubscriber.onCompleted();
        }
      }
      else {
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      Exceptions.throwOrReport(localThrowable, paramSubscriber);
      return;
    }
    paramSubscriber.setProducer(new IterableProducer(paramSubscriber, localThrowable));
  }
  
  static final class IterableProducer<T>
    extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -8730475647105475802L;
    private final Iterator<? extends T> it;
    private final Subscriber<? super T> o;
    
    IterableProducer(Subscriber<? super T> paramSubscriber, Iterator<? extends T> paramIterator)
    {
      this.o = paramSubscriber;
      this.it = paramIterator;
    }
    
    void fastpath()
    {
      Subscriber localSubscriber = this.o;
      Iterator localIterator = this.it;
      for (;;)
      {
        if (localSubscriber.isUnsubscribed()) {
          label17:
          return;
        }
        try
        {
          Object localObject = localIterator.next();
          localSubscriber.onNext(localObject);
          if (localSubscriber.isUnsubscribed()) {
            break label17;
          }
        }
        catch (Throwable localThrowable1)
        {
          try
          {
            boolean bool = localIterator.hasNext();
            if (bool) {
              continue;
            }
            if (localSubscriber.isUnsubscribed()) {
              break label17;
            }
            localSubscriber.onCompleted();
            return;
          }
          catch (Throwable localThrowable2)
          {
            Exceptions.throwOrReport(localThrowable2, localSubscriber);
          }
          localThrowable1 = localThrowable1;
          Exceptions.throwOrReport(localThrowable1, localSubscriber);
          return;
        }
      }
    }
    
    public void request(long paramLong)
    {
      if (get() == Long.MAX_VALUE) {}
      do
      {
        return;
        if ((paramLong == Long.MAX_VALUE) && (compareAndSet(0L, Long.MAX_VALUE)))
        {
          fastpath();
          return;
        }
      } while ((paramLong <= 0L) || (BackpressureUtils.getAndAddRequest(this, paramLong) != 0L));
      slowpath(paramLong);
    }
    
    void slowpath(long paramLong)
    {
      Subscriber localSubscriber = this.o;
      Iterator localIterator = this.it;
      long l1 = 0L;
      for (;;)
      {
        if (l1 != paramLong) {
          if (!localSubscriber.isUnsubscribed()) {}
        }
        label101:
        do
        {
          for (;;)
          {
            return;
            try
            {
              Object localObject = localIterator.next();
              localSubscriber.onNext(localObject);
              if (localSubscriber.isUnsubscribed()) {}
            }
            catch (Throwable localThrowable1)
            {
              try
              {
                boolean bool = localIterator.hasNext();
                if (bool) {
                  break label101;
                }
                if (localSubscriber.isUnsubscribed()) {
                  continue;
                }
                localSubscriber.onCompleted();
                return;
              }
              catch (Throwable localThrowable2)
              {
                Exceptions.throwOrReport(localThrowable2, localSubscriber);
                return;
              }
              localThrowable1 = localThrowable1;
              Exceptions.throwOrReport(localThrowable1, localSubscriber);
              return;
            }
          }
          l1 += 1L;
          break;
          long l2 = get();
          paramLong = l2;
          if (l1 != l2) {
            break;
          }
          paramLong = BackpressureUtils.produced(this, l1);
        } while (paramLong == 0L);
        l1 = 0L;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeFromIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */