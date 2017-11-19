package cc.makeblock.makeblock.engine.device.programmer;

import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import cc.makeblock.makeblock.engine.firmware.airblock.AirblockProgrammer;

public class AirBlockProgrammer
  implements Programmer.OnProgressChangeListener
{
  private final AirBlock airBlock;
  private OnUpgradeProgressChangeListener listener;
  private final AirblockProgrammer programmer;
  
  public AirBlockProgrammer(AirBlock paramAirBlock)
  {
    this.airBlock = paramAirBlock;
    this.programmer = new AirblockProgrammer(this);
    this.listener = paramAirBlock;
  }
  
  public void onFail()
  {
    if (this.listener != null) {
      this.listener.onFail();
    }
  }
  
  public void onProgressChange(float paramFloat)
  {
    if (this.listener != null) {
      this.listener.onProgressChange(paramFloat);
    }
  }
  
  public void onSuccess()
  {
    if (this.listener != null) {
      this.listener.onSuccess();
    }
  }
  
  public void receiveData(BleData paramBleData)
  {
    this.programmer.onReceive(paramBleData.data);
  }
  
  public void startProgram()
  {
    this.programmer.startProgram();
  }
  
  public static abstract interface OnUpgradeProgressChangeListener
  {
    public abstract void onFail();
    
    public abstract void onProgressChange(float paramFloat);
    
    public abstract void onSuccess();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\programmer\AirBlockProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */