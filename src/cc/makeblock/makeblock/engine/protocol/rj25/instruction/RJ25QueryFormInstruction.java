package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class RJ25QueryFormInstruction
  extends RJ25Instruction
{
  public static final byte INDEX = -125;
  private static final byte device = 60;
  private static final int length = 4;
  private static final byte sub = 113;
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(4);
    localByteBuffer.put((byte)-125);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)60);
    localByteBuffer.put((byte)113);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ": 通用查询形态指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\RJ25QueryFormInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */