package com.bumptech.glide.load.engine.bitmap_recycle;

public final class IntegerArrayAdapter
  implements ArrayAdapterInterface<int[]>
{
  private static final String TAG = "IntegerArrayPool";
  
  public int getArrayLength(int[] paramArrayOfInt)
  {
    return paramArrayOfInt.length;
  }
  
  public int getElementSizeInBytes()
  {
    return 4;
  }
  
  public String getTag()
  {
    return "IntegerArrayPool";
  }
  
  public int[] newArray(int paramInt)
  {
    return new int[paramInt];
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\IntegerArrayAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */