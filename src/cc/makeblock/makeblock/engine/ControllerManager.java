package cc.makeblock.makeblock.engine;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase.Builder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.customview.dialog.WarningDialog;
import cc.makeblock.makeblock.customview.dialog.WarningDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.engine.action.ActionHandlerHolder;
import cc.makeblock.makeblock.engine.blockly.BlocklyUtil;
import cc.makeblock.makeblock.engine.blockly.FirmwareBean;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.device.SmartServo;
import cc.makeblock.makeblock.engine.device.Starter;
import cc.makeblock.makeblock.engine.device.Ultimate2;
import cc.makeblock.makeblock.engine.device.UnidentifiedMakeBlockDevice;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.diy.database.AppDatabase;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.project.ProjectBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ml.xuexin.bleconsultant.entity.BleDevice;

public class ControllerManager
{
  private static final String TAG = ControllerManager.class.getSimpleName();
  private static ControllerManager instance;
  private static final int interval = 200;
  private static long lastTiltOrShakeTime;
  private AppDatabase appDatabase;
  private Device currentDevice;
  private List<OnConnectedStateChangeListener> onConnectedStateChangeListeners = new ArrayList();
  private List<OnDeviceChangeListener> onDeviceChangeListeners = new ArrayList();
  
  public static ControllerManager getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new ControllerManager();
      }
      return instance;
    }
    finally {}
  }
  
  private void notifyDeviceChange(Device paramDevice)
  {
    Iterator localIterator = new ArrayList(this.onDeviceChangeListeners).iterator();
    while (localIterator.hasNext()) {
      ((OnDeviceChangeListener)localIterator.next()).onDeviceChange(paramDevice);
    }
  }
  
  private void updateChooseBoardName(Device paramDevice)
  {
    if (((paramDevice instanceof MakeBlockDevice)) && (!(paramDevice instanceof UnidentifiedMakeBlockDevice)))
    {
      paramDevice = ((MakeBlockDevice)paramDevice).getBoardName();
      if (!paramDevice.equals(getChosenBoardName())) {
        setChosenBoardName(paramDevice.name());
      }
    }
  }
  
  public Device boardNameToDevice(@Nullable String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return new UnidentifiedMakeBlockDevice(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    }
    switch (DeviceBoardName.valueOf(paramString))
    {
    default: 
      return new UnidentifiedMakeBlockDevice(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new MBot(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new Ranger(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new AirBlock(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new Starter(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new Ultimate2(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    case ???: 
      return new SmartServo(BleManager.getInstance(), ActionHandlerHolder.getHandler());
    }
    return new Codey(BleManager.getInstance(), ActionHandlerHolder.getHandler());
  }
  
  public void cancelConnect()
  {
    if (!isConnectedOk()) {
      BleManager.getInstance().disconnectBluetooth();
    }
  }
  
  public String createBoardInfoJson(ProjectBean paramProjectBean)
  {
    if (paramProjectBean.boardName.equalsIgnoreCase("crystal"))
    {
      Object localObject = paramProjectBean.boardName;
      switch (Integer.parseInt(paramProjectBean.robotForm))
      {
      default: 
        paramProjectBean = (ProjectBean)localObject;
      }
      for (;;)
      {
        localObject = paramProjectBean;
        paramProjectBean = (ProjectBean)localObject;
        if (((String)localObject).equals("AirBlockShip")) {
          paramProjectBean = "AirBlockCar";
        }
        localObject = new FirmwareBean();
        ((FirmwareBean)localObject).type = paramProjectBean;
        if ((this.currentDevice instanceof MakeBlockDevice)) {
          ((FirmwareBean)localObject).version = ((MakeBlockDevice)this.currentDevice).getFirmwareVersion();
        }
        return JsonUtil.objectToJson(localObject);
        paramProjectBean = "AirBlockDrone";
        continue;
        paramProjectBean = "AirBlockShip";
        continue;
        paramProjectBean = "AirBlockCar";
        continue;
        paramProjectBean = "AirBlockCustom";
      }
    }
    paramProjectBean = DeviceBoardName.valueOf(SharedPreferencesUtils.getCurrentChooseDeviceBoardName());
    switch (paramProjectBean)
    {
    default: 
      paramProjectBean = DeviceBoardName.unknown;
    }
    for (;;)
    {
      paramProjectBean = paramProjectBean.name();
      break;
      paramProjectBean = DeviceBoardName.mcore;
      continue;
      paramProjectBean = DeviceBoardName.orion;
      continue;
      paramProjectBean = DeviceBoardName.auriga;
      continue;
      paramProjectBean = DeviceBoardName.megaPi;
    }
  }
  
  public void disconnect()
  {
    BleManager.getInstance().disconnectBluetooth();
  }
  
  public AppDatabase getAppDatabase()
  {
    return this.appDatabase;
  }
  
  public String getChosenBoardName()
  {
    return SharedPreferencesUtils.getCurrentChooseDeviceBoardName();
  }
  
  public Device getCurrentDevice()
  {
    return this.currentDevice;
  }
  
  public int getMode()
  {
    int i = -1;
    if ((this.currentDevice instanceof Ranger)) {
      i = ((Ranger)this.currentDevice).getMode();
    }
    do
    {
      return i;
      if ((this.currentDevice instanceof Ultimate2)) {
        return ((Ultimate2)this.currentDevice).getMode();
      }
    } while (!(this.currentDevice instanceof Starter));
    return ((Starter)this.currentDevice).getMode();
  }
  
  public void init()
  {
    setChosenDevice(getChosenBoardName());
    this.appDatabase = linkDataBase("projects");
  }
  
  public boolean isConnectedOk()
  {
    return (this.currentDevice != null) && (this.currentDevice.isConnected());
  }
  
  public boolean isNotifyDevice(Device paramDevice)
  {
    return !(paramDevice instanceof UnidentifiedMakeBlockDevice);
  }
  
  public AppDatabase linkDataBase(String paramString)
  {
    return (AppDatabase)Room.databaseBuilder(App.getContext(), AppDatabase.class, paramString).build();
  }
  
  public void notifyConnectedStateChange(boolean paramBoolean)
  {
    Iterator localIterator = new ArrayList(this.onConnectedStateChangeListeners).iterator();
    while (localIterator.hasNext()) {
      ((OnConnectedStateChangeListener)localIterator.next()).onConnectedStateChange(paramBoolean);
    }
  }
  
  public void onConnectDisconnected()
  {
    notifyConnectedStateChange(false);
  }
  
  public void onConnectionEndByUser()
  {
    SharedPreferencesUtils.setLastSuccessAddress("");
  }
  
  public void onConnectionSuccess()
  {
    try
    {
      String str = BleManager.getInstance().getConnectedDevice().getAddress();
      if (str != null) {
        SharedPreferencesUtils.setLastSuccessAddress(str);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public void onExitApp()
  {
    BleManager.getInstance().onExit();
  }
  
  public void registerConnectingStateListener(OnConnectedStateChangeListener paramOnConnectedStateChangeListener)
  {
    this.onConnectedStateChangeListeners.add(paramOnConnectedStateChangeListener);
  }
  
  public Device registerDeviceListener(OnDeviceChangeListener paramOnDeviceChangeListener)
  {
    this.onDeviceChangeListeners.add(paramOnDeviceChangeListener);
    return getCurrentDevice();
  }
  
  @Deprecated
  public void sendCommand(Command paramCommand)
  {
    BleManager.getInstance().writeToBluetooth(paramCommand.bytes);
  }
  
  @Deprecated
  public void sendStringDataToBluetooth(String paramString)
  {
    try
    {
      paramString = BlocklyUtil.parseStringToBytes(paramString);
      getInstance().sendCommand(new Command.Builder().appendCommand(paramString).build());
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setChosenBoardName(String paramString)
  {
    SharedPreferencesUtils.setChosenBoardName(paramString);
  }
  
  public void setChosenDevice(String paramString)
  {
    paramString = boardNameToDevice(paramString);
    if (paramString != null) {
      getInstance().setCurrentDevice(paramString);
    }
  }
  
  public void setCurrentDevice(Device paramDevice)
  {
    updateChooseBoardName(paramDevice);
    setCurrentDevice(paramDevice, false);
  }
  
  public void setCurrentDevice(Device paramDevice, boolean paramBoolean)
  {
    this.currentDevice = paramDevice;
    BleManager.getInstance().registerDevice(paramDevice);
    if (paramBoolean) {}
    do
    {
      return;
      if (isNotifyDevice(paramDevice)) {
        notifyDeviceChange(paramDevice);
      }
    } while (!(this.currentDevice instanceof Codey));
    SharedPreferencesUtils.setShowCodey(true);
  }
  
  public void showBleErrorDialog(final Activity paramActivity)
  {
    WarningDialog localWarningDialog = new WarningDialog(paramActivity);
    localWarningDialog.setOnOperationConfirmedListener(new WarningDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        App.getContext().restart(paramActivity);
      }
    });
    localWarningDialog.setTitleText(TextUtil.getStringById(2131492978));
    localWarningDialog.show();
  }
  
  public void unRegisterConnectingStateListener(OnConnectedStateChangeListener paramOnConnectedStateChangeListener)
  {
    this.onConnectedStateChangeListeners.remove(paramOnConnectedStateChangeListener);
  }
  
  public void unRegisterDeviceListener(OnDeviceChangeListener paramOnDeviceChangeListener)
  {
    this.onDeviceChangeListeners.remove(paramOnDeviceChangeListener);
  }
  
  public static abstract interface OnConnectedStateChangeListener
  {
    public abstract void onConnectedStateChange(boolean paramBoolean);
  }
  
  public static abstract interface OnDeviceChangeListener
  {
    public abstract void onDeviceChange(Device paramDevice);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\ControllerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */