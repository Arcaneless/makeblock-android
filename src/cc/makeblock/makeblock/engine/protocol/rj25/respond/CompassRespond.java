package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class CompassRespond
  extends RJ25Respond
{
  public final float compass;
  
  public CompassRespond(float paramFloat)
  {
    this.compass = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",电子罗盘:" + this.compass;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\CompassRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */