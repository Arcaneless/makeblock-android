package android.databinding;

public abstract class OnRebindCallback<T extends ViewDataBinding>
{
  public void onBound(T paramT) {}
  
  public void onCanceled(T paramT) {}
  
  public boolean onPreBind(T paramT)
  {
    return true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\OnRebindCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */