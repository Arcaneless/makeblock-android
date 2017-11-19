package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class TouchRespond
  extends RJ25Respond
{
  public final float status;
  
  public TouchRespond(float paramFloat)
  {
    this.status = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",触摸状态:" + this.status;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\TouchRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */