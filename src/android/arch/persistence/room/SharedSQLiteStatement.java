package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.support.annotation.RestrictTo;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class SharedSQLiteStatement
{
  private final RoomDatabase mDatabase;
  private final AtomicBoolean mLock = new AtomicBoolean(false);
  private volatile SupportSQLiteStatement mStmt;
  
  public SharedSQLiteStatement(RoomDatabase paramRoomDatabase)
  {
    this.mDatabase = paramRoomDatabase;
  }
  
  private SupportSQLiteStatement createNewStatement()
  {
    String str = createQuery();
    return this.mDatabase.compileStatement(str);
  }
  
  private SupportSQLiteStatement getStmt(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (this.mStmt == null) {
        this.mStmt = createNewStatement();
      }
      return this.mStmt;
    }
    return createNewStatement();
  }
  
  public SupportSQLiteStatement acquire()
  {
    assertNotMainThread();
    return getStmt(this.mLock.compareAndSet(false, true));
  }
  
  protected void assertNotMainThread()
  {
    this.mDatabase.assertNotMainThread();
  }
  
  protected abstract String createQuery();
  
  public void release(SupportSQLiteStatement paramSupportSQLiteStatement)
  {
    if (paramSupportSQLiteStatement == this.mStmt) {
      this.mLock.set(false);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\SharedSQLiteStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */