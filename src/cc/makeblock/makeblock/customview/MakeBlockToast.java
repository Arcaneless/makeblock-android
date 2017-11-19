package cc.makeblock.makeblock.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class MakeBlockToast
  extends RelativeLayout
{
  private FitWidthTextView textView;
  private Toast toast;
  
  public MakeBlockToast(Context paramContext)
  {
    super(paramContext);
    this.toast = new Toast(paramContext);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    paramContext = ((Activity)paramContext).getLayoutInflater().inflate(2131427532, this);
    this.textView = ((FitWidthTextView)paramContext.findViewById(2131296727));
    paramContext.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    this.toast.setView(this);
    this.toast.setDuration(0);
    this.toast.setGravity(17, 0, 0);
  }
  
  public void cancel()
  {
    this.toast.cancel();
  }
  
  public void cancelWithDelay(int paramInt)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (MakeBlockToast.this.toast != null) {
          MakeBlockToast.this.toast.cancel();
        }
      }
    }, paramInt);
  }
  
  public void setText(String paramString)
  {
    this.textView.setText(paramString);
  }
  
  public void show()
  {
    this.toast.show();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\MakeBlockToast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */