package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.support.percent.PercentLayoutHelper.PercentLayoutParams;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class PercentLinearLayout
  extends LinearLayout
{
  private PercentLayoutHelper mPercentLayoutHelper = new PercentLayoutHelper(this);
  
  public PercentLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mPercentLayoutHelper.restoreOriginalParams();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mPercentLayoutHelper.adjustChildren(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
    if (this.mPercentLayoutHelper.handleMeasuredStateTooSmall()) {
      super.onMeasure(paramInt1, paramInt2);
    }
  }
  
  public static class LayoutParams
    extends LinearLayout.LayoutParams
    implements PercentLayoutHelper.PercentLayoutParams
  {
    private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      this.mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(paramContext, paramAttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo()
    {
      return this.mPercentLayoutInfo;
    }
    
    protected void setBaseAttributes(TypedArray paramTypedArray, int paramInt1, int paramInt2)
    {
      PercentLayoutHelper.fetchWidthAndHeight(this, paramTypedArray, paramInt1, paramInt2);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\PercentLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */