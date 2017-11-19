package android.arch.persistence.room;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.List;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class RoomOpenHelper
  extends SupportSQLiteOpenHelper.Callback
{
  @Nullable
  private DatabaseConfiguration mConfiguration;
  @NonNull
  private final Delegate mDelegate;
  @NonNull
  private final String mIdentityHash;
  
  public RoomOpenHelper(@NonNull DatabaseConfiguration paramDatabaseConfiguration, @NonNull Delegate paramDelegate, @NonNull String paramString)
  {
    this.mConfiguration = paramDatabaseConfiguration;
    this.mDelegate = paramDelegate;
    this.mIdentityHash = paramString;
  }
  
  private void checkIdentity(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    createMasterTableIfNotExists(paramSupportSQLiteDatabase);
    String str = "";
    Cursor localCursor = paramSupportSQLiteDatabase.query(new SimpleSQLiteQuery("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
    paramSupportSQLiteDatabase = str;
    try
    {
      if (localCursor.moveToFirst()) {
        paramSupportSQLiteDatabase = localCursor.getString(0);
      }
      localCursor.close();
      if (!this.mIdentityHash.equals(paramSupportSQLiteDatabase)) {
        throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
      }
    }
    finally
    {
      localCursor.close();
    }
  }
  
  private void createMasterTableIfNotExists(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    paramSupportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
  }
  
  private void updateIdentity(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    createMasterTableIfNotExists(paramSupportSQLiteDatabase);
    paramSupportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.mIdentityHash));
  }
  
  public void onConfigure(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    super.onConfigure(paramSupportSQLiteDatabase);
  }
  
  public void onCreate(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    updateIdentity(paramSupportSQLiteDatabase);
    this.mDelegate.createAllTables(paramSupportSQLiteDatabase);
    this.mDelegate.onCreate(paramSupportSQLiteDatabase);
  }
  
  public void onDowngrade(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt1, int paramInt2)
  {
    onUpgrade(paramSupportSQLiteDatabase, paramInt1, paramInt2);
  }
  
  public void onOpen(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    super.onOpen(paramSupportSQLiteDatabase);
    checkIdentity(paramSupportSQLiteDatabase);
    this.mDelegate.onOpen(paramSupportSQLiteDatabase);
    this.mConfiguration = null;
  }
  
  public void onUpgrade(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = j;
    if (this.mConfiguration != null)
    {
      Object localObject = this.mConfiguration.migrationContainer.findMigrationPath(paramInt1, paramInt2);
      i = j;
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          ((Migration)((Iterator)localObject).next()).migrate(paramSupportSQLiteDatabase);
        }
        this.mDelegate.validateMigration(paramSupportSQLiteDatabase);
        updateIdentity(paramSupportSQLiteDatabase);
        i = 1;
      }
    }
    if (i == 0)
    {
      if ((this.mConfiguration == null) || (this.mConfiguration.requireMigration)) {
        throw new IllegalStateException("A migration from " + paramInt1 + " to " + paramInt2 + " is necessary. Please provide a Migration in the builder or call" + " fallbackToDestructiveMigration in the builder in which case Room will" + " re-create all of the tables.");
      }
      this.mDelegate.dropAllTables(paramSupportSQLiteDatabase);
      this.mDelegate.createAllTables(paramSupportSQLiteDatabase);
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static abstract class Delegate
  {
    protected abstract void createAllTables(SupportSQLiteDatabase paramSupportSQLiteDatabase);
    
    protected abstract void dropAllTables(SupportSQLiteDatabase paramSupportSQLiteDatabase);
    
    protected abstract void onCreate(SupportSQLiteDatabase paramSupportSQLiteDatabase);
    
    protected abstract void onOpen(SupportSQLiteDatabase paramSupportSQLiteDatabase);
    
    protected abstract void validateMigration(SupportSQLiteDatabase paramSupportSQLiteDatabase);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\RoomOpenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */