package cc.makeblock.makeblock.engine.diy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={DiyProjectEntity.class}, exportSchema=false, version=1)
public abstract class AppDatabase
  extends RoomDatabase
{
  public abstract DiyProjectDao diyProjectDao();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\database\AppDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */