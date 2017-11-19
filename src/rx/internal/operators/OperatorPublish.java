package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

public final class OperatorPublish<T>
  extends ConnectableObservable<T>
{
  final AtomicReference<PublishSubscriber<T>> current;
  final Observable<? extends T> source;
  
  private OperatorPublish(Observable.OnSubscribe<T> paramOnSubscribe, Observable<? extends T> paramObservable, AtomicReference<PublishSubscriber<T>> paramAtomicReference)
  {
    super(paramOnSubscribe);
    this.source = paramObservable;
    this.current = paramAtomicReference;
  }
  
  public static <T, R> Observable<R> create(Observable<? extends T> paramObservable, Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1)
  {
    return create(paramObservable, paramFunc1, false);
  }
  
  public static <T, R> Observable<R> create(final Observable<? extends T> paramObservable, final Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1, boolean paramBoolean)
  {
    create(new Observable.OnSubscribe()
    {
      public void call(final Subscriber<? super R> paramAnonymousSubscriber)
      {
        final OnSubscribePublishMulticast localOnSubscribePublishMulticast = new OnSubscribePublishMulticast(RxRingBuffer.SIZE, this.val$delayError);
        Subscriber local1 = new Subscriber()
        {
          public void onCompleted()
          {
            localOnSubscribePublishMulticast.unsubscribe();
            paramAnonymousSubscriber.onCompleted();
          }
          
          public void onError(Throwable paramAnonymous2Throwable)
          {
            localOnSubscribePublishMulticast.unsubscribe();
            paramAnonymousSubscriber.onError(paramAnonymous2Throwable);
          }
          
          public void onNext(R paramAnonymous2R)
          {
            paramAnonymousSubscriber.onNext(paramAnonymous2R);
          }
          
          public void setProducer(Producer paramAnonymous2Producer)
          {
            paramAnonymousSubscriber.setProducer(paramAnonymous2Producer);
          }
        };
        paramAnonymousSubscriber.add(localOnSubscribePublishMulticast);
        paramAnonymousSubscriber.add(local1);
        ((Observable)paramFunc1.call(Observable.create(localOnSubscribePublishMulticast))).unsafeSubscribe(local1);
        paramObservable.unsafeSubscribe(localOnSubscribePublishMulticast.subscriber());
      }
    });
  }
  
  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable)
  {
    AtomicReference localAtomicReference = new AtomicReference();
    new OperatorPublish(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        Object localObject2;
        Object localObject1;
        do
        {
          do
          {
            localObject2 = (OperatorPublish.PublishSubscriber)this.val$curr.get();
            if (localObject2 != null)
            {
              localObject1 = localObject2;
              if (!((OperatorPublish.PublishSubscriber)localObject2).isUnsubscribed()) {
                break;
              }
            }
            localObject1 = new OperatorPublish.PublishSubscriber(this.val$curr);
            ((OperatorPublish.PublishSubscriber)localObject1).init();
          } while (!this.val$curr.compareAndSet(localObject2, localObject1));
          localObject2 = new OperatorPublish.InnerProducer((OperatorPublish.PublishSubscriber)localObject1, paramAnonymousSubscriber);
        } while (!((OperatorPublish.PublishSubscriber)localObject1).add((OperatorPublish.InnerProducer)localObject2));
        paramAnonymousSubscriber.add((Subscription)localObject2);
        paramAnonymousSubscriber.setProducer((Producer)localObject2);
      }
    }, paramObservable, localAtomicReference);
  }
  
  public void connect(Action1<? super Subscription> paramAction1)
  {
    PublishSubscriber localPublishSubscriber2;
    PublishSubscriber localPublishSubscriber1;
    do
    {
      localPublishSubscriber2 = (PublishSubscriber)this.current.get();
      if (localPublishSubscriber2 != null)
      {
        localPublishSubscriber1 = localPublishSubscriber2;
        if (!localPublishSubscriber2.isUnsubscribed()) {
          break;
        }
      }
      localPublishSubscriber1 = new PublishSubscriber(this.current);
      localPublishSubscriber1.init();
    } while (!this.current.compareAndSet(localPublishSubscriber2, localPublishSubscriber1));
    if ((!localPublishSubscriber1.shouldConnect.get()) && (localPublishSubscriber1.shouldConnect.compareAndSet(false, true))) {}
    for (int i = 1;; i = 0)
    {
      paramAction1.call(localPublishSubscriber1);
      if (i != 0) {
        this.source.unsafeSubscribe(localPublishSubscriber1);
      }
      return;
    }
  }
  
  static final class InnerProducer<T>
    extends AtomicLong
    implements Producer, Subscription
  {
    static final long NOT_REQUESTED = -4611686018427387904L;
    static final long UNSUBSCRIBED = Long.MIN_VALUE;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    final OperatorPublish.PublishSubscriber<T> parent;
    
    public InnerProducer(OperatorPublish.PublishSubscriber<T> paramPublishSubscriber, Subscriber<? super T> paramSubscriber)
    {
      this.parent = paramPublishSubscriber;
      this.child = paramSubscriber;
      lazySet(-4611686018427387904L);
    }
    
    public boolean isUnsubscribed()
    {
      return get() == Long.MIN_VALUE;
    }
    
    public long produced(long paramLong)
    {
      if (paramLong <= 0L) {
        throw new IllegalArgumentException("Cant produce zero or less");
      }
      long l1;
      long l2;
      do
      {
        l1 = get();
        if (l1 == -4611686018427387904L) {
          throw new IllegalStateException("Produced without request");
        }
        if (l1 == Long.MIN_VALUE) {
          return Long.MIN_VALUE;
        }
        l2 = l1 - paramLong;
        if (l2 < 0L) {
          throw new IllegalStateException("More produced (" + paramLong + ") than requested (" + l1 + ")");
        }
      } while (!compareAndSet(l1, l2));
      return l2;
    }
    
    public void request(long paramLong)
    {
      if (paramLong < 0L) {
        return;
      }
      for (;;)
      {
        long l3 = get();
        if ((l3 == Long.MIN_VALUE) || ((l3 >= 0L) && (paramLong == 0L))) {
          break;
        }
        long l1;
        if (l3 == -4611686018427387904L) {
          l1 = paramLong;
        }
        while (compareAndSet(l3, l1))
        {
          this.parent.dispatch();
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
      if ((get() != Long.MIN_VALUE) && (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE))
      {
        this.parent.remove(this);
        this.parent.dispatch();
      }
    }
  }
  
  static final class PublishSubscriber<T>
    extends Subscriber<T>
    implements Subscription
  {
    static final OperatorPublish.InnerProducer[] EMPTY = new OperatorPublish.InnerProducer[0];
    static final OperatorPublish.InnerProducer[] TERMINATED = new OperatorPublish.InnerProducer[0];
    final AtomicReference<PublishSubscriber<T>> current;
    boolean emitting;
    boolean missed;
    final NotificationLite<T> nl;
    final AtomicReference<OperatorPublish.InnerProducer[]> producers;
    final Queue<Object> queue;
    final AtomicBoolean shouldConnect;
    volatile Object terminalEvent;
    
    public PublishSubscriber(AtomicReference<PublishSubscriber<T>> paramAtomicReference)
    {
      if (UnsafeAccess.isUnsafeAvailable()) {}
      for (Object localObject = new SpscArrayQueue(RxRingBuffer.SIZE);; localObject = new SynchronizedQueue(RxRingBuffer.SIZE))
      {
        this.queue = ((Queue)localObject);
        this.nl = NotificationLite.instance();
        this.producers = new AtomicReference(EMPTY);
        this.current = paramAtomicReference;
        this.shouldConnect = new AtomicBoolean();
        return;
      }
    }
    
    boolean add(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      if (paramInnerProducer == null) {
        throw new NullPointerException();
      }
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1;
      OperatorPublish.InnerProducer[] arrayOfInnerProducer2;
      do
      {
        arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.get();
        if (arrayOfInnerProducer1 == TERMINATED) {
          return false;
        }
        int i = arrayOfInnerProducer1.length;
        arrayOfInnerProducer2 = new OperatorPublish.InnerProducer[i + 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, i);
        arrayOfInnerProducer2[i] = paramInnerProducer;
      } while (!this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2));
      return true;
    }
    
    boolean checkTerminated(Object paramObject, boolean paramBoolean)
    {
      int j;
      OperatorPublish.InnerProducer[] arrayOfInnerProducer;
      int i;
      if (paramObject != null) {
        if (this.nl.isCompleted(paramObject))
        {
          if (paramBoolean)
          {
            this.current.compareAndSet(this, null);
            try
            {
              paramObject = (OperatorPublish.InnerProducer[])this.producers.getAndSet(TERMINATED);
              j = paramObject.length;
            }
            finally
            {
              unsubscribe();
            }
            arrayOfInnerProducer.child.onCompleted();
            i += 1;
            break label153;
            label65:
            unsubscribe();
            return true;
          }
        }
        else
        {
          paramObject = this.nl.getError(paramObject);
          this.current.compareAndSet(this, null);
        }
      }
      for (;;)
      {
        OperatorPublish.InnerProducer localInnerProducer;
        try
        {
          arrayOfInnerProducer = (OperatorPublish.InnerProducer[])this.producers.getAndSet(TERMINATED);
          j = arrayOfInnerProducer.length;
        }
        finally
        {
          unsubscribe();
        }
        localInnerProducer.child.onError((Throwable)paramObject);
        i += 1;
        break label169;
        unsubscribe();
        return true;
        return false;
        i = 0;
        label153:
        if (i >= j) {
          break label65;
        }
        arrayOfInnerProducer = paramObject[i];
        break;
        i = 0;
        label169:
        if (i < j) {
          localInnerProducer = arrayOfInnerProducer[i];
        }
      }
    }
    
    /* Error */
    void dispatch()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   6: ifeq +11 -> 17
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 148	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: return
      //   17: aload_0
      //   18: iconst_1
      //   19: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   22: aload_0
      //   23: iconst_0
      //   24: putfield 148	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   27: aload_0
      //   28: monitorexit
      //   29: iconst_0
      //   30: istore 5
      //   32: iconst_0
      //   33: istore 4
      //   35: iload 4
      //   37: istore_1
      //   38: aload_0
      //   39: getfield 150	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   42: astore 16
      //   44: iload 4
      //   46: istore_1
      //   47: aload_0
      //   48: getfield 65	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   51: invokeinterface 155 1 0
      //   56: istore 8
      //   58: iload 4
      //   60: istore_1
      //   61: aload_0
      //   62: aload 16
      //   64: iload 8
      //   66: invokevirtual 157	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   69: istore 9
      //   71: iload 9
      //   73: ifeq +31 -> 104
      //   76: iconst_1
      //   77: ifne +487 -> 564
      //   80: aload_0
      //   81: monitorenter
      //   82: aload_0
      //   83: iconst_0
      //   84: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   87: aload_0
      //   88: monitorexit
      //   89: return
      //   90: astore 16
      //   92: aload_0
      //   93: monitorexit
      //   94: aload 16
      //   96: athrow
      //   97: astore 16
      //   99: aload_0
      //   100: monitorexit
      //   101: aload 16
      //   103: athrow
      //   104: iload 8
      //   106: ifne +276 -> 382
      //   109: iload 4
      //   111: istore_1
      //   112: aload_0
      //   113: getfield 80	rx/internal/operators/OperatorPublish$PublishSubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   116: invokevirtual 101	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   119: checkcast 102	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   122: astore 16
      //   124: iload 4
      //   126: istore_1
      //   127: aload 16
      //   129: arraylength
      //   130: istore 6
      //   132: goto +433 -> 565
      //   135: iload 4
      //   137: istore_1
      //   138: aload 16
      //   140: arraylength
      //   141: istore 7
      //   143: goto +432 -> 575
      //   146: iload 4
      //   148: istore_1
      //   149: aload 17
      //   151: invokevirtual 160	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   154: lstore 14
      //   156: goto +436 -> 592
      //   159: iload 4
      //   161: istore_1
      //   162: lload 10
      //   164: lload 14
      //   166: invokestatic 166	java/lang/Math:min	(JJ)J
      //   169: lstore 12
      //   171: iload_3
      //   172: istore_1
      //   173: goto +429 -> 602
      //   176: iload 4
      //   178: istore_1
      //   179: aload_0
      //   180: getfield 150	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   183: astore 16
      //   185: iload 4
      //   187: istore_1
      //   188: aload_0
      //   189: getfield 65	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   192: invokeinterface 169 1 0
      //   197: astore 17
      //   199: goto +451 -> 650
      //   202: iload 4
      //   204: istore_1
      //   205: aload_0
      //   206: aload 16
      //   208: iload 8
      //   210: invokevirtual 157	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   213: istore 8
      //   215: iload 8
      //   217: ifeq +30 -> 247
      //   220: iconst_1
      //   221: ifne +343 -> 564
      //   224: aload_0
      //   225: monitorenter
      //   226: aload_0
      //   227: iconst_0
      //   228: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   231: aload_0
      //   232: monitorexit
      //   233: return
      //   234: astore 16
      //   236: aload_0
      //   237: monitorexit
      //   238: aload 16
      //   240: athrow
      //   241: iconst_0
      //   242: istore 8
      //   244: goto -42 -> 202
      //   247: iload 4
      //   249: istore_1
      //   250: aload_0
      //   251: lconst_1
      //   252: invokevirtual 173	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   255: goto -220 -> 35
      //   258: astore 16
      //   260: iload_1
      //   261: ifne +12 -> 273
      //   264: aload_0
      //   265: monitorenter
      //   266: aload_0
      //   267: iconst_0
      //   268: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   271: aload_0
      //   272: monitorexit
      //   273: aload 16
      //   275: athrow
      //   276: iconst_0
      //   277: istore_2
      //   278: iload_2
      //   279: i2l
      //   280: lload 10
      //   282: lcmp
      //   283: ifge +79 -> 362
      //   286: iload 4
      //   288: istore_1
      //   289: aload_0
      //   290: getfield 150	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   293: astore 17
      //   295: iload 4
      //   297: istore_1
      //   298: aload_0
      //   299: getfield 65	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   302: invokeinterface 169 1 0
      //   307: astore 18
      //   309: goto +352 -> 661
      //   312: iload 4
      //   314: istore_1
      //   315: aload_0
      //   316: aload 17
      //   318: iload 8
      //   320: invokevirtual 157	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   323: istore 9
      //   325: iload 9
      //   327: ifeq +30 -> 357
      //   330: iconst_1
      //   331: ifne +233 -> 564
      //   334: aload_0
      //   335: monitorenter
      //   336: aload_0
      //   337: iconst_0
      //   338: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   341: aload_0
      //   342: monitorexit
      //   343: return
      //   344: astore 16
      //   346: aload_0
      //   347: monitorexit
      //   348: aload 16
      //   350: athrow
      //   351: iconst_0
      //   352: istore 8
      //   354: goto -42 -> 312
      //   357: iload 8
      //   359: ifeq +72 -> 431
      //   362: iload_2
      //   363: ifle +309 -> 672
      //   366: iload_2
      //   367: i2l
      //   368: lstore 12
      //   370: iload 4
      //   372: istore_1
      //   373: aload_0
      //   374: lload 12
      //   376: invokevirtual 173	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   379: goto +293 -> 672
      //   382: iload 4
      //   384: istore_1
      //   385: aload_0
      //   386: monitorenter
      //   387: iload 5
      //   389: istore_1
      //   390: aload_0
      //   391: getfield 148	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   394: ifne +140 -> 534
      //   397: iload 5
      //   399: istore_1
      //   400: aload_0
      //   401: iconst_0
      //   402: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   405: goto +282 -> 687
      //   408: aload_0
      //   409: monitorexit
      //   410: iconst_1
      //   411: ifne +153 -> 564
      //   414: aload_0
      //   415: monitorenter
      //   416: aload_0
      //   417: iconst_0
      //   418: putfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   421: aload_0
      //   422: monitorexit
      //   423: return
      //   424: astore 16
      //   426: aload_0
      //   427: monitorexit
      //   428: aload 16
      //   430: athrow
      //   431: iload 4
      //   433: istore_1
      //   434: aload_0
      //   435: getfield 73	rx/internal/operators/OperatorPublish$PublishSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   438: aload 18
      //   440: invokevirtual 176	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   443: astore 17
      //   445: iload 4
      //   447: istore_1
      //   448: aload 16
      //   450: arraylength
      //   451: istore 6
      //   453: iconst_0
      //   454: istore_3
      //   455: goto +237 -> 692
      //   458: iload 4
      //   460: istore_1
      //   461: aload 18
      //   463: invokevirtual 160	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   466: lstore 12
      //   468: lload 12
      //   470: lconst_0
      //   471: lcmp
      //   472: ifle +235 -> 707
      //   475: iload 4
      //   477: istore_1
      //   478: aload 18
      //   480: getfield 127	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   483: aload 17
      //   485: invokevirtual 179	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   488: iload 4
      //   490: istore_1
      //   491: aload 18
      //   493: lconst_1
      //   494: invokevirtual 183	rx/internal/operators/OperatorPublish$InnerProducer:produced	(J)J
      //   497: pop2
      //   498: goto +209 -> 707
      //   501: iload 4
      //   503: istore_1
      //   504: aload 18
      //   506: invokevirtual 184	rx/internal/operators/OperatorPublish$InnerProducer:unsubscribe	()V
      //   509: iload 4
      //   511: istore_1
      //   512: aload 19
      //   514: aload 18
      //   516: getfield 127	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   519: aload 17
      //   521: invokestatic 190	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;Ljava/lang/Object;)V
      //   524: goto +183 -> 707
      //   527: iload_2
      //   528: iconst_1
      //   529: iadd
      //   530: istore_2
      //   531: goto -253 -> 278
      //   534: iload 5
      //   536: istore_1
      //   537: aload_0
      //   538: iconst_0
      //   539: putfield 148	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   542: iload 5
      //   544: istore_1
      //   545: aload_0
      //   546: monitorexit
      //   547: goto -512 -> 35
      //   550: astore 16
      //   552: aload_0
      //   553: monitorexit
      //   554: aload 16
      //   556: athrow
      //   557: astore 16
      //   559: aload_0
      //   560: monitorexit
      //   561: aload 16
      //   563: athrow
      //   564: return
      //   565: ldc2_w 191
      //   568: lstore 10
      //   570: iconst_0
      //   571: istore_3
      //   572: goto -437 -> 135
      //   575: iconst_0
      //   576: istore_2
      //   577: iload_2
      //   578: iload 7
      //   580: if_icmpge +61 -> 641
      //   583: aload 16
      //   585: iload_2
      //   586: aaload
      //   587: astore 17
      //   589: goto -443 -> 146
      //   592: lload 14
      //   594: lconst_0
      //   595: lcmp
      //   596: iflt +19 -> 615
      //   599: goto -440 -> 159
      //   602: iload_2
      //   603: iconst_1
      //   604: iadd
      //   605: istore_2
      //   606: lload 12
      //   608: lstore 10
      //   610: iload_1
      //   611: istore_3
      //   612: goto -35 -> 577
      //   615: lload 10
      //   617: lstore 12
      //   619: iload_3
      //   620: istore_1
      //   621: lload 14
      //   623: ldc2_w 193
      //   626: lcmp
      //   627: ifne -25 -> 602
      //   630: iload_3
      //   631: iconst_1
      //   632: iadd
      //   633: istore_1
      //   634: lload 10
      //   636: lstore 12
      //   638: goto -36 -> 602
      //   641: iload 6
      //   643: iload_3
      //   644: if_icmpne -368 -> 276
      //   647: goto -471 -> 176
      //   650: aload 17
      //   652: ifnonnull -411 -> 241
      //   655: iconst_1
      //   656: istore 8
      //   658: goto -456 -> 202
      //   661: aload 18
      //   663: ifnonnull -312 -> 351
      //   666: iconst_1
      //   667: istore 8
      //   669: goto -357 -> 312
      //   672: lload 10
      //   674: lconst_0
      //   675: lcmp
      //   676: ifeq -294 -> 382
      //   679: iload 8
      //   681: ifeq -646 -> 35
      //   684: goto -302 -> 382
      //   687: iconst_1
      //   688: istore_1
      //   689: goto -281 -> 408
      //   692: iload_3
      //   693: iload 6
      //   695: if_icmpge -168 -> 527
      //   698: aload 16
      //   700: iload_3
      //   701: aaload
      //   702: astore 18
      //   704: goto -246 -> 458
      //   707: iload_3
      //   708: iconst_1
      //   709: iadd
      //   710: istore_3
      //   711: goto -19 -> 692
      //   714: astore 19
      //   716: goto -215 -> 501
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	719	0	this	PublishSubscriber
      //   37	652	1	i	int
      //   277	329	2	j	int
      //   171	540	3	k	int
      //   33	477	4	m	int
      //   30	513	5	n	int
      //   130	566	6	i1	int
      //   141	440	7	i2	int
      //   56	624	8	bool1	boolean
      //   69	257	9	bool2	boolean
      //   162	119	10	localObject1	Object
      //   568	105	10	l1	long
      //   169	468	12	l2	long
      //   154	468	14	l3	long
      //   42	21	16	localObject2	Object
      //   90	5	16	localObject3	Object
      //   97	5	16	localObject4	Object
      //   122	85	16	localObject5	Object
      //   234	5	16	localObject6	Object
      //   258	16	16	localObject7	Object
      //   344	5	16	localObject8	Object
      //   424	25	16	localObject9	Object
      //   550	5	16	localObject10	Object
      //   557	142	16	localObject11	Object
      //   149	502	17	localObject12	Object
      //   307	396	18	localObject13	Object
      //   512	1	19	localThrowable1	Throwable
      //   714	1	19	localThrowable2	Throwable
      // Exception table:
      //   from	to	target	type
      //   82	89	90	finally
      //   92	94	90	finally
      //   2	16	97	finally
      //   17	29	97	finally
      //   99	101	97	finally
      //   226	233	234	finally
      //   236	238	234	finally
      //   38	44	258	finally
      //   47	58	258	finally
      //   61	71	258	finally
      //   112	124	258	finally
      //   127	132	258	finally
      //   138	143	258	finally
      //   149	156	258	finally
      //   162	171	258	finally
      //   179	185	258	finally
      //   188	199	258	finally
      //   205	215	258	finally
      //   250	255	258	finally
      //   289	295	258	finally
      //   298	309	258	finally
      //   315	325	258	finally
      //   373	379	258	finally
      //   385	387	258	finally
      //   434	445	258	finally
      //   448	453	258	finally
      //   461	468	258	finally
      //   478	488	258	finally
      //   491	498	258	finally
      //   504	509	258	finally
      //   512	524	258	finally
      //   554	557	258	finally
      //   336	343	344	finally
      //   346	348	344	finally
      //   416	423	424	finally
      //   426	428	424	finally
      //   390	397	550	finally
      //   400	405	550	finally
      //   408	410	550	finally
      //   537	542	550	finally
      //   545	547	550	finally
      //   552	554	550	finally
      //   266	273	557	finally
      //   559	561	557	finally
      //   478	488	714	java/lang/Throwable
    }
    
    void init()
    {
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorPublish.PublishSubscriber.this.producers.getAndSet(OperatorPublish.PublishSubscriber.TERMINATED);
          OperatorPublish.PublishSubscriber.this.current.compareAndSet(OperatorPublish.PublishSubscriber.this, null);
        }
      }));
    }
    
    public void onCompleted()
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.completed();
        dispatch();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.error(paramThrowable);
        dispatch();
      }
    }
    
    public void onNext(T paramT)
    {
      if (!this.queue.offer(this.nl.next(paramT)))
      {
        onError(new MissingBackpressureException());
        return;
      }
      dispatch();
    }
    
    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }
    
    void remove(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      OperatorPublish.InnerProducer[] arrayOfInnerProducer2 = (OperatorPublish.InnerProducer[])this.producers.get();
      if ((arrayOfInnerProducer2 == EMPTY) || (arrayOfInnerProducer2 == TERMINATED)) {}
      int m;
      int i;
      label39:
      int j;
      do
      {
        return;
        int k = -1;
        m = arrayOfInnerProducer2.length;
        i = 0;
        j = k;
        if (i < m)
        {
          if (!arrayOfInnerProducer2[i].equals(paramInnerProducer)) {
            break;
          }
          j = i;
        }
      } while (j < 0);
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1;
      if (m == 1) {
        arrayOfInnerProducer1 = EMPTY;
      }
      while (this.producers.compareAndSet(arrayOfInnerProducer2, arrayOfInnerProducer1))
      {
        return;
        i += 1;
        break label39;
        arrayOfInnerProducer1 = new OperatorPublish.InnerProducer[m - 1];
        System.arraycopy(arrayOfInnerProducer2, 0, arrayOfInnerProducer1, 0, j);
        System.arraycopy(arrayOfInnerProducer2, j + 1, arrayOfInnerProducer1, j, m - j - 1);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorPublish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */