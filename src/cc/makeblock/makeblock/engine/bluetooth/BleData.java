package cc.makeblock.makeblock.engine.bluetooth;

import ml.xuexin.bleconsultant.tool.BleLog;

public class BleData
{
  public final byte[] data;
  public final String uuidCharacteristic;
  public final String uuidService;
  
  public BleData(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    this.uuidService = paramString1;
    this.uuidCharacteristic = paramString2;
    this.data = paramArrayOfByte;
  }
  
  public String toString()
  {
    return "<" + this.uuidService + "," + this.uuidCharacteristic + "> " + BleLog.parseByte(this.data);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\bluetooth\BleData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */