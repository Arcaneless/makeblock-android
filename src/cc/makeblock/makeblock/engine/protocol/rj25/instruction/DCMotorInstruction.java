package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class DCMotorInstruction
  extends RJ25Instruction
{
  private static final byte DEVICE_DC_MOTOR = 10;
  private static final int length = 6;
  private final byte port;
  private final short speed;
  
  public DCMotorInstruction(byte paramByte, short paramShort)
  {
    this.port = paramByte;
    this.speed = paramShort;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(6);
    localByteBuffer.put((byte)5);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)10);
    localByteBuffer.put(this.port);
    localByteBuffer.putShort(this.speed);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ", DC Motor:port:" + this.port + "  speed:" + this.speed;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\DCMotorInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */