package com.bumptech.glide.load.data;

import com.bumptech.glide.util.Preconditions;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry
{
  private static final DataRewinder.Factory<?> DEFAULT_FACTORY = new DataRewinder.Factory()
  {
    public DataRewinder<Object> build(Object paramAnonymousObject)
    {
      return new DataRewinderRegistry.DefaultRewinder(paramAnonymousObject);
    }
    
    public Class<Object> getDataClass()
    {
      throw new UnsupportedOperationException("Not implemented");
    }
  };
  private final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();
  
  public <T> DataRewinder<T> build(T paramT)
  {
    Object localObject2;
    Object localObject1;
    try
    {
      Preconditions.checkNotNull(paramT);
      localObject2 = (DataRewinder.Factory)this.rewinders.get(paramT.getClass());
    }
    finally {}
    Iterator localIterator = this.rewinders.values().iterator();
    for (;;)
    {
      localObject1 = localObject2;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject1 = (DataRewinder.Factory)localIterator.next();
      if (((DataRewinder.Factory)localObject1).getDataClass().isAssignableFrom(paramT.getClass())) {
        break;
      }
    }
    for (;;)
    {
      localObject2 = DEFAULT_FACTORY;
      do
      {
        paramT = ((DataRewinder.Factory)localObject2).build(paramT);
        return paramT;
        localObject1 = localObject2;
        if (localObject2 == null) {
          break;
        }
        localObject2 = localObject1;
      } while (localObject1 != null);
    }
  }
  
  public void register(DataRewinder.Factory<?> paramFactory)
  {
    try
    {
      this.rewinders.put(paramFactory.getDataClass(), paramFactory);
      return;
    }
    finally
    {
      paramFactory = finally;
      throw paramFactory;
    }
  }
  
  private static class DefaultRewinder
    implements DataRewinder<Object>
  {
    private final Object data;
    
    public DefaultRewinder(Object paramObject)
    {
      this.data = paramObject;
    }
    
    public void cleanup() {}
    
    public Object rewindAndGet()
    {
      return this.data;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\DataRewinderRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */