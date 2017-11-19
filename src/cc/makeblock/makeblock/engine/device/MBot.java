package cc.makeblock.makeblock.engine.device;

import android.os.Handler;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.common.MakeBlockDevice;
import cc.makeblock.makeblock.engine.device.operation.Buzzer;
import cc.makeblock.makeblock.engine.device.operation.Joystick;
import cc.makeblock.makeblock.engine.device.operation.LedOnBoard;
import cc.makeblock.makeblock.engine.device.operation.LightSensor;
import cc.makeblock.makeblock.engine.device.operation.LineFollowerSensor;
import cc.makeblock.makeblock.engine.device.operation.UltrasonicSensor;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTempo;
import cc.makeblock.makeblock.engine.protocol.rj25.BuzzerTone;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.BuzzerInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.JoystickInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.LedInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.QueryLightInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.QueryLineFollowerInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.QueryUltrasonicInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.LightRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.LineFollowerRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.UltrasonicRespond;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MBot
  extends MakeBlockDevice
  implements Joystick, LedOnBoard, Buzzer, LineFollowerSensor, UltrasonicSensor, LightSensor
{
  public static final DeviceBoardName DEVICE_BOARD_NAME = DeviceBoardName.mcore;
  private List<MBotStatusAdapter> mBotStatusAdapters = new ArrayList();
  
  public MBot(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
  }
  
  public void buzz()
  {
    buzz(BuzzerTone.C5, new BuzzerTempo(1000).ONE_OF_FOUR);
  }
  
  public void buzz(BuzzerTone paramBuzzerTone, short paramShort)
  {
    sendData(new BuzzerInstruction(paramBuzzerTone, paramShort).getBytes());
  }
  
  public boolean equals(Object paramObject)
  {
    if (super.equals(paramObject)) {}
    while ((paramObject instanceof MBot)) {
      return true;
    }
    return false;
  }
  
  public DeviceBoardName getBoardName()
  {
    return DEVICE_BOARD_NAME;
  }
  
  public String getDeviceName()
  {
    return DEVICE_BOARD_NAME.getDeviceName();
  }
  
  protected void handleRJ25Respond(RJ25Respond paramRJ25Respond)
  {
    Iterator localIterator = new ArrayList(this.mBotStatusAdapters).iterator();
    while (localIterator.hasNext())
    {
      MBotStatusAdapter localMBotStatusAdapter = (MBotStatusAdapter)localIterator.next();
      if ((paramRJ25Respond instanceof UltrasonicRespond)) {
        localMBotStatusAdapter.onUltrasonicSensorReply(((UltrasonicRespond)paramRJ25Respond).distance);
      } else if ((paramRJ25Respond instanceof LineFollowerRespond)) {
        localMBotStatusAdapter.onLineFollowerSensorReply(((LineFollowerRespond)paramRJ25Respond).line);
      } else if ((paramRJ25Respond instanceof LightRespond)) {
        localMBotStatusAdapter.onLightSensorReply(((LightRespond)paramRJ25Respond).light);
      }
    }
  }
  
  public void ledOnBoard(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    sendData(new LedInstruction((byte)7, (byte)2, (byte)paramInt1, (byte)paramInt2, (byte)paramInt3, (byte)paramInt4).getBytes());
  }
  
  public void moveJoystick(int paramInt, float paramFloat)
  {
    int k = 0;
    int m = 0;
    int i;
    int j;
    if ((paramInt >= 0) && (paramInt <= 90))
    {
      i = 255;
      j = paramInt * 17 / 3 - 255;
    }
    for (;;)
    {
      if (paramFloat == 0.0F)
      {
        i = 0;
        j = 0;
      }
      moveJoystick((int)(i * paramFloat), (int)(j * paramFloat));
      return;
      if ((paramInt > 90) && (paramInt <= 180))
      {
        i = 765 - paramInt * 17 / 3;
        j = 255;
      }
      else if ((paramInt > -90) && (paramInt < 0))
      {
        i = 65281;
        j = paramInt * 17 / 3 + 255;
      }
      else
      {
        i = k;
        j = m;
        if (paramInt > 65356)
        {
          i = k;
          j = m;
          if (paramInt <= -90)
          {
            i = -paramInt * 17 / 3 - 765;
            j = 65281;
          }
        }
      }
    }
  }
  
  public void moveJoystick(int paramInt1, int paramInt2)
  {
    sendData(new JoystickInstruction((short)-paramInt1, (short)paramInt2).getBytes());
  }
  
  public void playStartAction()
  {
    ActionBuilder localActionBuilder = new ActionBuilder();
    int i = 0;
    if (i < 3)
    {
      Runnable local6 = new Runnable()
      {
        public void run()
        {
          MBot.this.ledOnBoard(0, 0, 0, 255);
        }
      };
      if (i == 0) {}
      for (long l = 0L;; l = 50L)
      {
        localActionBuilder.append(local6, l).append(new Runnable()
        {
          public void run()
          {
            MBot.this.moveJoystick(65281, 255);
          }
        }, 50L).append(new Runnable()
        {
          public void run()
          {
            MBot.this.moveJoystick(0, 0);
          }
        }, 100L).append(new Runnable()
        {
          public void run()
          {
            MBot.this.moveJoystick(255, 65281);
          }
        }, 50L).append(new Runnable()
        {
          public void run()
          {
            MBot.this.moveJoystick(0, 0);
          }
        }, 110L).append(new Runnable()
        {
          public void run()
          {
            MBot.this.ledOnBoard(0, 0, 0, 0);
          }
        }, 50L);
        i += 1;
        break;
      }
    }
    localActionBuilder.build().execute();
  }
  
  public void playTune(BuzzerTone paramBuzzerTone)
  {
    buzz(paramBuzzerTone, new BuzzerTempo(1000).ONE_OF_FOUR);
  }
  
  public void queryLightSensor(int paramInt)
  {
    sendData(new QueryLightInstruction((byte)paramInt).getBytes());
  }
  
  public void queryLineFollower(int paramInt)
  {
    sendData(new QueryLineFollowerInstruction((byte)paramInt).getBytes());
  }
  
  public void queryUltrasonic(int paramInt)
  {
    sendData(new QueryUltrasonicInstruction((byte)paramInt).getBytes());
  }
  
  public void registerMBotStatusAdapter(MBotStatusAdapter paramMBotStatusAdapter)
  {
    this.mBotStatusAdapters.add(paramMBotStatusAdapter);
  }
  
  public void removeMBotStatusAdapter(MBotStatusAdapter paramMBotStatusAdapter)
  {
    this.mBotStatusAdapters.remove(paramMBotStatusAdapter);
  }
  
  public void reset()
  {
    stop();
    turnOffLight();
  }
  
  public void stop()
  {
    moveJoystick(0, 0);
  }
  
  public void turnOffLight()
  {
    ledOnBoard(0, 0, 0, 0);
  }
  
  public void turnOnLight()
  {
    ledOnBoard(0, 100, 100, 100);
  }
  
  public static class MBotStatusAdapter
  {
    public void onLightSensorReply(float paramFloat) {}
    
    public void onLineFollowerSensorReply(float paramFloat) {}
    
    public void onUltrasonicSensorReply(float paramFloat) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\MBot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */