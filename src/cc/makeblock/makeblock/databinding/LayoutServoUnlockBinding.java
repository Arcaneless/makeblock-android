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
import cc.makeblock.makeblock.viewmodel.PanelViewModel;

public class LayoutServoUnlockBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView bluetoothStatusImg;
  @Nullable
  private final View.OnClickListener mCallback71;
  private long mDirtyFlags = -1L;
  @Nullable
  private PanelViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  
  static
  {
    sViewsWithIds.put(2131296314, 1);
  }
  
  public LayoutServoUnlockBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 2, sIncludes, sViewsWithIds);
    this.bluetoothStatusImg = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    setRootTag(paramView);
    this.mCallback71 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static LayoutServoUnlockBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoUnlockBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/layout_servo_unlock_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LayoutServoUnlockBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LayoutServoUnlockBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoUnlockBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427498, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LayoutServoUnlockBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoUnlockBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LayoutServoUnlockBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427498, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(PanelViewModel paramPanelViewModel, int paramInt)
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
        paramView.unlockServo();
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
      PanelViewModel localPanelViewModel = this.mViewModel;
      if ((0x2 & l) != 0L) {
        this.mboundView0.setOnClickListener(this.mCallback71);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public PanelViewModel getViewModel()
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
    switch (paramInt1)
    {
    default: 
      return false;
    }
    return onChangeViewModel((PanelViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((PanelViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable PanelViewModel paramPanelViewModel)
  {
    updateRegistration(0, paramPanelViewModel);
    this.mViewModel = paramPanelViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LayoutServoUnlockBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */