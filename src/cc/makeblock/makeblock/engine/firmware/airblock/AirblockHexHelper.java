package cc.makeblock.makeblock.engine.firmware.airblock;

import cc.makeblock.makeblock.engine.firmware.HexBean;
import cc.makeblock.makeblock.engine.firmware.HexHelper;

public class AirblockHexHelper
  extends HexHelper
{
  public AirblockHexHelper()
  {
    super("firmware/airblock.hex");
  }
  
  protected HexBean createHexBean(String paramString, byte[] paramArrayOfByte)
  {
    return new HexBean(paramString, paramArrayOfByte);
  }
  
  public byte[] getNextAddressCommand()
  {
    int j = 0;
    byte[] arrayOfByte1 = super.getNextAddressCommand();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + 1];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    int i = 0;
    int k = arrayOfByte1.length;
    while (j < k)
    {
      i = (byte)(i ^ arrayOfByte1[j]);
      j += 1;
    }
    arrayOfByte2[arrayOfByte1.length] = i;
    return arrayOfByte2;
  }
  
  public byte[] getNextCodeCommand()
  {
    byte[] arrayOfByte1 = super.getNextCodeCommand();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + 2];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1, arrayOfByte1.length);
    arrayOfByte2[0] = ((byte)(arrayOfByte1.length - 1));
    int i = 0;
    int j = 0;
    while (j < arrayOfByte2.length - 1)
    {
      i = (byte)(arrayOfByte2[j] ^ i);
      j += 1;
    }
    arrayOfByte2[(arrayOfByte2.length - 1)] = i;
    return arrayOfByte2;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\AirblockHexHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */