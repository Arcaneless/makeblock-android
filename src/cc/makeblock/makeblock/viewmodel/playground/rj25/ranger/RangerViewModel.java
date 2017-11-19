package cc.makeblock.makeblock.viewmodel.playground.rj25.ranger;

import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.action.ActionType;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.viewmodel.playground.ActionExecutorViewModel;

class RangerViewModel
  extends ActionExecutorViewModel<Ranger>
{
  static final String cpDeviceName = "mBot Ranger";
  
  RangerViewModel(Ranger paramRanger)
  {
    super(paramRanger);
    try
    {
      ((Ranger)ControllerManager.getInstance().getCurrentDevice()).setMode(0);
      return;
    }
    catch (Exception paramRanger) {}
  }
  
  protected ActionBuilder createMoveActionBuilder()
  {
    return new ActionBuilder().setActionType(ActionType.CONFLICT_MOTOR);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\ranger\RangerViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */