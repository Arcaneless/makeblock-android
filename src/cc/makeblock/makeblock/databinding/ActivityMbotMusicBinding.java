package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnButtonClickListener;
import android.databinding.generated.callback.OnButtonClickListener.Listener;
import android.databinding.generated.callback.OnKeyClickListener;
import android.databinding.generated.callback.OnKeyClickListener.Listener;
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
import cc.makeblock.makeblock.customview.playground.MusicKeyView;
import cc.makeblock.makeblock.customview.playground.MusicKeyView.OnKeyClickListener;
import cc.makeblock.makeblock.viewmodel.playground.rj25.mbot.MBotMusicViewModel;

public class ActivityMbotMusicBinding
  extends ViewDataBinding
  implements OnButtonClickListener.Listener, OnKeyClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final View layoutAnchor;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback64;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback65;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback66;
  @Nullable
  private final ButtonView.OnButtonClickListener mCallback67;
  @Nullable
  private final MusicKeyView.OnKeyClickListener mCallback68;
  private long mDirtyFlags = -1L;
  @Nullable
  private MBotMusicViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final MusicKeyView mboundView5;
  @NonNull
  public final ButtonView musicButtonBirthday;
  @NonNull
  public final ButtonView musicButtonChristmas;
  @NonNull
  public final ButtonView musicButtonStar;
  @NonNull
  public final ButtonView musicButtonTiger;
  @NonNull
  public final ToolBarLayout toolBar;
  
  static
  {
    sViewsWithIds.put(2131296728, 6);
    sViewsWithIds.put(2131296542, 7);
  }
  
  public ActivityMbotMusicBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 8, sIncludes, sViewsWithIds);
    this.layoutAnchor = ((View)paramDataBindingComponent[7]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView5 = ((MusicKeyView)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.musicButtonBirthday = ((ButtonView)paramDataBindingComponent[2]);
    this.musicButtonBirthday.setTag(null);
    this.musicButtonChristmas = ((ButtonView)paramDataBindingComponent[3]);
    this.musicButtonChristmas.setTag(null);
    this.musicButtonStar = ((ButtonView)paramDataBindingComponent[1]);
    this.musicButtonStar.setTag(null);
    this.musicButtonTiger = ((ButtonView)paramDataBindingComponent[4]);
    this.musicButtonTiger.setTag(null);
    this.toolBar = ((ToolBarLayout)paramDataBindingComponent[6]);
    setRootTag(paramView);
    this.mCallback67 = new OnButtonClickListener(this, 4);
    this.mCallback68 = new OnKeyClickListener(this, 5);
    this.mCallback65 = new OnButtonClickListener(this, 2);
    this.mCallback64 = new OnButtonClickListener(this, 1);
    this.mCallback66 = new OnButtonClickListener(this, 3);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityMbotMusicBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMbotMusicBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_mbot_music_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityMbotMusicBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityMbotMusicBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMbotMusicBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427379, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityMbotMusicBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMbotMusicBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityMbotMusicBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427379, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(MBotMusicViewModel paramMBotMusicViewModel, int paramInt)
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
  
  public final void _internalCallbackOnButtonClicked(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      MBotMusicViewModel localMBotMusicViewModel = this.mViewModel;
      if (localMBotMusicViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localMBotMusicViewModel.playTwoTigers();
        return;
      }
      continue;
      localMBotMusicViewModel = this.mViewModel;
      if (localMBotMusicViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localMBotMusicViewModel.playHappyBirthday();
        return;
      }
      continue;
      localMBotMusicViewModel = this.mViewModel;
      if (localMBotMusicViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localMBotMusicViewModel.playLittleStars();
        return;
      }
      continue;
      localMBotMusicViewModel = this.mViewModel;
      if (localMBotMusicViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localMBotMusicViewModel.playChristmas();
        return;
      }
    }
  }
  
  public final void _internalCallbackOnKeyClicked(int paramInt1, int paramInt2)
  {
    MBotMusicViewModel localMBotMusicViewModel = this.mViewModel;
    if (localMBotMusicViewModel != null) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0) {
        localMBotMusicViewModel.clickMusicKey(paramInt2);
      }
      return;
    }
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      MBotMusicViewModel localMBotMusicViewModel = this.mViewModel;
      if ((0x2 & l) != 0L)
      {
        this.mboundView5.setOnKeyClickListener(this.mCallback68);
        this.musicButtonBirthday.setOnButtonClickListener(this.mCallback65);
        this.musicButtonChristmas.setOnButtonClickListener(this.mCallback66);
        this.musicButtonStar.setOnButtonClickListener(this.mCallback64);
        this.musicButtonTiger.setOnButtonClickListener(this.mCallback67);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public MBotMusicViewModel getViewModel()
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
    return onChangeViewModel((MBotMusicViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((MBotMusicViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable MBotMusicViewModel paramMBotMusicViewModel)
  {
    updateRegistration(0, paramMBotMusicViewModel);
    this.mViewModel = paramMBotMusicViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityMbotMusicBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */