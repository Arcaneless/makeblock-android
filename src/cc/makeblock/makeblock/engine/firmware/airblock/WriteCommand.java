package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class WriteCommand
  extends AirblockUpdateCommand
{
  private static final byte[] WriteCommand = { 49, -50 };
  
  public WriteCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.SEND_ADDRESS, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth(WriteCommand);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "写模式ok");
      return;
    }
    callback(false, "进入写模式出错");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\WriteCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */