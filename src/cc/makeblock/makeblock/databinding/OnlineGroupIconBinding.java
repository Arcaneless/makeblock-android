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
import cc.makeblock.makeblock.viewmodel.PanelViewModel;

public class OnlineGroupIconBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  private long mDirtyFlags = -1L;
  @Nullable
  private PanelViewModel mViewModel;
  private OnClickListenerImpl mViewModelNavToGroupAndroidViewViewOnClickListener;
  @NonNull
  public final PercentRelativeLayout navGroup = (PercentRelativeLayout)mapBindings(paramDataBindingComponent, paramView, 1, sIncludes, sViewsWithIds)[0];
  
  public OnlineGroupIconBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    this.navGroup.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static OnlineGroupIconBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static OnlineGroupIconBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/online_group_icon_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new OnlineGroupIconBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static OnlineGroupIconBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static OnlineGroupIconBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427518, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static OnlineGroupIconBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static OnlineGroupIconBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (OnlineGroupIconBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427518, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
            if (this.mViewModelNavToGroupAndroidViewViewOnClickListener != null) {
              break label95;
            }
            localObject1 = new OnClickListenerImpl();
            this.mViewModelNavToGroupAndroidViewViewOnClickListener = ((OnClickListenerImpl)localObject1);
            localObject1 = ((OnClickListenerImpl)localObject1).setValue(localPanelViewModel);
          }
        }
        if ((l & 0x3) != 0L) {
          this.navGroup.setOnClickListener((View.OnClickListener)localObject1);
        }
        return;
      }
      finally {}
      label95:
      OnClickListenerImpl localOnClickListenerImpl = this.mViewModelNavToGroupAndroidViewViewOnClickListener;
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
      this.value.navToGroup(paramView);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\OnlineGroupIconBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */