package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class t
  extends SQLiteOpenHelper
{
  private static Context b = null;
  private String a = null;
  
  private t(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, str, paramCursorFactory, paramInt);
    a();
  }
  
  private t(Context paramContext, String paramString1, String paramString2, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    this(new r(paramContext, paramString1), paramString2, paramCursorFactory, paramInt);
  }
  
  public static t a(Context paramContext)
  {
    try
    {
      b = paramContext;
      paramContext = a.a();
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void a(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      this.a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER)";
      paramSQLiteDatabase.execSQL(this.a);
      return;
    }
    catch (SQLException paramSQLiteDatabase) {}
  }
  
  private void b(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      this.a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER)";
      paramSQLiteDatabase.execSQL(this.a);
      return;
    }
    catch (SQLException paramSQLiteDatabase) {}
  }
  
  private void c(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      this.a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT)";
      paramSQLiteDatabase.execSQL(this.a);
      return;
    }
    catch (SQLException paramSQLiteDatabase) {}
  }
  
  public void a()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      if (!v.a("__sd", localSQLiteDatabase)) {
        c(localSQLiteDatabase);
      }
      if (!v.a("__et", localSQLiteDatabase)) {
        b(localSQLiteDatabase);
      }
      if (!v.a("__er", localSQLiteDatabase)) {
        a(localSQLiteDatabase);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  /* Error */
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 97	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   4: aload_0
    //   5: aload_1
    //   6: invokespecial 81	com/umeng/analytics/pro/t:c	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 85	com/umeng/analytics/pro/t:b	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   14: aload_0
    //   15: aload_1
    //   16: invokespecial 89	com/umeng/analytics/pro/t:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   19: aload_1
    //   20: invokevirtual 100	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   23: aload_1
    //   24: ifnull +7 -> 31
    //   27: aload_1
    //   28: invokevirtual 103	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   31: return
    //   32: astore_2
    //   33: getstatic 16	com/umeng/analytics/pro/t:b	Landroid/content/Context;
    //   36: invokestatic 106	com/umeng/analytics/pro/v:b	(Landroid/content/Context;)V
    //   39: aload_1
    //   40: ifnull -9 -> 31
    //   43: aload_1
    //   44: invokevirtual 103	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   47: return
    //   48: astore_1
    //   49: return
    //   50: astore_2
    //   51: aload_1
    //   52: ifnull -21 -> 31
    //   55: aload_1
    //   56: invokevirtual 103	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   59: return
    //   60: astore_1
    //   61: return
    //   62: astore_2
    //   63: aload_1
    //   64: ifnull +7 -> 71
    //   67: aload_1
    //   68: invokevirtual 103	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   71: aload_2
    //   72: athrow
    //   73: astore_1
    //   74: return
    //   75: astore_1
    //   76: goto -5 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	t
    //   0	79	1	paramSQLiteDatabase	SQLiteDatabase
    //   32	1	2	localSQLiteDatabaseCorruptException	android.database.sqlite.SQLiteDatabaseCorruptException
    //   50	1	2	localThrowable	Throwable
    //   62	10	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	23	32	android/database/sqlite/SQLiteDatabaseCorruptException
    //   43	47	48	java/lang/Throwable
    //   0	23	50	java/lang/Throwable
    //   55	59	60	java/lang/Throwable
    //   0	23	62	finally
    //   33	39	62	finally
    //   27	31	73	java/lang/Throwable
    //   67	71	75	java/lang/Throwable
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  private static class a
  {
    private static final t a = new t(t.b(), v.a(t.b()), "ua.db", null, 1, null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */