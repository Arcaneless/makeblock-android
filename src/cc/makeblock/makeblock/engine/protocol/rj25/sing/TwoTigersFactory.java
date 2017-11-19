package cc.makeblock.makeblock.engine.protocol.rj25.sing;

import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTempo;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.InstructionMusicWrap;
import java.util.ArrayList;
import java.util.List;

public class TwoTigersFactory
  extends MusicActionBuilderFactory
{
  private BuzzerTempo mBuzzerTempo = new BuzzerTempo(1600);
  
  public TwoTigersFactory(Buzzer paramBuzzer)
  {
    super(paramBuzzer);
  }
  
  public List<InstructionMusicWrap> createInstructions()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.F5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_TWO, this.mBuzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.F5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_TWO, this.mBuzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.F5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.F5, this.mBuzzerTempo.ONE_OF_EIGHT, this.mBuzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G4, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_TWO, this.mBuzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G4, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C5, this.mBuzzerTempo.ONE_OF_FOUR, this.mBuzzerTempo.ONE_OF_FOUR));
    return localArrayList;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\sing\TwoTigersFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */