package cc.makeblock.makeblock.engine.protocol.rj25.instruction;

import java.nio.ByteBuffer;

public class QueryButtonStatusInstruction
  extends RJ25Instruction
{
  public static final byte DEVICE_INSTRUCTION = 22;
  private static final int length = 5;
  private final Key key;
  private final byte port;
  
  public QueryButtonStatusInstruction(byte paramByte, Key paramKey)
  {
    this.port = paramByte;
    this.key = paramKey;
  }
  
  public byte[] getBytes()
  {
    ByteBuffer localByteBuffer = getByteBuffer(5);
    localByteBuffer.put((byte)50);
    localByteBuffer.put((byte)1);
    localByteBuffer.put((byte)22);
    localByteBuffer.put(this.port);
    localByteBuffer.put(this.key.value);
    return localByteBuffer.array();
  }
  
  public String toString()
  {
    return super.toString() + ",按键状态,port:" + this.port + ",key:" + this.key;
  }
  
  public static enum Key
  {
    Key_1(1),  Key_2(2),  Key_3(3),  Key_4(4);
    
    public byte value;
    
    private Key(int paramInt)
    {
      this.value = ((byte)paramInt);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\rj25\instruction\QueryButtonStatusInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */