package cc.makeblock.makeblock.scene.firmware;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.databinding.ActivityFirmwareInfoBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.viewmodel.firmware.FirmwareInfoActivityViewModel;

public class FirmwareInfoActivity
  extends BaseActivity
  implements ControllerManager.OnDeviceChangeListener
{
  public static final int REQUEST_CODE_FIRMWARE_UPDATE_RESULT = 1;
  private FirmwareInfoActivityViewModel viewModel;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.viewModel.onActivityResult(paramInt1, paramInt2);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = (ActivityFirmwareInfoBinding)DataBindingUtil.setContentView(this, 2131427364);
    if (!ControllerManager.getInstance().getCurrentDevice().isConnected())
    {
      finish();
      return;
    }
    this.viewModel = new FirmwareInfoActivityViewModel(this, ControllerManager.getInstance().getCurrentDevice());
    paramBundle.setViewModel(this.viewModel);
    this.viewModel.onCreate();
    ControllerManager.getInstance().registerDeviceListener(this);
  }
  
  protected void onDestroy()
  {
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    super.onDestroy();
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    this.viewModel.onConnectedDeviceChanged(paramDevice);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\firmware\FirmwareInfoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */