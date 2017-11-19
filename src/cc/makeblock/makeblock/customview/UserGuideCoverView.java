package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class UserGuideCoverView
  extends PercentRelativeLayout
  implements View.OnClickListener
{
  private Context context;
  private View lastView;
  private int[] layoutIds;
  private LayoutInflater layoutInflater;
  private int nextPageIndex;
  private View rootView;
  
  public UserGuideCoverView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }
  
  public UserGuideCoverView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }
  
  private void init()
  {
    this.layoutInflater = ((LayoutInflater)this.context.getSystemService("layout_inflater"));
    this.rootView = this.layoutInflater.inflate(2131427501, this);
    setOnClickListener(this);
    setCurrentPage();
  }
  
  private void setCurrentPage()
  {
    if (this.lastView != null) {
      this.lastView.setVisibility(8);
    }
    if (this.nextPageIndex >= this.layoutIds.length)
    {
      setVisibility(8);
      return;
    }
    this.lastView = this.layoutInflater.inflate(this.layoutIds[this.nextPageIndex], null);
    ((ViewGroup)this.rootView).addView(this.lastView);
    this.nextPageIndex += 1;
  }
  
  public void onClick(View paramView)
  {
    setCurrentPage();
  }
  
  public void setLayoutIds(int[] paramArrayOfInt)
  {
    if (this.layoutIds == null)
    {
      this.layoutIds = paramArrayOfInt;
      init();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\UserGuideCoverView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */