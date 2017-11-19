package cc.makeblock.makeblock.engine.device.common;

import android.os.Handler;
import android.text.TextUtils;
import cc.makeblock.makeblock.engine.bluetooth.BleData;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25RespondParser;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25RespondParser.OnRespondReceiveListener;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.QueryFirmwareInstruction;
import cc.makeblock.makeblock.engine.protocol.rj25.respond.RJ25Respond;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MakeBlockDevice
  extends Device
{
  protected static final String UUID_NOTIFY = "0000ffe2-0000-1000-8000-00805f9b34fb";
  protected static final String UUID_SERVICE = "0000ffe1-0000-1000-8000-00805f9b34fb";
  protected static final String UUID_WRITE = "0000ffe3-0000-1000-8000-00805f9b34fb";
  private String firmwareVersion;
  private List<MakeBlockStatusAdapter> makeBlockStatusAdapters = new ArrayList();
  private final RJ25RespondParser.OnRespondReceiveListener onRespondReceiveListener = new RJ25RespondParser.OnRespondReceiveListener()
  {
    public void onInstructionReceive(RJ25Respond paramAnonymousRJ25Respond)
    {
      MakeBlockDevice.this.handleRJ25Respond(paramAnonymousRJ25Respond);
    }
  };
  private final RJ25RespondParser rj25RespondParser = new RJ25RespondParser();
  
  public MakeBlockDevice(BleManager paramBleManager, Handler paramHandler)
  {
    super(paramBleManager, paramHandler);
    this.rj25RespondParser.setOnRespondReceiveListener(this.onRespondReceiveListener);
  }
  
  public static DeviceBoardName getBoardName(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return DeviceBoardName.unknown;
    }
    paramString = paramString.split("\\.");
    if (paramString.length == 0) {
      return DeviceBoardName.unknown;
    }
    for (;;)
    {
      int i;
      try
      {
        i = Integer.valueOf(paramString[0].trim(), 16).intValue();
      }
      catch (Exception paramString)
      {
        return DeviceBoardName.unknown;
      }
      paramString = DeviceBoardName.unknown;
      break;
      paramString = DeviceBoardName.orion;
      break label217;
      paramString = DeviceBoardName.megaPi;
      break label219;
      paramString = DeviceBoardName.mcore;
      break label221;
      paramString = DeviceBoardName.auriga;
      break label223;
      paramString = DeviceBoardName.crystal;
      break label225;
      return DeviceBoardName.octopus;
      paramString = DeviceBoardName.codey;
      return paramString;
      switch (i)
      {
      }
    }
    return paramString;
    label217:
    return paramString;
    label219:
    return paramString;
    label221:
    return paramString;
    label223:
    return paramString;
    label225:
    return paramString;
    return paramString;
  }
  
  public abstract DeviceBoardName getBoardName();
  
  public abstract String getDeviceName();
  
  public String getFirmwareVersion()
  {
    return this.firmwareVersion;
  }
  
  public Map<String, String> getNotifyUuid()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe2-0000-1000-8000-00805f9b34fb");
    return localHashMap;
  }
  
  protected abstract void handleRJ25Respond(RJ25Respond paramRJ25Respond);
  
  public void queryFirmwareVersion()
  {
    sendData(new QueryFirmwareInstruction().getBytes());
  }
  
  public void receiveBleData(BleData paramBleData)
  {
    if (("0000ffe1-0000-1000-8000-00805f9b34fb".equals(paramBleData.uuidService)) && ("0000ffe2-0000-1000-8000-00805f9b34fb".equals(paramBleData.uuidCharacteristic)))
    {
      this.rj25RespondParser.parseBytes(paramBleData.data);
      int j = this.makeBlockStatusAdapters.size();
      int i = 0;
      while (i < j)
      {
        ((MakeBlockStatusAdapter)this.makeBlockStatusAdapters.get(i)).onReceive(paramBleData);
        i += 1;
      }
    }
  }
  
  public void registerMakeBlockStatusAdapter(MakeBlockStatusAdapter paramMakeBlockStatusAdapter)
  {
    this.makeBlockStatusAdapters.add(paramMakeBlockStatusAdapter);
  }
  
  public void removeMakeBlockStatusAdapter(MakeBlockStatusAdapter paramMakeBlockStatusAdapter)
  {
    this.makeBlockStatusAdapters.remove(paramMakeBlockStatusAdapter);
  }
  
  protected final void sendData(byte[] paramArrayOfByte)
  {
    sendBleData(new BleData("0000ffe1-0000-1000-8000-00805f9b34fb", "0000ffe3-0000-1000-8000-00805f9b34fb", paramArrayOfByte));
  }
  
  public void setFirmwareVersion(String paramString)
  {
    this.firmwareVersion = paramString;
  }
  
  public static class MakeBlockStatusAdapter
  {
    public void onReceive(BleData paramBleData) {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\common\MakeBlockDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */