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
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.connect.InitiatingActivityViewModel;

public class ActivityInitiatingDeviceBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final View boundaryView;
  @NonNull
  public final ImageView initAirblock;
  @NonNull
  public final ImageView initOthers;
  @Nullable
  private final View.OnClickListener mCallback23;
  @Nullable
  private final View.OnClickListener mCallback24;
  @Nullable
  private final View.OnClickListener mCallback25;
  @Nullable
  private final View.OnClickListener mCallback26;
  @Nullable
  private final View.OnClickListener mCallback27;
  @Nullable
  private final View.OnClickListener mCallback28;
  private long mDirtyFlags = -1L;
  @Nullable
  private InitiatingActivityViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final PercentRelativeLayout mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView4;
  @NonNull
  private final AutoResizeTextView mboundView6;
  @NonNull
  private final PercentRelativeLayout mboundView7;
  @NonNull
  private final PercentRelativeLayout mboundView8;
  @NonNull
  private final AutoResizeTextView mboundView9;
  
  static
  {
    sViewsWithIds.put(2131296375, 10);
  }
  
  public ActivityInitiatingDeviceBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 11, sIncludes, sViewsWithIds);
    this.boundaryView = ((View)paramDataBindingComponent[10]);
    this.initAirblock = ((ImageView)paramDataBindingComponent[3]);
    this.initAirblock.setTag(null);
    this.initOthers = ((ImageView)paramDataBindingComponent[5]);
    this.initOthers.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((PercentRelativeLayout)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView4 = ((AutoResizeTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView6 = ((AutoResizeTextView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((PercentRelativeLayout)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((PercentRelativeLayout)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((AutoResizeTextView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    setRootTag(paramView);
    this.mCallback27 = new OnClickListener(this, 5);
    this.mCallback28 = new OnClickListener(this, 6);
    this.mCallback25 = new OnClickListener(this, 3);
    this.mCallback26 = new OnClickListener(this, 4);
    this.mCallback23 = new OnClickListener(this, 1);
    this.mCallback24 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_initiating_device_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityInitiatingDeviceBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427367, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityInitiatingDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityInitiatingDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427367, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(InitiatingActivityViewModel paramInitiatingActivityViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 62) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 25) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 34) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 6) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    return false;
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
        paramView.jumpToWeb();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.jumpToFirmUpdate();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.jumpToFirmUpdate();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.jumpToWeb();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.leave(false);
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.jumpToFirmUpdate();
        return;
      }
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
        int m = 0;
        int k = 0;
        int n = 0;
        int i4 = 0;
        int i2 = 0;
        i = 0;
        boolean bool = false;
        int i3 = 0;
        InitiatingActivityViewModel localInitiatingActivityViewModel = this.mViewModel;
        long l3 = l2;
        int i1 = i3;
        if ((0x3F & l2) != 0L)
        {
          int j = k;
          if ((0x31 & l2) != 0L)
          {
            j = k;
            if (localInitiatingActivityViewModel != null) {
              j = localInitiatingActivityViewModel.getChooseLayoutVisibility();
            }
          }
          k = i4;
          if ((0x25 & l2) != 0L)
          {
            k = i4;
            if (localInitiatingActivityViewModel != null) {
              k = localInitiatingActivityViewModel.getFailLayoutVisibility();
            }
          }
          l1 = l2;
          if ((0x23 & l2) != 0L)
          {
            if (localInitiatingActivityViewModel != null) {
              bool = localInitiatingActivityViewModel.isShowBack();
            }
            l1 = l2;
            if ((0x23 & l2) != 0L)
            {
              if (!bool) {
                break label413;
              }
              l1 = l2 | 0x80;
            }
            if (!bool) {
              break label424;
            }
            i = 0;
          }
          l3 = l1;
          m = j;
          n = k;
          i1 = i3;
          i2 = i;
          if ((0x29 & l1) != 0L)
          {
            l3 = l1;
            m = j;
            n = k;
            i1 = i3;
            i2 = i;
            if (localInitiatingActivityViewModel != null)
            {
              i1 = localInitiatingActivityViewModel.getInitiatingLayoutVisibility();
              i2 = i;
              n = k;
              m = j;
              l3 = l1;
            }
          }
        }
        if ((0x20 & l3) != 0L)
        {
          this.initAirblock.setOnClickListener(this.mCallback24);
          this.initOthers.setOnClickListener(this.mCallback26);
          this.mboundView1.setOnClickListener(this.mCallback23);
          this.mboundView4.setOnClickListener(this.mCallback25);
          this.mboundView6.setOnClickListener(this.mCallback27);
          this.mboundView9.setOnClickListener(this.mCallback28);
        }
        if ((0x23 & l3) != 0L) {
          this.mboundView1.setVisibility(i2);
        }
        if ((0x25 & l3) != 0L) {
          this.mboundView2.setVisibility(n);
        }
        if ((0x29 & l3) != 0L) {
          this.mboundView7.setVisibility(i1);
        }
        if ((0x31 & l3) != 0L) {
          this.mboundView8.setVisibility(m);
        }
        return;
      }
      finally {}
      label413:
      long l1 = l2 | 0x40;
      continue;
      label424:
      int i = 8;
    }
  }
  
  @Nullable
  public InitiatingActivityViewModel getViewModel()
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
      this.mDirtyFlags = 32L;
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
    return onChangeViewModel((InitiatingActivityViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((InitiatingActivityViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable InitiatingActivityViewModel paramInitiatingActivityViewModel)
  {
    updateRegistration(0, paramInitiatingActivityViewModel);
    this.mViewModel = paramInitiatingActivityViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityInitiatingDeviceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */