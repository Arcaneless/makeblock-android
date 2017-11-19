package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.Command.Builder;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.firmware.HexHelper;

public class WriteCodeCommand
  extends AirblockUpdateCommand
{
  private HexHelper hexHelper;
  
  public WriteCodeCommand(AirblockProgrammer paramAirblockProgrammer, HexHelper paramHexHelper)
  {
    super(paramAirblockProgrammer);
    this.hexHelper = paramHexHelper;
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      if (this.hexHelper.isEnd())
      {
        this.airblockProgrammer.updateProgramStatus(UpdateStatus.LOCK, "写入完成");
        return;
      }
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.WRITE_COMMAND, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(this.hexHelper.getNextCodeCommand()).build());
  }
  
  protected int getExecuteTime()
  {
    return 5000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "写入成功");
      return;
    }
    callback(false, "写入数据失败");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\WriteCodeCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */