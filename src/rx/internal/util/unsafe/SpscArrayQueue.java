package rx.internal.util.unsafe;

import sun.misc.Unsafe;

public final class SpscArrayQueue<E>
  extends SpscArrayQueueL3Pad<E>
{
  public SpscArrayQueue(int paramInt)
  {
    super(paramInt);
  }
  
  private long lvConsumerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
  }
  
  private long lvProducerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
  }
  
  private void soConsumerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, paramLong);
  }
  
  private void soProducerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, paramLong);
  }
  
  public boolean isEmpty()
  {
    return lvProducerIndex() == lvConsumerIndex();
  }
  
  public boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new NullPointerException("null elements not allowed");
    }
    Object[] arrayOfObject = this.buffer;
    long l1 = this.producerIndex;
    long l2 = calcElementOffset(l1);
    if (lvElement(arrayOfObject, l2) != null) {
      return false;
    }
    soElement(arrayOfObject, l2, paramE);
    soProducerIndex(1L + l1);
    return true;
  }
  
  public E peek()
  {
    return (E)lvElement(calcElementOffset(this.consumerIndex));
  }
  
  public E poll()
  {
    long l1 = this.consumerIndex;
    long l2 = calcElementOffset(l1);
    Object[] arrayOfObject = this.buffer;
    Object localObject = lvElement(arrayOfObject, l2);
    if (localObject == null) {
      return null;
    }
    soElement(arrayOfObject, l2, null);
    soConsumerIndex(1L + l1);
    return (E)localObject;
  }
  
  public int size()
  {
    long l1 = lvConsumerIndex();
    long l2;
    long l4;
    long l3;
    do
    {
      l2 = l1;
      l4 = lvProducerIndex();
      l3 = lvConsumerIndex();
      l1 = l3;
    } while (l2 != l3);
    return (int)(l4 - l3);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\SpscArrayQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */