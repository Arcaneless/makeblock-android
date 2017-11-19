package cc.makeblock.makeblock.project;

import cc.makeblock.makeblock.engine.utils.TextUtil;
import java.util.List;

public class Playgrounds
{
  public List<Playground> expand;
  public List<Playground> play;
  
  public class Playground
  {
    public String action;
    public String cover;
    public String title;
    
    public Playground() {}
    
    public String getCoverPath()
    {
      return "file:///android_asset/settings/MakeblockAppResource/OfficialControlPanels/" + this.cover;
    }
    
    public String getTitleRes()
    {
      return TextUtil.getStringByKey(this.title);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\Playgrounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */