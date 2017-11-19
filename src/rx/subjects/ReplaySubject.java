package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.internal.util.RxJavaPluginUtils;

public final class ReplaySubject<T>
  extends Subject<T, T>
{
  private static final Object[] EMPTY_ARRAY = new Object[0];
  final ReplayState<T> state;
  
  ReplaySubject(ReplayState<T> paramReplayState)
  {
    super(paramReplayState);
    this.state = paramReplayState;
  }
  
  public static <T> ReplaySubject<T> create()
  {
    return create(16);
  }
  
  public static <T> ReplaySubject<T> create(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("capacity > 0 required but it was " + paramInt);
    }
    return new ReplaySubject(new ReplayState(new ReplayUnboundedBuffer(paramInt)));
  }
  
  static <T> ReplaySubject<T> createUnbounded()
  {
    return new ReplaySubject(new ReplayState(new ReplaySizeBoundBuffer(Integer.MAX_VALUE)));
  }
  
  public static <T> ReplaySubject<T> createWithSize(int paramInt)
  {
    return new ReplaySubject(new ReplayState(new ReplaySizeBoundBuffer(paramInt)));
  }
  
  public static <T> ReplaySubject<T> createWithTime(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return createWithTimeAndSize(paramLong, paramTimeUnit, Integer.MAX_VALUE, paramScheduler);
  }
  
  public static <T> ReplaySubject<T> createWithTimeAndSize(long paramLong, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    return new ReplaySubject(new ReplayState(new ReplaySizeAndTimeBoundBuffer(paramInt, paramTimeUnit.toMillis(paramLong), paramScheduler)));
  }
  
  @Beta
  public Throwable getThrowable()
  {
    if (this.state.isTerminated()) {
      return this.state.buffer.error();
    }
    return null;
  }
  
  @Beta
  public T getValue()
  {
    return (T)this.state.buffer.last();
  }
  
  @Beta
  public Object[] getValues()
  {
    Object[] arrayOfObject2 = getValues((Object[])EMPTY_ARRAY);
    Object[] arrayOfObject1 = arrayOfObject2;
    if (arrayOfObject2 == EMPTY_ARRAY) {
      arrayOfObject1 = new Object[0];
    }
    return arrayOfObject1;
  }
  
  @Beta
  public T[] getValues(T[] paramArrayOfT)
  {
    return this.state.buffer.toArray(paramArrayOfT);
  }
  
  @Beta
  public boolean hasAnyValue()
  {
    return !this.state.buffer.isEmpty();
  }
  
  @Beta
  public boolean hasCompleted()
  {
    return (this.state.isTerminated()) && (this.state.buffer.error() == null);
  }
  
  public boolean hasObservers()
  {
    return ((ReplayProducer[])this.state.get()).length != 0;
  }
  
  @Beta
  public boolean hasThrowable()
  {
    return (this.state.isTerminated()) && (this.state.buffer.error() != null);
  }
  
  @Beta
  public boolean hasValue()
  {
    return hasAnyValue();
  }
  
  public void onCompleted()
  {
    this.state.onCompleted();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.state.onError(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.state.onNext(paramT);
  }
  
  @Beta
  public int size()
  {
    return this.state.buffer.size();
  }
  
  int subscriberCount()
  {
    return ((ReplayProducer[])this.state.get()).length;
  }
  
  static abstract interface ReplayBuffer<T>
  {
    public abstract void complete();
    
    public abstract boolean drain(ReplaySubject.ReplayProducer<T> paramReplayProducer);
    
    public abstract Throwable error();
    
    public abstract void error(Throwable paramThrowable);
    
    public abstract boolean isComplete();
    
    public abstract boolean isEmpty();
    
    public abstract T last();
    
    public abstract void next(T paramT);
    
    public abstract int size();
    
    public abstract T[] toArray(T[] paramArrayOfT);
  }
  
  static final class ReplayProducer<T>
    extends AtomicInteger
    implements Producer, Subscription
  {
    private static final long serialVersionUID = -5006209596735204567L;
    final Subscriber<? super T> actual;
    boolean caughtUp;
    int index;
    Object node;
    final AtomicLong requested;
    final ReplaySubject.ReplayState<T> state;
    int tailIndex;
    
    public ReplayProducer(Subscriber<? super T> paramSubscriber, ReplaySubject.ReplayState<T> paramReplayState)
    {
      this.actual = paramSubscriber;
      this.requested = new AtomicLong();
      this.state = paramReplayState;
    }
    
    public boolean isUnsubscribed()
    {
      return this.actual.isUnsubscribed();
    }
    
    public void request(long paramLong)
    {
      if (paramLong > 0L)
      {
        BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        this.state.buffer.drain(this);
      }
      while (paramLong >= 0L) {
        return;
      }
      throw new IllegalArgumentException("n >= required but it was " + paramLong);
    }
    
    public void unsubscribe()
    {
      this.state.remove(this);
    }
  }
  
  static final class ReplaySizeAndTimeBoundBuffer<T>
    implements ReplaySubject.ReplayBuffer<T>
  {
    volatile boolean done;
    Throwable error;
    volatile TimedNode<T> head;
    final int limit;
    final long maxAgeMillis;
    final Scheduler scheduler;
    int size;
    TimedNode<T> tail;
    
    public ReplaySizeAndTimeBoundBuffer(int paramInt, long paramLong, Scheduler paramScheduler)
    {
      this.limit = paramInt;
      TimedNode localTimedNode = new TimedNode(null, 0L);
      this.tail = localTimedNode;
      this.head = localTimedNode;
      this.maxAgeMillis = paramLong;
      this.scheduler = paramScheduler;
    }
    
    public void complete()
    {
      evictFinal();
      this.done = true;
    }
    
    public boolean drain(ReplaySubject.ReplayProducer<T> paramReplayProducer)
    {
      if (paramReplayProducer.getAndIncrement() != 0) {
        return false;
      }
      Subscriber localSubscriber = paramReplayProducer.actual;
      int i = 1;
      long l3;
      int j;
      label143:
      label151:
      label253:
      label261:
      do
      {
        l3 = paramReplayProducer.requested.get();
        long l2 = 0L;
        TimedNode localTimedNode2 = (TimedNode)paramReplayProducer.node;
        long l1 = l2;
        TimedNode localTimedNode1 = localTimedNode2;
        if (localTimedNode2 == null)
        {
          localTimedNode1 = latestHead();
          l1 = l2;
        }
        for (;;)
        {
          if (l1 != l3)
          {
            if (localSubscriber.isUnsubscribed())
            {
              paramReplayProducer.node = null;
              return false;
            }
            bool = this.done;
            localTimedNode2 = (TimedNode)localTimedNode1.get();
            if (localTimedNode2 == null)
            {
              j = 1;
              if ((!bool) || (j == 0)) {
                break label151;
              }
              paramReplayProducer.node = null;
              paramReplayProducer = this.error;
              if (paramReplayProducer == null) {
                break label143;
              }
              localSubscriber.onError(paramReplayProducer);
            }
            for (;;)
            {
              return false;
              j = 0;
              break;
              localSubscriber.onCompleted();
            }
            if (j == 0) {}
          }
          else
          {
            if (l1 != l3) {
              break label261;
            }
            if (!localSubscriber.isUnsubscribed()) {
              break;
            }
            paramReplayProducer.node = null;
            return false;
          }
          localSubscriber.onNext(localTimedNode2.value);
          l1 += 1L;
          localTimedNode1 = localTimedNode2;
        }
        boolean bool = this.done;
        if (localTimedNode1.get() == null)
        {
          j = 1;
          if ((!bool) || (j == 0)) {
            break label261;
          }
          paramReplayProducer.node = null;
          paramReplayProducer = this.error;
          if (paramReplayProducer == null) {
            break label253;
          }
          localSubscriber.onError(paramReplayProducer);
        }
        for (;;)
        {
          return false;
          j = 0;
          break;
          localSubscriber.onCompleted();
        }
        if ((l1 != 0L) && (l3 != Long.MAX_VALUE)) {
          BackpressureUtils.produced(paramReplayProducer.requested, l1);
        }
        paramReplayProducer.node = localTimedNode1;
        j = paramReplayProducer.addAndGet(-i);
        i = j;
      } while (j != 0);
      return l3 == Long.MAX_VALUE;
    }
    
    public Throwable error()
    {
      return this.error;
    }
    
    public void error(Throwable paramThrowable)
    {
      evictFinal();
      this.error = paramThrowable;
      this.done = true;
    }
    
    void evictFinal()
    {
      long l1 = this.scheduler.now();
      long l2 = this.maxAgeMillis;
      TimedNode localTimedNode1 = this.head;
      TimedNode localTimedNode2;
      for (Object localObject = localTimedNode1;; localObject = localTimedNode2)
      {
        localTimedNode2 = (TimedNode)((TimedNode)localObject).get();
        if ((localTimedNode2 == null) || (localTimedNode2.timestamp > l1 - l2))
        {
          if (localTimedNode1 != localObject) {
            this.head = ((TimedNode)localObject);
          }
          return;
        }
      }
    }
    
    public boolean isComplete()
    {
      return this.done;
    }
    
    public boolean isEmpty()
    {
      return latestHead().get() == null;
    }
    
    public T last()
    {
      TimedNode localTimedNode;
      for (Object localObject = latestHead();; localObject = localTimedNode)
      {
        localTimedNode = (TimedNode)((TimedNode)localObject).get();
        if (localTimedNode == null) {
          break;
        }
      }
      return (T)((TimedNode)localObject).value;
    }
    
    TimedNode<T> latestHead()
    {
      long l1 = this.scheduler.now();
      long l2 = this.maxAgeMillis;
      TimedNode localTimedNode;
      for (Object localObject = this.head;; localObject = localTimedNode)
      {
        localTimedNode = (TimedNode)((TimedNode)localObject).get();
        if ((localTimedNode == null) || (localTimedNode.timestamp > l1 - l2)) {
          return (TimedNode<T>)localObject;
        }
      }
    }
    
    public void next(T paramT)
    {
      long l1 = this.scheduler.now();
      paramT = new TimedNode(paramT, l1);
      this.tail.set(paramT);
      this.tail = paramT;
      long l2 = this.maxAgeMillis;
      int i = this.size;
      TimedNode localTimedNode1 = this.head;
      paramT = localTimedNode1;
      if (i == this.limit) {
        paramT = (TimedNode)paramT.get();
      }
      for (;;)
      {
        TimedNode localTimedNode2 = (TimedNode)paramT.get();
        if ((localTimedNode2 == null) || (localTimedNode2.timestamp > l1 - l2))
        {
          this.size = i;
          if (paramT != localTimedNode1) {
            this.head = paramT;
          }
          return;
          i += 1;
        }
        else
        {
          paramT = localTimedNode2;
          i -= 1;
        }
      }
    }
    
    public int size()
    {
      int i = 0;
      TimedNode localTimedNode = (TimedNode)latestHead().get();
      while ((localTimedNode != null) && (i != Integer.MAX_VALUE))
      {
        localTimedNode = (TimedNode)localTimedNode.get();
        i += 1;
      }
      return i;
    }
    
    public T[] toArray(T[] paramArrayOfT)
    {
      ArrayList localArrayList = new ArrayList();
      for (TimedNode localTimedNode = (TimedNode)latestHead().get(); localTimedNode != null; localTimedNode = (TimedNode)localTimedNode.get()) {
        localArrayList.add(localTimedNode.value);
      }
      return localArrayList.toArray(paramArrayOfT);
    }
    
    static final class TimedNode<T>
      extends AtomicReference<TimedNode<T>>
    {
      private static final long serialVersionUID = 3713592843205853725L;
      final long timestamp;
      final T value;
      
      public TimedNode(T paramT, long paramLong)
      {
        this.value = paramT;
        this.timestamp = paramLong;
      }
    }
  }
  
  static final class ReplaySizeBoundBuffer<T>
    implements ReplaySubject.ReplayBuffer<T>
  {
    volatile boolean done;
    Throwable error;
    volatile Node<T> head;
    final int limit;
    int size;
    Node<T> tail;
    
    public ReplaySizeBoundBuffer(int paramInt)
    {
      this.limit = paramInt;
      Node localNode = new Node(null);
      this.tail = localNode;
      this.head = localNode;
    }
    
    public void complete()
    {
      this.done = true;
    }
    
    public boolean drain(ReplaySubject.ReplayProducer<T> paramReplayProducer)
    {
      if (paramReplayProducer.getAndIncrement() != 0) {
        return false;
      }
      Subscriber localSubscriber = paramReplayProducer.actual;
      int i = 1;
      long l3;
      int j;
      label143:
      label151:
      label253:
      label261:
      do
      {
        l3 = paramReplayProducer.requested.get();
        long l2 = 0L;
        Node localNode2 = (Node)paramReplayProducer.node;
        long l1 = l2;
        Node localNode1 = localNode2;
        if (localNode2 == null)
        {
          localNode1 = this.head;
          l1 = l2;
        }
        for (;;)
        {
          if (l1 != l3)
          {
            if (localSubscriber.isUnsubscribed())
            {
              paramReplayProducer.node = null;
              return false;
            }
            bool = this.done;
            localNode2 = (Node)localNode1.get();
            if (localNode2 == null)
            {
              j = 1;
              if ((!bool) || (j == 0)) {
                break label151;
              }
              paramReplayProducer.node = null;
              paramReplayProducer = this.error;
              if (paramReplayProducer == null) {
                break label143;
              }
              localSubscriber.onError(paramReplayProducer);
            }
            for (;;)
            {
              return false;
              j = 0;
              break;
              localSubscriber.onCompleted();
            }
            if (j == 0) {}
          }
          else
          {
            if (l1 != l3) {
              break label261;
            }
            if (!localSubscriber.isUnsubscribed()) {
              break;
            }
            paramReplayProducer.node = null;
            return false;
          }
          localSubscriber.onNext(localNode2.value);
          l1 += 1L;
          localNode1 = localNode2;
        }
        boolean bool = this.done;
        if (localNode1.get() == null)
        {
          j = 1;
          if ((!bool) || (j == 0)) {
            break label261;
          }
          paramReplayProducer.node = null;
          paramReplayProducer = this.error;
          if (paramReplayProducer == null) {
            break label253;
          }
          localSubscriber.onError(paramReplayProducer);
        }
        for (;;)
        {
          return false;
          j = 0;
          break;
          localSubscriber.onCompleted();
        }
        if ((l1 != 0L) && (l3 != Long.MAX_VALUE)) {
          BackpressureUtils.produced(paramReplayProducer.requested, l1);
        }
        paramReplayProducer.node = localNode1;
        j = paramReplayProducer.addAndGet(-i);
        i = j;
      } while (j != 0);
      return l3 == Long.MAX_VALUE;
    }
    
    public Throwable error()
    {
      return this.error;
    }
    
    public void error(Throwable paramThrowable)
    {
      this.error = paramThrowable;
      this.done = true;
    }
    
    public boolean isComplete()
    {
      return this.done;
    }
    
    public boolean isEmpty()
    {
      return this.head.get() == null;
    }
    
    public T last()
    {
      Node localNode;
      for (Object localObject = this.head;; localObject = localNode)
      {
        localNode = (Node)((Node)localObject).get();
        if (localNode == null) {
          break;
        }
      }
      return (T)((Node)localObject).value;
    }
    
    public void next(T paramT)
    {
      paramT = new Node(paramT);
      this.tail.set(paramT);
      this.tail = paramT;
      int i = this.size;
      if (i == this.limit)
      {
        this.head = ((Node)this.head.get());
        return;
      }
      this.size = (i + 1);
    }
    
    public int size()
    {
      int i = 0;
      Node localNode = (Node)this.head.get();
      while ((localNode != null) && (i != Integer.MAX_VALUE))
      {
        localNode = (Node)localNode.get();
        i += 1;
      }
      return i;
    }
    
    public T[] toArray(T[] paramArrayOfT)
    {
      ArrayList localArrayList = new ArrayList();
      for (Node localNode = (Node)this.head.get(); localNode != null; localNode = (Node)localNode.get()) {
        localArrayList.add(localNode.value);
      }
      return localArrayList.toArray(paramArrayOfT);
    }
    
    static final class Node<T>
      extends AtomicReference<Node<T>>
    {
      private static final long serialVersionUID = 3713592843205853725L;
      final T value;
      
      public Node(T paramT)
      {
        this.value = paramT;
      }
    }
  }
  
  static final class ReplayState<T>
    extends AtomicReference<ReplaySubject.ReplayProducer<T>[]>
    implements Observable.OnSubscribe<T>, Observer<T>
  {
    static final ReplaySubject.ReplayProducer[] EMPTY = new ReplaySubject.ReplayProducer[0];
    static final ReplaySubject.ReplayProducer[] TERMINATED = new ReplaySubject.ReplayProducer[0];
    private static final long serialVersionUID = 5952362471246910544L;
    final ReplaySubject.ReplayBuffer<T> buffer;
    
    public ReplayState(ReplaySubject.ReplayBuffer<T> paramReplayBuffer)
    {
      this.buffer = paramReplayBuffer;
      lazySet(EMPTY);
    }
    
    boolean add(ReplaySubject.ReplayProducer<T> paramReplayProducer)
    {
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer1;
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer2;
      do
      {
        arrayOfReplayProducer1 = (ReplaySubject.ReplayProducer[])get();
        if (arrayOfReplayProducer1 == TERMINATED) {
          return false;
        }
        int i = arrayOfReplayProducer1.length;
        arrayOfReplayProducer2 = new ReplaySubject.ReplayProducer[i + 1];
        System.arraycopy(arrayOfReplayProducer1, 0, arrayOfReplayProducer2, 0, i);
        arrayOfReplayProducer2[i] = paramReplayProducer;
      } while (!compareAndSet(arrayOfReplayProducer1, arrayOfReplayProducer2));
      return true;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      ReplaySubject.ReplayProducer localReplayProducer = new ReplaySubject.ReplayProducer(paramSubscriber, this);
      paramSubscriber.add(localReplayProducer);
      paramSubscriber.setProducer(localReplayProducer);
      if ((add(localReplayProducer)) && (localReplayProducer.isUnsubscribed()))
      {
        remove(localReplayProducer);
        return;
      }
      this.buffer.drain(localReplayProducer);
    }
    
    boolean isTerminated()
    {
      return get() == TERMINATED;
    }
    
    public void onCompleted()
    {
      ReplaySubject.ReplayBuffer localReplayBuffer = this.buffer;
      localReplayBuffer.complete();
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer = (ReplaySubject.ReplayProducer[])getAndSet(TERMINATED);
      int j = arrayOfReplayProducer.length;
      int i = 0;
      if (i < j)
      {
        ReplaySubject.ReplayProducer localReplayProducer = arrayOfReplayProducer[i];
        if (localReplayProducer.caughtUp) {
          localReplayProducer.actual.onCompleted();
        }
        for (;;)
        {
          i += 1;
          break;
          if (localReplayBuffer.drain(localReplayProducer))
          {
            localReplayProducer.caughtUp = true;
            localReplayProducer.node = null;
          }
        }
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      ReplaySubject.ReplayBuffer localReplayBuffer = this.buffer;
      localReplayBuffer.error(paramThrowable);
      Object localObject1 = null;
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer = (ReplaySubject.ReplayProducer[])getAndSet(TERMINATED);
      int j = arrayOfReplayProducer.length;
      int i = 0;
      for (;;)
      {
        Object localObject2;
        if (i < j)
        {
          ReplaySubject.ReplayProducer localReplayProducer = arrayOfReplayProducer[i];
          try
          {
            if (localReplayProducer.caughtUp)
            {
              localReplayProducer.actual.onError(paramThrowable);
              localObject2 = localObject1;
            }
            else
            {
              localObject2 = localObject1;
              if (localReplayBuffer.drain(localReplayProducer))
              {
                localReplayProducer.caughtUp = true;
                localReplayProducer.node = null;
                localObject2 = localObject1;
              }
            }
          }
          catch (Throwable localThrowable)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((List)localObject2).add(localThrowable);
          }
        }
        Exceptions.throwIfAny((List)localObject1);
        return;
        i += 1;
        localObject1 = localObject2;
      }
    }
    
    public void onNext(T paramT)
    {
      ReplaySubject.ReplayBuffer localReplayBuffer = this.buffer;
      localReplayBuffer.next(paramT);
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer = (ReplaySubject.ReplayProducer[])get();
      int j = arrayOfReplayProducer.length;
      int i = 0;
      if (i < j)
      {
        ReplaySubject.ReplayProducer localReplayProducer = arrayOfReplayProducer[i];
        if (localReplayProducer.caughtUp) {
          localReplayProducer.actual.onNext(paramT);
        }
        for (;;)
        {
          i += 1;
          break;
          if (localReplayBuffer.drain(localReplayProducer))
          {
            localReplayProducer.caughtUp = true;
            localReplayProducer.node = null;
          }
        }
      }
    }
    
    void remove(ReplaySubject.ReplayProducer<T> paramReplayProducer)
    {
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer2 = (ReplaySubject.ReplayProducer[])get();
      if ((arrayOfReplayProducer2 == TERMINATED) || (arrayOfReplayProducer2 == EMPTY)) {}
      int m;
      int i;
      label36:
      int j;
      do
      {
        return;
        m = arrayOfReplayProducer2.length;
        int k = -1;
        i = 0;
        j = k;
        if (i < m)
        {
          if (arrayOfReplayProducer2[i] != paramReplayProducer) {
            break;
          }
          j = i;
        }
      } while (j < 0);
      ReplaySubject.ReplayProducer[] arrayOfReplayProducer1;
      if (m == 1) {
        arrayOfReplayProducer1 = EMPTY;
      }
      while (compareAndSet(arrayOfReplayProducer2, arrayOfReplayProducer1))
      {
        return;
        i += 1;
        break label36;
        arrayOfReplayProducer1 = new ReplaySubject.ReplayProducer[m - 1];
        System.arraycopy(arrayOfReplayProducer2, 0, arrayOfReplayProducer1, 0, j);
        System.arraycopy(arrayOfReplayProducer2, j + 1, arrayOfReplayProducer1, j, m - j - 1);
      }
    }
  }
  
  static final class ReplayUnboundedBuffer<T>
    implements ReplaySubject.ReplayBuffer<T>
  {
    final int capacity;
    volatile boolean done;
    Throwable error;
    final Object[] head;
    volatile int size;
    Object[] tail;
    int tailIndex;
    
    public ReplayUnboundedBuffer(int paramInt)
    {
      this.capacity = paramInt;
      Object[] arrayOfObject = new Object[paramInt + 1];
      this.head = arrayOfObject;
      this.tail = arrayOfObject;
    }
    
    public void complete()
    {
      this.done = true;
    }
    
    public boolean drain(ReplaySubject.ReplayProducer<T> paramReplayProducer)
    {
      if (paramReplayProducer.getAndIncrement() != 0) {
        return false;
      }
      int j = 1;
      Subscriber localSubscriber = paramReplayProducer.actual;
      int n = this.capacity;
      long l2;
      int i;
      label152:
      label160:
      label306:
      label314:
      do
      {
        l2 = paramReplayProducer.requested.get();
        long l1 = 0L;
        Object localObject2 = (Object[])paramReplayProducer.node;
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = this.head;
        }
        i = paramReplayProducer.tailIndex;
        int k = paramReplayProducer.index;
        int m;
        for (;;)
        {
          if (l1 != l2)
          {
            if (localSubscriber.isUnsubscribed())
            {
              paramReplayProducer.node = null;
              return false;
            }
            bool = this.done;
            if (k == this.size)
            {
              m = 1;
              if ((!bool) || (m == 0)) {
                break label160;
              }
              paramReplayProducer.node = null;
              paramReplayProducer = this.error;
              if (paramReplayProducer == null) {
                break label152;
              }
              localSubscriber.onError(paramReplayProducer);
            }
            for (;;)
            {
              return false;
              m = 0;
              break;
              localSubscriber.onCompleted();
            }
            if (m == 0) {}
          }
          else
          {
            if (l1 != l2) {
              break label314;
            }
            if (!localSubscriber.isUnsubscribed()) {
              break;
            }
            paramReplayProducer.node = null;
            return false;
          }
          localObject2 = localObject1;
          m = i;
          if (i == n)
          {
            localObject2 = (Object[])localObject1[i];
            m = 0;
          }
          localSubscriber.onNext(localObject2[m]);
          l1 += 1L;
          i = m + 1;
          k += 1;
          localObject1 = localObject2;
        }
        boolean bool = this.done;
        if (k == this.size)
        {
          m = 1;
          if ((!bool) || (m == 0)) {
            break label314;
          }
          paramReplayProducer.node = null;
          paramReplayProducer = this.error;
          if (paramReplayProducer == null) {
            break label306;
          }
          localSubscriber.onError(paramReplayProducer);
        }
        for (;;)
        {
          return false;
          m = 0;
          break;
          localSubscriber.onCompleted();
        }
        if ((l1 != 0L) && (l2 != Long.MAX_VALUE)) {
          BackpressureUtils.produced(paramReplayProducer.requested, l1);
        }
        paramReplayProducer.index = k;
        paramReplayProducer.tailIndex = i;
        paramReplayProducer.node = localObject1;
        i = paramReplayProducer.addAndGet(-j);
        j = i;
      } while (i != 0);
      return l2 == Long.MAX_VALUE;
    }
    
    public Throwable error()
    {
      return this.error;
    }
    
    public void error(Throwable paramThrowable)
    {
      if (this.done)
      {
        RxJavaPluginUtils.handleException(paramThrowable);
        return;
      }
      this.error = paramThrowable;
      this.done = true;
    }
    
    public boolean isComplete()
    {
      return this.done;
    }
    
    public boolean isEmpty()
    {
      return this.size == 0;
    }
    
    public T last()
    {
      int i = this.size;
      if (i == 0) {
        return null;
      }
      Object[] arrayOfObject = this.head;
      int j = this.capacity;
      while (i >= j)
      {
        arrayOfObject = (Object[])arrayOfObject[j];
        i -= j;
      }
      return (T)arrayOfObject[(i - 1)];
    }
    
    public void next(T paramT)
    {
      if (this.done) {
        return;
      }
      int i = this.tailIndex;
      Object[] arrayOfObject1 = this.tail;
      if (i == arrayOfObject1.length - 1)
      {
        Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
        arrayOfObject2[0] = paramT;
        this.tailIndex = 1;
        arrayOfObject1[i] = arrayOfObject2;
        this.tail = arrayOfObject2;
      }
      for (;;)
      {
        this.size += 1;
        return;
        arrayOfObject1[i] = paramT;
        this.tailIndex = (i + 1);
      }
    }
    
    public int size()
    {
      return this.size;
    }
    
    public T[] toArray(T[] paramArrayOfT)
    {
      int j = this.size;
      Object localObject = paramArrayOfT;
      if (paramArrayOfT.length < j) {
        localObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), j);
      }
      paramArrayOfT = this.head;
      int k = this.capacity;
      int i = 0;
      while (i + k < j)
      {
        System.arraycopy(paramArrayOfT, 0, localObject, i, k);
        i += k;
        paramArrayOfT = (Object[])paramArrayOfT[k];
      }
      System.arraycopy(paramArrayOfT, 0, localObject, i, j - i);
      if (localObject.length > j) {
        localObject[j] = null;
      }
      return (T[])localObject;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\subjects\ReplaySubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */