package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class FlameRespond
  extends RJ25Respond
{
  public final float flame;
  
  public FlameRespond(float paramFloat)
  {
    this.flame = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",火焰:" + this.flame;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\FlameRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */