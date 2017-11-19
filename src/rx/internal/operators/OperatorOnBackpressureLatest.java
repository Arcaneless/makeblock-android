package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

public final class OperatorOnBackpressureLatest<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorOnBackpressureLatest<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    LatestEmitter localLatestEmitter = new LatestEmitter(paramSubscriber);
    LatestSubscriber localLatestSubscriber = new LatestSubscriber(localLatestEmitter);
    localLatestEmitter.parent = localLatestSubscriber;
    paramSubscriber.add(localLatestSubscriber);
    paramSubscriber.add(localLatestEmitter);
    paramSubscriber.setProducer(localLatestEmitter);
    return localLatestSubscriber;
  }
  
  static final class Holder
  {
    static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest();
  }
  
  static final class LatestEmitter<T>
    extends AtomicLong
    implements Producer, Subscription, Observer<T>
  {
    static final Object EMPTY = new Object();
    static final long NOT_REQUESTED = -4611686018427387904L;
    private static final long serialVersionUID = -1364393685005146274L;
    final Subscriber<? super T> child;
    volatile boolean done;
    boolean emitting;
    boolean missed;
    OperatorOnBackpressureLatest.LatestSubscriber<? super T> parent;
    Throwable terminal;
    final AtomicReference<Object> value;
    
    public LatestEmitter(Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
      this.value = new AtomicReference(EMPTY);
      lazySet(-4611686018427387904L);
    }
    
    /* Error */
    void emit()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 68	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   6: ifeq +11 -> 17
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 70	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: return
      //   17: aload_0
      //   18: iconst_1
      //   19: putfield 68	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   22: aload_0
      //   23: iconst_0
      //   24: putfield 70	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   27: aload_0
      //   28: monitorexit
      //   29: iconst_0
      //   30: istore_3
      //   31: iconst_0
      //   32: istore_2
      //   33: iload_3
      //   34: istore_1
      //   35: aload_0
      //   36: invokevirtual 74	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:get	()J
      //   39: lstore 4
      //   41: lload 4
      //   43: ldc2_w 75
      //   46: lcmp
      //   47: ifne +31 -> 78
      //   50: iconst_1
      //   51: ifne +234 -> 285
      //   54: aload_0
      //   55: monitorenter
      //   56: aload_0
      //   57: iconst_0
      //   58: putfield 68	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   61: aload_0
      //   62: monitorexit
      //   63: return
      //   64: astore 6
      //   66: aload_0
      //   67: monitorexit
      //   68: aload 6
      //   70: athrow
      //   71: astore 6
      //   73: aload_0
      //   74: monitorexit
      //   75: aload 6
      //   77: athrow
      //   78: iload_3
      //   79: istore_1
      //   80: aload_0
      //   81: getfield 59	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:value	Ljava/util/concurrent/atomic/AtomicReference;
      //   84: invokevirtual 79	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   87: astore 7
      //   89: aload 7
      //   91: astore 6
      //   93: lload 4
      //   95: lconst_0
      //   96: lcmp
      //   97: ifle +61 -> 158
      //   100: aload 7
      //   102: astore 6
      //   104: iload_3
      //   105: istore_1
      //   106: aload 7
      //   108: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   111: if_acmpeq +47 -> 158
      //   114: goto +172 -> 286
      //   117: iload_3
      //   118: istore_1
      //   119: aload_0
      //   120: getfield 52	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   123: aload 7
      //   125: invokevirtual 84	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   128: iload_3
      //   129: istore_1
      //   130: aload_0
      //   131: getfield 59	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:value	Ljava/util/concurrent/atomic/AtomicReference;
      //   134: aload 7
      //   136: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   139: invokevirtual 88	java/util/concurrent/atomic/AtomicReference:compareAndSet	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   142: pop
      //   143: iload_3
      //   144: istore_1
      //   145: aload_0
      //   146: lconst_1
      //   147: invokevirtual 92	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:produced	(J)J
      //   150: pop2
      //   151: iload_3
      //   152: istore_1
      //   153: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   156: astore 6
      //   158: iload_3
      //   159: istore_1
      //   160: aload 6
      //   162: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   165: if_acmpne +34 -> 199
      //   168: iload_3
      //   169: istore_1
      //   170: aload_0
      //   171: getfield 94	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:done	Z
      //   174: ifeq +25 -> 199
      //   177: iload_3
      //   178: istore_1
      //   179: aload_0
      //   180: getfield 96	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:terminal	Ljava/lang/Throwable;
      //   183: astore 6
      //   185: goto +104 -> 289
      //   188: iload_3
      //   189: istore_1
      //   190: aload_0
      //   191: getfield 52	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   194: aload 6
      //   196: invokevirtual 100	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   199: iload_3
      //   200: istore_1
      //   201: aload_0
      //   202: monitorenter
      //   203: iload_2
      //   204: istore_1
      //   205: aload_0
      //   206: getfield 70	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   209: ifne +55 -> 264
      //   212: iload_2
      //   213: istore_1
      //   214: aload_0
      //   215: iconst_0
      //   216: putfield 68	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   219: goto +78 -> 297
      //   222: aload_0
      //   223: monitorexit
      //   224: goto -174 -> 50
      //   227: astore 6
      //   229: aload_0
      //   230: monitorexit
      //   231: aload 6
      //   233: athrow
      //   234: astore 6
      //   236: iload_1
      //   237: ifne +12 -> 249
      //   240: aload_0
      //   241: monitorenter
      //   242: aload_0
      //   243: iconst_0
      //   244: putfield 68	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   247: aload_0
      //   248: monitorexit
      //   249: aload 6
      //   251: athrow
      //   252: iload_3
      //   253: istore_1
      //   254: aload_0
      //   255: getfield 52	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   258: invokevirtual 103	rx/Subscriber:onCompleted	()V
      //   261: goto -62 -> 199
      //   264: iload_2
      //   265: istore_1
      //   266: aload_0
      //   267: iconst_0
      //   268: putfield 70	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   271: iload_2
      //   272: istore_1
      //   273: aload_0
      //   274: monitorexit
      //   275: goto -242 -> 33
      //   278: astore 6
      //   280: aload_0
      //   281: monitorexit
      //   282: aload 6
      //   284: athrow
      //   285: return
      //   286: goto -169 -> 117
      //   289: aload 6
      //   291: ifnull -39 -> 252
      //   294: goto -106 -> 188
      //   297: iconst_1
      //   298: istore_1
      //   299: goto -77 -> 222
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	302	0	this	LatestEmitter
      //   34	265	1	i	int
      //   32	240	2	j	int
      //   30	223	3	k	int
      //   39	55	4	l	long
      //   64	5	6	localObject1	Object
      //   71	5	6	localObject2	Object
      //   91	104	6	localObject3	Object
      //   227	5	6	localObject4	Object
      //   234	16	6	localObject5	Object
      //   278	12	6	localObject6	Object
      //   87	48	7	localObject7	Object
      // Exception table:
      //   from	to	target	type
      //   56	63	64	finally
      //   66	68	64	finally
      //   2	16	71	finally
      //   17	29	71	finally
      //   73	75	71	finally
      //   205	212	227	finally
      //   214	219	227	finally
      //   222	224	227	finally
      //   229	231	227	finally
      //   266	271	227	finally
      //   273	275	227	finally
      //   35	41	234	finally
      //   80	89	234	finally
      //   106	114	234	finally
      //   119	128	234	finally
      //   130	143	234	finally
      //   145	151	234	finally
      //   153	158	234	finally
      //   160	168	234	finally
      //   170	177	234	finally
      //   179	185	234	finally
      //   190	199	234	finally
      //   201	203	234	finally
      //   231	234	234	finally
      //   254	261	234	finally
      //   242	249	278	finally
      //   280	282	278	finally
    }
    
    public boolean isUnsubscribed()
    {
      return get() == Long.MIN_VALUE;
    }
    
    public void onCompleted()
    {
      this.done = true;
      emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.terminal = paramThrowable;
      this.done = true;
      emit();
    }
    
    public void onNext(T paramT)
    {
      this.value.lazySet(paramT);
      emit();
    }
    
    long produced(long paramLong)
    {
      long l1;
      long l2;
      do
      {
        l1 = get();
        if (l1 < 0L) {
          return l1;
        }
        l2 = l1 - paramLong;
      } while (!compareAndSet(l1, l2));
      return l2;
    }
    
    public void request(long paramLong)
    {
      if (paramLong >= 0L) {}
      for (;;)
      {
        long l3 = get();
        if (l3 == Long.MIN_VALUE) {
          return;
        }
        long l1;
        if (l3 == -4611686018427387904L) {
          l1 = paramLong;
        }
        while (compareAndSet(l3, l1))
        {
          if (l3 == -4611686018427387904L) {
            this.parent.requestMore(Long.MAX_VALUE);
          }
          emit();
          return;
          long l2 = l3 + paramLong;
          l1 = l2;
          if (l2 < 0L) {
            l1 = Long.MAX_VALUE;
          }
        }
      }
    }
    
    public void unsubscribe()
    {
      if (get() >= 0L) {
        getAndSet(Long.MIN_VALUE);
      }
    }
  }
  
  static final class LatestSubscriber<T>
    extends Subscriber<T>
  {
    private final OperatorOnBackpressureLatest.LatestEmitter<T> producer;
    
    LatestSubscriber(OperatorOnBackpressureLatest.LatestEmitter<T> paramLatestEmitter)
    {
      this.producer = paramLatestEmitter;
    }
    
    public void onCompleted()
    {
      this.producer.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.producer.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.producer.onNext(paramT);
    }
    
    public void onStart()
    {
      request(0L);
    }
    
    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorOnBackpressureLatest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */