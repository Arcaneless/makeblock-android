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
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.scene.main.MainContentViewModel;

public class ActivityMain31Binding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final ImageView create;
  @NonNull
  public final ImageView expand;
  @Nullable
  private final View.OnClickListener mCallback111;
  @Nullable
  private final View.OnClickListener mCallback112;
  @Nullable
  private final View.OnClickListener mCallback113;
  private long mDirtyFlags = -1L;
  @Nullable
  private MainContentViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  public final ImageView play;
  
  public ActivityMain31Binding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.create = ((ImageView)paramDataBindingComponent[2]);
    this.create.setTag(null);
    this.expand = ((ImageView)paramDataBindingComponent[3]);
    this.expand.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.play = ((ImageView)paramDataBindingComponent[1]);
    this.play.setTag(null);
    setRootTag(paramView);
    this.mCallback112 = new OnClickListener(this, 2);
    this.mCallback113 = new OnClickListener(this, 3);
    this.mCallback111 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityMain31Binding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain31Binding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_main_3_1_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityMain31Binding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityMain31Binding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain31Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427374, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityMain31Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain31Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityMain31Binding)DataBindingUtil.inflate(paramLayoutInflater, 2131427374, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(MainContentViewModel paramMainContentViewModel, int paramInt)
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
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      MainContentViewModel localMainContentViewModel = this.mViewModel;
      if (localMainContentViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localMainContentViewModel.create(ViewTools.getActivity(paramView));
        return;
      }
      continue;
      localMainContentViewModel = this.mViewModel;
      if (localMainContentViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localMainContentViewModel.expand(ViewTools.getActivity(paramView));
        return;
      }
      continue;
      localMainContentViewModel = this.mViewModel;
      if (localMainContentViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localMainContentViewModel.play(ViewTools.getActivity(paramView));
        return;
      }
    }
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      MainContentViewModel localMainContentViewModel = this.mViewModel;
      if ((0x2 & l) != 0L)
      {
        this.create.setOnClickListener(this.mCallback112);
        this.expand.setOnClickListener(this.mCallback113);
        this.play.setOnClickListener(this.mCallback111);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public MainContentViewModel getViewModel()
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
    return onChangeViewModel((MainContentViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((MainContentViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable MainContentViewModel paramMainContentViewModel)
  {
    updateRegistration(0, paramMainContentViewModel);
    this.mViewModel = paramMainContentViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityMain31Binding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */