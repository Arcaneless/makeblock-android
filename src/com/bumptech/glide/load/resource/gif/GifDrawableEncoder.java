package com.bumptech.glide.load.resource.gif;

import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;

public class GifDrawableEncoder
  implements ResourceEncoder<GifDrawable>
{
  private static final String TAG = "GifEncoder";
  
  public boolean encode(Resource<GifDrawable> paramResource, File paramFile, Options paramOptions)
  {
    paramResource = (GifDrawable)paramResource.get();
    boolean bool = false;
    try
    {
      ByteBufferUtil.toFile(paramResource.getBuffer(), paramFile);
      bool = true;
    }
    catch (IOException paramResource)
    {
      while (!Log.isLoggable("GifEncoder", 5)) {}
      Log.w("GifEncoder", "Failed to encode GIF drawable data", paramResource);
    }
    return bool;
    return false;
  }
  
  public EncodeStrategy getEncodeStrategy(Options paramOptions)
  {
    return EncodeStrategy.SOURCE;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifDrawableEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */