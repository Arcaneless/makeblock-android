package rx.observables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;

@Experimental
public abstract class AsyncOnSubscribe<S, T>
  implements Observable.OnSubscribe<T>
{
  @Experimental
  public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> paramFunc0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> paramAction3)
  {
    new AsyncOnSubscribeImpl(paramFunc0, new Func3()
    {
      public S call(S paramAnonymousS, Long paramAnonymousLong, Observer<Observable<? extends T>> paramAnonymousObserver)
      {
        this.val$next.call(paramAnonymousS, paramAnonymousLong, paramAnonymousObserver);
        return paramAnonymousS;
      }
    });
  }
  
  @Experimental
  public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> paramFunc0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> paramAction3, Action1<? super S> paramAction1)
  {
    new AsyncOnSubscribeImpl(paramFunc0, new Func3()
    {
      public S call(S paramAnonymousS, Long paramAnonymousLong, Observer<Observable<? extends T>> paramAnonymousObserver)
      {
        this.val$next.call(paramAnonymousS, paramAnonymousLong, paramAnonymousObserver);
        return paramAnonymousS;
      }
    }, paramAction1);
  }
  
  @Experimental
  public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, paramFunc3);
  }
  
  @Experimental
  public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3, Action1<? super S> paramAction1)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, paramFunc3, paramAction1);
  }
  
  @Experimental
  public static <T> AsyncOnSubscribe<Void, T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> paramAction2)
  {
    new AsyncOnSubscribeImpl(new Func3()
    {
      public Void call(Void paramAnonymousVoid, Long paramAnonymousLong, Observer<Observable<? extends T>> paramAnonymousObserver)
      {
        this.val$next.call(paramAnonymousLong, paramAnonymousObserver);
        return paramAnonymousVoid;
      }
    });
  }
  
  @Experimental
  public static <T> AsyncOnSubscribe<Void, T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> paramAction2, Action0 paramAction0)
  {
    new AsyncOnSubscribeImpl(new Func3()new Action1
    {
      public Void call(Void paramAnonymousVoid, Long paramAnonymousLong, Observer<Observable<? extends T>> paramAnonymousObserver)
      {
        this.val$next.call(paramAnonymousLong, paramAnonymousObserver);
        return null;
      }
    }, new Action1()
    {
      public void call(Void paramAnonymousVoid)
      {
        this.val$onUnsubscribe.call();
      }
    });
  }
  
  public final void call(final Subscriber<? super T> paramSubscriber)
  {
    try
    {
      final Object localObject = generateState();
      UnicastSubject localUnicastSubject = UnicastSubject.create();
      localObject = new AsyncOuterManager(this, localObject, localUnicastSubject);
      Subscriber local6 = new Subscriber()
      {
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
          paramSubscriber.onNext(paramAnonymousT);
        }
        
        public void setProducer(Producer paramAnonymousProducer)
        {
          localObject.setConcatProducer(paramAnonymousProducer);
        }
      };
      localUnicastSubject.onBackpressureBuffer().concatMap(new Func1()
      {
        public Observable<T> call(Observable<T> paramAnonymousObservable)
        {
          return paramAnonymousObservable.onBackpressureBuffer();
        }
      }).unsafeSubscribe(local6);
      paramSubscriber.add(local6);
      paramSubscriber.add((Subscription)localObject);
      paramSubscriber.setProducer((Producer)localObject);
      return;
    }
    catch (Throwable localThrowable)
    {
      paramSubscriber.onError(localThrowable);
    }
  }
  
  protected abstract S generateState();
  
  protected abstract S next(S paramS, long paramLong, Observer<Observable<? extends T>> paramObserver);
  
  protected void onUnsubscribe(S paramS) {}
  
  private static final class AsyncOnSubscribeImpl<S, T>
    extends AsyncOnSubscribe<S, T>
  {
    private final Func0<? extends S> generator;
    private final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next;
    private final Action1<? super S> onUnsubscribe;
    
    public AsyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3)
    {
      this(paramFunc0, paramFunc3, null);
    }
    
    AsyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3, Action1<? super S> paramAction1)
    {
      this.generator = paramFunc0;
      this.next = paramFunc3;
      this.onUnsubscribe = paramAction1;
    }
    
    public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> paramFunc3)
    {
      this(null, paramFunc3, null);
    }
    
    public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> paramFunc3, Action1<? super S> paramAction1)
    {
      this(null, paramFunc3, paramAction1);
    }
    
    protected S generateState()
    {
      if (this.generator == null) {
        return null;
      }
      return (S)this.generator.call();
    }
    
    protected S next(S paramS, long paramLong, Observer<Observable<? extends T>> paramObserver)
    {
      return (S)this.next.call(paramS, Long.valueOf(paramLong), paramObserver);
    }
    
    protected void onUnsubscribe(S paramS)
    {
      if (this.onUnsubscribe != null) {
        this.onUnsubscribe.call(paramS);
      }
    }
  }
  
  static final class AsyncOuterManager<S, T>
    implements Producer, Subscription, Observer<Observable<? extends T>>
  {
    Producer concatProducer;
    boolean emitting;
    long expectedDelivery;
    private boolean hasTerminated;
    final AtomicBoolean isUnsubscribed;
    private final AsyncOnSubscribe.UnicastSubject<Observable<T>> merger;
    private boolean onNextCalled;
    private final AsyncOnSubscribe<S, T> parent;
    List<Long> requests;
    private final SerializedObserver<Observable<? extends T>> serializedSubscriber;
    private S state;
    final CompositeSubscription subscriptions = new CompositeSubscription();
    
    public AsyncOuterManager(AsyncOnSubscribe<S, T> paramAsyncOnSubscribe, S paramS, AsyncOnSubscribe.UnicastSubject<Observable<T>> paramUnicastSubject)
    {
      this.parent = paramAsyncOnSubscribe;
      this.serializedSubscriber = new SerializedObserver(this);
      this.state = paramS;
      this.merger = paramUnicastSubject;
      this.isUnsubscribed = new AtomicBoolean();
    }
    
    private void handleThrownError(Throwable paramThrowable)
    {
      if (this.hasTerminated)
      {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
        return;
      }
      this.hasTerminated = true;
      this.merger.onError(paramThrowable);
      cleanup();
    }
    
    private void subscribeBufferToObservable(Observable<? extends T> paramObservable)
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      final Subscriber local1 = new Subscriber()
      {
        long remaining = this.val$expected;
        
        public void onCompleted()
        {
          this.val$buffer.onCompleted();
          long l = this.remaining;
          if (l > 0L) {
            AsyncOnSubscribe.AsyncOuterManager.this.requestRemaining(l);
          }
        }
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          this.val$buffer.onError(paramAnonymousThrowable);
        }
        
        public void onNext(T paramAnonymousT)
        {
          this.remaining -= 1L;
          this.val$buffer.onNext(paramAnonymousT);
        }
      };
      this.subscriptions.add(local1);
      paramObservable.doOnTerminate(new Action0()
      {
        public void call()
        {
          AsyncOnSubscribe.AsyncOuterManager.this.subscriptions.remove(local1);
        }
      }).subscribe(local1);
      this.merger.onNext(localBufferUntilSubscriber);
    }
    
    void cleanup()
    {
      this.subscriptions.unsubscribe();
      try
      {
        this.parent.onUnsubscribe(this.state);
        return;
      }
      catch (Throwable localThrowable)
      {
        handleThrownError(localThrowable);
      }
    }
    
    public boolean isUnsubscribed()
    {
      return this.isUnsubscribed.get();
    }
    
    public void nextIteration(long paramLong)
    {
      this.state = this.parent.next(this.state, paramLong, this.serializedSubscriber);
    }
    
    public void onCompleted()
    {
      if (this.hasTerminated) {
        throw new IllegalStateException("Terminal event already emitted.");
      }
      this.hasTerminated = true;
      this.merger.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.hasTerminated) {
        throw new IllegalStateException("Terminal event already emitted.");
      }
      this.hasTerminated = true;
      this.merger.onError(paramThrowable);
    }
    
    public void onNext(Observable<? extends T> paramObservable)
    {
      if (this.onNextCalled) {
        throw new IllegalStateException("onNext called multiple times!");
      }
      this.onNextCalled = true;
      if (this.hasTerminated) {
        return;
      }
      subscribeBufferToObservable(paramObservable);
    }
    
    public void request(long paramLong)
    {
      if (paramLong == 0L) {
        return;
      }
      if (paramLong < 0L) {
        throw new IllegalStateException("Request can't be negative! " + paramLong);
      }
      int i = 0;
      List localList;
      for (;;)
      {
        try
        {
          if (this.emitting)
          {
            localList = this.requests;
            break label202;
            Object localObject1 = new ArrayList();
            this.requests = ((List)localObject1);
            label75:
            ((List)localObject1).add(Long.valueOf(paramLong));
            break label214;
            label90:
            this.concatProducer.request(paramLong);
            if ((i != 0) || (tryEmit(paramLong))) {
              break;
            }
            try
            {
              label113:
              label114:
              localObject1 = this.requests;
            }
            finally {}
            this.emitting = false;
            return;
          }
          this.emitting = true;
          break label90;
          this.requests = null;
        }
        finally {}
        Object localObject4;
        label202:
        label214:
        do
        {
          localObject4 = ((List)localObject3).iterator();
          if (!((Iterator)localObject4).hasNext()) {
            break label114;
          }
          if (!tryEmit(((Long)((Iterator)localObject4).next()).longValue())) {
            break label113;
          }
          return;
          localObject4 = localList;
          if (localList != null) {
            break label75;
          }
          break;
          i = 1;
          break label90;
        } while (localObject4 != null);
      }
    }
    
    public void requestRemaining(long paramLong)
    {
      if (paramLong == 0L) {}
      List localList2;
      label71:
      label90:
      do
      {
        return;
        if (paramLong < 0L) {
          throw new IllegalStateException("Request can't be negative! " + paramLong);
        }
        try
        {
          if (!this.emitting) {
            break label90;
          }
          localList2 = this.requests;
        }
        finally {}
        ArrayList localArrayList = new ArrayList();
        this.requests = localArrayList;
        localArrayList.add(Long.valueOf(paramLong));
        return;
        this.emitting = true;
      } while (tryEmit(paramLong));
      for (;;)
      {
        try
        {
          label104:
          label105:
          List localList1 = this.requests;
        }
        finally {}
        this.emitting = false;
        return;
        Object localObject3;
        while (localObject3 != null)
        {
          this.requests = null;
          localObject3 = ((List)localObject2).iterator();
          if (!((Iterator)localObject3).hasNext()) {
            break label105;
          }
          if (!tryEmit(((Long)((Iterator)localObject3).next()).longValue())) {
            break label104;
          }
          return;
          localObject3 = localList2;
          if (localList2 != null) {
            break label71;
          }
          break;
        }
      }
    }
    
    void setConcatProducer(Producer paramProducer)
    {
      if (this.concatProducer != null) {
        throw new IllegalStateException("setConcatProducer may be called at most once!");
      }
      this.concatProducer = paramProducer;
    }
    
    boolean tryEmit(long paramLong)
    {
      if (isUnsubscribed())
      {
        cleanup();
        return true;
      }
      try
      {
        this.onNextCalled = false;
        this.expectedDelivery = paramLong;
        nextIteration(paramLong);
        if ((this.hasTerminated) || (isUnsubscribed()))
        {
          cleanup();
          return true;
        }
      }
      catch (Throwable localThrowable)
      {
        handleThrownError(localThrowable);
        return true;
      }
      if (!this.onNextCalled)
      {
        handleThrownError(new IllegalStateException("No events emitted!"));
        return true;
      }
      return false;
    }
    
    public void unsubscribe()
    {
      if (this.isUnsubscribed.compareAndSet(false, true)) {
        try
        {
          if (this.emitting)
          {
            this.requests = new ArrayList();
            this.requests.add(Long.valueOf(0L));
            return;
          }
          this.emitting = true;
          cleanup();
          return;
        }
        finally {}
      }
    }
  }
  
  static final class UnicastSubject<T>
    extends Observable<T>
    implements Observer<T>
  {
    private State<T> state;
    
    protected UnicastSubject(State<T> paramState)
    {
      super();
      this.state = paramState;
    }
    
    public static <T> UnicastSubject<T> create()
    {
      return new UnicastSubject(new State());
    }
    
    public void onCompleted()
    {
      this.state.subscriber.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.state.subscriber.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.state.subscriber.onNext(paramT);
    }
    
    static final class State<T>
      implements Observable.OnSubscribe<T>
    {
      Subscriber<? super T> subscriber;
      
      public void call(Subscriber<? super T> paramSubscriber)
      {
        try
        {
          if (this.subscriber == null)
          {
            this.subscriber = paramSubscriber;
            return;
          }
          paramSubscriber.onError(new IllegalStateException("There can be only one subscriber"));
          return;
        }
        finally {}
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observables\AsyncOnSubscribe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */