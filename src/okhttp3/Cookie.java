package okhttp3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;

public final class Cookie
{
  private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
  private static final Pattern MONTH_PATTERN;
  private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
  private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
  private final String domain;
  private final long expiresAt;
  private final boolean hostOnly;
  private final boolean httpOnly;
  private final String name;
  private final String path;
  private final boolean persistent;
  private final boolean secure;
  private final String value;
  
  static
  {
    MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
  }
  
  private Cookie(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.name = paramString1;
    this.value = paramString2;
    this.expiresAt = paramLong;
    this.domain = paramString3;
    this.path = paramString4;
    this.secure = paramBoolean1;
    this.httpOnly = paramBoolean2;
    this.hostOnly = paramBoolean3;
    this.persistent = paramBoolean4;
  }
  
  private Cookie(Builder paramBuilder)
  {
    if (paramBuilder.name == null) {
      throw new NullPointerException("builder.name == null");
    }
    if (paramBuilder.value == null) {
      throw new NullPointerException("builder.value == null");
    }
    if (paramBuilder.domain == null) {
      throw new NullPointerException("builder.domain == null");
    }
    this.name = paramBuilder.name;
    this.value = paramBuilder.value;
    this.expiresAt = paramBuilder.expiresAt;
    this.domain = paramBuilder.domain;
    this.path = paramBuilder.path;
    this.secure = paramBuilder.secure;
    this.httpOnly = paramBuilder.httpOnly;
    this.persistent = paramBuilder.persistent;
    this.hostOnly = paramBuilder.hostOnly;
  }
  
  private static int dateCharacterOffset(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    while (paramInt1 < paramInt2)
    {
      int i = paramString.charAt(paramInt1);
      if (((i < 32) && (i != 9)) || (i >= 127) || ((i >= 48) && (i <= 57)) || ((i >= 97) && (i <= 122)) || ((i >= 65) && (i <= 90)) || (i == 58))
      {
        i = 1;
        if (paramBoolean) {
          break label107;
        }
      }
      label107:
      for (int j = 1;; j = 0)
      {
        if (i != j) {
          break label113;
        }
        return paramInt1;
        i = 0;
        break;
      }
      label113:
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  private static boolean domainMatch(HttpUrl paramHttpUrl, String paramString)
  {
    paramHttpUrl = paramHttpUrl.host();
    if (paramHttpUrl.equals(paramString)) {}
    while ((paramHttpUrl.endsWith(paramString)) && (paramHttpUrl.charAt(paramHttpUrl.length() - paramString.length() - 1) == '.') && (!Util.verifyAsIpAddress(paramHttpUrl))) {
      return true;
    }
    return false;
  }
  
  static Cookie parse(long paramLong, HttpUrl paramHttpUrl, String paramString)
  {
    int j = paramString.length();
    int i = Util.delimiterOffset(paramString, 0, j, ';');
    int k = Util.delimiterOffset(paramString, 0, i, '=');
    if (k == i) {
      return null;
    }
    String str1 = Util.trimSubstring(paramString, 0, k);
    if (str1.isEmpty()) {
      return null;
    }
    String str2 = Util.trimSubstring(paramString, k + 1, i);
    l1 = 253402300799999L;
    l2 = -1L;
    localObject2 = null;
    localObject1 = null;
    bool4 = false;
    boolean bool5 = false;
    bool2 = true;
    bool3 = false;
    i += 1;
    String str3;
    Object localObject3;
    if (i < j)
    {
      k = Util.delimiterOffset(paramString, i, j, ';');
      int m = Util.delimiterOffset(paramString, i, k, '=');
      str3 = Util.trimSubstring(paramString, i, m);
      if (m < k)
      {
        localObject3 = Util.trimSubstring(paramString, m + 1, k);
        label162:
        if (!str3.equalsIgnoreCase("expires")) {
          break label252;
        }
      }
    }
    for (;;)
    {
      try
      {
        l3 = parseExpires((String)localObject3, 0, ((String)localObject3).length());
        bool1 = true;
        l4 = l2;
        bool7 = bool2;
        bool6 = bool4;
        localObject7 = localObject1;
        localObject3 = localObject2;
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        label252:
        long l3 = l1;
        Object localObject6 = localObject2;
        Object localObject7 = localObject1;
        boolean bool6 = bool4;
        boolean bool7 = bool2;
        boolean bool1 = bool3;
        long l4 = l2;
        continue;
      }
      i = k + 1;
      l1 = l3;
      localObject2 = localObject3;
      localObject1 = localObject7;
      bool4 = bool6;
      bool2 = bool7;
      bool3 = bool1;
      l2 = l4;
      break;
      localObject3 = "";
      break label162;
      if (str3.equalsIgnoreCase("max-age")) {}
      try
      {
        l4 = parseMaxAge((String)localObject3);
        bool1 = true;
        l3 = l1;
        localObject3 = localObject2;
        localObject7 = localObject1;
        bool6 = bool4;
        bool7 = bool2;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        l3 = l1;
        localObject4 = localObject2;
        localObject7 = localObject1;
        bool6 = bool4;
        bool7 = bool2;
        bool1 = bool3;
        l4 = l2;
      }
      if (str3.equalsIgnoreCase("domain")) {}
      try
      {
        localObject3 = parseDomain((String)localObject3);
        bool7 = false;
        l3 = l1;
        localObject7 = localObject1;
        bool6 = bool4;
        bool1 = bool3;
        l4 = l2;
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        Object localObject4;
        l3 = l1;
        Object localObject5 = localObject2;
        localObject7 = localObject1;
        bool6 = bool4;
        bool7 = bool2;
        bool1 = bool3;
        l4 = l2;
      }
      if (str3.equalsIgnoreCase("path"))
      {
        localObject7 = localObject3;
        l3 = l1;
        localObject3 = localObject2;
        bool6 = bool4;
        bool7 = bool2;
        bool1 = bool3;
        l4 = l2;
      }
      else if (str3.equalsIgnoreCase("secure"))
      {
        bool6 = true;
        l3 = l1;
        localObject3 = localObject2;
        localObject7 = localObject1;
        bool7 = bool2;
        bool1 = bool3;
        l4 = l2;
      }
      else
      {
        l3 = l1;
        localObject3 = localObject2;
        localObject7 = localObject1;
        bool6 = bool4;
        bool7 = bool2;
        bool1 = bool3;
        l4 = l2;
        if (str3.equalsIgnoreCase("httponly"))
        {
          bool5 = true;
          l3 = l1;
          localObject3 = localObject2;
          localObject7 = localObject1;
          bool6 = bool4;
          bool7 = bool2;
          bool1 = bool3;
          l4 = l2;
          continue;
          if (l2 == Long.MIN_VALUE)
          {
            l1 = Long.MIN_VALUE;
            if (localObject2 == null)
            {
              localObject3 = paramHttpUrl.host();
              if (localObject1 != null)
              {
                paramString = (String)localObject1;
                if (((String)localObject1).startsWith("/")) {}
              }
              else
              {
                paramHttpUrl = paramHttpUrl.encodedPath();
                i = paramHttpUrl.lastIndexOf('/');
                if (i == 0) {
                  continue;
                }
                paramString = paramHttpUrl.substring(0, i);
              }
              return new Cookie(str1, str2, l1, (String)localObject3, paramString, bool4, bool5, bool2, bool3);
            }
          }
          else
          {
            if (l2 == -1L) {
              continue;
            }
            if (l2 <= 9223372036854775L)
            {
              l1 = l2 * 1000L;
              l2 = paramLong + l1;
              if (l2 >= paramLong)
              {
                l1 = l2;
                if (l2 <= 253402300799999L) {
                  continue;
                }
              }
              l1 = 253402300799999L;
              continue;
            }
            l1 = Long.MAX_VALUE;
            continue;
          }
          localObject3 = localObject2;
          if (!domainMatch(paramHttpUrl, (String)localObject2))
          {
            return null;
            paramString = "/";
            continue;
          }
        }
      }
    }
  }
  
  public static Cookie parse(HttpUrl paramHttpUrl, String paramString)
  {
    return parse(System.currentTimeMillis(), paramHttpUrl, paramString);
  }
  
  public static List<Cookie> parseAll(HttpUrl paramHttpUrl, Headers paramHeaders)
  {
    List localList = paramHeaders.values("Set-Cookie");
    paramHeaders = null;
    int i = 0;
    int j = localList.size();
    if (i < j)
    {
      Cookie localCookie = parse(paramHttpUrl, (String)localList.get(i));
      if (localCookie == null) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject = paramHeaders;
        if (paramHeaders == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(localCookie);
        paramHeaders = (Headers)localObject;
      }
    }
    if (paramHeaders != null) {
      return Collections.unmodifiableList(paramHeaders);
    }
    return Collections.emptyList();
  }
  
  private static String parseDomain(String paramString)
  {
    if (paramString.endsWith(".")) {
      throw new IllegalArgumentException();
    }
    String str = paramString;
    if (paramString.startsWith(".")) {
      str = paramString.substring(1);
    }
    paramString = Util.domainToAscii(str);
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    return paramString;
  }
  
  private static long parseExpires(String paramString, int paramInt1, int paramInt2)
  {
    int i1 = dateCharacterOffset(paramString, paramInt1, paramInt2, false);
    int n = -1;
    int m = -1;
    int j = -1;
    int k = -1;
    int i = -1;
    paramInt1 = -1;
    Matcher localMatcher = TIME_PATTERN.matcher(paramString);
    if (i1 < paramInt2)
    {
      int i7 = dateCharacterOffset(paramString, i1 + 1, paramInt2, true);
      localMatcher.region(i1, i7);
      int i6;
      int i3;
      int i4;
      int i2;
      int i5;
      if ((n == -1) && (localMatcher.usePattern(TIME_PATTERN).matches()))
      {
        i6 = Integer.parseInt(localMatcher.group(1));
        i1 = Integer.parseInt(localMatcher.group(2));
        i3 = Integer.parseInt(localMatcher.group(3));
        i4 = paramInt1;
        i2 = i;
        i5 = k;
      }
      for (;;)
      {
        i7 = dateCharacterOffset(paramString, i7 + 1, paramInt2, false);
        k = i5;
        n = i6;
        m = i1;
        i = i2;
        j = i3;
        paramInt1 = i4;
        i1 = i7;
        break;
        if ((k == -1) && (localMatcher.usePattern(DAY_OF_MONTH_PATTERN).matches()))
        {
          i5 = Integer.parseInt(localMatcher.group(1));
          i6 = n;
          i1 = m;
          i2 = i;
          i3 = j;
          i4 = paramInt1;
        }
        else if ((i == -1) && (localMatcher.usePattern(MONTH_PATTERN).matches()))
        {
          String str = localMatcher.group(1).toLowerCase(Locale.US);
          i2 = MONTH_PATTERN.pattern().indexOf(str) / 4;
          i5 = k;
          i6 = n;
          i1 = m;
          i3 = j;
          i4 = paramInt1;
        }
        else
        {
          i5 = k;
          i6 = n;
          i1 = m;
          i2 = i;
          i3 = j;
          i4 = paramInt1;
          if (paramInt1 == -1)
          {
            i5 = k;
            i6 = n;
            i1 = m;
            i2 = i;
            i3 = j;
            i4 = paramInt1;
            if (localMatcher.usePattern(YEAR_PATTERN).matches())
            {
              i4 = Integer.parseInt(localMatcher.group(1));
              i5 = k;
              i6 = n;
              i1 = m;
              i2 = i;
              i3 = j;
            }
          }
        }
      }
    }
    paramInt2 = paramInt1;
    if (paramInt1 >= 70)
    {
      paramInt2 = paramInt1;
      if (paramInt1 <= 99) {
        paramInt2 = paramInt1 + 1900;
      }
    }
    paramInt1 = paramInt2;
    if (paramInt2 >= 0)
    {
      paramInt1 = paramInt2;
      if (paramInt2 <= 69) {
        paramInt1 = paramInt2 + 2000;
      }
    }
    if (paramInt1 < 1601) {
      throw new IllegalArgumentException();
    }
    if (i == -1) {
      throw new IllegalArgumentException();
    }
    if ((k < 1) || (k > 31)) {
      throw new IllegalArgumentException();
    }
    if ((n < 0) || (n > 23)) {
      throw new IllegalArgumentException();
    }
    if ((m < 0) || (m > 59)) {
      throw new IllegalArgumentException();
    }
    if ((j < 0) || (j > 59)) {
      throw new IllegalArgumentException();
    }
    paramString = new GregorianCalendar(Util.UTC);
    paramString.setLenient(false);
    paramString.set(1, paramInt1);
    paramString.set(2, i - 1);
    paramString.set(5, k);
    paramString.set(11, n);
    paramString.set(12, m);
    paramString.set(13, j);
    paramString.set(14, 0);
    return paramString.getTimeInMillis();
  }
  
  private static long parseMaxAge(String paramString)
  {
    long l1 = Long.MIN_VALUE;
    try
    {
      long l2 = Long.parseLong(paramString);
      l1 = l2;
      l2 = l1;
      if (l1 <= 0L) {
        l2 = Long.MIN_VALUE;
      }
      return l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      if (paramString.matches("-?\\d+"))
      {
        if (paramString.startsWith("-")) {}
        for (;;)
        {
          return l1;
          l1 = Long.MAX_VALUE;
        }
      }
      throw localNumberFormatException;
    }
  }
  
  private static boolean pathMatch(HttpUrl paramHttpUrl, String paramString)
  {
    paramHttpUrl = paramHttpUrl.encodedPath();
    if (paramHttpUrl.equals(paramString)) {}
    while ((paramHttpUrl.startsWith(paramString)) && ((paramString.endsWith("/")) || (paramHttpUrl.charAt(paramString.length()) == '/'))) {
      return true;
    }
    return false;
  }
  
  public String domain()
  {
    return this.domain;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Cookie)) {}
    do
    {
      return false;
      paramObject = (Cookie)paramObject;
    } while ((!((Cookie)paramObject).name.equals(this.name)) || (!((Cookie)paramObject).value.equals(this.value)) || (!((Cookie)paramObject).domain.equals(this.domain)) || (!((Cookie)paramObject).path.equals(this.path)) || (((Cookie)paramObject).expiresAt != this.expiresAt) || (((Cookie)paramObject).secure != this.secure) || (((Cookie)paramObject).httpOnly != this.httpOnly) || (((Cookie)paramObject).persistent != this.persistent) || (((Cookie)paramObject).hostOnly != this.hostOnly));
    return true;
  }
  
  public long expiresAt()
  {
    return this.expiresAt;
  }
  
  public int hashCode()
  {
    int m = 0;
    int n = this.name.hashCode();
    int i1 = this.value.hashCode();
    int i2 = this.domain.hashCode();
    int i3 = this.path.hashCode();
    int i4 = (int)(this.expiresAt ^ this.expiresAt >>> 32);
    int i;
    int j;
    label72:
    int k;
    if (this.secure)
    {
      i = 0;
      if (!this.httpOnly) {
        break label145;
      }
      j = 0;
      if (!this.persistent) {
        break label150;
      }
      k = 0;
      label81:
      if (!this.hostOnly) {
        break label155;
      }
    }
    for (;;)
    {
      return ((((((((n + 527) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i) * 31 + j) * 31 + k) * 31 + m;
      i = 1;
      break;
      label145:
      j = 1;
      break label72;
      label150:
      k = 1;
      break label81;
      label155:
      m = 1;
    }
  }
  
  public boolean hostOnly()
  {
    return this.hostOnly;
  }
  
  public boolean httpOnly()
  {
    return this.httpOnly;
  }
  
  public boolean matches(HttpUrl paramHttpUrl)
  {
    boolean bool;
    if (this.hostOnly)
    {
      bool = paramHttpUrl.host().equals(this.domain);
      if (bool) {
        break label37;
      }
    }
    label37:
    while ((!pathMatch(paramHttpUrl, this.path)) || ((this.secure) && (!paramHttpUrl.isHttps())))
    {
      return false;
      bool = domainMatch(paramHttpUrl, this.domain);
      break;
    }
    return true;
  }
  
  public String name()
  {
    return this.name;
  }
  
  public String path()
  {
    return this.path;
  }
  
  public boolean persistent()
  {
    return this.persistent;
  }
  
  public boolean secure()
  {
    return this.secure;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append('=');
    localStringBuilder.append(this.value);
    if (this.persistent)
    {
      if (this.expiresAt != Long.MIN_VALUE) {
        break label131;
      }
      localStringBuilder.append("; max-age=0");
    }
    for (;;)
    {
      if (!this.hostOnly) {
        localStringBuilder.append("; domain=").append(this.domain);
      }
      localStringBuilder.append("; path=").append(this.path);
      if (this.secure) {
        localStringBuilder.append("; secure");
      }
      if (this.httpOnly) {
        localStringBuilder.append("; httponly");
      }
      return localStringBuilder.toString();
      label131:
      localStringBuilder.append("; expires=").append(HttpDate.format(new Date(this.expiresAt)));
    }
  }
  
  public String value()
  {
    return this.value;
  }
  
  public static final class Builder
  {
    String domain;
    long expiresAt = 253402300799999L;
    boolean hostOnly;
    boolean httpOnly;
    String name;
    String path = "/";
    boolean persistent;
    boolean secure;
    String value;
    
    private Builder domain(String paramString, boolean paramBoolean)
    {
      if (paramString == null) {
        throw new NullPointerException("domain == null");
      }
      String str = Util.domainToAscii(paramString);
      if (str == null) {
        throw new IllegalArgumentException("unexpected domain: " + paramString);
      }
      this.domain = str;
      this.hostOnly = paramBoolean;
      return this;
    }
    
    public Cookie build()
    {
      return new Cookie(this, null);
    }
    
    public Builder domain(String paramString)
    {
      return domain(paramString, false);
    }
    
    public Builder expiresAt(long paramLong)
    {
      long l = paramLong;
      if (paramLong <= 0L) {
        l = Long.MIN_VALUE;
      }
      paramLong = l;
      if (l > 253402300799999L) {
        paramLong = 253402300799999L;
      }
      this.expiresAt = paramLong;
      this.persistent = true;
      return this;
    }
    
    public Builder hostOnlyDomain(String paramString)
    {
      return domain(paramString, true);
    }
    
    public Builder httpOnly()
    {
      this.httpOnly = true;
      return this;
    }
    
    public Builder name(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException("name == null");
      }
      if (!paramString.trim().equals(paramString)) {
        throw new IllegalArgumentException("name is not trimmed");
      }
      this.name = paramString;
      return this;
    }
    
    public Builder path(String paramString)
    {
      if (!paramString.startsWith("/")) {
        throw new IllegalArgumentException("path must start with '/'");
      }
      this.path = paramString;
      return this;
    }
    
    public Builder secure()
    {
      this.secure = true;
      return this;
    }
    
    public Builder value(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException("value == null");
      }
      if (!paramString.trim().equals(paramString)) {
        throw new IllegalArgumentException("value is not trimmed");
      }
      this.value = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\Cookie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */