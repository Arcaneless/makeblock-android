package cc.makeblock.makeblock.engine.net.data;

public class AndroidVerInfoData
{
  private DataBean data;
  
  public DataBean getData()
  {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean)
  {
    this.data = paramDataBean;
  }
  
  public static class DataBean
  {
    private String info;
    private int versionCode;
    private String versionName;
    
    public String getInfo()
    {
      return this.info;
    }
    
    public int getVersionCode()
    {
      return this.versionCode;
    }
    
    public String getVersionName()
    {
      return this.versionName;
    }
    
    public void setInfo(String paramString)
    {
      this.info = paramString;
    }
    
    public void setVersionCode(int paramInt)
    {
      this.versionCode = paramInt;
    }
    
    public void setVersionName(String paramString)
    {
      this.versionName = paramString;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\data\AndroidVerInfoData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */