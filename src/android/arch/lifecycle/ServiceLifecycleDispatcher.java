package android.arch.lifecycle;

import android.os.Handler;
import android.support.annotation.NonNull;

public class ServiceLifecycleDispatcher
{
  private final Handler mHandler;
  private DispatchRunnable mLastDispatchRunnable;
  private final LifecycleRegistry mRegistry;
  
  public ServiceLifecycleDispatcher(@NonNull LifecycleOwner paramLifecycleOwner)
  {
    this.mRegistry = new LifecycleRegistry(paramLifecycleOwner);
    this.mHandler = new Handler();
  }
  
  private void postDispatchRunnable(Lifecycle.Event paramEvent)
  {
    if (this.mLastDispatchRunnable != null) {
      this.mLastDispatchRunnable.run();
    }
    this.mLastDispatchRunnable = new DispatchRunnable(this.mRegistry, paramEvent);
    this.mHandler.postAtFrontOfQueue(this.mLastDispatchRunnable);
  }
  
  public Lifecycle getLifecycle()
  {
    return this.mRegistry;
  }
  
  public void onServicePreSuperOnBind()
  {
    postDispatchRunnable(Lifecycle.Event.ON_START);
  }
  
  public void onServicePreSuperOnCreate()
  {
    postDispatchRunnable(Lifecycle.Event.ON_CREATE);
  }
  
  public void onServicePreSuperOnDestroy()
  {
    postDispatchRunnable(Lifecycle.Event.ON_STOP);
    postDispatchRunnable(Lifecycle.Event.ON_DESTROY);
  }
  
  public void onServicePreSuperOnStart()
  {
    postDispatchRunnable(Lifecycle.Event.ON_START);
  }
  
  static class DispatchRunnable
    implements Runnable
  {
    final Lifecycle.Event mEvent;
    private final LifecycleRegistry mRegistry;
    private boolean mWasExecuted = false;
    
    DispatchRunnable(@NonNull LifecycleRegistry paramLifecycleRegistry, Lifecycle.Event paramEvent)
    {
      this.mRegistry = paramLifecycleRegistry;
      this.mEvent = paramEvent;
    }
    
    public void run()
    {
      if (!this.mWasExecuted)
      {
        this.mRegistry.handleLifecycleEvent(this.mEvent);
        this.mWasExecuted = true;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\ServiceLifecycleDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */