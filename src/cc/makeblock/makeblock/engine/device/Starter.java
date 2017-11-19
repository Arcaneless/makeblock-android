package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.device.mode.ModeController;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.JoystickInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.UltrasonicRespond;

public class Starter
  extends MakeBlockDevice
{
  public static final DeviceBoardName DEVICE_BOARD_NAME = DeviceBoardName.orion;
  private int mode = 0;
  
  public Starter(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = super.equals(paramObject);
    if (bool) {
      return bool;
    }
    if ((paramObject instanceof Starter)) {
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
  
  public int getMode()
  {
    return this.mode;
  }
  
  protected void handleRJ25Respond(RJ25Respond paramRJ25Respond)
  {
    if (((paramRJ25Respond instanceof UltrasonicRespond)) && (this.mode == 1)) {
      ModeController.getInstance().setRobotMoveDueToUltrasonicChange(((UltrasonicRespond)paramRJ25Respond).distance);
    }
  }
  
  public void moveJoystick(int paramInt1, int paramInt2)
  {
    sendData(new JoystickInstruction((short)-paramInt1, (short)paramInt2).getBytes());
  }
  
  public void setMode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      ModeController.getInstance().setDeviceToManual();
    }
    for (;;)
    {
      this.mode = paramInt;
      return;
      ModeController.getInstance().setDeviceToObstacleAvoid();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\Starter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */