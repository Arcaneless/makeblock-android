package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class DigitalServo2Instruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 33;
  private static final int length = 5;
  private final byte angle;
  private final byte port;
  
  public DigitalServo2Instruction(byte paramByte1, byte paramByte2)
  {
    this.port = paramByte1;
    this.angle = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)24);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)33);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.angle);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",数字舵机2,port:" + this.port + ",slot:" + 2130903365 + ",angle:" + this.angle;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\DigitalServo2Instruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */