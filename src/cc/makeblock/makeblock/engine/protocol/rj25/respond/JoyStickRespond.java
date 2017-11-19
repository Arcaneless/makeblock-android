package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class JoyStickRespond
  extends RJ25Respond
{
  public final float rocker;
  
  public JoyStickRespond(float paramFloat)
  {
    this.rocker = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",摇杆:" + this.rocker;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\JoyStickRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */