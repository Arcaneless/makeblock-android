package cc.makeblock.makeblock.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnClickListener.Listener;
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
import android.widget.ScrollView;
import cc.makeblock.makeblock.customview.FitWidthTextView;
import cc.makeblock.makeblock.customview.SpeakerAnimationView;
import cc.makeblock.makeblock.customview.ToolBarLayout;
import cc.makeblock.makeblock.customview.bindingadapter.ResourceDrawableBindings;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerSpeakerViewModel;

public class ActivityRangerSpeakerBinding
  extends ViewDataBinding
  implements OnClickListener.Listener
{
  @Nullable
  private static final ViewDataBinding.IncludedLayouts sIncludes = null;
  @Nullable
  private static final SparseIntArray sViewsWithIds = new SparseIntArray();
  @Nullable
  private final View.OnClickListener mCallback43;
  private long mDirtyFlags = -1L;
  @Nullable
  private RangerSpeakerViewModel mViewModel;
  @NonNull
  private final PercentRelativeLayout mboundView0;
  @NonNull
  private final ImageView mboundView1;
  @NonNull
  private final FitWidthTextView mboundView10;
  @NonNull
  private final SpeakerAnimationView mboundView2;
  @NonNull
  private final FitWidthTextView mboundView3;
  @NonNull
  private final FitWidthTextView mboundView4;
  @NonNull
  private final FitWidthTextView mboundView5;
  @NonNull
  private final FitWidthTextView mboundView6;
  @NonNull
  private final ScrollView mboundView8;
  @NonNull
  private final FitWidthTextView mboundView9;
  @NonNull
  public final Button speakerButton;
  @NonNull
  public final FitWidthTextView speakerUnrecognizableTitle;
  @NonNull
  public final ToolBarLayout toolBar;
  
  static
  {
    sViewsWithIds.put(2131296728, 12);
  }
  
  public ActivityRangerSpeakerBinding(@NonNull DataBindingComponent paramDataBindingComponent, @NonNull View paramView)
  {
    super(paramDataBindingComponent, paramView, 1);
    paramDataBindingComponent = mapBindings(paramDataBindingComponent, paramView, 13, sIncludes, sViewsWithIds);
    this.mboundView0 = ((PercentRelativeLayout)paramDataBindingComponent[0]);
    this.mboundView0.setTag(null);
    this.mboundView1 = ((ImageView)paramDataBindingComponent[1]);
    this.mboundView1.setTag(null);
    this.mboundView10 = ((FitWidthTextView)paramDataBindingComponent[10]);
    this.mboundView10.setTag(null);
    this.mboundView2 = ((SpeakerAnimationView)paramDataBindingComponent[2]);
    this.mboundView2.setTag(null);
    this.mboundView3 = ((FitWidthTextView)paramDataBindingComponent[3]);
    this.mboundView3.setTag(null);
    this.mboundView4 = ((FitWidthTextView)paramDataBindingComponent[4]);
    this.mboundView4.setTag(null);
    this.mboundView5 = ((FitWidthTextView)paramDataBindingComponent[5]);
    this.mboundView5.setTag(null);
    this.mboundView6 = ((FitWidthTextView)paramDataBindingComponent[6]);
    this.mboundView6.setTag(null);
    this.mboundView8 = ((ScrollView)paramDataBindingComponent[8]);
    this.mboundView8.setTag(null);
    this.mboundView9 = ((FitWidthTextView)paramDataBindingComponent[9]);
    this.mboundView9.setTag(null);
    this.speakerButton = ((Button)paramDataBindingComponent[11]);
    this.speakerButton.setTag(null);
    this.speakerUnrecognizableTitle = ((FitWidthTextView)paramDataBindingComponent[7]);
    this.speakerUnrecognizableTitle.setTag(null);
    this.toolBar = ((ToolBarLayout)paramDataBindingComponent[12]);
    setRootTag(paramView);
    this.mCallback43 = new OnClickListener(this, 1);
    invalidateAll();
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding bind(@NonNull View paramView)
  {
    return bind(paramView, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding bind(@NonNull View paramView, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    if (!"layout/activity_ranger_speaker_0".equals(paramView.getTag())) {
      throw new RuntimeException("view tag isn't correct on view:" + paramView.getTag());
    }
    return new ActivityRangerSpeakerBinding(paramDataBindingComponent, paramView);
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding inflate(@NonNull LayoutInflater paramLayoutInflater)
  {
    return inflate(paramLayoutInflater, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return bind(paramLayoutInflater.inflate(2131427389, null, false), paramDataBindingComponent);
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean)
  {
    return inflate(paramLayoutInflater, paramViewGroup, paramBoolean, DataBindingUtil.getDefaultComponent());
  }
  
  @NonNull
  public static ActivityRangerSpeakerBinding inflate(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, boolean paramBoolean, @Nullable DataBindingComponent paramDataBindingComponent)
  {
    return (ActivityRangerSpeakerBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427389, paramViewGroup, paramBoolean, paramDataBindingComponent);
  }
  
  private boolean onChangeViewModel(RangerSpeakerViewModel paramRangerSpeakerViewModel, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        this.mDirtyFlags |= 1L;
        return true;
      }
      finally {}
    }
    if (paramInt == 82) {
      try
      {
        this.mDirtyFlags |= 0x2;
        return true;
      }
      finally {}
    }
    if (paramInt == 81) {
      try
      {
        this.mDirtyFlags |= 0x4;
        return true;
      }
      finally {}
    }
    if (paramInt == 37) {
      try
      {
        this.mDirtyFlags |= 0x8;
        return true;
      }
      finally {}
    }
    if (paramInt == 54) {
      try
      {
        this.mDirtyFlags |= 0x10;
        return true;
      }
      finally {}
    }
    if (paramInt == 57) {
      try
      {
        this.mDirtyFlags |= 0x20;
        return true;
      }
      finally {}
    }
    if (paramInt == 13) {
      try
      {
        this.mDirtyFlags |= 0x40;
        return true;
      }
      finally {}
    }
    if (paramInt == 74) {
      try
      {
        this.mDirtyFlags |= 0x80;
        return true;
      }
      finally {}
    }
    if (paramInt == 48) {
      try
      {
        this.mDirtyFlags |= 0x100;
        return true;
      }
      finally {}
    }
    if (paramInt == 47) {
      try
      {
        this.mDirtyFlags |= 0x200;
        return true;
      }
      finally {}
    }
    if (paramInt == 68) {
      try
      {
        this.mDirtyFlags |= 0x400;
        return true;
      }
      finally {}
    }
    return false;
  }
  
  public final void _internalCallbackOnClick(int paramInt, View paramView)
  {
    paramView = this.mViewModel;
    if (paramView != null) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0) {
        paramView.openExtraInfo();
      }
      return;
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
        int i9 = 0;
        j = 0;
        Object localObject3 = null;
        Object localObject4 = null;
        boolean bool1 = false;
        int i3 = 0;
        m = 0;
        int i10 = 0;
        i = 0;
        boolean bool9 = false;
        boolean bool8 = false;
        int i7 = 0;
        k = 0;
        boolean bool7 = false;
        boolean bool6 = false;
        boolean bool5 = false;
        int i5 = 0;
        i1 = 0;
        int i4 = 0;
        n = 0;
        boolean bool4 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        int i11 = 0;
        int i6 = 0;
        i2 = 0;
        RangerSpeakerViewModel localRangerSpeakerViewModel = this.mViewModel;
        long l3 = l2;
        int i8 = i11;
        if ((0xFFF & l2) != 0L)
        {
          Object localObject1 = localObject4;
          if ((0x821 & l2) != 0L)
          {
            localObject1 = localObject4;
            if (localRangerSpeakerViewModel != null) {
              localObject1 = localRangerSpeakerViewModel.getRecognizedText();
            }
          }
          l1 = l2;
          if ((0x805 & l2) != 0L)
          {
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getWaitingTextVisibility();
            }
            l1 = l2;
            if ((0x805 & l2) != 0L)
            {
              if (!bool1) {
                break label1020;
              }
              l1 = l2 | 0x20000;
            }
            if (!bool1) {
              break label1031;
            }
            i = 0;
          }
          l2 = l1;
          if ((0x881 & l1) != 0L)
          {
            bool1 = bool9;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getUnrecognizeTextVisibility();
            }
            l2 = l1;
            if ((0x881 & l1) != 0L)
            {
              if (!bool1) {
                break label1036;
              }
              l2 = l1 | 0x2000;
            }
            if (!bool1) {
              break label1047;
            }
            j = 0;
          }
          l1 = l2;
          if ((0x811 & l2) != 0L)
          {
            bool1 = bool8;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getProcessingTextVisibility();
            }
            l1 = l2;
            if ((0x811 & l2) != 0L)
            {
              if (!bool1) {
                break label1052;
              }
              l1 = l2 | 0x80000;
            }
            if (!bool1) {
              break label1063;
            }
            k = 0;
          }
          l2 = l1;
          if ((0x841 & l1) != 0L)
          {
            bool1 = bool7;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getCorrectResultTextVisibility();
            }
            l2 = l1;
            if ((0x841 & l1) != 0L)
            {
              if (!bool1) {
                break label1068;
              }
              l2 = l1 | 0x8000;
            }
            if (!bool1) {
              break label1079;
            }
            m = 0;
          }
          l1 = l2;
          if ((0x809 & l2) != 0L)
          {
            bool1 = bool6;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getListeningTextVisibility();
            }
            l1 = l2;
            if ((0x809 & l2) != 0L)
            {
              if (!bool1) {
                break label1085;
              }
              l1 = l2 | 0x800000;
            }
            if (!bool1) {
              break label1096;
            }
            n = 0;
          }
          l2 = l1;
          if ((0xA01 & l1) != 0L)
          {
            bool1 = bool5;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getNoInternetTextVisibility();
            }
            l2 = l1;
            if ((0xA01 & l1) != 0L)
            {
              if (!bool1) {
                break label1102;
              }
              l2 = l1 | 0x200000;
            }
            if (!bool1) {
              break label1113;
            }
            i1 = 0;
          }
          l1 = l2;
          if ((0x901 & l2) != 0L)
          {
            bool1 = bool4;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getNoPermissionTextVisibility();
            }
            l1 = l2;
            if ((0x901 & l2) != 0L)
            {
              if (!bool1) {
                break label1119;
              }
              l1 = l2 | 0x2000000;
            }
            if (!bool1) {
              break label1130;
            }
            i2 = 0;
          }
          bool1 = bool3;
          if ((0x803 & l1) != 0L)
          {
            bool1 = bool3;
            if (localRangerSpeakerViewModel != null) {
              bool1 = localRangerSpeakerViewModel.getWavePlayAnimation();
            }
          }
          l3 = l1;
          i3 = m;
          i4 = n;
          i5 = i1;
          i6 = i2;
          i7 = k;
          localObject3 = localObject1;
          i8 = i11;
          i9 = j;
          i10 = i;
          bool2 = bool1;
          if ((0xC01 & l1) != 0L)
          {
            l3 = l1;
            i3 = m;
            i4 = n;
            i5 = i1;
            i6 = i2;
            i7 = k;
            localObject3 = localObject1;
            i8 = i11;
            i9 = j;
            i10 = i;
            bool2 = bool1;
            if (localRangerSpeakerViewModel != null)
            {
              i8 = localRangerSpeakerViewModel.getSpeakButtonBackground();
              bool2 = bool1;
              i10 = i;
              i9 = j;
              localObject3 = localObject1;
              i7 = k;
              i6 = i2;
              i5 = i1;
              i4 = n;
              i3 = m;
              l3 = l1;
            }
          }
        }
        if ((0x800 & l3) != 0L) {
          this.mboundView1.setOnClickListener(this.mCallback43);
        }
        if ((0xA01 & l3) != 0L) {
          this.mboundView10.setVisibility(i5);
        }
        if ((0x803 & l3) != 0L) {
          this.mboundView2.setAnimationStart(bool2);
        }
        if ((0x805 & l3) != 0L) {
          this.mboundView3.setVisibility(i10);
        }
        if ((0x809 & l3) != 0L) {
          this.mboundView4.setVisibility(i4);
        }
        if ((0x811 & l3) != 0L) {
          this.mboundView5.setVisibility(i7);
        }
        if ((0x821 & l3) != 0L) {
          TextViewBindingAdapter.setText(this.mboundView6, (CharSequence)localObject3);
        }
        if ((0x841 & l3) != 0L) {
          this.mboundView6.setVisibility(i3);
        }
        if ((0x881 & l3) != 0L)
        {
          this.mboundView8.setVisibility(i9);
          this.speakerUnrecognizableTitle.setVisibility(i9);
        }
        if ((0x901 & l3) != 0L) {
          this.mboundView9.setVisibility(i6);
        }
        if ((0xC01 & l3) != 0L) {
          ResourceDrawableBindings.setImageBackground(this.speakerButton, i8);
        }
        return;
      }
      finally {}
      label1020:
      long l1 = l2 | 0x10000;
      continue;
      label1031:
      int i = 4;
      continue;
      label1036:
      long l2 = l1 | 0x1000;
      continue;
      label1047:
      int j = 4;
      continue;
      label1052:
      l1 = l2 | 0x40000;
      continue;
      label1063:
      int k = 4;
      continue;
      label1068:
      l2 = l1 | 0x4000;
      continue;
      label1079:
      int m = 4;
      continue;
      label1085:
      l1 = l2 | 0x400000;
      continue;
      label1096:
      int n = 4;
      continue;
      label1102:
      l2 = l1 | 0x100000;
      continue;
      label1113:
      int i1 = 4;
      continue;
      label1119:
      l1 = l2 | 0x1000000;
      continue;
      label1130:
      int i2 = 4;
    }
  }
  
  @Nullable
  public RangerSpeakerViewModel getViewModel()
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
      this.mDirtyFlags = 2048L;
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
    return onChangeViewModel((RangerSpeakerViewModel)paramObject, paramInt2);
  }
  
  public boolean setVariable(int paramInt, @Nullable Object paramObject)
  {
    if (79 == paramInt)
    {
      setViewModel((RangerSpeakerViewModel)paramObject);
      return true;
    }
    return false;
  }
  
  public void setViewModel(@Nullable RangerSpeakerViewModel paramRangerSpeakerViewModel)
  {
    updateRegistration(0, paramRangerSpeakerViewModel);
    this.mViewModel = paramRangerSpeakerViewModel;
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\databinding\ActivityRangerSpeakerBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */