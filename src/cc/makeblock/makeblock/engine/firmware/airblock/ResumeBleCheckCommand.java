package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class ResumeBleCheckCommand
  extends AirblockUpdateCommand
{
  public ResumeBleCheckCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.EXIT_BOOT, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    BleManager.getInstance().writeToBluetooth("0000ffe4-0000-1000-8000-00805f9b34fb", "0000ffe9-0000-1000-8000-00805f9b34fb", Down);
    callback(true, "这一步其实不确定是否成功的");
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\ResumeBleCheckCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */