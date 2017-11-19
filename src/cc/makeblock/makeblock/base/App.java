package cc.makeblock.makeblock.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.action.ActionHandlerHolder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.DeviceHelper;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.scene.main.MainActivity;
import com.iflytek.cloud.SpeechUtility;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import java.util.ArrayList;
import java.util.Iterator;

public class App
  extends Application
{
  private static final String SPEECH_APP_ID = "587457dc";
  private static Boolean isDebug = null;
  private static App mContext;
  private ArrayList<Activity> activities = new ArrayList();
  private RefWatcher refWatcher;
  
  private void autoAdapter()
  {
    if ((DeviceHelper.isTablet(this)) && (getResources().getDisplayMetrics().densityDpi < 480)) {
      getResources().getDisplayMetrics().densityDpi = 480;
    }
  }
  
  private void cleanActivityStack()
  {
    Iterator localIterator = this.activities.iterator();
    while (localIterator.hasNext()) {
      ((Activity)localIterator.next()).finish();
    }
  }
  
  public static App getContext()
  {
    return mContext;
  }
  
  public static RefWatcher getRefWatcher()
  {
    return mContext.refWatcher;
  }
  
  public static boolean isApkDebug()
  {
    if (isDebug != null) {
      return isDebug.booleanValue();
    }
    try
    {
      if ((getContext().getApplicationInfo().flags & 0x2) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        isDebug = Boolean.valueOf(bool);
        bool = isDebug.booleanValue();
        return bool;
      }
      return false;
    }
    catch (Exception localException) {}
  }
  
  public void onCreate()
  {
    autoAdapter();
    super.onCreate();
    mContext = this;
    StatisticsTool.getInstance().initStatistics(this, isApkDebug());
    this.refWatcher = LeakCanary.install(this);
    new Thread(new Runnable()
    {
      public void run()
      {
        SpeechUtility.createUtility(App.this, "appid=587457dc");
      }
    }).start();
    BleManager.getInstance().init(this);
    ActionHandlerHolder.initActionHandler();
    ControllerManager.getInstance().init();
    try
    {
      int i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      if (i > SharedPreferencesUtils.getApplicationNewVersion()) {
        SharedPreferencesUtils.setApplicationNewVersion(i);
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
  }
  
  public void onCreateActivity(Activity paramActivity)
  {
    this.activities.add(paramActivity);
  }
  
  public void onDestroyActivity(Activity paramActivity)
  {
    this.activities.remove(paramActivity);
    if (this.activities.size() == 0) {
      ControllerManager.getInstance().onExitApp();
    }
  }
  
  public void restart(Activity paramActivity)
  {
    ControllerManager.getInstance().disconnect();
    cleanActivityStack();
    Intent localIntent = new Intent(paramActivity, MainActivity.class);
    localIntent.putExtra("is_enter_connect", true);
    paramActivity.startActivity(localIntent);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\base\App.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */