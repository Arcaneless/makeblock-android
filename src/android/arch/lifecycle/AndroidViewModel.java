package android.arch.lifecycle;

import android.app.Application;

public class AndroidViewModel
  extends ViewModel
{
  private Application mApplication;
  
  public AndroidViewModel(Application paramApplication)
  {
    this.mApplication = paramApplication;
  }
  
  public <T extends Application> T getApplication()
  {
    return this.mApplication;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\lifecycle\AndroidViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */