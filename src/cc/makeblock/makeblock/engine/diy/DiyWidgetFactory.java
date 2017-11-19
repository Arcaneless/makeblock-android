package cc.makeblock.makeblock.engine.diy;

import cc.makeblock.makeblock.utils.ParseUtil;
import java.util.HashMap;
import java.util.Map;

public class DiyWidgetFactory
{
  private static final String ALARM_COLOR = "#ff0000";
  private static final Long DURATION_SPRINT = Long.valueOf(3000L);
  private static final int GRADIENT_INDEX = 0;
  private static final int MAX_SPEED = 255;
  private static final int MBOT_LIGHT_PORT = 6;
  private static final int MBOT_LINE_FOLLOWER_PORT = 2;
  private static final int MBOT_ULTRASONIC_PORT = 3;
  private static final int MIN_SPEED = 0;
  private static final int TURN_LEFT_SPEED = 100;
  private static final int TURN_RIGHT_SPEED = -100;
  
  public static Widget createWidget(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    Widget localWidget = new Widget();
    localWidget.type = paramString1;
    localWidget.x = paramInt1;
    localWidget.y = paramInt2;
    localWidget.name = paramString2;
    paramString1 = new HashMap();
    localWidget.arguments = paramString1;
    paramString2 = localWidget.type;
    paramInt1 = -1;
    switch (paramString2.hashCode())
    {
    }
    for (;;)
    {
      switch (paramInt1)
      {
      default: 
        return localWidget;
        if (paramString2.equals("move_sprint"))
        {
          paramInt1 = 0;
          continue;
          if (paramString2.equals("move_joystick"))
          {
            paramInt1 = 1;
            continue;
            if (paramString2.equals("move_rotate"))
            {
              paramInt1 = 2;
              continue;
              if (paramString2.equals("move_turn"))
              {
                paramInt1 = 3;
                continue;
                if (paramString2.equals("light_gradient"))
                {
                  paramInt1 = 4;
                  continue;
                  if (paramString2.equals("light_alarm"))
                  {
                    paramInt1 = 5;
                    continue;
                    if (paramString2.equals("detector_distance"))
                    {
                      paramInt1 = 6;
                      continue;
                      if (paramString2.equals("detector_light"))
                      {
                        paramInt1 = 7;
                        continue;
                        if (paramString2.equals("detector_line_follower"))
                        {
                          paramInt1 = 8;
                          continue;
                          if (paramString2.equals("light_colorPicker"))
                          {
                            paramInt1 = 9;
                            continue;
                            if (paramString2.equals("light_random"))
                            {
                              paramInt1 = 10;
                              continue;
                              if (paramString2.equals("sound_alert"))
                              {
                                paramInt1 = 11;
                                continue;
                                if (paramString2.equals("sound_birthday"))
                                {
                                  paramInt1 = 12;
                                  continue;
                                  if (paramString2.equals("sound_christmas"))
                                  {
                                    paramInt1 = 13;
                                    continue;
                                    if (paramString2.equals("sound_star"))
                                    {
                                      paramInt1 = 14;
                                      continue;
                                      if (paramString2.equals("sound_tiger"))
                                      {
                                        paramInt1 = 15;
                                        continue;
                                        if (paramString2.equals("sound_whistle")) {
                                          paramInt1 = 16;
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    paramString1.put("duration", ParseUtil.parseString(DURATION_SPRINT));
    paramString1.put("move_joystick_speed", ParseUtil.parseString(Integer.valueOf(255)));
    return localWidget;
    paramString1.put("move_joystick_speed", ParseUtil.parseString(Integer.valueOf(255)));
    return localWidget;
    paramString1.put("move_left_speed", ParseUtil.parseString(Integer.valueOf(255)));
    paramString1.put("move_right_speed", ParseUtil.parseString(Integer.valueOf(0)));
    return localWidget;
    paramString1.put("move_left_speed", ParseUtil.parseString(Integer.valueOf(100)));
    paramString1.put("move_right_speed", ParseUtil.parseString(Integer.valueOf(-100)));
    return localWidget;
    paramString1.put("light_gradient_color_index", ParseUtil.parseString(Integer.valueOf(0)));
    return localWidget;
    paramString1.put("light_alarm_color", "#ff0000");
    return localWidget;
    paramString1.put("port", ParseUtil.parseString(Integer.valueOf(3)));
    return localWidget;
    paramString1.put("port", ParseUtil.parseString(Integer.valueOf(6)));
    return localWidget;
    paramString1.put("port", ParseUtil.parseString(Integer.valueOf(2)));
    return localWidget;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\DiyWidgetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */