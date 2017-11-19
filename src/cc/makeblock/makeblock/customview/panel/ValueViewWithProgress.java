package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.CircleProgressView;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.engine.protocol.web.receive.SendValueToWidgetJson;

public class ValueViewWithProgress
  extends CellView
{
  private static final String TAG = "ValueViewWithProgress";
  private CircleProgressView circleProgressView;
  private TextView tv_title;
  
  public ValueViewWithProgress(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public ValueViewWithProgress(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public ValueViewWithProgress(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427568, null);
    addView(paramAttributeSet, new FrameLayout.LayoutParams(-1, -1));
    this.tv_title = ((TextView)paramAttributeSet.findViewById(2131296745));
    this.circleProgressView = ((CircleProgressView)paramAttributeSet.findViewById(2131296400));
    paramAttributeSet.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (ValueViewWithProgress.this.getCurrentMode() != 1) {
          return false;
        }
        switch (paramAnonymousMotionEvent.getAction() & 0xFF)
        {
        }
        return false;
      }
    });
  }
  
  public int getMax()
  {
    return this.circleProgressView.getMaxValue();
  }
  
  public boolean getProgrammable()
  {
    return true;
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setControlListener() {}
  
  public void setMax(int paramInt)
  {
    this.circleProgressView.setMaxValue(paramInt);
  }
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2) {
      post(new Runnable()
      {
        public void run()
        {
          ValueViewWithProgress.this.setProgress(0.0F);
        }
      });
    }
  }
  
  public void setProgress(float paramFloat)
  {
    this.circleProgressView.setProgress(paramFloat);
  }
  
  public boolean setWidgetValue(SendValueToWidgetJson paramSendValueToWidgetJson)
  {
    if (Integer.parseInt(paramSendValueToWidgetJson.getId()) == this.widgetData.widgetID)
    {
      setProgress(Float.parseFloat(paramSendValueToWidgetJson.getValue()) / getMax());
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ValueViewWithProgress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */