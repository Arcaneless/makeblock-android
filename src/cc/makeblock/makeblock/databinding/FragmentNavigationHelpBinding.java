package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationHelpViewModel;

public class FragmentNavigationHelpBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final AutoResizeTextView community;
  @Nullable
  private final View.OnClickListener mCallback17;
  @Nullable
  private final View.OnClickListener mCallback18;
  @Nullable
  private final View.OnClickListener mCallback19;
  private long mDirtyFlags = -1L;
  @Nullable
  private NavigationHelpViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  public final AutoResizeTextView qa;
  @NonNull
  public final AutoResizeTextView tutorial;
  
  public FragmentNavigationHelpBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.community = ((AutoResizeTextView)paramDataBindingComponent[2]);
    this.community.setTag(null);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.qa = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.qa.setTag(null);
    this.tutorial = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.tutorial.setTag(null);
    setRootTag(paramView);
    this.mCallback19 = new OnClickListener(this, 3);
    this.mCallback17 = new OnClickListener(this, 1);
    this.mCallback18 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_navigation_help_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentNavigationHelpBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427426, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationHelpBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentNavigationHelpBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427426, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(NavigationHelpViewModel paramNavigationHelpViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
      }
      finally {}
    } else {
      return false;
    }
    return true;
  }
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openQA();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openTutorial();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openCommunity();
        return;
      }
    }
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      NavigationHelpViewModel localNavigationHelpViewModel = this.mViewModel;
      if ((0x2 & l) != 0L)
      {
        this.community.setOnClickListener(this.mCallback18);
        this.qa.setOnClickListener(this.mCallback19);
        this.tutorial.setOnClickListener(this.mCallback17);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public NavigationHelpViewModel getViewModel()
  {
    return this.mViewModel;
  }
  
  public boolean hasPendingBindings()
  {
    try
    {
      if (this.mDirtyFlags != 0L) {
        return true;
      }
    }
    finally {}
    return false;
  }
  
  public void invalidateAll()
  {
    try
    {
      this.mDirtyFlags = 2L;
      requestRebind();
      return;
    }
    finally {}
  }
  
  protected boolean onFieldChange(int paramInt1, Object paramObject, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return false;
    }
    return onChangeViewModel((NavigationHelpViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((NavigationHelpViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable NavigationHelpViewModel paramNavigationHelpViewModel)
  {
    updateRegistration(0, paramNavigationHelpViewModel);
    this.mViewModel = paramNavigationHelpViewModel;
    try
    {
      this.mDirtyFlags |= 1L;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentNavigationHelpBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */