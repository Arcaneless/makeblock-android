package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

public class ba
{
  private static final String a = "umeng_general_config";
  
  public static SharedPreferences a(Context paramContext)
  {
    return paramContext.getSharedPreferences("umeng_general_config", 0);
  }
  
  public static SharedPreferences a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */