package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.JoystickListener;
import android.databinding.generated.callback.JoystickListener.Listener;
import android.databinding.generated.callback.OnButtonClickListener;
import android.databinding.generated.callback.OnButtonClickListener.Listener;
import android.databinding.generated.callback.OnSkillClickListener;
import android.databinding.generated.callback.OnSkillClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.ToolBarLayout;
import cc.makeblock.makeblock.customview.playground.ButtonView;
import cc.makeblock.makeblock.customview.playground.ButtonView.OnButtonClickListener;
import cc.makeblock.makeblock.customview.playground.Joystick.JoystickListener;
import cc.makeblock.makeblock.customview.playground.JoystickWithoutIndicator;
import cc.makeblock.makeblock.customview.playground.SimpleProgressView;
import cc.makeblock.makeblock.customview.playground.SkillView;
import cc.makeblock.makeblock.customview.playground.SkillView.OnSkillClickListener;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerControllerViewModel;

public class ActivityRangerControllerBinding
  extends ViewDataBinding
  implements OnSkillClickListener.Listener, OnButtonClickListener.Listener, JoystickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @Nullable
  private final Joystick.JoystickListener mCallback29;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback30;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback31;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback32;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback33;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback34;
  private long mDirtyFlags = -1L;
  @Nullable
  private RangerControllerViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final JoystickWithoutIndicator mboundView2;
  @NonNull
  private final SkillView mboundView3;
  @NonNull
  private final SkillView mboundView4;
  @NonNull
  private final SkillView mboundView5;
  @NonNull
  private final ButtonView mboundView6;
  @NonNull
  private final ButtonView mboundView7;
  @NonNull
  public final SimpleProgressView progress;
  @NonNull
  public final ToolBarLayout toolBar;
  
  static
  {
    sViewsWithIds.put(2131296728, 8);
  }
  
  public ActivityRangerControllerBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 9, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView2 = ((JoystickWithoutIndicator)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((SkillView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((SkillView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((SkillView)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((ButtonView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((ButtonView)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.progress = ((SimpleProgressView)paramDataBindingComponent[1]);
    this.progress.setTag(null);
    this.toolBar = ((ToolBarLayout)paramDataBindingComponent[8]);
    setRootTag(paramView);
    this.mCallback30 = new OnSkillClickListener(this, 2);
    this.mCallback32 = new OnSkillClickListener(this, 4);
    this.mCallback31 = new OnSkillClickListener(this, 3);
    this.mCallback33 = new OnButtonClickListener(this, 5);
    this.mCallback29 = new JoystickListener(this, 1);
    this.mCallback34 = new OnButtonClickListener(this, 6);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityRangerControllerBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerControllerBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_ranger_controller_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityRangerControllerBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityRangerControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427384, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityRangerControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityRangerControllerBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427384, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(RangerControllerViewModel paramRangerControllerViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 78) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 61) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  public final void _internalCallbackOnButtonClicked(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      RangerControllerViewModel localRangerControllerViewModel = this.mViewModel;
      if (localRangerControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localRangerControllerViewModel.light();
        return;
      }
      continue;
      localRangerControllerViewModel = this.mViewModel;
      if (localRangerControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localRangerControllerViewModel.buzz();
        return;
      }
    }
  }
  
  public final void _internalCallbackOnJoystickMoved(int paramInt1, int paramInt2, float paramFloat)
  {
    RangerControllerViewModel localRangerControllerViewModel = this.mViewModel;
    if (localRangerControllerViewModel != null) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0) {
        localRangerControllerViewModel.moveJoystick(paramInt2, paramFloat);
      }
      return;
    }
  }
  
  public final void _internalCallbackOnSkillTriggered(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      RangerControllerViewModel localRangerControllerViewModel = this.mViewModel;
      if (localRangerControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localRangerControllerViewModel.sprint();
        return;
      }
      continue;
      localRangerControllerViewModel = this.mViewModel;
      if (localRangerControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localRangerControllerViewModel.shake();
        return;
      }
      continue;
      localRangerControllerViewModel = this.mViewModel;
      if (localRangerControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localRangerControllerViewModel.spin();
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
      float f3 = 0.0F;
      boolean bool2 = false;
      RangerControllerViewModel localRangerControllerViewModel = this.mViewModel;
      boolean bool1 = bool2;
      if ((0xF & l) != 0L)
      {
        float f1 = f3;
        if ((l & 0xB) != 0L)
        {
          f1 = f3;
          if (localRangerControllerViewModel != null) {
            f1 = localRangerControllerViewModel.getVerticalJoystickPercent();
          }
        }
        bool1 = bool2;
        f2 = f1;
        if ((l & 0xD) != 0L)
        {
          bool1 = bool2;
          f2 = f1;
          if (localRangerControllerViewModel != null)
          {
            bool1 = localRangerControllerViewModel.getShouldCancelSkills();
            f2 = f1;
          }
        }
      }
      if ((0x8 & l) != 0L)
      {
        this.mboundView2.setJoystickListener(this.mCallback29);
        this.mboundView3.setOnSkillTriggerListener(this.mCallback30);
        this.mboundView4.setOnSkillTriggerListener(this.mCallback31);
        this.mboundView5.setOnSkillTriggerListener(this.mCallback32);
        this.mboundView6.setOnButtonClickListener(this.mCallback33);
        this.mboundView7.setOnButtonClickListener(this.mCallback34);
      }
      if ((l & 0xD) != 0L)
      {
        this.mboundView3.setCancel(bool1);
        this.mboundView4.setCancel(bool1);
        this.mboundView5.setCancel(bool1);
      }
      if ((l & 0xB) != 0L) {
        this.progress.setProgress(f2);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public RangerControllerViewModel getViewModel()
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
    return onChangeViewModel((RangerControllerViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((RangerControllerViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable RangerControllerViewModel paramRangerControllerViewModel)
  {
    updateRegistration(0, paramRangerControllerViewModel);
    this.mViewModel = paramRangerControllerViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityRangerControllerBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */