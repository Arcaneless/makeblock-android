package okhttp3.internal.framed;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class FramedConnection
  implements Closeable
{
  private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
  private static final ExecutorService executor;
  long bytesLeftInWriteWindow;
  final boolean client;
  private final Set<Integer> currentPushRequests = new LinkedHashSet();
  final FrameWriter frameWriter;
  private final String hostname;
  private int lastGoodStreamId;
  private final Listener listener;
  private int nextPingId;
  private int nextStreamId;
  Settings okHttpSettings = new Settings();
  final Settings peerSettings = new Settings();
  private Map<Integer, Ping> pings;
  final Protocol protocol;
  private final ExecutorService pushExecutor;
  private final PushObserver pushObserver;
  final Reader readerRunnable;
  private boolean receivedInitialPeerSettings = false;
  private boolean shutdown;
  final Socket socket;
  private final Map<Integer, FramedStream> streams = new HashMap();
  long unacknowledgedBytesRead = 0L;
  final Variant variant;
  
  static
  {
    if (!FramedConnection.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp FramedConnection", true));
      return;
    }
  }
  
  private FramedConnection(Builder paramBuilder)
  {
    this.protocol = paramBuilder.protocol;
    this.pushObserver = paramBuilder.pushObserver;
    this.client = paramBuilder.client;
    this.listener = paramBuilder.listener;
    int i;
    if (paramBuilder.client)
    {
      i = 1;
      this.nextStreamId = i;
      if ((paramBuilder.client) && (this.protocol == Protocol.HTTP_2)) {
        this.nextStreamId += 2;
      }
      i = j;
      if (paramBuilder.client) {
        i = 1;
      }
      this.nextPingId = i;
      if (paramBuilder.client) {
        this.okHttpSettings.set(7, 0, 16777216);
      }
      this.hostname = paramBuilder.hostname;
      if (this.protocol != Protocol.HTTP_2) {
        break label349;
      }
      this.variant = new Http2();
      this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", new Object[] { this.hostname }), true));
      this.peerSettings.set(7, 0, 65535);
      this.peerSettings.set(5, 0, 16384);
    }
    for (;;)
    {
      this.bytesLeftInWriteWindow = this.peerSettings.getInitialWindowSize(65536);
      this.socket = paramBuilder.socket;
      this.frameWriter = this.variant.newWriter(paramBuilder.sink, this.client);
      this.readerRunnable = new Reader(this.variant.newReader(paramBuilder.source, this.client), null);
      return;
      i = 2;
      break;
      label349:
      if (this.protocol != Protocol.SPDY_3) {
        break label378;
      }
      this.variant = new Spdy3();
      this.pushExecutor = null;
    }
    label378:
    throw new AssertionError(this.protocol);
  }
  
  private void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    Object localObject = null;
    try
    {
      shutdown(paramErrorCode1);
      paramErrorCode1 = (ErrorCode)localObject;
    }
    catch (IOException paramErrorCode1)
    {
      FramedStream[] arrayOfFramedStream;
      for (;;) {}
    }
    arrayOfFramedStream = null;
    Ping[] arrayOfPing = null;
    int j;
    int i;
    for (;;)
    {
      try
      {
        if (!this.streams.isEmpty())
        {
          arrayOfFramedStream = (FramedStream[])this.streams.values().toArray(new FramedStream[this.streams.size()]);
          this.streams.clear();
        }
        if (this.pings != null)
        {
          arrayOfPing = (Ping[])this.pings.values().toArray(new Ping[this.pings.size()]);
          this.pings = null;
        }
        localObject = paramErrorCode1;
        if (arrayOfFramedStream == null) {
          break label211;
        }
        j = arrayOfFramedStream.length;
        i = 0;
        localObject = paramErrorCode1;
        if (i >= j) {
          break label211;
        }
        localObject = arrayOfFramedStream[i];
      }
      finally {}
      try
      {
        ((FramedStream)localObject).close(paramErrorCode2);
        localObject = paramErrorCode1;
      }
      catch (IOException localIOException)
      {
        localObject = paramErrorCode1;
        if (paramErrorCode1 == null) {
          continue;
        }
        localObject = localIOException;
        continue;
      }
      i += 1;
      paramErrorCode1 = (ErrorCode)localObject;
    }
    label211:
    if (arrayOfPing != null)
    {
      j = arrayOfPing.length;
      i = 0;
      while (i < j)
      {
        arrayOfPing[i].cancel();
        i += 1;
      }
    }
    try
    {
      this.frameWriter.close();
      paramErrorCode1 = (ErrorCode)localObject;
      return;
    }
    catch (IOException paramErrorCode2)
    {
      try
      {
        for (;;)
        {
          this.socket.close();
          if (paramErrorCode1 == null) {
            break;
          }
          throw paramErrorCode1;
          paramErrorCode2 = paramErrorCode2;
          paramErrorCode1 = (ErrorCode)localObject;
          if (localObject == null) {
            paramErrorCode1 = paramErrorCode2;
          }
        }
      }
      catch (IOException paramErrorCode1)
      {
        for (;;) {}
      }
    }
  }
  
  private FramedStream newStream(int paramInt, List<Header> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    boolean bool;
    if (!paramBoolean1)
    {
      bool = true;
      if (paramBoolean2) {
        break label61;
      }
    }
    label61:
    for (paramBoolean2 = true;; paramBoolean2 = false)
    {
      synchronized (this.frameWriter)
      {
        try
        {
          if (!this.shutdown) {
            break label67;
          }
          throw new IOException("shutdown");
        }
        finally {}
      }
      bool = false;
      break;
    }
    label67:
    int j = this.nextStreamId;
    this.nextStreamId += 2;
    FramedStream localFramedStream = new FramedStream(j, this, bool, paramBoolean2, paramList);
    if ((paramBoolean1) && (this.bytesLeftInWriteWindow != 0L)) {
      if (localFramedStream.bytesLeftInWriteWindow == 0L) {
        break label234;
      }
    }
    for (;;)
    {
      if (localFramedStream.isOpen()) {
        this.streams.put(Integer.valueOf(j), localFramedStream);
      }
      if (paramInt == 0) {
        this.frameWriter.synStream(bool, paramBoolean2, j, paramInt, paramList);
      }
      for (;;)
      {
        if (i != 0) {
          this.frameWriter.flush();
        }
        return localFramedStream;
        i = 0;
        break;
        if (this.client) {
          throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
        }
        this.frameWriter.pushPromise(paramInt, j, paramList);
      }
      label234:
      int i = 1;
    }
  }
  
  private void pushDataLater(final int paramInt1, BufferedSource paramBufferedSource, final int paramInt2, final boolean paramBoolean)
    throws IOException
  {
    final Buffer localBuffer = new Buffer();
    paramBufferedSource.require(paramInt2);
    paramBufferedSource.read(localBuffer, paramInt2);
    if (localBuffer.size() != paramInt2) {
      throw new IOException(localBuffer.size() + " != " + paramInt2);
    }
    this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[] { this.hostname, Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          boolean bool = FramedConnection.this.pushObserver.onData(paramInt1, localBuffer, paramInt2, paramBoolean);
          if (bool) {
            FramedConnection.this.frameWriter.rstStream(paramInt1, ErrorCode.CANCEL);
          }
          if ((bool) || (paramBoolean)) {
            synchronized (FramedConnection.this)
            {
              FramedConnection.this.currentPushRequests.remove(Integer.valueOf(paramInt1));
              return;
            }
          }
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  private void pushHeadersLater(final int paramInt, final List<Header> paramList, final boolean paramBoolean)
  {
    this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[] { this.hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        boolean bool = FramedConnection.this.pushObserver.onHeaders(paramInt, paramList, paramBoolean);
        if (bool) {}
        try
        {
          FramedConnection.this.frameWriter.rstStream(paramInt, ErrorCode.CANCEL);
          if ((bool) || (paramBoolean)) {
            synchronized (FramedConnection.this)
            {
              FramedConnection.this.currentPushRequests.remove(Integer.valueOf(paramInt));
              return;
            }
          }
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  private void pushRequestLater(final int paramInt, final List<Header> paramList)
  {
    try
    {
      if (this.currentPushRequests.contains(Integer.valueOf(paramInt)))
      {
        writeSynResetLater(paramInt, ErrorCode.PROTOCOL_ERROR);
        return;
      }
      this.currentPushRequests.add(Integer.valueOf(paramInt));
      this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[] { this.hostname, Integer.valueOf(paramInt) })
      {
        public void execute()
        {
          if (FramedConnection.this.pushObserver.onRequest(paramInt, paramList)) {
            try
            {
              FramedConnection.this.frameWriter.rstStream(paramInt, ErrorCode.CANCEL);
              synchronized (FramedConnection.this)
              {
                FramedConnection.this.currentPushRequests.remove(Integer.valueOf(paramInt));
                return;
              }
              return;
            }
            catch (IOException localIOException) {}
          }
        }
      });
      return;
    }
    finally {}
  }
  
  private void pushResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[] { this.hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        FramedConnection.this.pushObserver.onReset(paramInt, paramErrorCode);
        synchronized (FramedConnection.this)
        {
          FramedConnection.this.currentPushRequests.remove(Integer.valueOf(paramInt));
          return;
        }
      }
    });
  }
  
  private boolean pushedStream(int paramInt)
  {
    return (this.protocol == Protocol.HTTP_2) && (paramInt != 0) && ((paramInt & 0x1) == 0);
  }
  
  /* Error */
  private Ping removePing(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 376	okhttp3/internal/framed/FramedConnection:pings	Ljava/util/Map;
    //   6: ifnull +24 -> 30
    //   9: aload_0
    //   10: getfield 376	okhttp3/internal/framed/FramedConnection:pings	Ljava/util/Map;
    //   13: iload_1
    //   14: invokestatic 411	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   17: invokeinterface 517 2 0
    //   22: checkcast 378	okhttp3/internal/framed/Ping
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: areturn
    //   30: aconst_null
    //   31: astore_2
    //   32: goto -6 -> 26
    //   35: astore_2
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_2
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	FramedConnection
    //   0	40	1	paramInt	int
    //   25	7	2	localPing	Ping
    //   35	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	26	35	finally
  }
  
  private void writePing(boolean paramBoolean, int paramInt1, int paramInt2, Ping paramPing)
    throws IOException
  {
    FrameWriter localFrameWriter = this.frameWriter;
    if (paramPing != null) {}
    try
    {
      paramPing.send();
      this.frameWriter.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    finally {}
  }
  
  private void writePingLater(final boolean paramBoolean, final int paramInt1, final int paramInt2, final Ping paramPing)
  {
    executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[] { this.hostname, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) })
    {
      public void execute()
      {
        try
        {
          FramedConnection.this.writePing(paramBoolean, paramInt1, paramInt2, paramPing);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void addBytesToWriteWindow(long paramLong)
  {
    this.bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L) {
      notifyAll();
    }
  }
  
  public void close()
    throws IOException
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
  }
  
  public void flush()
    throws IOException
  {
    this.frameWriter.flush();
  }
  
  public Protocol getProtocol()
  {
    return this.protocol;
  }
  
  FramedStream getStream(int paramInt)
  {
    try
    {
      FramedStream localFramedStream = (FramedStream)this.streams.get(Integer.valueOf(paramInt));
      return localFramedStream;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int maxConcurrentStreams()
  {
    try
    {
      int i = this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public FramedStream newStream(List<Header> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    return newStream(0, paramList, paramBoolean1, paramBoolean2);
  }
  
  public int openStreamCount()
  {
    try
    {
      int i = this.streams.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Ping ping()
    throws IOException
  {
    Ping localPing = new Ping();
    try
    {
      if (this.shutdown) {
        throw new IOException("shutdown");
      }
    }
    finally {}
    int i = this.nextPingId;
    this.nextPingId += 2;
    if (this.pings == null) {
      this.pings = new HashMap();
    }
    this.pings.put(Integer.valueOf(i), localObject);
    writePing(false, i, 1330343787, (Ping)localObject);
    return (Ping)localObject;
  }
  
  public FramedStream pushStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    if (this.client) {
      throw new IllegalStateException("Client cannot push requests.");
    }
    if (this.protocol != Protocol.HTTP_2) {
      throw new IllegalStateException("protocol != HTTP_2");
    }
    return newStream(paramInt, paramList, paramBoolean, false);
  }
  
  FramedStream removeStream(int paramInt)
  {
    try
    {
      FramedStream localFramedStream = (FramedStream)this.streams.remove(Integer.valueOf(paramInt));
      notifyAll();
      return localFramedStream;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setSettings(Settings paramSettings)
    throws IOException
  {
    synchronized (this.frameWriter)
    {
      try
      {
        if (this.shutdown) {
          throw new IOException("shutdown");
        }
      }
      finally {}
    }
    this.okHttpSettings.merge(paramSettings);
    this.frameWriter.settings(paramSettings);
  }
  
  public void shutdown(ErrorCode paramErrorCode)
    throws IOException
  {
    int i;
    synchronized (this.frameWriter) {}
  }
  
  public void start()
    throws IOException
  {
    start(true);
  }
  
  void start(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      this.frameWriter.connectionPreface();
      this.frameWriter.settings(this.okHttpSettings);
      int i = this.okHttpSettings.getInitialWindowSize(65536);
      if (i != 65536) {
        this.frameWriter.windowUpdate(0, i - 65536);
      }
    }
    new Thread(this.readerRunnable).start();
  }
  
  public void writeData(int paramInt, boolean paramBoolean, Buffer paramBuffer, long paramLong)
    throws IOException
  {
    long l = paramLong;
    if (paramLong == 0L)
    {
      this.frameWriter.data(paramBoolean, paramInt, paramBuffer, 0);
      return;
    }
    for (;;)
    {
      try
      {
        int i = Math.min((int)Math.min(l, this.bytesLeftInWriteWindow), this.frameWriter.maxDataLength());
        this.bytesLeftInWriteWindow -= i;
        l -= i;
        FrameWriter localFrameWriter = this.frameWriter;
        if ((!paramBoolean) || (l != 0L)) {
          break label170;
        }
        bool = true;
        localFrameWriter.data(bool, paramInt, paramBuffer, i);
        if (l <= 0L) {
          break;
        }
        try
        {
          if (this.bytesLeftInWriteWindow > 0L) {
            continue;
          }
          if (!this.streams.containsKey(Integer.valueOf(paramInt))) {
            throw new IOException("stream closed");
          }
        }
        catch (InterruptedException paramBuffer)
        {
          throw new InterruptedIOException();
        }
        wait();
      }
      finally {}
      continue;
      label170:
      boolean bool = false;
    }
  }
  
  void writeSynReply(int paramInt, boolean paramBoolean, List<Header> paramList)
    throws IOException
  {
    this.frameWriter.synReply(paramBoolean, paramInt, paramList);
  }
  
  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    this.frameWriter.rstStream(paramInt, paramErrorCode);
  }
  
  void writeSynResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    executor.submit(new NamedRunnable("OkHttp %s stream %d", new Object[] { this.hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        try
        {
          FramedConnection.this.writeSynReset(paramInt, paramErrorCode);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void writeWindowUpdateLater(final int paramInt, final long paramLong)
  {
    executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[] { this.hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        try
        {
          FramedConnection.this.frameWriter.windowUpdate(paramInt, paramLong);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  public static class Builder
  {
    private boolean client;
    private String hostname;
    private FramedConnection.Listener listener = FramedConnection.Listener.REFUSE_INCOMING_STREAMS;
    private Protocol protocol = Protocol.SPDY_3;
    private PushObserver pushObserver = PushObserver.CANCEL;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    
    public Builder(boolean paramBoolean)
    {
      this.client = paramBoolean;
    }
    
    public FramedConnection build()
      throws IOException
    {
      return new FramedConnection(this, null);
    }
    
    public Builder listener(FramedConnection.Listener paramListener)
    {
      this.listener = paramListener;
      return this;
    }
    
    public Builder protocol(Protocol paramProtocol)
    {
      this.protocol = paramProtocol;
      return this;
    }
    
    public Builder pushObserver(PushObserver paramPushObserver)
    {
      this.pushObserver = paramPushObserver;
      return this;
    }
    
    public Builder socket(Socket paramSocket)
      throws IOException
    {
      return socket(paramSocket, ((InetSocketAddress)paramSocket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(paramSocket)), Okio.buffer(Okio.sink(paramSocket)));
    }
    
    public Builder socket(Socket paramSocket, String paramString, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      this.socket = paramSocket;
      this.hostname = paramString;
      this.source = paramBufferedSource;
      this.sink = paramBufferedSink;
      return this;
    }
  }
  
  public static abstract class Listener
  {
    public static final Listener REFUSE_INCOMING_STREAMS = new Listener()
    {
      public void onStream(FramedStream paramAnonymousFramedStream)
        throws IOException
      {
        paramAnonymousFramedStream.close(ErrorCode.REFUSED_STREAM);
      }
    };
    
    public void onSettings(FramedConnection paramFramedConnection) {}
    
    public abstract void onStream(FramedStream paramFramedStream)
      throws IOException;
  }
  
  class Reader
    extends NamedRunnable
    implements FrameReader.Handler
  {
    final FrameReader frameReader;
    
    private Reader(FrameReader paramFrameReader)
    {
      super(new Object[] { FramedConnection.this.hostname });
      this.frameReader = paramFrameReader;
    }
    
    private void applyAndAckSettings(final Settings paramSettings)
    {
      FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[] { FramedConnection.this.hostname })
      {
        public void execute()
        {
          try
          {
            FramedConnection.this.frameWriter.applyAndAckSettings(paramSettings);
            return;
          }
          catch (IOException localIOException) {}
        }
      });
    }
    
    public void ackSettings() {}
    
    public void alternateService(int paramInt1, String paramString1, ByteString paramByteString, String paramString2, int paramInt2, long paramLong) {}
    
    public void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
      throws IOException
    {
      if (FramedConnection.this.pushedStream(paramInt1)) {
        FramedConnection.this.pushDataLater(paramInt1, paramBufferedSource, paramInt2, paramBoolean);
      }
      FramedStream localFramedStream;
      do
      {
        return;
        localFramedStream = FramedConnection.this.getStream(paramInt1);
        if (localFramedStream == null)
        {
          FramedConnection.this.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
          paramBufferedSource.skip(paramInt2);
          return;
        }
        localFramedStream.receiveData(paramBufferedSource, paramInt2);
      } while (!paramBoolean);
      localFramedStream.receiveFin();
    }
    
    /* Error */
    protected void execute()
    {
      // Byte code:
      //   0: getstatic 106	okhttp3/internal/framed/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/framed/ErrorCode;
      //   3: astore_3
      //   4: getstatic 106	okhttp3/internal/framed/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/framed/ErrorCode;
      //   7: astore 4
      //   9: aload_3
      //   10: astore_2
      //   11: aload_3
      //   12: astore_1
      //   13: aload_0
      //   14: getfield 23	okhttp3/internal/framed/FramedConnection$Reader:this$0	Lokhttp3/internal/framed/FramedConnection;
      //   17: getfield 110	okhttp3/internal/framed/FramedConnection:client	Z
      //   20: ifne +16 -> 36
      //   23: aload_3
      //   24: astore_2
      //   25: aload_3
      //   26: astore_1
      //   27: aload_0
      //   28: getfield 36	okhttp3/internal/framed/FramedConnection$Reader:frameReader	Lokhttp3/internal/framed/FrameReader;
      //   31: invokeinterface 115 1 0
      //   36: aload_3
      //   37: astore_2
      //   38: aload_3
      //   39: astore_1
      //   40: aload_0
      //   41: getfield 36	okhttp3/internal/framed/FramedConnection$Reader:frameReader	Lokhttp3/internal/framed/FrameReader;
      //   44: aload_0
      //   45: invokeinterface 119 2 0
      //   50: ifne -14 -> 36
      //   53: aload_3
      //   54: astore_2
      //   55: aload_3
      //   56: astore_1
      //   57: getstatic 122	okhttp3/internal/framed/ErrorCode:NO_ERROR	Lokhttp3/internal/framed/ErrorCode;
      //   60: astore_3
      //   61: aload_3
      //   62: astore_2
      //   63: aload_3
      //   64: astore_1
      //   65: getstatic 125	okhttp3/internal/framed/ErrorCode:CANCEL	Lokhttp3/internal/framed/ErrorCode;
      //   68: astore 5
      //   70: aload_0
      //   71: getfield 23	okhttp3/internal/framed/FramedConnection$Reader:this$0	Lokhttp3/internal/framed/FramedConnection;
      //   74: aload_3
      //   75: aload 5
      //   77: invokestatic 129	okhttp3/internal/framed/FramedConnection:access$1200	(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
      //   80: aload_0
      //   81: getfield 36	okhttp3/internal/framed/FramedConnection$Reader:frameReader	Lokhttp3/internal/framed/FrameReader;
      //   84: invokestatic 135	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   87: return
      //   88: astore_1
      //   89: aload_2
      //   90: astore_1
      //   91: getstatic 138	okhttp3/internal/framed/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/framed/ErrorCode;
      //   94: astore_2
      //   95: aload_2
      //   96: astore_1
      //   97: getstatic 138	okhttp3/internal/framed/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/framed/ErrorCode;
      //   100: astore_3
      //   101: aload_0
      //   102: getfield 23	okhttp3/internal/framed/FramedConnection$Reader:this$0	Lokhttp3/internal/framed/FramedConnection;
      //   105: aload_2
      //   106: aload_3
      //   107: invokestatic 129	okhttp3/internal/framed/FramedConnection:access$1200	(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
      //   110: aload_0
      //   111: getfield 36	okhttp3/internal/framed/FramedConnection$Reader:frameReader	Lokhttp3/internal/framed/FrameReader;
      //   114: invokestatic 135	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   117: return
      //   118: astore_2
      //   119: aload_0
      //   120: getfield 23	okhttp3/internal/framed/FramedConnection$Reader:this$0	Lokhttp3/internal/framed/FramedConnection;
      //   123: aload_1
      //   124: aload 4
      //   126: invokestatic 129	okhttp3/internal/framed/FramedConnection:access$1200	(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
      //   129: aload_0
      //   130: getfield 36	okhttp3/internal/framed/FramedConnection$Reader:frameReader	Lokhttp3/internal/framed/FrameReader;
      //   133: invokestatic 135	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   136: aload_2
      //   137: athrow
      //   138: astore_1
      //   139: goto -10 -> 129
      //   142: astore_1
      //   143: goto -33 -> 110
      //   146: astore_1
      //   147: goto -67 -> 80
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	150	0	this	Reader
      //   12	53	1	localErrorCode1	ErrorCode
      //   88	1	1	localIOException1	IOException
      //   90	34	1	localErrorCode2	ErrorCode
      //   138	1	1	localIOException2	IOException
      //   142	1	1	localIOException3	IOException
      //   146	1	1	localIOException4	IOException
      //   10	96	2	localErrorCode3	ErrorCode
      //   118	19	2	localObject	Object
      //   3	104	3	localErrorCode4	ErrorCode
      //   7	118	4	localErrorCode5	ErrorCode
      //   68	8	5	localErrorCode6	ErrorCode
      // Exception table:
      //   from	to	target	type
      //   13	23	88	java/io/IOException
      //   27	36	88	java/io/IOException
      //   40	53	88	java/io/IOException
      //   57	61	88	java/io/IOException
      //   65	70	88	java/io/IOException
      //   13	23	118	finally
      //   27	36	118	finally
      //   40	53	118	finally
      //   57	61	118	finally
      //   65	70	118	finally
      //   91	95	118	finally
      //   97	101	118	finally
      //   119	129	138	java/io/IOException
      //   101	110	142	java/io/IOException
      //   70	80	146	java/io/IOException
    }
    
    public void goAway(int paramInt, ErrorCode arg2, ByteString paramByteString)
    {
      if (paramByteString.size() > 0) {}
      synchronized (FramedConnection.this)
      {
        paramByteString = (FramedStream[])FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
        FramedConnection.access$1602(FramedConnection.this, true);
        int j = paramByteString.length;
        int i = 0;
        if (i < j)
        {
          ??? = paramByteString[i];
          if ((???.getId() > paramInt) && (???.isLocallyInitiated()))
          {
            ???.receiveRstStream(ErrorCode.REFUSED_STREAM);
            FramedConnection.this.removeStream(???.getId());
          }
          i += 1;
        }
      }
    }
    
    public void headers(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, final List<Header> paramList, HeadersMode paramHeadersMode)
    {
      if (FramedConnection.this.pushedStream(paramInt1))
      {
        FramedConnection.this.pushHeadersLater(paramInt1, paramList, paramBoolean2);
        return;
      }
      synchronized (FramedConnection.this)
      {
        if (FramedConnection.this.shutdown) {
          return;
        }
      }
      FramedStream localFramedStream = FramedConnection.this.getStream(paramInt1);
      for (;;)
      {
        if (paramHeadersMode.failIfStreamAbsent())
        {
          FramedConnection.this.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
          return;
        }
        if (paramInt1 <= FramedConnection.this.lastGoodStreamId) {
          return;
        }
        if (paramInt1 % 2 == FramedConnection.this.nextStreamId % 2) {
          return;
        }
        paramList = new FramedStream(paramInt1, FramedConnection.this, paramBoolean1, paramBoolean2, paramList);
        FramedConnection.access$1702(FramedConnection.this, paramInt1);
        FramedConnection.this.streams.put(Integer.valueOf(paramInt1), paramList);
        FramedConnection.executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { FramedConnection.this.hostname, Integer.valueOf(paramInt1) })
        {
          public void execute()
          {
            try
            {
              FramedConnection.this.listener.onStream(paramList);
              return;
            }
            catch (IOException localIOException1)
            {
              Platform.get().log(4, "FramedConnection.Listener failure for " + FramedConnection.this.hostname, localIOException1);
              try
              {
                paramList.close(ErrorCode.PROTOCOL_ERROR);
                return;
              }
              catch (IOException localIOException2) {}
            }
          }
        });
        return;
        do
        {
          if (paramHeadersMode.failIfStreamPresent())
          {
            localFramedStream.closeLater(ErrorCode.PROTOCOL_ERROR);
            FramedConnection.this.removeStream(paramInt1);
            return;
          }
          localFramedStream.receiveHeaders(paramList, paramHeadersMode);
          if (!paramBoolean2) {
            break;
          }
          localFramedStream.receiveFin();
          return;
        } while (localFramedStream != null);
      }
    }
    
    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
      {
        Ping localPing = FramedConnection.this.removePing(paramInt1);
        if (localPing != null) {
          localPing.receive();
        }
        return;
      }
      FramedConnection.this.writePingLater(true, paramInt1, paramInt2, null);
    }
    
    public void priority(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {}
    
    public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    {
      FramedConnection.this.pushRequestLater(paramInt2, paramList);
    }
    
    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      if (FramedConnection.this.pushedStream(paramInt)) {
        FramedConnection.this.pushResetLater(paramInt, paramErrorCode);
      }
      FramedStream localFramedStream;
      do
      {
        return;
        localFramedStream = FramedConnection.this.removeStream(paramInt);
      } while (localFramedStream == null);
      localFramedStream.receiveRstStream(paramErrorCode);
    }
    
    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      long l2 = 0L;
      ??? = null;
      for (;;)
      {
        int i;
        int j;
        synchronized (FramedConnection.this)
        {
          i = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
          if (paramBoolean) {
            FramedConnection.this.peerSettings.clear();
          }
          FramedConnection.this.peerSettings.merge(paramSettings);
          if (FramedConnection.this.getProtocol() == Protocol.HTTP_2) {
            applyAndAckSettings(paramSettings);
          }
          j = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
          break label277;
          if (!FramedConnection.this.receivedInitialPeerSettings)
          {
            FramedConnection.this.addBytesToWriteWindow(l2);
            FramedConnection.access$2302(FramedConnection.this, true);
          }
          l1 = l2;
          paramSettings = (Settings)???;
          if (!FramedConnection.this.streams.isEmpty())
          {
            paramSettings = (FramedStream[])FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
            l1 = l2;
          }
          FramedConnection.executor.execute(new NamedRunnable("OkHttp %s settings", new Object[] { FramedConnection.this.hostname })
          {
            public void execute()
            {
              FramedConnection.this.listener.onSettings(FramedConnection.this);
            }
          });
          if ((paramSettings == null) || (l1 == 0L)) {
            break label276;
          }
          j = paramSettings.length;
          i = 0;
          if (i >= j) {
            break label276;
          }
        }
        synchronized (paramSettings[i])
        {
          ((FramedStream)???).addBytesToWriteWindow(l1);
          i += 1;
          continue;
          paramSettings = finally;
          throw paramSettings;
        }
        label276:
        return;
        label277:
        long l1 = l2;
        paramSettings = (Settings)???;
        if (j != -1)
        {
          l1 = l2;
          paramSettings = (Settings)???;
          if (j != i) {
            l2 = j - i;
          }
        }
      }
    }
    
    public void windowUpdate(int paramInt, long paramLong)
    {
      if (paramInt == 0) {
        synchronized (FramedConnection.this)
        {
          FramedConnection localFramedConnection = FramedConnection.this;
          localFramedConnection.bytesLeftInWriteWindow += paramLong;
          FramedConnection.this.notifyAll();
          return;
        }
      }
      ??? = FramedConnection.this.getStream(paramInt);
      if (??? != null) {
        try
        {
          ((FramedStream)???).addBytesToWriteWindow(paramLong);
          return;
        }
        finally {}
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\framed\FramedConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */