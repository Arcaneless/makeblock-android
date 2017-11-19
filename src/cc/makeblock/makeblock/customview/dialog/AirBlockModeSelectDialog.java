package cc.makeblock.makeblock.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class AirBlockModeSelectDialog
  extends Dialog
  implements View.OnClickListener
{
  private OnModeSelectListener onModeSelectListener;
  
  public AirBlockModeSelectDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    for (;;)
    {
      dismiss();
      return;
      this.onModeSelectListener.robotForm(4);
      continue;
      this.onModeSelectListener.robotForm(5);
      continue;
      this.onModeSelectListener.robotForm(7);
      continue;
      this.onModeSelectListener.robotForm(6);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    setContentView(2131427399);
    findViewById(2131296291).setOnClickListener(this);
    findViewById(2131296292).setOnClickListener(this);
    findViewById(2131296290).setOnClickListener(this);
    findViewById(2131296289).setOnClickListener(this);
  }
  
  public void setOnModeSelectListener(OnModeSelectListener paramOnModeSelectListener)
  {
    this.onModeSelectListener = paramOnModeSelectListener;
  }
  
  public static abstract interface OnModeSelectListener
  {
    public abstract void robotForm(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\AirBlockModeSelectDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */