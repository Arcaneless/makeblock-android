package okhttp3.internal.cache;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor
  implements Interceptor
{
  private static final ResponseBody EMPTY_BODY = new ResponseBody()
  {
    public long contentLength()
    {
      return 0L;
    }
    
    public MediaType contentType()
    {
      return null;
    }
    
    public BufferedSource source()
    {
      return new Buffer();
    }
  };
  final InternalCache cache;
  
  public CacheInterceptor(InternalCache paramInternalCache)
  {
    this.cache = paramInternalCache;
  }
  
  private Response cacheWritingResponse(final CacheRequest paramCacheRequest, Response paramResponse)
    throws IOException
  {
    if (paramCacheRequest == null) {}
    Sink localSink;
    do
    {
      return paramResponse;
      localSink = paramCacheRequest.body();
    } while (localSink == null);
    paramCacheRequest = new Source()
    {
      boolean cacheRequestClosed;
      
      public void close()
        throws IOException
      {
        if ((!this.cacheRequestClosed) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS)))
        {
          this.cacheRequestClosed = true;
          paramCacheRequest.abort();
        }
        this.val$source.close();
      }
      
      public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        try
        {
          paramAnonymousLong = this.val$source.read(paramAnonymousBuffer, paramAnonymousLong);
          if (paramAnonymousLong == -1L)
          {
            if (!this.cacheRequestClosed)
            {
              this.cacheRequestClosed = true;
              this.val$cacheBody.close();
            }
            return -1L;
          }
        }
        catch (IOException paramAnonymousBuffer)
        {
          if (!this.cacheRequestClosed)
          {
            this.cacheRequestClosed = true;
            paramCacheRequest.abort();
          }
          throw paramAnonymousBuffer;
        }
        paramAnonymousBuffer.copyTo(this.val$cacheBody.buffer(), paramAnonymousBuffer.size() - paramAnonymousLong, paramAnonymousLong);
        this.val$cacheBody.emitCompleteSegments();
        return paramAnonymousLong;
      }
      
      public Timeout timeout()
      {
        return this.val$source.timeout();
      }
    };
    return paramResponse.newBuilder().body(new RealResponseBody(paramResponse.headers(), Okio.buffer(paramCacheRequest))).build();
  }
  
  private static Headers combine(Headers paramHeaders1, Headers paramHeaders2)
  {
    Headers.Builder localBuilder = new Headers.Builder();
    int i = 0;
    int j = paramHeaders1.size();
    if (i < j)
    {
      String str1 = paramHeaders1.name(i);
      String str2 = paramHeaders1.value(i);
      if (("Warning".equalsIgnoreCase(str1)) && (str2.startsWith("1"))) {}
      for (;;)
      {
        i += 1;
        break;
        if ((!isEndToEnd(str1)) || (paramHeaders2.get(str1) == null)) {
          Internal.instance.addLenient(localBuilder, str1, str2);
        }
      }
    }
    i = 0;
    j = paramHeaders2.size();
    if (i < j)
    {
      paramHeaders1 = paramHeaders2.name(i);
      if ("Content-Length".equalsIgnoreCase(paramHeaders1)) {}
      for (;;)
      {
        i += 1;
        break;
        if (isEndToEnd(paramHeaders1)) {
          Internal.instance.addLenient(localBuilder, paramHeaders1, paramHeaders2.value(i));
        }
      }
    }
    return localBuilder.build();
  }
  
  static boolean isEndToEnd(String paramString)
  {
    return (!"Connection".equalsIgnoreCase(paramString)) && (!"Keep-Alive".equalsIgnoreCase(paramString)) && (!"Proxy-Authenticate".equalsIgnoreCase(paramString)) && (!"Proxy-Authorization".equalsIgnoreCase(paramString)) && (!"TE".equalsIgnoreCase(paramString)) && (!"Trailers".equalsIgnoreCase(paramString)) && (!"Transfer-Encoding".equalsIgnoreCase(paramString)) && (!"Upgrade".equalsIgnoreCase(paramString));
  }
  
  private CacheRequest maybeCache(Response paramResponse, Request paramRequest, InternalCache paramInternalCache)
    throws IOException
  {
    if (paramInternalCache == null) {}
    do
    {
      return null;
      if (CacheStrategy.isCacheable(paramResponse, paramRequest)) {
        break;
      }
    } while (!HttpMethod.invalidatesCache(paramRequest.method()));
    try
    {
      paramInternalCache.remove(paramRequest);
      return null;
    }
    catch (IOException paramResponse)
    {
      return null;
    }
    return paramInternalCache.put(paramResponse);
  }
  
  private static Response stripBody(Response paramResponse)
  {
    Response localResponse = paramResponse;
    if (paramResponse != null)
    {
      localResponse = paramResponse;
      if (paramResponse.body() != null) {
        localResponse = paramResponse.newBuilder().body(null).build();
      }
    }
    return localResponse;
  }
  
  private static boolean validate(Response paramResponse1, Response paramResponse2)
  {
    if (paramResponse2.code() == 304) {}
    do
    {
      return true;
      paramResponse1 = paramResponse1.headers().getDate("Last-Modified");
      if (paramResponse1 == null) {
        break;
      }
      paramResponse2 = paramResponse2.headers().getDate("Last-Modified");
    } while ((paramResponse2 != null) && (paramResponse2.getTime() < paramResponse1.getTime()));
    return false;
  }
  
  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    Response localResponse1;
    Object localObject;
    Request localRequest;
    Response localResponse2;
    if (this.cache != null)
    {
      localResponse1 = this.cache.get(paramChain.request());
      localObject = new CacheStrategy.Factory(System.currentTimeMillis(), paramChain.request(), localResponse1).get();
      localRequest = ((CacheStrategy)localObject).networkRequest;
      localResponse2 = ((CacheStrategy)localObject).cacheResponse;
      if (this.cache != null) {
        this.cache.trackResponse((CacheStrategy)localObject);
      }
      if ((localResponse1 != null) && (localResponse2 == null)) {
        Util.closeQuietly(localResponse1.body());
      }
      if ((localRequest != null) || (localResponse2 != null)) {
        break label163;
      }
      paramChain = new Response.Builder().request(paramChain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
    }
    label163:
    label315:
    do
    {
      return paramChain;
      localResponse1 = null;
      break;
      if (localRequest == null) {
        return localResponse2.newBuilder().cacheResponse(stripBody(localResponse2)).build();
      }
      try
      {
        localObject = paramChain.proceed(localRequest);
        if ((localObject == null) && (localResponse1 != null)) {
          Util.closeQuietly(localResponse1.body());
        }
        if (localResponse2 == null) {
          break label315;
        }
        if (validate(localResponse2, (Response)localObject))
        {
          paramChain = localResponse2.newBuilder().headers(combine(localResponse2.headers(), ((Response)localObject).headers())).cacheResponse(stripBody(localResponse2)).networkResponse(stripBody((Response)localObject)).build();
          ((Response)localObject).body().close();
          this.cache.trackConditionalCacheHit();
          this.cache.update(localResponse2, paramChain);
          return paramChain;
        }
      }
      finally
      {
        if ((0 == 0) && (localResponse1 != null)) {
          Util.closeQuietly(localResponse1.body());
        }
      }
      Util.closeQuietly(localResponse2.body());
      localResponse1 = ((Response)localObject).newBuilder().cacheResponse(stripBody(localResponse2)).networkResponse(stripBody((Response)localObject)).build();
      paramChain = localResponse1;
    } while (!HttpHeaders.hasBody(localResponse1));
    return cacheWritingResponse(maybeCache(localResponse1, ((Response)localObject).request(), this.cache), localResponse1);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\cache\CacheInterceptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */