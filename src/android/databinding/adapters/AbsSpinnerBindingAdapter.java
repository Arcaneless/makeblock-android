package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import java.util.List;

public class AbsSpinnerBindingAdapter
{
  @BindingAdapter({"android:entries"})
  public static <T> void setEntries(AbsSpinner paramAbsSpinner, List<T> paramList)
  {
    if (paramList != null)
    {
      SpinnerAdapter localSpinnerAdapter = paramAbsSpinner.getAdapter();
      if ((localSpinnerAdapter instanceof ObservableListAdapter))
      {
        ((ObservableListAdapter)localSpinnerAdapter).setList(paramList);
        return;
      }
      paramAbsSpinner.setAdapter(new ObservableListAdapter(paramAbsSpinner.getContext(), paramList, 17367048, 17367049, 0));
      return;
    }
    paramAbsSpinner.setAdapter(null);
  }
  
  @BindingAdapter({"android:entries"})
  public static <T extends CharSequence> void setEntries(AbsSpinner paramAbsSpinner, T[] paramArrayOfT)
  {
    if (paramArrayOfT != null)
    {
      SpinnerAdapter localSpinnerAdapter = paramAbsSpinner.getAdapter();
      int j = 1;
      int i = j;
      int k;
      if (localSpinnerAdapter != null)
      {
        i = j;
        if (localSpinnerAdapter.getCount() == paramArrayOfT.length)
        {
          k = 0;
          j = 0;
        }
      }
      for (;;)
      {
        i = k;
        if (j < paramArrayOfT.length)
        {
          if (!paramArrayOfT[j].equals(localSpinnerAdapter.getItem(j))) {
            i = 1;
          }
        }
        else
        {
          if (i != 0)
          {
            paramArrayOfT = new ArrayAdapter(paramAbsSpinner.getContext(), 17367048, paramArrayOfT);
            paramArrayOfT.setDropDownViewResource(17367049);
            paramAbsSpinner.setAdapter(paramArrayOfT);
          }
          return;
        }
        j += 1;
      }
    }
    paramAbsSpinner.setAdapter(null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\AbsSpinnerBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */