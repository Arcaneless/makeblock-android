package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;
import rx.internal.util.RxJavaPluginUtils;

public final class OperatorSingle<T>
  implements Observable.Operator<T, T>
{
  private final T defaultValue;
  private final boolean hasDefaultValue;
  
  OperatorSingle()
  {
    this(false, null);
  }
  
  public OperatorSingle(T paramT)
  {
    this(true, paramT);
  }
  
  private OperatorSingle(boolean paramBoolean, T paramT)
  {
    this.hasDefaultValue = paramBoolean;
    this.defaultValue = paramT;
  }
  
  public static <T> OperatorSingle<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber, this.hasDefaultValue, this.defaultValue);
    paramSubscriber.add(localParentSubscriber);
    return localParentSubscriber;
  }
  
  private static class Holder
  {
    static final OperatorSingle<?> INSTANCE = new OperatorSingle();
  }
  
  private static final class ParentSubscriber<T>
    extends Subscriber<T>
  {
    private final Subscriber<? super T> child;
    private final T defaultValue;
    private final boolean hasDefaultValue;
    private boolean hasTooManyElements;
    private boolean isNonEmpty;
    private T value;
    
    ParentSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean, T paramT)
    {
      this.child = paramSubscriber;
      this.hasDefaultValue = paramBoolean;
      this.defaultValue = paramT;
      request(2L);
    }
    
    public void onCompleted()
    {
      if (this.hasTooManyElements) {
        return;
      }
      if (this.isNonEmpty)
      {
        this.child.setProducer(new SingleProducer(this.child, this.value));
        return;
      }
      if (this.hasDefaultValue)
      {
        this.child.setProducer(new SingleProducer(this.child, this.defaultValue));
        return;
      }
      this.child.onError(new NoSuchElementException("Sequence contains no elements"));
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.hasTooManyElements)
      {
        RxJavaPluginUtils.handleException(paramThrowable);
        return;
      }
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      if (this.hasTooManyElements) {
        return;
      }
      if (this.isNonEmpty)
      {
        this.hasTooManyElements = true;
        this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
        unsubscribe();
        return;
      }
      this.value = paramT;
      this.isNonEmpty = true;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\operators\OperatorSingle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */