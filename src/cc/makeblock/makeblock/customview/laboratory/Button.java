package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;

public class Button
  extends LaboratoryView
{
  public static final float WIDGET_SIZE_PERCENT = 0.5777778F;
  
  public Button(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public Button(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Button);
    int i = paramAttributeSet.getResourceId(0, 0);
    paramAttributeSet.recycle();
    ((ImageView)LayoutInflater.from(paramContext).inflate(2131427444, this).findViewById(2131296539)).setImageResource(i);
  }
  
  public float getSizePercent()
  {
    return 0.5777778F;
  }
  
  protected void sendState(Object paramObject) {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\Button.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */