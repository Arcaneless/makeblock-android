package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cc.makeblock.makeblock.viewmodel.laboratory.WidgetDialogViewModel;

public class LabLayoutWidgetSoundBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @Nullable
  private final View.OnClickListener mCallback35;
  @Nullable
  private final View.OnClickListener mCallback36;
  @Nullable
  private final View.OnClickListener mCallback37;
  @Nullable
  private final View.OnClickListener mCallback38;
  @Nullable
  private final View.OnClickListener mCallback39;
  @Nullable
  private final View.OnClickListener mCallback40;
  private long mDirtyFlags = -1L;
  @Nullable
  private WidgetDialogViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final PercentRelativeLayout mboundView2;
  @NonNull
  private final PercentRelativeLayout mboundView3;
  @NonNull
  private final PercentRelativeLayout mboundView4;
  @NonNull
  private final PercentRelativeLayout mboundView5;
  @NonNull
  private final PercentRelativeLayout mboundView6;
  
  public LabLayoutWidgetSoundBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((PercentRelativeLayout)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((PercentRelativeLayout)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((PercentRelativeLayout)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((PercentRelativeLayout)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((PercentRelativeLayout)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    setRootTag(paramView);
    this.mCallback39 = new OnClickListener(this, 5);
    this.mCallback37 = new OnClickListener(this, 3);
    this.mCallback40 = new OnClickListener(this, 6);
    this.mCallback38 = new OnClickListener(this, 4);
    this.mCallback35 = new OnClickListener(this, 1);
    this.mCallback36 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/lab_layout_widget_sound_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LabLayoutWidgetSoundBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427443, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetSoundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LabLayoutWidgetSoundBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427443, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(WidgetDialogViewModel paramWidgetDialogViewModel, int paramInt)
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
        paramView.soundWhistle();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.soundTiger();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.soundJungleBell();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.soundAlert();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.soundStar();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.soundBirthday();
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
      WidgetDialogViewModel localWidgetDialogViewModel = this.mViewModel;
      if ((0x2 & l) != 0L)
      {
        this.mboundView1.setOnClickListener(this.mCallback35);
        this.mboundView2.setOnClickListener(this.mCallback36);
        this.mboundView3.setOnClickListener(this.mCallback37);
        this.mboundView4.setOnClickListener(this.mCallback38);
        this.mboundView5.setOnClickListener(this.mCallback39);
        this.mboundView6.setOnClickListener(this.mCallback40);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public WidgetDialogViewModel getViewModel()
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
    return onChangeViewModel((WidgetDialogViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((WidgetDialogViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable WidgetDialogViewModel paramWidgetDialogViewModel)
  {
    updateRegistration(0, paramWidgetDialogViewModel);
    this.mViewModel = paramWidgetDialogViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LabLayoutWidgetSoundBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */