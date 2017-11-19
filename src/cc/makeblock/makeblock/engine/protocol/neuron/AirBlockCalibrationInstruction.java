package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockCalibrationInstruction
  extends AirBlockInstruction
{
  public static final byte TYPE_BOARD = 2;
  public static final byte TYPE_GYROSCOPE = 1;
  private static final byte cmd = 58;
  private byte type;
  
  public AirBlockCalibrationInstruction(byte paramByte)
  {
    this.type = paramByte;
  }
  
  protected int getLength()
  {
    return 2;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 58));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, this.type));
  }
  
  public String toString()
  {
    return "AirBlock调平指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockCalibrationInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */