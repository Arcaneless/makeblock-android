package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import cc.makeblock.makeblock.R.styleable;

public class RatioLayout
  extends FrameLayout
{
  private int heightRadio = -1;
  private int widthRadio = -1;
  
  public RatioLayout(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public RatioLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public RatioLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.RatioLayout, paramInt, 0);
    this.heightRadio = paramAttributeSet.getInt(0, -1);
    this.widthRadio = paramAttributeSet.getInt(1, -1);
    paramAttributeSet.recycle();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = paramInt2;
    int k = paramInt1;
    int m = j;
    int i = k;
    if (this.heightRadio > 0)
    {
      m = j;
      i = k;
      if (this.widthRadio > 0)
      {
        paramInt1 = View.MeasureSpec.getSize(paramInt1);
        paramInt2 = View.MeasureSpec.getSize(paramInt2);
        if (this.heightRadio * paramInt1 <= this.widthRadio * paramInt2) {
          break label94;
        }
        paramInt1 = this.widthRadio * paramInt2 / this.heightRadio;
      }
    }
    for (;;)
    {
      m = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      i = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      super.onMeasure(i, m);
      return;
      label94:
      paramInt2 = this.heightRadio * paramInt1 / this.widthRadio;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\RatioLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */