package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class FaceTimeInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 41;
  private static final int length = 8;
  private final byte hour;
  private final byte minute;
  private final byte port;
  private final byte separator = 1;
  private final byte type = 3;
  
  public FaceTimeInstruction(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.port = paramByte1;
    this.hour = paramByte2;
    this.minute = paramByte3;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(8);
    localByteBuffer.put((byte)51);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)41);
    localByteBuffer.put(this.port);
    localByteBuffer.put((byte)3);
    localByteBuffer.put((byte)1);
    localByteBuffer.put(this.hour);
    localByteBuffer.put(this.minute);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",表情面板,port:" + this.port + ",hour:" + this.hour + ",minute:" + this.minute;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\FaceTimeInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */