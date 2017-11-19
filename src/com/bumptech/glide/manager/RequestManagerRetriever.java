package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestManagerRetriever
  implements Handler.Callback
{
  private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory()
  {
    public RequestManager build(Glide paramAnonymousGlide, Lifecycle paramAnonymousLifecycle, RequestManagerTreeNode paramAnonymousRequestManagerTreeNode)
    {
      return new RequestManager(paramAnonymousGlide, paramAnonymousLifecycle, paramAnonymousRequestManagerTreeNode);
    }
  };
  private static final String FRAGMENT_INDEX_KEY = "key";
  private static final String FRAGMENT_MANAGER_GET_FRAGMENT_KEY = "i";
  static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
  private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
  private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
  private static final String TAG = "RMRetriever";
  private volatile RequestManager applicationManager;
  private final RequestManagerFactory factory;
  private final Handler handler;
  final Map<android.app.FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
  final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
  private final Bundle tempBundle = new Bundle();
  private final ArrayMap<View, android.app.Fragment> tempViewToFragment = new ArrayMap();
  private final ArrayMap<View, android.support.v4.app.Fragment> tempViewToSupportFragment = new ArrayMap();
  
  public RequestManagerRetriever(@Nullable RequestManagerFactory paramRequestManagerFactory)
  {
    if (paramRequestManagerFactory != null) {}
    for (;;)
    {
      this.factory = paramRequestManagerFactory;
      this.handler = new Handler(Looper.getMainLooper(), this);
      return;
      paramRequestManagerFactory = DEFAULT_FACTORY;
    }
  }
  
  @TargetApi(17)
  private static void assertNotDestroyed(Activity paramActivity)
  {
    if ((Build.VERSION.SDK_INT >= 17) && (paramActivity.isDestroyed())) {
      throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
    }
  }
  
  private Activity findActivity(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return (Activity)paramContext;
    }
    if ((paramContext instanceof ContextWrapper)) {
      return findActivity(((ContextWrapper)paramContext).getBaseContext());
    }
    return null;
  }
  
  private void findAllFragmentsWithViews(android.app.FragmentManager paramFragmentManager, ArrayMap<View, android.app.Fragment> paramArrayMap)
  {
    int i = 0;
    for (;;)
    {
      this.tempBundle.putInt("key", i);
      Object localObject = null;
      try
      {
        android.app.Fragment localFragment = paramFragmentManager.getFragment(this.tempBundle, "i");
        localObject = localFragment;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      if (localObject == null) {
        return;
      }
      if (((android.app.Fragment)localObject).getView() != null)
      {
        paramArrayMap.put(((android.app.Fragment)localObject).getView(), localObject);
        if (Build.VERSION.SDK_INT >= 17) {
          findAllFragmentsWithViews(((android.app.Fragment)localObject).getChildFragmentManager(), paramArrayMap);
        }
      }
      i += 1;
    }
  }
  
  private static void findAllSupportFragmentsWithViews(@Nullable Collection<android.support.v4.app.Fragment> paramCollection, Map<View, android.support.v4.app.Fragment> paramMap)
  {
    if (paramCollection == null) {}
    for (;;)
    {
      return;
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        android.support.v4.app.Fragment localFragment = (android.support.v4.app.Fragment)paramCollection.next();
        if ((localFragment != null) && (localFragment.getView() != null))
        {
          paramMap.put(localFragment.getView(), localFragment);
          findAllSupportFragmentsWithViews(localFragment.getChildFragmentManager().getFragments(), paramMap);
        }
      }
    }
  }
  
  @Nullable
  private android.app.Fragment findFragment(View paramView, Activity paramActivity)
  {
    this.tempViewToFragment.clear();
    findAllFragmentsWithViews(paramActivity.getFragmentManager(), this.tempViewToFragment);
    View localView1 = null;
    View localView2 = paramActivity.findViewById(16908290);
    paramActivity = paramView;
    paramView = localView1;
    for (;;)
    {
      localView1 = paramView;
      if (!paramActivity.equals(localView2))
      {
        paramView = (android.app.Fragment)this.tempViewToFragment.get(paramActivity);
        if (paramView == null) {
          break label71;
        }
        localView1 = paramView;
      }
      label71:
      do
      {
        this.tempViewToFragment.clear();
        return localView1;
        localView1 = paramView;
      } while (!(paramActivity.getParent() instanceof View));
      paramActivity = (View)paramActivity.getParent();
    }
  }
  
  @Nullable
  private android.support.v4.app.Fragment findSupportFragment(View paramView, FragmentActivity paramFragmentActivity)
  {
    this.tempViewToSupportFragment.clear();
    findAllSupportFragmentsWithViews(paramFragmentActivity.getSupportFragmentManager().getFragments(), this.tempViewToSupportFragment);
    View localView1 = null;
    View localView2 = paramFragmentActivity.findViewById(16908290);
    paramFragmentActivity = paramView;
    paramView = localView1;
    for (;;)
    {
      localView1 = paramView;
      if (!paramFragmentActivity.equals(localView2))
      {
        paramView = (android.support.v4.app.Fragment)this.tempViewToSupportFragment.get(paramFragmentActivity);
        if (paramView == null) {
          break label73;
        }
        localView1 = paramView;
      }
      label73:
      do
      {
        this.tempViewToSupportFragment.clear();
        return localView1;
        localView1 = paramView;
      } while (!(paramFragmentActivity.getParent() instanceof View));
      paramFragmentActivity = (View)paramFragmentActivity.getParent();
    }
  }
  
  private RequestManager fragmentGet(Context paramContext, android.app.FragmentManager paramFragmentManager, android.app.Fragment paramFragment)
  {
    RequestManagerFragment localRequestManagerFragment = getRequestManagerFragment(paramFragmentManager, paramFragment);
    paramFragment = localRequestManagerFragment.getRequestManager();
    paramFragmentManager = paramFragment;
    if (paramFragment == null)
    {
      paramContext = Glide.get(paramContext);
      paramFragmentManager = this.factory.build(paramContext, localRequestManagerFragment.getGlideLifecycle(), localRequestManagerFragment.getRequestManagerTreeNode());
      localRequestManagerFragment.setRequestManager(paramFragmentManager);
    }
    return paramFragmentManager;
  }
  
  private RequestManager getApplicationManager(Context paramContext)
  {
    if (this.applicationManager == null) {}
    try
    {
      if (this.applicationManager == null)
      {
        paramContext = Glide.get(paramContext);
        this.applicationManager = this.factory.build(paramContext, new ApplicationLifecycle(), new EmptyRequestManagerTreeNode());
      }
      return this.applicationManager;
    }
    finally {}
  }
  
  private RequestManager supportFragmentGet(Context paramContext, android.support.v4.app.FragmentManager paramFragmentManager, android.support.v4.app.Fragment paramFragment)
  {
    SupportRequestManagerFragment localSupportRequestManagerFragment = getSupportRequestManagerFragment(paramFragmentManager, paramFragment);
    paramFragment = localSupportRequestManagerFragment.getRequestManager();
    paramFragmentManager = paramFragment;
    if (paramFragment == null)
    {
      paramContext = Glide.get(paramContext);
      paramFragmentManager = this.factory.build(paramContext, localSupportRequestManagerFragment.getGlideLifecycle(), localSupportRequestManagerFragment.getRequestManagerTreeNode());
      localSupportRequestManagerFragment.setRequestManager(paramFragmentManager);
    }
    return paramFragmentManager;
  }
  
  public RequestManager get(Activity paramActivity)
  {
    if (Util.isOnBackgroundThread()) {
      return get(paramActivity.getApplicationContext());
    }
    assertNotDestroyed(paramActivity);
    return fragmentGet(paramActivity, paramActivity.getFragmentManager(), null);
  }
  
  @TargetApi(17)
  public RequestManager get(android.app.Fragment paramFragment)
  {
    if (paramFragment.getActivity() == null) {
      throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
    }
    if ((Util.isOnBackgroundThread()) || (Build.VERSION.SDK_INT < 17)) {
      return get(paramFragment.getActivity().getApplicationContext());
    }
    android.app.FragmentManager localFragmentManager = paramFragment.getChildFragmentManager();
    return fragmentGet(paramFragment.getActivity(), localFragmentManager, paramFragment);
  }
  
  public RequestManager get(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("You cannot start a load on a null Context");
    }
    if ((Util.isOnMainThread()) && (!(paramContext instanceof Application)))
    {
      if ((paramContext instanceof FragmentActivity)) {
        return get((FragmentActivity)paramContext);
      }
      if ((paramContext instanceof Activity)) {
        return get((Activity)paramContext);
      }
      if ((paramContext instanceof ContextWrapper)) {
        return get(((ContextWrapper)paramContext).getBaseContext());
      }
    }
    return getApplicationManager(paramContext);
  }
  
  public RequestManager get(android.support.v4.app.Fragment paramFragment)
  {
    Preconditions.checkNotNull(paramFragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
    if (Util.isOnBackgroundThread()) {
      return get(paramFragment.getActivity().getApplicationContext());
    }
    android.support.v4.app.FragmentManager localFragmentManager = paramFragment.getChildFragmentManager();
    return supportFragmentGet(paramFragment.getActivity(), localFragmentManager, paramFragment);
  }
  
  public RequestManager get(FragmentActivity paramFragmentActivity)
  {
    if (Util.isOnBackgroundThread()) {
      return get(paramFragmentActivity.getApplicationContext());
    }
    assertNotDestroyed(paramFragmentActivity);
    return supportFragmentGet(paramFragmentActivity, paramFragmentActivity.getSupportFragmentManager(), null);
  }
  
  public RequestManager get(View paramView)
  {
    if (Util.isOnBackgroundThread()) {
      return get(paramView.getContext().getApplicationContext());
    }
    Preconditions.checkNotNull(paramView);
    Preconditions.checkNotNull(paramView.getContext(), "Unable to obtain a request manager for a view without a Context");
    Activity localActivity = findActivity(paramView.getContext());
    if (localActivity == null) {
      return get(paramView.getContext().getApplicationContext());
    }
    if ((localActivity instanceof FragmentActivity))
    {
      paramView = findSupportFragment(paramView, (FragmentActivity)localActivity);
      if (paramView == null) {
        return get(localActivity);
      }
      return get(paramView);
    }
    paramView = findFragment(paramView, localActivity);
    if (paramView == null) {
      return get(localActivity);
    }
    return get(paramView);
  }
  
  @TargetApi(17)
  RequestManagerFragment getRequestManagerFragment(android.app.FragmentManager paramFragmentManager, android.app.Fragment paramFragment)
  {
    RequestManagerFragment localRequestManagerFragment2 = (RequestManagerFragment)paramFragmentManager.findFragmentByTag("com.bumptech.glide.manager");
    RequestManagerFragment localRequestManagerFragment1 = localRequestManagerFragment2;
    if (localRequestManagerFragment2 == null)
    {
      localRequestManagerFragment2 = (RequestManagerFragment)this.pendingRequestManagerFragments.get(paramFragmentManager);
      localRequestManagerFragment1 = localRequestManagerFragment2;
      if (localRequestManagerFragment2 == null)
      {
        localRequestManagerFragment1 = new RequestManagerFragment();
        localRequestManagerFragment1.setParentFragmentHint(paramFragment);
        this.pendingRequestManagerFragments.put(paramFragmentManager, localRequestManagerFragment1);
        paramFragmentManager.beginTransaction().add(localRequestManagerFragment1, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.handler.obtainMessage(1, paramFragmentManager).sendToTarget();
      }
    }
    return localRequestManagerFragment1;
  }
  
  SupportRequestManagerFragment getSupportRequestManagerFragment(android.support.v4.app.FragmentManager paramFragmentManager, android.support.v4.app.Fragment paramFragment)
  {
    SupportRequestManagerFragment localSupportRequestManagerFragment2 = (SupportRequestManagerFragment)paramFragmentManager.findFragmentByTag("com.bumptech.glide.manager");
    SupportRequestManagerFragment localSupportRequestManagerFragment1 = localSupportRequestManagerFragment2;
    if (localSupportRequestManagerFragment2 == null)
    {
      localSupportRequestManagerFragment2 = (SupportRequestManagerFragment)this.pendingSupportRequestManagerFragments.get(paramFragmentManager);
      localSupportRequestManagerFragment1 = localSupportRequestManagerFragment2;
      if (localSupportRequestManagerFragment2 == null)
      {
        localSupportRequestManagerFragment1 = new SupportRequestManagerFragment();
        localSupportRequestManagerFragment1.setParentFragmentHint(paramFragment);
        this.pendingSupportRequestManagerFragments.put(paramFragmentManager, localSupportRequestManagerFragment1);
        paramFragmentManager.beginTransaction().add(localSupportRequestManagerFragment1, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.handler.obtainMessage(2, paramFragmentManager).sendToTarget();
      }
    }
    return localSupportRequestManagerFragment1;
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    boolean bool = true;
    Object localObject1 = null;
    Object localObject2 = null;
    switch (paramMessage.what)
    {
    default: 
      bool = false;
      paramMessage = (Message)localObject2;
    }
    for (;;)
    {
      if ((bool) && (localObject1 == null) && (Log.isLoggable("RMRetriever", 5))) {
        Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + paramMessage);
      }
      return bool;
      localObject1 = (android.app.FragmentManager)paramMessage.obj;
      paramMessage = (Message)localObject1;
      localObject1 = this.pendingRequestManagerFragments.remove(localObject1);
      continue;
      localObject1 = (android.support.v4.app.FragmentManager)paramMessage.obj;
      paramMessage = (Message)localObject1;
      localObject1 = this.pendingSupportRequestManagerFragments.remove(localObject1);
    }
  }
  
  public static abstract interface RequestManagerFactory
  {
    public abstract RequestManager build(Glide paramGlide, Lifecycle paramLifecycle, RequestManagerTreeNode paramRequestManagerTreeNode);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\RequestManagerRetriever.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */