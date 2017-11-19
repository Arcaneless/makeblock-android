package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.internal.util.RxJavaPluginUtils;

public final class OperatorFilter<T>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, Boolean> predicate;
  
  public OperatorFilter(Func1<? super T, Boolean> paramFunc1)
  {
    this.predicate = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    FilterSubscriber localFilterSubscriber = new FilterSubscriber(paramSubscriber, this.predicate);
    paramSubscriber.add(localFilterSubscriber);
    return localFilterSubscriber;
  }
  
  static final class FilterSubscriber<T>
    extends Subscriber<T>
  {
    final Subscriber<? super T> actual;
    boolean done;
    final Func1<? super T, Boolean> predicate;
    
    public FilterSubscriber(Subscriber<? super T> paramSubscriber, Func1<? super T, Boolean> paramFunc1)
    {
      this.actual = paramSubscriber;
      this.predicate = paramFunc1;
      request(0L);
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
        boolean bool = ((Boolean)this.predicate.call(paramT)).booleanValue();
        if (bool)
        {
          this.actual.onNext(paramT);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        unsubscribe();
        onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
        return;
      }
      request(1L);
    }
    
    public void setProducer(Producer paramProducer)
    {
      super.setProducer(paramProducer);
      this.actual.setProducer(paramProducer);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */