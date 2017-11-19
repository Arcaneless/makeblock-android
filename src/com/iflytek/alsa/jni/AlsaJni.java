package com.iflytek.alsa.jni;

public class AlsaJni
{
  private static boolean a = false;
  private static boolean b = true;
  
  public static boolean isLibLoaded()
  {
    return a;
  }
  
  public static void loadLib()
  {
    if (!a) {}
    try
    {
      System.loadLibrary("alsa-jni");
      setJniLog(b);
      a = true;
      return;
    }
    catch (Exception localException)
    {
      a = false;
    }
  }
  
  public static native int pcm_buffer_size(int paramInt);
  
  public static native int pcm_close(int paramInt);
  
  public static native int pcm_open(int paramInt1, int paramInt2, int paramInt3, Object paramObject);
  
  public static native int pcm_read(byte[] paramArrayOfByte, int paramInt);
  
  public static native int pcm_start_record(int paramInt1, int paramInt2);
  
  public static native void setJniLog(boolean paramBoolean);
  
  public static void showJniLog(boolean paramBoolean)
  {
    b = paramBoolean;
    if (a) {
      setJniLog(paramBoolean);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\alsa\jni\AlsaJni.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */