package okhttp3;

import okhttp3.internal.Util;

public final class Challenge
{
  private final String realm;
  private final String scheme;
  
  public Challenge(String paramString1, String paramString2)
  {
    this.scheme = paramString1;
    this.realm = paramString2;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Challenge)) && (Util.equal(this.scheme, ((Challenge)paramObject).scheme)) && (Util.equal(this.realm, ((Challenge)paramObject).realm));
  }
  
  public int hashCode()
  {
    int j = 0;
    if (this.realm != null) {}
    for (int i = this.realm.hashCode();; i = 0)
    {
      if (this.scheme != null) {
        j = this.scheme.hashCode();
      }
      return (i + 899) * 31 + j;
    }
  }
  
  public String realm()
  {
    return this.realm;
  }
  
  public String scheme()
  {
    return this.scheme;
  }
  
  public String toString()
  {
    return this.scheme + " realm=\"" + this.realm + "\"";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\Challenge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */