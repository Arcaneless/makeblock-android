package rx.internal.util.unsafe;

import java.util.AbstractQueue;

abstract class SpscUnboundedArrayQueueProducerFields<E>
  extends AbstractQueue<E>
{
  protected long producerIndex;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscUnboundedArrayQueueProducerFields.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */