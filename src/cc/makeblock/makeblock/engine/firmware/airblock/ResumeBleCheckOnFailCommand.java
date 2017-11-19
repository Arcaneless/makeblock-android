package cc.makeblock.makeblock.engine.firmware.airblock;

public class ResumeBleCheckOnFailCommand
  extends ResumeBleCheckCommand
{
  public ResumeBleCheckOnFailCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.FAIL, paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\ResumeBleCheckOnFailCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */