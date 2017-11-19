package cc.makeblock.makeblock.engine;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;

public class GlideRequests
  extends RequestManager
{
  public GlideRequests(Glide paramGlide, Lifecycle paramLifecycle, RequestManagerTreeNode paramRequestManagerTreeNode)
  {
    super(paramGlide, paramLifecycle, paramRequestManagerTreeNode);
  }
  
  public <ResourceType> GlideRequest<ResourceType> as(Class<ResourceType> paramClass)
  {
    return new GlideRequest(this.glide, this, paramClass);
  }
  
  public GlideRequest<Bitmap> asBitmap()
  {
    return (GlideRequest)super.asBitmap();
  }
  
  public GlideRequest<Drawable> asDrawable()
  {
    return (GlideRequest)super.asDrawable();
  }
  
  public GlideRequest<File> asFile()
  {
    return (GlideRequest)super.asFile();
  }
  
  public GlideRequest<GifDrawable> asGif()
  {
    return (GlideRequest)super.asGif();
  }
  
  public GlideRequest<File> download(Object paramObject)
  {
    return (GlideRequest)super.download(paramObject);
  }
  
  public GlideRequest<File> downloadOnly()
  {
    return (GlideRequest)super.downloadOnly();
  }
  
  public GlideRequest<Drawable> load(Object paramObject)
  {
    return (GlideRequest)super.load(paramObject);
  }
  
  protected void setRequestOptions(@NonNull RequestOptions paramRequestOptions)
  {
    if ((paramRequestOptions instanceof GlideOptions))
    {
      super.setRequestOptions(paramRequestOptions);
      return;
    }
    super.setRequestOptions(new GlideOptions().apply(paramRequestOptions));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\GlideRequests.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */