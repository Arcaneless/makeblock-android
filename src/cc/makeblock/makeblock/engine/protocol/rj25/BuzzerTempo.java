package cc.makeblock.makeblock.engine.protocol.rj25;

public class BuzzerTempo
{
  public final short ONE;
  public final short ONE_OF_EIGHT;
  public final short ONE_OF_FOUR;
  public final short ONE_OF_TWO;
  public final short TWO;
  
  public BuzzerTempo(int paramInt)
  {
    this.ONE = ((short)paramInt);
    this.TWO = ((short)(paramInt * 2));
    this.ONE_OF_EIGHT = ((short)(paramInt / 8));
    this.ONE_OF_FOUR = ((short)(paramInt / 4));
    this.ONE_OF_TWO = ((short)(paramInt / 2));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\BuzzerTempo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */