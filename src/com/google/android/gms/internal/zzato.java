package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class zzato
  extends zzats
{
  private static final AtomicLong zzbsV = new AtomicLong(Long.MIN_VALUE);
  private zzd zzbsM;
  private zzd zzbsN;
  private final PriorityBlockingQueue<FutureTask<?>> zzbsO = new PriorityBlockingQueue();
  private final BlockingQueue<FutureTask<?>> zzbsP = new LinkedBlockingQueue();
  private final Thread.UncaughtExceptionHandler zzbsQ = new zzb("Thread death: Uncaught exception on worker thread");
  private final Thread.UncaughtExceptionHandler zzbsR = new zzb("Thread death: Uncaught exception on network thread");
  private final Object zzbsS = new Object();
  private final Semaphore zzbsT = new Semaphore(2);
  private volatile boolean zzbsU;
  
  zzato(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  private void zza(zzc<?> paramzzc)
  {
    synchronized (this.zzbsS)
    {
      this.zzbsO.add(paramzzc);
      if (this.zzbsM == null)
      {
        this.zzbsM = new zzd("Measurement Worker", this.zzbsO);
        this.zzbsM.setUncaughtExceptionHandler(this.zzbsQ);
        this.zzbsM.start();
        return;
      }
      this.zzbsM.zzhf();
    }
  }
  
  private void zza(FutureTask<?> paramFutureTask)
  {
    synchronized (this.zzbsS)
    {
      this.zzbsP.add(paramFutureTask);
      if (this.zzbsN == null)
      {
        this.zzbsN = new zzd("Measurement Network", this.zzbsP);
        this.zzbsN.setUncaughtExceptionHandler(this.zzbsR);
        this.zzbsN.start();
        return;
      }
      this.zzbsN.zzhf();
    }
  }
  
  public void zzJf()
  {
    if (Thread.currentThread() != this.zzbsN) {
      throw new IllegalStateException("Call expected from network thread");
    }
  }
  
  public boolean zzLr()
  {
    return Thread.currentThread() == this.zzbsM;
  }
  
  public boolean zzbd()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  public <V> Future<V> zzd(Callable<V> paramCallable)
    throws IllegalStateException
  {
    zznA();
    zzac.zzw(paramCallable);
    paramCallable = new zzc(paramCallable, false, "Task exception on worker thread");
    if (Thread.currentThread() == this.zzbsM)
    {
      paramCallable.run();
      return paramCallable;
    }
    zza(paramCallable);
    return paramCallable;
  }
  
  public <V> Future<V> zze(Callable<V> paramCallable)
    throws IllegalStateException
  {
    zznA();
    zzac.zzw(paramCallable);
    paramCallable = new zzc(paramCallable, true, "Task exception on worker thread");
    if (Thread.currentThread() == this.zzbsM)
    {
      paramCallable.run();
      return paramCallable;
    }
    zza(paramCallable);
    return paramCallable;
  }
  
  public void zzm(Runnable paramRunnable)
    throws IllegalStateException
  {
    zznA();
    zzac.zzw(paramRunnable);
    zza(new zzc(paramRunnable, false, "Task exception on worker thread"));
  }
  
  public void zzmq()
  {
    if (Thread.currentThread() != this.zzbsM) {
      throw new IllegalStateException("Call expected from worker thread");
    }
  }
  
  protected void zzmr() {}
  
  public void zzn(Runnable paramRunnable)
    throws IllegalStateException
  {
    zznA();
    zzac.zzw(paramRunnable);
    zza(new zzc(paramRunnable, false, "Task exception on network thread"));
  }
  
  static class zza
    extends RuntimeException
  {}
  
  private final class zzb
    implements Thread.UncaughtExceptionHandler
  {
    private final String zzbsW;
    
    public zzb(String paramString)
    {
      zzac.zzw(paramString);
      this.zzbsW = paramString;
    }
    
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      try
      {
        zzato.this.zzJt().zzLa().zzj(this.zzbsW, paramThrowable);
        return;
      }
      finally
      {
        paramThread = finally;
        throw paramThread;
      }
    }
  }
  
  private final class zzc<V>
    extends FutureTask<V>
    implements Comparable<zzc>
  {
    private final String zzbsW;
    private final long zzbsY;
    private final boolean zzbsZ;
    
    zzc(Runnable paramRunnable, boolean paramBoolean, String paramString)
    {
      super(null);
      zzac.zzw(paramString);
      this.zzbsY = zzato.zzLs().getAndIncrement();
      this.zzbsW = paramString;
      this.zzbsZ = paramBoolean;
      if (this.zzbsY == Long.MAX_VALUE) {
        zzato.this.zzJt().zzLa().log("Tasks index overflow");
      }
    }
    
    zzc(boolean paramBoolean, String paramString)
    {
      super();
      Object localObject;
      zzac.zzw(localObject);
      this.zzbsY = zzato.zzLs().getAndIncrement();
      this.zzbsW = ((String)localObject);
      this.zzbsZ = paramString;
      if (this.zzbsY == Long.MAX_VALUE) {
        zzato.this.zzJt().zzLa().log("Tasks index overflow");
      }
    }
    
    protected void setException(Throwable paramThrowable)
    {
      zzato.this.zzJt().zzLa().zzj(this.zzbsW, paramThrowable);
      if ((paramThrowable instanceof zzato.zza)) {
        Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), paramThrowable);
      }
      super.setException(paramThrowable);
    }
    
    public int zzb(@NonNull zzc paramzzc)
    {
      if (this.zzbsZ != paramzzc.zzbsZ) {
        if (!this.zzbsZ) {}
      }
      while (this.zzbsY < paramzzc.zzbsY)
      {
        return -1;
        return 1;
      }
      if (this.zzbsY > paramzzc.zzbsY) {
        return 1;
      }
      zzato.this.zzJt().zzLb().zzj("Two tasks share the same index. index", Long.valueOf(this.zzbsY));
      return 0;
    }
  }
  
  private final class zzd
    extends Thread
  {
    private final Object zzbta;
    private final BlockingQueue<FutureTask<?>> zzbtb;
    
    public zzd(BlockingQueue<FutureTask<?>> paramBlockingQueue)
    {
      zzac.zzw(paramBlockingQueue);
      Object localObject;
      zzac.zzw(localObject);
      this.zzbta = new Object();
      this.zzbtb = ((BlockingQueue)localObject);
      setName(paramBlockingQueue);
    }
    
    private void zza(InterruptedException paramInterruptedException)
    {
      zzato.this.zzJt().zzLc().zzj(String.valueOf(getName()).concat(" was interrupted"), paramInterruptedException);
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: iload_1
      //   3: ifne +27 -> 30
      //   6: aload_0
      //   7: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   10: invokestatic 81	com/google/android/gms/internal/zzato:zza	(Lcom/google/android/gms/internal/zzato;)Ljava/util/concurrent/Semaphore;
      //   13: invokevirtual 86	java/util/concurrent/Semaphore:acquire	()V
      //   16: iconst_1
      //   17: istore_1
      //   18: goto -16 -> 2
      //   21: astore_3
      //   22: aload_0
      //   23: aload_3
      //   24: invokespecial 88	com/google/android/gms/internal/zzato$zzd:zza	(Ljava/lang/InterruptedException;)V
      //   27: goto -25 -> 2
      //   30: aload_0
      //   31: getfield 34	com/google/android/gms/internal/zzato$zzd:zzbtb	Ljava/util/concurrent/BlockingQueue;
      //   34: invokeinterface 94 1 0
      //   39: checkcast 96	java/util/concurrent/FutureTask
      //   42: astore_3
      //   43: aload_3
      //   44: ifnull +67 -> 111
      //   47: aload_3
      //   48: invokevirtual 98	java/util/concurrent/FutureTask:run	()V
      //   51: goto -21 -> 30
      //   54: astore 4
      //   56: aload_0
      //   57: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   60: invokestatic 102	com/google/android/gms/internal/zzato:zzc	(Lcom/google/android/gms/internal/zzato;)Ljava/lang/Object;
      //   63: astore_3
      //   64: aload_3
      //   65: monitorenter
      //   66: aload_0
      //   67: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   70: invokestatic 81	com/google/android/gms/internal/zzato:zza	(Lcom/google/android/gms/internal/zzato;)Ljava/util/concurrent/Semaphore;
      //   73: invokevirtual 105	java/util/concurrent/Semaphore:release	()V
      //   76: aload_0
      //   77: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   80: invokestatic 102	com/google/android/gms/internal/zzato:zzc	(Lcom/google/android/gms/internal/zzato;)Ljava/lang/Object;
      //   83: invokevirtual 108	java/lang/Object:notifyAll	()V
      //   86: aload_0
      //   87: aload_0
      //   88: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   91: invokestatic 111	com/google/android/gms/internal/zzato:zzd	(Lcom/google/android/gms/internal/zzato;)Lcom/google/android/gms/internal/zzato$zzd;
      //   94: if_acmpne +215 -> 309
      //   97: aload_0
      //   98: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   101: aconst_null
      //   102: invokestatic 114	com/google/android/gms/internal/zzato:zza	(Lcom/google/android/gms/internal/zzato;Lcom/google/android/gms/internal/zzato$zzd;)Lcom/google/android/gms/internal/zzato$zzd;
      //   105: pop
      //   106: aload_3
      //   107: monitorexit
      //   108: aload 4
      //   110: athrow
      //   111: aload_0
      //   112: getfield 32	com/google/android/gms/internal/zzato$zzd:zzbta	Ljava/lang/Object;
      //   115: astore_3
      //   116: aload_3
      //   117: monitorenter
      //   118: aload_0
      //   119: getfield 34	com/google/android/gms/internal/zzato$zzd:zzbtb	Ljava/util/concurrent/BlockingQueue;
      //   122: invokeinterface 117 1 0
      //   127: ifnonnull +25 -> 152
      //   130: aload_0
      //   131: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   134: invokestatic 121	com/google/android/gms/internal/zzato:zzb	(Lcom/google/android/gms/internal/zzato;)Z
      //   137: istore_2
      //   138: iload_2
      //   139: ifne +13 -> 152
      //   142: aload_0
      //   143: getfield 32	com/google/android/gms/internal/zzato$zzd:zzbta	Ljava/lang/Object;
      //   146: ldc2_w 122
      //   149: invokevirtual 127	java/lang/Object:wait	(J)V
      //   152: aload_3
      //   153: monitorexit
      //   154: aload_0
      //   155: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   158: invokestatic 102	com/google/android/gms/internal/zzato:zzc	(Lcom/google/android/gms/internal/zzato;)Ljava/lang/Object;
      //   161: astore_3
      //   162: aload_3
      //   163: monitorenter
      //   164: aload_0
      //   165: getfield 34	com/google/android/gms/internal/zzato$zzd:zzbtb	Ljava/util/concurrent/BlockingQueue;
      //   168: invokeinterface 117 1 0
      //   173: ifnonnull +124 -> 297
      //   176: aload_3
      //   177: monitorexit
      //   178: aload_0
      //   179: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   182: invokestatic 102	com/google/android/gms/internal/zzato:zzc	(Lcom/google/android/gms/internal/zzato;)Ljava/lang/Object;
      //   185: astore_3
      //   186: aload_3
      //   187: monitorenter
      //   188: aload_0
      //   189: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   192: invokestatic 81	com/google/android/gms/internal/zzato:zza	(Lcom/google/android/gms/internal/zzato;)Ljava/util/concurrent/Semaphore;
      //   195: invokevirtual 105	java/util/concurrent/Semaphore:release	()V
      //   198: aload_0
      //   199: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   202: invokestatic 102	com/google/android/gms/internal/zzato:zzc	(Lcom/google/android/gms/internal/zzato;)Ljava/lang/Object;
      //   205: invokevirtual 108	java/lang/Object:notifyAll	()V
      //   208: aload_0
      //   209: aload_0
      //   210: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   213: invokestatic 111	com/google/android/gms/internal/zzato:zzd	(Lcom/google/android/gms/internal/zzato;)Lcom/google/android/gms/internal/zzato$zzd;
      //   216: if_acmpne +33 -> 249
      //   219: aload_0
      //   220: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   223: aconst_null
      //   224: invokestatic 114	com/google/android/gms/internal/zzato:zza	(Lcom/google/android/gms/internal/zzato;Lcom/google/android/gms/internal/zzato$zzd;)Lcom/google/android/gms/internal/zzato$zzd;
      //   227: pop
      //   228: aload_3
      //   229: monitorexit
      //   230: return
      //   231: astore 4
      //   233: aload_0
      //   234: aload 4
      //   236: invokespecial 88	com/google/android/gms/internal/zzato$zzd:zza	(Ljava/lang/InterruptedException;)V
      //   239: goto -87 -> 152
      //   242: astore 4
      //   244: aload_3
      //   245: monitorexit
      //   246: aload 4
      //   248: athrow
      //   249: aload_0
      //   250: aload_0
      //   251: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   254: invokestatic 130	com/google/android/gms/internal/zzato:zze	(Lcom/google/android/gms/internal/zzato;)Lcom/google/android/gms/internal/zzato$zzd;
      //   257: if_acmpne +22 -> 279
      //   260: aload_0
      //   261: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   264: aconst_null
      //   265: invokestatic 132	com/google/android/gms/internal/zzato:zzb	(Lcom/google/android/gms/internal/zzato;Lcom/google/android/gms/internal/zzato$zzd;)Lcom/google/android/gms/internal/zzato$zzd;
      //   268: pop
      //   269: goto -41 -> 228
      //   272: astore 4
      //   274: aload_3
      //   275: monitorexit
      //   276: aload 4
      //   278: athrow
      //   279: aload_0
      //   280: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   283: invokevirtual 47	com/google/android/gms/internal/zzato:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   286: invokevirtual 135	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
      //   289: ldc -119
      //   291: invokevirtual 140	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
      //   294: goto -66 -> 228
      //   297: aload_3
      //   298: monitorexit
      //   299: goto -269 -> 30
      //   302: astore 4
      //   304: aload_3
      //   305: monitorexit
      //   306: aload 4
      //   308: athrow
      //   309: aload_0
      //   310: aload_0
      //   311: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   314: invokestatic 130	com/google/android/gms/internal/zzato:zze	(Lcom/google/android/gms/internal/zzato;)Lcom/google/android/gms/internal/zzato$zzd;
      //   317: if_acmpne +22 -> 339
      //   320: aload_0
      //   321: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   324: aconst_null
      //   325: invokestatic 132	com/google/android/gms/internal/zzato:zzb	(Lcom/google/android/gms/internal/zzato;Lcom/google/android/gms/internal/zzato$zzd;)Lcom/google/android/gms/internal/zzato$zzd;
      //   328: pop
      //   329: goto -223 -> 106
      //   332: astore 4
      //   334: aload_3
      //   335: monitorexit
      //   336: aload 4
      //   338: athrow
      //   339: aload_0
      //   340: getfield 18	com/google/android/gms/internal/zzato$zzd:zzbsX	Lcom/google/android/gms/internal/zzato;
      //   343: invokevirtual 47	com/google/android/gms/internal/zzato:zzJt	()Lcom/google/android/gms/internal/zzati;
      //   346: invokevirtual 135	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
      //   349: ldc -119
      //   351: invokevirtual 140	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
      //   354: goto -248 -> 106
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	357	0	this	zzd
      //   1	17	1	i	int
      //   137	2	2	bool	boolean
      //   21	3	3	localInterruptedException1	InterruptedException
      //   54	55	4	localObject2	Object
      //   231	4	4	localInterruptedException2	InterruptedException
      //   242	5	4	localObject3	Object
      //   272	5	4	localObject4	Object
      //   302	5	4	localObject5	Object
      //   332	5	4	localObject6	Object
      // Exception table:
      //   from	to	target	type
      //   6	16	21	java/lang/InterruptedException
      //   30	43	54	finally
      //   47	51	54	finally
      //   111	118	54	finally
      //   154	164	54	finally
      //   246	249	54	finally
      //   306	309	54	finally
      //   142	152	231	java/lang/InterruptedException
      //   118	138	242	finally
      //   142	152	242	finally
      //   152	154	242	finally
      //   233	239	242	finally
      //   244	246	242	finally
      //   188	228	272	finally
      //   228	230	272	finally
      //   249	269	272	finally
      //   274	276	272	finally
      //   279	294	272	finally
      //   164	178	302	finally
      //   297	299	302	finally
      //   304	306	302	finally
      //   66	106	332	finally
      //   106	108	332	finally
      //   309	329	332	finally
      //   334	336	332	finally
      //   339	354	332	finally
    }
    
    public void zzhf()
    {
      synchronized (this.zzbta)
      {
        this.zzbta.notifyAll();
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzato.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */