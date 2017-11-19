package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.ProgressScrollBar;

public class ActivityPlayBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ProgressScrollBar indicatorOfficialProgress;
  private long mDirtyFlags = -1L;
  @NonNull
  private final PercentLinearLayout mboundView0;
  @NonNull
  public final RecyclerView officialProjectList;
  
  static
  {
    sViewsWithIds.put(2131296598, 1);
    sViewsWithIds.put(2131296512, 2);
  }
  
  public ActivityPlayBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 0);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 3, sIncludes, sViewsWithIds);
    this.indicatorOfficialProgress = ((ProgressScrollBar)paramDataBindingComponent[2]);
    this.mboundView0 = ((PercentLinearLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.officialProjectList = ((RecyclerView)paramDataBindingComponent[1]);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityPlayBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPlayBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_play_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityPlayBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityPlayBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPlayBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427382, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityPlayBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityPlayBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityPlayBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427382, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      return;
    }
    finally {}
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
      this.mDirtyFlags = 1L;
      requestRebind();
      return;
    }
    finally {}
  }
  
  protected boolean onFieldChange(int paramInt1, Object paramObject, int paramInt2)
  {
    return false;
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    return true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityPlayBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */