package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class DigitalServoInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 11;
  private static final int length = 6;
  private final byte angle;
  private final byte port;
  private final byte slot;
  
  public DigitalServoInstruction(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.port = paramByte1;
    this.slot = paramByte2;
    this.angle = paramByte3;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(6);
    localByteBuffer.put((byte)19);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)11);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.slot);
    localByteBuffer.put(this.angle);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",数字舵机,port:" + this.port + ",slot:" + this.slot + ",angle:" + this.angle;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\DigitalServoInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */