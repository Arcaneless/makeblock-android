package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class RJ25Instruction
  extends Instruction
{
  public static final byte[] Head = { -1, 85 };
  public static final byte INDEX_BUZZER = 1;
  public static final byte INDEX_DC_MOTOR = 5;
  public static final byte INDEX_DIGITAL_PIN = 22;
  public static final byte INDEX_DIGITAL_RUDDER = 19;
  public static final byte INDEX_DIGITAL_RUDDER2 = 24;
  public static final byte INDEX_DIGITAL_TUBE = 20;
  public static final byte INDEX_DUNMMY_JOYSTICK_2 = 56;
  public static final byte INDEX_ELECTRONIC_COMPASS = 36;
  public static final byte INDEX_FACE = 51;
  public static final byte INDEX_FLAME = 38;
  public static final byte INDEX_GAS = 39;
  public static final byte INDEX_GESTURE = 18;
  public static final byte INDEX_HUNTING_LINE = 34;
  public static final byte INDEX_JOYSTICK = 2;
  public static final byte INDEX_LED = 3;
  public static final byte INDEX_LIGHT = 9;
  public static final byte INDEX_LIMITING_STOPPER = 35;
  public static final byte INDEX_POTENTIOMETER = 16;
  public static final byte INDEX_PWM = 23;
  public static final byte INDEX_QUERY_BATTERY_LIFE = -121;
  public static final byte INDEX_QUERY_BOARD_KEY_STATUS = 49;
  public static final byte INDEX_QUERY_DEVICE_RUNTIME = 41;
  public static final byte INDEX_QUERY_DIGITAL_PIN = 40;
  public static final byte INDEX_QUERY_INFRARED_ACCEPT = 54;
  public static final byte INDEX_QUERY_INFRARED_TRANSMITTING = 53;
  public static final byte INDEX_QUERY_KEY_STATUS = 50;
  public static final byte INDEX_QUERY_THERMOELECTRICAL_INFRARED = 55;
  public static final byte INDEX_QUERY_TOUCH_STATUS = 48;
  public static final byte INDEX_RESET_DEVICE_RUNTIME = 25;
  public static final byte INDEX_RESET_SMART_SERVO = 57;
  public static final byte INDEX_ROCKER = 17;
  public static final byte INDEX_SET_FORM = 4;
  public static final byte INDEX_SET_MODE = 6;
  public static final byte INDEX_SHUTTER_RELEASE = 21;
  public static final byte INDEX_TEMPERATURE = 8;
  public static final byte INDEX_TEMPERATURE_HUMIDITY_HUMIDITY = 52;
  public static final byte INDEX_TEMPERATURE_HUMIDITY_TEMPERATURE = 37;
  public static final byte INDEX_ULTRASONIC = 7;
  public static final byte INDEX_UNLOCK_SMART_SERVO = 64;
  public static final byte INDEX_VOLUME = 32;
  protected static final byte MODE_READ = 1;
  protected static final byte MODE_WRITE = 2;
  
  protected final ByteBuffer getByteBuffer(int paramInt)
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(Head.length + paramInt + 1);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    localByteBuffer.put(Head);
    localByteBuffer.put((byte)paramInt);
    return localByteBuffer;
  }
  
  public String toString()
  {
    return "RJ25指令";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\RJ25Instruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */