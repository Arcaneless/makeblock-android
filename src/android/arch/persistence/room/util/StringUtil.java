package android.arch.persistence.room.util;

import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil
{
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  
  public static void appendPlaceholders(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append("?");
      if (i < paramInt - 1) {
        paramStringBuilder.append(",");
      }
      i += 1;
    }
  }
  
  @Nullable
  public static String joinIntoString(@Nullable List<Integer> paramList)
  {
    if (paramList == null) {
      return null;
    }
    int j = paramList.size();
    if (j == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(Integer.toString(((Integer)paramList.get(i)).intValue()));
      if (i < j - 1) {
        localStringBuilder.append(",");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static StringBuilder newStringBuilder()
  {
    return new StringBuilder();
  }
  
  @Nullable
  public static List<Integer> splitToIntList(@Nullable String paramString)
  {
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    ArrayList localArrayList = new ArrayList();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    for (;;)
    {
      paramString = localArrayList;
      if (!localStringTokenizer.hasMoreElements()) {
        break;
      }
      paramString = localStringTokenizer.nextToken();
      try
      {
        localArrayList.add(Integer.valueOf(Integer.parseInt(paramString)));
      }
      catch (NumberFormatException paramString)
      {
        Log.e("ROOM", "Malformed integer list", paramString);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\util\StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */