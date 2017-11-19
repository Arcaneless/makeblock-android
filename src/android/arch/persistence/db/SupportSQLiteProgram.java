package android.arch.persistence.db;

import android.annotation.TargetApi;

@TargetApi(19)
public abstract interface SupportSQLiteProgram
  extends AutoCloseable
{
  public abstract void bindBlob(int paramInt, byte[] paramArrayOfByte);
  
  public abstract void bindDouble(int paramInt, double paramDouble);
  
  public abstract void bindLong(int paramInt, long paramLong);
  
  public abstract void bindNull(int paramInt);
  
  public abstract void bindString(int paramInt, String paramString);
  
  public abstract void clearBindings();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SupportSQLiteProgram.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */