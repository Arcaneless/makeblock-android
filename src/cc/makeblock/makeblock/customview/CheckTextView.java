package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

public class CheckTextView
  extends AutoResizeTextView
{
  public CheckTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CheckTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CheckTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setTextColor(getResources().getColor(2131034220));
      return;
    }
    setTextColor(getResources().getColorStateList(2131034248));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CheckTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */