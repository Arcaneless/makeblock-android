package okhttp3;

import java.io.IOException;

public abstract interface Authenticator
{
  public static final Authenticator NONE = new Authenticator()
  {
    public Request authenticate(Route paramAnonymousRoute, Response paramAnonymousResponse)
    {
      return null;
    }
  };
  
  public abstract Request authenticate(Route paramRoute, Response paramResponse)
    throws IOException;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\okhttp3\Authenticator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */