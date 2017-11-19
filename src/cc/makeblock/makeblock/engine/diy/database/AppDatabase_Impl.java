package cc.makeblock.makeblock.engine.diy.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration.Builder;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AppDatabase_Impl
  extends AppDatabase
{
  private volatile DiyProjectDao _diyProjectDao;
  
  protected InvalidationTracker createInvalidationTracker()
  {
    return new InvalidationTracker(this, new String[] { "diy_project" });
  }
  
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration paramDatabaseConfiguration)
  {
    Object localObject = new RoomOpenHelper(paramDatabaseConfiguration, new RoomOpenHelper.Delegate()
    {
      public void createAllTables(SupportSQLiteDatabase paramAnonymousSupportSQLiteDatabase)
      {
        paramAnonymousSupportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `diy_project` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `project` TEXT)");
        paramAnonymousSupportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        paramAnonymousSupportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"d5f6d2236fa8182f76911a785dbf3034\")");
      }
      
      public void dropAllTables(SupportSQLiteDatabase paramAnonymousSupportSQLiteDatabase)
      {
        paramAnonymousSupportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `diy_project`");
      }
      
      protected void onCreate(SupportSQLiteDatabase paramAnonymousSupportSQLiteDatabase)
      {
        if (AppDatabase_Impl.this.mCallbacks != null)
        {
          int i = 0;
          int j = AppDatabase_Impl.this.mCallbacks.size();
          while (i < j)
          {
            ((RoomDatabase.Callback)AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(paramAnonymousSupportSQLiteDatabase);
            i += 1;
          }
        }
      }
      
      public void onOpen(SupportSQLiteDatabase paramAnonymousSupportSQLiteDatabase)
      {
        AppDatabase_Impl.access$302(AppDatabase_Impl.this, paramAnonymousSupportSQLiteDatabase);
        AppDatabase_Impl.this.internalInitInvalidationTracker(paramAnonymousSupportSQLiteDatabase);
        if (AppDatabase_Impl.this.mCallbacks != null)
        {
          int i = 0;
          int j = AppDatabase_Impl.this.mCallbacks.size();
          while (i < j)
          {
            ((RoomDatabase.Callback)AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(paramAnonymousSupportSQLiteDatabase);
            i += 1;
          }
        }
      }
      
      protected void validateMigration(SupportSQLiteDatabase paramAnonymousSupportSQLiteDatabase)
      {
        Object localObject = new HashMap(2);
        ((HashMap)localObject).put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        ((HashMap)localObject).put("project", new TableInfo.Column("project", "TEXT", false, 0));
        localObject = new TableInfo("diy_project", (Map)localObject, new HashSet(0));
        paramAnonymousSupportSQLiteDatabase = TableInfo.read(paramAnonymousSupportSQLiteDatabase, "diy_project");
        if (!((TableInfo)localObject).equals(paramAnonymousSupportSQLiteDatabase)) {
          throw new IllegalStateException("Migration didn't properly handle diy_project(cc.makeblock.makeblock.engine.diy.database.DiyProjectEntity).\n Expected:\n" + localObject + "\n Found:\n" + paramAnonymousSupportSQLiteDatabase);
        }
      }
    }, "d5f6d2236fa8182f76911a785dbf3034");
    localObject = SupportSQLiteOpenHelper.Configuration.builder(paramDatabaseConfiguration.context).name(paramDatabaseConfiguration.name).version(1).callback((SupportSQLiteOpenHelper.Callback)localObject).build();
    return paramDatabaseConfiguration.sqliteOpenHelperFactory.create((SupportSQLiteOpenHelper.Configuration)localObject);
  }
  
  public DiyProjectDao diyProjectDao()
  {
    if (this._diyProjectDao != null) {
      return this._diyProjectDao;
    }
    try
    {
      if (this._diyProjectDao == null) {
        this._diyProjectDao = new DiyProjectDao_Impl(this);
      }
      DiyProjectDao localDiyProjectDao = this._diyProjectDao;
      return localDiyProjectDao;
    }
    finally {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\database\AppDatabase_Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */