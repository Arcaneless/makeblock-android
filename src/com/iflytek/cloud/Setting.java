package com.iflytek.cloud;

import android.os.Environment;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.ad;
import java.io.File;
import java.util.Locale;

public class Setting
{
  public static final String a = Environment.getExternalStorageDirectory().getPath() + "/msc/msc.log";
  private static String b = a;
  private static boolean c = false;
  private static boolean d = true;
  
  public static boolean getLocationEnable()
  {
    return d;
  }
  
  public static LOG_LEVEL getLogLevel()
  {
    LOG_LEVEL localLOG_LEVEL1 = LOG_LEVEL.none;
    try
    {
      LOG_LEVEL localLOG_LEVEL2 = LOG_LEVEL.values()[ad.b().ordinal()];
      return localLOG_LEVEL2;
    }
    catch (Exception localException)
    {
      ad.a(localException);
    }
    return localLOG_LEVEL1;
  }
  
  public static String getLogPath()
  {
    return b;
  }
  
  public static boolean getSaveTestLog()
  {
    return c;
  }
  
  public static boolean getShowLog()
  {
    return ad.a();
  }
  
  public static void setLocale(Locale paramLocale)
  {
    Resource.setUILanguage(paramLocale);
  }
  
  public static void setLocationEnable(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public static void setLogLevel(LOG_LEVEL paramLOG_LEVEL)
  {
    if (paramLOG_LEVEL != null) {}
    try
    {
      ad.a(com.iflytek.cloud.thirdparty.ad.a.values()[paramLOG_LEVEL.ordinal()]);
      return;
    }
    catch (Exception paramLOG_LEVEL)
    {
      ad.a(paramLOG_LEVEL);
    }
  }
  
  public static void setLogPath(String paramString)
  {
    b = paramString;
  }
  
  public static void setSaveTestLog(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public static void setShowLog(boolean paramBoolean)
  {
    ad.a(paramBoolean);
  }
  
  public static enum LOG_LEVEL
  {
    static
    {
      low = new LOG_LEVEL("low", 3);
      none = new LOG_LEVEL("none", 4);
    }
    
    private LOG_LEVEL() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\Setting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */