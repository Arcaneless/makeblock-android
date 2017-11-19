package rx.internal.util.atomic;

public final class MpscLinkedAtomicQueue<E>
  extends BaseLinkedAtomicQueue<E>
{
  public MpscLinkedAtomicQueue()
  {
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode();
    spConsumerNode(localLinkedQueueNode);
    xchgProducerNode(localLinkedQueueNode);
  }
  
  public final boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new NullPointerException("null elements not allowed");
    }
    paramE = new LinkedQueueNode(paramE);
    xchgProducerNode(paramE).soNext(paramE);
    return true;
  }
  
  public final E peek()
  {
    LinkedQueueNode localLinkedQueueNode1 = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
    if (localLinkedQueueNode2 != null) {
      return (E)localLinkedQueueNode2.lpValue();
    }
    if (localLinkedQueueNode1 != lvProducerNode())
    {
      do
      {
        localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
      } while (localLinkedQueueNode2 == null);
      return (E)localLinkedQueueNode2.lpValue();
    }
    return null;
  }
  
  public final E poll()
  {
    Object localObject = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode = ((LinkedQueueNode)localObject).lvNext();
    if (localLinkedQueueNode != null)
    {
      localObject = localLinkedQueueNode.getAndNullValue();
      spConsumerNode(localLinkedQueueNode);
      return (E)localObject;
    }
    if (localObject != lvProducerNode())
    {
      do
      {
        localLinkedQueueNode = ((LinkedQueueNode)localObject).lvNext();
      } while (localLinkedQueueNode == null);
      localObject = localLinkedQueueNode.getAndNullValue();
      spConsumerNode(localLinkedQueueNode);
      return (E)localObject;
    }
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\atomic\MpscLinkedAtomicQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */