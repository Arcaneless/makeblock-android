package cc.makeblock.makeblock.engine.protocol.codey;

import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.nio.ByteBuffer;

public class DeleteFileInstruction
  extends CodeyInstruction
{
  private static final byte[] FRAME_NECK = { 0, 94 };
  private static final byte cmd = 3;
  private String filename;
  private int totalLength;
  
  public DeleteFileInstruction(String paramString)
  {
    this.filename = paramString;
  }
  
  protected byte[] getData()
  {
    byte[] arrayOfByte = getFrameDataLen(CodeyByteUtil.stringToByteArray(this.filename).length);
    this.totalLength = (FRAME_NECK.length + arrayOfByte.length + 2);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(this.totalLength);
    localByteBuffer.put((byte)1).put(FRAME_NECK).put((byte)3).put(arrayOfByte);
    return localByteBuffer.array();
  }
  
  protected int getLength()
  {
    return this.totalLength;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\DeleteFileInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */