package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class GestureInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 6;
  private static final int length = 5;
  private final byte axle;
  private final byte port;
  
  public GestureInstruction(byte paramByte1, byte paramByte2)
  {
    this.port = paramByte1;
    this.axle = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)18);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)6);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.axle);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",姿态,port:" + this.port + ",axle:" + this.axle;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\GestureInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */