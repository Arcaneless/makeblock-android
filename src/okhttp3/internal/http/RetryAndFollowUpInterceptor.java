package okhttp3.internal.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;

public final class RetryAndFollowUpInterceptor
  implements Interceptor
{
  private static final int MAX_FOLLOW_UPS = 20;
  private volatile boolean canceled;
  private final OkHttpClient client;
  private boolean forWebSocket;
  private StreamAllocation streamAllocation;
  
  public RetryAndFollowUpInterceptor(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }
  
  private Address createAddress(HttpUrl paramHttpUrl)
  {
    SSLSocketFactory localSSLSocketFactory = null;
    HostnameVerifier localHostnameVerifier = null;
    CertificatePinner localCertificatePinner = null;
    if (paramHttpUrl.isHttps())
    {
      localSSLSocketFactory = this.client.sslSocketFactory();
      localHostnameVerifier = this.client.hostnameVerifier();
      localCertificatePinner = this.client.certificatePinner();
    }
    return new Address(paramHttpUrl.host(), paramHttpUrl.port(), this.client.dns(), this.client.socketFactory(), localSSLSocketFactory, localHostnameVerifier, localCertificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
  }
  
  private Request followUpRequest(Response paramResponse)
    throws IOException
  {
    if (paramResponse == null) {
      throw new IllegalStateException();
    }
    Object localObject1 = this.streamAllocation.connection();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((Connection)localObject1).route();
      int i = paramResponse.code();
      localObject2 = paramResponse.request().method();
      switch (i)
      {
      }
    }
    label380:
    do
    {
      do
      {
        do
        {
          do
          {
            return null;
            localObject1 = null;
            break;
            if (localObject1 != null) {}
            for (localObject2 = ((Route)localObject1).proxy(); ((Proxy)localObject2).type() != Proxy.Type.HTTP; localObject2 = this.client.proxy()) {
              throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            return this.client.proxyAuthenticator().authenticate((Route)localObject1, paramResponse);
            return this.client.authenticator().authenticate((Route)localObject1, paramResponse);
          } while (((!((String)localObject2).equals("GET")) && (!((String)localObject2).equals("HEAD"))) || (!this.client.followRedirects()));
          localObject1 = paramResponse.header("Location");
        } while (localObject1 == null);
        localObject1 = paramResponse.request().url().resolve((String)localObject1);
      } while ((localObject1 == null) || ((!((HttpUrl)localObject1).scheme().equals(paramResponse.request().url().scheme())) && (!this.client.followSslRedirects())));
      Request.Builder localBuilder = paramResponse.request().newBuilder();
      if (HttpMethod.permitsRequestBody((String)localObject2))
      {
        if (!HttpMethod.redirectsToGet((String)localObject2)) {
          break label380;
        }
        localBuilder.method("GET", null);
      }
      for (;;)
      {
        localBuilder.removeHeader("Transfer-Encoding");
        localBuilder.removeHeader("Content-Length");
        localBuilder.removeHeader("Content-Type");
        if (!sameConnection(paramResponse, (HttpUrl)localObject1)) {
          localBuilder.removeHeader("Authorization");
        }
        return localBuilder.url((HttpUrl)localObject1).build();
        localBuilder.method((String)localObject2, null);
      }
    } while ((paramResponse.request().body() instanceof UnrepeatableRequestBody));
    return paramResponse.request();
  }
  
  private boolean isRecoverable(IOException paramIOException, boolean paramBoolean)
  {
    boolean bool = true;
    if ((paramIOException instanceof ProtocolException)) {}
    do
    {
      return false;
      if ((paramIOException instanceof InterruptedIOException))
      {
        if (((paramIOException instanceof SocketTimeoutException)) && (paramBoolean)) {}
        for (paramBoolean = bool;; paramBoolean = false) {
          return paramBoolean;
        }
      }
    } while ((((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException))) || ((paramIOException instanceof SSLPeerUnverifiedException)));
    return true;
  }
  
  private boolean recover(IOException paramIOException, boolean paramBoolean, Request paramRequest)
  {
    this.streamAllocation.streamFailed(paramIOException);
    if (!this.client.retryOnConnectionFailure()) {}
    while (((!paramBoolean) && ((paramRequest.body() instanceof UnrepeatableRequestBody))) || (!isRecoverable(paramIOException, paramBoolean)) || (!this.streamAllocation.hasMoreRoutes())) {
      return false;
    }
    return true;
  }
  
  private boolean sameConnection(Response paramResponse, HttpUrl paramHttpUrl)
  {
    paramResponse = paramResponse.request().url();
    return (paramResponse.host().equals(paramHttpUrl.host())) && (paramResponse.port() == paramHttpUrl.port()) && (paramResponse.scheme().equals(paramHttpUrl.scheme()));
  }
  
  public void cancel()
  {
    this.canceled = true;
    StreamAllocation localStreamAllocation = this.streamAllocation;
    if (localStreamAllocation != null) {
      localStreamAllocation.cancel();
    }
  }
  
  public OkHttpClient client()
  {
    return this.client;
  }
  
  /* Error */
  public Response intercept(okhttp3.Interceptor.Chain paramChain)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 275 1 0
    //   6: astore_3
    //   7: aload_0
    //   8: new 97	okhttp3/internal/connection/StreamAllocation
    //   11: dup
    //   12: aload_0
    //   13: getfield 23	okhttp3/internal/http/RetryAndFollowUpInterceptor:client	Lokhttp3/OkHttpClient;
    //   16: invokevirtual 279	okhttp3/OkHttpClient:connectionPool	()Lokhttp3/ConnectionPool;
    //   19: aload_0
    //   20: aload_3
    //   21: invokevirtual 175	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   24: invokespecial 281	okhttp3/internal/http/RetryAndFollowUpInterceptor:createAddress	(Lokhttp3/HttpUrl;)Lokhttp3/Address;
    //   27: invokespecial 284	okhttp3/internal/connection/StreamAllocation:<init>	(Lokhttp3/ConnectionPool;Lokhttp3/Address;)V
    //   30: putfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   33: iconst_0
    //   34: istore_2
    //   35: aconst_null
    //   36: astore 4
    //   38: aload_0
    //   39: getfield 265	okhttp3/internal/http/RetryAndFollowUpInterceptor:canceled	Z
    //   42: ifeq +21 -> 63
    //   45: aload_0
    //   46: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   49: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   52: new 90	java/io/IOException
    //   55: dup
    //   56: ldc_w 289
    //   59: invokespecial 290	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   62: athrow
    //   63: aload_1
    //   64: checkcast 292	okhttp3/internal/http/RealInterceptorChain
    //   67: aload_3
    //   68: aload_0
    //   69: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   72: aconst_null
    //   73: aconst_null
    //   74: invokevirtual 296	okhttp3/internal/http/RealInterceptorChain:proceed	(Lokhttp3/Request;Lokhttp3/internal/connection/StreamAllocation;Lokhttp3/internal/http/HttpStream;Lokhttp3/Connection;)Lokhttp3/Response;
    //   77: astore 5
    //   79: iconst_0
    //   80: ifeq +18 -> 98
    //   83: aload_0
    //   84: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   87: aconst_null
    //   88: invokevirtual 254	okhttp3/internal/connection/StreamAllocation:streamFailed	(Ljava/io/IOException;)V
    //   91: aload_0
    //   92: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   95: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   98: aload 5
    //   100: astore_3
    //   101: aload 4
    //   103: ifnull +27 -> 130
    //   106: aload 5
    //   108: invokevirtual 299	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   111: aload 4
    //   113: invokevirtual 299	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   116: aconst_null
    //   117: invokevirtual 304	okhttp3/Response$Builder:body	(Lokhttp3/ResponseBody;)Lokhttp3/Response$Builder;
    //   120: invokevirtual 307	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   123: invokevirtual 311	okhttp3/Response$Builder:priorResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   126: invokevirtual 307	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   129: astore_3
    //   130: aload_0
    //   131: aload_3
    //   132: invokespecial 313	okhttp3/internal/http/RetryAndFollowUpInterceptor:followUpRequest	(Lokhttp3/Response;)Lokhttp3/Request;
    //   135: astore 5
    //   137: aload 5
    //   139: ifnonnull +123 -> 262
    //   142: aload_0
    //   143: getfield 315	okhttp3/internal/http/RetryAndFollowUpInterceptor:forWebSocket	Z
    //   146: ifne +10 -> 156
    //   149: aload_0
    //   150: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   153: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   156: aload_3
    //   157: areturn
    //   158: astore 5
    //   160: aload_0
    //   161: aload 5
    //   163: invokevirtual 319	okhttp3/internal/connection/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   166: iconst_1
    //   167: aload_3
    //   168: invokespecial 321	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;ZLokhttp3/Request;)Z
    //   171: ifne +31 -> 202
    //   174: aload 5
    //   176: invokevirtual 319	okhttp3/internal/connection/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   179: athrow
    //   180: astore_1
    //   181: iconst_1
    //   182: ifeq +18 -> 200
    //   185: aload_0
    //   186: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   189: aconst_null
    //   190: invokevirtual 254	okhttp3/internal/connection/StreamAllocation:streamFailed	(Ljava/io/IOException;)V
    //   193: aload_0
    //   194: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   197: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   200: aload_1
    //   201: athrow
    //   202: iconst_0
    //   203: ifeq -165 -> 38
    //   206: aload_0
    //   207: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   210: aconst_null
    //   211: invokevirtual 254	okhttp3/internal/connection/StreamAllocation:streamFailed	(Ljava/io/IOException;)V
    //   214: aload_0
    //   215: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   218: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   221: goto -183 -> 38
    //   224: astore 5
    //   226: aload_0
    //   227: aload 5
    //   229: iconst_0
    //   230: aload_3
    //   231: invokespecial 321	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;ZLokhttp3/Request;)Z
    //   234: ifne +6 -> 240
    //   237: aload 5
    //   239: athrow
    //   240: iconst_0
    //   241: ifeq -203 -> 38
    //   244: aload_0
    //   245: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   248: aconst_null
    //   249: invokevirtual 254	okhttp3/internal/connection/StreamAllocation:streamFailed	(Ljava/io/IOException;)V
    //   252: aload_0
    //   253: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   256: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   259: goto -221 -> 38
    //   262: aload_3
    //   263: invokevirtual 324	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   266: invokestatic 330	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   269: iload_2
    //   270: iconst_1
    //   271: iadd
    //   272: istore_2
    //   273: iload_2
    //   274: bipush 20
    //   276: if_icmple +38 -> 314
    //   279: aload_0
    //   280: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   283: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   286: new 138	java/net/ProtocolException
    //   289: dup
    //   290: new 332	java/lang/StringBuilder
    //   293: dup
    //   294: invokespecial 333	java/lang/StringBuilder:<init>	()V
    //   297: ldc_w 335
    //   300: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: iload_2
    //   304: invokevirtual 342	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   307: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   310: invokespecial 143	java/net/ProtocolException:<init>	(Ljava/lang/String;)V
    //   313: athrow
    //   314: aload 5
    //   316: invokevirtual 229	okhttp3/Request:body	()Lokhttp3/RequestBody;
    //   319: instanceof 231
    //   322: ifeq +18 -> 340
    //   325: new 347	java/net/HttpRetryException
    //   328: dup
    //   329: ldc_w 349
    //   332: aload_3
    //   333: invokevirtual 112	okhttp3/Response:code	()I
    //   336: invokespecial 352	java/net/HttpRetryException:<init>	(Ljava/lang/String;I)V
    //   339: athrow
    //   340: aload_0
    //   341: aload_3
    //   342: aload 5
    //   344: invokevirtual 175	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   347: invokespecial 217	okhttp3/internal/http/RetryAndFollowUpInterceptor:sameConnection	(Lokhttp3/Response;Lokhttp3/HttpUrl;)Z
    //   350: ifne +46 -> 396
    //   353: aload_0
    //   354: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   357: invokevirtual 287	okhttp3/internal/connection/StreamAllocation:release	()V
    //   360: aload_0
    //   361: new 97	okhttp3/internal/connection/StreamAllocation
    //   364: dup
    //   365: aload_0
    //   366: getfield 23	okhttp3/internal/http/RetryAndFollowUpInterceptor:client	Lokhttp3/OkHttpClient;
    //   369: invokevirtual 279	okhttp3/OkHttpClient:connectionPool	()Lokhttp3/ConnectionPool;
    //   372: aload_0
    //   373: aload 5
    //   375: invokevirtual 175	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   378: invokespecial 281	okhttp3/internal/http/RetryAndFollowUpInterceptor:createAddress	(Lokhttp3/HttpUrl;)Lokhttp3/Address;
    //   381: invokespecial 284	okhttp3/internal/connection/StreamAllocation:<init>	(Lokhttp3/ConnectionPool;Lokhttp3/Address;)V
    //   384: putfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   387: aload_3
    //   388: astore 4
    //   390: aload 5
    //   392: astore_3
    //   393: goto -355 -> 38
    //   396: aload_0
    //   397: getfield 95	okhttp3/internal/http/RetryAndFollowUpInterceptor:streamAllocation	Lokhttp3/internal/connection/StreamAllocation;
    //   400: invokevirtual 356	okhttp3/internal/connection/StreamAllocation:stream	()Lokhttp3/internal/http/HttpStream;
    //   403: ifnull -16 -> 387
    //   406: new 92	java/lang/IllegalStateException
    //   409: dup
    //   410: new 332	java/lang/StringBuilder
    //   413: dup
    //   414: invokespecial 333	java/lang/StringBuilder:<init>	()V
    //   417: ldc_w 358
    //   420: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: aload_3
    //   424: invokevirtual 361	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   427: ldc_w 363
    //   430: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: invokespecial 364	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   439: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	440	0	this	RetryAndFollowUpInterceptor
    //   0	440	1	paramChain	okhttp3.Interceptor.Chain
    //   34	270	2	i	int
    //   6	418	3	localObject1	Object
    //   36	353	4	localObject2	Object
    //   77	61	5	localObject3	Object
    //   158	17	5	localRouteException	okhttp3.internal.connection.RouteException
    //   224	167	5	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   63	79	158	okhttp3/internal/connection/RouteException
    //   63	79	180	finally
    //   160	180	180	finally
    //   226	240	180	finally
    //   63	79	224	java/io/IOException
  }
  
  public boolean isCanceled()
  {
    return this.canceled;
  }
  
  public boolean isForWebSocket()
  {
    return this.forWebSocket;
  }
  
  public void setForWebSocket(boolean paramBoolean)
  {
    this.forWebSocket = paramBoolean;
  }
  
  public StreamAllocation streamAllocation()
  {
    return this.streamAllocation;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\http\RetryAndFollowUpInterceptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */