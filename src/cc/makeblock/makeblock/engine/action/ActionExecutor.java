package cc.makeblock.makeblock.engine.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActionExecutor
{
  public static final int ACTION_BUZZER = 2;
  public static final int ACTION_CODEY = 32;
  public static final int ACTION_DANCE = 5;
  public static final int ACTION_LED_ON_BOARD = 4;
  public static final int ACTION_MOTOR = 1;
  public static final int ACTION_SMART_SERVO = 8;
  public static final int ACTION_ULTRASONIC = 16;
  private List<Action> mActionList = new ArrayList();
  
  private void deleteAndCancelAction(Action paramAction)
  {
    paramAction.cancel();
    this.mActionList.remove(paramAction);
  }
  
  private void execute(Action paramAction)
  {
    if ((paramAction instanceof ConflictAction)) {
      cancelConflictActions(((ConflictAction)paramAction).getType());
    }
    setActionCallback(paramAction);
    paramAction.execute();
    this.mActionList.add(paramAction);
  }
  
  private boolean hasAction(Action paramAction)
  {
    return this.mActionList.contains(paramAction);
  }
  
  private boolean isActionConflict(ConflictAction paramConflictAction, int paramInt)
  {
    return (paramConflictAction.getType() & paramInt) != 0;
  }
  
  private void setActionCallback(final Action paramAction)
  {
    paramAction.addFinishListener(new Runnable()
    {
      public void run()
      {
        if ((paramAction instanceof LoopAction))
        {
          paramAction.execute();
          return;
        }
        ActionExecutor.this.mActionList.remove(paramAction);
      }
    });
  }
  
  public boolean cancelAction(Action paramAction)
  {
    boolean bool = false;
    int i = this.mActionList.indexOf(paramAction);
    if (i >= 0)
    {
      bool = true;
      ((Action)this.mActionList.remove(i)).cancel();
    }
    return bool;
  }
  
  public void cancelAllActions()
  {
    Iterator localIterator = new ArrayList(this.mActionList).iterator();
    while (localIterator.hasNext()) {
      deleteAndCancelAction((Action)localIterator.next());
    }
  }
  
  public void cancelConflictActions(int paramInt)
  {
    Iterator localIterator = new ArrayList(this.mActionList).iterator();
    while (localIterator.hasNext())
    {
      Action localAction = (Action)localIterator.next();
      if (((localAction instanceof ConflictAction)) && (isActionConflict((ConflictAction)localAction, paramInt))) {
        deleteAndCancelAction(localAction);
      }
    }
  }
  
  public boolean executeAction(Action paramAction)
  {
    return executeAction(paramAction, false);
  }
  
  public boolean executeAction(Action paramAction, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      i = this.mActionList.indexOf(paramAction);
      if (i > 0) {
        ((Action)this.mActionList.get(i)).cancel();
      }
    }
    while (!hasAction(paramAction))
    {
      int i;
      execute(paramAction);
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\action\ActionExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */