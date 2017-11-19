package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnCellClickListener;
import android.databinding.generated.callback.OnCellClickListener.Listener;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.ToolBarLayout;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout.OnCellClickListener;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryPanelViewModel;

public class ActivityLaboratoryPanelBinding
  extends ViewDataBinding
  implements OnClickListener.Listener, OnCellClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final FrameLayout delete;
  @NonNull
  public final ImageView deleteIcon;
  @Nullable
  private final View.OnClickListener mCallback102;
  @Nullable
  private final LaboratoryPanelLayout.OnCellClickListener mCallback103;
  private long mDirtyFlags = -1L;
  @Nullable
  private LaboratoryPanelViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  private final ToolBarLayout mboundView1;
  @NonNull
  public final LaboratoryPanelLayout panel;
  @NonNull
  public final ImageView switchModel;
  
  static
  {
    sViewsWithIds.put(2131296426, 4);
    sViewsWithIds.put(2131296427, 5);
  }
  
  public ActivityLaboratoryPanelBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 3);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 6, sIncludes, sViewsWithIds);
    this.delete = ((FrameLayout)paramDataBindingComponent[4]);
    this.deleteIcon = ((ImageView)paramDataBindingComponent[5]);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ToolBarLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.panel = ((LaboratoryPanelLayout)paramDataBindingComponent[3]);
    this.panel.setTag(null);
    this.switchModel = ((ImageView)paramDataBindingComponent[2]);
    this.switchModel.setTag(null);
    setRootTag(paramView);
    this.mCallback102 = new OnClickListener(this, 1);
    this.mCallback103 = new OnCellClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_laboratory_panel_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityLaboratoryPanelBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427370, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryPanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityLaboratoryPanelBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427370, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(LaboratoryPanelViewModel paramLaboratoryPanelViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x4;
      }
      finally {}
    } else {
      return false;
    }
    return true;
  }
  
  private boolean onChangeViewModelSwitchIcon(ObservableInt paramObservableInt, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x2;
      }
      finally {}
    } else {
      return false;
    }
    return true;
  }
  
  private boolean onChangeViewModelToolBarBg(ObservableInt paramObservableInt, int paramInt)
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
  
  public final void _internalCallbackOnCellClick(int paramInt1, View paramView, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0) {
        paramView.onCellClick(paramBoolean, paramInt2, paramInt3);
      }
      return;
    }
  }
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.clickSwitch();
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
      Object localObject1 = null;
      Object localObject3 = null;
      int m = 0;
      int k = 0;
      int n = 0;
      LaboratoryPanelViewModel localLaboratoryPanelViewModel = this.mViewModel;
      int j = m;
      if ((0xF & l) != 0L)
      {
        int i = n;
        if ((0xD & l) != 0L)
        {
          if (localLaboratoryPanelViewModel != null) {
            localObject1 = localLaboratoryPanelViewModel.toolBarBg;
          }
          updateRegistration(0, (Observable)localObject1);
          i = n;
          if (localObject1 != null) {
            i = ((ObservableInt)localObject1).get();
          }
        }
        j = m;
        k = i;
        if ((0xE & l) != 0L)
        {
          localObject1 = localObject3;
          if (localLaboratoryPanelViewModel != null) {
            localObject1 = localLaboratoryPanelViewModel.switchIcon;
          }
          updateRegistration(1, (Observable)localObject1);
          j = m;
          k = i;
          if (localObject1 != null)
          {
            j = ((ObservableInt)localObject1).get();
            k = i;
          }
        }
      }
      if ((0xD & l) != 0L) {
        ResourceDrawableBindings.setImageBackground(this.mboundView1, k);
      }
      if ((0x8 & l) != 0L)
      {
        this.panel.setOnCellClickListener(this.mCallback103);
        this.switchModel.setOnClickListener(this.mCallback102);
      }
      if ((0xE & l) != 0L) {
        ResourceDrawableBindings.setImageUri(this.switchModel, j);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public LaboratoryPanelViewModel getViewModel()
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
    case 0: 
      return onChangeViewModelToolBarBg((ObservableInt)paramObject, paramInt2);
    case 1: 
      return onChangeViewModelSwitchIcon((ObservableInt)paramObject, paramInt2);
    }
    return onChangeViewModel((LaboratoryPanelViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((LaboratoryPanelViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable LaboratoryPanelViewModel paramLaboratoryPanelViewModel)
  {
    updateRegistration(2, paramLaboratoryPanelViewModel);
    this.mViewModel = paramLaboratoryPanelViewModel;
    try
    {
      this.mDirtyFlags |= 0x4;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityLaboratoryPanelBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */