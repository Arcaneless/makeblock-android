package cc.makeblock.makeblock.engine.firmware.airblock;

public class FailRequestAngleCommand
  extends AirblockUpdateCommand
{
  public FailRequestAngleCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.SKIP_BLE_CHECK, "直接开始");
  }
  
  public void doExecute()
  {
    callback(true, "从引擎那边获取数据失败");
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\FailRequestAngleCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */