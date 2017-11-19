package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import java.io.File;

public class zzatg
  extends zzats
{
  private final zza zzbrD = new zza(getContext(), zznV());
  private boolean zzbrE;
  
  zzatg(zzatp paramzzatp)
  {
    super(paramzzatp);
  }
  
  @TargetApi(11)
  @WorkerThread
  private boolean zza(int paramInt, byte[] paramArrayOfByte)
  {
    zzJe();
    zzmq();
    if (this.zzbrE) {
      return false;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("type", Integer.valueOf(paramInt));
    localContentValues.put("entry", paramArrayOfByte);
    zzJv().zzKt();
    int i = 0;
    paramInt = 5;
    while (i < 5)
    {
      Object localObject3 = null;
      paramArrayOfByte = null;
      Object localObject1 = null;
      try
      {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase == null)
        {
          localObject1 = localSQLiteDatabase;
          localObject3 = localSQLiteDatabase;
          paramArrayOfByte = localSQLiteDatabase;
          this.zzbrE = true;
          return false;
        }
        localObject1 = localSQLiteDatabase;
        localObject3 = localSQLiteDatabase;
        paramArrayOfByte = localSQLiteDatabase;
        localSQLiteDatabase.beginTransaction();
        long l2 = 0L;
        localObject1 = localSQLiteDatabase;
        localObject3 = localSQLiteDatabase;
        paramArrayOfByte = localSQLiteDatabase;
        Cursor localCursor = localSQLiteDatabase.rawQuery("select count(1) from messages", null);
        long l1 = l2;
        if (localCursor != null)
        {
          l1 = l2;
          localObject1 = localSQLiteDatabase;
          localObject3 = localSQLiteDatabase;
          paramArrayOfByte = localSQLiteDatabase;
          if (localCursor.moveToFirst())
          {
            localObject1 = localSQLiteDatabase;
            localObject3 = localSQLiteDatabase;
            paramArrayOfByte = localSQLiteDatabase;
            l1 = localCursor.getLong(0);
          }
        }
        if (l1 >= 100000L)
        {
          localObject1 = localSQLiteDatabase;
          localObject3 = localSQLiteDatabase;
          paramArrayOfByte = localSQLiteDatabase;
          zzJt().zzLa().log("Data loss, local db full");
          l1 = 100000L - l1 + 1L;
          localObject1 = localSQLiteDatabase;
          localObject3 = localSQLiteDatabase;
          paramArrayOfByte = localSQLiteDatabase;
          l2 = localSQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[] { Long.toString(l1) });
          if (l2 != l1)
          {
            localObject1 = localSQLiteDatabase;
            localObject3 = localSQLiteDatabase;
            paramArrayOfByte = localSQLiteDatabase;
            zzJt().zzLa().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l1 - l2));
          }
        }
        localObject1 = localSQLiteDatabase;
        localObject3 = localSQLiteDatabase;
        paramArrayOfByte = localSQLiteDatabase;
        localSQLiteDatabase.insertOrThrow("messages", null, localContentValues);
        localObject1 = localSQLiteDatabase;
        localObject3 = localSQLiteDatabase;
        paramArrayOfByte = localSQLiteDatabase;
        localSQLiteDatabase.setTransactionSuccessful();
        localObject1 = localSQLiteDatabase;
        localObject3 = localSQLiteDatabase;
        paramArrayOfByte = localSQLiteDatabase;
        localSQLiteDatabase.endTransaction();
        return true;
      }
      catch (SQLiteFullException localSQLiteFullException)
      {
        paramArrayOfByte = (byte[])localObject1;
        zzJt().zzLa().zzj("Error writing entry to local database", localSQLiteFullException);
        paramArrayOfByte = (byte[])localObject1;
        this.zzbrE = true;
        j = paramInt;
        if (localObject1 != null)
        {
          ((SQLiteDatabase)localObject1).close();
          j = paramInt;
        }
        i += 1;
        paramInt = j;
      }
      catch (SQLiteException localSQLiteException)
      {
        int j;
        paramArrayOfByte = localSQLiteFullException;
        if (Build.VERSION.SDK_INT >= 11)
        {
          paramArrayOfByte = localSQLiteFullException;
          if ((localSQLiteException instanceof SQLiteDatabaseLockedException))
          {
            paramArrayOfByte = localSQLiteFullException;
            SystemClock.sleep(paramInt);
            paramInt += 20;
          }
        }
        for (;;)
        {
          j = paramInt;
          if (localSQLiteFullException == null) {
            break;
          }
          localSQLiteFullException.close();
          j = paramInt;
          break;
          if (localSQLiteFullException != null)
          {
            paramArrayOfByte = localSQLiteFullException;
            if (localSQLiteFullException.inTransaction())
            {
              paramArrayOfByte = localSQLiteFullException;
              localSQLiteFullException.endTransaction();
            }
          }
          paramArrayOfByte = localSQLiteFullException;
          zzJt().zzLa().zzj("Error writing entry to local database", localSQLiteException);
          paramArrayOfByte = localSQLiteFullException;
          this.zzbrE = true;
        }
      }
      finally
      {
        if (paramArrayOfByte != null) {
          paramArrayOfByte.close();
        }
      }
    }
    zzJt().zzLc().log("Failed to write entry to local database");
    return false;
  }
  
  @WorkerThread
  SQLiteDatabase getWritableDatabase()
  {
    if (Build.VERSION.SDK_INT < 11) {}
    while (this.zzbrE) {
      return null;
    }
    SQLiteDatabase localSQLiteDatabase = this.zzbrD.getWritableDatabase();
    if (localSQLiteDatabase == null)
    {
      this.zzbrE = true;
      return null;
    }
    return localSQLiteDatabase;
  }
  
  boolean zzKP()
  {
    return getContext().getDatabasePath(zznV()).exists();
  }
  
  public boolean zza(zzatb paramzzatb)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return false;
    }
    Parcel localParcel = Parcel.obtain();
    paramzzatb.writeToParcel(localParcel, 0);
    paramzzatb = localParcel.marshall();
    localParcel.recycle();
    if (paramzzatb.length > 131072)
    {
      zzJt().zzLc().log("Event is too long for local database. Sending event directly to service");
      return false;
    }
    return zza(0, paramzzatb);
  }
  
  public boolean zza(zzaub paramzzaub)
  {
    if (Build.VERSION.SDK_INT < 11) {
      return false;
    }
    Parcel localParcel = Parcel.obtain();
    paramzzaub.writeToParcel(localParcel, 0);
    paramzzaub = localParcel.marshall();
    localParcel.recycle();
    if (paramzzaub.length > 131072)
    {
      zzJt().zzLc().log("User property too long for local database. Sending directly to service");
      return false;
    }
    return zza(1, paramzzaub);
  }
  
  /* Error */
  @TargetApi(11)
  public java.util.List<com.google.android.gms.common.internal.safeparcel.zza> zzls(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 45	com/google/android/gms/internal/zzatg:zzmq	()V
    //   4: aload_0
    //   5: invokevirtual 42	com/google/android/gms/internal/zzatg:zzJe	()V
    //   8: getstatic 173	android/os/Build$VERSION:SDK_INT	I
    //   11: bipush 11
    //   13: if_icmpge +5 -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aload_0
    //   19: getfield 47	com/google/android/gms/internal/zzatg:zzbrE	Z
    //   22: ifeq +5 -> 27
    //   25: aconst_null
    //   26: areturn
    //   27: new 306	java/util/ArrayList
    //   30: dup
    //   31: invokespecial 307	java/util/ArrayList:<init>	()V
    //   34: astore 12
    //   36: aload_0
    //   37: invokevirtual 309	com/google/android/gms/internal/zzatg:zzKP	()Z
    //   40: ifne +6 -> 46
    //   43: aload 12
    //   45: areturn
    //   46: iconst_5
    //   47: istore_2
    //   48: iconst_0
    //   49: istore 4
    //   51: iload 4
    //   53: iconst_5
    //   54: if_icmpge +610 -> 664
    //   57: aconst_null
    //   58: astore 11
    //   60: aconst_null
    //   61: astore 7
    //   63: aconst_null
    //   64: astore 10
    //   66: aload_0
    //   67: invokevirtual 82	com/google/android/gms/internal/zzatg:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   70: astore 8
    //   72: aload 8
    //   74: ifnonnull +20 -> 94
    //   77: aload_0
    //   78: iconst_1
    //   79: putfield 47	com/google/android/gms/internal/zzatg:zzbrE	Z
    //   82: aload 8
    //   84: ifnull +8 -> 92
    //   87: aload 8
    //   89: invokevirtual 87	android/database/sqlite/SQLiteDatabase:close	()V
    //   92: aconst_null
    //   93: areturn
    //   94: aload 8
    //   96: invokevirtual 90	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   99: iload_1
    //   100: invokestatic 312	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   103: astore 7
    //   105: aload 8
    //   107: ldc -128
    //   109: iconst_3
    //   110: anewarray 132	java/lang/String
    //   113: dup
    //   114: iconst_0
    //   115: ldc_w 314
    //   118: aastore
    //   119: dup
    //   120: iconst_1
    //   121: ldc 53
    //   123: aastore
    //   124: dup
    //   125: iconst_2
    //   126: ldc 65
    //   128: aastore
    //   129: aconst_null
    //   130: aconst_null
    //   131: aconst_null
    //   132: aconst_null
    //   133: ldc_w 316
    //   136: aload 7
    //   138: invokevirtual 320	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   141: astore 9
    //   143: ldc2_w 321
    //   146: lstore 5
    //   148: aload 9
    //   150: invokeinterface 325 1 0
    //   155: ifeq +375 -> 530
    //   158: aload 9
    //   160: iconst_0
    //   161: invokeinterface 106 2 0
    //   166: lstore 5
    //   168: aload 9
    //   170: iconst_1
    //   171: invokeinterface 329 2 0
    //   176: istore_3
    //   177: aload 9
    //   179: iconst_2
    //   180: invokeinterface 333 2 0
    //   185: astore 11
    //   187: iload_3
    //   188: ifne +145 -> 333
    //   191: invokestatic 276	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   194: astore 7
    //   196: aload 7
    //   198: aload 11
    //   200: iconst_0
    //   201: aload 11
    //   203: arraylength
    //   204: invokevirtual 337	android/os/Parcel:unmarshall	([BII)V
    //   207: aload 7
    //   209: iconst_0
    //   210: invokevirtual 341	android/os/Parcel:setDataPosition	(I)V
    //   213: getstatic 345	com/google/android/gms/internal/zzatb:CREATOR	Landroid/os/Parcelable$Creator;
    //   216: aload 7
    //   218: invokeinterface 351 2 0
    //   223: checkcast 278	com/google/android/gms/internal/zzatb
    //   226: astore 10
    //   228: aload 7
    //   230: invokevirtual 289	android/os/Parcel:recycle	()V
    //   233: aload 10
    //   235: ifnull +13 -> 248
    //   238: aload 12
    //   240: aload 10
    //   242: invokeinterface 357 2 0
    //   247: pop
    //   248: goto -100 -> 148
    //   251: astore 10
    //   253: aload_0
    //   254: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   257: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   260: ldc_w 359
    //   263: invokevirtual 126	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
    //   266: aload 7
    //   268: invokevirtual 289	android/os/Parcel:recycle	()V
    //   271: goto -123 -> 148
    //   274: astore 9
    //   276: aload 7
    //   278: invokevirtual 289	android/os/Parcel:recycle	()V
    //   281: aload 9
    //   283: athrow
    //   284: astore 9
    //   286: aload 8
    //   288: astore 7
    //   290: aload_0
    //   291: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   294: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   297: ldc_w 361
    //   300: aload 9
    //   302: invokevirtual 167	com/google/android/gms/internal/zzati$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
    //   305: aload 8
    //   307: astore 7
    //   309: aload_0
    //   310: iconst_1
    //   311: putfield 47	com/google/android/gms/internal/zzatg:zzbrE	Z
    //   314: aload 8
    //   316: ifnull +390 -> 706
    //   319: aload 8
    //   321: invokevirtual 87	android/database/sqlite/SQLiteDatabase:close	()V
    //   324: iload 4
    //   326: iconst_1
    //   327: iadd
    //   328: istore 4
    //   330: goto -279 -> 51
    //   333: iload_3
    //   334: iconst_1
    //   335: if_icmpne +179 -> 514
    //   338: invokestatic 276	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   341: astore 10
    //   343: aload 10
    //   345: aload 11
    //   347: iconst_0
    //   348: aload 11
    //   350: arraylength
    //   351: invokevirtual 337	android/os/Parcel:unmarshall	([BII)V
    //   354: aload 10
    //   356: iconst_0
    //   357: invokevirtual 341	android/os/Parcel:setDataPosition	(I)V
    //   360: getstatic 362	com/google/android/gms/internal/zzaub:CREATOR	Landroid/os/Parcelable$Creator;
    //   363: aload 10
    //   365: invokeinterface 351 2 0
    //   370: checkcast 297	com/google/android/gms/internal/zzaub
    //   373: astore 7
    //   375: aload 10
    //   377: invokevirtual 289	android/os/Parcel:recycle	()V
    //   380: aload 7
    //   382: ifnull -134 -> 248
    //   385: aload 12
    //   387: aload 7
    //   389: invokeinterface 357 2 0
    //   394: pop
    //   395: goto -147 -> 248
    //   398: astore 9
    //   400: aload 8
    //   402: astore 7
    //   404: getstatic 173	android/os/Build$VERSION:SDK_INT	I
    //   407: bipush 11
    //   409: if_icmplt +196 -> 605
    //   412: aload 8
    //   414: astore 7
    //   416: aload 9
    //   418: instanceof 175
    //   421: ifeq +184 -> 605
    //   424: aload 8
    //   426: astore 7
    //   428: iload_2
    //   429: i2l
    //   430: invokestatic 181	android/os/SystemClock:sleep	(J)V
    //   433: iload_2
    //   434: bipush 20
    //   436: iadd
    //   437: istore_3
    //   438: iload_3
    //   439: istore_2
    //   440: aload 8
    //   442: ifnull -118 -> 324
    //   445: aload 8
    //   447: invokevirtual 87	android/database/sqlite/SQLiteDatabase:close	()V
    //   450: iload_3
    //   451: istore_2
    //   452: goto -128 -> 324
    //   455: astore 7
    //   457: aload_0
    //   458: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   461: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   464: ldc_w 364
    //   467: invokevirtual 126	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
    //   470: aload 10
    //   472: invokevirtual 289	android/os/Parcel:recycle	()V
    //   475: aconst_null
    //   476: astore 7
    //   478: goto -98 -> 380
    //   481: astore 7
    //   483: aload 10
    //   485: invokevirtual 289	android/os/Parcel:recycle	()V
    //   488: aload 7
    //   490: athrow
    //   491: astore 7
    //   493: aload 8
    //   495: astore 9
    //   497: aload 7
    //   499: astore 8
    //   501: aload 9
    //   503: ifnull +8 -> 511
    //   506: aload 9
    //   508: invokevirtual 87	android/database/sqlite/SQLiteDatabase:close	()V
    //   511: aload 8
    //   513: athrow
    //   514: aload_0
    //   515: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   518: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   521: ldc_w 366
    //   524: invokevirtual 126	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
    //   527: goto -279 -> 248
    //   530: aload 9
    //   532: invokeinterface 367 1 0
    //   537: aload 8
    //   539: ldc -128
    //   541: ldc_w 369
    //   544: iconst_1
    //   545: anewarray 132	java/lang/String
    //   548: dup
    //   549: iconst_0
    //   550: lload 5
    //   552: invokestatic 138	java/lang/Long:toString	(J)Ljava/lang/String;
    //   555: aastore
    //   556: invokevirtual 142	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   559: aload 12
    //   561: invokeinterface 372 1 0
    //   566: if_icmpge +16 -> 582
    //   569: aload_0
    //   570: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   573: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   576: ldc_w 374
    //   579: invokevirtual 126	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
    //   582: aload 8
    //   584: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   587: aload 8
    //   589: invokevirtual 161	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   592: aload 8
    //   594: ifnull +8 -> 602
    //   597: aload 8
    //   599: invokevirtual 87	android/database/sqlite/SQLiteDatabase:close	()V
    //   602: aload 12
    //   604: areturn
    //   605: aload 8
    //   607: ifnull +24 -> 631
    //   610: aload 8
    //   612: astore 7
    //   614: aload 8
    //   616: invokevirtual 184	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   619: ifeq +12 -> 631
    //   622: aload 8
    //   624: astore 7
    //   626: aload 8
    //   628: invokevirtual 161	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   631: aload 8
    //   633: astore 7
    //   635: aload_0
    //   636: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   639: invokevirtual 118	com/google/android/gms/internal/zzati:zzLa	()Lcom/google/android/gms/internal/zzati$zza;
    //   642: ldc_w 361
    //   645: aload 9
    //   647: invokevirtual 167	com/google/android/gms/internal/zzati$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
    //   650: aload 8
    //   652: astore 7
    //   654: aload_0
    //   655: iconst_1
    //   656: putfield 47	com/google/android/gms/internal/zzatg:zzbrE	Z
    //   659: iload_2
    //   660: istore_3
    //   661: goto -223 -> 438
    //   664: aload_0
    //   665: invokevirtual 112	com/google/android/gms/internal/zzatg:zzJt	()Lcom/google/android/gms/internal/zzati;
    //   668: invokevirtual 187	com/google/android/gms/internal/zzati:zzLc	()Lcom/google/android/gms/internal/zzati$zza;
    //   671: ldc_w 376
    //   674: invokevirtual 126	com/google/android/gms/internal/zzati$zza:log	(Ljava/lang/String;)V
    //   677: aconst_null
    //   678: areturn
    //   679: astore 8
    //   681: aload 7
    //   683: astore 9
    //   685: goto -184 -> 501
    //   688: astore 9
    //   690: aload 11
    //   692: astore 8
    //   694: goto -294 -> 400
    //   697: astore 9
    //   699: aload 10
    //   701: astore 8
    //   703: goto -417 -> 286
    //   706: goto -382 -> 324
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	709	0	this	zzatg
    //   0	709	1	paramInt	int
    //   47	613	2	i	int
    //   176	485	3	j	int
    //   49	280	4	k	int
    //   146	405	5	l	long
    //   61	366	7	localObject1	Object
    //   455	1	7	localzza1	com.google.android.gms.common.internal.safeparcel.zzb.zza
    //   476	1	7	localObject2	Object
    //   481	8	7	localObject3	Object
    //   491	7	7	localObject4	Object
    //   612	70	7	localObject5	Object
    //   70	581	8	localObject6	Object
    //   679	1	8	localObject7	Object
    //   692	10	8	localObject8	Object
    //   141	37	9	localCursor	Cursor
    //   274	8	9	localObject9	Object
    //   284	17	9	localSQLiteFullException1	SQLiteFullException
    //   398	19	9	localSQLiteException1	SQLiteException
    //   495	189	9	localObject10	Object
    //   688	1	9	localSQLiteException2	SQLiteException
    //   697	1	9	localSQLiteFullException2	SQLiteFullException
    //   64	177	10	localzzatb	zzatb
    //   251	1	10	localzza2	com.google.android.gms.common.internal.safeparcel.zzb.zza
    //   341	359	10	localParcel	Parcel
    //   58	633	11	arrayOfByte	byte[]
    //   34	569	12	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   196	228	251	com/google/android/gms/common/internal/safeparcel/zzb$zza
    //   196	228	274	finally
    //   253	266	274	finally
    //   77	82	284	android/database/sqlite/SQLiteFullException
    //   94	143	284	android/database/sqlite/SQLiteFullException
    //   148	187	284	android/database/sqlite/SQLiteFullException
    //   191	196	284	android/database/sqlite/SQLiteFullException
    //   228	233	284	android/database/sqlite/SQLiteFullException
    //   238	248	284	android/database/sqlite/SQLiteFullException
    //   266	271	284	android/database/sqlite/SQLiteFullException
    //   276	284	284	android/database/sqlite/SQLiteFullException
    //   338	343	284	android/database/sqlite/SQLiteFullException
    //   375	380	284	android/database/sqlite/SQLiteFullException
    //   385	395	284	android/database/sqlite/SQLiteFullException
    //   470	475	284	android/database/sqlite/SQLiteFullException
    //   483	491	284	android/database/sqlite/SQLiteFullException
    //   514	527	284	android/database/sqlite/SQLiteFullException
    //   530	582	284	android/database/sqlite/SQLiteFullException
    //   582	592	284	android/database/sqlite/SQLiteFullException
    //   77	82	398	android/database/sqlite/SQLiteException
    //   94	143	398	android/database/sqlite/SQLiteException
    //   148	187	398	android/database/sqlite/SQLiteException
    //   191	196	398	android/database/sqlite/SQLiteException
    //   228	233	398	android/database/sqlite/SQLiteException
    //   238	248	398	android/database/sqlite/SQLiteException
    //   266	271	398	android/database/sqlite/SQLiteException
    //   276	284	398	android/database/sqlite/SQLiteException
    //   338	343	398	android/database/sqlite/SQLiteException
    //   375	380	398	android/database/sqlite/SQLiteException
    //   385	395	398	android/database/sqlite/SQLiteException
    //   470	475	398	android/database/sqlite/SQLiteException
    //   483	491	398	android/database/sqlite/SQLiteException
    //   514	527	398	android/database/sqlite/SQLiteException
    //   530	582	398	android/database/sqlite/SQLiteException
    //   582	592	398	android/database/sqlite/SQLiteException
    //   343	375	455	com/google/android/gms/common/internal/safeparcel/zzb$zza
    //   343	375	481	finally
    //   457	470	481	finally
    //   77	82	491	finally
    //   94	143	491	finally
    //   148	187	491	finally
    //   191	196	491	finally
    //   228	233	491	finally
    //   238	248	491	finally
    //   266	271	491	finally
    //   276	284	491	finally
    //   338	343	491	finally
    //   375	380	491	finally
    //   385	395	491	finally
    //   470	475	491	finally
    //   483	491	491	finally
    //   514	527	491	finally
    //   530	582	491	finally
    //   582	592	491	finally
    //   66	72	679	finally
    //   290	305	679	finally
    //   309	314	679	finally
    //   404	412	679	finally
    //   416	424	679	finally
    //   428	433	679	finally
    //   614	622	679	finally
    //   626	631	679	finally
    //   635	650	679	finally
    //   654	659	679	finally
    //   66	72	688	android/database/sqlite/SQLiteException
    //   66	72	697	android/database/sqlite/SQLiteFullException
  }
  
  protected void zzmr() {}
  
  String zznV()
  {
    return zzJv().zzKj();
  }
  
  @TargetApi(11)
  private class zza
    extends SQLiteOpenHelper
  {
    zza(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    @WorkerThread
    public SQLiteDatabase getWritableDatabase()
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = super.getWritableDatabase();
        return localSQLiteDatabase;
      }
      catch (SQLiteException localSQLiteException1)
      {
        if ((Build.VERSION.SDK_INT >= 11) && ((localSQLiteException1 instanceof SQLiteDatabaseLockedException))) {
          throw localSQLiteException1;
        }
        zzatg.this.zzJt().zzLa().log("Opening the local database failed, dropping and recreating it");
        Object localObject = zzatg.this.zznV();
        if (!zzatg.this.getContext().getDatabasePath((String)localObject).delete()) {
          zzatg.this.zzJt().zzLa().zzj("Failed to delete corrupted local db file", localObject);
        }
        try
        {
          localObject = super.getWritableDatabase();
          return (SQLiteDatabase)localObject;
        }
        catch (SQLiteException localSQLiteException2)
        {
          zzatg.this.zzJt().zzLa().zzj("Failed to open local database. Events will bypass local storage", localSQLiteException2);
        }
      }
      return null;
    }
    
    @WorkerThread
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      zzasu.zza(zzatg.this.zzJt(), paramSQLiteDatabase);
    }
    
    @WorkerThread
    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (Build.VERSION.SDK_INT < 15) {
        localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
      }
      try
      {
        localCursor.moveToFirst();
        localCursor.close();
        zzasu.zza(zzatg.this.zzJt(), paramSQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
        return;
      }
      finally
      {
        localCursor.close();
      }
    }
    
    @WorkerThread
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\android\gms\internal\zzatg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */