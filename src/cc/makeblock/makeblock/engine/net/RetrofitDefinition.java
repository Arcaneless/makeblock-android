package cc.makeblock.makeblock.engine.net;

import cc.makeblock.makeblock.engine.net.data.AdInfoData;
import cc.makeblock.makeblock.engine.net.data.AndroidVerInfoData;
import cc.makeblock.makeblock.engine.net.data.FirmwareVersionData;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public class RetrofitDefinition
{
  public static abstract interface AdInfoService
  {
    @GET("/advertisementInfo.json")
    public abstract Call<AdInfoData> getResult();
  }
  
  public static abstract interface FirmwareVersionService
  {
    @GET("/firmwareVerInfo.json")
    public abstract Call<FirmwareVersionData> getResult();
  }
  
  public static abstract interface ProjectListItemAdInfoService
  {
    @GET("/tutorial2.json")
    public abstract Call<TutorialInfo> getResult();
  }
  
  public static abstract interface VersionInfoService
  {
    @GET("/androidVerInfo.json")
    public abstract Call<AndroidVerInfoData> getResult();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\RetrofitDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */