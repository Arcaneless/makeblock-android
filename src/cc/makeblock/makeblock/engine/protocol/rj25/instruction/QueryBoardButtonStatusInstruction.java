package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryBoardButtonStatusInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 35;
  private static final int length = 5;
  private final byte port = 7;
  private final byte status;
  
  public QueryBoardButtonStatusInstruction(byte paramByte)
  {
    this.status = paramByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)49);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)35);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.status);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",板载按键状态,port:" + this.port + ",status:" + this.status;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryBoardButtonStatusInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */