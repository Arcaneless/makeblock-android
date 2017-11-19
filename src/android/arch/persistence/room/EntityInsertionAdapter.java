package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.support.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class EntityInsertionAdapter<T>
  extends SharedSQLiteStatement
{
  public EntityInsertionAdapter(RoomDatabase paramRoomDatabase)
  {
    super(paramRoomDatabase);
  }
  
  protected abstract void bind(SupportSQLiteStatement paramSupportSQLiteStatement, T paramT);
  
  public final void insert(Iterable<T> paramIterable)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      paramIterable = paramIterable.iterator();
      while (paramIterable.hasNext())
      {
        bind(localSupportSQLiteStatement, paramIterable.next());
        localSupportSQLiteStatement.executeInsert();
      }
    }
    finally
    {
      release(localSupportSQLiteStatement);
    }
  }
  
  public final void insert(T paramT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      bind(localSupportSQLiteStatement, paramT);
      localSupportSQLiteStatement.executeInsert();
      return;
    }
    finally
    {
      release(localSupportSQLiteStatement);
    }
  }
  
  public final void insert(T[] paramArrayOfT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    for (;;)
    {
      int j;
      int i;
      Object localObject;
      try
      {
        j = paramArrayOfT.length;
        i = 0;
      }
      finally
      {
        release(localSupportSQLiteStatement);
      }
      bind(localSupportSQLiteStatement, localObject);
      localSupportSQLiteStatement.executeInsert();
      i += 1;
      break label53;
      release(localSupportSQLiteStatement);
      return;
      label53:
      if (i < j) {
        localObject = paramArrayOfT[i];
      }
    }
  }
  
  public final long insertAndReturnId(T paramT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      bind(localSupportSQLiteStatement, paramT);
      long l = localSupportSQLiteStatement.executeInsert();
      return l;
    }
    finally
    {
      release(localSupportSQLiteStatement);
    }
  }
  
  public final long[] insertAndReturnIdsArray(Collection<T> paramCollection)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    for (;;)
    {
      int i;
      try
      {
        arrayOfLong = new long[paramCollection.size()];
      }
      finally
      {
        long[] arrayOfLong;
        release(localSupportSQLiteStatement);
      }
      paramCollection = paramCollection.iterator();
      if (paramCollection.hasNext())
      {
        bind(localSupportSQLiteStatement, paramCollection.next());
        arrayOfLong[i] = localSupportSQLiteStatement.executeInsert();
        i += 1;
      }
      else
      {
        release(localSupportSQLiteStatement);
        return arrayOfLong;
        i = 0;
      }
    }
  }
  
  public final long[] insertAndReturnIdsArray(T[] paramArrayOfT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      arrayOfLong = new long[paramArrayOfT.length];
    }
    finally
    {
      long[] arrayOfLong;
      release(localSupportSQLiteStatement);
    }
    int k = paramArrayOfT.length;
    int j = 0;
    for (;;)
    {
      bind(localSupportSQLiteStatement, localObject);
      int i;
      arrayOfLong[i] = localSupportSQLiteStatement.executeInsert();
      i += 1;
      j += 1;
      while (j >= k)
      {
        release(localSupportSQLiteStatement);
        return arrayOfLong;
        i = 0;
        break;
      }
      Object localObject = paramArrayOfT[j];
    }
  }
  
  public final Long[] insertAndReturnIdsArrayBox(Collection<T> paramCollection)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    for (;;)
    {
      int i;
      try
      {
        arrayOfLong = new Long[paramCollection.size()];
      }
      finally
      {
        Long[] arrayOfLong;
        release(localSupportSQLiteStatement);
      }
      paramCollection = paramCollection.iterator();
      if (paramCollection.hasNext())
      {
        bind(localSupportSQLiteStatement, paramCollection.next());
        arrayOfLong[i] = Long.valueOf(localSupportSQLiteStatement.executeInsert());
        i += 1;
      }
      else
      {
        release(localSupportSQLiteStatement);
        return arrayOfLong;
        i = 0;
      }
    }
  }
  
  public final Long[] insertAndReturnIdsArrayBox(T[] paramArrayOfT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      arrayOfLong = new Long[paramArrayOfT.length];
    }
    finally
    {
      Long[] arrayOfLong;
      release(localSupportSQLiteStatement);
    }
    int k = paramArrayOfT.length;
    int j = 0;
    for (;;)
    {
      bind(localSupportSQLiteStatement, localObject);
      int i;
      arrayOfLong[i] = Long.valueOf(localSupportSQLiteStatement.executeInsert());
      i += 1;
      j += 1;
      while (j >= k)
      {
        release(localSupportSQLiteStatement);
        return arrayOfLong;
        i = 0;
        break;
      }
      Object localObject = paramArrayOfT[j];
    }
  }
  
  public final List<Long> insertAndReturnIdsList(Collection<T> paramCollection)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    for (;;)
    {
      int i;
      try
      {
        localArrayList = new ArrayList(paramCollection.size());
      }
      finally
      {
        ArrayList localArrayList;
        release(localSupportSQLiteStatement);
      }
      paramCollection = paramCollection.iterator();
      if (paramCollection.hasNext())
      {
        bind(localSupportSQLiteStatement, paramCollection.next());
        localArrayList.add(i, Long.valueOf(localSupportSQLiteStatement.executeInsert()));
        i += 1;
      }
      else
      {
        release(localSupportSQLiteStatement);
        return localArrayList;
        i = 0;
      }
    }
  }
  
  public final List<Long> insertAndReturnIdsList(T[] paramArrayOfT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      localArrayList = new ArrayList(paramArrayOfT.length);
    }
    finally
    {
      ArrayList localArrayList;
      release(localSupportSQLiteStatement);
    }
    int k = paramArrayOfT.length;
    int j = 0;
    for (;;)
    {
      bind(localSupportSQLiteStatement, localObject);
      int i;
      localArrayList.add(i, Long.valueOf(localSupportSQLiteStatement.executeInsert()));
      i += 1;
      j += 1;
      while (j >= k)
      {
        release(localSupportSQLiteStatement);
        return localArrayList;
        i = 0;
        break;
      }
      Object localObject = paramArrayOfT[j];
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\EntityInsertionAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */