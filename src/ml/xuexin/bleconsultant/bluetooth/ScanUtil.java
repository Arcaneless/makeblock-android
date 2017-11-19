package ml.xuexin.bleconsultant.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings.Builder;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import ml.xuexin.bleconsultant.entity.BleDevice;
import ml.xuexin.bleconsultant.tool.BleLog;

public class ScanUtil
  implements Resettable
{
  private BluetoothAdapter bluetoothAdapter;
  private HashMap<BluetoothDevice, BleDevice> deviceMap;
  private Vector<BleDevice> deviceVector;
  private BluetoothAdapter.LeScanCallback leScanCallback;
  private ScanCallback scanCallback;
  
  public ScanUtil(BluetoothAdapter paramBluetoothAdapter)
  {
    this.bluetoothAdapter = paramBluetoothAdapter;
    this.deviceMap = new LinkedHashMap();
    this.deviceVector = new Vector();
  }
  
  private void onBleScanFail(int paramInt) {}
  
  private void onBleScanResult(BluetoothDevice paramBluetoothDevice, int paramInt)
  {
    long l = System.currentTimeMillis();
    BleDevice localBleDevice = (BleDevice)this.deviceMap.get(paramBluetoothDevice);
    if (localBleDevice == null)
    {
      localBleDevice = new BleDevice(paramBluetoothDevice, paramInt, l);
      this.deviceMap.put(paramBluetoothDevice, localBleDevice);
      this.deviceVector.add(localBleDevice);
    }
    for (paramBluetoothDevice = localBleDevice;; paramBluetoothDevice = localBleDevice)
    {
      BleLog.w(paramBluetoothDevice.toString());
      return;
      localBleDevice.setRssi(paramInt);
      localBleDevice.setRssiUpdateTime(l);
    }
  }
  
  public List<BleDevice> getDevices()
  {
    return new ArrayList(this.deviceVector);
  }
  
  public void reset()
  {
    this.deviceMap.clear();
    this.deviceVector.clear();
  }
  
  public void startScan()
  {
    this.deviceMap.clear();
    this.deviceVector.clear();
    if (Build.VERSION.SDK_INT < 21)
    {
      if (this.leScanCallback == null) {
        this.leScanCallback = new BluetoothAdapter.LeScanCallback()
        {
          public void onLeScan(BluetoothDevice paramAnonymousBluetoothDevice, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
          {
            ScanUtil.this.onBleScanResult(paramAnonymousBluetoothDevice, paramAnonymousInt);
          }
        };
      }
      if (!this.bluetoothAdapter.startLeScan(this.leScanCallback)) {
        onBleScanFail(-1);
      }
      return;
    }
    if (Build.VERSION.SDK_INT < 23) {}
    for (int i = 0;; i = 2)
    {
      if (this.scanCallback == null) {
        this.scanCallback = new ScanCallback()
        {
          public void onScanFailed(int paramAnonymousInt)
          {
            ScanUtil.this.onBleScanFail(paramAnonymousInt);
          }
          
          @RequiresApi(api=21)
          public void onScanResult(int paramAnonymousInt, ScanResult paramAnonymousScanResult)
          {
            ScanUtil.this.onBleScanResult(paramAnonymousScanResult.getDevice(), paramAnonymousScanResult.getRssi());
          }
        };
      }
      this.bluetoothAdapter.getBluetoothLeScanner().startScan(null, new ScanSettings.Builder().setScanMode(i).build(), this.scanCallback);
      return;
    }
  }
  
  public void stopScan()
  {
    if (Build.VERSION.SDK_INT < 21) {
      if (this.leScanCallback != null) {
        this.bluetoothAdapter.stopLeScan(this.leScanCallback);
      }
    }
    BluetoothLeScanner localBluetoothLeScanner;
    do
    {
      do
      {
        return;
      } while (this.scanCallback == null);
      localBluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
    } while (localBluetoothLeScanner == null);
    localBluetoothLeScanner.stopScan(this.scanCallback);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\bluetooth\ScanUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */