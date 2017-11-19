package ml.xuexin.bleconsultant.entity;

import java.util.HashMap;
import java.util.Map;

public class DoubleKeyMap<K, T>
{
  private Map<K, Map<K, T>> map = new HashMap();
  
  public void clear()
  {
    this.map.clear();
  }
  
  public T get(K paramK1, K paramK2)
  {
    try
    {
      paramK1 = ((Map)this.map.get(paramK1)).get(paramK2);
      return paramK1;
    }
    catch (NullPointerException paramK1) {}
    return null;
  }
  
  public T put(K paramK1, K paramK2, T paramT)
  {
    Map localMap = (Map)this.map.get(paramK1);
    Object localObject = localMap;
    if (localMap == null)
    {
      localObject = new HashMap();
      this.map.put(paramK1, localObject);
    }
    return (T)((Map)localObject).put(paramK2, paramT);
  }
  
  public T remove(K paramK1, K paramK2)
  {
    try
    {
      paramK1 = ((Map)this.map.get(paramK1)).remove(paramK2);
      return paramK1;
    }
    catch (NullPointerException paramK1) {}
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\entity\DoubleKeyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */