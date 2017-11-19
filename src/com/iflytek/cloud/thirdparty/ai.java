package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.Setting.LOG_LEVEL;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.Version;

public class ai
{
  private static String a = "xiaoyan";
  private static String b = null;
  
  public static String a()
  {
    return SpeechUtility.getUtility().getParameter("appid");
  }
  
  public static String a(Context paramContext)
  {
    Object localObject;
    if (paramContext == null) {
      localObject = "null";
    }
    do
    {
      return (String)localObject;
      ag localag = M.a(paramContext);
      localObject = localag.e("os.imsi") + "|" + localag.e("os.imei");
      paramContext = (Context)localObject;
      if (((String)localObject).length() < 10) {
        paramContext = localag.e("net.mac");
      }
      if (TextUtils.isEmpty(paramContext)) {
        break;
      }
      localObject = paramContext;
    } while (paramContext.length() > 0);
    return null;
  }
  
  public static String a(Context paramContext, B paramB)
    throws SpeechError
  {
    if (paramContext == null) {
      throw new SpeechError(20012);
    }
    paramB = paramB.v().b();
    b = paramB.b("net_type", b);
    a(paramContext, paramB);
    paramB.a("timeout", "20000", false);
    paramB.a("auth", "1", false);
    paramB.a("msc.ver", Version.getVersion());
    paramB.a("mac", M.a(paramContext).e("net.mac"), false);
    paramB.a("dvc", a(paramContext), false);
    paramB.a("unique_id", Y.a(paramContext));
    paramB.a("msc.lat", "" + N.a(paramContext).b("msc.lat"), false);
    paramB.a("msc.lng", "" + N.a(paramContext).b("msc.lng"), false);
    paramB.a(M.b(paramContext));
    a(paramB);
    b(paramContext, paramB);
    paramB.a(ah.c);
    return paramB.toString();
  }
  
  public static String a(Context paramContext, String paramString, B paramB)
  {
    ag localag = paramB.v().b();
    localag.c("cloud_grammar");
    a(paramContext, localag);
    b(paramContext, localag);
    localag.a("language", "zh_cn", false);
    localag.a("result_type", "json", false);
    localag.a("rse", paramB.r(), false);
    localag.a("text_encoding", paramB.q(), false);
    localag.a("ssm", "1", false);
    localag.a("msc.skin", "0", false);
    if (TextUtils.isEmpty(paramString))
    {
      localag.a("subject", "iat", false);
      int i = paramB.s();
      localag.a("auf=audio/L16;rate", Integer.toString(i), false);
      if (i != 16000) {
        break label198;
      }
      localag.a("aue", "speex-wb", false);
      label144:
      if (!paramB.m()) {
        break label211;
      }
      localag.a("vad_bos", "5000", false);
      localag.a("vad_eos", "1800", false);
    }
    for (;;)
    {
      localag.a(ah.c);
      return localag.toString();
      localag.a("subject", "asr", false);
      break;
      label198:
      localag.a("aue", "speex", false);
      break label144;
      label211:
      localag.a("vad_bos", "4000", false);
      localag.a("vad_eos", "700", false);
    }
  }
  
  public static void a(Context paramContext, ag paramag)
  {
    if ((TextUtils.isEmpty(paramag.e("net_type"))) && (!TextUtils.isEmpty(b)))
    {
      paramag.a("net_type", b, false);
      return;
    }
    if (paramContext == null)
    {
      paramag.a("net_type", "none", false);
      return;
    }
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext == null)
    {
      paramag.a("net_type", "none", false);
      return;
    }
    paramag.a("net_type", W.a(paramContext), false);
    paramag.a("net_subtype", ag.f(W.b(paramContext)), false);
  }
  
  private static void a(ag paramag)
  {
    if ((paramag == null) || (Setting.getLogLevel() == Setting.LOG_LEVEL.none)) {
      return;
    }
    String str2 = Setting.getLogPath();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "/sdcard/msc/msc.log";
    }
    int i = -1;
    if (Setting.getLogLevel() == Setting.LOG_LEVEL.detail) {
      i = 31;
    }
    for (;;)
    {
      S.b(str1);
      paramag.a("log", str1);
      paramag.a("lvl", "" + i);
      paramag.a("output", "1", false);
      return;
      if (Setting.getLogLevel() == Setting.LOG_LEVEL.normal) {
        i = 15;
      } else if (Setting.getLogLevel() == Setting.LOG_LEVEL.low) {
        i = 7;
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while ((!paramString.contains("sms")) && (!paramString.contains("iat"))) {
      return false;
    }
    return true;
  }
  
  public static String b(Context paramContext, B paramB)
  {
    ag localag = paramB.v().b();
    a(paramContext, localag);
    b(paramContext, localag);
    localag.a("result_type", "json");
    localag.a("rse", paramB.r());
    localag.a("text_encoding", paramB.q());
    localag.a("ssm", "1", false);
    localag.a("subject", "ivp", false);
    int i = paramB.s();
    localag.a("auf=audio/L16;rate", Integer.toString(i), false);
    if (i == 16000) {
      localag.a("aue", "speex-wb;10", false);
    }
    for (;;)
    {
      localag.a("vad_bos", "3000", false);
      localag.a("vad_eos", "700", false);
      localag.a(ah.c);
      return localag.toString();
      localag.a("aue", "speex", false);
    }
  }
  
  public static String b(Context paramContext, String paramString, B paramB)
  {
    if (paramString.equals("oneshot")) {
      return a(paramContext, paramB.v().e("cloud_grammar"), paramB);
    }
    paramString = paramB.v().b();
    int i = paramString.a("ivw_net_mode", 0);
    if (i == 0) {
      paramString.a("ivw_net_mode", "0", true);
    }
    for (;;)
    {
      paramString.a(ah.c);
      return paramString.toString();
      if ((2 == i) || (1 == i)) {
        paramString.a("ivw_net_mode", "1", true);
      } else if (W.a(paramContext)) {
        paramString.a("ivw_net_mode", "1", true);
      } else {
        paramString.a("ivw_net_mode", "0", true);
      }
    }
  }
  
  public static void b(Context paramContext, ag paramag)
  {
    int j = 0;
    long l = SystemClock.elapsedRealtime();
    if (paramContext == null) {}
    while (!N.b(paramContext)) {
      return;
    }
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        k = paramContext.getPhoneType();
        String str = paramContext.getNetworkOperator();
        m = Integer.parseInt(str.substring(0, 3));
        n = Integer.parseInt(str.substring(3));
        switch (k)
        {
        }
      }
      catch (Exception paramContext)
      {
        int k;
        int m;
        int n;
        ad.c("get mmlc failed");
        continue;
        int i = 0;
        continue;
      }
      paramag.a("mmlc", m + "|" + n + "|" + i + "|" + j);
      ad.c("MCC = " + m + "\t MNC = " + n + "\t phoneType = " + k + "\t LAC = " + i + "\t CID = " + j);
      ad.c("get mmlc time cost:" + (SystemClock.elapsedRealtime() - l));
      return;
      paramContext = (CdmaCellLocation)paramContext.getCellLocation();
      j = paramContext.getBaseStationId();
      i = paramContext.getNetworkId();
      continue;
      paramContext = (GsmCellLocation)paramContext.getCellLocation();
      j = paramContext.getCid();
      i = paramContext.getLac();
    }
  }
  
  private static void b(ag paramag)
  {
    String str = SpeechUtility.getUtility().getParameter("ver_tts");
    int i;
    if (!paramag.g("speed_increase"))
    {
      i = paramag.a("speed", 50);
      if (i > 100) {
        break label72;
      }
      paramag.a("speed", "" + i);
      paramag.a("speed_increase", "1");
    }
    label72:
    do
    {
      return;
      if ((100 < i) && (i <= 150) && ((TextUtils.isEmpty(str)) || (str.contains("5.5."))))
      {
        paramag.a("speed", "" + (i - 50));
        paramag.a("speed_increase", "2");
        return;
      }
    } while ((100 >= i) || (i > 200));
    paramag.a("speed", "" + (i - 100));
    paramag.a("speed_increase", "4");
  }
  
  public static String c(Context paramContext, B paramB)
  {
    ag localag = paramB.v().b();
    a(paramContext, localag);
    b(paramContext, localag);
    localag.a("ssm", "1", false);
    localag.a("result_type", "json", false);
    localag.a("rse", paramB.r(), false);
    localag.a("text_encoding", paramB.q(), false);
    localag.a(ah.c);
    return localag.toString();
  }
  
  public static String c(Context paramContext, ag paramag)
  {
    paramag = paramag.b();
    paramag.a("appid", SpeechUtility.getUtility().getParameter("appid"));
    paramag.a(M.b(paramContext));
    paramag.a("dvc", a(paramContext), false);
    paramag.a("aue", "raw", false);
    paramag.a(ah.c);
    return paramag.toString();
  }
  
  public static String c(Context paramContext, String paramString, B paramB)
  {
    paramString = paramB.v().b();
    a(paramContext, paramString);
    b(paramContext, paramString);
    paramString.a("sub", "mfv", false);
    paramString.a("prot_ver", "50", false);
    paramString.a("mver", "2.0", false);
    paramString.a("server_url", "http://imfv.openspeech.cn/msp.do", false);
    if ("verify".equals(paramString.e("sst"))) {
      paramString.a("scene_mode", "vfy", false);
    }
    for (;;)
    {
      paramString.a(ah.c);
      return paramString.toString();
      paramString.a("scene_mode", "gen", false);
    }
  }
  
  public static String d(Context paramContext, B paramB)
  {
    ag localag = paramB.v().b();
    a(paramContext, localag);
    b(paramContext, localag);
    localag.a("ssm", "1", false);
    int i = paramB.s();
    localag.a("auf=audio/L16;rate", Integer.toString(i));
    if (i == 16000) {
      localag.a("aue", "speex-wb", false);
    }
    for (;;)
    {
      localag.a("voice_name", localag.b("voice_name", a), true);
      localag.a("text_encoding", paramB.q(), false);
      b(localag);
      localag.a(ah.c);
      return localag.toString();
      localag.a("aue", "speex", false);
    }
  }
  
  public static String d(Context paramContext, ag paramag)
  {
    paramag = paramag.b();
    paramag.a("appid", SpeechUtility.getUtility().getParameter("appid"));
    paramag.a(M.b(paramContext));
    paramag.a("dvc", a(paramContext), false);
    paramag.a(ah.c);
    return paramag.toString();
  }
  
  public static String e(Context paramContext, B paramB)
  {
    ag localag = paramB.v().b();
    a(paramContext, localag);
    b(paramContext, localag);
    localag.a("ssm", "1", false);
    int i = paramB.s();
    localag.a("auf=audio/L16;rate", Integer.toString(i), false);
    if (i == 16000) {
      localag.a("aue", "speex-wb", false);
    }
    for (;;)
    {
      localag.a("text_encoding", paramB.q(), false);
      localag.a("plev", "1", false);
      localag.a("accent", "mandarin", false);
      localag.a("domain", "ise", false);
      localag.a("subject", "ise", false);
      localag.a("result_type", "xml", false);
      localag.a("vad_bos", "5000", false);
      localag.a("vad_eos", "1800", false);
      localag.a("vad_speech_timeout", "2147483647", false);
      localag.a(ah.c);
      return localag.toString();
      localag.a("aue", "speex", false);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */