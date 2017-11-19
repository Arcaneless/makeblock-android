package cc.makeblock.makeblock.engine.protocol.rj25.sing;

import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTempo;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.InstructionMusicWrap;
import java.util.ArrayList;
import java.util.List;

public class ChristmasFactory
  extends MusicActionBuilderFactory
{
  private BuzzerTempo buzzerTempo = new BuzzerTempo(1200);
  
  public ChristmasFactory(Buzzer paramBuzzer)
  {
    super(paramBuzzer);
  }
  
  public List<InstructionMusicWrap> createInstructions()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.F6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_TWO, this.buzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_TWO, this.buzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_TWO, this.buzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_TWO, this.buzzerTempo.ONE_OF_TWO));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.E6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.G5, this.buzzerTempo.ONE_OF_EIGHT, this.buzzerTempo.ONE_OF_EIGHT));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.A5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.D6, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.B5, this.buzzerTempo.ONE_OF_FOUR, this.buzzerTempo.ONE_OF_FOUR));
    localArrayList.add(new InstructionMusicWrap(BuzzerTone.C6, this.buzzerTempo.ONE, this.buzzerTempo.ONE));
    return localArrayList;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\sing\ChristmasFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */