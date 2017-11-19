package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.CircleProgressView;
import cc.makeblock.makeblock.customview.CropImageView;
import cc.makeblock.makeblock.viewmodel.panel.AirBlockBoardViewModel;
import cc.makeblock.makeblock.viewmodel.panel.BindingAdapter.BindingAdapterUtils;

public class WidgetAirDashBoardBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final PercentFrameLayout batteryContainer;
  @NonNull
  public final CropImageView batteryCover;
  @NonNull
  public final AutoResizeTextView flightHeightData;
  @NonNull
  public final AutoResizeTextView liftSpeed;
  @NonNull
  public final AutoResizeTextView liftSpeedUnit;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirBlockBoardViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final CircleProgressView mboundView2;
  @NonNull
  public final CropImageView signalCover;
  
  static
  {
    sViewsWithIds.put(2131296309, 7);
    sViewsWithIds.put(2131296554, 8);
  }
  
  public WidgetAirDashBoardBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 9, sIncludes, sViewsWithIds);
    this.batteryContainer = ((PercentFrameLayout)paramDataBindingComponent[7]);
    this.batteryCover = ((CropImageView)paramDataBindingComponent[5]);
    this.batteryCover.setTag(null);
    this.flightHeightData = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.flightHeightData.setTag(null);
    this.liftSpeed = ((AutoResizeTextView)paramDataBindingComponent[6]);
    this.liftSpeed.setTag(null);
    this.liftSpeedUnit = ((AutoResizeTextView)paramDataBindingComponent[8]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((CircleProgressView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.signalCover = ((CropImageView)paramDataBindingComponent[4]);
    this.signalCover.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/widget_air_dash_board_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new WidgetAirDashBoardBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427534, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static WidgetAirDashBoardBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (WidgetAirDashBoardBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427534, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(AirBlockBoardViewModel paramAirBlockBoardViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 29) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 30) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 31) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 67) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 2) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 69) {
      try
      {
        this.mDirtyFlags |= 0x40;
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
      float f4 = 0.0F;
      float f2 = 0.0F;
      float f5 = 0.0F;
      float f3 = 0.0F;
      Object localObject5 = null;
      Object localObject3 = null;
      float f7 = 0.0F;
      float f9 = 0.0F;
      Object localObject4 = null;
      Object localObject6 = null;
      float f8 = 0.0F;
      AirBlockBoardViewModel localAirBlockBoardViewModel = this.mViewModel;
      float f6 = f8;
      if ((0xFF & l) != 0L)
      {
        float f1 = f2;
        if ((0xA1 & l) != 0L)
        {
          f1 = f2;
          if (localAirBlockBoardViewModel != null) {
            f1 = localAirBlockBoardViewModel.getBatteryPercent();
          }
        }
        f2 = f3;
        if ((0x83 & l) != 0L)
        {
          f2 = f3;
          if (localAirBlockBoardViewModel != null) {
            f2 = localAirBlockBoardViewModel.getHeightDegrees();
          }
        }
        Object localObject1 = localObject3;
        if ((0xC1 & l) != 0L)
        {
          localObject1 = localObject3;
          if (localAirBlockBoardViewModel != null) {
            localObject1 = localAirBlockBoardViewModel.getSpeed();
          }
        }
        f3 = f9;
        if ((0x91 & l) != 0L)
        {
          f3 = f9;
          if (localAirBlockBoardViewModel != null) {
            f3 = localAirBlockBoardViewModel.getSignalPercent();
          }
        }
        localObject3 = localObject6;
        if ((0x89 & l) != 0L)
        {
          localObject3 = localObject6;
          if (localAirBlockBoardViewModel != null) {
            localObject3 = localAirBlockBoardViewModel.getHeightText();
          }
        }
        f4 = f1;
        f5 = f2;
        f6 = f8;
        localObject4 = localObject3;
        f7 = f3;
        localObject5 = localObject1;
        if ((0x85 & l) != 0L)
        {
          f4 = f1;
          f5 = f2;
          f6 = f8;
          localObject4 = localObject3;
          f7 = f3;
          localObject5 = localObject1;
          if (localAirBlockBoardViewModel != null)
          {
            f6 = localAirBlockBoardViewModel.getHeightProgress();
            localObject5 = localObject1;
            f7 = f3;
            localObject4 = localObject3;
            f5 = f2;
            f4 = f1;
          }
        }
      }
      if ((0xA1 & l) != 0L) {
        BindingAdapterUtils.setLayoutWidthPercent(this.batteryCover, f4);
      }
      if ((0x89 & l) != 0L) {
        TextViewBindingAdapter.setText(this.flightHeightData, (CharSequence)localObject4);
      }
      if ((0xC1 & l) != 0L) {
        TextViewBindingAdapter.setText(this.liftSpeed, (CharSequence)localObject5);
      }
      if (((0x83 & l) != 0L) && (getBuildSdkInt() >= 11)) {
        this.mboundView1.setRotation(f5);
      }
      if ((0x85 & l) != 0L) {
        this.mboundView2.setProgress(f6);
      }
      if ((0x91 & l) != 0L) {
        BindingAdapterUtils.setLayoutWidthPercent(this.signalCover, f7);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public AirBlockBoardViewModel getViewModel()
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
      this.mDirtyFlags = 128L;
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
    return onChangeViewModel((AirBlockBoardViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirBlockBoardViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirBlockBoardViewModel paramAirBlockBoardViewModel)
  {
    updateRegistration(0, paramAirBlockBoardViewModel);
    this.mViewModel = paramAirBlockBoardViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\WidgetAirDashBoardBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */