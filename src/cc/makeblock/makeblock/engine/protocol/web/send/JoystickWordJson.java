package cc.makeblock.makeblock.engine.protocol.web.send;

public class JoystickWordJson
  extends BaseJsonObject
{
  private String angle;
  private String delay;
  private String duration;
  private String param1;
  private String param2;
  private String param3;
  private String word;
  
  public JoystickWordJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this.word = paramString1;
    this.angle = paramString2;
    this.duration = paramString3;
    this.delay = paramString4;
    this.param1 = paramString5;
    this.param2 = paramString6;
    this.param3 = paramString7;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\JoystickWordJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */