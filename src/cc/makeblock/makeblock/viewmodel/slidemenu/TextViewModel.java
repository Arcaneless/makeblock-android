package cc.makeblock.makeblock.viewmodel.slidemenu;

import android.databinding.BaseObservable;

public class TextViewModel
  extends BaseObservable
{
  private String text;
  
  public TextViewModel(String paramString)
  {
    this.text = paramString;
  }
  
  public String getText()
  {
    return this.text;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\slidemenu\TextViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */