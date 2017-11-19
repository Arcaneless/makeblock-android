package rx.internal.schedulers;

import rx.Scheduler.Worker;
import rx.functions.Action0;

class SleepingAction
  implements Action0
{
  private final long execTime;
  private final Scheduler.Worker innerScheduler;
  private final Action0 underlying;
  
  public SleepingAction(Action0 paramAction0, Scheduler.Worker paramWorker, long paramLong)
  {
    this.underlying = paramAction0;
    this.innerScheduler = paramWorker;
    this.execTime = paramLong;
  }
  
  public void call()
  {
    if (this.innerScheduler.isUnsubscribed()) {}
    for (;;)
    {
      return;
      long l = this.execTime - this.innerScheduler.now();
      if (l > 0L) {}
      try
      {
        Thread.sleep(l);
        if (this.innerScheduler.isUnsubscribed()) {
          continue;
        }
        this.underlying.call();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        Thread.currentThread().interrupt();
        throw new RuntimeException(localInterruptedException);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\schedulers\SleepingAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */