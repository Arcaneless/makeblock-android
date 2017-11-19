package com.unity3d.player;

import android.os.Build.VERSION;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class a
  extends SSLSocketFactory
{
  private static volatile SSLSocketFactory c;
  private static final Object[] d;
  private static final boolean e;
  private final SSLSocketFactory a;
  private final a b;
  
  static
  {
    boolean bool2 = false;
    d = new Object[0];
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 16)
    {
      bool1 = bool2;
      if (Build.VERSION.SDK_INT < 20) {
        bool1 = true;
      }
    }
    e = bool1;
  }
  
  private a()
  {
    SSLContext localSSLContext = SSLContext.getInstance("TLS");
    localSSLContext.init(null, null, null);
    this.a = localSSLContext.getSocketFactory();
    this.b = new a();
  }
  
  private static Socket a(Socket paramSocket)
  {
    if ((paramSocket != null) && ((paramSocket instanceof SSLSocket)) && (e)) {
      ((SSLSocket)paramSocket).setEnabledProtocols(((SSLSocket)paramSocket).getSupportedProtocols());
    }
    return paramSocket;
  }
  
  public static SSLSocketFactory a()
  {
    Object localObject1;
    synchronized (d)
    {
      if (c != null)
      {
        localObject1 = c;
        return (SSLSocketFactory)localObject1;
      }
    }
    return null;
  }
  
  public final Socket createSocket()
  {
    return a(this.a.createSocket());
  }
  
  public final Socket createSocket(String paramString, int paramInt)
  {
    return a(this.a.createSocket(paramString, paramInt));
  }
  
  public final Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
  {
    return a(this.a.createSocket(paramString, paramInt1, paramInetAddress, paramInt2));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress, int paramInt)
  {
    return a(this.a.createSocket(paramInetAddress, paramInt));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
  {
    return a(this.a.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2));
  }
  
  public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return a(this.a.createSocket(paramSocket, paramString, paramInt, paramBoolean));
  }
  
  public final String[] getDefaultCipherSuites()
  {
    return this.a.getDefaultCipherSuites();
  }
  
  public final String[] getSupportedCipherSuites()
  {
    return this.a.getSupportedCipherSuites();
  }
  
  final class a
    implements HandshakeCompletedListener
  {
    a() {}
    
    public final void handshakeCompleted(HandshakeCompletedEvent paramHandshakeCompletedEvent)
    {
      paramHandshakeCompletedEvent = paramHandshakeCompletedEvent.getSession();
      paramHandshakeCompletedEvent.getCipherSuite();
      paramHandshakeCompletedEvent.getProtocol();
      try
      {
        paramHandshakeCompletedEvent.getPeerPrincipal().getName();
        return;
      }
      catch (SSLPeerUnverifiedException paramHandshakeCompletedEvent) {}
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */