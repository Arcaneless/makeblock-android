package cc.makeblock.makeblock.scene.playground.rj25;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout.LayoutParams;
import cc.makeblock.makeblock.base.PlaygroundActivity;
import cc.makeblock.makeblock.customview.UserGuideCoverView;
import cc.makeblock.makeblock.databinding.ActivityRangerMbotSpeakerBinding;
import cc.makeblock.makeblock.databinding.ActivityRangerSpeakerBinding;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.device.Ranger;
import cc.makeblock.makeblock.engine.utils.SharedPreferencesUtils;
import cc.makeblock.makeblock.viewmodel.DeviceViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.mbot.MBotSpeakerViewModel;
import cc.makeblock.makeblock.viewmodel.playground.rj25.ranger.RangerSpeakerViewModel;

public class SpeakerActivity
  extends PlaygroundActivity<DeviceViewModel>
{
  private MBotSpeakerViewModel mBotSpeakerViewModel;
  private RangerSpeakerViewModel rangerSpeakerViewModel;
  
  private void checkGuide()
  {
    if (!SharedPreferencesUtils.getHasShowSpeakerGuide(ControllerManager.getInstance().getChosenBoardName()))
    {
      UserGuideCoverView localUserGuideCoverView = new UserGuideCoverView(this);
      localUserGuideCoverView.setLayoutIds(new int[] { 2131427499 });
      addContentView(localUserGuideCoverView, new RelativeLayout.LayoutParams(-1, -1));
      SharedPreferencesUtils.setHasShowSpeakerGuide(ControllerManager.getInstance().getChosenBoardName());
    }
  }
  
  protected DeviceViewModel createViewModel()
  {
    if ((this.device instanceof MBot)) {
      return new MBotSpeakerViewModel(this, (MBot)this.device);
    }
    return new RangerSpeakerViewModel(this, (Ranger)this.device);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ControllerManager.getInstance().getCurrentDevice();
    if ((paramBundle instanceof MBot))
    {
      paramBundle = (ActivityRangerMbotSpeakerBinding)DataBindingUtil.setContentView(this, 2131427387);
      this.mBotSpeakerViewModel = ((MBotSpeakerViewModel)this.viewModel);
      paramBundle.setViewModel(this.mBotSpeakerViewModel);
      paramBundle.getRoot().findViewById(2131296681).setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          switch (paramAnonymousMotionEvent.getAction())
          {
          }
          for (;;)
          {
            return false;
            SpeakerActivity.this.mBotSpeakerViewModel.pressButton();
            continue;
            SpeakerActivity.this.mBotSpeakerViewModel.releaseButton();
          }
        }
      });
    }
    for (;;)
    {
      checkGuide();
      return;
      if ((paramBundle instanceof Ranger))
      {
        paramBundle = (ActivityRangerSpeakerBinding)DataBindingUtil.setContentView(this, 2131427389);
        this.rangerSpeakerViewModel = ((RangerSpeakerViewModel)this.viewModel);
        paramBundle.setViewModel(this.rangerSpeakerViewModel);
        paramBundle.getRoot().findViewById(2131296681).setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            switch (paramAnonymousMotionEvent.getAction())
            {
            }
            for (;;)
            {
              return false;
              SpeakerActivity.this.rangerSpeakerViewModel.pressButton();
              continue;
              SpeakerActivity.this.rangerSpeakerViewModel.releaseButton();
            }
          }
        });
      }
    }
  }
  
  protected void onDestroy()
  {
    if (this.mBotSpeakerViewModel != null) {
      this.mBotSpeakerViewModel.onDestroy();
    }
    if (this.rangerSpeakerViewModel != null) {
      this.rangerSpeakerViewModel.onDestroy();
    }
    super.onDestroy();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\playground\rj25\SpeakerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */