package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cc.makeblock.makeblock.base.ToastUtil;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.dialog.SpeakerViewHelpDialog;
import cc.makeblock.makeblock.engine.utils.HanziToPinyin;
import cc.makeblock.makeblock.engine.utils.HanziToPinyin.Token;
import cc.makeblock.makeblock.engine.utils.JsonUtil;
import cc.makeblock.makeblock.engine.utils.ScreenHelper;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class SpeakerView
  extends CellView<SpeakerState>
{
  public static final int COMMAND_BACKWARD = 2;
  public static final int COMMAND_DANCE = 6;
  public static final int COMMAND_FORWARD = 1;
  public static final int COMMAND_LEFT = 3;
  public static final int COMMAND_RIGHT = 4;
  public static final int COMMAND_STOP = 5;
  public static final int COMMAND_UNRECOGNIZED = 0;
  private static final String TAG = SpeakerView.class.getSimpleName();
  private String commandText;
  private boolean hasRecognized = false;
  ImageView image_bg;
  ImageView image_mic;
  ImageView image_rotate_a;
  ImageView image_rotate_b;
  private SpeechRecognizer mAsr;
  Context mContext;
  private InitListener mInitListener = new InitListener()
  {
    public void onInit(int paramAnonymousInt)
    {
      Log.d(SpeakerView.TAG, "SpeechRecognizer init() code = " + paramAnonymousInt);
      if (paramAnonymousInt != 0) {
        Log.e(SpeakerView.TAG, "初始化失败，错误码：" + paramAnonymousInt);
      }
    }
  };
  private OnSpeakerStateSendListener onSpeakerStateSendListener;
  private RecognizerListener recognizerListener = new RecognizerListener()
  {
    public void onBeginOfSpeech() {}
    
    public void onEndOfSpeech() {}
    
    public void onError(SpeechError paramAnonymousSpeechError)
    {
      if (SpeakerView.this.hasRecognized) {
        SpeakerView.access$602(SpeakerView.this, false);
      }
      do
      {
        return;
        if ((paramAnonymousSpeechError.toString().contains("20001")) || (paramAnonymousSpeechError.toString().contains("12404")))
        {
          SpeakerView.this.toastInfo(SpeakerView.this.mContext.getString(2131493004));
          return;
        }
      } while (!paramAnonymousSpeechError.toString().contains("10118"));
      SpeakerView.this.toastInfo(SpeakerView.this.mContext.getString(2131493092));
    }
    
    public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, Bundle paramAnonymousBundle) {}
    
    public void onResult(RecognizerResult paramAnonymousRecognizerResult, boolean paramAnonymousBoolean)
    {
      paramAnonymousRecognizerResult = paramAnonymousRecognizerResult.getResultString();
      String str = JsonUtil.jsonToCommandText4Speaker(paramAnonymousRecognizerResult);
      if ((!paramAnonymousRecognizerResult.equalsIgnoreCase(".")) && (!paramAnonymousRecognizerResult.equalsIgnoreCase("。")) && (!paramAnonymousRecognizerResult.equalsIgnoreCase("")))
      {
        SpeakerView.access$102(SpeakerView.this, SpeakerView.this.commandText + str);
        if (paramAnonymousBoolean)
        {
          if (Locale.getDefault().getLanguage().equalsIgnoreCase("zh")) {
            SpeakerView.access$102(SpeakerView.this, SpeakerView.getPinYin(SpeakerView.this.commandText));
          }
          int i = SpeakerView.this.getCommand(SpeakerView.this.commandText);
          SpeakerView.this.toastInfo(SpeakerView.access$300(SpeakerView.this, i));
          SpeakerView.this.pushState(new SpeakerView.SpeakerState(SpeakerView.this, i));
          SpeakerView.access$602(SpeakerView.this, true);
          SpeakerView.access$102(SpeakerView.this, "");
          i = SpeakerView.this.mAsr.startListening(SpeakerView.this.recognizerListener);
          if (i != 0) {
            Log.e(SpeakerView.TAG, "识别失败,错误码: " + i);
          }
        }
      }
    }
    
    public void onVolumeChanged(int paramAnonymousInt, byte[] paramAnonymousArrayOfByte) {}
  };
  TextView tv_title;
  
  public SpeakerView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initView(null, 0);
  }
  
  public SpeakerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initView(paramAttributeSet, 0);
  }
  
  public SpeakerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    initView(paramAttributeSet, paramInt);
  }
  
  private void endListeningAnimation()
  {
    this.image_rotate_a.clearAnimation();
    this.image_rotate_b.clearAnimation();
    this.image_rotate_a.setVisibility(4);
    this.image_rotate_b.setVisibility(4);
  }
  
  private int getCommand(String paramString)
  {
    if ((paramString.contains("QIAN")) || (paramString.contains("forward")) || (paramString.contains("up"))) {
      return 1;
    }
    if ((paramString.contains("HOU")) || (paramString.contains("backward"))) {
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
  
  public static String getPinYin(String paramString)
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
  
  private String getToastMessage(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return this.mContext.getString(2131493352);
    case 2: 
      return this.mContext.getString(2131493350);
    case 3: 
      return this.mContext.getString(2131493353);
    case 4: 
      return this.mContext.getString(2131493354);
    case 5: 
      return this.mContext.getString(2131493355);
    case 6: 
      return this.mContext.getString(2131493351);
    }
    return this.mContext.getString(2131493092);
  }
  
  private void setSpeakerTouched(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      startListeningAnimation();
      setParam();
      int i = this.mAsr.startListening(this.recognizerListener);
      if (i != 0) {
        Log.e(TAG, "识别失败,错误码: " + i);
      }
      return;
    }
    pushState(new SpeakerState(5));
    endListeningAnimation();
    this.mAsr.stopListening();
  }
  
  private void startListeningAnimation()
  {
    this.image_rotate_a.setVisibility(0);
    this.image_rotate_b.setVisibility(0);
    Animation localAnimation = AnimationUtils.loadAnimation(this.mContext, 2130771993);
    this.image_rotate_a.startAnimation(localAnimation);
    localAnimation = AnimationUtils.loadAnimation(this.mContext, 2130771992);
    this.image_rotate_b.startAnimation(localAnimation);
  }
  
  private void toastInfo(String paramString)
  {
    ToastUtil.showToast(paramString);
  }
  
  public boolean getPortSelectable()
  {
    return false;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  public void initContent()
  {
    this.image_bg.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          return true;
        case 0: 
          SpeakerView.this.setSpeakerTouched(true);
          return true;
        }
        SpeakerView.this.setSpeakerTouched(false);
        return true;
      }
    });
    this.mAsr = SpeechRecognizer.createRecognizer(this.mContext, this.mInitListener);
  }
  
  public void initView(AttributeSet paramAttributeSet, int paramInt)
  {
    addView(((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427562, null), new FrameLayout.LayoutParams(-1, -1));
    this.image_mic = ((ImageView)findViewById(2131296778));
    this.image_bg = ((ImageView)findViewById(2131296775));
    this.image_rotate_a = ((ImageView)findViewById(2131296776));
    this.image_rotate_b = ((ImageView)findViewById(2131296777));
    this.tv_title = ((TextView)findViewById(2131296745));
    findViewById(2131296779).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new SpeakerViewHelpDialog(paramAnonymousView.getContext());
        paramAnonymousView.show();
        paramAnonymousView.getWindow().setLayout(ScreenHelper.getPercentWidthToPx(0.36458334F), ScreenHelper.getPercentWidthToPx(0.20833333F));
      }
    });
    this.image_mic.setSelected(false);
    this.image_bg.setSelected(false);
    this.image_rotate_a.setVisibility(4);
    this.image_rotate_b.setVisibility(4);
    setLayerType(1, null);
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.onSpeakerStateSendListener = null;
    if (this.mAsr != null)
    {
      this.mAsr.stopListening();
      this.mAsr.destroy();
    }
  }
  
  protected void sendState(SpeakerState paramSpeakerState)
  {
    if (this.onSpeakerStateSendListener != null) {
      this.onSpeakerStateSendListener.onSpeakerStateSend(paramSpeakerState.command);
    }
  }
  
  public void setControlListener() {}
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 1) {
      initContent();
    }
    while (this.mAsr == null) {
      return;
    }
    this.mAsr.stopListening();
    this.mAsr.destroy();
  }
  
  public void setOnSpeakerStateSendListener(OnSpeakerStateSendListener paramOnSpeakerStateSendListener)
  {
    this.onSpeakerStateSendListener = paramOnSpeakerStateSendListener;
  }
  
  public void setParam()
  {
    this.mAsr = SpeechRecognizer.createRecognizer(getContext(), this.mInitListener);
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
      return;
      this.mAsr.setParameter("language", "en_us");
    }
  }
  
  public static abstract interface OnSpeakerStateSendListener
  {
    public abstract void onSpeakerStateSend(int paramInt);
  }
  
  public class SpeakerState
  {
    public int command;
    
    public SpeakerState(int paramInt)
    {
      this.command = paramInt;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\SpeakerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */