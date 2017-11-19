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

public class LabLayoutWidgetDetectorBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @Nullable
  private final View.OnClickListener mCallback57;
  @Nullable
  private final View.OnClickListener mCallback58;
  @Nullable
  private final View.OnClickListener mCallback59;
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
  
  public LabLayoutWidgetDetectorBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((PercentRelativeLayout)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((PercentRelativeLayout)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    setRootTag(paramView);
    this.mCallback59 = new OnClickListener(this, 3);
    this.mCallback57 = new OnClickListener(this, 1);
    this.mCallback58 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/lab_layout_widget_detector_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LabLayoutWidgetDetectorBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427440, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LabLayoutWidgetDetectorBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LabLayoutWidgetDetectorBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427440, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
        paramView.detectorLineFollow();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.detectorLight();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.detectorDistance();
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
        this.mboundView1.setOnClickListener(this.mCallback57);
        this.mboundView2.setOnClickListener(this.mCallback58);
        this.mboundView3.setOnClickListener(this.mCallback59);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LabLayoutWidgetDetectorBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */