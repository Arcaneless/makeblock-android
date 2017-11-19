package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import cc.makeblock.makeblock.R.styleable;
import cc.makeblock.makeblock.bean.WidgetData;

public class PortSelectItemView
  extends ImageView
{
  private String[] directControl;
  private String port;
  private String portFilter;
  private int slot;
  
  public PortSelectItemView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public PortSelectItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public PortSelectItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void checkSelectStatus(WidgetData paramWidgetData)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    for (;;)
    {
      try
      {
        if (this.port.equals(paramWidgetData.port)) {
          if (this.slot == -1)
          {
            if ((paramWidgetData.slot == null) || (paramWidgetData.slot.isEmpty()) || (this.slot == Integer.valueOf(paramWidgetData.slot).intValue())) {
              break label142;
            }
            bool2 = bool1;
            if (this.directControl != null)
            {
              break label147;
              bool2 = bool1;
              if (i < this.directControl.length)
              {
                if (!this.directControl[i].equals("rgbled")) {
                  break label154;
                }
                break label152;
              }
            }
          }
          else
          {
            if (this.slot == 0) {
              break label166;
            }
            if (this.slot != Integer.valueOf(paramWidgetData.slot).intValue()) {
              continue;
            }
            break label161;
          }
        }
        setSelected(bool2);
        return;
      }
      catch (Exception paramWidgetData)
      {
        return;
      }
      label142:
      bool1 = true;
      continue;
      label147:
      int i = 0;
      continue;
      label152:
      bool1 = true;
      label154:
      i += 1;
      continue;
      label161:
      bool1 = true;
      continue;
      label166:
      bool1 = true;
    }
  }
  
  private boolean containsAll(String paramString1, String paramString2)
  {
    int i;
    if ((paramString2 != null) && (!paramString2.isEmpty())) {
      i = 0;
    }
    while (i < paramString2.length())
    {
      if (!paramString1.contains(paramString2.substring(i, i + 1))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Selectable, paramInt, 0);
    this.port = paramContext.getString(1);
    this.portFilter = paramContext.getString(2);
    this.slot = paramContext.getInt(3, -1);
    paramAttributeSet = paramContext.getString(0);
    if (paramAttributeSet != null) {
      this.directControl = paramAttributeSet.split(",");
    }
    paramContext.recycle();
  }
  
  public void checkStatus(WidgetData paramWidgetData)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (this.directControl != null)
    {
      int i = 0;
      for (;;)
      {
        bool1 = bool2;
        if (i >= this.directControl.length) {
          break;
        }
        if (this.directControl[i].equals(paramWidgetData.directControlType)) {
          bool2 = true;
        }
        i += 1;
      }
    }
    bool2 = bool1;
    if (this.portFilter != null)
    {
      bool2 = bool1;
      if (containsAll(this.portFilter, paramWidgetData.portFilter)) {
        bool2 = true;
      }
    }
    setEnabled(bool2);
    if (bool2) {
      checkSelectStatus(paramWidgetData);
    }
  }
  
  public String getPort()
  {
    return this.port;
  }
  
  public int getSlot()
  {
    return this.slot;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\PortSelectItemView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */