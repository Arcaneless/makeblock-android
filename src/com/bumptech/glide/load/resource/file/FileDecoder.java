package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;

public class FileDecoder
  implements ResourceDecoder<File, File>
{
  public Resource<File> decode(File paramFile, int paramInt1, int paramInt2, Options paramOptions)
  {
    return new FileResource(paramFile);
  }
  
  public boolean handles(File paramFile, Options paramOptions)
  {
    return true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\file\FileDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */