package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

public final class ConnectInterceptor
  implements Interceptor
{
  public final OkHttpClient client;
  
  public ConnectInterceptor(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }
  
  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    paramChain = (RealInterceptorChain)paramChain;
    Request localRequest = paramChain.request();
    StreamAllocation localStreamAllocation = paramChain.streamAllocation();
    if (!localRequest.method().equals("GET")) {}
    for (boolean bool = true;; bool = false) {
      return paramChain.proceed(localRequest, localStreamAllocation, localStreamAllocation.newStream(this.client, bool), localStreamAllocation.connection());
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\connection\ConnectInterceptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */