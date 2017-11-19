package cc.makeblock.makeblock.engine.firmware.airblock;

public class HandShake2Command
  extends HandShake1Command
{
  public HandShake2Command(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.ERASE_1, paramString);
      return;
    }
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.ONFAIL, paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\HandShake2Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */