package cc.makeblock.makeblock.engine.protocol.neuron;

import java.nio.ByteBuffer;

public class AirBlockControlWordListInstruction
  extends AirBlockInstruction
{
  public static final byte WORD_BACKWARD = 1;
  public static final byte WORD_BALANCE = 10;
  public static final byte WORD_DOWN = 5;
  public static final byte WORD_FORWARD = 0;
  public static final byte WORD_FREE = 30;
  public static final byte WORD_GROUP = 12;
  public static final byte WORD_HOVER = 9;
  public static final byte WORD_LEFT = 2;
  public static final byte WORD_RIGHT = 3;
  public static final byte WORD_ROLL = 11;
  public static final byte WORD_ROTATE = 6;
  public static final byte WORD_SHAKE = 13;
  public static final byte WORD_UP = 4;
  private static final byte cmd = 26;
  private final float data1;
  private final float data2;
  private final float data3;
  private final float data4;
  private final float data5;
  private final float data6;
  private final byte word;
  
  public AirBlockControlWordListInstruction(byte paramByte, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.word = paramByte;
    this.data1 = paramFloat1;
    this.data2 = paramFloat2;
    this.data3 = paramFloat3;
    this.data4 = paramFloat4;
    this.data5 = paramFloat5;
    this.data6 = paramFloat6;
  }
  
  protected int getLength()
  {
    return 32;
  }
  
  protected void putData(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, 26));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(1, this.word));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data1)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data2)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data3)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data4)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data5)));
    paramByteBuffer.put(NeuronByteUtil.convert8to7(6, parseToIntBit(this.data6)));
  }
  
  public String toString()
  {
    return "AirBlock控制字队列指令,控制字:" + this.word + ", data1:" + this.data1 + "data2:" + this.data2 + ", data3:" + this.data3 + "data4:" + this.data4 + ", data5:" + this.data5 + "data6:" + this.data6;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\neuron\AirBlockControlWordListInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */