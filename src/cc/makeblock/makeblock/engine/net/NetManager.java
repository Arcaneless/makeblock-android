package cc.makeblock.makeblock.engine.net;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.GlideApp;
import cc.makeblock.makeblock.engine.GlideRequest;
import cc.makeblock.makeblock.engine.GlideRequests;
import cc.makeblock.makeblock.engine.net.tutorial.ProjectItemDataTypeAdapter;
import cc.makeblock.makeblock.engine.net.tutorial.TutorialInfo;
import cc.makeblock.makeblock.engine.utils.FileUtils;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager
{
  protected static final String AD_INFO_API = "/advertisementInfo.json";
  protected static final String BASE_URL = "http://appinventor.makeblock.com";
  public static final String FIRMWARE_UPDATE_INFO = "http://learn.makeblock.com/getting-started-programming-with-mblock/";
  protected static final String FIRMWARE_VERSION_API = "/firmwareVerInfo.json";
  public static final String GROUP_URL_TEST = "http://192.168.11.212:4567/";
  public static final String PROJECT_LIST_ITEM_AD_API = "/tutorial2.json";
  public static final String PROJECT_LIST_ITEM_AD_BASE_URL = "https://github.com";
  protected static final String VERSION_INFO_API = "/androidVerInfo.json";
  private static NetManager instance;
  private Retrofit mRetrofit;
  
  private NetManager()
  {
    Gson localGson = new GsonBuilder().registerTypeAdapter(TutorialInfo.class, new ProjectItemDataTypeAdapter()).create();
    this.mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(localGson)).addConverterFactory(GsonConverterFactory.create()).baseUrl("http://appinventor.makeblock.com").build();
  }
  
  private <T> void doNetWork(retrofit2.Call<T> paramCall, final NetResponseListener<T> paramNetResponseListener)
  {
    paramCall.enqueue(new retrofit2.Callback()
    {
      public void onFailure(retrofit2.Call<T> paramAnonymousCall, Throwable paramAnonymousThrowable)
      {
        paramNetResponseListener.onResponse(null);
      }
      
      public void onResponse(retrofit2.Call<T> paramAnonymousCall, retrofit2.Response<T> paramAnonymousResponse)
      {
        if (paramAnonymousResponse.isSuccessful())
        {
          paramAnonymousCall = paramAnonymousResponse.body();
          if (paramAnonymousCall != null)
          {
            paramNetResponseListener.onResponse(paramAnonymousCall);
            return;
          }
        }
        paramNetResponseListener.onResponse(null);
      }
    });
  }
  
  public static NetManager getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new NetManager();
      }
      return instance;
    }
    finally {}
  }
  
  public void downloadFile(String paramString1, final String paramString2, final String paramString3, final NetResponseListener paramNetResponseListener)
  {
    new OkHttpClient().newCall(new Request.Builder().url(paramString1).build()).enqueue(new okhttp3.Callback()
    {
      public void onFailure(okhttp3.Call paramAnonymousCall, IOException paramAnonymousIOException) {}
      
      public void onResponse(okhttp3.Call paramAnonymousCall, okhttp3.Response paramAnonymousResponse)
        throws IOException
      {
        if (!paramAnonymousResponse.isSuccessful()) {
          return;
        }
        FileUtils.saveFileFromInputStream(paramAnonymousResponse.body().byteStream(), paramString2, paramString3);
        paramNetResponseListener.onResponse(null);
      }
    });
  }
  
  public void downloadImageByFresco(String paramString1, final String paramString2, final String paramString3, final NetResponseListener paramNetResponseListener)
  {
    GlideApp.with(App.getContext()).asBitmap().override(ScreenHelper.SCREEN_WIDTH, ScreenHelper.SCREEN_HEIGHT).load(paramString1).into(new SimpleTarget()
    {
      public void onResourceReady(Bitmap paramAnonymousBitmap, Transition<? super Bitmap> paramAnonymousTransition)
      {
        if (paramAnonymousBitmap != null) {
          try
          {
            if (paramAnonymousBitmap.isRecycled()) {
              return;
            }
            paramAnonymousTransition = new File(paramString2);
            if (!paramAnonymousTransition.exists()) {
              paramAnonymousTransition.mkdirs();
            }
            paramAnonymousTransition = new File(paramAnonymousTransition, paramString3);
            if (!paramAnonymousTransition.exists()) {
              paramAnonymousTransition.createNewFile();
            }
            paramAnonymousTransition = new FileOutputStream(paramAnonymousTransition);
            paramAnonymousBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramAnonymousTransition);
            paramNetResponseListener.onResponse(null);
            return;
          }
          catch (Exception paramAnonymousTransition)
          {
            paramAnonymousTransition.printStackTrace();
            paramAnonymousBitmap.recycle();
          }
        }
      }
    });
  }
  
  public void getAdInfo(NetResponseListener paramNetResponseListener)
  {
    doNetWork(((RetrofitDefinition.AdInfoService)this.mRetrofit.create(RetrofitDefinition.AdInfoService.class)).getResult(), paramNetResponseListener);
  }
  
  public void getFirmwareVersion(NetResponseListener paramNetResponseListener)
  {
    doNetWork(((RetrofitDefinition.FirmwareVersionService)this.mRetrofit.create(RetrofitDefinition.FirmwareVersionService.class)).getResult(), paramNetResponseListener);
  }
  
  public void getProjectItemAdInfo(NetResponseListener<TutorialInfo> paramNetResponseListener)
  {
    doNetWork(((RetrofitDefinition.ProjectListItemAdInfoService)this.mRetrofit.create(RetrofitDefinition.ProjectListItemAdInfoService.class)).getResult(), paramNetResponseListener);
  }
  
  public void getVersionInfo(NetResponseListener paramNetResponseListener)
  {
    doNetWork(((RetrofitDefinition.VersionInfoService)this.mRetrofit.create(RetrofitDefinition.VersionInfoService.class)).getResult(), paramNetResponseListener);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\NetManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */