package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache
  implements Closeable, Flushable
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  private final DiskLruCache cache;
  private int hitCount;
  final InternalCache internalCache = new InternalCache()
  {
    public Response get(Request paramAnonymousRequest)
      throws IOException
    {
      return Cache.this.get(paramAnonymousRequest);
    }
    
    public CacheRequest put(Response paramAnonymousResponse)
      throws IOException
    {
      return Cache.this.put(paramAnonymousResponse);
    }
    
    public void remove(Request paramAnonymousRequest)
      throws IOException
    {
      Cache.this.remove(paramAnonymousRequest);
    }
    
    public void trackConditionalCacheHit()
    {
      Cache.this.trackConditionalCacheHit();
    }
    
    public void trackResponse(CacheStrategy paramAnonymousCacheStrategy)
    {
      Cache.this.trackResponse(paramAnonymousCacheStrategy);
    }
    
    public void update(Response paramAnonymousResponse1, Response paramAnonymousResponse2)
    {
      Cache.this.update(paramAnonymousResponse1, paramAnonymousResponse2);
    }
  };
  private int networkCount;
  private int requestCount;
  private int writeAbortCount;
  private int writeSuccessCount;
  
  public Cache(File paramFile, long paramLong)
  {
    this(paramFile, paramLong, FileSystem.SYSTEM);
  }
  
  Cache(File paramFile, long paramLong, FileSystem paramFileSystem)
  {
    this.cache = DiskLruCache.create(paramFileSystem, paramFile, 201105, 2, paramLong);
  }
  
  private void abortQuietly(DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException paramEditor) {}
  }
  
  private CacheRequest put(Response paramResponse)
  {
    Object localObject = paramResponse.request().method();
    if (HttpMethod.invalidatesCache(paramResponse.request().method())) {}
    for (;;)
    {
      try
      {
        remove(paramResponse.request());
        return null;
      }
      catch (IOException paramResponse)
      {
        Entry localEntry;
        return null;
      }
      if ((((String)localObject).equals("GET")) && (!HttpHeaders.hasVaryAll(paramResponse)))
      {
        localEntry = new Entry(paramResponse);
        localObject = null;
      }
    }
    for (;;)
    {
      try
      {
        paramResponse = this.cache.edit(urlToKey(paramResponse.request()));
      }
      catch (IOException paramResponse)
      {
        abortQuietly((DiskLruCache.Editor)localObject);
        return null;
      }
      localObject = paramResponse;
      localEntry.writeTo(paramResponse);
      localObject = paramResponse;
      paramResponse = new CacheRequestImpl(paramResponse);
      return paramResponse;
      if (paramResponse == null) {
        break;
      }
    }
  }
  
  private static int readInt(BufferedSource paramBufferedSource)
    throws IOException
  {
    long l;
    try
    {
      l = paramBufferedSource.readDecimalLong();
      paramBufferedSource = paramBufferedSource.readUtf8LineStrict();
      if ((l < 0L) || (l > 2147483647L) || (!paramBufferedSource.isEmpty())) {
        throw new IOException("expected an int but was \"" + l + paramBufferedSource + "\"");
      }
    }
    catch (NumberFormatException paramBufferedSource)
    {
      throw new IOException(paramBufferedSource.getMessage());
    }
    return (int)l;
  }
  
  private void remove(Request paramRequest)
    throws IOException
  {
    this.cache.remove(urlToKey(paramRequest));
  }
  
  private void trackConditionalCacheHit()
  {
    try
    {
      this.hitCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  private void trackResponse(CacheStrategy paramCacheStrategy)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield 220	okhttp3/Cache:requestCount	I
    //   7: iconst_1
    //   8: iadd
    //   9: putfield 220	okhttp3/Cache:requestCount	I
    //   12: aload_1
    //   13: getfield 226	okhttp3/internal/cache/CacheStrategy:networkRequest	Lokhttp3/Request;
    //   16: ifnull +16 -> 32
    //   19: aload_0
    //   20: aload_0
    //   21: getfield 228	okhttp3/Cache:networkCount	I
    //   24: iconst_1
    //   25: iadd
    //   26: putfield 228	okhttp3/Cache:networkCount	I
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: aload_1
    //   33: getfield 232	okhttp3/internal/cache/CacheStrategy:cacheResponse	Lokhttp3/Response;
    //   36: ifnull -7 -> 29
    //   39: aload_0
    //   40: aload_0
    //   41: getfield 218	okhttp3/Cache:hitCount	I
    //   44: iconst_1
    //   45: iadd
    //   46: putfield 218	okhttp3/Cache:hitCount	I
    //   49: goto -20 -> 29
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	Cache
    //   0	57	1	paramCacheStrategy	CacheStrategy
    // Exception table:
    //   from	to	target	type
    //   2	29	52	finally
    //   32	49	52	finally
  }
  
  private void update(Response paramResponse1, Response paramResponse2)
  {
    Entry localEntry = new Entry(paramResponse2);
    paramResponse2 = ((CacheResponseBody)paramResponse1.body()).snapshot;
    paramResponse1 = null;
    for (;;)
    {
      try
      {
        paramResponse2 = paramResponse2.edit();
      }
      catch (IOException paramResponse2)
      {
        abortQuietly(paramResponse1);
        return;
      }
      paramResponse1 = paramResponse2;
      localEntry.writeTo(paramResponse2);
      paramResponse1 = paramResponse2;
      paramResponse2.commit();
      return;
      if (paramResponse2 == null) {}
    }
  }
  
  private static String urlToKey(Request paramRequest)
  {
    return Util.md5Hex(paramRequest.url().toString());
  }
  
  public void close()
    throws IOException
  {
    this.cache.close();
  }
  
  public void delete()
    throws IOException
  {
    this.cache.delete();
  }
  
  public File directory()
  {
    return this.cache.getDirectory();
  }
  
  public void evictAll()
    throws IOException
  {
    this.cache.evictAll();
  }
  
  public void flush()
    throws IOException
  {
    this.cache.flush();
  }
  
  Response get(Request paramRequest)
  {
    Object localObject = urlToKey(paramRequest);
    for (;;)
    {
      try
      {
        localObject = this.cache.get((String)localObject);
        if (localObject == null)
        {
          localObject = null;
          return (Response)localObject;
        }
      }
      catch (IOException paramRequest)
      {
        return null;
      }
      try
      {
        Entry localEntry = new Entry(((DiskLruCache.Snapshot)localObject).getSource(0));
        Response localResponse = localEntry.response((DiskLruCache.Snapshot)localObject);
        localObject = localResponse;
        if (!localEntry.matches(paramRequest, localResponse))
        {
          Util.closeQuietly(localResponse.body());
          return null;
        }
      }
      catch (IOException paramRequest)
      {
        Util.closeQuietly((Closeable)localObject);
      }
    }
    return null;
  }
  
  public int hitCount()
  {
    try
    {
      int i = this.hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void initialize()
    throws IOException
  {
    this.cache.initialize();
  }
  
  public boolean isClosed()
  {
    return this.cache.isClosed();
  }
  
  public long maxSize()
  {
    return this.cache.getMaxSize();
  }
  
  public int networkCount()
  {
    try
    {
      int i = this.networkCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int requestCount()
  {
    try
    {
      int i = this.requestCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long size()
    throws IOException
  {
    return this.cache.size();
  }
  
  public Iterator<String> urls()
    throws IOException
  {
    new Iterator()
    {
      boolean canRemove;
      final Iterator<DiskLruCache.Snapshot> delegate = Cache.this.cache.snapshots();
      String nextUrl;
      
      public boolean hasNext()
      {
        if (this.nextUrl != null) {
          return true;
        }
        this.canRemove = false;
        while (this.delegate.hasNext())
        {
          DiskLruCache.Snapshot localSnapshot = (DiskLruCache.Snapshot)this.delegate.next();
          try
          {
            this.nextUrl = Okio.buffer(localSnapshot.getSource(0)).readUtf8LineStrict();
            localSnapshot.close();
            return true;
          }
          catch (IOException localIOException)
          {
            localIOException = localIOException;
            localSnapshot.close();
          }
          finally
          {
            localObject = finally;
            localSnapshot.close();
            throw ((Throwable)localObject);
          }
        }
        return false;
      }
      
      public String next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        String str = this.nextUrl;
        this.nextUrl = null;
        this.canRemove = true;
        return str;
      }
      
      public void remove()
      {
        if (!this.canRemove) {
          throw new IllegalStateException("remove() before next()");
        }
        this.delegate.remove();
      }
    };
  }
  
  public int writeAbortCount()
  {
    try
    {
      int i = this.writeAbortCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int writeSuccessCount()
  {
    try
    {
      int i = this.writeSuccessCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private final class CacheRequestImpl
    implements CacheRequest
  {
    private Sink body;
    private Sink cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;
    
    public CacheRequestImpl(final DiskLruCache.Editor paramEditor)
    {
      this.editor = paramEditor;
      this.cacheOut = paramEditor.newSink(1);
      this.body = new ForwardingSink(this.cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (Cache.this)
          {
            if (Cache.CacheRequestImpl.this.done) {
              return;
            }
            Cache.CacheRequestImpl.access$702(Cache.CacheRequestImpl.this, true);
            Cache.access$808(Cache.this);
            super.close();
            paramEditor.commit();
            return;
          }
        }
      };
    }
    
    public void abort()
    {
      synchronized (Cache.this)
      {
        if (this.done) {
          return;
        }
        this.done = true;
        Cache.access$908(Cache.this);
        Util.closeQuietly(this.cacheOut);
        try
        {
          this.editor.abort();
          return;
        }
        catch (IOException localIOException) {}
      }
    }
    
    public Sink body()
    {
      return this.body;
    }
  }
  
  private static class CacheResponseBody
    extends ResponseBody
  {
    private final BufferedSource bodySource;
    private final String contentLength;
    private final String contentType;
    private final DiskLruCache.Snapshot snapshot;
    
    public CacheResponseBody(final DiskLruCache.Snapshot paramSnapshot, String paramString1, String paramString2)
    {
      this.snapshot = paramSnapshot;
      this.contentType = paramString1;
      this.contentLength = paramString2;
      this.bodySource = Okio.buffer(new ForwardingSource(paramSnapshot.getSource(1))
      {
        public void close()
          throws IOException
        {
          paramSnapshot.close();
          super.close();
        }
      });
    }
    
    public long contentLength()
    {
      long l = -1L;
      try
      {
        if (this.contentLength != null) {
          l = Long.parseLong(this.contentLength);
        }
        return l;
      }
      catch (NumberFormatException localNumberFormatException) {}
      return -1L;
    }
    
    public MediaType contentType()
    {
      if (this.contentType != null) {
        return MediaType.parse(this.contentType);
      }
      return null;
    }
    
    public BufferedSource source()
    {
      return this.bodySource;
    }
  }
  
  private static final class Entry
  {
    private static final String RECEIVED_MILLIS = Platform.get().getPrefix() + "-Received-Millis";
    private static final String SENT_MILLIS = Platform.get().getPrefix() + "-Sent-Millis";
    private final int code;
    private final Handshake handshake;
    private final String message;
    private final Protocol protocol;
    private final long receivedResponseMillis;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final long sentRequestMillis;
    private final String url;
    private final Headers varyHeaders;
    
    public Entry(Response paramResponse)
    {
      this.url = paramResponse.request().url().toString();
      this.varyHeaders = HttpHeaders.varyHeaders(paramResponse);
      this.requestMethod = paramResponse.request().method();
      this.protocol = paramResponse.protocol();
      this.code = paramResponse.code();
      this.message = paramResponse.message();
      this.responseHeaders = paramResponse.headers();
      this.handshake = paramResponse.handshake();
      this.sentRequestMillis = paramResponse.sentRequestAtMillis();
      this.receivedResponseMillis = paramResponse.receivedResponseAtMillis();
    }
    
    public Entry(Source paramSource)
      throws IOException
    {
      try
      {
        localBufferedSource1 = Okio.buffer(paramSource);
        this.url = localBufferedSource1.readUtf8LineStrict();
        this.requestMethod = localBufferedSource1.readUtf8LineStrict();
        localObject1 = new Headers.Builder();
        j = Cache.readInt(localBufferedSource1);
      }
      finally
      {
        BufferedSource localBufferedSource1;
        paramSource.close();
      }
      ((Headers.Builder)localObject1).addLenient(localBufferedSource1.readUtf8LineStrict());
      break label403;
      label66:
      this.varyHeaders = ((Headers.Builder)localObject1).build();
      Object localObject1 = StatusLine.parse(localBufferedSource1.readUtf8LineStrict());
      this.protocol = ((StatusLine)localObject1).protocol;
      this.code = ((StatusLine)localObject1).code;
      this.message = ((StatusLine)localObject1).message;
      localObject1 = new Headers.Builder();
      int j = Cache.readInt(localBufferedSource1);
      break label410;
      ((Headers.Builder)localObject1).addLenient(localBufferedSource1.readUtf8LineStrict());
      break label420;
      label148:
      Object localObject2 = ((Headers.Builder)localObject1).get(SENT_MILLIS);
      Object localObject3 = ((Headers.Builder)localObject1).get(RECEIVED_MILLIS);
      ((Headers.Builder)localObject1).removeAll(SENT_MILLIS);
      ((Headers.Builder)localObject1).removeAll(RECEIVED_MILLIS);
      break label427;
      label189:
      long l = Long.parseLong((String)localObject2);
      label196:
      this.sentRequestMillis = l;
      for (;;)
      {
        l = Long.parseLong((String)localObject3);
        label212:
        this.receivedResponseMillis = l;
        this.responseHeaders = ((Headers.Builder)localObject1).build();
        if (isHttps())
        {
          localObject1 = localBufferedSource1.readUtf8LineStrict();
          if (((String)localObject1).length() > 0)
          {
            throw new IOException("expected \"\" but was \"" + (String)localObject1 + "\"");
            label295:
            l = 0L;
            break label196;
          }
        }
        label403:
        label408:
        label410:
        label420:
        label425:
        label427:
        do
        {
          l = 0L;
          break label212;
          localObject1 = CipherSuite.forJavaName(localBufferedSource2.readUtf8LineStrict());
          localObject2 = readCertificateList(localBufferedSource2);
          localObject3 = readCertificateList(localBufferedSource2);
          TlsVersion localTlsVersion;
          if (!localBufferedSource2.exhausted()) {
            localTlsVersion = TlsVersion.forJavaName(localBufferedSource2.readUtf8LineStrict());
          }
          for (this.handshake = Handshake.get(localTlsVersion, (CipherSuite)localObject1, (List)localObject2, (List)localObject3);; this.handshake = null)
          {
            paramSource.close();
            return;
            localTlsVersion = null;
            break;
          }
          int i = 0;
          for (;;)
          {
            if (i >= j) {
              break label408;
            }
            break;
            i += 1;
          }
          break label66;
          i = 0;
          for (;;)
          {
            if (i >= j) {
              break label425;
            }
            break;
            i += 1;
          }
          break label148;
          if (localObject2 == null) {
            break label295;
          }
          break label189;
        } while (localObject3 == null);
      }
    }
    
    private boolean isHttps()
    {
      return this.url.startsWith("https://");
    }
    
    private List<Certificate> readCertificateList(BufferedSource paramBufferedSource)
      throws IOException
    {
      int j = Cache.readInt(paramBufferedSource);
      Object localObject;
      if (j == -1)
      {
        localObject = Collections.emptyList();
        return (List<Certificate>)localObject;
      }
      for (;;)
      {
        ArrayList localArrayList;
        try
        {
          localCertificateFactory = CertificateFactory.getInstance("X.509");
          localArrayList = new ArrayList(j);
        }
        catch (CertificateException paramBufferedSource)
        {
          CertificateFactory localCertificateFactory;
          Buffer localBuffer;
          throw new IOException(paramBufferedSource.getMessage());
        }
        localObject = paramBufferedSource.readUtf8LineStrict();
        localBuffer = new Buffer();
        localBuffer.write(ByteString.decodeBase64((String)localObject));
        localArrayList.add(localCertificateFactory.generateCertificate(localBuffer.inputStream()));
        i += 1;
        break label106;
        int i = 0;
        label106:
        localObject = localArrayList;
        if (i >= j) {
          break;
        }
      }
    }
    
    private void writeCertList(BufferedSink paramBufferedSink, List<Certificate> paramList)
      throws IOException
    {
      try
      {
        paramBufferedSink.writeDecimalLong(paramList.size()).writeByte(10);
      }
      catch (CertificateEncodingException paramBufferedSink)
      {
        throw new IOException(paramBufferedSink.getMessage());
      }
      int j = paramList.size();
      for (;;)
      {
        int i;
        paramBufferedSink.writeUtf8(ByteString.of(((Certificate)paramList.get(i)).getEncoded()).base64()).writeByte(10);
        i += 1;
        while (i >= j)
        {
          return;
          i = 0;
          break;
        }
      }
    }
    
    public boolean matches(Request paramRequest, Response paramResponse)
    {
      return (this.url.equals(paramRequest.url().toString())) && (this.requestMethod.equals(paramRequest.method())) && (HttpHeaders.varyMatches(paramResponse, this.varyHeaders, paramRequest));
    }
    
    public Response response(DiskLruCache.Snapshot paramSnapshot)
    {
      String str1 = this.responseHeaders.get("Content-Type");
      String str2 = this.responseHeaders.get("Content-Length");
      Request localRequest = new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build();
      return new Response.Builder().request(localRequest).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new Cache.CacheResponseBody(paramSnapshot, str1, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
    }
    
    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      paramEditor = Okio.buffer(paramEditor.newSink(0));
      paramEditor.writeUtf8(this.url).writeByte(10);
      paramEditor.writeUtf8(this.requestMethod).writeByte(10);
      paramEditor.writeDecimalLong(this.varyHeaders.size()).writeByte(10);
      int i = 0;
      int j = this.varyHeaders.size();
      while (i < j)
      {
        paramEditor.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
      paramEditor.writeDecimalLong(this.responseHeaders.size() + 2).writeByte(10);
      i = 0;
      j = this.responseHeaders.size();
      while (i < j)
      {
        paramEditor.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
      paramEditor.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
      if (isHttps())
      {
        paramEditor.writeByte(10);
        paramEditor.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
        writeCertList(paramEditor, this.handshake.peerCertificates());
        writeCertList(paramEditor, this.handshake.localCertificates());
        if (this.handshake.tlsVersion() != null) {
          paramEditor.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
        }
      }
      paramEditor.close();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\Cache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */