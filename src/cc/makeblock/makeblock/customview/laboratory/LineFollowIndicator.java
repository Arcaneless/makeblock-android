package cc.makeblock.makeblock.customview.laboratory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class LineFollowIndicator
  extends LaboratoryView
{
  private static final int LEFT_BLACK_RIGHT_BLACK = 3;
  private static final int LEFT_BLACK_RIGHT_WHITE = 2;
  private static final int LEFT_WHITE_RIGHT_BLACK = 1;
  private static final int LEFT_WHITE_RIGHT_WHITE = 0;
  public static final float WIDGET_SIZE_PERCENT = 0.84444445F;
  private ImageView detectorLeft;
  private ImageView detectorRight;
  
  public LineFollowIndicator(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public LineFollowIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = LayoutInflater.from(paramContext).inflate(2131427461, this);
    this.detectorLeft = ((ImageView)paramContext.findViewById(2131296499));
    this.detectorRight = ((ImageView)paramContext.findViewById(2131296500));
    setLineFollowState(2);
  }
  
  public float getSizePercent()
  {
    return 0.84444445F;
  }
  
  protected void reset()
  {
    setLineFollowState(2);
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setLineFollowState(int paramInt)
  {
    if (this.mode == 0) {}
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      this.detectorLeft.setImageResource(2131165664);
      this.detectorRight.setImageResource(2131165664);
      return;
    case 1: 
      this.detectorLeft.setImageResource(2131165664);
      this.detectorRight.setImageResource(2131165661);
      return;
    case 2: 
      this.detectorLeft.setImageResource(2131165661);
      this.detectorRight.setImageResource(2131165664);
      return;
    }
    this.detectorLeft.setImageResource(2131165661);
    this.detectorRight.setImageResource(2131165661);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\laboratory\LineFollowIndicator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */