package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.by;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class MobclickAgent
{
  private static final String a = "input map is null";
  private static final b b = new b();
  
  public static void enableEncrypt(boolean paramBoolean)
  {
    b.e(paramBoolean);
  }
  
  public static b getAgent()
  {
    return b;
  }
  
  public static void onEvent(Context paramContext, String paramString)
  {
    b.a(paramContext, paramString, null, -1L, 1);
  }
  
  public static void onEvent(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      by.c("label is null or empty");
      return;
    }
    b.a(paramContext, paramString1, paramString2, -1L, 1);
  }
  
  public static void onEvent(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    if (paramMap == null)
    {
      by.e("input map is null");
      return;
    }
    b.a(paramContext, paramString, new HashMap(paramMap), -1L);
  }
  
  public static void onEvent(Context paramContext, List<String> paramList, int paramInt, String paramString)
  {
    b.a(paramContext, paramList, paramInt, paramString);
  }
  
  public static void onEventValue(Context paramContext, String paramString, Map<String, String> paramMap, int paramInt)
  {
    if (paramMap == null) {}
    for (paramMap = new HashMap();; paramMap = new HashMap(paramMap))
    {
      paramMap.put("__ct__", Integer.valueOf(paramInt));
      b.a(paramContext, paramString, paramMap, -1L);
      return;
    }
  }
  
  public static void onKillProcess(Context paramContext)
  {
    b.d(paramContext);
  }
  
  public static void onPageEnd(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      b.b(paramString);
      return;
    }
    by.e("pageName is null or empty");
  }
  
  public static void onPageStart(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      b.a(paramString);
      return;
    }
    by.e("pageName is null or empty");
  }
  
  public static void onPause(Context paramContext)
  {
    b.b(paramContext);
  }
  
  public static void onProfileSignIn(String paramString)
  {
    onProfileSignIn("_adhoc", paramString);
  }
  
  public static void onProfileSignIn(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      by.d("uid is null");
      return;
    }
    if (paramString2.length() > 64)
    {
      by.d("uid is Illegal(length bigger then  legitimate length).");
      return;
    }
    if (TextUtils.isEmpty(paramString1))
    {
      b.a("_adhoc", paramString2);
      return;
    }
    if (paramString1.length() > 32)
    {
      by.d("provider is Illegal(length bigger then  legitimate length).");
      return;
    }
    b.a(paramString1, paramString2);
  }
  
  public static void onProfileSignOff()
  {
    b.b();
  }
  
  public static void onResume(Context paramContext)
  {
    if (paramContext == null)
    {
      by.e("unexpected null context in onResume");
      return;
    }
    b.a(paramContext);
  }
  
  public static void onSocialEvent(Context paramContext, String paramString, UMPlatformData... paramVarArgs)
  {
    if (paramContext == null)
    {
      by.e("context is null in onShareEvent");
      return;
    }
    com.umeng.analytics.social.d.d = "3";
    UMSocialService.share(paramContext, paramString, paramVarArgs);
  }
  
  public static void onSocialEvent(Context paramContext, UMPlatformData... paramVarArgs)
  {
    if (paramContext == null)
    {
      by.e("context is null in onShareEvent");
      return;
    }
    com.umeng.analytics.social.d.d = "3";
    UMSocialService.share(paramContext, paramVarArgs);
  }
  
  public static void openActivityDurationTrack(boolean paramBoolean)
  {
    b.b(paramBoolean);
  }
  
  public static void reportError(Context paramContext, String paramString)
  {
    b.a(paramContext, paramString);
  }
  
  public static void reportError(Context paramContext, Throwable paramThrowable)
  {
    b.a(paramContext, paramThrowable);
  }
  
  public static void setCatchUncaughtExceptions(boolean paramBoolean)
  {
    b.a(paramBoolean);
  }
  
  public static void setCheckDevice(boolean paramBoolean)
  {
    b.c(paramBoolean);
  }
  
  public static void setDebugMode(boolean paramBoolean)
  {
    b.d(paramBoolean);
  }
  
  public static void setLatencyWindow(long paramLong)
  {
    b.a(paramLong);
  }
  
  public static void setLocation(double paramDouble1, double paramDouble2)
  {
    b.a(paramDouble1, paramDouble2);
  }
  
  public static void setOpenGLContext(GL10 paramGL10)
  {
    b.a(paramGL10);
  }
  
  public static void setScenarioType(Context paramContext, EScenarioType paramEScenarioType)
  {
    b.a(paramContext, paramEScenarioType);
  }
  
  public static void setSecret(Context paramContext, String paramString)
  {
    b.b(paramContext, paramString);
  }
  
  public static void setSessionContinueMillis(long paramLong)
  {
    b.b(paramLong);
  }
  
  public static void startWithConfigure(UMAnalyticsConfig paramUMAnalyticsConfig)
  {
    if (paramUMAnalyticsConfig != null) {
      b.a(paramUMAnalyticsConfig);
    }
  }
  
  public static enum EScenarioType
  {
    private int a;
    
    static
    {
      E_UM_GAME = new EScenarioType("E_UM_GAME", 1, 1);
    }
    
    private EScenarioType(int paramInt)
    {
      this.a = paramInt;
    }
    
    public int toValue()
    {
      return this.a;
    }
  }
  
  public static class UMAnalyticsConfig
  {
    public String mAppkey = null;
    public String mChannelId = null;
    public Context mContext = null;
    public boolean mIsCrashEnable = true;
    public MobclickAgent.EScenarioType mType = MobclickAgent.EScenarioType.E_UM_NORMAL;
    
    private UMAnalyticsConfig() {}
    
    public UMAnalyticsConfig(Context paramContext, String paramString1, String paramString2)
    {
      this(paramContext, paramString1, paramString2, null, true);
    }
    
    public UMAnalyticsConfig(Context paramContext, String paramString1, String paramString2, MobclickAgent.EScenarioType paramEScenarioType)
    {
      this(paramContext, paramString1, paramString2, paramEScenarioType, true);
    }
    
    public UMAnalyticsConfig(Context paramContext, String paramString1, String paramString2, MobclickAgent.EScenarioType paramEScenarioType, boolean paramBoolean)
    {
      this.mContext = paramContext;
      this.mAppkey = paramString1;
      this.mChannelId = paramString2;
      this.mIsCrashEnable = paramBoolean;
      if (paramEScenarioType != null)
      {
        this.mType = paramEScenarioType;
        return;
      }
      switch (AnalyticsConfig.getVerticalType(paramContext))
      {
      default: 
        return;
      case 0: 
        this.mType = MobclickAgent.EScenarioType.E_UM_NORMAL;
        return;
      case 1: 
        this.mType = MobclickAgent.EScenarioType.E_UM_GAME;
        return;
      case 224: 
        this.mType = MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM;
        return;
      }
      this.mType = MobclickAgent.EScenarioType.E_UM_GAME_OEM;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\MobclickAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */