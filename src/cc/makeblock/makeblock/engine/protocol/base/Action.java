package cc.makeblock.makeblock.engine.protocol.base;

import android.os.Handler;
import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Action
{
  public static final long DURATION_IMMEDIATELY = 0L;
  public static final long DURATION_INFINITE = -1L;
  public static final long DURATION_UNKNOWN = -2L;
  private CopyOnWriteArrayList<ActionLifeListener> actionLifeListeners;
  private long duration;
  protected Handler handler;
  private int index;
  
  public Action(int paramInt, long paramLong)
  {
    this.index = paramInt;
    this.duration = paramLong;
    this.handler = new ActionHandler(null);
  }
  
  private void cancelNotify()
  {
    if (this.actionLifeListeners != null)
    {
      Iterator localIterator = this.actionLifeListeners.iterator();
      while (localIterator.hasNext()) {
        ((ActionLifeListener)localIterator.next()).onActionCancel(this);
      }
    }
  }
  
  private void executeNotify()
  {
    if (this.actionLifeListeners != null)
    {
      Iterator localIterator = this.actionLifeListeners.iterator();
      while (localIterator.hasNext()) {
        ((ActionLifeListener)localIterator.next()).onActionExecute(this);
      }
    }
  }
  
  private void finishNotify()
  {
    if (this.actionLifeListeners != null)
    {
      Iterator localIterator = this.actionLifeListeners.iterator();
      while (localIterator.hasNext()) {
        ((ActionLifeListener)localIterator.next()).onActionFinish(this);
      }
    }
  }
  
  public void addActionLifeListener(ActionLifeListener paramActionLifeListener)
  {
    if (this.actionLifeListeners == null) {
      this.actionLifeListeners = new CopyOnWriteArrayList();
    }
    this.actionLifeListeners.add(paramActionLifeListener);
  }
  
  public void cancel()
  {
    this.handler.removeMessages(1);
    cancelNotify();
  }
  
  public void execute()
  {
    this.handler.sendEmptyMessageDelayed(1, getExecuteDuration());
    executeNotify();
  }
  
  public void executeFinish()
  {
    finishNotify();
  }
  
  public long getDuration()
  {
    return this.duration;
  }
  
  public long getExecuteDuration()
  {
    return this.duration;
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public void onReceiveData() {}
  
  public boolean pause()
  {
    throw new RuntimeException("你不说,我怎么知道怎么做~~");
  }
  
  public void resume()
  {
    throw new RuntimeException("你不说,我怎么知道怎么做~~");
  }
  
  private class ActionHandler
    extends Handler
  {
    private static final int FINISH = 1;
    
    private ActionHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default: 
        return;
      }
      Action.this.executeFinish();
    }
  }
  
  public static abstract interface ActionLifeListener
  {
    public abstract void onActionCancel(Action paramAction);
    
    public abstract void onActionExecute(Action paramAction);
    
    public abstract void onActionFinish(Action paramAction);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\base\Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */