package okhttp3.internal.http;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1xStream
  implements HttpStream
{
  private static final int STATE_CLOSED = 6;
  private static final int STATE_IDLE = 0;
  private static final int STATE_OPEN_REQUEST_BODY = 1;
  private static final int STATE_OPEN_RESPONSE_BODY = 4;
  private static final int STATE_READING_RESPONSE_BODY = 5;
  private static final int STATE_READ_RESPONSE_HEADERS = 3;
  private static final int STATE_WRITING_REQUEST_BODY = 2;
  private final OkHttpClient client;
  private final BufferedSink sink;
  private final BufferedSource source;
  private int state = 0;
  private final StreamAllocation streamAllocation;
  
  public Http1xStream(OkHttpClient paramOkHttpClient, StreamAllocation paramStreamAllocation, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
  {
    this.client = paramOkHttpClient;
    this.streamAllocation = paramStreamAllocation;
    this.source = paramBufferedSource;
    this.sink = paramBufferedSink;
  }
  
  private void detachTimeout(ForwardingTimeout paramForwardingTimeout)
  {
    Timeout localTimeout = paramForwardingTimeout.delegate();
    paramForwardingTimeout.setDelegate(Timeout.NONE);
    localTimeout.clearDeadline();
    localTimeout.clearTimeout();
  }
  
  private Source getTransferStream(Response paramResponse)
    throws IOException
  {
    if (!HttpHeaders.hasBody(paramResponse)) {
      return newFixedLengthSource(0L);
    }
    if ("chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding"))) {
      return newChunkedSource(paramResponse.request().url());
    }
    long l = HttpHeaders.contentLength(paramResponse);
    if (l != -1L) {
      return newFixedLengthSource(l);
    }
    return newUnknownLengthSource();
  }
  
  public void cancel()
  {
    RealConnection localRealConnection = this.streamAllocation.connection();
    if (localRealConnection != null) {
      localRealConnection.cancel();
    }
  }
  
  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    if ("chunked".equalsIgnoreCase(paramRequest.header("Transfer-Encoding"))) {
      return newChunkedSink();
    }
    if (paramLong != -1L) {
      return newFixedLengthSink(paramLong);
    }
    throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
  }
  
  public void finishRequest()
    throws IOException
  {
    this.sink.flush();
  }
  
  public boolean isClosed()
  {
    return this.state == 6;
  }
  
  public Sink newChunkedSink()
  {
    if (this.state != 1) {
      throw new IllegalStateException("state: " + this.state);
    }
    this.state = 2;
    return new ChunkedSink(null);
  }
  
  public Source newChunkedSource(HttpUrl paramHttpUrl)
    throws IOException
  {
    if (this.state != 4) {
      throw new IllegalStateException("state: " + this.state);
    }
    this.state = 5;
    return new ChunkedSource(paramHttpUrl);
  }
  
  public Sink newFixedLengthSink(long paramLong)
  {
    if (this.state != 1) {
      throw new IllegalStateException("state: " + this.state);
    }
    this.state = 2;
    return new FixedLengthSink(paramLong, null);
  }
  
  public Source newFixedLengthSource(long paramLong)
    throws IOException
  {
    if (this.state != 4) {
      throw new IllegalStateException("state: " + this.state);
    }
    this.state = 5;
    return new FixedLengthSource(paramLong);
  }
  
  public Source newUnknownLengthSource()
    throws IOException
  {
    if (this.state != 4) {
      throw new IllegalStateException("state: " + this.state);
    }
    if (this.streamAllocation == null) {
      throw new IllegalStateException("streamAllocation == null");
    }
    this.state = 5;
    this.streamAllocation.noNewStreams();
    return new UnknownLengthSource(null);
  }
  
  public ResponseBody openResponseBody(Response paramResponse)
    throws IOException
  {
    Source localSource = getTransferStream(paramResponse);
    return new RealResponseBody(paramResponse.headers(), Okio.buffer(localSource));
  }
  
  public Headers readHeaders()
    throws IOException
  {
    Headers.Builder localBuilder = new Headers.Builder();
    for (;;)
    {
      String str = this.source.readUtf8LineStrict();
      if (str.length() == 0) {
        break;
      }
      Internal.instance.addLenient(localBuilder, str);
    }
    return localBuilder.build();
  }
  
  public Response.Builder readResponse()
    throws IOException
  {
    if ((this.state != 1) && (this.state != 3)) {
      throw new IllegalStateException("state: " + this.state);
    }
    try
    {
      StatusLine localStatusLine;
      do
      {
        localStatusLine = StatusLine.parse(this.source.readUtf8LineStrict());
        localObject = new Response.Builder().protocol(localStatusLine.protocol).code(localStatusLine.code).message(localStatusLine.message).headers(readHeaders());
      } while (localStatusLine.code == 100);
      this.state = 4;
      return (Response.Builder)localObject;
    }
    catch (EOFException localEOFException)
    {
      Object localObject = new IOException("unexpected end of stream on " + this.streamAllocation);
      ((IOException)localObject).initCause(localEOFException);
      throw ((Throwable)localObject);
    }
  }
  
  public Response.Builder readResponseHeaders()
    throws IOException
  {
    return readResponse();
  }
  
  public void writeRequest(Headers paramHeaders, String paramString)
    throws IOException
  {
    if (this.state != 0) {
      throw new IllegalStateException("state: " + this.state);
    }
    this.sink.writeUtf8(paramString).writeUtf8("\r\n");
    int i = 0;
    int j = paramHeaders.size();
    while (i < j)
    {
      this.sink.writeUtf8(paramHeaders.name(i)).writeUtf8(": ").writeUtf8(paramHeaders.value(i)).writeUtf8("\r\n");
      i += 1;
    }
    this.sink.writeUtf8("\r\n");
    this.state = 1;
  }
  
  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    String str = RequestLine.get(paramRequest, this.streamAllocation.connection().route().proxy().type());
    writeRequest(paramRequest.headers(), str);
  }
  
  private abstract class AbstractSource
    implements Source
  {
    protected boolean closed;
    protected final ForwardingTimeout timeout = new ForwardingTimeout(Http1xStream.this.source.timeout());
    
    private AbstractSource() {}
    
    protected final void endOfInput(boolean paramBoolean)
      throws IOException
    {
      if (Http1xStream.this.state == 6) {}
      do
      {
        return;
        if (Http1xStream.this.state != 5) {
          throw new IllegalStateException("state: " + Http1xStream.this.state);
        }
        Http1xStream.this.detachTimeout(this.timeout);
        Http1xStream.access$502(Http1xStream.this, 6);
      } while (Http1xStream.this.streamAllocation == null);
      StreamAllocation localStreamAllocation = Http1xStream.this.streamAllocation;
      if (!paramBoolean) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        localStreamAllocation.streamFinished(paramBoolean, Http1xStream.this);
        return;
      }
    }
    
    public Timeout timeout()
    {
      return this.timeout;
    }
  }
  
  private final class ChunkedSink
    implements Sink
  {
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
    
    private ChunkedSink() {}
    
    /* Error */
    public void close()
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 47	okhttp3/internal/http/Http1xStream$ChunkedSink:closed	Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifeq +6 -> 14
      //   11: aload_0
      //   12: monitorexit
      //   13: return
      //   14: aload_0
      //   15: iconst_1
      //   16: putfield 47	okhttp3/internal/http/Http1xStream$ChunkedSink:closed	Z
      //   19: aload_0
      //   20: getfield 19	okhttp3/internal/http/Http1xStream$ChunkedSink:this$0	Lokhttp3/internal/http/Http1xStream;
      //   23: invokestatic 28	okhttp3/internal/http/Http1xStream:access$300	(Lokhttp3/internal/http/Http1xStream;)Lokio/BufferedSink;
      //   26: ldc 49
      //   28: invokeinterface 53 2 0
      //   33: pop
      //   34: aload_0
      //   35: getfield 19	okhttp3/internal/http/Http1xStream$ChunkedSink:this$0	Lokhttp3/internal/http/Http1xStream;
      //   38: aload_0
      //   39: getfield 38	okhttp3/internal/http/Http1xStream$ChunkedSink:timeout	Lokio/ForwardingTimeout;
      //   42: invokestatic 57	okhttp3/internal/http/Http1xStream:access$400	(Lokhttp3/internal/http/Http1xStream;Lokio/ForwardingTimeout;)V
      //   45: aload_0
      //   46: getfield 19	okhttp3/internal/http/Http1xStream$ChunkedSink:this$0	Lokhttp3/internal/http/Http1xStream;
      //   49: iconst_3
      //   50: invokestatic 61	okhttp3/internal/http/Http1xStream:access$502	(Lokhttp3/internal/http/Http1xStream;I)I
      //   53: pop
      //   54: goto -43 -> 11
      //   57: astore_2
      //   58: aload_0
      //   59: monitorexit
      //   60: aload_2
      //   61: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	62	0	this	ChunkedSink
      //   6	2	1	bool	boolean
      //   57	4	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	7	57	finally
      //   14	54	57	finally
    }
    
    /* Error */
    public void flush()
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 47	okhttp3/internal/http/Http1xStream$ChunkedSink:closed	Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifeq +6 -> 14
      //   11: aload_0
      //   12: monitorexit
      //   13: return
      //   14: aload_0
      //   15: getfield 19	okhttp3/internal/http/Http1xStream$ChunkedSink:this$0	Lokhttp3/internal/http/Http1xStream;
      //   18: invokestatic 28	okhttp3/internal/http/Http1xStream:access$300	(Lokhttp3/internal/http/Http1xStream;)Lokio/BufferedSink;
      //   21: invokeinterface 65 1 0
      //   26: goto -15 -> 11
      //   29: astore_2
      //   30: aload_0
      //   31: monitorexit
      //   32: aload_2
      //   33: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	34	0	this	ChunkedSink
      //   6	2	1	bool	boolean
      //   29	4	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	7	29	finally
      //   14	26	29	finally
    }
    
    public Timeout timeout()
    {
      return this.timeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed) {
        throw new IllegalStateException("closed");
      }
      if (paramLong == 0L) {
        return;
      }
      Http1xStream.this.sink.writeHexadecimalUnsignedLong(paramLong);
      Http1xStream.this.sink.writeUtf8("\r\n");
      Http1xStream.this.sink.write(paramBuffer, paramLong);
      Http1xStream.this.sink.writeUtf8("\r\n");
    }
  }
  
  private class ChunkedSource
    extends Http1xStream.AbstractSource
  {
    private static final long NO_CHUNK_YET = -1L;
    private long bytesRemainingInChunk = -1L;
    private boolean hasMoreChunks = true;
    private final HttpUrl url;
    
    ChunkedSource(HttpUrl paramHttpUrl)
    {
      super(null);
      this.url = paramHttpUrl;
    }
    
    private void readChunkSize()
      throws IOException
    {
      if (this.bytesRemainingInChunk != -1L) {
        Http1xStream.this.source.readUtf8LineStrict();
      }
      try
      {
        this.bytesRemainingInChunk = Http1xStream.this.source.readHexadecimalUnsignedLong();
        String str = Http1xStream.this.source.readUtf8LineStrict().trim();
        if ((this.bytesRemainingInChunk < 0L) || ((!str.isEmpty()) && (!str.startsWith(";")))) {
          throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + str + "\"");
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException(localNumberFormatException.getMessage());
      }
      if (this.bytesRemainingInChunk == 0L)
      {
        this.hasMoreChunks = false;
        HttpHeaders.receiveHeaders(Http1xStream.this.client.cookieJar(), this.url, Http1xStream.this.readHeaders());
        endOfInput(true);
      }
    }
    
    public void close()
      throws IOException
    {
      if (this.closed) {
        return;
      }
      if ((this.hasMoreChunks) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
        endOfInput(false);
      }
      this.closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L) {
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      }
      if (this.closed) {
        throw new IllegalStateException("closed");
      }
      if (!this.hasMoreChunks) {
        return -1L;
      }
      if ((this.bytesRemainingInChunk == 0L) || (this.bytesRemainingInChunk == -1L))
      {
        readChunkSize();
        if (!this.hasMoreChunks) {
          return -1L;
        }
      }
      paramLong = Http1xStream.this.source.read(paramBuffer, Math.min(paramLong, this.bytesRemainingInChunk));
      if (paramLong == -1L)
      {
        endOfInput(false);
        throw new ProtocolException("unexpected end of stream");
      }
      this.bytesRemainingInChunk -= paramLong;
      return paramLong;
    }
  }
  
  private final class FixedLengthSink
    implements Sink
  {
    private long bytesRemaining;
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
    
    private FixedLengthSink(long paramLong)
    {
      this.bytesRemaining = paramLong;
    }
    
    public void close()
      throws IOException
    {
      if (this.closed) {
        return;
      }
      this.closed = true;
      if (this.bytesRemaining > 0L) {
        throw new ProtocolException("unexpected end of stream");
      }
      Http1xStream.this.detachTimeout(this.timeout);
      Http1xStream.access$502(Http1xStream.this, 3);
    }
    
    public void flush()
      throws IOException
    {
      if (this.closed) {
        return;
      }
      Http1xStream.this.sink.flush();
    }
    
    public Timeout timeout()
    {
      return this.timeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed) {
        throw new IllegalStateException("closed");
      }
      Util.checkOffsetAndCount(paramBuffer.size(), 0L, paramLong);
      if (paramLong > this.bytesRemaining) {
        throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + paramLong);
      }
      Http1xStream.this.sink.write(paramBuffer, paramLong);
      this.bytesRemaining -= paramLong;
    }
  }
  
  private class FixedLengthSource
    extends Http1xStream.AbstractSource
  {
    private long bytesRemaining;
    
    public FixedLengthSource(long paramLong)
      throws IOException
    {
      super(null);
      this.bytesRemaining = paramLong;
      if (this.bytesRemaining == 0L) {
        endOfInput(true);
      }
    }
    
    public void close()
      throws IOException
    {
      if (this.closed) {
        return;
      }
      if ((this.bytesRemaining != 0L) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
        endOfInput(false);
      }
      this.closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L) {
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      }
      if (this.closed) {
        throw new IllegalStateException("closed");
      }
      if (this.bytesRemaining == 0L) {
        paramLong = -1L;
      }
      long l;
      do
      {
        return paramLong;
        l = Http1xStream.this.source.read(paramBuffer, Math.min(this.bytesRemaining, paramLong));
        if (l == -1L)
        {
          endOfInput(false);
          throw new ProtocolException("unexpected end of stream");
        }
        this.bytesRemaining -= l;
        paramLong = l;
      } while (this.bytesRemaining != 0L);
      endOfInput(true);
      return l;
    }
  }
  
  private class UnknownLengthSource
    extends Http1xStream.AbstractSource
  {
    private boolean inputExhausted;
    
    private UnknownLengthSource()
    {
      super(null);
    }
    
    public void close()
      throws IOException
    {
      if (this.closed) {
        return;
      }
      if (!this.inputExhausted) {
        endOfInput(false);
      }
      this.closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L) {
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      }
      if (this.closed) {
        throw new IllegalStateException("closed");
      }
      if (this.inputExhausted) {
        paramLong = -1L;
      }
      long l;
      do
      {
        return paramLong;
        l = Http1xStream.this.source.read(paramBuffer, paramLong);
        paramLong = l;
      } while (l != -1L);
      this.inputExhausted = true;
      endOfInput(true);
      return -1L;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\http\Http1xStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */