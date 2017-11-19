package com.bumptech.glide.load.engine.bitmap_recycle;

public abstract interface ArrayAdapterInterface<T>
{
  public abstract int getArrayLength(T paramT);
  
  public abstract int getElementSizeInBytes();
  
  public abstract String getTag();
  
  public abstract T newArray(int paramInt);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\ArrayAdapterInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */