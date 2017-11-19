package cc.makeblock.makeblock.customview.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import cc.makeblock.makeblock.customview.FitSizeEditText;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;

public class SaveAsDialog
  extends MakeBlockDialog
  implements View.OnClickListener
{
  private PercentRelativeLayout confirmButton;
  private FitSizeEditText editText;
  public String initText;
  public OnConfirmListener onConfirmListener;
  
  public SaveAsDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public String getText()
  {
    return String.valueOf(this.editText.getText());
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296379: 
      this.editText.setText("");
      return;
    case 2131296380: 
      this.onConfirmListener.onConfirm(this.editText.getText().toString().trim());
      dismiss();
      return;
    }
    this.onConfirmListener.onCancel();
    dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getWindow() != null) {
      getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    setContentView(2131427418);
    this.confirmButton = ((PercentRelativeLayout)findViewById(2131296380));
    findViewById(2131296379).setOnClickListener(this);
    this.confirmButton.setOnClickListener(this);
    findViewById(2131296378).setOnClickListener(this);
    this.editText = ((FitSizeEditText)findViewById(2131296443));
    this.editText.setText(this.initText);
    this.editText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousCharSequence.length() == 0)
        {
          SaveAsDialog.this.confirmButton.setBackgroundResource(2131165411);
          SaveAsDialog.this.confirmButton.setClickable(false);
        }
        for (;;)
        {
          if (paramAnonymousCharSequence.length() >= 50)
          {
            paramAnonymousCharSequence = paramAnonymousCharSequence.subSequence(0, 49);
            SaveAsDialog.this.editText.setText(paramAnonymousCharSequence);
            SaveAsDialog.this.editText.setSelection(49);
          }
          return;
          SaveAsDialog.this.confirmButton.setBackgroundResource(2131165856);
          SaveAsDialog.this.confirmButton.setClickable(true);
        }
      }
    });
    Selection.setSelection(this.editText.getText(), this.editText.getText().length());
  }
  
  public void show()
  {
    super.show();
    if (getWindow() != null) {
      getWindow().setLayout(ScreenHelper.SCREEN_WIDTH, ScreenHelper.SCREEN_HEIGHT);
    }
  }
  
  public static abstract interface OnConfirmListener
  {
    public abstract void onCancel();
    
    public abstract void onConfirm(String paramString);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\SaveAsDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */