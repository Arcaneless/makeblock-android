package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

public class a
{
  private static ContentValues a(i parami)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("key", parami.a());
    localContentValues.put("label", parami.c());
    localContentValues.put("count", Long.valueOf(parami.g()));
    localContentValues.put("value", Long.valueOf(parami.f()));
    localContentValues.put("totalTimestamp", Long.valueOf(parami.e()));
    localContentValues.put("timeWindowNum", parami.h());
    return localContentValues;
  }
  
  /* Error */
  public static String a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 68	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   4: aload_0
    //   5: ldc 70
    //   7: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   10: ifgt +24 -> 34
    //   13: iconst_0
    //   14: ifeq +11 -> 25
    //   17: new 75	java/lang/NullPointerException
    //   20: dup
    //   21: invokespecial 76	java/lang/NullPointerException:<init>	()V
    //   24: athrow
    //   25: aload_0
    //   26: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   29: iconst_0
    //   30: invokestatic 84	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   33: areturn
    //   34: aload_0
    //   35: ldc 86
    //   37: aconst_null
    //   38: invokevirtual 90	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore_1
    //   42: aload_1
    //   43: astore_2
    //   44: aload_1
    //   45: invokeinterface 96 1 0
    //   50: ifeq +130 -> 180
    //   53: goto +132 -> 185
    //   56: aload_1
    //   57: astore_2
    //   58: aload_1
    //   59: aload_1
    //   60: ldc 57
    //   62: invokeinterface 100 2 0
    //   67: invokeinterface 103 2 0
    //   72: astore_3
    //   73: aload_1
    //   74: astore_2
    //   75: aload_0
    //   76: invokevirtual 106	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   79: aload_1
    //   80: ifnull +9 -> 89
    //   83: aload_1
    //   84: invokeinterface 109 1 0
    //   89: aload_0
    //   90: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   93: aload_3
    //   94: areturn
    //   95: astore 4
    //   97: aconst_null
    //   98: astore_1
    //   99: aconst_null
    //   100: astore_3
    //   101: aload_1
    //   102: astore_2
    //   103: new 111	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   110: ldc 114
    //   112: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload 4
    //   117: invokevirtual 121	android/database/SQLException:toString	()Ljava/lang/String;
    //   120: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   129: aload_1
    //   130: ifnull +9 -> 139
    //   133: aload_1
    //   134: invokeinterface 109 1 0
    //   139: aload_0
    //   140: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   143: aload_3
    //   144: areturn
    //   145: astore_1
    //   146: aconst_null
    //   147: astore_2
    //   148: aload_2
    //   149: ifnull +9 -> 158
    //   152: aload_2
    //   153: invokeinterface 109 1 0
    //   158: aload_0
    //   159: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   162: aload_1
    //   163: athrow
    //   164: astore_1
    //   165: goto -17 -> 148
    //   168: astore 4
    //   170: aconst_null
    //   171: astore_3
    //   172: goto -71 -> 101
    //   175: astore 4
    //   177: goto -76 -> 101
    //   180: aconst_null
    //   181: astore_3
    //   182: goto -109 -> 73
    //   185: goto -129 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramSQLiteDatabase	SQLiteDatabase
    //   41	93	1	localCursor1	Cursor
    //   145	18	1	localObject1	Object
    //   164	1	1	localObject2	Object
    //   43	110	2	localCursor2	Cursor
    //   72	110	3	str	String
    //   95	21	4	localSQLException1	SQLException
    //   168	1	4	localSQLException2	SQLException
    //   175	1	4	localSQLException3	SQLException
    // Exception table:
    //   from	to	target	type
    //   0	13	95	android/database/SQLException
    //   34	42	95	android/database/SQLException
    //   0	13	145	finally
    //   34	42	145	finally
    //   44	53	164	finally
    //   58	73	164	finally
    //   75	79	164	finally
    //   103	129	164	finally
    //   44	53	168	android/database/SQLException
    //   58	73	168	android/database/SQLException
    //   75	79	175	android/database/SQLException
  }
  
  /* Error */
  public static org.json.JSONObject a(f paramf, SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: new 132	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 133	org/json/JSONObject:<init>	()V
    //   7: astore 4
    //   9: aload_1
    //   10: ldc -121
    //   12: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   15: ifle +200 -> 215
    //   18: aload_1
    //   19: ldc -119
    //   21: aconst_null
    //   22: invokevirtual 90	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_1
    //   26: aload_1
    //   27: invokeinterface 140 1 0
    //   32: istore_2
    //   33: aload_1
    //   34: astore_3
    //   35: iload_2
    //   36: ifeq +181 -> 217
    //   39: aload_1
    //   40: aload_1
    //   41: ldc 16
    //   43: invokeinterface 100 2 0
    //   48: invokeinterface 103 2 0
    //   53: astore 5
    //   55: aload 4
    //   57: aload 5
    //   59: invokevirtual 144	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   62: ifeq +97 -> 159
    //   65: aload 4
    //   67: aload 5
    //   69: invokevirtual 148	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   72: astore_3
    //   73: aload 4
    //   75: aload 5
    //   77: invokevirtual 152	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   80: pop
    //   81: new 132	org/json/JSONObject
    //   84: dup
    //   85: invokespecial 133	org/json/JSONObject:<init>	()V
    //   88: astore 6
    //   90: goto +169 -> 259
    //   93: aload 6
    //   95: ldc 47
    //   97: aload_1
    //   98: aload_1
    //   99: ldc 32
    //   101: invokeinterface 100 2 0
    //   106: invokeinterface 156 2 0
    //   111: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   114: pop
    //   115: goto +147 -> 262
    //   118: aload 6
    //   120: ldc -95
    //   122: aload_1
    //   123: aload_1
    //   124: ldc -93
    //   126: invokeinterface 100 2 0
    //   131: invokeinterface 167 2 0
    //   136: invokevirtual 170	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   139: pop
    //   140: aload_3
    //   141: aload 6
    //   143: invokevirtual 175	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   146: pop
    //   147: aload 4
    //   149: aload 5
    //   151: aload_3
    //   152: invokevirtual 178	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   155: pop
    //   156: goto -130 -> 26
    //   159: new 172	org/json/JSONArray
    //   162: dup
    //   163: invokespecial 179	org/json/JSONArray:<init>	()V
    //   166: astore_3
    //   167: goto -86 -> 81
    //   170: astore_3
    //   171: aload_0
    //   172: ldc -75
    //   174: iconst_0
    //   175: invokevirtual 186	com/umeng/analytics/pro/f:a	(Ljava/lang/Object;Z)V
    //   178: new 111	java/lang/StringBuilder
    //   181: dup
    //   182: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   185: ldc -68
    //   187: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_3
    //   191: invokevirtual 121	android/database/SQLException:toString	()Ljava/lang/String;
    //   194: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   203: aload_1
    //   204: ifnull +9 -> 213
    //   207: aload_1
    //   208: invokeinterface 109 1 0
    //   213: aconst_null
    //   214: areturn
    //   215: aconst_null
    //   216: astore_3
    //   217: aload_3
    //   218: ifnull +9 -> 227
    //   221: aload_3
    //   222: invokeinterface 109 1 0
    //   227: aload 4
    //   229: areturn
    //   230: astore_0
    //   231: aconst_null
    //   232: astore_1
    //   233: aload_1
    //   234: ifnull +9 -> 243
    //   237: aload_1
    //   238: invokeinterface 109 1 0
    //   243: aload_0
    //   244: athrow
    //   245: astore_0
    //   246: goto -13 -> 233
    //   249: astore_0
    //   250: goto -17 -> 233
    //   253: astore_3
    //   254: aconst_null
    //   255: astore_1
    //   256: goto -85 -> 171
    //   259: goto -166 -> 93
    //   262: goto -144 -> 118
    //   265: astore_3
    //   266: goto -240 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	paramf	f
    //   0	269	1	paramSQLiteDatabase	SQLiteDatabase
    //   32	4	2	bool	boolean
    //   34	133	3	localObject1	Object
    //   170	21	3	localSQLException1	SQLException
    //   216	6	3	localObject2	Object
    //   253	1	3	localSQLException2	SQLException
    //   265	1	3	localException	Exception
    //   7	221	4	localJSONObject1	org.json.JSONObject
    //   53	97	5	str	String
    //   88	54	6	localJSONObject2	org.json.JSONObject
    // Exception table:
    //   from	to	target	type
    //   26	33	170	android/database/SQLException
    //   39	81	170	android/database/SQLException
    //   81	90	170	android/database/SQLException
    //   93	115	170	android/database/SQLException
    //   118	156	170	android/database/SQLException
    //   159	167	170	android/database/SQLException
    //   0	26	230	finally
    //   26	33	245	finally
    //   39	81	245	finally
    //   81	90	245	finally
    //   93	115	245	finally
    //   118	156	245	finally
    //   159	167	245	finally
    //   171	203	249	finally
    //   0	26	253	android/database/SQLException
    //   39	81	265	java/lang/Exception
    //   81	90	265	java/lang/Exception
    //   93	115	265	java/lang/Exception
    //   118	156	265	java/lang/Exception
    //   159	167	265	java/lang/Exception
  }
  
  public static void a(SQLiteDatabase paramSQLiteDatabase, String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      int i = c(paramSQLiteDatabase, "system");
      int j = n.a().c();
      break label125;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("key", paramString);
      localContentValues.put("timeStamp", Long.valueOf(paramLong2));
      localContentValues.put("count", Long.valueOf(paramLong1));
      paramSQLiteDatabase.insert("system", null, localContentValues);
      return;
      for (;;)
      {
        paramString = new ContentValues();
        paramString.put("key", "__meta_ve_of");
        paramString.put("timeStamp", Long.valueOf(System.currentTimeMillis()));
        paramString.put("count", Integer.valueOf(1));
        paramSQLiteDatabase.insert("system", null, paramString);
        return;
        label125:
        do
        {
          d(paramSQLiteDatabase, "__meta_ve_of");
          return;
          if (i < j) {
            break;
          }
        } while (i != j);
      }
      return;
    }
    catch (SQLException paramSQLiteDatabase) {}
  }
  
  public static void a(SQLiteDatabase paramSQLiteDatabase, Map<String, k> paramMap, f paramf)
  {
    int i = 0;
    Object localObject3 = null;
    ContentValues localContentValues = null;
    Object localObject2 = localContentValues;
    Object localObject1 = localObject3;
    try
    {
      localk = (k)paramMap.get("__ag_of");
      if (localk != null) {
        break label47;
      }
      if (0 != 0) {
        throw new NullPointerException();
      }
    }
    catch (SQLException paramSQLiteDatabase)
    {
      k localk;
      localObject1 = localObject2;
      by.e("save to system table error " + paramSQLiteDatabase.toString());
      return;
      localObject2 = paramMap;
      localObject1 = paramMap;
      localContentValues = new ContentValues();
      localObject2 = paramMap;
      localObject1 = paramMap;
      localContentValues.put("key", localk.c());
      break label443;
      localObject2 = paramMap;
      localObject1 = paramMap;
      for (l2 = localk.e();; l2 += l3)
      {
        localObject2 = paramMap;
        localObject1 = paramMap;
        localContentValues.put("count", Long.valueOf(l2));
        break label450;
        localObject2 = paramMap;
        localObject1 = paramMap;
        l2 = localk.d();
        localObject2 = paramMap;
        localObject1 = paramMap;
        localContentValues.put("timeStamp", Long.valueOf(l2));
        localObject2 = paramMap;
        localObject1 = paramMap;
        paramSQLiteDatabase.insert("system", null, localContentValues);
        localObject2 = paramMap;
        localObject1 = paramMap;
        paramf.a("success", false);
        if (paramMap == null) {
          break;
        }
        paramMap.close();
        return;
        l2 = i;
        localObject2 = paramMap;
        localObject1 = paramMap;
        l3 = localk.e();
      }
    }
    finally
    {
      if (localObject1 == null) {
        break label441;
      }
      ((Cursor)localObject1).close();
    }
    return;
    label47:
    localObject2 = localContentValues;
    localObject1 = localObject3;
    paramMap = paramSQLiteDatabase.rawQuery("select * from " + "system where key=\"__ag_of\"", null);
    localObject2 = paramMap;
    localObject1 = paramMap;
    paramMap.moveToFirst();
    long l1 = 0L;
    for (;;)
    {
      localObject2 = paramMap;
      localObject1 = paramMap;
      if (paramMap.isAfterLast()) {
        break;
      }
      localObject2 = paramMap;
      localObject1 = paramMap;
      if (paramMap.getCount() > 0)
      {
        localObject2 = paramMap;
        localObject1 = paramMap;
        i = paramMap.getInt(paramMap.getColumnIndex("count"));
        localObject2 = paramMap;
        localObject1 = paramMap;
        l1 = paramMap.getLong(paramMap.getColumnIndex("timeStamp"));
        localObject2 = paramMap;
        localObject1 = paramMap;
        paramSQLiteDatabase.execSQL("delete from " + "system where key=\"__ag_of\"");
      }
      localObject2 = paramMap;
      localObject1 = paramMap;
      paramMap.moveToNext();
    }
    for (;;)
    {
      long l2;
      long l3;
      label441:
      label443:
      if (i == 0)
      {
        continue;
        label450:
        l2 = l1;
        if (l1 == 0L) {}
      }
    }
  }
  
  public static void a(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean, f paramf)
  {
    b(paramSQLiteDatabase, "system");
    b(paramSQLiteDatabase, "aggregated");
    if (!paramBoolean)
    {
      b(paramSQLiteDatabase, "limitedck");
      paramf.a("success", false);
    }
  }
  
  /* Error */
  public static void a(f paramf, SQLiteDatabase paramSQLiteDatabase, java.util.List<String> paramList)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 68	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   4: aload_1
    //   5: ldc_w 268
    //   8: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   11: ifle +11 -> 22
    //   14: aload_1
    //   15: ldc_w 268
    //   18: invokestatic 264	com/umeng/analytics/pro/a:b	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z
    //   21: pop
    //   22: aload_2
    //   23: invokeinterface 275 1 0
    //   28: astore_2
    //   29: aload_2
    //   30: invokeinterface 280 1 0
    //   35: ifeq +77 -> 112
    //   38: aload_2
    //   39: invokeinterface 284 1 0
    //   44: checkcast 81	java/lang/String
    //   47: astore_3
    //   48: new 13	android/content/ContentValues
    //   51: dup
    //   52: invokespecial 14	android/content/ContentValues:<init>	()V
    //   55: astore 4
    //   57: aload 4
    //   59: ldc_w 286
    //   62: aload_3
    //   63: invokevirtual 25	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: aload_1
    //   67: ldc_w 268
    //   70: aconst_null
    //   71: aload 4
    //   73: invokevirtual 201	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   76: pop2
    //   77: goto -48 -> 29
    //   80: astore_0
    //   81: new 111	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   88: ldc_w 288
    //   91: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: aload_0
    //   95: invokevirtual 121	android/database/SQLException:toString	()Ljava/lang/String;
    //   98: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   107: aload_1
    //   108: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   111: return
    //   112: aload_1
    //   113: invokevirtual 106	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   116: aload_0
    //   117: ldc_w 257
    //   120: iconst_0
    //   121: invokevirtual 186	com/umeng/analytics/pro/f:a	(Ljava/lang/Object;Z)V
    //   124: aload_1
    //   125: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   128: return
    //   129: astore_0
    //   130: aload_1
    //   131: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   134: aload_0
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	paramf	f
    //   0	136	1	paramSQLiteDatabase	SQLiteDatabase
    //   0	136	2	paramList	java.util.List<String>
    //   47	16	3	str	String
    //   55	17	4	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   0	22	80	android/database/SQLException
    //   22	29	80	android/database/SQLException
    //   29	77	80	android/database/SQLException
    //   112	124	80	android/database/SQLException
    //   0	22	129	finally
    //   22	29	129	finally
    //   29	77	129	finally
    //   81	107	129	finally
    //   112	124	129	finally
  }
  
  public static boolean a(SQLiteDatabase paramSQLiteDatabase, f paramf)
  {
    try
    {
      paramSQLiteDatabase.beginTransaction();
      if (c(paramSQLiteDatabase, "aggregated_cache") <= 0)
      {
        paramf.a("faild", false);
        return false;
      }
      paramSQLiteDatabase.execSQL("insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache");
      paramSQLiteDatabase.setTransactionSuccessful();
      b(paramSQLiteDatabase, "aggregated_cache");
      paramf.a("success", false);
      return true;
    }
    catch (SQLException localSQLException)
    {
      paramf.a(Boolean.valueOf(false), false);
      by.e("cacheToAggregatedTable happen " + localSQLException.toString());
      return false;
    }
    finally
    {
      paramSQLiteDatabase.endTransaction();
    }
  }
  
  public static boolean a(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    try
    {
      paramSQLiteDatabase.execSQL("drop table if exists " + paramString);
      return true;
    }
    catch (SQLException paramSQLiteDatabase)
    {
      by.e("delete table faild!");
      paramSQLiteDatabase.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public static boolean a(SQLiteDatabase paramSQLiteDatabase, java.util.Collection<i> paramCollection)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 68	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   4: aload_0
    //   5: ldc 70
    //   7: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   10: ifle +10 -> 20
    //   13: aload_0
    //   14: ldc 70
    //   16: invokestatic 264	com/umeng/analytics/pro/a:b	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z
    //   19: pop
    //   20: aload_1
    //   21: invokeinterface 310 1 0
    //   26: astore_1
    //   27: aload_1
    //   28: invokeinterface 280 1 0
    //   33: ifeq +39 -> 72
    //   36: aload_0
    //   37: ldc 70
    //   39: aconst_null
    //   40: aload_1
    //   41: invokeinterface 284 1 0
    //   46: checkcast 18	com/umeng/analytics/pro/i
    //   49: invokestatic 312	com/umeng/analytics/pro/a:a	(Lcom/umeng/analytics/pro/i;)Landroid/content/ContentValues;
    //   52: invokevirtual 201	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   55: pop2
    //   56: goto -29 -> 27
    //   59: astore_1
    //   60: ldc_w 314
    //   63: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   66: aload_0
    //   67: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   70: iconst_0
    //   71: ireturn
    //   72: aload_0
    //   73: invokevirtual 106	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   76: aload_0
    //   77: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   80: iconst_1
    //   81: ireturn
    //   82: astore_1
    //   83: aload_0
    //   84: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramSQLiteDatabase	SQLiteDatabase
    //   0	89	1	paramCollection	java.util.Collection<i>
    // Exception table:
    //   from	to	target	type
    //   0	20	59	android/database/SQLException
    //   20	27	59	android/database/SQLException
    //   27	56	59	android/database/SQLException
    //   72	76	59	android/database/SQLException
    //   0	20	82	finally
    //   20	27	82	finally
    //   27	56	82	finally
    //   60	66	82	finally
    //   72	76	82	finally
  }
  
  /* Error */
  public static boolean a(f paramf, SQLiteDatabase paramSQLiteDatabase, java.util.Collection<i> paramCollection)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 68	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   4: aload_2
    //   5: invokeinterface 310 1 0
    //   10: astore_2
    //   11: aload_2
    //   12: invokeinterface 280 1 0
    //   17: ifeq +40 -> 57
    //   20: aload_1
    //   21: ldc_w 266
    //   24: aconst_null
    //   25: aload_2
    //   26: invokeinterface 284 1 0
    //   31: checkcast 18	com/umeng/analytics/pro/i
    //   34: invokestatic 312	com/umeng/analytics/pro/a:a	(Lcom/umeng/analytics/pro/i;)Landroid/content/ContentValues;
    //   37: invokevirtual 201	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   40: pop2
    //   41: goto -30 -> 11
    //   44: astore_0
    //   45: ldc_w 314
    //   48: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   51: aload_1
    //   52: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   55: iconst_0
    //   56: ireturn
    //   57: aload_1
    //   58: invokevirtual 106	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   61: aload_1
    //   62: ldc 70
    //   64: invokestatic 264	com/umeng/analytics/pro/a:b	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z
    //   67: pop
    //   68: aload_0
    //   69: ldc_w 257
    //   72: iconst_0
    //   73: invokevirtual 186	com/umeng/analytics/pro/f:a	(Ljava/lang/Object;Z)V
    //   76: aload_1
    //   77: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   80: iconst_1
    //   81: ireturn
    //   82: astore_0
    //   83: aload_1
    //   84: invokevirtual 79	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   87: aload_0
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramf	f
    //   0	89	1	paramSQLiteDatabase	SQLiteDatabase
    //   0	89	2	paramCollection	java.util.Collection<i>
    // Exception table:
    //   from	to	target	type
    //   0	11	44	android/database/SQLException
    //   11	41	44	android/database/SQLException
    //   57	76	44	android/database/SQLException
    //   0	11	82	finally
    //   11	41	82	finally
    //   45	51	82	finally
    //   57	76	82	finally
  }
  
  /* Error */
  public static org.json.JSONObject b(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 266
    //   4: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   7: ifle +337 -> 344
    //   10: aload_0
    //   11: ldc_w 320
    //   14: aconst_null
    //   15: invokevirtual 90	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   18: astore_2
    //   19: aload_2
    //   20: astore_0
    //   21: new 132	org/json/JSONObject
    //   24: dup
    //   25: invokespecial 133	org/json/JSONObject:<init>	()V
    //   28: astore 4
    //   30: aload_2
    //   31: astore_0
    //   32: aload_2
    //   33: invokeinterface 140 1 0
    //   38: istore_1
    //   39: iload_1
    //   40: ifeq +288 -> 328
    //   43: aload_2
    //   44: astore_0
    //   45: aload_2
    //   46: aload_2
    //   47: ldc 16
    //   49: invokeinterface 100 2 0
    //   54: invokeinterface 103 2 0
    //   59: astore 5
    //   61: aload_2
    //   62: astore_0
    //   63: aload 4
    //   65: aload 5
    //   67: invokevirtual 144	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   70: ifeq +202 -> 272
    //   73: aload_2
    //   74: astore_0
    //   75: aload 4
    //   77: aload 5
    //   79: invokevirtual 148	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   82: astore_3
    //   83: aload_2
    //   84: astore_0
    //   85: aload 4
    //   87: aload 5
    //   89: invokevirtual 152	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   92: pop
    //   93: aload_2
    //   94: astore_0
    //   95: new 132	org/json/JSONObject
    //   98: dup
    //   99: invokespecial 133	org/json/JSONObject:<init>	()V
    //   102: astore 6
    //   104: goto +277 -> 381
    //   107: aload_2
    //   108: astore_0
    //   109: aload 6
    //   111: ldc_w 322
    //   114: aload_2
    //   115: aload_2
    //   116: ldc 47
    //   118: invokeinterface 100 2 0
    //   123: invokeinterface 167 2 0
    //   128: invokevirtual 170	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   131: pop
    //   132: goto +252 -> 384
    //   135: aload_2
    //   136: astore_0
    //   137: aload 6
    //   139: ldc_w 324
    //   142: aload_2
    //   143: aload_2
    //   144: ldc 52
    //   146: invokeinterface 100 2 0
    //   151: invokeinterface 167 2 0
    //   156: invokevirtual 170	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   159: pop
    //   160: goto +227 -> 387
    //   163: aload_2
    //   164: astore_0
    //   165: aload 6
    //   167: ldc_w 326
    //   170: aload_2
    //   171: aload_2
    //   172: ldc 57
    //   174: invokeinterface 100 2 0
    //   179: invokeinterface 103 2 0
    //   184: invokestatic 329	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   187: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   190: pop
    //   191: goto +199 -> 390
    //   194: aload_2
    //   195: astore_0
    //   196: aload 6
    //   198: ldc 32
    //   200: aload_2
    //   201: aload_2
    //   202: ldc 32
    //   204: invokeinterface 100 2 0
    //   209: invokeinterface 156 2 0
    //   214: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   217: pop
    //   218: goto +175 -> 393
    //   221: aload_2
    //   222: astore_0
    //   223: aload 6
    //   225: ldc_w 331
    //   228: aload_2
    //   229: aload_2
    //   230: ldc 27
    //   232: invokeinterface 100 2 0
    //   237: invokeinterface 103 2 0
    //   242: invokestatic 335	com/umeng/analytics/pro/d:a	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   245: invokevirtual 178	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   248: pop
    //   249: aload_2
    //   250: astore_0
    //   251: aload_3
    //   252: aload 6
    //   254: invokevirtual 175	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   257: pop
    //   258: aload_2
    //   259: astore_0
    //   260: aload 4
    //   262: aload 5
    //   264: aload_3
    //   265: invokevirtual 178	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   268: pop
    //   269: goto -239 -> 30
    //   272: aload_2
    //   273: astore_0
    //   274: new 172	org/json/JSONArray
    //   277: dup
    //   278: invokespecial 179	org/json/JSONArray:<init>	()V
    //   281: astore_3
    //   282: goto -189 -> 93
    //   285: astore_3
    //   286: aload_2
    //   287: astore_0
    //   288: new 111	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   295: ldc_w 337
    //   298: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload_3
    //   302: invokevirtual 121	android/database/SQLException:toString	()Ljava/lang/String;
    //   305: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   311: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   314: aload_2
    //   315: ifnull +9 -> 324
    //   318: aload_2
    //   319: invokeinterface 109 1 0
    //   324: aconst_null
    //   325: astore_0
    //   326: aload_0
    //   327: areturn
    //   328: aload 4
    //   330: astore_0
    //   331: aload_2
    //   332: ifnull -6 -> 326
    //   335: aload_2
    //   336: invokeinterface 109 1 0
    //   341: aload 4
    //   343: areturn
    //   344: iconst_0
    //   345: ifeq -21 -> 324
    //   348: new 75	java/lang/NullPointerException
    //   351: dup
    //   352: invokespecial 76	java/lang/NullPointerException:<init>	()V
    //   355: athrow
    //   356: astore_2
    //   357: aconst_null
    //   358: astore_0
    //   359: aload_0
    //   360: ifnull +9 -> 369
    //   363: aload_0
    //   364: invokeinterface 109 1 0
    //   369: aload_2
    //   370: athrow
    //   371: astore_2
    //   372: goto -13 -> 359
    //   375: astore_3
    //   376: aconst_null
    //   377: astore_2
    //   378: goto -92 -> 286
    //   381: goto -274 -> 107
    //   384: goto -249 -> 135
    //   387: goto -224 -> 163
    //   390: goto -196 -> 194
    //   393: goto -172 -> 221
    //   396: astore_0
    //   397: goto -367 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	400	0	paramSQLiteDatabase	SQLiteDatabase
    //   38	2	1	bool	boolean
    //   18	318	2	localCursor	Cursor
    //   356	14	2	localObject1	Object
    //   371	1	2	localObject2	Object
    //   377	1	2	localObject3	Object
    //   82	200	3	localJSONArray	org.json.JSONArray
    //   285	17	3	localSQLException1	SQLException
    //   375	1	3	localSQLException2	SQLException
    //   28	314	4	localJSONObject1	org.json.JSONObject
    //   59	204	5	str	String
    //   102	151	6	localJSONObject2	org.json.JSONObject
    // Exception table:
    //   from	to	target	type
    //   21	30	285	android/database/SQLException
    //   32	39	285	android/database/SQLException
    //   45	61	285	android/database/SQLException
    //   63	73	285	android/database/SQLException
    //   75	83	285	android/database/SQLException
    //   85	93	285	android/database/SQLException
    //   95	104	285	android/database/SQLException
    //   109	132	285	android/database/SQLException
    //   137	160	285	android/database/SQLException
    //   165	191	285	android/database/SQLException
    //   196	218	285	android/database/SQLException
    //   223	249	285	android/database/SQLException
    //   251	258	285	android/database/SQLException
    //   260	269	285	android/database/SQLException
    //   274	282	285	android/database/SQLException
    //   0	19	356	finally
    //   21	30	371	finally
    //   32	39	371	finally
    //   45	61	371	finally
    //   63	73	371	finally
    //   75	83	371	finally
    //   85	93	371	finally
    //   95	104	371	finally
    //   109	132	371	finally
    //   137	160	371	finally
    //   165	191	371	finally
    //   196	218	371	finally
    //   223	249	371	finally
    //   251	258	371	finally
    //   260	269	371	finally
    //   274	282	371	finally
    //   288	314	371	finally
    //   0	19	375	android/database/SQLException
    //   45	61	396	java/lang/Exception
    //   63	73	396	java/lang/Exception
    //   75	83	396	java/lang/Exception
    //   85	93	396	java/lang/Exception
    //   95	104	396	java/lang/Exception
    //   109	132	396	java/lang/Exception
    //   137	160	396	java/lang/Exception
    //   165	191	396	java/lang/Exception
    //   196	218	396	java/lang/Exception
    //   223	249	396	java/lang/Exception
    //   251	258	396	java/lang/Exception
    //   260	269	396	java/lang/Exception
    //   274	282	396	java/lang/Exception
  }
  
  public static boolean b(SQLiteDatabase paramSQLiteDatabase, f paramf)
  {
    Object localObject3 = null;
    i locali = null;
    Object localObject2 = locali;
    Object localObject1 = localObject3;
    try
    {
      localHashMap = new HashMap();
      localObject2 = locali;
      localObject1 = localObject3;
      paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("select * from aggregated_cache", null);
      localObject2 = paramSQLiteDatabase;
      localObject1 = paramSQLiteDatabase;
      if (!paramSQLiteDatabase.moveToNext()) {
        break label308;
      }
      localObject2 = paramSQLiteDatabase;
      localObject1 = paramSQLiteDatabase;
      locali = new i();
    }
    catch (SQLException paramSQLiteDatabase)
    {
      HashMap localHashMap;
      label64:
      localObject1 = localObject2;
      paramf.a(Boolean.valueOf(false), false);
      localObject1 = localObject2;
      by.e("cacheToMemory happen " + paramSQLiteDatabase.toString());
      for (;;)
      {
        return false;
        localObject2 = paramSQLiteDatabase;
        localObject1 = paramSQLiteDatabase;
        if (localHashMap.size() > 0)
        {
          localObject2 = paramSQLiteDatabase;
          localObject1 = paramSQLiteDatabase;
          paramf.a(localHashMap, false);
        }
        while (paramSQLiteDatabase != null)
        {
          paramSQLiteDatabase.close();
          return false;
          localObject2 = paramSQLiteDatabase;
          localObject1 = paramSQLiteDatabase;
          paramf.a("faild", false);
        }
      }
    }
    finally
    {
      if (localObject1 == null) {
        break label370;
      }
      ((Cursor)localObject1).close();
    }
    localObject2 = paramSQLiteDatabase;
    localObject1 = paramSQLiteDatabase;
    locali.a(d.b(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("key"))));
    break label375;
    label93:
    localObject2 = paramSQLiteDatabase;
    localObject1 = paramSQLiteDatabase;
    locali.b(d.b(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("label"))));
    localObject2 = paramSQLiteDatabase;
    localObject1 = paramSQLiteDatabase;
    locali.c(paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("count")));
    localObject2 = paramSQLiteDatabase;
    localObject1 = paramSQLiteDatabase;
    locali.b(paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("value")));
    break label378;
    label170:
    localObject2 = paramSQLiteDatabase;
    localObject1 = paramSQLiteDatabase;
    locali.b(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("timeWindowNum")));
    for (;;)
    {
      localObject2 = paramSQLiteDatabase;
      localObject1 = paramSQLiteDatabase;
      locali.a(Long.parseLong(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("totalTimestamp"))));
      localObject2 = paramSQLiteDatabase;
      localObject1 = paramSQLiteDatabase;
      localHashMap.put(d.b(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("key"))), locali);
      break;
      label308:
      label370:
      break label64;
      label375:
      break label93;
      label378:
      break label170;
    }
  }
  
  public static boolean b(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    try
    {
      if (c(paramSQLiteDatabase, paramString) >= 0) {
        paramSQLiteDatabase.execSQL("delete from " + paramString);
      }
      return true;
    }
    catch (SQLException paramSQLiteDatabase)
    {
      by.e("cleanTableData faild!" + paramSQLiteDatabase.toString());
    }
    return false;
  }
  
  public static int c(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    localObject = null;
    localSQLiteDatabase = null;
    int j = 0;
    try
    {
      paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("select * from " + paramString, null);
      localSQLiteDatabase = paramSQLiteDatabase;
      localObject = paramSQLiteDatabase;
      int i = paramSQLiteDatabase.getCount();
      j = i;
      if (paramSQLiteDatabase != null)
      {
        paramSQLiteDatabase.close();
        j = i;
      }
    }
    catch (Exception paramSQLiteDatabase)
    {
      do
      {
        localObject = localSQLiteDatabase;
        by.e("count error " + paramSQLiteDatabase.toString());
      } while (localSQLiteDatabase == null);
      localSQLiteDatabase.close();
      return 0;
    }
    finally
    {
      if (localObject == null) {
        break label120;
      }
      ((Cursor)localObject).close();
    }
    return j;
  }
  
  /* Error */
  public static java.util.List<String> c(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 268
    //   4: invokestatic 73	com/umeng/analytics/pro/a:c	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   7: ifle +117 -> 124
    //   10: aload_0
    //   11: ldc_w 378
    //   14: aconst_null
    //   15: invokevirtual 90	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   18: astore_1
    //   19: aload_1
    //   20: astore_0
    //   21: new 380	java/util/ArrayList
    //   24: dup
    //   25: invokespecial 381	java/util/ArrayList:<init>	()V
    //   28: astore_2
    //   29: aload_1
    //   30: astore_0
    //   31: aload_1
    //   32: invokeinterface 140 1 0
    //   37: ifeq +73 -> 110
    //   40: aload_1
    //   41: astore_0
    //   42: aload_2
    //   43: aload_1
    //   44: aload_1
    //   45: ldc_w 286
    //   48: invokeinterface 100 2 0
    //   53: invokeinterface 103 2 0
    //   58: invokeinterface 385 2 0
    //   63: pop
    //   64: goto -35 -> 29
    //   67: astore_2
    //   68: aload_1
    //   69: astore_0
    //   70: new 111	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 112	java/lang/StringBuilder:<init>	()V
    //   77: ldc_w 387
    //   80: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_2
    //   84: invokevirtual 121	android/database/SQLException:toString	()Ljava/lang/String;
    //   87: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 127	com/umeng/analytics/pro/by:e	(Ljava/lang/String;)V
    //   96: aload_1
    //   97: ifnull +9 -> 106
    //   100: aload_1
    //   101: invokeinterface 109 1 0
    //   106: aconst_null
    //   107: astore_0
    //   108: aload_0
    //   109: areturn
    //   110: aload_2
    //   111: astore_0
    //   112: aload_1
    //   113: ifnull -5 -> 108
    //   116: aload_1
    //   117: invokeinterface 109 1 0
    //   122: aload_2
    //   123: areturn
    //   124: iconst_0
    //   125: ifeq -19 -> 106
    //   128: new 75	java/lang/NullPointerException
    //   131: dup
    //   132: invokespecial 76	java/lang/NullPointerException:<init>	()V
    //   135: athrow
    //   136: astore_1
    //   137: aconst_null
    //   138: astore_0
    //   139: aload_0
    //   140: ifnull +9 -> 149
    //   143: aload_0
    //   144: invokeinterface 109 1 0
    //   149: aload_1
    //   150: athrow
    //   151: astore_1
    //   152: goto -13 -> 139
    //   155: astore_2
    //   156: aconst_null
    //   157: astore_1
    //   158: goto -90 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	161	0	paramSQLiteDatabase	SQLiteDatabase
    //   18	99	1	localCursor	Cursor
    //   136	14	1	localObject1	Object
    //   151	1	1	localObject2	Object
    //   157	1	1	localObject3	Object
    //   28	15	2	localArrayList	java.util.ArrayList
    //   67	56	2	localSQLException1	SQLException
    //   155	1	2	localSQLException2	SQLException
    // Exception table:
    //   from	to	target	type
    //   21	29	67	android/database/SQLException
    //   31	40	67	android/database/SQLException
    //   42	64	67	android/database/SQLException
    //   0	19	136	finally
    //   21	29	151	finally
    //   31	40	151	finally
    //   42	64	151	finally
    //   70	96	151	finally
    //   0	19	155	android/database/SQLException
  }
  
  private static void d(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    try
    {
      paramSQLiteDatabase.beginTransaction();
      paramSQLiteDatabase.execSQL("update system set count=count+1 where key like '" + paramString + "'");
      paramSQLiteDatabase.setTransactionSuccessful();
      if (paramSQLiteDatabase != null) {
        paramSQLiteDatabase.endTransaction();
      }
      return;
    }
    catch (SQLException paramString)
    {
      do
      {
        paramString = paramString;
      } while (paramSQLiteDatabase == null);
      paramSQLiteDatabase.endTransaction();
      return;
    }
    finally
    {
      paramString = finally;
      if (paramSQLiteDatabase != null) {
        paramSQLiteDatabase.endTransaction();
      }
      throw paramString;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\umeng\analytics\pro\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */