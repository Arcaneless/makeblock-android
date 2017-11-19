package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();
  
  private LocalBroadcastManager(Context paramContext)
  {
    this.mAppContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          super.handleMessage(paramAnonymousMessage);
          return;
        }
        LocalBroadcastManager.this.executePendingBroadcasts();
      }
    };
  }
  
  private void executePendingBroadcasts()
  {
    for (;;)
    {
      int i;
      synchronized (this.mReceivers)
      {
        i = this.mPendingBroadcasts.size();
        break label142;
        return;
        BroadcastRecord[] arrayOfBroadcastRecord = new BroadcastRecord[i];
        this.mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        this.mPendingBroadcasts.clear();
        i = 0;
        if (i >= arrayOfBroadcastRecord.length) {
          continue;
        }
        ??? = arrayOfBroadcastRecord[i];
        int k = ???.receivers.size();
        int j = 0;
        if (j < k)
        {
          ReceiverRecord localReceiverRecord = (ReceiverRecord)???.receivers.get(j);
          if (!localReceiverRecord.dead) {
            localReceiverRecord.receiver.onReceive(this.mAppContext, ???.intent);
          }
          j += 1;
        }
      }
      i += 1;
      continue;
      label142:
      if (i > 0) {}
    }
  }
  
  public static LocalBroadcastManager getInstance(Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null) {
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      }
      paramContext = mInstance;
      return paramContext;
    }
  }
  
  public void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    for (;;)
    {
      Object localObject2;
      int i;
      synchronized (this.mReceivers)
      {
        ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
        localObject2 = (ArrayList)this.mReceivers.get(paramBroadcastReceiver);
        break label140;
        localObject1 = new ArrayList(1);
        this.mReceivers.put(paramBroadcastReceiver, localObject1);
        ((ArrayList)localObject1).add(localReceiverRecord);
        break label152;
        if (i < paramIntentFilter.countActions())
        {
          localObject2 = paramIntentFilter.getAction(i);
          localObject1 = (ArrayList)this.mActions.get(localObject2);
          break label157;
          paramBroadcastReceiver = new ArrayList(1);
          this.mActions.put(localObject2, paramBroadcastReceiver);
          paramBroadcastReceiver.add(localReceiverRecord);
          break label168;
        }
        return;
      }
      label140:
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        continue;
        label152:
        i = 0;
        continue;
        label157:
        paramBroadcastReceiver = (BroadcastReceiver)localObject1;
        if (localObject1 == null)
        {
          continue;
          label168:
          i += 1;
        }
      }
    }
  }
  
  public boolean sendBroadcast(Intent paramIntent)
  {
    int i;
    ArrayList localArrayList;
    label126:
    label159:
    int j;
    label223:
    Object localObject2;
    int k;
    synchronized (this.mReceivers)
    {
      String str1 = paramIntent.getAction();
      String str2 = paramIntent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
      Uri localUri = paramIntent.getData();
      String str3 = paramIntent.getScheme();
      Set localSet = paramIntent.getCategories();
      if ((paramIntent.getFlags() & 0x8) == 0) {
        break label520;
      }
      i = 1;
      if (i != 0) {
        Log.v("LocalBroadcastManager", "Resolving type " + str2 + " scheme " + str3 + " of intent " + paramIntent);
      }
      localArrayList = (ArrayList)this.mActions.get(paramIntent.getAction());
      break label482;
      if (i == 0) {
        break label490;
      }
      Log.v("LocalBroadcastManager", "Action list: " + localArrayList);
      break label490;
      if (j >= localArrayList.size()) {
        break label573;
      }
      ReceiverRecord localReceiverRecord = (ReceiverRecord)localArrayList.get(j);
      if (i != 0) {
        Log.v("LocalBroadcastManager", "Matching against filter " + localReceiverRecord.filter);
      }
      if (localReceiverRecord.broadcasting)
      {
        break label498;
        Log.v("LocalBroadcastManager", "  Filter's target already added");
        localObject1 = localObject2;
        break label509;
      }
      k = localReceiverRecord.filter.match(str1, str2, str3, localUri, localSet, "LocalBroadcastManager");
      break label525;
      label263:
      if (i == 0) {
        break label533;
      }
      Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(k));
      break label533;
      label299:
      localObject1 = new ArrayList();
      label308:
      ((ArrayList)localObject1).add(localReceiverRecord);
      localReceiverRecord.broadcasting = true;
    }
    label331:
    Object localObject1 = localObject2;
    if (i != 0) {
      switch (k)
      {
      default: 
        localObject1 = "unknown reason";
        label376:
        Log.v("LocalBroadcastManager", "  Filter did not match: " + (String)localObject1);
        localObject1 = localObject2;
        break;
      }
    }
    for (;;)
    {
      if (i < ((ArrayList)localObject2).size())
      {
        ((ReceiverRecord)((ArrayList)localObject2).get(i)).broadcasting = false;
      }
      else
      {
        this.mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList)localObject2));
        if (!this.mHandler.hasMessages(1)) {
          this.mHandler.sendEmptyMessage(1);
        }
        return true;
        label482:
        label490:
        label498:
        label509:
        label520:
        label525:
        label533:
        label573:
        do
        {
          do
          {
            return false;
          } while (localArrayList == null);
          break label126;
          localObject2 = null;
          j = 0;
          break label159;
          localObject1 = localObject2;
          if (i != 0) {
            break label223;
          }
          j += 1;
          localObject2 = localObject1;
          break label159;
          i = 0;
          break;
          if (k < 0) {
            break label331;
          }
          break label263;
          localObject1 = localObject2;
          if (localObject2 != null) {
            break label308;
          }
          break label299;
          localObject1 = "action";
          break label376;
          localObject1 = "category";
          break label376;
          localObject1 = "data";
          break label376;
          localObject1 = "type";
          break label376;
        } while (localObject2 == null);
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  public void sendBroadcastSync(Intent paramIntent)
  {
    if (sendBroadcast(paramIntent)) {
      executePendingBroadcasts();
    }
  }
  
  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    int i;
    label60:
    int j;
    ArrayList localArrayList2;
    label100:
    int k;
    for (;;)
    {
      ArrayList localArrayList1;
      synchronized (this.mReceivers)
      {
        localArrayList1 = (ArrayList)this.mReceivers.remove(paramBroadcastReceiver);
        break label181;
        return;
        i = localArrayList1.size() - 1;
        break;
        ReceiverRecord localReceiverRecord1 = (ReceiverRecord)localArrayList1.get(i);
        localReceiverRecord1.dead = true;
        break label196;
        if (j >= localReceiverRecord1.filter.countActions()) {
          break label233;
        }
        String str = localReceiverRecord1.filter.getAction(j);
        localArrayList2 = (ArrayList)this.mActions.get(str);
        break label201;
        k = localArrayList2.size() - 1;
        break label209;
        ReceiverRecord localReceiverRecord2 = (ReceiverRecord)localArrayList2.get(k);
        if (localReceiverRecord2.receiver != paramBroadcastReceiver) {
          break label217;
        }
        localReceiverRecord2.dead = true;
        localArrayList2.remove(k);
        break label217;
        label150:
        if (localArrayList2.size() > 0) {
          break label226;
        }
        this.mActions.remove(str);
        break label226;
        label171:
        return;
      }
      label181:
      if (localArrayList1 != null) {}
    }
    for (;;)
    {
      if (i < 0) {
        break label171;
      }
      break;
      label196:
      j = 0;
      break label60;
      label201:
      if (localArrayList2 != null)
      {
        break label100;
        for (;;)
        {
          label209:
          if (k < 0) {
            break label150;
          }
          break;
          label217:
          k -= 1;
        }
      }
      label226:
      j += 1;
      break label60;
      label233:
      i -= 1;
    }
  }
  
  private static final class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;
    
    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      this.intent = paramIntent;
      this.receivers = paramArrayList;
    }
  }
  
  private static final class ReceiverRecord
  {
    boolean broadcasting;
    boolean dead;
    final IntentFilter filter;
    final BroadcastReceiver receiver;
    
    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      this.filter = paramIntentFilter;
      this.receiver = paramBroadcastReceiver;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(this.receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(this.filter);
      if (this.dead) {
        localStringBuilder.append(" DEAD");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\content\LocalBroadcastManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */