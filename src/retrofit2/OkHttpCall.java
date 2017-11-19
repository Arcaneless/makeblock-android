package retrofit2;

import java.io.IOException;
import okhttp3.Call.Factory;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

final class OkHttpCall<T>
  implements Call<T>
{
  private final Object[] args;
  private volatile boolean canceled;
  private Throwable creationFailure;
  private boolean executed;
  private okhttp3.Call rawCall;
  private final ServiceMethod<T> serviceMethod;
  
  OkHttpCall(ServiceMethod<T> paramServiceMethod, Object[] paramArrayOfObject)
  {
    this.serviceMethod = paramServiceMethod;
    this.args = paramArrayOfObject;
  }
  
  private okhttp3.Call createRawCall()
    throws IOException
  {
    Object localObject = this.serviceMethod.toRequest(this.args);
    localObject = this.serviceMethod.callFactory.newCall((Request)localObject);
    if (localObject == null) {
      throw new NullPointerException("Call.Factory returned null.");
    }
    return (okhttp3.Call)localObject;
  }
  
  public void cancel()
  {
    this.canceled = true;
    try
    {
      okhttp3.Call localCall = this.rawCall;
      if (localCall != null) {
        localCall.cancel();
      }
      return;
    }
    finally {}
  }
  
  public OkHttpCall<T> clone()
  {
    return new OkHttpCall(this.serviceMethod, this.args);
  }
  
  public void enqueue(final Callback<T> paramCallback)
  {
    if (paramCallback == null) {
      throw new NullPointerException("callback == null");
    }
    try
    {
      if (this.executed) {
        throw new IllegalStateException("Already executed.");
      }
    }
    finally {}
    this.executed = true;
    okhttp3.Call localCall2 = this.rawCall;
    Throwable localThrowable3 = this.creationFailure;
    okhttp3.Call localCall1 = localCall2;
    Throwable localThrowable1 = localThrowable3;
    if (localCall2 == null)
    {
      localCall1 = localCall2;
      localThrowable1 = localThrowable3;
      if (localThrowable3 != null) {}
    }
    try
    {
      localCall1 = createRawCall();
      this.rawCall = localCall1;
      localThrowable1 = localThrowable3;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        this.creationFailure = localThrowable2;
        localCall1 = localCall2;
      }
      if (!this.canceled) {
        break label130;
      }
      localCall1.cancel();
      label130:
      localCall1.enqueue(new okhttp3.Callback()
      {
        private void callFailure(Throwable paramAnonymousThrowable)
        {
          try
          {
            paramCallback.onFailure(OkHttpCall.this, paramAnonymousThrowable);
            return;
          }
          catch (Throwable paramAnonymousThrowable)
          {
            paramAnonymousThrowable.printStackTrace();
          }
        }
        
        private void callSuccess(Response<T> paramAnonymousResponse)
        {
          try
          {
            paramCallback.onResponse(OkHttpCall.this, paramAnonymousResponse);
            return;
          }
          catch (Throwable paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
          }
        }
        
        public void onFailure(okhttp3.Call paramAnonymousCall, IOException paramAnonymousIOException)
        {
          try
          {
            paramCallback.onFailure(OkHttpCall.this, paramAnonymousIOException);
            return;
          }
          catch (Throwable paramAnonymousCall)
          {
            paramAnonymousCall.printStackTrace();
          }
        }
        
        public void onResponse(okhttp3.Call paramAnonymousCall, okhttp3.Response paramAnonymousResponse)
          throws IOException
        {
          try
          {
            paramAnonymousCall = OkHttpCall.this.parseResponse(paramAnonymousResponse);
            callSuccess(paramAnonymousCall);
            return;
          }
          catch (Throwable paramAnonymousCall)
          {
            callFailure(paramAnonymousCall);
          }
        }
      });
    }
    if (localThrowable1 != null)
    {
      paramCallback.onFailure(this, localThrowable1);
      return;
    }
  }
  
  public Response<T> execute()
    throws IOException
  {
    try
    {
      if (this.executed) {
        throw new IllegalStateException("Already executed.");
      }
    }
    finally {}
    this.executed = true;
    if (this.creationFailure != null)
    {
      if ((this.creationFailure instanceof IOException)) {
        throw ((IOException)this.creationFailure);
      }
      throw ((RuntimeException)this.creationFailure);
    }
    okhttp3.Call localCall = this.rawCall;
    Object localObject2 = localCall;
    if (localCall == null) {}
    try
    {
      localObject2 = createRawCall();
      this.rawCall = ((okhttp3.Call)localObject2);
      if (this.canceled) {
        ((okhttp3.Call)localObject2).cancel();
      }
      return parseResponse(((okhttp3.Call)localObject2).execute());
    }
    catch (IOException localIOException)
    {
      this.creationFailure = localIOException;
      throw localIOException;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;) {}
    }
  }
  
  public boolean isCanceled()
  {
    return this.canceled;
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
  
  Response<T> parseResponse(okhttp3.Response paramResponse)
    throws IOException
  {
    Object localObject = paramResponse.body();
    okhttp3.Response localResponse = paramResponse.newBuilder().body(new NoContentResponseBody(((ResponseBody)localObject).contentType(), ((ResponseBody)localObject).contentLength())).build();
    int i = localResponse.code();
    if ((i < 200) || (i >= 300)) {
      try
      {
        paramResponse = Response.error(Utils.buffer((ResponseBody)localObject), localResponse);
        return paramResponse;
      }
      finally
      {
        ((ResponseBody)localObject).close();
      }
    }
    if ((i == 204) || (i == 205)) {
      return Response.success(null, localResponse);
    }
    paramResponse = new ExceptionCatchingRequestBody((ResponseBody)localObject);
    try
    {
      localObject = Response.success(this.serviceMethod.toResponse(paramResponse), localResponse);
      return (Response<T>)localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramResponse.throwIfCaught();
      throw localRuntimeException;
    }
  }
  
  public Request request()
  {
    for (;;)
    {
      try
      {
        localObject1 = this.rawCall;
      }
      finally {}
      Object localObject1 = ((okhttp3.Call)localObject1).request();
      return (Request)localObject1;
      if (this.creationFailure != null)
      {
        if ((this.creationFailure instanceof IOException)) {
          throw new RuntimeException("Unable to create request.", this.creationFailure);
        }
        throw ((RuntimeException)this.creationFailure);
      }
      try
      {
        Object localObject3 = createRawCall();
        this.rawCall = ((okhttp3.Call)localObject3);
        localObject3 = ((okhttp3.Call)localObject3).request();
      }
      catch (RuntimeException localRuntimeException)
      {
        this.creationFailure = localRuntimeException;
        throw localRuntimeException;
        this.creationFailure = localRuntimeException;
        throw new RuntimeException("Unable to create request.", localRuntimeException);
        if (localRuntimeException == null) {
          continue;
        }
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
  }
  
  static final class ExceptionCatchingRequestBody
    extends ResponseBody
  {
    private final ResponseBody delegate;
    IOException thrownException;
    
    ExceptionCatchingRequestBody(ResponseBody paramResponseBody)
    {
      this.delegate = paramResponseBody;
    }
    
    public void close()
    {
      this.delegate.close();
    }
    
    public long contentLength()
    {
      return this.delegate.contentLength();
    }
    
    public MediaType contentType()
    {
      return this.delegate.contentType();
    }
    
    public BufferedSource source()
    {
      Okio.buffer(new ForwardingSource(this.delegate.source())
      {
        public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
          throws IOException
        {
          try
          {
            paramAnonymousLong = super.read(paramAnonymousBuffer, paramAnonymousLong);
            return paramAnonymousLong;
          }
          catch (IOException paramAnonymousBuffer)
          {
            OkHttpCall.ExceptionCatchingRequestBody.this.thrownException = paramAnonymousBuffer;
            throw paramAnonymousBuffer;
          }
        }
      });
    }
    
    void throwIfCaught()
      throws IOException
    {
      if (this.thrownException != null) {
        throw this.thrownException;
      }
    }
  }
  
  static final class NoContentResponseBody
    extends ResponseBody
  {
    private final long contentLength;
    private final MediaType contentType;
    
    NoContentResponseBody(MediaType paramMediaType, long paramLong)
    {
      this.contentType = paramMediaType;
      this.contentLength = paramLong;
    }
    
    public long contentLength()
    {
      return this.contentLength;
    }
    
    public MediaType contentType()
    {
      return this.contentType;
    }
    
    public BufferedSource source()
    {
      throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\OkHttpCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */