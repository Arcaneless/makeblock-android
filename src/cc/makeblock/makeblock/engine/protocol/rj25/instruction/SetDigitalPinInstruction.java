package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class SetDigitalPinInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 30;
  private static final int length = 5;
  private final byte level;
  private final byte port;
  
  public SetDigitalPinInstruction(byte paramByte1, byte paramByte2)
  {
    this.port = paramByte1;
    this.level = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)22);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)30);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.level);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",数字管脚,port:" + this.port + ",shutter:" + this.level;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\SetDigitalPinInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */