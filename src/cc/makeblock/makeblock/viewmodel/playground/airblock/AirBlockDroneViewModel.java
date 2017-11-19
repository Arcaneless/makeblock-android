package cc.makeblock.makeblock.viewmodel.playground.airblock;

import android.app.Activity;
import android.databinding.Bindable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockNavigator;
import java.util.List;

public class AirBlockDroneViewModel
  extends AirBlockBaseViewModel
{
  private float horizontalJoystickPercent;
  private float verticalJoystickPercent;
  
  public AirBlockDroneViewModel(AirBlockNavigator paramAirBlockNavigator, AirBlock paramAirBlock)
  {
    super(paramAirBlockNavigator, paramAirBlock);
    notifyPropertyChanged(35);
  }
  
  private void setForm()
  {
    ((AirBlock)this.device).setFormToDrone();
  }
  
  protected void cancelAllSkills()
  {
    notifyPropertyChanged(61);
  }
  
  public void circleSkill()
  {
    StatisticsTool.getInstance().onEvent("AirBlockCircleSkill", "Airblock圆圈运动技能");
    ((AirBlock)this.device).circle();
  }
  
  @Bindable
  public float getHorizontalJoystickPercent()
  {
    return this.horizontalJoystickPercent;
  }
  
  @Bindable
  public boolean getIsLand()
  {
    return this.land;
  }
  
  public boolean getProgressBarWithShrinkAnimation()
  {
    return false;
  }
  
  @Bindable
  public boolean getShouldCancelSkills()
  {
    return true;
  }
  
  @Bindable
  public float getVerticalJoystickPercent()
  {
    return this.verticalJoystickPercent;
  }
  
  public void goBack(Activity paramActivity)
  {
    super.goBack(paramActivity);
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
        StatisticsTool.getInstance().onEvent("AirBlockDroneTutorial", "打开Airblock飞行器新手引导");
        AirBlockDroneViewModel.this.airBlockNavigator.showUserGuide();
        AirBlockDroneViewModel.this.closeMenu();
      }
    }));
    this.menuViews.add(createItemMenu(localLayoutInflater, 2131493336, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("AirBlockDroneSafetyInstruction", "打开Airblock飞行器安全声明");
        AirBlockDroneViewModel.this.airBlockNavigator.showGuideDialog();
        AirBlockDroneViewModel.this.closeMenu();
      }
    }));
    this.menuViews.add(createItemMenu(localLayoutInflater, 2131492925, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("AirBlockDroneAdjusting", "打开Airblock飞行器调平");
        AirBlockDroneViewModel.this.airBlockNavigator.showTuneDialog();
        AirBlockDroneViewModel.this.closeMenu();
      }
    }));
  }
  
  public void land()
  {
    StatisticsTool.getInstance().onEvent("AirBlockLand", "Airblock降落");
    ((AirBlock)this.device).land();
  }
  
  public void moveHorizontalJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).droneHorizontalJoystick(paramInt, paramFloat);
    this.horizontalJoystickPercent = paramFloat;
    notifyPropertyChanged(33);
  }
  
  public void moveVerticalJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).droneVerticalJoystick(paramInt, paramFloat);
    this.verticalJoystickPercent = paramFloat;
    notifyPropertyChanged(78);
  }
  
  public void rollSkill()
  {
    StatisticsTool.getInstance().onEvent("AirblockRollSkill", "Airblock翻滚技能");
    ((AirBlock)this.device).roll();
  }
  
  public void shakeSkill()
  {
    StatisticsTool.getInstance().onEvent("AirBlockShakeSkill", "Airblock摆动技能");
    ((AirBlock)this.device).shake();
  }
  
  public void takeOff()
  {
    StatisticsTool.getInstance().onEvent("AirBlockTakeoff", "Airblock起飞");
    ((AirBlock)this.device).takeOff();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\airblock\AirBlockDroneViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */