package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class LimitingStopperRespond
  extends RJ25Respond
{
  public final float limitingStopper;
  
  public LimitingStopperRespond(float paramFloat)
  {
    this.limitingStopper = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",限位器:" + this.limitingStopper;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\LimitingStopperRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */