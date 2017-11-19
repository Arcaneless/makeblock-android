package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockLandingInstruction
  extends AirBlockInstruction
{
  public static final byte Landing = 5;
  public static final byte Manual = 1;
  public static final byte TakeOff = 8;
  private static final byte cmd = 11;
  private final byte mode;
  
  public AirBlockLandingInstruction(byte paramByte)
  {
    this.mode = paramByte;
  }
  
  protected int getLength()
  {
    return 2;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 11));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, this.mode));
  }
  
  public String toString()
  {
    return "AirBlock启动命令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockLandingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */