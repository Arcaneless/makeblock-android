package cc.makeblock.makeblock.scene.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import cc.makeblock.makeblock.base.NotifyBluetoothActivity;
import cc.makeblock.makeblock.base.ToastUtil;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog;
import cc.makeblock.makeblock.customview.dialog.ForceUpdateDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.dialog.UpdateHintDialog;
import cc.makeblock.makeblock.customview.dialog.UpdateHintDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.databinding.ActivityMainBinding;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnDeviceChangeListener;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.scene.laboratory.LaboratoryActivity;
import cc.makeblock.makeblock.viewmodel.scene.main.MainActivityViewModel;
import cc.makeblock.makeblock.viewmodel.scene.main.MainActivityViewModel.MainActivityView;
import cc.makeblock.makeblock.viewmodel.scene.main.MainContentViewModel;
import java.util.HashMap;
import java.util.Map;

public class MainActivity
  extends NotifyBluetoothActivity
  implements ControllerManager.OnDeviceChangeListener, MainActivityViewModel.MainActivityView
{
  public static final String KEY_IS_ENTER_CONNECT = "is_enter_connect";
  public static final int REQUEST_CODE_BOARD_NAME = 1;
  public static final int REQUEST_CODE_SWITCH_ROBOT = 2;
  private static final String TAG = MainActivity.class.getSimpleName();
  private ActivityMainBinding binding;
  private Device device;
  private long exitTime = 0L;
  private ForceUpdateDialog forceUpdateDialog;
  private Map<String, Integer> layoutMap = new HashMap(6);
  private MainContentViewModel mainContentViewModel;
  private MainActivityViewModel viewModel;
  
  private void firstEnterHandler()
  {
    if (SharedPreferencesUtils.getCurrentChooseDeviceBoardName().isEmpty()) {
      ActivityJumpUtil.jumpToChooseDeviceActivity(this, 2, null);
    }
  }
  
  private Object getMainContentViewModel()
  {
    if (this.mainContentViewModel == null) {
      this.mainContentViewModel = new MainContentViewModel(this.device);
    }
    return this.mainContentViewModel;
  }
  
  private void inflateContent()
  {
    Integer localInteger = (Integer)this.layoutMap.get(ControllerManager.getInstance().getChosenBoardName());
    if (localInteger == null) {
      return;
    }
    this.binding.container.removeAllViews();
    DataBindingUtil.inflate(getLayoutInflater(), localInteger.intValue(), this.binding.container, true).setVariable(79, getMainContentViewModel());
  }
  
  private void initLayoutMap()
  {
    this.layoutMap.put("mcore", Integer.valueOf(2131427376));
    this.layoutMap.put("orion", Integer.valueOf(2131427373));
    this.layoutMap.put("auriga", Integer.valueOf(2131427376));
    this.layoutMap.put("megaPi", Integer.valueOf(2131427373));
    this.layoutMap.put("crystal", Integer.valueOf(2131427373));
    this.layoutMap.put("octopus", Integer.valueOf(2131427373));
    this.layoutMap.put("codey", Integer.valueOf(2131427373));
  }
  
  private void resetDevice()
  {
    String str = SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
    ControllerManager.getInstance().setChosenDevice(str);
    setCurrentDevice(ControllerManager.getInstance().getCurrentDevice());
  }
  
  private void setCurrentDevice(Device paramDevice)
  {
    if ((this.device == null) || ((paramDevice != null) && (!paramDevice.equals(this.device)))) {
      inflateContent();
    }
    this.device = paramDevice;
    setLaboratoryStatus();
    this.viewModel.setDevice(paramDevice);
    if (this.mainContentViewModel != null) {
      this.mainContentViewModel.setDevice(paramDevice);
    }
  }
  
  private void setLaboratoryStatus()
  {
    this.binding.laboratory.setVisibility(8);
  }
  
  protected boolean isAutoShowConnectDialog()
  {
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    do
    {
      do
      {
        return;
      } while (paramInt2 != 0);
      return;
    } while (paramInt2 != -1);
    resetDevice();
  }
  
  public void onBackPressed()
  {
    if (System.currentTimeMillis() - this.exitTime > 2000L)
    {
      ToastUtil.showToast(2131493195);
      this.exitTime = System.currentTimeMillis();
      return;
    }
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      getWindow().getDecorView().postDelayed(new Runnable()
      {
        public void run()
        {
          MainActivity.access$002(MainActivity.this, (ActivityMainBinding)DataBindingUtil.setContentView(MainActivity.this, 2131427372));
          MainActivity.this.binding.setViewModel(MainActivity.this.viewModel);
          MainActivity.this.inflateContent();
        }
      }, 500L);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    firstEnterHandler();
    initLayoutMap();
    this.binding = ((ActivityMainBinding)DataBindingUtil.setContentView(this, 2131427372));
    this.viewModel = new MainActivityViewModel(this, ControllerManager.getInstance().getCurrentDevice());
    this.binding.setViewModel(this.viewModel);
    StatisticsTool.getInstance().onEvent("EnterMainActivity", "进入主页面");
    if (getIntent().getBooleanExtra("is_enter_connect", false)) {
      ActivityJumpUtil.jumpToConnectActivity(this, 1);
    }
    this.viewModel.onCreate();
    ControllerManager.getInstance().registerDeviceListener(this);
    setCurrentDevice(ControllerManager.getInstance().getCurrentDevice());
  }
  
  protected void onDestroy()
  {
    ControllerManager.getInstance().unRegisterDeviceListener(this);
    this.viewModel.onDestroy();
    super.onDestroy();
    if ((this.forceUpdateDialog != null) && (this.forceUpdateDialog.isShowing())) {
      this.forceUpdateDialog.dismiss();
    }
  }
  
  public void onDeviceChange(Device paramDevice)
  {
    DeviceBoardName localDeviceBoardName = DeviceBoardName.valueOf(SharedPreferencesUtils.getCurrentChooseDeviceBoardName());
    ControllerManager.getInstance().setChosenBoardName(localDeviceBoardName.name());
    setCurrentDevice(paramDevice);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    inflateContent();
  }
  
  public void onResume()
  {
    super.onResume();
    if ((this.device != null) && (this.device.isConnected())) {
      this.viewModel.forceUpdateAirBlock(this.device);
    }
  }
  
  public void openLaboratory()
  {
    startActivity(new Intent(this, LaboratoryActivity.class));
  }
  
  public void openNavigation()
  {
    ActivityJumpUtil.jumpToNavigationActivity(this);
  }
  
  public void showForceUpdateAirBlockDialog()
  {
    if (this.forceUpdateDialog == null)
    {
      this.forceUpdateDialog = new ForceUpdateDialog(this);
      this.forceUpdateDialog.setOnOperationConfirmedListener(new ForceUpdateDialog.OnOperationConfirmedListener()
      {
        public void onOperationConfirmed()
        {
          ActivityJumpUtil.jumpToFirmwareUpdateActivity(MainActivity.this);
          MainActivity.this.forceUpdateDialog.dismiss();
        }
      });
    }
    if (!this.forceUpdateDialog.isShowing()) {
      this.forceUpdateDialog.show();
    }
  }
  
  public void showNewVersionDialog(AndroidVerInfoData paramAndroidVerInfoData)
  {
    final UpdateHintDialog localUpdateHintDialog = new UpdateHintDialog(this);
    localUpdateHintDialog.setData(paramAndroidVerInfoData);
    localUpdateHintDialog.setOnOperationConfirmedListener(new UpdateHintDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        localUpdateHintDialog.dismiss();
      }
    });
    localUpdateHintDialog.show();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\main\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */