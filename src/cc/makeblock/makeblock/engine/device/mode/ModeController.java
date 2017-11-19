package cc.makeblock.makeblock.engine.device.mode;

import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.device.Starter;
import cc.makeblock.makeblock.engine.device.common.Device;
import cc.makeblock.makeblock.engine.protocol.base.Instruction;
import cc.makeblock.makeblock.engine.protocol.rj25.RJ25InstructionFactory;
import cc.makeblock.makeblock.engine.protocol.rj25.instruction.DCMotorInstruction;
import java.util.Timer;
import java.util.TimerTask;

public class ModeController
{
  private static String TAG = ModeController.class.getSimpleName();
  private static ModeController instance = new ModeController();
  private AddQueryDataToCommandQueueThread addQueryDataToCommandQueueThread;
  private Timer timer;
  
  public static ModeController getInstance()
  {
    return instance;
  }
  
  private void setSpeed2BothWheel(int paramInt1, int paramInt2)
  {
    if (ControllerManager.getInstance().getMode() != 0)
    {
      DCMotorInstruction localDCMotorInstruction1 = new DCMotorInstruction((byte)9, (short)paramInt1);
      DCMotorInstruction localDCMotorInstruction2 = new DCMotorInstruction((byte)10, (short)paramInt2);
      BleManager.getInstance().writeToBluetooth(localDCMotorInstruction1.getBytes());
      BleManager.getInstance().writeToBluetooth(localDCMotorInstruction2.getBytes());
    }
  }
  
  private void setUpQueryThread()
  {
    this.addQueryDataToCommandQueueThread = new AddQueryDataToCommandQueueThread(null);
    this.addQueryDataToCommandQueueThread.start();
  }
  
  private void stopMoving()
  {
    DCMotorInstruction localDCMotorInstruction1 = new DCMotorInstruction((byte)9, (short)0);
    DCMotorInstruction localDCMotorInstruction2 = new DCMotorInstruction((byte)10, (short)0);
    BleManager.getInstance().writeToBluetooth(localDCMotorInstruction1.getBytes());
    BleManager.getInstance().writeToBluetooth(localDCMotorInstruction2.getBytes());
  }
  
  private void stopSendingQueryData()
  {
    if (this.addQueryDataToCommandQueueThread != null)
    {
      this.timer.cancel();
      this.addQueryDataToCommandQueueThread = null;
    }
  }
  
  public void exitUltrasonicMode()
  {
    Device localDevice = ControllerManager.getInstance().getCurrentDevice();
    if (((localDevice instanceof Starter)) && (((Starter)localDevice).getMode() == 1)) {
      setDeviceToManual();
    }
  }
  
  public void setDeviceToManual()
  {
    stopSendingQueryData();
    stopMoving();
  }
  
  public void setDeviceToObstacleAvoid()
  {
    stopSendingQueryData();
    setUpQueryThread();
  }
  
  public void setRobotMoveDueToUltrasonicChange(float paramFloat)
  {
    if (paramFloat > 15.0F) {
      setSpeed2BothWheel(-100, 100);
    }
    do
    {
      return;
      if (paramFloat > 0.0F)
      {
        setSpeed2BothWheel(0, 65336);
        return;
      }
    } while (paramFloat > 0.0F);
    setSpeed2BothWheel(65386, 150);
  }
  
  private class AddQueryDataToCommandQueueThread
    extends Thread
  {
    private AddQueryDataToCommandQueueThread() {}
    
    public void run()
    {
      ModeController.access$102(ModeController.this, new Timer());
      ModeController.this.timer.schedule(new TimerTask()
      {
        public void run()
        {
          Instruction localInstruction = RJ25InstructionFactory.createQueryUltrasonicInstruction();
          BleManager.getInstance().writeToBluetooth(localInstruction.getBytes());
        }
      }, 0L, 90L);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\device\mode\ModeController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */