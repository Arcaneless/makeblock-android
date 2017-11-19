package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class PWMInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 32;
  private static final int length = 5;
  private final byte port;
  private final byte pwm;
  
  public PWMInstruction(byte paramByte1, byte paramByte2)
  {
    this.port = paramByte1;
    this.pwm = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)23);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)32);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.pwm);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",PWM,port:" + this.port + ",pwm:" + this.pwm;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\PWMInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */