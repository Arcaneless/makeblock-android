package cc.makeblock.makeblock.engine.firmware.airblock;

import android.os.Handler;
import android.util.Log;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.protocol.neuron.AirBlockOffsetAngleRespond;
import cc.makeblock.makeblock.engine.protocol.neuron.AirBlockSetOffsetAngleInstruction;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronAllotIdInstruction;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespond;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespondParser;
import cc.makeblock.makeblock.engine.protocol.neuron.NeuronRespondParser.OnRespondReceiveListener;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import ml.xuexin.bleconsultant.entity.BleDevice;

public class SetOffsetAngleCommand
  extends AirblockUpdateCommand
  implements NeuronRespondParser.OnRespondReceiveListener
{
  private Handler mHandler;
  private NeuronRespondParser neuronRespondParser = new NeuronRespondParser();
  
  public SetOffsetAngleCommand(AirblockProgrammer paramAirblockProgrammer, Handler paramHandler)
  {
    super(paramAirblockProgrammer);
    this.mHandler = paramHandler;
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
  
  private Runnable createSetOffsetAngleRunnable(final float paramFloat1, final float paramFloat2, final float paramFloat3)
  {
    new Runnable()
    {
      public void run()
      {
        BleManager.getInstance().writeToBluetooth(new AirBlockSetOffsetAngleInstruction(paramFloat1, paramFloat2, paramFloat3).getBytes());
        SetOffsetAngleCommand.this.mHandler.post(new Runnable()
        {
          public void run()
          {
            SetOffsetAngleCommand.this.callback(true, "假装设置成功了");
          }
        });
      }
    };
  }
  
  protected void doCallback(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      this.airblockProgrammer.updateProgramStatus(UpdateStatus.SUCCESS, "终于完了");
    }
  }
  
  protected void doExecute()
  {
    Object localObject = SharedPreferencesUtils.getAirblockOffsetAngle(BleManager.getInstance().getConnectedDevice().getAddress());
    if (localObject != null)
    {
      localObject = (AirBlockOffsetAngleRespond)new Gson().fromJson((String)localObject, AirBlockOffsetAngleRespond.class);
      new ActionBuilder().append(createHeartBeatRunnable(), 0L).append(createHeartBeatRunnable(), 500L).append(createHeartBeatRunnable(), 500L).append(createSetOffsetAngleRunnable(((AirBlockOffsetAngleRespond)localObject).angle1, ((AirBlockOffsetAngleRespond)localObject).angle2, ((AirBlockOffsetAngleRespond)localObject).angle3), 500L).build().execute();
      return;
    }
    callback(true, "并没有angle数据");
  }
  
  protected int getExecuteTime()
  {
    return 5000;
  }
  
  public void onRespondReceive(NeuronRespond paramNeuronRespond)
  {
    Log.e("lyh", "收到回复:" + paramNeuronRespond.toString());
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    Log.e("lyh", "收到数据:" + paramArrayOfByte.length);
    this.neuronRespondParser.parseBytes(paramArrayOfByte);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\SetOffsetAngleCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */