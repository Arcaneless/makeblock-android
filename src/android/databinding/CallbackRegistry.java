package android.databinding;

import java.util.ArrayList;
import java.util.List;

public class CallbackRegistry<C, T, A>
  implements Cloneable
{
  private static final String TAG = "CallbackRegistry";
  private List<C> mCallbacks = new ArrayList();
  private long mFirst64Removed = 0L;
  private int mNotificationLevel;
  private final NotifierCallback<C, T, A> mNotifier;
  private long[] mRemainderRemoved;
  
  public CallbackRegistry(NotifierCallback<C, T, A> paramNotifierCallback)
  {
    this.mNotifier = paramNotifierCallback;
  }
  
  private boolean isRemoved(int paramInt)
  {
    if (paramInt < 64) {
      if ((this.mFirst64Removed & 1L << paramInt) == 0L) {}
    }
    int i;
    do
    {
      return true;
      return false;
      if (this.mRemainderRemoved == null) {
        return false;
      }
      i = paramInt / 64 - 1;
      if (i >= this.mRemainderRemoved.length) {
        return false;
      }
    } while ((this.mRemainderRemoved[i] & 1L << paramInt % 64) != 0L);
    return false;
  }
  
  private void notifyCallbacks(T paramT, int paramInt1, A paramA, int paramInt2, int paramInt3, long paramLong)
  {
    long l = 1L;
    while (paramInt2 < paramInt3)
    {
      if ((paramLong & l) == 0L) {
        this.mNotifier.onNotifyCallback(this.mCallbacks.get(paramInt2), paramT, paramInt1, paramA);
      }
      l <<= 1;
      paramInt2 += 1;
    }
  }
  
  private void notifyFirst64(T paramT, int paramInt, A paramA)
  {
    notifyCallbacks(paramT, paramInt, paramA, 0, Math.min(64, this.mCallbacks.size()), this.mFirst64Removed);
  }
  
  private void notifyRecurse(T paramT, int paramInt, A paramA)
  {
    int j = this.mCallbacks.size();
    if (this.mRemainderRemoved == null) {}
    for (int i = -1;; i = this.mRemainderRemoved.length - 1)
    {
      notifyRemainder(paramT, paramInt, paramA, i);
      notifyCallbacks(paramT, paramInt, paramA, (i + 2) * 64, j, 0L);
      return;
    }
  }
  
  private void notifyRemainder(T paramT, int paramInt1, A paramA, int paramInt2)
  {
    if (paramInt2 < 0)
    {
      notifyFirst64(paramT, paramInt1, paramA);
      return;
    }
    long l = this.mRemainderRemoved[paramInt2];
    int i = (paramInt2 + 1) * 64;
    int j = Math.min(this.mCallbacks.size(), i + 64);
    notifyRemainder(paramT, paramInt1, paramA, paramInt2 - 1);
    notifyCallbacks(paramT, paramInt1, paramA, i, j, l);
  }
  
  private void removeRemovedCallbacks(int paramInt, long paramLong)
  {
    long l = Long.MIN_VALUE;
    int i = paramInt + 64 - 1;
    while (i >= paramInt)
    {
      if ((paramLong & l) != 0L) {
        this.mCallbacks.remove(i);
      }
      l >>>= 1;
      i -= 1;
    }
  }
  
  private void setRemovalBit(int paramInt)
  {
    if (paramInt < 64)
    {
      this.mFirst64Removed |= 1L << paramInt;
      return;
    }
    int i = paramInt / 64 - 1;
    if (this.mRemainderRemoved == null) {
      this.mRemainderRemoved = new long[this.mCallbacks.size() / 64];
    }
    for (;;)
    {
      long[] arrayOfLong = this.mRemainderRemoved;
      arrayOfLong[i] |= 1L << paramInt % 64;
      return;
      if (this.mRemainderRemoved.length <= i)
      {
        arrayOfLong = new long[this.mCallbacks.size() / 64];
        System.arraycopy(this.mRemainderRemoved, 0, arrayOfLong, 0, this.mRemainderRemoved.length);
        this.mRemainderRemoved = arrayOfLong;
      }
    }
  }
  
  public void add(C paramC)
  {
    if (paramC == null) {
      try
      {
        throw new IllegalArgumentException("callback cannot be null");
      }
      finally {}
    }
    int i = this.mCallbacks.lastIndexOf(paramC);
    if ((i < 0) || (isRemoved(i))) {
      this.mCallbacks.add(paramC);
    }
  }
  
  public void clear()
  {
    for (;;)
    {
      int i;
      try
      {
        if (this.mNotificationLevel == 0)
        {
          this.mCallbacks.clear();
          return;
        }
        if (this.mCallbacks.isEmpty()) {
          continue;
        }
        i = this.mCallbacks.size() - 1;
      }
      finally {}
      setRemovalBit(i);
      i -= 1;
      if (i < 0) {}
    }
  }
  
  public CallbackRegistry<C, T, A> clone()
  {
    Object localObject1 = null;
    for (;;)
    {
      int j;
      try
      {
        localCallbackRegistry = (CallbackRegistry)super.clone();
        localObject1 = localCallbackRegistry;
        localCallbackRegistry.mFirst64Removed = 0L;
        localObject1 = localCallbackRegistry;
        localCallbackRegistry.mRemainderRemoved = null;
        localObject1 = localCallbackRegistry;
        localCallbackRegistry.mNotificationLevel = 0;
        localObject1 = localCallbackRegistry;
        localCallbackRegistry.mCallbacks = new ArrayList();
        localObject1 = localCallbackRegistry;
        j = this.mCallbacks.size();
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        CallbackRegistry localCallbackRegistry;
        localCloneNotSupportedException.printStackTrace();
        return (CallbackRegistry<C, T, A>)localObject1;
      }
      finally {}
      localObject1 = localCallbackRegistry;
      if (!isRemoved(i))
      {
        localObject1 = localCallbackRegistry;
        localCallbackRegistry.mCallbacks.add(this.mCallbacks.get(i));
      }
      i += 1;
      break label131;
      int i = 0;
      label131:
      Object localObject3 = localCloneNotSupportedException;
      if (i >= j) {}
    }
  }
  
  public ArrayList<C> copyCallbacks()
  {
    for (;;)
    {
      ArrayList localArrayList;
      int j;
      try
      {
        localArrayList = new ArrayList(this.mCallbacks.size());
        j = this.mCallbacks.size();
      }
      finally {}
      if (!isRemoved(i)) {
        localArrayList.add(this.mCallbacks.get(i));
      }
      i += 1;
      break label73;
      return localArrayList;
      int i = 0;
      label73:
      if (i >= j) {}
    }
  }
  
  public void copyCallbacks(List<C> paramList)
  {
    for (;;)
    {
      int j;
      try
      {
        paramList.clear();
        j = this.mCallbacks.size();
      }
      finally {}
      if (!isRemoved(i)) {
        paramList.add(this.mCallbacks.get(i));
      }
      i += 1;
      break label63;
      return;
      int i = 0;
      label63:
      if (i >= j) {}
    }
  }
  
  public boolean isEmpty()
  {
    boolean bool2 = true;
    for (;;)
    {
      int j;
      int i;
      try
      {
        bool1 = this.mCallbacks.isEmpty();
        if (bool1)
        {
          bool1 = bool2;
          return bool1;
        }
        if (this.mNotificationLevel == 0)
        {
          bool1 = false;
          break label80;
        }
        j = this.mCallbacks.size();
      }
      finally {}
      boolean bool1 = isRemoved(i);
      if (!bool1)
      {
        bool1 = false;
      }
      else
      {
        i += 1;
        break label85;
        label80:
        continue;
        i = 0;
        label85:
        bool1 = bool2;
        if (i >= j) {}
      }
    }
  }
  
  public void notifyCallbacks(T paramT, int paramInt, A paramA)
  {
    label65:
    label118:
    label125:
    label135:
    label140:
    for (;;)
    {
      long l;
      try
      {
        this.mNotificationLevel += 1;
        notifyRecurse(paramT, paramInt, paramA);
        this.mNotificationLevel -= 1;
        if (this.mNotificationLevel == 0)
        {
          if (this.mRemainderRemoved != null)
          {
            paramInt = this.mRemainderRemoved.length - 1;
            break label118;
            l = this.mRemainderRemoved[paramInt];
            break label125;
            removeRemovedCallbacks((paramInt + 1) * 64, l);
            this.mRemainderRemoved[paramInt] = 0L;
            break label135;
          }
          if (this.mFirst64Removed != 0L)
          {
            removeRemovedCallbacks(0, this.mFirst64Removed);
            this.mFirst64Removed = 0L;
          }
        }
        return;
      }
      finally {}
      for (;;)
      {
        if (paramInt < 0) {
          break label140;
        }
        break;
        if (l != 0L) {
          break label65;
        }
        paramInt -= 1;
      }
    }
  }
  
  public void remove(C paramC)
  {
    for (;;)
    {
      int i;
      try
      {
        if (this.mNotificationLevel == 0)
        {
          this.mCallbacks.remove(paramC);
          return;
        }
        i = this.mCallbacks.lastIndexOf(paramC);
      }
      finally {}
      setRemovalBit(i);
      continue;
      if (i < 0) {}
    }
  }
  
  public static abstract class NotifierCallback<C, T, A>
  {
    public abstract void onNotifyCallback(C paramC, T paramT, int paramInt, A paramA);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\CallbackRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */