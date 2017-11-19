package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MultiTransformation<T>
  implements Transformation<T>
{
  private final Collection<? extends Transformation<T>> transformations;
  
  public MultiTransformation(Collection<? extends Transformation<T>> paramCollection)
  {
    if (paramCollection.isEmpty()) {
      throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }
    this.transformations = paramCollection;
  }
  
  @SafeVarargs
  public MultiTransformation(Transformation<T>... paramVarArgs)
  {
    if (paramVarArgs.length < 1) {
      throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }
    this.transformations = Arrays.asList(paramVarArgs);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof MultiTransformation))
    {
      paramObject = (MultiTransformation)paramObject;
      return this.transformations.equals(((MultiTransformation)paramObject).transformations);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.transformations.hashCode();
  }
  
  public Resource<T> transform(Context paramContext, Resource<T> paramResource, int paramInt1, int paramInt2)
  {
    Object localObject = paramResource;
    Iterator localIterator = this.transformations.iterator();
    while (localIterator.hasNext())
    {
      Resource localResource = ((Transformation)localIterator.next()).transform(paramContext, (Resource)localObject, paramInt1, paramInt2);
      if ((localObject != null) && (!localObject.equals(paramResource)) && (!localObject.equals(localResource))) {
        ((Resource)localObject).recycle();
      }
      localObject = localResource;
    }
    return (Resource<T>)localObject;
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    Iterator localIterator = this.transformations.iterator();
    while (localIterator.hasNext()) {
      ((Transformation)localIterator.next()).updateDiskCacheKey(paramMessageDigest);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\MultiTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */