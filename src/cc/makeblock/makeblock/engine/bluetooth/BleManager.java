package cc.makeblock.makeblock.engine.bluetooth;

import android.content.Context;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.common.Device;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import ml.xuexin.bleconsultant.BleConsultant;
import ml.xuexin.bleconsultant.entity.BleDevice;
import ml.xuexin.bleconsultant.port.CharacteristicNotifyListener;
import ml.xuexin.bleconsultant.port.ConnectCallback;
import ml.xuexin.bleconsultant.port.ConnectionStateListener;
import ml.xuexin.bleconsultant.port.RequestRssiCallback;
import ml.xuexin.bleconsultant.port.ScanDevicesHelper;

public class BleManager
{
  public static final int STATE_CONNECTED = 2;
  public static final int STATE_CONNECTING = 1;
  public static final int STATE_DISCONNECTED = 0;
  private static final String TAG = BleManager.class.getSimpleName();
  public static final String UUID_NOTIFY = "0000ffe2-0000-1000-8000-00805f9b34fb";
  public static final String UUID_SERVICE = "0000ffe1-0000-1000-8000-00805f9b34fb";
  public static final String UUID_WRITE = "0000ffe3-0000-1000-8000-00805f9b34fb";
  private static BleManager instance = new BleManager();
  private BleConsultant bleConsultant;
  private CharacteristicNotifyListener characteristicNotifyListener;
  private BleDevice connectedDevice;
  private ConnectionStateListener connectionStateListener = new ConnectionStateListener()
  {
    public void onStateChange(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      case -1: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      default: 
        return;
      }
      BleManager.access$002(BleManager.this, null);
      ControllerManager.getInstance().onConnectDisconnected();
      BleManager.access$102(BleManager.this, -100);
    }
  };
  private Device currentDevice;
  private int currentRssi = -100;
  
  private double calculateAccuracy(int paramInt, double paramDouble)
  {
    if (paramDouble == 0.0D) {
      return -1.0D;
    }
    paramDouble = paramDouble * 1.0D / paramInt;
    if (paramDouble < 1.0D) {
      return Math.pow(paramDouble, 10.0D);
    }
    return 0.89976D * Math.pow(paramDouble, 7.7095D) + 0.111D;
  }
  
  public static BleManager getInstance()
  {
    return instance;
  }
  
  private void registerDeviceNotify(Map<String, String> paramMap)
  {
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        registerNotify((String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
  }
  
  private void registerNotify(String paramString1, String paramString2)
  {
    if (this.characteristicNotifyListener == null) {
      this.characteristicNotifyListener = new CharacteristicNotifyListener()
      {
        public void onReceive(String paramAnonymousString1, String paramAnonymousString2, byte[] paramAnonymousArrayOfByte)
        {
          if (BleManager.this.currentDevice != null) {
            BleManager.this.currentDevice.receiveBleData(new BleData(paramAnonymousString1, paramAnonymousString2, paramAnonymousArrayOfByte));
          }
        }
      };
    }
    this.bleConsultant.setNotifyListener(this.characteristicNotifyListener);
    this.bleConsultant.registerNotify(paramString1, paramString2);
  }
  
  public void connect(final BleDevice paramBleDevice, final BleConnectCallback paramBleConnectCallback)
  {
    if (isSupportBle()) {
      this.bleConsultant.connect(paramBleDevice, new ConnectCallback()
      {
        public long getOvertimeTime()
        {
          return 5000L;
        }
        
        public void onOvertime()
        {
          BleManager.this.disconnectBluetooth();
          if (paramBleConnectCallback != null) {
            paramBleConnectCallback.onStateChange(5);
          }
        }
        
        public void onStateChange(int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          }
          for (;;)
          {
            if (paramBleConnectCallback != null) {
              paramBleConnectCallback.onStateChange(paramAnonymousInt);
            }
            return;
            BleManager.this.registerNotify("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe2-0000-1000-8000-00805f9b34fb");
            BleManager.access$002(BleManager.this, paramBleDevice);
            continue;
            BleManager.access$002(BleManager.this, null);
          }
        }
      });
    }
  }
  
  public void disconnectBluetooth()
  {
    ControllerManager.getInstance().onConnectDisconnected();
    if (isSupportBle()) {
      this.bleConsultant.disconnect();
    }
    this.currentRssi = -100;
    this.connectedDevice = null;
  }
  
  public int getBluetoothConnectionState()
  {
    if (isSupportBle()) {}
    switch (this.bleConsultant.getBleStatus())
    {
    case ???: 
    default: 
      return 0;
    case ???: 
      return 2;
    }
    return 1;
  }
  
  public int getBluetoothRssi()
  {
    if (isSupportBle()) {
      this.bleConsultant.requestCurrentRssi(new RequestRssiCallback()
      {
        public long getOvertimeTime()
        {
          return 500L;
        }
        
        public void onOvertime() {}
        
        public void onReadRemoteRssi(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          BleManager.access$102(BleManager.this, paramAnonymousInt1);
        }
      });
    }
    return this.currentRssi;
  }
  
  public BleDevice getConnectedDevice()
  {
    return this.connectedDevice;
  }
  
  public double getDistance(BleDevice paramBleDevice)
  {
    return calculateAccuracy(-59, paramBleDevice.getRssi());
  }
  
  public String getFormatName(BleDevice paramBleDevice)
  {
    try
    {
      paramBleDevice = paramBleDevice.getName();
      if (!paramBleDevice.equalsIgnoreCase("Makeblock"))
      {
        boolean bool = paramBleDevice.equalsIgnoreCase("Makeblock_LE");
        if (!bool) {
          break label28;
        }
      }
    }
    catch (Exception paramBleDevice)
    {
      label28:
      for (;;) {}
    }
    return "Makeblock";
    paramBleDevice = paramBleDevice.replace("Makeblock_LE", "").replace("Makeblock", "");
    return paramBleDevice;
  }
  
  public void init(Context paramContext)
  {
    try
    {
      this.bleConsultant = BleConsultant.getInstance();
      this.bleConsultant.printDebugLog(App.isApkDebug());
      this.bleConsultant.init(paramContext);
      this.bleConsultant.setConnectionStateListener(this.connectionStateListener);
      return;
    }
    catch (Exception paramContext)
    {
      this.bleConsultant = null;
    }
  }
  
  public boolean isBluetoothEnable()
  {
    if (isSupportBle()) {
      return this.bleConsultant.isEnabled();
    }
    return false;
  }
  
  public boolean isConnected()
  {
    return this.connectedDevice != null;
  }
  
  public boolean isSupportBle()
  {
    return this.bleConsultant != null;
  }
  
  public void onExit()
  {
    disconnectBluetooth();
  }
  
  public void registerDevice(Device paramDevice)
  {
    this.currentDevice = paramDevice;
    if ((this.currentDevice != null) && (isConnected())) {
      registerDeviceNotify(this.currentDevice.getNotifyUuid());
    }
  }
  
  public void requestCurrentRssi(RequestRssiCallback paramRequestRssiCallback)
  {
    BleConsultant.getInstance().requestCurrentRssi(paramRequestRssiCallback);
  }
  
  public boolean startDiscovery(ScanDevicesHelper paramScanDevicesHelper)
  {
    if (isSupportBle()) {
      return this.bleConsultant.setScanDevicesHelper(paramScanDevicesHelper);
    }
    return false;
  }
  
  public void stopDiscovery()
  {
    if (isSupportBle()) {
      this.bleConsultant.setScanDevicesHelper(null);
    }
  }
  
  public void stopProgram()
  {
    this.bleConsultant.setScanDevicesHelper(null);
  }
  
  public boolean writeToBluetooth(BleData paramBleData)
  {
    if (isSupportBle()) {
      return this.bleConsultant.sendToBle(paramBleData.uuidService, paramBleData.uuidCharacteristic, paramBleData.data);
    }
    return false;
  }
  
  @Deprecated
  public boolean writeToBluetooth(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    return writeToBluetooth(new BleData(paramString1, paramString2, paramArrayOfByte));
  }
  
  @Deprecated
  public boolean writeToBluetooth(byte[] paramArrayOfByte)
  {
    return writeToBluetooth("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe3-0000-1000-8000-00805f9b34fb", paramArrayOfByte);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\bluetooth\BleManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */