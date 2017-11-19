package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

class u
{
  private static u c;
  private static SQLiteOpenHelper d;
  private AtomicInteger a = new AtomicInteger();
  private AtomicInteger b = new AtomicInteger();
  private SQLiteDatabase e;
  
  public static u a(Context paramContext)
  {
    try
    {
      if (c == null) {
        b(paramContext);
      }
      paramContext = c;
      return paramContext;
    }
    finally {}
  }
  
  private static void b(Context paramContext)
  {
    try
    {
      if (c == null)
      {
        c = new u();
        d = t.a(paramContext);
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public SQLiteDatabase a()
  {
    try
    {
      if (this.a.incrementAndGet() == 1) {
        this.e = d.getWritableDatabase();
      }
      SQLiteDatabase localSQLiteDatabase = this.e;
      return localSQLiteDatabase;
    }
    finally {}
  }
  
  /* Error */
  public void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 22	com/umeng/analytics/pro/u:a	Ljava/util/concurrent/atomic/AtomicInteger;
    //   6: invokevirtual 56	java/util/concurrent/atomic/AtomicInteger:decrementAndGet	()I
    //   9: ifne +10 -> 19
    //   12: aload_0
    //   13: getfield 51	com/umeng/analytics/pro/u:e	Landroid/database/sqlite/SQLiteDatabase;
    //   16: invokevirtual 61	android/database/sqlite/SQLiteDatabase:close	()V
    //   19: aload_0
    //   20: getfield 24	com/umeng/analytics/pro/u:b	Ljava/util/concurrent/atomic/AtomicInteger;
    //   23: invokevirtual 56	java/util/concurrent/atomic/AtomicInteger:decrementAndGet	()I
    //   26: ifne +10 -> 36
    //   29: aload_0
    //   30: getfield 51	com/umeng/analytics/pro/u:e	Landroid/database/sqlite/SQLiteDatabase;
    //   33: invokevirtual 61	android/database/sqlite/SQLiteDatabase:close	()V
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    //   44: astore_1
    //   45: goto -9 -> 36
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	this	u
    //   39	4	1	localObject	Object
    //   44	1	1	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	19	39	finally
    //   19	36	39	finally
    //   2	19	44	java/lang/Throwable
    //   19	36	44	java/lang/Throwable
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */