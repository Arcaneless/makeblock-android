package ml.xuexin.bleconsultant.port;

import java.util.List;
import ml.xuexin.bleconsultant.entity.BleDevice;

public abstract interface ScanDevicesHelper
{
  public abstract boolean deviceFilter(BleDevice paramBleDevice);
  
  public abstract long getReportPeriod();
  
  public abstract void reportDevices(List<BleDevice> paramList);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\port\ScanDevicesHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */