package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class FirmwareRespond
  extends RJ25Respond
{
  public final String firmwareVersion;
  
  public FirmwareRespond(String paramString)
  {
    this.firmwareVersion = paramString;
  }
  
  public String toString()
  {
    return super.toString() + ", 固件版本:" + this.firmwareVersion;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\FirmwareRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */