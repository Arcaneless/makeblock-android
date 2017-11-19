package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

public class CheckableImageView
  extends ImageView
  implements Checkable
{
  private static final int[] CheckedStateSet = { 16842912 };
  private boolean mChecked = false;
  
  public CheckableImageView(Context paramContext)
  {
    super(paramContext);
  }
  
  public CheckableImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CheckableImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean isChecked()
  {
    return this.mChecked;
  }
  
  public int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      mergeDrawableStates(arrayOfInt, CheckedStateSet);
    }
    return arrayOfInt;
  }
  
  public boolean performClick()
  {
    toggle();
    return super.performClick();
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (paramBoolean != this.mChecked)
    {
      this.mChecked = paramBoolean;
      refreshDrawableState();
    }
  }
  
  public void toggle() {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CheckableImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */