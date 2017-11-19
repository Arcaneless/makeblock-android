package cc.makeblock.makeblock.engine.bluetooth.programmer;

import android.util.Log;
import java.util.UUID;

public class OTAProgrammer
  extends Programmer
{
  public void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte)
  {
    Log.e("lyh", "收到数据");
  }
  
  protected void processProgramming() {}
  
  public void receiveData(byte[] paramArrayOfByte) {}
  
  public boolean startProgram()
  {
    return true;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\bluetooth\programmer\OTAProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */