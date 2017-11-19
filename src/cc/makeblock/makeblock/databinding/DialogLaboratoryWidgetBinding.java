package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cc.makeblock.makeblock.viewmodel.laboratory.WidgetDialogViewModel;

public class DialogLaboratoryWidgetBinding
  extends ViewDataBinding
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(7);
  @Nullable
  private static final SparseIntArray sViewsWithIds;
  @NonNull
  public final ImageView dismiss;
  private long mDirtyFlags = -1L;
  @Nullable
  private WidgetDialogViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @Nullable
  private final LabLayoutWidgetMoveBinding mboundView1;
  @Nullable
  private final LabLayoutWidgetLightBinding mboundView11;
  @Nullable
  private final LabLayoutWidgetSoundBinding mboundView12;
  @Nullable
  private final LabLayoutWidgetDetectorBinding mboundView13;
  @NonNull
  public final PercentRelativeLayout widgetDialogContainer;
  
  static
  {
    sIncludes.setIncludes(1, new String[] { "lab_layout_widget_move", "lab_layout_widget_light", "lab_layout_widget_sound", "lab_layout_widget_detector" }, new int[] { 2, 3, 4, 5 }, new int[] { 2131427442, 2131427441, 2131427443, 2131427440 });
    sViewsWithIds = new SparseIntArray();
    sViewsWithIds.put(2131296441, 6);
  }
  
  public DialogLaboratoryWidgetBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 7, sIncludes, sViewsWithIds);
    this.dismiss = ((ImageView)paramDataBindingComponent[6]);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((LabLayoutWidgetMoveBinding)paramDataBindingComponent[2]);
    setContainedBinding(this.mboundView1);
    this.mboundView11 = ((LabLayoutWidgetLightBinding)paramDataBindingComponent[3]);
    setContainedBinding(this.mboundView11);
    this.mboundView12 = ((LabLayoutWidgetSoundBinding)paramDataBindingComponent[4]);
    setContainedBinding(this.mboundView12);
    this.mboundView13 = ((LabLayoutWidgetDetectorBinding)paramDataBindingComponent[5]);
    setContainedBinding(this.mboundView13);
    this.widgetDialogContainer = ((PercentRelativeLayout)paramDataBindingComponent[1]);
    this.widgetDialogContainer.setTag(null);
    setRootTag(paramView);
    invalidateAll();
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/dialog_laboratory_widget_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new DialogLaboratoryWidgetBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427416, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static DialogLaboratoryWidgetBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (DialogLaboratoryWidgetBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427416, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(WidgetDialogViewModel paramWidgetDialogViewModel, int paramInt)
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
    for (;;)
    {
      try
      {
        l2 = this.mDirtyFlags;
        this.mDirtyFlags = 0L;
        m = 0;
        k = 0;
        i = 0;
        j = 0;
        WidgetDialogViewModel localWidgetDialogViewModel = this.mViewModel;
        int n = 0;
        l1 = l2;
        if ((0x3 & l2) != 0L)
        {
          m = n;
          if (localWidgetDialogViewModel != null) {
            m = localWidgetDialogViewModel.getWidgetCategory();
          }
          if (m == 0)
          {
            i = 1;
            if (m != 1) {
              break label368;
            }
            j = 1;
            if (m != 3) {
              break label373;
            }
            k = 1;
            if (m != 2) {
              break label378;
            }
            m = 1;
            l1 = l2;
            if ((0x3 & l2) != 0L)
            {
              if (i == 0) {
                break label384;
              }
              l1 = l2 | 0x80;
            }
            l2 = l1;
            if ((0x3 & l1) != 0L)
            {
              if (j == 0) {
                break label395;
              }
              l2 = l1 | 0x200;
            }
            l1 = l2;
            if ((0x3 & l2) != 0L)
            {
              if (k == 0) {
                break label406;
              }
              l1 = l2 | 0x20;
            }
            l2 = l1;
            if ((0x3 & l1) != 0L)
            {
              if (m == 0) {
                break label417;
              }
              l2 = l1 | 0x8;
            }
            if (i == 0) {
              break label428;
            }
            i = 0;
            if (j == 0) {
              break label434;
            }
            j = 0;
            if (k == 0) {
              break label440;
            }
            k = 0;
            if (m == 0) {
              break label446;
            }
            m = 0;
            l1 = l2;
          }
        }
        else
        {
          if ((0x3 & l1) != 0L)
          {
            this.mboundView1.setViewModel(localWidgetDialogViewModel);
            this.mboundView1.getRoot().setVisibility(i);
            this.mboundView11.setViewModel(localWidgetDialogViewModel);
            this.mboundView11.getRoot().setVisibility(j);
            this.mboundView12.setViewModel(localWidgetDialogViewModel);
            this.mboundView12.getRoot().setVisibility(m);
            this.mboundView13.setViewModel(localWidgetDialogViewModel);
            this.mboundView13.getRoot().setVisibility(k);
          }
          executeBindingsOn(this.mboundView1);
          executeBindingsOn(this.mboundView11);
          executeBindingsOn(this.mboundView12);
          executeBindingsOn(this.mboundView13);
          return;
        }
      }
      finally {}
      int i = 0;
      continue;
      label368:
      int j = 0;
      continue;
      label373:
      int k = 0;
      continue;
      label378:
      int m = 0;
      continue;
      label384:
      long l1 = l2 | 0x40;
      continue;
      label395:
      long l2 = l1 | 0x100;
      continue;
      label406:
      l1 = l2 | 0x10;
      continue;
      label417:
      l2 = l1 | 0x4;
      continue;
      label428:
      i = 8;
      continue;
      label434:
      j = 8;
      continue;
      label440:
      k = 8;
      continue;
      label446:
      m = 8;
      l1 = l2;
    }
  }
  
  @Nullable
  public WidgetDialogViewModel getViewModel()
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
      if ((!this.mboundView1.hasPendingBindings()) && (!this.mboundView11.hasPendingBindings()) && (!this.mboundView12.hasPendingBindings()) && (!this.mboundView13.hasPendingBindings())) {
        return false;
      }
    }
    finally {}
    return true;
  }
  
  public void invalidateAll()
  {
    try
    {
      this.mDirtyFlags = 2L;
      this.mboundView1.invalidateAll();
      this.mboundView11.invalidateAll();
      this.mboundView12.invalidateAll();
      this.mboundView13.invalidateAll();
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
    return onChangeViewModel((WidgetDialogViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((WidgetDialogViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable WidgetDialogViewModel paramWidgetDialogViewModel)
  {
    updateRegistration(0, paramWidgetDialogViewModel);
    this.mViewModel = paramWidgetDialogViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\DialogLaboratoryWidgetBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */