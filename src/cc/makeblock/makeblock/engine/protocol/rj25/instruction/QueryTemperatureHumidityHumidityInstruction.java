package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryTemperatureHumidityHumidityInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 23;
  private static final int length = 5;
  private final byte port;
  private byte type;
  
  public QueryTemperatureHumidityHumidityInstruction(byte paramByte)
  {
    this.port = paramByte;
    this.type = 0;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)52);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)23);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.type);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",温湿度,port:" + this.port + ",type:" + this.type;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryTemperatureHumidityHumidityInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */