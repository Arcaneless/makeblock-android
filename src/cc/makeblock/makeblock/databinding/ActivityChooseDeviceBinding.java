package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import cc.makeblock.makeblock.customview.CycleLinePageIndicator;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.scene.choosedevice.ChooseDevicePagerBindings;
import cc.makeblock.makeblock.viewmodel.choosedevice.ChooseDeviceViewModel;
import java.util.List;

public class ActivityChooseDeviceBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView back;
  @NonNull
  public final CycleLinePageIndicator linePageIndicator;
  @Nullable
  private final View.OnClickListener mCallback22;
  private long mDirtyFlags = -1L;
  @Nullable
  private ChooseDeviceViewModel mViewModel;
  @NonNull
  private final PercentFrameLayout mboundView0;
  @NonNull
  public final ViewPager viewPager;
  
  static
  {
    sViewsWithIds.put(2131296558, 3);
  }
  
  public ActivityChooseDeviceBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.back = ((ImageView)paramDataBindingComponent[2]);
    this.back.setTag(null);
    this.linePageIndicator = ((CycleLinePageIndicator)paramDataBindingComponent[3]);
    this.mboundView0 = ((PercentFrameLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.viewPager = ((ViewPager)paramDataBindingComponent[1]);
    this.viewPager.setTag(null);
    setRootTag(paramView);
    this.mCallback22 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_choose_device_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityChooseDeviceBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427360, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityChooseDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityChooseDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427360, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(ChooseDeviceViewModel paramChooseDeviceViewModel, int paramInt)
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
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    ChooseDeviceViewModel localChooseDeviceViewModel = this.mViewModel;
    if (localChooseDeviceViewModel != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0)
      {
        ViewTools.getActivity(paramView);
        localChooseDeviceViewModel.onFinish(ViewTools.getActivity(paramView));
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
        int j = 0;
        boolean bool = false;
        Object localObject1 = null;
        Object localObject3 = null;
        ChooseDeviceViewModel localChooseDeviceViewModel = this.mViewModel;
        l1 = l2;
        if ((0x7 & l2) != 0L)
        {
          l1 = l2;
          i = j;
          if ((0x6 & l2) != 0L)
          {
            if (localChooseDeviceViewModel != null) {
              bool = localChooseDeviceViewModel.isShowBack();
            }
            l1 = l2;
            if ((0x6 & l2) != 0L)
            {
              if (bool) {
                l1 = l2 | 0x10;
              }
            }
            else
            {
              if (!bool) {
                break label209;
              }
              i = 0;
            }
          }
          else
          {
            localObject1 = localObject3;
            if (localChooseDeviceViewModel != null) {
              localObject1 = localChooseDeviceViewModel.deviceItems;
            }
            updateRegistration(0, (ObservableList)localObject1);
          }
        }
        else
        {
          if ((0x4 & l1) != 0L) {
            this.back.setOnClickListener(this.mCallback22);
          }
          if ((0x6 & l1) != 0L) {
            this.back.setVisibility(i);
          }
          if ((0x7 & l1) != 0L) {
            ChooseDevicePagerBindings.setItems(this.viewPager, (List)localObject1);
          }
          return;
        }
      }
      finally {}
      long l1 = l2 | 0x8;
      continue;
      label209:
      int i = 8;
    }
  }
  
  @Nullable
  public ChooseDeviceViewModel getViewModel()
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
    return onChangeViewModel((ChooseDeviceViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((ChooseDeviceViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable ChooseDeviceViewModel paramChooseDeviceViewModel)
  {
    updateRegistration(1, paramChooseDeviceViewModel);
    this.mViewModel = paramChooseDeviceViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityChooseDeviceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */