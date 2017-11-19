package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.ProgressScrollBar;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryViewModel;

public class ActivityLaboratoryMainBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ProgressScrollBar indicatorProgress;
  @NonNull
  public final RecyclerView laboratoryList;
  @Nullable
  private final View.OnClickListener mCallback48;
  private long mDirtyFlags = -1L;
  @Nullable
  private LaboratoryViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView3;
  
  static
  {
    sViewsWithIds.put(2131296513, 4);
  }
  
  public ActivityLaboratoryMainBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 5, sIncludes, sViewsWithIds);
    this.indicatorProgress = ((ProgressScrollBar)paramDataBindingComponent[4]);
    this.laboratoryList = ((RecyclerView)paramDataBindingComponent[1]);
    this.laboratoryList.setTag(null);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    setRootTag(paramView);
    this.mCallback48 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_laboratory_main_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityLaboratoryMainBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427369, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityLaboratoryMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityLaboratoryMainBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427369, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(LaboratoryViewModel paramLaboratoryViewModel, int paramInt)
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
  
  private boolean onChangeViewModelHasLaboratory(ObservableBoolean paramObservableBoolean, int paramInt)
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
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.jumpToChooseActionActivity();
      }
      return;
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
        i = 0;
        j = 0;
        ObservableBoolean localObservableBoolean = null;
        boolean bool = false;
        LaboratoryViewModel localLaboratoryViewModel = this.mViewModel;
        l1 = l2;
        if ((0x7 & l2) != 0L)
        {
          if (localLaboratoryViewModel != null) {
            localObservableBoolean = localLaboratoryViewModel.hasLaboratory;
          }
          updateRegistration(0, localObservableBoolean);
          if (localObservableBoolean != null) {
            bool = localObservableBoolean.get();
          }
          l1 = l2;
          if ((0x7 & l2) != 0L)
          {
            if (bool) {
              l1 = l2 | 0x10 | 0x40;
            }
          }
          else
          {
            if (!bool) {
              break label199;
            }
            i = 8;
            if (!bool) {
              break label204;
            }
            j = 0;
          }
        }
        else
        {
          if ((0x7 & l1) != 0L)
          {
            this.laboratoryList.setVisibility(j);
            this.mboundView2.setVisibility(i);
            this.mboundView3.setVisibility(i);
          }
          if ((0x4 & l1) != 0L) {
            this.mboundView2.setOnClickListener(this.mCallback48);
          }
          return;
        }
      }
      finally {}
      long l1 = l2 | 0x8 | 0x20;
      continue;
      label199:
      int i = 0;
      continue;
      label204:
      int j = 8;
    }
  }
  
  @Nullable
  public LaboratoryViewModel getViewModel()
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
      this.mDirtyFlags = 4L;
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
      return onChangeViewModelHasLaboratory((ObservableBoolean)paramObject, paramInt2);
    }
    return onChangeViewModel((LaboratoryViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((LaboratoryViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable LaboratoryViewModel paramLaboratoryViewModel)
  {
    updateRegistration(1, paramLaboratoryViewModel);
    this.mViewModel = paramLaboratoryViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityLaboratoryMainBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */