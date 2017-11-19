package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class FitWidthTextView
  extends AppCompatTextView
{
  public FitWidthTextView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public FitWidthTextView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public FitWidthTextView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FitWidthTextView, 0, 0);
    float f1 = paramContext.getFloat(0, 0.0F);
    float f2 = paramContext.getFloat(1, 0.0F);
    paramContext.recycle();
    if (f1 != 0.0F)
    {
      setTextSize(0, ScreenHelper.SCREEN_HEIGHT * f1);
      return;
    }
    setTextSize(0, ScreenHelper.SCREEN_WIDTH * f2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\FitWidthTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */