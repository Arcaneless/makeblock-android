package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.bluetooth.BleManager;

public class HandShake1Command
  extends AirblockUpdateCommand
{
  private static final byte[] HandShakeSend = { Byte.MAX_VALUE };
  
  public HandShake1Command(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.CHECK_LOCK, paramString);
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
          Thread.sleep(1000L);
          BleManager.getInstance().writeToBluetooth(HandShake1Command.HandShakeSend);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          HandShake1Command.this.callback(false, "握手指令发送失败");
        }
      }
    }).start();
  }
  
  protected int getExecuteTime()
  {
    return 3000;
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length == 1) && (paramArrayOfByte[0] == 121))
    {
      callback(true, "握手成功");
      return;
    }
    callback(false, "握手失败");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\HandShake1Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */