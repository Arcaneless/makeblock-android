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
import android.widget.ImageView;
import android.widget.LinearLayout;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.viewmodel.laboratory.ChooseActionViewModel;

public class ActivityLaboratoryChooseActionBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final PercentRelativeLayout actionDetector;
  @NonNull
  public final PercentRelativeLayout actionLight;
  @NonNull
  public final PercentRelativeLayout actionMove;
  @NonNull
  public final LinearLayout container;
  @Nullable
  private final View.OnClickListener mCallback1;
  @Nullable
  private final View.OnClickListener mCallback2;
  @Nullable
  private final View.OnClickListener mCallback3;
  @Nullable
  private final View.OnClickListener mCallback4;
  @Nullable
  private final View.OnClickListener mCallback5;
  private long mDirtyFlags = -1L;
  @Nullable
  private ChooseActionViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final PercentRelativeLayout mboundView5;
  
  static
  {
    sViewsWithIds.put(2131296415, 6);
  }
  
  public ActivityLaboratoryChooseActionBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 0);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.actionDetector = ((PercentRelativeLayout)paramDataBindingComponent[4]);
    this.actionDetector.setTag(null);
    this.actionLight = ((PercentRelativeLayout)paramDataBindingComponent[3]);
    this.actionLight.setTag(null);
    this.actionMove = ((PercentRelativeLayout)paramDataBindingComponent[2]);
    this.actionMove.setTag(null);
    this.container = ((LinearLayout)paramDataBindingComponent[6]);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView5 = ((PercentRelativeLayout)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    setRootTag(paramView);
    this.mCallback2 = new OnClickListener(this, 2);
    this.mCallback1 = new OnClickListener(this, 1);
    this.mCallback5 = new OnClickListener(this, 5);
    this.mCallback4 = new OnClickListener(this, 4);
    this.mCallback3 = new OnClickListener(this, 3);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_laboratory_choose_action_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityLaboratoryChooseActionBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427368, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryChooseActionBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityLaboratoryChooseActionBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427368, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
        paramView.popupMoveWidget();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.leave();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.popupSoundWidget();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.popupDetectorWidget();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.popupLightWidget();
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
      ChooseActionViewModel localChooseActionViewModel = this.mViewModel;
      if ((0x2 & l) != 0L)
      {
        this.actionDetector.setOnClickListener(this.mCallback4);
        this.actionLight.setOnClickListener(this.mCallback3);
        this.actionMove.setOnClickListener(this.mCallback2);
        this.mboundView1.setOnClickListener(this.mCallback1);
        this.mboundView5.setOnClickListener(this.mCallback5);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public ChooseActionViewModel getViewModel()
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
    return false;
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((ChooseActionViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable ChooseActionViewModel paramChooseActionViewModel)
  {
    this.mViewModel = paramChooseActionViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityLaboratoryChooseActionBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */