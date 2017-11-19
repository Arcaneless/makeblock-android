package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

@Deprecated
public class PercentLayoutHelper
{
  private static final boolean DEBUG = false;
  private static final String TAG = "PercentLayout";
  private static final boolean VERBOSE = false;
  private final ViewGroup mHost;
  
  public PercentLayoutHelper(@NonNull ViewGroup paramViewGroup)
  {
    if (paramViewGroup == null) {
      throw new IllegalArgumentException("host must be non-null");
    }
    this.mHost = paramViewGroup;
  }
  
  public static void fetchWidthAndHeight(ViewGroup.LayoutParams paramLayoutParams, TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    paramLayoutParams.width = paramTypedArray.getLayoutDimension(paramInt1, 0);
    paramLayoutParams.height = paramTypedArray.getLayoutDimension(paramInt2, 0);
  }
  
  public static PercentLayoutInfo getPercentLayoutInfo(Context paramContext, AttributeSet paramAttributeSet)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PercentLayout_Layout);
    float f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_widthPercent, 1, 1, -1.0F);
    paramContext = (Context)localObject2;
    if (f != -1.0F)
    {
      if (0 != 0)
      {
        paramContext = (Context)localObject1;
        paramContext.widthPercent = f;
      }
    }
    else
    {
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_heightPercent, 1, 1, -1.0F);
      paramAttributeSet = paramContext;
      if (f != -1.0F)
      {
        if (paramContext == null) {
          break label379;
        }
        label75:
        paramContext.heightPercent = f;
        paramAttributeSet = paramContext;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginPercent, 1, 1, -1.0F);
      paramContext = paramAttributeSet;
      if (f != -1.0F)
      {
        if (paramAttributeSet == null) {
          break label390;
        }
        paramContext = paramAttributeSet;
        label110:
        paramContext.leftMarginPercent = f;
        paramContext.topMarginPercent = f;
        paramContext.rightMarginPercent = f;
        paramContext.bottomMarginPercent = f;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginLeftPercent, 1, 1, -1.0F);
      paramAttributeSet = paramContext;
      if (f != -1.0F)
      {
        if (paramContext == null) {
          break label401;
        }
        label156:
        paramContext.leftMarginPercent = f;
        paramAttributeSet = paramContext;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginTopPercent, 1, 1, -1.0F);
      paramContext = paramAttributeSet;
      if (f != -1.0F)
      {
        if (paramAttributeSet == null) {
          break label412;
        }
        paramContext = paramAttributeSet;
        label191:
        paramContext.topMarginPercent = f;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginRightPercent, 1, 1, -1.0F);
      paramAttributeSet = paramContext;
      if (f != -1.0F)
      {
        if (paramContext == null) {
          break label423;
        }
        label222:
        paramContext.rightMarginPercent = f;
        paramAttributeSet = paramContext;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginBottomPercent, 1, 1, -1.0F);
      paramContext = paramAttributeSet;
      if (f != -1.0F)
      {
        if (paramAttributeSet == null) {
          break label434;
        }
        paramContext = paramAttributeSet;
        label257:
        paramContext.bottomMarginPercent = f;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginStartPercent, 1, 1, -1.0F);
      paramAttributeSet = paramContext;
      if (f != -1.0F)
      {
        if (paramContext == null) {
          break label445;
        }
        label288:
        paramContext.startMarginPercent = f;
        paramAttributeSet = paramContext;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginEndPercent, 1, 1, -1.0F);
      paramContext = paramAttributeSet;
      if (f != -1.0F)
      {
        if (paramAttributeSet == null) {
          break label456;
        }
        paramContext = paramAttributeSet;
        label323:
        paramContext.endMarginPercent = f;
      }
      f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_aspectRatio, 1, 1, -1.0F);
      paramAttributeSet = paramContext;
      if (f != -1.0F) {
        if (paramContext == null) {
          break label467;
        }
      }
    }
    for (;;)
    {
      paramContext.aspectRatio = f;
      paramAttributeSet = paramContext;
      localTypedArray.recycle();
      return paramAttributeSet;
      paramContext = new PercentLayoutInfo();
      break;
      label379:
      paramContext = new PercentLayoutInfo();
      break label75;
      label390:
      paramContext = new PercentLayoutInfo();
      break label110;
      label401:
      paramContext = new PercentLayoutInfo();
      break label156;
      label412:
      paramContext = new PercentLayoutInfo();
      break label191;
      label423:
      paramContext = new PercentLayoutInfo();
      break label222;
      label434:
      paramContext = new PercentLayoutInfo();
      break label257;
      label445:
      paramContext = new PercentLayoutInfo();
      break label288;
      label456:
      paramContext = new PercentLayoutInfo();
      break label323;
      label467:
      paramContext = new PercentLayoutInfo();
    }
  }
  
  private static boolean shouldHandleMeasuredHeightTooSmall(View paramView, PercentLayoutInfo paramPercentLayoutInfo)
  {
    return ((paramView.getMeasuredHeightAndState() & 0xFF000000) == 16777216) && (paramPercentLayoutInfo.heightPercent >= 0.0F) && (paramPercentLayoutInfo.mPreservedParams.height == -2);
  }
  
  private static boolean shouldHandleMeasuredWidthTooSmall(View paramView, PercentLayoutInfo paramPercentLayoutInfo)
  {
    return ((paramView.getMeasuredWidthAndState() & 0xFF000000) == 16777216) && (paramPercentLayoutInfo.widthPercent >= 0.0F) && (paramPercentLayoutInfo.mPreservedParams.width == -2);
  }
  
  public void adjustChildren(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1) - this.mHost.getPaddingLeft() - this.mHost.getPaddingRight();
    paramInt2 = View.MeasureSpec.getSize(paramInt2) - this.mHost.getPaddingTop() - this.mHost.getPaddingBottom();
    paramInt1 = 0;
    int j = this.mHost.getChildCount();
    if (paramInt1 < j)
    {
      View localView = this.mHost.getChildAt(paramInt1);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      PercentLayoutInfo localPercentLayoutInfo;
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        if (localPercentLayoutInfo != null)
        {
          if (!(localLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
            break label130;
          }
          localPercentLayoutInfo.fillMarginLayoutParams(localView, (ViewGroup.MarginLayoutParams)localLayoutParams, i, paramInt2);
        }
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        label130:
        localPercentLayoutInfo.fillLayoutParams(localLayoutParams, i, paramInt2);
      }
    }
  }
  
  public boolean handleMeasuredStateTooSmall()
  {
    boolean bool1 = false;
    int i = 0;
    int j = this.mHost.getChildCount();
    while (i < j)
    {
      View localView = this.mHost.getChildAt(i);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      boolean bool2 = bool1;
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        PercentLayoutInfo localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        bool2 = bool1;
        if (localPercentLayoutInfo != null)
        {
          if (shouldHandleMeasuredWidthTooSmall(localView, localPercentLayoutInfo))
          {
            bool1 = true;
            localLayoutParams.width = -2;
          }
          bool2 = bool1;
          if (shouldHandleMeasuredHeightTooSmall(localView, localPercentLayoutInfo))
          {
            bool2 = true;
            localLayoutParams.height = -2;
          }
        }
      }
      i += 1;
      bool1 = bool2;
    }
    return bool1;
  }
  
  public void restoreOriginalParams()
  {
    int i = 0;
    int j = this.mHost.getChildCount();
    if (i < j)
    {
      ViewGroup.LayoutParams localLayoutParams = this.mHost.getChildAt(i).getLayoutParams();
      PercentLayoutInfo localPercentLayoutInfo;
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        if (localPercentLayoutInfo != null)
        {
          if (!(localLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
            break label73;
          }
          localPercentLayoutInfo.restoreMarginLayoutParams((ViewGroup.MarginLayoutParams)localLayoutParams);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        label73:
        localPercentLayoutInfo.restoreLayoutParams(localLayoutParams);
      }
    }
  }
  
  @Deprecated
  public static class PercentLayoutInfo
  {
    public float aspectRatio;
    public float bottomMarginPercent = -1.0F;
    public float endMarginPercent = -1.0F;
    public float heightPercent = -1.0F;
    public float leftMarginPercent = -1.0F;
    final PercentLayoutHelper.PercentMarginLayoutParams mPreservedParams = new PercentLayoutHelper.PercentMarginLayoutParams(0, 0);
    public float rightMarginPercent = -1.0F;
    public float startMarginPercent = -1.0F;
    public float topMarginPercent = -1.0F;
    public float widthPercent = -1.0F;
    
    public void fillLayoutParams(ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
    {
      this.mPreservedParams.width = paramLayoutParams.width;
      this.mPreservedParams.height = paramLayoutParams.height;
      int i;
      if (((PercentLayoutHelper.PercentMarginLayoutParams.access$000(this.mPreservedParams)) || (this.mPreservedParams.width == 0)) && (this.widthPercent < 0.0F))
      {
        i = 1;
        if (((!PercentLayoutHelper.PercentMarginLayoutParams.access$100(this.mPreservedParams)) && (this.mPreservedParams.height != 0)) || (this.heightPercent >= 0.0F)) {
          break label210;
        }
      }
      label210:
      for (int j = 1;; j = 0)
      {
        if (this.widthPercent >= 0.0F) {
          paramLayoutParams.width = Math.round(paramInt1 * this.widthPercent);
        }
        if (this.heightPercent >= 0.0F) {
          paramLayoutParams.height = Math.round(paramInt2 * this.heightPercent);
        }
        if (this.aspectRatio >= 0.0F)
        {
          if (i != 0)
          {
            paramLayoutParams.width = Math.round(paramLayoutParams.height * this.aspectRatio);
            PercentLayoutHelper.PercentMarginLayoutParams.access$002(this.mPreservedParams, true);
          }
          if (j != 0)
          {
            paramLayoutParams.height = Math.round(paramLayoutParams.width / this.aspectRatio);
            PercentLayoutHelper.PercentMarginLayoutParams.access$102(this.mPreservedParams, true);
          }
        }
        return;
        i = 0;
        break;
      }
    }
    
    public void fillMarginLayoutParams(View paramView, ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt1, int paramInt2)
    {
      fillLayoutParams(paramMarginLayoutParams, paramInt1, paramInt2);
      this.mPreservedParams.leftMargin = paramMarginLayoutParams.leftMargin;
      this.mPreservedParams.topMargin = paramMarginLayoutParams.topMargin;
      this.mPreservedParams.rightMargin = paramMarginLayoutParams.rightMargin;
      this.mPreservedParams.bottomMargin = paramMarginLayoutParams.bottomMargin;
      MarginLayoutParamsCompat.setMarginStart(this.mPreservedParams, MarginLayoutParamsCompat.getMarginStart(paramMarginLayoutParams));
      MarginLayoutParamsCompat.setMarginEnd(this.mPreservedParams, MarginLayoutParamsCompat.getMarginEnd(paramMarginLayoutParams));
      if (this.leftMarginPercent >= 0.0F) {
        paramMarginLayoutParams.leftMargin = Math.round(paramInt1 * this.leftMarginPercent);
      }
      if (this.topMarginPercent >= 0.0F) {
        paramMarginLayoutParams.topMargin = Math.round(paramInt2 * this.topMarginPercent);
      }
      if (this.rightMarginPercent >= 0.0F) {
        paramMarginLayoutParams.rightMargin = Math.round(paramInt1 * this.rightMarginPercent);
      }
      if (this.bottomMarginPercent >= 0.0F) {
        paramMarginLayoutParams.bottomMargin = Math.round(paramInt2 * this.bottomMarginPercent);
      }
      paramInt2 = 0;
      if (this.startMarginPercent >= 0.0F)
      {
        MarginLayoutParamsCompat.setMarginStart(paramMarginLayoutParams, Math.round(paramInt1 * this.startMarginPercent));
        paramInt2 = 1;
      }
      if (this.endMarginPercent >= 0.0F)
      {
        MarginLayoutParamsCompat.setMarginEnd(paramMarginLayoutParams, Math.round(paramInt1 * this.endMarginPercent));
        paramInt2 = 1;
      }
      if ((paramInt2 != 0) && (paramView != null)) {
        MarginLayoutParamsCompat.resolveLayoutDirection(paramMarginLayoutParams, ViewCompat.getLayoutDirection(paramView));
      }
    }
    
    @Deprecated
    public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt1, int paramInt2)
    {
      fillMarginLayoutParams(null, paramMarginLayoutParams, paramInt1, paramInt2);
    }
    
    public void restoreLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      if (!PercentLayoutHelper.PercentMarginLayoutParams.access$000(this.mPreservedParams)) {
        paramLayoutParams.width = this.mPreservedParams.width;
      }
      if (!PercentLayoutHelper.PercentMarginLayoutParams.access$100(this.mPreservedParams)) {
        paramLayoutParams.height = this.mPreservedParams.height;
      }
      PercentLayoutHelper.PercentMarginLayoutParams.access$002(this.mPreservedParams, false);
      PercentLayoutHelper.PercentMarginLayoutParams.access$102(this.mPreservedParams, false);
    }
    
    public void restoreMarginLayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      restoreLayoutParams(paramMarginLayoutParams);
      paramMarginLayoutParams.leftMargin = this.mPreservedParams.leftMargin;
      paramMarginLayoutParams.topMargin = this.mPreservedParams.topMargin;
      paramMarginLayoutParams.rightMargin = this.mPreservedParams.rightMargin;
      paramMarginLayoutParams.bottomMargin = this.mPreservedParams.bottomMargin;
      MarginLayoutParamsCompat.setMarginStart(paramMarginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.mPreservedParams));
      MarginLayoutParamsCompat.setMarginEnd(paramMarginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.mPreservedParams));
    }
    
    public String toString()
    {
      return String.format("PercentLayoutInformation width: %f height %f, margins (%f, %f,  %f, %f, %f, %f)", new Object[] { Float.valueOf(this.widthPercent), Float.valueOf(this.heightPercent), Float.valueOf(this.leftMarginPercent), Float.valueOf(this.topMarginPercent), Float.valueOf(this.rightMarginPercent), Float.valueOf(this.bottomMarginPercent), Float.valueOf(this.startMarginPercent), Float.valueOf(this.endMarginPercent) });
    }
  }
  
  @Deprecated
  public static abstract interface PercentLayoutParams
  {
    public abstract PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo();
  }
  
  static class PercentMarginLayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    private boolean mIsHeightComputedFromAspectRatio;
    private boolean mIsWidthComputedFromAspectRatio;
    
    public PercentMarginLayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\percent\PercentLayoutHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */