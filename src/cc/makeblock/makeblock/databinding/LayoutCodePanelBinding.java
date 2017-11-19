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
import cc.makeblock.makeblock.customview.BlocklyWidgetLayout;
import cc.makeblock.makeblock.customview.BlocklyWidgetLayout.OnShowListener;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.viewmodel.PanelViewModel;
import com.gzsll.jsbridge.WVJBWebView;

public class LayoutCodePanelBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(7);
  @Nullable
  private static final SparseIntArray sViewsWithIds;
  @NonNull
  public final BlocklyWidgetLayout blocklyWidgetLayout;
  @Nullable
  private final View.OnClickListener mCallback49;
  private long mDirtyFlags = -1L;
  @Nullable
  private PanelViewModel mViewModel;
  private OnShowListenerImpl mViewModelOnShowWidgetCcMakeblockMakeblockCustomviewBlocklyWidgetLayoutOnShowListener;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final PercentLinearLayout mboundView2;
  @Nullable
  private final LayoutServoResetBinding mboundView21;
  @Nullable
  private final LayoutServoUnlockBinding mboundView22;
  @NonNull
  public final WVJBWebView webview;
  
  static
  {
    sIncludes.setIncludes(2, new String[] { "layout_servo_reset", "layout_servo_unlock" }, new int[] { 4, 5 }, new int[] { 2131427497, 2131427498 });
    sViewsWithIds = new SparseIntArray();
    sViewsWithIds.put(2131296769, 6);
  }
  
  public LayoutCodePanelBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.blocklyWidgetLayout = ((BlocklyWidgetLayout)paramDataBindingComponent[3]);
    this.blocklyWidgetLayout.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((PercentLinearLayout)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView21 = ((LayoutServoResetBinding)paramDataBindingComponent[4]);
    setContainedBinding(this.mboundView21);
    this.mboundView22 = ((LayoutServoUnlockBinding)paramDataBindingComponent[5]);
    setContainedBinding(this.mboundView22);
    this.webview = ((WVJBWebView)paramDataBindingComponent[6]);
    setRootTag(paramView);
    this.mCallback49 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static LayoutCodePanelBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutCodePanelBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/layout_code_panel_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LayoutCodePanelBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LayoutCodePanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutCodePanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427465, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LayoutCodePanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutCodePanelBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LayoutCodePanelBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427465, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
        paramView.quitBlockly();
      }
      return;
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
        j = 0;
        boolean bool2 = false;
        boolean bool1 = false;
        i = 0;
        Object localObject1 = null;
        Object localObject3 = null;
        PanelViewModel localPanelViewModel = this.mViewModel;
        l1 = l2;
        if ((0x3 & l2) != 0L)
        {
          localObject1 = localObject3;
          if (localPanelViewModel != null)
          {
            bool2 = localPanelViewModel.getServoStateButtonVisibility();
            bool1 = localPanelViewModel.getBlocklyServoResetButtonVisibility();
            if (this.mViewModelOnShowWidgetCcMakeblockMakeblockCustomviewBlocklyWidgetLayoutOnShowListener == null)
            {
              localObject1 = new OnShowListenerImpl();
              this.mViewModelOnShowWidgetCcMakeblockMakeblockCustomviewBlocklyWidgetLayoutOnShowListener = ((OnShowListenerImpl)localObject1);
              localObject1 = ((OnShowListenerImpl)localObject1).setValue(localPanelViewModel);
            }
          }
          else
          {
            l1 = l2;
            if ((0x3 & l2) != 0L)
            {
              if (!bool2) {
                break label284;
              }
              l1 = l2 | 0x20;
            }
            l2 = l1;
            if ((0x3 & l1) != 0L)
            {
              if (!bool1) {
                break label294;
              }
              l2 = l1 | 0x8;
            }
            if (!bool2) {
              break label304;
            }
            i = 0;
            if (!bool1) {
              break label310;
            }
            j = 0;
            l1 = l2;
          }
        }
        else
        {
          if ((0x3 & l1) != 0L)
          {
            this.blocklyWidgetLayout.setOnShowListener((BlocklyWidgetLayout.OnShowListener)localObject1);
            this.mboundView21.getRoot().setVisibility(j);
            this.mboundView21.setViewModel(localPanelViewModel);
            this.mboundView22.getRoot().setVisibility(i);
            this.mboundView22.setViewModel(localPanelViewModel);
          }
          if ((0x2 & l1) != 0L) {
            this.mboundView1.setOnClickListener(this.mCallback49);
          }
          executeBindingsOn(this.mboundView21);
          executeBindingsOn(this.mboundView22);
          return;
        }
      }
      finally {}
      OnShowListenerImpl localOnShowListenerImpl = this.mViewModelOnShowWidgetCcMakeblockMakeblockCustomviewBlocklyWidgetLayoutOnShowListener;
      continue;
      label284:
      long l1 = l2 | 0x10;
      continue;
      label294:
      long l2 = l1 | 0x4;
      continue;
      label304:
      int i = 8;
      continue;
      label310:
      int j = 8;
      l1 = l2;
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
      if ((!this.mboundView21.hasPendingBindings()) && (!this.mboundView22.hasPendingBindings())) {
        return false;
      }
    }
    finally {}
    return true;
  }
  
  public void invalidateAll()
  {
    try
    {
      this.mDirtyFlags = 2L;
      this.mboundView21.invalidateAll();
      this.mboundView22.invalidateAll();
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
  
  public static class OnShowListenerImpl
    implements BlocklyWidgetLayout.OnShowListener
  {
    private PanelViewModel value;
    
    public void onShow(boolean paramBoolean)
    {
      this.value.onShowWidget(paramBoolean);
    }
    
    public OnShowListenerImpl setValue(PanelViewModel paramPanelViewModel)
    {
      this.value = paramPanelViewModel;
      OnShowListenerImpl localOnShowListenerImpl = this;
      if (paramPanelViewModel == null) {
        localOnShowListenerImpl = null;
      }
      return localOnShowListenerImpl;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LayoutCodePanelBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */