package cc.makeblock.makeblock.viewmodel.playground.airblock;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cc.makeblock.makeblock.customview.util.TouchRegion;
import cc.makeblock.makeblock.databinding.LayoutMenuCommonHeaderBinding;
import cc.makeblock.makeblock.databinding.LayoutMenuTextBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.ControllerManager.OnConnectedStateChangeListener;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.device.AirBlock.AirBlockStatusAdapter;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockNavigator;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;
import cc.makeblock.makeblock.viewmodel.slidemenu.TextViewModel;
import java.util.ArrayList;
import java.util.List;

public class AirBlockBaseViewModel
  extends DeviceViewModel<AirBlock>
  implements ControllerManager.OnConnectedStateChangeListener
{
  protected Activity activity;
  protected AirBlockNavigator airBlockNavigator;
  private AirBlock.AirBlockStatusAdapter airBlockStatusAdapter = new AirBlock.AirBlockStatusAdapter()
  {
    public void onStateUpdate(float paramAnonymousFloat, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2)
    {
      AirBlockBaseViewModel.access$002(AirBlockBaseViewModel.this, paramAnonymousFloat);
      AirBlockBaseViewModel.access$102(AirBlockBaseViewModel.this, paramAnonymousBoolean1);
      AirBlockBaseViewModel.this.land = paramAnonymousBoolean2;
      AirBlockBaseViewModel.this.notifyPropertyChanged(67);
      AirBlockBaseViewModel.this.notifyPropertyChanged(2);
      AirBlockBaseViewModel.this.notifyPropertyChanged(35);
      AirBlockBaseViewModel.this.notifyPropertyChanged(36);
      if (!paramAnonymousBoolean1) {
        AirBlockBaseViewModel.this.cancelAllSkills();
      }
    }
  };
  private float battery = 0.0F;
  protected boolean isShowMenu;
  protected boolean land = true;
  private View menuHeaderView;
  protected List<View> menuViews = new ArrayList();
  private boolean powerOn = false;
  
  public AirBlockBaseViewModel(AirBlockNavigator paramAirBlockNavigator, AirBlock paramAirBlock)
  {
    super(paramAirBlock);
    if ((paramAirBlockNavigator instanceof Activity)) {
      this.activity = ((Activity)paramAirBlockNavigator);
    }
    this.airBlockNavigator = paramAirBlockNavigator;
    ControllerManager.getInstance().registerConnectingStateListener(this);
    onConnectedStateChange(ControllerManager.getInstance().isConnectedOk());
  }
  
  protected void cancelAllSkills() {}
  
  protected void closeMenu()
  {
    this.isShowMenu = false;
    notifyPropertyChanged(65);
  }
  
  protected View createItemMenu(LayoutInflater paramLayoutInflater, int paramInt, View.OnClickListener paramOnClickListener)
  {
    paramLayoutInflater = (LayoutMenuTextBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427489, null, false);
    paramLayoutInflater.setViewModel(new TextViewModel(TextUtil.getStringById(paramInt)));
    paramLayoutInflater.getRoot().setOnClickListener(paramOnClickListener);
    return paramLayoutInflater.getRoot();
  }
  
  @Bindable
  public float getBatteryPercent()
  {
    return this.battery;
  }
  
  @Bindable
  public boolean getIsPowerOn()
  {
    return this.powerOn;
  }
  
  public View getMenuHeaderView()
  {
    return this.menuHeaderView;
  }
  
  public List<View> getMenuViews()
  {
    return this.menuViews;
  }
  
  @Bindable
  public float getSignalPercent()
  {
    int i = BleManager.getInstance().getBluetoothRssi();
    if (i == -100) {
      return 0.0F;
    }
    if (i > -60) {
      return 1.0F;
    }
    if (i > -65) {
      return 0.8F;
    }
    if (i > -70) {
      return 0.6F;
    }
    if (i > -80) {
      return 0.4F;
    }
    return 0.15F;
  }
  
  public void goBack(Activity paramActivity)
  {
    paramActivity.finish();
  }
  
  protected void initDevice()
  {
    ((AirBlock)this.device).sendHeartBeatPkg();
    ((AirBlock)this.device).turnOnState();
    ((AirBlock)this.device).registerAirBlockStatusAdapter(this.airBlockStatusAdapter);
  }
  
  protected void initSlideMenuHeader()
  {
    LayoutMenuCommonHeaderBinding localLayoutMenuCommonHeaderBinding = (LayoutMenuCommonHeaderBinding)DataBindingUtil.inflate(LayoutInflater.from(this.activity), 2131427486, null, false);
    localLayoutMenuCommonHeaderBinding.setViewModel(new TextViewModel(TextUtil.getStringById(2131492935)));
    this.menuHeaderView = localLayoutMenuCommonHeaderBinding.getRoot();
    localLayoutMenuCommonHeaderBinding.close.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AirBlockBaseViewModel.this.isShowMenu = false;
        AirBlockBaseViewModel.this.notifyPropertyChanged(65);
      }
    });
    new TouchRegion(localLayoutMenuCommonHeaderBinding.close).expandViewTouchRegion(localLayoutMenuCommonHeaderBinding.close, 50);
  }
  
  @Bindable
  public boolean isShowMenu()
  {
    return this.isShowMenu;
  }
  
  public void onConnectedStateChange(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      cancelAllSkills();
      this.powerOn = false;
      this.battery = 0.0F;
      notifyPropertyChanged(36);
      notifyPropertyChanged(2);
      notifyPropertyChanged(67);
      ((AirBlock)this.device).reset();
    }
  }
  
  public void onDestroy()
  {
    ControllerManager.getInstance().unRegisterConnectingStateListener(this);
  }
  
  public void onStart()
  {
    initDevice();
  }
  
  public void onStop()
  {
    ((AirBlock)this.device).reset();
    ((AirBlock)this.device).removeAirBlockStatusAdapter(this.airBlockStatusAdapter);
  }
  
  public void openMenu()
  {
    StatisticsTool.getInstance().onEvent("AirBlockMenu", "点击Airblock菜单按钮");
    this.isShowMenu = true;
    notifyPropertyChanged(65);
  }
  
  public void setDevice(AirBlock paramAirBlock)
  {
    super.setDevice(paramAirBlock);
    initDevice();
  }
  
  public void switchPower()
  {
    StatisticsTool.getInstance().onEvent("AirBlockSwitchPower", "点击Airblock电源按钮");
    boolean bool;
    if (!this.powerOn)
    {
      bool = true;
      this.powerOn = bool;
      if (!this.powerOn) {
        break label53;
      }
      ((AirBlock)this.device).turnOnPower();
    }
    for (;;)
    {
      notifyPropertyChanged(36);
      return;
      bool = false;
      break;
      label53:
      ((AirBlock)this.device).turnOffPower();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\airblock\AirBlockBaseViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */