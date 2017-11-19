package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.util.AttributeSet;
import cc.makeblock.makeblock.customview.cell.CellView.OnWidgetTriggeredListener;
import cc.makeblock.makeblock.engine.protocol.web.send.JoystickThrottleJson;
import cc.makeblock.makeblock.engine.protocol.web.send.JoystickWordJson;

public class AirBlockBaseJoystickView
  extends BaseJoystickView
{
  public static final int AIRBLOCK_HOVER_SPEED_LAND = 1400;
  public static final int AIRBLOCK_HOVER_SPEED_SHIP = 0;
  public static final int AIRBLOCK_HOVER_SPEED_SHIP_STOP = 1600;
  public static final int AIRBLOCK_MAX_SPEED_LAND = 2000;
  public static final int AIRBLOCK_MAX_SPEED_SHIP = 1600;
  public static final int AIRBLOCK_WORD_BACKWARD = 1;
  public static final int AIRBLOCK_WORD_BALANCE = 10;
  public static final int AIRBLOCK_WORD_DOWN = 5;
  public static final int AIRBLOCK_WORD_FORWARD = 0;
  public static final int AIRBLOCK_WORD_HOVER = 9;
  public static final int AIRBLOCK_WORD_LEFT = 2;
  public static final int AIRBLOCK_WORD_RIGHT = 3;
  public static final int AIRBLOCK_WORD_ROLL = 11;
  public static final int AIRBLOCK_WORD_ROTATE = 6;
  public static final int AIRBLOCK_WORD_UP = 4;
  public static final int AIRBLOCK_WORD_WAIT = 30;
  
  public AirBlockBaseJoystickView(Context paramContext)
  {
    super(paramContext);
  }
  
  public AirBlockBaseJoystickView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void sendAirBlockControlCommand(int paramInt1, int paramInt2)
  {
    sendAirBlockControlCommand(paramInt1, paramInt2, 1, 0, 0.0F, 0.0F, 0.0F);
  }
  
  protected void sendAirBlockControlCommand(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    String str = new JoystickWordJson(String.valueOf(paramInt1), String.valueOf(paramInt2), String.valueOf(paramInt3), String.valueOf(paramInt4), String.valueOf(paramFloat1), String.valueOf(paramFloat2), String.valueOf(paramFloat3)).toString();
    this.mOnWidgetTriggeredListener.onWidgetTrigger("airblockSetControlWord", str);
  }
  
  protected void sendAirBlockSpeedCommand(int paramInt1, int paramInt2)
  {
    String str = new JoystickThrottleJson(String.valueOf(paramInt1), String.valueOf(paramInt2)).toString();
    this.mOnWidgetTriggeredListener.onWidgetTrigger("airblockSetThrottle", str);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\AirBlockBaseJoystickView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */