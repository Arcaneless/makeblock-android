package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class ModeInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_AURIGA = 17;
  public static final byte DEVICE_MEGAPI = 18;
  private static final byte DEVICE_SET_FORM = 60;
  public static final byte DEVICE_STARTER = 16;
  public static final byte MODE_BALANCE = 2;
  public static final byte MODE_BLUETOOTH = 0;
  public static final byte MODE_INFRARED = 3;
  public static final byte MODE_LINE_FOLLOW = 4;
  public static final byte MODE_OBSTACLE_VOID = 1;
  private static final int length = 5;
  private byte mode;
  private byte subCommand;
  
  public ModeInstruction(byte paramByte1, byte paramByte2)
  {
    this.mode = paramByte1;
    this.subCommand = paramByte2;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)4);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)60);
    localByteBuffer.put(this.subCommand);
    localByteBuffer.put(this.mode);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ", 设置模式, subCommand:" + this.subCommand + ", mode:" + this.mode;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\ModeInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */