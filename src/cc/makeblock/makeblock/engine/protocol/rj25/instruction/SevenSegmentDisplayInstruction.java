package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class SevenSegmentDisplayInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 9;
  private static final int length = 8;
  private final float digital;
  private final byte port;
  
  public SevenSegmentDisplayInstruction(byte paramByte, float paramFloat)
  {
    this.port = paramByte;
    this.digital = paramFloat;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(8);
    localByteBuffer.put((byte)20);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)9);
    localByteBuffer.put(this.port);
    localByteBuffer.putFloat(this.digital);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",数码管,port:" + this.port + ",digital:" + this.digital;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\SevenSegmentDisplayInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */