package cc.makeblock.makeblock.viewmodel.connect;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.action.ActionHandlerHolder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.UnidentifiedMakeBlockDevice;
import cc.makeblock.makeblock.engine.device.UnidentifiedMakeBlockDevice.DeviceStatusChangeListener;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.scene.connect.ConnectStateEnum;
import cc.makeblock.makeblock.scene.connect.InitiatingDeviceActivity;
import cc.makeblock.makeblock.scene.firmware.FirmwareUpdateActivity;

public class InitiatingActivityViewModel
  extends BaseObservable
{
  private ConnectStateEnum connectingState;
  private UnidentifiedMakeBlockDevice.DeviceStatusChangeListener deviceStatusChangeListener = new UnidentifiedMakeBlockDevice.DeviceStatusChangeListener()
  {
    public void onDeviceIdentified(MakeBlockDevice paramAnonymousMakeBlockDevice)
    {
      ControllerManager.getInstance().setCurrentDevice(paramAnonymousMakeBlockDevice);
      InitiatingActivityViewModel.this.leave(true);
    }
  };
  private InitiatingDeviceActivity initiatingDeviceActivity;
  private boolean isFirmwareUpdateInit = false;
  private Device oldDevice;
  private Action queryFirmwareVersionAction;
  private boolean success;
  private UnidentifiedMakeBlockDevice unidentifiedMakeBlockDevice;
  
  public InitiatingActivityViewModel(InitiatingDeviceActivity paramInitiatingDeviceActivity)
  {
    this.initiatingDeviceActivity = paramInitiatingDeviceActivity;
  }
  
  private Runnable createQueryFirmwareRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        InitiatingActivityViewModel.this.unidentifiedMakeBlockDevice.queryFirmwareVersion();
      }
    };
  }
  
  private boolean isBluetoothActive()
  {
    return ControllerManager.getInstance().isConnectedOk();
  }
  
  private void queryFirmwareVersion()
  {
    if (isBluetoothActive())
    {
      this.queryFirmwareVersionAction = new ActionBuilder().append(createQueryFirmwareRunnable(), 0L).append(createQueryFirmwareRunnable(), 500L).append(createQueryFirmwareRunnable(), 500L).append(createQueryFirmwareRunnable(), 500L).build();
      this.queryFirmwareVersionAction.execute();
    }
  }
  
  private void showErrorDialog()
  {
    ControllerManager.getInstance().showBleErrorDialog(this.initiatingDeviceActivity);
    this.initiatingDeviceActivity.removeCallbacks();
  }
  
  public void checkConnection()
  {
    if (!isBluetoothActive()) {
      showErrorDialog();
    }
  }
  
  public void fail()
  {
    if (this.isFirmwareUpdateInit) {}
    for (ConnectStateEnum localConnectStateEnum = ConnectStateEnum.INITIATING_FAIL_FIRMWARE_UPDATE;; localConnectStateEnum = ConnectStateEnum.INITIATING_FAIL)
    {
      this.connectingState = localConnectStateEnum;
      notifyPropertyChanged(0);
      return;
    }
  }
  
  @Bindable
  public int getChooseLayoutVisibility()
  {
    if (this.connectingState == ConnectStateEnum.INITIATING_FAIL_FIRMWARE_UPDATE) {
      return 0;
    }
    return 8;
  }
  
  @Bindable
  public int getFailLayoutVisibility()
  {
    if (this.connectingState == ConnectStateEnum.INITIATING_FAIL) {
      return 0;
    }
    return 8;
  }
  
  @Bindable
  public int getInitiatingLayoutVisibility()
  {
    if (this.connectingState == ConnectStateEnum.INITIATING) {
      return 0;
    }
    return 8;
  }
  
  @Bindable
  public boolean isShowBack()
  {
    return (!this.isFirmwareUpdateInit) && (!this.success);
  }
  
  public void jumpToFirmUpdate()
  {
    if (isBluetoothActive())
    {
      Object localObject = new AirBlock(BleManager.getInstance(), ActionHandlerHolder.getHandler());
      ControllerManager.getInstance().setCurrentDevice((Device)localObject);
      localObject = new Intent(this.initiatingDeviceActivity, FirmwareUpdateActivity.class);
      ((Intent)localObject).putExtra("FIRMWARE_IS_UNRECOGNIZABLE", true);
      this.initiatingDeviceActivity.removeCallbacks();
      this.initiatingDeviceActivity.startActivityForResult((Intent)localObject, 2);
      return;
    }
    showErrorDialog();
  }
  
  public void jumpToWeb()
  {
    ActivityJumpUtil.jumpToGroupActivity(this.initiatingDeviceActivity, this.initiatingDeviceActivity.getString(2131493228), 1);
    BleManager.getInstance().disconnectBluetooth();
  }
  
  public void leave(boolean paramBoolean)
  {
    this.success = paramBoolean;
    notifyPropertyChanged(62);
    InitiatingDeviceActivity localInitiatingDeviceActivity = this.initiatingDeviceActivity;
    if (paramBoolean) {}
    for (int i = -1;; i = 0)
    {
      localInitiatingDeviceActivity.setResult(i);
      if (paramBoolean) {
        break;
      }
      BleManager.getInstance().disconnectBluetooth();
      ControllerManager.getInstance().setCurrentDevice(this.oldDevice, true);
      this.initiatingDeviceActivity.finish();
      return;
    }
    this.initiatingDeviceActivity.postFinish(500L);
  }
  
  public void onBackPressed()
  {
    if (isShowBack()) {
      leave(false);
    }
  }
  
  public void onCreate()
  {
    this.success = false;
    this.connectingState = ConnectStateEnum.INITIATING;
    this.oldDevice = ControllerManager.getInstance().getCurrentDevice();
    this.unidentifiedMakeBlockDevice = new UnidentifiedMakeBlockDevice(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    ControllerManager.getInstance().setCurrentDevice(this.unidentifiedMakeBlockDevice);
    if (this.initiatingDeviceActivity == null) {
      throw new RuntimeException("activity为空??");
    }
    Intent localIntent = this.initiatingDeviceActivity.getIntent();
    if (localIntent != null) {
      this.isFirmwareUpdateInit = localIntent.getBooleanExtra("FIRMWARE_UPDATE_INIT", false);
    }
    this.unidentifiedMakeBlockDevice.registerDeviceStatusChangeListener(this.deviceStatusChangeListener);
    queryFirmwareVersion();
    notifyPropertyChanged(0);
  }
  
  public void onDestroy()
  {
    this.unidentifiedMakeBlockDevice.removeDeviceStatusChangeListener(this.deviceStatusChangeListener);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\connect\InitiatingActivityViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */