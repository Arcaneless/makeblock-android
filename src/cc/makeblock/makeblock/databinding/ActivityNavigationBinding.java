package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.NavigationMenuItem;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationViewModel;

public class ActivityNavigationBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView close;
  @NonNull
  public final FrameLayout content;
  @NonNull
  public final NavigationMenuItem device;
  @NonNull
  public final Guideline guideline;
  @NonNull
  public final NavigationMenuItem help;
  @Nullable
  private final View.OnClickListener mCallback104;
  @Nullable
  private final View.OnClickListener mCallback105;
  @Nullable
  private final View.OnClickListener mCallback106;
  @Nullable
  private final View.OnClickListener mCallback107;
  private long mDirtyFlags = -1L;
  @Nullable
  private NavigationViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  public final NavigationMenuItem settings;
  
  static
  {
    sViewsWithIds.put(2131296478, 5);
    sViewsWithIds.put(2131296416, 6);
  }
  
  public ActivityNavigationBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.close = ((ImageView)paramDataBindingComponent[1]);
    this.close.setTag(null);
    this.content = ((FrameLayout)paramDataBindingComponent[6]);
    this.device = ((NavigationMenuItem)paramDataBindingComponent[2]);
    this.device.setTag(null);
    this.guideline = ((Guideline)paramDataBindingComponent[5]);
    this.help = ((NavigationMenuItem)paramDataBindingComponent[3]);
    this.help.setTag(null);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.settings = ((NavigationMenuItem)paramDataBindingComponent[4]);
    this.settings.setTag(null);
    setRootTag(paramView);
    this.mCallback106 = new OnClickListener(this, 3);
    this.mCallback107 = new OnClickListener(this, 4);
    this.mCallback104 = new OnClickListener(this, 1);
    this.mCallback105 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityNavigationBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityNavigationBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_navigation_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityNavigationBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427380, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityNavigationBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427380, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(NavigationViewModel paramNavigationViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 18) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 32) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 60) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    return false;
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
        paramView.selectHelp();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.selectSettings();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.close();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.selectDevice();
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
      boolean bool4 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      boolean bool7 = false;
      boolean bool6 = false;
      NavigationViewModel localNavigationViewModel = this.mViewModel;
      boolean bool5 = bool6;
      if ((0x1F & l) != 0L)
      {
        boolean bool1 = bool2;
        if ((0x15 & l) != 0L)
        {
          bool1 = bool2;
          if (localNavigationViewModel != null) {
            bool1 = localNavigationViewModel.isHelpChecked();
          }
        }
        bool2 = bool7;
        if ((0x13 & l) != 0L)
        {
          bool2 = bool7;
          if (localNavigationViewModel != null) {
            bool2 = localNavigationViewModel.isDeviceChecked();
          }
        }
        bool3 = bool2;
        bool4 = bool1;
        bool5 = bool6;
        if ((0x19 & l) != 0L)
        {
          bool3 = bool2;
          bool4 = bool1;
          bool5 = bool6;
          if (localNavigationViewModel != null)
          {
            bool5 = localNavigationViewModel.isSettingsChecked();
            bool4 = bool1;
            bool3 = bool2;
          }
        }
      }
      if ((0x10 & l) != 0L)
      {
        this.close.setOnClickListener(this.mCallback104);
        this.device.setOnClickListener(this.mCallback105);
        this.help.setOnClickListener(this.mCallback106);
        this.settings.setOnClickListener(this.mCallback107);
      }
      if ((0x13 & l) != 0L) {
        this.device.setChecked(bool3);
      }
      if ((0x15 & l) != 0L) {
        this.help.setChecked(bool4);
      }
      if ((0x19 & l) != 0L) {
        this.settings.setChecked(bool5);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public NavigationViewModel getViewModel()
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
      this.mDirtyFlags = 16L;
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
    return onChangeViewModel((NavigationViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((NavigationViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable NavigationViewModel paramNavigationViewModel)
  {
    updateRegistration(0, paramNavigationViewModel);
    this.mViewModel = paramNavigationViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityNavigationBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */