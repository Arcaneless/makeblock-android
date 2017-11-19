package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.ImageViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.FitWidthTextView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.ExpandGuideViewModel;

public class FragmentExpandGuideBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final PercentRelativeLayout content;
  @NonNull
  public final ImageView formIcon;
  @Nullable
  private final View.OnClickListener mCallback46;
  @Nullable
  private final View.OnClickListener mCallback47;
  private long mDirtyFlags = -1L;
  @Nullable
  private ExpandGuideViewModel mViewModel;
  @NonNull
  private final FrameLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView1;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final ImageView mboundView3;
  @NonNull
  private final FitWidthTextView mboundView4;
  @NonNull
  private final FitWidthTextView mboundView5;
  @NonNull
  private final PercentLinearLayout mboundView6;
  @NonNull
  public final ImageView playIcon;
  @NonNull
  public final FitWidthTextView playTitle;
  
  static
  {
    sViewsWithIds.put(2131296416, 7);
    sViewsWithIds.put(2131296459, 8);
    sViewsWithIds.put(2131296619, 9);
    sViewsWithIds.put(2131296620, 10);
  }
  
  public FragmentExpandGuideBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 11, sIncludes, sViewsWithIds);
    this.content = ((PercentRelativeLayout)paramDataBindingComponent[7]);
    this.formIcon = ((ImageView)paramDataBindingComponent[8]);
    this.mboundView0 = ((FrameLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((ImageView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((FitWidthTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((FitWidthTextView)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((PercentLinearLayout)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.playIcon = ((ImageView)paramDataBindingComponent[9]);
    this.playTitle = ((FitWidthTextView)paramDataBindingComponent[10]);
    setRootTag(paramView);
    this.mCallback47 = new OnClickListener(this, 2);
    this.mCallback46 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentExpandGuideBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentExpandGuideBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_expand_guide_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentExpandGuideBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentExpandGuideBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentExpandGuideBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427424, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentExpandGuideBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentExpandGuideBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentExpandGuideBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427424, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(ExpandGuideViewModel paramExpandGuideViewModel, int paramInt)
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
      ExpandGuideViewModel localExpandGuideViewModel = this.mViewModel;
      if (localExpandGuideViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localExpandGuideViewModel.skipToMoreInfo(ViewTools.getActivity(paramView));
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.close();
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
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      ExpandGuideViewModel localExpandGuideViewModel = this.mViewModel;
      Object localObject5 = localObject9;
      Object localObject4 = localObject6;
      Object localObject3 = localObject8;
      Object localObject1 = localObject7;
      if ((l & 0x3) != 0L)
      {
        localObject5 = localObject9;
        localObject4 = localObject6;
        localObject3 = localObject8;
        localObject1 = localObject7;
        if (localExpandGuideViewModel != null)
        {
          localObject4 = localExpandGuideViewModel.getIconDrawable();
          localObject1 = localExpandGuideViewModel.getPlayMsg();
          localObject3 = localExpandGuideViewModel.getName();
          localObject5 = localExpandGuideViewModel.getFormMsg();
        }
      }
      if ((l & 0x3) != 0L)
      {
        TextViewBindingAdapter.setText(this.mboundView1, (CharSequence)localObject3);
        ImageViewBindingAdapter.setImageDrawable(this.mboundView3, (Drawable)localObject4);
        TextViewBindingAdapter.setText(this.mboundView4, (CharSequence)localObject5);
        TextViewBindingAdapter.setText(this.mboundView5, (CharSequence)localObject1);
      }
      if ((0x2 & l) != 0L)
      {
        this.mboundView2.setOnClickListener(this.mCallback46);
        this.mboundView6.setOnClickListener(this.mCallback47);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public ExpandGuideViewModel getViewModel()
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
    return onChangeViewModel((ExpandGuideViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((ExpandGuideViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable ExpandGuideViewModel paramExpandGuideViewModel)
  {
    updateRegistration(0, paramExpandGuideViewModel);
    this.mViewModel = paramExpandGuideViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentExpandGuideBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */