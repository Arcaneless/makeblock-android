package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.util.RxRingBuffer;

public final class BlockingOperatorToIterator
{
  private BlockingOperatorToIterator()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Iterator<T> toIterator(Observable<? extends T> paramObservable)
  {
    SubscriberIterator localSubscriberIterator = new SubscriberIterator();
    paramObservable.materialize().subscribe(localSubscriberIterator);
    return localSubscriberIterator;
  }
  
  public static final class SubscriberIterator<T>
    extends Subscriber<Notification<? extends T>>
    implements Iterator<T>
  {
    static final int LIMIT = RxRingBuffer.SIZE * 3 / 4;
    private Notification<? extends T> buf;
    private final BlockingQueue<Notification<? extends T>> notifications = new LinkedBlockingQueue();
    private int received;
    
    private Notification<? extends T> take()
    {
      do
      {
        try
        {
          localNotification = (Notification)this.notifications.poll();
        }
        catch (InterruptedException localInterruptedException)
        {
          Notification localNotification;
          unsubscribe();
          throw Exceptions.propagate(localInterruptedException);
        }
        localNotification = (Notification)this.notifications.take();
        return localNotification;
      } while (localInterruptedException == null);
      return localInterruptedException;
    }
    
    public boolean hasNext()
    {
      boolean bool = false;
      if (this.buf == null)
      {
        this.buf = take();
        this.received += 1;
        if (this.received >= LIMIT)
        {
          request(this.received);
          this.received = 0;
        }
      }
      if (this.buf.isOnError()) {
        throw Exceptions.propagate(this.buf.getThrowable());
      }
      if (!this.buf.isOnCompleted()) {
        bool = true;
      }
      return bool;
    }
    
    public T next()
    {
      if (hasNext())
      {
        Object localObject = this.buf.getValue();
        this.buf = null;
        return (T)localObject;
      }
      throw new NoSuchElementException();
    }
    
    public void onCompleted() {}
    
    public void onError(Throwable paramThrowable)
    {
      this.notifications.offer(Notification.createOnError(paramThrowable));
    }
    
    public void onNext(Notification<? extends T> paramNotification)
    {
      this.notifications.offer(paramNotification);
    }
    
    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException("Read-only iterator");
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\BlockingOperatorToIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */