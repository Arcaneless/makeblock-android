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
import cc.makeblock.makeblock.customview.playground.JoystickWithHorizontalIndicator;
import cc.makeblock.makeblock.customview.playground.JoystickWithWholeIndicator;
import cc.makeblock.makeblock.customview.playground.ProgressView;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockLandViewModel;
import java.util.List;

public class ActivityAirblockLandPlaygroundBinding
  extends ViewDataBinding
  implements OnClickListener.Listener, JoystickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(12);
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final MenuDrawerLayout airblockSlideMenu;
  @Nullable
  private final Joystick.JoystickListener mCallback122;
  @Nullable
  private final Joystick.JoystickListener mCallback123;
  @Nullable
  private final View.OnClickListener mCallback124;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirBlockLandViewModel mViewModel;
  private OnSkillClickListenerImpl1 mViewModelSMoveSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
  private OnSkillClickListenerImpl mViewModelShiftSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final DisableMaskView mboundView10;
  @NonNull
  private final ProgressView mboundView2;
  @NonNull
  private final ProgressView mboundView3;
  @NonNull
  private final JoystickWithHorizontalIndicator mboundView4;
  @NonNull
  private final JoystickWithWholeIndicator mboundView5;
  @NonNull
  private final HexagonSkillView mboundView6;
  @NonNull
  private final HexagonSkillView mboundView7;
  @NonNull
  private final DisableMaskView mboundView8;
  @NonNull
  private final ImageView mboundView9;
  @Nullable
  public final LayoutPlaygroundAirblockTopBarBinding toolBar;
  
  static
  {
    sIncludes.setIncludes(1, new String[] { "layout_playground_airblock_top_bar" }, new int[] { 11 }, new int[] { 2131427494 });
  }
  
  public ActivityAirblockLandPlaygroundBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 12, sIncludes, sViewsWithIds);
    this.airblockSlideMenu = ((MenuDrawerLayout)paramDataBindingComponent[0]);
    this.airblockSlideMenu.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView10 = ((DisableMaskView)paramDataBindingComponent[10]);
    this.mboundView10.setTag(null);
    this.mboundView2 = ((ProgressView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((ProgressView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((JoystickWithHorizontalIndicator)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((JoystickWithWholeIndicator)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((HexagonSkillView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((HexagonSkillView)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((DisableMaskView)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((ImageView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    this.toolBar = ((LayoutPlaygroundAirblockTopBarBinding)paramDataBindingComponent[11]);
    setContainedBinding(this.toolBar);
    setRootTag(paramView);
    this.mCallback124 = new OnClickListener(this, 3);
    this.mCallback122 = new JoystickListener(this, 1);
    this.mCallback123 = new JoystickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_airblock_land_playground_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityAirblockLandPlaygroundBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427358, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityAirblockLandPlaygroundBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityAirblockLandPlaygroundBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427358, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
  
  private boolean onChangeViewModel(AirBlockLandViewModel paramAirBlockLandViewModel, int paramInt)
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
    return false;
  }
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.brake();
      }
      return;
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
      AirBlockLandViewModel localAirBlockLandViewModel = this.mViewModel;
      if (localAirBlockLandViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockLandViewModel.moveTurnJoystick(paramInt2, paramFloat);
        return;
      }
      continue;
      localAirBlockLandViewModel = this.mViewModel;
      if (localAirBlockLandViewModel != null) {}
      for (paramInt1 = 1; paramInt1 != 0; paramInt1 = 0)
      {
        localAirBlockLandViewModel.moveMoveJoystick(paramInt2, paramFloat);
        return;
      }
    }
  }
  
  protected void executeBindings()
  {
    for (;;)
    {
      try
      {
        long l = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        Object localObject7 = null;
        Object localObject11 = null;
        boolean bool4 = false;
        boolean bool3 = false;
        Object localObject10 = null;
        Object localObject12 = null;
        Object localObject9 = null;
        Object localObject13 = null;
        boolean bool5 = false;
        boolean bool2 = false;
        Object localObject8 = null;
        Object localObject14 = null;
        boolean bool6 = false;
        boolean bool9 = false;
        float f3 = 0.0F;
        float f2 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        boolean bool8 = false;
        AirBlockLandViewModel localAirBlockLandViewModel = this.mViewModel;
        boolean bool7 = bool8;
        if ((0xFE & l) != 0L)
        {
          Object localObject6 = localObject11;
          Object localObject5 = localObject14;
          boolean bool1 = bool2;
          Object localObject4 = localObject13;
          Object localObject1 = localObject12;
          if ((0x82 & l) != 0L)
          {
            localObject6 = localObject11;
            localObject5 = localObject14;
            bool1 = bool2;
            localObject4 = localObject13;
            localObject1 = localObject12;
            if (localAirBlockLandViewModel != null)
            {
              localObject6 = localAirBlockLandViewModel.getMenuHeaderView();
              if (this.mViewModelShiftSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener != null) {
                break label752;
              }
              localObject1 = new OnSkillClickListenerImpl();
              this.mViewModelShiftSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener = ((OnSkillClickListenerImpl)localObject1);
              localObject4 = ((OnSkillClickListenerImpl)localObject1).setValue(localAirBlockLandViewModel);
              if (this.mViewModelSMoveSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener != null) {
                break label761;
              }
              localObject1 = new OnSkillClickListenerImpl1();
              this.mViewModelSMoveSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener = ((OnSkillClickListenerImpl1)localObject1);
              localObject7 = ((OnSkillClickListenerImpl1)localObject1).setValue(localAirBlockLandViewModel);
              bool1 = localAirBlockLandViewModel.getProgressBarWithShrinkAnimation();
              localObject5 = localAirBlockLandViewModel.getMenuViews();
              localObject1 = localObject4;
              localObject4 = localObject7;
            }
          }
          bool2 = bool3;
          if ((0xC2 & l) != 0L)
          {
            bool2 = bool3;
            if (localAirBlockLandViewModel != null) {
              bool2 = localAirBlockLandViewModel.getIsPowerOn();
            }
          }
          bool3 = bool9;
          if ((0xA2 & l) != 0L)
          {
            bool3 = bool9;
            if (localAirBlockLandViewModel != null) {
              bool3 = localAirBlockLandViewModel.getShouldCancelSkills();
            }
          }
          float f1 = f2;
          if ((0x92 & l) != 0L)
          {
            f1 = f2;
            if (localAirBlockLandViewModel != null) {
              f1 = localAirBlockLandViewModel.getMoveJoystickPercent();
            }
          }
          f2 = f5;
          if ((0x8A & l) != 0L)
          {
            f2 = f5;
            if (localAirBlockLandViewModel != null) {
              f2 = localAirBlockLandViewModel.getTurnJoystickPercent();
            }
          }
          bool4 = bool2;
          localObject7 = localObject6;
          localObject8 = localObject5;
          f3 = f1;
          bool5 = bool1;
          localObject9 = localObject4;
          localObject10 = localObject1;
          bool6 = bool3;
          bool7 = bool8;
          f4 = f2;
          if ((0x86 & l) != 0L)
          {
            bool4 = bool2;
            localObject7 = localObject6;
            localObject8 = localObject5;
            f3 = f1;
            bool5 = bool1;
            localObject9 = localObject4;
            localObject10 = localObject1;
            bool6 = bool3;
            bool7 = bool8;
            f4 = f2;
            if (localAirBlockLandViewModel != null)
            {
              bool7 = localAirBlockLandViewModel.isShowMenu();
              f4 = f2;
              bool6 = bool3;
              localObject10 = localObject1;
              localObject9 = localObject4;
              bool5 = bool1;
              f3 = f1;
              localObject8 = localObject5;
              localObject7 = localObject6;
              bool4 = bool2;
            }
          }
        }
        if ((0x82 & l) != 0L)
        {
          this.airblockSlideMenu.setHeaderView((View)localObject7);
          this.airblockSlideMenu.setMenuViews((List)localObject8);
          this.mboundView2.setWithShrinkAnimation(bool5);
          this.mboundView3.setWithShrinkAnimation(bool5);
          this.mboundView6.setOnSkillTriggerListener((HexagonSkillView.OnSkillClickListener)localObject9);
          this.mboundView7.setOnSkillTriggerListener((HexagonSkillView.OnSkillClickListener)localObject10);
          this.toolBar.setViewModel(localAirBlockLandViewModel);
        }
        if ((0x86 & l) != 0L) {
          this.airblockSlideMenu.setRightMenuStatus(bool7);
        }
        if ((0xC2 & l) != 0L)
        {
          this.mboundView10.setEnable(bool4);
          this.mboundView6.setShow(bool4);
          this.mboundView7.setShow(bool4);
          this.mboundView8.setEnable(bool4);
        }
        if ((0x8A & l) != 0L) {
          this.mboundView2.setProgress(f4);
        }
        if ((0x92 & l) != 0L) {
          this.mboundView3.setProgress(f3);
        }
        if ((0x80 & l) != 0L)
        {
          this.mboundView4.setJoystickListener(this.mCallback122);
          this.mboundView5.setJoystickListener(this.mCallback123);
          this.mboundView9.setOnClickListener(this.mCallback124);
        }
        if ((0xA2 & l) != 0L)
        {
          this.mboundView6.setCancel(bool6);
          this.mboundView7.setCancel(bool6);
        }
        executeBindingsOn(this.toolBar);
        return;
      }
      finally {}
      label752:
      Object localObject3 = this.mViewModelShiftSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
      continue;
      label761:
      localObject3 = this.mViewModelSMoveSkillCcMakeblockMakeblockCustomviewPlaygroundHexagonSkillViewOnSkillClickListener;
    }
  }
  
  @Nullable
  public AirBlockLandViewModel getViewModel()
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
      this.mDirtyFlags = 128L;
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
    return onChangeViewModel((AirBlockLandViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirBlockLandViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirBlockLandViewModel paramAirBlockLandViewModel)
  {
    updateRegistration(1, paramAirBlockLandViewModel);
    this.mViewModel = paramAirBlockLandViewModel;
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
    private AirBlockLandViewModel value;
    
    public void onSkillTriggered()
    {
      this.value.shiftSkill();
    }
    
    public OnSkillClickListenerImpl setValue(AirBlockLandViewModel paramAirBlockLandViewModel)
    {
      this.value = paramAirBlockLandViewModel;
      OnSkillClickListenerImpl localOnSkillClickListenerImpl = this;
      if (paramAirBlockLandViewModel == null) {
        localOnSkillClickListenerImpl = null;
      }
      return localOnSkillClickListenerImpl;
    }
  }
  
  public static class OnSkillClickListenerImpl1
    implements HexagonSkillView.OnSkillClickListener
  {
    private AirBlockLandViewModel value;
    
    public void onSkillTriggered()
    {
      this.value.sMoveSkill();
    }
    
    public OnSkillClickListenerImpl1 setValue(AirBlockLandViewModel paramAirBlockLandViewModel)
    {
      this.value = paramAirBlockLandViewModel;
      OnSkillClickListenerImpl1 localOnSkillClickListenerImpl1 = this;
      if (paramAirBlockLandViewModel == null) {
        localOnSkillClickListenerImpl1 = null;
      }
      return localOnSkillClickListenerImpl1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityAirblockLandPlaygroundBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */