package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.cache.DiskCache.Writer;
import java.io.File;

class DataCacheWriter<DataType>
  implements DiskCache.Writer
{
  private final DataType data;
  private final Encoder<DataType> encoder;
  private final Options options;
  
  DataCacheWriter(Encoder<DataType> paramEncoder, DataType paramDataType, Options paramOptions)
  {
    this.encoder = paramEncoder;
    this.data = paramDataType;
    this.options = paramOptions;
  }
  
  public boolean write(File paramFile)
  {
    return this.encoder.encode(this.data, paramFile, this.options);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\DataCacheWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */