package cc.makeblock.makeblock.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import cc.makeblock.makeblock.databinding.DialogAirblockTuneBinding;
import cc.makeblock.makeblock.viewmodel.customview.dialog.AirblockTuneDialogViewModel;

public class AirblockTuneDialog
  extends Dialog
{
  private Context context;
  
  public AirblockTuneDialog(@NonNull Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    if (getWindow() != null) {
      getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    setCanceledOnTouchOutside(false);
    getWindow().setFlags(67108864, 67108864);
    paramBundle = (DialogAirblockTuneBinding)DataBindingUtil.inflate((LayoutInflater)this.context.getSystemService("layout_inflater"), 2131427400, null, false);
    paramBundle.setViewModel(new AirblockTuneDialogViewModel(this));
    addContentView(paramBundle.getRoot(), new ViewGroup.LayoutParams(-1, -1));
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\AirblockTuneDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */