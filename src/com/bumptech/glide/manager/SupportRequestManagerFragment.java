package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SupportRequestManagerFragment
  extends Fragment
{
  private static final String TAG = "SupportRMFragment";
  private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments = new HashSet();
  private final ActivityFragmentLifecycle lifecycle;
  @Nullable
  private Fragment parentFragmentHint;
  @Nullable
  private RequestManager requestManager;
  private final RequestManagerTreeNode requestManagerTreeNode = new SupportFragmentRequestManagerTreeNode();
  @Nullable
  private SupportRequestManagerFragment rootRequestManagerFragment;
  
  public SupportRequestManagerFragment()
  {
    this(new ActivityFragmentLifecycle());
  }
  
  @SuppressLint({"ValidFragment"})
  public SupportRequestManagerFragment(ActivityFragmentLifecycle paramActivityFragmentLifecycle)
  {
    this.lifecycle = paramActivityFragmentLifecycle;
  }
  
  private void addChildRequestManagerFragment(SupportRequestManagerFragment paramSupportRequestManagerFragment)
  {
    this.childRequestManagerFragments.add(paramSupportRequestManagerFragment);
  }
  
  private Fragment getParentFragmentUsingHint()
  {
    Fragment localFragment = getParentFragment();
    if (localFragment != null) {
      return localFragment;
    }
    return this.parentFragmentHint;
  }
  
  private boolean isDescendant(Fragment paramFragment)
  {
    Fragment localFragment = getParentFragmentUsingHint();
    while (paramFragment.getParentFragment() != null)
    {
      if (paramFragment.getParentFragment() == localFragment) {
        return true;
      }
      paramFragment = paramFragment.getParentFragment();
    }
    return false;
  }
  
  private void registerFragmentWithRoot(FragmentActivity paramFragmentActivity)
  {
    unregisterFragmentWithRoot();
    this.rootRequestManagerFragment = Glide.get(paramFragmentActivity).getRequestManagerRetriever().getSupportRequestManagerFragment(paramFragmentActivity.getSupportFragmentManager(), null);
    if (this.rootRequestManagerFragment != this) {
      this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
    }
  }
  
  private void removeChildRequestManagerFragment(SupportRequestManagerFragment paramSupportRequestManagerFragment)
  {
    this.childRequestManagerFragments.remove(paramSupportRequestManagerFragment);
  }
  
  private void unregisterFragmentWithRoot()
  {
    if (this.rootRequestManagerFragment != null)
    {
      this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
      this.rootRequestManagerFragment = null;
    }
  }
  
  public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments()
  {
    if (this.rootRequestManagerFragment == null) {
      return Collections.emptySet();
    }
    if (this.rootRequestManagerFragment == this) {
      return Collections.unmodifiableSet(this.childRequestManagerFragments);
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.rootRequestManagerFragment.getDescendantRequestManagerFragments().iterator();
    while (localIterator.hasNext())
    {
      SupportRequestManagerFragment localSupportRequestManagerFragment = (SupportRequestManagerFragment)localIterator.next();
      if (isDescendant(localSupportRequestManagerFragment.getParentFragmentUsingHint())) {
        localHashSet.add(localSupportRequestManagerFragment);
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
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    try
    {
      registerFragmentWithRoot(getActivity());
      return;
    }
    catch (IllegalStateException paramContext)
    {
      while (!Log.isLoggable("SupportRMFragment", 5)) {}
      Log.w("SupportRMFragment", "Unable to register fragment with root", paramContext);
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
    this.parentFragmentHint = null;
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
  
  private class SupportFragmentRequestManagerTreeNode
    implements RequestManagerTreeNode
  {
    SupportFragmentRequestManagerTreeNode() {}
    
    public Set<RequestManager> getDescendants()
    {
      Object localObject = SupportRequestManagerFragment.this.getDescendantRequestManagerFragments();
      HashSet localHashSet = new HashSet(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        SupportRequestManagerFragment localSupportRequestManagerFragment = (SupportRequestManagerFragment)((Iterator)localObject).next();
        if (localSupportRequestManagerFragment.getRequestManager() != null) {
          localHashSet.add(localSupportRequestManagerFragment.getRequestManager());
        }
      }
      return localHashSet;
    }
    
    public String toString()
    {
      return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\SupportRequestManagerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */