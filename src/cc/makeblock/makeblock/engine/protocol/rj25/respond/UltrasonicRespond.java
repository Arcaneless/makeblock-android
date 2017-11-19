package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class UltrasonicRespond
  extends RJ25Respond
{
  public final float distance;
  
  public UltrasonicRespond(float paramFloat)
  {
    this.distance = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",距离:" + this.distance;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\UltrasonicRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */