package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;

public final class OnSubscribeUsing<T, Resource>
  implements Observable.OnSubscribe<T>
{
  private final Action1<? super Resource> dispose;
  private final boolean disposeEagerly;
  private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
  private final Func0<Resource> resourceFactory;
  
  public OnSubscribeUsing(Func0<Resource> paramFunc0, Func1<? super Resource, ? extends Observable<? extends T>> paramFunc1, Action1<? super Resource> paramAction1, boolean paramBoolean)
  {
    this.resourceFactory = paramFunc0;
    this.observableFactory = paramFunc1;
    this.dispose = paramAction1;
    this.disposeEagerly = paramBoolean;
  }
  
  private Throwable dispose(Action0 paramAction0)
  {
    try
    {
      paramAction0.call();
      return null;
    }
    catch (Throwable paramAction0) {}
    return paramAction0;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    for (;;)
    {
      try
      {
        Object localObject1 = this.resourceFactory.call();
        localObject2 = new DisposeAction(this.dispose, localObject1);
        paramSubscriber.add((Subscription)localObject2);
        try
        {
          localObject1 = (Observable)this.observableFactory.call(localObject1);
          if (!this.disposeEagerly) {
            break label120;
          }
          localObject1 = ((Observable)localObject1).doOnTerminate((Action0)localObject2);
        }
        catch (Throwable localThrowable1)
        {
          try
          {
            ((Observable)localObject1).unsafeSubscribe(Subscribers.wrap(paramSubscriber));
            return;
          }
          catch (Throwable localThrowable3)
          {
            for (;;)
            {
              continue;
              if (localObject2 == null) {}
            }
          }
          localThrowable1 = localThrowable1;
          localObject2 = dispose((Action0)localObject2);
          Exceptions.throwIfFatal(localThrowable1);
          Exceptions.throwIfFatal((Throwable)localObject2);
        }
        tmp92_89[0] = localThrowable1;
        Throwable[] tmp96_92 = tmp92_89;
        tmp96_92[1] = localObject2;
        paramSubscriber.onError(new CompositeException(tmp96_92));
        return;
      }
      catch (Throwable localThrowable2)
      {
        Exceptions.throwOrReport(localThrowable2, paramSubscriber);
        return;
      }
      label120:
      do
      {
        paramSubscriber.onError(localThrowable2);
        return;
        Observable localObservable = localThrowable2.doAfterTerminate((Action0)localObject2);
        break;
        localObject2 = dispose((Action0)localObject2);
        Exceptions.throwIfFatal(localObservable);
        Exceptions.throwIfFatal((Throwable)localObject2);
        break label190;
        paramSubscriber.onError(new CompositeException(new Throwable[] { localObservable, localObject2 }));
        return;
        paramSubscriber.onError(localObservable);
        return;
      } while (localObject2 == null);
    }
  }
  
  private static final class DisposeAction<Resource>
    extends AtomicBoolean
    implements Action0, Subscription
  {
    private static final long serialVersionUID = 4262875056400218316L;
    private Action1<? super Resource> dispose;
    private Resource resource;
    
    DisposeAction(Action1<? super Resource> paramAction1, Resource paramResource)
    {
      this.dispose = paramAction1;
      this.resource = paramResource;
      lazySet(false);
    }
    
    public void call()
    {
      if (compareAndSet(false, true)) {}
      try
      {
        this.dispose.call(this.resource);
        return;
      }
      finally
      {
        this.resource = null;
        this.dispose = null;
      }
    }
    
    public boolean isUnsubscribed()
    {
      return get();
    }
    
    public void unsubscribe()
    {
      call();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OnSubscribeUsing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */