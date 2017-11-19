package cc.makeblock.makeblock.viewmodel.controller.create;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import cc.makeblock.makeblock.customview.bindingadapter.GlideBindings;
import cc.makeblock.makeblock.customview.dialog.AirBlockModeSelectDialog;
import cc.makeblock.makeblock.customview.dialog.AirBlockModeSelectDialog.OnModeSelectListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.RenameDialog;
import cc.makeblock.makeblock.customview.dialog.RenameDialog.OnConfirmListener;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo.Data.Devices.Device.LanguageUnitData;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.project.DAO;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;
import cc.makeblock.makeblock.scene.controller.create.CreateActivity;

public class CreateItemViewModel
  extends BaseObservable
{
  private static final String KEY_ACTION = "create_controlpannel";
  private CreateActivity createActivity;
  private ListItemData itemData;
  private ProjectBean mProjectBean;
  private boolean projectIsNewlyCreated = false;
  private TutorialInfo.Data.Devices.Device.LanguageUnitData tutorialInfoData;
  
  private void jumpToPanelActivity(Activity paramActivity, String paramString1, int paramInt, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction(paramString1);
    if (paramInt >= 0) {
      localIntent.putExtra("PROJECT_BEAN_ID", paramInt);
    }
    if (!TextUtils.isEmpty(paramString2)) {
      localIntent.putExtra("PROJECT_BEAN_FORM", paramString2);
    }
    localIntent.putExtra("PROJECT_BEAN_IS_NEWLY_CREATED", this.projectIsNewlyCreated);
    paramActivity.startActivityForResult(localIntent, 0);
  }
  
  private void showAirBlockModeDialog(final View paramView)
  {
    AirBlockModeSelectDialog localAirBlockModeSelectDialog = new AirBlockModeSelectDialog(paramView.getContext());
    localAirBlockModeSelectDialog.setOnModeSelectListener(new AirBlockModeSelectDialog.OnModeSelectListener()
    {
      public void robotForm(int paramAnonymousInt)
      {
        CreateItemViewModel.this.jumpToPanelActivity((Activity)paramView.getContext(), "create_controlpannel", -1, String.valueOf(paramAnonymousInt));
      }
    });
    localAirBlockModeSelectDialog.show();
    if (localAirBlockModeSelectDialog.getWindow() != null) {
      localAirBlockModeSelectDialog.getWindow().setLayout(ScreenHelper.getPercentWidthToPx(0.7291667F), ScreenHelper.getPercentWidthToPx(0.3125F));
    }
  }
  
  private void statistics()
  {
    if (this.mProjectBean == null)
    {
      StatisticsTool.getInstance().onEvent("CreateNewProject", "创建新项目");
      this.projectIsNewlyCreated = true;
      return;
    }
    if ((this.mProjectBean.isOfficial & 0x1) == 1)
    {
      StatisticsTool.getInstance().onEvent("OpenOfficialCP" + this.mProjectBean.nameKey, "打开官方CP：" + this.mProjectBean.nameKey);
      return;
    }
    StatisticsTool.getInstance().onEvent("OpenCustomCP", "打开自定义CP");
  }
  
  public void delete(final Context paramContext)
  {
    StatisticsTool.getInstance().onEvent("CustomProjectClickDelete", "删除自定义CP");
    ConfirmDialog localConfirmDialog = new ConfirmDialog(paramContext);
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        if (DAO.getInstance(paramContext).delete(CreateItemViewModel.this.mProjectBean.id).booleanValue())
        {
          ProjectListChangeEvent localProjectListChangeEvent = new ProjectListChangeEvent(1);
          localProjectListChangeEvent.setListItemData(new ListItemData(CreateItemViewModel.this.mProjectBean));
          CreateItemViewModel.this.createActivity.notifyCustomProjectListUpdate(localProjectListChangeEvent);
        }
      }
    });
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493281));
    localConfirmDialog.show();
  }
  
  public Uri getImageURI()
  {
    if (this.itemData.getItemType() == -3) {
      return GlideBindings.resourceIdToUri(this.createActivity, 2131165612);
    }
    if (this.mProjectBean.getScreenshotUri() != null) {
      return Uri.parse(this.mProjectBean.getScreenshotUri());
    }
    return null;
  }
  
  public boolean getMenuVisibility()
  {
    return (this.mProjectBean != null) && ((this.mProjectBean.isOfficial & 0x1) == 0);
  }
  
  @Bindable
  public String getProjectItemName()
  {
    if ((this.mProjectBean != null) && (this.mProjectBean.name != null)) {
      return this.mProjectBean.name;
    }
    return "";
  }
  
  public boolean getTextBarVisibility()
  {
    return (this.itemData.getItemType() != -3) || (this.itemData.getPlayground() != null);
  }
  
  public boolean onClick(View paramView)
  {
    if (this.itemData.getItemType() == -1)
    {
      ActivityJumpUtil.jumpToGroupActivity((Activity)paramView.getContext(), this.tutorialInfoData.getLink());
      return true;
    }
    statistics();
    if ((ControllerManager.getInstance().getChosenBoardName().equalsIgnoreCase(DeviceBoardName.crystal.name())) && (this.projectIsNewlyCreated))
    {
      showAirBlockModeDialog(paramView);
      return true;
    }
    int i = -1;
    if (this.mProjectBean != null) {
      i = this.mProjectBean.id;
    }
    jumpToPanelActivity((Activity)paramView.getContext(), "create_controlpannel", i, null);
    return true;
  }
  
  public void rename(final Context paramContext)
  {
    StatisticsTool.getInstance().onEvent("CustomProjectClickRename", "重命名自定义CP");
    RenameDialog localRenameDialog = new RenameDialog(paramContext);
    localRenameDialog.initText = this.mProjectBean.name;
    localRenameDialog.onConfirmListener = new RenameDialog.OnConfirmListener()
    {
      public void onConfirm(String paramAnonymousString)
      {
        if ((!paramAnonymousString.equals(CreateItemViewModel.this.mProjectBean.name)) && (!paramAnonymousString.equals("")))
        {
          CreateItemViewModel.this.mProjectBean.name = paramAnonymousString;
          DAO.getInstance(paramContext).updateName(paramAnonymousString, CreateItemViewModel.this.mProjectBean.id);
          CreateItemViewModel.this.mProjectBean.name = paramAnonymousString;
          CreateItemViewModel.this.notifyPropertyChanged(56);
          paramAnonymousString = new ProjectListChangeEvent(4);
          CreateItemViewModel.this.createActivity.notifyCustomProjectListUpdate(paramAnonymousString);
        }
      }
    };
    localRenameDialog.show();
  }
  
  public void setItemData(CreateActivity paramCreateActivity, ListItemData paramListItemData)
  {
    this.createActivity = paramCreateActivity;
    this.itemData = paramListItemData;
    this.mProjectBean = paramListItemData.getProjectBean();
    if (paramListItemData.getItemType() == -1) {
      this.tutorialInfoData = paramListItemData.getTutorialInfoData();
    }
    notifyPropertyChanged(0);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\controller\create\CreateItemViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */