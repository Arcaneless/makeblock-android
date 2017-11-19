package rx.schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.annotations.Experimental;
import rx.internal.schedulers.ExecutorScheduler;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.ObjectPool;
import rx.internal.util.RxRingBuffer;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;

public final class Schedulers
{
  private static final AtomicReference<Schedulers> INSTANCE = new AtomicReference();
  private final Scheduler computationScheduler;
  private final Scheduler ioScheduler;
  private final Scheduler newThreadScheduler;
  
  private Schedulers()
  {
    Object localObject = RxJavaPlugins.getInstance().getSchedulersHook();
    Scheduler localScheduler = ((RxJavaSchedulersHook)localObject).getComputationScheduler();
    if (localScheduler != null)
    {
      this.computationScheduler = localScheduler;
      localScheduler = ((RxJavaSchedulersHook)localObject).getIOScheduler();
      if (localScheduler == null) {
        break label64;
      }
    }
    label64:
    for (this.ioScheduler = localScheduler;; this.ioScheduler = RxJavaSchedulersHook.createIoScheduler())
    {
      localObject = ((RxJavaSchedulersHook)localObject).getNewThreadScheduler();
      if (localObject == null) {
        break label74;
      }
      this.newThreadScheduler = ((Scheduler)localObject);
      return;
      this.computationScheduler = RxJavaSchedulersHook.createComputationScheduler();
      break;
    }
    label74:
    this.newThreadScheduler = RxJavaSchedulersHook.createNewThreadScheduler();
  }
  
  public static Scheduler computation()
  {
    return getInstance().computationScheduler;
  }
  
  public static Scheduler from(Executor paramExecutor)
  {
    return new ExecutorScheduler(paramExecutor);
  }
  
  private static Schedulers getInstance()
  {
    for (;;)
    {
      Object localObject = (Schedulers)INSTANCE.get();
      if (localObject != null) {}
      Schedulers localSchedulers;
      do
      {
        return (Schedulers)localObject;
        localSchedulers = new Schedulers();
        localObject = localSchedulers;
      } while (INSTANCE.compareAndSet(null, localSchedulers));
      localSchedulers.shutdownInstance();
    }
  }
  
  public static Scheduler immediate()
  {
    return ImmediateScheduler.INSTANCE;
  }
  
  public static Scheduler io()
  {
    return getInstance().ioScheduler;
  }
  
  public static Scheduler newThread()
  {
    return getInstance().newThreadScheduler;
  }
  
  @Experimental
  public static void reset()
  {
    Schedulers localSchedulers = (Schedulers)INSTANCE.getAndSet(null);
    if (localSchedulers != null) {
      localSchedulers.shutdownInstance();
    }
  }
  
  public static void shutdown()
  {
    Schedulers localSchedulers = getInstance();
    localSchedulers.shutdownInstance();
    try
    {
      GenericScheduledExecutorService.INSTANCE.shutdown();
      RxRingBuffer.SPSC_POOL.shutdown();
      RxRingBuffer.SPMC_POOL.shutdown();
      return;
    }
    finally {}
  }
  
  static void start()
  {
    Schedulers localSchedulers = getInstance();
    localSchedulers.startInstance();
    try
    {
      GenericScheduledExecutorService.INSTANCE.start();
      RxRingBuffer.SPSC_POOL.start();
      RxRingBuffer.SPMC_POOL.start();
      return;
    }
    finally {}
  }
  
  public static TestScheduler test()
  {
    return new TestScheduler();
  }
  
  public static Scheduler trampoline()
  {
    return TrampolineScheduler.INSTANCE;
  }
  
  void shutdownInstance()
  {
    try
    {
      if ((this.computationScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.computationScheduler).shutdown();
      }
      if ((this.ioScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.ioScheduler).shutdown();
      }
      if ((this.newThreadScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.newThreadScheduler).shutdown();
      }
      return;
    }
    finally {}
  }
  
  void startInstance()
  {
    try
    {
      if ((this.computationScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.computationScheduler).start();
      }
      if ((this.ioScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.ioScheduler).start();
      }
      if ((this.newThreadScheduler instanceof SchedulerLifecycle)) {
        ((SchedulerLifecycle)this.newThreadScheduler).start();
      }
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\schedulers\Schedulers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */