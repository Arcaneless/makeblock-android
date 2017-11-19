package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockStopInstruction
  extends AirBlockInstruction
{
  private static final byte cmd = 2;
  
  protected int getLength()
  {
    return 1;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 2));
  }
  
  public String toString()
  {
    return "AirBlock停止命令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockStopInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */