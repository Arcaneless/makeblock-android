package cc.makeblock.makeblock.scene.playground.airblock;

import android.os.Bundle;
import android.view.Window;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.customview.dialog.AirblockTuneDialog;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.viewmodel.playground.airblock.AirBlockBaseViewModel;

public abstract class AirBlockActivity<T extends AirBlockBaseViewModel>
  extends PlaygroundActivity<T>
  implements AirBlockNavigator
{
  private ForceUpdateDialog forceUpdateDialog;
  
  public void forceUpdateAirBlock(Device paramDevice)
  {
    if ((paramDevice != null) && ((paramDevice instanceof AirBlock)))
    {
      paramDevice = ((AirBlock)paramDevice).getFirmwareVersion();
      if ((paramDevice != null) && (!paramDevice.equals(""))) {
        break label33;
      }
    }
    for (;;)
    {
      return;
      try
      {
        label33:
        paramDevice = paramDevice.split("\\.");
        if (Integer.parseInt(paramDevice[(paramDevice.length - 2)]) < AirBlock.forceUpdateVersion)
        {
          showForceUpdateAirBlockDialog();
          return;
        }
      }
      catch (Exception paramDevice) {}
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.forceUpdateDialog != null) && (this.forceUpdateDialog.isShowing())) {
      this.forceUpdateDialog.dismiss();
    }
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    super.onDeviceChange(paramDevice);
    if (paramDevice.isConnected()) {
      forceUpdateAirBlock(paramDevice);
    }
  }
  
  public void showForceUpdateAirBlockDialog()
  {
    if (this.forceUpdateDialog == null)
    {
      this.forceUpdateDialog = new ForceUpdateDialog(this);
      this.forceUpdateDialog.setOnOperationConfirmedListener(new ForceUpdateDialog.OnOperationConfirmedListener()
      {
        public void onOperationConfirmed()
        {
          ActivityJumpUtil.jumpToFirmwareUpdateActivity(AirBlockActivity.this);
          AirBlockActivity.this.forceUpdateDialog.dismiss();
        }
      });
    }
    if (!this.forceUpdateDialog.isShowing()) {
      this.forceUpdateDialog.show();
    }
  }
  
  public void showTuneDialog()
  {
    AirblockTuneDialog localAirblockTuneDialog = new AirblockTuneDialog(this);
    localAirblockTuneDialog.show();
    if (localAirblockTuneDialog.getWindow() != null) {
      localAirblockTuneDialog.getWindow().setLayout(ScreenHelper.getPercentWidthToPx(0.74375F), ScreenHelper.getPercentWidthToPx(0.54583335F));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\airblock\AirBlockActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */