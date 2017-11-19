package cc.makeblock.makeblock.scene.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;

public class LoadingBuildingActivity
  extends BaseActivity
{
  private static final int REQUEST_CODE = 1;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1) {
      finish();
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427371);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ActivityJumpUtil.jumpToUnityActivity(LoadingBuildingActivity.this, 1);
      }
    }, 100L);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\main\LoadingBuildingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */