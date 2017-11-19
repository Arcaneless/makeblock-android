package cc.makeblock.makeblock.bean;

import java.util.Map;

public class WidgetBean
{
  public String className;
  public String config;
  public Map<String, Data> data;
  public String icon;
  public String iconName;
  public String icon_pre;
  public String name;
  public String type;
  
  public class Data
  {
    public String code;
    public String directControlType;
    public String port;
    public String portFilter;
    public String slot;
    public String xmlData;
    
    public Data() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\WidgetBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */