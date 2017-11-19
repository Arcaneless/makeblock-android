package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockStateInstruction
  extends AirBlockInstruction
{
  private static final byte cmd = 67;
  public static final byte off = 0;
  public static final byte on = 1;
  private final byte state;
  
  public AirBlockStateInstruction(byte paramByte)
  {
    this.state = paramByte;
  }
  
  protected int getLength()
  {
    return 2;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 67));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, this.state));
  }
  
  public String toString()
  {
    return super.toString() + "AirBlock 状态包开关:" + this.state;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockStateInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */