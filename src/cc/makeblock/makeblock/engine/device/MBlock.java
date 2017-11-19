package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.Device;
import java.util.HashMap;
import java.util.Map;

public class MBlock
  extends Device
{
  protected static final String UUID_NOTIFY = "0000ffe2-0000-1000-8000-00805f9b34fb";
  protected static final String UUID_SERVICE = "0000ffe1-0000-1000-8000-00805f9b34fb";
  protected static final String UUID_WRITE = "0000ffe3-0000-1000-8000-00805f9b34fb";
  
  public MBlock(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public Map<String, String> getNotifyUuid()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe2-0000-1000-8000-00805f9b34fb");
    return localHashMap;
  }
  
  public void receiveBleData(BleData paramBleData)
  {
    if (("0000ffe1-0000-1000-8000-00805f9b34fb".equals(paramBleData.uuidService)) && ("0000ffe2-0000-1000-8000-00805f9b34fb".equals(paramBleData.uuidCharacteristic))) {
      paramBleData = paramBleData.data;
    }
  }
  
  public final void sendData(byte[] paramArrayOfByte)
  {
    sendBleData(new BleData("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe3-0000-1000-8000-00805f9b34fb", paramArrayOfByte));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\MBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */