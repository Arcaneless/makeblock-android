package android.arch.persistence.db.framework;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class FrameworkSQLiteDatabase
  implements SupportSQLiteDatabase
{
  private static final String[] CONFLICT_VALUES = { "", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE " };
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  private final SQLiteDatabase mDelegate;
  
  public FrameworkSQLiteDatabase(SQLiteDatabase paramSQLiteDatabase)
  {
    this.mDelegate = paramSQLiteDatabase;
  }
  
  private static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public void beginTransaction()
  {
    this.mDelegate.beginTransaction();
  }
  
  public void beginTransactionNonExclusive()
  {
    this.mDelegate.beginTransactionNonExclusive();
  }
  
  public void beginTransactionWithListener(SQLiteTransactionListener paramSQLiteTransactionListener)
  {
    this.mDelegate.beginTransactionWithListener(paramSQLiteTransactionListener);
  }
  
  public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener paramSQLiteTransactionListener)
  {
    this.mDelegate.beginTransactionWithListenerNonExclusive(paramSQLiteTransactionListener);
  }
  
  public void close()
    throws IOException
  {
    this.mDelegate.close();
  }
  
  public SupportSQLiteStatement compileStatement(String paramString)
  {
    return new FrameworkSQLiteStatement(this.mDelegate.compileStatement(paramString));
  }
  
  public int delete(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("DELETE FROM ").append(paramString1);
    if (isEmpty(paramString2)) {}
    for (paramString1 = "";; paramString1 = " WHERE " + paramString2)
    {
      paramString1 = compileStatement(paramString1);
      SimpleSQLiteQuery.bind(paramString1, paramArrayOfObject);
      return paramString1.executeUpdateDelete();
    }
  }
  
  @RequiresApi(api=16)
  public void disableWriteAheadLogging()
  {
    this.mDelegate.disableWriteAheadLogging();
  }
  
  public boolean enableWriteAheadLogging()
  {
    return this.mDelegate.enableWriteAheadLogging();
  }
  
  public void endTransaction()
  {
    this.mDelegate.endTransaction();
  }
  
  public void execSQL(String paramString)
    throws SQLException
  {
    this.mDelegate.execSQL(paramString);
  }
  
  public void execSQL(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    this.mDelegate.execSQL(paramString, paramArrayOfObject);
  }
  
  public List<Pair<String, String>> getAttachedDbs()
  {
    return this.mDelegate.getAttachedDbs();
  }
  
  public long getMaximumSize()
  {
    return this.mDelegate.getMaximumSize();
  }
  
  public long getPageSize()
  {
    return this.mDelegate.getPageSize();
  }
  
  public String getPath()
  {
    return this.mDelegate.getPath();
  }
  
  public int getVersion()
  {
    return this.mDelegate.getVersion();
  }
  
  public boolean inTransaction()
  {
    return this.mDelegate.inTransaction();
  }
  
  public long insert(String paramString, int paramInt, ContentValues paramContentValues)
    throws SQLException
  {
    return this.mDelegate.insertWithOnConflict(paramString, null, paramContentValues, paramInt);
  }
  
  public boolean isDatabaseIntegrityOk()
  {
    return this.mDelegate.isDatabaseIntegrityOk();
  }
  
  public boolean isDbLockedByCurrentThread()
  {
    return this.mDelegate.isDbLockedByCurrentThread();
  }
  
  public boolean isOpen()
  {
    return this.mDelegate.isOpen();
  }
  
  public boolean isReadOnly()
  {
    return this.mDelegate.isReadOnly();
  }
  
  @RequiresApi(api=16)
  public boolean isWriteAheadLoggingEnabled()
  {
    return this.mDelegate.isWriteAheadLoggingEnabled();
  }
  
  public boolean needUpgrade(int paramInt)
  {
    return this.mDelegate.needUpgrade(paramInt);
  }
  
  public Cursor query(final SupportSQLiteQuery paramSupportSQLiteQuery)
  {
    this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory()
    {
      public Cursor newCursor(SQLiteDatabase paramAnonymousSQLiteDatabase, SQLiteCursorDriver paramAnonymousSQLiteCursorDriver, String paramAnonymousString, SQLiteQuery paramAnonymousSQLiteQuery)
      {
        paramSupportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(paramAnonymousSQLiteQuery));
        return new SQLiteCursor(paramAnonymousSQLiteCursorDriver, paramAnonymousString, paramAnonymousSQLiteQuery);
      }
    }, paramSupportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, null);
  }
  
  @RequiresApi(api=16)
  public Cursor query(final SupportSQLiteQuery paramSupportSQLiteQuery, CancellationSignal paramCancellationSignal)
  {
    this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory()
    {
      public Cursor newCursor(SQLiteDatabase paramAnonymousSQLiteDatabase, SQLiteCursorDriver paramAnonymousSQLiteCursorDriver, String paramAnonymousString, SQLiteQuery paramAnonymousSQLiteQuery)
      {
        paramSupportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(paramAnonymousSQLiteQuery));
        return new SQLiteCursor(paramAnonymousSQLiteCursorDriver, paramAnonymousString, paramAnonymousSQLiteQuery);
      }
    }, paramSupportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, null, paramCancellationSignal);
  }
  
  public Cursor query(String paramString)
  {
    return query(new SimpleSQLiteQuery(paramString));
  }
  
  public Cursor query(String paramString, Object[] paramArrayOfObject)
  {
    return query(new SimpleSQLiteQuery(paramString, paramArrayOfObject));
  }
  
  @RequiresApi(api=16)
  public void setForeignKeyConstraintsEnabled(boolean paramBoolean)
  {
    this.mDelegate.setForeignKeyConstraintsEnabled(paramBoolean);
  }
  
  public void setLocale(Locale paramLocale)
  {
    this.mDelegate.setLocale(paramLocale);
  }
  
  public void setMaxSqlCacheSize(int paramInt)
  {
    this.mDelegate.setMaxSqlCacheSize(paramInt);
  }
  
  public long setMaximumSize(long paramLong)
  {
    return this.mDelegate.setMaximumSize(paramLong);
  }
  
  public void setPageSize(long paramLong)
  {
    this.mDelegate.setPageSize(paramLong);
  }
  
  public void setTransactionSuccessful()
  {
    this.mDelegate.setTransactionSuccessful();
  }
  
  public void setVersion(int paramInt)
  {
    this.mDelegate.setVersion(paramInt);
  }
  
  public int update(String paramString1, int paramInt, ContentValues paramContentValues, String paramString2, Object[] paramArrayOfObject)
  {
    if ((paramContentValues == null) || (paramContentValues.size() == 0)) {
      throw new IllegalArgumentException("Empty values");
    }
    StringBuilder localStringBuilder = new StringBuilder(120);
    localStringBuilder.append("UPDATE ");
    localStringBuilder.append(CONFLICT_VALUES[paramInt]);
    localStringBuilder.append(paramString1);
    localStringBuilder.append(" SET ");
    paramInt = paramContentValues.size();
    int i;
    Object[] arrayOfObject;
    int j;
    label100:
    String str;
    if (paramArrayOfObject == null)
    {
      i = paramInt;
      arrayOfObject = new Object[i];
      j = 0;
      Iterator localIterator = paramContentValues.keySet().iterator();
      if (!localIterator.hasNext()) {
        break label191;
      }
      str = (String)localIterator.next();
      if (j <= 0) {
        break label185;
      }
    }
    label185:
    for (paramString1 = ",";; paramString1 = "")
    {
      localStringBuilder.append(paramString1);
      localStringBuilder.append(str);
      arrayOfObject[j] = paramContentValues.get(str);
      localStringBuilder.append("=?");
      j += 1;
      break label100;
      i = paramInt + paramArrayOfObject.length;
      break;
    }
    label191:
    if (paramArrayOfObject != null)
    {
      j = paramInt;
      while (j < i)
      {
        arrayOfObject[j] = paramArrayOfObject[(j - paramInt)];
        j += 1;
      }
    }
    if (!isEmpty(paramString2))
    {
      localStringBuilder.append(" WHERE ");
      localStringBuilder.append(paramString2);
    }
    paramString1 = compileStatement(localStringBuilder.toString());
    SimpleSQLiteQuery.bind(paramString1, arrayOfObject);
    return paramString1.executeUpdateDelete();
  }
  
  public boolean yieldIfContendedSafely()
  {
    return this.mDelegate.yieldIfContendedSafely();
  }
  
  public boolean yieldIfContendedSafely(long paramLong)
  {
    return this.mDelegate.yieldIfContendedSafely(paramLong);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\framework\FrameworkSQLiteDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */