package android.arch.persistence.db;

public class SimpleSQLiteQuery
  implements SupportSQLiteQuery
{
  private final Object[] mBindArgs;
  private final String mQuery;
  
  public SimpleSQLiteQuery(String paramString)
  {
    this(paramString, null);
  }
  
  public SimpleSQLiteQuery(String paramString, Object[] paramArrayOfObject)
  {
    this.mQuery = paramString;
    this.mBindArgs = paramArrayOfObject;
  }
  
  private static void bind(SupportSQLiteProgram paramSupportSQLiteProgram, int paramInt, Object paramObject)
  {
    if (paramObject == null)
    {
      paramSupportSQLiteProgram.bindNull(paramInt);
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      paramSupportSQLiteProgram.bindBlob(paramInt, (byte[])paramObject);
      return;
    }
    if ((paramObject instanceof Float))
    {
      paramSupportSQLiteProgram.bindDouble(paramInt, ((Float)paramObject).floatValue());
      return;
    }
    if ((paramObject instanceof Double))
    {
      paramSupportSQLiteProgram.bindDouble(paramInt, ((Double)paramObject).doubleValue());
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramSupportSQLiteProgram.bindLong(paramInt, ((Long)paramObject).longValue());
      return;
    }
    if ((paramObject instanceof Integer))
    {
      paramSupportSQLiteProgram.bindLong(paramInt, ((Integer)paramObject).intValue());
      return;
    }
    if ((paramObject instanceof Short))
    {
      paramSupportSQLiteProgram.bindLong(paramInt, ((Short)paramObject).shortValue());
      return;
    }
    if ((paramObject instanceof Byte))
    {
      paramSupportSQLiteProgram.bindLong(paramInt, ((Byte)paramObject).byteValue());
      return;
    }
    if ((paramObject instanceof String))
    {
      paramSupportSQLiteProgram.bindString(paramInt, (String)paramObject);
      return;
    }
    throw new IllegalArgumentException("Cannot bind " + paramObject + " at index " + paramInt + " Supported types: null, byte[], float, double, long, int, short, byte," + " string");
  }
  
  public static void bind(SupportSQLiteProgram paramSupportSQLiteProgram, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {}
    for (;;)
    {
      return;
      int j = paramArrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        bind(paramSupportSQLiteProgram, i + 1, paramArrayOfObject[i]);
        i += 1;
      }
    }
  }
  
  public void bindTo(SupportSQLiteProgram paramSupportSQLiteProgram)
  {
    bind(paramSupportSQLiteProgram, this.mBindArgs);
  }
  
  public String getSql()
  {
    return this.mQuery;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SimpleSQLiteQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */