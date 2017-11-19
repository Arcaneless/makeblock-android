package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class Erase1Command
  extends AirblockUpdateCommand
{
  private static final byte[] Erase = { 67, -68 };
  
  public Erase1Command(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.ERASE_2, paramString);
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
      callback(true, "擦除1成功");
      return;
    }
    callback(false, "擦除失败");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\Erase1Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */