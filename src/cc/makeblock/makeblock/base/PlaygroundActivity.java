package cc.makeblock.makeblock.base;

import android.os.Bundle;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public abstract class PlaygroundActivity<T extends DeviceViewModel>
  extends NotifyBluetoothActivity
  implements ControllerManager.OnDeviceChangeListener
{
  protected Device device;
  protected T viewModel;
  
  protected abstract T createViewModel();
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.device = ControllerManager.getInstance().getCurrentDevice();
    ControllerManager.getInstance().registerDeviceListener(this);
    this.viewModel = createViewModel();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ControllerManager.getInstance().unRegisterDeviceListener(this);
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if ((paramDevice.isConnected()) && (!this.device.equals(paramDevice)))
    {
      finish();
      return;
    }
    this.viewModel.setDevice(paramDevice);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\base\PlaygroundActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */