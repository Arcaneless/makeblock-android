package cc.makeblock.makeblock.engine.protocol.codey;

import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FileHeaderInstruction
  extends CodeyInstruction
{
  private static final byte[] FRAME_NECK = { 0, 94 };
  private static final byte cmd = 1;
  private static final byte fileType = 0;
  private byte[] contentBytes;
  private String fileName;
  private int totalLength;
  
  public FileHeaderInstruction(String paramString, byte[] paramArrayOfByte)
  {
    this.fileName = paramString;
    this.contentBytes = paramArrayOfByte;
  }
  
  protected byte[] getData()
  {
    byte[] arrayOfByte1 = CodeyByteUtil.calcFileCheckSum(this.contentBytes);
    byte[] arrayOfByte2 = CodeyByteUtil.stringToByteArray(this.fileName);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(arrayOfByte1.length + 4 + arrayOfByte2.length + 1);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    localByteBuffer.put((byte)0).putInt(this.contentBytes.length).put(arrayOfByte1).put(arrayOfByte2);
    arrayOfByte1 = localByteBuffer.array();
    arrayOfByte2 = getFrameDataLen(arrayOfByte1.length);
    this.totalLength = (arrayOfByte1.length + arrayOfByte2.length + FRAME_NECK.length + 2);
    localByteBuffer = ByteBuffer.allocate(this.totalLength);
    localByteBuffer.put((byte)1).put(FRAME_NECK).put((byte)1).put(arrayOfByte2).put(arrayOfByte1);
    return localByteBuffer.array();
  }
  
  protected int getLength()
  {
    return this.totalLength;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\FileHeaderInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */