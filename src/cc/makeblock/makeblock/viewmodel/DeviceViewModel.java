package cc.makeblock.makeblock.viewmodel;

import android.databinding.BaseObservable;
import cc.makeblock.makeblock.engine.device.common.Device;

public class DeviceViewModel<T extends Device>
  extends BaseObservable
{
  protected T device;
  
  public DeviceViewModel(T paramT)
  {
    this.device = paramT;
  }
  
  public void setDevice(T paramT)
  {
    this.device = paramT;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\DeviceViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */