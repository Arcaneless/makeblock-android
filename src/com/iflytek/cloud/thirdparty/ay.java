package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ay
  extends LinearLayout
{
  private ax.a a = null;
  protected boolean b = true;
  protected boolean c = true;
  
  public ay(Context paramContext)
  {
    super(paramContext);
  }
  
  protected static boolean a(ViewGroup paramViewGroup)
  {
    try
    {
      int j = paramViewGroup.getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = paramViewGroup.getChildAt(i);
        localView.setOnClickListener(null);
        localView.setBackgroundDrawable(null);
        i += 1;
      }
      paramViewGroup.removeAllViews();
      paramViewGroup.setOnClickListener(null);
      paramViewGroup.setBackgroundDrawable(null);
      return true;
    }
    catch (Exception paramViewGroup)
    {
      ad.a(paramViewGroup);
    }
    return false;
  }
  
  public void a(ax.a parama)
  {
    this.a = parama;
  }
  
  protected void b() {}
  
  protected void c() {}
  
  protected boolean d()
  {
    try
    {
      a(this);
      return true;
    }
    catch (Exception localException)
    {
      ad.a(localException);
    }
    return false;
  }
  
  protected void e()
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT > 10) {
      localIntent.setAction("android.settings.SETTINGS");
    }
    for (;;)
    {
      localIntent.addFlags(268435456);
      getContext().startActivity(localIntent);
      return;
      localIntent.setAction("android.settings.WIRELESS_SETTINGS");
    }
  }
  
  public void f()
  {
    if (this.a != null) {
      this.a.a();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */