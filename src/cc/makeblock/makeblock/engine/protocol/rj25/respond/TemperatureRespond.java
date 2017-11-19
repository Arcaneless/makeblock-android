package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class TemperatureRespond
  extends RJ25Respond
{
  public final float temperature;
  
  public TemperatureRespond(float paramFloat)
  {
    this.temperature = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",温度:" + this.temperature;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\TemperatureRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */