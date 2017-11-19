package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableFloat;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.ViewBindingAdapter;
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
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.engine.utils.ViewTools;
import cc.makeblock.makeblock.viewmodel.choosedevice.ChooseDeviceItemViewModel;

public class FragmentDeviceBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = null;
  @NonNull
  public final ImageView chooseDeviceIcon;
  @Nullable
  private final View.OnClickListener mCallback100;
  private long mDirtyFlags = -1L;
  @Nullable
  private ChooseDeviceItemViewModel mViewModel;
  @NonNull
  private final FrameLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView4;
  @NonNull
  private final FitWidthTextView mboundView5;
  @NonNull
  private final FitWidthTextView mboundView6;
  
  public FragmentDeviceBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 2);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.chooseDeviceIcon = ((ImageView)paramDataBindingComponent[3]);
    this.chooseDeviceIcon.setTag(null);
    this.mboundView0 = ((FrameLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView4 = ((AutoResizeTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((FitWidthTextView)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((FitWidthTextView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    setRootTag(paramView);
    this.mCallback100 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentDeviceBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentDeviceBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_device_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentDeviceBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427423, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentDeviceBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentDeviceBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427423, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(ChooseDeviceItemViewModel paramChooseDeviceItemViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 22) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 19) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 21) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 16) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  private boolean onChangeViewModelAlpha(ObservableFloat paramObservableFloat, int paramInt)
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
    ChooseDeviceItemViewModel localChooseDeviceItemViewModel = this.mViewModel;
    if (localChooseDeviceItemViewModel != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0)
      {
        ViewTools.getActivity(paramView);
        localChooseDeviceItemViewModel.onSelected(ViewTools.getActivity(paramView));
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
      float f2 = 0.0F;
      int m = 0;
      int j = 0;
      Object localObject6 = null;
      Object localObject3 = null;
      Object localObject5 = null;
      Object localObject4 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      int k = 0;
      int n = 0;
      ObservableFloat localObservableFloat = null;
      ChooseDeviceItemViewModel localChooseDeviceItemViewModel = this.mViewModel;
      float f1 = f2;
      if ((0x7F & l) != 0L)
      {
        int i = j;
        if ((0x46 & l) != 0L)
        {
          i = j;
          if (localChooseDeviceItemViewModel != null) {
            i = localChooseDeviceItemViewModel.getDevicePic();
          }
        }
        Object localObject1 = localObject3;
        if ((0x52 & l) != 0L)
        {
          localObject1 = localObject3;
          if (localChooseDeviceItemViewModel != null) {
            localObject1 = localChooseDeviceItemViewModel.getDeviceName();
          }
        }
        localObject3 = localObject4;
        if ((0x62 & l) != 0L)
        {
          localObject3 = localObject4;
          if (localChooseDeviceItemViewModel != null) {
            localObject3 = localChooseDeviceItemViewModel.getDescription();
          }
        }
        localObject4 = localObject8;
        if ((0x42 & l) != 0L)
        {
          localObject4 = localObject8;
          if (localChooseDeviceItemViewModel != null) {
            localObject4 = localChooseDeviceItemViewModel.getShadeDrawable();
          }
        }
        j = n;
        if ((0x4A & l) != 0L)
        {
          j = n;
          if (localChooseDeviceItemViewModel != null) {
            j = localChooseDeviceItemViewModel.getDeviceIcon();
          }
        }
        f1 = f2;
        localObject5 = localObject3;
        k = j;
        localObject6 = localObject1;
        m = i;
        localObject7 = localObject4;
        if ((0x43 & l) != 0L)
        {
          if (localChooseDeviceItemViewModel != null) {
            localObservableFloat = localChooseDeviceItemViewModel.alpha;
          }
          updateRegistration(0, localObservableFloat);
          f1 = f2;
          localObject5 = localObject3;
          k = j;
          localObject6 = localObject1;
          m = i;
          localObject7 = localObject4;
          if (localObservableFloat != null)
          {
            f1 = localObservableFloat.get();
            localObject7 = localObject4;
            m = i;
            localObject6 = localObject1;
            k = j;
            localObject5 = localObject3;
          }
        }
      }
      if (((0x43 & l) != 0L) && (getBuildSdkInt() >= 11))
      {
        this.chooseDeviceIcon.setAlpha(f1);
        this.mboundView1.setAlpha(f1);
        this.mboundView2.setAlpha(f1);
        this.mboundView4.setAlpha(f1);
        this.mboundView5.setAlpha(f1);
        this.mboundView6.setAlpha(f1);
      }
      if ((0x4A & l) != 0L) {
        ResourceDrawableBindings.setImageUri(this.chooseDeviceIcon, k);
      }
      if ((0x42 & l) != 0L) {
        ViewBindingAdapter.setBackground(this.mboundView1, (Drawable)localObject7);
      }
      if ((0x46 & l) != 0L) {
        ResourceDrawableBindings.setImageUri(this.mboundView2, m);
      }
      if ((0x52 & l) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView4, (CharSequence)localObject6);
      }
      if ((0x62 & l) != 0L) {
        TextViewBindingAdapter.setText(this.mboundView5, (CharSequence)localObject5);
      }
      if ((0x40 & l) != 0L) {
        this.mboundView6.setOnClickListener(this.mCallback100);
      }
      return;
    }
    finally {}
  }
  
  @Nullable
  public ChooseDeviceItemViewModel getViewModel()
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
      this.mDirtyFlags = 64L;
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
    case 0: 
      return onChangeViewModelAlpha((ObservableFloat)paramObject, paramInt2);
    }
    return onChangeViewModel((ChooseDeviceItemViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((ChooseDeviceItemViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable ChooseDeviceItemViewModel paramChooseDeviceItemViewModel)
  {
    updateRegistration(1, paramChooseDeviceItemViewModel);
    this.mViewModel = paramChooseDeviceItemViewModel;
    try
    {
      this.mDirtyFlags |= 0x2;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentDeviceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */