package cc.makeblock.makeblock.engine.protocol.rj25.sing;

import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.action.ActionType;
import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.InstructionMusicWrap;
import java.util.List;

public abstract class MusicActionBuilderFactory
{
  private Buzzer buzzer;
  
  public MusicActionBuilderFactory(Buzzer paramBuzzer)
  {
    this.buzzer = paramBuzzer;
  }
  
  private Runnable createRunnable(final BuzzerTone paramBuzzerTone, final short paramShort)
  {
    new Runnable()
    {
      public void run()
      {
        MusicActionBuilderFactory.this.buzzer.buzz(paramBuzzerTone, paramShort);
      }
    };
  }
  
  public ActionBuilder createActionBuilder()
  {
    ActionBuilder localActionBuilder = new ActionBuilder();
    List localList = createInstructions();
    int j = localList.size();
    Object localObject = null;
    int i = 0;
    if (i < j)
    {
      InstructionMusicWrap localInstructionMusicWrap = (InstructionMusicWrap)localList.get(i);
      Runnable localRunnable = createRunnable(localInstructionMusicWrap.tone, localInstructionMusicWrap.tempo);
      if (localObject != null) {}
      for (long l = ((InstructionMusicWrap)localObject).getDuration();; l = 0L)
      {
        localActionBuilder.append(localRunnable, l);
        localObject = localInstructionMusicWrap;
        i += 1;
        break;
      }
    }
    localActionBuilder.setActionType(ActionType.CONFLICT_BUZZER);
    return localActionBuilder;
  }
  
  public abstract List<InstructionMusicWrap> createInstructions();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\sing\MusicActionBuilderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */