package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.AnimatedProgressView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.firmware.FirmwareUpdateActivityViewModel;
import com.gzsll.jsbridge.WVJBWebView;

public class ActivityFirmwareUpdateBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final WVJBWebView firmwareHelperWebview;
  private long mDirtyFlags = -1L;
  @Nullable
  private FirmwareUpdateActivityViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView1;
  @NonNull
  private final AnimatedProgressView mboundView2;
  
  static
  {
    sViewsWithIds.put(2131296451, 3);
  }
  
  public ActivityFirmwareUpdateBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.firmwareHelperWebview = ((WVJBWebView)paramDataBindingComponent[3]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((AnimatedProgressView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_firmware_update_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityFirmwareUpdateBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427365, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityFirmwareUpdateBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427365, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(FirmwareUpdateActivityViewModel paramFirmwareUpdateActivityViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 77) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 76) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      float f2 = 0.0F;
      float f3 = 0.0F;
      Object localObject3 = null;
      FirmwareUpdateActivityViewModel localFirmwareUpdateActivityViewModel = this.mViewModel;
      Object localObject1 = localObject3;
      if ((0xF & l) != 0L)
      {
        float f1 = f3;
        if ((l & 0xD) != 0L)
        {
          f1 = f3;
          if (localFirmwareUpdateActivityViewModel != null) {
            f1 = localFirmwareUpdateActivityViewModel.getUpdatingProgressFloat();
          }
        }
        f2 = f1;
        localObject1 = localObject3;
        if ((l & 0xB) != 0L)
        {
          f2 = f1;
          localObject1 = localObject3;
          if (localFirmwareUpdateActivityViewModel != null)
          {
            localObject1 = localFirmwareUpdateActivityViewModel.getUpdatingProgressText();
            f2 = f1;
          }
        }
      }
      if ((l & 0xB) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView1, (CharSequence)localObject1);
      }
      if ((l & 0xD) != 0L) {
        this.mboundView2.setProgress(f2);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public FirmwareUpdateActivityViewModel getViewModel()
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
      this.mDirtyFlags = 8L;
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
    return onChangeViewModel((FirmwareUpdateActivityViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((FirmwareUpdateActivityViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable FirmwareUpdateActivityViewModel paramFirmwareUpdateActivityViewModel)
  {
    updateRegistration(0, paramFirmwareUpdateActivityViewModel);
    this.mViewModel = paramFirmwareUpdateActivityViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityFirmwareUpdateBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */