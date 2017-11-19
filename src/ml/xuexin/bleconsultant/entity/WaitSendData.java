package ml.xuexin.bleconsultant.entity;

public class WaitSendData
{
  public final String characteristicUuid;
  public final byte[] data;
  public final String serviceUuid;
  
  public WaitSendData(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    this.data = paramArrayOfByte;
    this.serviceUuid = paramString1;
    this.characteristicUuid = paramString2;
  }
  
  public boolean isSameCharacteristic(WaitSendData paramWaitSendData)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramWaitSendData == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramWaitSendData == this);
      if (!paramWaitSendData.serviceUuid.equals(this.serviceUuid)) {
        break;
      }
      bool1 = bool2;
    } while (paramWaitSendData.characteristicUuid.equals(this.characteristicUuid));
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\entity\WaitSendData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */