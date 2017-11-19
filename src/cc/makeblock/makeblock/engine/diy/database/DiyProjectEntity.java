package cc.makeblock.makeblock.engine.diy.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="diy_project")
public class DiyProjectEntity
{
  @PrimaryKey(autoGenerate=true)
  public int id;
  public String project;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\database\DiyProjectEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */