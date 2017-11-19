package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import rx.Scheduler;
import rx.Scheduler.Worker;

public final class NewThreadScheduler
  extends Scheduler
{
  private final ThreadFactory threadFactory;
  
  public NewThreadScheduler(ThreadFactory paramThreadFactory)
  {
    this.threadFactory = paramThreadFactory;
  }
  
  public Scheduler.Worker createWorker()
  {
    return new NewThreadWorker(this.threadFactory);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\schedulers\NewThreadScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */