package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.ByteBufferUtil;

public class GifDrawableBytesTranscoder
  implements ResourceTranscoder<GifDrawable, byte[]>
{
  public Resource<byte[]> transcode(Resource<GifDrawable> paramResource)
  {
    return new BytesResource(ByteBufferUtil.toBytes(((GifDrawable)paramResource.get()).getBuffer()));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\GifDrawableBytesTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */