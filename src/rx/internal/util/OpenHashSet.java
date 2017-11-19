package rx.internal.util;

import java.util.Arrays;
import rx.functions.Action1;
import rx.internal.util.unsafe.Pow2;

public final class OpenHashSet<T>
{
  private static final int INT_PHI = -1640531527;
  T[] keys;
  final float loadFactor;
  int mask;
  int maxSize;
  int size;
  
  public OpenHashSet()
  {
    this(16, 0.75F);
  }
  
  public OpenHashSet(int paramInt)
  {
    this(paramInt, 0.75F);
  }
  
  public OpenHashSet(int paramInt, float paramFloat)
  {
    this.loadFactor = paramFloat;
    paramInt = Pow2.roundToPowerOfTwo(paramInt);
    this.mask = (paramInt - 1);
    this.maxSize = ((int)(paramInt * paramFloat));
    this.keys = ((Object[])new Object[paramInt]);
  }
  
  static int mix(int paramInt)
  {
    paramInt *= -1640531527;
    return paramInt >>> 16 ^ paramInt;
  }
  
  public boolean add(T paramT)
  {
    Object[] arrayOfObject = this.keys;
    int k = this.mask;
    int i = mix(paramT.hashCode()) & k;
    Object localObject = arrayOfObject[i];
    int j = i;
    if (localObject != null) {
      if (localObject.equals(paramT)) {
        return false;
      }
    }
    do
    {
      i = i + 1 & k;
      localObject = arrayOfObject[i];
      if (localObject == null)
      {
        j = i;
        arrayOfObject[j] = paramT;
        i = this.size + 1;
        this.size = i;
        if (i >= this.maxSize) {
          rehash();
        }
        return true;
      }
    } while (!localObject.equals(paramT));
    return false;
  }
  
  public void clear(Action1<? super T> paramAction1)
  {
    if (this.size == 0) {
      return;
    }
    Object[] arrayOfObject = this.keys;
    int j = arrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfObject[i];
      if (localObject != null) {
        paramAction1.call(localObject);
      }
      i += 1;
    }
    Arrays.fill(arrayOfObject, null);
    this.size = 0;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  void rehash()
  {
    Object[] arrayOfObject1 = this.keys;
    int i = arrayOfObject1.length;
    int n = i << 1;
    int i1 = n - 1;
    Object[] arrayOfObject2 = (Object[])new Object[n];
    int j = this.size;
    while (j != 0)
    {
      int k = i;
      do
      {
        i = k - 1;
        k = i;
      } while (arrayOfObject1[i] == null);
      k = mix(arrayOfObject1[i].hashCode()) & i1;
      int m = k;
      if (arrayOfObject2[k] != null) {
        do
        {
          m = k + 1 & i1;
          k = m;
        } while (arrayOfObject2[m] != null);
      }
      arrayOfObject2[m] = arrayOfObject1[i];
      j -= 1;
    }
    this.mask = i1;
    this.maxSize = ((int)(n * this.loadFactor));
    this.keys = arrayOfObject2;
  }
  
  public boolean remove(T paramT)
  {
    Object[] arrayOfObject = this.keys;
    int k = this.mask;
    int j = mix(paramT.hashCode()) & k;
    Object localObject = arrayOfObject[j];
    if (localObject == null) {
      return false;
    }
    int i = j;
    if (localObject.equals(paramT)) {
      return removeEntry(j, arrayOfObject, k);
    }
    do
    {
      j = i + 1 & k;
      localObject = arrayOfObject[j];
      if (localObject == null) {
        break;
      }
      i = j;
    } while (!localObject.equals(paramT));
    return removeEntry(j, arrayOfObject, k);
  }
  
  boolean removeEntry(int paramInt1, T[] paramArrayOfT, int paramInt2)
  {
    this.size -= 1;
    int i = paramInt1;
    for (paramInt1 = i + 1 & paramInt2;; paramInt1 = paramInt1 + 1 & paramInt2)
    {
      T ? = paramArrayOfT[paramInt1];
      if (? == null)
      {
        paramArrayOfT[i] = null;
        return true;
      }
      int j = mix(?.hashCode()) & paramInt2;
      if (i <= paramInt1)
      {
        if ((i < j) && (j <= paramInt1)) {}
      }
      else {
        while ((i >= j) && (j > paramInt1))
        {
          paramArrayOfT[i] = ?;
          break;
        }
      }
    }
  }
  
  public void terminate()
  {
    this.size = 0;
    this.keys = ((Object[])new Object[0]);
  }
  
  public T[] values()
  {
    return this.keys;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\OpenHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */