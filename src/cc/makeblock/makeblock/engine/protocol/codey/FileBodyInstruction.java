package cc.makeblock.makeblock.engine.protocol.codey;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FileBodyInstruction
  extends CodeyInstruction
{
  private static final byte[] FRAME_NECK = { 0, 94 };
  private static final byte cmd = 2;
  private static final int offset_length = 4;
  private byte[] sliceData;
  private int startIndex;
  private int totalLength;
  
  public FileBodyInstruction(int paramInt, byte[] paramArrayOfByte)
  {
    this.startIndex = paramInt;
    this.sliceData = paramArrayOfByte;
  }
  
  protected byte[] getData()
  {
    byte[] arrayOfByte = getFrameDataLen(this.sliceData.length + 4);
    this.totalLength = (FRAME_NECK.length + arrayOfByte.length + 4 + this.sliceData.length + 2);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(this.totalLength);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    localByteBuffer.put((byte)1).put(FRAME_NECK).put((byte)2).put(arrayOfByte).putInt(this.startIndex).put(this.sliceData);
    return localByteBuffer.array();
  }
  
  protected int getLength()
  {
    return this.totalLength;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\FileBodyInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */