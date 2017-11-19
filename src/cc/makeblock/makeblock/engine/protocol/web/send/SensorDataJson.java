package cc.makeblock.makeblock.engine.protocol.web.send;

public class SensorDataJson
  extends BaseJsonObject
{
  private int forwardBackward;
  private int leftRight;
  
  public SensorDataJson(int paramInt1, int paramInt2)
  {
    this.leftRight = paramInt1;
    this.forwardBackward = paramInt2;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\SensorDataJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */