package com.iflytek.msc;

import android.text.TextUtils;

public class AIMIC
{
  public static final String DEF_LIBNAME_C = "aimic";
  public static final String DEF_LIBNAME_JNI = "aimicjni";
  public static final long INVALID_HANDLER = 0L;
  private static final String VAL_SEP = ";";
  private static long sHandler = 0L;
  private static boolean sIsLoaded = false;
  private static Object sSync = Long.valueOf(sHandler);
  
  public static native int AIMICAudioWrite(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws Throwable;
  
  public static native void AIMICDebugLog(boolean paramBoolean)
    throws Throwable;
  
  public static native int AIMICDestroy(long paramLong)
    throws Throwable;
  
  public static native int AIMICGetParam(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Throwable;
  
  public static native byte[] AIMICGetVersion()
    throws Throwable;
  
  public static native int AIMICNew(byte[] paramArrayOfByte, Listener paramListener)
    throws Throwable;
  
  public static native int AIMICResetEng(long paramLong)
    throws Throwable;
  
  public static native int AIMICSetParam(long paramLong, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Throwable;
  
  public static long getHandler()
  {
    return sHandler;
  }
  
  public static boolean isLoaded()
  {
    synchronized (sSync)
    {
      boolean bool = sIsLoaded;
      return bool;
    }
  }
  
  public static boolean isValid()
  {
    for (;;)
    {
      synchronized (sSync)
      {
        if (0L != sHandler)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public static void loadLibrary(String paramString)
    throws Throwable
  {
    int i = 0;
    synchronized (sSync)
    {
      sIsLoaded = false;
      if (TextUtils.isEmpty(paramString))
      {
        paramString = new String[2];
        paramString[0] = "aimicjni";
        paramString[1] = "aimic";
      }
      for (;;)
      {
        int j = paramString.length;
        while (i < j)
        {
          System.loadLibrary(paramString[i]);
          i += 1;
        }
        paramString = paramString.split(";");
      }
      sIsLoaded = true;
      return;
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onRecogAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject);
    
    public abstract void onWakeupAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject);
    
    public abstract void onWakeupMsg(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, int paramInt4, byte[] paramArrayOfByte2, int paramInt5, byte[] paramArrayOfByte3, int paramInt6);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\msc\AIMIC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */