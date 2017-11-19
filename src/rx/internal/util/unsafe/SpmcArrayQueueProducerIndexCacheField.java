package rx.internal.util.unsafe;

abstract class SpmcArrayQueueProducerIndexCacheField<E>
  extends SpmcArrayQueueMidPad<E>
{
  private volatile long producerIndexCache;
  
  public SpmcArrayQueueProducerIndexCacheField(int paramInt)
  {
    super(paramInt);
  }
  
  protected final long lvProducerIndexCache()
  {
    return this.producerIndexCache;
  }
  
  protected final void svProducerIndexCache(long paramLong)
  {
    this.producerIndexCache = paramLong;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpmcArrayQueueProducerIndexCacheField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */