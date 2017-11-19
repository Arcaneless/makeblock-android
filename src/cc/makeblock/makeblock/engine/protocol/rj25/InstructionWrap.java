package cc.makeblock.makeblock.engine.protocol.rj25;

import cc.makeblock.makeblock.engine.protocol.base.Instruction;

public class InstructionWrap
{
  private long duration;
  private Instruction mInstruction;
  
  public InstructionWrap(Instruction paramInstruction, long paramLong)
  {
    this.mInstruction = paramInstruction;
    this.duration = paramLong;
  }
  
  public boolean equals(Object paramObject)
  {
    if (getClass() != paramObject.getClass()) {}
    do
    {
      return false;
      paramObject = (InstructionWrap)paramObject;
    } while ((!this.mInstruction.equals(((InstructionWrap)paramObject).mInstruction)) || (this.duration != ((InstructionWrap)paramObject).duration));
    return true;
  }
  
  public long getDuration()
  {
    return this.duration;
  }
  
  public Instruction getInstruction()
  {
    return this.mInstruction;
  }
  
  public void setDuration(long paramLong)
  {
    this.duration = paramLong;
  }
  
  public void setInstruction(Instruction paramInstruction)
  {
    this.mInstruction = paramInstruction;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\InstructionWrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */