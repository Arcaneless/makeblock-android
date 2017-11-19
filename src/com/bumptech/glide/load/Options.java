package com.bumptech.glide.load;

import android.support.v4.util.ArrayMap;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public final class Options
  implements Key
{
  private final ArrayMap<Option<?>, Object> values = new ArrayMap();
  
  private static <T> void updateDiskCacheKey(Option<T> paramOption, Object paramObject, MessageDigest paramMessageDigest)
  {
    paramOption.update(paramObject, paramMessageDigest);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Options))
    {
      paramObject = (Options)paramObject;
      return this.values.equals(((Options)paramObject).values);
    }
    return false;
  }
  
  public <T> T get(Option<T> paramOption)
  {
    if (this.values.containsKey(paramOption)) {
      return (T)this.values.get(paramOption);
    }
    return (T)paramOption.getDefaultValue();
  }
  
  public int hashCode()
  {
    return this.values.hashCode();
  }
  
  public void putAll(Options paramOptions)
  {
    this.values.putAll(paramOptions.values);
  }
  
  public <T> Options set(Option<T> paramOption, T paramT)
  {
    this.values.put(paramOption, paramT);
    return this;
  }
  
  public String toString()
  {
    return "Options{values=" + this.values + '}';
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    Iterator localIterator = this.values.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      updateDiskCacheKey((Option)localEntry.getKey(), localEntry.getValue(), paramMessageDigest);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */