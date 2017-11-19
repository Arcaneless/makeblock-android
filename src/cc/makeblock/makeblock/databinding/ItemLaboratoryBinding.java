package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.widget.Space;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.laboratory.LaboratoryPanelLayout;
import cc.makeblock.makeblock.viewmodel.laboratory.LaboratoryItemViewModel;
import java.util.List;

public class ItemLaboratoryBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final AutoResizeTextView autoResizeTextView;
  @NonNull
  public final PercentRelativeLayout item;
  private long mDirtyFlags = -1L;
  @Nullable
  private LaboratoryItemViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  public final LaboratoryPanelLayout panel;
  @NonNull
  public final Space spaceBottom;
  
  static
  {
    sViewsWithIds.put(2131296518, 2);
    sViewsWithIds.put(2131296679, 3);
    sViewsWithIds.put(2131296306, 4);
  }
  
  public ItemLaboratoryBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 5, sIncludes, sViewsWithIds);
    this.autoResizeTextView = ((AutoResizeTextView)paramDataBindingComponent[4]);
    this.item = ((PercentRelativeLayout)paramDataBindingComponent[2]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.panel = ((LaboratoryPanelLayout)paramDataBindingComponent[1]);
    this.panel.setTag(null);
    this.spaceBottom = ((Space)paramDataBindingComponent[3]);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static ItemLaboratoryBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemLaboratoryBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/item_laboratory_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ItemLaboratoryBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ItemLaboratoryBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemLaboratoryBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427435, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ItemLaboratoryBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemLaboratoryBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ItemLaboratoryBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427435, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(LaboratoryItemViewModel paramLaboratoryItemViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 86) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      Object localObject3 = null;
      LaboratoryItemViewModel localLaboratoryItemViewModel = this.mViewModel;
      Object localObject1 = localObject3;
      if ((l & 0x7) != 0L)
      {
        localObject1 = localObject3;
        if (localLaboratoryItemViewModel != null) {
          localObject1 = localLaboratoryItemViewModel.getWidgets();
        }
      }
      if ((l & 0x7) != 0L) {
        this.panel.setWidgets((List)localObject1);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public LaboratoryItemViewModel getViewModel()
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
    }
    return onChangeViewModel((LaboratoryItemViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((LaboratoryItemViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable LaboratoryItemViewModel paramLaboratoryItemViewModel)
  {
    updateRegistration(0, paramLaboratoryItemViewModel);
    this.mViewModel = paramLaboratoryItemViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ItemLaboratoryBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */