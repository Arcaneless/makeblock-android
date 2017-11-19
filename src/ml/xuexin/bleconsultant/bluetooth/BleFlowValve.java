package ml.xuexin.bleconsultant.bluetooth;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import ml.xuexin.bleconsultant.entity.WaitSendData;
import ml.xuexin.bleconsultant.tool.BleLog;

public class BleFlowValve
  implements Resettable
{
  private int cacheCount = 0;
  private final Connector connector;
  private byte[] dataCache = new byte['Ѐ'];
  private final int dataMaxLength;
  private WaitSendData lastData;
  private final Timer sendDataTimer;
  private final LinkedBlockingQueue<WaitSendData> waitSendDataQueue = new LinkedBlockingQueue();
  
  public BleFlowValve(Connector paramConnector, int paramInt1, int paramInt2)
  {
    this.connector = paramConnector;
    this.dataMaxLength = paramInt1;
    this.sendDataTimer = new Timer("BleFlowValve", true);
    this.sendDataTimer.schedule(new SendDataTimerTask(null), 0L, paramInt2);
  }
  
  public void reset()
  {
    this.waitSendDataQueue.clear();
  }
  
  public boolean sendData(WaitSendData paramWaitSendData)
  {
    this.waitSendDataQueue.add(paramWaitSendData);
    return true;
  }
  
  private class SendDataTimerTask
    extends TimerTask
  {
    private SendDataTimerTask() {}
    
    public void run()
    {
      while ((BleFlowValve.this.cacheCount < BleFlowValve.this.dataMaxLength) && (BleFlowValve.this.waitSendDataQueue.size() > 0))
      {
        localObject = (WaitSendData)BleFlowValve.this.waitSendDataQueue.peek();
        if (BleFlowValve.this.lastData == null) {
          BleFlowValve.access$402(BleFlowValve.this, (WaitSendData)localObject);
        }
        if (!BleFlowValve.this.lastData.isSameCharacteristic((WaitSendData)localObject)) {
          break;
        }
        byte[] arrayOfByte = ((WaitSendData)localObject).data;
        System.arraycopy(arrayOfByte, 0, BleFlowValve.this.dataCache, BleFlowValve.this.cacheCount, arrayOfByte.length);
        BleFlowValve.access$102(BleFlowValve.this, BleFlowValve.this.cacheCount + arrayOfByte.length);
        if (localObject != null) {
          BleFlowValve.access$402(BleFlowValve.this, (WaitSendData)BleFlowValve.this.waitSendDataQueue.poll());
        }
      }
      if (BleFlowValve.this.cacheCount > BleFlowValve.this.dataMaxLength) {}
      for (int i = BleFlowValve.this.dataMaxLength; i == 0; i = BleFlowValve.this.cacheCount) {
        return;
      }
      Object localObject = new byte[i];
      System.arraycopy(BleFlowValve.this.dataCache, 0, localObject, 0, i);
      if (BleFlowValve.this.connector.writeToBle(BleFlowValve.this.lastData.serviceUuid, BleFlowValve.this.lastData.characteristicUuid, (byte[])localObject))
      {
        BleLog.w(BleFlowValve.this.lastData.characteristicUuid + ", send:" + BleLog.parseByte((byte[])localObject));
        BleFlowValve.access$102(BleFlowValve.this, BleFlowValve.this.cacheCount - i);
        if (BleFlowValve.this.cacheCount > 0) {
          System.arraycopy(BleFlowValve.this.dataCache, i, BleFlowValve.this.dataCache, 0, BleFlowValve.this.cacheCount);
        }
      }
      else
      {
        BleLog.w("send fail, wait to next time");
        return;
      }
      BleFlowValve.access$402(BleFlowValve.this, null);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\bluetooth\BleFlowValve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */