package cc.makeblock.makeblock.engine.protocol.rj25;

import cc.makeblock.makeblock.engine.protocol.base.RespondParser;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.RJ25Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.BoardButtonRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.ButtonRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.CompassRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.DeviceRuntimeRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.DigitalPinRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.FirmwareRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.FlameRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.GasRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.GestureRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.InfraredAcceptRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.InfraredTransmittingRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.JoyStickRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.LightRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.LimitingStopperRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.LineFollowerRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.PotentiometerRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25FormRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.TemperatureHumidityHumidityRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.TemperatureHumidityTemperatureRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.TemperatureRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.ThermoelectricalInfraredRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.TouchRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.UltrasonicRespond;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.VolumeRespond;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RJ25RespondParser
  extends RespondParser
{
  private static final byte[] HEAD = RJ25Instruction.Head;
  private static final byte[] TAIL = { 13, 10 };
  private OnRespondReceiveListener onRespondReceiveListener;
  
  public RJ25RespondParser()
  {
    super(HEAD, TAIL);
  }
  
  private static boolean assertFirmwareValid(String paramString)
  {
    try
    {
      paramString = new Scanner(paramString).useDelimiter("\\.");
      paramString.nextInt(16);
      paramString.nextInt(16);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static String decodeFirmwareVersion(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte[4];
    String str2 = "";
    int i = 0;
    String str1;
    for (;;)
    {
      str1 = str2;
      if (i < j) {
        try
        {
          str2 = str2 + String.format("%c", new Object[] { Byte.valueOf(paramArrayOfByte[(i + 5)]) });
          i += 1;
        }
        catch (Exception paramArrayOfByte)
        {
          str1 = "";
        }
      }
    }
    paramArrayOfByte = str1;
    if (!assertFirmwareValid(str1)) {
      paramArrayOfByte = "";
    }
    return paramArrayOfByte;
  }
  
  private static float decodeFloatReading(byte[] paramArrayOfByte)
  {
    Object localObject = new ArrayList();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      ((List)localObject).add(Byte.valueOf(paramArrayOfByte[i]));
      i += 1;
    }
    paramArrayOfByte = new int[((List)localObject).size()];
    localObject = (Byte[])((List)localObject).toArray(new Byte[((List)localObject).size()]);
    i = 0;
    while (i < localObject.length)
    {
      paramArrayOfByte[i] = localObject[i].byteValue();
      i += 1;
    }
    float f = 0.0F;
    if (paramArrayOfByte.length >= 7)
    {
      f = 0.0F;
      if (paramArrayOfByte[3] != 2) {
        break label171;
      }
      if (paramArrayOfByte.length > 7) {
        f = Float.intBitsToFloat((paramArrayOfByte[4] & 0xFF) + ((paramArrayOfByte[5] & 0xFF) << 8) + ((paramArrayOfByte[6] & 0xFF) << 16) + ((paramArrayOfByte[7] & 0xFF) << 24));
      }
    }
    label171:
    do
    {
      return f;
      if (paramArrayOfByte[3] == 1) {
        return paramArrayOfByte[4] & 0xFF;
      }
    } while (paramArrayOfByte[3] != 3);
    return (paramArrayOfByte[4] & 0xFF) + ((paramArrayOfByte[5] & 0xFF) << 8);
  }
  
  protected void packData(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    Object localObject1;
    switch (paramArrayOfByte[2])
    {
    default: 
      localObject1 = localObject2;
    }
    for (;;)
    {
      if ((this.onRespondReceiveListener != null) && (localObject1 != null)) {
        this.onRespondReceiveListener.onInstructionReceive((RJ25Respond)localObject1);
      }
      return;
      localObject1 = localObject2;
      if (paramArrayOfByte.length >= 4)
      {
        localObject1 = localObject2;
        if (paramArrayOfByte[3] == 4)
        {
          paramArrayOfByte = decodeFirmwareVersion(paramArrayOfByte);
          localObject1 = localObject2;
          if (paramArrayOfByte != null)
          {
            localObject1 = localObject2;
            if (!paramArrayOfByte.isEmpty())
            {
              localObject1 = new FirmwareRespond(paramArrayOfByte);
              continue;
              localObject1 = localObject2;
              if (paramArrayOfByte[3] == 1)
              {
                localObject1 = new RJ25FormRespond(paramArrayOfByte[4]);
                continue;
                ByteBuffer.wrap(paramArrayOfByte, 4, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
                localObject1 = localObject2;
                continue;
                localObject1 = new UltrasonicRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new TemperatureRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new LightRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new PotentiometerRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new JoyStickRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new GestureRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new VolumeRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new LineFollowerRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new LimitingStopperRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new CompassRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new TemperatureHumidityTemperatureRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new TemperatureHumidityHumidityRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new FlameRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new GasRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new DigitalPinRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new DeviceRuntimeRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new TouchRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new BoardButtonRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new ButtonRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new InfraredTransmittingRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new InfraredAcceptRespond(decodeFloatReading(paramArrayOfByte));
                continue;
                localObject1 = new ThermoelectricalInfraredRespond(decodeFloatReading(paramArrayOfByte));
              }
            }
          }
        }
      }
    }
  }
  
  public void setOnRespondReceiveListener(OnRespondReceiveListener paramOnRespondReceiveListener)
  {
    this.onRespondReceiveListener = paramOnRespondReceiveListener;
  }
  
  public static abstract interface OnRespondReceiveListener
  {
    public abstract void onInstructionReceive(RJ25Respond paramRJ25Respond);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\RJ25RespondParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */