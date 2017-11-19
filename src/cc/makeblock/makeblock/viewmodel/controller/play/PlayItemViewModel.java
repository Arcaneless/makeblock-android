package cc.makeblock.makeblock.viewmodel.controller.play;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.view.View;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices.Device.LanguageUnitData;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.project.Playgrounds.Playground;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.play.PlayActivity;

public class PlayItemViewModel
  extends BaseObservable
{
  private int cpType;
  private ListItemData itemData;
  private ProjectBean mProjectBean;
  private PlayActivity playActivity;
  private TutorialInfo.Data.Devices.Device.LanguageUnitData tutorialInfoData;
  
  private void jumpToPanelActivity(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction(paramString1);
    localIntent.putExtra("PROJECT_BEAN_NAME_KEY", paramString2);
    this.playActivity.startActivity(localIntent);
  }
  
  private void statistics()
  {
    if (this.mProjectBean == null)
    {
      StatisticsTool.getInstance().onEvent("CreateNewProject", "创建新项目");
      return;
    }
    if ((this.mProjectBean.isOfficial & 0x1) == 1)
    {
      StatisticsTool.getInstance().onEvent("OpenOfficialCP" + this.mProjectBean.nameKey, "打开官方CP：" + this.mProjectBean.nameKey);
      return;
    }
    StatisticsTool.getInstance().onEvent("OpenCustomCP", "打开自定义CP");
  }
  
  public Uri getImageURI()
  {
    if (this.itemData.getPlayground() != null) {
      return Uri.parse(this.itemData.getPlayground().getCoverPath());
    }
    return null;
  }
  
  @Bindable
  public String getProjectItemName()
  {
    if ((this.itemData.getItemType() == -1) && (this.tutorialInfoData != null)) {
      return this.tutorialInfoData.getTitle();
    }
    if (this.itemData.getPlayground() != null) {
      return this.itemData.getPlayground().getTitleRes();
    }
    return "";
  }
  
  public int getTypeMarkIcon()
  {
    if (this.cpType == 3) {
      return 2131165611;
    }
    return 0;
  }
  
  public boolean onClick(View paramView)
  {
    if (this.itemData.getItemType() == -1) {
      ActivityJumpUtil.jumpToGroupActivity((Activity)paramView.getContext(), this.tutorialInfoData.getLink());
    }
    do
    {
      return true;
      statistics();
      paramView = this.itemData.getPlayground();
    } while (paramView == null);
    jumpToPanelActivity(paramView.action, paramView.title);
    return true;
  }
  
  public void setItemData(PlayActivity paramPlayActivity, ListItemData paramListItemData, int paramInt)
  {
    this.playActivity = paramPlayActivity;
    this.itemData = paramListItemData;
    this.cpType = paramInt;
    this.mProjectBean = paramListItemData.getProjectBean();
    if (paramListItemData.getItemType() == -1) {
      this.tutorialInfoData = paramListItemData.getTutorialInfoData();
    }
    notifyPropertyChanged(0);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\controller\play\PlayItemViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */