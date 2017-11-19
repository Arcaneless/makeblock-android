package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class LightRespond
  extends RJ25Respond
{
  public final float light;
  
  public LightRespond(float paramFloat)
  {
    this.light = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",光线:" + this.light;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\LightRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */