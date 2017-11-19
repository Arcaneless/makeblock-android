package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;

final class RealCall
  implements Call
{
  private final OkHttpClient client;
  private boolean executed;
  Request originalRequest;
  private final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;
  
  protected RealCall(OkHttpClient paramOkHttpClient, Request paramRequest)
  {
    this.client = paramOkHttpClient;
    this.originalRequest = paramRequest;
    this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(paramOkHttpClient);
  }
  
  private Response getResponseWithInterceptorChain()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.client.interceptors());
    localArrayList.add(this.retryAndFollowUpInterceptor);
    localArrayList.add(new BridgeInterceptor(this.client.cookieJar()));
    localArrayList.add(new CacheInterceptor(this.client.internalCache()));
    localArrayList.add(new ConnectInterceptor(this.client));
    if (!this.retryAndFollowUpInterceptor.isForWebSocket()) {
      localArrayList.addAll(this.client.networkInterceptors());
    }
    localArrayList.add(new CallServerInterceptor(this.retryAndFollowUpInterceptor.isForWebSocket()));
    return new RealInterceptorChain(localArrayList, null, null, null, 0, this.originalRequest).proceed(this.originalRequest);
  }
  
  private String toLoggableString()
  {
    if (this.retryAndFollowUpInterceptor.isCanceled()) {}
    for (String str = "canceled call";; str = "call") {
      return str + " to " + redactedUrl();
    }
  }
  
  public void cancel()
  {
    this.retryAndFollowUpInterceptor.cancel();
  }
  
  public void enqueue(Callback paramCallback)
  {
    try
    {
      if (this.executed) {
        throw new IllegalStateException("Already Executed");
      }
    }
    finally {}
    this.executed = true;
    this.client.dispatcher().enqueue(new AsyncCall(paramCallback, null));
  }
  
  public Response execute()
    throws IOException
  {
    try
    {
      if (this.executed) {
        throw new IllegalStateException("Already Executed");
      }
    }
    finally {}
    this.executed = true;
    try
    {
      this.client.dispatcher().executed(this);
      Response localResponse1 = getResponseWithInterceptorChain();
      if (localResponse1 == null) {
        throw new IOException("Canceled");
      }
    }
    finally
    {
      this.client.dispatcher().finished(this);
    }
    this.client.dispatcher().finished(this);
    return localResponse2;
  }
  
  public boolean isCanceled()
  {
    return this.retryAndFollowUpInterceptor.isCanceled();
  }
  
  public boolean isExecuted()
  {
    try
    {
      boolean bool = this.executed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  HttpUrl redactedUrl()
  {
    return this.originalRequest.url().resolve("/...");
  }
  
  public Request request()
  {
    return this.originalRequest;
  }
  
  void setForWebSocket()
  {
    try
    {
      if (this.executed) {
        throw new IllegalStateException("Already Executed");
      }
    }
    finally {}
    this.retryAndFollowUpInterceptor.setForWebSocket(true);
  }
  
  StreamAllocation streamAllocation()
  {
    return this.retryAndFollowUpInterceptor.streamAllocation();
  }
  
  final class AsyncCall
    extends NamedRunnable
  {
    private final Callback responseCallback;
    
    private AsyncCall(Callback paramCallback)
    {
      super(new Object[] { RealCall.this.redactedUrl().toString() });
      this.responseCallback = paramCallback;
    }
    
    protected void execute()
    {
      int j = 0;
      int i = j;
      label173:
      for (;;)
      {
        try
        {
          Response localResponse = RealCall.this.getResponseWithInterceptorChain();
          i = j;
          if (RealCall.this.retryAndFollowUpInterceptor.isCanceled())
          {
            break label173;
            this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
          }
          else
          {
            i = 1;
            this.responseCallback.onResponse(RealCall.this, localResponse);
            continue;
          }
          i = 1;
        }
        catch (IOException localIOException)
        {
          if (i != 0)
          {
            Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), localIOException);
            return;
          }
          this.responseCallback.onFailure(RealCall.this, localIOException);
        }
        finally
        {
          RealCall.this.client.dispatcher().finished(this);
        }
      }
    }
    
    RealCall get()
    {
      return RealCall.this;
    }
    
    String host()
    {
      return RealCall.this.originalRequest.url().host();
    }
    
    Request request()
    {
      return RealCall.this.originalRequest;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\RealCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */