package cc.makeblock.makeblock.engine.firmware;

import cc.makeblock.makeblock.engine.Command.Builder;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import java.util.UUID;

public abstract class Programmer
{
  protected OnProgressChangeListener onProgressChangeListener;
  
  public Programmer(OnProgressChangeListener paramOnProgressChangeListener)
  {
    this.onProgressChangeListener = paramOnProgressChangeListener;
  }
  
  public abstract void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte);
  
  public abstract void onReceive(byte[] paramArrayOfByte);
  
  public abstract void startProgram();
  
  public void write(byte[] paramArrayOfByte)
  {
    ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(paramArrayOfByte).build());
  }
  
  public void write(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    paramArrayOfByte = new BleData(paramString1, paramString2, paramArrayOfByte);
    BleManager.getInstance().writeToBluetooth(paramArrayOfByte);
  }
  
  public static abstract interface OnProgressChangeListener
  {
    public abstract void onFail();
    
    public abstract void onProgressChange(float paramFloat);
    
    public abstract void onSuccess();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\Programmer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */