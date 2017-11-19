package cc.makeblock.makeblock.viewmodel.slidemenu;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import ml.xuexin.bleconsultant.entity.BleDevice;

public class SearchDeviceModel
  extends BaseObservable
{
  private final BleDevice bleDevice;
  
  public SearchDeviceModel(BleDevice paramBleDevice)
  {
    this.bleDevice = paramBleDevice;
  }
  
  @Bindable
  public String getName()
  {
    return BleManager.getInstance().getFormatName(this.bleDevice);
  }
  
  @Bindable
  public float getSignalPercent()
  {
    int i = this.bleDevice.getRssi();
    if (i <= -100) {
      return 0.0F;
    }
    if (i > -40) {
      return 1.0F;
    }
    if (i > -50) {
      return 0.8F;
    }
    if (i > -60) {
      return 0.6F;
    }
    if (i > -70) {
      return 0.4F;
    }
    return 0.15F;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\slidemenu\SearchDeviceModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */