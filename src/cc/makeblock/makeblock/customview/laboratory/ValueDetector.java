package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.R.styleable;

public class ValueDetector
  extends LaboratoryView
{
  public static final float WIDGET_SIZE_PERCENT = 0.84444445F;
  private Context context;
  private TextView reading;
  private WaveView waveView;
  
  public ValueDetector(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(null);
  }
  
  public ValueDetector(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramAttributeSet);
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = this.context.obtainStyledAttributes(paramAttributeSet, R.styleable.ValueDetector);
    int i = paramAttributeSet.getResourceId(0, 0);
    paramAttributeSet.recycle();
    paramAttributeSet = LayoutInflater.from(this.context).inflate(2131427462, this);
    ((ImageView)paramAttributeSet.findViewById(2131296428)).setImageResource(i);
    this.reading = ((TextView)paramAttributeSet.findViewById(2131296710));
    TextView localTextView = (TextView)paramAttributeSet.findViewById(2131296711);
    Typeface localTypeface = Typeface.createFromAsset(this.context.getAssets(), "font/diode.TTF");
    this.reading.setTypeface(localTypeface);
    localTextView.setTypeface(localTypeface);
    this.waveView = ((WaveView)paramAttributeSet.findViewById(2131296768));
  }
  
  public float getSizePercent()
  {
    return 0.84444445F;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    switch (paramInt)
    {
    case 0: 
    default: 
      return;
    }
    this.waveView.cancel();
    this.reading.setText("0000");
  }
  
  public void setValue(int paramInt)
  {
    if ((this.reading != null) && (this.mode == 0))
    {
      this.waveView.start();
      this.reading.setText(String.valueOf(paramInt));
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\ValueDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */