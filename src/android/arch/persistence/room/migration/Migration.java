package android.arch.persistence.room.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;

public abstract class Migration
{
  public final int endVersion;
  public final int startVersion;
  
  public Migration(int paramInt1, int paramInt2)
  {
    this.startVersion = paramInt1;
    this.endVersion = paramInt2;
  }
  
  public abstract void migrate(SupportSQLiteDatabase paramSupportSQLiteDatabase);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\migration\Migration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */