package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.support.annotation.RestrictTo;
import java.util.Iterator;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class EntityDeletionOrUpdateAdapter<T>
  extends SharedSQLiteStatement
{
  public EntityDeletionOrUpdateAdapter(RoomDatabase paramRoomDatabase)
  {
    super(paramRoomDatabase);
  }
  
  protected abstract void bind(SupportSQLiteStatement paramSupportSQLiteStatement, T paramT);
  
  protected abstract String createQuery();
  
  public final int handle(T paramT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    try
    {
      bind(localSupportSQLiteStatement, paramT);
      int i = localSupportSQLiteStatement.executeUpdateDelete();
      return i;
    }
    finally
    {
      release(localSupportSQLiteStatement);
    }
  }
  
  public final int handleMultiple(Iterable<T> paramIterable)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    int i = 0;
    try
    {
      paramIterable = paramIterable.iterator();
      while (paramIterable.hasNext())
      {
        bind(localSupportSQLiteStatement, paramIterable.next());
        int j = localSupportSQLiteStatement.executeUpdateDelete();
        i += j;
      }
      return i;
    }
    finally
    {
      release(localSupportSQLiteStatement);
    }
  }
  
  public final int handleMultiple(T[] paramArrayOfT)
  {
    SupportSQLiteStatement localSupportSQLiteStatement = acquire();
    int i = 0;
    for (;;)
    {
      int k;
      int j;
      Object localObject;
      try
      {
        k = paramArrayOfT.length;
        j = 0;
      }
      finally
      {
        int m;
        release(localSupportSQLiteStatement);
      }
      bind(localSupportSQLiteStatement, localObject);
      m = localSupportSQLiteStatement.executeUpdateDelete();
      i += m;
      j += 1;
      break label63;
      release(localSupportSQLiteStatement);
      return i;
      label63:
      if (j < k) {
        localObject = paramArrayOfT[j];
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\EntityDeletionOrUpdateAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */