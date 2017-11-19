package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
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

public class LayoutServoResetBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView bluetoothStatusImg;
  private long mDirtyFlags = -1L;
  @Nullable
  private PanelViewModel mViewModel;
  private OnClickListenerImpl mViewModelResetSmartServoAndroidViewViewOnClickListener;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  
  static
  {
    sViewsWithIds.put(2131296314, 1);
  }
  
  public LayoutServoResetBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 2, sIncludes, sViewsWithIds);
    this.bluetoothStatusImg = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static LayoutServoResetBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoResetBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/layout_servo_reset_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LayoutServoResetBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LayoutServoResetBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoResetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427497, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LayoutServoResetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutServoResetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LayoutServoResetBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427497, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
  
  protected void executeBindings()
  {
    for (;;)
    {
      try
      {
        long l = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        Object localObject3 = null;
        PanelViewModel localPanelViewModel = this.mViewModel;
        Object localObject1 = localObject3;
        if ((l & 0x3) != 0L)
        {
          localObject1 = localObject3;
          if (localPanelViewModel != null)
          {
            if (this.mViewModelResetSmartServoAndroidViewViewOnClickListener != null) {
              break label95;
            }
            localObject1 = new OnClickListenerImpl();
            this.mViewModelResetSmartServoAndroidViewViewOnClickListener = ((OnClickListenerImpl)localObject1);
            localObject1 = ((OnClickListenerImpl)localObject1).setValue(localPanelViewModel);
          }
        }
        if ((l & 0x3) != 0L) {
          this.mboundView0.setOnClickListener((View.OnClickListener)localObject1);
        }
        return;
      }
      finally {}
      label95:
      OnClickListenerImpl localOnClickListenerImpl = this.mViewModelResetSmartServoAndroidViewViewOnClickListener;
    }
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
  
  public static class OnClickListenerImpl
    implements View.OnClickListener
  {
    private PanelViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.resetSmartServo(paramView);
    }
    
    public OnClickListenerImpl setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnClickListenerImpl localOnClickListenerImpl = this;
      if (paramPanelViewModel == null) {
        localOnClickListenerImpl = null;
      }
      return localOnClickListenerImpl;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LayoutServoResetBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */