package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.support.v4.app.FragmentTransaction;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

class LifecycleDispatcher
{
  private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
  private static AtomicBoolean sInitialized = new AtomicBoolean(false);
  
  private static void dispatchIfLifecycleOwner(Fragment paramFragment, Lifecycle.Event paramEvent)
  {
    if ((paramFragment instanceof LifecycleRegistryOwner)) {
      ((LifecycleRegistryOwner)paramFragment).getLifecycle().handleLifecycleEvent(paramEvent);
    }
  }
  
  static void init(Context paramContext)
  {
    if (sInitialized.getAndSet(true)) {
      return;
    }
    ((Application)paramContext.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
  }
  
  private static void markState(FragmentActivity paramFragmentActivity, Lifecycle.State paramState)
  {
    markStateIn(paramFragmentActivity, paramState);
    markState(paramFragmentActivity.getSupportFragmentManager(), paramState);
  }
  
  private static void markState(FragmentManager paramFragmentManager, Lifecycle.State paramState)
  {
    paramFragmentManager = paramFragmentManager.getFragments();
    if (paramFragmentManager == null) {}
    for (;;)
    {
      return;
      paramFragmentManager = paramFragmentManager.iterator();
      while (paramFragmentManager.hasNext())
      {
        Fragment localFragment = (Fragment)paramFragmentManager.next();
        if (localFragment != null)
        {
          markStateIn(localFragment, paramState);
          if (localFragment.isAdded()) {
            markState(localFragment.getChildFragmentManager(), paramState);
          }
        }
      }
    }
  }
  
  private static void markStateIn(Object paramObject, Lifecycle.State paramState)
  {
    if ((paramObject instanceof LifecycleRegistryOwner)) {
      ((LifecycleRegistryOwner)paramObject).getLifecycle().markState(paramState);
    }
  }
  
  public static class DestructionReportFragment
    extends Fragment
  {
    protected void dispatch(Lifecycle.Event paramEvent)
    {
      LifecycleDispatcher.dispatchIfLifecycleOwner(getParentFragment(), paramEvent);
    }
    
    public void onDestroy()
    {
      super.onDestroy();
      dispatch(Lifecycle.Event.ON_DESTROY);
    }
    
    public void onPause()
    {
      super.onPause();
      dispatch(Lifecycle.Event.ON_PAUSE);
    }
    
    public void onStop()
    {
      super.onStop();
      dispatch(Lifecycle.Event.ON_STOP);
    }
  }
  
  @VisibleForTesting
  static class DispatcherActivityCallback
    extends EmptyActivityLifecycleCallbacks
  {
    private final LifecycleDispatcher.FragmentCallback mFragmentCallback = new LifecycleDispatcher.FragmentCallback();
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
    {
      if ((paramActivity instanceof FragmentActivity)) {
        ((FragmentActivity)paramActivity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentCallback, true);
      }
      ReportFragment.injectIfNeededIn(paramActivity);
    }
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
    {
      if ((paramActivity instanceof FragmentActivity)) {
        LifecycleDispatcher.markState((FragmentActivity)paramActivity, Lifecycle.State.CREATED);
      }
    }
    
    public void onActivityStopped(Activity paramActivity)
    {
      if ((paramActivity instanceof FragmentActivity)) {
        LifecycleDispatcher.markState((FragmentActivity)paramActivity, Lifecycle.State.CREATED);
      }
    }
  }
  
  @VisibleForTesting
  static class FragmentCallback
    extends FragmentManager.FragmentLifecycleCallbacks
  {
    public void onFragmentCreated(FragmentManager paramFragmentManager, Fragment paramFragment, Bundle paramBundle)
    {
      LifecycleDispatcher.dispatchIfLifecycleOwner(paramFragment, Lifecycle.Event.ON_CREATE);
      if (!(paramFragment instanceof LifecycleRegistryOwner)) {}
      while (paramFragment.getChildFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") != null) {
        return;
      }
      paramFragment.getChildFragmentManager().beginTransaction().add(new LifecycleDispatcher.DestructionReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
    }
    
    public void onFragmentResumed(FragmentManager paramFragmentManager, Fragment paramFragment)
    {
      LifecycleDispatcher.dispatchIfLifecycleOwner(paramFragment, Lifecycle.Event.ON_RESUME);
    }
    
    public void onFragmentStarted(FragmentManager paramFragmentManager, Fragment paramFragment)
    {
      LifecycleDispatcher.dispatchIfLifecycleOwner(paramFragment, Lifecycle.Event.ON_START);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\LifecycleDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */