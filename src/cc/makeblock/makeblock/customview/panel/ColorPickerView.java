package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.cell.CellView.OnWidgetTriggeredListener;
import cc.makeblock.makeblock.engine.protocol.web.send.BaseJsonObject;

public class ColorPickerView
  extends CellView<ColorPickerState>
{
  private static final String TAG = ColorPickerView.class.getSimpleName();
  private int RGB_BLUE = 0;
  private int RGB_GREEN = 0;
  private int RGB_RED = 0;
  private int circleImageDiameter;
  private int circleImageStroke;
  private CircleImageView circleImageView;
  private ImageView colorHoop;
  private FrameLayout colorHoopContainer;
  private int colorValueMax = 150;
  private Context context;
  private int innerRadius;
  private int lastBValue = 0;
  private int lastGValue = 0;
  private int lastRValue = 0;
  private int outerRadius;
  private PickListener pickListener;
  private AutoResizeTextView tv_title;
  
  public ColorPickerView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    initViews();
  }
  
  public ColorPickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    initViews();
  }
  
  public ColorPickerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    initViews();
  }
  
  public boolean getPortSelectable()
  {
    return true;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  public void initViews()
  {
    addView(((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427538, null), new FrameLayout.LayoutParams(-1, -1));
    this.tv_title = ((AutoResizeTextView)findViewById(2131296745));
    this.colorHoop = ((ImageView)findViewById(2131296404));
    this.colorHoopContainer = ((FrameLayout)findViewById(2131296405));
    this.colorHoop.post(new Runnable()
    {
      public void run()
      {
        ColorPickerView.access$002(ColorPickerView.this, ColorPickerView.this.colorHoop.getWidth() / 2);
        ColorPickerView.access$202(ColorPickerView.this, ColorPickerView.this.outerRadius - ColorPickerView.this.outerRadius * 2 * 48 / 399);
        ColorPickerView.access$302(ColorPickerView.this, (int)(ColorPickerView.this.outerRadius / 3.6D));
        ColorPickerView.access$402(ColorPickerView.this, ColorPickerView.this.circleImageDiameter / 10);
        ColorPickerView.access$502(ColorPickerView.this, new ColorPickerView.CircleImageView(ColorPickerView.this, ColorPickerView.this.context));
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(ColorPickerView.this.circleImageDiameter, ColorPickerView.this.circleImageDiameter);
        localLayoutParams.addRule(13);
        ColorPickerView.this.circleImageView.setLayoutParams(localLayoutParams);
        ColorPickerView.this.colorHoopContainer.addView(ColorPickerView.this.circleImageView);
        ColorPickerView.this.circleImageView.setVisibility(4);
      }
    });
    this.colorHoop.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        float f1 = paramAnonymousMotionEvent.getX();
        float f2 = paramAnonymousMotionEvent.getY();
        double d1 = Math.sqrt(Math.pow(ColorPickerView.this.outerRadius - f1, 2.0D) + Math.pow(ColorPickerView.this.outerRadius - f2, 2.0D));
        double d2;
        if (d1 < ColorPickerView.this.outerRadius)
        {
          if (ColorPickerView.this.circleImageView.getVisibility() == 4) {
            ColorPickerView.this.circleImageView.setVisibility(0);
          }
          double d3;
          double d4;
          double d5;
          if ((f1 < ColorPickerView.this.outerRadius) && (f2 < ColorPickerView.this.outerRadius))
          {
            d2 = ColorPickerView.this.outerRadius - ColorPickerView.this.outerRadius * (ColorPickerView.this.outerRadius - f2) / d1;
            d3 = ColorPickerView.this.outerRadius - Math.sqrt(Math.pow(ColorPickerView.this.outerRadius, 2.0D) - Math.pow(ColorPickerView.this.outerRadius - d2, 2.0D));
            d4 = (ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2 * (ColorPickerView.this.outerRadius - d2) / ColorPickerView.this.outerRadius;
            d5 = Math.sqrt(Math.pow((ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2, 2.0D) - Math.pow(d4, 2.0D));
            ColorPickerView.this.circleImageView.setX((float)(d3 + d5 - ColorPickerView.this.circleImageDiameter / 2));
            ColorPickerView.this.circleImageView.setY((float)(d2 + d4 - ColorPickerView.this.circleImageDiameter / 2));
            d2 = Math.toDegrees(Math.atan((ColorPickerView.this.outerRadius - d2) / (ColorPickerView.this.outerRadius - d3)));
            ColorPickerView.access$802(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * (d2 / 120.0D)));
            ColorPickerView.access$1002(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((120.0D - d2) / 120.0D)));
            ColorPickerView.access$1102(ColorPickerView.this, 0);
          }
          if ((f1 > ColorPickerView.this.outerRadius) && (f2 < ColorPickerView.this.outerRadius))
          {
            d2 = ColorPickerView.this.outerRadius - ColorPickerView.this.outerRadius * (ColorPickerView.this.outerRadius - f2) / d1;
            d3 = ColorPickerView.this.outerRadius + Math.sqrt(Math.pow(ColorPickerView.this.outerRadius, 2.0D) - Math.pow(ColorPickerView.this.outerRadius - d2, 2.0D));
            d4 = (ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2 * (ColorPickerView.this.outerRadius - d2) / ColorPickerView.this.outerRadius;
            d5 = Math.sqrt(Math.pow((ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2, 2.0D) - Math.pow(d4, 2.0D));
            ColorPickerView.this.circleImageView.setX((float)(d3 - d5 - ColorPickerView.this.circleImageDiameter / 2));
            ColorPickerView.this.circleImageView.setY((float)(d2 + d4 - ColorPickerView.this.circleImageDiameter / 2));
            d2 = Math.toDegrees(Math.atan((d3 - ColorPickerView.this.outerRadius) / (ColorPickerView.this.outerRadius - d2)));
            if (d2 >= 30.0D) {
              break label1608;
            }
            ColorPickerView.access$802(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((90.0D + d2) / 120.0D)));
            ColorPickerView.access$1102(ColorPickerView.this, 0);
            ColorPickerView.access$1002(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((30.0D - d2) / 120.0D)));
          }
          if ((f1 < ColorPickerView.this.outerRadius) && (f2 > ColorPickerView.this.outerRadius))
          {
            d2 = ColorPickerView.this.outerRadius + (f2 - ColorPickerView.this.outerRadius) * ColorPickerView.this.outerRadius / d1;
            d3 = ColorPickerView.this.outerRadius - Math.sqrt(Math.pow(ColorPickerView.this.outerRadius, 2.0D) - Math.pow(ColorPickerView.this.outerRadius - d2, 2.0D));
            d4 = (ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2 * (ColorPickerView.this.outerRadius - d2) / ColorPickerView.this.outerRadius;
            d5 = (int)Math.sqrt(Math.pow((ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2, 2.0D) - Math.pow(d4, 2.0D));
            ColorPickerView.this.circleImageView.setX((float)(d3 + d5 - ColorPickerView.this.circleImageDiameter / 2));
            ColorPickerView.this.circleImageView.setY((float)(d2 + d4 - ColorPickerView.this.circleImageDiameter / 2));
            d2 = Math.toDegrees(Math.atan((d2 - ColorPickerView.this.outerRadius) / (ColorPickerView.this.outerRadius - d3)));
            ColorPickerView.access$802(ColorPickerView.this, 0);
            ColorPickerView.access$1102(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * (d2 / 120.0D)));
            ColorPickerView.access$1002(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((120.0D - d2) / 120.0D)));
          }
          if ((f1 > ColorPickerView.this.outerRadius) && (f2 > ColorPickerView.this.outerRadius))
          {
            d1 = ColorPickerView.this.outerRadius - ColorPickerView.this.outerRadius * (ColorPickerView.this.outerRadius - f2) / d1;
            d2 = ColorPickerView.this.outerRadius + Math.sqrt(Math.pow(ColorPickerView.this.outerRadius, 2.0D) - Math.pow(ColorPickerView.this.outerRadius - d1, 2.0D));
            d3 = (ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2 * (ColorPickerView.this.outerRadius - d1) / ColorPickerView.this.outerRadius;
            d4 = (int)Math.sqrt(Math.pow((ColorPickerView.this.outerRadius - ColorPickerView.this.innerRadius) / 2, 2.0D) - Math.pow(d3, 2.0D));
            ColorPickerView.this.circleImageView.setX((float)(d2 - d4 - ColorPickerView.this.circleImageDiameter / 2));
            ColorPickerView.this.circleImageView.setY((float)(d1 + d3 - ColorPickerView.this.circleImageDiameter / 2));
            d1 = Math.toDegrees(Math.atan((d1 - ColorPickerView.this.outerRadius) / (d2 - ColorPickerView.this.outerRadius)));
            if (d1 >= 60.0D) {
              break label1676;
            }
            ColorPickerView.access$802(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((60.0D - d1) / 120.0D)));
            ColorPickerView.access$1102(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((60.0D + d1) / 120.0D)));
            ColorPickerView.access$1002(ColorPickerView.this, 0);
          }
        }
        for (;;)
        {
          if ((ColorPickerView.this.RGB_BLUE != ColorPickerView.this.lastBValue) || (ColorPickerView.this.RGB_GREEN != ColorPickerView.this.lastGValue) || (ColorPickerView.this.RGB_RED != ColorPickerView.this.lastRValue))
          {
            ColorPickerView.access$1402(ColorPickerView.this, ColorPickerView.this.RGB_RED);
            ColorPickerView.access$1302(ColorPickerView.this, ColorPickerView.this.RGB_GREEN);
            ColorPickerView.access$1202(ColorPickerView.this, ColorPickerView.this.RGB_BLUE);
            ColorPickerView.this.pickListener.onPick(ColorPickerView.this.lastRValue, ColorPickerView.this.lastGValue, ColorPickerView.this.lastBValue, 0, Integer.valueOf(ColorPickerView.this.getWidgetData().port).intValue(), Integer.valueOf(ColorPickerView.this.getWidgetData().slot).intValue());
          }
          return true;
          label1608:
          ColorPickerView.access$802(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((120.0D - d2) / 120.0D)));
          ColorPickerView.access$1102(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((d2 - 30.0D) / 120.0D)));
          ColorPickerView.access$1002(ColorPickerView.this, 0);
          break;
          label1676:
          ColorPickerView.access$802(ColorPickerView.this, 0);
          ColorPickerView.access$1102(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((180.0D - d1) / 120.0D)));
          ColorPickerView.access$1002(ColorPickerView.this, (int)(ColorPickerView.this.colorValueMax * ((d1 - 60.0D) / 120.0D)));
        }
      }
    });
    setControlListener();
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void sendState(ColorPickerState paramColorPickerState)
  {
    paramColorPickerState = paramColorPickerState.toString();
    this.mOnWidgetTriggeredListener.onWidgetTrigger("setLedColor", paramColorPickerState);
  }
  
  public void setControlListener()
  {
    setPickListener(new PickListener()
    {
      public void onPick(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6)
      {
        ColorPickerView.this.pushState(new ColorPickerView.ColorPickerState(ColorPickerView.this, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4, paramAnonymousInt5, paramAnonymousInt6));
      }
    });
  }
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
  }
  
  public void setPickListener(PickListener paramPickListener)
  {
    this.pickListener = paramPickListener;
  }
  
  private class CircleImageView
    extends AppCompatImageView
  {
    Paint paint = new Paint();
    
    public CircleImageView(Context paramContext)
    {
      super();
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      super.onDraw(paramCanvas);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setStrokeWidth(ColorPickerView.this.circleImageStroke);
      this.paint.setColor(-1);
      paramCanvas.drawCircle(ColorPickerView.this.circleImageDiameter / 2, ColorPickerView.this.circleImageDiameter / 2, ColorPickerView.this.circleImageDiameter / 2 - ColorPickerView.this.circleImageStroke, this.paint);
    }
  }
  
  class ColorPickerState
    extends BaseJsonObject
  {
    public int b;
    public int g;
    public int port;
    public int position;
    public int r;
    public int slot;
    
    ColorPickerState(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
      this.r = paramInt1;
      this.g = paramInt2;
      this.b = paramInt3;
      this.position = paramInt4;
      this.port = paramInt5;
      this.slot = paramInt6;
    }
  }
  
  static abstract interface PickListener
  {
    public abstract void onPick(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\ColorPickerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */