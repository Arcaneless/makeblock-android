package ml.xuexin.bleconsultant.bluetooth;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import ml.xuexin.bleconsultant.entity.UuidMap;

public class CharacteristicMap
  extends UuidMap<BluetoothGattCharacteristic>
{
  public void setCharacteristics(List<BluetoothGattService> paramList)
  {
    if (paramList == null) {
      throw new RuntimeException("List of services is null");
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      BluetoothGattService localBluetoothGattService = (BluetoothGattService)paramList.next();
      Iterator localIterator = localBluetoothGattService.getCharacteristics().iterator();
      while (localIterator.hasNext())
      {
        BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)localIterator.next();
        put(localBluetoothGattService.getUuid().toString(), localBluetoothGattCharacteristic.getUuid().toString(), localBluetoothGattCharacteristic);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\bluetooth\CharacteristicMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */