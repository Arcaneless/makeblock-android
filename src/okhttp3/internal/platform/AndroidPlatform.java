package okhttp3.internal.platform;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;

class AndroidPlatform
  extends Platform
{
  private static final int MAX_LOG_LENGTH = 4000;
  private final OptionalMethod<Socket> getAlpnSelectedProtocol;
  private final OptionalMethod<Socket> setAlpnProtocols;
  private final OptionalMethod<Socket> setHostname;
  private final OptionalMethod<Socket> setUseSessionTickets;
  private final Class<?> sslParametersClass;
  
  public AndroidPlatform(Class<?> paramClass, OptionalMethod<Socket> paramOptionalMethod1, OptionalMethod<Socket> paramOptionalMethod2, OptionalMethod<Socket> paramOptionalMethod3, OptionalMethod<Socket> paramOptionalMethod4)
  {
    this.sslParametersClass = paramClass;
    this.setUseSessionTickets = paramOptionalMethod1;
    this.setHostname = paramOptionalMethod2;
    this.getAlpnSelectedProtocol = paramOptionalMethod3;
    this.setAlpnProtocols = paramOptionalMethod4;
  }
  
  public static Platform buildIfSupported()
  {
    for (;;)
    {
      try
      {
        localClass = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
      }
      catch (ClassNotFoundException localClassNotFoundException3)
      {
        Class localClass;
        OptionalMethod localOptionalMethod5;
        OptionalMethod localOptionalMethod6;
        OptionalMethod localOptionalMethod4;
        OptionalMethod localOptionalMethod3;
        OptionalMethod localOptionalMethod1;
        continue;
      }
      try
      {
        localOptionalMethod5 = new OptionalMethod(null, "setUseSessionTickets", new Class[] { Boolean.TYPE });
        localOptionalMethod6 = new OptionalMethod(null, "setHostname", new Class[] { String.class });
        localOptionalMethod4 = null;
        localOptionalMethod3 = null;
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        return null;
      }
      try
      {
        Class.forName("android.net.Network");
        localOptionalMethod1 = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        OptionalMethod localOptionalMethod2 = localOptionalMethod4;
        continue;
      }
      try
      {
        localOptionalMethod4 = new OptionalMethod(null, "setAlpnProtocols", new Class[] { byte[].class });
        localOptionalMethod3 = localOptionalMethod4;
      }
      catch (ClassNotFoundException localClassNotFoundException4)
      {
        continue;
      }
      return new AndroidPlatform(localClass, localOptionalMethod5, localOptionalMethod6, localOptionalMethod1, localOptionalMethod3);
      localClass = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
    }
  }
  
  public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = Class.forName("android.net.http.X509TrustManagerExtensions");
      localObject = new AndroidCertificateChainCleaner(((Class)localObject).getConstructor(new Class[] { X509TrustManager.class }).newInstance(new Object[] { paramX509TrustManager }), ((Class)localObject).getMethod("checkServerTrusted", new Class[] { X509Certificate[].class, String.class, String.class }));
      return (CertificateChainCleaner)localObject;
    }
    catch (Exception localException) {}
    return super.buildCertificateChainCleaner(paramX509TrustManager);
  }
  
  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    if (paramString != null)
    {
      this.setUseSessionTickets.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
      this.setHostname.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
    if ((this.setAlpnProtocols != null) && (this.setAlpnProtocols.isSupported(paramSSLSocket)))
    {
      paramString = concatLengthPrefixed(paramList);
      this.setAlpnProtocols.invokeWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
  }
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    try
    {
      paramSocket.connect(paramInetSocketAddress, paramInt);
      return;
    }
    catch (AssertionError paramSocket)
    {
      if (Util.isAndroidGetsocknameError(paramSocket)) {
        throw new IOException(paramSocket);
      }
      throw paramSocket;
    }
    catch (SecurityException paramSocket)
    {
      paramInetSocketAddress = new IOException("Exception in connect");
      paramInetSocketAddress.initCause(paramSocket);
      throw paramInetSocketAddress;
    }
  }
  
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    if (this.getAlpnSelectedProtocol == null) {}
    while (!this.getAlpnSelectedProtocol.isSupported(paramSSLSocket)) {
      return null;
    }
    paramSSLSocket = (byte[])this.getAlpnSelectedProtocol.invokeWithoutCheckedException(paramSSLSocket, new Object[0]);
    if (paramSSLSocket != null) {}
    for (paramSSLSocket = new String(paramSSLSocket, Util.UTF_8);; paramSSLSocket = null) {
      return paramSSLSocket;
    }
  }
  
  public boolean isCleartextTrafficPermitted(String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.security.NetworkSecurityPolicy");
      Object localObject = localClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      boolean bool = ((Boolean)localClass.getMethod("isCleartextTrafficPermitted", new Class[] { String.class }).invoke(localObject, new Object[] { paramString })).booleanValue();
      return bool;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return super.isCleartextTrafficPermitted(paramString);
    }
    catch (IllegalAccessException paramString)
    {
      throw new AssertionError();
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramString)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
  }
  
  public void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    int i = 5;
    String str;
    int m;
    label52:
    int j;
    if (paramInt == 5)
    {
      str = paramString;
      if (paramThrowable != null) {
        str = paramString + '\n' + Log.getStackTraceString(paramThrowable);
      }
      paramInt = 0;
      m = str.length();
      if (paramInt >= m) {
        return;
      }
      j = str.indexOf('\n', paramInt);
      if (j == -1) {
        break label126;
      }
    }
    for (;;)
    {
      int k = Math.min(j, paramInt + 4000);
      Log.println(i, "OkHttp", str.substring(paramInt, k));
      paramInt = k;
      if (k >= j)
      {
        paramInt = k + 1;
        break label52;
        i = 3;
        break;
        label126:
        j = m;
      }
    }
  }
  
  public X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    Object localObject2 = readFieldOrNull(paramSSLSocketFactory, this.sslParametersClass, "sslParameters");
    Object localObject1 = localObject2;
    if (localObject2 == null) {}
    try
    {
      localObject1 = readFieldOrNull(paramSSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, paramSSLSocketFactory.getClass().getClassLoader()), "sslParameters");
      paramSSLSocketFactory = (X509TrustManager)readFieldOrNull(localObject1, X509TrustManager.class, "x509TrustManager");
      if (paramSSLSocketFactory != null) {
        return paramSSLSocketFactory;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return super.trustManager(paramSSLSocketFactory);
    }
    return (X509TrustManager)readFieldOrNull(localClassNotFoundException, X509TrustManager.class, "trustManager");
  }
  
  static final class AndroidCertificateChainCleaner
    extends CertificateChainCleaner
  {
    private final Method checkServerTrusted;
    private final Object x509TrustManagerExtensions;
    
    AndroidCertificateChainCleaner(Object paramObject, Method paramMethod)
    {
      this.x509TrustManagerExtensions = paramObject;
      this.checkServerTrusted = paramMethod;
    }
    
    public List<Certificate> clean(List<Certificate> paramList, String paramString)
      throws SSLPeerUnverifiedException
    {
      try
      {
        paramList = (X509Certificate[])paramList.toArray(new X509Certificate[paramList.size()]);
        paramList = (List)this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, new Object[] { paramList, "RSA", paramString });
        return paramList;
      }
      catch (InvocationTargetException paramList)
      {
        paramString = new SSLPeerUnverifiedException(paramList.getMessage());
        paramString.initCause(paramList);
        throw paramString;
      }
      catch (IllegalAccessException paramList)
      {
        throw new AssertionError(paramList);
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\platform\AndroidPlatform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */