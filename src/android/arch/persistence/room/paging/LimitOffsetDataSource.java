package android.arch.persistence.room.paging;

import android.arch.paging.TiledDataSource;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.List;
import java.util.Set;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class LimitOffsetDataSource<T>
  extends TiledDataSource<T>
{
  private final String mCountQuery;
  private final RoomDatabase mDb;
  private final String mLimitOffsetQuery;
  private final InvalidationTracker.Observer mObserver;
  private final RoomSQLiteQuery mSourceQuery;
  
  protected LimitOffsetDataSource(RoomDatabase paramRoomDatabase, RoomSQLiteQuery paramRoomSQLiteQuery, String... paramVarArgs)
  {
    this.mDb = paramRoomDatabase;
    this.mSourceQuery = paramRoomSQLiteQuery;
    this.mCountQuery = ("SELECT COUNT(*) FROM ( " + this.mSourceQuery.getSql() + " )");
    this.mLimitOffsetQuery = ("SELECT * FROM ( " + this.mSourceQuery.getSql() + " ) LIMIT ? OFFSET ?");
    this.mObserver = new InvalidationTracker.Observer(paramVarArgs)
    {
      public void onInvalidated(@NonNull Set<String> paramAnonymousSet)
      {
        LimitOffsetDataSource.this.invalidate();
      }
    };
    paramRoomDatabase.getInvalidationTracker().addWeakObserver(this.mObserver);
  }
  
  protected abstract List<T> convertRows(Cursor paramCursor);
  
  public int countItems()
  {
    RoomSQLiteQuery localRoomSQLiteQuery = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
    localRoomSQLiteQuery.copyArgumentsFrom(this.mSourceQuery);
    Cursor localCursor = this.mDb.query(localRoomSQLiteQuery);
    try
    {
      if (localCursor.moveToFirst())
      {
        int i = localCursor.getInt(0);
        return i;
      }
      return 0;
    }
    finally
    {
      localCursor.close();
      localRoomSQLiteQuery.release();
    }
  }
  
  public boolean isInvalid()
  {
    this.mDb.getInvalidationTracker().refreshVersionsSync();
    return super.isInvalid();
  }
  
  @Nullable
  public List<T> loadRange(int paramInt1, int paramInt2)
  {
    RoomSQLiteQuery localRoomSQLiteQuery = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
    localRoomSQLiteQuery.copyArgumentsFrom(this.mSourceQuery);
    localRoomSQLiteQuery.bindLong(localRoomSQLiteQuery.getArgCount() - 1, paramInt2);
    localRoomSQLiteQuery.bindLong(localRoomSQLiteQuery.getArgCount(), paramInt1);
    Cursor localCursor = this.mDb.query(localRoomSQLiteQuery);
    try
    {
      List localList = convertRows(localCursor);
      return localList;
    }
    finally
    {
      localCursor.close();
      localRoomSQLiteQuery.release();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\paging\LimitOffsetDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */