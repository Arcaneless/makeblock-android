package rx.internal.util.unsafe;

import sun.misc.Unsafe;

abstract class SpmcArrayQueueProducerField<E>
  extends SpmcArrayQueueL1Pad<E>
{
  protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueProducerField.class, "producerIndex");
  private volatile long producerIndex;
  
  public SpmcArrayQueueProducerField(int paramInt)
  {
    super(paramInt);
  }
  
  protected final long lvProducerIndex()
  {
    return this.producerIndex;
  }
  
  protected final void soTail(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, paramLong);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpmcArrayQueueProducerField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */