package cc.makeblock.makeblock.engine.protocol.rj25;

import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.BuzzerInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.JoystickInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.LedInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.ModeInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.QueryUltrasonicInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.ResetSmartServoInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.UnlockSmartServoInstruction;

public class RJ25InstructionFactory
{
  public static Instruction createBuzzerInstruction(String paramString, BuzzerTone paramBuzzerTone, short paramShort)
  {
    if (DeviceBoardName.auriga.name().equalsIgnoreCase(paramString)) {}
    for (byte b = 45;; b = -1) {
      return new BuzzerInstruction(b, paramBuzzerTone, paramShort);
    }
  }
  
  public static InstructionWrap createInstructionWrap(Instruction paramInstruction, long paramLong)
  {
    return new InstructionWrap(paramInstruction, paramLong);
  }
  
  public static Instruction createJoystickInstruction(String paramString, short paramShort1, short paramShort2)
  {
    if ((DeviceBoardName.auriga.name().equalsIgnoreCase(paramString)) || (DeviceBoardName.megaPi.name().equalsIgnoreCase(paramString))) {
      return new JoystickInstruction((short)-paramShort2, paramShort1);
    }
    return new JoystickInstruction((short)-paramShort1, paramShort2);
  }
  
  public static Instruction createLedInstruction(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6)
  {
    return new LedInstruction(paramByte1, paramByte2, paramByte3, paramByte4, paramByte5, paramByte6);
  }
  
  public static Instruction createQueryUltrasonicInstruction(byte paramByte)
  {
    return new QueryUltrasonicInstruction(paramByte);
  }
  
  public static Instruction createResetSmartServoInstruction(byte paramByte)
  {
    return new ResetSmartServoInstruction(paramByte);
  }
  
  public static Instruction createSetModeInstruction(byte paramByte1, byte paramByte2)
  {
    return new ModeInstruction(paramByte2, paramByte1);
  }
  
  public static Instruction createUnlockSmartServo(byte paramByte)
  {
    return new UnlockSmartServoInstruction(paramByte);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\RJ25InstructionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */