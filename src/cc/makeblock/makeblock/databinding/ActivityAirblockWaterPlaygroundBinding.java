package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.JoystickListener;
import android.databinding.generated.callback.JoystickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.MenuDrawerLayout;
import cc.makeblock.makeblock.customview.playground.DisableMaskView;
import cc.makeblock.makeblock.customview.playground.Joystick.JoystickListener;
import cc.makeblock.makeblock.customview.playground.JoystickWithHorizontalIndicator;
import cc.makeblock.makeblock.customview.playground.JoystickWithVerticalIndicator;
import cc.makeblock.makeblock.customview.playground.ProgressView;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockWaterViewModel;
import java.util.List;

public class ActivityAirblockWaterPlaygroundBinding
  extends ViewDataBinding
  implements JoystickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(8);
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final MenuDrawerLayout airblockSlideMenu;
  @Nullable
  private final Joystick.JoystickListener mCallback69;
  @Nullable
  private final Joystick.JoystickListener mCallback70;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirBlockWaterViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final ProgressView mboundView2;
  @NonNull
  private final ProgressView mboundView3;
  @NonNull
  private final JoystickWithHorizontalIndicator mboundView4;
  @NonNull
  private final JoystickWithVerticalIndicator mboundView5;
  @NonNull
  private final DisableMaskView mboundView6;
  @Nullable
  public final LayoutPlaygroundAirblockTopBarBinding toolBar;
  
  static
  {
    sIncludes.setIncludes(1, new String[] { "layout_playground_airblock_top_bar" }, new int[] { 7 }, new int[] { 2131427494 });
  }
  
  public ActivityAirblockWaterPlaygroundBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 8, sIncludes, sViewsWithIds);
    this.airblockSlideMenu = ((MenuDrawerLayout)paramDataBindingComponent[0]);
    this.airblockSlideMenu.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ProgressView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((ProgressView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((JoystickWithHorizontalIndicator)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((JoystickWithVerticalIndicator)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((DisableMaskView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.toolBar = ((LayoutPlaygroundAirblockTopBarBinding)paramDataBindingComponent[7]);
    setContainedBinding(this.toolBar);
    setRootTag(paramView);
    this.mCallback70 = new JoystickListener(this, 2);
    this.mCallback69 = new JoystickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_airblock_water_playground_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityAirblockWaterPlaygroundBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427359, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockWaterPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityAirblockWaterPlaygroundBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427359, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeToolBar(LayoutPlaygroundAirblockTopBarBinding paramLayoutPlaygroundAirblockTopBarBinding, int paramInt)
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
  
  private boolean onChangeViewModel(AirBlockWaterViewModel paramAirBlockWaterViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 65) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 73) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 41) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 36) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  public final void _internalCallbackOnJoystickMoved(int paramInt1, int paramInt2, float paramFloat)
  {
    switch (paramInt1)
    {
    }
    for (;;)
    {
      return;
      AirBlockWaterViewModel localAirBlockWaterViewModel = this.mViewModel;
      if (localAirBlockWaterViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockWaterViewModel.moveMoveJoystick(paramInt2, paramFloat);
        return;
      }
      continue;
      localAirBlockWaterViewModel = this.mViewModel;
      if (localAirBlockWaterViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockWaterViewModel.moveTurnJoystick(paramInt2, paramFloat);
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
      Object localObject4 = null;
      Object localObject6 = null;
      boolean bool3 = false;
      boolean bool7 = false;
      boolean bool4 = false;
      boolean bool2 = false;
      Object localObject5 = null;
      Object localObject7 = null;
      float f3 = 0.0F;
      float f2 = 0.0F;
      float f4 = 0.0F;
      float f5 = 0.0F;
      boolean bool6 = false;
      AirBlockWaterViewModel localAirBlockWaterViewModel = this.mViewModel;
      boolean bool5 = bool6;
      if ((0x7E & l) != 0L)
      {
        Object localObject3 = localObject6;
        Object localObject1 = localObject7;
        boolean bool1 = bool2;
        if ((0x42 & l) != 0L)
        {
          localObject3 = localObject6;
          localObject1 = localObject7;
          bool1 = bool2;
          if (localAirBlockWaterViewModel != null)
          {
            localObject3 = localAirBlockWaterViewModel.getMenuHeaderView();
            bool1 = localAirBlockWaterViewModel.getProgressBarWithShrinkAnimation();
            localObject1 = localAirBlockWaterViewModel.getMenuViews();
          }
        }
        bool2 = bool7;
        if ((0x62 & l) != 0L)
        {
          bool2 = bool7;
          if (localAirBlockWaterViewModel != null) {
            bool2 = localAirBlockWaterViewModel.getIsPowerOn();
          }
        }
        float f1 = f2;
        if ((0x52 & l) != 0L)
        {
          f1 = f2;
          if (localAirBlockWaterViewModel != null) {
            f1 = localAirBlockWaterViewModel.getMoveJoystickPercent();
          }
        }
        f2 = f5;
        if ((0x4A & l) != 0L)
        {
          f2 = f5;
          if (localAirBlockWaterViewModel != null) {
            f2 = localAirBlockWaterViewModel.getTurnJoystickPercent();
          }
        }
        bool3 = bool2;
        localObject4 = localObject3;
        localObject5 = localObject1;
        f3 = f1;
        bool4 = bool1;
        bool5 = bool6;
        f4 = f2;
        if ((0x46 & l) != 0L)
        {
          bool3 = bool2;
          localObject4 = localObject3;
          localObject5 = localObject1;
          f3 = f1;
          bool4 = bool1;
          bool5 = bool6;
          f4 = f2;
          if (localAirBlockWaterViewModel != null)
          {
            bool5 = localAirBlockWaterViewModel.isShowMenu();
            f4 = f2;
            bool4 = bool1;
            f3 = f1;
            localObject5 = localObject1;
            localObject4 = localObject3;
            bool3 = bool2;
          }
        }
      }
      if ((0x42 & l) != 0L)
      {
        this.airblockSlideMenu.setHeaderView((View)localObject4);
        this.airblockSlideMenu.setMenuViews((List)localObject5);
        this.mboundView2.setWithShrinkAnimation(bool4);
        this.mboundView3.setWithShrinkAnimation(bool4);
        this.toolBar.setViewModel(localAirBlockWaterViewModel);
      }
      if ((0x46 & l) != 0L) {
        this.airblockSlideMenu.setRightMenuStatus(bool5);
      }
      if ((0x4A & l) != 0L) {
        this.mboundView2.setProgress(f4);
      }
      if ((0x52 & l) != 0L) {
        this.mboundView3.setProgress(f3);
      }
      if ((0x40 & l) != 0L)
      {
        this.mboundView4.setJoystickListener(this.mCallback69);
        this.mboundView5.setJoystickListener(this.mCallback70);
      }
      if ((0x62 & l) != 0L) {
        this.mboundView6.setEnable(bool3);
      }
      executeBindingsOn(this.toolBar);
      return;
    }
    finally {}
  }
  
  @Nullable
  public AirBlockWaterViewModel getViewModel()
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
      if (!this.toolBar.hasPendingBindings()) {
        return false;
      }
    }
    finally {}
    return true;
  }
  
  public void invalidateAll()
  {
    try
    {
      this.mDirtyFlags = 64L;
      this.toolBar.invalidateAll();
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
    case 0: 
      return onChangeToolBar((LayoutPlaygroundAirblockTopBarBinding)paramObject, paramInt2);
    }
    return onChangeViewModel((AirBlockWaterViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirBlockWaterViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirBlockWaterViewModel paramAirBlockWaterViewModel)
  {
    updateRegistration(1, paramAirBlockWaterViewModel);
    this.mViewModel = paramAirBlockWaterViewModel;
    try
    {
      this.mDirtyFlags |= 0x2;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityAirblockWaterPlaygroundBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */