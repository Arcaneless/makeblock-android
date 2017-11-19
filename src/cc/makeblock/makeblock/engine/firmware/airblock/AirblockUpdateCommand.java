package cc.makeblock.makeblock.engine.firmware.airblock;

import android.os.Handler;

public abstract class AirblockUpdateCommand
{
  protected static final String BleSkipCheckUUID = "0000ffe9-0000-1000-8000-00805f9b34fb";
  protected static final byte[] Down = { 0 };
  protected static final byte Ok = 121;
  protected static final String PIO3_UUID = "0000ffe6-0000-1000-8000-00805f9b34fb";
  protected static final String PIO4_UUID = "0000ffe5-0000-1000-8000-00805f9b34fb";
  protected static final String ServiceUUID = "0000ffe4-0000-1000-8000-00805f9b34fb";
  protected static final byte[] Up = { 1 };
  protected AirblockProgrammer airblockProgrammer;
  private int executeTime = 0;
  private boolean hasCallback = false;
  
  public AirblockUpdateCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    this.airblockProgrammer = paramAirblockProgrammer;
    this.executeTime = getExecuteTime();
  }
  
  private final void timeout()
  {
    if (!this.hasCallback)
    {
      this.hasCallback = true;
      onTimeOut();
    }
  }
  
  public final void callback(boolean paramBoolean, String paramString)
  {
    if (!this.hasCallback)
    {
      this.hasCallback = true;
      doCallback(paramBoolean, paramString);
    }
  }
  
  protected String dataToString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      if (i == paramArrayOfByte.length - 1) {
        localStringBuffer.append(paramArrayOfByte[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuffer.append((paramArrayOfByte[i] & 0xFF) + " ");
      }
    }
    return localStringBuffer.toString();
  }
  
  protected abstract void doCallback(boolean paramBoolean, String paramString);
  
  protected abstract void doExecute();
  
  public final void execute()
  {
    this.hasCallback = false;
    doExecute();
    if (this.executeTime != 0) {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          AirblockUpdateCommand.this.timeout();
        }
      }, this.executeTime);
    }
  }
  
  protected abstract int getExecuteTime();
  
  protected void onTimeOut()
  {
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, getClass().getSimpleName() + "超时");
  }
  
  public abstract void receiveData(byte[] paramArrayOfByte);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\AirblockUpdateCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */