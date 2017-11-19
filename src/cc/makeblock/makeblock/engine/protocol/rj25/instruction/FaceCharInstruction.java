package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class FaceCharInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 41;
  private final int charLength;
  private final byte[] chars;
  private int length = 8;
  private final byte port;
  private final byte type = 1;
  private final byte x;
  private final byte y;
  
  public FaceCharInstruction(byte paramByte1, byte paramByte2, byte paramByte3, byte[] paramArrayOfByte)
  {
    this.x = paramByte2;
    this.y = paramByte3;
    this.port = paramByte1;
    this.chars = paramArrayOfByte;
    this.charLength = paramArrayOfByte.length;
    this.length += this.charLength;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(this.length);
    localByteBuffer.put((byte)51);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)41);
    localByteBuffer.put(this.port);
    localByteBuffer.put((byte)1);
    localByteBuffer.put(this.x);
    localByteBuffer.put(this.y);
    localByteBuffer.put((byte)this.charLength);
    int i = 0;
    while (i < this.charLength)
    {
      localByteBuffer.put(this.chars[i]);
      i += 1;
    }
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",表情面板,port:" + this.port + ",x:" + this.x + ",y:" + this.y + ",charLength:" + this.charLength + ",chars:" + this.chars;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\FaceCharInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */