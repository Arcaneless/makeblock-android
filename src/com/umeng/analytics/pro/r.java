package com.umeng.analytics.pro;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

class r
  extends ContextWrapper
{
  private String a;
  
  public r(Context paramContext, String paramString)
  {
    super(paramContext);
    this.a = paramString;
  }
  
  public File getDatabasePath(String paramString)
  {
    paramString = new File(this.a + paramString);
    if ((!paramString.getParentFile().exists()) && (!paramString.getParentFile().isDirectory())) {
      paramString.getParentFile().mkdirs();
    }
    return paramString;
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory)
  {
    return SQLiteDatabase.openDatabase(getDatabasePath(paramString).getAbsolutePath(), paramCursorFactory, 268435472);
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler)
  {
    return SQLiteDatabase.openDatabase(getDatabasePath(paramString).getAbsolutePath(), paramCursorFactory, 268435472);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */