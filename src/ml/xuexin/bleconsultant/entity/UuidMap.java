package ml.xuexin.bleconsultant.entity;

public class UuidMap<T>
  extends DoubleKeyMap<String, T>
{
  public T get(String paramString1, String paramString2)
  {
    return (T)super.get(paramString1, paramString2);
  }
  
  public T put(String paramString1, String paramString2, T paramT)
  {
    return (T)super.put(paramString1, paramString2, paramT);
  }
  
  public T remove(String paramString1, String paramString2)
  {
    return (T)super.remove(paramString1, paramString2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\entity\UuidMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */