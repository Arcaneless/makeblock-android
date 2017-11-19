package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class RadioLinearLayout
  extends LinearLayout
{
  private int mCheckedId = -1;
  private ItemSelectedListener mItemSelectedListener;
  
  public RadioLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public RadioLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public RadioLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private List<Checkable> findCheckableChildView(View paramView)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramView instanceof Checkable)) {
      localArrayList.add((Checkable)paramView);
    }
    for (;;)
    {
      return localArrayList;
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int i = 0;
        int j = paramView.getChildCount();
        while (i < j)
        {
          localArrayList.addAll(findCheckableChildView(paramView.getChildAt(i)));
          i += 1;
        }
      }
    }
  }
  
  public int getCheckedId()
  {
    return this.mCheckedId;
  }
  
  public void setCheckedId(int paramInt)
  {
    if (this.mCheckedId == paramInt) {}
    View localView;
    do
    {
      return;
      localView = findViewById(this.mCheckedId);
      if ((localView != null) && ((localView instanceof RadioLinearLayoutItem))) {
        ((RadioLinearLayoutItem)localView).setChecked(false);
      }
      this.mCheckedId = paramInt;
      localView = findViewById(this.mCheckedId);
    } while ((localView == null) || (!(localView instanceof RadioLinearLayoutItem)));
    ((RadioLinearLayoutItem)localView).setChecked(true);
  }
  
  public void setItemSelectedListener(ItemSelectedListener paramItemSelectedListener)
  {
    this.mItemSelectedListener = paramItemSelectedListener;
  }
  
  public static abstract interface ItemSelectedListener
  {
    public abstract void onItemSelected(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\RadioLinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */