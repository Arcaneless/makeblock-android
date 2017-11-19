package okhttp3.internal.tls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public abstract class TrustRootIndex
{
  public static TrustRootIndex get(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = paramX509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[] { X509Certificate.class });
      ((Method)localObject).setAccessible(true);
      localObject = new AndroidTrustRootIndex(paramX509TrustManager, (Method)localObject);
      return (TrustRootIndex)localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return get(paramX509TrustManager.getAcceptedIssuers());
  }
  
  public static TrustRootIndex get(X509Certificate... paramVarArgs)
  {
    return new BasicTrustRootIndex(paramVarArgs);
  }
  
  public abstract X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate);
  
  static final class AndroidTrustRootIndex
    extends TrustRootIndex
  {
    private final Method findByIssuerAndSignatureMethod;
    private final X509TrustManager trustManager;
    
    AndroidTrustRootIndex(X509TrustManager paramX509TrustManager, Method paramMethod)
    {
      this.findByIssuerAndSignatureMethod = paramMethod;
      this.trustManager = paramX509TrustManager;
    }
    
    public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
    {
      Object localObject = null;
      for (;;)
      {
        TrustAnchor localTrustAnchor;
        try
        {
          localTrustAnchor = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[] { paramX509Certificate });
        }
        catch (IllegalAccessException paramX509Certificate)
        {
          throw new AssertionError();
        }
        catch (InvocationTargetException paramX509Certificate)
        {
          return null;
        }
        paramX509Certificate = localTrustAnchor.getTrustedCert();
        return paramX509Certificate;
        paramX509Certificate = (X509Certificate)localObject;
        if (localTrustAnchor == null) {}
      }
    }
  }
  
  static final class BasicTrustRootIndex
    extends TrustRootIndex
  {
    private final Map<X500Principal, List<X509Certificate>> subjectToCaCerts = new LinkedHashMap();
    
    public BasicTrustRootIndex(X509Certificate... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        X509Certificate localX509Certificate = paramVarArgs[i];
        X500Principal localX500Principal = localX509Certificate.getSubjectX500Principal();
        List localList = (List)this.subjectToCaCerts.get(localX500Principal);
        Object localObject = localList;
        if (localList == null)
        {
          localObject = new ArrayList(1);
          this.subjectToCaCerts.put(localX500Principal, localObject);
        }
        ((List)localObject).add(localX509Certificate);
        i += 1;
      }
    }
    
    public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
    {
      Object localObject = paramX509Certificate.getIssuerX500Principal();
      localObject = (List)this.subjectToCaCerts.get(localObject);
      if (localObject == null) {
        return null;
      }
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        X509Certificate localX509Certificate = (X509Certificate)((Iterator)localObject).next();
        PublicKey localPublicKey = localX509Certificate.getPublicKey();
        try
        {
          paramX509Certificate.verify(localPublicKey);
          return localX509Certificate;
        }
        catch (Exception localException) {}
      }
      return null;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\internal\tls\TrustRootIndex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */