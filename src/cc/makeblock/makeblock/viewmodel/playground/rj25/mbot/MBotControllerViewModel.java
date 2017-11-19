package cc.makeblock.makeblock.viewmodel.playground.rj25.mbot;

import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;

public class MBotControllerViewModel
  extends MBotViewModel
{
  private boolean ledIsOn = false;
  private float verticalJoystickPercent;
  
  public MBotControllerViewModel(MBot paramMBot)
  {
    super(paramMBot);
    resetMBot();
  }
  
  private Runnable createJoystickRunnable(final int paramInt, final float paramFloat)
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotControllerViewModel.this.device).moveJoystick(paramInt, paramFloat);
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
        ((MBot)MBotControllerViewModel.this.device).stop();
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
    StatisticsTool.getInstance().onEvent("mBotBeep", "mBot点击蜂鸣器");
    ((MBot)this.device).buzz();
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
    StatisticsTool.getInstance().onEvent("mBotLight", "mBot点击亮灯");
    if (this.ledIsOn)
    {
      this.ledIsOn = false;
      ((MBot)this.device).turnOffLight();
      return;
    }
    this.ledIsOn = true;
    ((MBot)this.device).turnOnLight();
  }
  
  public void moveJoystick(final int paramInt, final float paramFloat)
  {
    this.verticalJoystickPercent = paramFloat;
    notifyPropertyChanged(78);
    executeAction(createMoveActionBuilder().append(new Runnable()
    {
      public void run()
      {
        ((MBot)MBotControllerViewModel.this.device).moveJoystick(paramInt, paramFloat);
      }
    }, 0L).build());
  }
  
  public void resetMBot()
  {
    ((MBot)this.device).stop();
    ((MBot)this.device).turnOffLight();
    this.ledIsOn = false;
    cancelAllActions();
    notifyPropertyChanged(61);
  }
  
  public void shake()
  {
    StatisticsTool.getInstance().onEvent("mBotShakingSkill", "mBot点击摆动");
    executeAction(createMoveActionBuilder().append(createTurnLeftRunnable(), 0L).append(createTurnRightRunnable(), 500L).append(createTurnLeftRunnable(), 1000L).append(createStopRunnable(), 500L).build());
  }
  
  public void spin()
  {
    StatisticsTool.getInstance().onEvent("mBotSpinningSkill", "mBot点击旋转");
    executeAction(createMoveActionBuilder().append(createTurnLeftRunnable(), 0L).append(createStopRunnable(), 3000L).build());
  }
  
  public void sprint()
  {
    StatisticsTool.getInstance().onEvent("mBotRunningSkill", "mBot点击冲刺");
    executeAction(createMoveActionBuilder().append(createSpriteRunnable(), 0L).append(createStopRunnable(), 3000L).build());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\mbot\MBotControllerViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */