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
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.ToolBarLayout;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.viewmodel.scene.main.MainActivityViewModel;

public class ActivityMainBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final PercentRelativeLayout container;
  @NonNull
  public final ImageView laboratory;
  @Nullable
  private final View.OnClickListener mCallback92;
  @Nullable
  private final View.OnClickListener mCallback93;
  private long mDirtyFlags = -1L;
  @Nullable
  private MainActivityViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  public final ImageView menu;
  @NonNull
  public final ToolBarLayout toolBar;
  
  static
  {
    sViewsWithIds.put(2131296415, 4);
  }
  
  public ActivityMainBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 5, sIncludes, sViewsWithIds);
    this.container = ((PercentRelativeLayout)paramDataBindingComponent[4]);
    this.laboratory = ((ImageView)paramDataBindingComponent[3]);
    this.laboratory.setTag(null);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.menu = ((ImageView)paramDataBindingComponent[2]);
    this.menu.setTag(null);
    this.toolBar = ((ToolBarLayout)paramDataBindingComponent[1]);
    this.toolBar.setTag(null);
    setRootTag(paramView);
    this.mCallback92 = new OnClickListener(this, 1);
    this.mCallback93 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityMainBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMainBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_main_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityMainBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427372, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityMainBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427372, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(MainActivityViewModel paramMainActivityViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 14) {
      try
      {
        this.mDirtyFlags |= 0x2;
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
        paramView.openNavigation();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.openLaboratory();
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
        int j = 0;
        localObject2 = null;
        String str = null;
        boolean bool = false;
        MainActivityViewModel localMainActivityViewModel = this.mViewModel;
        l1 = l2;
        i = j;
        if ((0x7 & l2) != 0L)
        {
          if (localMainActivityViewModel != null) {
            str = localMainActivityViewModel.getCurrentSelectedDevice();
          }
          l1 = l2;
          localObject2 = str;
          i = j;
          if ((0x5 & l2) != 0L)
          {
            if (localMainActivityViewModel != null) {
              bool = localMainActivityViewModel.laboratoryVisible();
            }
            l1 = l2;
            if ((0x5 & l2) != 0L)
            {
              if (!bool) {
                break label256;
              }
              l1 = l2 | 0x10;
            }
            if (!bool) {
              break label266;
            }
            i = 0;
            localObject2 = str;
          }
        }
        if ((0x4 & l1) != 0L)
        {
          this.laboratory.setOnClickListener(this.mCallback93);
          ResourceDrawableBindings.setStateBackground(this.laboratory, getDrawableFromResource(this.laboratory, 2131165448), getDrawableFromResource(this.laboratory, 2131165448));
          this.menu.setOnClickListener(this.mCallback92);
          ResourceDrawableBindings.setStateBackground(this.menu, getDrawableFromResource(this.menu, 2131165442), getDrawableFromResource(this.menu, 2131165449));
        }
        if ((0x5 & l1) != 0L) {
          this.laboratory.setVisibility(i);
        }
        if ((0x7 & l1) != 0L) {
          this.toolBar.setTitle((String)localObject2);
        }
        return;
      }
      finally {}
      label256:
      long l1 = l2 | 0x8;
      continue;
      label266:
      int i = 8;
      Object localObject2 = localObject1;
    }
  }
  
  @Nullable
  public MainActivityViewModel getViewModel()
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
    return onChangeViewModel((MainActivityViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((MainActivityViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable MainActivityViewModel paramMainActivityViewModel)
  {
    updateRegistration(0, paramMainActivityViewModel);
    this.mViewModel = paramMainActivityViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityMainBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */