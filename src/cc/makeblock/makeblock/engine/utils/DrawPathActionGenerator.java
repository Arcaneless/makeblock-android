package cc.makeblock.makeblock.engine.utils;

import android.graphics.PointF;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.device.common.Device;
import java.util.List;

public class DrawPathActionGenerator
{
  private static final int SPEED_FOR_DRAW = 200;
  private static final int X_COUNT = 115;
  private static final float jifenzhifangkuai_MBOT = 7.5F;
  private static final float jifenzhifangkuai_RANGER = 1.5F;
  private final int Y_COUNT = 65;
  private ActionBuilder actionBuilder;
  private volatile int currentAngle = 90;
  private Device device;
  private long lastForwardGap;
  private volatile PointF lastPoint;
  private OnRunnableRunListener onRunnableRunListener;
  private int runnableCount;
  
  private Runnable createMoveRunnable(final int paramInt1, final int paramInt2)
  {
    new Runnable()
    {
      public void run()
      {
        if (DrawPathActionGenerator.this.device != null)
        {
          if (!(DrawPathActionGenerator.this.device instanceof MBot)) {
            break label67;
          }
          ((MBot)DrawPathActionGenerator.this.device).moveJoystick(paramInt1, paramInt2);
        }
        for (;;)
        {
          if (DrawPathActionGenerator.this.onRunnableRunListener != null) {
            DrawPathActionGenerator.this.onRunnableRunListener.onRunnableRun();
          }
          return;
          label67:
          if ((DrawPathActionGenerator.this.device instanceof Ranger)) {
            ((Ranger)DrawPathActionGenerator.this.device).moveJoystick(paramInt1, paramInt2);
          }
        }
      }
    };
  }
  
  private Runnable createStopRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        if (DrawPathActionGenerator.this.device != null)
        {
          if (!(DrawPathActionGenerator.this.device instanceof MBot)) {
            break label37;
          }
          ((MBot)DrawPathActionGenerator.this.device).stop();
        }
        label37:
        while (!(DrawPathActionGenerator.this.device instanceof Ranger)) {
          return;
        }
        ((Ranger)DrawPathActionGenerator.this.device).stop();
      }
    };
  }
  
  private int getExpectedMovingAngle(PointF paramPointF)
  {
    int i = (int)Math.toDegrees(Math.atan2(paramPointF.y - this.lastPoint.y, paramPointF.x - this.lastPoint.x));
    int j = i;
    if (i < 0) {
      j = i + 360;
    }
    int k = Math.abs(j - this.currentAngle);
    i = k;
    if (k > 180) {
      i = k - 360;
    }
    k = i;
    if (j > this.currentAngle) {
      k = -i;
    }
    if (Math.abs(k) > 4)
    {
      this.currentAngle = j;
      if (this.currentAngle < 0) {
        this.currentAngle += 360;
      }
    }
    return k;
  }
  
  private void getInstructionForMBot(int paramInt, float paramFloat)
  {
    long l1;
    long l2;
    if (Math.abs(paramInt) < 30)
    {
      l1 = (16.33D * Math.abs(paramInt));
      l2 = (1900.0F * Math.abs(paramFloat) / 115.0F * 23.0F / 7.5F);
      if (paramInt >= 0) {
        break label141;
      }
      paramInt = 65336;
    }
    for (int i = 200;; i = 65336)
    {
      this.actionBuilder.append(createMoveRunnable(paramInt, i), this.lastForwardGap).append(createMoveRunnable(200, 200), l1);
      this.lastForwardGap = l2;
      this.runnableCount += 2;
      return;
      if (Math.abs(paramInt) < 90)
      {
        l1 = (7.529999999999999D * Math.abs(paramInt));
        break;
      }
      l1 = (4.52D * Math.abs(paramInt));
      break;
      label141:
      paramInt = 200;
    }
  }
  
  private void getInstructionForRanger(int paramInt, float paramFloat)
  {
    long l1;
    long l2;
    if (Math.abs(paramInt) < 10)
    {
      l1 = (3.3500000000000014D * Math.abs(paramInt));
      l2 = (540.0D * paramFloat / 115.0D * 23.0D / 1.5D);
      if (paramInt >= 0) {
        break label143;
      }
      paramInt = 65336;
    }
    for (int i = 200;; i = 65336)
    {
      this.actionBuilder.append(createMoveRunnable(paramInt, i), this.lastForwardGap).append(createMoveRunnable(200, 200), l1);
      this.lastForwardGap = l2;
      this.runnableCount += 2;
      return;
      if (Math.abs(paramInt) < 35)
      {
        l1 = (5.115000000000002D * Math.abs(paramInt));
        break;
      }
      l1 = (6.115000000000002D * Math.abs(paramInt));
      break;
      label143:
      paramInt = 200;
    }
  }
  
  private float getRelativeDistance(PointF paramPointF)
  {
    return (float)Math.sqrt(Math.pow(paramPointF.x - this.lastPoint.x, 2.0D) + Math.pow(paramPointF.y - this.lastPoint.y, 2.0D));
  }
  
  public Action generateOrderedActions(List<PointF> paramList, Device paramDevice)
  {
    this.device = paramDevice;
    if (paramList.size() <= 1) {
      return null;
    }
    this.runnableCount = 0;
    this.lastForwardGap = 0L;
    this.actionBuilder = new ActionBuilder();
    int i = 0;
    this.currentAngle = 90;
    while (i < paramList.size())
    {
      PointF localPointF = (PointF)paramList.get(i);
      if (i == 0)
      {
        this.lastPoint = localPointF;
        i += 1;
      }
      else
      {
        int j = getExpectedMovingAngle(localPointF);
        float f = getRelativeDistance(localPointF);
        this.lastPoint = localPointF;
        if ((paramDevice instanceof MBot)) {
          getInstructionForMBot(j, f);
        }
        for (;;)
        {
          i += 1;
          break;
          getInstructionForRanger(j, f);
        }
      }
    }
    this.actionBuilder.append(createMoveRunnable(0, 0), this.lastForwardGap);
    this.actionBuilder.setCancelRunnable(createStopRunnable());
    return this.actionBuilder.build();
  }
  
  public void setOnRunnableRunListener(OnRunnableRunListener paramOnRunnableRunListener)
  {
    this.onRunnableRunListener = paramOnRunnableRunListener;
  }
  
  public static abstract interface OnRunnableRunListener
  {
    public abstract void onRunnableRun();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\DrawPathActionGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */