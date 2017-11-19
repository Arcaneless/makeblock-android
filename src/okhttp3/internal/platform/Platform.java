package okhttp3.internal.platform;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;

public class Platform
{
  public static final int INFO = 4;
  private static final Platform PLATFORM = ;
  public static final int WARN = 5;
  private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());
  
  public static List<String> alpnProtocolNames(List<Protocol> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol == Protocol.HTTP_1_0) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localProtocol.toString());
      }
    }
    return localArrayList;
  }
  
  static byte[] concatLengthPrefixed(List<Protocol> paramList)
  {
    Buffer localBuffer = new Buffer();
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol == Protocol.HTTP_1_0) {}
      for (;;)
      {
        i += 1;
        break;
        localBuffer.writeByte(localProtocol.toString().length());
        localBuffer.writeUtf8(localProtocol.toString());
      }
    }
    return localBuffer.readByteArray();
  }
  
  private static Platform findPlatform()
  {
    Object localObject = AndroidPlatform.buildIfSupported();
    if (localObject != null) {
      return (Platform)localObject;
    }
    localObject = Jdk9Platform.buildIfSupported();
    if (localObject != null) {
      return (Platform)localObject;
    }
    localObject = JdkWithJettyBootPlatform.buildIfSupported();
    if (localObject != null) {
      return (Platform)localObject;
    }
    return new Platform();
  }
  
  public static Platform get()
  {
    return PLATFORM;
  }
  
  static <T> T readFieldOrNull(Object paramObject, Class<T> paramClass, String paramString)
  {
    Object localObject3 = null;
    Class localClass = paramObject.getClass();
    while (localClass != Object.class) {
      try
      {
        Object localObject1 = localClass.getDeclaredField(paramString);
        ((Field)localObject1).setAccessible(true);
        Object localObject4 = ((Field)localObject1).get(paramObject);
        localObject1 = localObject3;
        if (localObject4 == null) {
          break label123;
        }
        if (!paramClass.isInstance(localObject4)) {
          return null;
        }
        localObject1 = paramClass.cast(localObject4);
        return (T)localObject1;
      }
      catch (IllegalAccessException paramObject)
      {
        throw new AssertionError();
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        localClass = localClass.getSuperclass();
      }
    }
    Object localObject2 = localObject3;
    if (!paramString.equals("delegate"))
    {
      paramObject = readFieldOrNull(paramObject, Object.class, "delegate");
      localObject2 = localObject3;
      if (paramObject != null) {
        localObject2 = readFieldOrNull(paramObject, paramClass, paramString);
      }
    }
    label123:
    return (T)localObject2;
  }
  
  public void afterHandshake(SSLSocket paramSSLSocket) {}
  
  public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager paramX509TrustManager)
  {
    return new BasicCertificateChainCleaner(TrustRootIndex.get(paramX509TrustManager));
  }
  
  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList) {}
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }
  
  public String getPrefix()
  {
    return "OkHttp";
  }
  
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    return null;
  }
  
  public boolean isCleartextTrafficPermitted(String paramString)
  {
    return true;
  }
  
  public void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    if (paramInt == 5) {}
    for (Level localLevel = Level.WARNING;; localLevel = Level.INFO)
    {
      logger.log(localLevel, paramString, paramThrowable);
      return;
    }
  }
  
  public X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    do
    {
      try
      {
        paramSSLSocketFactory = readFieldOrNull(paramSSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
      }
      catch (ClassNotFoundException paramSSLSocketFactory)
      {
        return null;
      }
      paramSSLSocketFactory = (X509TrustManager)readFieldOrNull(paramSSLSocketFactory, X509TrustManager.class, "trustManager");
      return paramSSLSocketFactory;
    } while (paramSSLSocketFactory != null);
    return null;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\platform\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */