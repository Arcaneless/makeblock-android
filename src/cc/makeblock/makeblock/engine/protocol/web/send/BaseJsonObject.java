package cc.makeblock.makeblock.engine.protocol.web.send;

import com.google.gson.Gson;

public class BaseJsonObject
{
  private static Gson gson = new Gson();
  
  public String toString()
  {
    return gson.toJson(this);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\web\send\BaseJsonObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */