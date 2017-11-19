package cc.makeblock.makeblock.viewmodel.playground.rj25.mbot;

import android.content.Context;
import android.databinding.Bindable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import cc.makeblock.makeblock.base.BaseActivity;
import cc.makeblock.makeblock.customview.ExtraInfoFragment;
import cc.makeblock.makeblock.engine.action.Action;
import cc.makeblock.makeblock.engine.action.ActionBuilder;
import cc.makeblock.makeblock.engine.device.MBot;
import cc.makeblock.makeblock.engine.utils.HanziToPinyin;
import cc.makeblock.makeblock.engine.utils.HanziToPinyin.Token;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class MBotSpeakerViewModel
  extends MBotViewModel
{
  private static final int COMMAND_BACKWARD = 2;
  private static final int COMMAND_DANCE = 6;
  private static final int COMMAND_FORWARD = 1;
  private static final int COMMAND_LEFT = 3;
  private static final int COMMAND_RIGHT = 4;
  private static final int COMMAND_STOP = 5;
  private static final int COMMAND_UNRECOGNIZED = 0;
  private static final int STATE_CORRECT_RESULT = 3;
  private static final int STATE_LISTENING = 1;
  private static final int STATE_NO_INTERNET = 6;
  private static final int STATE_NO_PERMISSION = 5;
  private static final int STATE_PROCESSING = 2;
  private static final int STATE_UNRECOGNIZED = 4;
  private static final int STATE_WAITING_OPERATION = 0;
  private static final String TAG = "SpeakerViewModel";
  private String commandPinyin;
  private String commandText = "";
  private Context context;
  private int currentState = 0;
  private boolean hasRecognized = false;
  private SpeechRecognizer mAsr;
  private InitListener mInitListener = new InitListener()
  {
    public void onInit(int paramAnonymousInt)
    {
      Log.d("SpeakerViewModel", "SpeechRecognizer init() code = " + paramAnonymousInt);
      if (paramAnonymousInt != 0) {
        Log.e("SpeakerViewModel", "初始化失败，错误码：" + paramAnonymousInt);
      }
    }
  };
  private RecognizerListener recognizerListener = new RecognizerListener()
  {
    public void onBeginOfSpeech() {}
    
    public void onEndOfSpeech() {}
    
    public void onError(SpeechError paramAnonymousSpeechError)
    {
      if (MBotSpeakerViewModel.this.hasRecognized)
      {
        MBotSpeakerViewModel.access$402(MBotSpeakerViewModel.this, false);
        MBotSpeakerViewModel.this.setCurrentState(4);
      }
      do
      {
        return;
        if ((paramAnonymousSpeechError.toString().contains("20001")) || (paramAnonymousSpeechError.toString().contains("12404")))
        {
          MBotSpeakerViewModel.this.setCurrentState(6);
          return;
        }
        if (paramAnonymousSpeechError.toString().contains("10118"))
        {
          MBotSpeakerViewModel.this.setCurrentState(4);
          return;
        }
      } while (!paramAnonymousSpeechError.toString().contains("20006"));
      MBotSpeakerViewModel.this.setCurrentState(5);
    }
    
    public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, Bundle paramAnonymousBundle) {}
    
    public void onResult(RecognizerResult paramAnonymousRecognizerResult, boolean paramAnonymousBoolean)
    {
      paramAnonymousRecognizerResult = paramAnonymousRecognizerResult.getResultString();
      String str = JsonUtil.jsonToCommandText4Speaker(paramAnonymousRecognizerResult);
      if ((!paramAnonymousRecognizerResult.equalsIgnoreCase(".")) && (!paramAnonymousRecognizerResult.equalsIgnoreCase("。")) && (!paramAnonymousRecognizerResult.equalsIgnoreCase("")))
      {
        MBotSpeakerViewModel.access$002(MBotSpeakerViewModel.this, MBotSpeakerViewModel.this.commandPinyin + str);
        if (paramAnonymousBoolean)
        {
          if (Locale.getDefault().getLanguage().equalsIgnoreCase("zh")) {
            MBotSpeakerViewModel.access$002(MBotSpeakerViewModel.this, MBotSpeakerViewModel.this.getPinYin(MBotSpeakerViewModel.this.commandPinyin));
          }
          int i = MBotSpeakerViewModel.this.getCommand(MBotSpeakerViewModel.this.commandPinyin);
          MBotSpeakerViewModel.this.executeCommand(i);
          MBotSpeakerViewModel.access$402(MBotSpeakerViewModel.this, true);
          MBotSpeakerViewModel.access$002(MBotSpeakerViewModel.this, "");
        }
        return;
      }
      MBotSpeakerViewModel.this.setCurrentState(4);
    }
    
    public void onVolumeChanged(int paramAnonymousInt, byte[] paramAnonymousArrayOfByte) {}
  };
  
  public MBotSpeakerViewModel(Context paramContext, MBot paramMBot)
  {
    super(paramMBot);
    this.context = paramContext;
  }
  
  private Runnable createGoBackwardRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotSpeakerViewModel.this.device).moveJoystick(65281, 65281);
      }
    };
  }
  
  private Runnable createGoForwardRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotSpeakerViewModel.this.device).moveJoystick(255, 255);
      }
    };
  }
  
  private Runnable createStopRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotSpeakerViewModel.this.device).stop();
      }
    };
  }
  
  private Runnable createTurnLeftRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotSpeakerViewModel.this.device).moveJoystick(65281, 255);
      }
    };
  }
  
  private Runnable createTurnRightRunnable()
  {
    new Runnable()
    {
      public void run()
      {
        ((MBot)MBotSpeakerViewModel.this.device).moveJoystick(255, 65281);
      }
    };
  }
  
  private void executeCommand(int paramInt)
  {
    Action localAction = null;
    switch (paramInt)
    {
    }
    while (localAction != null)
    {
      setCurrentState(3);
      localAction.execute();
      return;
      this.commandText = TextUtil.getStringById(2131493352);
      localAction = new ActionBuilder().append(createGoForwardRunnable(), 0L).append(createStopRunnable(), 2000L).setCancelRunnable(createStopRunnable()).build();
      continue;
      this.commandText = TextUtil.getStringById(2131493350);
      localAction = new ActionBuilder().append(createGoBackwardRunnable(), 0L).append(createStopRunnable(), 2000L).setCancelRunnable(createStopRunnable()).build();
      continue;
      this.commandText = TextUtil.getStringById(2131493353);
      localAction = new ActionBuilder().append(createTurnLeftRunnable(), 0L).append(createStopRunnable(), 450L).setCancelRunnable(createStopRunnable()).build();
      continue;
      this.commandText = TextUtil.getStringById(2131493354);
      localAction = new ActionBuilder().append(createTurnRightRunnable(), 0L).append(createStopRunnable(), 450L).setCancelRunnable(createStopRunnable()).build();
      continue;
      this.commandText = TextUtil.getStringById(2131493355);
      localAction = new ActionBuilder().append(createStopRunnable(), 0L).build();
      continue;
      this.commandText = TextUtil.getStringById(2131493351);
      localAction = new ActionBuilder().append(createTurnLeftRunnable(), 0L).append(createStopRunnable(), 2000L).setCancelRunnable(createStopRunnable()).build();
    }
    setCurrentState(4);
  }
  
  private int getCommand(String paramString)
  {
    if ((paramString.contains("QIAN")) || (paramString.contains("forward")) || (paramString.contains("up"))) {
      return 1;
    }
    if ((paramString.contains("HOU")) || (paramString.contains("backward")) || (paramString.contains("back"))) {
      return 2;
    }
    if ((paramString.contains("ZUO")) || (paramString.contains("left"))) {
      return 3;
    }
    if ((paramString.contains("YOU")) || (paramString.contains("right"))) {
      return 4;
    }
    if ((paramString.contains("TIN")) || (paramString.contains("TING")) || (paramString.contains("onStop")) || (paramString.contains("down"))) {
      return 5;
    }
    if ((paramString.contains("WU")) || (paramString.contains("dance")) || (paramString.contains("dancing"))) {
      return 6;
    }
    return 0;
  }
  
  private String getPinYin(String paramString)
  {
    Object localObject = HanziToPinyin.getInstance().get(paramString);
    paramString = new StringBuilder();
    if ((localObject != null) && (((ArrayList)localObject).size() > 0))
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        HanziToPinyin.Token localToken = (HanziToPinyin.Token)((Iterator)localObject).next();
        if (2 == localToken.type) {
          paramString.append(localToken.target);
        } else {
          paramString.append(localToken.source);
        }
      }
    }
    return paramString.toString().toUpperCase();
  }
  
  private void setCurrentState(int paramInt)
  {
    this.currentState = paramInt;
    notifyChange();
  }
  
  @Bindable
  public boolean getCorrectResultTextVisibility()
  {
    return this.currentState == 3;
  }
  
  @Bindable
  public boolean getListeningTextVisibility()
  {
    return this.currentState == 1;
  }
  
  @Bindable
  public boolean getNoInternetTextVisibility()
  {
    return this.currentState == 6;
  }
  
  @Bindable
  public boolean getNoPermissionTextVisibility()
  {
    return this.currentState == 5;
  }
  
  @Bindable
  public boolean getProcessingTextVisibility()
  {
    return this.currentState == 2;
  }
  
  @Bindable
  public String getRecognizedText()
  {
    return this.commandText;
  }
  
  @Bindable
  public int getSpeakButtonBackground()
  {
    if ((this.currentState == 2) || (this.currentState == 6) || (this.currentState == 5)) {
      return 2131165921;
    }
    return 2131165913;
  }
  
  @Bindable
  public boolean getUnrecognizeTextVisibility()
  {
    return this.currentState == 4;
  }
  
  @Bindable
  public boolean getWaitingTextVisibility()
  {
    return this.currentState == 0;
  }
  
  @Bindable
  public boolean getWavePlayAnimation()
  {
    return (this.currentState == 1) || (this.currentState == 2);
  }
  
  public void onDestroy()
  {
    if (this.mAsr != null)
    {
      this.mAsr.stopListening();
      this.mAsr.destroy();
    }
  }
  
  public void openExtraInfo()
  {
    ExtraInfoFragment localExtraInfoFragment = new ExtraInfoFragment();
    localExtraInfoFragment.setLayoutId(2131427500);
    localExtraInfoFragment.show(((BaseActivity)this.context).getSupportFragmentManager(), "extra");
  }
  
  public void pressButton()
  {
    if (this.currentState == 2) {
      return;
    }
    setCurrentState(1);
    this.mAsr = SpeechRecognizer.createRecognizer(this.context, this.mInitListener);
    this.mAsr.setParameter("engine_type", "cloud");
    this.mAsr.setParameter("domain", "iat");
    if (Locale.getDefault().getLanguage().equalsIgnoreCase("zh")) {
      this.mAsr.setParameter("language", "zh_cn");
    }
    for (;;)
    {
      this.mAsr.setParameter("asr_audio_path", Environment.getExternalStorageDirectory() + "/msc/asr.wav");
      this.mAsr.setParameter("result_type", "json");
      this.mAsr.setParameter("mixed_threshold", "30");
      int i = this.mAsr.startListening(this.recognizerListener);
      if (i == 0) {
        break;
      }
      Log.e("SpeakerViewModel", "识别失败,错误码: " + i);
      return;
      this.mAsr.setParameter("language", "en_us");
    }
  }
  
  public void releaseButton()
  {
    if (this.currentState == 1)
    {
      setCurrentState(2);
      this.mAsr.stopListening();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\mbot\MBotSpeakerViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */