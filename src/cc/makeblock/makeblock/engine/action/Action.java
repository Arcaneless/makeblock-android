package cc.makeblock.makeblock.engine.action;

import android.os.Handler;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class Action
{
  private Runnable cancelRunnable;
  private Handler handler;
  private ArrayList<Long> intervals;
  private String name;
  private ArrayList<Runnable> runnables;
  
  public Action(Handler paramHandler, ArrayList<Runnable> paramArrayList, ArrayList<Long> paramArrayList1, String paramString, Runnable paramRunnable)
  {
    this.handler = paramHandler;
    this.runnables = paramArrayList;
    this.intervals = paramArrayList1;
    this.name = paramString;
    if ((paramArrayList == null) || (paramArrayList1 == null) || (paramArrayList.size() != paramArrayList1.size())) {
      throw new RuntimeException("检查塞进来的数据");
    }
    this.cancelRunnable = paramRunnable;
  }
  
  public void addFinishListener(Runnable paramRunnable)
  {
    this.runnables.add(paramRunnable);
    this.intervals.add(Long.valueOf(20L));
  }
  
  public void cancel()
  {
    Iterator localIterator = this.runnables.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      this.handler.removeCallbacks(localRunnable);
    }
    if (this.cancelRunnable != null) {
      this.handler.post(this.cancelRunnable);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (TextUtils.isEmpty(this.name));
      bool1 = bool2;
    } while (!(paramObject instanceof Action));
    return this.name.equals(((Action)paramObject).name);
  }
  
  public void execute()
  {
    long l = 0L;
    int i = 0;
    while (i < this.runnables.size())
    {
      l += ((Long)this.intervals.get(i)).longValue();
      this.handler.postDelayed((Runnable)this.runnables.get(i), l);
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\action\Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */