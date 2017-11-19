package ml.xuexin.bleconsultant.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.UUID;
import ml.xuexin.bleconsultant.BleConsultant;
import ml.xuexin.bleconsultant.entity.BleStatus;
import ml.xuexin.bleconsultant.port.ConnectCallback;
import ml.xuexin.bleconsultant.port.ConnectionStateListener;
import ml.xuexin.bleconsultant.port.ReadCallback;
import ml.xuexin.bleconsultant.port.RequestRssiCallback;
import ml.xuexin.bleconsultant.tool.BleLog;

public class Connector
  implements Resettable
{
  private static final UUID UUID_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
  private BluetoothGatt bluetoothGatt;
  private ReliableBluetoothGattCallback bluetoothGattCallback;
  private CharacteristicMap characteristicMap = new CharacteristicMap();
  private ConnectCallback connectCallback;
  private BleStatus connectStatus = BleStatus.DISCONNECTED;
  private ConnectionStateListener connectionStateListener;
  private ConnectorHandler handler = new ConnectorHandler(null);
  private ReadCallbackMap readCallbackMap = new ReadCallbackMap();
  private RequestRssiCallback requestRssiCallback;
  
  public Connector(ConnectionStateListener paramConnectionStateListener)
  {
    this.connectionStateListener = paramConnectionStateListener;
  }
  
  private void dispatchNotifyData(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    BleConsultant.getInstance().onReceiveData(paramString1, paramString2, paramArrayOfByte);
  }
  
  private BluetoothGattCharacteristic getCharacteristic(String paramString1, String paramString2)
  {
    return (BluetoothGattCharacteristic)this.characteristicMap.get(paramString1, paramString2);
  }
  
  private boolean hasConnected()
  {
    return (this.connectStatus == BleStatus.CONNECTED) && (this.bluetoothGatt != null);
  }
  
  private void onConnectCallback(int paramInt)
  {
    if (this.connectionStateListener != null) {
      this.connectionStateListener.onStateChange(paramInt);
    }
    if (this.connectCallback != null) {
      this.connectCallback.onStateChange(paramInt);
    }
    switch (paramInt)
    {
    case 1: 
    case 2: 
    case 3: 
    default: 
      return;
    case 5: 
    case 6: 
      if (this.bluetoothGatt != null) {
        this.bluetoothGatt.disconnect();
      }
    case 0: 
      reset();
    }
    ConnectorHandler localConnectorHandler1 = this.handler;
    ConnectorHandler localConnectorHandler2 = this.handler;
    localConnectorHandler1.removeMessages(2);
    this.connectCallback = null;
  }
  
  public void connectDevice(BluetoothDevice paramBluetoothDevice, Context paramContext, ConnectCallback paramConnectCallback)
  {
    this.connectCallback = paramConnectCallback;
    paramConnectCallback = this.handler;
    ConnectorHandler localConnectorHandler = this.handler;
    paramConnectCallback.sendEmptyMessageDelayed(2, this.connectCallback.getOvertimeTime());
    this.bluetoothGattCallback = new ReliableBluetoothGattCallback();
    this.bluetoothGatt = paramBluetoothDevice.connectGatt(paramContext, false, this.bluetoothGattCallback);
    this.connectStatus = BleStatus.CONNECTING;
  }
  
  public void disconnect()
  {
    if (this.bluetoothGatt != null) {
      this.bluetoothGatt.disconnect();
    }
    reset();
  }
  
  public BleStatus getConnectStatus()
  {
    return this.connectStatus;
  }
  
  public boolean readCharacteristic(String paramString1, String paramString2, ReadCallback paramReadCallback, boolean paramBoolean)
  {
    if ((paramBoolean) && (this.readCallbackMap.get(paramString1, paramString2) != null)) {
      return false;
    }
    BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)this.characteristicMap.get(paramString1, paramString2);
    this.readCallbackMap.put(paramString1, paramString2, paramReadCallback);
    Message localMessage = new Message();
    Object localObject = this.handler;
    localMessage.what = 6;
    localObject = localMessage.getData();
    ConnectorHandler localConnectorHandler = this.handler;
    ((Bundle)localObject).putString("SERVICE_UUID_KEY", paramString1);
    paramString1 = localMessage.getData();
    localObject = this.handler;
    paramString1.putString("CHARACTERISTIC_UUID_KEY", paramString2);
    this.handler.sendMessageDelayed(localMessage, paramReadCallback.getOvertimeTime());
    return this.bluetoothGatt.readCharacteristic(localBluetoothGattCharacteristic);
  }
  
  public boolean registerNotify(String paramString1, String paramString2)
  {
    paramString1 = getCharacteristic(paramString1, paramString2);
    return setCharacteristicNotification(this.bluetoothGatt, paramString1, true);
  }
  
  public boolean requestRssi(RequestRssiCallback paramRequestRssiCallback)
  {
    if (hasConnected())
    {
      this.requestRssiCallback = paramRequestRssiCallback;
      ConnectorHandler localConnectorHandler1 = this.handler;
      ConnectorHandler localConnectorHandler2 = this.handler;
      localConnectorHandler1.sendEmptyMessageDelayed(4, paramRequestRssiCallback.getOvertimeTime());
      return this.bluetoothGatt.readRemoteRssi();
    }
    return false;
  }
  
  public void reset()
  {
    this.connectStatus = BleStatus.DISCONNECTED;
    this.connectCallback = null;
    this.characteristicMap.clear();
    this.readCallbackMap.clear();
    ConnectorHandler localConnectorHandler1 = this.handler;
    ConnectorHandler localConnectorHandler2 = this.handler;
    localConnectorHandler1.removeMessages(2);
    localConnectorHandler1 = this.handler;
    localConnectorHandler2 = this.handler;
    localConnectorHandler1.removeMessages(4);
  }
  
  public boolean setCharacteristicNotification(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, boolean paramBoolean)
  {
    if ((paramBluetoothGatt == null) || (paramBluetoothGattCharacteristic == null)) {
      BleLog.w("gatt or characteristic equal null");
    }
    BluetoothGattDescriptor localBluetoothGattDescriptor;
    do
    {
      return false;
      if ((paramBluetoothGattCharacteristic.getProperties() & 0x10) == 0)
      {
        BleLog.e("Check characteristic property: false");
        return false;
      }
      boolean bool = paramBluetoothGatt.setCharacteristicNotification(paramBluetoothGattCharacteristic, paramBoolean);
      BleLog.d("setCharacteristicNotification----" + paramBoolean + "----success： " + bool + '\n' + "characteristic.getUuid() :  " + paramBluetoothGattCharacteristic.getUuid());
      localBluetoothGattDescriptor = paramBluetoothGattCharacteristic.getDescriptor(UUID_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR);
    } while (localBluetoothGattDescriptor == null);
    if (paramBoolean) {}
    for (paramBluetoothGattCharacteristic = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;; paramBluetoothGattCharacteristic = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)
    {
      localBluetoothGattDescriptor.setValue(paramBluetoothGattCharacteristic);
      return paramBluetoothGatt.writeDescriptor(localBluetoothGattDescriptor);
    }
  }
  
  public void setConnectionStateListener(ConnectionStateListener paramConnectionStateListener)
  {
    this.connectionStateListener = paramConnectionStateListener;
  }
  
  public boolean unregisterNotify(String paramString1, String paramString2)
  {
    paramString1 = getCharacteristic(paramString1, paramString2);
    return setCharacteristicNotification(this.bluetoothGatt, paramString1, false);
  }
  
  public boolean writeToBle(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    paramString1 = getCharacteristic(paramString1, paramString2);
    if (paramString1 != null) {
      try
      {
        paramString1.setValue(paramArrayOfByte);
        boolean bool = this.bluetoothGatt.writeCharacteristic(paramString1);
        return bool;
      }
      catch (Exception paramString1) {}
    }
    return false;
  }
  
  private class ConnectorHandler
    extends Handler
  {
    public static final String CHARACTERISTIC_UUID_KEY = "CHARACTERISTIC_UUID_KEY";
    public static final int CONNECT_MESSAGE = 1;
    public static final int CONNECT_OVERTIME_MESSAGE = 2;
    public static final int READ_MESSAGE = 5;
    public static final int READ_MESSAGE_OVERTIME = 6;
    public static final String RECEIVE_DATA_KEY = "RECEIVE_DATA_KEY";
    public static final int RECEIVE_MESSAGE = 0;
    public static final int REQUEST_RSSI_MESSAGE = 3;
    public static final int REQUEST_RSSI_OVERTIME = 4;
    public static final String SERVICE_UUID_KEY = "SERVICE_UUID_KEY";
    
    private ConnectorHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      byte[] arrayOfByte = paramMessage.getData().getByteArray("RECEIVE_DATA_KEY");
      String str1 = paramMessage.getData().getString("SERVICE_UUID_KEY");
      String str2 = paramMessage.getData().getString("CHARACTERISTIC_UUID_KEY");
      switch (paramMessage.what)
      {
      default: 
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
        do
        {
          do
          {
            do
            {
              do
              {
                return;
              } while ((arrayOfByte == null) || (str1 == null) || (str2 == null));
              Connector.this.dispatchNotifyData(str1, str2, arrayOfByte);
              return;
              Connector.this.onConnectCallback(paramMessage.arg1);
              return;
              Connector.this.onConnectCallback(5);
              return;
            } while (Connector.this.requestRssiCallback == null);
            removeMessages(4);
            Connector.this.requestRssiCallback.onReadRemoteRssi(paramMessage.arg1, paramMessage.arg2);
            return;
          } while (Connector.this.requestRssiCallback == null);
          Connector.this.requestRssiCallback.onOvertime();
          Connector.access$802(Connector.this, null);
          return;
        } while ((arrayOfByte == null) || (str1 == null) || (str2 == null));
        ReadCallback localReadCallback = (ReadCallback)Connector.this.readCallbackMap.get(str1, str2);
        if (localReadCallback != null) {
          localReadCallback.onCharacteristicRead(paramMessage.arg1, arrayOfByte);
        }
        Connector.this.handler.removeMessages(6);
        Connector.this.readCallbackMap.remove(str1, str2);
        return;
      }
      paramMessage = (ReadCallback)Connector.this.readCallbackMap.get(str1, str2);
      if (paramMessage != null) {
        paramMessage.onOvertime();
      }
      Connector.this.readCallbackMap.remove(str1, str2);
    }
  }
  
  private class ReliableBluetoothGattCallback
    extends BluetoothGattCallback
  {
    public ReliableBluetoothGattCallback() {}
    
    private boolean isLatestGattCallback()
    {
      return Connector.this.bluetoothGattCallback == this;
    }
    
    public void onCharacteristicChanged(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    {
      super.onCharacteristicChanged(paramBluetoothGatt, paramBluetoothGattCharacteristic);
      if (!isLatestGattCallback()) {
        return;
      }
      paramBluetoothGatt = paramBluetoothGattCharacteristic.getValue();
      Message localMessage = new Message();
      Object localObject = localMessage.getData();
      ((Bundle)localObject).putByteArray("RECEIVE_DATA_KEY", paramBluetoothGatt);
      localMessage.what = 0;
      localObject = paramBluetoothGattCharacteristic.getService().getUuid().toString();
      paramBluetoothGattCharacteristic = paramBluetoothGattCharacteristic.getUuid().toString();
      Bundle localBundle = localMessage.getData();
      localBundle.putString("SERVICE_UUID_KEY", (String)localObject);
      localBundle = localMessage.getData();
      localBundle.putString("CHARACTERISTIC_UUID_KEY", paramBluetoothGattCharacteristic);
      Connector.this.handler.sendMessage(localMessage);
      BleLog.d("onCharacteristicChanged, data:" + BleLog.parseByte(paramBluetoothGatt) + ", serviceUUID:" + (String)localObject + ", characteristicUUID:" + paramBluetoothGattCharacteristic);
    }
    
    public void onCharacteristicRead(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
      super.onCharacteristicRead(paramBluetoothGatt, paramBluetoothGattCharacteristic, paramInt);
      if (!isLatestGattCallback()) {
        return;
      }
      paramBluetoothGatt = new Message();
      paramBluetoothGatt.what = 5;
      paramBluetoothGatt.obj = paramBluetoothGattCharacteristic;
      Bundle localBundle = paramBluetoothGatt.getData();
      localBundle.putByteArray("RECEIVE_DATA_KEY", paramBluetoothGattCharacteristic.getValue());
      paramBluetoothGatt.arg1 = paramInt;
      Connector.this.handler.sendMessage(paramBluetoothGatt);
      BleLog.i("onCharacteristicRead");
    }
    
    public void onCharacteristicWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
      super.onCharacteristicWrite(paramBluetoothGatt, paramBluetoothGattCharacteristic, paramInt);
      if (!isLatestGattCallback()) {
        return;
      }
      BleLog.i("onCharacteristicWrite");
    }
    
    public void onConnectionStateChange(BluetoothGatt paramBluetoothGatt, int paramInt1, int paramInt2)
    {
      super.onConnectionStateChange(paramBluetoothGatt, paramInt1, paramInt2);
      paramInt1 = -1;
      switch (paramInt2)
      {
      }
      while (!isLatestGattCallback())
      {
        return;
        paramBluetoothGatt.discoverServices();
        paramInt1 = 2;
        continue;
        paramInt1 = 1;
        continue;
        paramInt2 = 0;
        paramBluetoothGatt.close();
        paramInt1 = paramInt2;
        if (Connector.this.bluetoothGatt == paramBluetoothGatt)
        {
          Connector.access$202(Connector.this, null);
          paramInt1 = paramInt2;
          continue;
          paramInt1 = 3;
        }
      }
      paramBluetoothGatt = new Message();
      paramBluetoothGatt.what = 1;
      paramBluetoothGatt.arg1 = paramInt1;
      Connector.this.handler.sendMessage(paramBluetoothGatt);
    }
    
    public void onReadRemoteRssi(BluetoothGatt paramBluetoothGatt, int paramInt1, int paramInt2)
    {
      super.onReadRemoteRssi(paramBluetoothGatt, paramInt1, paramInt2);
      if (!isLatestGattCallback()) {
        return;
      }
      paramBluetoothGatt = new Message();
      paramBluetoothGatt.what = 3;
      paramBluetoothGatt.arg1 = paramInt1;
      paramBluetoothGatt.arg2 = paramInt2;
      Connector.this.handler.sendMessage(paramBluetoothGatt);
    }
    
    public void onServicesDiscovered(BluetoothGatt paramBluetoothGatt, int paramInt)
    {
      super.onServicesDiscovered(paramBluetoothGatt, paramInt);
      if (!isLatestGattCallback()) {
        return;
      }
      if (paramInt == 0)
      {
        BleLog.w("onServicesDiscovered");
        Connector.access$402(Connector.this, BleStatus.CONNECTED);
        Connector.this.characteristicMap.setCharacteristics(paramBluetoothGatt.getServices());
      }
      for (paramInt = 4;; paramInt = 6)
      {
        paramBluetoothGatt = new Message();
        paramBluetoothGatt.what = 1;
        paramBluetoothGatt.arg1 = paramInt;
        Connector.this.handler.sendMessage(paramBluetoothGatt);
        return;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\bluetooth\Connector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */