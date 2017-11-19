package cc.makeblock.makeblock.viewmodel.navigation;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class NavigationViewModel
  extends BaseObservable
{
  public static final int INDEX_DEVICE = 0;
  public static final int INDEX_HELP = 1;
  public static final int INDEX_SETTINGS = 2;
  private int currentIndex;
  private NavigationView mView;
  
  public NavigationViewModel(NavigationView paramNavigationView)
  {
    this.mView = paramNavigationView;
    this.mView.showDevice();
  }
  
  private void updateItemState(int paramInt)
  {
    this.currentIndex = paramInt;
    notifyPropertyChanged(18);
    notifyPropertyChanged(32);
    notifyPropertyChanged(60);
  }
  
  public void close()
  {
    this.mView.close();
  }
  
  @Bindable
  public boolean isDeviceChecked()
  {
    return this.currentIndex == 0;
  }
  
  @Bindable
  public boolean isHelpChecked()
  {
    return this.currentIndex == 1;
  }
  
  @Bindable
  public boolean isSettingsChecked()
  {
    return this.currentIndex == 2;
  }
  
  public void selectDevice()
  {
    updateItemState(0);
    this.mView.showDevice();
  }
  
  public void selectHelp()
  {
    updateItemState(1);
    this.mView.showHelp();
  }
  
  public void selectSettings()
  {
    updateItemState(2);
    this.mView.showSettings();
  }
  
  public static abstract interface NavigationView
  {
    public abstract void close();
    
    public abstract void showDevice();
    
    public abstract void showHelp();
    
    public abstract void showSettings();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\navigation\NavigationViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */