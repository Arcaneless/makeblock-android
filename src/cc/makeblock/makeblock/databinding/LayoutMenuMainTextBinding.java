package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.slidemenu.TextViewModel;

public class LayoutMenuMainTextBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  private long mDirtyFlags = -1L;
  @Nullable
  private TextViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final AutoResizeTextView mboundView1;
  
  public LayoutMenuMainTextBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 2, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag("13.15%");
    this.mboundView1 = ((AutoResizeTextView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/layout_menu_main_text_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new LayoutMenuMainTextBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427487, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static LayoutMenuMainTextBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (LayoutMenuMainTextBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427487, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(TextViewModel paramTextViewModel, int paramInt)
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
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      Object localObject3 = null;
      TextViewModel localTextViewModel = this.mViewModel;
      Object localObject1 = localObject3;
      if ((l & 0x3) != 0L)
      {
        localObject1 = localObject3;
        if (localTextViewModel != null) {
          localObject1 = localTextViewModel.getText();
        }
      }
      if ((l & 0x3) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView1, (CharSequence)localObject1);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public TextViewModel getViewModel()
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
    return onChangeViewModel((TextViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((TextViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable TextViewModel paramTextViewModel)
  {
    updateRegistration(0, paramTextViewModel);
    this.mViewModel = paramTextViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\LayoutMenuMainTextBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */