package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import okhttp3.Address;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;

public final class RealInterceptorChain
  implements Interceptor.Chain
{
  private int calls;
  private final Connection connection;
  private final HttpStream httpStream;
  private final int index;
  private final List<Interceptor> interceptors;
  private final Request request;
  private final StreamAllocation streamAllocation;
  
  public RealInterceptorChain(List<Interceptor> paramList, StreamAllocation paramStreamAllocation, HttpStream paramHttpStream, Connection paramConnection, int paramInt, Request paramRequest)
  {
    this.interceptors = paramList;
    this.connection = paramConnection;
    this.streamAllocation = paramStreamAllocation;
    this.httpStream = paramHttpStream;
    this.index = paramInt;
    this.request = paramRequest;
  }
  
  private boolean sameConnection(HttpUrl paramHttpUrl)
  {
    return (paramHttpUrl.host().equals(this.connection.route().address().url().host())) && (paramHttpUrl.port() == this.connection.route().address().url().port());
  }
  
  public Connection connection()
  {
    return this.connection;
  }
  
  public HttpStream httpStream()
  {
    return this.httpStream;
  }
  
  public Response proceed(Request paramRequest)
    throws IOException
  {
    return proceed(paramRequest, this.streamAllocation, this.httpStream, this.connection);
  }
  
  public Response proceed(Request paramRequest, StreamAllocation paramStreamAllocation, HttpStream paramHttpStream, Connection paramConnection)
    throws IOException
  {
    if (this.index >= this.interceptors.size()) {
      throw new AssertionError();
    }
    this.calls += 1;
    if ((this.httpStream != null) && (!sameConnection(paramRequest.url()))) {
      throw new IllegalStateException("network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port");
    }
    if ((this.httpStream != null) && (this.calls > 1)) {
      throw new IllegalStateException("network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once");
    }
    paramRequest = new RealInterceptorChain(this.interceptors, paramStreamAllocation, paramHttpStream, paramConnection, this.index + 1, paramRequest);
    paramStreamAllocation = (Interceptor)this.interceptors.get(this.index);
    paramConnection = paramStreamAllocation.intercept(paramRequest);
    if ((paramHttpStream != null) && (this.index + 1 < this.interceptors.size()) && (paramRequest.calls != 1)) {
      throw new IllegalStateException("network interceptor " + paramStreamAllocation + " must call proceed() exactly once");
    }
    if (paramConnection == null) {
      throw new NullPointerException("interceptor " + paramStreamAllocation + " returned null");
    }
    return paramConnection;
  }
  
  public Request request()
  {
    return this.request;
  }
  
  public StreamAllocation streamAllocation()
  {
    return this.streamAllocation;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\http\RealInterceptorChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */