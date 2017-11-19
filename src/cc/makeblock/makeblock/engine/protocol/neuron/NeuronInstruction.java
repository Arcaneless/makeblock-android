package cc.makeblock.makeblock.engine.protocol.neuron;

import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class NeuronInstruction
  extends Instruction
{
  protected static final byte HEAD = -16;
  private static final int LENGTH = 3;
  protected static final byte TAIL = -9;
  
  protected ByteBuffer getByteBuffer()
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(getTotalLength());
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN).put((byte)-16).put(localByteBuffer.limit() - 1, (byte)-9).position(1);
    return localByteBuffer;
  }
  
  public final byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer();
    putData(localByteBuffer);
    localByteBuffer.put(NeuronByteUtil.getCheckSum(localByteBuffer.array(), 1, localByteBuffer.position()));
    return localByteBuffer.array();
  }
  
  protected abstract int getLength();
  
  protected int getTotalLength()
  {
    return getLength() + 3;
  }
  
  protected abstract void putData(ByteBuffer paramByteBuffer);
  
  public String toString()
  {
    return "神经元指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\NeuronInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */