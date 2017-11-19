package com.bumptech.glide.provider;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;

public class ResourceEncoderRegistry
{
  final List<Entry<?>> encoders = new ArrayList();
  
  public <Z> void add(Class<Z> paramClass, ResourceEncoder<Z> paramResourceEncoder)
  {
    try
    {
      this.encoders.add(new Entry(paramClass, paramResourceEncoder));
      return;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  @Nullable
  public <Z> ResourceEncoder<Z> get(Class<Z> paramClass)
  {
    for (;;)
    {
      int j;
      try
      {
        j = this.encoders.size();
      }
      finally {}
      Entry localEntry = (Entry)this.encoders.get(i);
      if (localEntry.handles(paramClass))
      {
        paramClass = localEntry.encoder;
        return paramClass;
      }
      i += 1;
      break label68;
      paramClass = null;
      continue;
      int i = 0;
      label68:
      if (i >= j) {}
    }
  }
  
  private static final class Entry<T>
  {
    final ResourceEncoder<T> encoder;
    private final Class<T> resourceClass;
    
    Entry(Class<T> paramClass, ResourceEncoder<T> paramResourceEncoder)
    {
      this.resourceClass = paramClass;
      this.encoder = paramResourceEncoder;
    }
    
    boolean handles(Class<?> paramClass)
    {
      return this.resourceClass.isAssignableFrom(paramClass);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\provider\ResourceEncoderRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */