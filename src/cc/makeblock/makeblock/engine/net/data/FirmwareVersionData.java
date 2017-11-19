package cc.makeblock.makeblock.engine.net.data;

import java.util.List;

public class FirmwareVersionData
{
  private List<DataBean> data;
  
  public List<DataBean> getData()
  {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList)
  {
    this.data = paramList;
  }
  
  public static class DataBean
  {
    private int deviceNumber;
    private String name;
    private int protocol;
    private int version;
    
    public int getDeviceNumber()
    {
      return this.deviceNumber;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public int getProtocol()
    {
      return this.protocol;
    }
    
    public int getVersion()
    {
      return this.version;
    }
    
    public void setDeviceNumber(int paramInt)
    {
      this.deviceNumber = paramInt;
    }
    
    public void setName(String paramString)
    {
      this.name = paramString;
    }
    
    public void setProtocol(int paramInt)
    {
      this.protocol = paramInt;
    }
    
    public void setVersion(int paramInt)
    {
      this.version = paramInt;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\data\FirmwareVersionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */