package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.util.SparseBooleanArray;
import android.widget.TableLayout;
import java.util.regex.Pattern;

public class TableLayoutBindingAdapter
{
  private static final int MAX_COLUMNS = 20;
  private static Pattern sColumnPattern = Pattern.compile("\\s*,\\s*");
  
  private static SparseBooleanArray parseColumns(CharSequence paramCharSequence)
  {
    SparseBooleanArray localSparseBooleanArray = new SparseBooleanArray();
    if (paramCharSequence == null) {}
    for (;;)
    {
      return localSparseBooleanArray;
      paramCharSequence = sColumnPattern.split(paramCharSequence);
      int j = paramCharSequence.length;
      int i = 0;
      while (i < j)
      {
        String str = paramCharSequence[i];
        try
        {
          k = Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          for (;;)
          {
            int k;
            continue;
            if (k < 0) {}
          }
        }
        localSparseBooleanArray.put(k, true);
        i += 1;
      }
    }
  }
  
  @BindingAdapter({"android:collapseColumns"})
  public static void setCollapseColumns(TableLayout paramTableLayout, CharSequence paramCharSequence)
  {
    paramCharSequence = parseColumns(paramCharSequence);
    int i = 0;
    while (i < 20)
    {
      boolean bool = paramCharSequence.get(i, false);
      if (bool != paramTableLayout.isColumnCollapsed(i)) {
        paramTableLayout.setColumnCollapsed(i, bool);
      }
      i += 1;
    }
  }
  
  @BindingAdapter({"android:shrinkColumns"})
  public static void setShrinkColumns(TableLayout paramTableLayout, CharSequence paramCharSequence)
  {
    if ((paramCharSequence != null) && (paramCharSequence.length() > 0) && (paramCharSequence.charAt(0) == '*')) {
      paramTableLayout.setShrinkAllColumns(true);
    }
    for (;;)
    {
      return;
      paramTableLayout.setShrinkAllColumns(false);
      paramCharSequence = parseColumns(paramCharSequence);
      int j = paramCharSequence.size();
      int i = 0;
      while (i < j)
      {
        int k = paramCharSequence.keyAt(i);
        boolean bool = paramCharSequence.valueAt(i);
        if (bool) {
          paramTableLayout.setColumnShrinkable(k, bool);
        }
        i += 1;
      }
    }
  }
  
  @BindingAdapter({"android:stretchColumns"})
  public static void setStretchColumns(TableLayout paramTableLayout, CharSequence paramCharSequence)
  {
    if ((paramCharSequence != null) && (paramCharSequence.length() > 0) && (paramCharSequence.charAt(0) == '*')) {
      paramTableLayout.setStretchAllColumns(true);
    }
    for (;;)
    {
      return;
      paramTableLayout.setStretchAllColumns(false);
      paramCharSequence = parseColumns(paramCharSequence);
      int j = paramCharSequence.size();
      int i = 0;
      while (i < j)
      {
        int k = paramCharSequence.keyAt(i);
        boolean bool = paramCharSequence.valueAt(i);
        if (bool) {
          paramTableLayout.setColumnStretchable(k, bool);
        }
        i += 1;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\TableLayoutBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */