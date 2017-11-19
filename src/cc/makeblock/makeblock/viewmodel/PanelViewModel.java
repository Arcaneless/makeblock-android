package cc.makeblock.makeblock.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.bean.BoardGroupBean;
import cc.makeblock.makeblock.bean.ExpandGuideData;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.BlocklyWidgetLayout;
import cc.makeblock.makeblock.customview.cell.CellLayout;
import cc.makeblock.makeblock.customview.cell.CellLayout.SendWidgetValueCallback;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.dialog.AirblockTuneDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationCanceledListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.SaveAsDialog;
import cc.makeblock.makeblock.customview.dialog.SaveAsDialog.OnConfirmListener;
import cc.makeblock.makeblock.customview.panel.JoyStickView.OnJoystickTriggerListener;
import cc.makeblock.makeblock.customview.panel.SpeakerView.OnSpeakerStateSendListener;
import cc.makeblock.makeblock.databinding.ActivityPanelBinding;
import cc.makeblock.makeblock.databinding.LayoutCodePanelBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.blockly.BlocklyUtil;
import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.device.SmartServo;
import cc.makeblock.makeblock.engine.device.Starter;
import cc.makeblock.makeblock.engine.device.Ultimate2;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice.MakeBlockStatusAdapter;
import cc.makeblock.makeblock.engine.device.mode.ModeController;
import cc.makeblock.makeblock.engine.protocol.web.receive.OnWidgetBeSelectedJson;
import cc.makeblock.makeblock.engine.protocol.web.receive.SaveControlPanelJson;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendBluetoothJson;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;
import cc.makeblock.makeblock.engine.protocol.web.send.AirBlockFormJson;
import cc.makeblock.makeblock.engine.protocol.web.send.SendWidgetDataJson;
import cc.makeblock.makeblock.engine.protocol.web.send.WidgetUpdateJson;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.god.ProjectBeanWrapper;
import cc.makeblock.makeblock.project.DAO;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.project.ProjectStoreUtils;
import cc.makeblock.makeblock.project.SettingsManager;
import cc.makeblock.makeblock.scene.controller.ListItemData;
import cc.makeblock.makeblock.scene.controller.ProjectListChangeEvent;
import cc.makeblock.makeblock.scene.panel.ExpandGuideDialogFragment;
import cc.makeblock.makeblock.scene.panel.PanelXWalkActivity;
import cc.makeblock.makeblock.scene.panel.WidgetFactory;
import com.google.gson.Gson;
import com.gzsll.jsbridge.WVJBWebView;
import com.gzsll.jsbridge.WVJBWebView.WVJBHandler;
import com.gzsll.jsbridge.WVJBWebView.WVJBResponseCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PanelViewModel
  extends BaseObservable
  implements SpeakerView.OnSpeakerStateSendListener, JoyStickView.OnJoystickTriggerListener
{
  public static final int MODE_DESIGN = 1;
  public static final int MODE_PLAY = 0;
  private WVJBWebView.WVJBHandler airblockIsReadyHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      PanelViewModel.this.setAirBlockForm();
    }
  };
  private ActivityPanelBinding binding;
  private WVJBWebView.WVJBHandler blocklyRequestWidgetValueHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousWVJBResponseCallback.callback(Integer.valueOf(PanelViewModel.this.currentCellView.getValue()));
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private WVJBWebView.WVJBHandler blocklySendValueToWidgetHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousObject = (SendValueToWidgetJson)PanelViewModel.this.parseJson(paramAnonymousObject.toString(), SendValueToWidgetJson.class);
        PanelViewModel.this.currentCellView.setWidgetValue((SendValueToWidgetJson)paramAnonymousObject);
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private List<BoardGroupBean> boardGroupBeanList;
  private CellView currentCellView;
  private int currentMode = -1;
  private Device device;
  private Gson gson = new Gson();
  private boolean hasInit = false;
  private boolean hasInitFinish = false;
  private Device initDevice = ControllerManager.getInstance().getCurrentDevice();
  private boolean isCreateFirstEnter;
  private boolean isShowCodePanel;
  private boolean isShowExpand = false;
  private boolean isVisible;
  private boolean isWaitShowExpandGuide;
  private boolean isWidgetListOn = false;
  private Handler mHandler = new Handler();
  private ProjectBeanWrapper mProjectBeanWrapper;
  private ControllerManager.OnDeviceChangeListener onConnectedStateChangeListener = new ControllerManager.OnDeviceChangeListener()
  {
    public void onDeviceChange(Device paramAnonymousDevice)
    {
      PanelViewModel.this.setCurrentDevice(paramAnonymousDevice);
      PanelViewModel.this.registerReceiveListener();
    }
  };
  private PanelXWalkActivity panelActivity;
  private WVJBWebView.WVJBHandler printJsLogHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      Log.e("lyh", paramAnonymousObject.toString());
    }
  };
  private final MakeBlockDevice.MakeBlockStatusAdapter receiveBluetoothDataListener;
  private WVJBWebView.WVJBHandler reportCurrentWidgetHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousObject = (OnWidgetBeSelectedJson)PanelViewModel.this.parseJson(paramAnonymousObject.toString(), OnWidgetBeSelectedJson.class);
        PanelViewModel.this.onWidgetBeSelected((OnWidgetBeSelectedJson)paramAnonymousObject, PanelViewModel.this.binding);
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private WVJBWebView.WVJBHandler requestProjectHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      paramAnonymousObject = PanelViewModel.this.mProjectBeanWrapper.getOriginalProjectBean();
      if (((ProjectBean)paramAnonymousObject).codeSheet != null)
      {
        paramAnonymousWVJBResponseCallback.callback(((ProjectBean)paramAnonymousObject).codeSheet.replace("'", "##"));
        return;
      }
      paramAnonymousWVJBResponseCallback.callback("{}");
    }
  };
  private WVJBWebView.WVJBHandler requestWidgetNameHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousWVJBResponseCallback.callback(PanelViewModel.this.panelActivity.getCellViewFromCellLayout(Integer.parseInt(((JSONObject)paramAnonymousObject).getString("id"))).getWidgetData().name);
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private WVJBWebView.WVJBHandler requestWidgetValueHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousWVJBResponseCallback.callback(Integer.valueOf(PanelViewModel.this.panelActivity.getCellViewFromCellLayout(Integer.parseInt(((JSONObject)paramAnonymousObject).getString("id"))).getValue()));
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private WVJBWebView.WVJBHandler saveControlPanelHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousObject = (SaveControlPanelJson)PanelViewModel.this.parseJson(paramAnonymousObject.toString(), SaveControlPanelJson.class);
        PanelViewModel.this.mProjectBeanWrapper.getProjectBean().codeSheet = ((SaveControlPanelJson)paramAnonymousObject).getData();
        if (!PanelViewModel.this.shouldFinish) {
          return;
        }
        if (PanelViewModel.this.mProjectBeanWrapper.projectIsNewlyCreated())
        {
          PanelViewModel.this.commitSaveNew(ProjectStoreUtils.CP_NAME_DEFAULT);
          return;
        }
        if (PanelViewModel.this.mProjectBeanWrapper.isOfficialProjectBeanModified())
        {
          PanelViewModel.this.commitSaveNew(PanelViewModel.this.mProjectBeanWrapper.getProjectName());
          return;
        }
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
        return;
      }
      PanelViewModel.this.commitModification();
    }
  };
  private WVJBWebView.WVJBHandler sendBluetoothHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousObject = (SendBluetoothJson)PanelViewModel.this.parseJson(paramAnonymousObject.toString(), SendBluetoothJson.class);
        ControllerManager.getInstance().sendStringDataToBluetooth(((SendBluetoothJson)paramAnonymousObject).getCmdStr());
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private WVJBWebView.WVJBHandler sendValueToWidgetHandler = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      try
      {
        paramAnonymousObject = (SendValueToWidgetJson)PanelViewModel.this.parseJson(paramAnonymousObject.toString(), SendValueToWidgetJson.class);
        PanelViewModel.this.binding.panelCellLayout.receiveWidgetValue((SendValueToWidgetJson)paramAnonymousObject);
        return;
      }
      catch (Exception paramAnonymousObject)
      {
        ((Exception)paramAnonymousObject).printStackTrace();
      }
    }
  };
  private CellLayout.SendWidgetValueCallback sendWidgetValueCallback = new CellLayout.SendWidgetValueCallback()
  {
    public void sendWidgetValue(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      PanelViewModel.this.callJSMethod("sendWidgetValue", new SendWidgetDataJson(paramAnonymousInt1, paramAnonymousInt2));
    }
  };
  private boolean shouldFinish = false;
  private WVJBWebView.WVJBHandler tellMainboardHanlder = new WVJBWebView.WVJBHandler()
  {
    public void request(Object paramAnonymousObject, WVJBWebView.WVJBResponseCallback paramAnonymousWVJBResponseCallback)
    {
      PanelViewModel.this.tellMainboardInfo();
      PanelViewModel.this.onLoadingFinish();
      PanelViewModel.this.showExpandGuide(PanelViewModel.this.mProjectBeanWrapper.getNameKey(), PanelViewModel.this.mProjectBeanWrapper.getName());
    }
  };
  
  public PanelViewModel(PanelXWalkActivity paramPanelXWalkActivity, ProjectBeanWrapper paramProjectBeanWrapper, ActivityPanelBinding paramActivityPanelBinding)
  {
    this.mProjectBeanWrapper = paramProjectBeanWrapper;
    this.panelActivity = paramPanelXWalkActivity;
    setCurrentDevice(ControllerManager.getInstance().registerDeviceListener(this.onConnectedStateChangeListener));
    this.boardGroupBeanList = SettingsManager.getBoardGroupBeans(this.mProjectBeanWrapper.getCorrectedBoardName());
    this.binding = paramActivityPanelBinding;
    this.receiveBluetoothDataListener = new MakeBlockDevice.MakeBlockStatusAdapter()
    {
      public void onReceive(BleData paramAnonymousBleData)
      {
        super.onReceive(paramAnonymousBleData);
        PanelViewModel.this.callJSMethod("receiveBluetoothData", BlocklyUtil.createDataString(paramAnonymousBleData.data));
      }
    };
    registerReceiveListener();
  }
  
  private void addProjectToUI(ProjectBean paramProjectBean)
  {
    if (paramProjectBean.getWidgets() == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramProjectBean.getWidgets().size())
      {
        Object localObject = (WidgetData)paramProjectBean.getWidgets().get(i);
        localObject = WidgetFactory.createCellView(this.panelActivity, (WidgetData)localObject, 1);
        this.panelActivity.addCellView((CellView)localObject);
        i += 1;
      }
    }
  }
  
  private void backToPanel()
  {
    this.binding.code.webview.registerHandler("sendValueToWidget", this.sendValueToWidgetHandler);
    this.binding.code.webview.registerHandler("requestWidgetValue", this.requestWidgetValueHandler);
    exitPlayMode();
  }
  
  private void callJSMethod(String paramString, Object paramObject, WVJBWebView.WVJBResponseCallback paramWVJBResponseCallback)
  {
    this.binding.code.webview.callHandler(paramString, paramObject, paramWVJBResponseCallback);
  }
  
  private void commitModification()
  {
    ProjectListChangeEvent localProjectListChangeEvent = null;
    int i = this.mProjectBeanWrapper.getId();
    ProjectBean localProjectBean = this.mProjectBeanWrapper.getProjectBean();
    if (this.mProjectBeanWrapper.shouldDeleteProject())
    {
      DAO.getInstance(this.panelActivity).delete(i);
      localProjectListChangeEvent = new ProjectListChangeEvent(1);
      localProjectListChangeEvent.setListItemData(new ListItemData(localProjectBean));
    }
    for (;;)
    {
      finish(localProjectListChangeEvent);
      return;
      if (DAO.getInstance(this.panelActivity).exist(i))
      {
        DAO.getInstance(this.panelActivity).delete(i);
        ProjectStoreUtils.saveProjectData(this.panelActivity, localProjectBean);
        localProjectListChangeEvent = new ProjectListChangeEvent(2);
        localProjectListChangeEvent.setListItemData(new ListItemData(localProjectBean));
      }
    }
  }
  
  private void commitSaveNew(String paramString)
  {
    ProjectListChangeEvent localProjectListChangeEvent = null;
    if (this.mProjectBeanWrapper.existWidgets())
    {
      this.mProjectBeanWrapper.setProjectName(paramString);
      this.mProjectBeanWrapper.setId(-1);
      this.mProjectBeanWrapper.setIsOfficialValue(0);
      this.mProjectBeanWrapper.setScreenShot(null);
      ProjectStoreUtils.saveProjectData(this.panelActivity, this.mProjectBeanWrapper.getProjectBean());
      localProjectListChangeEvent = new ProjectListChangeEvent(0);
      localProjectListChangeEvent.setListItemData(new ListItemData(this.mProjectBeanWrapper.getProjectBean()));
      StatisticsTool.getInstance().onEvent("SaveAsNewProject", "成功另存为一个新项目");
    }
    finish(localProjectListChangeEvent);
  }
  
  private void enterPlayMode()
  {
    callJSMethod("enterPlayMode", null);
  }
  
  private void exitPlayMode()
  {
    callJSMethod("exitPlayMode", null);
  }
  
  private void finish(ProjectListChangeEvent paramProjectListChangeEvent)
  {
    exitPlayMode();
    ModeController.getInstance().exitUltrasonicMode();
    if (paramProjectListChangeEvent != null)
    {
      Intent localIntent = new Intent();
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("MODIFY_PROJECT_RESULT_EVENT", paramProjectListChangeEvent);
      localBundle.putString("PROJECT_BOARD_NAME", this.mProjectBeanWrapper.getBoardName());
      localIntent.putExtra("MODIFY_PROJECT_RESULT_BUNDLE", localBundle);
      this.panelActivity.setResult(-1, localIntent);
    }
    this.panelActivity.finish();
  }
  
  private String getExpandDirectory(String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return null;
        if (paramString.equals("mcore"))
        {
          i = 0;
          continue;
          if (paramString.equals("auriga")) {
            i = 1;
          }
        }
        break;
      }
    }
    return "mbot";
    return "ranger";
  }
  
  private boolean isBoardMatchCp(String paramString)
  {
    return this.mProjectBeanWrapper.getProjectBeanBoardName().equalsIgnoreCase(paramString);
  }
  
  private void jumpToBlockly()
  {
    this.binding.code.webview.registerHandler("sendValueToWidget", this.blocklySendValueToWidgetHandler);
    this.binding.code.webview.registerHandler("requestWidgetValue", this.blocklyRequestWidgetValueHandler);
  }
  
  private void onLoadingFinish()
  {
    this.hasInitFinish = true;
    notifyPropertyChanged(39);
  }
  
  private void onWidgetBeSelected(final OnWidgetBeSelectedJson paramOnWidgetBeSelectedJson, final ActivityPanelBinding paramActivityPanelBinding)
  {
    if (this.isShowCodePanel) {
      try
      {
        int i = Integer.valueOf(paramOnWidgetBeSelectedJson.getId()).intValue();
        paramOnWidgetBeSelectedJson = ProjectStoreUtils.findCurrentWidgetBean(this.mProjectBeanWrapper.getProjectBean(), i);
        paramOnWidgetBeSelectedJson = WidgetFactory.createCellView(paramActivityPanelBinding.panelCellLayout.getContext(), paramOnWidgetBeSelectedJson, 1);
        if (paramActivityPanelBinding.panelCellLayout.getHeight() == 0)
        {
          paramActivityPanelBinding.panelCellLayout.post(new Runnable()
          {
            public void run()
            {
              PanelViewModel.this.setCellView(paramActivityPanelBinding, paramOnWidgetBeSelectedJson);
            }
          });
          return;
        }
        setCellView(paramActivityPanelBinding, paramOnWidgetBeSelectedJson);
        return;
      }
      catch (Exception paramOnWidgetBeSelectedJson) {}
    }
  }
  
  private <T> T parseJson(String paramString, Class<T> paramClass)
  {
    return (T)this.gson.fromJson(paramString, paramClass);
  }
  
  private void registerReceiveListener()
  {
    if ((this.device instanceof MakeBlockDevice))
    {
      ((MakeBlockDevice)this.device).removeMakeBlockStatusAdapter(this.receiveBluetoothDataListener);
      ((MakeBlockDevice)this.device).registerMakeBlockStatusAdapter(this.receiveBluetoothDataListener);
    }
  }
  
  private void saveAsNewProject()
  {
    final SaveAsDialog localSaveAsDialog = new SaveAsDialog(this.panelActivity);
    if (this.mProjectBeanWrapper.isOfficial()) {}
    for (localSaveAsDialog.initText = this.mProjectBeanWrapper.getProjectName();; localSaveAsDialog.initText = ProjectStoreUtils.CP_NAME_DEFAULT)
    {
      localSaveAsDialog.onConfirmListener = new SaveAsDialog.OnConfirmListener()
      {
        public void onCancel()
        {
          StatisticsTool.getInstance().onEvent("CancelSave", "取消另存为");
          PanelViewModel.this.finish(null);
        }
        
        public void onConfirm(String paramAnonymousString)
        {
          String str = localSaveAsDialog.getText();
          paramAnonymousString = str;
          if (str.equals("")) {
            paramAnonymousString = ProjectStoreUtils.CP_NAME_DEFAULT;
          }
          PanelViewModel.this.commitSaveNew(paramAnonymousString);
        }
      };
      localSaveAsDialog.show();
      return;
    }
  }
  
  private void saveModification()
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(this.panelActivity);
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493324));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        PanelViewModel.this.commitModification();
      }
    });
    localConfirmDialog.setOnOperationCanceledListener(new ConfirmDialog.OnOperationCanceledListener()
    {
      public void onOperationCancel()
      {
        PanelViewModel.this.finish(null);
      }
    });
    localConfirmDialog.show();
  }
  
  private void setAirBlockForm()
  {
    String str1 = "";
    String str2 = this.mProjectBeanWrapper.getRobotForm();
    int i = -1;
    switch (str2.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    for (;;)
    {
      this.binding.code.webview.callHandler("airblockSetForm", new AirBlockFormJson(str1));
      return;
      if (!str2.equals("4")) {
        break;
      }
      i = 0;
      break;
      if (!str2.equals("5")) {
        break;
      }
      i = 1;
      break;
      if (!str2.equals("7")) {
        break;
      }
      i = 2;
      break;
      if (!str2.equals("6")) {
        break;
      }
      i = 3;
      break;
      str1 = "2";
      continue;
      str1 = "1";
      continue;
      str1 = "6";
      continue;
      str1 = "5";
    }
  }
  
  private void setCellView(ActivityPanelBinding paramActivityPanelBinding, CellView paramCellView)
  {
    Point localPoint = paramActivityPanelBinding.panelCellLayout.getCellViewSize(paramCellView);
    paramActivityPanelBinding.code.blocklyWidgetLayout.setCellView(paramCellView, localPoint);
    paramCellView.setControlListener();
    this.currentCellView = paramCellView;
  }
  
  private void setCurrentDevice(Device paramDevice)
  {
    this.device = paramDevice;
    if (this.hasInit)
    {
      if (!paramDevice.isConnected()) {
        break label34;
      }
      callJSMethod("bleconnect", null);
    }
    for (;;)
    {
      notifyPropertyChanged(72);
      return;
      label34:
      callJSMethod("bledisconnect", null);
    }
  }
  
  private void setRJ25Mode(int paramInt)
  {
    if ((this.device instanceof Ranger)) {
      ((Ranger)this.device).setMode(paramInt);
    }
    do
    {
      return;
      if ((this.device instanceof Starter))
      {
        ((Starter)this.device).setMode(paramInt);
        return;
      }
    } while (!(this.device instanceof Ultimate2));
    ((Ultimate2)this.device).setMode(paramInt);
  }
  
  private void setShowCodePanel(boolean paramBoolean)
  {
    if (this.isShowCodePanel != paramBoolean)
    {
      this.isShowCodePanel = paramBoolean;
      if (!paramBoolean) {
        break label28;
      }
      jumpToBlockly();
    }
    for (;;)
    {
      notifyPropertyChanged(63);
      return;
      label28:
      backToPanel();
    }
  }
  
  private void showExpandDialog(ExpandGuideData paramExpandGuideData, String paramString1, String paramString2)
  {
    ExpandGuideDialogFragment.newInstance(paramExpandGuideData, paramString1, paramString2).show(this.panelActivity.getSupportFragmentManager(), "expand");
  }
  
  private void showExpandGuide(String paramString1, String paramString2)
  {
    if (!this.isVisible) {
      this.isWaitShowExpandGuide = true;
    }
    ExpandGuideData localExpandGuideData;
    do
    {
      do
      {
        return;
        this.isWaitShowExpandGuide = false;
      } while (TextUtils.isEmpty(paramString1));
      localExpandGuideData = SettingsManager.getExpandGuideData(this.panelActivity, File.separator + getExpandDirectory(this.mProjectBeanWrapper.getBoardName()) + File.separator + paramString1);
      boolean bool = SharedPreferencesUtils.isShowExpandGuide(paramString1);
      if ((localExpandGuideData != null) && (!bool))
      {
        showExpandDialog(localExpandGuideData, paramString2, getExpandDirectory(this.mProjectBeanWrapper.getBoardName()));
        SharedPreferencesUtils.setExpandGuide(paramString1, true);
      }
    } while (localExpandGuideData == null);
    this.isShowExpand = true;
    notifyPropertyChanged(64);
  }
  
  private void tellMainboardInfo()
  {
    callJSMethod("tellMainboardInfo", ControllerManager.getInstance().createBoardInfoJson(this.mProjectBeanWrapper.getOriginalProjectBean()));
  }
  
  private void triggerDeviceJoystick(Device paramDevice, int paramInt1, int paramInt2)
  {
    if ((paramDevice instanceof MBot)) {
      ((MBot)paramDevice).moveJoystick(paramInt1, paramInt2);
    }
    do
    {
      return;
      if ((paramDevice instanceof Ranger))
      {
        ((Ranger)paramDevice).moveJoystick(paramInt1, paramInt2);
        return;
      }
      if ((paramDevice instanceof Starter))
      {
        ((Starter)paramDevice).moveJoystick(paramInt1, paramInt2);
        return;
      }
    } while (!(paramDevice instanceof Ultimate2));
    ((Ultimate2)paramDevice).moveJoystick(paramInt1, paramInt2);
  }
  
  public void addWidgetToBlockly(String paramString)
  {
    callJSMethod("widgetAdd", paramString);
  }
  
  public void back(View paramView)
  {
    this.panelActivity.onBackPressed();
  }
  
  public void callJSMethod(String paramString, Object paramObject)
  {
    callJSMethod(paramString, paramObject, new WVJBWebView.WVJBResponseCallback()
    {
      public void callback(Object paramAnonymousObject)
      {
        Log.e("lyh", paramAnonymousObject.toString());
      }
    });
  }
  
  public void clickListButton()
  {
    if (this.isWidgetListOn)
    {
      turnOffWidgetList();
      return;
    }
    turnOnWidgetList();
  }
  
  public boolean getBlocklyServoResetButtonVisibility()
  {
    return this.mProjectBeanWrapper.supportSmartServo();
  }
  
  @Bindable
  public List<BoardGroupBean> getBoardGroupBeanList()
  {
    return SettingsManager.getBoardGroupBeans(this.mProjectBeanWrapper.getCorrectedBoardName());
  }
  
  public boolean getFormSelectContainerVisibility()
  {
    return this.mProjectBeanWrapper.isFormSelectorVisible();
  }
  
  @Bindable
  public String getFormText()
  {
    return this.mProjectBeanWrapper.getCurrentFormText();
  }
  
  public float getFormTextAlpha()
  {
    if (this.mProjectBeanWrapper.isFormChangeable()) {
      return 1.0F;
    }
    return 0.6F;
  }
  
  public int getLoading()
  {
    return 2131165396;
  }
  
  @Bindable
  public boolean getLoadingGifVisibility()
  {
    return !this.hasInitFinish;
  }
  
  public int getMode()
  {
    return this.currentMode;
  }
  
  @Bindable
  public Drawable getPanelBackground()
  {
    switch (this.currentMode)
    {
    default: 
      return new ColorDrawable(App.getContext().getResources().getColor(2131034258));
    }
    return App.getContext().getResources().getDrawable(2131165284);
  }
  
  public boolean getServoStateButtonVisibility()
  {
    return this.mProjectBeanWrapper.supportSmartServo();
  }
  
  public String getTitle()
  {
    String str = this.mProjectBeanWrapper.getProjectBean().nameKey;
    if (!TextUtils.isEmpty(str)) {
      return TextUtil.getStringByKey(str);
    }
    return this.mProjectBeanWrapper.getProjectBean().name;
  }
  
  @Bindable
  public boolean getTuneButtonVisibility()
  {
    return (this.mProjectBeanWrapper.supportTune()) && (ControllerManager.getInstance().isConnectedOk());
  }
  
  @Bindable
  public List<List<WidgetData>> getWidgetDataList()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.boardGroupBeanList.size())
    {
      localArrayList.add(SettingsManager.getGroupWidgetBeans(this.mProjectBeanWrapper.getCorrectedBoardName(), (BoardGroupBean)this.boardGroupBeanList.get(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  @Bindable
  public boolean getWidgetListOnState()
  {
    return this.isWidgetListOn;
  }
  
  public void initWebView()
  {
    this.binding.code.webview.registerHandler("requestLoadProject", this.requestProjectHandler);
    this.binding.code.webview.registerHandler("printJsLog", this.printJsLogHandler);
    this.binding.code.webview.registerHandler("blocklyIsReady", this.tellMainboardHanlder);
    this.binding.code.webview.registerHandler("sendValueToWidget", this.sendValueToWidgetHandler);
    this.binding.code.webview.registerHandler("reportCurrentWidget", this.reportCurrentWidgetHandler);
    this.binding.code.webview.registerHandler("requestWidgetValue", this.requestWidgetValueHandler);
    this.binding.code.webview.registerHandler("requestWidgetName", this.requestWidgetNameHandler);
    this.binding.code.webview.registerHandler("saveControlPanel", this.saveControlPanelHandler);
    this.binding.code.webview.registerHandler("airblockIsReady", this.airblockIsReadyHandler);
    registerReceiveListener();
    this.binding.panelCellLayout.setSendWidgetValueCallback(this.sendWidgetValueCallback);
    this.hasInit = true;
  }
  
  public boolean isCreate()
  {
    return this.panelActivity.getIntent().getAction().equalsIgnoreCase("create_controlpannel");
  }
  
  @Bindable
  public boolean isDesignMode()
  {
    return this.currentMode == 1;
  }
  
  @Bindable
  public boolean isFormChangeable()
  {
    return this.mProjectBeanWrapper.isFormChangeable();
  }
  
  @Bindable
  public boolean isPlayMode()
  {
    return this.currentMode == 0;
  }
  
  @Bindable
  public boolean isShowCodePanel()
  {
    return this.isShowCodePanel;
  }
  
  @Bindable
  public boolean isShowExpand()
  {
    return this.isShowExpand;
  }
  
  public void jumpToBlockly(WidgetData paramWidgetData)
  {
    StatisticsTool.getInstance().onEvent("EnterBlockly", "进入Blockly页面");
    callJSMethod("switchToCodingPanel", Integer.valueOf(paramWidgetData.widgetID));
    setHasEnteredBlockly();
    setShowCodePanel(true);
  }
  
  public void loadCurrentProjectBean()
  {
    addProjectToUI(this.mProjectBeanWrapper.getProjectBean());
    if (this.mProjectBeanWrapper.projectIsNewlyCreated()) {
      this.isCreateFirstEnter = true;
    }
    setMode(0);
    if (this.mProjectBeanWrapper.projectIsNewlyCreated()) {
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          PanelViewModel.this.setMode(1);
        }
      }, 500L);
    }
  }
  
  public void modifyRobotForm(String paramString)
  {
    this.mProjectBeanWrapper.modifyRobotForm(paramString, this);
  }
  
  public void navToGroup(View paramView)
  {
    paramView = paramView.getContext();
    if ((paramView instanceof Activity)) {
      ActivityJumpUtil.jumpToGroupActivity((Activity)paramView, "enterCommunityFromCP");
    }
  }
  
  public void onBackPressed()
  {
    if (this.isShowCodePanel)
    {
      quitBlockly();
      return;
    }
    if (this.mProjectBeanWrapper.shouldSaveAsNewProject())
    {
      saveAsNewProject();
      return;
    }
    if (this.mProjectBeanWrapper.shouldSaveModification())
    {
      saveModification();
      return;
    }
    finish(null);
  }
  
  public void onCellLayoutClick(View paramView)
  {
    if (this.isWidgetListOn)
    {
      this.isWidgetListOn = false;
      notifyPropertyChanged(84);
    }
  }
  
  public void onConnectedStateChange(Device paramDevice)
  {
    if (!this.initDevice.equals(paramDevice))
    {
      this.shouldFinish = true;
      callJSMethod("requestSaveControlPanel", null);
    }
  }
  
  public void onCreate()
  {
    if ((this.mProjectBeanWrapper.isOfficial()) && (ControllerManager.getInstance().isConnectedOk()))
    {
      if (!this.mProjectBeanWrapper.isRobotFormSelfBalance()) {
        break label55;
      }
      if (((this.device instanceof Ranger)) || ((this.device instanceof Ultimate2))) {
        setRJ25Mode(3);
      }
    }
    label55:
    while ((!(this.device instanceof Ranger)) && (!(this.device instanceof Ultimate2))) {
      return;
    }
    setRJ25Mode(0);
  }
  
  public void onDestroy()
  {
    this.binding.code.webview.destroy();
    if ((this.device instanceof MakeBlockDevice)) {
      ((MakeBlockDevice)this.device).removeMakeBlockStatusAdapter(this.receiveBluetoothDataListener);
    }
    ControllerManager.getInstance().unRegisterDeviceListener(this.onConnectedStateChangeListener);
  }
  
  public void onJoystickTrigger(int paramInt, float paramFloat)
  {
    if (((this.device instanceof Ranger)) || ((this.device instanceof Ultimate2)))
    {
      int i;
      short s2;
      short s1;
      if ((this.device instanceof Ranger))
      {
        i = ((Ranger)this.device).getMode();
        if (i != 3) {
          break label213;
        }
        s2 = 0;
        if ((paramInt < 0) || (paramInt > 90)) {
          break label111;
        }
        i = 90 - paramInt;
        s1 = 255;
      }
      for (;;)
      {
        if (!(this.device instanceof Ranger)) {
          break label198;
        }
        ((Ranger)this.device).moveDummyJoystick2((short)i, s1);
        return;
        i = ((Ultimate2)this.device).getMode();
        break;
        label111:
        if ((paramInt > 90) && (paramInt <= 180))
        {
          i = 90 - paramInt;
          s1 = 255;
        }
        else if ((paramInt > -90) && (paramInt < 0))
        {
          i = paramInt + 90;
          s1 = 65281;
        }
        else
        {
          s1 = s2;
          i = paramInt;
          if (paramInt > 65356)
          {
            s1 = s2;
            i = paramInt;
            if (paramInt <= -90)
            {
              i = -90 - paramInt;
              s1 = 65281;
            }
          }
        }
      }
      label198:
      ((Ultimate2)this.device).moveDummyJoystick2((short)i, s1);
      return;
    }
    label213:
    float f3 = 0.0F;
    float f4 = 0.0F;
    float f1;
    float f2;
    if ((paramInt >= 0) && (paramInt <= 90))
    {
      f1 = 255.0F;
      f2 = paramInt * 17 / 3 - 255;
    }
    for (;;)
    {
      if (paramFloat == 0.0F)
      {
        f1 = 0.0F;
        f2 = 0.0F;
      }
      triggerDeviceJoystick(this.device, (int)(f1 * paramFloat), (int)(f2 * paramFloat));
      return;
      if ((paramInt > 90) && (paramInt <= 180))
      {
        f1 = 765 - paramInt * 17 / 3;
        f2 = 255.0F;
      }
      else if ((paramInt > -90) && (paramInt < 0))
      {
        f1 = -255.0F;
        f2 = paramInt * 17 / 3 + 255;
      }
      else
      {
        f1 = f3;
        f2 = f4;
        if (paramInt > 65356)
        {
          f1 = f3;
          f2 = f4;
          if (paramInt <= -90)
          {
            f1 = -paramInt * 17 / 3 - 765;
            f2 = -255.0F;
          }
        }
      }
    }
  }
  
  public void onPause()
  {
    exitPlayMode();
    this.isVisible = false;
  }
  
  public void onResume()
  {
    if (this.hasInit)
    {
      if (!isPlayMode()) {
        break label60;
      }
      enterPlayMode();
    }
    for (;;)
    {
      modifyRobotForm(this.mProjectBeanWrapper.getRobotForm());
      this.isVisible = true;
      if (this.isWaitShowExpandGuide) {
        showExpandGuide(this.mProjectBeanWrapper.getNameKey(), this.mProjectBeanWrapper.getName());
      }
      return;
      label60:
      exitPlayMode();
    }
  }
  
  public void onShowWidget(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      enterPlayMode();
      return;
    }
    exitPlayMode();
  }
  
  public void onSpeakerStateSend(int paramInt)
  {
    switch (paramInt)
    {
    case 5: 
    default: 
      triggerDeviceJoystick(this.device, 0, 0);
      return;
    case 1: 
      triggerDeviceJoystick(this.device, 255, 255);
      return;
    case 2: 
      triggerDeviceJoystick(this.device, 65281, 65281);
      return;
    case 3: 
      triggerDeviceJoystick(this.device, 65281, 255);
      return;
    case 4: 
      triggerDeviceJoystick(this.device, 255, 65281);
      return;
    }
    triggerDeviceJoystick(this.device, 65281, 255);
  }
  
  public void onStart()
  {
    this.binding.code.webview.registerHandler("sendViaBluetooth", this.sendBluetoothHandler);
    this.binding.code.webview.registerHandler("sendViaBluetoothUnreliably", this.sendBluetoothHandler);
  }
  
  public void onStop()
  {
    this.binding.code.webview.unregisterHandler("sendViaBluetooth");
    this.binding.code.webview.unregisterHandler("sendViaBluetoothUnreliably");
  }
  
  public void quitBlockly()
  {
    setShowCodePanel(false);
    notifyPropertyChanged(63);
    callJSMethod("switchToControlPanel", null);
  }
  
  public void removeCellView(WidgetData paramWidgetData)
  {
    this.mProjectBeanWrapper.removeWidgetData(paramWidgetData);
    callJSMethod("widgetDelete", Integer.valueOf(paramWidgetData.widgetID));
  }
  
  public void resetSmartServo(View paramView)
  {
    paramView = new ConfirmDialog(paramView.getContext());
    paramView.setTitleText(TextUtil.getStringById(2131493032));
    paramView.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        if ((PanelViewModel.this.device instanceof SmartServo)) {
          ((SmartServo)PanelViewModel.this.device).reset();
        }
      }
    });
    paramView.show();
  }
  
  public void setHasEnteredBlockly()
  {
    this.mProjectBeanWrapper.setHasEnteredBlockly(true);
  }
  
  public void setMode(int paramInt)
  {
    if (this.currentMode != paramInt)
    {
      this.currentMode = paramInt;
      if ((paramInt != -1) && (paramInt != 0)) {
        break label75;
      }
      enterPlayMode();
      this.panelActivity.setModeToPlay();
      turnOffWidgetList();
      if (!this.isCreateFirstEnter) {
        this.panelActivity.showConnectDialog();
      }
      this.isCreateFirstEnter = false;
    }
    for (;;)
    {
      notifyPropertyChanged(49);
      notifyPropertyChanged(52);
      notifyPropertyChanged(17);
      return;
      label75:
      if (paramInt == 1)
      {
        exitPlayMode();
        this.panelActivity.setModeToDesign();
        turnOnWidgetList();
      }
    }
  }
  
  public void setRobotForm(String paramString)
  {
    if (((this.device instanceof Starter)) || ((this.device instanceof Ranger)) || ((this.device instanceof Ultimate2))) {
      switch (Integer.valueOf(paramString).intValue())
      {
      }
    }
    for (;;)
    {
      notifyPropertyChanged(27);
      notifyPropertyChanged(28);
      return;
      if (((this.device instanceof Starter)) && (this.currentMode == 0)) {
        enterPlayMode();
      }
      setRJ25Mode(0);
      StatisticsTool.getInstance().onEvent("SwitchRobotModeToManual", "设置模式为手动");
      continue;
      if ((this.device instanceof Starter)) {
        exitPlayMode();
      }
      setRJ25Mode(1);
      StatisticsTool.getInstance().onEvent("SwitchRobotModeToObstacleVoid", "设置模式为避障");
      continue;
      setRJ25Mode(2);
      StatisticsTool.getInstance().onEvent("SwitchRobotModeToLineFollow", "设置模式为巡线");
      continue;
      setRJ25Mode(3);
      StatisticsTool.getInstance().onEvent("SwitchRobotModeToSelfBalance", "设置模式为自平衡");
    }
  }
  
  public void showAirblockTuningDialog(View paramView)
  {
    paramView = new AirblockTuneDialog(paramView.getContext());
    paramView.show();
    if (paramView.getWindow() != null) {
      paramView.getWindow().setLayout(ScreenHelper.getPercentWidthToPx(0.74375F), ScreenHelper.getPercentWidthToPx(0.54583335F));
    }
  }
  
  public void showExpandGuide()
  {
    showExpandDialog(SettingsManager.getExpandGuideData(this.panelActivity, File.separator + getExpandDirectory(this.mProjectBeanWrapper.getBoardName()) + File.separator + this.mProjectBeanWrapper.getNameKey()), this.mProjectBeanWrapper.getName(), getExpandDirectory(this.mProjectBeanWrapper.getBoardName()));
  }
  
  public void showModeSelectPopup(View paramView)
  {
    this.panelActivity.showPopupWindowForModeSelector(paramView, this.mProjectBeanWrapper.getFormSelectableTexts());
  }
  
  public void turnOffWidgetList()
  {
    if (this.isWidgetListOn)
    {
      this.isWidgetListOn = false;
      notifyPropertyChanged(84);
    }
  }
  
  public void turnOnWidgetList()
  {
    if (!this.isWidgetListOn)
    {
      this.isWidgetListOn = true;
      notifyPropertyChanged(84);
    }
  }
  
  public void unlockServo()
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(this.panelActivity);
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493040));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        if ((PanelViewModel.this.device instanceof SmartServo)) {
          ((SmartServo)PanelViewModel.this.device).unlock();
        }
      }
    });
    localConfirmDialog.show();
  }
  
  public void widgetUpdate(WidgetData paramWidgetData)
  {
    callJSMethod("widgetUpdate", new WidgetUpdateJson(paramWidgetData.widgetID, paramWidgetData.getWidgetUpdateJson()));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\PanelViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */