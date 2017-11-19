package com.iflytek.cloud.thirdparty;

import android.util.Log;
import com.iflytek.msc.MSC;

public class ad
{
  private static String a = "MscSpeechLog";
  private static a b = a.a;
  private static boolean c = true;
  private static boolean d = false;
  
  public static void a(a parama)
  {
    b = parama;
    c();
  }
  
  public static void a(String paramString)
  {
    a(a, paramString);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    if (e()) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void a(Throwable paramThrowable)
  {
    if ((d()) && (paramThrowable != null)) {
      paramThrowable.printStackTrace();
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    c = paramBoolean;
    c();
  }
  
  public static boolean a()
  {
    return c;
  }
  
  public static a b()
  {
    return b;
  }
  
  public static void b(String paramString)
  {
    b(a, paramString);
  }
  
  public static void b(String paramString1, String paramString2)
  {
    if (d()) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public static void b(Throwable paramThrowable)
  {
    if (f()) {
      paramThrowable.printStackTrace();
    }
  }
  
  public static void c()
  {
    try
    {
      if (MSC.isLoaded()) {
        if ((!a()) || (!e())) {
          break label26;
        }
      }
      label26:
      for (boolean bool = true;; bool = false)
      {
        MSC.DebugLog(bool);
        return;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }
  
  public static void c(String paramString)
  {
    c(a, paramString);
  }
  
  public static void c(String paramString1, String paramString2)
  {
    if (f()) {
      Log.d(paramString1, paramString2);
    }
  }
  
  private static boolean d()
  {
    return (a()) && (a.e != b());
  }
  
  private static boolean e()
  {
    return (a()) && ((a.a == b()) || (a.b == b()));
  }
  
  private static boolean f()
  {
    return (d) && (a());
  }
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */