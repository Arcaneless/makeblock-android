package com.umeng.analytics.pro;

import android.os.Build;
import android.os.Build.VERSION;

public class aj
  extends y
{
  private static final String a = "serial";
  
  public aj()
  {
    super("serial");
  }
  
  public String f()
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return Build.SERIAL;
    }
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */