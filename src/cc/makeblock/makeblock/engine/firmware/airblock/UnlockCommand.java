package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class UnlockCommand
  extends AirblockUpdateCommand
{
  private static final byte[] Reply = { 121, 121 };
  private static final byte[] Unlock = { -110, 109 };
  private int index = 0;
  
  public UnlockCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.HAND_SHAKE_3, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth(Unlock);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    int i = 0;
    if ((i < paramArrayOfByte.length) && (this.index < Reply.length)) {
      if (paramArrayOfByte[i] != Reply[this.index]) {
        callback(false, "解锁出错");
      }
    }
    while (this.index != Reply.length)
    {
      return;
      this.index += 1;
      i += 1;
      break;
    }
    callback(true, "解锁成功");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\UnlockCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */