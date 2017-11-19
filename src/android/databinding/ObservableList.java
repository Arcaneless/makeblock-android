package android.databinding;

import java.util.List;

public abstract interface ObservableList<T>
  extends List<T>
{
  public abstract void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> paramOnListChangedCallback);
  
  public abstract void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> paramOnListChangedCallback);
  
  public static abstract class OnListChangedCallback<T extends ObservableList>
  {
    public abstract void onChanged(T paramT);
    
    public abstract void onItemRangeChanged(T paramT, int paramInt1, int paramInt2);
    
    public abstract void onItemRangeInserted(T paramT, int paramInt1, int paramInt2);
    
    public abstract void onItemRangeMoved(T paramT, int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void onItemRangeRemoved(T paramT, int paramInt1, int paramInt2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ObservableList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */