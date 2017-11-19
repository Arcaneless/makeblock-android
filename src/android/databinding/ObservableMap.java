package android.databinding;

import java.util.Map;

public abstract interface ObservableMap<K, V>
  extends Map<K, V>
{
  public abstract void addOnMapChangedCallback(OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> paramOnMapChangedCallback);
  
  public abstract void removeOnMapChangedCallback(OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> paramOnMapChangedCallback);
  
  public static abstract class OnMapChangedCallback<T extends ObservableMap<K, V>, K, V>
  {
    public abstract void onMapChanged(T paramT, K paramK);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */