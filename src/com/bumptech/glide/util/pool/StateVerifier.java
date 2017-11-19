package com.bumptech.glide.util.pool;

public abstract class StateVerifier
{
  private static final boolean DEBUG = false;
  
  public static StateVerifier newInstance()
  {
    return new DefaultStateVerifier();
  }
  
  abstract void setRecycled(boolean paramBoolean);
  
  public abstract void throwIfRecycled();
  
  private static class DebugStateVerifier
    extends StateVerifier
  {
    private volatile RuntimeException recycledAtStackTraceException;
    
    DebugStateVerifier()
    {
      super();
    }
    
    void setRecycled(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        this.recycledAtStackTraceException = new RuntimeException("Released");
        return;
      }
      this.recycledAtStackTraceException = null;
    }
    
    public void throwIfRecycled()
    {
      if (this.recycledAtStackTraceException != null) {
        throw new IllegalStateException("Already released", this.recycledAtStackTraceException);
      }
    }
  }
  
  private static class DefaultStateVerifier
    extends StateVerifier
  {
    private volatile boolean isReleased;
    
    DefaultStateVerifier()
    {
      super();
    }
    
    public void setRecycled(boolean paramBoolean)
    {
      this.isReleased = paramBoolean;
    }
    
    public void throwIfRecycled()
    {
      if (this.isReleased) {
        throw new IllegalStateException("Already released");
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\util\pool\StateVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */