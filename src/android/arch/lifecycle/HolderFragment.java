package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class HolderFragment
  extends Fragment
{
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String HOLDER_TAG = "android.arch.lifecycle.state.StateProviderHolderFragment";
  private static final String LOG_TAG = "ViewModelStores";
  private static final HolderFragmentManager sHolderFragmentManager = new HolderFragmentManager();
  private ViewModelStore mViewModelStore = new ViewModelStore();
  
  public HolderFragment()
  {
    setRetainInstance(true);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static HolderFragment holderFragmentFor(Fragment paramFragment)
  {
    return sHolderFragmentManager.holderFragmentFor(paramFragment);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static HolderFragment holderFragmentFor(FragmentActivity paramFragmentActivity)
  {
    return sHolderFragmentManager.holderFragmentFor(paramFragmentActivity);
  }
  
  public ViewModelStore getViewModelStore()
  {
    return this.mViewModelStore;
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    sHolderFragmentManager.holderFragmentCreated(this);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mViewModelStore.clear();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  static class HolderFragmentManager
  {
    private Application.ActivityLifecycleCallbacks mActivityCallbacks = new EmptyActivityLifecycleCallbacks()
    {
      public void onActivityDestroyed(Activity paramAnonymousActivity)
      {
        if ((HolderFragment)HolderFragment.HolderFragmentManager.this.mNotCommittedActivityHolders.remove(paramAnonymousActivity) != null) {
          Log.e("ViewModelStores", "Failed to save a ViewModel for " + paramAnonymousActivity);
        }
      }
    };
    private boolean mActivityCallbacksIsAdded = false;
    private Map<Activity, HolderFragment> mNotCommittedActivityHolders = new HashMap();
    private Map<Fragment, HolderFragment> mNotCommittedFragmentHolders = new HashMap();
    private FragmentManager.FragmentLifecycleCallbacks mParentDestroyedCallback = new FragmentManager.FragmentLifecycleCallbacks()
    {
      public void onFragmentDestroyed(FragmentManager paramAnonymousFragmentManager, Fragment paramAnonymousFragment)
      {
        super.onFragmentDestroyed(paramAnonymousFragmentManager, paramAnonymousFragment);
        if ((HolderFragment)HolderFragment.HolderFragmentManager.this.mNotCommittedFragmentHolders.remove(paramAnonymousFragment) != null) {
          Log.e("ViewModelStores", "Failed to save a ViewModel for " + paramAnonymousFragment);
        }
      }
    };
    
    private static HolderFragment createHolderFragment(FragmentManager paramFragmentManager)
    {
      HolderFragment localHolderFragment = new HolderFragment();
      paramFragmentManager.beginTransaction().add(localHolderFragment, "android.arch.lifecycle.state.StateProviderHolderFragment").commitAllowingStateLoss();
      return localHolderFragment;
    }
    
    private static HolderFragment findHolderFragment(FragmentManager paramFragmentManager)
    {
      if (paramFragmentManager.isDestroyed()) {
        throw new IllegalStateException("Can't access ViewModels from onDestroy");
      }
      paramFragmentManager = paramFragmentManager.findFragmentByTag("android.arch.lifecycle.state.StateProviderHolderFragment");
      if ((paramFragmentManager != null) && (!(paramFragmentManager instanceof HolderFragment))) {
        throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
      }
      return (HolderFragment)paramFragmentManager;
    }
    
    void holderFragmentCreated(Fragment paramFragment)
    {
      Fragment localFragment = paramFragment.getParentFragment();
      if (localFragment != null)
      {
        this.mNotCommittedFragmentHolders.remove(localFragment);
        localFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.mParentDestroyedCallback);
        return;
      }
      this.mNotCommittedActivityHolders.remove(paramFragment.getActivity());
    }
    
    HolderFragment holderFragmentFor(Fragment paramFragment)
    {
      Object localObject = paramFragment.getChildFragmentManager();
      HolderFragment localHolderFragment = findHolderFragment((FragmentManager)localObject);
      if (localHolderFragment != null) {
        return localHolderFragment;
      }
      localHolderFragment = (HolderFragment)this.mNotCommittedFragmentHolders.get(paramFragment);
      if (localHolderFragment != null) {
        return localHolderFragment;
      }
      paramFragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.mParentDestroyedCallback, false);
      localObject = createHolderFragment((FragmentManager)localObject);
      this.mNotCommittedFragmentHolders.put(paramFragment, localObject);
      return (HolderFragment)localObject;
    }
    
    HolderFragment holderFragmentFor(FragmentActivity paramFragmentActivity)
    {
      Object localObject = paramFragmentActivity.getSupportFragmentManager();
      HolderFragment localHolderFragment = findHolderFragment((FragmentManager)localObject);
      if (localHolderFragment != null) {
        return localHolderFragment;
      }
      localHolderFragment = (HolderFragment)this.mNotCommittedActivityHolders.get(paramFragmentActivity);
      if (localHolderFragment != null) {
        return localHolderFragment;
      }
      if (!this.mActivityCallbacksIsAdded)
      {
        this.mActivityCallbacksIsAdded = true;
        paramFragmentActivity.getApplication().registerActivityLifecycleCallbacks(this.mActivityCallbacks);
      }
      localObject = createHolderFragment((FragmentManager)localObject);
      this.mNotCommittedActivityHolders.put(paramFragmentActivity, localObject);
      return (HolderFragment)localObject;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\HolderFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */