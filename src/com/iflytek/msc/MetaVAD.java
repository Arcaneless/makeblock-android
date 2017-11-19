package com.iflytek.msc;

public class MetaVAD
{
  public static native int VADAppendPCM(Instance paramInstance, byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public static native int VADCreateSession(Instance paramInstance);
  
  public static native int VADDelResource(int paramInt);
  
  public static native int VADDestroySession(Instance paramInstance);
  
  public static native int VADGetSeg(Instance paramInstance);
  
  public static native int VADInitialize(byte[] paramArrayOfByte);
  
  public static native int VADLoadResource(int paramInt, byte[] paramArrayOfByte);
  
  public static native int VADResetSession(Instance paramInstance);
  
  public static native int VADSetParam(Instance paramInstance, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public static native int VADUninitialize();
  
  public static class Instance
  {
    public static final long INVALID_HANDLE = 0L;
    public static final byte SEG = 2;
    public static final byte SEG_FIRST = 1;
    public static final byte SEG_LAST = 3;
    public static final byte SEG_NONE = 0;
    public int begin = 0;
    public int end = 0;
    public long handle = 0L;
    public int rate = 0;
    public int seg = 0;
    public int volume = 0;
    
    public void a()
    {
      this.begin = 0;
      this.end = 0;
      this.seg = 0;
      this.volume = 0;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\msc\MetaVAD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */