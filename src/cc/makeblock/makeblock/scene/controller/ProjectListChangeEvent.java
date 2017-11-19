package cc.makeblock.makeblock.scene.controller;

import java.io.Serializable;

public class ProjectListChangeEvent
  implements Serializable
{
  public static final int LIST_CHANGE_EVENT_TYPE_CHANGE = 2;
  public static final int LIST_CHANGE_EVENT_TYPE_INSERT = 0;
  public static final int LIST_CHANGE_EVENT_TYPE_REFRESH = 3;
  public static final int LIST_CHANGE_EVENT_TYPE_REMOVE = 1;
  public static final int LIST_CHANGE_EVENT_TYPE_RENAME = 4;
  public int eventType;
  public ListItemData listItemData;
  
  public ProjectListChangeEvent(int paramInt)
  {
    this.eventType = paramInt;
  }
  
  public void setListItemData(ListItemData paramListItemData)
  {
    this.listItemData = paramListItemData;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\controller\ProjectListChangeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */