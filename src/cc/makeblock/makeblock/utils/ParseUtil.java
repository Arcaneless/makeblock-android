package cc.makeblock.makeblock.utils;

public class ParseUtil
{
  public static boolean parseBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = Boolean.parseBoolean(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return paramBoolean;
  }
  
  public static float parseFloat(String paramString, float paramFloat)
  {
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (Exception paramString) {}
    return paramFloat;
  }
  
  public static int parseInt(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString) {}
    return paramInt;
  }
  
  public static String parseString(Object paramObject)
  {
    try
    {
      paramObject = paramObject.toString();
      return (String)paramObject;
    }
    catch (Exception paramObject) {}
    return "";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\utils\ParseUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */