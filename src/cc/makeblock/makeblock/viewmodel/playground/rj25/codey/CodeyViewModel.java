package cc.makeblock.makeblock.viewmodel.playground.rj25.codey;

import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;

public class CodeyViewModel
  extends DeviceViewModel<Codey>
{
  private CodeyControllerView mCodeyView;
  
  public CodeyViewModel(Codey paramCodey, CodeyControllerView paramCodeyControllerView)
  {
    super(paramCodey);
    this.mCodeyView = paramCodeyControllerView;
  }
  
  public void showProgramUpdateDialog(String paramString1, String paramString2)
  {
    this.mCodeyView.showProgramUpdateDialog(paramString1, paramString2);
  }
  
  public static abstract interface CodeyControllerView
  {
    public abstract void showProgramUpdateDialog(String paramString1, String paramString2);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\codey\CodeyViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */