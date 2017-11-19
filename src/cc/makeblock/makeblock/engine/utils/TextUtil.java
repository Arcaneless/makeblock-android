package cc.makeblock.makeblock.engine.utils;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import cc.makeblock.makeblock.R.string;
import cc.makeblock.makeblock.base.App;
import java.lang.reflect.Field;

public class TextUtil
{
  public static String getStringById(@StringRes int paramInt)
  {
    try
    {
      String str = App.getContext().getResources().getString(paramInt);
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String getStringByKey(String paramString)
  {
    try
    {
      int i = R.string.class.getDeclaredField(paramString).getInt(null);
      paramString = App.getContext().getResources().getString(i);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\TextUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */