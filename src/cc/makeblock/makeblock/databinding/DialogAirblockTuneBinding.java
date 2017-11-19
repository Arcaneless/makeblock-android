package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.viewmodel.customview.dialog.AirblockTuneDialogViewModel;

public class DialogAirblockTuneBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final ImageView gifImage;
  @Nullable
  private final View.OnClickListener mCallback20;
  @Nullable
  private final View.OnClickListener mCallback21;
  private long mDirtyFlags = -1L;
  @Nullable
  private AirblockTuneDialogViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView2;
  @NonNull
  private final PercentFrameLayout mboundView3;
  @NonNull
  private final PercentFrameLayout mboundView5;
  @NonNull
  public final ImageView popupClose;
  
  public DialogAirblockTuneBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 6, sIncludes, sViewsWithIds);
    this.gifImage = ((ImageView)paramDataBindingComponent[4]);
    this.gifImage.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView2 = ((AutoResizeTextView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((PercentFrameLayout)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView5 = ((PercentFrameLayout)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.popupClose = ((ImageView)paramDataBindingComponent[1]);
    this.popupClose.setTag(null);
    setRootTag(paramView);
    this.mCallback21 = new OnClickListener(this, 2);
    this.mCallback20 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static DialogAirblockTuneBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogAirblockTuneBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/dialog_airblock_tune_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new DialogAirblockTuneBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static DialogAirblockTuneBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogAirblockTuneBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427400, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static DialogAirblockTuneBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogAirblockTuneBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (DialogAirblockTuneBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427400, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(AirblockTuneDialogViewModel paramAirblockTuneDialogViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 26) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 39) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 4) {
      try
      {
        this.mDirtyFlags |= 0x8;
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
        paramView.startTuning();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.dismissDialog();
        return;
      }
    }
  }
  
  protected void executeBindings()
  {
    for (;;)
    {
      int k;
      try
      {
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        boolean bool1 = false;
        int i3 = 0;
        i2 = 0;
        i = 0;
        i1 = 0;
        int i4 = 0;
        boolean bool3 = false;
        n = 0;
        j = 0;
        boolean bool2 = false;
        AirblockTuneDialogViewModel localAirblockTuneDialogViewModel = this.mViewModel;
        l1 = l2;
        m = i3;
        if ((0x1F & l2) != 0L)
        {
          l1 = l2;
          if ((0x15 & l2) != 0L)
          {
            if (localAirblockTuneDialogViewModel != null) {
              bool1 = localAirblockTuneDialogViewModel.getLoadingGifVisibility();
            }
            l1 = l2;
            if ((0x15 & l2) != 0L)
            {
              if (!bool1) {
                break label434;
              }
              l1 = l2 | 0x100;
            }
            if (!bool1) {
              break label445;
            }
            i = 0;
          }
          k = i4;
          if ((0x11 & l1) != 0L)
          {
            k = i4;
            if (localAirblockTuneDialogViewModel != null) {
              k = localAirblockTuneDialogViewModel.getGif();
            }
          }
          l2 = l1;
          if ((0x13 & l1) != 0L)
          {
            bool1 = bool3;
            if (localAirblockTuneDialogViewModel != null) {
              bool1 = localAirblockTuneDialogViewModel.getFinishTextVisibility();
            }
            l2 = l1;
            if ((0x13 & l1) != 0L)
            {
              if (!bool1) {
                break label451;
              }
              l2 = l1 | 0x400;
            }
            if (!bool1) {
              break label462;
            }
            j = 0;
          }
          l1 = l2;
          m = i3;
          n = j;
          i1 = k;
          i2 = i;
          if ((0x19 & l2) != 0L)
          {
            bool1 = bool2;
            if (localAirblockTuneDialogViewModel != null) {
              bool1 = localAirblockTuneDialogViewModel.getButtonVisibility();
            }
            l1 = l2;
            if ((0x19 & l2) != 0L)
            {
              if (!bool1) {
                break label467;
              }
              l1 = l2 | 0x40;
            }
            if (!bool1) {
              break label478;
            }
            m = 0;
            i2 = i;
            i1 = k;
            n = j;
          }
        }
        if ((0x11 & l1) != 0L) {
          GlideBindings.setImageSrc(this.gifImage, i1);
        }
        if ((0x13 & l1) != 0L) {
          this.mboundView2.setVisibility(n);
        }
        if ((0x15 & l1) != 0L) {
          this.mboundView3.setVisibility(i2);
        }
        if ((0x10 & l1) != 0L)
        {
          this.mboundView5.setOnClickListener(this.mCallback21);
          this.popupClose.setOnClickListener(this.mCallback20);
        }
        if ((0x19 & l1) != 0L) {
          this.mboundView5.setVisibility(m);
        }
        return;
      }
      finally {}
      label434:
      long l1 = l2 | 0x80;
      continue;
      label445:
      int i = 8;
      continue;
      label451:
      long l2 = l1 | 0x200;
      continue;
      label462:
      int j = 4;
      continue;
      label467:
      l1 = l2 | 0x20;
      continue;
      label478:
      int m = 4;
      int n = j;
      int i1 = k;
      int i2 = i;
    }
  }
  
  @Nullable
  public AirblockTuneDialogViewModel getViewModel()
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
      this.mDirtyFlags = 16L;
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
    return onChangeViewModel((AirblockTuneDialogViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((AirblockTuneDialogViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable AirblockTuneDialogViewModel paramAirblockTuneDialogViewModel)
  {
    updateRegistration(0, paramAirblockTuneDialogViewModel);
    this.mViewModel = paramAirblockTuneDialogViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\DialogAirblockTuneBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */