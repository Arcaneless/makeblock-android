package cc.makeblock.makeblock.engine.action;

import java.util.ArrayList;

public class ActionBuilder
{
  private Runnable cancelRunnable;
  private ArrayList<Long> intervals = new ArrayList();
  private String name;
  private ArrayList<Runnable> runnables = new ArrayList();
  private ActionType type = ActionType.UNDEFINED;
  
  public ActionBuilder append(Runnable paramRunnable, long paramLong)
  {
    this.runnables.add(paramRunnable);
    this.intervals.add(Long.valueOf(paramLong));
    return this;
  }
  
  public Action build()
  {
    switch (this.type)
    {
    default: 
      throw new RuntimeException("并没有这个选项");
    case ???: 
      return new ConflictAction(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable, 2);
    case ???: 
      return new ConflictAction(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable, 1);
    case ???: 
      return new ConflictAction(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable, 4);
    case ???: 
      return new LoopAction(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable);
    case ???: 
      return new Action(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable);
    }
    return new ConflictAction(ActionHandlerHolder.getHandler(), this.runnables, this.intervals, this.name, this.cancelRunnable, 32);
  }
  
  public ActionBuilder setActionType(ActionType paramActionType)
  {
    this.type = paramActionType;
    return this;
  }
  
  public ActionBuilder setCancelRunnable(Runnable paramRunnable)
  {
    this.cancelRunnable = paramRunnable;
    return this;
  }
  
  public ActionBuilder setName(String paramString)
  {
    this.name = paramString;
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\action\ActionBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */