package android.databinding.adapters;

import android.content.Context;
import android.databinding.ObservableList;
import android.databinding.ObservableList.OnListChangedCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class ObservableListAdapter<T>
  extends BaseAdapter
{
  private final Context mContext;
  private final int mDropDownResourceId;
  private final LayoutInflater mLayoutInflater;
  private List<T> mList;
  private ObservableList.OnListChangedCallback mListChangedCallback;
  private final int mResourceId;
  private final int mTextViewResourceId;
  
  public ObservableListAdapter(Context paramContext, List<T> paramList, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mContext = paramContext;
    this.mResourceId = paramInt1;
    this.mDropDownResourceId = paramInt2;
    this.mTextViewResourceId = paramInt3;
    if (paramInt1 == 0) {}
    for (paramContext = null;; paramContext = (LayoutInflater)paramContext.getSystemService("layout_inflater"))
    {
      this.mLayoutInflater = paramContext;
      setList(paramList);
      return;
    }
  }
  
  public int getCount()
  {
    return this.mList.size();
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getViewForResource(this.mDropDownResourceId, paramInt, paramView, paramViewGroup);
  }
  
  public Object getItem(int paramInt)
  {
    return this.mList.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getViewForResource(this.mResourceId, paramInt, paramView, paramViewGroup);
  }
  
  public View getViewForResource(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = paramView;
    if (paramView == null)
    {
      if (paramInt1 == 0) {
        localObject = new TextView(this.mContext);
      }
    }
    else
    {
      if (this.mTextViewResourceId != 0) {
        break label91;
      }
      paramView = (View)localObject;
      label34:
      paramViewGroup = (TextView)paramView;
      paramView = this.mList.get(paramInt2);
      if (!(paramView instanceof CharSequence)) {
        break label104;
      }
    }
    label91:
    label104:
    for (paramView = (CharSequence)paramView;; paramView = String.valueOf(paramView))
    {
      paramViewGroup.setText(paramView);
      return (View)localObject;
      localObject = this.mLayoutInflater.inflate(paramInt1, paramViewGroup, false);
      break;
      paramView = ((View)localObject).findViewById(this.mTextViewResourceId);
      break label34;
    }
  }
  
  public void setList(List<T> paramList)
  {
    if (this.mList == paramList) {
      return;
    }
    if ((this.mList instanceof ObservableList)) {
      ((ObservableList)this.mList).removeOnListChangedCallback(this.mListChangedCallback);
    }
    this.mList = paramList;
    if ((this.mList instanceof ObservableList))
    {
      if (this.mListChangedCallback == null) {
        this.mListChangedCallback = new ObservableList.OnListChangedCallback()
        {
          public void onChanged(ObservableList paramAnonymousObservableList)
          {
            ObservableListAdapter.this.notifyDataSetChanged();
          }
          
          public void onItemRangeChanged(ObservableList paramAnonymousObservableList, int paramAnonymousInt1, int paramAnonymousInt2)
          {
            ObservableListAdapter.this.notifyDataSetChanged();
          }
          
          public void onItemRangeInserted(ObservableList paramAnonymousObservableList, int paramAnonymousInt1, int paramAnonymousInt2)
          {
            ObservableListAdapter.this.notifyDataSetChanged();
          }
          
          public void onItemRangeMoved(ObservableList paramAnonymousObservableList, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            ObservableListAdapter.this.notifyDataSetChanged();
          }
          
          public void onItemRangeRemoved(ObservableList paramAnonymousObservableList, int paramAnonymousInt1, int paramAnonymousInt2)
          {
            ObservableListAdapter.this.notifyDataSetChanged();
          }
        };
      }
      ((ObservableList)this.mList).addOnListChangedCallback(this.mListChangedCallback);
    }
    notifyDataSetChanged();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\ObservableListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */