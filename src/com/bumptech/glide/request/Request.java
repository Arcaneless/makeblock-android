package com.bumptech.glide.request;

public abstract interface Request
{
  public abstract void begin();
  
  public abstract void clear();
  
  public abstract boolean isCancelled();
  
  public abstract boolean isComplete();
  
  public abstract boolean isFailed();
  
  public abstract boolean isPaused();
  
  public abstract boolean isResourceSet();
  
  public abstract boolean isRunning();
  
  public abstract void pause();
  
  public abstract void recycle();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\request\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */