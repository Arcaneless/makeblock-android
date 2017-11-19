package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.JoystickListener;
import android.databinding.generated.callback.JoystickListener.Listener;
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
import cc.makeblock.makeblock.customview.playground.Joystick.JoystickListener;
import cc.makeblock.makeblock.customview.playground.JoystickWithoutIndicator;
import cc.makeblock.makeblock.customview.playground.SimpleProgressView;
import cc.makeblock.makeblock.customview.playground.SkillView;
import cc.makeblock.makeblock.customview.playground.SkillView.OnSkillClickListener;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.CodeyControllerViewModel;

public class ActivityCodeyControllerBinding
  extends ViewDataBinding
  implements OnSkillClickListener.Listener, JoystickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @Nullable
  private final Joystick.JoystickListener mCallback50;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback51;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback52;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback53;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback54;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback55;
  @Nullable
  private final SkillView.OnSkillClickListener mCallback56;
  private long mDirtyFlags = -1L;
  @Nullable
  private CodeyControllerViewModel mViewModel;
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
  private final SkillView mboundView6;
  @NonNull
  private final SkillView mboundView7;
  @NonNull
  private final SkillView mboundView8;
  @NonNull
  public final SimpleProgressView progress;
  @NonNull
  public final ToolBarLayout toolBar;
  
  static
  {
    sViewsWithIds.put(2131296728, 9);
  }
  
  public ActivityCodeyControllerBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 10, sIncludes, sViewsWithIds);
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
    this.mboundView6 = ((SkillView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((SkillView)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((SkillView)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.progress = ((SimpleProgressView)paramDataBindingComponent[1]);
    this.progress.setTag(null);
    this.toolBar = ((ToolBarLayout)paramDataBindingComponent[9]);
    setRootTag(paramView);
    this.mCallback55 = new OnSkillClickListener(this, 6);
    this.mCallback56 = new OnSkillClickListener(this, 7);
    this.mCallback52 = new OnSkillClickListener(this, 3);
    this.mCallback54 = new OnSkillClickListener(this, 5);
    this.mCallback53 = new OnSkillClickListener(this, 4);
    this.mCallback50 = new JoystickListener(this, 1);
    this.mCallback51 = new OnSkillClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_codey_controller_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityCodeyControllerBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427361, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityCodeyControllerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityCodeyControllerBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427361, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(CodeyControllerViewModel paramCodeyControllerViewModel, int paramInt)
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
    if (paramInt == 70) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  public final void _internalCallbackOnJoystickMoved(int paramInt1, int paramInt2, float paramFloat)
  {
    CodeyControllerViewModel localCodeyControllerViewModel = this.mViewModel;
    if (localCodeyControllerViewModel != null) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0) {
        localCodeyControllerViewModel.moveJoystick(paramInt2, paramFloat);
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
      CodeyControllerViewModel localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.sad();
        return;
      }
      continue;
      localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.shock();
        return;
      }
      continue;
      localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.spin();
        return;
      }
      continue;
      localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.happy();
        return;
      }
      continue;
      localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.shake();
        return;
      }
      continue;
      localCodeyControllerViewModel = this.mViewModel;
      if (localCodeyControllerViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCodeyControllerViewModel.sprint();
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
      boolean bool5 = false;
      boolean bool4 = false;
      CodeyControllerViewModel localCodeyControllerViewModel = this.mViewModel;
      boolean bool3 = bool4;
      if ((0x1F & l) != 0L)
      {
        float f1 = f3;
        if ((0x13 & l) != 0L)
        {
          f1 = f3;
          if (localCodeyControllerViewModel != null) {
            f1 = localCodeyControllerViewModel.getVerticalJoystickPercent();
          }
        }
        boolean bool1 = bool5;
        if ((0x15 & l) != 0L)
        {
          bool1 = bool5;
          if (localCodeyControllerViewModel != null) {
            bool1 = localCodeyControllerViewModel.getShouldCancelSkills();
          }
        }
        bool2 = bool1;
        bool3 = bool4;
        f2 = f1;
        if ((0x19 & l) != 0L)
        {
          bool2 = bool1;
          bool3 = bool4;
          f2 = f1;
          if (localCodeyControllerViewModel != null)
          {
            bool3 = localCodeyControllerViewModel.isStartCooling();
            f2 = f1;
            bool2 = bool1;
          }
        }
      }
      if ((0x10 & l) != 0L)
      {
        this.mboundView2.setJoystickListener(this.mCallback50);
        this.mboundView3.setOnSkillTriggerListener(this.mCallback51);
        this.mboundView4.setOnSkillTriggerListener(this.mCallback52);
        this.mboundView5.setOnSkillTriggerListener(this.mCallback53);
        this.mboundView6.setOnSkillTriggerListener(this.mCallback54);
        this.mboundView7.setOnSkillTriggerListener(this.mCallback55);
        this.mboundView8.setOnSkillTriggerListener(this.mCallback56);
      }
      if ((0x15 & l) != 0L)
      {
        this.mboundView3.setCancel(bool2);
        this.mboundView4.setCancel(bool2);
        this.mboundView5.setCancel(bool2);
        this.mboundView6.setCancel(bool2);
        this.mboundView7.setCancel(bool2);
        this.mboundView8.setCancel(bool2);
      }
      if ((0x19 & l) != 0L)
      {
        this.mboundView3.setStartCooling(bool3);
        this.mboundView4.setStartCooling(bool3);
        this.mboundView5.setStartCooling(bool3);
        this.mboundView6.setStartCooling(bool3);
        this.mboundView7.setStartCooling(bool3);
        this.mboundView8.setStartCooling(bool3);
      }
      if ((0x13 & l) != 0L) {
        this.progress.setProgress(f2);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public CodeyControllerViewModel getViewModel()
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
    return onChangeViewModel((CodeyControllerViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((CodeyControllerViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable CodeyControllerViewModel paramCodeyControllerViewModel)
  {
    updateRegistration(0, paramCodeyControllerViewModel);
    this.mViewModel = paramCodeyControllerViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityCodeyControllerBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */