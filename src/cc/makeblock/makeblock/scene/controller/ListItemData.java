package cc.makeblock.makeblock.scene.controller;

import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices.Device.LanguageUnitData;
import cc.makeblock.makeblock.project.Playgrounds.Playground;
import cc.makeblock.makeblock.project.ProjectBean;
import java.io.Serializable;

public class ListItemData
  implements Serializable
{
  public static final int ITEM_TYPE_NEW = -3;
  public static final int ITEM_TYPE_PLAY_GROUND_AIRBLOCK_DRONE = -5;
  public static final int ITEM_TYPE_PLAY_GROUND_AIRBLOCK_LAND = -6;
  public static final int ITEM_TYPE_PLAY_GROUND_AIRBLOCK_WATER = -7;
  public static final int ITEM_TYPE_PLAY_GROUND_MBOT = -4;
  public static final int ITEM_TYPE_PLAY_GROUND_MBOT_RANGER = -8;
  public static final int ITEM_TYPE_PROJECT = -2;
  public static final int ITEM_TYPE_URL = -1;
  private int itemType = -3;
  private Playgrounds.Playground playground;
  private PlaygroundItemData playgroundItemData;
  private ProjectBean projectBean;
  private TutorialInfo.Data.Devices.Device.LanguageUnitData tutorialInfoData;
  
  public ListItemData(int paramInt)
  {
    this.itemType = paramInt;
  }
  
  public ListItemData(int paramInt, PlaygroundItemData paramPlaygroundItemData)
  {
    this.itemType = paramInt;
    this.playgroundItemData = paramPlaygroundItemData;
  }
  
  public ListItemData(TutorialInfo.Data.Devices.Device.LanguageUnitData paramLanguageUnitData)
  {
    this.itemType = -1;
    this.tutorialInfoData = paramLanguageUnitData;
  }
  
  public ListItemData(Playgrounds.Playground paramPlayground)
  {
    this.playground = paramPlayground;
  }
  
  public ListItemData(ProjectBean paramProjectBean)
  {
    this.projectBean = paramProjectBean;
    this.itemType = -2;
  }
  
  public int getItemType()
  {
    return this.itemType;
  }
  
  public Playgrounds.Playground getPlayground()
  {
    return this.playground;
  }
  
  public PlaygroundItemData getPlaygroundItemData()
  {
    return this.playgroundItemData;
  }
  
  public ProjectBean getProjectBean()
  {
    return this.projectBean;
  }
  
  public TutorialInfo.Data.Devices.Device.LanguageUnitData getTutorialInfoData()
  {
    return this.tutorialInfoData;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\ListItemData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */