package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.Util;

public final class Address
{
  final CertificatePinner certificatePinner;
  final List<ConnectionSpec> connectionSpecs;
  final Dns dns;
  final HostnameVerifier hostnameVerifier;
  final List<Protocol> protocols;
  final Proxy proxy;
  final Authenticator proxyAuthenticator;
  final ProxySelector proxySelector;
  final SocketFactory socketFactory;
  final SSLSocketFactory sslSocketFactory;
  final HttpUrl url;
  
  public Address(String paramString, int paramInt, Dns paramDns, SocketFactory paramSocketFactory, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier, CertificatePinner paramCertificatePinner, Authenticator paramAuthenticator, Proxy paramProxy, List<Protocol> paramList, List<ConnectionSpec> paramList1, ProxySelector paramProxySelector)
  {
    HttpUrl.Builder localBuilder = new HttpUrl.Builder();
    if (paramSSLSocketFactory != null) {}
    for (String str = "https";; str = "http")
    {
      this.url = localBuilder.scheme(str).host(paramString).port(paramInt).build();
      if (paramDns != null) {
        break;
      }
      throw new NullPointerException("dns == null");
    }
    this.dns = paramDns;
    if (paramSocketFactory == null) {
      throw new NullPointerException("socketFactory == null");
    }
    this.socketFactory = paramSocketFactory;
    if (paramAuthenticator == null) {
      throw new NullPointerException("proxyAuthenticator == null");
    }
    this.proxyAuthenticator = paramAuthenticator;
    if (paramList == null) {
      throw new NullPointerException("protocols == null");
    }
    this.protocols = Util.immutableList(paramList);
    if (paramList1 == null) {
      throw new NullPointerException("connectionSpecs == null");
    }
    this.connectionSpecs = Util.immutableList(paramList1);
    if (paramProxySelector == null) {
      throw new NullPointerException("proxySelector == null");
    }
    this.proxySelector = paramProxySelector;
    this.proxy = paramProxy;
    this.sslSocketFactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramHostnameVerifier;
    this.certificatePinner = paramCertificatePinner;
  }
  
  public CertificatePinner certificatePinner()
  {
    return this.certificatePinner;
  }
  
  public List<ConnectionSpec> connectionSpecs()
  {
    return this.connectionSpecs;
  }
  
  public Dns dns()
  {
    return this.dns;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Address))
    {
      paramObject = (Address)paramObject;
      bool1 = bool2;
      if (this.url.equals(((Address)paramObject).url))
      {
        bool1 = bool2;
        if (this.dns.equals(((Address)paramObject).dns))
        {
          bool1 = bool2;
          if (this.proxyAuthenticator.equals(((Address)paramObject).proxyAuthenticator))
          {
            bool1 = bool2;
            if (this.protocols.equals(((Address)paramObject).protocols))
            {
              bool1 = bool2;
              if (this.connectionSpecs.equals(((Address)paramObject).connectionSpecs))
              {
                bool1 = bool2;
                if (this.proxySelector.equals(((Address)paramObject).proxySelector))
                {
                  bool1 = bool2;
                  if (Util.equal(this.proxy, ((Address)paramObject).proxy))
                  {
                    bool1 = bool2;
                    if (Util.equal(this.sslSocketFactory, ((Address)paramObject).sslSocketFactory))
                    {
                      bool1 = bool2;
                      if (Util.equal(this.hostnameVerifier, ((Address)paramObject).hostnameVerifier))
                      {
                        bool1 = bool2;
                        if (Util.equal(this.certificatePinner, ((Address)paramObject).certificatePinner)) {
                          bool1 = true;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public int hashCode()
  {
    int m = 0;
    int n = this.url.hashCode();
    int i1 = this.dns.hashCode();
    int i2 = this.proxyAuthenticator.hashCode();
    int i3 = this.protocols.hashCode();
    int i4 = this.connectionSpecs.hashCode();
    int i5 = this.proxySelector.hashCode();
    int i;
    int j;
    if (this.proxy != null)
    {
      i = this.proxy.hashCode();
      if (this.sslSocketFactory == null) {
        break label185;
      }
      j = this.sslSocketFactory.hashCode();
      label91:
      if (this.hostnameVerifier == null) {
        break label190;
      }
    }
    label185:
    label190:
    for (int k = this.hostnameVerifier.hashCode();; k = 0)
    {
      if (this.certificatePinner != null) {
        m = this.certificatePinner.hashCode();
      }
      return (((((((((n + 527) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i) * 31 + j) * 31 + k) * 31 + m;
      i = 0;
      break;
      j = 0;
      break label91;
    }
  }
  
  public HostnameVerifier hostnameVerifier()
  {
    return this.hostnameVerifier;
  }
  
  public List<Protocol> protocols()
  {
    return this.protocols;
  }
  
  public Proxy proxy()
  {
    return this.proxy;
  }
  
  public Authenticator proxyAuthenticator()
  {
    return this.proxyAuthenticator;
  }
  
  public ProxySelector proxySelector()
  {
    return this.proxySelector;
  }
  
  public SocketFactory socketFactory()
  {
    return this.socketFactory;
  }
  
  public SSLSocketFactory sslSocketFactory()
  {
    return this.sslSocketFactory;
  }
  
  public HttpUrl url()
  {
    return this.url;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */