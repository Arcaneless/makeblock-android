package cc.makeblock.makeblock.engine.protocol.neuron;

import android.util.Log;
import cc.makeblock.makeblock.engine.protocol.base.RespondParser;
import ml.xuexin.bleconsultant.tool.BleLog;

public class NeuronRespondParser
  extends RespondParser
{
  private static final byte[] HEAD = { -16 };
  private static final int POSITION_CMD = 3;
  private static final byte[] TAIL = { -9 };
  private OnRespondReceiveListener onRespondReceiveListener;
  
  public NeuronRespondParser()
  {
    super(HEAD, TAIL);
  }
  
  private boolean check(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 3)) {}
    int j;
    do
    {
      return false;
      j = 0;
      int i = 1;
      while (i < paramArrayOfByte.length - 2)
      {
        j = (byte)(paramArrayOfByte[i] + j);
        i += 1;
      }
    } while ((byte)(j & 0x7F) != paramArrayOfByte[(paramArrayOfByte.length - 2)]);
    return true;
  }
  
  protected void packData(byte[] paramArrayOfByte)
  {
    Log.e("BYTES:", BleLog.parseByte(paramArrayOfByte));
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (check(paramArrayOfByte)) {
      switch (paramArrayOfByte[3])
      {
      default: 
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      if ((this.onRespondReceiveListener != null) && (localObject1 != null)) {
        this.onRespondReceiveListener.onRespondReceive((NeuronRespond)localObject1);
      }
      return;
      int i = NeuronByteUtil.convert7to8(paramArrayOfByte, 4, 5);
      int k = 4 + 1;
      int j = NeuronByteUtil.convert7to8(paramArrayOfByte, k, 6);
      k = NeuronByteUtil.convert7to8(paramArrayOfByte, k + 1, 7);
      localObject1 = new AirBlockStateRespond(i, j, k);
      continue;
      float f = Float.intBitsToFloat(NeuronByteUtil.convert7to8(paramArrayOfByte, 4, 9));
      i = 4 + 5;
      localObject1 = new AirBlockOffsetAngleRespond(f, Float.intBitsToFloat(NeuronByteUtil.convert7to8(paramArrayOfByte, i, 14)), Float.intBitsToFloat(NeuronByteUtil.convert7to8(paramArrayOfByte, i + 5, 19)));
    }
  }
  
  public void setOnRespondReceiveListener(OnRespondReceiveListener paramOnRespondReceiveListener)
  {
    this.onRespondReceiveListener = paramOnRespondReceiveListener;
  }
  
  public static abstract interface OnRespondReceiveListener
  {
    public abstract void onRespondReceive(NeuronRespond paramNeuronRespond);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\NeuronRespondParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */