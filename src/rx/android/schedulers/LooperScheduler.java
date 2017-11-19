package rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.Subscriptions;

class LooperScheduler
  extends Scheduler
{
  private final Handler handler;
  
  LooperScheduler(Handler paramHandler)
  {
    this.handler = paramHandler;
  }
  
  LooperScheduler(Looper paramLooper)
  {
    this.handler = new Handler(paramLooper);
  }
  
  public Scheduler.Worker createWorker()
  {
    return new HandlerWorker(this.handler);
  }
  
  static class HandlerWorker
    extends Scheduler.Worker
  {
    private final Handler handler;
    private final RxAndroidSchedulersHook hook;
    private volatile boolean unsubscribed;
    
    HandlerWorker(Handler paramHandler)
    {
      this.handler = paramHandler;
      this.hook = RxAndroidPlugins.getInstance().getSchedulersHook();
    }
    
    public boolean isUnsubscribed()
    {
      return this.unsubscribed;
    }
    
    public Subscription schedule(Action0 paramAction0)
    {
      return schedule(paramAction0, 0L, TimeUnit.MILLISECONDS);
    }
    
    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (this.unsubscribed) {
        paramAction0 = Subscriptions.unsubscribed();
      }
      LooperScheduler.ScheduledAction localScheduledAction;
      do
      {
        return paramAction0;
        localScheduledAction = new LooperScheduler.ScheduledAction(this.hook.onSchedule(paramAction0), this.handler);
        paramAction0 = Message.obtain(this.handler, localScheduledAction);
        paramAction0.obj = this;
        this.handler.sendMessageDelayed(paramAction0, paramTimeUnit.toMillis(paramLong));
        paramAction0 = localScheduledAction;
      } while (!this.unsubscribed);
      this.handler.removeCallbacks(localScheduledAction);
      return Subscriptions.unsubscribed();
    }
    
    public void unsubscribe()
    {
      this.unsubscribed = true;
      this.handler.removeCallbacksAndMessages(this);
    }
  }
  
  static final class ScheduledAction
    implements Runnable, Subscription
  {
    private final Action0 action;
    private final Handler handler;
    private volatile boolean unsubscribed;
    
    ScheduledAction(Action0 paramAction0, Handler paramHandler)
    {
      this.action = paramAction0;
      this.handler = paramHandler;
    }
    
    public boolean isUnsubscribed()
    {
      return this.unsubscribed;
    }
    
    public void run()
    {
      try
      {
        this.action.call();
        return;
      }
      catch (Throwable localThrowable)
      {
        if (!(localThrowable instanceof OnErrorNotImplementedException)) {}
      }
      for (IllegalStateException localIllegalStateException = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", localThrowable);; localIllegalStateException = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", localIllegalStateException))
      {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(localIllegalStateException);
        Thread localThread = Thread.currentThread();
        localThread.getUncaughtExceptionHandler().uncaughtException(localThread, localIllegalStateException);
        return;
      }
    }
    
    public void unsubscribe()
    {
      this.unsubscribed = true;
      this.handler.removeCallbacks(this);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\android\schedulers\LooperScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */