package rx.subjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T>
  extends AtomicReference<State<T>>
  implements Observable.OnSubscribe<T>
{
  private static final long serialVersionUID = 6035251036011671568L;
  boolean active = true;
  volatile Object latest;
  public final NotificationLite<T> nl = NotificationLite.instance();
  Action1<SubjectObserver<T>> onAdded = Actions.empty();
  Action1<SubjectObserver<T>> onStart = Actions.empty();
  Action1<SubjectObserver<T>> onTerminated = Actions.empty();
  
  public SubjectSubscriptionManager()
  {
    super(State.EMPTY);
  }
  
  boolean add(SubjectObserver<T> paramSubjectObserver)
  {
    State localState;
    do
    {
      localState = (State)get();
      if (localState.terminated)
      {
        this.onTerminated.call(paramSubjectObserver);
        return false;
      }
    } while (!compareAndSet(localState, localState.add(paramSubjectObserver)));
    this.onAdded.call(paramSubjectObserver);
    return true;
  }
  
  void addUnsubscriber(Subscriber<? super T> paramSubscriber, final SubjectObserver<T> paramSubjectObserver)
  {
    paramSubscriber.add(Subscriptions.create(new Action0()
    {
      public void call()
      {
        SubjectSubscriptionManager.this.remove(paramSubjectObserver);
      }
    }));
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    SubjectObserver localSubjectObserver = new SubjectObserver(paramSubscriber);
    addUnsubscriber(paramSubscriber, localSubjectObserver);
    this.onStart.call(localSubjectObserver);
    if ((!paramSubscriber.isUnsubscribed()) && (add(localSubjectObserver)) && (paramSubscriber.isUnsubscribed())) {
      remove(localSubjectObserver);
    }
  }
  
  Object getLatest()
  {
    return this.latest;
  }
  
  SubjectObserver<T>[] next(Object paramObject)
  {
    setLatest(paramObject);
    return ((State)get()).observers;
  }
  
  SubjectObserver<T>[] observers()
  {
    return ((State)get()).observers;
  }
  
  void remove(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1;
    State localState2;
    do
    {
      localState1 = (State)get();
      if (localState1.terminated) {}
      do
      {
        return;
        localState2 = localState1.remove(paramSubjectObserver);
      } while (localState2 == localState1);
    } while (!compareAndSet(localState1, localState2));
  }
  
  void setLatest(Object paramObject)
  {
    this.latest = paramObject;
  }
  
  SubjectObserver<T>[] terminate(Object paramObject)
  {
    setLatest(paramObject);
    this.active = false;
    if (((State)get()).terminated) {
      return State.NO_OBSERVERS;
    }
    return ((State)getAndSet(State.TERMINATED)).observers;
  }
  
  protected static final class State<T>
  {
    static final State EMPTY = new State(false, NO_OBSERVERS);
    static final SubjectSubscriptionManager.SubjectObserver[] NO_OBSERVERS = new SubjectSubscriptionManager.SubjectObserver[0];
    static final State TERMINATED = new State(true, NO_OBSERVERS);
    final SubjectSubscriptionManager.SubjectObserver[] observers;
    final boolean terminated;
    
    public State(boolean paramBoolean, SubjectSubscriptionManager.SubjectObserver[] paramArrayOfSubjectObserver)
    {
      this.terminated = paramBoolean;
      this.observers = paramArrayOfSubjectObserver;
    }
    
    public State add(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      int i = this.observers.length;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i + 1];
      System.arraycopy(this.observers, 0, arrayOfSubjectObserver, 0, i);
      arrayOfSubjectObserver[i] = paramSubjectObserver;
      return new State(this.terminated, arrayOfSubjectObserver);
    }
    
    public State remove(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver2 = this.observers;
      int m = arrayOfSubjectObserver2.length;
      State localState;
      if ((m == 1) && (arrayOfSubjectObserver2[0] == paramSubjectObserver)) {
        localState = EMPTY;
      }
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver1;
      int j;
      SubjectSubscriptionManager.SubjectObserver localSubjectObserver;
      do
      {
        do
        {
          return localState;
          localState = this;
        } while (m == 0);
        arrayOfSubjectObserver1 = new SubjectSubscriptionManager.SubjectObserver[m - 1];
        j = 0;
        i = 0;
        if (j >= m) {
          break;
        }
        localSubjectObserver = arrayOfSubjectObserver2[j];
        if (localSubjectObserver == paramSubjectObserver) {
          break label150;
        }
        localState = this;
      } while (i == m - 1);
      int k = i + 1;
      arrayOfSubjectObserver1[i] = localSubjectObserver;
      int i = k;
      label150:
      for (;;)
      {
        j += 1;
        break;
        if (i == 0) {
          return EMPTY;
        }
        paramSubjectObserver = arrayOfSubjectObserver1;
        if (i < m - 1)
        {
          paramSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i];
          System.arraycopy(arrayOfSubjectObserver1, 0, paramSubjectObserver, 0, i);
        }
        return new State(this.terminated, paramSubjectObserver);
      }
    }
  }
  
  protected static final class SubjectObserver<T>
    implements Observer<T>
  {
    final Subscriber<? super T> actual;
    protected volatile boolean caughtUp;
    boolean emitting;
    boolean fastPath;
    boolean first = true;
    private volatile Object index;
    List<Object> queue;
    
    public SubjectObserver(Subscriber<? super T> paramSubscriber)
    {
      this.actual = paramSubscriber;
    }
    
    protected void accept(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (paramObject != null) {
        paramNotificationLite.accept(this.actual, paramObject);
      }
    }
    
    protected void emitFirst(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      boolean bool = false;
      for (;;)
      {
        try
        {
          if ((!this.first) || (this.emitting)) {
            return;
          }
          this.first = false;
        }
        finally {}
        this.emitting = bool;
        if (paramObject != null)
        {
          emitLoop(null, paramObject, paramNotificationLite);
          return;
        }
        return;
        if (paramObject != null) {
          bool = true;
        }
      }
    }
    
    protected void emitLoop(List<Object> paramList, Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      int k = 1;
      int n = 0;
      int m = 0;
      for (;;)
      {
        if (paramList != null)
        {
          i = m;
          try
          {
            paramList = paramList.iterator();
            for (;;)
            {
              i = m;
              if (!paramList.hasNext()) {
                break;
              }
              i = m;
              accept(paramList.next(), paramNotificationLite);
            }
            try
            {
              this.emitting = false;
              throw paramList;
            }
            finally
            {
              for (;;)
              {
                int j;
                throw paramList;
                if (paramList != null) {}
              }
            }
          }
          finally
          {
            if (i != 0) {}
          }
        }
        j = k;
        if (k != 0)
        {
          j = 0;
          i = m;
          accept(paramObject, paramNotificationLite);
        }
        i = m;
        i = n;
        try
        {
          paramList = this.queue;
          i = n;
          this.queue = null;
        }
        finally {}
        i = n;
        this.emitting = false;
        break label184;
        if (1 == 0) {}
        try
        {
          this.emitting = false;
          return;
        }
        finally {}
        i = n;
        k = j;
      }
    }
    
    protected void emitNext(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (!this.fastPath) {}
      try
      {
        this.first = false;
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(paramObject);
          return;
        }
        this.fastPath = true;
        paramNotificationLite.accept(this.actual, paramObject);
        return;
      }
      finally {}
    }
    
    protected Observer<? super T> getActual()
    {
      return this.actual;
    }
    
    public <I> I index()
    {
      return (I)this.index;
    }
    
    public void index(Object paramObject)
    {
      this.index = paramObject;
    }
    
    public void onCompleted()
    {
      this.actual.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.actual.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.actual.onNext(paramT);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\subjects\SubjectSubscriptionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */