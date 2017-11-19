package cc.makeblock.makeblock.viewmodel.playground.rj25.ranger;

import android.databinding.Bindable;
import android.graphics.PointF;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.statistics.StatisticsTool;
import cc.makeblock.makeblock.engine.utils.DrawPathActionGenerator;
import cc.makeblock.makeblock.engine.utils.DrawPathActionGenerator.OnRunnableRunListener;
import java.util.ArrayList;
import java.util.List;

public class RangerDrawPathViewModel
  extends RangerViewModel
  implements DrawPathActionGenerator.OnRunnableRunListener
{
  private static final int STATE_EMPTY = 3;
  private static final int STATE_PAUSING = 1;
  private static final int STATE_PLAYING = 0;
  private static final int STATE_READY = 2;
  private Action action;
  private DrawPathActionGenerator actionGenerator = new DrawPathActionGenerator();
  private List<PointF> chosenPointsList;
  private boolean currentActionIsForward = false;
  private int currentState = 3;
  private int passedIndex = -1;
  
  public RangerDrawPathViewModel(Ranger paramRanger)
  {
    super(paramRanger);
    this.actionGenerator.setOnRunnableRunListener(this);
  }
  
  private void clear()
  {
    this.action = null;
    this.currentActionIsForward = false;
    setCurrentState(3);
  }
  
  private void setCurrentState(int paramInt)
  {
    this.currentState = paramInt;
    notifyPropertyChanged(51);
  }
  
  public void cancelAllActions()
  {
    if (this.action != null) {
      this.action.cancel();
    }
    reset();
  }
  
  @Bindable
  public int getPassedIndex()
  {
    return this.passedIndex;
  }
  
  @Bindable
  public boolean isPause()
  {
    return this.currentState != 0;
  }
  
  public void onDrawPathFinish(List<PointF> paramList)
  {
    this.passedIndex = -1;
    this.chosenPointsList = new ArrayList(paramList);
    if (paramList.size() > 0)
    {
      this.action = this.actionGenerator.generateOrderedActions(paramList, this.device);
      setCurrentState(2);
      this.passedIndex = 0;
    }
  }
  
  public void onRunnableRun()
  {
    if (!this.currentActionIsForward) {
      this.currentActionIsForward = true;
    }
    for (;;)
    {
      if (this.passedIndex == this.chosenPointsList.size() - 1) {
        clear();
      }
      return;
      this.currentActionIsForward = false;
      this.passedIndex += 1;
      notifyPropertyChanged(50);
    }
  }
  
  public void pause()
  {
    if ((this.action != null) && (this.currentState == 0))
    {
      this.action.cancel();
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(0, this.chosenPointsList.subList(this.passedIndex, this.chosenPointsList.size()));
      this.action = this.actionGenerator.generateOrderedActions(localArrayList, this.device);
      this.passedIndex += 1;
      this.currentActionIsForward = false;
      setCurrentState(1);
    }
  }
  
  public void play()
  {
    if (!((Ranger)this.device).isConnected()) {
      return;
    }
    switch (this.currentState)
    {
    }
    for (;;)
    {
      setCurrentState(0);
      return;
      this.action.execute();
      continue;
      if (this.action == null)
      {
        setCurrentState(3);
        return;
      }
      StatisticsTool.getInstance().onEvent("mBot RangerPlayDrawPath");
      this.passedIndex = 1;
      notifyPropertyChanged(50);
      this.action.execute();
    }
  }
  
  public void reset()
  {
    clear();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\ranger\RangerDrawPathViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */