package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryFlameInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 24;
  private static final int length = 4;
  private final byte port;
  
  public QueryFlameInstruction(byte paramByte)
  {
    this.port = paramByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(4);
    localByteBuffer.put((byte)38);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)24);
    localByteBuffer.put(this.port);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",火焰传感器,port:" + this.port;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryFlameInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */