package cc.makeblock.makeblock.viewmodel.playground.rj25.codey;

import cc.makeblock.makeblock.engine.device.Codey;

public abstract class CodeyOffLineUpdateViewModel
  extends CodeyViewModel
{
  public CodeyOffLineUpdateViewModel(Codey paramCodey, CodeyViewModel.CodeyControllerView paramCodeyControllerView)
  {
    super(paramCodey, paramCodeyControllerView);
  }
  
  public abstract String getProgramPath();
  
  public abstract String getProgramVersion();
  
  public void showProgramUpdateDialog()
  {
    showProgramUpdateDialog(getProgramPath(), getProgramVersion());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\codey\CodeyOffLineUpdateViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */