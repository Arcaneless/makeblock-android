package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class TemperatureHumidityHumidityRespond
  extends RJ25Respond
{
  public final float value;
  
  public TemperatureHumidityHumidityRespond(float paramFloat)
  {
    this.value = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",温度:" + this.value;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\TemperatureHumidityHumidityRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */