package cc.makeblock.makeblock.viewmodel.playground.airblock;

import android.databinding.Bindable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.device.AirBlock;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.scene.playground.airblock.AirBlockNavigator;
import java.util.List;

public class AirBlockLandViewModel
  extends AirBlockBaseViewModel
{
  private float moveJoystickPercent;
  private float turnJoystickPercent;
  
  public AirBlockLandViewModel(AirBlockNavigator paramAirBlockNavigator, AirBlock paramAirBlock)
  {
    super(paramAirBlockNavigator, paramAirBlock);
  }
  
  private Runnable createControlRotateRunnable(final int paramInt, final float paramFloat)
  {
    new Runnable()
    {
      public void run()
      {
        ((AirBlock)AirBlockLandViewModel.this.device).controlWordRotate(paramInt, paramFloat);
      }
    };
  }
  
  private Runnable createControlWordForwardRunnable(final float paramFloat)
  {
    new Runnable()
    {
      public void run()
      {
        ((AirBlock)AirBlockLandViewModel.this.device).controlWord(0, paramFloat);
      }
    };
  }
  
  private Runnable createControlWordList(final byte paramByte, final float paramFloat1, final float paramFloat2, final float paramFloat3, final float paramFloat4, final float paramFloat5, final float paramFloat6)
  {
    new Runnable()
    {
      public void run()
      {
        ((AirBlock)AirBlockLandViewModel.this.device).controlWordList(paramByte, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
      }
    };
  }
  
  private Runnable createKeepBalanceRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((AirBlock)AirBlockLandViewModel.this.device).keepBalance();
      }
    };
  }
  
  private Runnable createSetSpeed(final short paramShort1, final short paramShort2)
  {
    new Runnable()
    {
      public void run()
      {
        ((AirBlock)AirBlockLandViewModel.this.device).setSpeed(paramShort1, paramShort2);
      }
    };
  }
  
  private void setForm()
  {
    ((AirBlock)this.device).setFormToCar();
  }
  
  public void brake() {}
  
  protected void cancelAllSkills()
  {
    notifyPropertyChanged(61);
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
  public boolean getShouldCancelSkills()
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
        StatisticsTool.getInstance().onEvent("AirBlockCarTutorial", "打开Airblock陆地教程");
        AirBlockLandViewModel.this.airBlockNavigator.showUserGuide();
        AirBlockLandViewModel.this.closeMenu();
      }
    }));
    this.menuViews.add(createItemMenu(localLayoutInflater, 2131493336, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        StatisticsTool.getInstance().onEvent("AirBlockCarSafetyInstruction", "打开Airblock陆地安全声明");
        AirBlockLandViewModel.this.airBlockNavigator.showGuideDialog();
        AirBlockLandViewModel.this.closeMenu();
      }
    }));
  }
  
  public void moveMoveJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).carMoveJoystick(paramInt, paramFloat);
    this.moveJoystickPercent = paramFloat;
    notifyPropertyChanged(41);
  }
  
  public void moveTurnJoystick(int paramInt, float paramFloat)
  {
    ((AirBlock)this.device).carTurnJoystick(paramInt, paramFloat);
    this.turnJoystickPercent = paramFloat;
    notifyPropertyChanged(73);
  }
  
  public void sMoveSkill()
  {
    StatisticsTool.getInstance().onEvent("AirBlockSMoveSkill", "Airblock S 运动技能");
    new ActionBuilder().append(createControlWordList((byte)2, -10.0F, 0.4F, 0.0F, 0.0F, 0.0F, 0.0F), 0L).append(createControlWordList((byte)0, 80.0F, 0.7F, 0.0F, 0.0F, 0.0F, 0.0F), 100L).append(createControlWordList((byte)0, -120.0F, 0.8F, 0.0F, 0.0F, 0.0F, 0.0F), 100L).append(createControlWordList((byte)0, 120.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F), 100L).build().execute();
  }
  
  public void shiftSkill()
  {
    StatisticsTool.getInstance().onEvent("AirBlockShiftSkill", "Airblock漂移技能");
    new ActionBuilder().append(createSetSpeed((short)2000, (short)1400), 0L).append(createControlWordForwardRunnable(1.5F), 0L).append(createControlRotateRunnable(150, 1.2F), 1200L).append(createKeepBalanceRunnable(), 1200L).setCancelRunnable(createKeepBalanceRunnable()).build().execute();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\airblock\AirBlockLandViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */