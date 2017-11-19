package android.databinding;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableArrayList<T>
  extends ArrayList<T>
  implements ObservableList<T>
{
  private transient ListChangeRegistry mListeners = new ListChangeRegistry();
  
  private void notifyAdd(int paramInt1, int paramInt2)
  {
    if (this.mListeners != null) {
      this.mListeners.notifyInserted(this, paramInt1, paramInt2);
    }
  }
  
  private void notifyRemove(int paramInt1, int paramInt2)
  {
    if (this.mListeners != null) {
      this.mListeners.notifyRemoved(this, paramInt1, paramInt2);
    }
  }
  
  public void add(int paramInt, T paramT)
  {
    super.add(paramInt, paramT);
    notifyAdd(paramInt, 1);
  }
  
  public boolean add(T paramT)
  {
    super.add(paramT);
    notifyAdd(size() - 1, 1);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection<? extends T> paramCollection)
  {
    boolean bool = super.addAll(paramInt, paramCollection);
    if (bool) {
      notifyAdd(paramInt, paramCollection.size());
    }
    return bool;
  }
  
  public boolean addAll(Collection<? extends T> paramCollection)
  {
    int i = size();
    boolean bool = super.addAll(paramCollection);
    if (bool) {
      notifyAdd(i, size() - i);
    }
    return bool;
  }
  
  public void addOnListChangedCallback(ObservableList.OnListChangedCallback paramOnListChangedCallback)
  {
    if (this.mListeners == null) {
      this.mListeners = new ListChangeRegistry();
    }
    this.mListeners.add(paramOnListChangedCallback);
  }
  
  public void clear()
  {
    int i = size();
    super.clear();
    if (i != 0) {
      notifyRemove(0, i);
    }
  }
  
  public T remove(int paramInt)
  {
    Object localObject = super.remove(paramInt);
    notifyRemove(paramInt, 1);
    return (T)localObject;
  }
  
  public boolean remove(Object paramObject)
  {
    int i = indexOf(paramObject);
    if (i >= 0)
    {
      remove(i);
      return true;
    }
    return false;
  }
  
  public void removeOnListChangedCallback(ObservableList.OnListChangedCallback paramOnListChangedCallback)
  {
    if (this.mListeners != null) {
      this.mListeners.remove(paramOnListChangedCallback);
    }
  }
  
  protected void removeRange(int paramInt1, int paramInt2)
  {
    super.removeRange(paramInt1, paramInt2);
    notifyRemove(paramInt1, paramInt2 - paramInt1);
  }
  
  public T set(int paramInt, T paramT)
  {
    paramT = super.set(paramInt, paramT);
    if (this.mListeners != null) {
      this.mListeners.notifyChanged(this, paramInt, 1);
    }
    return paramT;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */