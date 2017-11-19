package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  final boolean delayErrors;
  final int maxConcurrent;
  
  OperatorMerge(boolean paramBoolean, int paramInt)
  {
    this.delayErrors = paramBoolean;
    this.maxConcurrent = paramInt;
  }
  
  public static <T> OperatorMerge<T> instance(boolean paramBoolean)
  {
    if (paramBoolean) {
      return HolderDelayErrors.INSTANCE;
    }
    return HolderNoDelay.INSTANCE;
  }
  
  public static <T> OperatorMerge<T> instance(boolean paramBoolean, int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + paramInt);
    }
    if (paramInt == Integer.MAX_VALUE) {
      return instance(paramBoolean);
    }
    return new OperatorMerge(paramBoolean, paramInt);
  }
  
  public Subscriber<Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    MergeSubscriber localMergeSubscriber = new MergeSubscriber(paramSubscriber, this.delayErrors, this.maxConcurrent);
    MergeProducer localMergeProducer = new MergeProducer(localMergeSubscriber);
    localMergeSubscriber.producer = localMergeProducer;
    paramSubscriber.add(localMergeSubscriber);
    paramSubscriber.setProducer(localMergeProducer);
    return localMergeSubscriber;
  }
  
  private static final class HolderDelayErrors
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(true, Integer.MAX_VALUE);
  }
  
  private static final class HolderNoDelay
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(false, Integer.MAX_VALUE);
  }
  
  static final class InnerSubscriber<T>
    extends Subscriber<T>
  {
    static final int limit = RxRingBuffer.SIZE / 4;
    volatile boolean done;
    final long id;
    int outstanding;
    final OperatorMerge.MergeSubscriber<T> parent;
    volatile RxRingBuffer queue;
    
    public InnerSubscriber(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber, long paramLong)
    {
      this.parent = paramMergeSubscriber;
      this.id = paramLong;
    }
    
    public void onCompleted()
    {
      this.done = true;
      this.parent.emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.done = true;
      this.parent.getOrCreateErrorQueue().offer(paramThrowable);
      this.parent.emit();
    }
    
    public void onNext(T paramT)
    {
      this.parent.tryEmit(this, paramT);
    }
    
    public void onStart()
    {
      this.outstanding = RxRingBuffer.SIZE;
      request(RxRingBuffer.SIZE);
    }
    
    public void requestMore(long paramLong)
    {
      int i = this.outstanding - (int)paramLong;
      if (i > limit) {
        this.outstanding = i;
      }
      do
      {
        return;
        this.outstanding = RxRingBuffer.SIZE;
        i = RxRingBuffer.SIZE - i;
      } while (i <= 0);
      request(i);
    }
  }
  
  static final class MergeProducer<T>
    extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -1214379189873595503L;
    final OperatorMerge.MergeSubscriber<T> subscriber;
    
    public MergeProducer(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber)
    {
      this.subscriber = paramMergeSubscriber;
    }
    
    public long produced(int paramInt)
    {
      return addAndGet(-paramInt);
    }
    
    public void request(long paramLong)
    {
      if (paramLong > 0L) {
        if (get() != Long.MAX_VALUE) {}
      }
      while (paramLong >= 0L)
      {
        return;
        BackpressureUtils.getAndAddRequest(this, paramLong);
        this.subscriber.emit();
        return;
      }
      throw new IllegalArgumentException("n >= 0 required");
    }
  }
  
  static final class MergeSubscriber<T>
    extends Subscriber<Observable<? extends T>>
  {
    static final OperatorMerge.InnerSubscriber<?>[] EMPTY = new OperatorMerge.InnerSubscriber[0];
    final Subscriber<? super T> child;
    final boolean delayErrors;
    volatile boolean done;
    boolean emitting;
    volatile ConcurrentLinkedQueue<Throwable> errors;
    final Object innerGuard;
    volatile OperatorMerge.InnerSubscriber<?>[] innerSubscribers;
    long lastId;
    int lastIndex;
    final int maxConcurrent;
    boolean missed;
    final NotificationLite<T> nl;
    OperatorMerge.MergeProducer<T> producer;
    volatile Queue<Object> queue;
    int scalarEmissionCount;
    final int scalarEmissionLimit;
    volatile CompositeSubscription subscriptions;
    long uniqueId;
    
    public MergeSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean, int paramInt)
    {
      this.child = paramSubscriber;
      this.delayErrors = paramBoolean;
      this.maxConcurrent = paramInt;
      this.nl = NotificationLite.instance();
      this.innerGuard = new Object();
      this.innerSubscribers = EMPTY;
      if (paramInt == Integer.MAX_VALUE)
      {
        this.scalarEmissionLimit = Integer.MAX_VALUE;
        request(Long.MAX_VALUE);
        return;
      }
      this.scalarEmissionLimit = Math.max(1, paramInt >> 1);
      request(paramInt);
    }
    
    private void reportError()
    {
      ArrayList localArrayList = new ArrayList(this.errors);
      if (localArrayList.size() == 1)
      {
        this.child.onError((Throwable)localArrayList.get(0));
        return;
      }
      this.child.onError(new CompositeException(localArrayList));
    }
    
    void addInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      getOrCreateComposite().add(paramInnerSubscriber);
      for (;;)
      {
        int i;
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber2;
        synchronized (this.innerGuard)
        {
          OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber1 = this.innerSubscribers;
          i = arrayOfInnerSubscriber1.length;
          arrayOfInnerSubscriber2 = new OperatorMerge.InnerSubscriber[i + 1];
          System.arraycopy(arrayOfInnerSubscriber1, 0, arrayOfInnerSubscriber2, 0, i);
          break label60;
          this.innerSubscribers = arrayOfInnerSubscriber2;
          return;
        }
        label60:
        arrayOfInnerSubscriber2[i] = paramInnerSubscriber;
      }
    }
    
    boolean checkTerminate()
    {
      if (this.child.isUnsubscribed()) {
        return true;
      }
      ConcurrentLinkedQueue localConcurrentLinkedQueue = this.errors;
      if ((!this.delayErrors) && (localConcurrentLinkedQueue != null) && (!localConcurrentLinkedQueue.isEmpty())) {
        try
        {
          reportError();
          return true;
        }
        finally
        {
          unsubscribe();
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
    
    void emitEmpty()
    {
      int i = this.scalarEmissionCount + 1;
      if (i == this.scalarEmissionLimit)
      {
        this.scalarEmissionCount = 0;
        requestMore(i);
        return;
      }
      this.scalarEmissionCount = i;
    }
    
    void emitLoop()
    {
      int i5 = 0;
      int i4 = 0;
      int j = i4;
      for (;;)
      {
        Subscriber localSubscriber;
        Object localObject18;
        long l1;
        label74:
        int i1;
        int i;
        try
        {
          localSubscriber = this.child;
          j = i4;
          bool = checkTerminate();
          if (bool)
          {
            if (1 != 0) {
              break label1054;
            }
            try
            {
              this.emitting = false;
              return;
            }
            finally {}
          }
          j = i4;
          localObject18 = this.queue;
          j = i4;
          l1 = this.producer.get();
        }
        finally
        {
          Object localObject2;
          Object localObject4;
          Object localObject19;
          if (j != 0) {
            break label426;
          }
        }
        j = i4;
        localObject2 = ((Queue)localObject18).poll();
        j = i4;
        boolean bool = checkTerminate();
        label121:
        label132:
        int k;
        long l2;
        label176:
        int i6;
        if (bool)
        {
          if (1 == 0)
          {
            try
            {
              this.emitting = false;
              return;
            }
            finally {}
            i1 = 0;
            break label1067;
          }
        }
        else
        {
          if (localObject3 == null)
          {
            if (i > 0)
            {
              if (i1 == 0) {
                break label429;
              }
              l1 = Long.MAX_VALUE;
            }
            i = k;
            l2 = l1;
            if (l1 != 0L)
            {
              j = k;
              l2 = l1;
              if (localObject3 != null) {
                break label1084;
              }
              l2 = l1;
              i = k;
            }
            j = i4;
            bool = this.done;
            j = i4;
            localObject4 = this.queue;
            j = i4;
            localObject19 = this.innerSubscribers;
            j = i4;
            i6 = localObject19.length;
            if (!bool) {
              break label1118;
            }
            if (localObject4 == null) {
              break label1105;
            }
            j = i4;
            if (!((Queue)localObject4).isEmpty()) {
              break label1118;
            }
            break label1105;
            label237:
            j = i4;
            localObject4 = this.errors;
            if (localObject4 != null)
            {
              j = i4;
              if (!((Queue)localObject4).isEmpty()) {
                break label445;
              }
            }
            j = i4;
            localSubscriber.onCompleted();
            if (1 != 0) {
              break label1054;
            }
            try
            {
              this.emitting = false;
              return;
            }
            finally {}
          }
          j = i4;
          localObject19 = this.nl.getValue(localObject5);
          j = i4;
          try
          {
            localSubscriber.onNext(localObject19);
            k += 1;
            i += 1;
            l1 -= 1L;
          }
          catch (Throwable localThrowable2)
          {
            j = i4;
            if (this.delayErrors) {
              continue;
            }
          }
          j = i4;
          Exceptions.throwIfFatal(localThrowable2);
          break label1113;
          label357:
          j = i;
          unsubscribe();
          j = i;
          localSubscriber.onError(localThrowable2);
          if (1 == 0)
          {
            try
            {
              this.emitting = false;
              return;
            }
            finally {}
            j = i4;
            getOrCreateErrorQueue().offer(localThrowable2);
            continue;
          }
        }
        label426:
        label429:
        label445:
        label608:
        label694:
        label1054:
        label1067:
        label1084:
        label1105:
        label1113:
        label1118:
        label1135:
        label1152:
        label1162:
        label1168:
        label1183:
        label1199:
        label1206:
        label1227:
        label1249:
        label1272:
        label1284:
        label1292:
        label1300:
        label1310:
        label1318:
        label1326:
        try
        {
          this.emitting = false;
          throw ((Throwable)localObject7);
          j = i4;
          l1 = this.producer.produced(i);
          continue;
          j = i4;
          reportError();
          continue;
          j = i4;
          l1 = this.lastId;
          j = i4;
          m = this.lastIndex;
          if (i6 > m)
          {
            j = i4;
            k = m;
            if (localThrowable2[m].id == l1) {
              break label1168;
            }
            break label1135;
            j = i4;
            if (localThrowable2[k].id == l1)
            {
              break label1162;
              j = i4;
              this.lastIndex = k;
              j = i4;
              this.lastId = localThrowable2[k].id;
              k = m;
              break label1168;
              j = i4;
              bool = checkTerminate();
              if (!bool) {
                break label608;
              }
              if (1 != 0) {
                break label1054;
              }
              try
              {
                this.emitting = false;
                return;
              }
              finally {}
            }
            j = k + 1;
            k = j;
            if (j == i6) {
              k = 0;
            }
            m += 1;
            break label1152;
            OperatorMerge.InnerSubscriber localInnerSubscriber = localThrowable2[i3];
            break label1199;
            j = i4;
            bool = checkTerminate();
            if (bool)
            {
              if (1 != 0) {
                break label1054;
              }
              try
              {
                this.emitting = false;
                return;
              }
              finally {}
            }
            j = i4;
            RxRingBuffer localRxRingBuffer = localInnerSubscriber.queue;
            break label1227;
            j = i4;
            l1 = this.producer.produced(m);
            j = i4;
            localInnerSubscriber.requestMore(m);
            break label1249;
            j = i4;
            bool = localInnerSubscriber.done;
            j = i4;
            localRxRingBuffer = localInnerSubscriber.queue;
            n = k;
            m = i;
            if (!bool) {
              break label1300;
            }
            if (localRxRingBuffer != null)
            {
              j = i4;
              n = k;
              m = i;
              if (!localRxRingBuffer.isEmpty()) {
                break label1300;
              }
            }
            j = i4;
            removeInner(localInnerSubscriber);
            j = i4;
            bool = checkTerminate();
            if (!bool) {
              break label1292;
            }
            if (1 == 0)
            {
              try
              {
                this.emitting = false;
                return;
              }
              finally {}
              j = i4;
              localObject18 = ((RxRingBuffer)localObject10).poll();
              break label1272;
              j = i4;
              Object localObject11 = this.nl.getValue(localObject18);
              j = i4;
              try
              {
                localSubscriber.onNext(localObject11);
                l1 -= 1L;
                m += 1;
              }
              catch (Throwable localThrowable1)
              {
                i = 1;
                j = i;
                Exceptions.throwIfFatal(localThrowable1);
                try
                {
                  localSubscriber.onError(localThrowable1);
                  j = i;
                  unsubscribe();
                  if (1 != 0) {
                    break label1054;
                  }
                  try
                  {
                    this.emitting = false;
                    return;
                  }
                  finally {}
                  j = i4;
                }
                finally
                {
                  j = i;
                  unsubscribe();
                  j = i;
                }
              }
              this.lastIndex = i3;
              j = i4;
              this.lastId = localThrowable2[i3].id;
              break label1310;
              j = i4;
              request(m);
              break label1318;
              j = i4;
              j = i5;
              try
              {
                if (!this.missed)
                {
                  break label1326;
                  j = i;
                  this.emitting = false;
                  j = i;
                  if (1 == 0)
                  {
                    try
                    {
                      this.emitting = false;
                      return;
                    }
                    finally {}
                    j = i3 + 1;
                    i = j;
                    if (j == i6) {
                      i = 0;
                    }
                    i2 += 1;
                    k = n;
                    i3 = i;
                    i = m;
                    break label1183;
                  }
                }
                else
                {
                  j = i5;
                  this.missed = false;
                  j = i5;
                  continue;
                }
              }
              finally {}
            }
          }
        }
        finally
        {
          for (;;)
          {
            int i3;
            throw ((Throwable)localObject16);
            return;
            if (l1 != Long.MAX_VALUE) {
              break label121;
            }
            i1 = 1;
            j = 0;
            i = 0;
            l2 = l1;
            if (localObject18 == null) {
              break label176;
            }
            l2 = l1;
            i = 0;
            Object localObject17 = null;
            l1 = l2;
            k = j;
            if (l1 <= 0L) {
              break label132;
            }
            break label74;
            if (i6 == 0)
            {
              break label237;
              i = 1;
              break label357;
            }
            int n = 0;
            int i2 = 0;
            int m = i;
            if (i6 > 0)
            {
              continue;
              j = m;
              if (i6 <= m) {
                j = 0;
              }
              m = 0;
              k = j;
              if (m >= i6)
              {
                m = k;
                continue;
                j = 0;
                l1 = l2;
                i3 = k;
                k = i2;
                i2 = j;
                n = k;
                m = i;
                if (i2 < i6)
                {
                  continue;
                  localObject18 = null;
                  l2 = l1;
                  m = 0;
                  l1 = l2;
                  localObject17 = localObject18;
                  if (l1 > 0L)
                  {
                    continue;
                    if (localObject17 == null) {
                      localObject17 = localObject18;
                    }
                  }
                  else
                  {
                    do
                    {
                      if (m > 0)
                      {
                        if (i1 != 0) {
                          break label1284;
                        }
                        break;
                      }
                      if (l1 == 0L) {
                        break label694;
                      }
                      localObject18 = localObject17;
                      l2 = l1;
                      if (localObject17 != null) {
                        break label1206;
                      }
                      break label694;
                      localObject17 = localObject18;
                    } while (localObject18 == null);
                    continue;
                    l1 = Long.MAX_VALUE;
                    continue;
                    m = i + 1;
                    n = 1;
                    if (l1 == 0L) {}
                  }
                }
              }
            }
            else if (m <= 0)
            {
              if (n != 0) {
                break;
              }
            }
          }
        }
      }
    }
    
    /* Error */
    protected void emitScalar(T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 5
      //   3: iload 5
      //   5: istore 4
      //   7: aload_0
      //   8: getfield 57	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   11: aload_1
      //   12: invokevirtual 196	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   15: lload_2
      //   16: ldc2_w 80
      //   19: lcmp
      //   20: ifeq +16 -> 36
      //   23: iload 5
      //   25: istore 4
      //   27: aload_0
      //   28: getfield 174	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   31: iconst_1
      //   32: invokevirtual 213	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   35: pop2
      //   36: iload 5
      //   38: istore 4
      //   40: aload_0
      //   41: getfield 165	rx/internal/operators/OperatorMerge$MergeSubscriber:scalarEmissionCount	I
      //   44: iconst_1
      //   45: iadd
      //   46: istore 6
      //   48: iload 5
      //   50: istore 4
      //   52: iload 6
      //   54: aload_0
      //   55: getfield 79	rx/internal/operators/OperatorMerge$MergeSubscriber:scalarEmissionLimit	I
      //   58: if_icmpne +154 -> 212
      //   61: iload 5
      //   63: istore 4
      //   65: aload_0
      //   66: iconst_0
      //   67: putfield 165	rx/internal/operators/OperatorMerge$MergeSubscriber:scalarEmissionCount	I
      //   70: iload 5
      //   72: istore 4
      //   74: aload_0
      //   75: iload 6
      //   77: i2l
      //   78: invokevirtual 168	rx/internal/operators/OperatorMerge$MergeSubscriber:requestMore	(J)V
      //   81: iload 5
      //   83: istore 4
      //   85: aload_0
      //   86: monitorenter
      //   87: iconst_1
      //   88: istore 4
      //   90: aload_0
      //   91: getfield 159	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   94: ifne +136 -> 230
      //   97: aload_0
      //   98: iconst_0
      //   99: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   102: aload_0
      //   103: monitorexit
      //   104: iconst_1
      //   105: ifne +12 -> 117
      //   108: aload_0
      //   109: monitorenter
      //   110: aload_0
      //   111: iconst_0
      //   112: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   115: aload_0
      //   116: monitorexit
      //   117: return
      //   118: astore_1
      //   119: iload 5
      //   121: istore 4
      //   123: aload_0
      //   124: getfield 59	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   127: ifne +50 -> 177
      //   130: iload 5
      //   132: istore 4
      //   134: aload_1
      //   135: invokestatic 201	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   138: goto +132 -> 270
      //   141: iload 5
      //   143: istore 4
      //   145: aload_0
      //   146: invokevirtual 154	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   149: iload 5
      //   151: istore 4
      //   153: aload_0
      //   154: aload_1
      //   155: invokevirtual 235	rx/internal/operators/OperatorMerge$MergeSubscriber:onError	(Ljava/lang/Throwable;)V
      //   158: iconst_1
      //   159: ifne -42 -> 117
      //   162: aload_0
      //   163: monitorenter
      //   164: aload_0
      //   165: iconst_0
      //   166: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   169: aload_0
      //   170: monitorexit
      //   171: return
      //   172: astore_1
      //   173: aload_0
      //   174: monitorexit
      //   175: aload_1
      //   176: athrow
      //   177: iload 5
      //   179: istore 4
      //   181: aload_0
      //   182: invokevirtual 205	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   185: aload_1
      //   186: invokeinterface 209 2 0
      //   191: pop
      //   192: goto -177 -> 15
      //   195: astore_1
      //   196: iload 4
      //   198: ifne +12 -> 210
      //   201: aload_0
      //   202: monitorenter
      //   203: aload_0
      //   204: iconst_0
      //   205: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   208: aload_0
      //   209: monitorexit
      //   210: aload_1
      //   211: athrow
      //   212: iload 5
      //   214: istore 4
      //   216: aload_0
      //   217: iload 6
      //   219: putfield 165	rx/internal/operators/OperatorMerge$MergeSubscriber:scalarEmissionCount	I
      //   222: goto -141 -> 81
      //   225: astore_1
      //   226: aload_0
      //   227: monitorexit
      //   228: aload_1
      //   229: athrow
      //   230: aload_0
      //   231: iconst_0
      //   232: putfield 159	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   235: aload_0
      //   236: monitorexit
      //   237: iconst_1
      //   238: ifne +12 -> 250
      //   241: aload_0
      //   242: monitorenter
      //   243: aload_0
      //   244: iconst_0
      //   245: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   248: aload_0
      //   249: monitorexit
      //   250: aload_0
      //   251: invokevirtual 162	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   254: return
      //   255: astore_1
      //   256: aload_0
      //   257: monitorexit
      //   258: aload_1
      //   259: athrow
      //   260: astore_1
      //   261: aload_0
      //   262: monitorexit
      //   263: aload_1
      //   264: athrow
      //   265: astore_1
      //   266: aload_0
      //   267: monitorexit
      //   268: aload_1
      //   269: athrow
      //   270: iconst_1
      //   271: istore 5
      //   273: goto -132 -> 141
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	276	0	this	MergeSubscriber
      //   0	276	1	paramT	T
      //   0	276	2	paramLong	long
      //   5	210	4	i	int
      //   1	271	5	j	int
      //   46	172	6	k	int
      // Exception table:
      //   from	to	target	type
      //   7	15	118	java/lang/Throwable
      //   164	171	172	finally
      //   173	175	172	finally
      //   7	15	195	finally
      //   27	36	195	finally
      //   40	48	195	finally
      //   52	61	195	finally
      //   65	70	195	finally
      //   74	81	195	finally
      //   85	87	195	finally
      //   123	130	195	finally
      //   134	138	195	finally
      //   145	149	195	finally
      //   153	158	195	finally
      //   181	192	195	finally
      //   216	222	195	finally
      //   258	260	195	finally
      //   110	117	225	finally
      //   226	228	225	finally
      //   90	104	255	finally
      //   230	237	255	finally
      //   256	258	255	finally
      //   243	250	260	finally
      //   261	263	260	finally
      //   203	210	265	finally
      //   266	268	265	finally
    }
    
    /* Error */
    protected void emitScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 6
      //   3: iload 6
      //   5: istore 5
      //   7: aload_0
      //   8: getfield 57	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   11: aload_2
      //   12: invokevirtual 196	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   15: lload_3
      //   16: ldc2_w 80
      //   19: lcmp
      //   20: ifeq +16 -> 36
      //   23: iload 6
      //   25: istore 5
      //   27: aload_0
      //   28: getfield 174	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   31: iconst_1
      //   32: invokevirtual 213	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   35: pop2
      //   36: iload 6
      //   38: istore 5
      //   40: aload_1
      //   41: lconst_1
      //   42: invokevirtual 224	rx/internal/operators/OperatorMerge$InnerSubscriber:requestMore	(J)V
      //   45: iload 6
      //   47: istore 5
      //   49: aload_0
      //   50: monitorenter
      //   51: iconst_1
      //   52: istore 5
      //   54: aload_0
      //   55: getfield 159	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   58: ifne +123 -> 181
      //   61: aload_0
      //   62: iconst_0
      //   63: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   66: aload_0
      //   67: monitorexit
      //   68: iconst_1
      //   69: ifne +12 -> 81
      //   72: aload_0
      //   73: monitorenter
      //   74: aload_0
      //   75: iconst_0
      //   76: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   79: aload_0
      //   80: monitorexit
      //   81: return
      //   82: astore_2
      //   83: iload 6
      //   85: istore 5
      //   87: aload_0
      //   88: getfield 59	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   91: ifne +50 -> 141
      //   94: iload 6
      //   96: istore 5
      //   98: aload_2
      //   99: invokestatic 201	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   102: goto +119 -> 221
      //   105: iload 6
      //   107: istore 5
      //   109: aload_1
      //   110: invokevirtual 238	rx/internal/operators/OperatorMerge$InnerSubscriber:unsubscribe	()V
      //   113: iload 6
      //   115: istore 5
      //   117: aload_1
      //   118: aload_2
      //   119: invokevirtual 239	rx/internal/operators/OperatorMerge$InnerSubscriber:onError	(Ljava/lang/Throwable;)V
      //   122: iconst_1
      //   123: ifne -42 -> 81
      //   126: aload_0
      //   127: monitorenter
      //   128: aload_0
      //   129: iconst_0
      //   130: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   133: aload_0
      //   134: monitorexit
      //   135: return
      //   136: astore_1
      //   137: aload_0
      //   138: monitorexit
      //   139: aload_1
      //   140: athrow
      //   141: iload 6
      //   143: istore 5
      //   145: aload_0
      //   146: invokevirtual 205	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   149: aload_2
      //   150: invokeinterface 209 2 0
      //   155: pop
      //   156: goto -141 -> 15
      //   159: astore_1
      //   160: iload 5
      //   162: ifne +12 -> 174
      //   165: aload_0
      //   166: monitorenter
      //   167: aload_0
      //   168: iconst_0
      //   169: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   172: aload_0
      //   173: monitorexit
      //   174: aload_1
      //   175: athrow
      //   176: astore_1
      //   177: aload_0
      //   178: monitorexit
      //   179: aload_1
      //   180: athrow
      //   181: aload_0
      //   182: iconst_0
      //   183: putfield 159	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   186: aload_0
      //   187: monitorexit
      //   188: iconst_1
      //   189: ifne +12 -> 201
      //   192: aload_0
      //   193: monitorenter
      //   194: aload_0
      //   195: iconst_0
      //   196: putfield 157	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   199: aload_0
      //   200: monitorexit
      //   201: aload_0
      //   202: invokevirtual 162	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   205: return
      //   206: astore_1
      //   207: aload_0
      //   208: monitorexit
      //   209: aload_1
      //   210: athrow
      //   211: astore_1
      //   212: aload_0
      //   213: monitorexit
      //   214: aload_1
      //   215: athrow
      //   216: astore_1
      //   217: aload_0
      //   218: monitorexit
      //   219: aload_1
      //   220: athrow
      //   221: iconst_1
      //   222: istore 6
      //   224: goto -119 -> 105
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	227	0	this	MergeSubscriber
      //   0	227	1	paramInnerSubscriber	OperatorMerge.InnerSubscriber<T>
      //   0	227	2	paramT	T
      //   0	227	3	paramLong	long
      //   5	156	5	i	int
      //   1	222	6	j	int
      // Exception table:
      //   from	to	target	type
      //   7	15	82	java/lang/Throwable
      //   128	135	136	finally
      //   137	139	136	finally
      //   7	15	159	finally
      //   27	36	159	finally
      //   40	45	159	finally
      //   49	51	159	finally
      //   87	94	159	finally
      //   98	102	159	finally
      //   109	113	159	finally
      //   117	122	159	finally
      //   145	156	159	finally
      //   209	211	159	finally
      //   74	81	176	finally
      //   177	179	176	finally
      //   54	68	206	finally
      //   181	188	206	finally
      //   207	209	206	finally
      //   194	201	211	finally
      //   212	214	211	finally
      //   167	174	216	finally
      //   217	219	216	finally
    }
    
    CompositeSubscription getOrCreateComposite()
    {
      CompositeSubscription localCompositeSubscription1 = this.subscriptions;
      CompositeSubscription localCompositeSubscription2 = localCompositeSubscription1;
      int i;
      if (localCompositeSubscription1 == null) {
        i = 0;
      }
      for (;;)
      {
        try
        {
          localCompositeSubscription2 = this.subscriptions;
        }
        finally
        {
          try
          {
            this.subscriptions = localCompositeSubscription1;
            i = 1;
            localCompositeSubscription2 = localCompositeSubscription1;
            if (i != 0)
            {
              add(localCompositeSubscription1);
              localCompositeSubscription2 = localCompositeSubscription1;
            }
            return localCompositeSubscription2;
          }
          finally {}
          localObject1 = finally;
        }
        localCompositeSubscription1 = new CompositeSubscription();
        Object localObject3 = localCompositeSubscription2;
        if (localCompositeSubscription2 != null) {}
      }
    }
    
    Queue<Throwable> getOrCreateErrorQueue()
    {
      ConcurrentLinkedQueue localConcurrentLinkedQueue1 = this.errors;
      if (localConcurrentLinkedQueue1 == null) {
        for (;;)
        {
          try
          {
            localConcurrentLinkedQueue2 = this.errors;
          }
          finally
          {
            ConcurrentLinkedQueue localConcurrentLinkedQueue2;
            Object localObject2;
            continue;
          }
          localConcurrentLinkedQueue1 = new ConcurrentLinkedQueue();
          try
          {
            this.errors = localConcurrentLinkedQueue1;
            return localConcurrentLinkedQueue1;
          }
          finally {}
          throw localConcurrentLinkedQueue1;
          localObject2 = localConcurrentLinkedQueue2;
          if (localConcurrentLinkedQueue2 != null) {}
        }
      }
      return (Queue<Throwable>)localObject2;
    }
    
    public void onCompleted()
    {
      this.done = true;
      emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      getOrCreateErrorQueue().offer(paramThrowable);
      this.done = true;
      emit();
    }
    
    public void onNext(Observable<? extends T> paramObservable)
    {
      if (paramObservable == null) {
        return;
      }
      if (paramObservable == Observable.empty())
      {
        emitEmpty();
        return;
      }
      if ((paramObservable instanceof ScalarSynchronousObservable))
      {
        tryEmit(((ScalarSynchronousObservable)paramObservable).get());
        return;
      }
      long l = this.uniqueId;
      this.uniqueId = (1L + l);
      OperatorMerge.InnerSubscriber localInnerSubscriber = new OperatorMerge.InnerSubscriber(this, l);
      addInner(localInnerSubscriber);
      paramObservable.unsafeSubscribe(localInnerSubscriber);
      emit();
    }
    
    protected void queueScalar(T paramT)
    {
      Queue localQueue = this.queue;
      Object localObject = localQueue;
      int i;
      if (localQueue == null)
      {
        i = this.maxConcurrent;
        if (i != Integer.MAX_VALUE) {
          break label78;
        }
        localObject = new SpscUnboundedAtomicArrayQueue(RxRingBuffer.SIZE);
      }
      for (;;)
      {
        this.queue = ((Queue)localObject);
        if (((Queue)localObject).offer(this.nl.next(paramT))) {
          break;
        }
        unsubscribe();
        onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), paramT));
        return;
        label78:
        if (Pow2.isPowerOfTwo(i))
        {
          if (UnsafeAccess.isUnsafeAvailable()) {
            localObject = new SpscArrayQueue(i);
          } else {
            localObject = new SpscAtomicArrayQueue(i);
          }
        }
        else {
          localObject = new SpscExactAtomicArrayQueue(i);
        }
      }
      emit();
    }
    
    protected void queueScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      RxRingBuffer localRxRingBuffer2 = paramInnerSubscriber.queue;
      RxRingBuffer localRxRingBuffer1 = localRxRingBuffer2;
      if (localRxRingBuffer2 == null)
      {
        localRxRingBuffer1 = RxRingBuffer.getSpscInstance();
        paramInnerSubscriber.add(localRxRingBuffer1);
        paramInnerSubscriber.queue = localRxRingBuffer1;
      }
      try
      {
        localRxRingBuffer1.onNext(this.nl.next(paramT));
        emit();
        return;
      }
      catch (MissingBackpressureException paramT)
      {
        paramInnerSubscriber.unsubscribe();
        paramInnerSubscriber.onError(paramT);
        return;
      }
      catch (IllegalStateException paramT)
      {
        while (paramInnerSubscriber.isUnsubscribed()) {}
        paramInnerSubscriber.unsubscribe();
        paramInnerSubscriber.onError(paramT);
      }
    }
    
    void removeInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      ??? = paramInnerSubscriber.queue;
      if (??? != null) {
        ((RxRingBuffer)???).release();
      }
      this.subscriptions.remove(paramInnerSubscriber);
      for (;;)
      {
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber;
        int m;
        int i;
        synchronized (this.innerGuard)
        {
          arrayOfInnerSubscriber = this.innerSubscribers;
          m = arrayOfInnerSubscriber.length;
          break label124;
          if (paramInnerSubscriber.equals(arrayOfInnerSubscriber[i]))
          {
            break label141;
            label61:
            return;
            this.innerSubscribers = EMPTY;
            return;
          }
        }
        label124:
        label141:
        label157:
        do
        {
          paramInnerSubscriber = new OperatorMerge.InnerSubscriber[m - 1];
          int j;
          System.arraycopy(arrayOfInnerSubscriber, 0, paramInnerSubscriber, 0, j);
          System.arraycopy(arrayOfInnerSubscriber, j + 1, paramInnerSubscriber, j, m - j - 1);
          this.innerSubscribers = paramInnerSubscriber;
          return;
          int k = -1;
          i = 0;
          for (;;)
          {
            j = k;
            if (i < m)
            {
              break;
              j = i;
            }
            if (j >= 0) {
              break label157;
            }
            break label61;
            i += 1;
          }
        } while (m != 1);
      }
    }
    
    public void requestMore(long paramLong)
    {
      request(paramLong);
    }
    
    void tryEmit(T paramT)
    {
      int i = 0;
      int j = 0;
      long l2 = this.producer.get();
      long l1 = l2;
      if (l2 != 0L) {}
      for (;;)
      {
        try
        {
          l1 = this.producer.get();
          i = j;
          if (!this.emitting)
          {
            i = j;
            if (l1 != 0L)
            {
              this.emitting = true;
              break label86;
            }
          }
          if (i != 0)
          {
            emitScalar(paramT, l1);
            return;
          }
        }
        finally {}
        queueScalar(paramT);
        return;
        label86:
        i = 1;
      }
    }
    
    void tryEmit(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      int i = 0;
      int j = 0;
      long l2 = this.producer.get();
      long l1 = l2;
      if (l2 != 0L) {}
      for (;;)
      {
        try
        {
          l1 = this.producer.get();
          i = j;
          if (!this.emitting)
          {
            i = j;
            if (l1 != 0L)
            {
              this.emitting = true;
              break label91;
            }
          }
          if (i != 0)
          {
            emitScalar(paramInnerSubscriber, paramT, l1);
            return;
          }
        }
        finally {}
        queueScalar(paramInnerSubscriber, paramT);
        return;
        label91:
        i = 1;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorMerge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */