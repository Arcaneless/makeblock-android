package rx.observers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;

public class TestObserver<T>
  implements Observer<T>
{
  private static Observer<Object> INERT = new Observer()
  {
    public void onCompleted() {}
    
    public void onError(Throwable paramAnonymousThrowable) {}
    
    public void onNext(Object paramAnonymousObject) {}
  };
  private final Observer<T> delegate;
  private final ArrayList<Notification<T>> onCompletedEvents = new ArrayList();
  private final ArrayList<Throwable> onErrorEvents = new ArrayList();
  private final ArrayList<T> onNextEvents = new ArrayList();
  
  public TestObserver()
  {
    this.delegate = INERT;
  }
  
  public TestObserver(Observer<T> paramObserver)
  {
    this.delegate = paramObserver;
  }
  
  public void assertReceivedOnNext(List<T> paramList)
  {
    if (this.onNextEvents.size() != paramList.size()) {
      assertionError("Number of items does not match. Provided: " + paramList.size() + "  Actual: " + this.onNextEvents.size() + ".\n" + "Provided values: " + paramList + "\n" + "Actual values: " + this.onNextEvents + "\n");
    }
    int i = 0;
    if (i < paramList.size())
    {
      Object localObject2 = paramList.get(i);
      Object localObject1 = this.onNextEvents.get(i);
      if (localObject2 == null) {
        if (localObject1 != null) {
          assertionError("Value at index: " + i + " expected to be [null] but was: [" + localObject1 + "]\n");
        }
      }
      while (localObject2.equals(localObject1))
      {
        i += 1;
        break;
      }
      localObject2 = new StringBuilder().append("Value at index: ").append(i).append(" expected to be [").append(localObject2).append("] (").append(localObject2.getClass().getSimpleName()).append(") but was: [").append(localObject1).append("] (");
      if (localObject1 != null) {}
      for (localObject1 = localObject1.getClass().getSimpleName();; localObject1 = "null")
      {
        assertionError((String)localObject1 + ")\n");
        break;
      }
    }
  }
  
  public void assertTerminalEvent()
  {
    if (this.onErrorEvents.size() > 1) {
      assertionError("Too many onError events: " + this.onErrorEvents.size());
    }
    if (this.onCompletedEvents.size() > 1) {
      assertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
    }
    if ((this.onCompletedEvents.size() == 1) && (this.onErrorEvents.size() == 1)) {
      assertionError("Received both an onError and onCompleted. Should be one or the other.");
    }
    if ((this.onCompletedEvents.size() == 0) && (this.onErrorEvents.size() == 0)) {
      assertionError("No terminal events received.");
    }
  }
  
  final void assertionError(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 32);
    localStringBuilder.append(paramString);
    localStringBuilder.append(" (");
    int i = this.onCompletedEvents.size();
    localStringBuilder.append(i);
    localStringBuilder.append(" completion");
    if (i != 1) {
      localStringBuilder.append("s");
    }
    localStringBuilder.append(")");
    if (!this.onErrorEvents.isEmpty())
    {
      i = this.onErrorEvents.size();
      localStringBuilder.append(" (+").append(i).append(" error");
      if (i != 1) {
        localStringBuilder.append("s");
      }
      localStringBuilder.append(")");
    }
    paramString = new AssertionError(localStringBuilder.toString());
    if (!this.onErrorEvents.isEmpty())
    {
      if (this.onErrorEvents.size() != 1) {
        break label172;
      }
      paramString.initCause((Throwable)this.onErrorEvents.get(0));
    }
    for (;;)
    {
      throw paramString;
      label172:
      paramString.initCause(new CompositeException(this.onErrorEvents));
    }
  }
  
  public List<Object> getEvents()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.onNextEvents);
    localArrayList.add(this.onErrorEvents);
    localArrayList.add(this.onCompletedEvents);
    return Collections.unmodifiableList(localArrayList);
  }
  
  public List<Notification<T>> getOnCompletedEvents()
  {
    return Collections.unmodifiableList(this.onCompletedEvents);
  }
  
  public List<Throwable> getOnErrorEvents()
  {
    return Collections.unmodifiableList(this.onErrorEvents);
  }
  
  public List<T> getOnNextEvents()
  {
    return Collections.unmodifiableList(this.onNextEvents);
  }
  
  public void onCompleted()
  {
    this.onCompletedEvents.add(Notification.createOnCompleted());
    this.delegate.onCompleted();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.onErrorEvents.add(paramThrowable);
    this.delegate.onError(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.onNextEvents.add(paramT);
    this.delegate.onNext(paramT);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observers\TestObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */