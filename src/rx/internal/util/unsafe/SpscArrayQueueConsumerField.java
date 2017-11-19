package rx.internal.util.unsafe;

abstract class SpscArrayQueueConsumerField<E>
  extends SpscArrayQueueL2Pad<E>
{
  protected static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueConsumerField.class, "consumerIndex");
  protected long consumerIndex;
  
  public SpscArrayQueueConsumerField(int paramInt)
  {
    super(paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscArrayQueueConsumerField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */