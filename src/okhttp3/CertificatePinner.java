package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner
{
  public static final CertificatePinner DEFAULT = new Builder().build();
  private final CertificateChainCleaner certificateChainCleaner;
  private final List<Pin> pins;
  
  private CertificatePinner(List<Pin> paramList, CertificateChainCleaner paramCertificateChainCleaner)
  {
    this.pins = paramList;
    this.certificateChainCleaner = paramCertificateChainCleaner;
  }
  
  public static String pin(Certificate paramCertificate)
  {
    if (!(paramCertificate instanceof X509Certificate)) {
      throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }
    return "sha256/" + sha256((X509Certificate)paramCertificate).base64();
  }
  
  static ByteString sha1(X509Certificate paramX509Certificate)
  {
    return Util.sha1(ByteString.of(paramX509Certificate.getPublicKey().getEncoded()));
  }
  
  static ByteString sha256(X509Certificate paramX509Certificate)
  {
    return Util.sha256(ByteString.of(paramX509Certificate.getPublicKey().getEncoded()));
  }
  
  public void check(String paramString, List<Certificate> paramList)
    throws SSLPeerUnverifiedException
  {
    List localList = findMatchingPins(paramString);
    if (localList.isEmpty()) {
      return;
    }
    Object localObject3 = paramList;
    if (this.certificateChainCleaner != null) {
      localObject3 = this.certificateChainCleaner.clean(paramList, paramString);
    }
    int i = 0;
    int k = ((List)localObject3).size();
    Object localObject1;
    for (;;)
    {
      if (i >= k) {
        break label221;
      }
      X509Certificate localX509Certificate = (X509Certificate)((List)localObject3).get(i);
      localObject1 = null;
      paramList = null;
      j = 0;
      int m = localList.size();
      if (j < m)
      {
        Pin localPin = (Pin)localList.get(j);
        Object localObject2;
        if (localPin.hashAlgorithm.equals("sha256/"))
        {
          localObject2 = paramList;
          if (paramList == null) {
            localObject2 = sha256(localX509Certificate);
          }
          if (localPin.hash.equals(localObject2)) {
            break;
          }
          paramList = (List<Certificate>)localObject2;
        }
        do
        {
          j += 1;
          break;
          if (!localPin.hashAlgorithm.equals("sha1/")) {
            break label206;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = sha1(localX509Certificate);
          }
          localObject1 = localObject2;
        } while (!localPin.hash.equals(localObject2));
        return;
        label206:
        throw new AssertionError();
      }
      i += 1;
    }
    label221:
    paramList = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
    i = 0;
    int j = ((List)localObject3).size();
    while (i < j)
    {
      localObject1 = (X509Certificate)((List)localObject3).get(i);
      paramList.append("\n    ").append(pin((Certificate)localObject1)).append(": ").append(((X509Certificate)localObject1).getSubjectDN().getName());
      i += 1;
    }
    paramList.append("\n  Pinned certificates for ").append(paramString).append(":");
    i = 0;
    j = localList.size();
    while (i < j)
    {
      paramString = (Pin)localList.get(i);
      paramList.append("\n    ").append(paramString);
      i += 1;
    }
    throw new SSLPeerUnverifiedException(paramList.toString());
  }
  
  public void check(String paramString, Certificate... paramVarArgs)
    throws SSLPeerUnverifiedException
  {
    check(paramString, Arrays.asList(paramVarArgs));
  }
  
  List<Pin> findMatchingPins(String paramString)
  {
    Object localObject1 = Collections.emptyList();
    Iterator localIterator = this.pins.iterator();
    while (localIterator.hasNext())
    {
      Pin localPin = (Pin)localIterator.next();
      if (localPin.matches(paramString))
      {
        Object localObject2 = localObject1;
        if (((List)localObject1).isEmpty()) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(localPin);
        localObject1 = localObject2;
      }
    }
    return (List<Pin>)localObject1;
  }
  
  CertificatePinner withCertificateChainCleaner(CertificateChainCleaner paramCertificateChainCleaner)
  {
    CertificatePinner localCertificatePinner = this;
    if (this.certificateChainCleaner != paramCertificateChainCleaner) {
      localCertificatePinner = new CertificatePinner(this.pins, paramCertificateChainCleaner);
    }
    return localCertificatePinner;
  }
  
  public static final class Builder
  {
    private final List<CertificatePinner.Pin> pins = new ArrayList();
    
    public Builder add(String paramString, String... paramVarArgs)
    {
      if (paramString == null) {
        throw new NullPointerException("pattern == null");
      }
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        String str = paramVarArgs[i];
        this.pins.add(new CertificatePinner.Pin(paramString, str));
        i += 1;
      }
      return this;
    }
    
    public CertificatePinner build()
    {
      return new CertificatePinner(Util.immutableList(this.pins), null, null);
    }
  }
  
  static final class Pin
  {
    private static final String WILDCARD = "*.";
    final String canonicalHostname;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;
    
    Pin(String paramString1, String paramString2)
    {
      this.pattern = paramString1;
      if (paramString1.startsWith("*."))
      {
        paramString1 = HttpUrl.parse("http://" + paramString1.substring("*.".length())).host();
        this.canonicalHostname = paramString1;
        if (!paramString2.startsWith("sha1/")) {
          break label151;
        }
        this.hashAlgorithm = "sha1/";
        this.hash = ByteString.decodeBase64(paramString2.substring("sha1/".length()));
      }
      for (;;)
      {
        if (this.hash == null)
        {
          throw new IllegalArgumentException("pins must be base64: " + paramString2);
          paramString1 = HttpUrl.parse("http://" + paramString1).host();
          break;
          label151:
          if (paramString2.startsWith("sha256/"))
          {
            this.hashAlgorithm = "sha256/";
            this.hash = ByteString.decodeBase64(paramString2.substring("sha256/".length()));
          }
          else
          {
            throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + paramString2);
          }
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Pin)) && (this.pattern.equals(((Pin)paramObject).pattern)) && (this.hashAlgorithm.equals(((Pin)paramObject).hashAlgorithm)) && (this.hash.equals(((Pin)paramObject).hash));
    }
    
    public int hashCode()
    {
      return ((this.pattern.hashCode() + 527) * 31 + this.hashAlgorithm.hashCode()) * 31 + this.hash.hashCode();
    }
    
    boolean matches(String paramString)
    {
      if (this.pattern.startsWith("*.")) {
        return paramString.regionMatches(false, paramString.indexOf('.') + 1, this.canonicalHostname, 0, this.canonicalHostname.length());
      }
      return paramString.equals(this.canonicalHostname);
    }
    
    public String toString()
    {
      return this.hashAlgorithm + this.hash.base64();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\CertificatePinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */