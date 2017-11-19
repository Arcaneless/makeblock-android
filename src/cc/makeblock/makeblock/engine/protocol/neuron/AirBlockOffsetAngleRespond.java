package cc.makeblock.makeblock.engine.protocol.neuron;

public class AirBlockOffsetAngleRespond
  extends NeuronRespond
{
  public static final byte cmd = 44;
  public final float angle1;
  public final float angle2;
  public final float angle3;
  
  public AirBlockOffsetAngleRespond(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.angle1 = paramFloat1;
    this.angle2 = paramFloat2;
    this.angle3 = paramFloat3;
  }
  
  public String toString()
  {
    return super.toString() + ": 俯仰角：" + this.angle1 + " 横滚角：" + this.angle2 + " 偏航角：" + this.angle3;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockOffsetAngleRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */