package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class FaceDigitalInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 41;
  private static final int length = 9;
  private final float digital;
  private final byte port;
  private final byte type = 4;
  
  public FaceDigitalInstruction(byte paramByte, float paramFloat)
  {
    this.port = paramByte;
    this.digital = paramFloat;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(9);
    localByteBuffer.put((byte)51);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)41);
    localByteBuffer.put(this.port);
    localByteBuffer.put((byte)4);
    localByteBuffer.putFloat(this.digital);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",表情面板,port:" + this.port + ",digital:" + this.digital;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\FaceDigitalInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */