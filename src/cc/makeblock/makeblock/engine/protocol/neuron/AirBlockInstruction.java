package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public abstract class AirBlockInstruction
  extends NeuronInstruction
{
  private static final byte NO = 1;
  private static final byte TYPE_AIRBLOCK = 95;
  
  protected final ByteBuffer getByteBuffer()
  {
    ByteBuffer localByteBuffer = super.getByteBuffer();
    localByteBuffer.put(NeuronByteUtil.convert8to7(1, 1));
    localByteBuffer.put(NeuronByteUtil.convert8to7(1, 95));
    return localByteBuffer;
  }
  
  protected int getTotalLength()
  {
    return super.getTotalLength() + 2;
  }
  
  protected final int parseToIntBit(float paramFloat)
  {
    return Float.floatToIntBits(paramFloat);
  }
  
  public String toString()
  {
    return "AirBlock指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */