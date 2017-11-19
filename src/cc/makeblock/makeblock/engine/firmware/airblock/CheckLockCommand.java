package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class CheckLockCommand
  extends AirblockUpdateCommand
{
  private static final String HasLockMessage = "有锁";
  private static final String NoLockMessage = "没锁";
  private static final byte[] RequestLockSend = { 17, -18 };
  
  public CheckLockCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      if ("有锁".equals(paramString))
      {
        this.airblockProgrammer.updateProgramStatus(UpdateStatus.UNLOCK, paramString);
        return;
      }
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.ENTER_BOOT_2, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth(RequestLockSend);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "没锁");
      return;
    }
    callback(true, "有锁");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\CheckLockCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */