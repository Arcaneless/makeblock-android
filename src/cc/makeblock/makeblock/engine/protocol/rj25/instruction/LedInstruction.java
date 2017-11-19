package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class LedInstruction
  extends RJ25Instruction
{
  private static final byte DEVICE_LED = 8;
  private static final int length = 9;
  private final byte b;
  private final byte g;
  private final byte ledIndex;
  private final byte port;
  private final byte r;
  private final byte slot;
  
  public LedInstruction(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6)
  {
    this.port = paramByte1;
    this.slot = paramByte2;
    this.ledIndex = paramByte3;
    this.r = paramByte4;
    this.g = paramByte5;
    this.b = paramByte6;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(9);
    localByteBuffer.put((byte)3);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)8);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.slot);
    localByteBuffer.put(this.ledIndex);
    localByteBuffer.put(this.r);
    localByteBuffer.put(this.g);
    localByteBuffer.put(this.b);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ", LED, port:" + this.port + "slot:" + this.slot + ", index:" + this.ledIndex + ", rgb:" + this.r + "," + this.g + "," + this.b;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\LedInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */