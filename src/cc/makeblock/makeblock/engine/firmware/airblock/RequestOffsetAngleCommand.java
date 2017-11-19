package cc.makeblock.makeblock.engine.firmware.airblock;

import android.util.Log;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.protocol.neuron.AirBlockOffsetAngleRespond;
import cc.makeblock.makeblock.engine.protocol.neuron.AirBlockRequestOffsetAngleInstruction;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronAllotIdInstruction;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespond;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespondParser;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespondParser.OnRespondReceiveListener;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import ml.xuexin.bleconsultant.entity.BleDevice;

public class RequestOffsetAngleCommand
  extends AirblockUpdateCommand
  implements NeuronRespondParser.OnRespondReceiveListener
{
  private NeuronRespondParser neuronRespondParser = new NeuronRespondParser();
  
  public RequestOffsetAngleCommand(AirblockProgrammer paramAirblockProgrammer)
  {
    super(paramAirblockProgrammer);
    this.neuronRespondParser.setOnRespondReceiveListener(this);
  }
  
  private Runnable createHeartBeatRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        BleManager.getInstance().writeToBluetooth(new NeuronAllotIdInstruction().getBytes());
      }
    };
  }
  
  private Runnable createRequestOffsetAngleRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        BleManager.getInstance().writeToBluetooth(new AirBlockRequestOffsetAngleInstruction().getBytes());
      }
    };
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.SKIP_BLE_CHECK, paramString);
    }
  }
  
  public void doExecute()
  {
    new ActionBuilder().append(createHeartBeatRunnable(), 0L).append(createHeartBeatRunnable(), 500L).append(createHeartBeatRunnable(), 500L).append(createRequestOffsetAngleRunnable(), 500L).build().execute();
  }
  
  protected int getExecuteTime()
  {
    return 5000;
  }
  
  public void onRespondReceive(NeuronRespond paramNeuronRespond)
  {
    if ((paramNeuronRespond instanceof AirBlockOffsetAngleRespond))
    {
      SharedPreferencesUtils.setAirblockOffsetAngle(BleManager.getInstance().getConnectedDevice().getAddress(), new Gson().toJson(paramNeuronRespond));
      Log.e("lyh", "获取偏转角成功" + paramNeuronRespond);
      callback(true, "获取偏转角成功");
    }
  }
  
  protected void onTimeOut()
  {
    this.airblockProgrammer.updateProgramStatus(UpdateStatus.FAIL_REQUEST_ANGLE, getClass().getSimpleName() + "超时");
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    Log.e("lyh", "收到数据:" + paramArrayOfByte.length);
    this.neuronRespondParser.parseBytes(paramArrayOfByte);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\RequestOffsetAngleCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */