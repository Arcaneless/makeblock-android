package com.iflytek.msc;

public class VAD
{
  public static native int AppendData(long paramLong, byte[] paramArrayOfByte, int paramInt);
  
  public static native int CalcVolumLevel(long paramLong, byte[] paramArrayOfByte, int paramInt, VadData paramVadData);
  
  public static native int EndAudioData(long paramLong);
  
  public static native int FetchData(long paramLong, VadData paramVadData);
  
  public static native int GetLastSpeechPos(long paramLong, VadData paramVadData);
  
  public static native long Initialize(int paramInt);
  
  public static native void Reset(long paramLong);
  
  public static native int SetParam(long paramLong, int paramInt1, int paramInt2);
  
  public static native void Uninitialize(long paramLong);
  
  public static class VadData
  {
    public int audioQuality;
    public int endByte;
    public int endRemainFrameNum;
    public int firstOutByte;
    public int inSpeech;
    public int startByte;
    public int startRemainFrameNum;
    public int status;
    public int volumeLevel;
    public int waitPauseOrEnd;
    public int waitStart;
    public byte[] wavData;
    public int wavDataSize;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\msc\VAD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */