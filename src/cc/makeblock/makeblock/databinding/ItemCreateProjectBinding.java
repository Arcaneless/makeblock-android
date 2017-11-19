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
import android.widget.Button;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.PercentLinearLayout;
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.controller.create.CreateItemViewModel;

public class ItemCreateProjectBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final PercentRelativeLayout itemProjectBack;
  @NonNull
  public final PercentRelativeLayout itemProjectDelete;
  @NonNull
  public final Button itemProjectDismiss;
  @NonNull
  public final PercentRelativeLayout itemProjectFront;
  @NonNull
  public final PercentRelativeLayout itemProjectMenu;
  @NonNull
  public final PercentRelativeLayout itemProjectRename;
  @Nullable
  private final View.OnClickListener mCallback72;
  @Nullable
  private final View.OnClickListener mCallback73;
  @Nullable
  private final View.OnClickListener mCallback74;
  private long mDirtyFlags = -1L;
  @Nullable
  private CreateItemViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView3;
  @NonNull
  private final ImageView mboundView7;
  @NonNull
  private final PercentLinearLayout mboundView8;
  @NonNull
  private final AutoResizeTextView mboundView9;
  
  static
  {
    sViewsWithIds.put(2131296521, 11);
  }
  
  public ItemCreateProjectBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 12, sIncludes, sViewsWithIds);
    this.itemProjectBack = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.itemProjectBack.setTag(null);
    this.itemProjectDelete = ((PercentRelativeLayout)paramDataBindingComponent[5]);
    this.itemProjectDelete.setTag(null);
    this.itemProjectDismiss = ((Button)paramDataBindingComponent[11]);
    this.itemProjectFront = ((PercentRelativeLayout)paramDataBindingComponent[6]);
    this.itemProjectFront.setTag(null);
    this.itemProjectMenu = ((PercentRelativeLayout)paramDataBindingComponent[10]);
    this.itemProjectMenu.setTag(null);
    this.itemProjectRename = ((PercentRelativeLayout)paramDataBindingComponent[4]);
    this.itemProjectRename.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView7 = ((ImageView)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((PercentLinearLayout)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((AutoResizeTextView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    setRootTag(paramView);
    this.mCallback74 = new OnClickListener(this, 3);
    this.mCallback72 = new OnClickListener(this, 1);
    this.mCallback73 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static ItemCreateProjectBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemCreateProjectBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/item_create_project_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ItemCreateProjectBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ItemCreateProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemCreateProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427432, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ItemCreateProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ItemCreateProjectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ItemCreateProjectBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427432, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(CreateItemViewModel paramCreateItemViewModel, int paramInt)
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
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      CreateItemViewModel localCreateItemViewModel = this.mViewModel;
      if (localCreateItemViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        localCreateItemViewModel.onClick(paramView);
        return;
      }
      continue;
      localCreateItemViewModel = this.mViewModel;
      if (localCreateItemViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localCreateItemViewModel.rename(ViewTools.getActivity(paramView));
        return;
      }
      continue;
      localCreateItemViewModel = this.mViewModel;
      if (localCreateItemViewModel != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        ViewTools.getActivity(paramView);
        localCreateItemViewModel.delete(ViewTools.getActivity(paramView));
        return;
      }
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
        int k = 0;
        i = 0;
        Object localObject1 = null;
        Object localObject3 = null;
        Object localObject6 = null;
        boolean bool2 = false;
        Object localObject5 = null;
        boolean bool1 = false;
        int m = 0;
        j = 0;
        CreateItemViewModel localCreateItemViewModel = this.mViewModel;
        long l3 = l2;
        Object localObject4 = localObject5;
        if ((0x7 & l2) != 0L)
        {
          l1 = l2;
          if ((0x5 & l2) != 0L)
          {
            localObject1 = localObject6;
            if (localCreateItemViewModel != null)
            {
              localObject1 = localCreateItemViewModel.getImageURI();
              bool2 = localCreateItemViewModel.getTextBarVisibility();
              bool1 = localCreateItemViewModel.getMenuVisibility();
            }
            l1 = l2;
            if ((0x5 & l2) != 0L)
            {
              if (!bool2) {
                break label439;
              }
              l1 = l2 | 0x40;
            }
            l2 = l1;
            if ((0x5 & l1) != 0L)
            {
              if (!bool1) {
                break label450;
              }
              l2 = l1 | 0x10;
            }
            if (!bool2) {
              break label461;
            }
            j = 0;
            if (!bool1) {
              break label467;
            }
            i = 0;
            l1 = l2;
          }
          l3 = l1;
          localObject3 = localObject1;
          k = i;
          localObject4 = localObject5;
          m = j;
          if (localCreateItemViewModel != null)
          {
            localObject4 = localCreateItemViewModel.getProjectItemName();
            m = j;
            k = i;
            localObject3 = localObject1;
            l3 = l1;
          }
        }
        if ((0x4 & l3) != 0L)
        {
          if (getBuildSdkInt() >= 12)
          {
            this.itemProjectBack.setCameraDistance(this.itemProjectBack.getResources().getInteger(2131361803));
            this.itemProjectFront.setCameraDistance(this.itemProjectFront.getResources().getInteger(2131361803));
          }
          this.itemProjectDelete.setOnClickListener(this.mCallback73);
          this.itemProjectFront.setOnClickListener(this.mCallback74);
          this.itemProjectRename.setOnClickListener(this.mCallback72);
        }
        if ((0x5 & l3) != 0L)
        {
          this.itemProjectMenu.setVisibility(k);
          GlideBindings.setRoundImageUri(this.mboundView2, (Uri)localObject3, this.mboundView2.getResources().getDimension(2131099793));
          GlideBindings.setRoundImageUriWithPlaceHolder(this.mboundView7, (Uri)localObject3, getDrawableFromResource(this.mboundView7, 2131165606), this.mboundView7.getResources().getDimension(2131099793));
          this.mboundView8.setVisibility(m);
        }
        if ((0x7 & l3) != 0L)
        {
          TextViewBindingAdapter.setText(this.mboundView3, (CharSequence)localObject4);
          TextViewBindingAdapter.setText(this.mboundView9, (CharSequence)localObject4);
        }
        return;
      }
      finally {}
      label439:
      long l1 = l2 | 0x20;
      continue;
      label450:
      long l2 = l1 | 0x8;
      continue;
      label461:
      int j = 8;
      continue;
      label467:
      int i = 8;
      l1 = l2;
    }
  }
  
  @Nullable
  public CreateItemViewModel getViewModel()
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
    return onChangeViewModel((CreateItemViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((CreateItemViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable CreateItemViewModel paramCreateItemViewModel)
  {
    updateRegistration(0, paramCreateItemViewModel);
    this.mViewModel = paramCreateItemViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ItemCreateProjectBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */