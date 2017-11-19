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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.viewmodel.WidgetEditPopupViewModel;

public class PopupWidgetEditBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @NonNull
  public final ImageView arrowBottom;
  @NonNull
  public final ImageView arrowLeft;
  @NonNull
  public final ImageView arrowRight;
  @NonNull
  public final ImageView arrowTop;
  @NonNull
  public final ImageView imgRename;
  @NonNull
  public final PercentRelativeLayout layoutCode;
  @NonNull
  public final LinearLayout layoutContent;
  @NonNull
  public final PercentRelativeLayout layoutDelete;
  @NonNull
  public final PercentRelativeLayout layoutPort;
  @NonNull
  public final PercentRelativeLayout layoutRename;
  private long mDirtyFlags = -1L;
  @Nullable
  private WidgetEditPopupViewModel mViewModel;
  private OnClickListenerImpl mViewModelDeleteAndroidViewViewOnClickListener;
  private OnClickListenerImpl2 mViewModelEnterBlocklyAndroidViewViewOnClickListener;
  private OnClickListenerImpl1 mViewModelRenameAndroidViewViewOnClickListener;
  private OnClickListenerImpl3 mViewModelSelectPortAndroidViewViewOnClickListener;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  public final AutoResizeTextView tvWidgetName;
  
  static
  {
    sViewsWithIds.put(2131296545, 10);
    sViewsWithIds.put(2131296508, 11);
  }
  
  public PopupWidgetEditBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 12, sIncludes, sViewsWithIds);
    this.arrowBottom = ((ImageView)paramDataBindingComponent[9]);
    this.arrowBottom.setTag(null);
    this.arrowLeft = ((ImageView)paramDataBindingComponent[7]);
    this.arrowLeft.setTag(null);
    this.arrowRight = ((ImageView)paramDataBindingComponent[6]);
    this.arrowRight.setTag(null);
    this.arrowTop = ((ImageView)paramDataBindingComponent[8]);
    this.arrowTop.setTag(null);
    this.imgRename = ((ImageView)paramDataBindingComponent[11]);
    this.layoutCode = ((PercentRelativeLayout)paramDataBindingComponent[4]);
    this.layoutCode.setTag(null);
    this.layoutContent = ((LinearLayout)paramDataBindingComponent[10]);
    this.layoutDelete = ((PercentRelativeLayout)paramDataBindingComponent[5]);
    this.layoutDelete.setTag(null);
    this.layoutPort = ((PercentRelativeLayout)paramDataBindingComponent[3]);
    this.layoutPort.setTag(null);
    this.layoutRename = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.layoutRename.setTag(null);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.tvWidgetName = ((AutoResizeTextView)paramDataBindingComponent[2]);
    this.tvWidgetName.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static PopupWidgetEditBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static PopupWidgetEditBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/popup_widget_edit_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new PopupWidgetEditBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static PopupWidgetEditBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static PopupWidgetEditBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427527, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static PopupWidgetEditBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static PopupWidgetEditBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (PopupWidgetEditBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427527, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(WidgetEditPopupViewModel paramWidgetEditPopupViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 43) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 85) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 53) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 55) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 15) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 1) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  protected void executeBindings()
  {
    for (;;)
    {
      Object localObject7;
      Object localObject6;
      Object localObject5;
      Object localObject4;
      try
      {
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        i9 = 0;
        int i12 = 0;
        boolean bool1 = false;
        localObject8 = null;
        localObject7 = null;
        localObject10 = null;
        Object localObject14 = null;
        boolean bool4 = false;
        m = 0;
        i8 = 0;
        j = 0;
        i6 = 0;
        k = 0;
        localObject12 = null;
        Object localObject13 = null;
        int i11 = 0;
        i7 = 0;
        i2 = 0;
        localObject9 = null;
        Object localObject15 = null;
        localObject11 = null;
        Object localObject16 = null;
        i4 = 0;
        n = 0;
        i5 = 0;
        i1 = 0;
        i10 = 0;
        i = 0;
        boolean bool3 = false;
        boolean bool2 = false;
        WidgetEditPopupViewModel localWidgetEditPopupViewModel = this.mViewModel;
        l1 = l2;
        i3 = i11;
        if ((0xFF & l2) != 0L)
        {
          l1 = l2;
          if ((0x89 & l2) != 0L)
          {
            if (localWidgetEditPopupViewModel != null) {
              bool1 = localWidgetEditPopupViewModel.isPortSelectable();
            }
            l1 = l2;
            if ((0x89 & l2) != 0L)
            {
              if (!bool1) {
                break label1098;
              }
              l1 = l2 | 0x800000;
            }
            if (!bool1) {
              break label1109;
            }
            i = 0;
          }
          localObject6 = localObject7;
          localObject5 = localObject15;
          localObject4 = localObject14;
          Object localObject1 = localObject16;
          if ((0x81 & l1) != 0L)
          {
            localObject6 = localObject7;
            localObject5 = localObject15;
            localObject4 = localObject14;
            localObject1 = localObject16;
            if (localWidgetEditPopupViewModel != null)
            {
              if (this.mViewModelDeleteAndroidViewViewOnClickListener != null) {
                break label1115;
              }
              localObject1 = new OnClickListenerImpl();
              this.mViewModelDeleteAndroidViewViewOnClickListener = ((OnClickListenerImpl)localObject1);
              localObject6 = ((OnClickListenerImpl)localObject1).setValue(localWidgetEditPopupViewModel);
              if (this.mViewModelRenameAndroidViewViewOnClickListener != null) {
                break label1124;
              }
              localObject1 = new OnClickListenerImpl1();
              this.mViewModelRenameAndroidViewViewOnClickListener = ((OnClickListenerImpl1)localObject1);
              localObject4 = ((OnClickListenerImpl1)localObject1).setValue(localWidgetEditPopupViewModel);
              if (this.mViewModelEnterBlocklyAndroidViewViewOnClickListener != null) {
                break label1133;
              }
              localObject1 = new OnClickListenerImpl2();
              this.mViewModelEnterBlocklyAndroidViewViewOnClickListener = ((OnClickListenerImpl2)localObject1);
              localObject5 = ((OnClickListenerImpl2)localObject1).setValue(localWidgetEditPopupViewModel);
              if (this.mViewModelSelectPortAndroidViewViewOnClickListener != null) {
                break label1142;
              }
              localObject1 = new OnClickListenerImpl3();
              this.mViewModelSelectPortAndroidViewViewOnClickListener = ((OnClickListenerImpl3)localObject1);
              localObject1 = ((OnClickListenerImpl3)localObject1).setValue(localWidgetEditPopupViewModel);
            }
          }
          l2 = l1;
          if ((0xA1 & l1) != 0L)
          {
            bool1 = bool4;
            if (localWidgetEditPopupViewModel != null) {
              bool1 = localWidgetEditPopupViewModel.isDeletable();
            }
            l2 = l1;
            if ((0xA1 & l1) != 0L)
            {
              if (!bool1) {
                break label1151;
              }
              l2 = l1 | 0x800;
            }
            if (!bool1) {
              break label1162;
            }
            j = 0;
          }
          l1 = l2;
          if ((0xC1 & l2) != 0L)
          {
            if (localWidgetEditPopupViewModel != null) {
              m = localWidgetEditPopupViewModel.getArrowDirection();
            }
            if (m != 0) {
              break label1168;
            }
            n = 1;
            if (m != 3) {
              break label1174;
            }
            i1 = 1;
            if (m != 1) {
              break label1180;
            }
            k = 1;
            if (m != 2) {
              break label1185;
            }
            m = 1;
            l1 = l2;
            if ((0xC1 & l2) != 0L)
            {
              if (n == 0) {
                break label1191;
              }
              l1 = l2 | 0x80000;
            }
            l2 = l1;
            if ((0xC1 & l1) != 0L)
            {
              if (i1 == 0) {
                break label1202;
              }
              l2 = l1 | 0x20000;
            }
            l1 = l2;
            if ((0xC1 & l2) != 0L)
            {
              if (k == 0) {
                break label1213;
              }
              l1 = l2 | 0x200000;
            }
            l2 = l1;
            if ((0xC1 & l1) != 0L)
            {
              if (m == 0) {
                break label1224;
              }
              l2 = l1 | 0x2000;
            }
            if (n == 0) {
              break label1235;
            }
            n = 0;
            if (i1 == 0) {
              break label1241;
            }
            i2 = 0;
            if (k == 0) {
              break label1247;
            }
            i1 = 0;
            if (m == 0) {
              break label1253;
            }
            k = 0;
            l1 = l2;
          }
          localObject7 = localObject13;
          if ((0x85 & l1) != 0L)
          {
            localObject7 = localObject13;
            if (localWidgetEditPopupViewModel != null) {
              localObject7 = localWidgetEditPopupViewModel.getWidgetPopupTitle();
            }
          }
          l2 = l1;
          m = i12;
          if ((0x83 & l1) != 0L)
          {
            bool1 = bool3;
            if (localWidgetEditPopupViewModel != null) {
              bool1 = localWidgetEditPopupViewModel.isNameChangeable();
            }
            l2 = l1;
            if ((0x83 & l1) != 0L)
            {
              if (!bool1) {
                break label1262;
              }
              l2 = l1 | 0x200;
            }
            if (!bool1) {
              break label1273;
            }
            m = 0;
          }
          l1 = l2;
          i4 = n;
          i5 = i1;
          i6 = k;
          i7 = i2;
          i8 = j;
          localObject8 = localObject6;
          localObject9 = localObject5;
          i9 = m;
          i10 = i;
          i3 = i11;
          localObject10 = localObject4;
          localObject11 = localObject1;
          localObject12 = localObject7;
          if ((0x91 & l2) != 0L)
          {
            bool1 = bool2;
            if (localWidgetEditPopupViewModel != null) {
              bool1 = localWidgetEditPopupViewModel.isProgrammable();
            }
            l1 = l2;
            if ((0x91 & l2) != 0L)
            {
              if (!bool1) {
                break label1280;
              }
              l1 = l2 | 0x8000;
            }
            if (!bool1) {
              break label1291;
            }
            i3 = 0;
            localObject12 = localObject7;
            localObject11 = localObject1;
            localObject10 = localObject4;
            i10 = i;
            i9 = m;
            localObject9 = localObject5;
            localObject8 = localObject6;
            i8 = j;
            i7 = i2;
            i6 = k;
            i5 = i1;
            i4 = n;
          }
        }
        if ((0xC1 & l1) != 0L)
        {
          this.arrowBottom.setVisibility(i6);
          this.arrowLeft.setVisibility(i7);
          this.arrowRight.setVisibility(i5);
          this.arrowTop.setVisibility(i4);
        }
        if ((0x81 & l1) != 0L)
        {
          this.layoutCode.setOnClickListener((View.OnClickListener)localObject9);
          this.layoutDelete.setOnClickListener((View.OnClickListener)localObject8);
          this.layoutPort.setOnClickListener((View.OnClickListener)localObject11);
          this.layoutRename.setOnClickListener((View.OnClickListener)localObject10);
        }
        if ((0x91 & l1) != 0L) {
          this.layoutCode.setVisibility(i3);
        }
        if ((0xA1 & l1) != 0L) {
          this.layoutDelete.setVisibility(i8);
        }
        if ((0x89 & l1) != 0L) {
          this.layoutPort.setVisibility(i10);
        }
        if ((0x83 & l1) != 0L) {
          this.layoutRename.setVisibility(i9);
        }
        if ((0x85 & l1) != 0L) {
          TextViewBindingAdapter.setText(this.tvWidgetName, (CharSequence)localObject12);
        }
        return;
      }
      finally {}
      label1098:
      long l1 = l2 | 0x400000;
      continue;
      label1109:
      int i = 8;
      continue;
      label1115:
      Object localObject3 = this.mViewModelDeleteAndroidViewViewOnClickListener;
      continue;
      label1124:
      localObject3 = this.mViewModelRenameAndroidViewViewOnClickListener;
      continue;
      label1133:
      localObject3 = this.mViewModelEnterBlocklyAndroidViewViewOnClickListener;
      continue;
      label1142:
      localObject3 = this.mViewModelSelectPortAndroidViewViewOnClickListener;
      continue;
      label1151:
      long l2 = l1 | 0x400;
      continue;
      label1162:
      int j = 8;
      continue;
      label1168:
      int n = 0;
      continue;
      label1174:
      int i1 = 0;
      continue;
      label1180:
      int k = 0;
      continue;
      label1185:
      int m = 0;
      continue;
      label1191:
      l1 = l2 | 0x40000;
      continue;
      label1202:
      l2 = l1 | 0x10000;
      continue;
      label1213:
      l1 = l2 | 0x100000;
      continue;
      label1224:
      l2 = l1 | 0x1000;
      continue;
      label1235:
      n = 4;
      continue;
      label1241:
      int i2 = 4;
      continue;
      label1247:
      i1 = 4;
      continue;
      label1253:
      k = 4;
      l1 = l2;
      continue;
      label1262:
      l2 = l1 | 0x100;
      continue;
      label1273:
      m = 8;
      continue;
      label1280:
      l1 = l2 | 0x4000;
      continue;
      label1291:
      int i3 = 8;
      int i4 = n;
      int i5 = i1;
      int i6 = k;
      int i7 = i2;
      int i8 = j;
      Object localObject8 = localObject6;
      Object localObject9 = localObject5;
      int i9 = m;
      int i10 = i;
      Object localObject10 = localObject4;
      Object localObject11 = localObject3;
      Object localObject12 = localObject7;
    }
  }
  
  @Nullable
  public WidgetEditPopupViewModel getViewModel()
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
      this.mDirtyFlags = 128L;
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
    return onChangeViewModel((WidgetEditPopupViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((WidgetEditPopupViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable WidgetEditPopupViewModel paramWidgetEditPopupViewModel)
  {
    updateRegistration(0, paramWidgetEditPopupViewModel);
    this.mViewModel = paramWidgetEditPopupViewModel;
    try
    {
      this.mDirtyFlags |= 1L;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
  
  public static class OnClickListenerImpl
    implements View.OnClickListener
  {
    private WidgetEditPopupViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.delete(paramView);
    }
    
    public OnClickListenerImpl setValue(WidgetEditPopupViewModel paramWidgetEditPopupViewModel)
    {
      this.value = paramWidgetEditPopupViewModel;
      OnClickListenerImpl localOnClickListenerImpl = this;
      if (paramWidgetEditPopupViewModel == null) {
        localOnClickListenerImpl = null;
      }
      return localOnClickListenerImpl;
    }
  }
  
  public static class OnClickListenerImpl1
    implements View.OnClickListener
  {
    private WidgetEditPopupViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.rename(paramView);
    }
    
    public OnClickListenerImpl1 setValue(WidgetEditPopupViewModel paramWidgetEditPopupViewModel)
    {
      this.value = paramWidgetEditPopupViewModel;
      OnClickListenerImpl1 localOnClickListenerImpl1 = this;
      if (paramWidgetEditPopupViewModel == null) {
        localOnClickListenerImpl1 = null;
      }
      return localOnClickListenerImpl1;
    }
  }
  
  public static class OnClickListenerImpl2
    implements View.OnClickListener
  {
    private WidgetEditPopupViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.enterBlockly(paramView);
    }
    
    public OnClickListenerImpl2 setValue(WidgetEditPopupViewModel paramWidgetEditPopupViewModel)
    {
      this.value = paramWidgetEditPopupViewModel;
      OnClickListenerImpl2 localOnClickListenerImpl2 = this;
      if (paramWidgetEditPopupViewModel == null) {
        localOnClickListenerImpl2 = null;
      }
      return localOnClickListenerImpl2;
    }
  }
  
  public static class OnClickListenerImpl3
    implements View.OnClickListener
  {
    private WidgetEditPopupViewModel value;
    
    public void onClick(View paramView)
    {
      this.value.selectPort(paramView);
    }
    
    public OnClickListenerImpl3 setValue(WidgetEditPopupViewModel paramWidgetEditPopupViewModel)
    {
      this.value = paramWidgetEditPopupViewModel;
      OnClickListenerImpl3 localOnClickListenerImpl3 = this;
      if (paramWidgetEditPopupViewModel == null) {
        localOnClickListenerImpl3 = null;
      }
      return localOnClickListenerImpl3;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\PopupWidgetEditBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */