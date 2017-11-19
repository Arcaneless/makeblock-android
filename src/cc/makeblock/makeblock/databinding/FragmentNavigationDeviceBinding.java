package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnItemClickListener;
import android.databinding.generated.callback.OnItemClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.bindingadapter.RecyclerViewBindings;
import cc.makeblock.makeblock.customview.bindingadapter.RecyclerViewBindings.OnItemClickListener;
import cc.makeblock.makeblock.scene.navigation.DeviceListBindings;
import cc.makeblock.makeblock.viewmodel.navigation.NavigationDeviceViewModel;
import java.util.List;

public class FragmentNavigationDeviceBinding
  extends ViewDataBinding
  implements OnItemClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @Nullable
  private final RecyclerViewBindings.OnItemClickListener mCallback16;
  private long mDirtyFlags = -1L;
  @Nullable
  private NavigationDeviceViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  public final RecyclerView recyclerView;
  
  public FragmentNavigationDeviceBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 2, sIncludes, sViewsWithIds);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.recyclerView = ((RecyclerView)paramDataBindingComponent[1]);
    this.recyclerView.setTag(null);
    setRootTag(paramView);
    this.mCallback16 = new OnItemClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_navigation_device_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentNavigationDeviceBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427425, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentNavigationDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentNavigationDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427425, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(NavigationDeviceViewModel paramNavigationDeviceViewModel, int paramInt)
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
  
  private boolean onChangeViewModelDeviceItems(ObservableArrayList<ChooseDeviceItem> paramObservableArrayList, int paramInt)
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
  
  public final void _internalCallbackOnItemClick(int paramInt1, View paramView, int paramInt2)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if (paramInt1 != 0) {
        paramView.onItemClick(paramInt2);
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
      NavigationDeviceViewModel localNavigationDeviceViewModel = this.mViewModel;
      if ((l & 0x7) != 0L)
      {
        localObject1 = localObject3;
        if (localNavigationDeviceViewModel != null) {
          localObject1 = localNavigationDeviceViewModel.deviceItems;
        }
        updateRegistration(0, (ObservableList)localObject1);
      }
      if ((l & 0x7) != 0L) {
        DeviceListBindings.setItems(this.recyclerView, (List)localObject1);
      }
      if ((0x4 & l) != 0L) {
        RecyclerViewBindings.onItemClick(this.recyclerView, this.mCallback16);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public NavigationDeviceViewModel getViewModel()
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
      return onChangeViewModelDeviceItems((ObservableArrayList)paramObject, paramInt2);
    }
    return onChangeViewModel((NavigationDeviceViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((NavigationDeviceViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable NavigationDeviceViewModel paramNavigationDeviceViewModel)
  {
    updateRegistration(1, paramNavigationDeviceViewModel);
    this.mViewModel = paramNavigationDeviceViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentNavigationDeviceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */