package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class JoystickInstruction
  extends RJ25Instruction
{
  private static final byte DEVICE_JOYSTICK = 5;
  private static final int length = 7;
  private final short speed1;
  private final short speed2;
  
  public JoystickInstruction(short paramShort1, short paramShort2)
  {
    this.speed1 = paramShort1;
    this.speed2 = paramShort2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(7);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)5);
    localByteBuffer.putShort(this.speed1);
    localByteBuffer.putShort(this.speed2);
    return localByteBuffer.array();
  }
  
  public short getSpeed1()
  {
    return this.speed1;
  }
  
  public short getSpeed2()
  {
    return this.speed2;
  }
  
  public String toString()
  {
    return super.toString() + ", 摇杆指令, speed1:" + this.speed1 + ", speed2:" + this.speed2;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\JoystickInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */