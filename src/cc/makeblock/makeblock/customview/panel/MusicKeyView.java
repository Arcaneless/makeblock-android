package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.AutoResizeTextView;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.cell.CellView.OnWidgetTriggeredListener;
import cc.makeblock.makeblock.engine.protocol.web.send.BaseJsonObject;

public class MusicKeyView
  extends CellView<MusicKeyState>
  implements View.OnTouchListener
{
  private final String TAG = MusicKeyView.class.getSimpleName();
  private volatile boolean keyIsClickable;
  private Handler mHandler = new Handler();
  private MusicKeyViewListener musicKeyViewListener;
  private AutoResizeTextView tv_title;
  
  public MusicKeyView(Context paramContext)
  {
    super(paramContext);
    initViews();
  }
  
  public MusicKeyView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViews();
  }
  
  public MusicKeyView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initViews();
  }
  
  public boolean getPortSelectable()
  {
    return false;
  }
  
  public boolean getProgrammable()
  {
    return false;
  }
  
  public void initViews()
  {
    View localView = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427552, null);
    localView.findViewById(2131296532).setOnTouchListener(this);
    localView.findViewById(2131296536).setOnTouchListener(this);
    localView.findViewById(2131296535).setOnTouchListener(this);
    localView.findViewById(2131296533).setOnTouchListener(this);
    localView.findViewById(2131296538).setOnTouchListener(this);
    localView.findViewById(2131296534).setOnTouchListener(this);
    localView.findViewById(2131296537).setOnTouchListener(this);
    this.tv_title = ((AutoResizeTextView)localView.findViewById(2131296745));
    addView(localView, new FrameLayout.LayoutParams(-1, -1));
    this.keyIsClickable = true;
    setControlListener();
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.tv_title.setText(this.widgetData.name);
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if (i == 0) {
      switch (paramView.getId())
      {
      default: 
        paramMotionEvent = "C5";
        if (this.keyIsClickable)
        {
          this.musicKeyViewListener.onClick(new MusicKeyState(paramMotionEvent));
          this.keyIsClickable = false;
          this.mHandler.postDelayed(new Runnable()
          {
            public void run()
            {
              MusicKeyView.access$002(MusicKeyView.this, true);
            }
          }, 250L);
        }
        paramView.setSelected(true);
      }
    }
    while (i != 1) {
      for (;;)
      {
        return true;
        paramMotionEvent = "C5";
        continue;
        paramMotionEvent = "D5";
        continue;
        paramMotionEvent = "E5";
        continue;
        paramMotionEvent = "F5";
        continue;
        paramMotionEvent = "G5";
        continue;
        paramMotionEvent = "A5";
        continue;
        paramMotionEvent = "B5";
      }
    }
    paramView.setSelected(false);
    return true;
  }
  
  protected void sendState(MusicKeyState paramMusicKeyState)
  {
    this.mOnWidgetTriggeredListener.onWidgetTrigger("setTone", paramMusicKeyState.toString());
  }
  
  public void setControlListener()
  {
    setMusicKeyViewListener(new MusicKeyViewListener()
    {
      public void onClick(MusicKeyView.MusicKeyState paramAnonymousMusicKeyState)
      {
        MusicKeyView.this.pushState(paramAnonymousMusicKeyState);
      }
    });
  }
  
  public void setMusicKeyViewListener(MusicKeyViewListener paramMusicKeyViewListener)
  {
    this.musicKeyViewListener = paramMusicKeyViewListener;
  }
  
  class MusicKeyState
    extends BaseJsonObject
  {
    String toneName;
    
    MusicKeyState(String paramString)
    {
      this.toneName = paramString;
    }
  }
  
  static abstract interface MusicKeyViewListener
  {
    public abstract void onClick(MusicKeyView.MusicKeyState paramMusicKeyState);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\MusicKeyView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */