package com.iflytek.cloud;

import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.E.a;
import com.iflytek.cloud.thirdparty.ad;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;

public class Version
{
  private static String a = "";
  private static String b = "0";
  
  private static String getModeVersion()
  {
    if (E.a.a == SpeechUtility.DEF_ENGINE_MODE) {
      return "6";
    }
    return "5";
  }
  
  private static String getMscVersion()
  {
    if ("0".equalsIgnoreCase(b)) {}
    for (;;)
    {
      try
      {
        if (!MSC.isLoaded()) {
          continue;
        }
        localObject = new MSCSessionInfo();
        byte[] arrayOfByte = MSC.QMSPGetVersion("ver_msc".getBytes("gb2312"), (MSCSessionInfo)localObject);
        if (((MSCSessionInfo)localObject).errorcode != 0) {
          continue;
        }
        localObject = new String(arrayOfByte, "gb2312");
        ad.a("get msc full version name: " + (String)localObject);
        int i = ((String)localObject).lastIndexOf(".");
        if ((i >= 0) && (((String)localObject).length() > i + 1)) {
          b = ((String)localObject).substring(i + 1);
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject;
        ad.b("get msc version exception:");
        ad.a(localThrowable);
        continue;
        ad.b("get msc version msc is not load.");
        continue;
      }
      return b;
      ad.a("get msc version error: " + ((MSCSessionInfo)localObject).errorcode);
    }
  }
  
  public static String getVersion()
  {
    if ((TextUtils.isEmpty(a)) || ("0".equalsIgnoreCase(b))) {
      a = "4." + getModeVersion() + "." + "1109" + "." + getMscVersion();
    }
    return a;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */