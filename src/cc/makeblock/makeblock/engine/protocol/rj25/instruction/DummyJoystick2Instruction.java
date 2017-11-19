package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class DummyJoystick2Instruction
  extends RJ25Instruction
{
  private static final byte DEVICE_JOYSTICK_2 = 52;
  private static final int length = 8;
  private static final byte port = 0;
  private short speed;
  private short turningAngle;
  
  public DummyJoystick2Instruction(short paramShort1, short paramShort2)
  {
    this.turningAngle = paramShort1;
    this.speed = paramShort2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(8);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)52);
    localByteBuffer.put((byte)0);
    localByteBuffer.putShort(this.turningAngle);
    localByteBuffer.putShort(this.speed);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + " 虚拟摇杆2，角度：" + this.turningAngle + " 速度:" + this.speed;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\DummyJoystick2Instruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */