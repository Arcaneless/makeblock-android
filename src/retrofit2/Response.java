package retrofit2;

import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request.Builder;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;

public final class Response<T>
{
  private final T body;
  private final ResponseBody errorBody;
  private final okhttp3.Response rawResponse;
  
  private Response(okhttp3.Response paramResponse, T paramT, ResponseBody paramResponseBody)
  {
    this.rawResponse = paramResponse;
    this.body = paramT;
    this.errorBody = paramResponseBody;
  }
  
  public static <T> Response<T> error(int paramInt, ResponseBody paramResponseBody)
  {
    if (paramInt < 400) {
      throw new IllegalArgumentException("code < 400: " + paramInt);
    }
    return error(paramResponseBody, new Response.Builder().code(paramInt).protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
  }
  
  public static <T> Response<T> error(ResponseBody paramResponseBody, okhttp3.Response paramResponse)
  {
    if (paramResponseBody == null) {
      throw new NullPointerException("body == null");
    }
    if (paramResponse == null) {
      throw new NullPointerException("rawResponse == null");
    }
    if (paramResponse.isSuccessful()) {
      throw new IllegalArgumentException("rawResponse should not be successful response");
    }
    return new Response(paramResponse, null, paramResponseBody);
  }
  
  public static <T> Response<T> success(T paramT)
  {
    return success(paramT, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
  }
  
  public static <T> Response<T> success(T paramT, Headers paramHeaders)
  {
    if (paramHeaders == null) {
      throw new NullPointerException("headers == null");
    }
    return success(paramT, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).headers(paramHeaders).request(new Request.Builder().url("http://localhost/").build()).build());
  }
  
  public static <T> Response<T> success(T paramT, okhttp3.Response paramResponse)
  {
    if (paramResponse == null) {
      throw new NullPointerException("rawResponse == null");
    }
    if (!paramResponse.isSuccessful()) {
      throw new IllegalArgumentException("rawResponse must be successful response");
    }
    return new Response(paramResponse, paramT, null);
  }
  
  public T body()
  {
    return (T)this.body;
  }
  
  public int code()
  {
    return this.rawResponse.code();
  }
  
  public ResponseBody errorBody()
  {
    return this.errorBody;
  }
  
  public Headers headers()
  {
    return this.rawResponse.headers();
  }
  
  public boolean isSuccessful()
  {
    return this.rawResponse.isSuccessful();
  }
  
  public String message()
  {
    return this.rawResponse.message();
  }
  
  public okhttp3.Response raw()
  {
    return this.rawResponse;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\retrofit2\Response.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */