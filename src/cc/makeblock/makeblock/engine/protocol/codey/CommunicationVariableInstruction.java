package cc.makeblock.makeblock.engine.protocol.codey;

import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CommunicationVariableInstruction
  extends CodeyInstruction
{
  private static final byte STOP_CHARACTER = 0;
  private static final byte TYPE_BOOLEAN = 11;
  private static final byte TYPE_CHAR = 1;
  private static final byte TYPE_FLOAT = 9;
  private static final byte TYPE_INT = 5;
  private static final byte TYPE_STRING = 12;
  private byte[] nameBytes;
  private byte type = 0;
  private Object value;
  private int valueLength = 0;
  
  public CommunicationVariableInstruction(String paramString, Object paramObject)
  {
    this.value = paramObject;
    this.nameBytes = CodeyByteUtil.stringToByteArray(paramString);
    if ((paramObject instanceof Float))
    {
      this.type = 9;
      this.valueLength = 4;
    }
    do
    {
      return;
      if ((paramObject instanceof Integer))
      {
        this.type = 5;
        this.valueLength = 4;
        return;
      }
      if ((paramObject instanceof Boolean))
      {
        this.type = 11;
        this.valueLength = 1;
        return;
      }
      if ((paramObject instanceof String))
      {
        this.type = 12;
        this.valueLength = CodeyByteUtil.stringToByteArray(paramObject.toString()).length;
        return;
      }
    } while (!(paramObject instanceof Character));
    this.type = 1;
    this.valueLength = 1;
  }
  
  protected byte[] getData()
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(getLength());
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN).put((byte)2).put(this.nameBytes).put((byte)0).put(this.type);
    switch (this.type)
    {
    }
    for (;;)
    {
      return localByteBuffer.array();
      localByteBuffer.putInt(Float.floatToIntBits(((Float)this.value).floatValue()));
      continue;
      if (((Boolean)this.value).booleanValue()) {}
      for (int i = 1;; i = 0)
      {
        localByteBuffer.put((byte)i);
        break;
      }
      localByteBuffer.put(CodeyByteUtil.stringToByteArray(this.value.toString()));
      continue;
      localByteBuffer.putInt(((Integer)this.value).intValue());
      continue;
      localByteBuffer.put(((Byte)this.value).byteValue());
    }
  }
  
  protected int getLength()
  {
    return this.nameBytes.length + this.valueLength + 3;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\CommunicationVariableInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */