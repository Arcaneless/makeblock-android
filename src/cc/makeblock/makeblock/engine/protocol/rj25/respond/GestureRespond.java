package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class GestureRespond
  extends RJ25Respond
{
  public final float gesture;
  
  public GestureRespond(float paramFloat)
  {
    this.gesture = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",姿态:" + this.gesture;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\GestureRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */