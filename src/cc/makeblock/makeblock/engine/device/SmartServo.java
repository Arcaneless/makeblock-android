package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25InstructionFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;

public class SmartServo
  extends MakeBlockDevice
{
  public static final DeviceBoardName DEVICE_BOARD_NAME = DeviceBoardName.octopus;
  
  public SmartServo(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = super.equals(paramObject);
    if (bool) {
      return bool;
    }
    if ((paramObject instanceof SmartServo)) {
      return true;
    }
    return super.equals(paramObject);
  }
  
  public DeviceBoardName getBoardName()
  {
    return DEVICE_BOARD_NAME;
  }
  
  public String getDeviceName()
  {
    return DEVICE_BOARD_NAME.getDeviceName();
  }
  
  protected void handleRJ25Respond(RJ25Respond paramRJ25Respond) {}
  
  public void reset()
  {
    sendData(RJ25InstructionFactory.createResetSmartServoInstruction((byte)5).getBytes());
  }
  
  public void unlock()
  {
    sendData(RJ25InstructionFactory.createUnlockSmartServo((byte)5).getBytes());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\SmartServo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */