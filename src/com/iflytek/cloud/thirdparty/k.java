package com.iflytek.cloud.thirdparty;

import java.util.LinkedList;
import java.util.Map.Entry;

public class k
{
  private final LinkedList<a> a = new LinkedList();
  private final LinkedList<a> b = new LinkedList();
  private int c = 512;
  private long d = 5120L;
  private long e = -1L;
  private long f = 0L;
  private long g = 0L;
  private long h = 0L;
  private boolean i = false;
  private boolean j = true;
  private long k = 0L;
  private final Object l = new Object();
  
  public k(long paramLong1, int paramInt, long paramLong2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.e = paramLong1;
    this.d = paramLong2;
    this.c = paramInt;
    this.i = paramBoolean1;
    this.j = paramBoolean2;
    this.k = (this.e + this.c * 2);
    if (!this.i) {
      k();
    }
  }
  
  private a h()
    throws OutOfMemoryError
  {
    if (!this.b.isEmpty()) {}
    for (Object localObject1 = (a)this.b.remove();; localObject1 = null)
    {
      Object localObject2 = localObject1;
      int m;
      if (localObject1 == null)
      {
        if ((!this.i) && (i())) {
          break label89;
        }
        if (this.i) {
          break label131;
        }
        m = this.c;
        localObject1 = new byte[this.c];
        this.f += this.c;
      }
      for (;;)
      {
        localObject2 = new a((byte[])localObject1, Integer.valueOf(m));
        return (a)localObject2;
        label89:
        throw new OutOfMemoryError("current buffer len=" + this.f + ", has match max len: " + this.e);
        label131:
        m = 0;
        localObject1 = null;
      }
    }
  }
  
  private boolean i()
  {
    return (-1L != this.e) && (this.k <= this.f);
  }
  
  private void j()
    throws OutOfMemoryError
  {
    a locala = (a)this.a.peek();
    if (locala != null)
    {
      long l1 = this.g + ((Integer)locala.getValue()).intValue();
      if (a() <= this.h - l1)
      {
        locala = (a)this.a.remove();
        if (this.i) {
          locala.a();
        }
        this.b.add(locala);
        this.g = l1;
      }
    }
  }
  
  private void k()
  {
    if (0L < this.d)
    {
      int n = (int)(this.d / this.c + 2L);
      int m = 0;
      while (m < n)
      {
        a locala = new a(new byte[this.c], Integer.valueOf(this.c));
        this.b.add(locala);
        this.f += this.c;
        m += 1;
      }
    }
  }
  
  public long a()
  {
    synchronized (this.l)
    {
      long l1 = this.e;
      return l1;
    }
  }
  
  public void a(long paramLong)
  {
    synchronized (this.l)
    {
      this.e += paramLong;
      this.k = (this.e + this.c * 2);
      return;
    }
  }
  
  /* Error */
  public void a(a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 62	com/iflytek/cloud/thirdparty/k:l	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnull +23 -> 31
    //   11: aload_0
    //   12: getfield 56	com/iflytek/cloud/thirdparty/k:i	Z
    //   15: ifeq +19 -> 34
    //   18: aload_1
    //   19: invokevirtual 124	com/iflytek/cloud/thirdparty/k$a:a	()V
    //   22: aload_0
    //   23: getfield 38	com/iflytek/cloud/thirdparty/k:b	Ljava/util/LinkedList;
    //   26: aload_1
    //   27: invokevirtual 128	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   30: pop
    //   31: aload_2
    //   32: monitorexit
    //   33: return
    //   34: aload_0
    //   35: getfield 50	com/iflytek/cloud/thirdparty/k:f	J
    //   38: aload_0
    //   39: getfield 48	com/iflytek/cloud/thirdparty/k:e	J
    //   42: lcmp
    //   43: ifge -12 -> 31
    //   46: aload_1
    //   47: invokevirtual 135	com/iflytek/cloud/thirdparty/k$a:getKey	()Ljava/lang/Object;
    //   50: ifnull -19 -> 31
    //   53: aload_0
    //   54: getfield 38	com/iflytek/cloud/thirdparty/k:b	Ljava/util/LinkedList;
    //   57: aload_1
    //   58: invokevirtual 128	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   61: pop
    //   62: aload_0
    //   63: aload_0
    //   64: getfield 50	com/iflytek/cloud/thirdparty/k:f	J
    //   67: aload_1
    //   68: invokevirtual 135	com/iflytek/cloud/thirdparty/k$a:getKey	()Ljava/lang/Object;
    //   71: checkcast 137	[B
    //   74: arraylength
    //   75: i2l
    //   76: ladd
    //   77: putfield 50	com/iflytek/cloud/thirdparty/k:f	J
    //   80: goto -49 -> 31
    //   83: astore_1
    //   84: aload_2
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	k
    //   0	88	1	parama	a
    //   4	81	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	31	83	finally
    //   31	33	83	finally
    //   34	80	83	finally
    //   84	86	83	finally
  }
  
  public void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IllegalArgumentException, NullPointerException, OutOfMemoryError
  {
    int i2 = paramInt1 + paramInt2;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i2) || (paramInt2 < 0) || ((this.i) && (paramInt1 > 0))) {
      throw new IllegalArgumentException();
    }
    synchronized (this.l)
    {
      if ((0L >= this.e) || (paramInt2 <= 0)) {
        break label379;
      }
      if ((!this.j) && (this.h - this.g + paramInt2 > this.e)) {
        throw new OutOfMemoryError("current start=" + this.g + ", current end=" + this.h + ", data len=" + paramInt2 + ", has over max len=" + this.e);
      }
    }
    a locala = null;
    if (!this.i) {
      locala = (a)this.a.peekLast();
    }
    int m;
    int n;
    if ((locala == null) || (((byte[])locala.getKey()).length == ((Integer)locala.getValue()).intValue()))
    {
      locala = h();
      m = 0;
      if (paramInt1 >= i2) {
        break label379;
      }
      if (!this.i) {
        break label341;
      }
      locala.a(paramArrayOfByte);
      n = paramInt2;
    }
    for (;;)
    {
      int i1 = paramInt1 + n;
      locala.setValue(Integer.valueOf(m + n));
      this.h += n;
      if (!locala.equals(this.a.peekLast())) {
        this.a.add(locala);
      }
      j();
      paramInt1 = i1;
      if (i1 >= i2) {
        break;
      }
      locala = h();
      m = 0;
      paramInt1 = i1;
      break;
      m = ((Integer)locala.getValue()).intValue();
      break;
      label341:
      n = Math.min(i2 - paramInt1, ((byte[])locala.getKey()).length - m);
      System.arraycopy(paramArrayOfByte, paramInt1, locala.getKey(), m, n);
    }
    label379:
  }
  
  public long b()
  {
    synchronized (this.l)
    {
      long l1 = this.h;
      long l2 = this.g;
      return l1 - l2;
    }
  }
  
  public void c()
  {
    synchronized (this.l)
    {
      this.g = 0L;
      this.h = 0L;
      if (!this.a.isEmpty())
      {
        a locala = (a)this.a.pop();
        if (this.i) {
          locala.a();
        }
        this.b.add(locala);
      }
    }
  }
  
  public boolean d()
  {
    synchronized (this.l)
    {
      boolean bool = this.a.isEmpty();
      return bool;
    }
  }
  
  public a e()
  {
    Object localObject1 = null;
    synchronized (this.l)
    {
      if (!this.a.isEmpty())
      {
        a locala = (a)this.a.pop();
        this.g += ((Integer)locala.getValue()).intValue();
        localObject1 = locala;
        if (!this.i)
        {
          localObject1 = locala;
          if (locala.getKey() != null)
          {
            this.f -= ((byte[])locala.getKey()).length;
            localObject1 = locala;
          }
        }
      }
      return (a)localObject1;
    }
  }
  
  public long f()
  {
    synchronized (this.l)
    {
      long l1 = this.b.size() * this.c;
      return l1;
    }
  }
  
  public void g()
  {
    int m = 0;
    synchronized (this.l)
    {
      while ((this.d < this.f) && (!this.b.isEmpty()))
      {
        this.b.remove();
        this.f -= this.c;
        m = 1;
      }
      if (m != 0) {
        System.gc();
      }
      return;
    }
  }
  
  public static class a
    extends k.b<byte[], Integer>
  {
    public a(byte[] paramArrayOfByte, Integer paramInteger)
    {
      super(paramInteger);
    }
  }
  
  private static class b<K, V>
    implements Map.Entry<K, V>
  {
    private K a = null;
    private V b = null;
    
    public b(K paramK, V paramV)
    {
      this.a = paramK;
      this.b = paramV;
    }
    
    public K a(K paramK)
    {
      this.a = paramK;
      return paramK;
    }
    
    public void a()
    {
      this.a = null;
      this.b = null;
    }
    
    public K getKey()
    {
      return (K)this.a;
    }
    
    public V getValue()
    {
      return (V)this.b;
    }
    
    public V setValue(V paramV)
    {
      Object localObject = this.b;
      this.b = paramV;
      return (V)localObject;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */