package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
  private static int mNextId = 1;
  private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks = new SparseArray();
  
  public static boolean completeWakefulIntent(Intent arg0)
  {
    int i = ???.getIntExtra("android.support.content.wakelockid", 0);
    if (i == 0) {
      return false;
    }
    for (;;)
    {
      synchronized (sActiveWakeLocks)
      {
        PowerManager.WakeLock localWakeLock = (PowerManager.WakeLock)sActiveWakeLocks.get(i);
        break label83;
        localWakeLock.release();
        sActiveWakeLocks.remove(i);
        return true;
        Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + i);
        return true;
      }
      label83:
      if (localObject == null) {}
    }
  }
  
  public static ComponentName startWakefulService(Context paramContext, Intent paramIntent)
  {
    for (;;)
    {
      synchronized (sActiveWakeLocks)
      {
        int i = mNextId;
        mNextId += 1;
        if (mNextId <= 0) {
          mNextId = 1;
        }
        paramIntent.putExtra("android.support.content.wakelockid", i);
        paramIntent = paramContext.startService(paramIntent);
        break label114;
        return null;
        paramContext = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "wake:" + paramIntent.flattenToShortString());
        paramContext.setReferenceCounted(false);
        paramContext.acquire(60000L);
        sActiveWakeLocks.put(i, paramContext);
        return paramIntent;
      }
      label114:
      if (paramIntent != null) {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\content\WakefulBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */