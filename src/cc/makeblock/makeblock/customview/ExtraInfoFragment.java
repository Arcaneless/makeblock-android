package cc.makeblock.makeblock.customview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;

public class ExtraInfoFragment
  extends DialogFragment
{
  private int layoutId;
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setStyle(0, 16973834);
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramViewGroup = getDialog().getWindow();
    if (paramViewGroup != null) {
      paramViewGroup.requestFeature(1);
    }
    getDialog().getWindow().setWindowAnimations(2131558835);
    paramLayoutInflater = paramLayoutInflater.inflate(this.layoutId, null);
    paramLayoutInflater.findViewById(2131296489).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ExtraInfoFragment.this.dismiss();
      }
    });
    return paramLayoutInflater;
  }
  
  public void setLayoutId(int paramInt)
  {
    this.layoutId = paramInt;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\ExtraInfoFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */