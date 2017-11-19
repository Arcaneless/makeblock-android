package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.StreamResetException;
import okhttp3.internal.http.HttpStream;

public final class StreamAllocation
{
  public final Address address;
  private boolean canceled;
  private RealConnection connection;
  private final ConnectionPool connectionPool;
  private int refusedStreamCount;
  private boolean released;
  private Route route;
  private final RouteSelector routeSelector;
  private HttpStream stream;
  
  public StreamAllocation(ConnectionPool paramConnectionPool, Address paramAddress)
  {
    this.connectionPool = paramConnectionPool;
    this.address = paramAddress;
    this.routeSelector = new RouteSelector(paramAddress, routeDatabase());
  }
  
  private void deallocate(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    ConnectionPool localConnectionPool = this.connectionPool;
    label32:
    Object localObject1;
    if (paramBoolean3)
    {
      try
      {
        this.stream = null;
      }
      finally {}
      this.released = true;
      localObject1 = localObject4;
      if (this.connection != null) {
        break label187;
      }
    }
    for (;;)
    {
      this.connection.noNewStreams = true;
      label187:
      do
      {
        localObject1 = localObject4;
        if (this.stream == null) {
          if (!this.released)
          {
            localObject1 = localObject4;
            if (!this.connection.noNewStreams) {}
          }
          else
          {
            release(this.connection);
            localObject1 = localObject3;
            if (this.connection.allocations.isEmpty())
            {
              this.connection.idleAtNanos = System.nanoTime();
              localObject1 = localObject3;
              if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                localObject1 = this.connection;
              }
            }
            this.connection = null;
          }
        }
        if (localObject1 != null) {
          Util.closeQuietly(((RealConnection)localObject1).socket());
        }
        return;
        if (!paramBoolean2) {
          break label32;
        }
        break;
      } while (!paramBoolean1);
    }
  }
  
  private RealConnection findConnection(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws IOException
  {
    synchronized (this.connectionPool)
    {
      if (this.released) {
        throw new IllegalStateException("released");
      }
    }
    if (this.stream != null) {
      throw new IllegalStateException("stream != null");
    }
    if (this.canceled) {
      throw new IOException("Canceled");
    }
    ??? = this.connection;
    if ((??? != null) && (!((RealConnection)???).noNewStreams)) {
      return (RealConnection)???;
    }
    ??? = Internal.instance.get(this.connectionPool, this.address, this);
    for (;;)
    {
      this.connection = ((RealConnection)???);
      return (RealConnection)???;
      do
      {
        ??? = this.route;
        ??? = ???;
        if (??? == null) {
          ??? = this.routeSelector.next();
        }
        synchronized (this.connectionPool)
        {
          this.route = ((Route)???);
          this.refusedStreamCount = 0;
          ??? = new RealConnection((Route)???);
          acquire((RealConnection)???);
          synchronized (this.connectionPool)
          {
            Internal.instance.put(this.connectionPool, (RealConnection)???);
            this.connection = ((RealConnection)???);
            if (this.canceled) {
              throw new IOException("Canceled");
            }
          }
        }
        localRealConnection.connect(paramInt1, paramInt2, paramInt3, this.address.connectionSpecs(), paramBoolean);
        routeDatabase().connected(localRealConnection.route());
        return localRealConnection;
      } while (localRealConnection == null);
    }
  }
  
  private RealConnection findHealthyConnection(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    for (;;)
    {
      RealConnection localRealConnection1 = findConnection(paramInt1, paramInt2, paramInt3, paramBoolean1);
      synchronized (this.connectionPool)
      {
        if (localRealConnection1.successCount == 0) {
          return localRealConnection1;
        }
        if (!localRealConnection1.isHealthy(paramBoolean2)) {
          noNewStreams();
        }
      }
    }
    return localRealConnection2;
  }
  
  private void release(RealConnection paramRealConnection)
  {
    int i = 0;
    int j = paramRealConnection.allocations.size();
    while (i < j)
    {
      if (((Reference)paramRealConnection.allocations.get(i)).get() == this)
      {
        paramRealConnection.allocations.remove(i);
        return;
      }
      i += 1;
    }
    throw new IllegalStateException();
  }
  
  private RouteDatabase routeDatabase()
  {
    return Internal.instance.routeDatabase(this.connectionPool);
  }
  
  public void acquire(RealConnection paramRealConnection)
  {
    paramRealConnection.allocations.add(new WeakReference(this));
  }
  
  public void cancel()
  {
    RealConnection localRealConnection;
    do
    {
      synchronized (this.connectionPool)
      {
        this.canceled = true;
        HttpStream localHttpStream = this.stream;
        localRealConnection = this.connection;
        if (localHttpStream != null)
        {
          localHttpStream.cancel();
          return;
        }
      }
    } while (localRealConnection == null);
    localRealConnection.cancel();
  }
  
  public RealConnection connection()
  {
    try
    {
      RealConnection localRealConnection = this.connection;
      return localRealConnection;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean hasMoreRoutes()
  {
    return (this.route != null) || (this.routeSelector.hasNext());
  }
  
  /* Error */
  public HttpStream newStream(okhttp3.OkHttpClient paramOkHttpClient, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 215	okhttp3/OkHttpClient:connectTimeoutMillis	()I
    //   4: istore_3
    //   5: aload_1
    //   6: invokevirtual 218	okhttp3/OkHttpClient:readTimeoutMillis	()I
    //   9: istore 4
    //   11: aload_1
    //   12: invokevirtual 221	okhttp3/OkHttpClient:writeTimeoutMillis	()I
    //   15: istore 5
    //   17: aload_1
    //   18: invokevirtual 224	okhttp3/OkHttpClient:retryOnConnectionFailure	()Z
    //   21: istore 6
    //   23: aload_0
    //   24: iload_3
    //   25: iload 4
    //   27: iload 5
    //   29: iload 6
    //   31: iload_2
    //   32: invokespecial 226	okhttp3/internal/connection/StreamAllocation:findHealthyConnection	(IIIZZ)Lokhttp3/internal/connection/RealConnection;
    //   35: astore 7
    //   37: aload 7
    //   39: getfield 230	okhttp3/internal/connection/RealConnection:framedConnection	Lokhttp3/internal/framed/FramedConnection;
    //   42: ifnull +37 -> 79
    //   45: new 232	okhttp3/internal/http/Http2xStream
    //   48: dup
    //   49: aload_1
    //   50: aload_0
    //   51: aload 7
    //   53: getfield 230	okhttp3/internal/connection/RealConnection:framedConnection	Lokhttp3/internal/framed/FramedConnection;
    //   56: invokespecial 235	okhttp3/internal/http/Http2xStream:<init>	(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/StreamAllocation;Lokhttp3/internal/framed/FramedConnection;)V
    //   59: astore_1
    //   60: aload_0
    //   61: getfield 28	okhttp3/internal/connection/StreamAllocation:connectionPool	Lokhttp3/ConnectionPool;
    //   64: astore 7
    //   66: aload 7
    //   68: monitorenter
    //   69: aload_0
    //   70: aload_1
    //   71: putfield 46	okhttp3/internal/connection/StreamAllocation:stream	Lokhttp3/internal/http/HttpStream;
    //   74: aload 7
    //   76: monitorexit
    //   77: aload_1
    //   78: areturn
    //   79: aload 7
    //   81: invokevirtual 93	okhttp3/internal/connection/RealConnection:socket	()Ljava/net/Socket;
    //   84: iload 4
    //   86: invokevirtual 241	java/net/Socket:setSoTimeout	(I)V
    //   89: aload 7
    //   91: getfield 245	okhttp3/internal/connection/RealConnection:source	Lokio/BufferedSource;
    //   94: invokeinterface 251 1 0
    //   99: iload 4
    //   101: i2l
    //   102: getstatic 257	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   105: invokevirtual 262	okio/Timeout:timeout	(JLjava/util/concurrent/TimeUnit;)Lokio/Timeout;
    //   108: pop
    //   109: aload 7
    //   111: getfield 266	okhttp3/internal/connection/RealConnection:sink	Lokio/BufferedSink;
    //   114: invokeinterface 269 1 0
    //   119: iload 5
    //   121: i2l
    //   122: getstatic 257	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   125: invokevirtual 262	okio/Timeout:timeout	(JLjava/util/concurrent/TimeUnit;)Lokio/Timeout;
    //   128: pop
    //   129: new 271	okhttp3/internal/http/Http1xStream
    //   132: dup
    //   133: aload_1
    //   134: aload_0
    //   135: aload 7
    //   137: getfield 245	okhttp3/internal/connection/RealConnection:source	Lokio/BufferedSource;
    //   140: aload 7
    //   142: getfield 266	okhttp3/internal/connection/RealConnection:sink	Lokio/BufferedSink;
    //   145: invokespecial 274	okhttp3/internal/http/Http1xStream:<init>	(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/StreamAllocation;Lokio/BufferedSource;Lokio/BufferedSink;)V
    //   148: astore_1
    //   149: goto -89 -> 60
    //   152: astore_1
    //   153: aload 7
    //   155: monitorexit
    //   156: aload_1
    //   157: athrow
    //   158: astore_1
    //   159: new 276	okhttp3/internal/connection/RouteException
    //   162: dup
    //   163: aload_1
    //   164: invokespecial 279	okhttp3/internal/connection/RouteException:<init>	(Ljava/io/IOException;)V
    //   167: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	StreamAllocation
    //   0	168	1	paramOkHttpClient	okhttp3.OkHttpClient
    //   0	168	2	paramBoolean	boolean
    //   4	21	3	i	int
    //   9	91	4	j	int
    //   15	105	5	k	int
    //   21	9	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   69	77	152	finally
    //   153	156	152	finally
    //   23	60	158	java/io/IOException
    //   60	69	158	java/io/IOException
    //   79	149	158	java/io/IOException
    //   156	158	158	java/io/IOException
  }
  
  public void noNewStreams()
  {
    deallocate(true, false, false);
  }
  
  public void release()
  {
    deallocate(false, true, false);
  }
  
  public HttpStream stream()
  {
    synchronized (this.connectionPool)
    {
      HttpStream localHttpStream = this.stream;
      return localHttpStream;
    }
  }
  
  public void streamFailed(IOException paramIOException)
  {
    boolean bool2 = false;
    for (;;)
    {
      synchronized (this.connectionPool)
      {
        if ((paramIOException instanceof StreamResetException))
        {
          paramIOException = (StreamResetException)paramIOException;
          if (paramIOException.errorCode == ErrorCode.REFUSED_STREAM) {
            this.refusedStreamCount += 1;
          }
          if (paramIOException.errorCode == ErrorCode.REFUSED_STREAM)
          {
            bool1 = bool2;
            if (this.refusedStreamCount > 1)
            {
              break label157;
              this.route = null;
            }
            else
            {
              deallocate(bool1, false, true);
            }
          }
        }
        else
        {
          bool1 = bool2;
          if (this.connection == null) {
            continue;
          }
          bool1 = bool2;
          if (this.connection.isMultiplexed()) {
            continue;
          }
          break label162;
          bool1 = bool2;
          if (this.connection.successCount != 0) {
            continue;
          }
          if ((this.route != null) && (paramIOException != null)) {
            this.routeSelector.connectFailed(this.route, paramIOException);
          }
          this.route = null;
          bool1 = bool2;
        }
      }
      label157:
      boolean bool1 = true;
      continue;
      label162:
      bool2 = true;
    }
  }
  
  public void streamFinished(boolean paramBoolean, HttpStream paramHttpStream)
  {
    ConnectionPool localConnectionPool = this.connectionPool;
    if (paramHttpStream != null) {}
    try
    {
      if (paramHttpStream != this.stream) {
        throw new IllegalStateException("expected " + this.stream + " but was " + paramHttpStream);
      }
    }
    finally
    {
      throw paramHttpStream;
      if (!paramBoolean)
      {
        paramHttpStream = this.connection;
        paramHttpStream.successCount += 1;
      }
    }
  }
  
  public String toString()
  {
    return this.address.toString();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\connection\StreamAllocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */