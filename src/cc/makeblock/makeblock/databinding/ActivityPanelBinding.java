package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
import android.databinding.generated.callback.OnCheckedListener;
import android.databinding.generated.callback.OnCheckedListener.Listener;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.CheckableAutoResizeTextView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.RadioLinearLayoutItem;
import cc.makeblock.makeblock.customview.RadioLinearLayoutItem.OnCheckedListener;
import cc.makeblock.makeblock.customview.SwitchTabRadioLinearLayout;
import cc.makeblock.makeblock.customview.WidgetContainerButtonView;
import cc.makeblock.makeblock.customview.WidgetContainerViewGroup;
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.viewmodel.PanelViewModel;
import java.util.List;

public class ActivityPanelBinding
  extends ViewDataBinding
  implements OnCheckedListener.Listener, OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(20);
  @Nullable
  private static final SparseIntArray sViewsWithIds;
  @NonNull
  public final WidgetContainerButtonView buttonWidgetList;
  @Nullable
  public final LayoutCodePanelBinding code;
  @NonNull
  public final ImageView gifImage;
  @NonNull
  public final ImageView iconModeSwitch;
  @Nullable
  private final RadioLinearLayoutItem.OnCheckedListener mCallback125;
  @Nullable
  private final RadioLinearLayoutItem.OnCheckedListener mCallback126;
  @Nullable
  private final View.OnClickListener mCallback127;
  @Nullable
  private final View.OnClickListener mCallback128;
  private long mDirtyFlags = -1L;
  @Nullable
  private PanelViewModel mViewModel;
  private OnClickListenerImpl2 mViewModelBackAndroidViewViewOnClickListener;
  private OnClickListenerImpl mViewModelOnCellLayoutClickAndroidViewViewOnClickListener;
  private OnClickListenerImpl1 mViewModelShowAirblockTuningDialogAndroidViewViewOnClickListener;
  private OnClickListenerImpl3 mViewModelShowModeSelectPopupAndroidViewViewOnClickListener;
  @NonNull
  private final PercentFrameLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final WidgetContainerViewGroup mboundView11;
  @NonNull
  private final PercentRelativeLayout mboundView13;
  @NonNull
  private final RelativeLayout mboundView15;
  @NonNull
  private final AutoResizeTextView mboundView4;
  @NonNull
  private final PercentRelativeLayout mboundView8;
  @NonNull
  private final PercentRelativeLayout mboundView9;
  @NonNull
  public final AutoResizeTextView navTvFormSelect;
  @NonNull
  public final PercentLinearLayout navTvFormSelectContainer;
  @NonNull
  public final CellLayout panelCellLayout;
  @NonNull
  public final SwitchTabRadioLinearLayout panelSwitch;
  @NonNull
  public final RadioLinearLayoutItem tabFirstItem;
  @NonNull
  public final CheckableAutoResizeTextView tabFirstText;
  @NonNull
  public final RadioLinearLayoutItem tabSecondItem;
  @NonNull
  public final CheckableAutoResizeTextView tabSecondText;
  
  static
  {
    sIncludes.setIncludes(15, new String[] { "layout_code_panel" }, new int[] { 16 }, new int[] { 2131427465 });
    sViewsWithIds = new SparseIntArray();
    sViewsWithIds.put(2131296608, 17);
    sViewsWithIds.put(2131296698, 18);
    sViewsWithIds.put(2131296700, 19);
  }
  
  public ActivityPanelBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 20, sIncludes, sViewsWithIds);
    this.buttonWidgetList = ((WidgetContainerButtonView)paramDataBindingComponent[12]);
    this.buttonWidgetList.setTag(null);
    this.code = ((LayoutCodePanelBinding)paramDataBindingComponent[16]);
    setContainedBinding(this.code);
    this.gifImage = ((ImageView)paramDataBindingComponent[14]);
    this.gifImage.setTag(null);
    this.iconModeSwitch = ((ImageView)paramDataBindingComponent[7]);
    this.iconModeSwitch.setTag(null);
    this.mboundView0 = ((PercentFrameLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView11 = ((WidgetContainerViewGroup)paramDataBindingComponent[11]);
    this.mboundView11.setTag(null);
    this.mboundView13 = ((PercentRelativeLayout)paramDataBindingComponent[13]);
    this.mboundView13.setTag(null);
    this.mboundView15 = ((RelativeLayout)paramDataBindingComponent[15]);
    this.mboundView15.setTag(null);
    this.mboundView4 = ((AutoResizeTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView8 = ((PercentRelativeLayout)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((PercentRelativeLayout)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    this.navTvFormSelect = ((AutoResizeTextView)paramDataBindingComponent[6]);
    this.navTvFormSelect.setTag(null);
    this.navTvFormSelectContainer = ((PercentLinearLayout)paramDataBindingComponent[5]);
    this.navTvFormSelectContainer.setTag(null);
    this.panelCellLayout = ((CellLayout)paramDataBindingComponent[10]);
    this.panelCellLayout.setTag(null);
    this.panelSwitch = ((SwitchTabRadioLinearLayout)paramDataBindingComponent[17]);
    this.tabFirstItem = ((RadioLinearLayoutItem)paramDataBindingComponent[2]);
    this.tabFirstItem.setTag(null);
    this.tabFirstText = ((CheckableAutoResizeTextView)paramDataBindingComponent[18]);
    this.tabSecondItem = ((RadioLinearLayoutItem)paramDataBindingComponent[3]);
    this.tabSecondItem.setTag(null);
    this.tabSecondText = ((CheckableAutoResizeTextView)paramDataBindingComponent[19]);
    setRootTag(paramView);
    this.mCallback125 = new OnCheckedListener(this, 1);
    this.mCallback128 = new OnClickListener(this, 4);
    this.mCallback126 = new OnCheckedListener(this, 2);
    this.mCallback127 = new OnClickListener(this, 3);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityPanelBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPanelBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_panel_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityPanelBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427381, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityPanelBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427381, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeCode(LayoutCodePanelBinding paramLayoutCodePanelBinding, int paramInt)
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
  
  private boolean onChangeViewModel(PanelViewModel paramPanelViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 17) {
      try
      {
        this.mDirtyFlags |= 0x1004;
        return true;
      }
      finally {}
    }
    if (paramInt == 52) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 27) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 28) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 72) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    if (paramInt == 64) {
      try
      {
        this.mDirtyFlags |= 0x80;
        return true;
      }
      finally {}
    }
    if (paramInt == 49) {
      try
      {
        this.mDirtyFlags |= 0x100;
        return true;
      }
      finally {}
    }
    if (paramInt == 3) {
      try
      {
        this.mDirtyFlags |= 0x200;
        return true;
      }
      finally {}
    }
    if (paramInt == 84) {
      try
      {
        this.mDirtyFlags |= 0x400;
        return true;
      }
      finally {}
    }
    if (paramInt == 83) {
      try
      {
        this.mDirtyFlags |= 0x800;
        return true;
      }
      finally {}
    }
    if (paramInt == 39) {
      try
      {
        this.mDirtyFlags |= 0x2000;
        return true;
      }
      finally {}
    }
    if (paramInt == 63) {
      try
      {
        this.mDirtyFlags |= 0x4000;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  public final void _internalCallbackOnChecked(int paramInt, RadioLinearLayoutItem paramRadioLinearLayoutItem)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      paramRadioLinearLayoutItem = this.mViewModel;
      if (paramRadioLinearLayoutItem != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramRadioLinearLayoutItem.setMode(1);
        return;
      }
      continue;
      paramRadioLinearLayoutItem = this.mViewModel;
      if (paramRadioLinearLayoutItem != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramRadioLinearLayoutItem.setMode(0);
        return;
      }
    }
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
        paramView.clickListButton();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.showExpandGuide();
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
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        boolean bool1 = false;
        boolean bool16 = false;
        Object localObject19 = null;
        Object localObject25 = null;
        int i6 = 0;
        j = 0;
        boolean bool6 = false;
        boolean bool15 = false;
        Object localObject4 = null;
        Object localObject14 = null;
        Object localObject23 = null;
        boolean bool14 = false;
        int i8 = 0;
        m = 0;
        Object localObject6 = null;
        Object localObject18 = null;
        Object localObject7 = null;
        int i2 = 0;
        int i7 = 0;
        int i10 = 0;
        Object localObject12 = null;
        Object localObject8 = null;
        int i4 = 0;
        k = 0;
        Object localObject13 = null;
        Object localObject21 = null;
        boolean bool9 = false;
        boolean bool13 = false;
        Object localObject5 = null;
        Object localObject16 = null;
        Object localObject22 = null;
        boolean bool3 = false;
        boolean bool7 = false;
        boolean bool4 = false;
        int i9 = 0;
        i = 0;
        boolean bool8 = false;
        boolean bool12 = false;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        boolean bool2 = false;
        int i5 = 0;
        i1 = 0;
        boolean bool11 = false;
        int i3 = 0;
        n = 0;
        localObject3 = null;
        Object localObject11 = null;
        Object localObject24 = null;
        Object localObject15 = null;
        Object localObject20 = null;
        localObject9 = null;
        Object localObject17 = null;
        Object localObject10 = null;
        PanelViewModel localPanelViewModel = this.mViewModel;
        boolean bool10 = false;
        boolean bool5 = bool10;
        l1 = l2;
        if ((0xFFFE & l2) != 0L)
        {
          l1 = l2;
          if ((0x8042 & l2) != 0L)
          {
            if (localPanelViewModel != null) {
              bool1 = localPanelViewModel.getTuneButtonVisibility();
            }
            l1 = l2;
            if ((0x8042 & l2) != 0L)
            {
              if (!bool1) {
                break label1715;
              }
              l1 = l2 | 0x800000;
            }
            if (!bool1) {
              break label1726;
            }
            i = 0;
          }
          l2 = l1;
          if ((0x8082 & l1) != 0L)
          {
            bool1 = bool16;
            if (localPanelViewModel != null) {
              bool1 = localPanelViewModel.isShowExpand();
            }
            l2 = l1;
            if ((0x8082 & l1) != 0L)
            {
              if (!bool1) {
                break label1733;
              }
              l2 = l1 | 0x20000;
            }
            if (!bool1) {
              break label1744;
            }
            j = 0;
          }
          Object localObject1 = localObject25;
          if ((0x8802 & l2) != 0L)
          {
            localObject1 = localObject25;
            if (localPanelViewModel != null) {
              localObject1 = localPanelViewModel.getWidgetDataList();
            }
          }
          bool1 = bool15;
          if ((0x8006 & l2) != 0L)
          {
            bool1 = bool15;
            if (localPanelViewModel != null) {
              bool1 = localPanelViewModel.isDesignMode();
            }
          }
          l1 = l2;
          if ((0x8002 & l2) != 0L)
          {
            localObject3 = localObject24;
            f1 = f3;
            i2 = i10;
            localObject4 = localObject23;
            localObject5 = localObject22;
            localObject6 = localObject7;
            if (localPanelViewModel != null)
            {
              if (this.mViewModelOnCellLayoutClickAndroidViewViewOnClickListener != null) {
                break label1751;
              }
              localObject3 = new OnClickListenerImpl();
              this.mViewModelOnCellLayoutClickAndroidViewViewOnClickListener = ((OnClickListenerImpl)localObject3);
              localObject4 = ((OnClickListenerImpl)localObject3).setValue(localPanelViewModel);
              localObject6 = localPanelViewModel.getTitle();
              i2 = localPanelViewModel.getLoading();
              if (this.mViewModelShowAirblockTuningDialogAndroidViewViewOnClickListener != null) {
                break label1760;
              }
              localObject3 = new OnClickListenerImpl1();
              this.mViewModelShowAirblockTuningDialogAndroidViewViewOnClickListener = ((OnClickListenerImpl1)localObject3);
              localObject5 = ((OnClickListenerImpl1)localObject3).setValue(localPanelViewModel);
              f1 = localPanelViewModel.getFormTextAlpha();
              bool2 = localPanelViewModel.getFormSelectContainerVisibility();
              if (this.mViewModelBackAndroidViewViewOnClickListener != null) {
                break label1769;
              }
              localObject3 = new OnClickListenerImpl2();
              this.mViewModelBackAndroidViewViewOnClickListener = ((OnClickListenerImpl2)localObject3);
              localObject3 = ((OnClickListenerImpl2)localObject3).setValue(localPanelViewModel);
            }
            l1 = l2;
            if ((0x8002 & l2) != 0L)
            {
              if (!bool2) {
                break label1778;
              }
              l1 = l2 | 0x200000;
            }
            if (!bool2) {
              break label1789;
            }
            k = 0;
          }
          l2 = l1;
          if ((0xA002 & l1) != 0L)
          {
            bool2 = bool14;
            if (localPanelViewModel != null) {
              bool2 = localPanelViewModel.getLoadingGifVisibility();
            }
            l2 = l1;
            if ((0xA002 & l1) != 0L)
            {
              if (!bool2) {
                break label1796;
              }
              l2 = l1 | 0x80000;
            }
            if (!bool2) {
              break label1807;
            }
            m = 0;
          }
          localObject7 = localObject8;
          if ((0x8202 & l2) != 0L)
          {
            localObject7 = localObject8;
            if (localPanelViewModel != null) {
              localObject7 = localPanelViewModel.getBoardGroupBeanList();
            }
          }
          localObject8 = localObject21;
          if ((0x8022 & l2) != 0L)
          {
            localObject8 = localObject21;
            if (localPanelViewModel != null) {
              localObject8 = localPanelViewModel.getFormText();
            }
          }
          bool2 = bool13;
          if ((0x8402 & l2) != 0L)
          {
            bool2 = bool13;
            if (localPanelViewModel != null) {
              bool2 = localPanelViewModel.getWidgetListOnState();
            }
          }
          l1 = l2;
          if ((0x8012 & l2) != 0L)
          {
            bool3 = bool4;
            localObject9 = localObject10;
            if (localPanelViewModel != null)
            {
              bool3 = localPanelViewModel.isFormChangeable();
              if (this.mViewModelShowModeSelectPopupAndroidViewViewOnClickListener != null) {
                break label1814;
              }
              localObject9 = new OnClickListenerImpl3();
              this.mViewModelShowModeSelectPopupAndroidViewViewOnClickListener = ((OnClickListenerImpl3)localObject9);
              localObject9 = ((OnClickListenerImpl3)localObject9).setValue(localPanelViewModel);
            }
            l1 = l2;
            if ((0x8012 & l2) != 0L)
            {
              if (!bool3) {
                break label1823;
              }
              l1 = l2 | 0x8000000;
            }
            if (!bool3) {
              break label1834;
            }
            n = 0;
          }
          bool4 = bool12;
          if ((0x800A & l1) != 0L)
          {
            bool4 = bool12;
            if (localPanelViewModel != null) {
              bool4 = localPanelViewModel.isPlayMode();
            }
          }
          l2 = l1;
          if ((0xC002 & l1) != 0L)
          {
            bool5 = bool11;
            if (localPanelViewModel != null) {
              bool5 = localPanelViewModel.isShowCodePanel();
            }
            l2 = l1;
            if ((0xC002 & l1) != 0L)
            {
              if (!bool5) {
                break label1841;
              }
              l2 = l1 | 0x2000000;
            }
            if (!bool5) {
              break label1852;
            }
            i1 = 0;
          }
          localObject10 = localObject20;
          if ((0x8102 & l2) != 0L)
          {
            localObject10 = localObject20;
            if (localPanelViewModel != null) {
              localObject10 = localPanelViewModel.getPanelBackground();
            }
          }
          bool5 = bool10;
          l1 = l2;
          localObject11 = localObject3;
          localObject12 = localObject7;
          bool6 = bool1;
          bool7 = bool3;
          i3 = n;
          i4 = k;
          localObject13 = localObject8;
          f2 = f1;
          i5 = i1;
          i6 = j;
          i7 = i2;
          i8 = m;
          localObject14 = localObject4;
          localObject15 = localObject10;
          bool8 = bool4;
          localObject16 = localObject5;
          localObject17 = localObject9;
          localObject18 = localObject6;
          i9 = i;
          localObject19 = localObject1;
          bool9 = bool2;
          if ((0x9002 & l2) != 0L)
          {
            bool5 = bool10;
            l1 = l2;
            localObject11 = localObject3;
            localObject12 = localObject7;
            bool6 = bool1;
            bool7 = bool3;
            i3 = n;
            i4 = k;
            localObject13 = localObject8;
            f2 = f1;
            i5 = i1;
            i6 = j;
            i7 = i2;
            i8 = m;
            localObject14 = localObject4;
            localObject15 = localObject10;
            bool8 = bool4;
            localObject16 = localObject5;
            localObject17 = localObject9;
            localObject18 = localObject6;
            i9 = i;
            localObject19 = localObject1;
            bool9 = bool2;
            if (localPanelViewModel != null)
            {
              bool5 = localPanelViewModel.isDesignMode();
              bool9 = bool2;
              localObject19 = localObject1;
              i9 = i;
              localObject18 = localObject6;
              localObject17 = localObject9;
              localObject16 = localObject5;
              bool8 = bool4;
              localObject15 = localObject10;
              localObject14 = localObject4;
              i8 = m;
              i7 = i2;
              i6 = j;
              i5 = i1;
              f2 = f1;
              localObject13 = localObject8;
              i4 = k;
              i3 = n;
              bool7 = bool3;
              bool6 = bool1;
              localObject12 = localObject7;
              localObject11 = localObject3;
              l1 = l2;
            }
          }
        }
        if ((0x8000 & l1) != 0L)
        {
          this.buttonWidgetList.setOnClickListener(this.mCallback128);
          this.mboundView9.setOnClickListener(this.mCallback127);
          this.tabFirstItem.setOnCheckedListener(this.mCallback125);
          this.tabSecondItem.setOnCheckedListener(this.mCallback126);
        }
        if ((0x9002 & l1) != 0L) {
          this.buttonWidgetList.setShow(bool5);
        }
        if ((0x8402 & l1) != 0L)
        {
          this.buttonWidgetList.setTurnOn(bool9);
          this.mboundView11.setTurnOn(bool9);
        }
        if ((0x8002 & l1) != 0L)
        {
          this.code.setViewModel(localPanelViewModel);
          GlideBindings.setImageSrc(this.gifImage, i7);
          this.mboundView1.setOnClickListener((View.OnClickListener)localObject11);
          TextViewBindingAdapter.setText(this.mboundView4, (CharSequence)localObject18);
          this.mboundView8.setOnClickListener((View.OnClickListener)localObject16);
          this.navTvFormSelectContainer.setVisibility(i4);
          this.panelCellLayout.setOnClickListener((View.OnClickListener)localObject14);
          if (getBuildSdkInt() >= 11) {
            this.navTvFormSelect.setAlpha(f2);
          }
        }
        if ((0x8012 & l1) != 0L)
        {
          this.iconModeSwitch.setVisibility(i3);
          ViewBindingAdapter.setOnClick(this.navTvFormSelectContainer, (View.OnClickListener)localObject17, bool7);
        }
        if ((0x8202 & l1) != 0L) {
          this.mboundView11.setBoardGroupBeanList((List)localObject12);
        }
        if ((0x8802 & l1) != 0L) {
          this.mboundView11.setWidgetDataList((List)localObject19);
        }
        if ((0xA002 & l1) != 0L) {
          this.mboundView13.setVisibility(i8);
        }
        if ((0xC002 & l1) != 0L) {
          this.mboundView15.setVisibility(i5);
        }
        if ((0x8042 & l1) != 0L) {
          this.mboundView8.setVisibility(i9);
        }
        if ((0x8082 & l1) != 0L) {
          this.mboundView9.setVisibility(i6);
        }
        if ((0x8022 & l1) != 0L) {
          TextViewBindingAdapter.setText(this.navTvFormSelect, (CharSequence)localObject13);
        }
        if ((0x8102 & l1) != 0L) {
          ViewBindingAdapter.setBackground(this.panelCellLayout, (Drawable)localObject15);
        }
        if ((0x8006 & l1) != 0L) {
          this.tabFirstItem.setChecked(bool6);
        }
        if ((0x800A & l1) != 0L) {
          this.tabSecondItem.setChecked(bool8);
        }
        executeBindingsOn(this.code);
        return;
      }
      finally {}
      label1715:
      long l1 = l2 | 0x400000;
      continue;
      label1726:
      int i = 8;
      continue;
      label1733:
      long l2 = l1 | 0x10000;
      continue;
      label1744:
      int j = 8;
      continue;
      label1751:
      Object localObject3 = this.mViewModelOnCellLayoutClickAndroidViewViewOnClickListener;
      continue;
      label1760:
      localObject3 = this.mViewModelShowAirblockTuningDialogAndroidViewViewOnClickListener;
      continue;
      label1769:
      localObject3 = this.mViewModelBackAndroidViewViewOnClickListener;
      continue;
      label1778:
      l1 = l2 | 0x100000;
      continue;
      label1789:
      int k = 8;
      continue;
      label1796:
      l2 = l1 | 0x40000;
      continue;
      label1807:
      int m = 8;
      continue;
      label1814:
      Object localObject9 = this.mViewModelShowModeSelectPopupAndroidViewViewOnClickListener;
      continue;
      label1823:
      l1 = l2 | 0x4000000;
      continue;
      label1834:
      int n = 8;
      continue;
      label1841:
      l2 = l1 | 0x1000000;
      continue;
      label1852:
      int i1 = 4;
    }
  }
  
  @Nullable
  public PanelViewModel getViewModel()
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
      if (!this.code.hasPendingBindings()) {
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
      this.mDirtyFlags = 32768L;
      this.code.invalidateAll();
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
      return onChangeCode((LayoutCodePanelBinding)paramObject, paramInt2);
    }
    return onChangeViewModel((PanelViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((PanelViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable PanelViewModel paramPanelViewModel)
  {
    updateRegistration(1, paramPanelViewModel);
    this.mViewModel = paramPanelViewModel;
    try
    {
      this.mDirtyFlags |= 0x2;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
  
  public static class OnClickListenerImpl
    implements View.OnClickListener
  {
    private PanelViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.onCellLayoutClick(paramView);
    }
    
    public OnClickListenerImpl setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnClickListenerImpl localOnClickListenerImpl = this;
      if (paramPanelViewModel == null) {
        localOnClickListenerImpl = null;
      }
      return localOnClickListenerImpl;
    }
  }
  
  public static class OnClickListenerImpl1
    implements View.OnClickListener
  {
    private PanelViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.showAirblockTuningDialog(paramView);
    }
    
    public OnClickListenerImpl1 setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnClickListenerImpl1 localOnClickListenerImpl1 = this;
      if (paramPanelViewModel == null) {
        localOnClickListenerImpl1 = null;
      }
      return localOnClickListenerImpl1;
    }
  }
  
  public static class OnClickListenerImpl2
    implements View.OnClickListener
  {
    private PanelViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.back(paramView);
    }
    
    public OnClickListenerImpl2 setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnClickListenerImpl2 localOnClickListenerImpl2 = this;
      if (paramPanelViewModel == null) {
        localOnClickListenerImpl2 = null;
      }
      return localOnClickListenerImpl2;
    }
  }
  
  public static class OnClickListenerImpl3
    implements View.OnClickListener
  {
    private PanelViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.showModeSelectPopup(paramView);
    }
    
    public OnClickListenerImpl3 setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnClickListenerImpl3 localOnClickListenerImpl3 = this;
      if (paramPanelViewModel == null) {
        localOnClickListenerImpl3 = null;
      }
      return localOnClickListenerImpl3;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityPanelBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */