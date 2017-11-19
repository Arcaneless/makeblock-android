package android.arch.persistence.room.util;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TableInfo
{
  public final Map<String, Column> columns;
  public final Set<ForeignKey> foreignKeys;
  public final String name;
  
  public TableInfo(String paramString, Map<String, Column> paramMap, Set<ForeignKey> paramSet)
  {
    this.name = paramString;
    this.columns = Collections.unmodifiableMap(paramMap);
    this.foreignKeys = Collections.unmodifiableSet(paramSet);
  }
  
  public static TableInfo read(SupportSQLiteDatabase paramSupportSQLiteDatabase, String paramString)
  {
    return new TableInfo(paramString, readColumns(paramSupportSQLiteDatabase, paramString), readForeignKeys(paramSupportSQLiteDatabase, paramString));
  }
  
  private static Map<String, Column> readColumns(SupportSQLiteDatabase paramSupportSQLiteDatabase, String paramString)
  {
    paramSupportSQLiteDatabase = paramSupportSQLiteDatabase.query("PRAGMA table_info(`" + paramString + "`)");
    paramString = new HashMap();
    for (;;)
    {
      try
      {
        if (paramSupportSQLiteDatabase.getColumnCount() <= 0) {
          break;
        }
        int i = paramSupportSQLiteDatabase.getColumnIndex("name");
        int j = paramSupportSQLiteDatabase.getColumnIndex("type");
        int k = paramSupportSQLiteDatabase.getColumnIndex("notnull");
        int m = paramSupportSQLiteDatabase.getColumnIndex("pk");
        if (!paramSupportSQLiteDatabase.moveToNext()) {
          break;
        }
        String str1 = paramSupportSQLiteDatabase.getString(i);
        String str2 = paramSupportSQLiteDatabase.getString(j);
        boolean bool;
        if (paramSupportSQLiteDatabase.getInt(k) != 0)
        {
          bool = true;
          paramString.put(str1, new Column(str1, str2, bool, paramSupportSQLiteDatabase.getInt(m)));
        }
        else
        {
          bool = false;
        }
      }
      finally
      {
        paramSupportSQLiteDatabase.close();
      }
    }
    paramSupportSQLiteDatabase.close();
    return paramString;
  }
  
  private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor paramCursor)
  {
    int j = paramCursor.getColumnIndex("id");
    int k = paramCursor.getColumnIndex("seq");
    int m = paramCursor.getColumnIndex("from");
    int n = paramCursor.getColumnIndex("to");
    int i1 = paramCursor.getCount();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < i1)
    {
      paramCursor.moveToPosition(i);
      localArrayList.add(new ForeignKeyWithSequence(paramCursor.getInt(j), paramCursor.getInt(k), paramCursor.getString(m), paramCursor.getString(n)));
      i += 1;
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase paramSupportSQLiteDatabase, String paramString)
  {
    HashSet localHashSet = new HashSet();
    paramSupportSQLiteDatabase = paramSupportSQLiteDatabase.query("PRAGMA foreign_key_list(`" + paramString + "`)");
    int m;
    int n;
    int i1;
    int i2;
    int i;
    try
    {
      j = paramSupportSQLiteDatabase.getColumnIndex("id");
      k = paramSupportSQLiteDatabase.getColumnIndex("seq");
      m = paramSupportSQLiteDatabase.getColumnIndex("table");
      n = paramSupportSQLiteDatabase.getColumnIndex("on_delete");
      i1 = paramSupportSQLiteDatabase.getColumnIndex("on_update");
      paramString = readForeignKeyFieldMappings(paramSupportSQLiteDatabase);
      i2 = paramSupportSQLiteDatabase.getCount();
    }
    finally
    {
      int j;
      int k;
      Iterator localIterator;
      paramSupportSQLiteDatabase.close();
    }
    paramSupportSQLiteDatabase.moveToPosition(i);
    int i3 = paramSupportSQLiteDatabase.getInt(k);
    break label297;
    label126:
    i3 = paramSupportSQLiteDatabase.getInt(j);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localIterator = paramString.iterator();
    while (localIterator.hasNext())
    {
      ForeignKeyWithSequence localForeignKeyWithSequence = (ForeignKeyWithSequence)localIterator.next();
      if (localForeignKeyWithSequence.mId == i3)
      {
        localArrayList1.add(localForeignKeyWithSequence.mFrom);
        localArrayList2.add(localForeignKeyWithSequence.mTo);
      }
    }
    localHashSet.add(new ForeignKey(paramSupportSQLiteDatabase.getString(m), paramSupportSQLiteDatabase.getString(n), paramSupportSQLiteDatabase.getString(i1), localArrayList1, localArrayList2));
    label297:
    label307:
    for (;;)
    {
      paramSupportSQLiteDatabase.close();
      return localHashSet;
      i = 0;
      for (;;)
      {
        if (i >= i2) {
          break label307;
        }
        break;
        if (i3 == 0) {
          break label126;
        }
        i += 1;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (paramObject == null);
          bool1 = bool2;
        } while (getClass() != paramObject.getClass());
        paramObject = (TableInfo)paramObject;
        bool1 = bool2;
      } while (!this.name.equals(((TableInfo)paramObject).name));
      bool1 = bool2;
    } while (!this.columns.equals(((TableInfo)paramObject).columns));
    return this.foreignKeys.equals(((TableInfo)paramObject).foreignKeys);
  }
  
  public int hashCode()
  {
    return (this.name.hashCode() * 31 + this.columns.hashCode()) * 31 + this.foreignKeys.hashCode();
  }
  
  public String toString()
  {
    return "TableInfo{name='" + this.name + '\'' + ", columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + '}';
  }
  
  public static class Column
  {
    public final String name;
    public final boolean notNull;
    public final int primaryKeyPosition;
    public final String type;
    
    public Column(String paramString1, String paramString2, boolean paramBoolean, int paramInt)
    {
      this.name = paramString1;
      this.type = paramString2;
      this.notNull = paramBoolean;
      this.primaryKeyPosition = paramInt;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = true;
      boolean bool3 = false;
      boolean bool1;
      if (this == paramObject) {
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return bool1;
                bool1 = bool3;
              } while (paramObject == null);
              bool1 = bool3;
            } while (getClass() != paramObject.getClass());
            paramObject = (Column)paramObject;
            if (Build.VERSION.SDK_INT < 20) {
              break;
            }
            bool1 = bool3;
          } while (this.primaryKeyPosition != ((Column)paramObject).primaryKeyPosition);
          bool1 = bool3;
        } while (!this.name.equals(((Column)paramObject).name));
        bool1 = bool3;
      } while (this.notNull != ((Column)paramObject).notNull);
      if (this.type != null) {
        bool1 = this.type.equalsIgnoreCase(((Column)paramObject).type);
      }
      for (;;)
      {
        return bool1;
        if (isPrimaryKey() == ((Column)paramObject).isPrimaryKey()) {
          break;
        }
        return false;
        bool1 = bool2;
        if (((Column)paramObject).type != null) {
          bool1 = false;
        }
      }
    }
    
    public int hashCode()
    {
      int k = this.name.hashCode();
      int i;
      if (this.type != null)
      {
        i = this.type.hashCode();
        if (!this.notNull) {
          break label59;
        }
      }
      label59:
      for (int j = 1231;; j = 1237)
      {
        return ((k * 31 + i) * 31 + j) * 31 + this.primaryKeyPosition;
        i = 0;
        break;
      }
    }
    
    public boolean isPrimaryKey()
    {
      return this.primaryKeyPosition > 0;
    }
    
    public String toString()
    {
      return "Column{name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + '}';
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class ForeignKey
  {
    @NonNull
    public final List<String> columnNames;
    @NonNull
    public final String onDelete;
    @NonNull
    public final String onUpdate;
    @NonNull
    public final List<String> referenceColumnNames;
    @NonNull
    public final String referenceTable;
    
    public ForeignKey(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @NonNull List<String> paramList1, @NonNull List<String> paramList2)
    {
      this.referenceTable = paramString1;
      this.onDelete = paramString2;
      this.onUpdate = paramString3;
      this.columnNames = Collections.unmodifiableList(paramList1);
      this.referenceColumnNames = Collections.unmodifiableList(paramList2);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (this == paramObject) {
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return bool1;
                  bool1 = bool2;
                } while (paramObject == null);
                bool1 = bool2;
              } while (getClass() != paramObject.getClass());
              paramObject = (ForeignKey)paramObject;
              bool1 = bool2;
            } while (!this.referenceTable.equals(((ForeignKey)paramObject).referenceTable));
            bool1 = bool2;
          } while (!this.onDelete.equals(((ForeignKey)paramObject).onDelete));
          bool1 = bool2;
        } while (!this.onUpdate.equals(((ForeignKey)paramObject).onUpdate));
        bool1 = bool2;
      } while (!this.columnNames.equals(((ForeignKey)paramObject).columnNames));
      return this.referenceColumnNames.equals(((ForeignKey)paramObject).referenceColumnNames);
    }
    
    public int hashCode()
    {
      return (((this.referenceTable.hashCode() * 31 + this.onDelete.hashCode()) * 31 + this.onUpdate.hashCode()) * 31 + this.columnNames.hashCode()) * 31 + this.referenceColumnNames.hashCode();
    }
    
    public String toString()
    {
      return "ForeignKey{referenceTable='" + this.referenceTable + '\'' + ", onDelete='" + this.onDelete + '\'' + ", onUpdate='" + this.onUpdate + '\'' + ", columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  static class ForeignKeyWithSequence
    implements Comparable<ForeignKeyWithSequence>
  {
    final String mFrom;
    final int mId;
    final int mSequence;
    final String mTo;
    
    ForeignKeyWithSequence(int paramInt1, int paramInt2, String paramString1, String paramString2)
    {
      this.mId = paramInt1;
      this.mSequence = paramInt2;
      this.mFrom = paramString1;
      this.mTo = paramString2;
    }
    
    public int compareTo(ForeignKeyWithSequence paramForeignKeyWithSequence)
    {
      int j = this.mId - paramForeignKeyWithSequence.mId;
      int i = j;
      if (j == 0) {
        i = this.mSequence - paramForeignKeyWithSequence.mSequence;
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\util\TableInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */