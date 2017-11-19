package cc.makeblock.makeblock.viewmodel.navigation;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.databinding.BaseObservable;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.net.NetManager;
import cc.makeblock.makeblock.engine.net.NetResponseListener;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData.DataBean;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.engine.utils.TextUtil;

public class NavigationSettingsViewModel
  extends BaseObservable
{
  private NavigationSettingsView mView;
  
  public NavigationSettingsViewModel(NavigationSettingsView paramNavigationSettingsView)
  {
    this.mView = paramNavigationSettingsView;
  }
  
  private void queryAppVersionData()
  {
    NetManager.getInstance().getVersionInfo(new NetResponseListener()
    {
      public void onResponse(AndroidVerInfoData paramAnonymousAndroidVerInfoData)
      {
        NavigationSettingsViewModel.this.mView.cancelUpdateToastWithDelay(1000);
        if (paramAnonymousAndroidVerInfoData != null)
        {
          int i = 0;
          try
          {
            int j = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), 0).versionCode;
            i = j;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              localNameNotFoundException.printStackTrace();
            }
            NavigationSettingsViewModel.this.mView.showToast(TextUtil.getStringById(2131493333));
            return;
          }
          if (i < paramAnonymousAndroidVerInfoData.getData().getVersionCode())
          {
            NavigationSettingsViewModel.this.mView.showNewVersionDialog(paramAnonymousAndroidVerInfoData);
            SharedPreferencesUtils.setApplicationNewVersion(paramAnonymousAndroidVerInfoData.getData().getVersionCode());
            return;
          }
        }
        NavigationSettingsViewModel.this.mView.showToast(TextUtil.getStringById(2131493004));
      }
    });
  }
  
  public boolean isAutoConnectionEnable()
  {
    return SharedPreferencesUtils.isAutoConnectionEnable();
  }
  
  public void openAbout()
  {
    StatisticsTool.getInstance().onEvent("OpenAboutApp", "打开关于应用");
    this.mView.openAbout();
  }
  
  public void openFirmware()
  {
    StatisticsTool.getInstance().onEvent("OpenFirmwareInfo", "打开固件信息");
    if (!ControllerManager.getInstance().isConnectedOk())
    {
      this.mView.showNonFirmwareDialog();
      return;
    }
    this.mView.openFirmwareInfoPage();
  }
  
  public void openUpdate()
  {
    StatisticsTool.getInstance().onEvent("OpenUpdateApp", "点击版本更新");
    this.mView.showUpdateToast(TextUtil.getStringById(2131493330));
    queryAppVersionData();
  }
  
  public void setAutoConnectionEnable(boolean paramBoolean)
  {
    SharedPreferencesUtils.setAutoConnectionEnable(paramBoolean);
  }
  
  public static abstract interface NavigationSettingsView
  {
    public abstract void cancelUpdateToastWithDelay(int paramInt);
    
    public abstract void openAbout();
    
    public abstract void openFirmwareInfoPage();
    
    public abstract void showNewVersionDialog(AndroidVerInfoData paramAndroidVerInfoData);
    
    public abstract void showNonFirmwareDialog();
    
    public abstract void showToast(String paramString);
    
    public abstract void showUpdateToast(String paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\navigation\NavigationSettingsViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */