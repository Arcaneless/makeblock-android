package cc.makeblock.makeblock.customview.panel;

import android.graphics.PointF;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.DrawPathActionGenerator;
import cc.makeblock.makeblock.engine.utils.DrawPathActionGenerator.OnRunnableRunListener;
import java.util.ArrayList;
import java.util.List;

public class PathFinderHelper
  implements DrawPathActionGenerator.OnRunnableRunListener
{
  private static final int STATE_EMPTY = 3;
  private static final int STATE_PAUSING = 1;
  private static final int STATE_PLAYING = 0;
  private static final int STATE_READY = 2;
  private static final String TAG = PathFinderHelper.class.getSimpleName();
  private Action action;
  private DrawPathActionGenerator actionGenerator;
  private List<PointF> chosenPointsList;
  private boolean currentActionIsForward = false;
  private int currentState = 3;
  private int maxDistanceUnit;
  private OnCurrentPointDrawFinishListener onCurrentPointDrawFinishListener;
  private int passedIndex = -1;
  
  PathFinderHelper(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2) {}
    for (;;)
    {
      this.maxDistanceUnit = paramInt1;
      this.actionGenerator = new DrawPathActionGenerator();
      this.actionGenerator.setOnRunnableRunListener(this);
      return;
      paramInt1 = paramInt2;
    }
  }
  
  private void clear()
  {
    this.action.cancel();
    this.action = null;
    this.currentActionIsForward = false;
    setCurrentState(3);
  }
  
  private void setCurrentState(int paramInt)
  {
    this.currentState = paramInt;
  }
  
  public void onRunnableRun()
  {
    if (!this.currentActionIsForward) {
      this.currentActionIsForward = true;
    }
    for (;;)
    {
      if (this.passedIndex == this.chosenPointsList.size() - 1)
      {
        setCurrentState(3);
        clear();
      }
      return;
      this.currentActionIsForward = false;
      this.passedIndex += 1;
      this.onCurrentPointDrawFinishListener.onCurrentPointDrawFinish(this.passedIndex);
    }
  }
  
  void pauseDrawing()
  {
    if ((this.action != null) && (this.currentState == 0))
    {
      this.action.cancel();
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(0, this.chosenPointsList.subList(this.passedIndex, this.chosenPointsList.size()));
      this.action = this.actionGenerator.generateOrderedActions(localArrayList, ControllerManager.getInstance().getCurrentDevice());
      this.passedIndex += 1;
      this.currentActionIsForward = false;
      setCurrentState(1);
    }
  }
  
  void setOnCurrentPointDrawFinishListener(OnCurrentPointDrawFinishListener paramOnCurrentPointDrawFinishListener)
  {
    this.onCurrentPointDrawFinishListener = paramOnCurrentPointDrawFinishListener;
  }
  
  void setPointsList(List<PointF> paramList)
  {
    this.chosenPointsList = paramList;
    if (paramList.size() > 1)
    {
      this.action = this.actionGenerator.generateOrderedActions(paramList, ControllerManager.getInstance().getCurrentDevice());
      setCurrentState(2);
      this.passedIndex = 0;
    }
  }
  
  void startDrawing()
  {
    switch (this.currentState)
    {
    }
    for (;;)
    {
      setCurrentState(0);
      return;
      this.action.execute();
      continue;
      StatisticsTool.getInstance().onEvent("MBotPlayDrawPath");
      if (this.action == null)
      {
        setCurrentState(3);
        return;
      }
      this.passedIndex = 1;
      this.action.execute();
      this.onCurrentPointDrawFinishListener.onCurrentPointDrawFinish(this.passedIndex);
    }
  }
  
  void stopDrawing()
  {
    clear();
  }
  
  static abstract interface OnCurrentPointDrawFinishListener
  {
    public abstract void onCurrentPointDrawFinish(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\PathFinderHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */