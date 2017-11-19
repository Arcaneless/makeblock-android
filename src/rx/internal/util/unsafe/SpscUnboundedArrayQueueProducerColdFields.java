package rx.internal.util.unsafe;

abstract class SpscUnboundedArrayQueueProducerColdFields<E>
  extends SpscUnboundedArrayQueueProducerFields<E>
{
  protected E[] producerBuffer;
  protected long producerLookAhead;
  protected int producerLookAheadStep;
  protected long producerMask;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscUnboundedArrayQueueProducerColdFields.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */