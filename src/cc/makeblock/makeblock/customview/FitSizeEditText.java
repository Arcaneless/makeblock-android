package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class FitSizeEditText
  extends AppCompatEditText
{
  private static final float TEXT_HEIGHT_PERCENT = 0.05F;
  
  public FitSizeEditText(Context paramContext)
  {
    super(paramContext);
    initView();
  }
  
  public FitSizeEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }
  
  public FitSizeEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView();
  }
  
  private void initView()
  {
    setTextSize(0, 0.05F * ScreenHelper.SCREEN_HEIGHT);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\FitSizeEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */