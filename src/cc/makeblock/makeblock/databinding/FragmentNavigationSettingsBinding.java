package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.CompoundButtonBindingAdapter;
import android.databinding.generated.callback.OnCheckedChangeListener;
import android.databinding.generated.callback.OnCheckedChangeListener.Listener;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationSettingsViewModel;

public class FragmentNavigationSettingsBinding
  extends ViewDataBinding
  implements OnClickListener.Listener, OnCheckedChangeListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final AutoResizeTextView about;
  @NonNull
  public final AutoResizeTextView connect;
  @NonNull
  public final AutoResizeTextView firmware;
  @Nullable
  private final View.OnClickListener mCallback135;
  @Nullable
  private final View.OnClickListener mCallback136;
  @Nullable
  private final View.OnClickListener mCallback137;
  @Nullable
  private final CompoundButton.OnCheckedChangeListener mCallback138;
  private long mDirtyFlags = -1L;
  @Nullable
  private NavigationSettingsViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  private final SwitchCompat mboundView4;
  @NonNull
  public final AutoResizeTextView update;
  
  static
  {
    sViewsWithIds.put(2131296411, 5);
  }
  
  public FragmentNavigationSettingsBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 6, sIncludes, sViewsWithIds);
    this.about = ((AutoResizeTextView)paramDataBindingComponent[2]);
    this.about.setTag(null);
    this.connect = ((AutoResizeTextView)paramDataBindingComponent[5]);
    this.firmware = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.firmware.setTag(null);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView4 = ((SwitchCompat)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.update = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.update.setTag(null);
    setRootTag(paramView);
    this.mCallback136 = new OnClickListener(this, 2);
    this.mCallback137 = new OnClickListener(this, 3);
    this.mCallback135 = new OnClickListener(this, 1);
    this.mCallback138 = new OnCheckedChangeListener(this, 4);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_navigation_settings_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentNavigationSettingsBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427427, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationSettingsBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentNavigationSettingsBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427427, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(NavigationSettingsViewModel paramNavigationSettingsViewModel, int paramInt)
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
  
  public final void _internalCallbackOnCheckedChanged(int paramInt, CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    paramCompoundButton = this.mViewModel;
    if (paramCompoundButton != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramCompoundButton.setAutoConnectionEnable(paramBoolean);
      }
      return;
    }
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
        paramView.openAbout();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openUpdate();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openFirmware();
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
      boolean bool2 = false;
      NavigationSettingsViewModel localNavigationSettingsViewModel = this.mViewModel;
      boolean bool1 = bool2;
      if ((l & 0x3) != 0L)
      {
        bool1 = bool2;
        if (localNavigationSettingsViewModel != null) {
          bool1 = localNavigationSettingsViewModel.isAutoConnectionEnable();
        }
      }
      if ((0x2 & l) != 0L)
      {
        this.about.setOnClickListener(this.mCallback136);
        this.firmware.setOnClickListener(this.mCallback135);
        this.mboundView4.setOnCheckedChangeListener(this.mCallback138);
        this.update.setOnClickListener(this.mCallback137);
      }
      if ((l & 0x3) != 0L) {
        CompoundButtonBindingAdapter.setChecked(this.mboundView4, bool1);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public NavigationSettingsViewModel getViewModel()
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
    return onChangeViewModel((NavigationSettingsViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((NavigationSettingsViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable NavigationSettingsViewModel paramNavigationSettingsViewModel)
  {
    updateRegistration(0, paramNavigationSettingsViewModel);
    this.mViewModel = paramNavigationSettingsViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentNavigationSettingsBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */