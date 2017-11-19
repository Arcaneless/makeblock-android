package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.FramedConnection;
import okhttp3.internal.framed.FramedConnection.Builder;
import okhttp3.internal.framed.FramedConnection.Listener;
import okhttp3.internal.framed.FramedStream;
import okhttp3.internal.http.Http1xStream;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public final class RealConnection
  extends FramedConnection.Listener
  implements Connection
{
  public int allocationLimit;
  public final List<Reference<StreamAllocation>> allocations = new ArrayList();
  public volatile FramedConnection framedConnection;
  private Handshake handshake;
  public long idleAtNanos = Long.MAX_VALUE;
  public boolean noNewStreams;
  private Protocol protocol;
  private Socket rawSocket;
  private final Route route;
  public BufferedSink sink;
  public Socket socket;
  public BufferedSource source;
  public int successCount;
  
  public RealConnection(Route paramRoute)
  {
    this.route = paramRoute;
  }
  
  private void buildConnection(int paramInt1, int paramInt2, int paramInt3, ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    connectSocket(paramInt1, paramInt2);
    establishProtocol(paramInt2, paramInt3, paramConnectionSpecSelector);
  }
  
  private void buildTunneledConnection(int paramInt1, int paramInt2, int paramInt3, ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    Request localRequest = createTunnelRequest();
    HttpUrl localHttpUrl = localRequest.url();
    int i = 0;
    for (;;)
    {
      i += 1;
      if (i > 21) {
        throw new ProtocolException("Too many tunnel connections attempted: " + 21);
      }
      connectSocket(paramInt1, paramInt2);
      localRequest = createTunnel(paramInt2, paramInt3, localRequest, localHttpUrl);
      if (localRequest == null)
      {
        establishProtocol(paramInt2, paramInt3, paramConnectionSpecSelector);
        return;
      }
      Util.closeQuietly(this.rawSocket);
      this.rawSocket = null;
      this.sink = null;
      this.source = null;
    }
  }
  
  private void connectSocket(int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject = this.route.proxy();
    Address localAddress = this.route.address();
    if ((((Proxy)localObject).type() == Proxy.Type.DIRECT) || (((Proxy)localObject).type() == Proxy.Type.HTTP)) {}
    for (localObject = localAddress.socketFactory().createSocket();; localObject = new Socket((Proxy)localObject))
    {
      this.rawSocket = ((Socket)localObject);
      this.rawSocket.setSoTimeout(paramInt2);
      try
      {
        Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), paramInt1);
        this.source = Okio.buffer(Okio.source(this.rawSocket));
        this.sink = Okio.buffer(Okio.sink(this.rawSocket));
        return;
      }
      catch (ConnectException localConnectException)
      {
        throw new ConnectException("Failed to connect to " + this.route.socketAddress());
      }
    }
  }
  
  private void connectTls(int paramInt1, int paramInt2, ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    Address localAddress = this.route.address();
    Object localObject3 = localAddress.sslSocketFactory();
    Object localObject2 = null;
    Object localObject1 = null;
    Handshake localHandshake;
    try
    {
      localObject3 = (SSLSocket)((SSLSocketFactory)localObject3).createSocket(this.rawSocket, localAddress.url().host(), localAddress.url().port(), true);
      localObject1 = localObject3;
      localObject2 = localObject3;
      paramConnectionSpecSelector = paramConnectionSpecSelector.configureSecureSocket((SSLSocket)localObject3);
      localObject1 = localObject3;
      localObject2 = localObject3;
      if (paramConnectionSpecSelector.supportsTlsExtensions())
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        Platform.get().configureTlsExtensions((SSLSocket)localObject3, localAddress.url().host(), localAddress.protocols());
      }
      localObject1 = localObject3;
      localObject2 = localObject3;
      ((SSLSocket)localObject3).startHandshake();
      localObject1 = localObject3;
      localObject2 = localObject3;
      localHandshake = Handshake.get(((SSLSocket)localObject3).getSession());
      localObject1 = localObject3;
      localObject2 = localObject3;
      if (!localAddress.hostnameVerifier().verify(localAddress.url().host(), ((SSLSocket)localObject3).getSession()))
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        paramConnectionSpecSelector = (X509Certificate)localHandshake.peerCertificates().get(0);
        localObject1 = localObject3;
        localObject2 = localObject3;
        throw new SSLPeerUnverifiedException("Hostname " + localAddress.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(paramConnectionSpecSelector) + "\n    DN: " + paramConnectionSpecSelector.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(paramConnectionSpecSelector));
      }
    }
    catch (AssertionError paramConnectionSpecSelector)
    {
      localObject2 = localObject1;
      if (!Util.isAndroidGetsocknameError(paramConnectionSpecSelector)) {
        break label542;
      }
      localObject2 = localObject1;
      throw new IOException(paramConnectionSpecSelector);
    }
    finally
    {
      if (localObject2 != null) {
        Platform.get().afterHandshake((SSLSocket)localObject2);
      }
      if (0 == 0) {
        Util.closeQuietly((Socket)localObject2);
      }
    }
    localObject1 = localObject3;
    localObject2 = localObject3;
    localAddress.certificatePinner().check(localAddress.url().host(), localHandshake.peerCertificates());
    localObject1 = localObject3;
    localObject2 = localObject3;
    if (paramConnectionSpecSelector.supportsTlsExtensions())
    {
      localObject1 = localObject3;
      localObject2 = localObject3;
      paramConnectionSpecSelector = Platform.get().getSelectedProtocol((SSLSocket)localObject3);
      localObject1 = localObject3;
      localObject2 = localObject3;
      this.socket = ((Socket)localObject3);
      localObject1 = localObject3;
      localObject2 = localObject3;
      this.source = Okio.buffer(Okio.source(this.socket));
      localObject1 = localObject3;
      localObject2 = localObject3;
      this.sink = Okio.buffer(Okio.sink(this.socket));
      localObject1 = localObject3;
      localObject2 = localObject3;
      this.handshake = localHandshake;
    }
    for (;;)
    {
      localObject1 = localObject3;
      localObject2 = localObject3;
      paramConnectionSpecSelector = Protocol.get(paramConnectionSpecSelector);
      localObject1 = localObject3;
      localObject2 = localObject3;
      this.protocol = paramConnectionSpecSelector;
      if (localObject3 != null) {
        Platform.get().afterHandshake((SSLSocket)localObject3);
      }
      if (1 == 0) {
        Util.closeQuietly((Socket)localObject3);
      }
      return;
      paramConnectionSpecSelector = null;
      break;
      label542:
      do
      {
        localObject1 = localObject3;
        localObject2 = localObject3;
        paramConnectionSpecSelector = Protocol.HTTP_1_1;
        break;
        localObject2 = localObject1;
        throw paramConnectionSpecSelector;
      } while (paramConnectionSpecSelector == null);
    }
  }
  
  private Request createTunnel(int paramInt1, int paramInt2, Request paramRequest, HttpUrl paramHttpUrl)
    throws IOException
  {
    Object localObject = null;
    String str = "CONNECT " + Util.hostHeader(paramHttpUrl, true) + " HTTP/1.1";
    Response localResponse;
    do
    {
      paramHttpUrl = new Http1xStream(null, null, this.source, this.sink);
      this.source.timeout().timeout(paramInt1, TimeUnit.MILLISECONDS);
      this.sink.timeout().timeout(paramInt2, TimeUnit.MILLISECONDS);
      paramHttpUrl.writeRequest(paramRequest.headers(), str);
      paramHttpUrl.finishRequest();
      localResponse = paramHttpUrl.readResponse().request(paramRequest).build();
      long l2 = HttpHeaders.contentLength(localResponse);
      long l1 = l2;
      if (l2 == -1L) {
        l1 = 0L;
      }
      paramRequest = paramHttpUrl.newFixedLengthSource(l1);
      Util.skipAll(paramRequest, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
      paramRequest.close();
      switch (localResponse.code())
      {
      default: 
        throw new IOException("Unexpected response code for CONNECT: " + localResponse.code());
      case 200: 
        if (this.source.buffer().exhausted())
        {
          paramHttpUrl = (HttpUrl)localObject;
          if (this.sink.buffer().exhausted()) {
            break;
          }
        }
        throw new IOException("TLS tunnel buffered too many bytes!");
      case 407: 
        paramHttpUrl = this.route.address().proxyAuthenticator().authenticate(this.route, localResponse);
        if (paramHttpUrl == null) {
          throw new IOException("Failed to authenticate with proxy");
        }
        paramRequest = paramHttpUrl;
      }
    } while (!"close".equalsIgnoreCase(localResponse.header("Connection")));
    return paramHttpUrl;
  }
  
  private Request createTunnelRequest()
  {
    return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
  }
  
  private void establishProtocol(int paramInt1, int paramInt2, ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    if (this.route.address().sslSocketFactory() != null) {
      connectTls(paramInt1, paramInt2, paramConnectionSpecSelector);
    }
    while ((this.protocol == Protocol.SPDY_3) || (this.protocol == Protocol.HTTP_2))
    {
      this.socket.setSoTimeout(0);
      paramConnectionSpecSelector = new FramedConnection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).protocol(this.protocol).listener(this).build();
      paramConnectionSpecSelector.start();
      this.allocationLimit = paramConnectionSpecSelector.maxConcurrentStreams();
      this.framedConnection = paramConnectionSpecSelector;
      return;
      this.protocol = Protocol.HTTP_1_1;
      this.socket = this.rawSocket;
    }
    this.allocationLimit = 1;
  }
  
  public void cancel()
  {
    Util.closeQuietly(this.rawSocket);
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, List<ConnectionSpec> paramList, boolean paramBoolean)
  {
    if (this.protocol != null) {
      throw new IllegalStateException("already connected");
    }
    Object localObject2 = null;
    ConnectionSpecSelector localConnectionSpecSelector = new ConnectionSpecSelector(paramList);
    Object localObject1 = localObject2;
    if (this.route.address().sslSocketFactory() == null)
    {
      if (!paramList.contains(ConnectionSpec.CLEARTEXT)) {
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
      }
      paramList = this.route.address().url().host();
      localObject1 = localObject2;
      if (!Platform.get().isCleartextTrafficPermitted(paramList)) {
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + paramList + " not permitted by network security policy"));
      }
    }
    for (;;)
    {
      if (this.protocol == null) {
        try
        {
          if (this.route.requiresTunnel()) {
            buildTunneledConnection(paramInt1, paramInt2, paramInt3, localConnectionSpecSelector);
          }
        }
        catch (IOException localIOException)
        {
          Util.closeQuietly(this.socket);
          Util.closeQuietly(this.rawSocket);
          this.socket = null;
          this.rawSocket = null;
          this.source = null;
          this.sink = null;
          this.handshake = null;
          this.protocol = null;
          if (localObject1 == null) {}
          for (paramList = new RouteException(localIOException);; paramList = (List<ConnectionSpec>)localObject1)
          {
            if (paramBoolean)
            {
              localObject1 = paramList;
              if (localConnectionSpecSelector.connectionFailed(localIOException)) {
                break;
              }
            }
            throw paramList;
            buildConnection(paramInt1, paramInt2, paramInt3, localConnectionSpecSelector);
            break;
            ((RouteException)localObject1).addConnectException(localIOException);
          }
        }
      }
    }
  }
  
  public Handshake handshake()
  {
    return this.handshake;
  }
  
  public boolean isHealthy(boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((this.socket.isClosed()) || (this.socket.isInputShutdown()) || (this.socket.isOutputShutdown())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (this.framedConnection != null);
      bool1 = bool2;
    } while (!paramBoolean);
    try
    {
      int i = this.socket.getSoTimeout();
      try
      {
        this.socket.setSoTimeout(1);
        paramBoolean = this.source.exhausted();
        if (paramBoolean) {
          this.socket.setSoTimeout(i);
        } else {
          return true;
        }
      }
      finally
      {
        this.socket.setSoTimeout(i);
      }
      return true;
    }
    catch (IOException localIOException)
    {
      return false;
      return false;
    }
    catch (SocketTimeoutException localSocketTimeoutException) {}
  }
  
  public boolean isMultiplexed()
  {
    return this.framedConnection != null;
  }
  
  public void onSettings(FramedConnection paramFramedConnection)
  {
    this.allocationLimit = paramFramedConnection.maxConcurrentStreams();
  }
  
  public void onStream(FramedStream paramFramedStream)
    throws IOException
  {
    paramFramedStream.close(ErrorCode.REFUSED_STREAM);
  }
  
  public Protocol protocol()
  {
    if (this.framedConnection == null)
    {
      if (this.protocol != null) {
        return this.protocol;
      }
      return Protocol.HTTP_1_1;
    }
    return this.framedConnection.getProtocol();
  }
  
  public Route route()
  {
    return this.route;
  }
  
  public Socket socket()
  {
    return this.socket;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Connection{").append(this.route.address().url().host()).append(":").append(this.route.address().url().port()).append(", proxy=").append(this.route.proxy()).append(" hostAddress=").append(this.route.socketAddress()).append(" cipherSuite=");
    if (this.handshake != null) {}
    for (Object localObject = this.handshake.cipherSuite();; localObject = "none") {
      return localObject + " protocol=" + this.protocol + '}';
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\connection\RealConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */