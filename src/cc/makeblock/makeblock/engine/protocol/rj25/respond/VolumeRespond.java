package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class VolumeRespond
  extends RJ25Respond
{
  public final float volume;
  
  public VolumeRespond(float paramFloat)
  {
    this.volume = paramFloat;
  }
  
  public String toString()
  {
    return super.toString() + ",音量:" + this.volume;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\VolumeRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */