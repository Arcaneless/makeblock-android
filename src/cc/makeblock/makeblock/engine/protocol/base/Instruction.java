package cc.makeblock.makeblock.engine.protocol.base;

import java.util.Arrays;

public abstract class Instruction
{
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof Instruction)) {}
    for (paramObject = ((Instruction)paramObject).getBytes();; paramObject = (byte[])paramObject)
    {
      return Arrays.equals(getBytes(), (byte[])paramObject);
      if (!(paramObject instanceof byte[])) {
        break;
      }
    }
    return false;
  }
  
  public abstract byte[] getBytes();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\base\Instruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */