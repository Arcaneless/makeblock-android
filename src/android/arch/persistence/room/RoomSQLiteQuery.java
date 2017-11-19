package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteProgram;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.TreeMap;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class RoomSQLiteQuery
  implements SupportSQLiteQuery, SupportSQLiteProgram
{
  private static final int BLOB = 5;
  @VisibleForTesting
  static final int DESIRED_POOL_SIZE = 10;
  private static final int DOUBLE = 3;
  private static final int LONG = 2;
  private static final int NULL = 1;
  @VisibleForTesting
  static final int POOL_LIMIT = 15;
  private static final int STRING = 4;
  @VisibleForTesting
  static final TreeMap<Integer, RoomSQLiteQuery> sQueryPool = new TreeMap();
  @VisibleForTesting
  int mArgCount;
  private final int[] mBindingTypes;
  @VisibleForTesting
  final byte[][] mBlobBindings;
  @VisibleForTesting
  final int mCapacity;
  @VisibleForTesting
  final double[] mDoubleBindings;
  @VisibleForTesting
  final long[] mLongBindings;
  private volatile String mQuery;
  @VisibleForTesting
  final String[] mStringBindings;
  
  private RoomSQLiteQuery(int paramInt)
  {
    this.mCapacity = paramInt;
    paramInt += 1;
    this.mBindingTypes = new int[paramInt];
    this.mLongBindings = new long[paramInt];
    this.mDoubleBindings = new double[paramInt];
    this.mStringBindings = new String[paramInt];
    this.mBlobBindings = new byte[paramInt][];
  }
  
  public static RoomSQLiteQuery acquire(String paramString, int paramInt)
  {
    for (;;)
    {
      Object localObject2;
      synchronized (sQueryPool)
      {
        localObject2 = sQueryPool.ceilingEntry(Integer.valueOf(paramInt));
        break label77;
        sQueryPool.remove(((Map.Entry)localObject2).getKey());
        localObject2 = (RoomSQLiteQuery)((Map.Entry)localObject2).getValue();
        ((RoomSQLiteQuery)localObject2).init(paramString, paramInt);
        return (RoomSQLiteQuery)localObject2;
        ??? = new RoomSQLiteQuery(paramInt);
        ((RoomSQLiteQuery)???).init(paramString, paramInt);
        return (RoomSQLiteQuery)???;
      }
      label77:
      if (localObject2 == null) {}
    }
  }
  
  private static void prunePoolLocked()
  {
    if (sQueryPool.size() > 15)
    {
      int i = sQueryPool.size();
      Iterator localIterator = sQueryPool.descendingKeySet().iterator();
      i -= 10;
      while (i > 0)
      {
        localIterator.next();
        localIterator.remove();
        i -= 1;
      }
    }
  }
  
  public void bindBlob(int paramInt, byte[] paramArrayOfByte)
  {
    this.mBindingTypes[paramInt] = 5;
    this.mBlobBindings[paramInt] = paramArrayOfByte;
  }
  
  public void bindDouble(int paramInt, double paramDouble)
  {
    this.mBindingTypes[paramInt] = 3;
    this.mDoubleBindings[paramInt] = paramDouble;
  }
  
  public void bindLong(int paramInt, long paramLong)
  {
    this.mBindingTypes[paramInt] = 2;
    this.mLongBindings[paramInt] = paramLong;
  }
  
  public void bindNull(int paramInt)
  {
    this.mBindingTypes[paramInt] = 1;
  }
  
  public void bindString(int paramInt, String paramString)
  {
    this.mBindingTypes[paramInt] = 4;
    this.mStringBindings[paramInt] = paramString;
  }
  
  public void bindTo(SupportSQLiteProgram paramSupportSQLiteProgram)
  {
    int i = 1;
    if (i <= this.mArgCount)
    {
      switch (this.mBindingTypes[i])
      {
      }
      for (;;)
      {
        i += 1;
        break;
        paramSupportSQLiteProgram.bindNull(i);
        continue;
        paramSupportSQLiteProgram.bindLong(i, this.mLongBindings[i]);
        continue;
        paramSupportSQLiteProgram.bindDouble(i, this.mDoubleBindings[i]);
        continue;
        paramSupportSQLiteProgram.bindString(i, this.mStringBindings[i]);
        continue;
        paramSupportSQLiteProgram.bindBlob(i, this.mBlobBindings[i]);
      }
    }
  }
  
  public void clearBindings()
  {
    Arrays.fill(this.mBindingTypes, 1);
    Arrays.fill(this.mStringBindings, null);
    Arrays.fill(this.mBlobBindings, null);
    this.mQuery = null;
  }
  
  public void close()
    throws Exception
  {}
  
  public void copyArgumentsFrom(RoomSQLiteQuery paramRoomSQLiteQuery)
  {
    int i = paramRoomSQLiteQuery.getArgCount() + 1;
    System.arraycopy(paramRoomSQLiteQuery.mBindingTypes, 0, this.mBindingTypes, 0, i);
    System.arraycopy(paramRoomSQLiteQuery.mLongBindings, 0, this.mLongBindings, 0, i);
    System.arraycopy(paramRoomSQLiteQuery.mStringBindings, 0, this.mStringBindings, 0, i);
    System.arraycopy(paramRoomSQLiteQuery.mBlobBindings, 0, this.mBlobBindings, 0, i);
    System.arraycopy(paramRoomSQLiteQuery.mDoubleBindings, 0, this.mDoubleBindings, 0, i);
  }
  
  public int getArgCount()
  {
    return this.mArgCount;
  }
  
  public String getSql()
  {
    return this.mQuery;
  }
  
  void init(String paramString, int paramInt)
  {
    this.mQuery = paramString;
    this.mArgCount = paramInt;
  }
  
  public void release()
  {
    synchronized (sQueryPool)
    {
      sQueryPool.put(Integer.valueOf(this.mCapacity), this);
      prunePoolLocked();
      return;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\RoomSQLiteQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */