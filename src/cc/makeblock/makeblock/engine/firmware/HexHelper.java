package cc.makeblock.makeblock.engine.firmware;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import cc.makeblock.makeblock.base.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HexHelper
{
  private static final int PageSize = 16;
  private List<HexBean> hexBeanList = new ArrayList();
  private int index;
  
  public HexHelper(String paramString)
  {
    getFirmwareFromAssetsFile(App.getContext(), paramString, this.hexBeanList);
    reset();
  }
  
  protected HexBean createHexBean(String paramString, byte[] paramArrayOfByte)
  {
    return new HexBean(paramString, paramArrayOfByte);
  }
  
  protected void getFirmwareFromAssetsFile(Context paramContext, String paramString, List<HexBean> paramList)
  {
    paramContext = paramContext.getResources().getAssets();
    byte[] arrayOfByte = new byte[1000000];
    try
    {
      localInputStream = paramContext.open(paramString);
      localInputStreamReader = new InputStreamReader(localInputStream);
      localBufferedReader = new BufferedReader(localInputStreamReader);
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        InputStream localInputStream;
        InputStreamReader localInputStreamReader;
        BufferedReader localBufferedReader;
        paramContext.printStackTrace();
        continue;
        paramContext = null;
      }
    }
    paramString = localBufferedReader.readLine();
    break label124;
    paramString = createHexBean(paramString, paramContext);
    paramContext = paramString.getBaseAddress();
    paramList.add(paramString);
    paramString = localBufferedReader.readLine();
    label124:
    label132:
    for (;;)
    {
      localBufferedReader.close();
      localInputStreamReader.close();
      localInputStream.close();
      System.arraycopy(arrayOfByte, 0, new byte[0], 0, 0);
      return;
      for (;;)
      {
        if (paramString == null) {
          break label132;
        }
        break;
      }
    }
  }
  
  public byte[] getNextAddressCommand()
  {
    int i = this.index;
    while (i < this.hexBeanList.size()) {
      if (((HexBean)this.hexBeanList.get(i)).getType() == HexBean.Type.BaseAddress) {
        i += 1;
      } else {
        return ((HexBean)this.hexBeanList.get(i)).getAddress();
      }
    }
    return null;
  }
  
  public byte[] getNextCodeCommand()
  {
    ArrayList localArrayList = new ArrayList(256);
    for (int j = 0; (this.index < this.hexBeanList.size()) && (j < 16); j = i)
    {
      localObject = (HexBean)this.hexBeanList.get(this.index);
      i = j;
      if (((HexBean)localObject).getType() == HexBean.Type.Data)
      {
        int k = j + 1;
        localObject = ((HexBean)localObject).getData();
        int m = localObject.length;
        j = 0;
        for (;;)
        {
          i = k;
          if (j >= m) {
            break;
          }
          localArrayList.add(Byte.valueOf(localObject[j]));
          j += 1;
        }
      }
      this.index += 1;
    }
    Object localObject = new byte[localArrayList.size()];
    int i = 0;
    while (i < localObject.length)
    {
      localObject[i] = ((Byte)localArrayList.get(i)).byteValue();
      i += 1;
    }
    return (byte[])localObject;
  }
  
  public float getPercent()
  {
    return this.index / this.hexBeanList.size();
  }
  
  public boolean isEnd()
  {
    return this.index >= this.hexBeanList.size();
  }
  
  public void reset()
  {
    this.index = 0;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\HexHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */