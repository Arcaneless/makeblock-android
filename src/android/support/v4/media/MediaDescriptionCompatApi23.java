package android.support.v4.media;

import android.media.MediaDescription;
import android.media.MediaDescription.Builder;
import android.net.Uri;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaDescriptionCompatApi23
  extends MediaDescriptionCompatApi21
{
  public static Uri getMediaUri(Object paramObject)
  {
    return ((MediaDescription)paramObject).getMediaUri();
  }
  
  static class Builder
    extends MediaDescriptionCompatApi21.Builder
  {
    public static void setMediaUri(Object paramObject, Uri paramUri)
    {
      ((MediaDescription.Builder)paramObject).setMediaUri(paramUri);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\support\v4\media\MediaDescriptionCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */