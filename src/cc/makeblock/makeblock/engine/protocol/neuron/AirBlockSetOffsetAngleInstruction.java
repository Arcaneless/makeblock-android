package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockSetOffsetAngleInstruction
  extends AirBlockInstruction
{
  private static final byte cmd = 41;
  private final float angle1;
  private final float angle2;
  private final float angle3;
  
  public AirBlockSetOffsetAngleInstruction(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.angle1 = paramFloat1;
    this.angle2 = paramFloat2;
    this.angle3 = paramFloat3;
  }
  
  protected int getLength()
  {
    return 16;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 41));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.angle1)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.angle2)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.angle3)));
  }
  
  public String toString()
  {
    return "AirBlock请求偏转角度";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockSetOffsetAngleInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */