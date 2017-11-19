package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockLaunchInstruction
  extends AirBlockInstruction
{
  private static final byte cmd = 1;
  
  protected int getLength()
  {
    return 1;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 1));
  }
  
  public String toString()
  {
    return "AirBlock启动命令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockLaunchInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */