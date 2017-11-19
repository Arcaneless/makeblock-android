package cc.makeblock.makeblock.bean;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class CommonMenuItem
  implements IMenuItem
{
  public OnClickListener onClickListener;
  public String title;
  
  public CommonMenuItem(String paramString, OnClickListener paramOnClickListener)
  {
    this.title = paramString;
    this.onClickListener = paramOnClickListener;
  }
  
  public Drawable getIcon()
  {
    return null;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public static abstract interface OnClickListener
  {
    public abstract void onClick(Activity paramActivity);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\CommonMenuItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */