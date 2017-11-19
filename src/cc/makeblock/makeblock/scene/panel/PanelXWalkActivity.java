package cc.makeblock.makeblock.scene.panel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow.OnDismissListener;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.UserGuideCoverView;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellLayout.CellLayoutListener;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.cell.CellView.OnWidgetTriggeredListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.RenameDialog;
import cc.makeblock.makeblock.customview.dialog.RenameDialog.OnConfirmListener;
import cc.makeblock.makeblock.customview.panel.HorizontalJoyStickView;
import cc.makeblock.makeblock.customview.panel.JoyStickView;
import cc.makeblock.makeblock.customview.panel.SpeakerView;
import cc.makeblock.makeblock.databinding.ActivityPanelBinding;
import cc.makeblock.makeblock.databinding.LayoutCodePanelBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.blockly.BlocklyUtil;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.PopupWindowUtils;
import cc.makeblock.makeblock.engine.utils.PopupWindowUtils.Builder;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.SensorToBlockly;
import cc.makeblock.makeblock.engine.utils.SensorToBlockly.ActionListener;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.god.ProjectBeanWrapper;
import cc.makeblock.makeblock.project.DAO;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.project.ProjectStoreUtils;
import cc.makeblock.makeblock.project.SettingsManager;
import cc.makeblock.makeblock.scene.splash.SplashScreenActivity;
import cc.makeblock.makeblock.viewmodel.PanelViewModel;
import com.gzsll.jsbridge.WVJBWebView;

public class PanelXWalkActivity
  extends NotifyBluetoothActivity
  implements CellLayout.CellLayoutListener, ControllerManager.OnDeviceChangeListener
{
  public static final String PROJECT_BEAN_FORM = "PROJECT_BEAN_FORM";
  public static final String PROJECT_BEAN_ID = "PROJECT_BEAN_ID";
  public static final String PROJECT_BEAN_IS_NEWLY_CREATED = "PROJECT_BEAN_IS_NEWLY_CREATED";
  public static final String PROJECT_BEAN_NAME_KEY = "PROJECT_BEAN_NAME_KEY";
  public static final String TAG = PanelXWalkActivity.class.getSimpleName();
  private ActivityPanelBinding binding;
  private View.OnClickListener clickListener_widgets = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      StatisticsTool.getInstance().onEvent("OpenWidgetMenu", "打开控件菜单");
      PanelXWalkActivity.this.showWidgetEditPopup((CellView)paramAnonymousView);
    }
  };
  private ForceUpdateDialog forceUpdateDialog;
  private boolean isEnterBlockly;
  private PortSelectPopupWindow mPortSelectPopupWindow = null;
  private WidgetEditPopupWindow mWidgetEditPopupWindow;
  private PopupWindowUtils modeSelectorPopupWindow;
  private PopupWindowUtils.Builder modeSelectorPopupWindowBuilder;
  private PanelViewModel panelViewModel;
  private boolean projectBeanIsNewlyCreated = false;
  private ProjectBeanWrapper projectBeanWrapper;
  private SensorToBlockly sensorToBlockly;
  
  private void forceUpdateAirBlock(Device paramDevice)
  {
    if ((paramDevice != null) && ((paramDevice instanceof AirBlock)))
    {
      paramDevice = ((AirBlock)paramDevice).getFirmwareVersion();
      if ((paramDevice != null) && (!paramDevice.equals(""))) {
        break label33;
      }
    }
    for (;;)
    {
      return;
      try
      {
        label33:
        paramDevice = paramDevice.split("\\.");
        if (Integer.parseInt(paramDevice[(paramDevice.length - 2)]) < AirBlock.forceUpdateVersion)
        {
          showForceUpdateAirBlockDialog();
          return;
        }
      }
      catch (Exception paramDevice) {}
    }
  }
  
  private ProjectBean initProjectBean()
  {
    String str = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    Object localObject = getIntent().getStringExtra("PROJECT_BEAN_NAME_KEY");
    if (!this.projectBeanIsNewlyCreated)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        return SettingsManager.getProjectBean(str, (String)localObject);
      }
      int i = getIntent().getIntExtra("PROJECT_BEAN_ID", -1);
      if (i >= 0) {
        return DAO.getInstance(getApplicationContext()).selectById(i);
      }
    }
    else
    {
      localObject = new ProjectBean();
      ((ProjectBean)localObject).name = ProjectStoreUtils.CP_NAME_DEFAULT;
      ((ProjectBean)localObject).boardName = str;
      if (str.equalsIgnoreCase(DeviceBoardName.crystal.name()))
      {
        ((ProjectBean)localObject).robotForm = getIntent().getStringExtra("PROJECT_BEAN_FORM");
        return (ProjectBean)localObject;
      }
      ((ProjectBean)localObject).robotForm = "0";
      return (ProjectBean)localObject;
    }
    return null;
  }
  
  private void projectNullCheck(ProjectBean paramProjectBean)
  {
    if (paramProjectBean == null)
    {
      paramProjectBean = new ProjectBean();
      paramProjectBean.boardName = "";
      paramProjectBean.name = ProjectStoreUtils.CP_NAME_DEFAULT;
      paramProjectBean.robotForm = "0";
      paramProjectBean = new Intent(this, SplashScreenActivity.class);
      paramProjectBean.setFlags(32768);
      startActivity(paramProjectBean);
      finish();
    }
  }
  
  private void showDeleteConfirmDialog(final CellView paramCellView)
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(this);
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131492974));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        PanelXWalkActivity.this.widgetDelete(paramCellView);
        PanelXWalkActivity.this.panelViewModel.removeCellView(paramCellView.getWidgetData());
      }
    });
    localConfirmDialog.show();
  }
  
  private void showForceUpdateAirBlockDialog()
  {
    if (this.forceUpdateDialog == null)
    {
      this.forceUpdateDialog = new ForceUpdateDialog(this);
      this.forceUpdateDialog.setOnOperationConfirmedListener(new ForceUpdateDialog.OnOperationConfirmedListener()
      {
        public void onOperationConfirmed()
        {
          ActivityJumpUtil.jumpToFirmwareUpdateActivity(PanelXWalkActivity.this);
          PanelXWalkActivity.this.forceUpdateDialog.dismiss();
        }
      });
    }
    if (!this.forceUpdateDialog.isShowing()) {
      this.forceUpdateDialog.show();
    }
  }
  
  private void showPortSelectWindow(final CellView paramCellView)
  {
    if (this.mPortSelectPopupWindow == null) {
      this.mPortSelectPopupWindow = PortSelectPopupWindow.createPortSelectPopupWindow((Activity)paramCellView.getContext(), ControllerManager.getInstance().getChosenBoardName());
    }
    this.mPortSelectPopupWindow.setWidgetData(paramCellView.getWidgetData());
    this.mPortSelectPopupWindow.setOnOkButtonClickListener(new PortSelectPopupWindow.OnOkButtonClickListener()
    {
      public void onOkClick()
      {
        PanelXWalkActivity.this.panelViewModel.widgetUpdate(paramCellView.getWidgetData());
        paramCellView.notifyWidgetBeanChanged();
      }
    });
    this.mPortSelectPopupWindow.showAtLocation(((Activity)paramCellView.getContext()).getWindow().getDecorView(), 17, 0, 0);
  }
  
  private void showRenameInputDialog(final CellView paramCellView)
  {
    RenameDialog localRenameDialog = new RenameDialog(paramCellView.getContext());
    localRenameDialog.initText = paramCellView.getWidgetData().name;
    localRenameDialog.onConfirmListener = new RenameDialog.OnConfirmListener()
    {
      public void onConfirm(String paramAnonymousString)
      {
        if (!paramAnonymousString.equals(""))
        {
          paramCellView.getWidgetData().name = paramAnonymousString;
          PanelXWalkActivity.this.panelViewModel.widgetUpdate(paramCellView.getWidgetData());
          paramCellView.notifyWidgetBeanChanged();
        }
      }
    };
    localRenameDialog.show();
    if (localRenameDialog.getWindow() != null) {
      localRenameDialog.getWindow().setLayout(ScreenHelper.getPercentWidthToPx(0.44479167F), ScreenHelper.getPercentWidthToPx(0.221875F));
    }
  }
  
  private void showWidgetEditPopup(final CellView paramCellView)
  {
    int j = (int)(ScreenHelper.SCREEN_WIDTH * 0.024414062F);
    int i1 = (int)(ScreenHelper.SCREEN_WIDTH * 0.21972656F);
    int i = j;
    if (paramCellView.getNameChangeable()) {
      i = j + (int)(ScreenHelper.SCREEN_WIDTH * 0.043945312F);
    }
    j = i;
    if (paramCellView.getPortSelectable())
    {
      j = i;
      if (paramCellView.getWidgetData().port != null) {
        j = i + (int)(ScreenHelper.SCREEN_WIDTH * 0.048828125F);
      }
    }
    i = j;
    if (paramCellView.getProgrammable()) {
      i = j + (int)(ScreenHelper.SCREEN_WIDTH * 0.048828125F);
    }
    int m = i;
    if (paramCellView.getDeletable()) {
      m = i + (int)(ScreenHelper.SCREEN_WIDTH * 0.048828125F);
    }
    if (this.mWidgetEditPopupWindow == null)
    {
      this.mWidgetEditPopupWindow = new WidgetEditPopupWindow(paramCellView.getContext(), paramCellView.getWidgetData());
      this.mWidgetEditPopupWindow.setTouchable(true);
      this.mWidgetEditPopupWindow.setOutsideTouchable(true);
      this.mWidgetEditPopupWindow.setFocusable(true);
      this.mWidgetEditPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
      this.mWidgetEditPopupWindow.setNameChangeable(paramCellView.getNameChangeable());
      if (paramCellView.getWidgetData().port != null) {
        this.mWidgetEditPopupWindow.setPortSelectable(paramCellView.getPortSelectable());
      }
      this.mWidgetEditPopupWindow.setProgrammable(paramCellView.getProgrammable());
      this.mWidgetEditPopupWindow.setDeletable(paramCellView.getDeletable());
      this.mWidgetEditPopupWindow.setWidth(i1);
      this.mWidgetEditPopupWindow.setHeight(m);
      this.mWidgetEditPopupWindow.setOnWidgetRenameListener(new WidgetEditPopupWindow.OnWidgetRenameListener()
      {
        public void onWidgetRename()
        {
          PanelXWalkActivity.this.showRenameInputDialog(paramCellView);
        }
      });
      this.mWidgetEditPopupWindow.setOnPortSelectListener(new WidgetEditPopupWindow.OnPortSelectListener()
      {
        public void onPortSelect()
        {
          PanelXWalkActivity.this.showPortSelectWindow(paramCellView);
        }
      });
      this.mWidgetEditPopupWindow.setOnEnterBlocklyListener(new WidgetEditPopupWindow.OnEnterBlocklyListener()
      {
        public void onEnterBlockly()
        {
          PanelXWalkActivity.this.panelViewModel.jumpToBlockly(paramCellView.getWidgetData());
        }
      });
      this.mWidgetEditPopupWindow.setOnWidgetDeleteListener(new WidgetEditPopupWindow.OnWidgetDeleteListener()
      {
        public void onWidgetDelete()
        {
          PanelXWalkActivity.this.showDeleteConfirmDialog(paramCellView);
        }
      });
      this.mWidgetEditPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          PanelXWalkActivity.access$702(PanelXWalkActivity.this, null);
        }
      });
    }
    int i4 = ((ViewGroup)paramCellView.getParent()).getWidth();
    int n = ((ViewGroup)paramCellView.getParent()).getHeight();
    i = 2;
    j = i4 / 2 - i1 / 2;
    int k = n / 2 - m / 2;
    int i3 = paramCellView.getLeft();
    int i2 = paramCellView.getTop();
    int i5 = paramCellView.getRight();
    int i6 = paramCellView.getBottom();
    if (i2 > m)
    {
      i = 2;
      j = (paramCellView.getWidth() - i1) / 2 + i3;
      k = i2 - m;
    }
    for (;;)
    {
      this.mWidgetEditPopupWindow.setArrowDirection(i);
      this.mWidgetEditPopupWindow.showAtLocation(paramCellView, 0, j, ScreenHelper.SCREEN_HEIGHT - n + k);
      return;
      if (n - i6 > m)
      {
        i = 0;
        j = (paramCellView.getWidth() - i1) / 2 + i3;
        k = i2 + paramCellView.getHeight();
      }
      else if (i3 > i1)
      {
        i = 1;
        j = i3 - i1;
        k = (paramCellView.getHeight() - m) / 2 + i2;
      }
      else if (i4 - i5 > i1)
      {
        i = 3;
        j = i3 + paramCellView.getWidth();
        k = (paramCellView.getHeight() - m) / 2 + i2;
      }
    }
  }
  
  private void widgetDelete(CellView paramCellView)
  {
    this.binding.panelCellLayout.removeView(paramCellView);
  }
  
  public void addCellView(CellView paramCellView)
  {
    this.binding.panelCellLayout.addCellView(paramCellView);
    paramCellView.setControlListener();
    if ((paramCellView instanceof HorizontalJoyStickView)) {
      ((HorizontalJoyStickView)paramCellView).setIsCar(this.projectBeanWrapper.isCar());
    }
    if ((paramCellView instanceof SpeakerView)) {
      ((SpeakerView)paramCellView).setOnSpeakerStateSendListener(this.panelViewModel);
    }
    if ((paramCellView instanceof JoyStickView)) {
      ((JoyStickView)paramCellView).setOnJoystickTriggerListener(this.panelViewModel);
    }
    paramCellView.setOnWidgetTriggeredListener(new CellView.OnWidgetTriggeredListener()
    {
      public void onWidgetTrigger(String paramAnonymousString1, String paramAnonymousString2)
      {
        PanelXWalkActivity.this.panelViewModel.callJSMethod(paramAnonymousString1, paramAnonymousString2);
      }
    });
    paramCellView.setOnClickListener(this.clickListener_widgets);
  }
  
  public CellView getCellViewFromCellLayout(int paramInt)
  {
    int i = 0;
    while (i < this.binding.panelCellLayout.getChildCount())
    {
      if ((this.binding.panelCellLayout.getChildAt(i) instanceof CellView))
      {
        CellView localCellView = (CellView)this.binding.panelCellLayout.getChildAt(i);
        if (localCellView.getWidgetData().widgetID == paramInt) {
          return localCellView;
        }
      }
      i += 1;
    }
    return null;
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  public void onAddCellViewByDrop(CellView paramCellView, boolean paramBoolean)
  {
    this.binding.getViewModel().turnOnWidgetList();
    if (paramBoolean)
    {
      StatisticsTool.getInstance().onEvent("DragWidgetToScreen", "拖拽控件到屏幕");
      this.projectBeanWrapper.addWidgetData(paramCellView.getWidgetData(), this.panelViewModel);
      addCellView(paramCellView);
    }
  }
  
  public void onBackPressed()
  {
    this.binding.getViewModel().onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    if (getIntent() != null) {
      this.projectBeanIsNewlyCreated = getIntent().getBooleanExtra("PROJECT_BEAN_IS_NEWLY_CREATED", false);
    }
    super.onCreate(paramBundle);
    paramBundle = initProjectBean();
    projectNullCheck(paramBundle);
    this.projectBeanWrapper = new ProjectBeanWrapper(paramBundle, this.projectBeanIsNewlyCreated);
    this.binding = ((ActivityPanelBinding)DataBindingUtil.setContentView(this, 2131427381));
    this.binding.panelCellLayout.setCellLayoutListener(this);
    this.panelViewModel = new PanelViewModel(this, this.projectBeanWrapper, this.binding);
    this.binding.setViewModel(this.panelViewModel);
    this.binding.getViewModel().loadCurrentProjectBean();
    this.sensorToBlockly = new SensorToBlockly(this, new SensorToBlockly.ActionListener()
    {
      public void sendAction(String paramAnonymousString1, String paramAnonymousString2)
      {
        PanelXWalkActivity.this.panelViewModel.callJSMethod(paramAnonymousString1, paramAnonymousString2);
      }
    });
    this.binding.getViewModel().onCreate();
    onXWalkReady();
    ControllerManager.getInstance().registerDeviceListener(this);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.binding.getViewModel().onDestroy();
    this.binding.code.webview.destroy();
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    if ((this.forceUpdateDialog != null) && (this.forceUpdateDialog.isShowing())) {
      this.forceUpdateDialog.dismiss();
    }
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    if (paramDevice.isConnected())
    {
      forceUpdateAirBlock(paramDevice);
      if (this.panelViewModel != null) {
        this.panelViewModel.onConnectedStateChange(ControllerManager.getInstance().getCurrentDevice());
      }
    }
  }
  
  public void onDragStart(int paramInt)
  {
    this.panelViewModel.turnOffWidgetList();
  }
  
  public void onPause()
  {
    this.sensorToBlockly.onPause();
    this.binding.getViewModel().onPause();
    super.onPause();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    this.isEnterBlockly = paramBundle.getBoolean("isEnterBlockly");
    if (this.isEnterBlockly) {
      this.panelViewModel.setHasEnteredBlockly();
    }
    super.onRestoreInstanceState(paramBundle);
  }
  
  public void onResume()
  {
    super.onResume();
    this.binding.getViewModel().onResume();
    this.sensorToBlockly.onResume();
    if (this.binding.code.webview != null) {
      this.binding.code.webview.resumeTimers();
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("isEnterBlockly", this.isEnterBlockly);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    super.onStart();
    this.panelViewModel.onStart();
  }
  
  protected void onStop()
  {
    this.panelViewModel.onStop();
    super.onStop();
  }
  
  protected void onXWalkReady()
  {
    String str = BlocklyUtil.getHtmlPath();
    this.binding.code.webview.loadUrl(str);
    this.panelViewModel.initWebView();
  }
  
  public void setModeToDesign()
  {
    StatisticsTool.getInstance().onEvent("EnterDesignMode", "进入编辑模式");
    this.binding.panelCellLayout.setMode(2);
    if (!SharedPreferencesUtils.getHasShowPanelEditGuide())
    {
      UserGuideCoverView localUserGuideCoverView = new UserGuideCoverView(this);
      localUserGuideCoverView.setLayoutIds(new int[] { 2131427484, 2131427485 });
      addContentView(localUserGuideCoverView, new PercentRelativeLayout.LayoutParams(-1, -1));
      SharedPreferencesUtils.setHasShowPanelEditGuide(true);
    }
  }
  
  public void setModeToPlay()
  {
    this.binding.panelCellLayout.setMode(1);
  }
  
  public void showPopupWindowForModeSelector(View paramView, String[] paramArrayOfString)
  {
    View.OnClickListener local2 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PanelXWalkActivity.this.binding.getViewModel().modifyRobotForm("0");
        PanelXWalkActivity.this.modeSelectorPopupWindow.dismiss();
      }
    };
    View.OnClickListener local3 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PanelXWalkActivity.this.binding.getViewModel().modifyRobotForm("1");
        PanelXWalkActivity.this.modeSelectorPopupWindow.dismiss();
      }
    };
    View.OnClickListener local4 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PanelXWalkActivity.this.binding.getViewModel().modifyRobotForm("2");
        PanelXWalkActivity.this.modeSelectorPopupWindow.dismiss();
      }
    };
    View.OnClickListener local5 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PanelXWalkActivity.this.binding.getViewModel().modifyRobotForm("3");
        PanelXWalkActivity.this.modeSelectorPopupWindow.dismiss();
      }
    };
    if (this.modeSelectorPopupWindowBuilder == null)
    {
      this.modeSelectorPopupWindowBuilder = new PopupWindowUtils.Builder().relativeParent(paramView).dismissWhenTouchOutside(true).addArrowBackground(2131165304).sizeDependsOn(2);
      switch (paramArrayOfString.length)
      {
      default: 
        return;
      case 2: 
        this.modeSelectorPopupWindowBuilder.sizePercent(0.2F, 0.2F).addTextViewWithDivider(paramArrayOfString[0], local2).addTextViewWithDivider(paramArrayOfString[1], local3);
      }
    }
    for (;;)
    {
      if (this.modeSelectorPopupWindow == null) {
        this.modeSelectorPopupWindow = this.modeSelectorPopupWindowBuilder.build(this);
      }
      if (this.modeSelectorPopupWindow.isShowing()) {
        break;
      }
      this.modeSelectorPopupWindow.showBelowCenter();
      return;
      this.modeSelectorPopupWindowBuilder.sizePercent(0.28F, 0.2F).addTextViewWithDivider(paramArrayOfString[0], local2).addTextViewWithDivider(paramArrayOfString[1], local3).addTextViewWithDivider(paramArrayOfString[2], local4);
      continue;
      this.modeSelectorPopupWindowBuilder.sizePercent(0.36F, 0.2F).addTextViewWithDivider(paramArrayOfString[0], local2).addTextViewWithDivider(paramArrayOfString[1], local3).addTextViewWithDivider(paramArrayOfString[2], local4).addTextViewWithDivider(paramArrayOfString[3], local5);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\panel\PanelXWalkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */