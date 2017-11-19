package cc.makeblock.makeblock.viewmodel.firmware;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.text.TextUtils;
import cc.makeblock.makeblock.customview.MakeBlockToast;
import cc.makeblock.makeblock.engine.ActivityJumpUtil;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.device.SmartServo;
import cc.makeblock.makeblock.engine.device.Starter;
import cc.makeblock.makeblock.engine.device.Ultimate2;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;
import cc.makeblock.makeblock.engine.net.data.FirmwareVersionData;
import cc.makeblock.makeblock.engine.net.data.FirmwareVersionData.DataBean;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import java.util.List;

public class FirmwareInfoActivityViewModel
  extends BaseObservable
{
  private static final String FIRMWARE_NAME_AURIGA = "auriga";
  private static final String FIRMWARE_NAME_CRYSTAL = "crystal";
  private static final String FIRMWARE_NAME_MCORE = "mcore";
  private static final String FIRMWARE_NAME_MEGAPI = "megapi";
  private static final String FIRMWARE_NAME_OCTOPUS = "octopus";
  private static final String FIRMWARE_NAME_ORION = "orion";
  private static final String FIRMWARE_NAME_STARTER = "starter";
  private static final String FIRMWARE_NAME_UNKNOWN = "unknown";
  private static final int PAGE_STATE_CONNECTED_DEVICE_INFO = 3;
  private static final int PAGE_STATE_LOADING_FROM_NET = 0;
  private static final int PAGE_STATE_NEW_FIRMWARE_FOUND = 2;
  private static final int PAGE_STATE_UP_TO_DATE = 1;
  private Context context;
  private String currentFirmwareDeviceNumber;
  private String currentFirmwareName;
  private int currentFirmwareProtocol;
  private int currentFirmwareVersion;
  private String currentFirmwareVersionName;
  private int currentPageState = 0;
  private Device device;
  private Handler mHandler = new Handler();
  private int newFirmwareDeviceNumber;
  private int newFirmwareProtocol;
  private int newFirmwareVersion;
  
  public FirmwareInfoActivityViewModel(Context paramContext, Device paramDevice)
  {
    this.context = paramContext;
    this.device = paramDevice;
  }
  
  private void initConnectedDeviceInfo(Device paramDevice)
  {
    if ((paramDevice instanceof MakeBlockDevice)) {
      this.currentFirmwareVersionName = ((MakeBlockDevice)paramDevice).getFirmwareVersion();
    }
    if ((this.currentFirmwareVersionName == null) || (this.currentFirmwareVersionName.equals(""))) {
      return;
    }
    try
    {
      paramDevice = this.currentFirmwareVersionName.split("\\.");
      this.currentFirmwareVersion = Integer.parseInt(paramDevice[(paramDevice.length - 1)]);
      this.currentFirmwareProtocol = Integer.parseInt(paramDevice[(paramDevice.length - 2)]);
      this.currentFirmwareDeviceNumber = paramDevice[(paramDevice.length - 3)];
      return;
    }
    catch (Exception paramDevice) {}
  }
  
  private void setCorrectDeviceLocation(Device paramDevice)
  {
    if ((paramDevice instanceof MBot))
    {
      this.currentFirmwareName = "mcore";
      return;
    }
    if ((paramDevice instanceof Starter))
    {
      this.currentFirmwareName = "starter";
      return;
    }
    if ((paramDevice instanceof Ranger))
    {
      this.currentFirmwareName = "auriga";
      return;
    }
    if ((paramDevice instanceof Ultimate2))
    {
      this.currentFirmwareName = "megapi";
      return;
    }
    if ((paramDevice instanceof AirBlock))
    {
      this.currentFirmwareName = "crystal";
      return;
    }
    if ((paramDevice instanceof SmartServo))
    {
      this.currentFirmwareName = "octopus";
      return;
    }
    this.currentFirmwareName = "unknown";
  }
  
  private void setCurrentPageState(int paramInt)
  {
    this.currentPageState = paramInt;
  }
  
  public void checkUpdates()
  {
    setCurrentPageState(0);
    notifyChange();
    NetManager.getInstance().getFirmwareVersion(new NetResponseListener()
    {
      public void onResponse(final FirmwareVersionData paramAnonymousFirmwareVersionData)
      {
        FirmwareInfoActivityViewModel.this.mHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            if (FirmwareInfoActivityViewModel.this.currentFirmwareName.equalsIgnoreCase("crystal"))
            {
              FirmwareInfoActivityViewModel.access$102(FirmwareInfoActivityViewModel.this, 22);
              FirmwareInfoActivityViewModel.access$202(FirmwareInfoActivityViewModel.this, 2);
              FirmwareInfoActivityViewModel.access$302(FirmwareInfoActivityViewModel.this, 19);
              if ((FirmwareInfoActivityViewModel.this.newFirmwareProtocol > FirmwareInfoActivityViewModel.this.currentFirmwareProtocol) || (FirmwareInfoActivityViewModel.this.newFirmwareVersion > FirmwareInfoActivityViewModel.this.currentFirmwareVersion))
              {
                FirmwareInfoActivityViewModel.this.setCurrentPageState(2);
                FirmwareInfoActivityViewModel.this.notifyChange();
              }
            }
            Object localObject;
            do
            {
              return;
              FirmwareInfoActivityViewModel.this.setCurrentPageState(1);
              break;
              if (paramAnonymousFirmwareVersionData == null) {
                break label396;
              }
              if ("0a".equalsIgnoreCase(FirmwareInfoActivityViewModel.this.currentFirmwareDeviceNumber)) {
                FirmwareInfoActivityViewModel.access$002(FirmwareInfoActivityViewModel.this, "orion");
              }
              for (;;)
              {
                localObject = null;
                List localList = paramAnonymousFirmwareVersionData.getData();
                int i = 0;
                while (i < localList.size())
                {
                  FirmwareVersionData.DataBean localDataBean = (FirmwareVersionData.DataBean)localList.get(i);
                  if (localDataBean.getName().equalsIgnoreCase(FirmwareInfoActivityViewModel.this.currentFirmwareName)) {
                    localObject = localDataBean;
                  }
                  i += 1;
                }
                if ("01".equalsIgnoreCase(FirmwareInfoActivityViewModel.this.currentFirmwareDeviceNumber)) {
                  FirmwareInfoActivityViewModel.access$002(FirmwareInfoActivityViewModel.this, "starter");
                }
              }
              if (localObject != null)
              {
                FirmwareInfoActivityViewModel.access$302(FirmwareInfoActivityViewModel.this, ((FirmwareVersionData.DataBean)localObject).getVersion());
                FirmwareInfoActivityViewModel.access$102(FirmwareInfoActivityViewModel.this, ((FirmwareVersionData.DataBean)localObject).getDeviceNumber());
                FirmwareInfoActivityViewModel.access$202(FirmwareInfoActivityViewModel.this, ((FirmwareVersionData.DataBean)localObject).getProtocol());
              }
            } while (FirmwareInfoActivityViewModel.this.newFirmwareVersion == 0);
            if (FirmwareInfoActivityViewModel.this.newFirmwareVersion > FirmwareInfoActivityViewModel.this.currentFirmwareVersion) {
              FirmwareInfoActivityViewModel.this.setCurrentPageState(2);
            }
            for (;;)
            {
              FirmwareInfoActivityViewModel.this.notifyChange();
              return;
              FirmwareInfoActivityViewModel.this.setCurrentPageState(1);
              continue;
              label396:
              localObject = new MakeBlockToast(FirmwareInfoActivityViewModel.this.context);
              ((MakeBlockToast)localObject).setText(FirmwareInfoActivityViewModel.this.context.getString(2131493004));
              ((MakeBlockToast)localObject).show();
              FirmwareInfoActivityViewModel.this.setCurrentPageState(3);
            }
          }
        }, 3000L);
      }
    });
  }
  
  @Bindable
  public boolean getCheckUpdatesButtonVisibility()
  {
    return this.currentPageState == 3;
  }
  
  @Bindable
  public String getConnectedBoardName()
  {
    if ((this.device instanceof MBot)) {
      return this.context.getString(2131492992);
    }
    if ((this.device instanceof AirBlock)) {
      return this.context.getString(2131492990);
    }
    if ((this.device instanceof Starter)) {
      return this.context.getString(2131492995);
    }
    if ((this.device instanceof Ranger)) {
      return this.context.getString(2131492991);
    }
    if ((this.device instanceof Ultimate2)) {
      return this.context.getString(2131492993);
    }
    if ((this.device instanceof SmartServo)) {
      return this.context.getString(2131492994);
    }
    return this.context.getString(2131493194);
  }
  
  @Bindable
  public String getConnectedFirmwareVersion()
  {
    String str = "";
    if ((this.device instanceof MakeBlockDevice)) {
      str = ((MakeBlockDevice)this.device).getFirmwareVersion();
    }
    return str;
  }
  
  @Bindable
  public boolean getLoadingFromNetTextVisibility()
  {
    return this.currentPageState == 0;
  }
  
  @Bindable
  public boolean getMascotShadowVisibility()
  {
    return this.currentPageState == 2;
  }
  
  @Bindable
  public String getNewVersionText()
  {
    String str1 = "";
    String str2 = "";
    if (this.currentFirmwareName.equalsIgnoreCase(DeviceBoardName.crystal.name()))
    {
      str1 = String.format("%02d", new Object[] { Integer.valueOf(this.newFirmwareDeviceNumber) });
      str2 = String.format("%02d", new Object[] { Integer.valueOf(this.newFirmwareProtocol) });
    }
    for (;;)
    {
      String str3 = String.format("%03d", new Object[] { Integer.valueOf(this.newFirmwareVersion) });
      str1 = str1.toLowerCase() + "." + str2 + "." + str3;
      return this.context.getResources().getString(2131493347) + str1;
      if (!TextUtils.isEmpty(this.currentFirmwareDeviceNumber))
      {
        str1 = this.currentFirmwareVersionName.split("\\.")[0];
        str2 = this.currentFirmwareVersionName.split("\\.")[1];
      }
    }
  }
  
  @Bindable
  public boolean getNewVersionTextVisibility()
  {
    return this.currentPageState == 2;
  }
  
  @Bindable
  public boolean getStartUpdateButtonVisibility()
  {
    return this.currentPageState == 2;
  }
  
  @Bindable
  public boolean getUpToDateTextVisibility()
  {
    return this.currentPageState == 1;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 1) && (paramInt2 == -1)) {
      onCreate();
    }
  }
  
  public void onConnectedDeviceChanged(Device paramDevice)
  {
    this.device = paramDevice;
    notifyPropertyChanged(10);
    notifyPropertyChanged(9);
  }
  
  public void onCreate()
  {
    setCurrentPageState(0);
    setCorrectDeviceLocation(this.device);
    initConnectedDeviceInfo(this.device);
    notifyChange();
    checkUpdates();
  }
  
  public void startUpdate()
  {
    StatisticsTool.getInstance().onEvent("UpdateRobot", "点击更新固件");
    if ((this.device instanceof AirBlock))
    {
      ActivityJumpUtil.jumpToFirmwareUpdateActivity((Activity)this.context);
      return;
    }
    ActivityJumpUtil.jumpToGroupActivity((Activity)this.context, this.context.getString(2131493228));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\firmware\FirmwareInfoActivityViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */