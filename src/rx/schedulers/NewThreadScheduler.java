package rx.schedulers;

import rx.Scheduler;
import rx.Scheduler.Worker;

@Deprecated
public final class NewThreadScheduler
  extends Scheduler
{
  private NewThreadScheduler()
  {
    throw new AssertionError();
  }
  
  public Scheduler.Worker createWorker()
  {
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\schedulers\NewThreadScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */