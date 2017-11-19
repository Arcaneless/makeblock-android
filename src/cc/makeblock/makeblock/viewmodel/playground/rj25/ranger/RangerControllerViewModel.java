package cc.makeblock.makeblock.viewmodel.playground.rj25.ranger;

import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;

public class RangerControllerViewModel
  extends RangerViewModel
{
  private boolean ledIsOn = false;
  private float verticalJoystickPercent;
  
  public RangerControllerViewModel(Ranger paramRanger)
  {
    super(paramRanger);
    resetRanger();
  }
  
  private Runnable createJoystickRunnable(final int paramInt, final float paramFloat)
  {
    new Runnable()
    {
      public void run()
      {
        ((Ranger)RangerControllerViewModel.this.device).moveJoystick(paramInt, paramFloat);
      }
    };
  }
  
  private Runnable createSpriteRunnable()
  {
    return createJoystickRunnable(90, 1.0F);
  }
  
  private Runnable createStopRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((Ranger)RangerControllerViewModel.this.device).stop();
      }
    };
  }
  
  private Runnable createTurnLeftRunnable()
  {
    return createJoystickRunnable(180, 1.0F);
  }
  
  private Runnable createTurnRightRunnable()
  {
    return createJoystickRunnable(0, 1.0F);
  }
  
  public void buzz()
  {
    StatisticsTool.getInstance().onEvent("mBot RangerBeep", "mBot Ranger点击蜂鸣器");
    ((Ranger)this.device).buzz();
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
  
  public void light()
  {
    StatisticsTool.getInstance().onEvent("mBot RangerLight", "mBot Ranger点击亮灯");
    if (this.ledIsOn)
    {
      this.ledIsOn = false;
      ((Ranger)this.device).turnOffLight();
      return;
    }
    this.ledIsOn = true;
    ((Ranger)this.device).turnOnLight();
  }
  
  public void moveJoystick(final int paramInt, final float paramFloat)
  {
    this.verticalJoystickPercent = paramFloat;
    notifyPropertyChanged(78);
    executeAction(createMoveActionBuilder().append(new Runnable()
    {
      public void run()
      {
        ((Ranger)RangerControllerViewModel.this.device).moveJoystick(paramInt, paramFloat);
      }
    }, 0L).build());
  }
  
  public void resetRanger()
  {
    ((Ranger)this.device).stop();
    ((Ranger)this.device).turnOffLight();
    this.ledIsOn = false;
    cancelAllActions();
    notifyPropertyChanged(61);
  }
  
  public void shake()
  {
    StatisticsTool.getInstance().onEvent("mBot RangerShakingSkill", "mBot Ranger点击摆动");
    executeAction(createMoveActionBuilder().append(createTurnLeftRunnable(), 0L).append(createTurnRightRunnable(), 500L).append(createTurnLeftRunnable(), 1000L).append(createStopRunnable(), 500L).build());
  }
  
  public void spin()
  {
    StatisticsTool.getInstance().onEvent("mBot RangerSpinningSkill", "mBot Ranger点击旋转");
    executeAction(createMoveActionBuilder().append(createTurnLeftRunnable(), 0L).append(createStopRunnable(), 3000L).build());
  }
  
  public void sprint()
  {
    StatisticsTool.getInstance().onEvent("mBot RangerRunningSkill", "mBot Ranger点击冲刺");
    executeAction(createMoveActionBuilder().append(createSpriteRunnable(), 0L).append(createStopRunnable(), 3000L).build());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\ranger\RangerControllerViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */