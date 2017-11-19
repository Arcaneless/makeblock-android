package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class UnlockSmartServoInstruction
  extends RJ25Instruction
{
  private static final byte INSTRUCTION_UNLOCK = 1;
  private static final int SERVO_NUM = 255;
  private static final byte SMART_SERVO_INSTRUCTION = 64;
  private static final byte SUB_COMMAND = 1;
  private static final int length = 7;
  private byte port;
  
  public UnlockSmartServoInstruction(byte paramByte)
  {
    this.port = paramByte;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(7);
    localByteBuffer.put((byte)64);
    localByteBuffer.put((byte)2);
    localByteBuffer.put((byte)64);
    localByteBuffer.put((byte)1);
    localByteBuffer.put(this.port);
    localByteBuffer.put((byte)-1);
    localByteBuffer.put((byte)1);
    return localByteBuffer.array();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\UnlockSmartServoInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */