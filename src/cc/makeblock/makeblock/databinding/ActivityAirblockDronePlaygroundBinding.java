package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.JoystickListener;
import android.databinding.generated.callback.JoystickListener.Listener;
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
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.MenuDrawerLayout;
import cc.makeblock.makeblock.customview.playground.DisableMaskView;
import cc.makeblock.makeblock.customview.playground.HexagonSkillView;
import cc.makeblock.makeblock.customview.playground.HexagonSkillView.OnSkillClickListener;
import cc.makeblock.makeblock.customview.playground.Joystick.JoystickListener;
import cc.makeblock.makeblock.customview.playground.JoystickWithWholeIndicator;
import cc.makeblock.makeblock.customview.playground.ProgressView;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockDroneViewModel;
import java.util.List;

public class ActivityAirblockDronePlaygroundBinding
  extends ViewDataBinding
  implements JoystickListener.Listener, OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(14);
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @Nullable
  private final Joystick.JoystickListener mCallback118;
  @Nullable
  private final Joystick.JoystickListener mCallback119;
  @Nullable
  private final View.OnClickListener mCallback120;
  @Nullable
  private final View.OnClickListener mCallback121;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirBlockDroneViewModel mViewModel;
  private OnSkillClickListenerImpl1 mViewModelCircleSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
  private OnSkillClickListenerImpl mViewModelRollSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
  private OnSkillClickListenerImpl2 mViewModelShakeSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
  @NonNull
  private final MenuDrawerLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final ImageView mboundView10;
  @NonNull
  private final ImageView mboundView11;
  @NonNull
  private final DisableMaskView mboundView12;
  @NonNull
  private final ProgressView mboundView2;
  @NonNull
  private final ProgressView mboundView3;
  @NonNull
  private final JoystickWithWholeIndicator mboundView4;
  @NonNull
  private final JoystickWithWholeIndicator mboundView5;
  @NonNull
  private final HexagonSkillView mboundView6;
  @NonNull
  private final HexagonSkillView mboundView7;
  @NonNull
  private final HexagonSkillView mboundView8;
  @NonNull
  private final DisableMaskView mboundView9;
  @Nullable
  public final LayoutPlaygroundAirblockTopBarBinding toolBar;
  
  static
  {
    sIncludes.setIncludes(1, new String[] { "layout_playground_airblock_top_bar" }, new int[] { 13 }, new int[] { 2131427494 });
  }
  
  public ActivityAirblockDronePlaygroundBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 14, sIncludes, sViewsWithIds);
    this.mboundView0 = ((MenuDrawerLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView10 = ((ImageView)paramDataBindingComponent[10]);
    this.mboundView10.setTag(null);
    this.mboundView11 = ((ImageView)paramDataBindingComponent[11]);
    this.mboundView11.setTag(null);
    this.mboundView12 = ((DisableMaskView)paramDataBindingComponent[12]);
    this.mboundView12.setTag(null);
    this.mboundView2 = ((ProgressView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((ProgressView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((JoystickWithWholeIndicator)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((JoystickWithWholeIndicator)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((HexagonSkillView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((HexagonSkillView)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((HexagonSkillView)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((DisableMaskView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    this.toolBar = ((LayoutPlaygroundAirblockTopBarBinding)paramDataBindingComponent[13]);
    setContainedBinding(this.toolBar);
    setRootTag(paramView);
    this.mCallback118 = new JoystickListener(this, 1);
    this.mCallback119 = new JoystickListener(this, 2);
    this.mCallback120 = new OnClickListener(this, 3);
    this.mCallback121 = new OnClickListener(this, 4);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_airblock_drone_playground_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityAirblockDronePlaygroundBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427357, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockDronePlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityAirblockDronePlaygroundBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427357, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
  
  private boolean onChangeViewModel(AirBlockDroneViewModel paramAirBlockDroneViewModel, int paramInt)
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
    if (paramInt == 78) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 33) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 61) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 36) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    if (paramInt == 35) {
      try
      {
        this.mDirtyFlags |= 0x80;
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
        paramView.takeOff();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.land();
        return;
      }
    }
  }
  
  public final void _internalCallbackOnJoystickMoved(int paramInt1, int paramInt2, float paramFloat)
  {
    switch (paramInt1)
    {
    }
    for (;;)
    {
      return;
      AirBlockDroneViewModel localAirBlockDroneViewModel = this.mViewModel;
      if (localAirBlockDroneViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockDroneViewModel.moveVerticalJoystick(paramInt2, paramFloat);
        return;
      }
      continue;
      localAirBlockDroneViewModel = this.mViewModel;
      if (localAirBlockDroneViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockDroneViewModel.moveHorizontalJoystick(paramInt2, paramFloat);
        return;
      }
    }
  }
  
  protected void executeBindings()
  {
    for (;;)
    {
      long l2;
      try
      {
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        float f4 = 0.0F;
        float f2 = 0.0F;
        Object localObject9 = null;
        Object localObject13 = null;
        Object localObject11 = null;
        Object localObject14 = null;
        boolean bool4 = false;
        boolean bool10 = false;
        boolean bool3 = false;
        boolean bool5 = false;
        boolean bool2 = false;
        float f3 = 0.0F;
        float f5 = 0.0F;
        int m = 0;
        j = 0;
        Object localObject8 = null;
        Object localObject15 = null;
        Object localObject10 = null;
        Object localObject16 = null;
        boolean bool6 = false;
        boolean bool9 = false;
        int k = 0;
        i = 0;
        Object localObject12 = null;
        Object localObject17 = null;
        boolean bool8 = false;
        AirBlockDroneViewModel localAirBlockDroneViewModel = this.mViewModel;
        long l3 = l2;
        boolean bool7 = bool8;
        if ((0x1FE & l2) != 0L)
        {
          float f1 = f2;
          if ((0x10A & l2) != 0L)
          {
            f1 = f2;
            if (localAirBlockDroneViewModel != null) {
              f1 = localAirBlockDroneViewModel.getVerticalJoystickPercent();
            }
          }
          Object localObject7 = localObject15;
          Object localObject6 = localObject13;
          Object localObject5 = localObject16;
          boolean bool1 = bool2;
          Object localObject4 = localObject14;
          Object localObject1 = localObject17;
          if ((0x102 & l2) != 0L)
          {
            localObject7 = localObject15;
            localObject6 = localObject13;
            localObject5 = localObject16;
            bool1 = bool2;
            localObject4 = localObject14;
            localObject1 = localObject17;
            if (localAirBlockDroneViewModel != null)
            {
              localObject6 = localAirBlockDroneViewModel.getMenuHeaderView();
              if (this.mViewModelRollSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener != null) {
                break label999;
              }
              localObject1 = new OnSkillClickListenerImpl();
              this.mViewModelRollSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener = ((OnSkillClickListenerImpl)localObject1);
              localObject4 = ((OnSkillClickListenerImpl)localObject1).setValue(localAirBlockDroneViewModel);
              bool1 = localAirBlockDroneViewModel.getProgressBarWithShrinkAnimation();
              if (this.mViewModelCircleSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener != null) {
                break label1008;
              }
              localObject1 = new OnSkillClickListenerImpl1();
              this.mViewModelCircleSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener = ((OnSkillClickListenerImpl1)localObject1);
              localObject7 = ((OnSkillClickListenerImpl1)localObject1).setValue(localAirBlockDroneViewModel);
              localObject5 = localAirBlockDroneViewModel.getMenuViews();
              if (this.mViewModelShakeSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener != null) {
                break label1017;
              }
              localObject1 = new OnSkillClickListenerImpl2();
              this.mViewModelShakeSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener = ((OnSkillClickListenerImpl2)localObject1);
              localObject1 = ((OnSkillClickListenerImpl2)localObject1).setValue(localAirBlockDroneViewModel);
            }
          }
          bool2 = bool10;
          if ((0x142 & l2) != 0L)
          {
            bool2 = bool10;
            if (localAirBlockDroneViewModel != null) {
              bool2 = localAirBlockDroneViewModel.getIsPowerOn();
            }
          }
          l1 = l2;
          if ((0x182 & l2) != 0L)
          {
            if (localAirBlockDroneViewModel != null) {
              bool3 = localAirBlockDroneViewModel.getIsLand();
            }
            l1 = l2;
            if ((0x182 & l2) != 0L)
            {
              if (!bool3) {
                break label1026;
              }
              l1 = l2 | 0x400 | 0x1000;
            }
            if (!bool3) {
              break label1041;
            }
            j = 0;
            if (!bool3) {
              break label1048;
            }
            i = 8;
          }
          f2 = f5;
          if ((0x112 & l1) != 0L)
          {
            f2 = f5;
            if (localAirBlockDroneViewModel != null) {
              f2 = localAirBlockDroneViewModel.getHorizontalJoystickPercent();
            }
          }
          bool3 = bool9;
          if ((0x122 & l1) != 0L)
          {
            bool3 = bool9;
            if (localAirBlockDroneViewModel != null) {
              bool3 = localAirBlockDroneViewModel.getShouldCancelSkills();
            }
          }
          l3 = l1;
          localObject8 = localObject7;
          f3 = f2;
          k = i;
          m = j;
          bool4 = bool2;
          localObject9 = localObject6;
          localObject10 = localObject5;
          bool5 = bool1;
          localObject11 = localObject4;
          localObject12 = localObject1;
          bool6 = bool3;
          bool7 = bool8;
          f4 = f1;
          if ((0x106 & l1) != 0L)
          {
            l3 = l1;
            localObject8 = localObject7;
            f3 = f2;
            k = i;
            m = j;
            bool4 = bool2;
            localObject9 = localObject6;
            localObject10 = localObject5;
            bool5 = bool1;
            localObject11 = localObject4;
            localObject12 = localObject1;
            bool6 = bool3;
            bool7 = bool8;
            f4 = f1;
            if (localAirBlockDroneViewModel != null)
            {
              bool7 = localAirBlockDroneViewModel.isShowMenu();
              f4 = f1;
              bool6 = bool3;
              localObject12 = localObject1;
              localObject11 = localObject4;
              bool5 = bool1;
              localObject10 = localObject5;
              localObject9 = localObject6;
              bool4 = bool2;
              m = j;
              k = i;
              f3 = f2;
              localObject8 = localObject7;
              l3 = l1;
            }
          }
        }
        if ((0x102 & l3) != 0L)
        {
          this.mboundView0.setHeaderView((View)localObject9);
          this.mboundView0.setMenuViews((List)localObject10);
          this.mboundView2.setWithShrinkAnimation(bool5);
          this.mboundView3.setWithShrinkAnimation(bool5);
          this.mboundView6.setOnSkillTriggerListener((HexagonSkillView.OnSkillClickListener)localObject11);
          this.mboundView7.setOnSkillTriggerListener((HexagonSkillView.OnSkillClickListener)localObject12);
          this.mboundView8.setOnSkillTriggerListener((HexagonSkillView.OnSkillClickListener)localObject8);
          this.toolBar.setViewModel(localAirBlockDroneViewModel);
        }
        if ((0x106 & l3) != 0L) {
          this.mboundView0.setRightMenuStatus(bool7);
        }
        if ((0x100 & l3) != 0L)
        {
          this.mboundView10.setOnClickListener(this.mCallback120);
          this.mboundView11.setOnClickListener(this.mCallback121);
          this.mboundView4.setJoystickListener(this.mCallback118);
          this.mboundView5.setJoystickListener(this.mCallback119);
        }
        if ((0x182 & l3) != 0L)
        {
          this.mboundView10.setVisibility(m);
          this.mboundView11.setVisibility(k);
        }
        if ((0x142 & l3) != 0L)
        {
          this.mboundView12.setEnable(bool4);
          this.mboundView6.setShow(bool4);
          this.mboundView7.setShow(bool4);
          this.mboundView8.setShow(bool4);
          this.mboundView9.setEnable(bool4);
        }
        if ((0x10A & l3) != 0L) {
          this.mboundView2.setProgress(f4);
        }
        if ((0x112 & l3) != 0L) {
          this.mboundView3.setProgress(f3);
        }
        if ((0x122 & l3) != 0L)
        {
          this.mboundView6.setCancel(bool6);
          this.mboundView7.setCancel(bool6);
          this.mboundView8.setCancel(bool6);
        }
        executeBindingsOn(this.toolBar);
        return;
      }
      finally {}
      label999:
      Object localObject3 = this.mViewModelRollSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
      continue;
      label1008:
      localObject3 = this.mViewModelCircleSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
      continue;
      label1017:
      localObject3 = this.mViewModelShakeSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
      continue;
      label1026:
      long l1 = l2 | 0x200 | 0x800;
      continue;
      label1041:
      int j = 8;
      continue;
      label1048:
      int i = 0;
    }
  }
  
  @Nullable
  public AirBlockDroneViewModel getViewModel()
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
      this.mDirtyFlags = 256L;
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
    return onChangeViewModel((AirBlockDroneViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirBlockDroneViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirBlockDroneViewModel paramAirBlockDroneViewModel)
  {
    updateRegistration(1, paramAirBlockDroneViewModel);
    this.mViewModel = paramAirBlockDroneViewModel;
    try
    {
      this.mDirtyFlags |= 0x2;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
  
  public static class OnSkillClickListenerImpl
    implements HexagonSkillView.OnSkillClickListener
  {
    private AirBlockDroneViewModel value;
    
    public void onSkillTriggered()
    {
      this.value.rollSkill();
    }
    
    public OnSkillClickListenerImpl setValue(AirBlockDroneViewModel paramAirBlockDroneViewModel)
    {
      this.value = paramAirBlockDroneViewModel;
      OnSkillClickListenerImpl localOnSkillClickListenerImpl = this;
      if (paramAirBlockDroneViewModel == null) {
        localOnSkillClickListenerImpl = null;
      }
      return localOnSkillClickListenerImpl;
    }
  }
  
  public static class OnSkillClickListenerImpl1
    implements HexagonSkillView.OnSkillClickListener
  {
    private AirBlockDroneViewModel value;
    
    public void onSkillTriggered()
    {
      this.value.circleSkill();
    }
    
    public OnSkillClickListenerImpl1 setValue(AirBlockDroneViewModel paramAirBlockDroneViewModel)
    {
      this.value = paramAirBlockDroneViewModel;
      OnSkillClickListenerImpl1 localOnSkillClickListenerImpl1 = this;
      if (paramAirBlockDroneViewModel == null) {
        localOnSkillClickListenerImpl1 = null;
      }
      return localOnSkillClickListenerImpl1;
    }
  }
  
  public static class OnSkillClickListenerImpl2
    implements HexagonSkillView.OnSkillClickListener
  {
    private AirBlockDroneViewModel value;
    
    public void onSkillTriggered()
    {
      this.value.shakeSkill();
    }
    
    public OnSkillClickListenerImpl2 setValue(AirBlockDroneViewModel paramAirBlockDroneViewModel)
    {
      this.value = paramAirBlockDroneViewModel;
      OnSkillClickListenerImpl2 localOnSkillClickListenerImpl2 = this;
      if (paramAirBlockDroneViewModel == null) {
        localOnSkillClickListenerImpl2 = null;
      }
      return localOnSkillClickListenerImpl2;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityAirblockDronePlaygroundBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */