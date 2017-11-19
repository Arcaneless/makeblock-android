package cc.makeblock.makeblock.engine.net.tutorial;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;

public class ProjectItemDataTypeAdapter
  extends TypeAdapter<TutorialInfo>
{
  public TutorialInfo read(JsonReader paramJsonReader)
    throws IOException
  {
    paramJsonReader.beginObject();
    TutorialInfo localTutorialInfo = new TutorialInfo();
    TutorialInfo.Data localData = new TutorialInfo.Data();
    TutorialInfo.Data.Devices localDevices = new TutorialInfo.Data.Devices();
    while (paramJsonReader.hasNext())
    {
      Object localObject = paramJsonReader.nextName();
      int i = -1;
      switch (((String)localObject).hashCode())
      {
      default: 
        switch (i)
        {
        default: 
          break;
        case 0: 
          paramJsonReader.beginObject();
        }
        break;
      case 3076010: 
        label68:
        while (paramJsonReader.hasNext())
        {
          localObject = paramJsonReader.nextName();
          i = -1;
          switch (((String)localObject).hashCode())
          {
          }
          for (;;)
          {
            switch (i)
            {
            default: 
              break;
            case 0: 
              localData.setVersion(paramJsonReader.nextDouble());
              break;
              if (!((String)localObject).equals("data")) {
                break label68;
              }
              i = 0;
              break label68;
              if (((String)localObject).equals("version"))
              {
                i = 0;
                continue;
                if (((String)localObject).equals("devices")) {
                  i = 1;
                }
              }
              break;
            }
          }
          paramJsonReader.beginObject();
          localObject = new HashMap();
          while (paramJsonReader.hasNext())
          {
            String str1 = paramJsonReader.nextName();
            TutorialInfo.Data.Devices.Device localDevice = new TutorialInfo.Data.Devices.Device();
            HashMap localHashMap = new HashMap();
            paramJsonReader.beginObject();
            while (paramJsonReader.hasNext())
            {
              String str2 = paramJsonReader.nextName();
              TutorialInfo.Data.Devices.Device.LanguageUnitData localLanguageUnitData = new TutorialInfo.Data.Devices.Device.LanguageUnitData();
              paramJsonReader.beginObject();
              while (paramJsonReader.hasNext())
              {
                String str3 = paramJsonReader.nextName();
                i = -1;
                switch (str3.hashCode())
                {
                }
                for (;;)
                {
                  switch (i)
                  {
                  default: 
                    break;
                  case 0: 
                    localLanguageUnitData.setTitle(paramJsonReader.nextString());
                    break;
                    if (str3.equals("title"))
                    {
                      i = 0;
                      continue;
                      if (str3.equals("image"))
                      {
                        i = 1;
                        continue;
                        if (str3.equals("link"))
                        {
                          i = 2;
                          continue;
                          if (str3.equals("show")) {
                            i = 3;
                          }
                        }
                      }
                    }
                    break;
                  }
                }
                localLanguageUnitData.setImage(paramJsonReader.nextString());
                continue;
                localLanguageUnitData.setLink(paramJsonReader.nextString());
                continue;
                localLanguageUnitData.setShow(paramJsonReader.nextDouble());
              }
              paramJsonReader.endObject();
              localHashMap.put(str2, localLanguageUnitData);
            }
            paramJsonReader.endObject();
            localDevice.setLanguageUnitDataMap(localHashMap);
            ((HashMap)localObject).put(str1, localDevice);
          }
          paramJsonReader.endObject();
          localDevices.setDeviceMap((HashMap)localObject);
          localData.setDevices(localDevices);
          localTutorialInfo.setData(localData);
        }
      }
    }
    return localTutorialInfo;
  }
  
  public void write(JsonWriter paramJsonWriter, TutorialInfo paramTutorialInfo)
    throws IOException
  {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\net\tutorial\ProjectItemDataTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */