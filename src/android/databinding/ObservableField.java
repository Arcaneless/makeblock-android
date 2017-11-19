package android.databinding;

import java.io.Serializable;

public class ObservableField<T>
  extends BaseObservable
  implements Serializable
{
  static final long serialVersionUID = 1L;
  private T mValue;
  
  public ObservableField() {}
  
  public ObservableField(T paramT)
  {
    this.mValue = paramT;
  }
  
  public T get()
  {
    return (T)this.mValue;
  }
  
  public void set(T paramT)
  {
    if (paramT != this.mValue)
    {
      this.mValue = paramT;
      notifyChange();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */