package cc.makeblock.makeblock.databinding;

import android.content.res.Resources;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.net.Uri;
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
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.viewmodel.controller.play.PlayItemViewModel;

public class ItemPlayProjectBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final PercentRelativeLayout itemProjectFront;
  @Nullable
  private final View.OnClickListener mCallback101;
  private long mDirtyFlags = -1L;
  @Nullable
  private PlayItemViewModel mViewModel;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView3;
  
  public ItemPlayProjectBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 4, sIncludes, sViewsWithIds);
    this.itemProjectFront = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.itemProjectFront.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    setRootTag(paramView);
    this.mCallback101 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ItemPlayProjectBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemPlayProjectBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/item_play_project_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ItemPlayProjectBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ItemPlayProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemPlayProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427438, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ItemPlayProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemPlayProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ItemPlayProjectBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427438, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(PlayItemViewModel paramPlayItemViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 56) {
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
    PlayItemViewModel localPlayItemViewModel = this.mViewModel;
    if (localPlayItemViewModel != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        localPlayItemViewModel.onClick(paramView);
      }
      return;
    }
  }
  
  protected void executeBindings()
  {
    try
    {
      long l = this.mDirtyFlags;
      this.mDirtyFlags = 0L;
      int j = 0;
      int k = 0;
      Object localObject3 = null;
      Object localObject6 = null;
      Object localObject5 = null;
      PlayItemViewModel localPlayItemViewModel = this.mViewModel;
      Object localObject4 = localObject5;
      if ((l & 0x7) != 0L)
      {
        Object localObject1 = localObject6;
        int i = k;
        if ((l & 0x5) != 0L)
        {
          localObject1 = localObject6;
          i = k;
          if (localPlayItemViewModel != null)
          {
            i = localPlayItemViewModel.getTypeMarkIcon();
            localObject1 = localPlayItemViewModel.getImageURI();
          }
        }
        localObject3 = localObject1;
        localObject4 = localObject5;
        j = i;
        if (localPlayItemViewModel != null)
        {
          localObject4 = localPlayItemViewModel.getProjectItemName();
          j = i;
          localObject3 = localObject1;
        }
      }
      if ((0x4 & l) != 0L) {
        this.itemProjectFront.setOnClickListener(this.mCallback101);
      }
      if ((l & 0x5) != 0L)
      {
        GlideBindings.setRoundImageUriWithPlaceHolder(this.mboundView1, (Uri)localObject3, getDrawableFromResource(this.mboundView1, 2131165606), this.mboundView1.getResources().getDimension(2131099793));
        ResourceDrawableBindings.setImageUri(this.mboundView2, j);
      }
      if ((l & 0x7) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView3, (CharSequence)localObject4);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public PlayItemViewModel getViewModel()
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
    return onChangeViewModel((PlayItemViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((PlayItemViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable PlayItemViewModel paramPlayItemViewModel)
  {
    updateRegistration(0, paramPlayItemViewModel);
    this.mViewModel = paramPlayItemViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ItemPlayProjectBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */