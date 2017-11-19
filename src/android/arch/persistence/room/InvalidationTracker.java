package android.arch.persistence.room;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class InvalidationTracker
{
  @VisibleForTesting
  static final String CLEANUP_SQL = "DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)";
  private static final String CREATE_VERSION_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)";
  @VisibleForTesting
  static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;";
  private static final String TABLE_ID_COLUMN_NAME = "table_id";
  private static final String[] TRIGGERS = { "UPDATE", "DELETE", "INSERT" };
  private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
  private static final String VERSION_COLUMN_NAME = "version";
  private volatile SupportSQLiteStatement mCleanupStatement;
  private final RoomDatabase mDatabase;
  private volatile boolean mInitialized = false;
  private long mMaxVersion = 0L;
  private ObservedTableTracker mObservedTableTracker;
  @VisibleForTesting
  final SafeIterableMap<Observer, ObserverWrapper> mObserverMap = new SafeIterableMap();
  AtomicBoolean mPendingRefresh = new AtomicBoolean(false);
  private Object[] mQueryArgs = new Object[1];
  @VisibleForTesting
  Runnable mRefreshRunnable = new Runnable()
  {
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   4: invokestatic 27	android/arch/persistence/room/InvalidationTracker:access$000	(Landroid/arch/persistence/room/InvalidationTracker;)Landroid/arch/persistence/room/RoomDatabase;
      //   7: invokevirtual 33	android/arch/persistence/room/RoomDatabase:getCloseLock	()Ljava/util/concurrent/locks/Lock;
      //   10: astore 10
      //   12: iconst_0
      //   13: istore 4
      //   15: iconst_0
      //   16: istore 5
      //   18: iconst_0
      //   19: istore_1
      //   20: iload 4
      //   22: istore_2
      //   23: iload 5
      //   25: istore_3
      //   26: aload 10
      //   28: invokeinterface 38 1 0
      //   33: iload 4
      //   35: istore_2
      //   36: iload 5
      //   38: istore_3
      //   39: aload_0
      //   40: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   43: invokestatic 42	android/arch/persistence/room/InvalidationTracker:access$100	(Landroid/arch/persistence/room/InvalidationTracker;)Z
      //   46: istore 6
      //   48: iload 6
      //   50: ifne +11 -> 61
      //   53: aload 10
      //   55: invokeinterface 45 1 0
      //   60: return
      //   61: iload 4
      //   63: istore_2
      //   64: iload 5
      //   66: istore_3
      //   67: aload_0
      //   68: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   71: invokestatic 27	android/arch/persistence/room/InvalidationTracker:access$000	(Landroid/arch/persistence/room/InvalidationTracker;)Landroid/arch/persistence/room/RoomDatabase;
      //   74: invokevirtual 49	android/arch/persistence/room/RoomDatabase:inTransaction	()Z
      //   77: ifne +28 -> 105
      //   80: iload 4
      //   82: istore_2
      //   83: iload 5
      //   85: istore_3
      //   86: aload_0
      //   87: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   90: getfield 53	android/arch/persistence/room/InvalidationTracker:mPendingRefresh	Ljava/util/concurrent/atomic/AtomicBoolean;
      //   93: iconst_1
      //   94: iconst_0
      //   95: invokevirtual 59	java/util/concurrent/atomic/AtomicBoolean:compareAndSet	(ZZ)Z
      //   98: istore 6
      //   100: iload 6
      //   102: ifne +11 -> 113
      //   105: aload 10
      //   107: invokeinterface 45 1 0
      //   112: return
      //   113: iload 4
      //   115: istore_2
      //   116: iload 5
      //   118: istore_3
      //   119: aload_0
      //   120: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   123: invokestatic 63	android/arch/persistence/room/InvalidationTracker:access$500	(Landroid/arch/persistence/room/InvalidationTracker;)Landroid/arch/persistence/db/SupportSQLiteStatement;
      //   126: invokeinterface 69 1 0
      //   131: pop
      //   132: iload 4
      //   134: istore_2
      //   135: iload 5
      //   137: istore_3
      //   138: aload_0
      //   139: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   142: invokestatic 73	android/arch/persistence/room/InvalidationTracker:access$600	(Landroid/arch/persistence/room/InvalidationTracker;)[Ljava/lang/Object;
      //   145: iconst_0
      //   146: aload_0
      //   147: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   150: invokestatic 77	android/arch/persistence/room/InvalidationTracker:access$700	(Landroid/arch/persistence/room/InvalidationTracker;)J
      //   153: invokestatic 83	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   156: aastore
      //   157: iload 4
      //   159: istore_2
      //   160: iload 5
      //   162: istore_3
      //   163: aload_0
      //   164: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   167: invokestatic 27	android/arch/persistence/room/InvalidationTracker:access$000	(Landroid/arch/persistence/room/InvalidationTracker;)Landroid/arch/persistence/room/RoomDatabase;
      //   170: ldc 85
      //   172: aload_0
      //   173: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   176: invokestatic 73	android/arch/persistence/room/InvalidationTracker:access$600	(Landroid/arch/persistence/room/InvalidationTracker;)[Ljava/lang/Object;
      //   179: invokevirtual 89	android/arch/persistence/room/RoomDatabase:query	(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;
      //   182: astore 9
      //   184: iload_1
      //   185: istore 4
      //   187: aload 9
      //   189: invokeinterface 94 1 0
      //   194: ifeq +180 -> 374
      //   197: iload_1
      //   198: istore 4
      //   200: aload 9
      //   202: iconst_0
      //   203: invokeinterface 98 2 0
      //   208: lstore 7
      //   210: iload_1
      //   211: istore 4
      //   213: aload 9
      //   215: iconst_1
      //   216: invokeinterface 102 2 0
      //   221: istore_2
      //   222: iload_1
      //   223: istore 4
      //   225: aload_0
      //   226: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   229: getfield 106	android/arch/persistence/room/InvalidationTracker:mTableVersions	[J
      //   232: iload_2
      //   233: lload 7
      //   235: lastore
      //   236: goto +182 -> 418
      //   239: aload_0
      //   240: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   243: lload 7
      //   245: invokestatic 110	android/arch/persistence/room/InvalidationTracker:access$702	(Landroid/arch/persistence/room/InvalidationTracker;J)J
      //   248: pop2
      //   249: goto -65 -> 184
      //   252: astore 11
      //   254: iload 4
      //   256: istore_2
      //   257: iload 4
      //   259: istore_3
      //   260: aload 9
      //   262: invokeinterface 113 1 0
      //   267: iload 4
      //   269: istore_2
      //   270: iload 4
      //   272: istore_3
      //   273: aload 11
      //   275: athrow
      //   276: astore 9
      //   278: ldc 115
      //   280: ldc 117
      //   282: aload 9
      //   284: invokestatic 123	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   287: pop
      //   288: aload 10
      //   290: invokeinterface 45 1 0
      //   295: iload_2
      //   296: istore_1
      //   297: iload_1
      //   298: ifeq -238 -> 60
      //   301: aload_0
      //   302: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   305: getfield 127	android/arch/persistence/room/InvalidationTracker:mObserverMap	Landroid/arch/core/internal/SafeIterableMap;
      //   308: astore 9
      //   310: aload 9
      //   312: monitorenter
      //   313: aload_0
      //   314: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   317: getfield 127	android/arch/persistence/room/InvalidationTracker:mObserverMap	Landroid/arch/core/internal/SafeIterableMap;
      //   320: invokevirtual 133	android/arch/core/internal/SafeIterableMap:iterator	()Ljava/util/Iterator;
      //   323: astore 10
      //   325: aload 10
      //   327: invokeinterface 138 1 0
      //   332: ifeq +75 -> 407
      //   335: aload 10
      //   337: invokeinterface 142 1 0
      //   342: checkcast 144	java/util/Map$Entry
      //   345: invokeinterface 147 1 0
      //   350: checkcast 149	android/arch/persistence/room/InvalidationTracker$ObserverWrapper
      //   353: aload_0
      //   354: getfield 14	android/arch/persistence/room/InvalidationTracker$2:this$0	Landroid/arch/persistence/room/InvalidationTracker;
      //   357: getfield 106	android/arch/persistence/room/InvalidationTracker:mTableVersions	[J
      //   360: invokevirtual 153	android/arch/persistence/room/InvalidationTracker$ObserverWrapper:checkForInvalidation	([J)V
      //   363: goto -38 -> 325
      //   366: astore 10
      //   368: aload 9
      //   370: monitorexit
      //   371: aload 10
      //   373: athrow
      //   374: iload_1
      //   375: istore_2
      //   376: iload_1
      //   377: istore_3
      //   378: aload 9
      //   380: invokeinterface 113 1 0
      //   385: aload 10
      //   387: invokeinterface 45 1 0
      //   392: goto -95 -> 297
      //   395: astore 9
      //   397: aload 10
      //   399: invokeinterface 45 1 0
      //   404: aload 9
      //   406: athrow
      //   407: aload 9
      //   409: monitorexit
      //   410: return
      //   411: astore 9
      //   413: iload_3
      //   414: istore_2
      //   415: goto -137 -> 278
      //   418: iconst_1
      //   419: istore 4
      //   421: iconst_1
      //   422: istore_1
      //   423: goto -184 -> 239
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	426	0	this	2
      //   19	404	1	i	int
      //   22	393	2	j	int
      //   25	389	3	k	int
      //   13	407	4	m	int
      //   16	145	5	n	int
      //   46	55	6	bool	boolean
      //   208	36	7	l	long
      //   182	79	9	localCursor	android.database.Cursor
      //   276	7	9	localIllegalStateException	IllegalStateException
      //   395	13	9	localObject1	Object
      //   411	1	9	localSQLiteException	SQLiteException
      //   10	326	10	localObject2	Object
      //   366	32	10	localObject3	Object
      //   252	22	11	localObject4	Object
      // Exception table:
      //   from	to	target	type
      //   187	197	252	finally
      //   200	210	252	finally
      //   213	222	252	finally
      //   225	236	252	finally
      //   239	249	252	finally
      //   26	33	276	java/lang/IllegalStateException
      //   39	48	276	java/lang/IllegalStateException
      //   67	80	276	java/lang/IllegalStateException
      //   86	100	276	java/lang/IllegalStateException
      //   119	132	276	java/lang/IllegalStateException
      //   138	157	276	java/lang/IllegalStateException
      //   163	184	276	java/lang/IllegalStateException
      //   260	267	276	java/lang/IllegalStateException
      //   273	276	276	java/lang/IllegalStateException
      //   378	385	276	java/lang/IllegalStateException
      //   313	325	366	finally
      //   325	363	366	finally
      //   368	371	366	finally
      //   407	410	366	finally
      //   26	33	395	finally
      //   39	48	395	finally
      //   67	80	395	finally
      //   86	100	395	finally
      //   119	132	395	finally
      //   138	157	395	finally
      //   163	184	395	finally
      //   260	267	395	finally
      //   273	276	395	finally
      //   278	288	395	finally
      //   378	385	395	finally
      //   26	33	411	android/database/sqlite/SQLiteException
      //   39	48	411	android/database/sqlite/SQLiteException
      //   67	80	411	android/database/sqlite/SQLiteException
      //   86	100	411	android/database/sqlite/SQLiteException
      //   119	132	411	android/database/sqlite/SQLiteException
      //   138	157	411	android/database/sqlite/SQLiteException
      //   163	184	411	android/database/sqlite/SQLiteException
      //   260	267	411	android/database/sqlite/SQLiteException
      //   273	276	411	android/database/sqlite/SQLiteException
      //   378	385	411	android/database/sqlite/SQLiteException
    }
  };
  private Runnable mSyncTriggers = new Runnable()
  {
    public void run()
    {
      if (InvalidationTracker.this.mDatabase.inTransaction()) {
        break label13;
      }
      label13:
      while (!InvalidationTracker.this.ensureInitialization()) {
        return;
      }
      for (;;)
      {
        int j;
        try
        {
          int[] arrayOfInt = InvalidationTracker.this.mObservedTableTracker.getTablesToSync();
          break label143;
          j = arrayOfInt.length;
          localSupportSQLiteDatabase = InvalidationTracker.this.mDatabase.getOpenHelper().getWritableDatabase();
          try {}finally
          {
            localSupportSQLiteDatabase.endTransaction();
          }
          InvalidationTracker.this.startTrackingTable(localSupportSQLiteDatabase, i);
        }
        catch (IllegalStateException localIllegalStateException)
        {
          SupportSQLiteDatabase localSupportSQLiteDatabase;
          Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", localIllegalStateException);
          return;
          InvalidationTracker.this.stopTrackingTable(localIllegalStateException, i);
          break label184;
          localIllegalStateException.setTransactionSuccessful();
          localIllegalStateException.endTransaction();
          InvalidationTracker.this.mObservedTableTracker.onSyncCompleted();
          continue;
        }
        catch (SQLiteException localSQLiteException)
        {
          continue;
        }
        label143:
        if (localObject == null) {
          break;
        }
        continue;
        int i = 0;
        while (i < j)
        {
          switch (localObject[i])
          {
          }
          label184:
          i += 1;
        }
      }
    }
  };
  @NonNull
  @VisibleForTesting
  ArrayMap<String, Integer> mTableIdLookup;
  private String[] mTableNames;
  @NonNull
  @VisibleForTesting
  long[] mTableVersions;
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public InvalidationTracker(RoomDatabase paramRoomDatabase, String... paramVarArgs)
  {
    this.mDatabase = paramRoomDatabase;
    this.mObservedTableTracker = new ObservedTableTracker(paramVarArgs.length);
    this.mTableIdLookup = new ArrayMap();
    int j = paramVarArgs.length;
    this.mTableNames = new String[j];
    int i = 0;
    while (i < j)
    {
      paramRoomDatabase = paramVarArgs[i].toLowerCase(Locale.US);
      this.mTableIdLookup.put(paramRoomDatabase, Integer.valueOf(i));
      this.mTableNames[i] = paramRoomDatabase;
      i += 1;
    }
    this.mTableVersions = new long[paramVarArgs.length];
    Arrays.fill(this.mTableVersions, 0L);
  }
  
  private static void appendTriggerName(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    paramStringBuilder.append("room_table_modification_trigger_").append(paramString1).append("_").append(paramString2);
  }
  
  private boolean ensureInitialization()
  {
    if (!this.mDatabase.isOpen()) {
      return false;
    }
    if (!this.mInitialized) {
      this.mDatabase.getOpenHelper().getWritableDatabase();
    }
    if (!this.mInitialized)
    {
      Log.e("ROOM", "database is not initialized even though it is open");
      return false;
    }
    return true;
  }
  
  private void startTrackingTable(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt)
  {
    String str1 = this.mTableNames[paramInt];
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = TRIGGERS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str2 = arrayOfString[i];
      localStringBuilder.setLength(0);
      localStringBuilder.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
      appendTriggerName(localStringBuilder, str1, str2);
      localStringBuilder.append(" AFTER ").append(str2).append(" ON ").append(str1).append(" BEGIN INSERT OR REPLACE INTO ").append("room_table_modification_log").append(" VALUES(null, ").append(paramInt).append("); END");
      paramSupportSQLiteDatabase.execSQL(localStringBuilder.toString());
      i += 1;
    }
  }
  
  private void stopTrackingTable(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt)
  {
    String str1 = this.mTableNames[paramInt];
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = TRIGGERS;
    int i = arrayOfString.length;
    paramInt = 0;
    while (paramInt < i)
    {
      String str2 = arrayOfString[paramInt];
      localStringBuilder.setLength(0);
      localStringBuilder.append("DROP TRIGGER IF EXISTS ");
      appendTriggerName(localStringBuilder, str1, str2);
      paramSupportSQLiteDatabase.execSQL(localStringBuilder.toString());
      paramInt += 1;
    }
  }
  
  public void addObserver(Observer paramObserver)
  {
    ??? = paramObserver.mTables;
    int[] arrayOfInt = new int[???.length];
    int j = ???.length;
    Object localObject2 = new long[???.length];
    int i = 0;
    while (i < j)
    {
      Integer localInteger = (Integer)this.mTableIdLookup.get(???[i].toLowerCase(Locale.US));
      if (localInteger == null) {
        throw new IllegalArgumentException("There is no table with name " + ???[i]);
      }
      arrayOfInt[i] = localInteger.intValue();
      localObject2[i] = this.mMaxVersion;
      i += 1;
    }
    localObject2 = new ObserverWrapper(paramObserver, arrayOfInt, (String[])???, (long[])localObject2);
    synchronized (this.mObserverMap)
    {
      paramObserver = (ObserverWrapper)this.mObserverMap.putIfAbsent(paramObserver, localObject2);
      if ((paramObserver == null) && (this.mObservedTableTracker.onAdded(arrayOfInt))) {
        AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mSyncTriggers);
      }
      return;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void addWeakObserver(Observer paramObserver)
  {
    addObserver(new WeakObserver(this, paramObserver));
  }
  
  /* Error */
  void internalInit(SupportSQLiteDatabase paramSupportSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 103	android/arch/persistence/room/InvalidationTracker:mInitialized	Z
    //   6: ifeq +15 -> 21
    //   9: ldc -38
    //   11: ldc_w 308
    //   14: invokestatic 226	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: aload_1
    //   22: invokeinterface 311 1 0
    //   27: aload_1
    //   28: ldc_w 313
    //   31: invokeinterface 257 2 0
    //   36: aload_1
    //   37: ldc_w 315
    //   40: invokeinterface 257 2 0
    //   45: aload_1
    //   46: ldc 28
    //   48: invokeinterface 257 2 0
    //   53: aload_1
    //   54: invokeinterface 318 1 0
    //   59: aload_1
    //   60: invokeinterface 321 1 0
    //   65: aload_0
    //   66: aload_1
    //   67: ldc 24
    //   69: invokeinterface 325 2 0
    //   74: putfield 183	android/arch/persistence/room/InvalidationTracker:mCleanupStatement	Landroid/arch/persistence/db/SupportSQLiteStatement;
    //   77: aload_0
    //   78: iconst_1
    //   79: putfield 103	android/arch/persistence/room/InvalidationTracker:mInitialized	Z
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    //   90: astore_2
    //   91: aload_1
    //   92: invokeinterface 321 1 0
    //   97: aload_2
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	InvalidationTracker
    //   0	99	1	paramSupportSQLiteDatabase	SupportSQLiteDatabase
    //   90	8	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	85	finally
    //   21	27	85	finally
    //   59	84	85	finally
    //   86	88	85	finally
    //   91	99	85	finally
    //   27	59	90	finally
  }
  
  public void refreshVersionsAsync()
  {
    if (this.mPendingRefresh.compareAndSet(false, true)) {
      AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mRefreshRunnable);
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  @WorkerThread
  public void refreshVersionsSync()
  {
    syncTriggers();
    this.mRefreshRunnable.run();
  }
  
  public void removeObserver(Observer paramObserver)
  {
    synchronized (this.mObserverMap)
    {
      paramObserver = (ObserverWrapper)this.mObserverMap.remove(paramObserver);
      if ((paramObserver != null) && (this.mObservedTableTracker.onRemoved(paramObserver.mTableIds))) {
        AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mSyncTriggers);
      }
      return;
    }
  }
  
  void syncTriggers()
  {
    this.mSyncTriggers.run();
  }
  
  static class ObservedTableTracker
  {
    static final int ADD = 1;
    static final int NO_OP = 0;
    static final int REMOVE = 2;
    boolean mNeedsSync;
    boolean mPendingSync;
    final long[] mTableObservers;
    final int[] mTriggerStateChanges;
    final boolean[] mTriggerStates;
    
    ObservedTableTracker(int paramInt)
    {
      this.mTableObservers = new long[paramInt];
      this.mTriggerStates = new boolean[paramInt];
      this.mTriggerStateChanges = new int[paramInt];
      Arrays.fill(this.mTableObservers, 0L);
      Arrays.fill(this.mTriggerStates, false);
    }
    
    @Nullable
    int[] getTablesToSync()
    {
      int i;
      int m;
      int j;
      label70:
      label98:
      int[] arrayOfInt2;
      for (;;)
      {
        int k;
        try
        {
          if ((!this.mNeedsSync) || (this.mPendingSync)) {
            return null;
          }
          k = this.mTableObservers.length;
        }
        finally {}
        if (this.mTableObservers[i] <= 0L) {
          break label144;
        }
        m = 1;
        if (m != this.mTriggerStates[i])
        {
          int[] arrayOfInt1 = this.mTriggerStateChanges;
          if (m == 0) {
            break label150;
          }
          j = 1;
          break;
          this.mTriggerStates[i] = m;
          break label137;
        }
        this.mTriggerStateChanges[i] = 0;
        continue;
        this.mPendingSync = true;
        this.mNeedsSync = false;
        arrayOfInt2 = this.mTriggerStateChanges;
        return arrayOfInt2;
        i = 0;
        label121:
        if (i >= k) {
          break label142;
        }
      }
      for (;;)
      {
        arrayOfInt2[i] = j;
        break label70;
        label137:
        i += 1;
        break label121;
        label142:
        break label98;
        label144:
        m = 0;
        break;
        label150:
        j = 2;
      }
    }
    
    boolean onAdded(int... paramVarArgs)
    {
      boolean bool = false;
      int j;
      int i;
      int k;
      label36:
      try
      {
        j = paramVarArgs.length;
        i = 0;
      }
      finally {}
      long l = this.mTableObservers[k];
      this.mTableObservers[k] = (1L + l);
      break label67;
      this.mNeedsSync = true;
      label67:
      label85:
      for (;;)
      {
        return bool;
        for (;;)
        {
          if (i >= j) {
            break label85;
          }
          k = paramVarArgs[i];
          break;
          if (l == 0L)
          {
            break label36;
            bool = true;
          }
          i += 1;
        }
      }
    }
    
    boolean onRemoved(int... paramVarArgs)
    {
      boolean bool = false;
      int j;
      int i;
      int k;
      label36:
      try
      {
        j = paramVarArgs.length;
        i = 0;
      }
      finally {}
      long l = this.mTableObservers[k];
      this.mTableObservers[k] = (l - 1L);
      break label67;
      this.mNeedsSync = true;
      label67:
      label85:
      for (;;)
      {
        return bool;
        for (;;)
        {
          if (i >= j) {
            break label85;
          }
          k = paramVarArgs[i];
          break;
          if (l == 1L)
          {
            break label36;
            bool = true;
          }
          i += 1;
        }
      }
    }
    
    void onSyncCompleted()
    {
      try
      {
        this.mPendingSync = false;
        return;
      }
      finally {}
    }
  }
  
  public static abstract class Observer
  {
    final String[] mTables;
    
    protected Observer(@NonNull String paramString, String... paramVarArgs)
    {
      this.mTables = ((String[])Arrays.copyOf(paramVarArgs, paramVarArgs.length + 1));
      this.mTables[paramVarArgs.length] = paramString;
    }
    
    public Observer(@NonNull String[] paramArrayOfString)
    {
      this.mTables = ((String[])Arrays.copyOf(paramArrayOfString, paramArrayOfString.length));
    }
    
    public abstract void onInvalidated(@NonNull Set<String> paramSet);
  }
  
  static class ObserverWrapper
  {
    final InvalidationTracker.Observer mObserver;
    private final Set<String> mSingleTableSet;
    final int[] mTableIds;
    private final String[] mTableNames;
    private final long[] mVersions;
    
    ObserverWrapper(InvalidationTracker.Observer paramObserver, int[] paramArrayOfInt, String[] paramArrayOfString, long[] paramArrayOfLong)
    {
      this.mObserver = paramObserver;
      this.mTableIds = paramArrayOfInt;
      this.mTableNames = paramArrayOfString;
      this.mVersions = paramArrayOfLong;
      if (paramArrayOfInt.length == 1)
      {
        paramObserver = new ArraySet();
        paramObserver.add(this.mTableNames[0]);
        this.mSingleTableSet = Collections.unmodifiableSet(paramObserver);
        return;
      }
      this.mSingleTableSet = null;
    }
    
    void checkForInvalidation(long[] paramArrayOfLong)
    {
      Object localObject1 = null;
      int j = this.mTableIds.length;
      int i = 0;
      if (i < j)
      {
        long l = paramArrayOfLong[this.mTableIds[i]];
        Object localObject2 = localObject1;
        if (this.mVersions[i] < l)
        {
          this.mVersions[i] = l;
          if (j != 1) {
            break label72;
          }
          localObject2 = this.mSingleTableSet;
        }
        for (;;)
        {
          i += 1;
          localObject1 = localObject2;
          break;
          label72:
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArraySet(j);
          }
          ((Set)localObject2).add(this.mTableNames[i]);
        }
      }
      if (localObject1 != null) {
        this.mObserver.onInvalidated((Set)localObject1);
      }
    }
  }
  
  static class WeakObserver
    extends InvalidationTracker.Observer
  {
    final WeakReference<InvalidationTracker.Observer> mDelegateRef;
    final InvalidationTracker mTracker;
    
    WeakObserver(InvalidationTracker paramInvalidationTracker, InvalidationTracker.Observer paramObserver)
    {
      super();
      this.mTracker = paramInvalidationTracker;
      this.mDelegateRef = new WeakReference(paramObserver);
    }
    
    public void onInvalidated(@NonNull Set<String> paramSet)
    {
      InvalidationTracker.Observer localObserver = (InvalidationTracker.Observer)this.mDelegateRef.get();
      if (localObserver == null)
      {
        this.mTracker.removeObserver(this);
        return;
      }
      localObserver.onInvalidated(paramSet);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\InvalidationTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */