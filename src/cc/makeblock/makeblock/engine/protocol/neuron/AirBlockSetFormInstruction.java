package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockSetFormInstruction
  extends AirBlockInstruction
{
  public static final byte FORM_AIR = 2;
  public static final byte FORM_CAR = 1;
  public static final byte FORM_CAR_DIY = 3;
  public static final byte FORM_DIY = 5;
  public static final byte FORM_SHIP = 6;
  public static final byte FORM_TEST = 112;
  private static final byte cmd = 14;
  private final byte form;
  
  public AirBlockSetFormInstruction(byte paramByte)
  {
    this.form = paramByte;
  }
  
  protected int getLength()
  {
    return 2;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 14));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, this.form));
  }
  
  public String toString()
  {
    String str = null;
    switch (this.form)
    {
    }
    for (;;)
    {
      return "设置AirBlock模式命令(" + str + ")";
      str = "气垫车";
      continue;
      str = "无人机";
      continue;
      str = "气垫车DIY";
      continue;
      str = "气垫船";
      continue;
      str = "DIY";
      continue;
      str = "测试";
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockSetFormInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */