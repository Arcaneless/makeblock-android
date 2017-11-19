package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class LineFollowerRespond
  extends RJ25Respond
{
  public final float line;
  
  public LineFollowerRespond(float paramFloat)
  {
    this.line = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",巡线:" + this.line;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\LineFollowerRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */