package cc.makeblock.makeblock.engine.utils;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import cc.makeblock.makeblock.engine.protocol.web.send.SensorDataJson;

public class SensorToBlockly
  implements SensorEventListener
{
  private ActionListener mActionListener;
  private Sensor mSensor;
  private SensorManager mSensorManager;
  
  public SensorToBlockly(Activity paramActivity, ActionListener paramActionListener)
  {
    this.mActionListener = paramActionListener;
    this.mSensorManager = ((SensorManager)paramActivity.getSystemService("sensor"));
    this.mSensor = this.mSensorManager.getDefaultSensor(1);
  }
  
  private String getTiltJson(int paramInt1, int paramInt2)
  {
    return new SensorDataJson(paramInt1, paramInt2).toString();
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onPause()
  {
    this.mSensorManager.unregisterListener(this);
  }
  
  public void onResume()
  {
    this.mSensorManager.registerListener(this, this.mSensor, 3);
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (this.mActionListener == null) {}
    float f2;
    do
    {
      float f1;
      do
      {
        return;
        f1 = paramSensorEvent.values[1];
        f2 = paramSensorEvent.values[0];
        if (Math.sqrt(paramSensorEvent.values[0] * paramSensorEvent.values[0] + paramSensorEvent.values[1] * paramSensorEvent.values[1] + paramSensorEvent.values[2] * paramSensorEvent.values[2]) > 20.0D)
        {
          this.mActionListener.sendAction("shake", null);
          return;
        }
      } while (Math.abs(Math.abs(f1) - Math.abs(f2)) < 3.0F);
      if (f1 < -3.0F)
      {
        this.mActionListener.sendAction("tilt", getTiltJson(1, 0));
        return;
      }
      if (f1 > 3.0F)
      {
        this.mActionListener.sendAction("tilt", getTiltJson(-1, 0));
        return;
      }
      if (f2 < -3.0F)
      {
        this.mActionListener.sendAction("tilt", getTiltJson(0, -1));
        return;
      }
    } while (f2 <= 3.0F);
    this.mActionListener.sendAction("tilt", getTiltJson(0, 1));
  }
  
  public static abstract interface ActionListener
  {
    public abstract void sendAction(String paramString1, String paramString2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\SensorToBlockly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */