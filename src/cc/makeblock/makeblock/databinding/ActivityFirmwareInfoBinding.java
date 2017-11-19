package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
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
import cc.makeblock.makeblock.customview.InfiniteProgressView;
import cc.makeblock.makeblock.viewmodel.firmware.FirmwareInfoActivityViewModel;

public class ActivityFirmwareInfoBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView imageView;
  @Nullable
  private final View.OnClickListener mCallback41;
  @Nullable
  private final View.OnClickListener mCallback42;
  private long mDirtyFlags = -1L;
  @Nullable
  private FirmwareInfoActivityViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final AutoResizeTextView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView3;
  @NonNull
  private final AutoResizeTextView mboundView4;
  @NonNull
  private final AutoResizeTextView mboundView6;
  @NonNull
  private final PercentRelativeLayout mboundView7;
  @NonNull
  private final PercentRelativeLayout mboundView8;
  @NonNull
  private final InfiniteProgressView mboundView9;
  @NonNull
  public final AutoResizeTextView textBoardName;
  
  static
  {
    sViewsWithIds.put(2131296495, 10);
  }
  
  public ActivityFirmwareInfoBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 11, sIncludes, sViewsWithIds);
    this.imageView = ((ImageView)paramDataBindingComponent[10]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((AutoResizeTextView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((AutoResizeTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView6 = ((AutoResizeTextView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((PercentRelativeLayout)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((PercentRelativeLayout)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((InfiniteProgressView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    this.textBoardName = ((AutoResizeTextView)paramDataBindingComponent[5]);
    this.textBoardName.setTag(null);
    setRootTag(paramView);
    this.mCallback42 = new OnClickListener(this, 2);
    this.mCallback41 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_firmware_info_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityFirmwareInfoBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427364, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityFirmwareInfoBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityFirmwareInfoBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427364, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(FirmwareInfoActivityViewModel paramFirmwareInfoActivityViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 40) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 45) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 46) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 75) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 38) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 9) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    if (paramInt == 10) {
      try
      {
        this.mDirtyFlags |= 0x80;
        return true;
      }
      finally {}
    }
    if (paramInt == 5) {
      try
      {
        this.mDirtyFlags |= 0x100;
        return true;
      }
      finally {}
    }
    if (paramInt == 71) {
      try
      {
        this.mDirtyFlags |= 0x200;
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
        paramView.startUpdate();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.checkUpdates();
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
        int i6 = 0;
        j = 0;
        int i4 = 0;
        k = 0;
        boolean bool1 = false;
        int i7 = 0;
        i = 0;
        boolean bool6 = false;
        Object localObject4 = null;
        Object localObject3 = null;
        boolean bool5 = false;
        int i5 = 0;
        m = 0;
        boolean bool4 = false;
        int i2 = 0;
        n = 0;
        boolean bool3 = false;
        int i3 = 0;
        i1 = 0;
        Object localObject6 = null;
        Object localObject8 = null;
        boolean bool2 = false;
        Object localObject7 = null;
        FirmwareInfoActivityViewModel localFirmwareInfoActivityViewModel = this.mViewModel;
        l1 = l2;
        Object localObject5 = localObject7;
        if ((0x7FF & l2) != 0L)
        {
          l1 = l2;
          if ((0x411 & l2) != 0L)
          {
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getUpToDateTextVisibility();
            }
            l1 = l2;
            if ((0x411 & l2) != 0L)
            {
              if (!bool1) {
                break label923;
              }
              l1 = l2 | 0x10000;
            }
            if (!bool1) {
              break label934;
            }
            i = 0;
          }
          l2 = l1;
          if ((0x601 & l1) != 0L)
          {
            bool1 = bool6;
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getStartUpdateButtonVisibility();
            }
            l2 = l1;
            if ((0x601 & l1) != 0L)
            {
              if (!bool1) {
                break label939;
              }
              l2 = l1 | 0x1000;
            }
            if (!bool1) {
              break label950;
            }
            j = 0;
          }
          Object localObject1 = localObject3;
          if ((0x441 & l2) != 0L)
          {
            localObject1 = localObject3;
            if (localFirmwareInfoActivityViewModel != null) {
              localObject1 = localFirmwareInfoActivityViewModel.getConnectedBoardName();
            }
          }
          l1 = l2;
          if ((0x403 & l2) != 0L)
          {
            bool1 = bool5;
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getMascotShadowVisibility();
            }
            l1 = l2;
            if ((0x403 & l2) != 0L)
            {
              if (!bool1) {
                break label955;
              }
              l1 = l2 | 0x4000;
            }
            if (!bool1) {
              break label966;
            }
            k = 0;
          }
          l2 = l1;
          if ((0x409 & l1) != 0L)
          {
            bool1 = bool4;
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getNewVersionTextVisibility();
            }
            l2 = l1;
            if ((0x409 & l1) != 0L)
            {
              if (!bool1) {
                break label971;
              }
              l2 = l1 | 0x40000;
            }
            if (!bool1) {
              break label982;
            }
            m = 0;
          }
          l1 = l2;
          if ((0x501 & l2) != 0L)
          {
            bool1 = bool3;
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getCheckUpdatesButtonVisibility();
            }
            l1 = l2;
            if ((0x501 & l2) != 0L)
            {
              if (!bool1) {
                break label988;
              }
              l1 = l2 | 0x100000;
            }
            if (!bool1) {
              break label999;
            }
            n = 0;
          }
          localObject3 = localObject8;
          if ((0x405 & l1) != 0L)
          {
            localObject3 = localObject8;
            if (localFirmwareInfoActivityViewModel != null) {
              localObject3 = localFirmwareInfoActivityViewModel.getNewVersionText();
            }
          }
          l2 = l1;
          if ((0x421 & l1) != 0L)
          {
            bool1 = bool2;
            if (localFirmwareInfoActivityViewModel != null) {
              bool1 = localFirmwareInfoActivityViewModel.getLoadingFromNetTextVisibility();
            }
            l2 = l1;
            if ((0x421 & l1) != 0L)
            {
              if (!bool1) {
                break label1005;
              }
              l2 = l1 | 0x400000;
            }
            if (!bool1) {
              break label1016;
            }
            i1 = 0;
          }
          l1 = l2;
          i2 = n;
          localObject4 = localObject1;
          localObject5 = localObject7;
          i3 = i1;
          i4 = k;
          localObject6 = localObject3;
          i5 = m;
          i6 = j;
          i7 = i;
          if ((0x481 & l2) != 0L)
          {
            l1 = l2;
            i2 = n;
            localObject4 = localObject1;
            localObject5 = localObject7;
            i3 = i1;
            i4 = k;
            localObject6 = localObject3;
            i5 = m;
            i6 = j;
            i7 = i;
            if (localFirmwareInfoActivityViewModel != null)
            {
              localObject5 = localFirmwareInfoActivityViewModel.getConnectedFirmwareVersion();
              i7 = i;
              i6 = j;
              i5 = m;
              localObject6 = localObject3;
              i4 = k;
              i3 = i1;
              localObject4 = localObject1;
              i2 = n;
              l1 = l2;
            }
          }
        }
        if ((0x403 & l1) != 0L) {
          this.mboundView1.setVisibility(i4);
        }
        if ((0x405 & l1) != 0L) {
          TextViewBindingAdapter.setText(this.mboundView2, (CharSequence)localObject6);
        }
        if ((0x409 & l1) != 0L) {
          this.mboundView2.setVisibility(i5);
        }
        if ((0x411 & l1) != 0L) {
          this.mboundView3.setVisibility(i7);
        }
        if ((0x421 & l1) != 0L)
        {
          this.mboundView4.setVisibility(i3);
          this.mboundView9.setVisibility(i3);
        }
        if ((0x481 & l1) != 0L) {
          TextViewBindingAdapter.setText(this.mboundView6, (CharSequence)localObject5);
        }
        if ((0x400 & l1) != 0L)
        {
          this.mboundView7.setOnClickListener(this.mCallback41);
          this.mboundView8.setOnClickListener(this.mCallback42);
        }
        if ((0x501 & l1) != 0L) {
          this.mboundView7.setVisibility(i2);
        }
        if ((0x601 & l1) != 0L) {
          this.mboundView8.setVisibility(i6);
        }
        if ((0x441 & l1) != 0L) {
          TextViewBindingAdapter.setText(this.textBoardName, (CharSequence)localObject4);
        }
        return;
      }
      finally {}
      label923:
      long l1 = l2 | 0x8000;
      continue;
      label934:
      int i = 4;
      continue;
      label939:
      long l2 = l1 | 0x800;
      continue;
      label950:
      int j = 4;
      continue;
      label955:
      l1 = l2 | 0x2000;
      continue;
      label966:
      int k = 4;
      continue;
      label971:
      l2 = l1 | 0x20000;
      continue;
      label982:
      int m = 4;
      continue;
      label988:
      l1 = l2 | 0x80000;
      continue;
      label999:
      int n = 4;
      continue;
      label1005:
      l2 = l1 | 0x200000;
      continue;
      label1016:
      int i1 = 4;
    }
  }
  
  @Nullable
  public FirmwareInfoActivityViewModel getViewModel()
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
      this.mDirtyFlags = 1024L;
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
    return onChangeViewModel((FirmwareInfoActivityViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((FirmwareInfoActivityViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable FirmwareInfoActivityViewModel paramFirmwareInfoActivityViewModel)
  {
    updateRegistration(0, paramFirmwareInfoActivityViewModel);
    this.mViewModel = paramFirmwareInfoActivityViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityFirmwareInfoBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */