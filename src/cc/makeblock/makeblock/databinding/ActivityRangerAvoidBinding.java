package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerAvoidViewModel;

public class ActivityRangerAvoidBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @Nullable
  private final View.OnClickListener mCallback82;
  @Nullable
  private final View.OnClickListener mCallback83;
  private long mDirtyFlags = -1L;
  @Nullable
  private RangerAvoidViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  public final ImageView playgroundModeImg;
  
  static
  {
    sViewsWithIds.put(2131296621, 3);
  }
  
  public ActivityRangerAvoidBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.playgroundModeImg = ((ImageView)paramDataBindingComponent[3]);
    setRootTag(paramView);
    this.mCallback82 = new OnClickListener(this, 1);
    this.mCallback83 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_ranger_avoid_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityRangerAvoidBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427383, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerAvoidBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityRangerAvoidBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427383, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(RangerAvoidViewModel paramRangerAvoidViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 66) {
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
        paramView.stop();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.play();
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
        i = 0;
        boolean bool = false;
        j = 0;
        RangerAvoidViewModel localRangerAvoidViewModel = this.mViewModel;
        l1 = l2;
        if ((l2 & 0x7) != 0L)
        {
          if (localRangerAvoidViewModel != null) {
            bool = localRangerAvoidViewModel.isShowPlay();
          }
          l1 = l2;
          if ((l2 & 0x7) != 0L)
          {
            if (bool) {
              l1 = l2 | 0x10 | 0x40;
            }
          }
          else
          {
            if (!bool) {
              break label179;
            }
            i = 0;
            if (!bool) {
              break label185;
            }
            j = 8;
          }
        }
        else
        {
          if ((l1 & 0x7) != 0L)
          {
            this.mboundView1.setVisibility(j);
            this.mboundView2.setVisibility(i);
          }
          if ((0x4 & l1) != 0L)
          {
            this.mboundView1.setOnClickListener(this.mCallback82);
            this.mboundView2.setOnClickListener(this.mCallback83);
          }
          return;
        }
      }
      finally {}
      long l1 = l2 | 0x8 | 0x20;
      continue;
      label179:
      int i = 8;
      continue;
      label185:
      int j = 0;
    }
  }
  
  @Nullable
  public RangerAvoidViewModel getViewModel()
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
    return onChangeViewModel((RangerAvoidViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((RangerAvoidViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable RangerAvoidViewModel paramRangerAvoidViewModel)
  {
    updateRegistration(0, paramRangerAvoidViewModel);
    this.mViewModel = paramRangerAvoidViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityRangerAvoidBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */