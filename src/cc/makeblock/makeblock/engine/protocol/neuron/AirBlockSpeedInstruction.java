package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockSpeedInstruction
  extends AirBlockInstruction
{
  private static final byte cmd = 38;
  private final short speed1;
  private final short speed2;
  private final short speed3;
  private final short speed4;
  
  public AirBlockSpeedInstruction(short paramShort1, short paramShort2, short paramShort3, short paramShort4)
  {
    this.speed1 = paramShort1;
    this.speed2 = paramShort2;
    this.speed3 = paramShort3;
    this.speed4 = paramShort4;
  }
  
  protected int getLength()
  {
    return 9;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 38));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(3, this.speed1));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(3, this.speed2));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(3, this.speed3));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(3, this.speed4));
  }
  
  public String toString()
  {
    return "AirBlock设置油门值: speed1:" + this.speed1 + ", speed2:" + this.speed2 + ", speed3:" + this.speed3 + ", speed4:" + this.speed4;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockSpeedInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */