package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.device.operation.Joystick;
import cc.makeblock.makeblock.engine.device.operation.LedOnBoard;
import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTempo;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25InstructionFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.BuzzerInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.DummyJoystick2Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.JoystickInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.LedInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;

public class Ranger
  extends MakeBlockDevice
  implements Joystick, LedOnBoard, Buzzer
{
  public static final DeviceBoardName DEVICE_BOARD_NAME = DeviceBoardName.auriga;
  private int mode = 0;
  
  public Ranger(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public void buzz()
  {
    buzz(BuzzerTone.C5, new BuzzerTempo(1000).ONE_OF_FOUR);
  }
  
  public void buzz(BuzzerTone paramBuzzerTone, short paramShort)
  {
    sendData(new BuzzerInstruction((byte)45, paramBuzzerTone, paramShort).getBytes());
  }
  
  public boolean equals(Object paramObject)
  {
    if (super.equals(paramObject)) {}
    while ((paramObject instanceof Ranger)) {
      return true;
    }
    return false;
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
  
  public void ledOnBoard(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    sendData(new LedInstruction((byte)0, (byte)2, (byte)paramInt1, (byte)paramInt2, (byte)paramInt3, (byte)paramInt4).getBytes());
  }
  
  public void moveDummyJoystick2(short paramShort1, short paramShort2)
  {
    sendData(new DummyJoystick2Instruction(paramShort1, paramShort2).getBytes());
  }
  
  public void moveJoystick(int paramInt, float paramFloat)
  {
    int k = 0;
    int m = 0;
    int i;
    int j;
    if ((paramInt >= 0) && (paramInt <= 90))
    {
      i = 255;
      j = paramInt * 17 / 3 - 255;
    }
    for (;;)
    {
      if (paramFloat == 0.0F)
      {
        i = 0;
        j = 0;
      }
      moveJoystick((int)(i * paramFloat), (int)(j * paramFloat));
      return;
      if ((paramInt > 90) && (paramInt <= 180))
      {
        i = 765 - paramInt * 17 / 3;
        j = 255;
      }
      else if ((paramInt > -90) && (paramInt < 0))
      {
        i = 65281;
        j = paramInt * 17 / 3 + 255;
      }
      else
      {
        i = k;
        j = m;
        if (paramInt > 65356)
        {
          i = k;
          j = m;
          if (paramInt <= -90)
          {
            i = -paramInt * 17 / 3 - 765;
            j = 65281;
          }
        }
      }
    }
  }
  
  public void moveJoystick(int paramInt1, int paramInt2)
  {
    sendData(new JoystickInstruction((short)-paramInt2, (short)paramInt1).getBytes());
  }
  
  public void playTune(BuzzerTone paramBuzzerTone)
  {
    buzz(paramBuzzerTone, new BuzzerTempo(1000).ONE_OF_FOUR);
  }
  
  public void reset()
  {
    stop();
    turnOffLight();
  }
  
  public void setMode(int paramInt)
  {
    Instruction localInstruction;
    switch (paramInt)
    {
    default: 
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)17, (byte)0);
    }
    for (;;)
    {
      sendData(localInstruction.getBytes());
      this.mode = paramInt;
      return;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)17, (byte)1);
      continue;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)17, (byte)4);
      continue;
      localInstruction = RJ25InstructionFactory.createSetModeInstruction((byte)17, (byte)2);
    }
  }
  
  public void stop()
  {
    moveJoystick(0, 0);
  }
  
  public void turnOffLight()
  {
    ledOnBoard(0, 0, 0, 0);
  }
  
  public void turnOnLight()
  {
    ledOnBoard(0, 100, 100, 100);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\Ranger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */