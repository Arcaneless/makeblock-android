package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;

public final class CachedObservable<T>
  extends Observable<T>
{
  private final CacheState<T> state;
  
  private CachedObservable(Observable.OnSubscribe<T> paramOnSubscribe, CacheState<T> paramCacheState)
  {
    super(paramOnSubscribe);
    this.state = paramCacheState;
  }
  
  public static <T> CachedObservable<T> from(Observable<? extends T> paramObservable)
  {
    return from(paramObservable, 16);
  }
  
  public static <T> CachedObservable<T> from(Observable<? extends T> paramObservable, int paramInt)
  {
    if (paramInt < 1) {
      throw new IllegalArgumentException("capacityHint > 0 required");
    }
    paramObservable = new CacheState(paramObservable, paramInt);
    return new CachedObservable(new CachedSubscribe(paramObservable), paramObservable);
  }
  
  boolean hasObservers()
  {
    return this.state.producers.length != 0;
  }
  
  boolean isConnected()
  {
    return this.state.isConnected;
  }
  
  static final class CacheState<T>
    extends LinkedArrayList
    implements Observer<T>
  {
    static final CachedObservable.ReplayProducer<?>[] EMPTY = new CachedObservable.ReplayProducer[0];
    final SerialSubscription connection;
    volatile boolean isConnected;
    final NotificationLite<T> nl;
    volatile CachedObservable.ReplayProducer<?>[] producers;
    final Observable<? extends T> source;
    boolean sourceDone;
    
    public CacheState(Observable<? extends T> paramObservable, int paramInt)
    {
      super();
      this.source = paramObservable;
      this.producers = EMPTY;
      this.nl = NotificationLite.instance();
      this.connection = new SerialSubscription();
    }
    
    public void addProducer(CachedObservable.ReplayProducer<T> paramReplayProducer)
    {
      for (;;)
      {
        int i;
        CachedObservable.ReplayProducer[] arrayOfReplayProducer2;
        synchronized (this.connection)
        {
          CachedObservable.ReplayProducer[] arrayOfReplayProducer1 = this.producers;
          i = arrayOfReplayProducer1.length;
          arrayOfReplayProducer2 = new CachedObservable.ReplayProducer[i + 1];
          System.arraycopy(arrayOfReplayProducer1, 0, arrayOfReplayProducer2, 0, i);
          break label52;
          this.producers = arrayOfReplayProducer2;
          return;
        }
        label52:
        arrayOfReplayProducer2[i] = paramReplayProducer;
      }
    }
    
    public void connect()
    {
      Subscriber local1 = new Subscriber()
      {
        public void onCompleted()
        {
          CachedObservable.CacheState.this.onCompleted();
        }
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          CachedObservable.CacheState.this.onError(paramAnonymousThrowable);
        }
        
        public void onNext(T paramAnonymousT)
        {
          CachedObservable.CacheState.this.onNext(paramAnonymousT);
        }
      };
      this.connection.set(local1);
      this.source.unsafeSubscribe(local1);
      this.isConnected = true;
    }
    
    void dispatch()
    {
      CachedObservable.ReplayProducer[] arrayOfReplayProducer = this.producers;
      int j = arrayOfReplayProducer.length;
      int i = 0;
      while (i < j)
      {
        arrayOfReplayProducer[i].replay();
        i += 1;
      }
    }
    
    public void onCompleted()
    {
      if (!this.sourceDone)
      {
        this.sourceDone = true;
        add(this.nl.completed());
        this.connection.unsubscribe();
        dispatch();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (!this.sourceDone)
      {
        this.sourceDone = true;
        add(this.nl.error(paramThrowable));
        this.connection.unsubscribe();
        dispatch();
      }
    }
    
    public void onNext(T paramT)
    {
      if (!this.sourceDone)
      {
        add(this.nl.next(paramT));
        dispatch();
      }
    }
    
    public void removeProducer(CachedObservable.ReplayProducer<T> paramReplayProducer)
    {
      for (;;)
      {
        CachedObservable.ReplayProducer[] arrayOfReplayProducer;
        int m;
        int i;
        synchronized (this.connection)
        {
          arrayOfReplayProducer = this.producers;
          m = arrayOfReplayProducer.length;
          break label100;
          if (arrayOfReplayProducer[i].equals(paramReplayProducer))
          {
            break label117;
            label37:
            return;
            this.producers = EMPTY;
            return;
          }
        }
        label100:
        label117:
        label133:
        do
        {
          paramReplayProducer = new CachedObservable.ReplayProducer[m - 1];
          int j;
          System.arraycopy(arrayOfReplayProducer, 0, paramReplayProducer, 0, j);
          System.arraycopy(arrayOfReplayProducer, j + 1, paramReplayProducer, j, m - j - 1);
          this.producers = paramReplayProducer;
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
              break label133;
            }
            break label37;
            i += 1;
          }
        } while (m != 1);
      }
    }
  }
  
  static final class CachedSubscribe<T>
    extends AtomicBoolean
    implements Observable.OnSubscribe<T>
  {
    private static final long serialVersionUID = -2817751667698696782L;
    final CachedObservable.CacheState<T> state;
    
    public CachedSubscribe(CachedObservable.CacheState<T> paramCacheState)
    {
      this.state = paramCacheState;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      CachedObservable.ReplayProducer localReplayProducer = new CachedObservable.ReplayProducer(paramSubscriber, this.state);
      this.state.addProducer(localReplayProducer);
      paramSubscriber.add(localReplayProducer);
      paramSubscriber.setProducer(localReplayProducer);
      if ((!get()) && (compareAndSet(false, true))) {
        this.state.connect();
      }
    }
  }
  
  static final class ReplayProducer<T>
    extends AtomicLong
    implements Producer, Subscription
  {
    private static final long serialVersionUID = -2557562030197141021L;
    final Subscriber<? super T> child;
    Object[] currentBuffer;
    int currentIndexInBuffer;
    boolean emitting;
    int index;
    boolean missed;
    final CachedObservable.CacheState<T> state;
    
    public ReplayProducer(Subscriber<? super T> paramSubscriber, CachedObservable.CacheState<T> paramCacheState)
    {
      this.child = paramSubscriber;
      this.state = paramCacheState;
    }
    
    public boolean isUnsubscribed()
    {
      return get() < 0L;
    }
    
    public long produced(long paramLong)
    {
      return addAndGet(-paramLong);
    }
    
    /* Error */
    public void replay()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   6: ifeq +11 -> 17
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 60	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: return
      //   17: aload_0
      //   18: iconst_1
      //   19: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   22: aload_0
      //   23: monitorexit
      //   24: iconst_0
      //   25: istore 7
      //   27: iconst_0
      //   28: istore 8
      //   30: iconst_0
      //   31: istore 6
      //   33: iload 8
      //   35: istore_1
      //   36: aload_0
      //   37: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   40: getfield 66	rx/internal/operators/CachedObservable$CacheState:nl	Lrx/internal/operators/NotificationLite;
      //   43: astore 16
      //   45: iload 8
      //   47: istore_1
      //   48: aload_0
      //   49: getfield 37	rx/internal/operators/CachedObservable$ReplayProducer:child	Lrx/Subscriber;
      //   52: astore 17
      //   54: iload 8
      //   56: istore_1
      //   57: aload_0
      //   58: invokevirtual 48	rx/internal/operators/CachedObservable$ReplayProducer:get	()J
      //   61: lstore 11
      //   63: lload 11
      //   65: lconst_0
      //   66: lcmp
      //   67: ifge +31 -> 98
      //   70: iconst_1
      //   71: ifne +637 -> 708
      //   74: aload_0
      //   75: monitorenter
      //   76: aload_0
      //   77: iconst_0
      //   78: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   81: aload_0
      //   82: monitorexit
      //   83: return
      //   84: astore 14
      //   86: aload_0
      //   87: monitorexit
      //   88: aload 14
      //   90: athrow
      //   91: astore 14
      //   93: aload_0
      //   94: monitorexit
      //   95: aload 14
      //   97: athrow
      //   98: iload 8
      //   100: istore_1
      //   101: aload_0
      //   102: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   105: invokevirtual 70	rx/internal/operators/CachedObservable$CacheState:size	()I
      //   108: istore 9
      //   110: goto +599 -> 709
      //   113: iload 8
      //   115: istore_1
      //   116: aload_0
      //   117: getfield 72	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   120: astore 15
      //   122: goto +595 -> 717
      //   125: iload 8
      //   127: istore_1
      //   128: aload_0
      //   129: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   132: invokevirtual 76	rx/internal/operators/CachedObservable$CacheState:head	()[Ljava/lang/Object;
      //   135: astore 14
      //   137: iload 8
      //   139: istore_1
      //   140: aload_0
      //   141: aload 14
      //   143: putfield 72	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   146: iload 8
      //   148: istore_1
      //   149: aload 14
      //   151: arraylength
      //   152: iconst_1
      //   153: isub
      //   154: istore 10
      //   156: iload 8
      //   158: istore_1
      //   159: aload_0
      //   160: getfield 78	rx/internal/operators/CachedObservable$ReplayProducer:index	I
      //   163: istore 4
      //   165: iload 8
      //   167: istore_1
      //   168: aload_0
      //   169: getfield 80	rx/internal/operators/CachedObservable$ReplayProducer:currentIndexInBuffer	I
      //   172: istore_2
      //   173: goto +556 -> 729
      //   176: iload 8
      //   178: istore_1
      //   179: aload 16
      //   181: aload 14
      //   183: invokevirtual 86	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   186: ifeq +39 -> 225
      //   189: iload 8
      //   191: istore_1
      //   192: aload 17
      //   194: invokevirtual 91	rx/Subscriber:onCompleted	()V
      //   197: goto +548 -> 745
      //   200: aload_0
      //   201: invokevirtual 94	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   204: iconst_1
      //   205: ifne +503 -> 708
      //   208: aload_0
      //   209: monitorenter
      //   210: aload_0
      //   211: iconst_0
      //   212: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   215: aload_0
      //   216: monitorexit
      //   217: return
      //   218: astore 14
      //   220: aload_0
      //   221: monitorexit
      //   222: aload 14
      //   224: athrow
      //   225: iload 8
      //   227: istore_1
      //   228: aload 16
      //   230: aload 14
      //   232: invokevirtual 97	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   235: ifeq +376 -> 611
      //   238: iload 8
      //   240: istore_1
      //   241: aload 17
      //   243: aload 16
      //   245: aload 14
      //   247: invokevirtual 101	rx/internal/operators/NotificationLite:getError	(Ljava/lang/Object;)Ljava/lang/Throwable;
      //   250: invokevirtual 105	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   253: goto +497 -> 750
      //   256: aload_0
      //   257: invokevirtual 94	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   260: iconst_1
      //   261: ifne +447 -> 708
      //   264: aload_0
      //   265: monitorenter
      //   266: aload_0
      //   267: iconst_0
      //   268: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   271: aload_0
      //   272: monitorexit
      //   273: return
      //   274: astore 14
      //   276: aload_0
      //   277: monitorexit
      //   278: aload 14
      //   280: athrow
      //   281: lload 11
      //   283: lconst_0
      //   284: lcmp
      //   285: ifle +326 -> 611
      //   288: iconst_0
      //   289: istore_3
      //   290: aload 14
      //   292: astore 15
      //   294: iload 4
      //   296: iload 9
      //   298: if_icmpge +241 -> 539
      //   301: lload 11
      //   303: lconst_0
      //   304: lcmp
      //   305: ifle +234 -> 539
      //   308: iload 8
      //   310: istore_1
      //   311: aload 17
      //   313: invokevirtual 107	rx/Subscriber:isUnsubscribed	()Z
      //   316: istore 13
      //   318: iload 13
      //   320: ifeq +24 -> 344
      //   323: iconst_1
      //   324: ifne +384 -> 708
      //   327: aload_0
      //   328: monitorenter
      //   329: aload_0
      //   330: iconst_0
      //   331: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   334: aload_0
      //   335: monitorexit
      //   336: return
      //   337: astore 14
      //   339: aload_0
      //   340: monitorexit
      //   341: aload 14
      //   343: athrow
      //   344: aload 15
      //   346: astore 14
      //   348: iload_2
      //   349: istore 5
      //   351: iload_2
      //   352: iload 10
      //   354: if_icmpne +22 -> 376
      //   357: iload 8
      //   359: istore_1
      //   360: aload 15
      //   362: iload 10
      //   364: aaload
      //   365: checkcast 108	[Ljava/lang/Object;
      //   368: checkcast 108	[Ljava/lang/Object;
      //   371: astore 14
      //   373: goto +382 -> 755
      //   376: aload 14
      //   378: iload 5
      //   380: aaload
      //   381: astore 15
      //   383: iload 6
      //   385: istore_2
      //   386: iload 8
      //   388: istore_1
      //   389: aload 16
      //   391: aload 17
      //   393: aload 15
      //   395: invokevirtual 112	rx/internal/operators/NotificationLite:accept	(Lrx/Observer;Ljava/lang/Object;)Z
      //   398: ifeq +113 -> 511
      //   401: goto +360 -> 761
      //   404: aload_0
      //   405: invokevirtual 94	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   408: iconst_1
      //   409: ifne +299 -> 708
      //   412: aload_0
      //   413: monitorenter
      //   414: aload_0
      //   415: iconst_0
      //   416: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   419: aload_0
      //   420: monitorexit
      //   421: return
      //   422: astore 14
      //   424: aload_0
      //   425: monitorexit
      //   426: aload 14
      //   428: athrow
      //   429: astore 14
      //   431: iload_2
      //   432: istore_1
      //   433: aload 14
      //   435: invokestatic 117	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   438: goto +330 -> 768
      //   441: iload_2
      //   442: istore_1
      //   443: aload_0
      //   444: invokevirtual 94	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   447: iload_2
      //   448: istore_1
      //   449: aload 16
      //   451: aload 15
      //   453: invokevirtual 97	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   456: ifne +34 -> 490
      //   459: iload_2
      //   460: istore_1
      //   461: aload 16
      //   463: aload 15
      //   465: invokevirtual 86	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   468: ifne +22 -> 490
      //   471: iload_2
      //   472: istore_1
      //   473: aload 17
      //   475: aload 14
      //   477: aload 16
      //   479: aload 15
      //   481: invokevirtual 121	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   484: invokestatic 127	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   487: invokevirtual 105	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   490: iconst_1
      //   491: ifne +217 -> 708
      //   494: aload_0
      //   495: monitorenter
      //   496: aload_0
      //   497: iconst_0
      //   498: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   501: aload_0
      //   502: monitorexit
      //   503: return
      //   504: astore 14
      //   506: aload_0
      //   507: monitorexit
      //   508: aload 14
      //   510: athrow
      //   511: iload 5
      //   513: iconst_1
      //   514: iadd
      //   515: istore_2
      //   516: iload 4
      //   518: iconst_1
      //   519: iadd
      //   520: istore 4
      //   522: lload 11
      //   524: lconst_1
      //   525: lsub
      //   526: lstore 11
      //   528: iload_3
      //   529: iconst_1
      //   530: iadd
      //   531: istore_3
      //   532: aload 14
      //   534: astore 15
      //   536: goto -242 -> 294
      //   539: iload 8
      //   541: istore_1
      //   542: aload 17
      //   544: invokevirtual 107	rx/Subscriber:isUnsubscribed	()Z
      //   547: istore 13
      //   549: iload 13
      //   551: ifeq +24 -> 575
      //   554: iconst_1
      //   555: ifne +153 -> 708
      //   558: aload_0
      //   559: monitorenter
      //   560: aload_0
      //   561: iconst_0
      //   562: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   565: aload_0
      //   566: monitorexit
      //   567: return
      //   568: astore 14
      //   570: aload_0
      //   571: monitorexit
      //   572: aload 14
      //   574: athrow
      //   575: iload 8
      //   577: istore_1
      //   578: aload_0
      //   579: iload 4
      //   581: putfield 78	rx/internal/operators/CachedObservable$ReplayProducer:index	I
      //   584: iload 8
      //   586: istore_1
      //   587: aload_0
      //   588: iload_2
      //   589: putfield 80	rx/internal/operators/CachedObservable$ReplayProducer:currentIndexInBuffer	I
      //   592: iload 8
      //   594: istore_1
      //   595: aload_0
      //   596: aload 15
      //   598: putfield 72	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   601: iload 8
      //   603: istore_1
      //   604: aload_0
      //   605: iload_3
      //   606: i2l
      //   607: invokevirtual 129	rx/internal/operators/CachedObservable$ReplayProducer:produced	(J)J
      //   610: pop2
      //   611: iload 8
      //   613: istore_1
      //   614: aload_0
      //   615: monitorenter
      //   616: iload 7
      //   618: istore_1
      //   619: aload_0
      //   620: getfield 60	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   623: ifne +37 -> 660
      //   626: iload 7
      //   628: istore_1
      //   629: aload_0
      //   630: iconst_0
      //   631: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   634: goto +139 -> 773
      //   637: aload_0
      //   638: monitorexit
      //   639: iconst_1
      //   640: ifne +68 -> 708
      //   643: aload_0
      //   644: monitorenter
      //   645: aload_0
      //   646: iconst_0
      //   647: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   650: aload_0
      //   651: monitorexit
      //   652: return
      //   653: astore 14
      //   655: aload_0
      //   656: monitorexit
      //   657: aload 14
      //   659: athrow
      //   660: iload 7
      //   662: istore_1
      //   663: aload_0
      //   664: iconst_0
      //   665: putfield 60	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   668: iload 7
      //   670: istore_1
      //   671: aload_0
      //   672: monitorexit
      //   673: goto -619 -> 54
      //   676: astore 14
      //   678: aload_0
      //   679: monitorexit
      //   680: aload 14
      //   682: athrow
      //   683: astore 14
      //   685: iload_1
      //   686: ifne +12 -> 698
      //   689: aload_0
      //   690: monitorenter
      //   691: aload_0
      //   692: iconst_0
      //   693: putfield 58	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   696: aload_0
      //   697: monitorexit
      //   698: aload 14
      //   700: athrow
      //   701: astore 14
      //   703: aload_0
      //   704: monitorexit
      //   705: aload 14
      //   707: athrow
      //   708: return
      //   709: iload 9
      //   711: ifeq -100 -> 611
      //   714: goto -601 -> 113
      //   717: aload 15
      //   719: astore 14
      //   721: aload 15
      //   723: ifnonnull -577 -> 146
      //   726: goto -601 -> 125
      //   729: lload 11
      //   731: lconst_0
      //   732: lcmp
      //   733: ifne -452 -> 281
      //   736: aload 14
      //   738: iload_2
      //   739: aaload
      //   740: astore 14
      //   742: goto -566 -> 176
      //   745: iconst_1
      //   746: istore_1
      //   747: goto -547 -> 200
      //   750: iconst_1
      //   751: istore_1
      //   752: goto -496 -> 256
      //   755: iconst_0
      //   756: istore 5
      //   758: goto -382 -> 376
      //   761: iconst_1
      //   762: istore_1
      //   763: iconst_1
      //   764: istore_2
      //   765: goto -361 -> 404
      //   768: iconst_1
      //   769: istore_2
      //   770: goto -329 -> 441
      //   773: iconst_1
      //   774: istore_1
      //   775: goto -138 -> 637
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	778	0	this	ReplayProducer
      //   35	740	1	i	int
      //   172	598	2	j	int
      //   289	317	3	k	int
      //   163	417	4	m	int
      //   349	408	5	n	int
      //   31	353	6	i1	int
      //   25	644	7	i2	int
      //   28	584	8	i3	int
      //   108	602	9	i4	int
      //   154	209	10	i5	int
      //   61	669	11	l	long
      //   316	234	13	bool	boolean
      //   84	5	14	localObject1	Object
      //   91	5	14	localObject2	Object
      //   135	47	14	arrayOfObject	Object[]
      //   218	28	14	localObject3	Object
      //   274	17	14	localObject4	Object
      //   337	5	14	localObject5	Object
      //   346	31	14	localObject6	Object
      //   422	5	14	localObject7	Object
      //   429	47	14	localThrowable	Throwable
      //   504	29	14	localObject8	Object
      //   568	5	14	localObject9	Object
      //   653	5	14	localObject10	Object
      //   676	5	14	localObject11	Object
      //   683	16	14	localObject12	Object
      //   701	5	14	localObject13	Object
      //   719	22	14	localObject14	Object
      //   120	602	15	localObject15	Object
      //   43	435	16	localNotificationLite	NotificationLite
      //   52	491	17	localSubscriber	Subscriber
      // Exception table:
      //   from	to	target	type
      //   76	83	84	finally
      //   86	88	84	finally
      //   2	16	91	finally
      //   17	24	91	finally
      //   93	95	91	finally
      //   210	217	218	finally
      //   220	222	218	finally
      //   266	273	274	finally
      //   276	278	274	finally
      //   329	336	337	finally
      //   339	341	337	finally
      //   414	421	422	finally
      //   424	426	422	finally
      //   389	401	429	java/lang/Throwable
      //   404	408	429	java/lang/Throwable
      //   496	503	504	finally
      //   506	508	504	finally
      //   560	567	568	finally
      //   570	572	568	finally
      //   645	652	653	finally
      //   655	657	653	finally
      //   619	626	676	finally
      //   629	634	676	finally
      //   637	639	676	finally
      //   663	668	676	finally
      //   671	673	676	finally
      //   678	680	676	finally
      //   36	45	683	finally
      //   48	54	683	finally
      //   57	63	683	finally
      //   101	110	683	finally
      //   116	122	683	finally
      //   128	137	683	finally
      //   140	146	683	finally
      //   149	156	683	finally
      //   159	165	683	finally
      //   168	173	683	finally
      //   179	189	683	finally
      //   192	197	683	finally
      //   200	204	683	finally
      //   228	238	683	finally
      //   241	253	683	finally
      //   256	260	683	finally
      //   311	318	683	finally
      //   360	373	683	finally
      //   389	401	683	finally
      //   404	408	683	finally
      //   433	438	683	finally
      //   443	447	683	finally
      //   449	459	683	finally
      //   461	471	683	finally
      //   473	490	683	finally
      //   542	549	683	finally
      //   578	584	683	finally
      //   587	592	683	finally
      //   595	601	683	finally
      //   604	611	683	finally
      //   614	616	683	finally
      //   680	683	683	finally
      //   691	698	701	finally
      //   703	705	701	finally
    }
    
    public void request(long paramLong)
    {
      long l3;
      long l1;
      do
      {
        l3 = get();
        if (l3 < 0L) {
          return;
        }
        long l2 = l3 + paramLong;
        l1 = l2;
        if (l2 < 0L) {
          l1 = Long.MAX_VALUE;
        }
      } while (!compareAndSet(l3, l1));
      replay();
    }
    
    public void unsubscribe()
    {
      if ((get() >= 0L) && (getAndSet(-1L) >= 0L)) {
        this.state.removeProducer(this);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\CachedObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */