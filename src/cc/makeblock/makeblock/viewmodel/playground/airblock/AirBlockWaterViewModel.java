package cc.makeblock.makeblock.viewmodel.playground.airblock;

import android.databinding.Bindable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockNavigator;
import java.util.List;

public class AirBlockWaterViewModel
  extends AirBlockBaseViewModel
{
  private float moveJoystickPercent;
  private float turnJoystickPercent;
  
  public AirBlockWaterViewModel(AirBlockNavigator paramAirBlockNavigator, AirBlock paramAirBlock)
  {
    super(paramAirBlockNavigator, paramAirBlock);
  }
  
  private void setForm()
  {
    ((AirBlock)this.device).setFormToShip();
  }
  
  @Bindable
  public float getMoveJoystickPercent()
  {
    return this.moveJoystickPercent;
  }
  
  public boolean getProgressBarWithShrinkAnimation()
  {
    return true;
  }
  
  @Bindable
  public float getTurnJoystickPercent()
  {
    return this.turnJoystickPercent;
  }
  
  protected void initDevice()
  {
    super.initDevice();
    setForm();
  }
  
  public void initSlideMenu()
  {
    initSlideMenuHeader();
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.activity);
    this.menuViews.add(createItemMenu(localLayoutInflater, 2131492931, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("AirBlockShipTutorial", "打开Airblock气垫船新手引导");
        AirBlockWaterViewModel.this.airBlockNavigator.showUserGuide();
        AirBlockWaterViewModel.this.closeMenu();
      }
    }));
    this.menuViews.add(createItemMenu(localLayoutInflater, 2131493336, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("AirBlockShipSafetyInstruction", "打开Airblock气垫船安全声明");
        AirBlockWaterViewModel.this.airBlockNavigator.showGuideDialog();
        AirBlockWaterViewModel.this.closeMenu();
      }
    }));
  }
  
  public void moveMoveJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).shipMoveJoystick(paramInt, paramFloat);
    this.moveJoystickPercent = paramFloat;
    notifyPropertyChanged(41);
  }
  
  public void moveTurnJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).shipTurnJoystick(paramInt, paramFloat);
    this.turnJoystickPercent = paramFloat;
    notifyPropertyChanged(73);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\airblock\AirBlockWaterViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */