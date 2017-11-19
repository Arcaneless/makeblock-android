package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.databinding.generated.callback.OnHideListener;
import android.databinding.generated.callback.OnHideListener.Listener;
import android.databinding.generated.callback.OnShowListener;
import android.databinding.generated.callback.OnShowListener.Listener;
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
import cc.makeblock.makeblock.customview.MenuDrawerLayout;
import cc.makeblock.makeblock.customview.MenuDrawerLayout.OnHideListener;
import cc.makeblock.makeblock.customview.MenuDrawerLayout.OnShowListener;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.viewmodel.connect.ConnectActivityViewModel;
import java.util.List;

public class ActivityConnectBinding
  extends ViewDataBinding
  implements OnClickListener.Listener, OnHideListener.Listener, OnShowListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView connectClose;
  @NonNull
  public final ImageView handPhone;
  @Nullable
  private final View.OnClickListener mCallback94;
  @Nullable
  private final View.OnClickListener mCallback95;
  @Nullable
  private final MenuDrawerLayout.OnHideListener mCallback96;
  @Nullable
  private final MenuDrawerLayout.OnShowListener mCallback97;
  @Nullable
  private final View.OnClickListener mCallback98;
  @Nullable
  private final View.OnClickListener mCallback99;
  private long mDirtyFlags = -1L;
  @Nullable
  private ConnectActivityViewModel mViewModel;
  @NonNull
  private final FrameLayout mboundView0;
  @NonNull
  private final PercentRelativeLayout mboundView1;
  @NonNull
  private final AutoResizeTextView mboundView10;
  @NonNull
  private final ImageView mboundView11;
  @NonNull
  private final AutoResizeTextView mboundView12;
  @NonNull
  private final ImageView mboundView2;
  @NonNull
  private final AutoResizeTextView mboundView3;
  @NonNull
  private final MenuDrawerLayout mboundView4;
  @NonNull
  private final ImageView mboundView6;
  @NonNull
  private final PercentRelativeLayout mboundView7;
  @NonNull
  private final FitWidthTextView mboundView8;
  @NonNull
  private final PercentRelativeLayout mboundView9;
  
  static
  {
    sViewsWithIds.put(2131296480, 13);
  }
  
  public ActivityConnectBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 14, sIncludes, sViewsWithIds);
    this.connectClose = ((ImageView)paramDataBindingComponent[5]);
    this.connectClose.setTag(null);
    this.handPhone = ((ImageView)paramDataBindingComponent[13]);
    this.mboundView0 = ((FrameLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView10 = ((AutoResizeTextView)paramDataBindingComponent[10]);
    this.mboundView10.setTag(null);
    this.mboundView11 = ((ImageView)paramDataBindingComponent[11]);
    this.mboundView11.setTag(null);
    this.mboundView12 = ((AutoResizeTextView)paramDataBindingComponent[12]);
    this.mboundView12.setTag(null);
    this.mboundView2 = ((ImageView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((AutoResizeTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((MenuDrawerLayout)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView6 = ((ImageView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView7 = ((PercentRelativeLayout)paramDataBindingComponent[7]);
    this.mboundView7.setTag(null);
    this.mboundView8 = ((FitWidthTextView)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((PercentRelativeLayout)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    setRootTag(paramView);
    this.mCallback94 = new OnClickListener(this, 1);
    this.mCallback95 = new OnClickListener(this, 2);
    this.mCallback99 = new OnClickListener(this, 6);
    this.mCallback96 = new OnHideListener(this, 3);
    this.mCallback98 = new OnClickListener(this, 5);
    this.mCallback97 = new OnShowListener(this, 4);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityConnectBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityConnectBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_connect_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityConnectBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityConnectBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityConnectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427362, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityConnectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityConnectBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityConnectBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427362, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(ConnectActivityViewModel paramConnectActivityViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 8) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 44) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 65) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 80) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 59) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 23) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    if (paramInt == 24) {
      try
      {
        this.mDirtyFlags |= 0x80;
        return true;
      }
      finally {}
    }
    if (paramInt == 11) {
      try
      {
        this.mDirtyFlags |= 0x100;
        return true;
      }
      finally {}
    }
    if (paramInt == 7) {
      try
      {
        this.mDirtyFlags |= 0x200;
        return true;
      }
      finally {}
    }
    if (paramInt == 58) {
      try
      {
        this.mDirtyFlags |= 0x400;
        return true;
      }
      finally {}
    }
    if (paramInt == 12) {
      try
      {
        this.mDirtyFlags |= 0x800;
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
        paramView.leave();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.disconnect();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.showMenu();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.leave();
        return;
      }
    }
  }
  
  public final void _internalCallbackOnHide(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.onHideMenu();
      }
      return;
    }
  }
  
  public final void _internalCallbackOnShow(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.onShowMenu();
      }
      return;
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
        Object localObject5 = null;
        Object localObject3 = null;
        Object localObject4 = null;
        Object localObject6 = null;
        int i5 = 0;
        n = 0;
        int i10 = 0;
        int k = 0;
        boolean bool3 = false;
        boolean bool6 = false;
        int i9 = 0;
        int m = 0;
        int i8 = 0;
        int i1 = 0;
        boolean bool2 = false;
        int i11 = 0;
        int i2 = 0;
        int i6 = 0;
        i = 0;
        int i12 = 0;
        int i3 = 0;
        boolean bool4 = false;
        boolean bool5 = false;
        int i7 = 0;
        int i14 = 0;
        int i13 = 0;
        ConnectActivityViewModel localConnectActivityViewModel = this.mViewModel;
        long l3 = l2;
        int i4 = i13;
        if ((0x1FFF & l2) != 0L)
        {
          Object localObject1 = localObject3;
          if ((0x1011 & l2) != 0L)
          {
            localObject1 = localObject3;
            if (localConnectActivityViewModel != null) {
              localObject1 = localConnectActivityViewModel.getWaitAddItems();
            }
          }
          localObject3 = localObject6;
          if ((0x1001 & l2) != 0L)
          {
            localObject3 = localObject6;
            if (localConnectActivityViewModel != null) {
              localObject3 = localConnectActivityViewModel.getMenuHeaderView();
            }
          }
          int j = k;
          if ((0x1081 & l2) != 0L)
          {
            j = k;
            if (localConnectActivityViewModel != null) {
              j = localConnectActivityViewModel.getErrorTextId();
            }
          }
          boolean bool1 = bool6;
          if ((0x1005 & l2) != 0L)
          {
            bool1 = bool6;
            if (localConnectActivityViewModel != null) {
              bool1 = localConnectActivityViewModel.isNeedClearMenu();
            }
          }
          k = m;
          if ((0x1041 & l2) != 0L)
          {
            k = m;
            if (localConnectActivityViewModel != null) {
              k = localConnectActivityViewModel.getErrorLayoutVisibility();
            }
          }
          m = i1;
          if ((0x1801 & l2) != 0L)
          {
            m = i1;
            if (localConnectActivityViewModel != null) {
              m = localConnectActivityViewModel.getConnectingStateTextId();
            }
          }
          l1 = l2;
          if ((0x1003 & l2) != 0L)
          {
            if (localConnectActivityViewModel != null) {
              bool2 = localConnectActivityViewModel.isConnected();
            }
            l1 = l2;
            if ((0x1003 & l2) != 0L)
            {
              if (!bool2) {
                break label1005;
              }
              l1 = l2 | 0x4000 | 0x10000;
            }
            if (!bool2) {
              break label1020;
            }
            n = 8;
            if (!bool2) {
              break label1026;
            }
            i = 0;
          }
          i1 = i2;
          if ((0x1401 & l1) != 0L)
          {
            i1 = i2;
            if (localConnectActivityViewModel != null) {
              i1 = localConnectActivityViewModel.getRobotImageId();
            }
          }
          i2 = i3;
          if ((0x1021 & l1) != 0L)
          {
            i2 = i3;
            if (localConnectActivityViewModel != null) {
              i2 = localConnectActivityViewModel.getSearchButtonVisibility();
            }
          }
          bool2 = bool5;
          if ((0x1009 & l1) != 0L)
          {
            bool2 = bool5;
            if (localConnectActivityViewModel != null) {
              bool2 = localConnectActivityViewModel.isShowMenu();
            }
          }
          i3 = i14;
          if ((0x1101 & l1) != 0L)
          {
            i3 = i14;
            if (localConnectActivityViewModel != null) {
              i3 = localConnectActivityViewModel.getConnectingLayoutVisibility();
            }
          }
          l3 = l1;
          i4 = i13;
          i5 = n;
          i6 = i;
          i7 = i3;
          i8 = m;
          i9 = k;
          i10 = j;
          localObject4 = localObject3;
          bool3 = bool1;
          i11 = i1;
          i12 = i2;
          bool4 = bool2;
          localObject5 = localObject1;
          if ((0x1201 & l1) != 0L)
          {
            l3 = l1;
            i4 = i13;
            i5 = n;
            i6 = i;
            i7 = i3;
            i8 = m;
            i9 = k;
            i10 = j;
            localObject4 = localObject3;
            bool3 = bool1;
            i11 = i1;
            i12 = i2;
            bool4 = bool2;
            localObject5 = localObject1;
            if (localConnectActivityViewModel != null)
            {
              i4 = localConnectActivityViewModel.getCloseDeviceVisibility();
              localObject5 = localObject1;
              bool4 = bool2;
              i12 = i2;
              i11 = i1;
              bool3 = bool1;
              localObject4 = localObject3;
              i10 = j;
              i9 = k;
              i8 = m;
              i7 = i3;
              i6 = i;
              i5 = n;
              l3 = l1;
            }
          }
        }
        if ((0x1000 & l3) != 0L)
        {
          this.connectClose.setOnClickListener(this.mCallback98);
          this.mboundView2.setOnClickListener(this.mCallback94);
          this.mboundView3.setOnClickListener(this.mCallback95);
          this.mboundView4.setOnHide(this.mCallback96);
          this.mboundView4.setOnShow(this.mCallback97);
          this.mboundView6.setOnClickListener(this.mCallback99);
        }
        if ((0x1003 & l3) != 0L)
        {
          this.mboundView1.setVisibility(i6);
          this.mboundView4.setVisibility(i5);
        }
        if ((0x1201 & l3) != 0L) {
          this.mboundView10.setVisibility(i4);
        }
        if ((0x1401 & l3) != 0L) {
          ResourceDrawableBindings.setImageUri(this.mboundView11, i11);
        }
        if ((0x1801 & l3) != 0L) {
          this.mboundView12.setText(i8);
        }
        if ((0x1005 & l3) != 0L) {
          this.mboundView4.clearMenu(bool3);
        }
        if ((0x1001 & l3) != 0L) {
          this.mboundView4.setHeaderView((View)localObject4);
        }
        if ((0x1009 & l3) != 0L) {
          this.mboundView4.setRightMenuStatus(bool4);
        }
        if ((0x1011 & l3) != 0L) {
          this.mboundView4.setWaitAddViews((List)localObject5);
        }
        if ((0x1021 & l3) != 0L) {
          this.mboundView6.setVisibility(i12);
        }
        if ((0x1041 & l3) != 0L) {
          this.mboundView7.setVisibility(i9);
        }
        if ((0x1081 & l3) != 0L) {
          this.mboundView8.setText(i10);
        }
        if ((0x1101 & l3) != 0L) {
          this.mboundView9.setVisibility(i7);
        }
        return;
      }
      finally {}
      label1005:
      long l1 = l2 | 0x2000 | 0x8000;
      continue;
      label1020:
      int n = 0;
      continue;
      label1026:
      int i = 8;
    }
  }
  
  @Nullable
  public ConnectActivityViewModel getViewModel()
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
      this.mDirtyFlags = 4096L;
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
    return onChangeViewModel((ConnectActivityViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((ConnectActivityViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable ConnectActivityViewModel paramConnectActivityViewModel)
  {
    updateRegistration(0, paramConnectActivityViewModel);
    this.mViewModel = paramConnectActivityViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityConnectBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */