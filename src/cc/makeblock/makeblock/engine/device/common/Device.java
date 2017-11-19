package cc.makeblock.makeblock.engine.device.common;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import java.util.Map;

public abstract class Device
{
  protected final BleManager bleManager;
  protected final Handler handler;
  
  public Device(BleManager paramBleManager, Handler paramHandler)
  {
    this.handler = paramHandler;
    this.bleManager = paramBleManager;
  }
  
  public abstract Map<String, String> getNotifyUuid();
  
  public boolean isConnected()
  {
    return BleManager.getInstance().isConnected();
  }
  
  public abstract void receiveBleData(BleData paramBleData);
  
  protected final void sendBleData(final BleData paramBleData)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Device.this.bleManager.writeToBluetooth(paramBleData);
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\common\Device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */