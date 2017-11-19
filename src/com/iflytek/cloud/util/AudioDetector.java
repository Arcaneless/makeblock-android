package com.iflytek.cloud.util;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.aA;
import com.iflytek.cloud.thirdparty.aB;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.cloud.thirdparty.ag;
import java.util.LinkedHashMap;

public abstract class AudioDetector
{
  public static final int DEF_BOS = 1200;
  public static final String DEF_ENGINE_TYPE = "fixfront";
  public static final int DEF_EOS = 20000;
  public static final int DEF_RATE = 16000;
  public static final String EARLY_START = "early_start";
  public static final int MAX_BUF_LEN = 32768;
  public static final String RES_PATH = "vad_res_path";
  public static final String SUB_TIMEOUT = "sub_timeout";
  public static final String THRESHOLD = "threshold";
  public static final String TYPE_FIXFRONT = "fixfront";
  public static final String TYPE_META = "meta";
  public static final String VAD_ENGINE = "vad_engine";
  protected static AudioDetector a = null;
  protected static Object b = new Object();
  
  protected AudioDetector(Context paramContext, String paramString) {}
  
  private static AudioDetector a(Context paramContext, String paramString)
  {
    Object localObject = new ag();
    ((ag)localObject).a(paramString);
    String str = ((ag)localObject).e("lib_name");
    if ((TextUtils.isEmpty(str)) || (a(str)))
    {
      localObject = ((ag)localObject).b("vad_engine", "fixfront");
      if ("fixfront".equalsIgnoreCase((String)localObject)) {
        return new aA(paramContext, paramString);
      }
      if ("meta".equalsIgnoreCase((String)localObject)) {
        return new aB(paramContext, paramString);
      }
      ad.b("detector factory unmatched engine type: " + (String)localObject);
      return null;
    }
    ad.b("detector factory load library failed: " + str);
    return null;
  }
  
  private static boolean a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      System.loadLibrary(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      ad.b("Load library failed.");
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static AudioDetector createDetector(Context paramContext, String paramString)
  {
    ad.a("createDetector enter, context: " + paramContext + ", param: " + paramString);
    synchronized (b)
    {
      if (a == null) {
        a = a(paramContext, paramString);
      }
      ad.a("createDetector leave");
      return a;
    }
  }
  
  public static AudioDetector getDetector()
  {
    synchronized (b)
    {
      ad.a("getDetector enter");
      return a;
    }
  }
  
  public abstract boolean destroy();
  
  public abstract DetectorResult detect(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract void reset();
  
  public abstract void setParameter(String paramString1, String paramString2);
  
  public static class DetectorResult
  {
    public static final int STATUS_BOS = 3;
    public static final int STATUS_EOS = 2;
    public static final int STATUS_OK = 0;
    public static final int STATUS_START = 1;
    public static final int STATUS_TIMEOUT = 4;
    public static final int SUB_END = 2;
    public static final int SUB_OK = 0;
    public static final int SUB_START = 1;
    public static final int SUB_STARTEND = 3;
    public byte[] buffer = null;
    public int end = 0;
    public int error = 0;
    public int length = 0;
    public int offset = 0;
    public int quality = 0;
    public int start = 0;
    public int status = 0;
    public int sub = 0;
    public final LinkedHashMap<Integer, Integer> subs = new LinkedHashMap();
    public boolean voice = false;
    public int volume = 0;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\util\AudioDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */