package com.bumptech.glide.load.model;

public abstract interface ModelLoaderFactory<T, Y>
{
  public abstract ModelLoader<T, Y> build(MultiModelLoaderFactory paramMultiModelLoaderFactory);
  
  public abstract void teardown();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\model\ModelLoaderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */