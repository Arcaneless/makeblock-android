package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.DynamicUtil;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.customview.AdapterConstraintLayout;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.UpdateProgramViewModel;

public class FragmentCodeyProgramUpdateBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @Nullable
  private final View.OnClickListener mCallback130;
  @Nullable
  private final View.OnClickListener mCallback131;
  private long mDirtyFlags = -1L;
  @Nullable
  private UpdateProgramViewModel mViewModel;
  @NonNull
  private final AdapterConstraintLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final TextView mboundView3;
  @NonNull
  private final TextView mboundView4;
  @NonNull
  public final ImageView updateBg;
  @NonNull
  public final ImageView updatePic;
  
  static
  {
    sViewsWithIds.put(2131296758, 5);
  }
  
  public FragmentCodeyProgramUpdateBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 4);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 6, sIncludes, sViewsWithIds);
    this.mboundView0 = ((AdapterConstraintLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView3 = ((TextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((TextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.updateBg = ((ImageView)paramDataBindingComponent[5]);
    this.updatePic = ((ImageView)paramDataBindingComponent[2]);
    this.updatePic.setTag(null);
    setRootTag(paramView);
    this.mCallback130 = new OnClickListener(this, 1);
    this.mCallback131 = new OnClickListener(this, 2);
    invalidateAll();
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/fragment_codey_program_update_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new FragmentCodeyProgramUpdateBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427422, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static FragmentCodeyProgramUpdateBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (FragmentCodeyProgramUpdateBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427422, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(UpdateProgramViewModel paramUpdateProgramViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x8;
      }
      finally {}
    } else {
      return false;
    }
    return true;
  }
  
  private boolean onChangeViewModelIsFail(ObservableBoolean paramObservableBoolean, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x2;
      }
      finally {}
    } else {
      return false;
    }
    return true;
  }
  
  private boolean onChangeViewModelUpdatePic(ObservableField<Integer> paramObservableField, int paramInt)
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
  
  private boolean onChangeViewModelUpdateTip(ObservableField<String> paramObservableField, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 0x4;
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
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.close();
        return;
      }
      continue;
      paramView = this.mViewModel;
      if (paramView != null) {}
      for (paramInt = 1; paramInt != 0; paramInt = 0)
      {
        paramView.restartInstallProgram();
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
        int k = 0;
        int j = 0;
        Object localObject7 = null;
        Object localObject6 = null;
        Object localObject3 = null;
        int m = 0;
        i = 0;
        Object localObject4 = null;
        Object localObject5 = null;
        boolean bool = false;
        UpdateProgramViewModel localUpdateProgramViewModel = this.mViewModel;
        long l3 = l2;
        Object localObject1 = localObject4;
        if ((0x1F & l2) != 0L)
        {
          if ((0x19 & l2) != 0L)
          {
            localObject1 = localObject7;
            if (localUpdateProgramViewModel != null) {
              localObject1 = localUpdateProgramViewModel.updatePic;
            }
            updateRegistration(0, (Observable)localObject1);
            if (localObject1 != null) {
              localObject3 = (Integer)((ObservableField)localObject1).get();
            }
            j = DynamicUtil.safeUnbox((Integer)localObject3);
          }
          l1 = l2;
          if ((0x1A & l2) != 0L)
          {
            localObject1 = localObject6;
            if (localUpdateProgramViewModel != null) {
              localObject1 = localUpdateProgramViewModel.isFail;
            }
            updateRegistration(1, (Observable)localObject1);
            if (localObject1 != null) {
              bool = ((ObservableBoolean)localObject1).get();
            }
            l1 = l2;
            if ((0x1A & l2) != 0L)
            {
              if (!bool) {
                break label403;
              }
              l1 = l2 | 0x40;
            }
            if (!bool) {
              break label414;
            }
            i = 0;
          }
          k = j;
          l3 = l1;
          m = i;
          localObject1 = localObject4;
          if ((0x1C & l1) != 0L)
          {
            localObject3 = localObject5;
            if (localUpdateProgramViewModel != null) {
              localObject3 = localUpdateProgramViewModel.updateTip;
            }
            updateRegistration(2, (Observable)localObject3);
            k = j;
            l3 = l1;
            m = i;
            localObject1 = localObject4;
            if (localObject3 != null)
            {
              localObject1 = (String)((ObservableField)localObject3).get();
              m = i;
              l3 = l1;
              k = j;
            }
          }
        }
        if ((0x10 & l3) != 0L)
        {
          this.mboundView1.setOnClickListener(this.mCallback130);
          this.mboundView4.setOnClickListener(this.mCallback131);
        }
        if ((0x1A & l3) != 0L)
        {
          this.mboundView1.setVisibility(m);
          this.mboundView4.setVisibility(m);
        }
        if ((0x1C & l3) != 0L) {
          TextViewBindingAdapter.setText(this.mboundView3, (CharSequence)localObject1);
        }
        if ((0x19 & l3) != 0L) {
          ResourceDrawableBindings.setImageBackground(this.updatePic, k);
        }
        return;
      }
      finally {}
      label403:
      long l1 = l2 | 0x20;
      continue;
      label414:
      int i = 8;
    }
  }
  
  @Nullable
  public UpdateProgramViewModel getViewModel()
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
      this.mDirtyFlags = 16L;
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
      return onChangeViewModelUpdatePic((ObservableField)paramObject, paramInt2);
    case 1: 
      return onChangeViewModelIsFail((ObservableBoolean)paramObject, paramInt2);
    case 2: 
      return onChangeViewModelUpdateTip((ObservableField)paramObject, paramInt2);
    }
    return onChangeViewModel((UpdateProgramViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((UpdateProgramViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable UpdateProgramViewModel paramUpdateProgramViewModel)
  {
    updateRegistration(3, paramUpdateProgramViewModel);
    this.mViewModel = paramUpdateProgramViewModel;
    try
    {
      this.mDirtyFlags |= 0x8;
      notifyPropertyChanged(79);
      super.requestRebind();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\FragmentCodeyProgramUpdateBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */