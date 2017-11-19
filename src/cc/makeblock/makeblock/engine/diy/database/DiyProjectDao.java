package cc.makeblock.makeblock.engine.diy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public abstract interface DiyProjectDao
{
  @Delete
  public abstract void delete(DiyProjectEntity paramDiyProjectEntity);
  
  @Query("SELECT * FROM diy_project ORDER BY id DESC")
  public abstract List<DiyProjectEntity> getAll();
  
  @Query("SELECT * FROM diy_project ORDER BY id DESC")
  public abstract LiveData<List<DiyProjectEntity>> getAllAsync();
  
  @Insert
  public abstract void insert(DiyProjectEntity... paramVarArgs);
  
  @Query("SELECT * FROM diy_project WHERE id IN (:ids)")
  public abstract List<DiyProjectEntity> loadAllByIds(int[] paramArrayOfInt);
  
  @Update
  public abstract int update(DiyProjectEntity... paramVarArgs);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\database\DiyProjectDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */