package cc.makeblock.makeblock.scene.controller;

public class PlaygroundItemData
{
  private String coverUri;
  private String title;
  
  public PlaygroundItemData(String paramString1, String paramString2)
  {
    this.title = paramString1;
    this.coverUri = ("asset:///settings/MakeblockAppResource/OfficialControlPanels/" + paramString2);
  }
  
  public String getCoverUri()
  {
    return this.coverUri;
  }
  
  public String getTitle()
  {
    return this.title;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\PlaygroundItemData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */