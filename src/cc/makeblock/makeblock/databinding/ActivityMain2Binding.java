package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.scene.main.MainContentViewModel;

public class ActivityMain2Binding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final ImageView create;
  @Nullable
  private final View.OnClickListener mCallback75;
  @Nullable
  private final View.OnClickListener mCallback76;
  private long mDirtyFlags = -1L;
  @Nullable
  private MainContentViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView3;
  @NonNull
  public final ImageView play;
  
  public ActivityMain2Binding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.create = ((ImageView)paramDataBindingComponent[2]);
    this.create.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.play = ((ImageView)paramDataBindingComponent[1]);
    this.play.setTag(null);
    setRootTag(paramView);
    this.mCallback76 = new OnClickListener(this, 2);
    this.mCallback75 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityMain2Binding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain2Binding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_main_2_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityMain2Binding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427373, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityMain2Binding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityMain2Binding)DataBindingUtil.inflate(paramLayoutInflater, 2131427373, paramViewGroup, paramBoolean, paramDataBindingComponent);
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
        localMainContentViewModel.play(ViewTools.getActivity(paramView));
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
        Drawable localDrawable1 = null;
        boolean bool = false;
        MainContentViewModel localMainContentViewModel = this.mViewModel;
        l1 = l2;
        if ((l2 & 0x3) != 0L)
        {
          if (localMainContentViewModel != null) {
            bool = localMainContentViewModel.isCodey();
          }
          l1 = l2;
          if ((l2 & 0x3) != 0L)
          {
            if (bool) {
              l1 = l2 | 0x8 | 0x20;
            }
          }
          else
          {
            if (!bool) {
              break label197;
            }
            i = getColorFromResource(this.mboundView3, 2131034194);
            if (!bool) {
              break label210;
            }
            localDrawable1 = getDrawableFromResource(this.create, 2131165390);
          }
        }
        else
        {
          if ((0x2 & l1) != 0L)
          {
            this.create.setOnClickListener(this.mCallback76);
            this.play.setOnClickListener(this.mCallback75);
          }
          if ((l1 & 0x3) != 0L)
          {
            ImageViewBindingAdapter.setImageDrawable(this.create, localDrawable1);
            this.mboundView3.setTextColor(i);
          }
          return;
        }
      }
      finally {}
      long l1 = l2 | 0x4 | 0x10;
      continue;
      label197:
      int i = getColorFromResource(this.mboundView3, 2131034272);
      continue;
      label210:
      Drawable localDrawable2 = getDrawableFromResource(this.create, 2131165389);
    }
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityMain2Binding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */