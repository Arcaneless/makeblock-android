package cc.makeblock.makeblock.engine.protocol.web.send;

public class JoystickThrottleJson
  extends BaseJsonObject
{
  private String throttle1;
  private String throttle2;
  private String throttle3;
  private String throttle4;
  
  public JoystickThrottleJson(String paramString1, String paramString2)
  {
    this.throttle1 = paramString1;
    this.throttle2 = paramString2;
    this.throttle3 = paramString2;
    this.throttle4 = paramString1;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\JoystickThrottleJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */