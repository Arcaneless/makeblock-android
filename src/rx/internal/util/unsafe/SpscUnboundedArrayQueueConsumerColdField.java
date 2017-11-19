package rx.internal.util.unsafe;

abstract class SpscUnboundedArrayQueueConsumerColdField<E>
  extends SpscUnboundedArrayQueueL2Pad<E>
{
  protected E[] consumerBuffer;
  protected long consumerMask;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscUnboundedArrayQueueConsumerColdField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */