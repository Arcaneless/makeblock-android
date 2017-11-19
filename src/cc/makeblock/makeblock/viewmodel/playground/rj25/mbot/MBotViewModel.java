package cc.makeblock.makeblock.viewmodel.playground.rj25.mbot;

import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.action.ActionType;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.playground.ActionExecutorViewModel;

public abstract class MBotViewModel
  extends ActionExecutorViewModel<MBot>
{
  protected static final String cpDeviceName = "mBot";
  
  public MBotViewModel(MBot paramMBot)
  {
    super(paramMBot);
    try
    {
      ((Ranger)ControllerManager.getInstance().getCurrentDevice()).setMode(0);
      return;
    }
    catch (Exception paramMBot) {}
  }
  
  protected ActionBuilder createMoveActionBuilder()
  {
    return new ActionBuilder().setActionType(ActionType.CONFLICT_MOTOR);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\mbot\MBotViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */