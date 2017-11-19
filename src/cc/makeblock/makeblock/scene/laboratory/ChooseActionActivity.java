package cc.makeblock.makeblock.scene.laboratory;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.databinding.ActivityLaboratoryChooseActionBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.diy.Widget;
import cc.makeblock.makeblock.viewmodel.laboratory.ChooseActionViewModel;

public class ChooseActionActivity
  extends NotifyBluetoothActivity
{
  public static final String START_UP_FROM_ENTRANCE = "START_UP_FROM_ENTRANCE";
  public static final String WIDGET_LOCATION = "WIDGET_LOCATION";
  
  public void finish()
  {
    super.finish();
  }
  
  public void finishWithAnimation()
  {
    finishExitFlow();
  }
  
  public void finishWithResult(Widget paramWidget)
  {
    boolean bool = getIntent().getBooleanExtra("START_UP_FROM_ENTRANCE", true);
    Intent localIntent = new Intent(this, LaboratoryPanelActivity.class);
    localIntent.putExtra("cp_widget", paramWidget);
    if (bool) {
      ActivityJumpUtil.jumpToLabPanelActivity(this, localIntent);
    }
    for (;;)
    {
      finish();
      return;
      setResult(-1, localIntent);
    }
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getIntArrayExtra("WIDGET_LOCATION");
    ((ActivityLaboratoryChooseActionBinding)DataBindingUtil.setContentView(this, 2131427368)).setViewModel(new ChooseActionViewModel(this, paramBundle));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\laboratory\ChooseActionActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */