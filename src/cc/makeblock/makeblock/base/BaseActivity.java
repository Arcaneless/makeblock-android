package cc.makeblock.makeblock.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class BaseActivity
  extends FragmentActivity
{
  protected final void finishExitFlow()
  {
    super.finish();
    overridePendingTransition(2130771978, 2130771987);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    App.getContext().onCreateActivity(this);
    ScreenHelper.getDeviceInfo(this);
  }
  
  protected void onDestroy()
  {
    App.getContext().onDestroyActivity(this);
    super.onDestroy();
  }
  
  public void onPause()
  {
    StatisticsTool.getInstance().onPause(this);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    StatisticsTool.getInstance().onResume(this);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\base\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */