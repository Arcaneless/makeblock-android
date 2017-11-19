package ml.xuexin.bleconsultant;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ml.xuexin.bleconsultant.bluetooth.BleFlowValve;
import ml.xuexin.bleconsultant.bluetooth.Connector;
import ml.xuexin.bleconsultant.bluetooth.ScanUtil;
import ml.xuexin.bleconsultant.entity.BleDevice;
import ml.xuexin.bleconsultant.entity.BleStatus;
import ml.xuexin.bleconsultant.entity.WaitSendData;
import ml.xuexin.bleconsultant.exception.BleNotSupportException;
import ml.xuexin.bleconsultant.exception.InitBluetoothException;
import ml.xuexin.bleconsultant.exception.ThreadException;
import ml.xuexin.bleconsultant.port.CharacteristicNotifyListener;
import ml.xuexin.bleconsultant.port.ConnectCallback;
import ml.xuexin.bleconsultant.port.ConnectionStateListener;
import ml.xuexin.bleconsultant.port.ReadCallback;
import ml.xuexin.bleconsultant.port.RequestRssiCallback;
import ml.xuexin.bleconsultant.port.ScanDevicesHelper;
import ml.xuexin.bleconsultant.tool.BleLog;
import ml.xuexin.bleconsultant.tool.ThreadUtil;

public class BleConsultant
{
  public static final int DATA_MAX_LENGTH = 20;
  public static final int TIME_GAP = 10;
  private static BleConsultant instance;
  private Context applicationContext;
  private BleFlowValve bleFlowValve;
  private BluetoothAdapter bluetoothAdapter;
  private CharacteristicNotifyListener characteristicNotifyListener;
  private ConnectionStateListener connectionStateListener;
  private Connector connector;
  private Handler handler;
  private ScanCallBackRunnable scanCallBackRunnable;
  private ScanUtil scanUtil;
  
  private boolean connect(BluetoothDevice paramBluetoothDevice, ConnectCallback paramConnectCallback)
  {
    if (this.connector.getConnectStatus() == BleStatus.DISCONNECTED)
    {
      this.connector.connectDevice(paramBluetoothDevice, this.applicationContext, paramConnectCallback);
      return true;
    }
    return false;
  }
  
  public static BleConsultant getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new BleConsultant();
      }
      return instance;
    }
    finally {}
  }
  
  private void reset()
  {
    this.bleFlowValve.reset();
    this.handler.removeCallbacks(this.scanCallBackRunnable);
    this.scanUtil.reset();
  }
  
  private void startScan()
  {
    BleLog.w("startScan");
    this.scanUtil.startScan();
  }
  
  private void stopScan()
  {
    BleLog.w("stopScan");
    if (this.scanCallBackRunnable != null)
    {
      this.handler.removeCallbacks(this.scanCallBackRunnable);
      this.scanCallBackRunnable = null;
    }
    this.scanUtil.stopScan();
  }
  
  public boolean connect(BleDevice paramBleDevice, ConnectCallback paramConnectCallback)
  {
    stopScan();
    return connect(paramBleDevice.getBluetoothDevice(), paramConnectCallback);
  }
  
  public void disconnect()
  {
    this.bleFlowValve.reset();
    this.connector.disconnect();
    reset();
  }
  
  public BleStatus getBleStatus()
  {
    return this.connector.getConnectStatus();
  }
  
  public List<BleDevice> getScanDevices()
  {
    return this.scanUtil.getDevices();
  }
  
  public boolean hasConnected()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.connector != null)
    {
      bool1 = bool2;
      if (this.connector.getConnectStatus() == BleStatus.CONNECTED) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void init(Context paramContext)
  {
    init(paramContext, 20, 10);
  }
  
  public void init(Context paramContext, int paramInt1, int paramInt2)
  {
    if (!ThreadUtil.isMainThread()) {
      throw new ThreadException();
    }
    if (!paramContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
      throw new BleNotSupportException();
    }
    this.handler = new Handler();
    this.bluetoothAdapter = ((BluetoothManager)paramContext.getSystemService("bluetooth")).getAdapter();
    if (!isSupportBle()) {
      throw new InitBluetoothException();
    }
    this.scanUtil = new ScanUtil(this.bluetoothAdapter);
    this.connector = new Connector(this.connectionStateListener);
    this.bleFlowValve = new BleFlowValve(this.connector, paramInt1, paramInt2);
    this.applicationContext = paramContext.getApplicationContext();
  }
  
  public boolean isDiscovering()
  {
    if (isSupportBle()) {
      return this.bluetoothAdapter.isDiscovering();
    }
    return false;
  }
  
  public boolean isEnabled()
  {
    if (isSupportBle()) {
      return this.bluetoothAdapter.isEnabled();
    }
    return false;
  }
  
  public boolean isSupportBle()
  {
    return this.bluetoothAdapter != null;
  }
  
  public void onReceiveData(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    if (this.characteristicNotifyListener != null) {
      this.characteristicNotifyListener.onReceive(paramString1, paramString2, paramArrayOfByte);
    }
  }
  
  public boolean openBluetoothSilently()
  {
    if (!isSupportBle()) {
      throw new RuntimeException("bluetoothAdapter is null");
    }
    return this.bluetoothAdapter.enable();
  }
  
  public void printDebugLog(boolean paramBoolean)
  {
    BleLog.DEBUG = paramBoolean;
  }
  
  public boolean readCharacteristic(String paramString1, String paramString2, ReadCallback paramReadCallback, boolean paramBoolean)
  {
    if (hasConnected()) {
      return this.connector.readCharacteristic(paramString1, paramString2, paramReadCallback, paramBoolean);
    }
    return false;
  }
  
  public boolean registerNotify(String paramString1, String paramString2)
  {
    if (hasConnected()) {
      return this.connector.registerNotify(paramString1, paramString2);
    }
    return false;
  }
  
  public boolean requestCurrentRssi(RequestRssiCallback paramRequestRssiCallback)
  {
    if (hasConnected()) {
      return this.connector.requestRssi(paramRequestRssiCallback);
    }
    return false;
  }
  
  public boolean sendToBle(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    if (hasConnected()) {
      return this.bleFlowValve.sendData(new WaitSendData(paramArrayOfByte, paramString1, paramString2));
    }
    return false;
  }
  
  public void setConnectionStateListener(ConnectionStateListener paramConnectionStateListener)
  {
    this.connectionStateListener = paramConnectionStateListener;
    if (this.connector != null) {
      this.connector.setConnectionStateListener(paramConnectionStateListener);
    }
  }
  
  public void setNotifyListener(CharacteristicNotifyListener paramCharacteristicNotifyListener)
  {
    this.characteristicNotifyListener = paramCharacteristicNotifyListener;
  }
  
  public boolean setScanDevicesHelper(@Nullable ScanDevicesHelper paramScanDevicesHelper)
  {
    if (this.bluetoothAdapter.isEnabled()) {}
    for (;;)
    {
      try
      {
        stopScan();
      }
      catch (Exception paramScanDevicesHelper)
      {
        BleLog.e(paramScanDevicesHelper.getMessage());
      }
      this.scanCallBackRunnable = new ScanCallBackRunnable(paramScanDevicesHelper);
      this.handler.postDelayed(this.scanCallBackRunnable, paramScanDevicesHelper.getReportPeriod());
      startScan();
      return true;
      return false;
      if (paramScanDevicesHelper == null) {}
    }
  }
  
  public boolean unregisterNotify(String paramString1, String paramString2)
  {
    if (hasConnected()) {
      return this.connector.unregisterNotify(paramString1, paramString2);
    }
    return false;
  }
  
  private class ScanCallBackRunnable
    implements Runnable
  {
    private final ScanDevicesHelper scanDevicesHelper;
    
    public ScanCallBackRunnable(ScanDevicesHelper paramScanDevicesHelper)
    {
      this.scanDevicesHelper = paramScanDevicesHelper;
    }
    
    public void run()
    {
      Object localObject = BleConsultant.this.getScanDevices();
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        BleDevice localBleDevice = (BleDevice)((Iterator)localObject).next();
        if (this.scanDevicesHelper.deviceFilter(localBleDevice)) {
          localArrayList.add(localBleDevice);
        }
      }
      BleConsultant.this.handler.postDelayed(this, this.scanDevicesHelper.getReportPeriod());
      this.scanDevicesHelper.reportDevices(localArrayList);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\BleConsultant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */