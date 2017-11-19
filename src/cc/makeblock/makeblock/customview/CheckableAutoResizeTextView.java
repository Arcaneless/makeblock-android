package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;

public class CheckableAutoResizeTextView
  extends AutoResizeTextView
  implements Checkable
{
  private static final int[] CheckedStateSet = { 16842912 };
  private boolean mChecked = false;
  
  public CheckableAutoResizeTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public CheckableAutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CheckableAutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean isChecked()
  {
    return this.mChecked;
  }
  
  protected int[] onCreateDrawableState(int paramInt)
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CheckableAutoResizeTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */