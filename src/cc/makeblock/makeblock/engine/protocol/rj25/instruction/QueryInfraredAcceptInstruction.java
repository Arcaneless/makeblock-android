package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryInfraredAcceptInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 20;
  private static final int length = 5;
  private final byte key;
  private final byte port = 0;
  
  public QueryInfraredAcceptInstruction(byte paramByte)
  {
    this.key = paramByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)54);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)20);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.key);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",红外传感器(接受端),port:" + this.port + ",key:" + this.key;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryInfraredAcceptInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */