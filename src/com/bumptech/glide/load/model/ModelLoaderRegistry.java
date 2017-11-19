package com.bumptech.glide.load.model;

import android.support.v4.util.Pools.Pool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ModelLoaderRegistry
{
  private final ModelLoaderCache cache = new ModelLoaderCache();
  private final MultiModelLoaderFactory multiModelLoaderFactory;
  
  public ModelLoaderRegistry(Pools.Pool<List<Exception>> paramPool)
  {
    this(new MultiModelLoaderFactory(paramPool));
  }
  
  ModelLoaderRegistry(MultiModelLoaderFactory paramMultiModelLoaderFactory)
  {
    this.multiModelLoaderFactory = paramMultiModelLoaderFactory;
  }
  
  private static <A> Class<A> getClass(A paramA)
  {
    return paramA.getClass();
  }
  
  private <A> List<ModelLoader<A, ?>> getModelLoadersForClass(Class<A> paramClass)
  {
    List localList2 = this.cache.get(paramClass);
    List localList1 = localList2;
    if (localList2 == null)
    {
      localList1 = Collections.unmodifiableList(this.multiModelLoaderFactory.build(paramClass));
      this.cache.put(paramClass, localList1);
    }
    return localList1;
  }
  
  private <Model, Data> void tearDown(List<ModelLoaderFactory<Model, Data>> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      ((ModelLoaderFactory)paramList.next()).teardown();
    }
  }
  
  public <Model, Data> void append(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      this.multiModelLoaderFactory.append(paramClass, paramClass1, paramModelLoaderFactory);
      this.cache.clear();
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public <Model, Data> ModelLoader<Model, Data> build(Class<Model> paramClass, Class<Data> paramClass1)
  {
    try
    {
      paramClass = this.multiModelLoaderFactory.build(paramClass, paramClass1);
      return paramClass;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public List<Class<?>> getDataClasses(Class<?> paramClass)
  {
    try
    {
      paramClass = this.multiModelLoaderFactory.getDataClasses(paramClass);
      return paramClass;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public <A> List<ModelLoader<A, ?>> getModelLoaders(A paramA)
  {
    for (;;)
    {
      List localList;
      int j;
      ArrayList localArrayList;
      try
      {
        localList = getModelLoadersForClass(getClass(paramA));
        j = localList.size();
        localArrayList = new ArrayList(j);
      }
      finally {}
      ModelLoader localModelLoader = (ModelLoader)localList.get(i);
      if (localModelLoader.handles(paramA)) {
        localArrayList.add(localModelLoader);
      }
      i += 1;
      break label86;
      return localArrayList;
      int i = 0;
      label86:
      if (i >= j) {}
    }
  }
  
  public <Model, Data> void prepend(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      this.multiModelLoaderFactory.prepend(paramClass, paramClass1, paramModelLoaderFactory);
      this.cache.clear();
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public <Model, Data> void remove(Class<Model> paramClass, Class<Data> paramClass1)
  {
    try
    {
      tearDown(this.multiModelLoaderFactory.remove(paramClass, paramClass1));
      this.cache.clear();
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public <Model, Data> void replace(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      tearDown(this.multiModelLoaderFactory.replace(paramClass, paramClass1, paramModelLoaderFactory));
      this.cache.clear();
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  private static class ModelLoaderCache
  {
    private final Map<Class<?>, Entry<?>> cachedModelLoaders = new HashMap();
    
    public void clear()
    {
      this.cachedModelLoaders.clear();
    }
    
    public <Model> List<ModelLoader<Model, ?>> get(Class<Model> paramClass)
    {
      paramClass = (Entry)this.cachedModelLoaders.get(paramClass);
      if (paramClass == null) {
        return null;
      }
      return paramClass.loaders;
    }
    
    public <Model> void put(Class<Model> paramClass, List<ModelLoader<Model, ?>> paramList)
    {
      if ((Entry)this.cachedModelLoaders.put(paramClass, new Entry(paramList)) != null) {
        throw new IllegalStateException("Already cached loaders for model: " + paramClass);
      }
    }
    
    private static class Entry<Model>
    {
      final List<ModelLoader<Model, ?>> loaders;
      
      public Entry(List<ModelLoader<Model, ?>> paramList)
      {
        this.loaders = paramList;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\model\ModelLoaderRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */