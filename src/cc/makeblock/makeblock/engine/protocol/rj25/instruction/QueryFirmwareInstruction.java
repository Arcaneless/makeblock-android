package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryFirmwareInstruction
  extends RJ25Instruction
{
  public static final byte INDEX = 0;
  private static final byte device = 0;
  private static final int length = 3;
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(3);
    localByteBuffer.put((byte)0);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)0);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ": 通用查询版本号指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryFirmwareInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */