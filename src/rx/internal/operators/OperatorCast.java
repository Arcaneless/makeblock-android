package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxJavaPluginUtils;

public class OperatorCast<T, R>
  implements Observable.Operator<R, T>
{
  final Class<R> castClass;
  
  public OperatorCast(Class<R> paramClass)
  {
    this.castClass = paramClass;
  }
  
  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    CastSubscriber localCastSubscriber = new CastSubscriber(paramSubscriber, this.castClass);
    paramSubscriber.add(localCastSubscriber);
    return localCastSubscriber;
  }
  
  static final class CastSubscriber<T, R>
    extends Subscriber<T>
  {
    final Subscriber<? super R> actual;
    final Class<R> castClass;
    boolean done;
    
    public CastSubscriber(Subscriber<? super R> paramSubscriber, Class<R> paramClass)
    {
      this.actual = paramSubscriber;
      this.castClass = paramClass;
    }
    
    public void onCompleted()
    {
      if (this.done) {
        return;
      }
      this.actual.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.done)
      {
        RxJavaPluginUtils.handleException(paramThrowable);
        return;
      }
      this.done = true;
      this.actual.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      try
      {
        Object localObject = this.castClass.cast(paramT);
        this.actual.onNext(localObject);
        return;
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        unsubscribe();
        onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
      }
    }
    
    public void setProducer(Producer paramProducer)
    {
      this.actual.setProducer(paramProducer);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorCast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */