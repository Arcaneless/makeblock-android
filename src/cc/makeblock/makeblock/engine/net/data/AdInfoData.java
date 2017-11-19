package cc.makeblock.makeblock.engine.net.data;

public class AdInfoData
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
    private String imgUrl;
    private String imgUrlAndroidCn;
    private String imgUrlAndroidEn;
    private String imgUrlCn;
    private String link;
    private String linkAndroidCn;
    private String linkAndroidEn;
    private String linkCn;
    private int show;
    private int version;
    
    public String getImgUrl()
    {
      return this.imgUrl;
    }
    
    public String getImgUrlAndroidCn()
    {
      return this.imgUrlAndroidCn;
    }
    
    public String getImgUrlAndroidEn()
    {
      return this.imgUrlAndroidEn;
    }
    
    public String getImgUrlCn()
    {
      return this.imgUrlCn;
    }
    
    public String getLink()
    {
      return this.link;
    }
    
    public String getLinkAndroidCn()
    {
      return this.linkAndroidCn;
    }
    
    public String getLinkAndroidEn()
    {
      return this.linkAndroidEn;
    }
    
    public String getLinkCn()
    {
      return this.linkCn;
    }
    
    public int getShow()
    {
      return this.show;
    }
    
    public int getVersion()
    {
      return this.version;
    }
    
    public void setImgUrl(String paramString)
    {
      this.imgUrl = paramString;
    }
    
    public void setImgUrlAndroidCn(String paramString)
    {
      this.imgUrlAndroidCn = paramString;
    }
    
    public void setImgUrlAndroidEn(String paramString)
    {
      this.imgUrlAndroidEn = paramString;
    }
    
    public void setImgUrlCn(String paramString)
    {
      this.imgUrlCn = paramString;
    }
    
    public void setLink(String paramString)
    {
      this.link = paramString;
    }
    
    public void setLinkAndroidCn(String paramString)
    {
      this.linkAndroidCn = paramString;
    }
    
    public void setLinkAndroidEn(String paramString)
    {
      this.linkAndroidEn = paramString;
    }
    
    public void setLinkCn(String paramString)
    {
      this.linkCn = paramString;
    }
    
    public void setShow(int paramInt)
    {
      this.show = paramInt;
    }
    
    public void setVersion(int paramInt)
    {
      this.version = paramInt;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\data\AdInfoData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */