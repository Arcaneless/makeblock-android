package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class ResetDeviceRuntimeInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 50;
  private static final int length = 3;
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(3);
    localByteBuffer.put((byte)25);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)50);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",重置固件运行时间";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\ResetDeviceRuntimeInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */