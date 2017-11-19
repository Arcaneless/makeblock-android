package cc.makeblock.makeblock.engine.statistics;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;

public class StatisticsTool
{
  private static StatisticsTool instance;
  private Context context;
  private FirebaseAnalytics mFirebaseAnalytics;
  
  public static StatisticsTool getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new StatisticsTool();
      }
      return instance;
    }
    finally {}
  }
  
  public void initStatistics(Application paramApplication, boolean paramBoolean)
  {
    this.context = paramApplication;
    MobclickAgent.setScenarioType(paramApplication, MobclickAgent.EScenarioType.E_UM_NORMAL);
    MobclickAgent.setCheckDevice(false);
    if (paramBoolean) {
      MobclickAgent.setCatchUncaughtExceptions(false);
    }
    for (;;)
    {
      this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.context);
      return;
      MobclickAgent.setCatchUncaughtExceptions(true);
    }
  }
  
  public void onEvent(String paramString)
  {
    onEvent(paramString, paramString);
  }
  
  public void onEvent(String paramString1, String paramString2)
  {
    MobclickAgent.onEvent(this.context, paramString1, paramString2);
    Bundle localBundle = new Bundle();
    localBundle.putString("item_id", paramString1);
    localBundle.putString("value", paramString2);
    this.mFirebaseAnalytics.logEvent("自定义事件", localBundle);
  }
  
  public void onPause(Context paramContext)
  {
    MobclickAgent.onPause(paramContext);
  }
  
  public void onResume(Context paramContext)
  {
    MobclickAgent.onResume(paramContext);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\statistics\StatisticsTool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */