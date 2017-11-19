package rx.subjects;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;

public abstract class Subject<T, R>
  extends Observable<R>
  implements Observer<T>
{
  protected Subject(Observable.OnSubscribe<R> paramOnSubscribe)
  {
    super(paramOnSubscribe);
  }
  
  public abstract boolean hasObservers();
  
  public final SerializedSubject<T, R> toSerialized()
  {
    if (getClass() == SerializedSubject.class) {
      return (SerializedSubject)this;
    }
    return new SerializedSubject(this);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\subjects\Subject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */