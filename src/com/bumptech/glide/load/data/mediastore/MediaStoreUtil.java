package com.bumptech.glide.load.data.mediastore;

import android.net.Uri;
import java.util.List;

public final class MediaStoreUtil
{
  private static final int MINI_THUMB_HEIGHT = 384;
  private static final int MINI_THUMB_WIDTH = 512;
  
  public static boolean isMediaStoreImageUri(Uri paramUri)
  {
    return (isMediaStoreUri(paramUri)) && (!isVideoUri(paramUri));
  }
  
  public static boolean isMediaStoreUri(Uri paramUri)
  {
    return (paramUri != null) && ("content".equals(paramUri.getScheme())) && ("media".equals(paramUri.getAuthority()));
  }
  
  public static boolean isMediaStoreVideoUri(Uri paramUri)
  {
    return (isMediaStoreUri(paramUri)) && (isVideoUri(paramUri));
  }
  
  public static boolean isThumbnailSize(int paramInt1, int paramInt2)
  {
    return (paramInt1 <= 512) && (paramInt2 <= 384);
  }
  
  private static boolean isVideoUri(Uri paramUri)
  {
    return paramUri.getPathSegments().contains("video");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\mediastore\MediaStoreUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */