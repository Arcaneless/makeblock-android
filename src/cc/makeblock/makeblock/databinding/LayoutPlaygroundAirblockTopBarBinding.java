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
import cc.makeblock.makeblock.customview.playground.AirBlockPowerSwitch;
import cc.makeblock.makeblock.customview.playground.BatteryView;
import cc.makeblock.makeblock.customview.playground.SignalView;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockBaseViewModel;

public class LayoutPlaygroundAirblockTopBarBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final AirBlockPowerSwitch airblockPowerSwitch;
  @NonNull
  public final PercentRelativeLayout back;
  @Nullable
  private final View.OnClickListener mCallback108;
  @Nullable
  private final View.OnClickListener mCallback109;
  @Nullable
  private final View.OnClickListener mCallback110;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirBlockBaseViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final SignalView mboundView3;
  @NonNull
  private final BatteryView mboundView4;
  @NonNull
  public final PercentRelativeLayout menu;
  
  public LayoutPlaygroundAirblockTopBarBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 6, sIncludes, sViewsWithIds);
    this.airblockPowerSwitch = ((AirBlockPowerSwitch)paramDataBindingComponent[2]);
    this.airblockPowerSwitch.setTag(null);
    this.back = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.back.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView3 = ((SignalView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((BatteryView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.menu = ((PercentRelativeLayout)paramDataBindingComponent[5]);
    this.menu.setTag(null);
    setRootTag(paramView);
    this.mCallback108 = new OnClickListener(this, 1);
    this.mCallback109 = new OnClickListener(this, 2);
    this.mCallback110 = new OnClickListener(this, 3);
    invalidateAll();
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/layout_playground_airblock_top_bar_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LayoutPlaygroundAirblockTopBarBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427494, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutPlaygroundAirblockTopBarBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LayoutPlaygroundAirblockTopBarBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427494, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(AirBlockBaseViewModel paramAirBlockBaseViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 36) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 67) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 2) {
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
      AirBlockBaseViewModel localAirBlockBaseViewModel = this.mViewModel;
      if (localAirBlockBaseViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localAirBlockBaseViewModel.goBack(ViewTools.getActivity(paramView));
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.switchPower();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openMenu();
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
      float f2 = 0.0F;
      float f5 = 0.0F;
      boolean bool2 = false;
      boolean bool3 = false;
      float f4 = 0.0F;
      AirBlockBaseViewModel localAirBlockBaseViewModel = this.mViewModel;
      float f3 = f4;
      if ((0x1F & l) != 0L)
      {
        float f1 = f5;
        if ((0x19 & l) != 0L)
        {
          f1 = f5;
          if (localAirBlockBaseViewModel != null) {
            f1 = localAirBlockBaseViewModel.getBatteryPercent();
          }
        }
        boolean bool1 = bool3;
        if ((0x13 & l) != 0L)
        {
          bool1 = bool3;
          if (localAirBlockBaseViewModel != null) {
            bool1 = localAirBlockBaseViewModel.getIsPowerOn();
          }
        }
        f2 = f1;
        bool2 = bool1;
        f3 = f4;
        if ((0x15 & l) != 0L)
        {
          f2 = f1;
          bool2 = bool1;
          f3 = f4;
          if (localAirBlockBaseViewModel != null)
          {
            f3 = localAirBlockBaseViewModel.getSignalPercent();
            bool2 = bool1;
            f2 = f1;
          }
        }
      }
      if ((0x10 & l) != 0L)
      {
        this.airblockPowerSwitch.setOnClickListener(this.mCallback109);
        this.back.setOnClickListener(this.mCallback108);
        this.menu.setOnClickListener(this.mCallback110);
      }
      if ((0x13 & l) != 0L) {
        this.airblockPowerSwitch.setState(bool2);
      }
      if ((0x15 & l) != 0L) {
        this.mboundView3.setSignalPercent(f3);
      }
      if ((0x19 & l) != 0L) {
        this.mboundView4.setBatteryPercent(f2);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public AirBlockBaseViewModel getViewModel()
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
    return onChangeViewModel((AirBlockBaseViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirBlockBaseViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirBlockBaseViewModel paramAirBlockBaseViewModel)
  {
    updateRegistration(0, paramAirBlockBaseViewModel);
    this.mViewModel = paramAirBlockBaseViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LayoutPlaygroundAirblockTopBarBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */