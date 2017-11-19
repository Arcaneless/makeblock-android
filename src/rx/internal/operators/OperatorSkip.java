package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorSkip<T>
  implements Observable.Operator<T, T>
{
  final int toSkip;
  
  public OperatorSkip(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("n >= 0 required but it was " + paramInt);
    }
    this.toSkip = paramInt;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      int skipped = 0;
      
      public void onCompleted()
      {
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (this.skipped >= OperatorSkip.this.toSkip)
        {
          paramSubscriber.onNext(paramAnonymousT);
          return;
        }
        this.skipped += 1;
      }
      
      public void setProducer(Producer paramAnonymousProducer)
      {
        paramSubscriber.setProducer(paramAnonymousProducer);
        paramAnonymousProducer.request(OperatorSkip.this.toSkip);
      }
    };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorSkip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */