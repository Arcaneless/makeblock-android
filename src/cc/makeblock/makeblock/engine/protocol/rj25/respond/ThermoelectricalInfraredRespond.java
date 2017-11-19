package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class ThermoelectricalInfraredRespond
  extends RJ25Respond
{
  public final float value;
  
  public ThermoelectricalInfraredRespond(float paramFloat)
  {
    this.value = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",热释电红外传感器:" + this.value;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\ThermoelectricalInfraredRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */