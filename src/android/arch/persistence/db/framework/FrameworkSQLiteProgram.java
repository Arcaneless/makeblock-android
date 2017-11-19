package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteProgram;
import android.database.sqlite.SQLiteProgram;

class FrameworkSQLiteProgram
  implements SupportSQLiteProgram
{
  private final SQLiteProgram mDelegate;
  
  FrameworkSQLiteProgram(SQLiteProgram paramSQLiteProgram)
  {
    this.mDelegate = paramSQLiteProgram;
  }
  
  public void bindBlob(int paramInt, byte[] paramArrayOfByte)
  {
    this.mDelegate.bindBlob(paramInt, paramArrayOfByte);
  }
  
  public void bindDouble(int paramInt, double paramDouble)
  {
    this.mDelegate.bindDouble(paramInt, paramDouble);
  }
  
  public void bindLong(int paramInt, long paramLong)
  {
    this.mDelegate.bindLong(paramInt, paramLong);
  }
  
  public void bindNull(int paramInt)
  {
    this.mDelegate.bindNull(paramInt);
  }
  
  public void bindString(int paramInt, String paramString)
  {
    this.mDelegate.bindString(paramInt, paramString);
  }
  
  public void clearBindings()
  {
    this.mDelegate.clearBindings();
  }
  
  public void close()
    throws Exception
  {
    this.mDelegate.close();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\framework\FrameworkSQLiteProgram.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */