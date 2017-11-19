package android.arch.persistence.room;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RoomDatabase
{
  private static final String DB_IMPL_SUFFIX = "_Impl";
  private boolean mAllowMainThreadQueries;
  @Nullable
  protected List<Callback> mCallbacks;
  private final ReentrantLock mCloseLock = new ReentrantLock();
  protected volatile SupportSQLiteDatabase mDatabase;
  private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();
  private SupportSQLiteOpenHelper mOpenHelper;
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void assertNotMainThread()
  {
    if (this.mAllowMainThreadQueries) {}
    while (!AppToolkitTaskExecutor.getInstance().isMainThread()) {
      return;
    }
    throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
  }
  
  public void beginTransaction()
  {
    assertNotMainThread();
    this.mInvalidationTracker.syncTriggers();
    this.mOpenHelper.getWritableDatabase().beginTransaction();
  }
  
  public void close()
  {
    if (isOpen()) {}
    try
    {
      this.mCloseLock.lock();
      this.mOpenHelper.close();
      return;
    }
    finally
    {
      this.mCloseLock.unlock();
    }
  }
  
  public SupportSQLiteStatement compileStatement(String paramString)
  {
    assertNotMainThread();
    return this.mOpenHelper.getWritableDatabase().compileStatement(paramString);
  }
  
  protected abstract InvalidationTracker createInvalidationTracker();
  
  protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration paramDatabaseConfiguration);
  
  public void endTransaction()
  {
    this.mOpenHelper.getWritableDatabase().endTransaction();
    this.mInvalidationTracker.refreshVersionsAsync();
  }
  
  Lock getCloseLock()
  {
    return this.mCloseLock;
  }
  
  public InvalidationTracker getInvalidationTracker()
  {
    return this.mInvalidationTracker;
  }
  
  public SupportSQLiteOpenHelper getOpenHelper()
  {
    return this.mOpenHelper;
  }
  
  public boolean inTransaction()
  {
    return this.mOpenHelper.getWritableDatabase().inTransaction();
  }
  
  @CallSuper
  public void init(DatabaseConfiguration paramDatabaseConfiguration)
  {
    this.mOpenHelper = createOpenHelper(paramDatabaseConfiguration);
    this.mCallbacks = paramDatabaseConfiguration.callbacks;
    this.mAllowMainThreadQueries = paramDatabaseConfiguration.allowMainThreadQueries;
  }
  
  protected void internalInitInvalidationTracker(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    this.mInvalidationTracker.internalInit(paramSupportSQLiteDatabase);
  }
  
  public boolean isOpen()
  {
    SupportSQLiteDatabase localSupportSQLiteDatabase = this.mDatabase;
    return (localSupportSQLiteDatabase != null) && (localSupportSQLiteDatabase.isOpen());
  }
  
  public Cursor query(SupportSQLiteQuery paramSupportSQLiteQuery)
  {
    assertNotMainThread();
    return this.mOpenHelper.getWritableDatabase().query(paramSupportSQLiteQuery);
  }
  
  public Cursor query(String paramString, @Nullable Object[] paramArrayOfObject)
  {
    return this.mOpenHelper.getWritableDatabase().query(new SimpleSQLiteQuery(paramString, paramArrayOfObject));
  }
  
  /* Error */
  public <V> V runInTransaction(java.util.concurrent.Callable<V> paramCallable)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 165	android/arch/persistence/room/RoomDatabase:beginTransaction	()V
    //   4: aload_1
    //   5: invokeinterface 171 1 0
    //   10: astore_1
    //   11: aload_0
    //   12: invokevirtual 174	android/arch/persistence/room/RoomDatabase:setTransactionSuccessful	()V
    //   15: aload_0
    //   16: invokevirtual 175	android/arch/persistence/room/RoomDatabase:endTransaction	()V
    //   19: aload_1
    //   20: areturn
    //   21: astore_1
    //   22: aload_1
    //   23: athrow
    //   24: astore_1
    //   25: aload_0
    //   26: invokevirtual 175	android/arch/persistence/room/RoomDatabase:endTransaction	()V
    //   29: aload_1
    //   30: athrow
    //   31: astore_1
    //   32: new 162	java/lang/RuntimeException
    //   35: dup
    //   36: ldc -79
    //   38: aload_1
    //   39: invokespecial 180	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	RoomDatabase
    //   0	43	1	paramCallable	java.util.concurrent.Callable<V>
    // Exception table:
    //   from	to	target	type
    //   4	15	21	java/lang/RuntimeException
    //   4	15	24	finally
    //   22	24	24	finally
    //   32	43	24	finally
    //   4	15	31	java/lang/Exception
  }
  
  public void runInTransaction(Runnable paramRunnable)
  {
    beginTransaction();
    try
    {
      paramRunnable.run();
      setTransactionSuccessful();
      return;
    }
    finally
    {
      endTransaction();
    }
  }
  
  public void setTransactionSuccessful()
  {
    this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
  }
  
  public static class Builder<T extends RoomDatabase>
  {
    private boolean mAllowMainThreadQueries;
    private ArrayList<RoomDatabase.Callback> mCallbacks;
    private final Context mContext;
    private final Class<T> mDatabaseClass;
    private SupportSQLiteOpenHelper.Factory mFactory;
    private boolean mInMemory;
    private RoomDatabase.MigrationContainer mMigrationContainer;
    private final String mName;
    private boolean mRequireMigration;
    
    Builder(@NonNull Context paramContext, @NonNull Class<T> paramClass, @Nullable String paramString)
    {
      this.mContext = paramContext;
      this.mDatabaseClass = paramClass;
      this.mName = paramString;
      this.mRequireMigration = true;
      this.mMigrationContainer = new RoomDatabase.MigrationContainer();
    }
    
    public Builder<T> addCallback(@NonNull RoomDatabase.Callback paramCallback)
    {
      if (this.mCallbacks == null) {
        this.mCallbacks = new ArrayList();
      }
      this.mCallbacks.add(paramCallback);
      return this;
    }
    
    public Builder<T> addMigrations(Migration... paramVarArgs)
    {
      this.mMigrationContainer.addMigrations(paramVarArgs);
      return this;
    }
    
    public Builder<T> allowMainThreadQueries()
    {
      this.mAllowMainThreadQueries = true;
      return this;
    }
    
    public T build()
    {
      if (this.mContext == null) {
        throw new IllegalArgumentException("Cannot provide null context for the database.");
      }
      if (this.mDatabaseClass == null) {
        throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
      }
      if (this.mFactory == null) {
        this.mFactory = new FrameworkSQLiteOpenHelperFactory();
      }
      DatabaseConfiguration localDatabaseConfiguration = new DatabaseConfiguration(this.mContext, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mRequireMigration);
      RoomDatabase localRoomDatabase = (RoomDatabase)Room.getGeneratedImplementation(this.mDatabaseClass, "_Impl");
      localRoomDatabase.init(localDatabaseConfiguration);
      return localRoomDatabase;
    }
    
    public Builder<T> fallbackToDestructiveMigration()
    {
      this.mRequireMigration = false;
      return this;
    }
    
    public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory paramFactory)
    {
      this.mFactory = paramFactory;
      return this;
    }
  }
  
  public static abstract class Callback
  {
    public void onCreate(@NonNull SupportSQLiteDatabase paramSupportSQLiteDatabase) {}
    
    public void onOpen(@NonNull SupportSQLiteDatabase paramSupportSQLiteDatabase) {}
  }
  
  public static class MigrationContainer
  {
    private SparseArrayCompat<SparseArrayCompat<Migration>> mMigrations = new SparseArrayCompat();
    
    private void addMigration(Migration paramMigration)
    {
      int i = paramMigration.startVersion;
      int j = paramMigration.endVersion;
      Object localObject2 = (SparseArrayCompat)this.mMigrations.get(i);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new SparseArrayCompat();
        this.mMigrations.put(i, localObject1);
      }
      localObject2 = (Migration)((SparseArrayCompat)localObject1).get(j);
      if (localObject2 != null) {
        Log.w("ROOM", "Overriding migration " + localObject2 + " with " + paramMigration);
      }
      ((SparseArrayCompat)localObject1).append(j, paramMigration);
    }
    
    private List<Migration> findUpMigrationPath(List<Migration> paramList, boolean paramBoolean, int paramInt1, int paramInt2)
    {
      int i;
      int j;
      Object localObject;
      if (paramBoolean)
      {
        i = -1;
        j = paramInt1;
        if (!paramBoolean) {
          break label58;
        }
        localObject = paramList;
        if (j >= paramInt2) {}
      }
      for (;;)
      {
        localObject = (SparseArrayCompat)this.mMigrations.get(j);
        if (localObject != null) {
          break label71;
        }
        localObject = null;
        label58:
        do
        {
          return (List<Migration>)localObject;
          i = 1;
          j = paramInt1;
          break;
          localObject = paramList;
        } while (j <= paramInt2);
      }
      label71:
      int k = ((SparseArrayCompat)localObject).size();
      label90:
      int i1;
      if (paramBoolean)
      {
        paramInt1 = k - 1;
        k = -1;
        i1 = 0;
      }
      for (;;)
      {
        int n = i1;
        int m = j;
        if (paramInt1 != k)
        {
          m = ((SparseArrayCompat)localObject).keyAt(paramInt1);
          if ((m <= paramInt2) && (m > j))
          {
            paramList.add(((SparseArrayCompat)localObject).valueAt(paramInt1));
            n = 1;
          }
        }
        else
        {
          j = m;
          if (n != 0) {
            break;
          }
          return null;
          paramInt1 = 0;
          break label90;
        }
        paramInt1 += i;
      }
    }
    
    public void addMigrations(Migration... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        addMigration(paramVarArgs[i]);
        i += 1;
      }
    }
    
    @Nullable
    public List<Migration> findMigrationPath(int paramInt1, int paramInt2)
    {
      if (paramInt1 == paramInt2) {
        return Collections.emptyList();
      }
      if (paramInt2 > paramInt1) {}
      for (boolean bool = true;; bool = false) {
        return findUpMigrationPath(new ArrayList(), bool, paramInt1, paramInt2);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\RoomDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */