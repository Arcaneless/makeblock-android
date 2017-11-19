package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.internal.operators.NotificationLite;

public class SerializedObserver<T>
  implements Observer<T>
{
  private static final int MAX_DRAIN_ITERATION = 1024;
  private final Observer<? super T> actual;
  private boolean emitting;
  private final NotificationLite<T> nl = NotificationLite.instance();
  private FastList queue;
  private volatile boolean terminated;
  
  public SerializedObserver(Observer<? super T> paramObserver)
  {
    this.actual = paramObserver;
  }
  
  public void onCompleted()
  {
    if (this.terminated) {
      return;
    }
    try
    {
      if (this.terminated) {
        return;
      }
    }
    finally {}
    this.terminated = true;
    FastList localFastList;
    if (this.emitting) {
      localFastList = this.queue;
    }
    for (;;)
    {
      Object localObject2 = new FastList();
      this.queue = ((FastList)localObject2);
      do
      {
        ((FastList)localObject2).add(this.nl.completed());
        return;
        this.emitting = true;
        this.actual.onCompleted();
        return;
        localObject2 = localFastList;
      } while (localFastList != null);
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    Exceptions.throwIfFatal(paramThrowable);
    if (this.terminated) {
      return;
    }
    try
    {
      if (this.terminated) {
        return;
      }
    }
    finally {}
    this.terminated = true;
    FastList localFastList2;
    if (this.emitting) {
      localFastList2 = this.queue;
    }
    for (;;)
    {
      FastList localFastList1 = new FastList();
      this.queue = localFastList1;
      do
      {
        localFastList1.add(this.nl.error(paramThrowable));
        return;
        this.emitting = true;
        this.actual.onError(paramThrowable);
        return;
        localFastList1 = localFastList2;
      } while (localFastList2 != null);
    }
  }
  
  public void onNext(T paramT)
  {
    if (this.terminated) {
      return;
    }
    try
    {
      if (this.terminated) {
        return;
      }
    }
    finally {}
    FastList localFastList3;
    FastList localFastList1;
    if (this.emitting)
    {
      localFastList3 = this.queue;
      break label242;
      localFastList1 = new FastList();
      this.queue = localFastList1;
      label56:
      localFastList1.add(this.nl.next(paramT));
    }
    else
    {
      this.emitting = true;
    }
    for (;;)
    {
      int i;
      try
      {
        this.actual.onNext(paramT);
        i = 0;
        if (i >= 1024) {
          continue;
        }
        try
        {
          localFastList1 = this.queue;
        }
        finally {}
        this.emitting = false;
        return;
      }
      catch (Throwable localThrowable1)
      {
        this.terminated = true;
        Exceptions.throwOrReport(localThrowable1, this.actual, paramT);
        return;
      }
      label242:
      FastList localFastList2;
      while (localFastList2 != null)
      {
        this.queue = null;
        Object[] arrayOfObject = localThrowable1.array;
        int k = arrayOfObject.length;
        int j = 0;
        for (;;)
        {
          if (j < k)
          {
            localFastList3 = arrayOfObject[j];
            if (localFastList3 != null) {}
          }
          else
          {
            i += 1;
            break;
          }
          try
          {
            if (this.nl.accept(this.actual, localFastList3))
            {
              this.terminated = true;
              return;
            }
          }
          catch (Throwable localThrowable2)
          {
            this.terminated = true;
            Exceptions.throwIfFatal(localThrowable2);
            this.actual.onError(OnErrorThrowable.addValueAsLastCause(localThrowable2, paramT));
            return;
          }
          j += 1;
        }
        localFastList2 = localFastList3;
        if (localFastList3 != null) {
          break label56;
        }
        break;
      }
    }
  }
  
  static final class FastList
  {
    Object[] array;
    int size;
    
    public void add(Object paramObject)
    {
      int i = this.size;
      Object[] arrayOfObject2 = this.array;
      Object[] arrayOfObject1;
      if (arrayOfObject2 == null)
      {
        arrayOfObject1 = new Object[16];
        this.array = arrayOfObject1;
      }
      for (;;)
      {
        arrayOfObject1[i] = paramObject;
        this.size = (i + 1);
        return;
        arrayOfObject1 = arrayOfObject2;
        if (i == arrayOfObject2.length)
        {
          arrayOfObject1 = new Object[(i >> 2) + i];
          System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, i);
          this.array = arrayOfObject1;
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observers\SerializedObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */