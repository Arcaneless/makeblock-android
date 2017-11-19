package cc.makeblock.makeblock.engine.diy.database;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiyProjectDao_Impl
  implements DiyProjectDao
{
  private final RoomDatabase __db;
  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDiyProjectEntity;
  private final EntityInsertionAdapter __insertionAdapterOfDiyProjectEntity;
  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDiyProjectEntity;
  
  public DiyProjectDao_Impl(RoomDatabase paramRoomDatabase)
  {
    this.__db = paramRoomDatabase;
    this.__insertionAdapterOfDiyProjectEntity = new EntityInsertionAdapter(paramRoomDatabase)
    {
      public void bind(SupportSQLiteStatement paramAnonymousSupportSQLiteStatement, DiyProjectEntity paramAnonymousDiyProjectEntity)
      {
        paramAnonymousSupportSQLiteStatement.bindLong(1, paramAnonymousDiyProjectEntity.id);
        if (paramAnonymousDiyProjectEntity.project == null)
        {
          paramAnonymousSupportSQLiteStatement.bindNull(2);
          return;
        }
        paramAnonymousSupportSQLiteStatement.bindString(2, paramAnonymousDiyProjectEntity.project);
      }
      
      public String createQuery()
      {
        return "INSERT OR ABORT INTO `diy_project`(`id`,`project`) VALUES (nullif(?, 0),?)";
      }
    };
    this.__deletionAdapterOfDiyProjectEntity = new EntityDeletionOrUpdateAdapter(paramRoomDatabase)
    {
      public void bind(SupportSQLiteStatement paramAnonymousSupportSQLiteStatement, DiyProjectEntity paramAnonymousDiyProjectEntity)
      {
        paramAnonymousSupportSQLiteStatement.bindLong(1, paramAnonymousDiyProjectEntity.id);
      }
      
      public String createQuery()
      {
        return "DELETE FROM `diy_project` WHERE `id` = ?";
      }
    };
    this.__updateAdapterOfDiyProjectEntity = new EntityDeletionOrUpdateAdapter(paramRoomDatabase)
    {
      public void bind(SupportSQLiteStatement paramAnonymousSupportSQLiteStatement, DiyProjectEntity paramAnonymousDiyProjectEntity)
      {
        paramAnonymousSupportSQLiteStatement.bindLong(1, paramAnonymousDiyProjectEntity.id);
        if (paramAnonymousDiyProjectEntity.project == null) {
          paramAnonymousSupportSQLiteStatement.bindNull(2);
        }
        for (;;)
        {
          paramAnonymousSupportSQLiteStatement.bindLong(3, paramAnonymousDiyProjectEntity.id);
          return;
          paramAnonymousSupportSQLiteStatement.bindString(2, paramAnonymousDiyProjectEntity.project);
        }
      }
      
      public String createQuery()
      {
        return "UPDATE OR ABORT `diy_project` SET `id` = ?,`project` = ? WHERE `id` = ?";
      }
    };
  }
  
  public void delete(DiyProjectEntity paramDiyProjectEntity)
  {
    this.__db.beginTransaction();
    try
    {
      this.__deletionAdapterOfDiyProjectEntity.handle(paramDiyProjectEntity);
      this.__db.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.__db.endTransaction();
    }
  }
  
  public List<DiyProjectEntity> getAll()
  {
    RoomSQLiteQuery localRoomSQLiteQuery = RoomSQLiteQuery.acquire("SELECT * FROM diy_project ORDER BY id DESC", 0);
    Cursor localCursor = this.__db.query(localRoomSQLiteQuery);
    try
    {
      int i = localCursor.getColumnIndexOrThrow("id");
      int j = localCursor.getColumnIndexOrThrow("project");
      ArrayList localArrayList = new ArrayList(localCursor.getCount());
      while (localCursor.moveToNext())
      {
        DiyProjectEntity localDiyProjectEntity = new DiyProjectEntity();
        localDiyProjectEntity.id = localCursor.getInt(i);
        localDiyProjectEntity.project = localCursor.getString(j);
        localArrayList.add(localDiyProjectEntity);
      }
      localCursor.close();
    }
    finally
    {
      localCursor.close();
      localRoomSQLiteQuery.release();
    }
    localRoomSQLiteQuery.release();
    return localList;
  }
  
  public LiveData<List<DiyProjectEntity>> getAllAsync()
  {
    new ComputableLiveData()
    {
      private InvalidationTracker.Observer _observer;
      
      protected List<DiyProjectEntity> compute()
      {
        if (this._observer == null)
        {
          this._observer = new InvalidationTracker.Observer("diy_project", new String[0])
          {
            public void onInvalidated(@NonNull Set<String> paramAnonymous2Set)
            {
              DiyProjectDao_Impl.4.this.invalidate();
            }
          };
          DiyProjectDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
        }
        Cursor localCursor = DiyProjectDao_Impl.this.__db.query(this.val$_statement);
        try
        {
          int i = localCursor.getColumnIndexOrThrow("id");
          int j = localCursor.getColumnIndexOrThrow("project");
          ArrayList localArrayList = new ArrayList(localCursor.getCount());
          while (localCursor.moveToNext())
          {
            DiyProjectEntity localDiyProjectEntity = new DiyProjectEntity();
            localDiyProjectEntity.id = localCursor.getInt(i);
            localDiyProjectEntity.project = localCursor.getString(j);
            localArrayList.add(localDiyProjectEntity);
          }
        }
        finally
        {
          localCursor.close();
        }
        return localList;
      }
      
      protected void finalize()
      {
        this.val$_statement.release();
      }
    }.getLiveData();
  }
  
  public void insert(DiyProjectEntity... paramVarArgs)
  {
    this.__db.beginTransaction();
    try
    {
      this.__insertionAdapterOfDiyProjectEntity.insert(paramVarArgs);
      this.__db.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.__db.endTransaction();
    }
  }
  
  public List<DiyProjectEntity> loadAllByIds(int[] paramArrayOfInt)
  {
    Object localObject = StringUtil.newStringBuilder();
    ((StringBuilder)localObject).append("SELECT * FROM diy_project WHERE id IN (");
    int i = paramArrayOfInt.length;
    StringUtil.appendPlaceholders((StringBuilder)localObject, i);
    ((StringBuilder)localObject).append(")");
    localObject = RoomSQLiteQuery.acquire(((StringBuilder)localObject).toString(), i + 0);
    int j = 1;
    int k = paramArrayOfInt.length;
    i = 0;
    while (i < k)
    {
      ((RoomSQLiteQuery)localObject).bindLong(j, paramArrayOfInt[i]);
      j += 1;
      i += 1;
    }
    paramArrayOfInt = this.__db.query((SupportSQLiteQuery)localObject);
    try
    {
      i = paramArrayOfInt.getColumnIndexOrThrow("id");
      j = paramArrayOfInt.getColumnIndexOrThrow("project");
      ArrayList localArrayList = new ArrayList(paramArrayOfInt.getCount());
      while (paramArrayOfInt.moveToNext())
      {
        DiyProjectEntity localDiyProjectEntity = new DiyProjectEntity();
        localDiyProjectEntity.id = paramArrayOfInt.getInt(i);
        localDiyProjectEntity.project = paramArrayOfInt.getString(j);
        localArrayList.add(localDiyProjectEntity);
      }
      paramArrayOfInt.close();
    }
    finally
    {
      paramArrayOfInt.close();
      ((RoomSQLiteQuery)localObject).release();
    }
    ((RoomSQLiteQuery)localObject).release();
    return localList;
  }
  
  public int update(DiyProjectEntity... paramVarArgs)
  {
    this.__db.beginTransaction();
    try
    {
      int i = this.__updateAdapterOfDiyProjectEntity.handleMultiple(paramVarArgs);
      this.__db.setTransactionSuccessful();
      return 0 + i;
    }
    finally
    {
      this.__db.endTransaction();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\database\DiyProjectDao_Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */