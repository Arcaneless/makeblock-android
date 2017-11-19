package com.bumptech.glide.load.model;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.Registry.NoModelLoaderAvailableException;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MultiModelLoaderFactory
{
  private static final Factory DEFAULT_FACTORY = new Factory();
  private static final ModelLoader<Object, Object> EMPTY_MODEL_LOADER = new EmptyModelLoader();
  private final Set<Entry<?, ?>> alreadyUsedEntries = new HashSet();
  private final List<Entry<?, ?>> entries = new ArrayList();
  private final Pools.Pool<List<Exception>> exceptionListPool;
  private final Factory factory;
  
  public MultiModelLoaderFactory(Pools.Pool<List<Exception>> paramPool)
  {
    this(paramPool, DEFAULT_FACTORY);
  }
  
  MultiModelLoaderFactory(Pools.Pool<List<Exception>> paramPool, Factory paramFactory)
  {
    this.exceptionListPool = paramPool;
    this.factory = paramFactory;
  }
  
  private <Model, Data> void add(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory, boolean paramBoolean)
  {
    paramClass = new Entry(paramClass, paramClass1, paramModelLoaderFactory);
    paramClass1 = this.entries;
    if (paramBoolean) {}
    for (int i = this.entries.size();; i = 0)
    {
      paramClass1.add(i, paramClass);
      return;
    }
  }
  
  private <Model, Data> ModelLoader<Model, Data> build(Entry<?, ?> paramEntry)
  {
    return (ModelLoader)Preconditions.checkNotNull(paramEntry.factory.build(this));
  }
  
  private static <Model, Data> ModelLoader<Model, Data> emptyModelLoader()
  {
    return EMPTY_MODEL_LOADER;
  }
  
  private <Model, Data> ModelLoaderFactory<Model, Data> getFactory(Entry<?, ?> paramEntry)
  {
    return paramEntry.factory;
  }
  
  <Model, Data> void append(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      add(paramClass, paramClass1, paramModelLoaderFactory, true);
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
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
    }
    catch (Throwable paramClass)
    {
      Iterator localIterator;
      this.alreadyUsedEntries.clear();
      throw paramClass;
    }
    finally {}
    localIterator = this.entries.iterator();
    label25:
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      if (this.alreadyUsedEntries.contains(localEntry)) {
        break label209;
      }
      if (localEntry.handles(paramClass, paramClass1))
      {
        this.alreadyUsedEntries.add(localEntry);
        localArrayList.add(build(localEntry));
        this.alreadyUsedEntries.remove(localEntry);
      }
    }
    if (localArrayList.size() > 1) {}
    for (paramClass = this.factory.build(localArrayList, this.exceptionListPool);; paramClass = (ModelLoader)localArrayList.get(0))
    {
      label157:
      return paramClass;
      if (localArrayList.size() != 1) {
        break;
      }
    }
    for (;;)
    {
      paramClass = emptyModelLoader();
      break label157;
      int i;
      label209:
      do
      {
        throw new Registry.NoModelLoaderAvailableException(paramClass, paramClass1);
        i = 0;
        break;
        i = 1;
        break label25;
      } while (i == 0);
    }
  }
  
  <Model> List<ModelLoader<Model, ?>> build(Class<Model> paramClass)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      Iterator localIterator = this.entries.iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        if ((!this.alreadyUsedEntries.contains(localEntry)) && (localEntry.handles(paramClass)))
        {
          this.alreadyUsedEntries.add(localEntry);
          localArrayList.add(build(localEntry));
          this.alreadyUsedEntries.remove(localEntry);
        }
      }
    }
    catch (Throwable paramClass)
    {
      this.alreadyUsedEntries.clear();
      throw paramClass;
    }
    finally {}
    return localArrayList;
  }
  
  List<Class<?>> getDataClasses(Class<?> paramClass)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      Iterator localIterator = this.entries.iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        if ((!localArrayList.contains(localEntry.dataClass)) && (localEntry.handles(paramClass))) {
          localArrayList.add(localEntry.dataClass);
        }
      }
    }
    finally {}
    return localArrayList;
  }
  
  <Model, Data> void prepend(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      add(paramClass, paramClass1, paramModelLoaderFactory, false);
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  <Model, Data> List<ModelLoaderFactory<Model, Data>> remove(Class<Model> paramClass, Class<Data> paramClass1)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      Iterator localIterator = this.entries.iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        if (localEntry.handles(paramClass, paramClass1))
        {
          localIterator.remove();
          localArrayList.add(getFactory(localEntry));
        }
      }
    }
    finally {}
    return localArrayList;
  }
  
  <Model, Data> List<ModelLoaderFactory<Model, Data>> replace(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
  {
    try
    {
      List localList = remove(paramClass, paramClass1);
      append(paramClass, paramClass1, paramModelLoaderFactory);
      return localList;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  private static class EmptyModelLoader
    implements ModelLoader<Object, Object>
  {
    @Nullable
    public ModelLoader.LoadData<Object> buildLoadData(Object paramObject, int paramInt1, int paramInt2, Options paramOptions)
    {
      return null;
    }
    
    public boolean handles(Object paramObject)
    {
      return false;
    }
  }
  
  private static class Entry<Model, Data>
  {
    final Class<Data> dataClass;
    final ModelLoaderFactory<Model, Data> factory;
    private final Class<Model> modelClass;
    
    public Entry(Class<Model> paramClass, Class<Data> paramClass1, ModelLoaderFactory<Model, Data> paramModelLoaderFactory)
    {
      this.modelClass = paramClass;
      this.dataClass = paramClass1;
      this.factory = paramModelLoaderFactory;
    }
    
    public boolean handles(Class<?> paramClass)
    {
      return this.modelClass.isAssignableFrom(paramClass);
    }
    
    public boolean handles(Class<?> paramClass1, Class<?> paramClass2)
    {
      return (handles(paramClass1)) && (this.dataClass.isAssignableFrom(paramClass2));
    }
  }
  
  static class Factory
  {
    public <Model, Data> MultiModelLoader<Model, Data> build(List<ModelLoader<Model, Data>> paramList, Pools.Pool<List<Exception>> paramPool)
    {
      return new MultiModelLoader(paramList, paramPool);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\model\MultiModelLoaderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */