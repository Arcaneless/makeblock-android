package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class GasRespond
  extends RJ25Respond
{
  public final float gas;
  
  public GasRespond(float paramFloat)
  {
    this.gas = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",气体:" + this.gas;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\GasRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */