package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.firmware.HexHelper;

public class SendAddressCommand
  extends AirblockUpdateCommand
{
  private HexHelper hexHelper;
  
  public SendAddressCommand(AirblockProgrammer paramAirblockProgrammer, HexHelper paramHexHelper)
  {
    super(paramAirblockProgrammer);
    this.hexHelper = paramHexHelper;
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.WRITE_CODE, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    byte[] arrayOfByte = this.hexHelper.getNextAddressCommand();
    BleManager.getInstance().writeToBluetooth(arrayOfByte);
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "发送地址成功");
      return;
    }
    callback(false, "发送地址出错");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\SendAddressCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */