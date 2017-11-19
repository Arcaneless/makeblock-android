package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class Erase2Command
  extends AirblockUpdateCommand
{
  private static final byte[] Erase = { -1, 0 };
  
  public Erase2Command(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.WRITE_COMMAND, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth(Erase);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "擦除2成功");
      return;
    }
    callback(false, "擦除失败");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\Erase2Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */