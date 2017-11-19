package cc.makeblock.makeblock.scene.panel;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import cc.makeblock.makeblock.bean.ExpandGuideData;
import cc.makeblock.makeblock.databinding.FragmentExpandGuideBinding;
import cc.makeblock.makeblock.viewmodel.ExpandGuideViewModel;

public class ExpandGuideDialogFragment
  extends DialogFragment
{
  private static final String KEY_DIRECTORY = "directory";
  private static final String KEY_GUIDE_DATA = "guide_data";
  private static final String KEY_NAME = "name";
  private String mDirectory;
  private ExpandGuideData mGuideData;
  private String mName;
  private ExpandGuideViewModel mViewModel;
  
  public static ExpandGuideDialogFragment newInstance(ExpandGuideData paramExpandGuideData, String paramString1, String paramString2)
  {
    ExpandGuideDialogFragment localExpandGuideDialogFragment = new ExpandGuideDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("guide_data", paramExpandGuideData);
    localBundle.putString("name", paramString1);
    localBundle.putString("directory", paramString2);
    localExpandGuideDialogFragment.setArguments(localBundle);
    return localExpandGuideDialogFragment;
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mViewModel.isClose.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback()
    {
      public void onPropertyChanged(Observable paramAnonymousObservable, int paramAnonymousInt)
      {
        if (((ObservableBoolean)paramAnonymousObservable).get()) {
          ExpandGuideDialogFragment.this.dismiss();
        }
      }
    });
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setStyle(0, 16973834);
    this.mGuideData = ((ExpandGuideData)getArguments().getSerializable("guide_data"));
    this.mName = getArguments().getString("name");
    this.mDirectory = getArguments().getString("directory");
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    getDialog().getWindow().requestFeature(1);
    getDialog().getWindow().setWindowAnimations(2131558835);
    paramLayoutInflater = (FragmentExpandGuideBinding)DataBindingUtil.inflate(paramLayoutInflater, 2131427424, paramViewGroup, false);
    this.mViewModel = new ExpandGuideViewModel(this.mGuideData, this.mName, this.mDirectory);
    paramLayoutInflater.setViewModel(this.mViewModel);
    return paramLayoutInflater.getRoot();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\panel\ExpandGuideDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */