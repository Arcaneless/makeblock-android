package android.databinding;

import android.support.v4.util.ArrayMap;
import java.util.Collection;
import java.util.Iterator;

public class ObservableArrayMap<K, V>
  extends ArrayMap<K, V>
  implements ObservableMap<K, V>
{
  private transient MapChangeRegistry mListeners;
  
  private void notifyChange(Object paramObject)
  {
    if (this.mListeners != null) {
      this.mListeners.notifyCallbacks(this, 0, paramObject);
    }
  }
  
  public void addOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> paramOnMapChangedCallback)
  {
    if (this.mListeners == null) {
      this.mListeners = new MapChangeRegistry();
    }
    this.mListeners.add(paramOnMapChangedCallback);
  }
  
  public void clear()
  {
    if (!isEmpty())
    {
      super.clear();
      notifyChange(null);
    }
  }
  
  public V put(K paramK, V paramV)
  {
    super.put(paramK, paramV);
    notifyChange(paramK);
    return paramV;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      int i = indexOfKey(paramCollection.next());
      if (i >= 0)
      {
        bool = true;
        removeAt(i);
      }
    }
    return bool;
  }
  
  public V removeAt(int paramInt)
  {
    Object localObject1 = keyAt(paramInt);
    Object localObject2 = super.removeAt(paramInt);
    if (localObject2 != null) {
      notifyChange(localObject1);
    }
    return (V)localObject2;
  }
  
  public void removeOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> paramOnMapChangedCallback)
  {
    if (this.mListeners != null) {
      this.mListeners.remove(paramOnMapChangedCallback);
    }
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    boolean bool = false;
    int i = size() - 1;
    while (i >= 0)
    {
      if (!paramCollection.contains(keyAt(i)))
      {
        removeAt(i);
        bool = true;
      }
      i -= 1;
    }
    return bool;
  }
  
  public V setValueAt(int paramInt, V paramV)
  {
    Object localObject = keyAt(paramInt);
    paramV = super.setValueAt(paramInt, paramV);
    notifyChange(localObject);
    return paramV;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableArrayMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */