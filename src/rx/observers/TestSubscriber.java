package rx.observers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;

public class TestSubscriber<T>
  extends Subscriber<T>
{
  private static final Observer<Object> INERT = new Observer()
  {
    public void onCompleted() {}
    
    public void onError(Throwable paramAnonymousThrowable) {}
    
    public void onNext(Object paramAnonymousObject) {}
  };
  private volatile Thread lastSeenThread;
  private final CountDownLatch latch = new CountDownLatch(1);
  private final TestObserver<T> testObserver;
  
  public TestSubscriber()
  {
    this(-1L);
  }
  
  public TestSubscriber(long paramLong)
  {
    this(INERT, paramLong);
  }
  
  public TestSubscriber(Observer<T> paramObserver)
  {
    this(paramObserver, -1L);
  }
  
  public TestSubscriber(Observer<T> paramObserver, long paramLong)
  {
    if (paramObserver == null) {
      throw new NullPointerException();
    }
    this.testObserver = new TestObserver(paramObserver);
    if (paramLong >= 0L) {
      request(paramLong);
    }
  }
  
  public TestSubscriber(Subscriber<T> paramSubscriber)
  {
    this(paramSubscriber, -1L);
  }
  
  public static <T> TestSubscriber<T> create()
  {
    return new TestSubscriber();
  }
  
  public static <T> TestSubscriber<T> create(long paramLong)
  {
    return new TestSubscriber(paramLong);
  }
  
  public static <T> TestSubscriber<T> create(Observer<T> paramObserver)
  {
    return new TestSubscriber(paramObserver);
  }
  
  public static <T> TestSubscriber<T> create(Observer<T> paramObserver, long paramLong)
  {
    return new TestSubscriber(paramObserver, paramLong);
  }
  
  public static <T> TestSubscriber<T> create(Subscriber<T> paramSubscriber)
  {
    return new TestSubscriber(paramSubscriber);
  }
  
  public void assertCompleted()
  {
    int i = this.testObserver.getOnCompletedEvents().size();
    if (i == 0) {
      this.testObserver.assertionError("Not completed!");
    }
    while (i <= 1) {
      return;
    }
    this.testObserver.assertionError("Completed multiple times: " + i);
  }
  
  public void assertError(Class<? extends Throwable> paramClass)
  {
    List localList = this.testObserver.getOnErrorEvents();
    if (localList.size() == 0) {
      this.testObserver.assertionError("No errors");
    }
    do
    {
      return;
      if (localList.size() > 1)
      {
        paramClass = new AssertionError("Multiple errors: " + localList.size());
        paramClass.initCause(new CompositeException(localList));
        throw paramClass;
      }
    } while (paramClass.isInstance(localList.get(0)));
    paramClass = new AssertionError("Exceptions differ; expected: " + paramClass + ", actual: " + localList.get(0));
    paramClass.initCause((Throwable)localList.get(0));
    throw paramClass;
  }
  
  public void assertError(Throwable paramThrowable)
  {
    List localList = this.testObserver.getOnErrorEvents();
    if (localList.size() == 0) {
      this.testObserver.assertionError("No errors");
    }
    do
    {
      return;
      if (localList.size() > 1)
      {
        paramThrowable = new AssertionError("Multiple errors: " + localList.size());
        paramThrowable.initCause(new CompositeException(localList));
        throw paramThrowable;
      }
    } while (paramThrowable.equals(localList.get(0)));
    paramThrowable = new AssertionError("Exceptions differ; expected: " + paramThrowable + ", actual: " + localList.get(0));
    paramThrowable.initCause((Throwable)localList.get(0));
    throw paramThrowable;
  }
  
  public void assertNoErrors()
  {
    List localList = getOnErrorEvents();
    if (localList.size() > 0)
    {
      AssertionError localAssertionError = new AssertionError("Unexpected onError events: " + getOnErrorEvents().size());
      if (localList.size() == 1) {
        localAssertionError.initCause((Throwable)getOnErrorEvents().get(0));
      }
      for (;;)
      {
        throw localAssertionError;
        localAssertionError.initCause(new CompositeException(localList));
      }
    }
  }
  
  public void assertNoTerminalEvent()
  {
    List localList = this.testObserver.getOnErrorEvents();
    int i = this.testObserver.getOnCompletedEvents().size();
    if ((localList.size() > 0) || (i > 0))
    {
      if (localList.isEmpty()) {
        this.testObserver.assertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
      }
    }
    else {
      return;
    }
    if (localList.size() == 1)
    {
      localAssertionError = new AssertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
      localAssertionError.initCause((Throwable)localList.get(0));
      throw localAssertionError;
    }
    AssertionError localAssertionError = new AssertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
    localAssertionError.initCause(new CompositeException(localList));
    throw localAssertionError;
  }
  
  public void assertNoValues()
  {
    int i = this.testObserver.getOnNextEvents().size();
    if (i > 0) {
      this.testObserver.assertionError("No onNext events expected yet some received: " + i);
    }
  }
  
  public void assertNotCompleted()
  {
    int i = this.testObserver.getOnCompletedEvents().size();
    if (i == 1) {
      this.testObserver.assertionError("Completed!");
    }
    while (i <= 1) {
      return;
    }
    this.testObserver.assertionError("Completed multiple times: " + i);
  }
  
  public void assertReceivedOnNext(List<T> paramList)
  {
    this.testObserver.assertReceivedOnNext(paramList);
  }
  
  public void assertTerminalEvent()
  {
    this.testObserver.assertTerminalEvent();
  }
  
  public void assertUnsubscribed()
  {
    if (!isUnsubscribed()) {
      this.testObserver.assertionError("Not unsubscribed.");
    }
  }
  
  public void assertValue(T paramT)
  {
    assertReceivedOnNext(Collections.singletonList(paramT));
  }
  
  public void assertValueCount(int paramInt)
  {
    int i = this.testObserver.getOnNextEvents().size();
    if (i != paramInt) {
      this.testObserver.assertionError("Number of onNext events differ; expected: " + paramInt + ", actual: " + i);
    }
  }
  
  public void assertValues(T... paramVarArgs)
  {
    assertReceivedOnNext(Arrays.asList(paramVarArgs));
  }
  
  public void awaitTerminalEvent()
  {
    try
    {
      this.latch.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException("Interrupted", localInterruptedException);
    }
  }
  
  public void awaitTerminalEvent(long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      this.latch.await(paramLong, paramTimeUnit);
      return;
    }
    catch (InterruptedException paramTimeUnit)
    {
      throw new RuntimeException("Interrupted", paramTimeUnit);
    }
  }
  
  public void awaitTerminalEventAndUnsubscribeOnTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    for (;;)
    {
      boolean bool;
      try
      {
        bool = this.latch.await(paramLong, paramTimeUnit);
      }
      catch (InterruptedException paramTimeUnit)
      {
        unsubscribe();
        return;
      }
      unsubscribe();
      return;
      if (bool) {}
    }
  }
  
  public Thread getLastSeenThread()
  {
    return this.lastSeenThread;
  }
  
  public List<Notification<T>> getOnCompletedEvents()
  {
    return this.testObserver.getOnCompletedEvents();
  }
  
  public List<Throwable> getOnErrorEvents()
  {
    return this.testObserver.getOnErrorEvents();
  }
  
  public List<T> getOnNextEvents()
  {
    return this.testObserver.getOnNextEvents();
  }
  
  public void onCompleted()
  {
    try
    {
      this.lastSeenThread = Thread.currentThread();
      this.testObserver.onCompleted();
      return;
    }
    finally
    {
      this.latch.countDown();
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    try
    {
      this.lastSeenThread = Thread.currentThread();
      this.testObserver.onError(paramThrowable);
      return;
    }
    finally
    {
      this.latch.countDown();
    }
  }
  
  public void onNext(T paramT)
  {
    this.lastSeenThread = Thread.currentThread();
    this.testObserver.onNext(paramT);
  }
  
  public void requestMore(long paramLong)
  {
    request(paramLong);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observers\TestSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */