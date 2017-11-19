package cc.makeblock.makeblock.engine.net.tutorial;

import java.util.HashMap;

public class TutorialInfo
{
  private Data data;
  
  public Data getData()
  {
    return this.data;
  }
  
  public void setData(Data paramData)
  {
    this.data = paramData;
  }
  
  public static class Data
  {
    private Devices devices;
    private double version;
    
    public Devices getDevices()
    {
      return this.devices;
    }
    
    public double getVersion()
    {
      return this.version;
    }
    
    public void setDevices(Devices paramDevices)
    {
      this.devices = paramDevices;
    }
    
    public void setVersion(double paramDouble)
    {
      this.version = paramDouble;
    }
    
    public static class Devices
    {
      private HashMap<String, Device> deviceMap;
      
      public HashMap<String, Device> getDeviceMap()
      {
        return this.deviceMap;
      }
      
      void setDeviceMap(HashMap<String, Device> paramHashMap)
      {
        this.deviceMap = paramHashMap;
      }
      
      public static class Device
      {
        private HashMap<String, LanguageUnitData> languageUnitDataMap;
        
        public HashMap<String, LanguageUnitData> getLanguageUnitDataMap()
        {
          return this.languageUnitDataMap;
        }
        
        void setLanguageUnitDataMap(HashMap<String, LanguageUnitData> paramHashMap)
        {
          this.languageUnitDataMap = paramHashMap;
        }
        
        public static class LanguageUnitData
        {
          private String image;
          private String link;
          private double show;
          private String title;
          
          public String getImage()
          {
            return this.image;
          }
          
          public String getLink()
          {
            return this.link;
          }
          
          public double getShow()
          {
            return this.show;
          }
          
          public String getTitle()
          {
            return this.title;
          }
          
          public void setImage(String paramString)
          {
            this.image = paramString;
          }
          
          public void setLink(String paramString)
          {
            this.link = paramString;
          }
          
          public void setShow(double paramDouble)
          {
            this.show = paramDouble;
          }
          
          public void setTitle(String paramString)
          {
            this.title = paramString;
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\TutorialInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */