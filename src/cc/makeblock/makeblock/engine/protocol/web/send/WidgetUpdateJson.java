package cc.makeblock.makeblock.engine.protocol.web.send;

import cc.makeblock.makeblock.bean.WidgetUpdateBean;

public class WidgetUpdateJson
  extends BaseJsonObject
{
  private WidgetUpdateBean changeInfo;
  private int widgetId;
  
  public WidgetUpdateJson(int paramInt, WidgetUpdateBean paramWidgetUpdateBean)
  {
    this.widgetId = paramInt;
    this.changeInfo = paramWidgetUpdateBean;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\WidgetUpdateJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */