package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.databinding.generated.callback.OnDrawPathFinishListener;
import android.databinding.generated.callback.OnDrawPathFinishListener.Listener;
import android.databinding.generated.callback.ResetListener;
import android.databinding.generated.callback.ResetListener.Listener;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.playground.PathMap;
import cc.makeblock.makeblock.customview.playground.PathMap.OnDrawPathFinishListener;
import cc.makeblock.makeblock.customview.playground.PathMap.ResetListener;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerDrawPathViewModel;
import java.util.List;

public class ActivityRangerDrawPathBinding
  extends ViewDataBinding
  implements OnClickListener.Listener, OnDrawPathFinishListener.Listener, ResetListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @Nullable
  private final PathMap.OnDrawPathFinishListener mCallback84;
  @Nullable
  private final PathMap.ResetListener mCallback85;
  @Nullable
  private final View.OnClickListener mCallback86;
  @Nullable
  private final View.OnClickListener mCallback87;
  private long mDirtyFlags = -1L;
  @Nullable
  private RangerDrawPathViewModel mViewModel;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  private final PathMap mboundView1;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final ImageView mboundView3;
  
  public ActivityRangerDrawPathBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PathMap)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((ImageView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    setRootTag(paramView);
    this.mCallback87 = new OnClickListener(this, 4);
    this.mCallback86 = new OnClickListener(this, 3);
    this.mCallback84 = new OnDrawPathFinishListener(this, 1);
    this.mCallback85 = new ResetListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_ranger_draw_path_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityRangerDrawPathBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427385, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerDrawPathBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityRangerDrawPathBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427385, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(RangerDrawPathViewModel paramRangerDrawPathViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 50) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 51) {
      try
      {
        this.mDirtyFlags |= 0x4;
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
        paramView.play();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.pause();
        return;
      }
    }
  }
  
  public final void _internalCallbackOnDrawPathFinish(int paramInt, List<PointF> paramList)
  {
    RangerDrawPathViewModel localRangerDrawPathViewModel = this.mViewModel;
    if (localRangerDrawPathViewModel != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        localRangerDrawPathViewModel.onDrawPathFinish(paramList);
      }
      return;
    }
  }
  
  public final void _internalCallbackOnReset(int paramInt)
  {
    RangerDrawPathViewModel localRangerDrawPathViewModel = this.mViewModel;
    if (localRangerDrawPathViewModel != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        localRangerDrawPathViewModel.reset();
      }
      return;
    }
  }
  
  protected void executeBindings()
  {
    for (;;)
    {
      int i;
      try
      {
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        m = 0;
        int i2 = 0;
        boolean bool3 = false;
        boolean bool2 = false;
        int n = 0;
        int i1 = 0;
        RangerDrawPathViewModel localRangerDrawPathViewModel = this.mViewModel;
        k = i1;
        l1 = l2;
        boolean bool1 = bool3;
        j = n;
        if ((0xF & l2) != 0L)
        {
          i = i2;
          if ((0xB & l2) != 0L)
          {
            i = i2;
            if (localRangerDrawPathViewModel != null) {
              i = localRangerDrawPathViewModel.getPassedIndex();
            }
          }
          k = i1;
          l1 = l2;
          m = i;
          bool1 = bool3;
          j = n;
          if ((0xD & l2) != 0L)
          {
            bool1 = bool2;
            if (localRangerDrawPathViewModel != null) {
              bool1 = localRangerDrawPathViewModel.isPause();
            }
            l1 = l2;
            if ((0xD & l2) != 0L)
            {
              if (!bool1) {
                break label337;
              }
              l1 = l2 | 0x80;
            }
            if (bool1) {
              break label348;
            }
            j = 1;
            if (!bool1) {
              break label353;
            }
            k = 0;
            l2 = l1;
            if ((0xD & l1) != 0L)
            {
              if (j == 0) {
                break label358;
              }
              l2 = l1 | 0x20;
            }
            if (j == 0) {
              break label369;
            }
            j = 0;
            m = i;
            l1 = l2;
          }
        }
        if ((0x8 & l1) != 0L)
        {
          this.mboundView1.setOnDrawPathFinishListener(this.mCallback84);
          this.mboundView1.setResetListener(this.mCallback85);
          this.mboundView2.setOnClickListener(this.mCallback86);
          this.mboundView3.setOnClickListener(this.mCallback87);
        }
        if ((0xB & l1) != 0L) {
          this.mboundView1.setPassedIndex(m);
        }
        if ((0xD & l1) != 0L)
        {
          this.mboundView1.setPause(bool1);
          this.mboundView2.setVisibility(j);
          this.mboundView3.setVisibility(k);
        }
        return;
      }
      finally {}
      label337:
      long l1 = l2 | 0x40;
      continue;
      label348:
      int j = 0;
      continue;
      label353:
      int k = 4;
      continue;
      label358:
      long l2 = l1 | 0x10;
      continue;
      label369:
      j = 4;
      l1 = l2;
      int m = i;
    }
  }
  
  @Nullable
  public RangerDrawPathViewModel getViewModel()
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
      this.mDirtyFlags = 8L;
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
    return onChangeViewModel((RangerDrawPathViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((RangerDrawPathViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable RangerDrawPathViewModel paramRangerDrawPathViewModel)
  {
    updateRegistration(0, paramRangerDrawPathViewModel);
    this.mViewModel = paramRangerDrawPathViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityRangerDrawPathBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */