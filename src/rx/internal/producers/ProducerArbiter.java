package rx.internal.producers;

import rx.Producer;

public final class ProducerArbiter
  implements Producer
{
  static final Producer NULL_PRODUCER = new Producer()
  {
    public void request(long paramAnonymousLong) {}
  };
  Producer currentProducer;
  boolean emitting;
  long missedProduced;
  Producer missedProducer;
  long missedRequested;
  long requested;
  
  public void emitLoop()
  {
    for (;;)
    {
      long l3;
      long l4;
      Producer localProducer1;
      try
      {
        l3 = this.missedRequested;
        l4 = this.missedProduced;
        localProducer1 = this.missedProducer;
      }
      finally {}
      this.emitting = false;
      return;
      this.missedRequested = 0L;
      this.missedProduced = 0L;
      this.missedProducer = null;
      long l2 = this.requested;
      long l1 = l2;
      if (l2 != Long.MAX_VALUE)
      {
        l1 = l2 + l3;
        if ((l1 < 0L) || (l1 == Long.MAX_VALUE))
        {
          l1 = Long.MAX_VALUE;
          this.requested = Long.MAX_VALUE;
        }
      }
      else
      {
        if (localProducer1 == null) {
          break label169;
        }
        if (localProducer1 != NULL_PRODUCER) {
          break label152;
        }
        this.currentProducer = null;
        break;
      }
      l2 = l1 - l4;
      if (l2 < 0L) {
        throw new IllegalStateException("more produced than requested");
      }
      l1 = l2;
      this.requested = l2;
      continue;
      label152:
      this.currentProducer = localProducer2;
      localProducer2.request(l1);
      break;
      label169:
      Producer localProducer3 = this.currentProducer;
      if ((localProducer3 == null) || (l3 == 0L)) {
        break;
      }
      localProducer3.request(l3);
      break;
      if ((l3 != 0L) || (l4 != 0L) || (localProducer3 != null)) {}
    }
  }
  
  /* Error */
  public void produced(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifgt +13 -> 16
    //   6: new 56	java/lang/IllegalArgumentException
    //   9: dup
    //   10: ldc 58
    //   12: invokespecial 59	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: aload_0
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield 36	rx/internal/producers/ProducerArbiter:emitting	Z
    //   22: ifeq +16 -> 38
    //   25: aload_0
    //   26: aload_0
    //   27: getfield 32	rx/internal/producers/ProducerArbiter:missedProduced	J
    //   30: lload_1
    //   31: ladd
    //   32: putfield 32	rx/internal/producers/ProducerArbiter:missedProduced	J
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: aload_0
    //   39: iconst_1
    //   40: putfield 36	rx/internal/producers/ProducerArbiter:emitting	Z
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_0
    //   46: getfield 38	rx/internal/producers/ProducerArbiter:requested	J
    //   49: lstore_3
    //   50: goto +76 -> 126
    //   53: new 44	java/lang/IllegalStateException
    //   56: dup
    //   57: ldc 61
    //   59: invokespecial 49	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   62: athrow
    //   63: astore 5
    //   65: iconst_0
    //   66: ifne +12 -> 78
    //   69: aload_0
    //   70: monitorenter
    //   71: aload_0
    //   72: iconst_0
    //   73: putfield 36	rx/internal/producers/ProducerArbiter:emitting	Z
    //   76: aload_0
    //   77: monitorexit
    //   78: aload 5
    //   80: athrow
    //   81: astore 5
    //   83: aload_0
    //   84: monitorexit
    //   85: aload 5
    //   87: athrow
    //   88: aload_0
    //   89: lload_1
    //   90: putfield 38	rx/internal/producers/ProducerArbiter:requested	J
    //   93: aload_0
    //   94: invokevirtual 63	rx/internal/producers/ProducerArbiter:emitLoop	()V
    //   97: iconst_1
    //   98: ifne +27 -> 125
    //   101: aload_0
    //   102: monitorenter
    //   103: aload_0
    //   104: iconst_0
    //   105: putfield 36	rx/internal/producers/ProducerArbiter:emitting	Z
    //   108: aload_0
    //   109: monitorexit
    //   110: return
    //   111: astore 5
    //   113: aload_0
    //   114: monitorexit
    //   115: aload 5
    //   117: athrow
    //   118: astore 5
    //   120: aload_0
    //   121: monitorexit
    //   122: aload 5
    //   124: athrow
    //   125: return
    //   126: lload_3
    //   127: ldc2_w 39
    //   130: lcmp
    //   131: ifeq -38 -> 93
    //   134: lload_3
    //   135: lload_1
    //   136: lsub
    //   137: lstore_1
    //   138: lload_1
    //   139: lconst_0
    //   140: lcmp
    //   141: ifge -53 -> 88
    //   144: goto -91 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	ProducerArbiter
    //   0	147	1	paramLong	long
    //   49	86	3	l	long
    //   63	16	5	localObject1	Object
    //   81	5	5	localObject2	Object
    //   111	5	5	localObject3	Object
    //   118	5	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   45	50	63	finally
    //   53	63	63	finally
    //   88	93	63	finally
    //   93	97	63	finally
    //   18	37	81	finally
    //   38	45	81	finally
    //   83	85	81	finally
    //   103	110	111	finally
    //   113	115	111	finally
    //   71	78	118	finally
    //   120	122	118	finally
  }
  
  public void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("n >= 0 required");
    }
    if (paramLong == 0L) {}
    label67:
    do
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
      try
      {
        l1 = this.requested;
      }
      finally
      {
        long l1;
        Producer localProducer;
        if (0 != 0) {
          break;
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
            break label67;
          }
          l1 = Long.MAX_VALUE;
          break label67;
        }
      }
      this.requested = l1;
      localProducer = this.currentProducer;
      break label161;
      localProducer.request(paramLong);
      emitLoop();
    } while (1 != 0);
    try
    {
      this.emitting = false;
      return;
    }
    finally {}
  }
  
  public void setProducer(Producer paramProducer)
  {
    try
    {
      if (this.emitting)
      {
        Producer localProducer = paramProducer;
        if (paramProducer == null) {
          localProducer = NULL_PRODUCER;
        }
        this.missedProducer = localProducer;
        return;
      }
      this.emitting = true;
    }
    finally {}
    try
    {
      this.currentProducer = paramProducer;
    }
    finally
    {
      if (0 != 0) {
        break label94;
      }
      try
      {
        this.emitting = false;
        throw paramProducer;
      }
      finally
      {
        throw paramProducer;
        return;
      }
    }
    paramProducer.request(this.requested);
    emitLoop();
    if (1 == 0) {
      try
      {
        this.emitting = false;
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\producers\ProducerArbiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */