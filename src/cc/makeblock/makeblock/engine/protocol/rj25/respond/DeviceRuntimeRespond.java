package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class DeviceRuntimeRespond
  extends RJ25Respond
{
  public final float pin;
  
  public DeviceRuntimeRespond(float paramFloat)
  {
    this.pin = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",数字管脚:" + this.pin;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\DeviceRuntimeRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */