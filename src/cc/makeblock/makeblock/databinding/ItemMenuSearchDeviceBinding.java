package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.playground.SignalView;
import cc.makeblock.makeblock.viewmodel.slidemenu.SearchDeviceModel;

public class ItemMenuSearchDeviceBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  private long mDirtyFlags = -1L;
  @Nullable
  private SearchDeviceModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView1;
  @NonNull
  private final SignalView mboundView2;
  
  public ItemMenuSearchDeviceBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 3, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag("12.96%");
    this.mboundView1 = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((SignalView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/item_menu_search_device_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ItemMenuSearchDeviceBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427437, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemMenuSearchDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ItemMenuSearchDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427437, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(SearchDeviceModel paramSearchDeviceModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 42) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 67) {
      try
      {
        this.mDirtyFlags |= 0x4;
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
      Object localObject4 = null;
      float f2 = 0.0F;
      SearchDeviceModel localSearchDeviceModel = this.mViewModel;
      float f1 = f2;
      if ((0xF & l) != 0L)
      {
        Object localObject1 = localObject4;
        if ((l & 0xB) != 0L)
        {
          localObject1 = localObject4;
          if (localSearchDeviceModel != null) {
            localObject1 = localSearchDeviceModel.getName();
          }
        }
        localObject3 = localObject1;
        f1 = f2;
        if ((l & 0xD) != 0L)
        {
          localObject3 = localObject1;
          f1 = f2;
          if (localSearchDeviceModel != null)
          {
            f1 = localSearchDeviceModel.getSignalPercent();
            localObject3 = localObject1;
          }
        }
      }
      if ((l & 0xB) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView1, (CharSequence)localObject3);
      }
      if ((l & 0xD) != 0L) {
        this.mboundView2.setSignalPercent(f1);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public SearchDeviceModel getViewModel()
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
    return onChangeViewModel((SearchDeviceModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((SearchDeviceModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable SearchDeviceModel paramSearchDeviceModel)
  {
    updateRegistration(0, paramSearchDeviceModel);
    this.mViewModel = paramSearchDeviceModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ItemMenuSearchDeviceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */