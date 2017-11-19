package cc.makeblock.makeblock.bean;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.Map;

public class WidgetData
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public String boardName;
  public String code;
  public String config;
  public String directControlType;
  public String icon;
  public String icon_pre;
  public String name;
  public String port;
  public String portFilter;
  public String slot;
  public String type;
  public int widgetID;
  public int xPosition;
  public String xibName;
  public String xmlData;
  public int yPosition;
  
  public WidgetData() {}
  
  public WidgetData(String paramString, WidgetBean paramWidgetBean)
  {
    this.boardName = paramString;
    if (paramWidgetBean.data != null)
    {
      WidgetBean.Data localData = (WidgetBean.Data)paramWidgetBean.data.get(paramString);
      paramString = localData;
      if (localData == null) {
        paramString = (WidgetBean.Data)paramWidgetBean.data.get("default");
      }
      if (paramString != null)
      {
        this.code = paramString.code;
        this.directControlType = paramString.directControlType;
        this.port = paramString.port;
        this.slot = paramString.slot;
        this.portFilter = paramString.portFilter;
        this.xmlData = paramString.xmlData;
      }
    }
    this.xibName = paramWidgetBean.className;
    this.type = paramWidgetBean.type;
    this.config = paramWidgetBean.config;
    this.icon = paramWidgetBean.icon;
    this.icon_pre = paramWidgetBean.icon_pre;
    this.name = paramWidgetBean.name;
  }
  
  public WidgetData deepCopy()
  {
    WidgetData localWidgetData = new WidgetData();
    localWidgetData.portFilter = this.portFilter;
    localWidgetData.xibName = this.xibName;
    localWidgetData.xPosition = this.xPosition;
    localWidgetData.yPosition = this.yPosition;
    localWidgetData.widgetID = this.widgetID;
    localWidgetData.type = this.type;
    localWidgetData.xmlData = this.xmlData;
    localWidgetData.code = this.code;
    localWidgetData.slot = this.slot;
    localWidgetData.config = this.config;
    localWidgetData.port = this.port;
    localWidgetData.directControlType = this.directControlType;
    localWidgetData.icon = this.icon;
    localWidgetData.icon_pre = this.icon_pre;
    localWidgetData.name = this.name;
    localWidgetData.boardName = this.boardName;
    return localWidgetData;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WidgetData)) && (toString().equalsIgnoreCase(paramObject.toString()));
  }
  
  public String getWidgetAddJson()
  {
    WidgetAddBean localWidgetAddBean = new WidgetAddBean(null);
    localWidgetAddBean.id = String.valueOf(this.widgetID);
    localWidgetAddBean.name = this.name;
    if (TextUtils.isEmpty(this.xmlData)) {}
    for (localWidgetAddBean.xmlData = "";; localWidgetAddBean.xmlData = this.xmlData)
    {
      localWidgetAddBean.xib = this.xibName;
      localWidgetAddBean.type = this.type;
      return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(localWidgetAddBean);
    }
  }
  
  public WidgetUpdateBean getWidgetUpdateJson()
  {
    WidgetUpdateBean localWidgetUpdateBean = new WidgetUpdateBean();
    localWidgetUpdateBean.name = this.name;
    localWidgetUpdateBean.port = this.port;
    localWidgetUpdateBean.slot = this.slot;
    return localWidgetUpdateBean;
  }
  
  public String toString()
  {
    return "WidgetData{portFilter='" + this.portFilter + '\'' + ", xibName='" + this.xibName + '\'' + ", xPosition=" + this.xPosition + ", yPosition=" + this.yPosition + ", widgetID=" + this.widgetID + ", type='" + this.type + '\'' + ", xmlData='" + this.xmlData + '\'' + ", code='" + this.code + '\'' + ", slot='" + this.slot + '\'' + ", config='" + this.config + '\'' + ", port='" + this.port + '\'' + ", directControlType='" + this.directControlType + '\'' + ", icon='" + this.icon + '\'' + ", icon_pre='" + this.icon_pre + '\'' + ", name='" + this.name + '\'' + '}';
  }
  
  private class WidgetAddBean
  {
    public String code;
    public String id;
    public String name;
    public String type;
    public String xib;
    public String xmlData;
    
    private WidgetAddBean() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\WidgetData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */