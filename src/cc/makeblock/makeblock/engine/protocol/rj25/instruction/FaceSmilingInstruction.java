package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class FaceSmilingInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 41;
  private static final int length = 23;
  private final byte port;
  private final byte[] smile;
  private final byte type = 2;
  private final byte x;
  private final byte y;
  
  public FaceSmilingInstruction(byte paramByte1, byte paramByte2, byte paramByte3, byte[] paramArrayOfByte)
  {
    this.x = paramByte2;
    this.y = paramByte3;
    this.port = paramByte1;
    this.smile = paramArrayOfByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(23);
    localByteBuffer.put((byte)51);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)41);
    localByteBuffer.put(this.port);
    localByteBuffer.put((byte)2);
    localByteBuffer.put(this.x);
    localByteBuffer.put(this.y);
    int j = this.smile.length;
    int i = 0;
    while (i < j)
    {
      localByteBuffer.put(this.smile[i]);
      i += 1;
    }
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",表情面板,port:" + this.port + ",x:" + this.x + ",y:" + this.y + ",smile:" + this.smile.toString();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\FaceSmilingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */