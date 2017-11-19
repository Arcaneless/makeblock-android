package cc.makeblock.makeblock.viewmodel.playground;

import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionExecutor;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public abstract class ActionExecutorViewModel<T extends Device>
  extends DeviceViewModel<T>
{
  private ActionExecutor actionExecutor = new ActionExecutor();
  
  public ActionExecutorViewModel(T paramT)
  {
    super(paramT);
  }
  
  protected void cancelAction(int paramInt)
  {
    this.actionExecutor.cancelConflictActions(paramInt);
  }
  
  protected void cancelAction(Action paramAction)
  {
    this.actionExecutor.cancelAction(paramAction);
  }
  
  public void cancelAllActions()
  {
    this.actionExecutor.cancelAllActions();
  }
  
  protected boolean executeAction(Action paramAction)
  {
    return this.actionExecutor.executeAction(paramAction);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\ActionExecutorViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */