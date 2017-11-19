package cc.makeblock.makeblock.customview.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import cc.makeblock.makeblock.customview.FitWidthTextView;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class WarningDialog
  extends MakeBlockDialog
  implements View.OnClickListener
{
  private OnOperationConfirmedListener onOperationConfirmedListener;
  private String title;
  
  public WarningDialog(@NonNull Context paramContext)
  {
    super(paramContext);
  }
  
  public void onClick(View paramView)
  {
    this.onOperationConfirmedListener.onOperationConfirmed();
    dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getWindow() != null) {
      getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    setContentView(2131427421);
    findViewById(2131296380).setOnClickListener(this);
    ((FitWidthTextView)findViewById(2131296439)).setText(this.title);
  }
  
  public void setOnOperationConfirmedListener(OnOperationConfirmedListener paramOnOperationConfirmedListener)
  {
    this.onOperationConfirmedListener = paramOnOperationConfirmedListener;
  }
  
  public void setTitleText(String paramString)
  {
    this.title = paramString;
  }
  
  public void show()
  {
    super.show();
    if (getWindow() != null) {
      getWindow().setLayout(ScreenHelper.SCREEN_WIDTH, ScreenHelper.SCREEN_HEIGHT);
    }
  }
  
  public static abstract interface OnOperationConfirmedListener
  {
    public abstract void onOperationConfirmed();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\WarningDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */