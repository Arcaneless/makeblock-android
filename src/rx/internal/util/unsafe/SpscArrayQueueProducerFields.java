package rx.internal.util.unsafe;

abstract class SpscArrayQueueProducerFields<E>
  extends SpscArrayQueueL1Pad<E>
{
  protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
  protected long producerIndex;
  protected long producerLookAhead;
  
  public SpscArrayQueueProducerFields(int paramInt)
  {
    super(paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscArrayQueueProducerFields.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */