package com.gzsll;

import android.text.TextUtils;
import android.util.Log;

public final class Logger
{
  private static final String TAG = "WebViewJavascriptBridge";
  private static boolean logEnabled_d = true;
  private static boolean logEnabled_e = true;
  private static boolean logEnabled_i = true;
  
  public static void d()
  {
    if (logEnabled_d) {
      Log.v("WebViewJavascriptBridge", getLocation());
    }
  }
  
  public static void d(String paramString)
  {
    if (logEnabled_d) {
      Log.v("WebViewJavascriptBridge", getLocation() + paramString);
    }
  }
  
  public static void e()
  {
    if (logEnabled_e) {
      Log.e("WebViewJavascriptBridge", getLocation());
    }
  }
  
  public static void e(String paramString)
  {
    if (logEnabled_e) {
      Log.e("WebViewJavascriptBridge", getLocation() + paramString);
    }
  }
  
  public static void e(String paramString, Throwable paramThrowable)
  {
    if (logEnabled_e) {
      Log.e("WebViewJavascriptBridge", getLocation() + paramString, paramThrowable);
    }
  }
  
  public static void e(Throwable paramThrowable)
  {
    if (logEnabled_e) {
      Log.e("WebViewJavascriptBridge", getLocation(), paramThrowable);
    }
  }
  
  private static String getClassName(Class<?> paramClass)
  {
    if (paramClass != null)
    {
      if (!TextUtils.isEmpty(paramClass.getSimpleName())) {
        return paramClass.getSimpleName();
      }
      return getClassName(paramClass.getEnclosingClass());
    }
    return "";
  }
  
  private static String getLocation()
  {
    String str = Logger.class.getName();
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    j = 0;
    int m = arrayOfStackTraceElement.length;
    int i = 0;
    while (i < m)
    {
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
      if (j != 0) {
        k = j;
      }
      try
      {
        if (localStackTraceElement.getClassName().startsWith(str)) {
          break label139;
        }
        Class localClass = Class.forName(localStackTraceElement.getClassName());
        return "[" + getClassName(localClass) + ":" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber() + "]: ";
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;)
        {
          boolean bool;
          k = j;
        }
      }
      bool = localStackTraceElement.getClassName().startsWith(str);
      k = j;
      if (bool) {
        k = 1;
      }
      label139:
      i += 1;
      j = k;
    }
    return "[]: ";
  }
  
  public static void i()
  {
    if (logEnabled_i) {
      Log.i("WebViewJavascriptBridge", getLocation());
    }
  }
  
  public static void i(String paramString)
  {
    if (logEnabled_i) {
      Log.i("WebViewJavascriptBridge", getLocation() + paramString);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\gzsll\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */