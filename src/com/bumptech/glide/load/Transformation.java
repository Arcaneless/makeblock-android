package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.Resource;

public abstract interface Transformation<T>
  extends Key
{
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract Resource<T> transform(Context paramContext, Resource<T> paramResource, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\Transformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */