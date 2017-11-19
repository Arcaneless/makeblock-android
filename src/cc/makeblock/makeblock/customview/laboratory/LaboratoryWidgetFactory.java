package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.view.LayoutInflater;

public class LaboratoryWidgetFactory
{
  public static LaboratoryView createWidgetView(Context paramContext, String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return null;
        if (paramString.equals("move_sprint"))
        {
          i = 0;
          continue;
          if (paramString.equals("move_rotate"))
          {
            i = 1;
            continue;
            if (paramString.equals("move_turn"))
            {
              i = 2;
              continue;
              if (paramString.equals("move_joystick"))
              {
                i = 3;
                continue;
                if (paramString.equals("light_colorPicker"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("light_random"))
                  {
                    i = 5;
                    continue;
                    if (paramString.equals("light_alarm"))
                    {
                      i = 6;
                      continue;
                      if (paramString.equals("light_gradient"))
                      {
                        i = 7;
                        continue;
                        if (paramString.equals("sound_star"))
                        {
                          i = 8;
                          continue;
                          if (paramString.equals("sound_birthday"))
                          {
                            i = 9;
                            continue;
                            if (paramString.equals("sound_tiger"))
                            {
                              i = 10;
                              continue;
                              if (paramString.equals("sound_alert"))
                              {
                                i = 11;
                                continue;
                                if (paramString.equals("sound_whistle"))
                                {
                                  i = 12;
                                  continue;
                                  if (paramString.equals("sound_christmas"))
                                  {
                                    i = 13;
                                    continue;
                                    if (paramString.equals("detector_light"))
                                    {
                                      i = 14;
                                      continue;
                                      if (paramString.equals("detector_distance"))
                                      {
                                        i = 15;
                                        continue;
                                        if (paramString.equals("detector_line_follower")) {
                                          i = 16;
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
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427452, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427451, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427455, null);
    return new Joystick(paramContext);
    return new ColorPicker(paramContext);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427450, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427445, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427449, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427453, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427447, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427454, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427446, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427456, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427448, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427459, null);
    return (LaboratoryView)LayoutInflater.from(paramContext).inflate(2131427458, null);
    return new LineFollowIndicator(paramContext);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\LaboratoryWidgetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */