package cc.makeblock.makeblock.engine.protocol.rj25;

public class InstructionMusicWrap
{
  private final long duration;
  public final short tempo;
  public final BuzzerTone tone;
  
  public InstructionMusicWrap(BuzzerTone paramBuzzerTone, short paramShort, long paramLong)
  {
    this.tone = paramBuzzerTone;
    this.tempo = paramShort;
    this.duration = paramLong;
  }
  
  public long getDuration()
  {
    return this.duration + 100L;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\InstructionMusicWrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */