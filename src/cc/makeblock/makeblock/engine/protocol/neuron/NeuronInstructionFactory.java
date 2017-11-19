package cc.makeblock.makeblock.engine.protocol.neuron;

import cc.makeblock.makeblock.engine.protocol.base.Instruction;

public class NeuronInstructionFactory
{
  public static final int AIRBLOCK_AIR_ROTATE_LEFT_ANGLE = -10;
  public static final int AIRBLOCK_AIR_ROTATE_RIGHT_ANGLE = 10;
  
  public static Instruction createAirBlockAllotIdInstruction()
  {
    return new NeuronAllotIdInstruction();
  }
  
  public static AirBlockInstruction createAirBlockBoardCalibrationInstruction()
  {
    return new AirBlockCalibrationInstruction((byte)2);
  }
  
  public static AirBlockInstruction createAirBlockCircleSkillInstruction()
  {
    return new AirBlockControlWordInstruction((byte)0, 360.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public static AirBlockInstruction createAirBlockControlWordInstruction(int paramInt, float paramFloat)
  {
    switch (paramInt)
    {
    case 12: 
    case 30: 
    default: 
      return null;
    case 1: 
      return new AirBlockControlWordInstruction((byte)1, 0.0F, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
    case 0: 
      return new AirBlockControlWordInstruction((byte)0, 0.0F, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
    case 2: 
      return new AirBlockControlWordInstruction((byte)2, 0.0F, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
    case 3: 
      return new AirBlockControlWordInstruction((byte)3, 0.0F, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
    case 4: 
      return new AirBlockControlWordInstruction((byte)4, 0.0F, paramFloat, 0.0F, 1.5F, 0.0F, 0.0F);
    case 5: 
      return new AirBlockControlWordInstruction((byte)5, 0.0F, paramFloat, 0.0F, -0.8F, 0.0F, 0.0F);
    case 10: 
      return new AirBlockControlWordInstruction((byte)10, 0.0F, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
    case 11: 
      return new AirBlockControlWordInstruction((byte)11, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }
    return new AirBlockControlWordInstruction((byte)13, 20.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public static AirBlockInstruction createAirBlockControlWordRotateLeftInstruction(int paramInt, float paramFloat)
  {
    return new AirBlockControlWordInstruction((byte)6, paramInt, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public static AirBlockInstruction createAirBlockControlWordRotateRightInstruction(int paramInt, float paramFloat)
  {
    return new AirBlockControlWordInstruction((byte)6, paramInt, paramFloat, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public static AirBlockInstruction createAirBlockGyroscopeCalibrationInstruction()
  {
    return new AirBlockCalibrationInstruction((byte)1);
  }
  
  public static AirBlockInstruction createAirBlockLandingInstruction()
  {
    return new AirBlockLandingInstruction((byte)5);
  }
  
  public static AirBlockInstruction createAirBlockLaunchInstruction()
  {
    return new AirBlockLaunchInstruction();
  }
  
  public static AirBlockInstruction createAirBlockSetFormInstruction(byte paramByte)
  {
    return new AirBlockSetFormInstruction(paramByte);
  }
  
  public static AirBlockInstruction createAirBlockSpeedInstruction(short paramShort1, short paramShort2)
  {
    return new AirBlockSpeedInstruction(paramShort1, paramShort2, paramShort2, paramShort1);
  }
  
  public static AirBlockInstruction createAirBlockStateOffInstruction()
  {
    return new AirBlockStateInstruction((byte)0);
  }
  
  public static AirBlockInstruction createAirBlockStateOnInstruction()
  {
    return new AirBlockStateInstruction((byte)1);
  }
  
  public static AirBlockInstruction createAirBlockStopInstruction()
  {
    return new AirBlockStopInstruction();
  }
  
  public static AirBlockInstruction createAirBlockTakeOffInstruction()
  {
    return new AirBlockLandingInstruction((byte)8);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\NeuronInstructionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */