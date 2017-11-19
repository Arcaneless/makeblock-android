package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class NeuronAllotIdInstruction
  extends NeuronInstruction
{
  private static final byte BLOCK_NO_BROADCAST = -1;
  private static final byte DATA = 0;
  private static final byte TYPE_NO = 16;
  
  protected int getLength()
  {
    return 3;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put((byte)-1);
    paramByteBuffer.put((byte)16);
    paramByteBuffer.put((byte)0);
  }
  
  public String toString()
  {
    return "airblock心跳包,其实是神经元分配ID的命令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\NeuronAllotIdInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */