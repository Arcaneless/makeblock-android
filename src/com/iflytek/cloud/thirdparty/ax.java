package com.iflytek.cloud.thirdparty;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

public class ax
  extends Dialog
{
  protected ay a = null;
  private a b = new a()
  {
    public void a()
    {
      ax.this.dismiss();
    }
  };
  
  public ax(Context paramContext)
  {
    super(paramContext);
  }
  
  public boolean destroy()
  {
    if (isShowing()) {
      return false;
    }
    boolean bool = this.a.d();
    this.a = null;
    return bool;
  }
  
  public void dismiss()
  {
    this.a.c();
    super.dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(this.a);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
  }
  
  public void show()
  {
    setCanceledOnTouchOutside(true);
    this.a.a(this.b);
    this.a.b();
    super.show();
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */