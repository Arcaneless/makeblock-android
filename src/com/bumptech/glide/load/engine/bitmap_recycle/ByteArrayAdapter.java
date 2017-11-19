package com.bumptech.glide.load.engine.bitmap_recycle;

public final class ByteArrayAdapter
  implements ArrayAdapterInterface<byte[]>
{
  private static final String TAG = "ByteArrayPool";
  
  public int getArrayLength(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte.length;
  }
  
  public int getElementSizeInBytes()
  {
    return 1;
  }
  
  public String getTag()
  {
    return "ByteArrayPool";
  }
  
  public byte[] newArray(int paramInt)
  {
    return new byte[paramInt];
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\ByteArrayAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */