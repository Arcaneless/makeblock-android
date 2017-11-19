package cc.makeblock.makeblock.engine;

import java.util.Arrays;

public class Command
{
  private static final String TAG = Command.class.getSimpleName();
  public byte[] bytes;
  public int priorityLevel;
  
  public Command(Builder paramBuilder)
  {
    this.bytes = paramBuilder.command;
    this.priorityLevel = paramBuilder.priorityLevel;
  }
  
  public String toString()
  {
    return "Command{bytes=" + Arrays.toString(this.bytes) + '}';
  }
  
  public static class Builder
  {
    private byte[] command = new byte[0];
    private int priorityLevel;
    
    private byte[] joinBytes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    {
      byte[] arrayOfByte = new byte[paramArrayOfByte1.length + paramArrayOfByte2.length];
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte, 0, paramArrayOfByte1.length);
      System.arraycopy(paramArrayOfByte2, 0, arrayOfByte, paramArrayOfByte1.length, paramArrayOfByte2.length);
      return arrayOfByte;
    }
    
    public Builder appendCommand(byte[] paramArrayOfByte)
    {
      this.command = joinBytes(this.command, paramArrayOfByte);
      return this;
    }
    
    public Command build()
    {
      return new Command(this);
    }
    
    public Builder setPriority(int paramInt)
    {
      this.priorityLevel = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\Command.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */