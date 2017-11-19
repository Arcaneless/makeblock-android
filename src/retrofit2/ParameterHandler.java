package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import okhttp3.Headers;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;

abstract class ParameterHandler<T>
{
  abstract void apply(RequestBuilder paramRequestBuilder, T paramT)
    throws IOException;
  
  final ParameterHandler<Object> array()
  {
    new ParameterHandler()
    {
      void apply(RequestBuilder paramAnonymousRequestBuilder, Object paramAnonymousObject)
        throws IOException
      {
        if (paramAnonymousObject == null) {}
        for (;;)
        {
          return;
          int i = 0;
          int j = Array.getLength(paramAnonymousObject);
          while (i < j)
          {
            ParameterHandler.this.apply(paramAnonymousRequestBuilder, Array.get(paramAnonymousObject, i));
            i += 1;
          }
        }
      }
    };
  }
  
  final ParameterHandler<Iterable<T>> iterable()
  {
    new ParameterHandler()
    {
      void apply(RequestBuilder paramAnonymousRequestBuilder, Iterable<T> paramAnonymousIterable)
        throws IOException
      {
        if (paramAnonymousIterable == null) {}
        for (;;)
        {
          return;
          paramAnonymousIterable = paramAnonymousIterable.iterator();
          while (paramAnonymousIterable.hasNext())
          {
            Object localObject = paramAnonymousIterable.next();
            ParameterHandler.this.apply(paramAnonymousRequestBuilder, localObject);
          }
        }
      }
    };
  }
  
  static final class Body<T>
    extends ParameterHandler<T>
  {
    private final Converter<T, RequestBody> converter;
    
    Body(Converter<T, RequestBody> paramConverter)
    {
      this.converter = paramConverter;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
    {
      if (paramT == null) {
        throw new IllegalArgumentException("Body parameter value must not be null.");
      }
      try
      {
        RequestBody localRequestBody = (RequestBody)this.converter.convert(paramT);
        paramRequestBuilder.setBody(localRequestBody);
        return;
      }
      catch (IOException paramRequestBuilder)
      {
        throw new RuntimeException("Unable to convert " + paramT + " to RequestBody", paramRequestBuilder);
      }
    }
  }
  
  static final class Field<T>
    extends ParameterHandler<T>
  {
    private final boolean encoded;
    private final String name;
    private final Converter<T, String> valueConverter;
    
    Field(String paramString, Converter<T, String> paramConverter, boolean paramBoolean)
    {
      this.name = ((String)Utils.checkNotNull(paramString, "name == null"));
      this.valueConverter = paramConverter;
      this.encoded = paramBoolean;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
      throws IOException
    {
      if (paramT == null) {
        return;
      }
      paramRequestBuilder.addFormField(this.name, (String)this.valueConverter.convert(paramT), this.encoded);
    }
  }
  
  static final class FieldMap<T>
    extends ParameterHandler<Map<String, T>>
  {
    private final boolean encoded;
    private final Converter<T, String> valueConverter;
    
    FieldMap(Converter<T, String> paramConverter, boolean paramBoolean)
    {
      this.valueConverter = paramConverter;
      this.encoded = paramBoolean;
    }
    
    void apply(RequestBuilder paramRequestBuilder, Map<String, T> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        throw new IllegalArgumentException("Field map was null.");
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        if (str == null) {
          throw new IllegalArgumentException("Field map contained null key.");
        }
        localObject = ((Map.Entry)localObject).getValue();
        if (localObject == null) {
          throw new IllegalArgumentException("Field map contained null value for key '" + str + "'.");
        }
        paramRequestBuilder.addFormField(str, (String)this.valueConverter.convert(localObject), this.encoded);
      }
    }
  }
  
  static final class Header<T>
    extends ParameterHandler<T>
  {
    private final String name;
    private final Converter<T, String> valueConverter;
    
    Header(String paramString, Converter<T, String> paramConverter)
    {
      this.name = ((String)Utils.checkNotNull(paramString, "name == null"));
      this.valueConverter = paramConverter;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
      throws IOException
    {
      if (paramT == null) {
        return;
      }
      paramRequestBuilder.addHeader(this.name, (String)this.valueConverter.convert(paramT));
    }
  }
  
  static final class HeaderMap<T>
    extends ParameterHandler<Map<String, T>>
  {
    private final Converter<T, String> valueConverter;
    
    HeaderMap(Converter<T, String> paramConverter)
    {
      this.valueConverter = paramConverter;
    }
    
    void apply(RequestBuilder paramRequestBuilder, Map<String, T> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        throw new IllegalArgumentException("Header map was null.");
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        if (str == null) {
          throw new IllegalArgumentException("Header map contained null key.");
        }
        localObject = ((Map.Entry)localObject).getValue();
        if (localObject == null) {
          throw new IllegalArgumentException("Header map contained null value for key '" + str + "'.");
        }
        paramRequestBuilder.addHeader(str, (String)this.valueConverter.convert(localObject));
      }
    }
  }
  
  static final class Part<T>
    extends ParameterHandler<T>
  {
    private final Converter<T, RequestBody> converter;
    private final Headers headers;
    
    Part(Headers paramHeaders, Converter<T, RequestBody> paramConverter)
    {
      this.headers = paramHeaders;
      this.converter = paramConverter;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
    {
      if (paramT == null) {
        return;
      }
      try
      {
        RequestBody localRequestBody = (RequestBody)this.converter.convert(paramT);
        paramRequestBuilder.addPart(this.headers, localRequestBody);
        return;
      }
      catch (IOException paramRequestBuilder)
      {
        throw new RuntimeException("Unable to convert " + paramT + " to RequestBody", paramRequestBuilder);
      }
    }
  }
  
  static final class PartMap<T>
    extends ParameterHandler<Map<String, T>>
  {
    private final String transferEncoding;
    private final Converter<T, RequestBody> valueConverter;
    
    PartMap(Converter<T, RequestBody> paramConverter, String paramString)
    {
      this.valueConverter = paramConverter;
      this.transferEncoding = paramString;
    }
    
    void apply(RequestBuilder paramRequestBuilder, Map<String, T> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        throw new IllegalArgumentException("Part map was null.");
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        if (str == null) {
          throw new IllegalArgumentException("Part map contained null key.");
        }
        localObject = ((Map.Entry)localObject).getValue();
        if (localObject == null) {
          throw new IllegalArgumentException("Part map contained null value for key '" + str + "'.");
        }
        paramRequestBuilder.addPart(Headers.of(new String[] { "Content-Disposition", "form-data; name=\"" + str + "\"", "Content-Transfer-Encoding", this.transferEncoding }), (RequestBody)this.valueConverter.convert(localObject));
      }
    }
  }
  
  static final class Path<T>
    extends ParameterHandler<T>
  {
    private final boolean encoded;
    private final String name;
    private final Converter<T, String> valueConverter;
    
    Path(String paramString, Converter<T, String> paramConverter, boolean paramBoolean)
    {
      this.name = ((String)Utils.checkNotNull(paramString, "name == null"));
      this.valueConverter = paramConverter;
      this.encoded = paramBoolean;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
      throws IOException
    {
      if (paramT == null) {
        throw new IllegalArgumentException("Path parameter \"" + this.name + "\" value must not be null.");
      }
      paramRequestBuilder.addPathParam(this.name, (String)this.valueConverter.convert(paramT), this.encoded);
    }
  }
  
  static final class Query<T>
    extends ParameterHandler<T>
  {
    private final boolean encoded;
    private final String name;
    private final Converter<T, String> valueConverter;
    
    Query(String paramString, Converter<T, String> paramConverter, boolean paramBoolean)
    {
      this.name = ((String)Utils.checkNotNull(paramString, "name == null"));
      this.valueConverter = paramConverter;
      this.encoded = paramBoolean;
    }
    
    void apply(RequestBuilder paramRequestBuilder, T paramT)
      throws IOException
    {
      if (paramT == null) {
        return;
      }
      paramRequestBuilder.addQueryParam(this.name, (String)this.valueConverter.convert(paramT), this.encoded);
    }
  }
  
  static final class QueryMap<T>
    extends ParameterHandler<Map<String, T>>
  {
    private final boolean encoded;
    private final Converter<T, String> valueConverter;
    
    QueryMap(Converter<T, String> paramConverter, boolean paramBoolean)
    {
      this.valueConverter = paramConverter;
      this.encoded = paramBoolean;
    }
    
    void apply(RequestBuilder paramRequestBuilder, Map<String, T> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        throw new IllegalArgumentException("Query map was null.");
      }
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        if (str == null) {
          throw new IllegalArgumentException("Query map contained null key.");
        }
        localObject = ((Map.Entry)localObject).getValue();
        if (localObject == null) {
          throw new IllegalArgumentException("Query map contained null value for key '" + str + "'.");
        }
        paramRequestBuilder.addQueryParam(str, (String)this.valueConverter.convert(localObject), this.encoded);
      }
    }
  }
  
  static final class RawPart
    extends ParameterHandler<MultipartBody.Part>
  {
    static final RawPart INSTANCE = new RawPart();
    
    void apply(RequestBuilder paramRequestBuilder, MultipartBody.Part paramPart)
      throws IOException
    {
      if (paramPart != null) {
        paramRequestBuilder.addPart(paramPart);
      }
    }
  }
  
  static final class RelativeUrl
    extends ParameterHandler<Object>
  {
    void apply(RequestBuilder paramRequestBuilder, Object paramObject)
    {
      paramRequestBuilder.setRelativeUrl(paramObject);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\ParameterHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */