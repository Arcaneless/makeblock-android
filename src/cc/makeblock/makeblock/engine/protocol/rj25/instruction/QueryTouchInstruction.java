package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryTouchInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 51;
  private static final int length = 4;
  private final byte port;
  
  public QueryTouchInstruction(byte paramByte)
  {
    this.port = paramByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(4);
    localByteBuffer.put((byte)48);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)51);
    localByteBuffer.put(this.port);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",用于查询触摸传感器值,port:" + this.port;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryTouchInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */