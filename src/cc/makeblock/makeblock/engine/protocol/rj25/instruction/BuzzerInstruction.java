package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import java.nio.ByteBuffer;

public class BuzzerInstruction
  extends RJ25Instruction
{
  private static final byte DEVICE_BUZZER = 34;
  private static final int length = 7;
  private final BuzzerTone buzzerTone;
  private final short duration;
  private byte port = -1;
  
  public BuzzerInstruction(byte paramByte, BuzzerTone paramBuzzerTone, short paramShort)
  {
    this.port = paramByte;
    this.buzzerTone = paramBuzzerTone;
    this.duration = paramShort;
  }
  
  public BuzzerInstruction(BuzzerTone paramBuzzerTone, short paramShort)
  {
    this((byte)-1, paramBuzzerTone, paramShort);
  }
  
  public BuzzerTone getBuzzerTone()
  {
    return this.buzzerTone;
  }
  
  public byte[] getBytes()
  {
    if (this.port != -1) {}
    for (ByteBuffer localByteBuffer = getByteBuffer(8);; localByteBuffer = getByteBuffer(7))
    {
      localByteBuffer.put((byte)1);
      localByteBuffer.put((byte)2);
      localByteBuffer.put((byte)34);
      if (this.port != -1) {
        localByteBuffer.put(this.port);
      }
      localByteBuffer.putShort((short)this.buzzerTone.frequency);
      localByteBuffer.putShort(this.duration);
      return localByteBuffer.array();
    }
  }
  
  public String toString()
  {
    return super.toString() + ", 蜂鸣器指令, 音调:" + this.buzzerTone.name() + ", 持续时间:" + this.duration;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\BuzzerInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */