package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

public final class OperatorOnErrorResumeNextViaFunction<T>
  implements Observable.Operator<T, T>
{
  final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;
  
  public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> paramFunc1)
  {
    this.resumeFunction = paramFunc1;
  }
  
  public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(Observable<? extends T> paramObservable)
  {
    new OperatorOnErrorResumeNextViaFunction(new Func1()
    {
      public Observable<? extends T> call(Throwable paramAnonymousThrowable)
      {
        if ((paramAnonymousThrowable instanceof Exception)) {
          return this.val$other;
        }
        return Observable.error(paramAnonymousThrowable);
      }
    });
  }
  
  public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(Observable<? extends T> paramObservable)
  {
    new OperatorOnErrorResumeNextViaFunction(new Func1()
    {
      public Observable<? extends T> call(Throwable paramAnonymousThrowable)
      {
        return this.val$other;
      }
    });
  }
  
  public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(Func1<Throwable, ? extends T> paramFunc1)
  {
    new OperatorOnErrorResumeNextViaFunction(new Func1()
    {
      public Observable<? extends T> call(Throwable paramAnonymousThrowable)
      {
        return Observable.just(this.val$resumeFunction.call(paramAnonymousThrowable));
      }
    });
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    final ProducerArbiter localProducerArbiter = new ProducerArbiter();
    final SerialSubscription localSerialSubscription = new SerialSubscription();
    Subscriber local4 = new Subscriber()
    {
      private boolean done;
      long produced;
      
      public void onCompleted()
      {
        if (this.done) {
          return;
        }
        this.done = true;
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (this.done)
        {
          Exceptions.throwIfFatal(paramAnonymousThrowable);
          RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAnonymousThrowable);
          return;
        }
        this.done = true;
        for (;;)
        {
          long l;
          try
          {
            unsubscribe();
            local1 = new Subscriber()
            {
              public void onCompleted()
              {
                OperatorOnErrorResumeNextViaFunction.4.this.val$child.onCompleted();
              }
              
              public void onError(Throwable paramAnonymous2Throwable)
              {
                OperatorOnErrorResumeNextViaFunction.4.this.val$child.onError(paramAnonymous2Throwable);
              }
              
              public void onNext(T paramAnonymous2T)
              {
                OperatorOnErrorResumeNextViaFunction.4.this.val$child.onNext(paramAnonymous2T);
              }
              
              public void setProducer(Producer paramAnonymous2Producer)
              {
                OperatorOnErrorResumeNextViaFunction.4.this.val$pa.setProducer(paramAnonymous2Producer);
              }
            };
            localSerialSubscription.set(local1);
            l = this.produced;
          }
          catch (Throwable paramAnonymousThrowable)
          {
            Subscriber local1;
            Exceptions.throwOrReport(paramAnonymousThrowable, paramSubscriber);
            return;
          }
          localProducerArbiter.produced(l);
          ((Observable)OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(paramAnonymousThrowable)).unsafeSubscribe(local1);
          return;
          if (l == 0L) {}
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (this.done) {
          return;
        }
        this.produced += 1L;
        paramSubscriber.onNext(paramAnonymousT);
      }
      
      public void setProducer(Producer paramAnonymousProducer)
      {
        localProducerArbiter.setProducer(paramAnonymousProducer);
      }
    };
    localSerialSubscription.set(local4);
    paramSubscriber.add(localSerialSubscription);
    paramSubscriber.setProducer(localProducerArbiter);
    return local4;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorOnErrorResumeNextViaFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */