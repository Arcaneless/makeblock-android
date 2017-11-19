package cc.makeblock.makeblock.engine.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class DeviceHelper
{
  private static boolean sIsTabletChecked = false;
  private static boolean sIsTabletValue = false;
  
  private static boolean _isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    if (sIsTabletChecked) {
      return sIsTabletValue;
    }
    sIsTabletValue = _isTablet(paramContext);
    sIsTabletChecked = true;
    return sIsTabletValue;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\DeviceHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */