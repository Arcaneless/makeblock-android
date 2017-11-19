package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationDeviceItemViewModel;

public class ItemDeviceSmallBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final View guideline2;
  @NonNull
  public final ImageView imageView3;
  @NonNull
  public final ImageView imageView4;
  private long mDirtyFlags = -1L;
  @Nullable
  private NavigationDeviceItemViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  public final AutoResizeTextView textView;
  
  static
  {
    sViewsWithIds.put(2131296479, 4);
  }
  
  public ItemDeviceSmallBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 5, sIncludes, sViewsWithIds);
    this.guideline2 = ((View)paramDataBindingComponent[4]);
    this.imageView3 = ((ImageView)paramDataBindingComponent[1]);
    this.imageView3.setTag(null);
    this.imageView4 = ((ImageView)paramDataBindingComponent[2]);
    this.imageView4.setTag(null);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.textView = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.textView.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static ItemDeviceSmallBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemDeviceSmallBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/item_device_small_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ItemDeviceSmallBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ItemDeviceSmallBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemDeviceSmallBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427434, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ItemDeviceSmallBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemDeviceSmallBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ItemDeviceSmallBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427434, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(NavigationDeviceItemViewModel paramNavigationDeviceItemViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 20) {
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
      ChooseDeviceItem localChooseDeviceItem = null;
      int m = 0;
      int n = 0;
      int i1 = 0;
      NavigationDeviceItemViewModel localNavigationDeviceItemViewModel = this.mViewModel;
      int k = n;
      int j = i1;
      Object localObject2 = localObject3;
      int i = m;
      if ((l & 0x7) != 0L)
      {
        if (localNavigationDeviceItemViewModel != null) {
          localChooseDeviceItem = localNavigationDeviceItemViewModel.getDeviceItem();
        }
        k = n;
        j = i1;
        localObject2 = localObject3;
        i = m;
        if (localChooseDeviceItem != null)
        {
          localObject2 = localChooseDeviceItem.deviceName;
          i = localChooseDeviceItem.devicePic;
          k = localChooseDeviceItem.deviceBg;
          j = localChooseDeviceItem.deviceIcon;
        }
      }
      if ((l & 0x7) != 0L)
      {
        GlideBindings.setImageSrc(this.imageView3, i);
        GlideBindings.setImageSrc(this.imageView4, j);
        GlideBindings.setBackground(this.mboundView0, k, 0.0F);
        TextViewBindingAdapter.setText(this.textView, (CharSequence)localObject2);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public NavigationDeviceItemViewModel getViewModel()
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
    return onChangeViewModel((NavigationDeviceItemViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((NavigationDeviceItemViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable NavigationDeviceItemViewModel paramNavigationDeviceItemViewModel)
  {
    updateRegistration(0, paramNavigationDeviceItemViewModel);
    this.mViewModel = paramNavigationDeviceItemViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ItemDeviceSmallBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */