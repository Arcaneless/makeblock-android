package cc.makeblock.makeblock.engine.action;

import android.os.Handler;
import java.util.ArrayList;

public class ConflictAction
  extends Action
{
  private int type;
  
  public ConflictAction(Handler paramHandler, ArrayList<Runnable> paramArrayList, ArrayList<Long> paramArrayList1, String paramString, Runnable paramRunnable, int paramInt)
  {
    super(paramHandler, paramArrayList, paramArrayList1, paramString, paramRunnable);
    this.type = paramInt;
  }
  
  public int getType()
  {
    return this.type;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\action\ConflictAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */