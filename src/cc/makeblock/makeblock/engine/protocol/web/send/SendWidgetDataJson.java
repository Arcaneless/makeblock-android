package cc.makeblock.makeblock.engine.protocol.web.send;

public class SendWidgetDataJson
  extends BaseJsonObject
{
  private final int widgetId;
  private final int widgetState;
  
  public SendWidgetDataJson(int paramInt1, int paramInt2)
  {
    this.widgetId = paramInt1;
    this.widgetState = paramInt2;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\SendWidgetDataJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */