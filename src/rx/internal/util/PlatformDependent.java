package rx.internal.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public final class PlatformDependent
{
  private static final int ANDROID_API_VERSION = ;
  public static final int ANDROID_API_VERSION_IS_NOT_ANDROID = 0;
  private static final boolean IS_ANDROID;
  
  static
  {
    if (ANDROID_API_VERSION != 0) {}
    for (boolean bool = true;; bool = false)
    {
      IS_ANDROID = bool;
      return;
    }
  }
  
  public static int getAndroidApiVersion()
  {
    return ANDROID_API_VERSION;
  }
  
  static ClassLoader getSystemClassLoader()
  {
    if (System.getSecurityManager() == null) {
      return ClassLoader.getSystemClassLoader();
    }
    (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public ClassLoader run()
      {
        return ClassLoader.getSystemClassLoader();
      }
    });
  }
  
  public static boolean isAndroid()
  {
    return IS_ANDROID;
  }
  
  private static int resolveAndroidApiVersion()
  {
    try
    {
      int i = ((Integer)Class.forName("android.os.Build$VERSION", true, getSystemClassLoader()).getField("SDK_INT").get(null)).intValue();
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\PlatformDependent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */