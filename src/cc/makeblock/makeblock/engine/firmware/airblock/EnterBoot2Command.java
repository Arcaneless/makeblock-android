package cc.makeblock.makeblock.engine.firmware.airblock;

public class EnterBoot2Command
  extends EnterBootCommand
{
  public EnterBoot2Command(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.HAND_SHAKE_2, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\EnterBoot2Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */