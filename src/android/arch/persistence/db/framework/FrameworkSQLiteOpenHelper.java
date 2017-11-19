package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RequiresApi;

class FrameworkSQLiteOpenHelper
  implements SupportSQLiteOpenHelper
{
  private final OpenHelper mDelegate;
  
  FrameworkSQLiteOpenHelper(Context paramContext, String paramString, int paramInt, DatabaseErrorHandler paramDatabaseErrorHandler, SupportSQLiteOpenHelper.Callback paramCallback)
  {
    this.mDelegate = createDelegate(paramContext, paramString, paramInt, paramDatabaseErrorHandler, paramCallback);
  }
  
  private OpenHelper createDelegate(Context paramContext, String paramString, int paramInt, DatabaseErrorHandler paramDatabaseErrorHandler, final SupportSQLiteOpenHelper.Callback paramCallback)
  {
    new OpenHelper(paramContext, paramString, null, paramInt, paramDatabaseErrorHandler)
    {
      public void onConfigure(SQLiteDatabase paramAnonymousSQLiteDatabase)
      {
        paramCallback.onConfigure(getWrappedDb(paramAnonymousSQLiteDatabase));
      }
      
      public void onCreate(SQLiteDatabase paramAnonymousSQLiteDatabase)
      {
        this.mWrappedDb = new FrameworkSQLiteDatabase(paramAnonymousSQLiteDatabase);
        paramCallback.onCreate(this.mWrappedDb);
      }
      
      public void onDowngrade(SQLiteDatabase paramAnonymousSQLiteDatabase, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        paramCallback.onDowngrade(getWrappedDb(paramAnonymousSQLiteDatabase), paramAnonymousInt1, paramAnonymousInt2);
      }
      
      public void onOpen(SQLiteDatabase paramAnonymousSQLiteDatabase)
      {
        paramCallback.onOpen(getWrappedDb(paramAnonymousSQLiteDatabase));
      }
      
      public void onUpgrade(SQLiteDatabase paramAnonymousSQLiteDatabase, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        paramCallback.onUpgrade(getWrappedDb(paramAnonymousSQLiteDatabase), paramAnonymousInt1, paramAnonymousInt2);
      }
    };
  }
  
  public void close()
  {
    this.mDelegate.close();
  }
  
  public String getDatabaseName()
  {
    return this.mDelegate.getDatabaseName();
  }
  
  public SupportSQLiteDatabase getReadableDatabase()
  {
    return this.mDelegate.getReadableSupportDatabase();
  }
  
  public SupportSQLiteDatabase getWritableDatabase()
  {
    return this.mDelegate.getWritableSupportDatabase();
  }
  
  @RequiresApi(api=16)
  public void setWriteAheadLoggingEnabled(boolean paramBoolean)
  {
    this.mDelegate.setWriteAheadLoggingEnabled(paramBoolean);
  }
  
  static abstract class OpenHelper
    extends SQLiteOpenHelper
  {
    FrameworkSQLiteDatabase mWrappedDb;
    
    OpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, DatabaseErrorHandler paramDatabaseErrorHandler)
    {
      super(paramString, paramCursorFactory, paramInt, paramDatabaseErrorHandler);
    }
    
    public void close()
    {
      try
      {
        super.close();
        this.mWrappedDb = null;
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    SupportSQLiteDatabase getReadableSupportDatabase()
    {
      return getWrappedDb(super.getReadableDatabase());
    }
    
    FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase paramSQLiteDatabase)
    {
      if (this.mWrappedDb == null) {
        this.mWrappedDb = new FrameworkSQLiteDatabase(paramSQLiteDatabase);
      }
      return this.mWrappedDb;
    }
    
    SupportSQLiteDatabase getWritableSupportDatabase()
    {
      return getWrappedDb(super.getWritableDatabase());
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\framework\FrameworkSQLiteOpenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */