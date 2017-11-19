package com.iflytek.aimic;

import com.iflytek.cloud.thirdparty.i;

public abstract class AIMic
{
  public static final int AUDIO_ALSA = -3;
  public static final int AUDIO_EXTERNAL = -1;
  public static final String KEY_AIMIC_IVW_SSB = "ivw_ssb";
  public static final String KEY_AIMIC_IVW_SSE = "ivw_sse";
  public static final String KEY_ALSA_CARD = "alsa_card";
  public static final String KEY_ALSA_SAMPLE_RATE = "alsa_rate";
  public static final String KEY_ASR_BUFFER_TIME = "aimic_asr_buffer_time";
  public static final String KEY_AUDIO_SOURCE = "audio_source";
  public static final String KEY_IVW_AUDIO_CB = "ivw_audio";
  public static final String KEY_IVW_AUDIO_SAVE = "ivw_save_file";
  public static final String KEY_IVW_BUF_TIME = "ivw_buf_tm";
  public static final String KEY_IVW_THREAD_NUM = "ivw_thread_num";
  public static final String KEY_LIB_NAME = "lib_name";
  public static final String KEY_SAVE_ALSA = "alsa_save";
  
  public static AIMic createAIMic(String paramString)
  {
    return i.a(paramString);
  }
  
  public static AIMic getAIMic()
  {
    return i.a();
  }
  
  public abstract void destroy();
  
  public abstract String getParameter(String paramString);
  
  public abstract String getVesion();
  
  public abstract boolean isListening();
  
  public abstract void registerListener(AsrDataListener paramAsrDataListener);
  
  public abstract void registerListener(IvwDataListener paramIvwDataListener);
  
  public abstract int reset();
  
  public abstract int setParameter(String paramString1, String paramString2);
  
  public abstract void setShowLog(boolean paramBoolean);
  
  public abstract int startListening();
  
  public abstract void stopListening();
  
  public abstract void unregisterListener(AsrDataListener paramAsrDataListener);
  
  public abstract void unregisterListener(IvwDataListener paramIvwDataListener);
  
  public abstract int writeAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public static abstract interface AsrDataListener
  {
    public abstract void onError(int paramInt);
    
    public abstract void onRecogAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject);
  }
  
  public static abstract interface IvwDataListener
  {
    public abstract void onError(int paramInt);
    
    public abstract void onWakeupAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject);
    
    public abstract void onWakeupMsg(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, int paramInt4, byte[] paramArrayOfByte2, int paramInt5, byte[] paramArrayOfByte3, int paramInt6);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\aimic\AIMic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */