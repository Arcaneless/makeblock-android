package cc.makeblock.makeblock.scene.about;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;

public class AboutAppActivity
  extends BaseActivity
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296708: 
      StatisticsTool.getInstance().onEvent("OpenProductionTeam", "打开团队页面");
      startActivity(new Intent(this, AboutTeamActivity.class));
      return;
    }
    StatisticsTool.getInstance().onEvent("OpenSpecialThanks", "打开鸣谢页面");
    startActivity(new Intent(this, SpecialThanksActivity.class));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427355);
    paramBundle = "";
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      paramBundle = str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    findViewById(2131296708).setOnClickListener(this);
    findViewById(2131296712).setOnClickListener(this);
    ((AutoResizeTextView)findViewById(2131296298)).setText("v" + paramBundle);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\about\AboutAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */