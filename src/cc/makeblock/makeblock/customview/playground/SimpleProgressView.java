package cc.makeblock.makeblock.customview.playground;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.customview.drawable.ProgressDrawable;

public class SimpleProgressView
  extends View
{
  private ProgressDrawable mProgressDrawable;
  
  public SimpleProgressView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SimpleProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SimpleProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ProgressView, 0, 0);
    paramInt = paramContext.getResourceId(0, 0);
    int i = paramContext.getResourceId(2, 0);
    int j = paramContext.getInt(3, 0);
    int k = paramContext.getInt(1, 0);
    paramContext.recycle();
    this.mProgressDrawable = new ProgressDrawable(paramInt, i, j, k);
    if (!isInEditMode()) {
      setBackground(this.mProgressDrawable);
    }
  }
  
  public void setProgress(float paramFloat)
  {
    this.mProgressDrawable.setProgress(paramFloat);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\playground\SimpleProgressView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */