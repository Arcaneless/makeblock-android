package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class BoardButtonRespond
  extends RJ25Respond
{
  public final float status;
  
  public BoardButtonRespond(float paramFloat)
  {
    this.status = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",板载按键状态:" + this.status;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\BoardButtonRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */