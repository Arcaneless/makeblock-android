package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.FrameLayout;

public class MainRadioTextGuideView
  extends FrameLayout
{
  public MainRadioTextGuideView(@NonNull Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }
  
  public MainRadioTextGuideView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2131427496, this);
    final FitWidthTextView localFitWidthTextView = (FitWidthTextView)paramContext.findViewById(2131296622);
    addOnLayoutChangeListener(new View.OnLayoutChangeListener()
    {
      public void onLayoutChange(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6, int paramAnonymousInt7, int paramAnonymousInt8)
      {
        this.val$description.setX(localFitWidthTextView.getX() - (this.val$description.getWidth() / 2 - localFitWidthTextView.getWidth() / 2));
        localFitWidthTextView.setPivotX(localFitWidthTextView.getWidth() / 2);
        localFitWidthTextView.setPivotY(localFitWidthTextView.getHeight());
        localFitWidthTextView.setScaleX(0.9F);
        localFitWidthTextView.setScaleY(0.9F);
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\MainRadioTextGuideView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */