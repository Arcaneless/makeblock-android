package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import java.util.LinkedHashMap;

public class ae
{
  public static LinkedHashMap<String, String> a = new LinkedHashMap();
  private static String b = "=";
  private static String c = ":";
  private static String d = ";";
  private static String e = "=========================================================\r\n";
  private static boolean f = false;
  
  public static void a(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        boolean bool = f;
        if (!bool) {
          return;
        }
        ad.c("appendInfo:" + paramString1 + "," + paramString2);
        if (!TextUtils.isEmpty(paramString2))
        {
          paramString2 = paramString2 + c;
          a.put(paramString1, paramString2 + System.currentTimeMillis());
        }
        else
        {
          paramString2 = "";
        }
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */