package cc.makeblock.makeblock.engine.diy;

import android.graphics.Color;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.action.ActionType;
import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.device.operation.Joystick;
import cc.makeblock.makeblock.engine.device.operation.LedOnBoard;
import cc.makeblock.makeblock.engine.device.operation.LightSensor;
import cc.makeblock.makeblock.engine.device.operation.LineFollowerSensor;
import cc.makeblock.makeblock.engine.device.operation.UltrasonicSensor;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTempo;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.sing.ChristmasFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.sing.HappyBirthdayFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.sing.LittleStarsFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.sing.TwoTigersFactory;
import cc.makeblock.makeblock.utils.ParseUtil;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiyActionFactory
{
  private static ActionBuilder createActionWithActionType(ActionType paramActionType)
  {
    return new ActionBuilder().setActionType(paramActionType);
  }
  
  public static Action createBuzzerAction(Buzzer paramBuzzer, String paramString, Map<String, String> paramMap)
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
        if (paramString.equals("sound_star"))
        {
          i = 0;
          continue;
          if (paramString.equals("sound_birthday"))
          {
            i = 1;
            continue;
            if (paramString.equals("sound_tiger"))
            {
              i = 2;
              continue;
              if (paramString.equals("sound_christmas"))
              {
                i = 3;
                continue;
                if (paramString.equals("sound_alert"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("sound_whistle")) {
                    i = 5;
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return new LittleStarsFactory(paramBuzzer).createActionBuilder().setName("sound_star").build();
    return new HappyBirthdayFactory(paramBuzzer).createActionBuilder().setName("sound_birthday").build();
    return new TwoTigersFactory(paramBuzzer).createActionBuilder().setName("sound_tiger").build();
    return new ChristmasFactory(paramBuzzer).createActionBuilder().setName("sound_christmas").build();
    short s = new BuzzerTempo(1000).ONE_OF_TWO;
    return createBuzzerActionBuilder().append(createBuzzerRunnable(paramBuzzer, BuzzerTone.A5, s), 0L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.E5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.A5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.E5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.A5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.E5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.A5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.E5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.A5, s), 500L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.E5, s), 500L).setName("sound_alert").build();
    s = new BuzzerTempo(1000).ONE_OF_TWO;
    return createBuzzerActionBuilder().append(createBuzzerRunnable(paramBuzzer, BuzzerTone.C6, s), 0L).append(createBuzzerRunnable(paramBuzzer, BuzzerTone.C6, s), 1000L).setName("sound_whistle").build();
  }
  
  private static ActionBuilder createBuzzerActionBuilder()
  {
    return createActionWithActionType(ActionType.CONFLICT_BUZZER);
  }
  
  private static Runnable createBuzzerRunnable(Buzzer paramBuzzer, final BuzzerTone paramBuzzerTone, final short paramShort)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$buzzer.buzz(paramBuzzerTone, paramShort);
      }
    };
  }
  
  private static Runnable createDummyJoystickRunnable(Joystick paramJoystick, final int paramInt, final float paramFloat)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$joystick.moveJoystick(paramInt, paramFloat);
      }
    };
  }
  
  public static Action createJoystickAction(Joystick paramJoystick, String paramString, Map<String, String> paramMap)
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
        if (paramString.equals("move_joystick"))
        {
          i = 0;
          continue;
          if (paramString.equals("move_sprint"))
          {
            i = 1;
            continue;
            if (paramString.equals("move_rotate"))
            {
              i = 2;
              continue;
              if (paramString.equals("move_turn")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    i = ParseUtil.parseInt((String)paramMap.get("move_joystick_angle"), 0);
    float f = ParseUtil.parseFloat((String)paramMap.get("move_joystick_percent_r"), 0.0F);
    return createJoystickActionBuilder().append(createDummyJoystickRunnable(paramJoystick, i, f), 0L).setCancelRunnable(createDummyJoystickRunnable(paramJoystick, 0, 0.0F)).build();
    i = ParseUtil.parseInt((String)paramMap.get("duration"), 1000);
    int j = ParseUtil.parseInt((String)paramMap.get("move_joystick_speed"), 255);
    return createJoystickActionBuilder().append(createDummyJoystickRunnable(paramJoystick, 90, j / 255), 0L).append(createDummyJoystickRunnable(paramJoystick, 0, 0.0F), i).setCancelRunnable(createDummyJoystickRunnable(paramJoystick, 0, 0.0F)).setName("move_sprint").build();
    i = ParseUtil.parseInt((String)paramMap.get("duration"), 2500);
    j = ParseUtil.parseInt((String)paramMap.get("move_joystick_speed"), 255);
    return createJoystickActionBuilder().append(createDummyJoystickRunnable(paramJoystick, 45, j / 255), 0L).append(createDummyJoystickRunnable(paramJoystick, 0, 0.0F), i).setCancelRunnable(createDummyJoystickRunnable(paramJoystick, 0, 0.0F)).setName("move_rotate").build();
    i = ParseUtil.parseInt((String)paramMap.get("duration"), 2000);
    j = ParseUtil.parseInt((String)paramMap.get("move_left_speed"), 100);
    ParseUtil.parseInt((String)paramMap.get("move_right_speed"), -100);
    return createJoystickActionBuilder().append(createDummyJoystickRunnable(paramJoystick, 0, Math.abs(j) / 255.0F), 0L).append(createDummyJoystickRunnable(paramJoystick, 0, 0.0F), i).setCancelRunnable(createDummyJoystickRunnable(paramJoystick, 0, 0.0F)).setName("move_turn").build();
  }
  
  private static ActionBuilder createJoystickActionBuilder()
  {
    return createActionWithActionType(ActionType.CONFLICT_MOTOR);
  }
  
  public static Action createLedOnBoardAction(LedOnBoard paramLedOnBoard, String paramString, Map<String, String> paramMap)
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
        if (paramString.equals("light_colorPicker"))
        {
          i = 0;
          continue;
          if (paramString.equals("light_gradient"))
          {
            i = 1;
            continue;
            if (paramString.equals("light_alarm"))
            {
              i = 2;
              continue;
              if (paramString.equals("light_random")) {
                i = 3;
              }
            }
          }
        }
        break;
      }
    }
    int k = Integer.parseInt((String)paramMap.get("light_colorPicker_color"));
    i = Color.red(k);
    int j = Color.green(k);
    k = Color.blue(k);
    return createLedOnBoardActionBuilder().append(createLedOnBoardRunnable(paramLedOnBoard, 0, i, j, k), 0L).setName("light_colorPicker").build();
    ParseUtil.parseInt((String)paramMap.get("light_gradient_color_index"), 0);
    return createLedOnBoardActionBuilder().append(createLedOnBoardRunnable(paramLedOnBoard, 0, 51, 0, 51), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 102, 51, 102), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 153, 51, 153), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 204, 51, 204), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 204, 102, 204), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 255, 153, 255), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 255, 204, 255), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 0, 0, 0), 1000L).setName("light_gradient").build();
    k = Color.parseColor((String)paramMap.get("light_alarm_color"));
    i = Color.red(k);
    j = Color.green(k);
    k = Color.blue(k);
    return createLedOnBoardActionBuilder().append(createLedOnBoardRunnable(paramLedOnBoard, 1, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, 0, 0, 0), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, 0, 0, 0), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, i, j, k), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, 0, 0, 0), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, 0, 0, 0), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, i, j, k), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, 0, 0, 0), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, 0, 0, 0), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, i, j, k), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, 0, 0, 0), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, 0, 0, 0), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, i, j, k), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, 0, 0, 0), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 1, 0, 0, 0), 500L).append(createLedOnBoardRunnable(paramLedOnBoard, 2, i, j, k), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 0, 0, 0), 500L).setName("light_alarm").build();
    paramString = new int[7];
    paramString[0] = Color.rgb(128, 0, 0);
    paramString[1] = Color.rgb(128, 64, 0);
    paramString[2] = Color.rgb(128, 128, 0);
    paramString[3] = Color.rgb(0, 128, 0);
    paramString[4] = Color.rgb(0, 128, 128);
    paramString[5] = Color.rgb(0, 0, 128);
    paramString[6] = Color.rgb(64, 0, 128);
    paramMap = ThreadLocalRandom.current();
    i = paramString.length - 1;
    while (i > 0)
    {
      j = paramMap.nextInt(i + 1);
      k = paramString[j];
      paramString[j] = paramString[i];
      paramString[i] = k;
      i -= 1;
    }
    return createLedOnBoardActionBuilder().append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[0]), Color.green(paramString[0]), Color.blue(paramString[0])), 0L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[1]), Color.green(paramString[1]), Color.blue(paramString[1])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[2]), Color.green(paramString[2]), Color.blue(paramString[2])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[3]), Color.green(paramString[3]), Color.blue(paramString[3])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[4]), Color.green(paramString[4]), Color.blue(paramString[4])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[5]), Color.green(paramString[5]), Color.blue(paramString[5])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, Color.red(paramString[6]), Color.green(paramString[6]), Color.blue(paramString[6])), 200L).append(createLedOnBoardRunnable(paramLedOnBoard, 0, 0, 0, 0), 200L).setName("light_random").build();
  }
  
  private static ActionBuilder createLedOnBoardActionBuilder()
  {
    return createActionWithActionType(ActionType.CONFLICT_LED_ON_BOARD);
  }
  
  private static Runnable createLedOnBoardRunnable(LedOnBoard paramLedOnBoard, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$ledOnBoard.ledOnBoard(paramInt1, paramInt2, paramInt3, paramInt4);
      }
    };
  }
  
  public static Action createLightSensorAction(LightSensor paramLightSensor, String paramString, Map<String, String> paramMap)
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
        throw new RuntimeException("未找到对应的type");
        if (paramString.equals("detector_light")) {
          i = 0;
        }
        break;
      }
    }
    i = ParseUtil.parseInt((String)paramMap.get("port"), 0);
    long l = ParseUtil.parseInt((String)paramMap.get("duration"), 500);
    return createLoopActionBuilder().append(createLightSensorRunnable(paramLightSensor, i), l).setName("detector_light").build();
  }
  
  private static Runnable createLightSensorRunnable(LightSensor paramLightSensor, final int paramInt)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$lightSensor.queryLightSensor(paramInt);
      }
    };
  }
  
  public static Action createLineFollowerSensorAction(LineFollowerSensor paramLineFollowerSensor, String paramString, Map<String, String> paramMap)
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
        throw new RuntimeException("未找到对应的type");
        if (paramString.equals("detector_line_follower")) {
          i = 0;
        }
        break;
      }
    }
    i = ParseUtil.parseInt((String)paramMap.get("port"), 0);
    long l = ParseUtil.parseInt((String)paramMap.get("duration"), 500);
    return createLoopActionBuilder().append(createLineFollowerSensorRunnable(paramLineFollowerSensor, i), l).setName("detector_line_follower").build();
  }
  
  private static Runnable createLineFollowerSensorRunnable(LineFollowerSensor paramLineFollowerSensor, final int paramInt)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$lineFollowerSensor.queryLineFollower(paramInt);
      }
    };
  }
  
  private static ActionBuilder createLoopActionBuilder()
  {
    return createActionWithActionType(ActionType.LOOP);
  }
  
  public static Action createUltrasonicSensorAction(UltrasonicSensor paramUltrasonicSensor, String paramString, Map<String, String> paramMap)
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
        throw new RuntimeException("未找到对应的type");
        if (paramString.equals("detector_distance")) {
          i = 0;
        }
        break;
      }
    }
    i = ParseUtil.parseInt((String)paramMap.get("port"), 0);
    long l = ParseUtil.parseInt((String)paramMap.get("duration"), 500);
    return createLoopActionBuilder().append(createUltrasonicSensorRunnable(paramUltrasonicSensor, i), l).setName("detector_distance").build();
  }
  
  private static Runnable createUltrasonicSensorRunnable(UltrasonicSensor paramUltrasonicSensor, final int paramInt)
  {
    new Runnable()
    {
      public void run()
      {
        this.val$ultrasonicSensor.queryUltrasonic(paramInt);
      }
    };
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\diy\DiyActionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */