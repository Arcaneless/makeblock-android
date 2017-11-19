package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Func1;

public final class OperatorTakeLast<T>
  implements Observable.Operator<T, T>
{
  final int count;
  
  public OperatorTakeLast(int paramInt)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException("count cannot be negative");
    }
    this.count = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    final TakeLastSubscriber localTakeLastSubscriber = new TakeLastSubscriber(paramSubscriber, this.count);
    paramSubscriber.add(localTakeLastSubscriber);
    paramSubscriber.setProducer(new Producer()
    {
      public void request(long paramAnonymousLong)
      {
        localTakeLastSubscriber.requestMore(paramAnonymousLong);
      }
    });
    return localTakeLastSubscriber;
  }
  
  static final class TakeLastSubscriber<T>
    extends Subscriber<T>
    implements Func1<Object, T>
  {
    final Subscriber<? super T> actual;
    final int count;
    final NotificationLite<T> nl;
    final ArrayDeque<Object> queue;
    final AtomicLong requested;
    
    public TakeLastSubscriber(Subscriber<? super T> paramSubscriber, int paramInt)
    {
      this.actual = paramSubscriber;
      this.count = paramInt;
      this.requested = new AtomicLong();
      this.queue = new ArrayDeque();
      this.nl = NotificationLite.instance();
    }
    
    public T call(Object paramObject)
    {
      return (T)this.nl.getValue(paramObject);
    }
    
    public void onCompleted()
    {
      BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.queue.clear();
      this.actual.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      if (this.queue.size() == this.count) {
        this.queue.poll();
      }
      this.queue.offer(this.nl.next(paramT));
    }
    
    void requestMore(long paramLong)
    {
      if (paramLong > 0L) {
        BackpressureUtils.postCompleteRequest(this.requested, paramLong, this.queue, this.actual, this);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorTakeLast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */