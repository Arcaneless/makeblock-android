package cc.makeblock.makeblock.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class SpeakerViewHelpDialog
  extends Dialog
{
  public SpeakerViewHelpDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    setContentView(2131427419);
    findViewById(2131296623).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SpeakerViewHelpDialog.this.dismiss();
      }
    });
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\dialog\SpeakerViewHelpDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */