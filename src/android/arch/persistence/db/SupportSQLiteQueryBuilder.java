package android.arch.persistence.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupportSQLiteQueryBuilder
{
  private static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
  private Object[] mBindArgs;
  private String[] mColumns = null;
  private boolean mDistinct = false;
  private String mGroupBy = null;
  private String mHaving = null;
  private String mLimit = null;
  private String mOrderBy = null;
  private String mSelection;
  private final String mTable;
  
  private SupportSQLiteQueryBuilder(String paramString)
  {
    this.mTable = paramString;
  }
  
  private static void appendClause(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    if (!isEmpty(paramString2))
    {
      paramStringBuilder.append(paramString1);
      paramStringBuilder.append(paramString2);
    }
  }
  
  private static void appendColumns(StringBuilder paramStringBuilder, String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (i > 0) {
        paramStringBuilder.append(", ");
      }
      paramStringBuilder.append(str);
      i += 1;
    }
    paramStringBuilder.append(' ');
  }
  
  public static SupportSQLiteQueryBuilder builder(String paramString)
  {
    return new SupportSQLiteQueryBuilder(paramString);
  }
  
  private static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public SupportSQLiteQueryBuilder columns(String[] paramArrayOfString)
  {
    this.mColumns = paramArrayOfString;
    return this;
  }
  
  public SupportSQLiteQuery create()
  {
    if ((isEmpty(this.mGroupBy)) && (!isEmpty(this.mHaving))) {
      throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
    }
    StringBuilder localStringBuilder = new StringBuilder(120);
    localStringBuilder.append("SELECT ");
    if (this.mDistinct) {
      localStringBuilder.append("DISTINCT ");
    }
    if ((this.mColumns != null) && (this.mColumns.length != 0)) {
      appendColumns(localStringBuilder, this.mColumns);
    }
    for (;;)
    {
      localStringBuilder.append(" FROM ");
      localStringBuilder.append(this.mTable);
      appendClause(localStringBuilder, " WHERE ", this.mSelection);
      appendClause(localStringBuilder, " GROUP BY ", this.mGroupBy);
      appendClause(localStringBuilder, " HAVING ", this.mHaving);
      appendClause(localStringBuilder, " ORDER BY ", this.mOrderBy);
      appendClause(localStringBuilder, " LIMIT ", this.mLimit);
      return new SimpleSQLiteQuery(localStringBuilder.toString(), this.mBindArgs);
      localStringBuilder.append(" * ");
    }
  }
  
  public SupportSQLiteQueryBuilder distinct()
  {
    this.mDistinct = true;
    return this;
  }
  
  public SupportSQLiteQueryBuilder groupBy(String paramString)
  {
    this.mGroupBy = paramString;
    return this;
  }
  
  public SupportSQLiteQueryBuilder having(String paramString)
  {
    this.mHaving = paramString;
    return this;
  }
  
  public SupportSQLiteQueryBuilder limit(String paramString)
  {
    if ((!isEmpty(paramString)) && (!sLimitPattern.matcher(paramString).matches())) {
      throw new IllegalArgumentException("invalid LIMIT clauses:" + paramString);
    }
    this.mLimit = paramString;
    return this;
  }
  
  public SupportSQLiteQueryBuilder orderBy(String paramString)
  {
    this.mOrderBy = paramString;
    return this;
  }
  
  public SupportSQLiteQueryBuilder selection(String paramString, Object[] paramArrayOfObject)
  {
    this.mSelection = paramString;
    this.mBindArgs = paramArrayOfObject;
    return this;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SupportSQLiteQueryBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */