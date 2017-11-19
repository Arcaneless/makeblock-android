package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.Okio;

public final class CallServerInterceptor
  implements Interceptor
{
  private final boolean forWebSocket;
  
  public CallServerInterceptor(boolean paramBoolean)
  {
    this.forWebSocket = paramBoolean;
  }
  
  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    HttpStream localHttpStream = ((RealInterceptorChain)paramChain).httpStream();
    StreamAllocation localStreamAllocation = ((RealInterceptorChain)paramChain).streamAllocation();
    paramChain = paramChain.request();
    long l = System.currentTimeMillis();
    localHttpStream.writeRequestHeaders(paramChain);
    if ((HttpMethod.permitsRequestBody(paramChain.method())) && (paramChain.body() != null))
    {
      localObject = Okio.buffer(localHttpStream.createRequestBody(paramChain, paramChain.body().contentLength()));
      paramChain.body().writeTo((BufferedSink)localObject);
      ((BufferedSink)localObject).close();
    }
    localHttpStream.finishRequest();
    Object localObject = localHttpStream.readResponseHeaders().request(paramChain).handshake(localStreamAllocation.connection().handshake()).sentRequestAtMillis(l).receivedResponseAtMillis(System.currentTimeMillis()).build();
    if (this.forWebSocket)
    {
      paramChain = (Interceptor.Chain)localObject;
      if (((Response)localObject).code() == 101) {}
    }
    else
    {
      paramChain = ((Response)localObject).newBuilder().body(localHttpStream.openResponseBody((Response)localObject)).build();
    }
    if (("close".equalsIgnoreCase(paramChain.request().header("Connection"))) || ("close".equalsIgnoreCase(paramChain.header("Connection")))) {
      localStreamAllocation.noNewStreams();
    }
    int i = paramChain.code();
    if (((i == 204) || (i == 205)) && (paramChain.body().contentLength() > 0L)) {
      throw new ProtocolException("HTTP " + i + " had non-zero Content-Length: " + paramChain.body().contentLength());
    }
    return paramChain;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\http\CallServerInterceptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */