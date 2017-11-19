package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class LockCommand
  extends AirblockUpdateCommand
{
  private static final byte[] Lock = { -126, 125 };
  private static final byte[] Reply = { 121, 121 };
  private int replyIndex = 0;
  
  public LockCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.RESUME_BLE_CHECK, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth(Lock);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        if (paramArrayOfByte[i] == Reply[this.replyIndex])
        {
          int k = this.replyIndex + 1;
          this.replyIndex = k;
          if (k != Reply.length) {
            break label63;
          }
          callback(true, "加读保护成功");
        }
      }
      else {
        return;
      }
      callback(false, "加读保护失败");
      return;
      label63:
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\LockCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */