package cc.makeblock.makeblock.customview.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.FitWidthTextView;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class ConfirmDialog
  extends MakeBlockDialog
  implements View.OnClickListener
{
  private String cancelText;
  private String confirmText;
  private String message;
  private OnOperationCanceledListener onOperationCanceledListener;
  private OnOperationConfirmedListener onOperationConfirmedListener;
  private String title;
  
  public ConfirmDialog(@NonNull Context paramContext)
  {
    super(paramContext);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131296379: 
    default: 
      return;
    case 2131296380: 
      this.onOperationConfirmedListener.onOperationConfirmed();
      dismiss();
      return;
    }
    if (this.onOperationCanceledListener != null) {
      this.onOperationCanceledListener.onOperationCancel();
    }
    dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getWindow() != null) {
      getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    setContentView(2131427401);
    paramBundle = findViewById(2131296380);
    View localView = findViewById(2131296378);
    paramBundle.setOnClickListener(this);
    localView.setOnClickListener(this);
    ((FitWidthTextView)findViewById(2131296439)).setText(this.title);
    if (this.message != null) {
      ((FitWidthTextView)findViewById(2131296436)).setText(this.message);
    }
    for (;;)
    {
      if (this.confirmText != null) {
        ((AutoResizeTextView)findViewById(2131296435)).setText(this.confirmText);
      }
      if (this.cancelText != null) {
        ((AutoResizeTextView)findViewById(2131296434)).setText(this.cancelText);
      }
      return;
      findViewById(2131296616).setVisibility(8);
      ((PercentRelativeLayout.LayoutParams)paramBundle.getLayoutParams()).addRule(3, 2131296617);
      ((PercentRelativeLayout.LayoutParams)localView.getLayoutParams()).addRule(3, 2131296617);
    }
  }
  
  public void setCancelText(String paramString)
  {
    this.cancelText = paramString;
  }
  
  public void setConfirmText(String paramString)
  {
    this.confirmText = paramString;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setOnOperationCanceledListener(OnOperationCanceledListener paramOnOperationCanceledListener)
  {
    this.onOperationCanceledListener = paramOnOperationCanceledListener;
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
  
  public static abstract interface OnOperationCanceledListener
  {
    public abstract void onOperationCancel();
  }
  
  public static abstract interface OnOperationConfirmedListener
  {
    public abstract void onOperationConfirmed();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\ConfirmDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */