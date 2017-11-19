package com.bumptech.glide.load.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class LazyHeaders
  implements Headers
{
  private volatile Map<String, String> combinedHeaders;
  private final Map<String, List<LazyHeaderFactory>> headers;
  
  LazyHeaders(Map<String, List<LazyHeaderFactory>> paramMap)
  {
    this.headers = Collections.unmodifiableMap(paramMap);
  }
  
  private Map<String, String> generateHeaders()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.headers.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      List localList = (List)localEntry.getValue();
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        String str = ((LazyHeaderFactory)localList.get(i)).buildHeader();
        if (!TextUtils.isEmpty(str))
        {
          localStringBuilder.append(str);
          if (i != localList.size() - 1) {
            localStringBuilder.append(',');
          }
        }
        i += 1;
      }
      if (!TextUtils.isEmpty(localStringBuilder.toString())) {
        localHashMap.put(localEntry.getKey(), localStringBuilder.toString());
      }
    }
    return localHashMap;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof LazyHeaders))
    {
      paramObject = (LazyHeaders)paramObject;
      return this.headers.equals(((LazyHeaders)paramObject).headers);
    }
    return false;
  }
  
  public Map<String, String> getHeaders()
  {
    if (this.combinedHeaders == null) {}
    try
    {
      if (this.combinedHeaders == null) {
        this.combinedHeaders = Collections.unmodifiableMap(generateHeaders());
      }
      return this.combinedHeaders;
    }
    finally {}
  }
  
  public int hashCode()
  {
    return this.headers.hashCode();
  }
  
  public String toString()
  {
    return "LazyHeaders{headers=" + this.headers + '}';
  }
  
  public static final class Builder
  {
    private static final Map<String, List<LazyHeaderFactory>> DEFAULT_HEADERS;
    private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
    private static final String USER_AGENT_HEADER = "User-Agent";
    private boolean copyOnModify = true;
    private Map<String, List<LazyHeaderFactory>> headers = DEFAULT_HEADERS;
    private boolean isUserAgentDefault = true;
    
    static
    {
      HashMap localHashMap = new HashMap(2);
      if (!TextUtils.isEmpty(DEFAULT_USER_AGENT)) {
        localHashMap.put("User-Agent", Collections.singletonList(new LazyHeaders.StringHeaderFactory(DEFAULT_USER_AGENT)));
      }
      DEFAULT_HEADERS = Collections.unmodifiableMap(localHashMap);
    }
    
    private Map<String, List<LazyHeaderFactory>> copyHeaders()
    {
      HashMap localHashMap = new HashMap(this.headers.size());
      Iterator localIterator = this.headers.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHashMap.put(localEntry.getKey(), new ArrayList((Collection)localEntry.getValue()));
      }
      return localHashMap;
    }
    
    private void copyIfNecessary()
    {
      if (this.copyOnModify)
      {
        this.copyOnModify = false;
        this.headers = copyHeaders();
      }
    }
    
    private List<LazyHeaderFactory> getFactories(String paramString)
    {
      List localList = (List)this.headers.get(paramString);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.headers.put(paramString, localObject);
      }
      return (List<LazyHeaderFactory>)localObject;
    }
    
    public Builder addHeader(String paramString, LazyHeaderFactory paramLazyHeaderFactory)
    {
      if ((this.isUserAgentDefault) && ("User-Agent".equalsIgnoreCase(paramString))) {
        return setHeader(paramString, paramLazyHeaderFactory);
      }
      copyIfNecessary();
      getFactories(paramString).add(paramLazyHeaderFactory);
      return this;
    }
    
    public Builder addHeader(String paramString1, String paramString2)
    {
      return addHeader(paramString1, new LazyHeaders.StringHeaderFactory(paramString2));
    }
    
    public LazyHeaders build()
    {
      this.copyOnModify = true;
      return new LazyHeaders(this.headers);
    }
    
    public Builder setHeader(String paramString, LazyHeaderFactory paramLazyHeaderFactory)
    {
      copyIfNecessary();
      if (paramLazyHeaderFactory == null) {
        this.headers.remove(paramString);
      }
      for (;;)
      {
        if ((this.isUserAgentDefault) && ("User-Agent".equalsIgnoreCase(paramString))) {
          this.isUserAgentDefault = false;
        }
        return this;
        List localList = getFactories(paramString);
        localList.clear();
        localList.add(paramLazyHeaderFactory);
      }
    }
    
    public Builder setHeader(String paramString1, String paramString2)
    {
      if (paramString2 == null) {}
      for (paramString2 = null;; paramString2 = new LazyHeaders.StringHeaderFactory(paramString2)) {
        return setHeader(paramString1, paramString2);
      }
    }
  }
  
  static final class StringHeaderFactory
    implements LazyHeaderFactory
  {
    private final String value;
    
    StringHeaderFactory(String paramString)
    {
      this.value = paramString;
    }
    
    public String buildHeader()
    {
      return this.value;
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof StringHeaderFactory))
      {
        paramObject = (StringHeaderFactory)paramObject;
        return this.value.equals(((StringHeaderFactory)paramObject).value);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.value.hashCode();
    }
    
    public String toString()
    {
      return "StringHeaderFactory{value='" + this.value + '\'' + '}';
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\model\LazyHeaders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */