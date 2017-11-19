package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25InstructionFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.DummyJoystick2Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.JoystickInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;

public class Ultimate2
  extends MakeBlockDevice
{
  public static final DeviceBoardName DEVICE_BOARD_NAME = DeviceBoardName.megaPi;
  private int mode = 0;
  
  public Ultimate2(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = super.equals(paramObject);
    if (bool) {
      return bool;
    }
    if ((paramObject instanceof Ultimate2)) {
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
  
  protected void handleRJ25Respond(RJ25Respond paramRJ25Respond) {}
  
  public void moveDummyJoystick2(short paramShort1, short paramShort2)
  {
    sendData(new DummyJoystick2Instruction(paramShort1, paramShort2).getBytes());
  }
  
  public void moveJoystick(int paramInt1, int paramInt2)
  {
    sendData(new JoystickInstruction((short)-paramInt2, (short)paramInt1).getBytes());
  }
  
  public void setMode(int paramInt)
  {
    Instruction localInstruction;
    switch (paramInt)
    {
    default: 
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)18, (byte)0);
    }
    for (;;)
    {
      sendData(localInstruction.getBytes());
      this.mode = paramInt;
      return;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)18, (byte)1);
      continue;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)18, (byte)4);
      continue;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)18, (byte)2);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\Ultimate2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */