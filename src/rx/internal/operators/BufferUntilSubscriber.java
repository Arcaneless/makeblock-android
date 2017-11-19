package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class BufferUntilSubscriber<T>
  extends Subject<T, T>
{
  static final Observer EMPTY_OBSERVER = new Observer()
  {
    public void onCompleted() {}
    
    public void onError(Throwable paramAnonymousThrowable) {}
    
    public void onNext(Object paramAnonymousObject) {}
  };
  private boolean forward = false;
  final State<T> state;
  
  private BufferUntilSubscriber(State<T> paramState)
  {
    super(new OnSubscribeAction(paramState));
    this.state = paramState;
  }
  
  public static <T> BufferUntilSubscriber<T> create()
  {
    return new BufferUntilSubscriber(new State());
  }
  
  private void emit(Object paramObject)
  {
    synchronized (this.state.guard)
    {
      this.state.buffer.add(paramObject);
      if ((this.state.get() != null) && (!this.state.emitting))
      {
        this.forward = true;
        this.state.emitting = true;
      }
      if (this.forward)
      {
        paramObject = this.state.buffer.poll();
        if (paramObject != null) {
          this.state.nl.accept((Observer)this.state.get(), paramObject);
        }
      }
    }
  }
  
  public boolean hasObservers()
  {
    for (;;)
    {
      synchronized (this.state.guard)
      {
        if (this.state.get() != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void onCompleted()
  {
    if (this.forward)
    {
      ((Observer)this.state.get()).onCompleted();
      return;
    }
    emit(this.state.nl.completed());
  }
  
  public void onError(Throwable paramThrowable)
  {
    if (this.forward)
    {
      ((Observer)this.state.get()).onError(paramThrowable);
      return;
    }
    emit(this.state.nl.error(paramThrowable));
  }
  
  public void onNext(T paramT)
  {
    if (this.forward)
    {
      ((Observer)this.state.get()).onNext(paramT);
      return;
    }
    emit(this.state.nl.next(paramT));
  }
  
  static final class OnSubscribeAction<T>
    implements Observable.OnSubscribe<T>
  {
    final BufferUntilSubscriber.State<T> state;
    
    public OnSubscribeAction(BufferUntilSubscriber.State<T> paramState)
    {
      this.state = paramState;
    }
    
    public void call(Subscriber<? super T> arg1)
    {
      if (this.state.casObserverRef(null, ???)) {
        ???.add(Subscriptions.create(new Action0()
        {
          public void call()
          {
            BufferUntilSubscriber.OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
          }
        }));
      }
      for (int i = 0;; i = 1)
      {
        for (;;)
        {
          synchronized (this.state.guard)
          {
            if (!this.state.emitting)
            {
              this.state.emitting = true;
              break;
            }
            if (i == 0) {
              return;
            }
            ??? = NotificationLite.instance();
            Object localObject1 = this.state.buffer.poll();
            if (localObject1 != null) {
              ???.accept((Observer)this.state.get(), localObject1);
            }
          }
          synchronized (this.state.guard)
          {
            if (this.state.buffer.isEmpty())
            {
              this.state.emitting = false;
              return;
            }
          }
        }
        ???.onError(new IllegalStateException("Only one subscriber allowed!"));
        return;
      }
    }
  }
  
  static final class State<T>
    extends AtomicReference<Observer<? super T>>
  {
    private static final long serialVersionUID = 8026705089538090368L;
    final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue();
    boolean emitting = false;
    final Object guard = new Object();
    final NotificationLite<T> nl = NotificationLite.instance();
    
    boolean casObserverRef(Observer<? super T> paramObserver1, Observer<? super T> paramObserver2)
    {
      return compareAndSet(paramObserver1, paramObserver2);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\BufferUntilSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */