package android.databinding.adapters;

import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil
{
  private static SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray();
  
  public static <T> T getListener(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return (T)paramView.getTag(paramInt);
    }
    WeakHashMap localWeakHashMap;
    synchronized (sListeners)
    {
      localWeakHashMap = (WeakHashMap)sListeners.get(paramInt);
      break label68;
      return null;
    }
    label43:
    paramView = (WeakReference)localWeakHashMap.get(paramView);
    for (;;)
    {
      return null;
      label68:
      do
      {
        paramView = paramView.get();
        return paramView;
        if (localWeakHashMap != null) {
          break label43;
        }
        break;
      } while (paramView != null);
    }
  }
  
  public static <T> T trackListener(View paramView, T paramT, int paramInt)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 14)
    {
      localObject = paramView.getTag(paramInt);
      paramView.setTag(paramInt, paramT);
      return (T)localObject;
    }
    WeakHashMap localWeakHashMap;
    synchronized (sListeners)
    {
      localWeakHashMap = (WeakHashMap)sListeners.get(paramInt);
      break label117;
      localObject = new WeakHashMap();
      sListeners.put(paramInt, localObject);
      break label128;
      label64:
      paramView = (WeakReference)((WeakHashMap)localObject).remove(paramView);
      break label135;
      return null;
    }
    label87:
    paramView = (WeakReference)((WeakHashMap)localObject).put(paramView, new WeakReference(paramT));
    label117:
    label128:
    label135:
    label143:
    for (;;)
    {
      paramView = paramView.get();
      return paramView;
      localObject = localWeakHashMap;
      if (localWeakHashMap == null) {
        break;
      }
      if (paramT != null) {
        break label87;
      }
      break label64;
      for (;;)
      {
        if (paramView != null) {
          break label143;
        }
        break;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\ListenerUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */