package cc.makeblock.makeblock.bean;

import java.io.Serializable;

public class ExpandGuideData
  implements Serializable
{
  public String formMsg;
  public String imgName;
  public Link link;
  public String playMsg;
  
  public static class Link
    implements Serializable
  {
    public String cn;
    public String en;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\bean\ExpandGuideData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */