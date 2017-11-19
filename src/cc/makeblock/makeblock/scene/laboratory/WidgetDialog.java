package cc.makeblock.makeblock.scene.laboratory;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.percent.PercentRelativeLayout;
import android.support.percent.PercentRelativeLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import cc.makeblock.makeblock.databinding.DialogLaboratoryWidgetBinding;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import cc.makeblock.makeblock.viewmodel.laboratory.WidgetDialogViewModel;

public class WidgetDialog
  extends Dialog
{
  private int contentId;
  private Context context;
  private PercentRelativeLayout dialogContainer;
  private OnWidgetSelectListener onWidgetSelectListener;
  
  public WidgetDialog(@NonNull Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }
  
  public void onBackPressed()
  {
    dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getWindow() != null) {
      getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    paramBundle = (DialogLaboratoryWidgetBinding)DataBindingUtil.inflate(LayoutInflater.from(this.context), 2131427416, null, false);
    paramBundle.setViewModel(new WidgetDialogViewModel(this));
    paramBundle.getViewModel().setLayoutContentId(this.contentId);
    setContentView(paramBundle.getRoot());
    this.dialogContainer = ((PercentRelativeLayout)findViewById(2131296772));
    if (this.contentId != 0)
    {
      paramBundle = LayoutInflater.from(this.context).inflate(this.contentId, null);
      paramBundle.setLayoutParams(new PercentRelativeLayout.LayoutParams(-1, -1));
      this.dialogContainer.addView(paramBundle);
    }
    findViewById(2131296441).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WidgetDialog.this.dismiss();
      }
    });
  }
  
  public void onSelectWidget(int paramInt)
  {
    if (this.onWidgetSelectListener != null)
    {
      this.onWidgetSelectListener.onWidgetSelect(paramInt);
      dismiss();
    }
  }
  
  public void setOnWidgetSelectListener(OnWidgetSelectListener paramOnWidgetSelectListener)
  {
    this.onWidgetSelectListener = paramOnWidgetSelectListener;
  }
  
  public void setWidgetLayout(int paramInt)
  {
    this.contentId = paramInt;
  }
  
  public void show()
  {
    super.show();
    if (getWindow() != null) {
      getWindow().setLayout(ScreenHelper.SCREEN_WIDTH, ScreenHelper.SCREEN_HEIGHT);
    }
  }
  
  public static abstract interface OnWidgetSelectListener
  {
    public abstract void onWidgetSelect(int paramInt);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\laboratory\WidgetDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */