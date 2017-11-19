package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RequestManagerFragment
  extends Fragment
{
  private static final String TAG = "RMFragment";
  private final HashSet<RequestManagerFragment> childRequestManagerFragments = new HashSet();
  private final ActivityFragmentLifecycle lifecycle;
  @Nullable
  private Fragment parentFragmentHint;
  @Nullable
  private RequestManager requestManager;
  private final RequestManagerTreeNode requestManagerTreeNode = new FragmentRequestManagerTreeNode();
  @Nullable
  private RequestManagerFragment rootRequestManagerFragment;
  
  public RequestManagerFragment()
  {
    this(new ActivityFragmentLifecycle());
  }
  
  @SuppressLint({"ValidFragment"})
  RequestManagerFragment(ActivityFragmentLifecycle paramActivityFragmentLifecycle)
  {
    this.lifecycle = paramActivityFragmentLifecycle;
  }
  
  private void addChildRequestManagerFragment(RequestManagerFragment paramRequestManagerFragment)
  {
    this.childRequestManagerFragments.add(paramRequestManagerFragment);
  }
  
  @TargetApi(17)
  private Fragment getParentFragmentUsingHint()
  {
    if (Build.VERSION.SDK_INT >= 17) {}
    for (Fragment localFragment = getParentFragment(); localFragment != null; localFragment = null) {
      return localFragment;
    }
    return this.parentFragmentHint;
  }
  
  @TargetApi(17)
  private boolean isDescendant(Fragment paramFragment)
  {
    Fragment localFragment = getParentFragment();
    while (paramFragment.getParentFragment() != null)
    {
      if (paramFragment.getParentFragment() == localFragment) {
        return true;
      }
      paramFragment = paramFragment.getParentFragment();
    }
    return false;
  }
  
  private void registerFragmentWithRoot(Activity paramActivity)
  {
    unregisterFragmentWithRoot();
    this.rootRequestManagerFragment = Glide.get(paramActivity).getRequestManagerRetriever().getRequestManagerFragment(paramActivity.getFragmentManager(), null);
    if (this.rootRequestManagerFragment != this) {
      this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
    }
  }
  
  private void removeChildRequestManagerFragment(RequestManagerFragment paramRequestManagerFragment)
  {
    this.childRequestManagerFragments.remove(paramRequestManagerFragment);
  }
  
  private void unregisterFragmentWithRoot()
  {
    if (this.rootRequestManagerFragment != null)
    {
      this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
      this.rootRequestManagerFragment = null;
    }
  }
  
  @TargetApi(17)
  public Set<RequestManagerFragment> getDescendantRequestManagerFragments()
  {
    if (this.rootRequestManagerFragment == this) {
      return Collections.unmodifiableSet(this.childRequestManagerFragments);
    }
    if ((this.rootRequestManagerFragment == null) || (Build.VERSION.SDK_INT < 17)) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.rootRequestManagerFragment.getDescendantRequestManagerFragments().iterator();
    while (localIterator.hasNext())
    {
      RequestManagerFragment localRequestManagerFragment = (RequestManagerFragment)localIterator.next();
      if (isDescendant(localRequestManagerFragment.getParentFragment())) {
        localHashSet.add(localRequestManagerFragment);
      }
    }
    return Collections.unmodifiableSet(localHashSet);
  }
  
  ActivityFragmentLifecycle getGlideLifecycle()
  {
    return this.lifecycle;
  }
  
  @Nullable
  public RequestManager getRequestManager()
  {
    return this.requestManager;
  }
  
  public RequestManagerTreeNode getRequestManagerTreeNode()
  {
    return this.requestManagerTreeNode;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    try
    {
      registerFragmentWithRoot(paramActivity);
      return;
    }
    catch (IllegalStateException paramActivity)
    {
      while (!Log.isLoggable("RMFragment", 5)) {}
      Log.w("RMFragment", "Unable to register fragment with root", paramActivity);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.lifecycle.onDestroy();
    unregisterFragmentWithRoot();
  }
  
  public void onDetach()
  {
    super.onDetach();
    unregisterFragmentWithRoot();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    if (this.requestManager != null) {
      this.requestManager.onLowMemory();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.lifecycle.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
    this.lifecycle.onStop();
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if (this.requestManager != null) {
      this.requestManager.onTrimMemory(paramInt);
    }
  }
  
  void setParentFragmentHint(Fragment paramFragment)
  {
    this.parentFragmentHint = paramFragment;
    if ((paramFragment != null) && (paramFragment.getActivity() != null)) {
      registerFragmentWithRoot(paramFragment.getActivity());
    }
  }
  
  public void setRequestManager(RequestManager paramRequestManager)
  {
    this.requestManager = paramRequestManager;
  }
  
  public String toString()
  {
    return super.toString() + "{parent=" + getParentFragmentUsingHint() + "}";
  }
  
  private class FragmentRequestManagerTreeNode
    implements RequestManagerTreeNode
  {
    FragmentRequestManagerTreeNode() {}
    
    public Set<RequestManager> getDescendants()
    {
      Object localObject = RequestManagerFragment.this.getDescendantRequestManagerFragments();
      HashSet localHashSet = new HashSet(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        RequestManagerFragment localRequestManagerFragment = (RequestManagerFragment)((Iterator)localObject).next();
        if (localRequestManagerFragment.getRequestManager() != null) {
          localHashSet.add(localRequestManagerFragment.getRequestManager());
        }
      }
      return localHashSet;
    }
    
    public String toString()
    {
      return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\RequestManagerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */