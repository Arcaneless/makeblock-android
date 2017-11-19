package cc.makeblock.makeblock.engine.protocol.rj25.respond;

public class RJ25FormRespond
  extends RJ25Respond
{
  public static final int BLUETOOTH = 0;
  public static final int INFRARED = 3;
  public static final int LINE_PATROL = 4;
  public static final int OBSTACLE_AVOIDANCE = 1;
  public static final int SELF_BALANCING = 2;
  public final int mode;
  
  public RJ25FormRespond(int paramInt)
  {
    this.mode = paramInt;
  }
  
  public String toString()
  {
    String str;
    switch (this.mode)
    {
    default: 
      str = "";
    }
    for (;;)
    {
      return super.toString() + ", 模式:" + str;
      str = "手动模式";
      continue;
      str = "避障模式";
      continue;
      str = "自平衡";
      continue;
      str = "红外";
      continue;
      str = "巡线";
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\respond\RJ25FormRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */