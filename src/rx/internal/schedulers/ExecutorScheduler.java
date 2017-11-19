package rx.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

public final class ExecutorScheduler
  extends Scheduler
{
  final Executor executor;
  
  public ExecutorScheduler(Executor paramExecutor)
  {
    this.executor = paramExecutor;
  }
  
  public Scheduler.Worker createWorker()
  {
    return new ExecutorSchedulerWorker(this.executor);
  }
  
  static final class ExecutorSchedulerWorker
    extends Scheduler.Worker
    implements Runnable
  {
    final Executor executor;
    final ConcurrentLinkedQueue<ScheduledAction> queue;
    final ScheduledExecutorService service;
    final CompositeSubscription tasks;
    final AtomicInteger wip;
    
    public ExecutorSchedulerWorker(Executor paramExecutor)
    {
      this.executor = paramExecutor;
      this.queue = new ConcurrentLinkedQueue();
      this.wip = new AtomicInteger();
      this.tasks = new CompositeSubscription();
      this.service = GenericScheduledExecutorService.getInstance();
    }
    
    public boolean isUnsubscribed()
    {
      return this.tasks.isUnsubscribed();
    }
    
    public void run()
    {
      do
      {
        if (this.tasks.isUnsubscribed()) {
          this.queue.clear();
        }
        ScheduledAction localScheduledAction;
        do
        {
          return;
          localScheduledAction = (ScheduledAction)this.queue.poll();
        } while (localScheduledAction == null);
        if (!localScheduledAction.isUnsubscribed())
        {
          if (this.tasks.isUnsubscribed()) {
            break;
          }
          localScheduledAction.run();
        }
      } while (this.wip.decrementAndGet() != 0);
      return;
      this.queue.clear();
    }
    
    public Subscription schedule(Action0 paramAction0)
    {
      if (isUnsubscribed()) {
        paramAction0 = Subscriptions.unsubscribed();
      }
      ScheduledAction localScheduledAction;
      do
      {
        return paramAction0;
        localScheduledAction = new ScheduledAction(paramAction0, this.tasks);
        this.tasks.add(localScheduledAction);
        this.queue.offer(localScheduledAction);
        paramAction0 = localScheduledAction;
      } while (this.wip.getAndIncrement() != 0);
      try
      {
        this.executor.execute(this);
        return localScheduledAction;
      }
      catch (RejectedExecutionException paramAction0)
      {
        this.tasks.remove(localScheduledAction);
        this.wip.decrementAndGet();
        RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAction0);
        throw paramAction0;
      }
    }
    
    public Subscription schedule(final Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong <= 0L) {
        return schedule(paramAction0);
      }
      if (isUnsubscribed()) {
        return Subscriptions.unsubscribed();
      }
      MultipleAssignmentSubscription localMultipleAssignmentSubscription1 = new MultipleAssignmentSubscription();
      final MultipleAssignmentSubscription localMultipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
      localMultipleAssignmentSubscription2.set(localMultipleAssignmentSubscription1);
      this.tasks.add(localMultipleAssignmentSubscription2);
      final Subscription localSubscription = Subscriptions.create(new Action0()
      {
        public void call()
        {
          ExecutorScheduler.ExecutorSchedulerWorker.this.tasks.remove(localMultipleAssignmentSubscription2);
        }
      });
      paramAction0 = new ScheduledAction(new Action0()
      {
        public void call()
        {
          if (localMultipleAssignmentSubscription2.isUnsubscribed()) {}
          Subscription localSubscription;
          do
          {
            return;
            localSubscription = ExecutorScheduler.ExecutorSchedulerWorker.this.schedule(paramAction0);
            localMultipleAssignmentSubscription2.set(localSubscription);
          } while (localSubscription.getClass() != ScheduledAction.class);
          ((ScheduledAction)localSubscription).add(localSubscription);
        }
      });
      localMultipleAssignmentSubscription1.set(paramAction0);
      try
      {
        paramAction0.add(this.service.schedule(paramAction0, paramLong, paramTimeUnit));
        return localSubscription;
      }
      catch (RejectedExecutionException paramAction0)
      {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAction0);
        throw paramAction0;
      }
    }
    
    public void unsubscribe()
    {
      this.tasks.unsubscribe();
      this.queue.clear();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\schedulers\ExecutorScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */