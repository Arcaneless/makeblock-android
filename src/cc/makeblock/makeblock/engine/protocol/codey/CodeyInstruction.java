package cc.makeblock.makeblock.engine.protocol.codey;

import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.nio.ByteBuffer;

public abstract class CodeyInstruction
  extends Instruction
{
  protected static final byte BROADCAST = 3;
  protected static final byte COMM_VAR = 2;
  protected static final byte FILE = 1;
  protected static final byte HEAD = -13;
  private static final int LENGTH = 6;
  protected static final byte SENSOR = 4;
  protected static final byte TAIL = -12;
  
  private byte[] wrapperDataContent(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = getFrameDataLen(paramArrayOfByte.length);
    byte b1 = CodeyByteUtil.calcFrameCheckSum(new byte[] { -13, arrayOfByte[0], arrayOfByte[1] });
    byte b2 = CodeyByteUtil.calcFrameCheckSum(paramArrayOfByte);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(getTotalLength());
    localByteBuffer.put((byte)-13).put(b1).put(arrayOfByte[0]).put(arrayOfByte[1]).put(paramArrayOfByte).put(b2).put((byte)-12);
    return localByteBuffer.array();
  }
  
  public final byte[] getBytes()
  {
    return wrapperDataContent(getData());
  }
  
  protected abstract byte[] getData();
  
  protected byte[] getFrameDataLen(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)(paramInt >> 8 & 0xFF) };
  }
  
  protected abstract int getLength();
  
  protected int getTotalLength()
  {
    return getLength() + 6;
  }
  
  public String toString()
  {
    return "Codey指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\CodeyInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */