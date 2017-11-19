package cc.makeblock.makeblock.viewmodel.scene.main;

import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData.DataBean;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public class MainActivityViewModel
  extends DeviceViewModel<Device>
{
  public static final boolean isHideLaboratory = true;
  private MainActivityView mView;
  
  public MainActivityViewModel(MainActivityView paramMainActivityView, Device paramDevice)
  {
    super(paramDevice);
    this.mView = paramMainActivityView;
  }
  
  public void forceUpdateAirBlock(Device paramDevice)
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
          this.mView.showForceUpdateAirBlockDialog();
          return;
        }
      }
      catch (Exception paramDevice) {}
    }
  }
  
  @Bindable
  public String getCurrentSelectedDevice()
  {
    return ((MakeBlockDevice)this.device).getDeviceName();
  }
  
  public boolean laboratoryVisible()
  {
    return false;
  }
  
  public void onCreate()
  {
    NetManager.getInstance().getVersionInfo(new NetResponseListener()
    {
      public void onResponse(AndroidVerInfoData paramAnonymousAndroidVerInfoData)
      {
        if ((paramAnonymousAndroidVerInfoData != null) && (SharedPreferencesUtils.getApplicationNewVersion() < paramAnonymousAndroidVerInfoData.getData().getVersionCode()))
        {
          MainActivityViewModel.this.mView.showNewVersionDialog(paramAnonymousAndroidVerInfoData);
          SharedPreferencesUtils.setApplicationNewVersion(paramAnonymousAndroidVerInfoData.getData().getVersionCode());
        }
      }
    });
  }
  
  public void onDestroy() {}
  
  public void openLaboratory()
  {
    StatisticsTool.getInstance().onEvent("GoToLaboratory", "打开实验室");
    this.mView.openLaboratory();
  }
  
  public void openNavigation()
  {
    StatisticsTool.getInstance().onEvent("GoToSettings", "打开设置栏");
    this.mView.openNavigation();
  }
  
  public void setDevice(Device paramDevice)
  {
    super.setDevice(paramDevice);
    notifyPropertyChanged(14);
  }
  
  public static abstract interface MainActivityView
  {
    public abstract void openLaboratory();
    
    public abstract void openNavigation();
    
    public abstract void showForceUpdateAirBlockDialog();
    
    public abstract void showNewVersionDialog(AndroidVerInfoData paramAndroidVerInfoData);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\scene\main\MainActivityViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */