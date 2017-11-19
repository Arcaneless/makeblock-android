package rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorToObservableList<T>
  implements Observable.Operator<List<T>, T>
{
  public static <T> OperatorToObservableList<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super List<T>> paramSubscriber)
  {
    final SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    Subscriber local1 = new Subscriber()
    {
      boolean completed = false;
      List<T> list = new LinkedList();
      
      public void onCompleted()
      {
        if (!this.completed) {
          this.completed = true;
        }
        try
        {
          ArrayList localArrayList = new ArrayList(this.list);
          this.list = null;
          localSingleDelayedProducer.setValue(localArrayList);
          return;
        }
        catch (Throwable localThrowable)
        {
          Exceptions.throwOrReport(localThrowable, this);
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (!this.completed) {
          this.list.add(paramAnonymousT);
        }
      }
      
      public void onStart()
      {
        request(Long.MAX_VALUE);
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }
  
  private static final class Holder
  {
    static final OperatorToObservableList<Object> INSTANCE = new OperatorToObservableList();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorToObservableList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */