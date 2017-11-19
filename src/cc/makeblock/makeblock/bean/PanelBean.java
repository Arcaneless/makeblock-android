package cc.makeblock.makeblock.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class PanelBean
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public ArrayList<GroupBean> groups;
  public String icon;
  public String icon_pre;
  public String name;
  
  public String toString()
  {
    return "PanelBean [name=" + this.name + ", icon=" + this.icon + ", groups=" + this.groups + "]";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\PanelBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */