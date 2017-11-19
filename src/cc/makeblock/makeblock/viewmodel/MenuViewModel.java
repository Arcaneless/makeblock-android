package cc.makeblock.makeblock.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import cc.makeblock.makeblock.bean.IMenuItem;

public abstract class MenuViewModel
  extends BaseObservable
{
  public final ObservableList<IMenuItem> menuItems = new ObservableArrayList();
  
  public abstract void menuItemClick(Activity paramActivity, IMenuItem paramIMenuItem, int paramInt);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\MenuViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */