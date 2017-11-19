package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecodePath<DataType, ResourceType, Transcode>
{
  private static final String TAG = "DecodePath";
  private final Class<DataType> dataClass;
  private final List<? extends ResourceDecoder<DataType, ResourceType>> decoders;
  private final String failureMessage;
  private final Pools.Pool<List<Exception>> listPool;
  private final ResourceTranscoder<ResourceType, Transcode> transcoder;
  
  public DecodePath(Class<DataType> paramClass, Class<ResourceType> paramClass1, Class<Transcode> paramClass2, List<? extends ResourceDecoder<DataType, ResourceType>> paramList, ResourceTranscoder<ResourceType, Transcode> paramResourceTranscoder, Pools.Pool<List<Exception>> paramPool)
  {
    this.dataClass = paramClass;
    this.decoders = paramList;
    this.transcoder = paramResourceTranscoder;
    this.listPool = paramPool;
    this.failureMessage = ("Failed DecodePath{" + paramClass.getSimpleName() + "->" + paramClass1.getSimpleName() + "->" + paramClass2.getSimpleName() + "}");
  }
  
  private Resource<ResourceType> decodeResource(DataRewinder<DataType> paramDataRewinder, int paramInt1, int paramInt2, Options paramOptions)
    throws GlideException
  {
    List localList = (List)this.listPool.acquire();
    try
    {
      paramDataRewinder = decodeResourceWithList(paramDataRewinder, paramInt1, paramInt2, paramOptions, localList);
      return paramDataRewinder;
    }
    finally
    {
      this.listPool.release(localList);
    }
  }
  
  private Resource<ResourceType> decodeResourceWithList(DataRewinder<DataType> paramDataRewinder, int paramInt1, int paramInt2, Options paramOptions, List<Exception> paramList)
    throws GlideException
  {
    Object localObject1 = null;
    int i = 0;
    int j = this.decoders.size();
    Object localObject3;
    for (;;)
    {
      Object localObject2 = localObject1;
      ResourceDecoder localResourceDecoder;
      if (i < j)
      {
        localResourceDecoder = (ResourceDecoder)this.decoders.get(i);
        localObject2 = localObject1;
      }
      try
      {
        if (localResourceDecoder.handles(paramDataRewinder.rewindAndGet(), paramOptions)) {
          localObject2 = localResourceDecoder.decode(paramDataRewinder.rewindAndGet(), paramInt1, paramInt2, paramOptions);
        }
        if (localObject2 != null)
        {
          if (localObject2 != null) {
            break;
          }
          throw new GlideException(this.failureMessage, new ArrayList(paramList));
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (Log.isLoggable("DecodePath", 2)) {
            Log.v("DecodePath", "Failed to decode data for " + localResourceDecoder, localIOException);
          }
          paramList.add(localIOException);
          localObject3 = localObject1;
        }
        i += 1;
        localObject1 = localObject3;
      }
    }
    return (Resource<ResourceType>)localObject3;
  }
  
  public Resource<Transcode> decode(DataRewinder<DataType> paramDataRewinder, int paramInt1, int paramInt2, Options paramOptions, DecodeCallback<ResourceType> paramDecodeCallback)
    throws GlideException
  {
    paramDataRewinder = paramDecodeCallback.onResourceDecoded(decodeResource(paramDataRewinder, paramInt1, paramInt2, paramOptions));
    return this.transcoder.transcode(paramDataRewinder);
  }
  
  public String toString()
  {
    return "DecodePath{ dataClass=" + this.dataClass + ", decoders=" + this.decoders + ", transcoder=" + this.transcoder + '}';
  }
  
  static abstract interface DecodeCallback<ResourceType>
  {
    public abstract Resource<ResourceType> onResourceDecoded(Resource<ResourceType> paramResource);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\DecodePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */