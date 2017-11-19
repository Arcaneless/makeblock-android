package cc.makeblock.makeblock.engine.protocol.neuron;

public class AirBlockStateRespond
  extends NeuronRespond
{
  public static final byte cmd = 67;
  public final float battery;
  public final boolean land;
  public final boolean switchOn;
  
  public AirBlockStateRespond(float paramFloat, int paramInt1, int paramInt2)
  {
    this.battery = (paramFloat / 100.0F);
    if (paramInt1 == 1)
    {
      bool1 = true;
      this.switchOn = bool1;
      if (paramInt2 != 1) {
        break label51;
      }
    }
    label51:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.land = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public String toString()
  {
    return super.toString() + ": 状态，电量：" + this.battery + " 开关打开：" + this.switchOn + " 着陆：" + this.land;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockStateRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */