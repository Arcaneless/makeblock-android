package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.internal.util.RxJavaPluginUtils;

public final class OperatorMap<T, R>
  implements Observable.Operator<R, T>
{
  final Func1<? super T, ? extends R> transformer;
  
  public OperatorMap(Func1<? super T, ? extends R> paramFunc1)
  {
    this.transformer = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    MapSubscriber localMapSubscriber = new MapSubscriber(paramSubscriber, this.transformer);
    paramSubscriber.add(localMapSubscriber);
    return localMapSubscriber;
  }
  
  static final class MapSubscriber<T, R>
    extends Subscriber<T>
  {
    final Subscriber<? super R> actual;
    boolean done;
    final Func1<? super T, ? extends R> mapper;
    
    public MapSubscriber(Subscriber<? super R> paramSubscriber, Func1<? super T, ? extends R> paramFunc1)
    {
      this.actual = paramSubscriber;
      this.mapper = paramFunc1;
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
        Object localObject = this.mapper.call(paramT);
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


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */