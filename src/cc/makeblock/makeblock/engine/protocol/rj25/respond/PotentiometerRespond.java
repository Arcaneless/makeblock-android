package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class PotentiometerRespond
  extends RJ25Respond
{
  public final float potentiometer;
  
  public PotentiometerRespond(float paramFloat)
  {
    this.potentiometer = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",电位器:" + this.potentiometer;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\PotentiometerRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */