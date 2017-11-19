package cc.makeblock.makeblock.engine;

import android.content.Context;
import android.os.Environment;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import java.io.File;

public class GlideModule
  extends AppGlideModule
{
  public void applyOptions(Context paramContext, GlideBuilder paramGlideBuilder)
  {
    paramGlideBuilder.setMemoryCache(new LruResourceCache((int)(Runtime.getRuntime().maxMemory() / 8L)));
    paramGlideBuilder.setDiskCache(new ExternalCacheDiskCacheFactory(paramContext, "makeblock/imgCache", 104857600));
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      paramGlideBuilder.setDiskCache(new DiskLruCacheFactory(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "makeblock/imgCache", 104857600));
      return;
    }
    paramGlideBuilder.setDiskCache(new ExternalCacheDiskCacheFactory(paramContext, "makeblock/imgCache", 104857600));
  }
  
  public boolean isManifestParsingEnabled()
  {
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\GlideModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */