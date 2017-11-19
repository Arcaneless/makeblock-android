package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.Checkable;

public class CheckImageView
  extends AppCompatImageView
  implements Checkable
{
  private static final int[] CheckedStateSet = { 16842912 };
  private boolean mChecked = false;
  
  public CheckImageView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CheckImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CheckImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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
  
  public void toggle()
  {
    if (!this.mChecked) {}
    for (boolean bool = true;; bool = false)
    {
      this.mChecked = bool;
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CheckImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */