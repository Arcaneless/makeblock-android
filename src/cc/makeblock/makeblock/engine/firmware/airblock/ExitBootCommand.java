package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class ExitBootCommand
  extends AirblockUpdateCommand
{
  public ExitBootCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.SET_OFFSET_ANGLE, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
  
  public void doExecute()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          BleManager.getInstance().writeToBluetooth("0000ffe4-0000-1000-8000-00805f9b34fb", "0000ffe5-0000-1000-8000-00805f9b34fb", AirblockUpdateCommand.Down);
          Thread.sleep(20L);
          BleManager.getInstance().writeToBluetooth("0000ffe4-0000-1000-8000-00805f9b34fb", "0000ffe6-0000-1000-8000-00805f9b34fb", AirblockUpdateCommand.Up);
          Thread.sleep(20L);
          BleManager.getInstance().writeToBluetooth("0000ffe4-0000-1000-8000-00805f9b34fb", "0000ffe5-0000-1000-8000-00805f9b34fb", AirblockUpdateCommand.Up);
          Thread.sleep(20L);
          BleManager.getInstance().writeToBluetooth("0000ffe4-0000-1000-8000-00805f9b34fb", "0000ffe6-0000-1000-8000-00805f9b34fb", AirblockUpdateCommand.Down);
          ExitBootCommand.this.callback(true, "退出boot模式指令已发送");
          return;
        }
        catch (Exception localException)
        {
          ExitBootCommand.this.callback(false, "退出boot模式出错");
        }
      }
    }).start();
  }
  
  protected int getExecuteTime()
  {
    return 1000;
  }
  
  public void receiveData(byte[] paramArrayOfByte) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\ExitBootCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */