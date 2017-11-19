package ml.xuexin.bleconsultant.entity;

import android.bluetooth.BluetoothDevice;

public class BleDevice
{
  private final BluetoothDevice bluetoothDevice;
  private final String name;
  private int rssi;
  private long rssiUpdateTime;
  
  public BleDevice(BluetoothDevice paramBluetoothDevice, int paramInt, long paramLong)
  {
    this.bluetoothDevice = paramBluetoothDevice;
    this.rssi = paramInt;
    this.rssiUpdateTime = paramLong;
    this.name = paramBluetoothDevice.getName();
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BleDevice)) {
      return this.bluetoothDevice.equals(((BleDevice)paramObject).bluetoothDevice);
    }
    return super.equals(paramObject);
  }
  
  public String getAddress()
  {
    return this.bluetoothDevice.getAddress();
  }
  
  public BluetoothDevice getBluetoothDevice()
  {
    return this.bluetoothDevice;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getRssi()
  {
    return this.rssi;
  }
  
  public long getRssiUpdateTime()
  {
    return this.rssiUpdateTime;
  }
  
  public int hashCode()
  {
    return this.bluetoothDevice.hashCode();
  }
  
  public void setRssi(int paramInt)
  {
    this.rssi = paramInt;
  }
  
  public void setRssiUpdateTime(long paramLong)
  {
    this.rssiUpdateTime = paramLong;
  }
  
  public String toString()
  {
    return new StringBuilder().append("name:").append(getName()).toString() + new StringBuilder().append(", address:").append(getAddress()).toString() + new StringBuilder().append(", rssi:").append(getRssi()).toString();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\entity\BleDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */