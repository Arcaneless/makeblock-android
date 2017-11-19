package cc.makeblock.makeblock.scene.playground.codey;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import cc.makeblock.makeblock.databinding.ActivityCodeyControllerBinding;
import cc.makeblock.makeblock.engine.device.Codey;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.CodeyControllerViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.codey.CodeyViewModel.CodeyControllerView;

public class ControllerActivity
  extends CodeyOfflinePlaygroundActivity<CodeyControllerViewModel>
  implements CodeyViewModel.CodeyControllerView
{
  private static final String TAG_UPDATE_PROGRAM_DIALOG = "update_program_dialog";
  
  private void hideUpdateProgramDialog()
  {
    Object localObject = getSupportFragmentManager().findFragmentByTag("update_program_dialog");
    if ((localObject != null) && ((localObject instanceof DialogFragment)))
    {
      localObject = (DialogFragment)localObject;
      if ((((DialogFragment)localObject).getDialog() != null) && (((DialogFragment)localObject).getDialog().isShowing())) {
        ((DialogFragment)localObject).dismiss();
      }
    }
  }
  
  protected CodeyControllerViewModel createViewModel()
  {
    return new CodeyControllerViewModel((Codey)this.device, this);
  }
  
  public void finish()
  {
    super.finish();
    if (this.viewModel != null) {
      ((CodeyControllerViewModel)this.viewModel).resetCodey();
    }
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ((ActivityCodeyControllerBinding)DataBindingUtil.setContentView(this, 2131427361)).setViewModel((CodeyControllerViewModel)this.viewModel);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    hideUpdateProgramDialog();
  }
  
  public void showProgramUpdateDialog(String paramString1, String paramString2)
  {
    UpdateProgramDialog.newInstance(paramString1, paramString2).show(getSupportFragmentManager(), "update_program_dialog");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\codey\ControllerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */