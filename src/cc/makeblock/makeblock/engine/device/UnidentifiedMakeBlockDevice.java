package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.action.ActionHandlerHolder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.FirmwareRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnidentifiedMakeBlockDevice
  extends MakeBlockDevice
{
  private List<DeviceStatusChangeListener> deviceStatusChangeListeners = new ArrayList();
  
  public UnidentifiedMakeBlockDevice(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = super.equals(paramObject);
    if (bool) {
      return bool;
    }
    if ((paramObject instanceof UnidentifiedMakeBlockDevice)) {
      return true;
    }
    return super.equals(paramObject);
  }
  
  public DeviceBoardName getBoardName()
  {
    return DeviceBoardName.unknown;
  }
  
  public String getDeviceName()
  {
    return null;
  }
  
  protected void handleRJ25Respond(RJ25Respond paramRJ25Respond)
  {
    if ((paramRJ25Respond instanceof FirmwareRespond))
    {
      setFirmwareVersion(((FirmwareRespond)paramRJ25Respond).firmwareVersion);
      paramRJ25Respond = null;
      switch (getBoardName(getFirmwareVersion()))
      {
      }
      while (paramRJ25Respond != null)
      {
        paramRJ25Respond.setFirmwareVersion(getFirmwareVersion());
        Iterator localIterator = this.deviceStatusChangeListeners.iterator();
        while (localIterator.hasNext()) {
          ((DeviceStatusChangeListener)localIterator.next()).onDeviceIdentified(paramRJ25Respond);
        }
        paramRJ25Respond = new Starter(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        continue;
        paramRJ25Respond = new Ultimate2(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        continue;
        paramRJ25Respond = new MBot(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        ((MBot)paramRJ25Respond).playStartAction();
        continue;
        paramRJ25Respond = new Ranger(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        continue;
        paramRJ25Respond = new AirBlock(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        continue;
        paramRJ25Respond = new SmartServo(BleManager.getInstance(), ActionHandlerHolder.getHandler());
        continue;
        paramRJ25Respond = new Codey(BleManager.getInstance(), ActionHandlerHolder.getHandler());
      }
    }
  }
  
  public void registerDeviceStatusChangeListener(DeviceStatusChangeListener paramDeviceStatusChangeListener)
  {
    this.deviceStatusChangeListeners.add(paramDeviceStatusChangeListener);
  }
  
  public void removeDeviceStatusChangeListener(DeviceStatusChangeListener paramDeviceStatusChangeListener)
  {
    this.deviceStatusChangeListeners.remove(paramDeviceStatusChangeListener);
  }
  
  public static abstract interface DeviceStatusChangeListener
  {
    public abstract void onDeviceIdentified(MakeBlockDevice paramMakeBlockDevice);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\UnidentifiedMakeBlockDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */