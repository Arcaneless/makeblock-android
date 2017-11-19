package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDescriptorLocalUriFetcher
  extends LocalUriFetcher<ParcelFileDescriptor>
{
  public FileDescriptorLocalUriFetcher(ContentResolver paramContentResolver, Uri paramUri)
  {
    super(paramContentResolver, paramUri);
  }
  
  protected void close(ParcelFileDescriptor paramParcelFileDescriptor)
    throws IOException
  {
    paramParcelFileDescriptor.close();
  }
  
  public Class<ParcelFileDescriptor> getDataClass()
  {
    return ParcelFileDescriptor.class;
  }
  
  protected ParcelFileDescriptor loadResource(Uri paramUri, ContentResolver paramContentResolver)
    throws FileNotFoundException
  {
    paramContentResolver = paramContentResolver.openAssetFileDescriptor(paramUri, "r");
    if (paramContentResolver == null) {
      throw new FileNotFoundException("FileDescriptor is null for: " + paramUri);
    }
    return paramContentResolver.getParcelFileDescriptor();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\FileDescriptorLocalUriFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */