package cc.makeblock.makeblock.viewmodel.connect;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationCanceledListener;
import cc.makeblock.makeblock.customview.dialog.ConfirmDialog.OnOperationConfirmedListener;
import cc.makeblock.makeblock.customview.util.TouchRegion;
import cc.makeblock.makeblock.databinding.ItemMenuSearchDeviceBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuSearchHeaderBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.bluetooth.BleConnectCallback;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.scene.connect.ConnectActivity;
import cc.makeblock.makeblock.scene.connect.ConnectStateEnum;
import cc.makeblock.makeblock.viewmodel.slidemenu.SearchDeviceModel;
import cc.makeblock.makeblock.viewmodel.slidemenu.TextViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import ml.xuexin.bleconsultant.entity.BleDevice;
import ml.xuexin.bleconsultant.port.ScanDevicesHelper;

public class ConnectActivityViewModel
  extends BaseObservable
{
  public static final String CONNECT_BOARD_NAME = "CONNECT_BOARD_NAME";
  private boolean autoConnectionEnable;
  private LinkedHashSet<BleDevice> bleDevices = new LinkedHashSet();
  private int bluetoothState = 0;
  private final ConnectActivity connectActivity;
  private ConnectStateEnum connectingState;
  private boolean hasRequest = false;
  private boolean isConnected;
  private boolean isShowMenu = false;
  private String lastAddress;
  LayoutInflater layoutInflater;
  private View menuHeaderView;
  private List<View> waitAddItems = new ArrayList();
  
  public ConnectActivityViewModel(ConnectActivity paramConnectActivity)
  {
    this.connectActivity = paramConnectActivity;
    if (BleManager.getInstance().isSupportBle())
    {
      if (BleManager.getInstance().isBluetoothEnable()) {}
      for (paramConnectActivity = ConnectStateEnum.BLUETOOTH_ENABLE;; paramConnectActivity = ConnectStateEnum.BLUETOOTH_DISABLED)
      {
        setConnectingState(paramConnectActivity);
        return;
      }
    }
    setConnectingState(ConnectStateEnum.NOT_SUPPORTED);
  }
  
  private void connectDevice(BleDevice paramBleDevice)
  {
    this.bluetoothState = 1;
    setConnectingState(ConnectStateEnum.CONNECTING);
    BleManager.getInstance().connect(paramBleDevice, new BleConnectCallback()
    {
      public void onStateChange(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        }
        for (;;)
        {
          ConnectActivityViewModel.access$502(ConnectActivityViewModel.this, paramAnonymousInt);
          return;
          ConnectActivityViewModel.this.setConnectingState(ConnectStateEnum.CONNECTING_FAIL);
          ConnectActivityViewModel.this.connectActivity.postResume(1000);
          continue;
          ConnectActivityViewModel.this.setConnectingState(ConnectStateEnum.INITIATING);
          ControllerManager.getInstance().notifyConnectedStateChange(true);
          ConnectActivityViewModel.this.resume();
        }
      }
    });
  }
  
  private boolean discovery()
  {
    BleManager.getInstance().startDiscovery(new ScanDevicesHelper()
    {
      private final int firstInterval = 1200;
      private boolean isFirst = true;
      private final int normalInterval = 600;
      
      public boolean deviceFilter(BleDevice paramAnonymousBleDevice)
      {
        paramAnonymousBleDevice = paramAnonymousBleDevice.getName();
        if (TextUtils.isEmpty(paramAnonymousBleDevice)) {}
        while ((!paramAnonymousBleDevice.contains("Makeblock")) && (!paramAnonymousBleDevice.contains("makeblock"))) {
          return false;
        }
        return true;
      }
      
      public long getReportPeriod()
      {
        if (this.isFirst)
        {
          this.isFirst = false;
          return 1200L;
        }
        return 600L;
      }
      
      public void reportDevices(List<BleDevice> paramAnonymousList)
      {
        ConnectActivityViewModel.this.handleDevices(paramAnonymousList);
      }
    });
  }
  
  private BleDevice findDevice(List<BleDevice> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      BleDevice localBleDevice = (BleDevice)paramList.next();
      if (localBleDevice.getAddress().equals(paramString)) {
        return localBleDevice;
      }
    }
    return null;
  }
  
  private BleDevice findNearestDevice(List<BleDevice> paramList)
  {
    BleDevice localBleDevice = null;
    Iterator localIterator = paramList.iterator();
    for (paramList = localBleDevice; localIterator.hasNext(); paramList = localBleDevice)
    {
      label11:
      localBleDevice = (BleDevice)localIterator.next();
      if ((paramList != null) && (paramList.getRssi() >= localBleDevice.getRssi())) {
        break label11;
      }
    }
    return paramList;
  }
  
  private void handleDevices(List<BleDevice> paramList)
  {
    if (this.autoConnectionEnable)
    {
      BleDevice localBleDevice = findDevice(paramList, this.lastAddress);
      if (localBleDevice != null)
      {
        Log.e("lyh", "没管距离,直接连接了");
        connectDevice(localBleDevice);
      }
    }
    do
    {
      do
      {
        int j;
        do
        {
          return;
          if (this.connectingState != ConnectStateEnum.SEARCHING_LIST) {
            break;
          }
          j = -1;
          int i = 0;
          while (i < paramList.size())
          {
            int k = j;
            if (this.bleDevices.add(paramList.get(i)))
            {
              k = j;
              if (j == -1) {
                k = i;
              }
            }
            i += 1;
            j = k;
          }
        } while (j < 0);
        onNewDeviceSearched(paramList.subList(j, paramList.size()));
        return;
      } while ((this.bluetoothState != 0) || (this.connectingState != ConnectStateEnum.SEARCHING_CLOSEST));
      paramList = findNearestDevice(paramList);
    } while ((paramList == null) || (paramList.getRssi() <= -45));
    StatisticsTool.getInstance().onEvent("ConnectThroughBLEApproach", "通过靠近连接连接机器人");
    connectDevice(paramList);
  }
  
  private void onNewDeviceSearched(List<BleDevice> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      final BleDevice localBleDevice = (BleDevice)paramList.next();
      SearchDeviceModel localSearchDeviceModel = new SearchDeviceModel(localBleDevice);
      ItemMenuSearchDeviceBinding localItemMenuSearchDeviceBinding = (ItemMenuSearchDeviceBinding)DataBindingUtil.inflate(this.layoutInflater, 2131427437, null, false);
      localItemMenuSearchDeviceBinding.setViewModel(localSearchDeviceModel);
      localItemMenuSearchDeviceBinding.getRoot().setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ConnectActivityViewModel.access$202(ConnectActivityViewModel.this, false);
          StatisticsTool.getInstance().onEvent("ConnectThroughList", "通过列表连接机器人");
          ConnectActivityViewModel.this.connectDevice(localBleDevice);
        }
      });
      this.waitAddItems.add(localItemMenuSearchDeviceBinding.getRoot());
    }
    notifyPropertyChanged(80);
  }
  
  private boolean requestEnableBluetooth()
  {
    this.hasRequest = this.connectActivity.requestEnableBluetooth();
    return this.hasRequest;
  }
  
  private void restartDiscovery()
  {
    resume();
  }
  
  public void disconnect()
  {
    ConfirmDialog localConfirmDialog = new ConfirmDialog(this.connectActivity);
    localConfirmDialog.setTitleText(TextUtil.getStringById(2131493192));
    localConfirmDialog.setMessage(TextUtil.getStringById(2131493007));
    localConfirmDialog.setCancelText(TextUtil.getStringById(2131492998));
    localConfirmDialog.setConfirmText(TextUtil.getStringById(2131493035));
    localConfirmDialog.setOnOperationConfirmedListener(new ConfirmDialog.OnOperationConfirmedListener()
    {
      public void onOperationConfirmed()
      {
        ControllerManager.getInstance().onConnectionEndByUser();
        ControllerManager.getInstance().disconnect();
        ConnectActivityViewModel.access$602(ConnectActivityViewModel.this, false);
        ConnectActivityViewModel.this.notifyPropertyChanged(8);
        ConnectActivityViewModel.access$702(ConnectActivityViewModel.this, null);
        ConnectActivityViewModel.this.connectActivity.postResume(500);
      }
    });
    localConfirmDialog.setOnOperationCanceledListener(new ConfirmDialog.OnOperationCanceledListener()
    {
      public void onOperationCancel() {}
    });
    localConfirmDialog.show();
  }
  
  @Bindable
  public int getCloseDeviceVisibility()
  {
    switch (this.connectingState)
    {
    default: 
      return 8;
    }
    return 0;
  }
  
  @Bindable
  public int getConnectingLayoutVisibility()
  {
    switch (this.connectingState)
    {
    default: 
      return 0;
    }
    return 8;
  }
  
  @VisibleForTesting
  public ConnectStateEnum getConnectingState()
  {
    return this.connectingState;
  }
  
  @Bindable
  public int getConnectingStateTextId()
  {
    switch (this.connectingState)
    {
    default: 
      return 2131493194;
    case ???: 
      return 2131492982;
    }
    return 2131492980;
  }
  
  @Bindable
  public int getErrorLayoutVisibility()
  {
    switch (this.connectingState)
    {
    default: 
      return 8;
    }
    return 0;
  }
  
  @Bindable
  public int getErrorTextId()
  {
    switch (this.connectingState)
    {
    default: 
      return 2131493194;
    case ???: 
      return 2131492984;
    }
    return 2131492989;
  }
  
  public View getMenuHeaderView()
  {
    return this.menuHeaderView;
  }
  
  @Bindable
  @DrawableRes
  public int getRobotImageId()
  {
    switch (this.connectingState)
    {
    case ???: 
    case ???: 
    case ???: 
    default: 
      return 2131165584;
    case ???: 
    case ???: 
      return 2131165585;
    }
    return 2131165586;
  }
  
  @Bindable
  public int getSearchButtonVisibility()
  {
    switch (this.connectingState)
    {
    case ???: 
    case ???: 
    case ???: 
    default: 
      return 0;
    }
    return 8;
  }
  
  @Bindable
  public List<View> getWaitAddItems()
  {
    if (this.connectingState == ConnectStateEnum.SEARCHING_LIST)
    {
      ArrayList localArrayList = new ArrayList(this.waitAddItems);
      this.waitAddItems.clear();
      return localArrayList;
    }
    return new ArrayList();
  }
  
  public void hideMenu()
  {
    this.isShowMenu = false;
    notifyPropertyChanged(65);
  }
  
  public void initSlideMenu()
  {
    this.layoutInflater = LayoutInflater.from(this.connectActivity);
    LayoutMenuSearchHeaderBinding localLayoutMenuSearchHeaderBinding = (LayoutMenuSearchHeaderBinding)DataBindingUtil.inflate(this.layoutInflater, 2131427488, null, false);
    localLayoutMenuSearchHeaderBinding.setViewModel(new TextViewModel(TextUtil.getStringById(2131492986)));
    this.menuHeaderView = localLayoutMenuSearchHeaderBinding.header;
    localLayoutMenuSearchHeaderBinding.close.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConnectActivityViewModel.this.hideMenu();
      }
    });
    localLayoutMenuSearchHeaderBinding.searchRefresh.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = ObjectAnimator.ofFloat(paramAnonymousView, "rotation", new float[] { 0.0F, 360.0F });
        paramAnonymousView.setDuration(500L);
        paramAnonymousView.setInterpolator(new LinearInterpolator());
        paramAnonymousView.start();
        ConnectActivityViewModel.this.restartDiscovery();
      }
    });
    if (localLayoutMenuSearchHeaderBinding.close != null) {
      new TouchRegion(localLayoutMenuSearchHeaderBinding.close).expandViewTouchRegion(localLayoutMenuSearchHeaderBinding.close, 100).expandViewTouchRegion(localLayoutMenuSearchHeaderBinding.searchRefresh, 100);
    }
  }
  
  @Bindable
  public boolean isConnected()
  {
    return this.isConnected;
  }
  
  @Bindable
  public boolean isNeedClearMenu()
  {
    return true;
  }
  
  @Bindable
  public boolean isShowMenu()
  {
    return this.isShowMenu;
  }
  
  public void leave()
  {
    if (ControllerManager.getInstance().isConnectedOk())
    {
      String str = ControllerManager.getInstance().getChosenBoardName();
      Intent localIntent = new Intent();
      localIntent.putExtra("CONNECT_BOARD_NAME", str);
      this.connectActivity.setResult(-1, localIntent);
      StatisticsTool.getInstance().onEvent("ConnectTo" + str, "成功连接设备" + str);
    }
    for (;;)
    {
      this.connectActivity.finish();
      return;
      this.connectActivity.setResult(0);
      StatisticsTool.getInstance().onEvent("UnconnectedAndLeave", "未连接设备点击返回");
    }
  }
  
  public void onHideMenu()
  {
    this.isShowMenu = false;
    if (this.connectingState == ConnectStateEnum.SEARCHING_LIST) {
      setConnectingState(ConnectStateEnum.SEARCHING_CLOSEST);
    }
  }
  
  public void onShowMenu()
  {
    this.isShowMenu = true;
    this.bleDevices.clear();
    setConnectingState(ConnectStateEnum.SEARCHING_LIST);
  }
  
  public void reset()
  {
    this.isConnected = ControllerManager.getInstance().getCurrentDevice().isConnected();
    this.autoConnectionEnable = SharedPreferencesUtils.isAutoConnectionEnable();
    this.lastAddress = SharedPreferencesUtils.getLastSuccessAddress();
  }
  
  public void resume()
  {
    if (this.isConnected) {}
    do
    {
      do
      {
        return;
        switch (this.connectingState)
        {
        default: 
          return;
        case ???: 
          this.bluetoothState = 0;
          if (BleManager.getInstance().isBluetoothEnable())
          {
            setConnectingState(ConnectStateEnum.BLUETOOTH_ENABLE);
            this.connectActivity.postResume(500);
            return;
          }
          break;
        }
      } while ((this.hasRequest) || (!requestEnableBluetooth()));
      this.connectActivity.postResume(500);
      return;
      this.bluetoothState = 0;
      if (discovery())
      {
        setConnectingState(ConnectStateEnum.SEARCHING_CLOSEST);
        return;
      }
      this.connectActivity.postResume(500);
      return;
      notifyPropertyChanged(44);
      this.bleDevices.clear();
      this.waitAddItems.clear();
    } while (discovery());
    this.connectActivity.postResume(500);
    return;
    this.connectActivity.onBluetoothConnected();
  }
  
  public void setConnectingState(ConnectStateEnum paramConnectStateEnum)
  {
    if (this.connectingState == paramConnectStateEnum) {
      return;
    }
    this.connectingState = paramConnectStateEnum;
    this.connectActivity.setState(this.connectingState);
    notifyPropertyChanged(0);
  }
  
  @VisibleForTesting
  public void setIsShowMenu(boolean paramBoolean)
  {
    this.isShowMenu = paramBoolean;
  }
  
  public void showMenu()
  {
    this.isShowMenu = true;
    notifyPropertyChanged(65);
  }
  
  public void stopDiscovery()
  {
    BleManager.getInstance().stopDiscovery();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\connect\ConnectActivityViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */