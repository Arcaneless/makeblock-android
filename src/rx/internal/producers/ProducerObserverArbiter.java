package rx.internal.producers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;

public final class ProducerObserverArbiter<T>
  implements Producer, Observer<T>
{
  static final Producer NULL_PRODUCER = new Producer()
  {
    public void request(long paramAnonymousLong) {}
  };
  final Subscriber<? super T> child;
  Producer currentProducer;
  boolean emitting;
  volatile boolean hasError;
  Producer missedProducer;
  long missedRequested;
  Object missedTerminal;
  List<T> queue;
  long requested;
  
  public ProducerObserverArbiter(Subscriber<? super T> paramSubscriber)
  {
    this.child = paramSubscriber;
  }
  
  void emitLoop()
  {
    Subscriber localSubscriber = this.child;
    long l1 = 0L;
    Object localObject1 = null;
    int i = 0;
    long l5;
    Producer localProducer;
    Object localObject4;
    List localList;
    label42:
    label50:
    label75:
    try
    {
      l5 = this.missedRequested;
      localProducer = this.missedProducer;
      localObject4 = this.missedTerminal;
      localList = this.queue;
    }
    finally {}
    this.emitting = false;
    for (;;)
    {
      if (i != 0)
      {
        if ((l1 != 0L) && (localObject1 != null)) {
          ((Producer)localObject1).request(l1);
        }
        return;
      }
      do
      {
        this.missedRequested = 0L;
        this.missedProducer = null;
        this.queue = null;
        this.missedTerminal = null;
        break label50;
        if ((localList == null) || (localList.isEmpty())) {
          i = 1;
        }
        while (localObject4 != null) {
          if (localObject4 != Boolean.TRUE)
          {
            localSubscriber.onError((Throwable)localObject4);
            return;
            i = 0;
          }
          else if (i != 0)
          {
            localSubscriber.onCompleted();
            return;
          }
        }
        long l4 = 0L;
        if (localList != null)
        {
          Iterator localIterator = localList.iterator();
          for (;;)
          {
            if (localIterator.hasNext())
            {
              localObject4 = localIterator.next();
              if (localSubscriber.isUnsubscribed()) {
                break label75;
              }
              if (this.hasError) {
                break;
              }
              try
              {
                localSubscriber.onNext(localObject4);
              }
              catch (Throwable localThrowable)
              {
                Exceptions.throwOrReport(localThrowable, localSubscriber, localObject4);
                return;
              }
            }
          }
          l4 = 0L + localList.size();
        }
        long l3 = this.requested;
        long l2 = l3;
        if (l3 != Long.MAX_VALUE)
        {
          l2 = l3;
          if (l5 != 0L)
          {
            l3 += l5;
            l2 = l3;
            if (l3 < 0L) {
              l2 = Long.MAX_VALUE;
            }
          }
          l3 = l2;
          if (l4 != 0L)
          {
            l3 = l2;
            if (l2 != Long.MAX_VALUE)
            {
              l3 = l2 - l4;
              if (l3 < 0L) {
                throw new IllegalStateException("More produced than requested");
              }
            }
          }
          this.requested = l3;
          l2 = l3;
        }
        if (localProducer != null)
        {
          if (localProducer == NULL_PRODUCER)
          {
            this.currentProducer = null;
            break;
          }
          this.currentProducer = localProducer;
          if (l2 == 0L) {
            break;
          }
          l1 = BackpressureUtils.addCap(l1, l2);
          localObject3 = localProducer;
          break;
        }
        localProducer = this.currentProducer;
        if ((localProducer == null) || (l5 == 0L)) {
          break;
        }
        l1 = BackpressureUtils.addCap(l1, l5);
        Object localObject3 = localProducer;
        break;
      } while ((l5 != 0L) || (localProducer != null) || (localList != null) || (localObject4 != null));
      break label42;
      i = 1;
    }
  }
  
  public void onCompleted()
  {
    try
    {
      if (this.emitting)
      {
        this.missedTerminal = Boolean.valueOf(true);
        return;
      }
      this.emitting = true;
      this.child.onCompleted();
      return;
    }
    finally {}
  }
  
  public void onError(Throwable paramThrowable)
  {
    for (;;)
    {
      try
      {
        if (this.emitting)
        {
          this.missedTerminal = paramThrowable;
          break label51;
          if (i != 0) {
            this.child.onError(paramThrowable);
          }
        }
        else
        {
          this.emitting = true;
        }
      }
      finally {}
      this.hasError = true;
      return;
      label51:
      int i = 0;
      continue;
      i = 1;
    }
  }
  
  public void onNext(T paramT)
  {
    List localList;
    label18:
    Object localObject;
    label34:
    long l;
    try
    {
      if (this.emitting)
      {
        localList = this.queue;
        break label120;
        localObject = new ArrayList(4);
        this.queue = ((List)localObject);
        ((List)localObject).add(paramT);
        return;
      }
    }
    finally {}
    try
    {
      this.child.onNext(paramT);
      l = this.requested;
    }
    finally
    {
      if (0 != 0) {
        break label113;
      }
    }
    this.requested = (l - 1L);
    emitLoop();
    if (1 == 0) {
      try
      {
        this.emitting = false;
        return;
      }
      finally {}
    }
    try
    {
      this.emitting = false;
      label113:
      throw paramT;
    }
    finally
    {
      throw paramT;
      label120:
      localObject = localList;
      if (localList != null) {
        break label34;
      }
      break label18;
      return;
    }
  }
  
  public void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("n >= 0 required");
    }
    if (paramLong == 0L) {}
    for (;;)
    {
      return;
      try
      {
        if (this.emitting)
        {
          this.missedRequested += paramLong;
          return;
        }
      }
      finally {}
      this.emitting = true;
      Producer localProducer = this.currentProducer;
      try
      {
        l1 = this.requested;
      }
      finally
      {
        long l1;
        label73:
        if (0 != 0) {
          break label131;
        }
        try
        {
          this.emitting = false;
          throw ((Throwable)localObject3);
        }
        finally
        {
          throw ((Throwable)localObject4);
          long l2 = l1 + paramLong;
          l1 = l2;
          if (l2 >= 0L) {
            break label73;
          }
        }
      }
      this.requested = l1;
      emitLoop();
      if (1 == 0) {}
      try
      {
        this.emitting = false;
        if (localProducer == null) {
          continue;
        }
        localProducer.request(paramLong);
        return;
      }
      finally {}
    }
  }
  
  /* Error */
  public void setProducer(Producer paramProducer)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   6: ifeq +21 -> 27
    //   9: goto +101 -> 110
    //   12: aload_0
    //   13: aload_1
    //   14: putfield 50	rx/internal/producers/ProducerObserverArbiter:missedProducer	Lrx/Producer;
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: getstatic 36	rx/internal/producers/ProducerObserverArbiter:NULL_PRODUCER	Lrx/Producer;
    //   23: astore_1
    //   24: goto -12 -> 12
    //   27: aload_0
    //   28: iconst_1
    //   29: putfield 56	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_0
    //   35: aload_1
    //   36: putfield 126	rx/internal/producers/ProducerObserverArbiter:currentProducer	Lrx/Producer;
    //   39: aload_0
    //   40: getfield 115	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   43: lstore_2
    //   44: aload_0
    //   45: invokevirtual 147	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   48: iconst_1
    //   49: ifne +12 -> 61
    //   52: aload_0
    //   53: monitorenter
    //   54: aload_0
    //   55: iconst_0
    //   56: putfield 56	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_1
    //   62: ifnull +55 -> 117
    //   65: lload_2
    //   66: lconst_0
    //   67: lcmp
    //   68: ifeq +49 -> 117
    //   71: aload_1
    //   72: lload_2
    //   73: invokeinterface 60 3 0
    //   78: return
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    //   84: astore_1
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_1
    //   88: athrow
    //   89: astore_1
    //   90: iconst_0
    //   91: ifne +12 -> 103
    //   94: aload_0
    //   95: monitorenter
    //   96: aload_0
    //   97: iconst_0
    //   98: putfield 56	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    //   110: aload_1
    //   111: ifnull -91 -> 20
    //   114: goto -102 -> 12
    //   117: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	118	0	this	ProducerObserverArbiter
    //   0	118	1	paramProducer	Producer
    //   43	30	2	l	long
    // Exception table:
    //   from	to	target	type
    //   2	9	79	finally
    //   12	19	79	finally
    //   20	24	79	finally
    //   27	34	79	finally
    //   80	82	79	finally
    //   54	61	84	finally
    //   85	87	84	finally
    //   44	48	89	finally
    //   96	103	105	finally
    //   106	108	105	finally
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\producers\ProducerObserverArbiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */