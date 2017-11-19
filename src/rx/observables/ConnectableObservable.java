package rx.observables;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscription;
import rx.annotations.Beta;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.OnSubscribeAutoConnect;
import rx.internal.operators.OnSubscribeRefCount;

public abstract class ConnectableObservable<T>
  extends Observable<T>
{
  protected ConnectableObservable(Observable.OnSubscribe<T> paramOnSubscribe)
  {
    super(paramOnSubscribe);
  }
  
  @Beta
  public Observable<T> autoConnect()
  {
    return autoConnect(1);
  }
  
  @Beta
  public Observable<T> autoConnect(int paramInt)
  {
    return autoConnect(paramInt, Actions.empty());
  }
  
  @Beta
  public Observable<T> autoConnect(int paramInt, Action1<? super Subscription> paramAction1)
  {
    if (paramInt <= 0)
    {
      connect(paramAction1);
      return this;
    }
    return create(new OnSubscribeAutoConnect(this, paramInt, paramAction1));
  }
  
  public final Subscription connect()
  {
    final Subscription[] arrayOfSubscription = new Subscription[1];
    connect(new Action1()
    {
      public void call(Subscription paramAnonymousSubscription)
      {
        arrayOfSubscription[0] = paramAnonymousSubscription;
      }
    });
    return arrayOfSubscription[0];
  }
  
  public abstract void connect(Action1<? super Subscription> paramAction1);
  
  public Observable<T> refCount()
  {
    return create(new OnSubscribeRefCount(this));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\observables\ConnectableObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */