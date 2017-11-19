package com.iflytek.cloud.thirdparty;

import android.util.Log;

public class j
{
  private static String a = "AIMicLog";
  private static boolean b = false;
  private static a c = a.a;
  private static boolean d = true;
  
  public static a a()
  {
    return c;
  }
  
  public static void a(String paramString)
  {
    a(a, paramString);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    if (c()) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void a(Throwable paramThrowable)
  {
    if ((b()) && (paramThrowable != null)) {
      paramThrowable.printStackTrace();
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public static void b(String paramString)
  {
    b(a, paramString);
  }
  
  public static void b(String paramString1, String paramString2)
  {
    if (b()) {
      Log.e(paramString1, paramString2);
    }
  }
  
  private static boolean b()
  {
    return (d) && (a.e != a());
  }
  
  public static void c(String paramString)
  {
    c(a, paramString);
  }
  
  public static void c(String paramString1, String paramString2)
  {
    if (d()) {
      Log.d(paramString1, paramString2);
    }
  }
  
  private static boolean c()
  {
    return (d) && ((a.a == a()) || (a.b == a()));
  }
  
  private static boolean d()
  {
    return (b) && (d);
  }
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */