package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryTemperatureInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 2;
  private static final int length = 5;
  private final byte port;
  private final byte slot;
  
  public QueryTemperatureInstruction(byte paramByte1, byte paramByte2)
  {
    this.port = paramByte1;
    this.slot = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)8);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)2);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.slot);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",温度,port:" + this.port + ",slot:" + this.slot;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryTemperatureInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */